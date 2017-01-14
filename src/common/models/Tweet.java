package common.models;

import java.io.Serializable;
import java.util.Date;

public class Tweet implements Serializable {

    private String tweet;
    private Date date;

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
