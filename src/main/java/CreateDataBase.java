import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import save.Save;
import servermodels.chatroom.Message;
import servermodels.chatroom.MessageType;
import servermodels.cw.EducationalThing;
import servermodels.cw.HomeWork;
import servermodels.department.*;
import servermodels.users.*;
import time.DateAndTime;
import util.extra.EncodeDecodeFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CreateDataBase {
    public static void main(String[] args) {
        //mohseni
        User mrMohseni = new User("2", "123");
        mrMohseni.setEmailAddress("mohseni@yahoo.com");
        mrMohseni.setFullName("Mr. Mohseni");
        mrMohseni.setLastLogin("none");
        mrMohseni.setRole(Role.MR_MOHSENI);
        mrMohseni.setUserImageBytes(EncodeDecodeFile.encode("C:\\Users\\Li\\Desktop\\Masters\\1.jpg"));
        mrMohseni.setPhoneNumber("09364521426");
        mrMohseni.setNationalCode("001245365");
        //admin
        User admin = new User("1", "123");
        admin.setNationalCode("1080598251");
        admin.setRole(Role.EDU_ADMIN);
        admin.setEmailAddress("a.sadeghiali@yahoo.com");
        admin.setLastLogin("none");
        admin.setPhoneNumber("09103527237");
        admin.setUserImageBytes(EncodeDecodeFile.encode("C:\\Users\\Li\\Desktop\\Admin\\1.jpg"));
        admin.setFullName("Ali Sadeghi");


        Student student3 = new Student("97102563", "123");
        student3.setPhoneNumber("09123456789");
        student3.setFullName("Ali Bagheri");
        student3.setEnteringYear("97");
        student3.setUserImageBytes(EncodeDecodeFile.encode("C:\\Users\\Li\\Desktop\\Students\\3.jpg"));
        student3.setNationalCode("102546325");
        student3.setStatus(EducationalStatus.STUDYING);
        student3.setGrade(StudentGrade.UNDERGRADUATE);
        student3.setEmailAddress("ali.B@sharif.edu");
        student3.setLastLogin("none");


        Student student4 = new Student("97100123", "123");
        student4.setPhoneNumber("09131254884");
        student4.setFullName("Mohammad Mohseni");
        student4.setEnteringYear("97");
        student4.setUserImageBytes(EncodeDecodeFile.encode("C:\\Users\\Li\\Desktop\\Students\\4.jpg"));
        student4.setNationalCode("142578654");
        student4.setStatus(EducationalStatus.STUDYING);
        student4.setGrade(StudentGrade.UNDERGRADUATE);
        student4.setEmailAddress("mm.mohseni@sharif.edu");
        student4.setLastLogin("none");


        Student student5 = new Student("9810254", "123");
        student5.setPhoneNumber("09154869134");
        student5.setFullName("Mahyar Fakhri");
        student5.setEnteringYear("98");
        student5.setUserImageBytes(EncodeDecodeFile.encode("C:\\Users\\Li\\Desktop\\Students\\5.jpg"));
        student5.setNationalCode("002546935");
        student5.setStatus(EducationalStatus.STUDYING);
        student5.setGrade(StudentGrade.UNDERGRADUATE);
        student5.setEmailAddress("m.fakhri@yahoo.com");
        student5.setLastLogin("none");


        Student student6 = new Student("9810147", "123");
        student6.setPhoneNumber("09144257413");
        student6.setFullName("Navid Kiani");
        student6.setEnteringYear("98");
        student6.setUserImageBytes(EncodeDecodeFile.encode("C:\\Users\\Li\\Desktop\\Students\\6.jpg"));
        student6.setNationalCode("012458637");
        student6.setStatus(EducationalStatus.STUDYING);
        student6.setGrade(StudentGrade.UNDERGRADUATE);
        student6.setEmailAddress("n.kiani@out.com");
        student6.setLastLogin("none");



        Student student7 = new Student("9910326", "123");
        student7.setPhoneNumber("09163489527");
        student7.setFullName("Sina Leilaei");
        student7.setEnteringYear("99");
        student7.setUserImageBytes(EncodeDecodeFile.encode("C:\\Users\\Li\\Desktop\\Students\\7.jpg"));
        student7.setNationalCode("01486259");
        student7.setStatus(EducationalStatus.STUDYING);
        student7.setGrade(StudentGrade.UNDERGRADUATE);
        student7.setEmailAddress("sinaLeilaei@sharif.edu");
        student7.setLastLogin("none");



        Student student8 = new Student("9910586", "123");
        student8.setPhoneNumber("09174697361");
        student8.setFullName("Sadegh Moharrami");
        student8.setEnteringYear("99");
        student8.setUserImageBytes(EncodeDecodeFile.encode("C:\\Users\\Li\\Desktop\\Students\\8.jpg"));
        student8.setNationalCode("84521639");
        student8.setStatus(EducationalStatus.STUDYING);
        student8.setGrade(StudentGrade.UNDERGRADUATE);
        student8.setEmailAddress("s.moharrami@gmail.com");
        student8.setLastLogin("none");


        Student student9 = new Student("96201237", "123");
        student9.setPhoneNumber("09181286347");
        student9.setFullName("Aslan montazeri");
        student9.setEnteringYear("96");
        student9.setUserImageBytes(EncodeDecodeFile.encode("C:\\Users\\Li\\Desktop\\Students\\9.jpg"));
        student9.setNationalCode("120300456");
        student9.setStatus(EducationalStatus.STUDYING);
        student9.setGrade(StudentGrade.GRADUATED);
        student9.setEmailAddress("a.montazeri@yahoo.com");
        student9.setLastLogin("none");




        Student student2 = new Student("99205365", "123");
        student2.setPhoneNumber("09194263751");
        student2.setFullName("Arsalan Komeili");
        student2.setEnteringYear("99");
        student2.setUserImageBytes(EncodeDecodeFile.encode("C:\\Users\\Li\\Desktop\\Students\\2.jpg"));
        student2.setNationalCode("40086210055");
        student2.setStatus(EducationalStatus.STUDYING);
        student2.setGrade(StudentGrade.GRADUATED);
        student2.setEmailAddress("a.komeili@yahoo.com");
        student2.setLastLogin("none");
        //dep
        //
//
        Student student1 = new Student("97304892", "123");
        student1.setPhoneNumber("091215348641");
        student1.setFullName("Soheil Sadeghi");
        student1.setEnteringYear("97");
        student1.setUserImageBytes(EncodeDecodeFile.encode("C:\\Users\\Li\\Desktop\\Students\\1.jpg"));
        student1.setNationalCode("7856510100");
        student1.setStatus(EducationalStatus.STUDYING);
        student1.setGrade(StudentGrade.PHD);
        student1.setEmailAddress("s.sadeghi@yahoo.com");
        student1.setLastLogin("none");



        Master master1 = new Master("123456", "123");
        master1.setPhoneNumber("02166458564");
        master1.setNationalCode("66458564");
        master1.setFullName("Mojtaba Eslami");
        master1.setEmailAddress("m.eslami@sharif.ir");
        master1.setUserImageBytes(EncodeDecodeFile.encode("C:\\Users\\Li\\Desktop\\Masters\\1.jpg"));
        master1.setLastLogin(DateAndTime.getDateAndTime());
        master1.setGrade(MasterGrade.FULL_PROFESSOR);
        master1.setMasterRole(MasterRole.MASTER);
        master1.setRoomNumber("123");
        master1.setLastLogin("none");

        Master master2 = new Master("234567", "123");
        master2.setPhoneNumber("02166164578");
        master2.setNationalCode("66164578");
        master2.setFullName("Kazem Hejran");
        master2.setEmailAddress("k.hejran@sharif.ir");
        master2.setUserImageBytes(EncodeDecodeFile.encode("C:\\Users\\Li\\Desktop\\Masters\\2.jpg"));
        master2.setLastLogin(DateAndTime.getDateAndTime());
        master2.setGrade(MasterGrade.FULL_PROFESSOR);
        master2.setMasterRole(MasterRole.MASTER);
        master2.setRoomNumber("234");
        master2.setLastLogin("none");


        Master master3 = new Master("345678", "123");
        master3.setPhoneNumber("02166163258");
        master3.setNationalCode("66163258");
        master3.setFullName("Mohsen Salehi");
        master3.setEmailAddress("m.salehi@sharif.ir");
        master3.setUserImageBytes(EncodeDecodeFile.encode("C:\\Users\\Li\\Desktop\\Masters\\3.jpg"));
        master3.setLastLogin(DateAndTime.getDateAndTime());
        master3.setGrade(MasterGrade.FULL_PROFESSOR);
        master3.setMasterRole(MasterRole.MASTER);
        master3.setRoomNumber("345");
        master3.setLastLogin("none");



        Master master4 = new Master("456789", "123");
        master4.setPhoneNumber("02166164895");
        master4.setNationalCode("66164895");
        master4.setFullName("Komeil Kordlheili");
        master4.setEmailAddress("k.kordkheili@sharif.ir");
        master4.setUserImageBytes(EncodeDecodeFile.encode("C:\\Users\\Li\\Desktop\\Masters\\4.jpg"));
        master4.setLastLogin(DateAndTime.getDateAndTime());
        master4.setGrade(MasterGrade.ASSOCIATE_PROFESSOR);
        master4.setMasterRole(MasterRole.MASTER);
        master4.setRoomNumber("456");
        master4.setLastLogin("none");



        Master master5 = new Master("567890", "123");
        master5.setPhoneNumber("02166169584");
        master5.setNationalCode("66169584");
        master5.setFullName("Abdollah Langari");
        master5.setEmailAddress("a.langari@sharif.ir");
        master5.setUserImageBytes(EncodeDecodeFile.encode("C:\\Users\\Li\\Desktop\\Masters\\5.jpg"));
        master5.setLastLogin(DateAndTime.getDateAndTime());
        master5.setGrade(MasterGrade.ASSOCIATE_PROFESSOR);
        master5.setMasterRole(MasterRole.MASTER);
        master5.setRoomNumber("567");
        master5.setLastLogin("none");




        Master master6 = new Master("678901", "123");
        master6.setPhoneNumber("02166168451");
        master6.setNationalCode("66168451");
        master6.setFullName("Ehsan Movaffagh");
        master6.setEmailAddress("e.movaffagh@sharif.ir");
        master6.setUserImageBytes(EncodeDecodeFile.encode("C:\\Users\\Li\\Desktop\\Masters\\6.jpg"));
        master6.setLastLogin(DateAndTime.getDateAndTime());
        master6.setGrade(MasterGrade.ASSOCIATE_PROFESSOR);
        master6.setMasterRole(MasterRole.MASTER);
        master6.setRoomNumber("678");
        master6.setLastLogin("none");


        Master master7 = new Master("789012", "123");
        master7.setPhoneNumber("02166164738");
        master7.setNationalCode("66164738");
        master7.setFullName("Ali Zarei");
        master7.setEmailAddress("a.zarei@sharif.ir");
        master7.setUserImageBytes(EncodeDecodeFile.encode("C:\\Users\\Li\\Desktop\\Masters\\4.jpg"));
        master7.setLastLogin(DateAndTime.getDateAndTime());
        master7.setGrade(MasterGrade.ASSISTANT_PROFESSOR);
        master7.setMasterRole(MasterRole.EDUCATIONAL_ASSISTANT);
        master7.setRoomNumber("789");
        master7.setLastLogin("none");


        Master master8 = new Master("890123", "123");
        master8.setPhoneNumber("02166483297");
        master8.setNationalCode("66483297");
        master8.setFullName("Ali Dehghani");
        master8.setEmailAddress("a.dehghani@sharif.ir");
        master8.setUserImageBytes(EncodeDecodeFile.encode("C:\\Users\\Li\\Desktop\\Masters\\5.jpg"));
        master8.setLastLogin(DateAndTime.getDateAndTime());
        master8.setGrade(MasterGrade.ASSISTANT_PROFESSOR);
        master8.setMasterRole(MasterRole.EDUCATIONAL_ASSISTANT);
        master8.setRoomNumber("890");
        master8.setLastLogin("none");



        Master master9 = new Master("901234", "123");
        master9.setPhoneNumber("02144223794");
        master9.setNationalCode("44223794");
        master9.setFullName("Esfandiar Movahhed");
        master9.setEmailAddress("e.movahhed@sharif.ir");
        master9.setUserImageBytes(EncodeDecodeFile.encode("C:\\Users\\Li\\Desktop\\Masters\\6.jpg"));
        master9.setLastLogin(DateAndTime.getDateAndTime());
        master9.setGrade(MasterGrade.ASSISTANT_PROFESSOR);
        master9.setMasterRole(MasterRole.EDUCATIONAL_ASSISTANT);
        master9.setRoomNumber("901");
        master9.setLastLogin("none");



        Department department1 = new Department();
        department1.setName("Maaref");
        department1.setEducationalAssistantId(master7.getUsername());
        department1.setChairmanId(master1.getUsername());

        Department department2 = new Department();
        department2.setName("Math");
        department2.setChairmanId(master3.getUsername());
        department2.setEducationalAssistantId(master8.getUsername());

        Department department3 = new Department();
        department3.setName("Computer");
        department3.setChairmanId(master9.getUsername());
        department3.setEducationalAssistantId(master2.getUsername());


        student1.setDepartment(department1);
        student2.setDepartment(department1);
        student3.setDepartment(department1);
        master1.setDepartment(department1);
        master7.setDepartment(department1);
        department1.getStudents().add(student1);
        department1.getStudents().add(student2);
        department1.getStudents().add(student3);
        department1.getMasters().add(master1);
        department1.getMasters().add(master7);


        student4.setDepartment(department2);
        student5.setDepartment(department2);
        student6.setDepartment(department2);
        master3.setDepartment(department2);
        master4.setDepartment(department2);
        master5.setDepartment(department2);
        master8.setDepartment(department2);
        department2.getStudents().add(student4);
        department2.getStudents().add(student5);
        department2.getStudents().add(student6);
        department2.getMasters().add(master3);
        department2.getMasters().add(master4);
        department2.getMasters().add(master5);
        department2.getMasters().add(master8);


        student7.setDepartment(department3);
        student8.setDepartment(department3);
        student9.setDepartment(department3);
        master2.setDepartment(department3);
        master6.setDepartment(department3);
        master9.setDepartment(department3);
        department3.getStudents().add(student7);
        department3.getStudents().add(student8);
        department3.getStudents().add(student9);
        department3.getMasters().add(master2);
        department3.getMasters().add(master6);
        department3.getMasters().add(master9);


        student1.setHelperMaster(master1);
        master1.getStudentsIsHelperMaster().add(student1);
        student2.setHelperMaster(master7);
        master7.getStudentsIsHelperMaster().add(student2);
        student3.setHelperMaster(master1);
        master1.getStudentsIsHelperMaster().add(student3);
        student4.setHelperMaster(master5);
        master5.getStudentsIsHelperMaster().add(student4);
        student5.setHelperMaster(master4);
        master4.getStudentsIsHelperMaster().add(student5);
        student6.setHelperMaster(master3);
        master3.getStudentsIsHelperMaster().add(student6);
        student7.setHelperMaster(master2);
        master2.getStudentsIsHelperMaster().add(student7);
        student8.setHelperMaster(master8);
        master8.getStudentsIsHelperMaster().add(student8);
        student9.setHelperMaster(master8);
        master8.getStudentsIsHelperMaster().add(student9);


        Course course1 = new Course();
        course1.setDepartment(department1);
        course1.setName("Aein zendegi");
        course1.setMaster(master1);
        course1.setUnit(2);
        course1.setCapacity(4);
        course1.setExamTime(DateAndTime.getFormatTime(LocalDateTime.of(2022, 12, 25, 14, 0, 0)));
        course1.setWeeklyTime("8 AM on SUNDAY");
        master1.getCourses().add(course1);
        department1.getCourses().add(course1);
        course1.setId("11111");

        Course course2 = new Course();
        course2.setDepartment(department1);
        course2.setName("Akhlagh eslami");
        course2.setMaster(master7);
        course2.setUnit(2);
        course2.setCapacity(4);
        course2.setExamTime(DateAndTime.getFormatTime(LocalDateTime.of(2022, 12, 24, 14, 0, 0)));
        course2.setWeeklyTime("8 AM on MONDAY");
        master7.getCourses().add(course2);
        department1.getCourses().add(course2);
        course2.setId("22222");


        Course course3 = new Course();
        course3.setDepartment(department2);
        course3.setName("Riazi1");
        course3.setMaster(master5);
        course3.setUnit(4);
        course3.setCapacity(4);
        course3.setExamTime(DateAndTime.getFormatTime(LocalDateTime.of(2022, 12, 15, 14, 0, 0)));
        course3.setWeeklyTime("3 PM on SUNDAY");
        department2.getCourses().add(course3);
        master5.getCourses().add(course3);
        course3.setId("33333");



        Course course4 = new Course();
        course4.setDepartment(department2);
        course4.setName("Advanced Programming");
        course4.setMaster(master4);
        course4.setUnit(4);
        course4.setCapacity(4);
        course4.setExamTime(DateAndTime.getFormatTime(LocalDateTime.of(2022, 12, 15, 14, 0, 0)));
        course4.setWeeklyTime("3 PM on MONDAY");
        department2.getCourses().add(course4);
        master4.getCourses().add(course4);
        course4.setId("44444");



        Course course5 = new Course();
        course5.setDepartment(department2);
        course5.setName("Data Structures");
        course5.setMaster(master3);
        course5.setUnit(4);
        course5.setCapacity(4);
        course5.setExamTime(DateAndTime.getFormatTime(LocalDateTime.of(2022, 12, 15, 14, 0, 0)));
        course5.setWeeklyTime("10 AM on SUNDAY");
        department2.getCourses().add(course5);
        master3.getCourses().add(course5);
        course5.setId("55555");




        Course course6 = new Course();
        course6.setDepartment(department3);
        course6.setName("Basic Programming");
        course6.setMaster(master9);
        course6.setUnit(3);
        course6.setCapacity(4);
        course6.setExamTime(DateAndTime.getFormatTime(LocalDateTime.of(2022, 11, 25, 14, 0, 0)));
        course6.setWeeklyTime("8 AM on THURSDAY");
        course6.setId("66666");
        department3.getCourses().add(course6);
        master9.getCourses().add(course6);


        Course course7 = new Course();
        course7.setDepartment(department3);
        course7.setName("Operating System");
        course7.setMaster(master6);
        course7.setUnit(4);
        course7.setCapacity(4);
        course7.setExamTime(DateAndTime.getFormatTime(LocalDateTime.of(2022, 10, 25, 14, 0, 0)));
        course7.setWeeklyTime("8 AM on THURSDAY");
        course7.setId("77777");
        department3.getCourses().add(course7);
        master6.getCourses().add(course7);



        Course course8 = new Course();
        course8.setDepartment(department3);
        course8.setName("Architecture");
        course8.setMaster(master2);
        course8.setUnit(3);
        course8.setCapacity(4);
        course8.setExamTime(DateAndTime.getFormatTime(LocalDateTime.of(2022, 9, 25, 14, 0, 0)));
        course8.setWeeklyTime("8 AM on THURSDAY");
        course8.setId("88888");
        department3.getCourses().add(course8);
        master2.getCourses().add(course8);




        course8.getTeacherAssistants().add(student7);
        course8.getTeacherAssistants().add(student8);


        course4.getTeacherAssistants().add(student4);
        course4.getTeacherAssistants().add(student5);

        HomeWork homeWork1 = new HomeWork();
        homeWork1.setHomeworkFileString(EncodeDecodeFile.encode("D:\\University\\Term4\\AP\\HWs\\HW1\\HW1.pdf"));
        homeWork1.setHomeWorkName("basic questions");


        HomeWork homeWork2 = new HomeWork();
        homeWork2.setHomeworkFileString(EncodeDecodeFile.encode("C:\\Users\\Li\\Downloads\\Video\\video.mp4"));
        homeWork2.setHomeWorkName("how to use");

        course3.getHomeWorks().add(homeWork1);
        course3.getHomeWorks().add(homeWork2);

        EducationalThing educationalThing1 = new EducationalThing();
        educationalThing1.setName("how to use");
        educationalThing1.setFileString(EncodeDecodeFile.encode("C:\\Users\\Li\\Downloads\\Video\\video.mp4"));


        EducationalThing educationalThing2 = new EducationalThing();
        educationalThing2.setName("how to download");
        educationalThing2.setFileString(EncodeDecodeFile.encode("C:\\Users\\Li\\Downloads\\Video\\video.mp4"));

        course1.getEducationalThings().add(educationalThing1);
        course1.getEducationalThings().add(educationalThing2);

        HomeWork homeWork3 = new HomeWork();
        homeWork3.setHomeWorkName("home work1");
        homeWork3.setHomeworkFileString(EncodeDecodeFile.encode("C:\\Users\\Li\\Downloads\\Video\\video.mp4"));

        HomeWork homeWork4 = new HomeWork();
        homeWork4.setHomeWorkName("home work2");
        homeWork4.setHomeworkFileString(EncodeDecodeFile.encode("C:\\Users\\Li\\Downloads\\Video\\video.mp4"));



        HomeWork homeWork5 = new HomeWork();
        homeWork5.setHomeWorkName("home wok 5");
        homeWork5.setHomeworkFileString(EncodeDecodeFile.encode("C:\\Users\\Li\\Downloads\\Video\\video.mp4"));





        course7.getHomeWorks().add(homeWork3);
        course7.getHomeWorks().add(homeWork4);
        course7.getHomeWorks().add(homeWork5);



        EducationalThing educationalThing3 = new EducationalThing();
        educationalThing3.setName("a video to education");
        educationalThing3.setFileString(EncodeDecodeFile.encode("C:\\Users\\Li\\Downloads\\Video\\video.mp4"));
        course7.getEducationalThings().add(educationalThing3);


        EducationalThing educationalThing4 = new EducationalThing();
        educationalThing4.setName("a video for you");
        educationalThing4.setFileString(EncodeDecodeFile.encode("C:\\Users\\Li\\Downloads\\Video\\video.mp4"));
        course7.getEducationalThings().add(educationalThing4);


        course1.getStudentsHaveCourse().add(student1);
        course7.getStudentsHaveCourse().add(student2);

        student1.getCourses().add(course1);
        student2.getCourses().add(course7);

        PassedCourse passedCourse = new PassedCourse();
        passedCourse.setCourseId("4561");
        passedCourse.setMaster(master1);
        passedCourse.setStudent(student1);
        passedCourse.setMark(12.5);
        passedCourse.setUnit(4);
        passedCourse.setName("Basic Programming");
        passedCourse.setStatus(PassStatus.PASS);
        student1.getPassedCourses().add(passedCourse);
        student1.setAverage();
        System.out.println(student1.getAverage());


        TemporaryCourse temporaryCourse = new TemporaryCourse();
        temporaryCourse.setMark(11);
        temporaryCourse.setCourseId("4562");
        temporaryCourse.setMaster(master1);
        temporaryCourse.setStudent(student1);
        temporaryCourse.setUnit(4);
        temporaryCourse.setName("Advanced Programming");
        temporaryCourse.setStatus(PassStatus.PASS);
        student1.getTemporaryCourses().add(temporaryCourse);


        Message message1 = new Message();
        message1.setMessageText("hi");
        message1.setMessageType(MessageType.TEXT);
        message1.setSender(student1);
        message1.setReceiver(student2);
        message1.setTime(DateAndTime.getDateAndTime());
        message1.setFileType("");

//        Message message = new Message();
//        message.setMessageText("hi");
//        message.setMessageType(MessageType.TEXT);
//        message.setSender(student1);
//        message.setReceiver(student2);
//        message.setTime(DateAndTime.getDateAndTime());
//        message.setFileType("");



        Session session = Save.sessionFactory.openSession();
        session.beginTransaction();
        session.save(mrMohseni);
        session.save(admin);


        session.save(student1);
        session.save(student2);
        session.save(student3);
        session.save(student4);
        session.save(student5);
        session.save(student6);
        session.save(student7);
        session.save(student8);
        session.save(student9);

        session.save(master1);
        session.save(master2);
        session.save(master3);
        session.save(master4);
        session.save(master5);
        session.save(master6);
        session.save(master7);
        session.save(master8);
        session.save(master9);

        session.save(course1);
        session.save(course2);
        session.save(course3);
        session.save(course4);
        session.save(course5);
        session.save(course6);
        session.save(course7);
        session.save(course8);

        session.save(mrMohseni);
        session.save(admin);


        session.save(homeWork1);
        session.save(homeWork2);
        session.save(homeWork3);
        session.save(homeWork4);
        session.save(homeWork5);


        session.save(educationalThing1);
        session.save(educationalThing2);
        session.save(educationalThing3);
        session.save(educationalThing4);


        session.save(passedCourse);
        session.save(temporaryCourse);


        session.save(department1);
        session.save(department2);
        session.save(department3);

        session.save(message1);

        session.getTransaction().commit();
        session.close();








//        List<Object> objects = new ArrayList<>();
//        objects.add(mrMohseni);
//        objects.add(admin);
//        objects.add(student1);
//        objects.add(student2);
//        objects.add(department3);
//        objects.add(master1);
//        Save.saveAll(objects);

//        System.out.println(student2.getUsername());
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
//        Save.save(user1);
//        Save.save(message);
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
