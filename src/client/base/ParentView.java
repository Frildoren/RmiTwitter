package client.base;

public interface ParentView<P extends ParentPresenter> extends View<P> {

    void setNestedView(View view);

}
