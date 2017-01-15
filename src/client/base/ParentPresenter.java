package client.base;

public interface ParentPresenter<V extends ParentView> extends Presenter<V> {

    void setNestedView(View view);

}
