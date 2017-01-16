package client.userMessages.impl;


import client.base.View;
import client.base.impl.BaseView;
import client.timeline.impl.TimelineViewImpl;
import client.userMessages.UserMessagesPresenter;
import client.userMessages.UserMessagesView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class UserMessagesViewImpl extends BaseView<UserMessagesPresenter> implements UserMessagesView{

    private JPanel messagesPanel = new JPanel();

    @Override
    public void setNestedView(View view) {
        messagesPanel.removeAll();
        messagesPanel.add(view.getPanel());
        getPanel().updateUI();
    }

    @Override
    protected void initializePanel(JPanel panel) {

        panel.setBackground(new Color(192, 222, 237));
        panel.setLayout(new BorderLayout());

        JPanel sendMessage = new JPanel();
        sendMessage.setLayout(new BorderLayout());
        sendMessage.setBorder(new EmptyBorder(10, 5, 5, 10));
        sendMessage.setBackground(new Color(192, 222, 237));

        JLabel profilePhoto;
        profilePhoto = generateImageLabel("res/defaultprofile.png",20,20,null);
        LineBorder profilePhotoBorder = new LineBorder(new Color(254, 254, 255), 1, true);
        profilePhoto.setBorder(profilePhotoBorder);
        profilePhoto.setBackground(Color.white);
        profilePhoto.setOpaque(true);
        profilePhoto.setBackground(new Color(255, 255, 255));
        profilePhoto.setBorder(new EmptyBorder(0, 5, 0, 5));

        // Add the photo at west.
        sendMessage.add(profilePhoto,BorderLayout.WEST);

        JTextField tweetZone = new JTextField("Escribe aquí el mensaje.",140);
        tweetZone.setBorder(new EmptyBorder(0, 5, 0, 10));
        tweetZone.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                tweetZone.setText("");
            }

        });

        tweetZone.setBackground(Color.WHITE);
        sendMessage.add(tweetZone,BorderLayout.CENTER);

        JButton tweetButton;
        tweetButton = generateImageButton("res/send_icon_white.png",15,15,"Enviar");
        tweetButton.setBackground(new Color(0, 132, 180));
        tweetButton.setOpaque(true);
        tweetButton.setBorderPainted(false);
        tweetButton.setForeground(Color.WHITE);
        tweetButton.addActionListener(e -> {
            //TODO:getPresenter().sendMessage(tweetZone.getText().trim());
            tweetZone.setText("Escribe aquí el mensaje.");
            sendMessage.requestFocusInWindow();
        });

        tweetZone.addActionListener(e -> {
            //TODO:getPresenter().sendMessage(tweetZone.getText().trim());
            tweetZone.setText("Escribe aquí el mensaje.");
            sendMessage.requestFocusInWindow();
        });


        sendMessage.add(tweetButton,BorderLayout.EAST);


        messagesPanel.setLayout(new BoxLayout(messagesPanel,BoxLayout.Y_AXIS));
        messagesPanel.setBackground(new Color(192, 222, 237));
        messagesPanel.setBorder(new EmptyBorder(5, 5, 10, 5));

        panel.add(sendMessage,BorderLayout.NORTH);
        panel.add(messagesPanel,BorderLayout.CENTER);

    }

    // Method to generate a JButton Icon.
    private JButton generateImageButton(String resourcePath, int width, int height, String labelText){
        ImageIcon imageIcon = null;
        try {
            imageIcon = new ImageIcon(ImageIO.read( UserMessagesViewImpl.class.getResourceAsStream(resourcePath)));
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
            imageIcon = new ImageIcon(ImageIO.read( UserMessagesViewImpl.class.getResourceAsStream(resourcePath)));
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
