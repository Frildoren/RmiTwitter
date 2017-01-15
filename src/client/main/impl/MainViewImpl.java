package client.main.impl;

import client.base.View;
import client.base.impl.BaseFrameView;
import client.main.MainPresenter;
import client.main.MainView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MainViewImpl extends BaseFrameView<MainPresenter> implements MainView{

    private Dimension WINDOW_DIMENSION = new Dimension(800,600);
    private String username;
    private String myname;
    private int numberTweets;
    private int numberFollowing;

    private JPanel centerZone;
    private View nestedView;


    @Override
    public void setUserName(String name) {
        myname = name;
    }

    @Override
    public void setUserNick(String nick) {
        username = nick;
    }

    @Override
    public void setUserTweets(int tweets) {
        numberTweets = tweets;
    }

    @Override
    public void setUserFollowing(int following) {
        numberFollowing = following;
    }

    @Override
    public void setNestedView(View view) {
        nestedView = view;
        if(centerZone != null) {
            centerZone.removeAll();
            centerZone.add(view.getPanel());
            getPanel().updateUI();
        }
    }

    @Override
    protected void initializePanel(JPanel panel) {

        panel.setLayout(new BorderLayout());
        panel.add(generateBar(),BorderLayout.NORTH);

        // Left
        JPanel leftzone = new JPanel();
        leftzone.setLayout(new BoxLayout(leftzone,BoxLayout.Y_AXIS));
        leftzone.setBackground(new Color(192, 222, 237));
        leftzone.add(generteProfile());
        panel.add(leftzone,BorderLayout.WEST);


        centerZone = new JPanel();
        centerZone.setBackground(new Color(192, 222, 237));
        centerZone.setLayout(new BorderLayout());
        if(nestedView != null){
            setNestedView(nestedView);
        }

        // Center
        panel.add(centerZone);
        panel.setBackground(new Color(192, 222, 237));

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

    // Method to generate the Twitter Bar
    private JPanel generateBar(){

        JPanel tweetBar = new JPanel();
        tweetBar.setLayout(new BorderLayout());

        // Now we generate the three principal left buttons.
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonsPanel.setBackground(Color.WHITE);

        JButton homeButton = generateImageButton("res/home_icon_black.png",15,15,"Inicio");
        JButton messagesButton = generateImageButton("res/messages_icon_black.png",15,15,"Mensajes");

        buttonsPanel.add(homeButton);
        buttonsPanel.add(messagesButton);

        // Now we generate the picture and the tweet button of the right.
        JPanel tweetAndPhotoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        tweetAndPhotoPanel.setBackground(Color.WHITE);


        JTextField searchBox = new JTextField("¿A quién quieres buscar?",15);
        searchBox.setBorder(new EmptyBorder(0, 5, 0, 10));


        searchBox.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                searchBox.setText("");
            }
        });


        tweetAndPhotoPanel.add(searchBox);


        JButton searchButton;
        searchButton = generateImageButton("res/search_icon_black.png",15,15,"Buscar ");
        searchButton.setBackground(new Color(192, 222, 237));
        searchButton.setOpaque(true);
        searchButton.setBorderPainted(false);
        searchButton.setForeground(Color.BLACK);

        searchButton.addActionListener(e -> {
            getPresenter().onSearch(searchBox.getText().trim());
            tweetAndPhotoPanel.requestFocusInWindow();
        });

        searchBox.addActionListener(e -> {
            getPresenter().onSearch(searchBox.getText().trim());
            tweetAndPhotoPanel.requestFocusInWindow();

        });

        tweetAndPhotoPanel.add(searchButton);



        JLabel profilePhoto;
        profilePhoto = generateImageLabel("res/defaultprofile.png",20,20,null);
        LineBorder profilePhotoBorder = new LineBorder(new Color(204, 204, 205), 1, true);
        profilePhoto.setBorder(profilePhotoBorder);
        profilePhoto.setCursor(new Cursor(Cursor.HAND_CURSOR));

        profilePhoto.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if ( SwingUtilities.isLeftMouseButton(e) ) {
                    JPopupMenu menu = new JPopupMenu();
                    JMenuItem itemRemove = new JMenuItem("Desconectar");
                    menu.add(itemRemove);
                    menu.show(profilePhoto, e.getPoint().x, e.getPoint().y);

                    itemRemove.addActionListener(e1 ->
                            getPresenter().onDisconnect()
                    );
                }
            }
        });


        tweetAndPhotoPanel.add(profilePhoto);




        // Now we generate twitter logo in the center.
        JPanel twitterLogo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        twitterLogo.setBackground(Color.WHITE);
        twitterLogo.add(generateImageLabel("res/twitter_icon_black.png",25,25,null));

        tweetBar.add(buttonsPanel,BorderLayout.WEST);
        tweetBar.add(tweetAndPhotoPanel,BorderLayout.EAST);
        tweetBar.add(twitterLogo,BorderLayout.CENTER);
        return tweetBar;
    }

    // Method to generate the Profile
    private JPanel generteProfile(){

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

        JLabel name = new JLabel(myname);
        name.setBorder(new EmptyBorder(1,0,1,5));
        Font nameFont = new Font("Helvetica Neue", Font.BOLD, 18);
        name.setFont(nameFont);
        name.setCursor(new Cursor(Cursor.HAND_CURSOR));
        name.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                getPresenter().onUserClick();
            }
        });
        names.add(name);

        JLabel usernameLabel = new JLabel("@"+ username);
        usernameLabel.setForeground(new Color(95, 95, 95));
        usernameLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        usernameLabel.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                getPresenter().onUserClick();
            }
        });
        names.add(usernameLabel);

        // Add the name and username at east.
        usernames.add(names,BorderLayout.CENTER);

        JPanel numbers = new JPanel();
        numbers.setLayout(new BorderLayout());

        // Display of number of tweets.
        JPanel tweets = new JPanel();
        tweets.setBorder(new EmptyBorder(5, 5, 5, 5));
        tweets.setLayout(new BoxLayout(tweets,BoxLayout.Y_AXIS));
        tweets.setBackground(Color.WHITE);

        JLabel tweetsLabel = new JLabel("TWEETS");
        Font infoLabels = new Font(tweetsLabel.getFont().getFontName(), Font.BOLD, tweetsLabel.getFont().getSize());
        tweetsLabel.setForeground(new Color(125, 125, 125));
        tweetsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tweetsLabel.setFont(infoLabels);
        tweets.add(tweetsLabel);

        JLabel numOfTweets = new JLabel(String.valueOf(numberTweets));
        numOfTweets.setForeground(new Color(0, 132, 180));
        numOfTweets.setAlignmentX(Component.CENTER_ALIGNMENT);
        numOfTweets.setFont(nameFont);
        tweets.add(numOfTweets);

        // Display of number of followings.
        JPanel following = new JPanel();
        following.setBorder(new EmptyBorder(5, 5, 5, 5));
        following.setLayout(new BoxLayout(following,BoxLayout.Y_AXIS));
        following.setBackground(Color.WHITE);

        JLabel followingLabel = new JLabel("SIGUIENDO");
        followingLabel.setForeground(new Color(125, 125, 125));
        followingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        followingLabel.setFont(infoLabels);
        following.add(followingLabel);

        JLabel followingNumberLabel = new JLabel(String.valueOf(numberFollowing));
        followingNumberLabel.setForeground(new Color(0, 132, 180));
        followingNumberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        followingNumberLabel.setFont(nameFont);
        followingNumberLabel.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                getPresenter().onFollowingClick();
            }
        });
        followingNumberLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        following.add(followingNumberLabel);

        // Display of number of followers.
        JPanel followers = new JPanel();
        followers.setBorder(new EmptyBorder(5, 5, 5, 5));
        followers.setLayout(new BoxLayout(followers,BoxLayout.Y_AXIS));
        followers.setBackground(Color.WHITE);

        JLabel followersLabel = new JLabel("SEGUIDORES");
        followersLabel.setForeground(new Color(125, 125, 125));
        followersLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        followersLabel.setFont(infoLabels);
        followers.add(followersLabel);

        JLabel followersNumberLabel = new JLabel("0");
        followersNumberLabel.setForeground(new Color(0, 132, 180));
        followersNumberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        followersNumberLabel.setFont(nameFont);
        followers.add(followersNumberLabel);



        // Add all to the main panel of numbers and statics.
        numbers.add(tweets,BorderLayout.WEST);
        numbers.add(following,BorderLayout.CENTER);
        numbers.add(followers,BorderLayout.EAST);

        profilePanel.add(usernames);
        profilePanel.add(numbers);

        profilePanel.setPreferredSize(new Dimension(270,130));
        profilePanel.setMaximumSize(new Dimension(400,130));

        return profilePanel;
    }

    // Method to generate a JButton Icon.
    private JButton generateImageButton(String resourcePath, int width, int height, String labelText){
        ImageIcon imageIcon = null;
        try {
            imageIcon = new ImageIcon(ImageIO.read( MainViewImpl.class.getResourceAsStream(resourcePath)));
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
            imageIcon = new ImageIcon(ImageIO.read( MainViewImpl.class.getResourceAsStream(resourcePath)) );
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