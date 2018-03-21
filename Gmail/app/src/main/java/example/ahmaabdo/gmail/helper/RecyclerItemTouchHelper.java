package example.ahmaabdo.gmail.helper;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import example.ahmaabdo.gmail.adapters.MessagesAdapter;

/**
 * Created by Ahmad on Mar 21, 2018.
 * https://www.androidhive.info/2017/09/android-recyclerview-swipe-delete-undo-using-itemtouchhelper/
 */

public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {
    private RecyclerItemTouchHelperListener listener;

    public RecyclerItemTouchHelper(int dragDirs, int swipeDirs, RecyclerItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null) {
            final View foregroundView = ((MessagesAdapter.MyViewHolder) viewHolder).viewForeground;
            //will be used by ItemTouchHelper to detect whenever there is UI change on the view.
            getDefaultUIUtil().onSelected(foregroundView);
        }
    }

    // the x-position of the foreground view is changed while user is swiping the view.
    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((MessagesAdapter.MyViewHolder) viewHolder).viewForeground;
        //will be used by ItemTouchHelper to detect whenever there is UI change on the view.
        getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final View foregroundView = ((MessagesAdapter.MyViewHolder) viewHolder).viewForeground;
        //will be used by ItemTouchHelper to detect whenever there is UI change on the view.
        getDefaultUIUtil().clearView(foregroundView);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((MessagesAdapter.MyViewHolder) viewHolder).viewForeground;

        //will be used by ItemTouchHelper to detect whenever there is UI change on the view.
        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    /**
     * interface used to send the callback to implementing activity.
     * Here the listener will be triggered in MainActivity once the swipe is done.
     */
    public interface RecyclerItemTouchHelperListener {
        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
    }
}