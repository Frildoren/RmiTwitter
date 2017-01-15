package client.tweets.impl;

import client.base.impl.BasePresenter;
import client.tweets.TweetsPresenter;
import client.tweets.TweetsView;
import common.models.Tweet;

import java.util.List;

public class TweetsPresenterImpl extends BasePresenter<TweetsView> implements TweetsPresenter {

    @Override
    protected TweetsView createView() {
        TweetsViewImpl tweetsView = new TweetsViewImpl();
        tweetsView.create(this);
        return tweetsView;
    }

    @Override
    public void setTweets(List<Tweet> tweets){
        getView().setTweets(tweets);
    }
}
