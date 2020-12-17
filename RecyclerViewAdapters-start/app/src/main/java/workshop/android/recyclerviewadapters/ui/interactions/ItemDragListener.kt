package workshop.android.recyclerviewadapters.ui.interactions

import androidx.recyclerview.widget.RecyclerView

interface ItemDragListener {
    fun onItemDrag(viewHolder: RecyclerView.ViewHolder)
}