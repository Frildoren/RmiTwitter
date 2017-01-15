package client.people;

import client.base.Presenter;
import common.models.User;

import java.util.List;

public interface PeoplePresenter extends Presenter<PeopleView> {
    void setTitle(String title);

    void setUserList(List<User> userList);

    void onUserClick(User user);
}
