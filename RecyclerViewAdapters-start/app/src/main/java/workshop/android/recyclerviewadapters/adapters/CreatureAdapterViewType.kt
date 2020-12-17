package workshop.android.recyclerviewadapters.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_creature.view.*
import kotlinx.android.synthetic.main.list_item_planet_header.view.*
import workshop.android.recyclerviewadapters.databinding.ListItemCreatureBinding
import workshop.android.recyclerviewadapters.databinding.ListItemPlanetHeaderBinding
import workshop.android.recyclerviewadapters.model.CompositeItem
import workshop.android.recyclerviewadapters.model.Creature

//#2
class CreatureAdapterViewType(private val composites: MutableList<CompositeItem>, private val listener: (Creature) -> Unit):
    RecyclerView.Adapter<CreatureAdapterViewType.ViewHolder>()  {

    //#0
    enum class ViewType {
        HEADER,CREATURE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = when(viewType){
            // #1
            ViewType.CREATURE.ordinal ->
                ListItemCreatureBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            ViewType.HEADER.ordinal ->
                ListItemPlanetHeaderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            else -> throw IllegalArgumentException()
        }

        return ViewHolder(binding.root)
    }

    override fun getItemCount() = composites.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val composite = composites[position]

        holder.bind(composite)

        holder.itemView.setOnClickListener { listener(composite.creature) }
    }

    override fun getItemViewType(position: Int): Int {
        //#2
        return if (composites[position].isHeader){
            ViewType.HEADER.ordinal
        }
        else {
            ViewType.CREATURE.ordinal
        }
    }


    class ViewHolder(view: View) :  RecyclerView.ViewHolder(view) {
        private lateinit var creature: Creature
        fun bind(composite: CompositeItem) {
            if(composite.isHeader){
                itemView.headerName.text = composite.header.name
            }
            else {
                val context = itemView.context
                creature = composite.creature
                itemView.creatureImage.setImageResource(
                    context.resources.getIdentifier(creature.uri, null, context.packageName))
                itemView.nickname.text = creature.nickname
                itemView.fullName.text = creature.fullName
            }
        }

    }

    fun updateCreatures(creatures: List<CompositeItem>) {
        this.composites.clear()
        this.composites.addAll(creatures)
        notifyDataSetChanged()
    }

}