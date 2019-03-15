package pe.com.gadolfolozano.mymovie.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MovieWrapperModel implements Parcelable {

    private List<MovieModel> movies;

    public MovieWrapperModel() {
        //Do Nothing
    }

    protected MovieWrapperModel(Parcel in) {
        movies = in.createTypedArrayList(MovieModel.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(movies);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MovieWrapperModel> CREATOR = new Creator<MovieWrapperModel>() {
        @Override
        public MovieWrapperModel createFromParcel(Parcel in) {
            return new MovieWrapperModel(in);
        }

        @Override
        public MovieWrapperModel[] newArray(int size) {
            return new MovieWrapperModel[size];
        }
    };

    public List<MovieModel> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieModel> movies) {
        this.movies = movies;
    }
}
