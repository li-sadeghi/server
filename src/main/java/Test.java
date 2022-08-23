import load.Load;
import org.hibernate.Session;
import save.Save;
import save.Update;
import servermodels.chatroom.Message;
import servermodels.chatroom.MessageType;
import servermodels.cw.HomeWork;
import servermodels.department.Course;
import servermodels.department.Department;
import servermodels.users.Master;
import servermodels.users.MasterRole;
import servermodels.users.Student;
import servermodels.users.User;
import util.extra.EncodeDecodeFile;

import java.util.ArrayList;
import java.util.List;

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
//        User master = Load.fetch(User.class, "1234567");
//        master.setPhoneNumber("02158646");
//        Update.update(master);

//        Master master = Load.fetch(Master.class, "1234567");
//        master.setMasterRole(MasterRole.EDUCATIONAL_ASSISTANT);
//        Update.update(master);


//        Student student = Load.fetch(Student.class, "99108223");

//        Course course = Load.fetch(Course.class, "77777");
//        course.setHaveCwPage(true);
//        Update.update(course);
//        HomeWork homeWork = Load.fetch(HomeWork.class , 1);
//        System.out.println(homeWork.getEndTime());

//        Student student = Load.fetch(Student.class, "99205365");
//        System.out.println(student.getCourses().size());

//        Master master = Load.fetch(Master.class, "678901");
//        System.out.println(master.getCourses().size());

//        Course course = Load.fetch(Course.class, "77777");
//        System.out.println(course.getStudentsHaveCourse().size());


        Course course = Load.fetch(Course.class, "11111");
        course.setHaveCwPage(true);
        Update.update(course);
    }
}
