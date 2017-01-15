package client.people.impl;

import client.base.impl.BasePresenter;
import client.people.PeoplePresenter;
import client.people.PeopleView;
import common.models.User;

import java.util.List;

public class PeoplePresenterImpl extends BasePresenter<PeopleView> implements PeoplePresenter {

    @Override
    protected PeopleView createView() {
        return null;
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
        
    }

}
