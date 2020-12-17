package workshop.android.recyclerviewadapters.setting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import workshop.android.recyclerviewadapters.R
import workshop.android.recyclerviewadapters.data.Setting
import workshop.android.recyclerviewadapters.databinding.ItemCalendarBinding
import workshop.android.recyclerviewadapters.util.TextViewHolder
import workshop.android.recyclerviewadapters.util.inflate

class SettingsAdapter : RecyclerView.Adapter<SettingsAdapter.ViewHolder>() {

    var data = listOf<Setting>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val view = parent.inflate(R.layout.item_calendar)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.contact.text = item.description
        holder.statusCheck.isChecked = item.status
        holder.statusImage.setImageResource(when(item.type){
            0 -> R.drawable.ic_baseline_perm_contact_calendar_24
            1 -> R.drawable.ic_baseline_calendar_today_24
            else -> R.drawable.ic_baseline_arrow_forward_ios_24
        })
    }


 class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
    private val binding = ItemCalendarBinding.bind(itemView)
     val statusImage: ImageView = binding.statusImage
     val contact : TextView = binding.contact
     val statusCheck : CheckBox = binding.statusCheckBox

 }
}