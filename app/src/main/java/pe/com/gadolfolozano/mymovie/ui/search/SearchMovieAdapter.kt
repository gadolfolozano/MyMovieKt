package pe.com.gadolfolozano.mymovie.ui.search

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import pe.com.gadolfolozano.mymovie.databinding.ItemMovieSearchBinding
import pe.com.gadolfolozano.mymovie.model.MovieModel
import pe.com.gadolfolozano.mymovie.ui.util.CommonBindingUtils

class SearchMovieAdapter(private var movies: MutableList<MovieModel>) :
    RecyclerView.Adapter<SearchMovieAdapter.MyViewHolder>(), CommonBindingUtils.RecyclerHelper<MovieModel> {

    override fun setData(data: MutableList<MovieModel>) {
        movies = data
        notifyDataSetChanged()
        Log.i("DATABINDING ", "DATABINDING setData ")
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val itemMovieSearchBinding = ItemMovieSearchBinding.inflate(layoutInflater, viewGroup, false)
        return MyViewHolder(itemMovieSearchBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(SearchItemViewModel(movies[holder.adapterPosition]))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class MyViewHolder(private val binding: ItemMovieSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(searchItemViewModel: SearchItemViewModel) {
            binding.viewModel = searchItemViewModel
            binding.executePendingBindings()
        }
    }
}
