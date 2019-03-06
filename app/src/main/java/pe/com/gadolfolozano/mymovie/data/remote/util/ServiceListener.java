package pe.com.gadolfolozano.mymovie.data.remote.util;

public interface ServiceListener<T> {
    void onSucess(T response);

    void onError(Throwable t);
}
