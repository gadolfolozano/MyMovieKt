package pe.com.gadolfolozano.mymovie.data.remote.util;

import android.arch.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitLiveData<T> extends LiveData<StateData<T>> implements Callback<T> {

    private Call<T> call;
    private StateData<T> stateData;

    public RetrofitLiveData(Call<T> call) {
        this.call = call;
        this.stateData = new StateData<>();
    }

    @Override
    protected void onActive() {
        if (!call.isCanceled() && !call.isExecuted()) {
            call.enqueue(this);
            setValue(stateData.loading());
        }
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.body() != null) {
            setValue(stateData.success(response.body()));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        setValue(stateData.error(t));
    }
}
