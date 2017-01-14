package client.register;

import client.base.FrameView;

public interface RegisterView extends FrameView<RegisterPresenter> {

    String getUser();
    String getPassword();
    String getName();

}
