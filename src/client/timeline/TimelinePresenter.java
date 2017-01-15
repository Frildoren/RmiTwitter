package client.timeline;


import client.base.ParentPresenter;

public interface TimelinePresenter extends ParentPresenter<TimelineView> {

    void sendTweet(String tweet);

}
