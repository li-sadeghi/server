import load.Load;
import save.Save;
import save.Update;
import servermodels.chatroom.Message;
import servermodels.chatroom.MessageType;
import servermodels.users.Master;
import servermodels.users.Student;
import servermodels.users.User;

public class Test {
    public static void main(String[] args) {
//        User user = Load.fetch(User.class, "1");
//        System.out.println(user.toString());

//        Message message = new Message();
//        Student student = Load.fetch(Student.class, "98100499");
//        User user = Load.fetch(User.class, "1");
//        message.setSender(student);
//        message.setReceiver(user);
//        message.setMessageType(MessageType.TEXT);
//        message.setMessageText("Hiiiiiiiiiiiiiiiii");
//        Save.save(message);
//        User student = Load.fetch(User.class, "1");
//        student.setEmailAddress("adfghjghjk");
//        Update.update(student);
        User master = Load.fetch(User.class, "1234567");
        master.setPhoneNumber("02158646");
        Update.update(master);
    }
}
