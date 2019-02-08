package pe.com.gadolfolozano.mymovie.ui.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import pe.com.gadolfolozano.mymovie.R
import pe.com.gadolfolozano.mymovie.model.MovieModel
import pe.com.gadolfolozano.mymovie.ui.util.AdapterListener

class SearchMovieAdapter(private val movies: MutableList<MovieModel>) : RecyclerView.Adapter<SearchMovieAdapter.MyViewHolder>() {

    private var adapterListener: AdapterListener<MovieModel>? = null

    fun setAdapterListener(adapterListener: AdapterListener<MovieModel>) {
        this.adapterListener = adapterListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_movie_search, viewGroup, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textMovieTitle.text = movies[holder.adapterPosition].title
        holder.imbtDelete.setOnClickListener { removeItem(holder.adapterPosition) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    private fun removeItem(position: Int) {
        if (movies.size > position) {
            val removed = movies.removeAt(position)
            notifyItemRemoved(position)
            adapterListener?.onItemRemoved(movies, removed)
        }
    }

    fun addMoview(movieModel: MovieModel) {
        movies.add(movieModel)
        notifyItemInserted(movies.size)
        adapterListener?.onItemAdded(movies, movieModel)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textMovieTitle: TextView
        var imbtDelete: ImageButton

        init {

            textMovieTitle = view.findViewById(R.id.text_movie_title)
            imbtDelete = view.findViewById(R.id.imbt_delete)
        }
    }
}
