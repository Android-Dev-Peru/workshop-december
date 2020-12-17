package workshop.android.recyclerviewadapters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import workshop.android.recyclerviewadapters.databinding.FragmentAllCreatureBinding
import workshop.android.recyclerviewadapters.model.Creature


class FavoriteCreatureFragment : Fragment() {


    private var _binding: FragmentAllCreatureBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        fun newInstance(): FavoriteCreatureFragment {
            return FavoriteCreatureFragment()
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

    private fun goToActivity(creature: Creature) {
        val intent = context?.let { context -> CreatureActivity.newIntent(context, creature.id) }
        context?.startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


