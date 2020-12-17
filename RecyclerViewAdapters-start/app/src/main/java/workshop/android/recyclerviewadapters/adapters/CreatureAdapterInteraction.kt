package workshop.android.recyclerviewadapters.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_creature.view.*
import workshop.android.recyclerviewadapters.R
import workshop.android.recyclerviewadapters.model.Creature
import workshop.android.recyclerviewadapters.util.inflate
import workshop.android.recyclerviewadapters.ui.CreatureActivity
import workshop.android.recyclerviewadapters.ui.interactions.ItemTouchHelperListener
import java.util.*


class CreatureAdapterInteraction(private val creatures: MutableList<Creature>, private val listener: (Creature) -> Unit):
    RecyclerView.Adapter<CreatureAdapterInteraction.ViewHolder>(), ItemTouchHelperListener {

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


        fun bind(creature: Creature) {
            this.creature = creature
            val context = itemView.context
            itemView.creatureImage.setImageResource(
                context.resources.getIdentifier(creature.uri, null, context.packageName))
            itemView.nickname.text = creature.nickname
            itemView.fullName.text = creature.fullName
        }

        override fun onClick(view: View?) {
            view?.let {
                val context = it.context
                val intent = CreatureActivity.newIntent(context, creature.id)
                context.startActivity(intent)
            }
        }

    }

    fun updateCreatures(creatures: List<Creature>) {
        this.creatures.clear()
        this.creatures.addAll(creatures)
        notifyDataSetChanged()
    }

    override fun onItemMove(
        recyclerView: RecyclerView,
        fromPosition: Int,
        toPosition: Int
    ): Boolean {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(creatures, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(creatures, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
        return true
    }
}