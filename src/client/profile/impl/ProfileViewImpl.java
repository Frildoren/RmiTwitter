package client.profile.impl;


import client.base.impl.BaseView;
import client.people.impl.PeopleViewImpl;
import client.profile.ProfilePresenter;
import client.profile.ProfileView;
import common.models.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public class ProfileViewImpl extends BaseView<ProfilePresenter> implements ProfileView{

    private JPanel mainPanel;
    private User myUser;

    @Override
    protected void initializePanel(JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(192, 222, 237));

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(192, 222, 237));
    }

    @Override
    public void setUser(User user) {
        myUser = user;
        try {
            getPanel().add(generteProfile(myUser));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    // Method to generate the Profile
    private JPanel generteProfile(User user) throws RemoteException {

        // Panel for the profile.
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel,BoxLayout.Y_AXIS));
        profilePanel.setBackground(new Color(192, 222, 237));
        profilePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel usernames = new JPanel();
        usernames.setLayout(new BorderLayout());
        usernames.setBackground(Color.WHITE);

        JLabel profilePhoto;
        profilePhoto = generateImageLabel("res/defaultprofile.png",50,50,null);
        LineBorder profilePhotoBorder = new LineBorder(new Color(204, 204, 205), 1, true);
        profilePhoto.setBorder(profilePhotoBorder);
        profilePhoto.setBackground(Color.white);
        profilePhoto.setOpaque(true);
        profilePhoto.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Add the photo at west.
        usernames.add(profilePhoto,BorderLayout.WEST);

        JPanel names = new JPanel();
        names.setBackground(Color.white);
        names.setLayout(new BoxLayout(names,BoxLayout.Y_AXIS));

        JLabel name = new JLabel(user.getName());


        name.setBorder(new EmptyBorder(1,0,1,5));
        Font nameFont = new Font("Helvetica Neue", Font.BOLD, 18);
        name.setFont(nameFont);
        name.setCursor(new Cursor(Cursor.HAND_CURSOR));

        names.add(name);

        JLabel usernameLabel = new JLabel("@"+ user.getNick());

        usernameLabel.setForeground(new Color(95, 95, 95));
        usernameLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        names.add(usernameLabel);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.X_AXIS));
        buttonsPanel.setBackground(Color.WHITE);

        // Display buttons.
        JButton followButton;
        followButton = generateImageButton("res/person_icon_black.png",15,15,"Seguir ");
        followButton.setBackground(new Color(7, 112, 180));
        followButton.setOpaque(true);
        followButton.setBorderPainted(false);
        followButton.setForeground(Color.WHITE);
        followButton.addActionListener(e -> {
            //TODO:getPresenter().onFollowing(user);
        });

        // Display buttons.
        JButton messageButton;
        messageButton = generateImageButton("res/person_icon_black.png",15,15,"Mensaje Privado ");
        messageButton.setBackground(new Color(7, 112, 180));
        messageButton.setOpaque(true);
        messageButton.setBorderPainted(false);
        messageButton.setForeground(Color.WHITE);
        messageButton.addActionListener(e -> {
            //TODO:getPresenter().onMessaging(user);
        });

        buttonsPanel.add(followButton);
        buttonsPanel.add(messageButton);
        profilePanel.setMaximumSize(new Dimension(100,50));

        usernames.add(buttonsPanel,BorderLayout.SOUTH);


        // Add the name and username at east.
        usernames.add(names,BorderLayout.CENTER);

        profilePanel.add(usernames);

        profilePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE,150));

        return profilePanel;
    }

    // Method to generate a JButton Icon.
    private JButton generateImageButton(String resourcePath, int width, int height, String labelText){
        ImageIcon imageIcon = null;
        try {
            imageIcon = new ImageIcon(ImageIO.read( PeopleViewImpl.class.getResourceAsStream(resourcePath)));
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
            imageIcon = new ImageIcon(ImageIO.read( PeopleViewImpl.class.getResourceAsStream(resourcePath)) );
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
