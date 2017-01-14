package common;

import common.models.User;

public interface UserManager {

    User connect(String nick, String password);
    User register(String nick, String name, String password);
    boolean disconnect(String nick);

}
