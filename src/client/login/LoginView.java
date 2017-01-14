package client.login;

import client.base.FrameView;

public interface LoginView extends FrameView<LoginPresenter> {

    String getUser();
    String getPassword();

}
