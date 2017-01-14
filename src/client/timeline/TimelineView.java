package client.timeline;

import client.base.FrameView;
import common.models.Tweet;
import common.models.User;

import java.util.List;

public interface TimelineView extends FrameView<TimelinePresenter> {

    void setTimeline(List<Tweet> tweets);

}
