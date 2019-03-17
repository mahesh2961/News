package `in`.news.ui.news.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Class to provide equals margin between Items in [RecyclerView]
 */
class MarginItemDecoration() : RecyclerView.ItemDecoration() {

    //Todo Move this to dimen
    private val spaceHorizontal: Int=50
    private val spaceVertical: Int=25


    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = spaceHorizontal
            }
            bottom = spaceHorizontal
            left=spaceVertical
            right=spaceVertical
        }
    }
}