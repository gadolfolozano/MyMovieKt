package pe.com.gadolfolozano.mymovie.data.wrapper;

import android.support.annotation.NonNull;

public class StateData<T> {

    private State state;
    private T data;

    public StateData() {
        state = new State();
    }

    public StateData<T> loading() {
        this.state = new State().loading();
        this.data = null;
        return this;
    }

    public StateData<T> error(@NonNull Throwable error) {
        this.state = new State().error(error);
        this.data = null;
        return this;
    }

    public StateData<T> success(@NonNull T data) {
        this.state = new State().success();
        this.data = data;
        return this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
