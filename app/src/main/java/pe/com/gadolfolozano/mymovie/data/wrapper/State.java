package pe.com.gadolfolozano.mymovie.data.wrapper;

import android.support.annotation.NonNull;

public class State {
    public static final int STATE_CREATED = 100;
    public static final int STATE_LOADING = 101;
    public static final int STATE_ERROR = 102;
    public static final int STATE_SUCCESS = 103;

    private int status;
    private Throwable error;

    public State() {
        this.status = STATE_CREATED;
    }

    public State loading() {
        this.status = STATE_LOADING;
        this.error = null;
        return this;
    }

    public State error(@NonNull Throwable error) {
        this.status = STATE_ERROR;
        this.error = error;
        return this;
    }

    public State success() {
        this.status = STATE_SUCCESS;
        this.error = null;
        return this;
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
