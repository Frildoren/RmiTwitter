package client.tweets.impl;

import client.base.impl.BaseView;
import client.tweets.TweetsPresenter;
import client.tweets.TweetsView;
import common.models.Tweet;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class TweetsViewImpl extends BaseView<TweetsPresenter> implements TweetsView {
    
    @Override
    protected void initializePanel(JPanel panel) {

        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(new Color(192, 222, 237));

        JScrollPane tweetsTimeLine = new JScrollPane(panel);
        tweetsTimeLine.setBorder(new EmptyBorder(5, 5, 10, 5));
        tweetsTimeLine.setBackground(new Color(192, 222, 237));
        tweetsTimeLine.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    }

    @Override
    public void setTweets(List<Tweet> tweets) {

        tweets.forEach(tweet -> {
            getPanel().removeAll();
            getPanel().add(generateTweet(tweet.getUser().getNick(),tweet.getUser().getName(),"res/defaultprofile.png",tweet.getDate(),tweet.getTweet()),0);
        });

    }

    // Method to generate a tweet.
    private JPanel generateTweet(String username, String user, String picURL, Date date, String tweet){

        Boolean replyed = false;
        final Boolean[] retweeted = {false};
        final Boolean[] liked = {false};

        JPanel tweetpanel = new JPanel();
        tweetpanel.setLayout(new BorderLayout());
        tweetpanel.setBackground(new Color(192, 222, 237));

        // UserPic
        JPanel userPic = new JPanel();
        userPic.setLayout(new FlowLayout(FlowLayout.CENTER));
        userPic.setBackground(Color.WHITE);

        JLabel picPhoto;
        picPhoto = generateImageLabel(picURL,40,40,null);
        LineBorder picPhotoBorder = new LineBorder(new Color(234, 234, 235), 1, true);
        picPhoto.setBorder(picPhotoBorder);
        picPhoto.setBackground(new Color(192, 222, 237));
        picPhoto.setOpaque(true);
        picPhoto.setBackground(new Color(255, 255, 255));
        userPic.add(picPhoto);


        // InfoAndTweet Panel
        JPanel infoAndTweet = new JPanel();
        infoAndTweet.setLayout(new BorderLayout());
        infoAndTweet.setBackground(new Color(255, 255, 255));

        JPanel nameAndUsername = new JPanel();
        nameAndUsername.setBackground(Color.WHITE);
        nameAndUsername.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel fullName = new JLabel(user);
        fullName.setBackground(Color.WHITE);
        fullName.setBorder(new EmptyBorder(1,0,1,2));
        Font nameFont = new Font("Helvetica Neue", Font.PLAIN, 14);
        fullName.setFont(nameFont);


        JLabel userName = new JLabel("@"+username+" · 5h");
        userName.setBackground(Color.WHITE);
        userName.setForeground(new Color(95, 95, 95));

        nameAndUsername.add(fullName);
        nameAndUsername.add(userName);



        JPanel tweetInfo = new JPanel();
        tweetInfo.setLayout(new BorderLayout());
        tweetInfo.setBackground(Color.WHITE);

        JLabel fullTweet = new JLabel("<html>"+ tweet +"</html>");
        fullTweet.setBorder(new EmptyBorder(5,5,1,1));
        fullTweet.setBackground(Color.WHITE);
        Font tweetFont = new Font("Helvetica Neue", Font.PLAIN, 12);
        fullTweet.setFont(tweetFont);


        tweetInfo.add(fullTweet,BorderLayout.CENTER);


        infoAndTweet.add(tweetInfo,BorderLayout.CENTER);
        infoAndTweet.add(nameAndUsername,BorderLayout.NORTH);



        // Interactions
        JPanel interactions = new JPanel();
        interactions.setBackground(Color.WHITE);
        interactions.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton reply;
        reply = generateImageButton("res/reply_icon_black.png",15,15,"Responder");
        reply.setBackground(new Color(255, 255, 255));
        reply.setOpaque(true);
        reply.setBorderPainted(false);
        reply.setForeground(Color.BLACK);

        reply.addActionListener(e -> {



        });

        interactions.add(reply);

        JButton retweet;
        retweet = generateImageButton("res/retweet_icon_black.png",15,15,"Retwittear");
        retweet.setBackground(new Color(255, 255, 255));
        retweet.setOpaque(true);
        retweet.setBorderPainted(false);
        retweet.setForeground(Color.BLACK);
        retweet.addActionListener(e -> {

            if (retweeted[0]){

                retweet.setText("Retwittear");
                retweet.setForeground(Color.BLACK);

                ImageIcon imageIcon = null;
                try {
                    imageIcon = new ImageIcon(ImageIO.read( TweetsViewImpl.class.getResourceAsStream("res/retweet_icon_black.png")));
                } catch (IOException error) {
                    error.printStackTrace();
                }
                Image image = imageIcon.getImage(); // transform it
                Image newimg = image.getScaledInstance(15, 15,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                imageIcon = new ImageIcon(newimg);  // transform it back
                retweet.setIcon(imageIcon);
                retweeted[0] = false;

            }else{

                retweet.setText("Has retwitteado esto.");
                retweet.setForeground(new Color(25,207,134));

                ImageIcon imageIcon = null;
                try {
                    imageIcon = new ImageIcon(ImageIO.read( TweetsViewImpl.class.getResourceAsStream("res/retweet_icon_green.png")));
                } catch (IOException error) {
                    error.printStackTrace();
                }
                Image image = imageIcon.getImage(); // transform it
                Image newimg = image.getScaledInstance(15, 15,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                imageIcon = new ImageIcon(newimg);  // transform it back
                retweet.setIcon(imageIcon);
                retweeted[0] = true;
            }



        });
        interactions.add(retweet);

        JButton like;
        like = generateImageButton("res/like_icon_black.png",15,15,"Me gusta");
        like.setBackground(new Color(255, 255, 255));
        like.setOpaque(true);
        like.setBorderPainted(false);
        like.setForeground(Color.BLACK);
        like.addActionListener(e -> {

            if (liked[0]){

                like.setText("Me gusta");
                like.setForeground(Color.BLACK);

                ImageIcon imageIcon = null;
                try {
                    imageIcon = new ImageIcon(ImageIO.read( TweetsViewImpl.class.getResourceAsStream("res/like_icon_black.png")));
                } catch (IOException error) {
                    error.printStackTrace();
                }
                Image image = imageIcon.getImage(); // transform it
                Image newimg = image.getScaledInstance(15, 15,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                imageIcon = new ImageIcon(newimg);  // transform it back
                like.setIcon(imageIcon);
                liked[0] = false;
            }else{
                like.setText("Te ha gustado esto.");
                like.setForeground(new Color(226, 38, 77));

                ImageIcon imageIcon = null;
                try {
                    imageIcon = new ImageIcon(ImageIO.read( TweetsViewImpl.class.getResourceAsStream("res/like_icon_red.png")));
                } catch (IOException error) {
                    error.printStackTrace();
                }
                Image image = imageIcon.getImage(); // transform it
                Image newimg = image.getScaledInstance(15, 15,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                imageIcon = new ImageIcon(newimg);  // transform it back
                like.setIcon(imageIcon);
                liked[0] = true;
            }



        });
        interactions.add(like);


        tweetpanel.add(userPic,BorderLayout.WEST);
        tweetpanel.add(infoAndTweet,BorderLayout.CENTER);

        tweetpanel.add(interactions,BorderLayout.SOUTH);
        tweetpanel.setPreferredSize(new Dimension(getPanel().getWidth(),110));
        tweetpanel.setMaximumSize(new Dimension(getPanel().getWidth(),110));
        tweetpanel.setBorder(new EmptyBorder(0, 0, 10, 5));


        return tweetpanel;
    }

    // Method to generate a JButton Icon.
    private JButton generateImageButton(String resourcePath, int width, int height, String labelText){
        ImageIcon imageIcon = null;
        try {
            imageIcon = new ImageIcon(ImageIO.read( TweetsViewImpl.class.getResourceAsStream(resourcePath)));
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
            imageIcon = new ImageIcon(ImageIO.read( TweetsViewImpl.class.getResourceAsStream(resourcePath)));
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
