package client.login.impl;

import client.base.impl.BasePresenter;
import client.login.LoginPresenter;
import client.login.LoginView;
import common.models.User;

public class LoginPresenterImpl extends BasePresenter<LoginView> implements LoginPresenter {

    @Override
    protected LoginView createView() {
        LoginView loginView = new LoginViewImpl();
        loginView.create(this);
        return loginView;
    }

    @Override
    public void onLogin() {
        User user = getClient().getServer().getUserManager().connect( getView().getUser(), getView().getPassword() );
        if(user == null){
            getView().showError("Incorrect credentials");
        } else {
            //TODO: Success login in
        }
    }

    @Override
    public void onRegister() {
        //TODO:
        // createPresenter(getClient(), RegisterPresenterImpl.class);
        // finish();
    }

}
