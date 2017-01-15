package client.main;

import client.base.ParentPresenter;

public interface MainPresenter extends ParentPresenter<MainView> {

    void onSearch();
    void onFollowingClick();
    void onDisconnect();

}