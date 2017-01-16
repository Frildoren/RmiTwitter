package client.messages.impl;


import client.people.impl.PeoplePresenterImpl;
import client.userMessages.UserMessagesPresenter;
import client.userMessages.impl.UserMessagesPresenterImpl;
import common.models.User;

public class ConversationsPresenterImpl extends PeoplePresenterImpl {

    @Override
    public void onUserClick(User user) {
        UserMessagesPresenter userMessagesPresenter = createPresenter(UserMessagesPresenterImpl.class);
        userMessagesPresenter.setUser(user);
        getParentPresenter().setNestedView(userMessagesPresenter.getView());
    }

}
