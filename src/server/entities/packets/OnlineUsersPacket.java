package server.entities.packets;

import java.util.ArrayList;
import java.util.List;

/**
 * POJO that represents a specific packet used to list
 * the online users of the platform.
 */
public class OnlineUsersPacket extends Packet {
    private List<String> users;

    public OnlineUsersPacket() {
        this.users = new ArrayList<>();
    }

    public OnlineUsersPacket(HeaderType headerType) {
        super(headerType);
        this.users = new ArrayList<>();
    }

    public void addUser(String user) {
        this.users.add(user);
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
