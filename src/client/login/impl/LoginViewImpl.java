package client.login.impl;

import client.base.impl.BaseFrameView;
import client.login.LoginPresenter;
import client.login.LoginView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class LoginViewImpl extends BaseFrameView<LoginPresenter> implements LoginView {

    private JPasswordField password;
    private JTextField user;

    private JButton loginButton;
    private JLabel registerLabel;

    private final Dimension WINDOW_DIMENSION = new Dimension(600,400);

    @Override
    public String getTitle() {
        return "Twitter. Es lo que está pasando.";
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

    protected void initializePanel(JPanel panel){

        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(29, 161, 242));
        panel.setBorder(new EmptyBorder(30, 80, 30, 80));

        JPanel loginZone = new JPanel();
        loginZone.setLayout(new BorderLayout());
        loginZone.setBorder(new EmptyBorder(10, 20, 10, 20));
        loginZone.setBackground(Color.WHITE);

        // Top Zone
        JPanel loginTextPanel = new JPanel();
        loginTextPanel.setLayout(new BoxLayout(loginTextPanel,BoxLayout.Y_AXIS));
        loginTextPanel.setBackground(Color.WHITE);

        JLabel loginText = new JLabel("Inicia sesión en Twitter");
        Font loginTextFont = new Font(loginText.getFont().getFontName(), Font.PLAIN, loginText.getFont().getSize()+6);
        loginText.setForeground(new Color(102, 117, 130));
        loginText.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginText.setFont(loginTextFont);

        JLabel twitterIcon = generateImageLabel("res/twitter_icon_blue.png",25,25,null);
        twitterIcon.setBorder(new EmptyBorder(8, 0, 0, 0));
        twitterIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginTextPanel.add(loginText);
        loginTextPanel.add(twitterIcon);


        loginZone.add(loginTextPanel,BorderLayout.NORTH);


        // Center Zone
        JPanel loginDataPanel = new JPanel();
        loginDataPanel.setLayout(new BoxLayout(loginDataPanel,BoxLayout.Y_AXIS));
        loginDataPanel.setBorder(new EmptyBorder(5, 0, 5, 0));
        loginDataPanel.setBackground(Color.WHITE);

        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new BoxLayout(usernamePanel,BoxLayout.Y_AXIS));
        usernamePanel.setBorder(new EmptyBorder(15, 0, 5, 0));
        usernamePanel.setBackground(Color.WHITE);
        usernamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameTip = new JLabel("Nombre de usuario:");
        usernameTip.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernamePanel.add(usernameTip);

        user = new JTextField();
        user.setPreferredSize(new Dimension(800,30));
        user.setMaximumSize(new Dimension(800,30));

        usernamePanel.add(user);


        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel,BoxLayout.Y_AXIS));
        passwordPanel.setBorder(new EmptyBorder(5, 0, 40, 0));
        passwordPanel.setBackground(Color.WHITE);

        password = new JPasswordField();
        password.setPreferredSize(new Dimension(800,30));
        password.setMaximumSize(new Dimension(800,30));

        JLabel passwordTip = new JLabel("Contraseña:");
        passwordTip.setAlignmentX(Component.CENTER_ALIGNMENT);

        passwordPanel.add(passwordTip);
        passwordPanel.add(password);

        loginButton = generateImageButton("res/fingerprint_icon_white.png",25,25,"Iniciar sesión");
        Font loginButtonFont = new Font(loginButton.getFont().getFontName(), Font.PLAIN, loginButton.getFont().getSize()+3);
        loginButton.setFont(loginButtonFont);
        loginButton.setBackground(new Color(0, 132, 180));
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        loginButton.setForeground(Color.WHITE);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setBorder(new EmptyBorder(6, 136, 6, 136));
        loginButton.setEnabled(false);

        loginButton.addActionListener(e -> {
            getPresenter().onLogin();
        });

        loginDataPanel.add(usernamePanel);
        loginDataPanel.add(passwordPanel);
        loginDataPanel.add(loginButton);

        loginZone.add(loginDataPanel,BorderLayout.CENTER);


        // Bottom Zone
        JPanel registerTextPanel = new JPanel();
        registerTextPanel.setLayout(new FlowLayout());
        registerTextPanel.setBackground(Color.WHITE);

        JLabel askText = new JLabel("¿No tienes una cuenta?");
        Font askTextFont = new Font(askText.getFont().getFontName(), Font.PLAIN, askText.getFont().getSize()+2);
        askText.setForeground(new Color(102, 117, 130));
        askText.setBorder(new EmptyBorder(0, 0, 0, 5));
        askText.setAlignmentX(Component.CENTER_ALIGNMENT);
        askText.setFont(askTextFont);

        registerLabel = new JLabel("Regístrate");
        Font registerTextFont = new Font(askText.getFont().getFontName(), Font.BOLD, askText.getFont().getSize()+1);
        registerLabel.setForeground(new Color(29, 161, 242));
        registerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLabel.setFont(registerTextFont);
        registerLabel.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                getPresenter().onRegister();
            }
        });

        registerTextPanel.add(askText);
        registerTextPanel.add(registerLabel);
        loginZone.add(registerTextPanel,BorderLayout.SOUTH);

        panel.add(loginZone,BorderLayout.CENTER);


        DocumentListener listener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                disableButton();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                disableButton();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                disableButton();
            }
        };

        user.getDocument().addDocumentListener(listener);
        password.getDocument().addDocumentListener(listener);

    }

    // Method for disable buttons for Login.LoginViewImpl
    private void disableButton() {
        boolean disabled = user.getText().trim().isEmpty() || password.getPassword().toString().isEmpty();
        loginButton.setEnabled(!disabled);
    }

    // Method to generate a JButton Icon.
    private JButton generateImageButton(String resourcePath, int width, int height, String labelText){
        ImageIcon imageIcon = null;
        try {
            imageIcon = new ImageIcon(ImageIO.read( LoginViewImpl.class.getResourceAsStream(resourcePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back

        if (labelText == null){
            return new JButton(imageIcon);
        }else{
            JButton label = new JButton(labelText);
            label.setIcon(imageIcon);
            return label;
        }


    }

    // Method to generate a JLabel Icon.
    private JLabel generateImageLabel(String resourcePath, int width, int height, String labelText){
        ImageIcon imageIcon = null;
        try {
            imageIcon = new ImageIcon(ImageIO.read( LoginViewImpl.class.getResourceAsStream(resourcePath)) );
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back

        if (labelText == null){
            return new JLabel(imageIcon);
        }else{
            JLabel label = new JLabel(labelText);
            label.setIcon(imageIcon);
            return label;
        }


    }


}
