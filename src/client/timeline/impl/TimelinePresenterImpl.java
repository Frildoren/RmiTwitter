package client.timeline.impl;

import client.Client;
import client.base.impl.BasePresenter;
import client.timeline.TimelinePresenter;
import client.timeline.TimelineView;
import client.tweets.TweetsPresenter;
import client.tweets.impl.TweetsPresenterImpl;
import common.models.Tweet;

import java.util.Arrays;
import java.util.Date;

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

        Tweet a = new Tweet();
        a.setDate(new Date(System.currentTimeMillis()));
        a.setTweet("Timeline1");
        a.setUser(getClient().getUser());
        Tweet b = new Tweet();
        b.setDate(new Date(System.currentTimeMillis()));
        b.setTweet("tweeep2");
        b.setUser(getClient().getUser());

        Tweet c = new Tweet();
        c.setDate(new Date(System.currentTimeMillis()));
        c.setTweet("Timeline3");
        c.setUser(getClient().getUser());
        Tweet d = new Tweet();
        d.setDate(new Date(System.currentTimeMillis()));
        d.setTweet("tweeep4");
        d.setUser(getClient().getUser());

        Tweet e = new Tweet();
        e.setDate(new Date(System.currentTimeMillis()));
        e.setTweet("Timeline5");
        e.setUser(getClient().getUser());
        Tweet f = new Tweet();
        f.setDate(new Date(System.currentTimeMillis()));
        f.setTweet("tweeep6");
        f.setUser(getClient().getUser());
        Tweet g = new Tweet();
        g.setDate(new Date(System.currentTimeMillis()));
        g.setTweet("tweeep7");
        g.setUser(getClient().getUser());

        TweetsPresenter tweetsPresenter = createPresenter(TweetsPresenterImpl.class);
        tweetsPresenter.setTweets(Arrays.asList(new Tweet[]{a,b,c,d,f,g}));

        getView().setNestedView(tweetsPresenter.getView());
    }
}
