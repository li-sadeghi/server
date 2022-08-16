package servermodels.chatroom;

public enum MessageType {
    TEXT,
    FILE;

    public sharedmodels.chatroom.MessageType toShared(){
        if (this == TEXT)return sharedmodels.chatroom.MessageType.TEXT;
        else return sharedmodels.chatroom.MessageType.FILE;
    }
}
