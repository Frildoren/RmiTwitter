package client.base;

import client.Client;

public interface Presenter <V extends View>{

    void initialize(Client client);
    V getView();
    void setParentPresenter(ParentPresenter parentPresenter);
    ParentPresenter getParentPresenter();
    void finish();

}
