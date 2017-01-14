package client.base.impl;

import client.base.Presenter;
import client.base.View;

import javax.swing.*;

public abstract class BaseView<P extends Presenter> implements View<P> {

    P presenter;
    JPanel panel;

    @Override
    public void create(P presenter) {
        this.presenter = presenter;
        panel = new JPanel();
    }

    @Override
    public void initialize() {
        initializePanel(panel);
    }

    protected abstract void initializePanel(JPanel panel);

    @Override
    public JPanel getPanel() {
        return panel;
    }

    @Override
    public void showError(String message){
        JOptionPane.showMessageDialog(panel, message, "Error", JOptionPane.WARNING_MESSAGE);
    }

    public P getPresenter() {
        return presenter;
    }
}
