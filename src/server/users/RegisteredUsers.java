package server.users;

import javafx.util.Pair;
import server.constants.Constants;
import server.entities.User;
import server.exceptions.UserNotFoundException;
import server.io.TxtFilesHelper;
import server.io.TxtUserHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible of providing a way to access the registered users
 * text file.
 * <p>
 * The semantics of the text file are the following:
 * [username,password,boolean] where the boolean refers to the ban status of
 * the user.
 */
public class RegisteredUsers implements Users<User, Boolean> {

    @Override
    public void addUser(User user, Boolean information) {

    }

    @Override
    public void removeUser(String username) {

    }

    @Override
    public void removeAllUsers() {

    }

    @Override
    public Pair<User, Boolean> getUserByUsername(String username) throws UserNotFoundException {
        return null;
    }

    @Override
    public List<Pair<User, Boolean>> getAllUsers() {
        List<Pair<User, Boolean>> users = new ArrayList<>();

        for (String line : TxtFilesHelper.getAllLines(Constants.REGISTERED_USERS_FILE_NAME)) {
            users.add(TxtUserHelper.decodeUser(line));
        }

        return users;
    }
}
