package workshop.android.recyclerviewadapters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import workshop.android.recyclerviewadapters.adapters.CreatureCardAdapter
import workshop.android.recyclerviewadapters.databinding.FragmentAllCreatureBinding
import workshop.android.recyclerviewadapters.model.CreatureStore

class AllCreatureFragment : Fragment() {

    // 2
    private val adapter = CreatureCardAdapter(CreatureStore.getCreatures().toMutableList())

   //1
   // private val adapter = CreatureAdapter(CreatureStore.getCreatures().toMutableList())


    private var _binding: FragmentAllCreatureBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        fun newInstance(): AllCreatureFragment {
            return AllCreatureFragment()
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
      //  val layoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)

            val layoutManager = GridLayoutManager(context,2)

            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if ((position + 1) % 5 == 0) 2 else 1
                }
            }
                binding.creatureRecyclerView.layoutManager = layoutManager

            binding.creatureRecyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}