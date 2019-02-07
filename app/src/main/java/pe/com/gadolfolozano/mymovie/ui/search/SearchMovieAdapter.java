package pe.com.gadolfolozano.mymovie.ui.search;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import pe.com.gadolfolozano.mymovie.R;
import pe.com.gadolfolozano.mymovie.model.MovieModel;

import java.util.List;

public class SearchMovieAdapter extends RecyclerView.Adapter<SearchMovieAdapter.MyViewHolder> {

    private List<MovieModel> movies;

    public SearchMovieAdapter(List<MovieModel> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_movie_search, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textMovieTitle.setText(movies.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void addMoview(MovieModel movieModel) {
        movies.add(movieModel);
        notifyItemInserted(movies.size());
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textMovieTitle;
        ImageButton imbtDelete;

        MyViewHolder(View view) {
            super(view);

            textMovieTitle = view.findViewById(R.id.text_movie_title);
            imbtDelete = view.findViewById(R.id.imbt_delete);
        }
    }
}
