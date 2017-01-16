package client.messages.impl;


import client.base.impl.BaseView;
import client.messages.MessagesPresenter;
import client.messages.MessagesView;
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

public class MessagesViewImpl extends BaseView<MessagesPresenter> implements MessagesView{

    private JPanel mainPanel;
    private JLabel titleLabel;

    @Override
    public void setTitle(String title) {
        titleLabel.setText(title);
    }

    @Override
    public void setUserList(List<User> userList) {
        getPanel().removeAll();
        getPanel().add(titleLabel);

        userList.forEach(user -> {
            mainPanel.add(generteProfile(user));
        });

        JScrollPane profilesResults = new JScrollPane(mainPanel);
        profilesResults.setBorder(new EmptyBorder(5, 5, 5, 5));
        profilesResults.setBackground(new Color(192, 222, 237));
        profilesResults.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        getPanel().add(profilesResults);
    }

    @Override
    protected void initializePanel(JPanel panel) {

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(192, 222, 237));

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(192, 222, 237));

        titleLabel = new JLabel();

    }

    // Method to generate the Profile
    private JPanel generteProfile(User user){

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

        JLabel name = null;
        try {
            name = new JLabel(user.getName());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        name.setBorder(new EmptyBorder(1,0,1,5));
        Font nameFont = new Font("Helvetica Neue", Font.BOLD, 18);
        name.setFont(nameFont);
        name.setCursor(new Cursor(Cursor.HAND_CURSOR));

        name.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                getPresenter().onUserClick(user);
            }
        });

        names.add(name);

        JLabel usernameLabel = null;
        try {
            usernameLabel = new JLabel("@"+ user.getNick());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        usernameLabel.setForeground(new Color(95, 95, 95));
        usernameLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        usernameLabel.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                getPresenter().onUserClick(user);
            }
        });

        names.add(usernameLabel);

        // Add the name and username at east.
        usernames.add(names,BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.X_AXIS));

        profilePanel.add(usernames);

        profilePanel.setPreferredSize(new Dimension(270,80));
        profilePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE,80));

        return profilePanel;
    }

    // Method to generate a JLabel Icon.
    private JLabel generateImageLabel(String resourcePath, int width, int height, String labelText){
        ImageIcon imageIcon = null;
        try {
            imageIcon = new ImageIcon(ImageIO.read( MessagesViewImpl.class.getResourceAsStream(resourcePath)) );
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
