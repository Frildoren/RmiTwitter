package client.people;

import client.base.View;
import common.models.User;

import java.util.List;

public interface PeopleView extends View<PeoplePresenter> {
    void setTitle(String title);

    void setUserList(List<User> userList);
}
