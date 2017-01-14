package client.main.impl;

import client.base.View;
import client.base.impl.BaseFrameView;
import client.main.MainPresenter;
import client.main.MainView;
import common.models.User;

import javax.swing.*;

public class MainViewImpl extends BaseFrameView<MainPresenter> implements MainView{

    private final Dimension WINDOW_DIMENSION = new Dimension(800,600);

    @Override
    public void setUser(User user) {

    }

    @Override
    public void setNestedView(View view) {

    }

    @Override
    protected void initializePanel(JPanel panel) {

    }

    @Override
    protected void initializeFrame(JFrame frame) {

        // Set graphics settings, like size and position.
        frame.setSize(WINDOW_DIMENSION);
        frame.setPreferredSize(WINDOW_DIMENSION);
        frame.setLocationRelativeTo(null);

        // Set options of the bar buttons.
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Set the type of layout for our frame.
        frame.setLayout(new BorderLayout());

        // Show the frame.
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public String getTitle() {
        return "Twitter";
    }
}