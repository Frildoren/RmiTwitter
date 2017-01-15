package client.main.impl;

import client.Client;
import client.base.View;
import client.base.impl.BasePresenter;
import client.login.impl.LoginPresenterImpl;
import client.main.MainPresenter;
import client.main.MainView;
import client.people.PeoplePresenter;
import client.people.impl.PeoplePresenterImpl;
import client.profile.ProfilePresenter;
import client.profile.impl.ProfilePresenterImpl;
import client.timeline.TimelinePresenter;
import client.timeline.impl.TimelinePresenterImpl;
import common.models.User;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class MainPresenterImpl extends BasePresenter<MainView> implements MainPresenter {

    @Override
    protected MainView createView() {
        MainViewImpl mainView = new MainViewImpl();
        mainView.create(this);
        return mainView;
    }

    @Override
    public void initialize(Client client) {
        super.initialize(client);

        try {
            getView().setUserName(getClient().getUser().getName());
            getView().setUserNick(getClient().getUser().getNick());
            getView().setUserTweets(getClient().getUser().getTweets().size());
            getView().setUserFollowing(getClient().getUser().getFollowing().size());

            TimelinePresenter timelinePresenter = createPresenter(TimelinePresenterImpl.class);
            getView().setNestedView(timelinePresenter.getView());

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSearch(String search) {

        List<User> users = new ArrayList<>();

        try {
            users = getClient().getServer().getUserManager().search(search);
        } catch (RemoteException e) {
            getView().showError("Error while looking for users");
            e.printStackTrace();
        }

        PeoplePresenter peoplePresenter = createPresenter(PeoplePresenterImpl.class);
        peoplePresenter.setUserList(users);
        peoplePresenter.setTitle("Search");
        setNestedView(peoplePresenter.getView());

    }

    @Override
    public void onFollowingClick() {

        List<User> following = new ArrayList<>();

        try {
            following = getClient().getUser().getFollowing();
        } catch (RemoteException e) {
            getView().showError("Error showing people you follow");
            e.printStackTrace();
        }

        PeoplePresenter peoplePresenter = createPresenter(PeoplePresenterImpl.class);
        peoplePresenter.setUserList(following);
        peoplePresenter.setTitle("Following");
        setNestedView(peoplePresenter.getView());

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

    @Override
    public void onUserClick(){
        ProfilePresenter profilePresenter = createPresenter(ProfilePresenterImpl.class);
        profilePresenter.setUser(getClient().getUser());
        setNestedView(profilePresenter.getView());
    }

    @Override
    public void setNestedView(View view) {
        getView().setNestedView(view);
    }
}
