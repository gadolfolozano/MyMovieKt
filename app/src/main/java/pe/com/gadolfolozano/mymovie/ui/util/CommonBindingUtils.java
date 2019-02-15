package pe.com.gadolfolozano.mymovie.ui.util;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import pe.com.gadolfolozano.mymovie.R;

import java.util.List;

public class CommonBindingUtils {

    @BindingAdapter("bind:data")
    public static void setText(RecyclerView recyclerView, List<pe.com.gadolfolozano.mymovie.model.MovieModel> elements) {
        Log.i("CommonBindingUtils", "CommonBindingUtils " + elements);
        //recyclerView.setVisibility(View.GONE);
        recyclerView.setBackgroundResource(R.drawable.ic_lock_outline);
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter instanceof RecyclerHelper) {
            ((RecyclerHelper) adapter).setData(elements);
        }
    }

    public interface RecyclerHelper<T> {
        void setData(List<T> data);
    }
}
