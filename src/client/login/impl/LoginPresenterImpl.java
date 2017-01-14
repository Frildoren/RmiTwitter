package client.login.impl;

import client.base.impl.BasePresenter;
import client.login.LoginPresenter;
import client.login.LoginView;

public class LoginPresenterImpl extends BasePresenter<LoginView> implements LoginPresenter {

    @Override
    protected LoginView createView() {
        LoginView loginView = new LoginViewImpl();
        loginView.create(this);
        return loginView;
    }

    @Override
    public void onLogin() {

    }

    @Override
    public void onRegister() {
        //TODO:
        // createPresenter(getClient(), RegisterPresenterImpl.class);
        // finish();
    }

}
