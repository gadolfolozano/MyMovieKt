package pe.com.gadolfolozano.mymovie.data.remote.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieWrapperResponse {
    @SerializedName("Search")
    private List<MovieResponse> search;

    @SerializedName("totalResults")
    private int totalResults;

    @SerializedName("Response")
    private boolean response;

    public MovieWrapperResponse() {
        //Do nothing
    }

    public List<MovieResponse> getSearch() {
        return search;
    }

    public void setSearch(List<MovieResponse> search) {
        this.search = search;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }
}
