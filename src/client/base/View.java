package client.base;

import javax.swing.*;

public interface View <P extends Presenter>{

    void create(P presenter);
    void initialize();
    JPanel getPanel();
    void showError(String error);

}
