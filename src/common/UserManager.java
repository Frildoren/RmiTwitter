package common;

import common.models.User;

import java.rmi.Remote;

public interface UserManager extends Remote {

    User connect(String nick, String password);
    User register(String nick, String name, String password);
    boolean disconnect(String nick);

}
