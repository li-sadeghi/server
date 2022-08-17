import save.Save;
import servermodels.chatroom.Message;
import servermodels.chatroom.MessageType;
import servermodels.department.Department;
import servermodels.users.*;
import time.DateAndTime;
import util.extra.EncodeDecodeFile;

public class CreateDataBase {
    public static void main(String[] args) {
        //mohseni
        User mrMohseni = new User("123", "mohseni123");
        mrMohseni.setEmailAddress("a.sadeghiali@yahoo.com");
        mrMohseni.setFullName("Mr. Mohseni");
        mrMohseni.setLastLogin(DateAndTime.getDateAndTime());
        mrMohseni.setRole(Role.MR_MOHSENI);
        mrMohseni.setUserImageBytes(EncodeDecodeFile.encode("C:\\Users\\Li\\Downloads\\Telegram Desktop\\Ambassador.png"));
        mrMohseni.setPhoneNumber("09103527237");
        mrMohseni.setNationalCode("1080598251");
        //admin
        User admin = new User("1", "123");
        admin.setNationalCode("1080598251");
        admin.setRole(Role.EDU_ADMIN);
        admin.setEmailAddress("a.sadeghiali@yahoo.com");
        admin.setLastLogin(DateAndTime.getDateAndTime());
        admin.setPhoneNumber("09103527237");
        admin.setUserImageBytes(EncodeDecodeFile.encode("C:\\Users\\Li\\Downloads\\Telegram Desktop\\Ambassador.png"));
        admin.setFullName("Ali Sadeghi");
        //new user
//        User user1 = new User("123456", "123456");
//        Message message = new Message();
//        message.setMessageType(MessageType.TEXT);
//        message.setMessageText("salam!! khobi?");
//        message.setSender(user1);
//        message.setReceiver(admin);
////

        Department department = new Department();
        department.setName("math");

        Master master = new Master("1234567", "1234567");
        master.setEmailAddress("dgfhjkm@uhj.com");
        master.setLastLogin(DateAndTime.getDateAndTime());
        master.setGrade(MasterGrade.FULL_PROFESSOR);
        master.setDepartment(department);
        master.setMasterRole(MasterRole.MASTER);
        master.setRoomNumber("123");


        Student student = new Student("99108223", "1380");
        student.setPhoneNumber("09103527237");
        student.setFullName("Ali sadeghi");
        student.setEnteringYear("99");
        student.setUserImageBytes(EncodeDecodeFile.encode("C:\\Users\\Li\\Downloads\\Telegram Desktop\\Ambassador.png"));
        student.setNationalCode("102546325");
        student.setStatus(EducationalStatus.STUDYING);
        student.setDepartment(department);
        student.setHelperMaster(master);
        student.setGrade(StudentGrade.UNDERGRADUATE);
//
        Student student1 = new Student("98100499", "1380");
        student1.setPhoneNumber("09123456789");
        student1.setFullName("Mahya Motaghi");
        student1.setEnteringYear("98");
        student1.setUserImageBytes(EncodeDecodeFile.encode("C:\\Users\\Li\\Downloads\\Telegram Desktop\\Ambassador.png"));
        student1.setNationalCode("102546325");
        student1.setStatus(EducationalStatus.STUDYING);
        student1.setDepartment(department);
        student1.setHelperMaster(master);
        student1.setGrade(StudentGrade.UNDERGRADUATE);
//
//        Message message1 = new Message();
//        message1.setMessageType(MessageType.TEXT);
//        message1.setSender(user1);
//        message1.setReceiver(admin);
//        message1.setMessageText("hi ahmagh");
//
//        Message message2 = new Message();
//        message2.setMessageType(MessageType.TEXT);
//        message2.setSender(student1);
//        message2.setReceiver(admin);
//        message2.setMessageText("hi ahmagh111");
//
        Save.save(mrMohseni);
        Save.save(admin);
//        Save.save(user1);
//        Save.save(message);
        Save.save(department);
        Save.save(master);
        Save.save(student1);
        Save.save(student);
//        Save.save(message1);
//        Save.save(message2);


//        Save.save(admin);
//        Message message = new Message();
//        User sender = Load.fetch(User.class, "123");
//        User receiver = Load.fetch(User.class, "1");
//        message.setMessageType(MessageType.TEXT);
//        message.setMessageText("salam khobi?");
//        message.setReceiver(receiver);
//        message.setSender(sender);
//        Save.save(message);

    }
}
