package client.main;

import client.base.ParentPresenter;

public interface MainPresenter extends ParentPresenter<MainView> {

    void onSearch(String search);
    void onFollowingClick();
    void onHomeClick();
    void onMessagesClick();
    void onDisconnect();
    void onUserClick();
    void onFollowersClick();
}