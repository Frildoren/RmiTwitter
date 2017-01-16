package client.messages;


import client.base.View;
import common.models.User;

import java.util.List;

public interface MessagesView extends View<MessagesPresenter>{
    void setTitle(String title);
    void setUserList(List<User> userList);
}
