package pe.com.gadolfolozano.mymovie.data.remote.util;

import android.support.annotation.NonNull;

public class StateData<T> {

    public static final int STATE_CREATED = 100;
    public static final int STATE_LOADING = 101;
    public static final int STATE_ERROR = 102;
    public static final int STATE_SUCCESS = 103;

    private T data;
    private int status;
    private Throwable error;

    public StateData() {
        this.status = STATE_CREATED;
    }

    public StateData<T> loading() {
        this.status = STATE_LOADING;
        this.data = null;
        this.error = null;
        return this;
    }

    public StateData<T> error(@NonNull Throwable error) {
        this.status = STATE_ERROR;
        this.data = null;
        this.error = error;
        return this;
    }

    public StateData<T> success(@NonNull T data) {
        this.status = STATE_SUCCESS;
        this.data = data;
        this.error = null;
        return this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}
