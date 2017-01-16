package client.messages.impl;


import client.base.impl.BasePresenter;
import client.messages.MessagesPresenter;
import client.messages.MessagesView;
import common.models.User;

import java.util.List;

public class MessagesPresenterImpl extends BasePresenter<MessagesView> implements MessagesPresenter{
    @Override
    public void setTitle(String title) {
        getView().setTitle(title);
    }

    @Override
    public void setUserList(List<User> userList) {
        getView().setUserList(userList);
    }

    @Override
    public void onUserClick(User user) {
        //TODO: usermessages
    }

    @Override
    protected MessagesView createView() {
        MessagesView messagesView = new MessagesViewImpl();
        messagesView.create(this);
        return messagesView;
    }
}
