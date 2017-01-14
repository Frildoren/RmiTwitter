package client;

public interface RequestCallback<T> {

    void success(T response);
    void failure(String error);

}
