package pe.com.gadolfolozano.mymovie.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class SearchMoviesModel implements Parcelable {
    private List<MovieWrapperModel> movieWrapperModels;

    public SearchMoviesModel() {
        //Do nothing
    }

    protected SearchMoviesModel(Parcel in) {
        movieWrapperModels = in.createTypedArrayList(MovieWrapperModel.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(movieWrapperModels);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SearchMoviesModel> CREATOR = new Creator<SearchMoviesModel>() {
        @Override
        public SearchMoviesModel createFromParcel(Parcel in) {
            return new SearchMoviesModel(in);
        }

        @Override
        public SearchMoviesModel[] newArray(int size) {
            return new SearchMoviesModel[size];
        }
    };

    public List<MovieWrapperModel> getMovieWrapperModels() {
        return movieWrapperModels;
    }

    public void setMovieWrapperModels(List<MovieWrapperModel> movieWrapperModels) {
        this.movieWrapperModels = movieWrapperModels;
    }
}
