package client.main.impl;

import client.base.impl.BasePresenter;
import client.login.impl.LoginPresenterImpl;
import client.main.MainPresenter;
import client.main.MainView;

import java.rmi.RemoteException;

public class MainPresenterImpl extends BasePresenter<MainView> implements MainPresenter {

    @Override
    protected MainView createView() {
        MainViewImpl mainView = new MainViewImpl();
        mainView.create(this);

        try {
            mainView.setUserName(getClient().getUser().getName());
            mainView.setUserNick(getClient().getUser().getNick());
            mainView.setUserTweets(getClient().getUser().getTweets().size());
            mainView.setUserFollowing(getClient().getUser().getFollowing().size());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return mainView;
    }

    @Override
    public void onSearch() {

    }

    @Override
    public void onFollowingClick() {

    }

    @Override
    public void onDisconnect() {

        try {
            if(getClient().getServer().getUserManager().disconnect( getClient().getUser().getNick() )){
                getClient().setUser(null);
                createPresenter(LoginPresenterImpl.class);
                finish();
            } else {
                getView().showError("Unexpected error disconnecting");
            }
        } catch (RemoteException e) {
            getView().showError("Connection error");
        }

    }
}
