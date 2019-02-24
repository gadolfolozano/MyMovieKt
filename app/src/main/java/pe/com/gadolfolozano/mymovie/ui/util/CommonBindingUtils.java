package pe.com.gadolfolozano.mymovie.ui.util;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class CommonBindingUtils {

    @BindingAdapter("bind:data")
    public static void setText(RecyclerView recyclerView, List elements) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter instanceof RecyclerHelper) {
            ((RecyclerHelper) adapter).setData(elements);
        }
    }

    public interface RecyclerHelper<T> {
        void setData(List<T> data);
    }
}
