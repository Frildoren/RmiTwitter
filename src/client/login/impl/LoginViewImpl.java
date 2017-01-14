package client.login.impl;

import client.base.impl.BaseFrameView;
import client.login.LoginPresenter;
import client.login.LoginView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginViewImpl extends BaseFrameView<LoginPresenter> implements LoginView {

    private JPasswordField password;
    private JTextField user;

    private JButton loginButton;
    private JButton registerButton;

    @Override
    public String getTitle() {
        return "Login - SwaggaIRC";
    }

    @Override
    public String getUser() {
        return user.getText();
    }

    @Override
    public String getPassword() {
        return new String(password.getPassword());
    }

    @Override
    protected void initializeFrame(JFrame frame){

        // Set graphics settings, like size and position.
        frame.setSize(350,150);
        frame.setPreferredSize(new Dimension(350,150));
        frame.setLocationRelativeTo(null);

        // Set options of the bar buttons.
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Show the frame.
        frame.pack();
        frame.setVisible(true);
    }

    protected void initializePanel(JPanel panel){

        panel.setLayout(new BorderLayout());

        JPanel data = new JPanel(new GridBagLayout());

        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
        JPanel buttonsPanel = new JPanel();

        // User elements.
        JLabel userLabel = new JLabel("User:");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        data.add(userLabel,cs);

        user = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        data.add(user,cs);

        // Password elements.
        JLabel passwordLabel = new JLabel("Password:");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        data.add(passwordLabel,cs);

        password = new JPasswordField();
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        data.add(password,cs);

        // Button elements.
        loginButton = new JButton (new AbstractAction("Login") {
            public void actionPerformed(ActionEvent e) {
                getPresenter().onLogin();
            }
        });

        registerButton = new JButton (new AbstractAction("Register") {
            public void actionPerformed(ActionEvent e) {
                getPresenter().onRegister();
            }
        });


        buttonsPanel.add(loginButton);
        buttonsPanel.add(registerButton);

        loginButton.setEnabled(false);
        registerButton.setEnabled(false);

        panel.add(data,BorderLayout.CENTER);
        panel.add(buttonsPanel,BorderLayout.PAGE_END);


        DocumentListener listener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                disableButtons();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                disableButtons();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                disableButtons();
            }
        };

        user.getDocument().addDocumentListener(listener);
        password.getDocument().addDocumentListener(listener);

    }

    private void disableButtons() {
        boolean disabled = user.getText().trim().isEmpty() || password.getPassword().toString().isEmpty();
        loginButton.setEnabled(!disabled);
        registerButton.setEnabled(!disabled);
    }




}
