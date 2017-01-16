package client.messages;


import client.base.Presenter;
import common.models.User;

import java.util.List;

public interface MessagesPresenter extends Presenter<MessagesView>{
    void setTitle(String title);

    void setUserList(List<User> userList);

    void onUserClick(User user);
}
