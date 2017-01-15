package client.people.impl;

import client.base.impl.BasePresenter;
import client.people.PeoplePresenter;
import client.people.PeopleView;
import client.profile.ProfilePresenter;
import client.profile.impl.ProfilePresenterImpl;
import common.models.User;

import java.util.List;

public class PeoplePresenterImpl extends BasePresenter<PeopleView> implements PeoplePresenter {

    @Override
    protected PeopleView createView() {
        PeopleView peopleView = new PeopleViewImpl();
        peopleView.create(this);
        return peopleView;
    }

    @Override
    public void setTitle(String title){
        getView().setTitle(title);
    }

    @Override
    public void setUserList(List<User> userList){
        getView().setUserList(userList);
    }

    @Override
    public void onUserClick(User user){
        ProfilePresenter profilePresenter = createPresenter(ProfilePresenterImpl.class);
        profilePresenter.setUser(user);
        getParentPresenter().setNestedView(profilePresenter.getView());
    }

}
