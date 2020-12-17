package workshop.android.recyclerviewadapters.ui.interactions
import androidx.recyclerview.widget.RecyclerView

interface ItemTouchHelperListener {
    fun onItemMove(recyclerView: RecyclerView, fromPosition: Int, toPosition: Int): Boolean
}