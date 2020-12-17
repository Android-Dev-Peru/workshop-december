package workshop.android.recyclerviewadapters.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import workshop.android.recyclerviewadapters.databinding.SettingItemViewBinding
import workshop.android.recyclerviewadapters.model.Creature

class SimpleAdapter: RecyclerView.Adapter<SimpleAdapter.TextSimpleViewHolder>(){

    var data = listOf<Creature>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextSimpleViewHolder {
        val binding = SettingItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TextSimpleViewHolder(binding.root)
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: TextSimpleViewHolder, position: Int) {
        val item = data[position]
        holder.name.text = item.firstName
    }

    class TextSimpleViewHolder(val name: TextView): RecyclerView.ViewHolder(name)

}