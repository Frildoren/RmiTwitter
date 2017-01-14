package client;

import common.models.User;
import server.Server;

public interface Client {

    Server getServer();

    User getUser();

    void setUser(User user);
}
