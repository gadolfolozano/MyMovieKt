package pe.com.gadolfolozano.mymovie.data.remote.util;

import android.arch.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitLiveData<T> extends LiveData<T> implements Callback<T> {

    private Call<T> call;

    public RetrofitLiveData(Call<T> call) {
        this.call = call;
    }

    @Override
    protected void onActive() {
        if (!call.isCanceled() && !call.isExecuted()) {
            call.enqueue(this);
        }
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.body() != null) {
            setValue(response.body());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        //Do nothing?
    }

    public void cancel() {
        if (!call.isCanceled()) {
            call.cancel();
        }
    }
}
