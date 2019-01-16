package server.users;

import javafx.util.Pair;
import server.entities.User;
import server.exceptions.UserNotFoundException;
import server.logging.Logger;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible of managing the online users of the chat.
 */
public class OnlineUsers implements Users<User, Socket> {
    private static OnlineUsers instance;

    private List<Pair<User, Socket>> onlineUsers;

    private OnlineUsers() {
        onlineUsers = new ArrayList<>();
    }

    public static OnlineUsers getInstance() {
        if (instance == null) instance = new OnlineUsers();

        return instance;
    }

    @Override
    public synchronized void addUser(User user, Socket information) {
        onlineUsers.add(new Pair<>(user, information));

        Logger.logConnection(this, "User " + user.getUsername() + " connected!");
    }

    @Override
    public synchronized void removeUser(String username) {
        try {
            onlineUsers.remove(searchUserByUsername(username));

            Logger.logConnection(this, "User " + username + " disconnected!");
        } catch (UserNotFoundException exc) {
            Logger.logError(this, exc.getMessage());
        }
    }

    @Override
    public synchronized void removeAllUsers() {
        onlineUsers.clear();
    }

    @Override
    public synchronized Pair<User, Socket> getUserByUsername(String username) throws UserNotFoundException {
        return searchUserByUsername(username);
    }

    @Override
    public synchronized List<Pair<User, Socket>> getAllUsers() {
        return onlineUsers;
    }

    private Pair<User, Socket> searchUserByUsername(String username) throws UserNotFoundException {
        int counter = 0;

        while (counter < onlineUsers.size()) {
            Pair<User, Socket> onlineUser = onlineUsers.get(counter);

            if (onlineUser.getKey().getUsername().equals(username))
                return onlineUser;

            counter++;
        }

        throw new UserNotFoundException();
    }
}