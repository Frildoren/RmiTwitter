package client.timeline.impl;


import client.base.impl.BaseView;
import client.timeline.TimelinePresenter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;

public class TimelineViewImpl extends BaseView<TimelinePresenter> implements TimelineView {

    @Override
    protected void initializePanel(JPanel panel) {

        panel = new JPanel();
        panel.setBackground(new Color(192, 222, 237));
        panel.setLayout(new BorderLayout());

        JPanel sendTweet = new JPanel();
        sendTweet.setLayout(new BorderLayout());
        sendTweet.setBorder(new EmptyBorder(10, 5, 5, 10));
        sendTweet.setBackground(new Color(192, 222, 237));

        JLabel profilePhoto;
        profilePhoto = generateImageLabel("res/defaultprofile.png",20,20,null);
        LineBorder profilePhotoBorder = new LineBorder(new Color(254, 254, 255), 1, true);
        profilePhoto.setBorder(profilePhotoBorder);
        profilePhoto.setBackground(Color.white);
        profilePhoto.setOpaque(true);
        profilePhoto.setBackground(new Color(255, 255, 255));
        profilePhoto.setBorder(new EmptyBorder(0, 5, 0, 5));

        // Add the photo at west.
        sendTweet.add(profilePhoto,BorderLayout.WEST);

        JTextField tweetZone = new JTextField("¿Qué está pasando?",140);
        tweetZone.setBorder(new EmptyBorder(0, 5, 0, 10));

        tweetZone.setBackground(Color.WHITE);
        sendTweet.add(tweetZone,BorderLayout.CENTER);

        JButton tweetButton;
        tweetButton = generateImageButton("res/send_icon_white.png",15,15,"Enviar");
        tweetButton.setBackground(new Color(0, 132, 180));
        tweetButton.setOpaque(true);
        tweetButton.setBorderPainted(false);
        tweetButton.setForeground(Color.WHITE);
        sendTweet.add(tweetButton,BorderLayout.EAST);

    }

    // Method to generate a JButton Icon.
    private JButton generateImageButton(String resourcePath, int width, int height, String labelText){
        ImageIcon imageIcon = null;
        try {
            imageIcon = new ImageIcon(ImageIO.read( TimelineViewImpl.class.getResourceAsStream(resourcePath)));
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
            imageIcon = new ImageIcon(ImageIO.read( TimelineViewImpl.class.getResourceAsStream(resourcePath)));
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
