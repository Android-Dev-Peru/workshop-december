package workshop.android.recyclerviewadapters.setting

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import workshop.android.recyclerviewadapters.R
import workshop.android.recyclerviewadapters.model.Creature
import workshop.android.recyclerviewadapters.util.TextViewHolder

class SettingsAdapterAnt : RecyclerView.Adapter<TextViewHolder>() {

    var data = listOf<Creature>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.setting_item_view,parent,false) as TextView
        return TextViewHolder(view)
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        val item = data[position]
        holder.description.text = item.firstName
    }

}