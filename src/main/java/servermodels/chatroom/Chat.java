package servermodels.chatroom;

import servermodels.users.User;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    private User sender;
    private User receiver;
    private List<Message> messages = new ArrayList<>();

    public Chat() {
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}
