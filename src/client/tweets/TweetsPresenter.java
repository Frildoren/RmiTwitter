package client.tweets;

import client.base.Presenter;
import common.models.Tweet;

import java.util.List;

public interface TweetsPresenter extends Presenter<TweetsView> {


    void setTweets(List<Tweet> tweets);
}
