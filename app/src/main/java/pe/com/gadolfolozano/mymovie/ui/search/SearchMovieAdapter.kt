package pe.com.gadolfolozano.mymovie.ui.search

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import pe.com.gadolfolozano.mymovie.databinding.ItemMovieSearchBinding
import pe.com.gadolfolozano.mymovie.model.MovieModel
import pe.com.gadolfolozano.mymovie.ui.util.CommonBindingUtils

class SearchMovieAdapter(private val context: Context) :
    RecyclerView.Adapter<SearchMovieAdapter.MyViewHolder>(), CommonBindingUtils.RecyclerHelper<MovieModel> {

    private var movies: List<MovieModel> = ArrayList()

    override fun setData(data: List<MovieModel>) {
        movies = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val itemMovieSearchBinding = ItemMovieSearchBinding.inflate(layoutInflater, viewGroup, false)
        return MyViewHolder(itemMovieSearchBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val searchItemViewModel = SearchItemViewModel(movies[holder.adapterPosition], holder.adapterPosition)
        if (context is SearchNavigator) {
            searchItemViewModel.navigator = context
        }
        holder.bind(searchItemViewModel)
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
