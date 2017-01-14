package client.login.impl;

import client.RequestCallback;
import client.base.impl.BasePresenter;
import client.login.LoginPresenter;
import client.login.LoginView;

public class LoginPresenterImpl extends BasePresenter<LoginView> implements LoginPresenter, RequestCallback {

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
    }

    @Override
    public void success(Object response) {
    }

    @Override
    public void failure(String error) {
        getView().showError(error);
    }
}
