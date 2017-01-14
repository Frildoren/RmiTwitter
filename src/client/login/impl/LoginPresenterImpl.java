package client.login.impl;

import client.base.impl.BasePresenter;
import client.login.LoginPresenter;
import client.login.LoginView;
import client.main.impl.MainPresenterImpl;
import client.register.impl.RegisterPresenterImpl;
import common.models.User;

import java.rmi.RemoteException;

public class LoginPresenterImpl extends BasePresenter<LoginView> implements LoginPresenter {

    @Override
    protected LoginView createView() {
        LoginView loginView = new LoginViewImpl();
        loginView.create(this);
        return loginView;
    }

    @Override
    public void onLogin() {
        User user = null;
        try {
            user = getClient().getServer().getUserManager().connect( getView().getUser(), getView().getPassword() );
            if(user == null){
                getView().showError("Incorrect credentials");
            } else {
                getClient().setUser(user);
                createPresenter(MainPresenterImpl.class);
                finish();
            }
        } catch (RemoteException e) {
            getView().showError("Connection error");
        }
    }

    @Override
    public void onRegister() {
        createPresenter(getClient(), RegisterPresenterImpl.class);
        finish();
    }

}
