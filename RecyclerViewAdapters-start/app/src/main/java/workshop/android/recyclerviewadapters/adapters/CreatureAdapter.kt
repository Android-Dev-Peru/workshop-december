package workshop.android.recyclerviewadapters.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import workshop.android.recyclerviewadapters.R
import workshop.android.recyclerviewadapters.databinding.ListItemCreatureBinding
import workshop.android.recyclerviewadapters.model.Creature
import workshop.android.recyclerviewadapters.util.inflate
import workshop.android.recyclerviewadapters.ui.CreatureActivity

class CreatureAdapter(private val creatures: List<Creature>,private val listener: (Creature) -> Unit):
    RecyclerView.Adapter<CreatureAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item_creature))
    }

    override fun getItemCount() = creatures.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val creature = creatures[position]
        holder.bind(creature)
        holder.itemView.setOnClickListener { listener(creature) }
    }

    class ViewHolder(itemView: View) : View.OnClickListener, RecyclerView.ViewHolder(itemView) {

        private lateinit var creature: Creature

        private val binding = ListItemCreatureBinding.bind(itemView)

        fun bind(creature: Creature) {
            this.creature = creature
            val context = itemView.context
            binding.creatureImage.setImageResource(
                context.resources.getIdentifier(creature.uri, null, context.packageName))
            binding.nickname.text = creature.nickname
            binding.fullName.text = creature.fullName
        }

        override fun onClick(view: View?) {
            view?.let {
                val context = it.context
                val intent = CreatureActivity.newIntent(context, creature.id)
                context.startActivity(intent)
            }
        }
    }

}