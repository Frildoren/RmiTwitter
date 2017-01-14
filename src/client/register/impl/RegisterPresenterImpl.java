package client.register.impl;

import client.base.impl.BasePresenter;
import client.login.impl.LoginPresenterImpl;
import client.register.RegisterPresenter;
import client.register.RegisterView;
import common.models.User;

import java.rmi.RemoteException;

public class RegisterPresenterImpl extends BasePresenter<RegisterView> implements RegisterPresenter {

    @Override
    protected RegisterView createView() {
        RegisterView registerView = new RegisterViewImpl();
        registerView.create(this);
        return registerView;
    }

    @Override
    public void onLogin() {
        createPresenter(LoginPresenterImpl.class);
        finish();
    }

    @Override
    public void onRegister() {

        try {
            User user = null;
            user = getClient().getServer().getUserManager().register( getView().getUser(), getView().getName(), getView().getPassword() );
            if(user == null){
                getView().showError("Nickname already in use");
            } else {
                //TODO: Success login in
            }
        } catch (RemoteException e) {
            getView().showError("Connection error");
        }
    }
    
}
