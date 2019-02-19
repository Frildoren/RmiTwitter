package client.base;

import javax.swing.*;

public interface FrameView<P extends Presenter> extends View<P> {

    void close();
    JFrame getFrame();

}
