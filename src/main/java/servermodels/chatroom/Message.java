package servermodels.chatroom;

import servermodels.users.User;

import javax.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.ALL})
    @JoinTable(name = "message_sender")
    private User sender;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.ALL})
    @JoinTable(name = "message_receiver")
    private User receiver;
    @Column
    private String messageText;
    @Column
    private String time;
    @Column
    private String fileType;
    @Column
    private MessageType messageType;

    public Message() {
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public sharedmodels.chatroom.Message toShared(){
        sharedmodels.chatroom.Message message = new sharedmodels.chatroom.Message();
        message.setId(id);
        message.setMessageText(messageText);
        message.setMessageType(messageType.toShared());
        message.setSenderId(sender.getUsername());
        message.setReceiverId(receiver.getUsername());
        message.setFileType(fileType);
        return message;
    }

}
