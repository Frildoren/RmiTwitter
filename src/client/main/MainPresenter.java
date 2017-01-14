package client.main;

import client.base.Presenter;
import client.base.View;

public interface MainPresenter extends Presenter<MainView> {

    void onSearch();
    void onFollowingClick();
    void onDisconnect();

}