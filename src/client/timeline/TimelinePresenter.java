package client.timeline;


import client.base.Presenter;

public interface TimelinePresenter extends Presenter<TimelineView> {

    void sendTweet(String tweet);

}
