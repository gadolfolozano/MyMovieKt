package pe.com.gadolfolozano.mymovie.data.remote.util;

public class Repository {
    private final ApiInterface apiInterface;

    public Repository() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }
}
