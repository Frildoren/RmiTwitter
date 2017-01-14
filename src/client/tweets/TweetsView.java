package client.tweets;

import client.base.View;
import common.models.Tweet;

import java.util.List;

public interface TweetsView extends View<TweetsPresenter> {

    void setTweets(List<Tweet> tweets);

}
