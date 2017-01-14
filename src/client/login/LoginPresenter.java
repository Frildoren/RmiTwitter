package client.login;

import client.base.Presenter;

public interface LoginPresenter extends Presenter<LoginView> {

    void onLogin();
    void onRegister();

}
