package client.register.impl;

import client.base.impl.BaseFrameView;
import client.register.RegisterPresenter;
import client.register.RegisterView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class RegisterViewImpl extends BaseFrameView<RegisterPresenter> implements RegisterView {

    private JPasswordField password;
    private JTextField user;
    private JTextField nick;

    private JButton registerButton;
    private JLabel loginLabel;

    private final Dimension WINDOW_DIMENSION = new Dimension(600,400);

    @Override
    public String getTitle() {
        return "Regístrate en Twitter.";
    }

    @Override
    public String getUser() {
        return nick.getText();
    }

    @Override
    public String getPassword() {
        return new String(password.getPassword());
    }

    @Override
    public String getName() {
        return user.getText();
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
        panel.setBackground(new Color(255, 255, 255));

        // Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.Y_AXIS));
        topPanel.setBackground(Color.white);


        JLabel registerText = new JLabel("Únete hoy a Twitter.");
        Font registerTextFont = new Font(registerText.getFont().getFontName(), Font.PLAIN, registerText.getFont().getSize()+6);
        registerText.setForeground(new Color(44, 49, 55));
        registerText.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerText.setBorder(new EmptyBorder(10, 0, 10, 0));
        registerText.setFont(registerTextFont);

        topPanel.add(registerText);


        // Center Zone
        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new BoxLayout(dataPanel,BoxLayout.Y_AXIS));
        dataPanel.setBorder(new EmptyBorder(20, 80, 5, 80));
        dataPanel.setBackground(Color.white);

        JPanel userOficialNamePanel = new JPanel();
        userOficialNamePanel.setLayout(new BoxLayout(userOficialNamePanel,BoxLayout.Y_AXIS));
        userOficialNamePanel.setBackground(Color.WHITE);
        userOficialNamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel userOficialTip = new JLabel("Nombre completo:");
        userOficialTip.setAlignmentX(Component.CENTER_ALIGNMENT);
        userOficialNamePanel.add(userOficialTip);

        user = new JTextField();
        user.setPreferredSize(new Dimension(600,30));
        user.setMaximumSize(new Dimension(600,30));
        user.setHorizontalAlignment(JTextField.CENTER);

        userOficialNamePanel.add(userOficialTip);
        userOficialNamePanel.add(user);

        dataPanel.add(userOficialNamePanel);


        JPanel userNamePanel = new JPanel();
        userNamePanel.setLayout(new BoxLayout(userNamePanel,BoxLayout.Y_AXIS));
        userNamePanel.setBackground(Color.WHITE);
        userNamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);


        JLabel usernameTip = new JLabel("Nombre de usuario:");
        usernameTip.setBorder(new EmptyBorder(20, 0, 0, 0));
        usernameTip.setAlignmentX(Component.CENTER_ALIGNMENT);
        userOficialNamePanel.add(usernameTip);

        nick = new JTextField();
        nick.setPreferredSize(new Dimension(600,30));
        nick.setMaximumSize(new Dimension(600,30));
        nick.setHorizontalAlignment(JTextField.CENTER);

        userOficialNamePanel.add(usernameTip);
        userOficialNamePanel.add(nick);
        dataPanel.add(userNamePanel);


        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel,BoxLayout.Y_AXIS));
        passwordPanel.setBackground(Color.WHITE);
        passwordPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel passwordTip = new JLabel("Contraseña:");
        passwordTip.setBorder(new EmptyBorder(20, 0, 0, 0));
        passwordTip.setAlignmentX(Component.CENTER_ALIGNMENT);

        passwordPanel.add(passwordTip);

        password = new JPasswordField();
        password.setPreferredSize(new Dimension(600,30));
        password.setMaximumSize(new Dimension(600,30));
        password.setHorizontalAlignment(JTextField.CENTER);

        passwordPanel.add(passwordTip);
        passwordPanel.add(password);
        dataPanel.add(passwordPanel);


        // Bottom Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel,BoxLayout.Y_AXIS));
        bottomPanel.setBackground(Color.white);
        bottomPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

        registerButton = generateImageButton("res/check_icon_white.png",25,25,"Regístrate");
        Font loginButtonFont = new Font(registerButton.getFont().getFontName(), Font.PLAIN, registerButton.getFont().getSize()+3);
        registerButton.setFont(loginButtonFont);
        registerButton.setBackground(new Color(0, 132, 180));
        registerButton.setOpaque(true);
        registerButton.setBorderPainted(false);
        registerButton.setForeground(Color.WHITE);
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setBorder(new EmptyBorder(6, 136, 6, 136));

        registerButton.addActionListener(e -> {
            getPresenter().onRegister();
        });

        bottomPanel.add(registerButton);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new FlowLayout());
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

        JLabel askText = new JLabel("¿Tienes cuenta?");
        Font askTextFont = new Font(askText.getFont().getFontName(), Font.PLAIN, askText.getFont().getSize()+2);
        askText.setForeground(new Color(102, 117, 130));
        askText.setBorder(new EmptyBorder(0, 0, 0, 5));
        askText.setAlignmentX(Component.CENTER_ALIGNMENT);
        askText.setFont(askTextFont);

        loginLabel = new JLabel("Iniciar sesión");
        Font loginTextFont = new Font(askText.getFont().getFontName(), Font.BOLD, askText.getFont().getSize()+1);
        loginLabel.setForeground(new Color(29, 161, 242));
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLabel.setFont(loginTextFont);
        loginLabel.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                getPresenter().onLogin();
            }
        });

        loginPanel.add(askText);
        loginPanel.add(loginLabel);

        bottomPanel.add(loginPanel);


        panel.add(topPanel,BorderLayout.NORTH);
        panel.add(bottomPanel,BorderLayout.SOUTH);
        panel.add(dataPanel,BorderLayout.CENTER);

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

        this.user.getDocument().addDocumentListener(listener);
        password.getDocument().addDocumentListener(listener);

    }

    // Method for disable buttons for Login.
    private void disableButton() {
        boolean disabled = user.getText().trim().isEmpty() || password.getPassword().toString().isEmpty() || nick.getText().trim().isEmpty() ;
        registerButton.setEnabled(!disabled);
    }

    // Method to generate a JButton Icon.
    private static JButton generateImageButton(String resourcePath, int width, int height, String labelText){
        ImageIcon imageIcon = null;
        try {
            imageIcon = new ImageIcon(ImageIO.read( RegisterViewImpl.class.getResourceAsStream(resourcePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(width, height,  Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back

        if (labelText == null){
            return new JButton(imageIcon);
        }else{
            JButton label = new JButton(labelText);
            label.setIcon(imageIcon);
            return label;
        }


    }


}
