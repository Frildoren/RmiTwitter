package client.userMessages;


import client.base.ParentPresenter;
import common.models.User;

public interface UserMessagesPresenter extends ParentPresenter<UserMessagesView> {

    void sendMessage(String tweet);
    void setUser(User user);
}
