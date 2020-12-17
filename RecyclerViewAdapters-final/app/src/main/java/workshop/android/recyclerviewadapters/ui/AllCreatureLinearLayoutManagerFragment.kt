package workshop.android.recyclerviewadapters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import workshop.android.recyclerviewadapters.R
import workshop.android.recyclerviewadapters.adapters.CreatureAdapterInteraction
import workshop.android.recyclerviewadapters.adapters.CreatureAdapterViewType
import workshop.android.recyclerviewadapters.databinding.FragmentAllCreatureBinding
import workshop.android.recyclerviewadapters.model.Creature
import workshop.android.recyclerviewadapters.model.CreatureStore
import workshop.android.recyclerviewadapters.ui.decorator.DividerItemDecoration
import workshop.android.recyclerviewadapters.ui.interactions.ItemTouchHelperCallback

class AllCreatureLinearLayoutManagerFragment : Fragment() {

   // private lateinit var  adapter: CreatureAdapterViewType
     private lateinit var adapter : CreatureAdapterInteraction

    private var _binding: FragmentAllCreatureBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        fun newInstance(): AllCreatureLinearLayoutManagerFragment {
            return AllCreatureLinearLayoutManagerFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllCreatureBinding.inflate(inflater, container, false)
        return binding.root

    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            adapter = CreatureAdapterInteraction(mutableListOf()){goToActivity(it) }

            val layoutManager = LinearLayoutManager(context)

            binding.creatureRecyclerView.layoutManager = layoutManager

            binding.creatureRecyclerView.adapter = adapter

            val heightInPixels = resources.getDimensionPixelSize(R.dimen.list_item_divider_height)

            context?.let {
                binding.creatureRecyclerView.addItemDecoration(
                    DividerItemDecoration(
                        ContextCompat.getColor(it, R.color.black), heightInPixels))
            }

            setupItemTouchHelper()
    }

    private fun goToActivity(creature: Creature){
        val intent = context?.let { context -> CreatureActivity.newIntent(context, creature.id) }
        context?.startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupItemTouchHelper() {
        val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
        itemTouchHelper.attachToRecyclerView(binding.creatureRecyclerView)
    }


    override fun onResume() {
        super.onResume()
        activity?.let {
            CreatureStore.getFavoriteCreatures(it)?.let {
                    favorites ->
                adapter.updateCreatures(favorites)
            }
        }
        /*
        activity?.let {
            val creatures = CreatureStore.getFavoriteComposites(it)
            creatures?.let { compositeList ->
                adapter.updateCreatures(compositeList)
            }
        }
         */
    }
}
