package client.timeline.impl;

import client.Client;
import client.base.impl.BasePresenter;
import client.timeline.TimelinePresenter;
import client.timeline.TimelineView;
import client.tweets.TweetsPresenter;
import client.tweets.impl.TweetsPresenterImpl;
import common.models.Tweet;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public class TimelinePresenterImpl extends BasePresenter<TimelineView> implements TimelinePresenter {
    @Override
    protected TimelineView createView() {
        TimelineView timelineView = new TimelineViewImpl();
        timelineView.create(this);
        return timelineView;
    }

    @Override
    public void initialize(Client client) {
        super.initialize(client);
        showTimeline();
    }

    private void showTimeline(){
        try {
            List<Tweet> list = getClient().getUser().getTweets();
            TweetsPresenter tweetsPresenter = createPresenter(TweetsPresenterImpl.class);
            tweetsPresenter.setTweets(list);

            getView().setNestedView(tweetsPresenter.getView());
        } catch (RemoteException e) {
            getView().showError("Could not load timeline");
            e.printStackTrace();
        }

    }

    @Override
    public void sendTweet(String tweet) {

        if (tweet.length() <= 140 && tweet.length() > 0){

            Tweet t = new Tweet();
            t.setUser(getClient().getUser());
            t.setDate(new Date(System.currentTimeMillis()));
            t.setTweet(tweet);

            try {
                getClient().getUser().addTweet(t);
                showTimeline();
            } catch (RemoteException e) {
                getView().showError("Error sending tweet");
                e.printStackTrace();
            }

        }else{
            getView().showError("Tweet exceeds the limit 140 chars or is empty.");
        }

    }
}
