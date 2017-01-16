package client.userMessages;


import client.base.ParentPresenter;
import common.models.User;

public interface UserMessagesPresenter extends ParentPresenter<UserMessagesView> {

    void sendMessage(User dest, String tweet);
    void setUser(User user);
}
