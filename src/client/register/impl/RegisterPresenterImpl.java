package client.register.impl;

import client.RequestCallback;
import client.base.impl.BasePresenter;
import client.register.RegisterPresenter;
import client.register.RegisterView;

public class RegisterPresenterImpl extends BasePresenter<RegisterView> implements RegisterPresenter, RequestCallback {

    @Override
    protected RegisterView createView() {
        RegisterView registerView = new RegisterViewImpl();
        registerView.create(this);
        return registerView;
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
