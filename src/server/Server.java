package server;

import common.UserManager;

import java.rmi.Remote;

public interface Server extends Remote {
    String NAME = "twitter.server";

    UserManager getUserManager();
}
