package network;

import config.Config;
import load.Load;
import login.CheckDateTime;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import request.Request;
import request.RequestType;
import response.Response;
import response.ResponseStatus;
import save.Save;
import save.Update;
import servermodels.chatroom.Message;
import servermodels.chatroom.MessageType;
import servermodels.cw.EducationalThing;
import servermodels.cw.HomeWork;
import servermodels.cw.Solution;
import servermodels.department.Course;
import servermodels.department.Department;
import servermodels.department.PassedCourse;
import servermodels.department.TemporaryCourse;
import servermodels.security.Captcha;
import servermodels.users.Master;
import servermodels.users.Student;
import servermodels.users.User;
import sharedmodels.chatroom.Chat;
import sharedmodels.users.*;
import time.DateAndTime;
import util.extra.EncodeDecodeFile;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private final ArrayList<ClientHandler> clients;
    private static int clientCount = 0;
    private static final SessionFactory sessionFactory = Save.sessionFactory;

    private static final Config config = Config.getConfig();
    private ServerSocket serverSocket;
    //    private Edu edu;
    private final int port;
    private boolean running;

    public Server(int port) {
        this.port = port;
        clients = new ArrayList<>();
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            running = true;
//            initializeEdu();
            listenForNewConnection();
        } catch (IOException e) {
            e.printStackTrace(); // Failed to run the server
        }
    }

    public void stop() {
        try {
            serverSocket.close();
            running = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void initializeEdu() {
//        edu = new Edu();
//    }

    private void listenForNewConnection() {
        while (running) {
            try {
                clientCount++;
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientCount, this, socket);
                clients.add(clientHandler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void clientDisconnected(ClientHandler clientHandler) {
        // Remove client from clients
        clients.remove(clientHandler);
    }

    private ClientHandler getClientHandler(int clientId) {
        for (ClientHandler clientHandler : clients) {
            if (clientHandler.getId() == clientId) {
                return clientHandler;
            }
        }
        return null;
    }

    private void findClientAndSendResponse(int clientId, Response response) {
        ClientHandler clientHandler = getClientHandler(clientId);
        if (clientHandler != null) {
            clientHandler.sendResponse(response);
        }
    }

    public void handleRequest(int clientId, Request request) {
        RequestType requestType = request.getRequestType();
        switch (requestType) {
            case CAPTCHA -> {
                sendCaptchaResponse(clientId);
            }
            case LOGIN -> {
                sendLoginResponse(clientId, request);
            }
            case CHANGE_PASS -> {
                changePassword(clientId, request);
            }
            case ADMIN_DATA -> {
                sendAllAdminData(clientId);
            }
            case NEW_MESSAGE -> {
                newMessage(clientId, request);
            }
            case CHECK_CONNECTION -> {
                sendConnectionState(clientId, request);
            }
            case MOHSENI_DATA -> {
                sendAllMohseniData(clientId, request);
            }
            case STUDENT_DATA -> {
                sendAllStudentData(clientId, request);
            }
            case MASTER_DATA -> {
                sendAllMasterData(clientId, request);
            }
            case SINGLE_STUDENT -> {
                sendSingleStudentData(clientId, request);
            }
            case CHANGE_EMAIL -> {
                changeEmail(clientId, request);
            }
            case CHANGE_PHONE -> {
                changePhoneNumber(clientId, request);
            }
            case DELETE_COURSE -> {
                deleteCourse(clientId, request);
            }
            case DELETE_MASTER -> {
                deleteMAster(clientId, request);
            }
            case ADD_COURSE -> {
                addNewCourse(clientId, request);
            }
            case EDIT_COURSE -> {
                editCourse(clientId, request);
            }
            case ADD_MASTER -> {
                addMaster(clientId, request);
            }
            case ADD_STUDENT -> {
                addStudent(clientId, request);
            }
            case EDIT_MASTER -> {
                editMaster(clientId, request);
            }
            case NEW_PROTEST -> {
                newProtest(clientId, request);
            }
            case SET_SELECTION_TIME -> {
                setSelectionTime(clientId, request);
            }
            case FILE_MESSAGE -> {
                fileMessage(clientId, request);
            }
            case STAR_COURSE -> {
                starCourse(clientId, request);
            }
            case CATCH_COURSE -> {
                catchCourse(clientId, request);
            }
            case REMOVE_COURSE -> {
                removeCourse(clientId, request);
            }
            case GET_HOMEWORK -> {
                sendHomeWorks(clientId, request);
            }
            case GET_SOLUTIONS -> {
                sendSolutions(clientId, request);
            }
            case REGISTER_MARK -> {
                registerMark(clientId, request);
            }
            case HAVE_SOLUTION -> {
                checkHaveSolution(clientId, request);
            }
            case GET_SOLUTION -> {
                sendSolution(clientId, request);
            }
            case NEW_SOLUTION -> {
                newSolution(clientId, request);
            }
            case ADD_USER_TO_COURSE -> {
                addUserToCourse(clientId, request);
            }
            case GET_EDUCATIONAL -> {
                sendEducational(clientId, request);
            }
            case DELETE_EDUCATIONAL -> {
                deleteEducational(clientId, request);
            }
            case ADD_HOMEWORK -> {
                addHomework(clientId, request);
            }
            case ADD_EDUCATIONAL -> {
                addEducational(clientId, request);
            }
            case ALL_HOMEWORKS -> {
                sendAllHomeworks(clientId, request);
            }
            case ADD_TA -> {
                addTAtoCourse(clientId, request);
            }
        }
    }

    private void addTAtoCourse(int clientId, Request request) {
        String taId = (String) request.getData("taId");
        String courseId = (String) request.getData("courseId");
        Course course = Load.fetch(Course.class, courseId);
        Student ta = Load.fetch(Student.class, taId);
        course.getTeacherAssistants().add(ta);
        Update.update(course);
    }

    private void sendAllHomeworks(int clientId, Request request) {
        String courseId = (String) request.getData("courseId");
        List<HomeWork> homeWorks = Load.fetchAll(HomeWork.class);
        ArrayList<sharedmodels.cw.HomeWork> homeWorkArrayList = new ArrayList<>();
        for (HomeWork homeWork : homeWorks) {
            if (homeWork.getCourse().getId().equals(courseId)){
                homeWorkArrayList.add(homeWork.toShared());
            }
        }
        Response response = new Response();
        response.addData("homeworks", homeWorkArrayList);
        findClientAndSendResponse(clientId, response);
    }

    private void addEducational(int clientId, Request request) {
        EducationalThing newEducationalThing = new EducationalThing();
        sharedmodels.cw.EducationalThing educationalThing = (sharedmodels.cw.EducationalThing) request.getData("educational");
        newEducationalThing.setName(educationalThing.getName());
        newEducationalThing.setFileType(educationalThing.getFileType());
        newEducationalThing.setFileString(educationalThing.getFileString());
        String courseId = (String) request.getData("courseId");
        Course course = Load.fetch(Course.class, courseId);
        newEducationalThing.setCourse(course);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(educationalThing);
        session.update(course);
        session.getTransaction().commit();
        session.close();
    }

    private void addHomework(int clientId, Request request) {
        sharedmodels.cw.HomeWork homeWork = (sharedmodels.cw.HomeWork) request.getData("homework");
        HomeWork newHomework = new HomeWork();
        String courseId = homeWork.getCourseId();
        Course course = Load.fetch(Course.class, courseId);
        newHomework.setHomeWorkFileType(homeWork.getHomeWorkFileType());
        newHomework.setCourse(course);
        newHomework.setEndTime(homeWork.getEndTime());
        newHomework.setStartTime(homeWork.getStartTime());
        newHomework.setHomeWorkName(homeWork.getHomeWorkName());
        newHomework.setHomeworkFileString(homeWork.getHomeworkFileString());
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
        Save.save(homeWork);
        Update.update(course);
//        session.getTransaction().commit();
//        session.close();
    }

    private void deleteEducational(int clientId, Request request) {
        int id = (int) request.getData("id");
        Load.delete(EducationalThing.class, id);
    }

    private void sendEducational(int clientId, Request request) {
        int eduId = (int) request.getData("id");
        EducationalThing educationalThing = Load.fetch(EducationalThing.class, eduId);
        Response response = new Response();
        response.addData("educational", educationalThing.toShared());
        findClientAndSendResponse(clientId, response);
    }

    private void addUserToCourse(int clientId, Request request) {
        String studentId = (String) request.getData("studentId");
        String courseId = (String) request.getData("courseId");
        Student student = Load.fetch(Student.class, studentId);
        Course course = Load.fetch(Course.class, courseId);
        course.getStudentsHaveCourse().add(student);
        student.getCourses().add(course);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(course);
        session.update(student);
        session.getTransaction().commit();
        session.close();
    }

    private void newSolution(int clientId, Request request) {
        sharedmodels.cw.Solution registeredSol = (sharedmodels.cw.Solution) request.getData("solution");
        sharedmodels.cw.HomeWork solutionHomework = (sharedmodels.cw.HomeWork) request.getData("homework");
        Solution solution = new Solution();
        HomeWork homeWork = Load.fetch(HomeWork.class, solutionHomework.getId());
        solution.setAnswerFileString(registeredSol.getAnswerFileString());
        solution.setAnswerFileType(registeredSol.getAnswerFileType());
        String responsiveId = registeredSol.getResponsiveId();
        Student student = Load.fetch(Student.class, responsiveId);
        solution.setResponsive(student);
        solution.setTime(registeredSol.getTime());
        solution.setHomeWork(homeWork);
        homeWork.getSolutions().add(solution);
        Save.save(solution);
        Update.update(homeWork);
    }

    private void sendSolution(int clientId, Request request) {
        Response response = new Response();
        int homeworkId = (int) request.getData("homeworkId");
        String username = (String) request.getData("username");
        HomeWork homeWork = Load.fetch(HomeWork.class, homeworkId);
        for (Solution solution : homeWork.getSolutions()) {
            if (solution.getResponsive().getUsername().equals(username)){
                response.addData("solution", solution);
            }
        }
        findClientAndSendResponse(clientId, response);
    }

    private void checkHaveSolution(int clientId, Request request) {
        int homeworkId = (int) request.getData("homeworkId");
        String username = (String) request.getData("username");
        Response response = new Response();
        HomeWork homeWork = Load.fetch(HomeWork.class, homeworkId);
        boolean haveSolution = false;
        for (Solution solution : homeWork.getSolutions()) {
            if (solution.getResponsive().getUsername().equals(username))haveSolution = true;
        }
        response.addData("result", haveSolution);
        findClientAndSendResponse(clientId, response);
    }

    private void registerMark(int clientId, Request request) {
        int solutionId = (int) request.getData("solutionId");
        Solution solution = Load.fetch(Solution.class, solutionId);
        double mark = (double) request.getData("mark");
        solution.setMark(mark);
        Update.update(solution);
    }

    private void sendSolutions(int clientId, Request request) {
        Response response = new Response();
        int homeworkId = (int) request.getData("homeworkId");
        List<Solution> solutions = Load.fetchAll(Solution.class);
        ArrayList<sharedmodels.cw.Solution> solutionsToThis = new ArrayList<>();
        for (Solution solution : solutions) {
            if (solution.getHomeWork().getId() == homeworkId){
                solutionsToThis.add(solution.toShared());
            }
        }
        response.addData("solutions", solutionsToThis);

        findClientAndSendResponse(clientId, response);
    }

    private void sendHomeWorks(int clientId, Request request) {
        Response response = new Response();
        int id = (int) request.getData("id");
        HomeWork homeWork = Load.fetch(HomeWork.class, id);
        response.addData("homework", homeWork.toShared());
        findClientAndSendResponse(clientId, response);
    }

    private void starCourse(int clientId, Request request) {
        Response response = new Response();
        String courseId = (String) request.getData("courseId");
        String userId = (String) request.getData("username");
        Course course = Load.fetch(Course.class, courseId);
        Student student = Load.fetch(Student.class, userId);
        boolean isStarred = false;
        List<Course> starred = student.getStarredCourses();
        for (Course course1 : starred) {
            if (course1.getId().equals(courseId))isStarred = true;
        }
        if (isStarred){
            for (Course course1 : starred) {
                if (course1.getId().equals(courseId)){
                    student.getStarredCourses().remove(course1);
                    break;
                }
                String unstarCourseNotice = config.getProperty(String.class, "unstarCourseNotice");
                response.setErrorMessage(unstarCourseNotice);
            }
        }else {
            student.getStarredCourses().add(course);
            String starCourseNotice = config.getProperty(String.class, "starCourseNotice");
            response.setErrorMessage(starCourseNotice);
        }
        Update.update(student);
        findClientAndSendResponse(clientId, response);
    }

    private void catchCourse(int clientId, Request request) {
        Response response = new Response();
        String courseId = (String) request.getData("courseId");
        String userId = (String) request.getData("username");
        Course course = Load.fetch(Course.class, courseId);
        Student student = Load.fetch(Student.class, userId);
        if (student.getUnits() + course.getUnit() > 10){
            String unitLimitError = config.getProperty(String.class, "unitLimitError");
            response.setErrorMessage(unitLimitError);
        } else if (course.getCapacity() == 0) {
            String capacityError = config.getProperty(String.class, "capacityError");
            response.setErrorMessage(capacityError);
        }else {
            if (course.isMaaref() && student.haveMaaref()){
                String maarefCourseError = config.getProperty(String.class, "maarefCourseError");
                response.setErrorMessage(maarefCourseError);
            }else {
                String pre = course.getPrerequisiteId();
                if (pre != null && !student.isPassedCourse(pre)){
                    String prerequisiteError = config.getProperty(String.class, "prerequisiteError");
                    response.setErrorMessage(prerequisiteError);
                }else if (student.interferenceWeeklyTime(course.getWeeklyTime())){
                    String interferenceWeeklyTimeError = config.getProperty(String.class, "interferenceWeeklyTimeError");
                    response.setErrorMessage(interferenceWeeklyTimeError);
                }else if (student.interferenceExamTime(course.getExamTime())){
                    String interferenceExamTimeError = config.getProperty(String.class, "interferenceExamTimeError");
                    response.setErrorMessage(interferenceExamTimeError);
                }else {
                    student.getCourses().add(course);
                    course.getStudentsHaveCourse().add(student);
                    course.setCapacity(course.getCapacity() - 1);
                    student.setUnits(student.getUnits() + course.getUnit());
                    Update.update(course);
                    Update.update(student);
                    String catchCourseNotice  = config.getProperty(String.class, "catchCourseNotice");
                    response.setErrorMessage(catchCourseNotice);
                }
            }

        }
        findClientAndSendResponse(clientId, response);
    }

    private void removeCourse(int clientId, Request request) {
        Response response = new Response();
        String courseId = (String) request.getData("courseId");
        String userId = (String) request.getData("username");
        Student student = Load.fetch(Student.class, userId);
        List<Course> courses = student.getCourses();
        for (Course course : courses) {
            if (course.getId().equals(courseId)){
                student.getCourses().remove(course);
                break;
            }
        }
        Course course = Load.fetch(Course.class, courseId);
        for (Student student1 : course.getStudentsHaveCourse()) {
            if (student1.getUsername().equals(userId)){
                course.getStudentsHaveCourse().remove(student1);
                break;
            }
        }
        course.setCapacity(course.getCapacity() + 1);
        Update.update(course);
        Update.update(student);
        String deleteCourseNotice = config.getProperty(String.class, "deleteCourseNotice");
        response.setErrorMessage(deleteCourseNotice);
        findClientAndSendResponse(clientId, response);
    }

    private void fileMessage(int clientId, Request request) {
        Message newMessage = new Message();
        sharedmodels.chatroom.Message message = (sharedmodels.chatroom.Message) request.getData("message");
        newMessage.setTime(DateAndTime.getDateAndTime());
        newMessage.setFileType(message.getFileType());
        newMessage.setMessageType(MessageType.FILE);
        newMessage.setMessageText(message.getMessageText());
        String senderId = message.getSenderId();
        String receiverId = message.getReceiverId();
        User sender = Load.fetch(User.class, senderId);
        User receiver = Load.fetch(User.class, receiverId);
        newMessage.setSender(sender);
        newMessage.setReceiver(receiver);
        Save.save(newMessage);

    }

    private void setSelectionTime(int clientId, Request request) {
        String username = (String) request.getData("username");
        String startTime = (String) request.getData("startTime");
        String endTime = (String) request.getData("endTime");
        Student student = Load.fetch(Student.class, username);
        student.setRegistrationTimeStart(startTime);
        student.setRegistrationTimeEnd(endTime);
        Update.update(student);
    }

    private void newProtest(int clientId, Request request) {
        Response response = new Response();
        String temporaryCourseId = (String) request.getData("temporaryCourseId");
        String protestTex = (String) request.getData("protestTex");
        TemporaryCourse temporaryCourse = Load.fetch(TemporaryCourse.class, temporaryCourseId);
        temporaryCourse.setProtestText(protestTex);
        Update.update(temporaryCourse);
        String registerProtestNotice = config.getProperty(String.class, "registerProtestNotice");
        response.setErrorMessage(registerProtestNotice);
        findClientAndSendResponse(clientId, response);
    }

    private void editMaster(int clientId, Request request) {
        Response response = new Response();
        SharedMaster sharedMaster = (SharedMaster) request.getData("newMaster");
        String username = sharedMaster.getUsername();
        String password = (String) request.getData("password");
        Master master = Load.fetch(Master.class, username);
        master.setPassword(password);
        ArrayList<String> coursesIDs = (ArrayList<String>) request.getData("coursesIDs");
        ArrayList<Course> courses = new ArrayList<>();
        for (String courseID : coursesIDs) {
            Course course = Load.fetch(Course.class, courseID);
            courses.add(course);
            course.setMaster(master);
        }
        master.setCourses(courses);
        master.setFullName(sharedMaster.getFullName());
        master.setNationalCode(sharedMaster.getNationalCode());
        master.setPhoneNumber(sharedMaster.getPhoneNumber());
        master.setEmailAddress(sharedMaster.getEmailAddress());
        master.setRoomNumber(sharedMaster.getRoomNumber());
        if (sharedMaster.getMasterRole() == MasterRole.EDUCATIONAL_ASSISTANT){
            master.setMasterRole(servermodels.users.MasterRole.EDUCATIONAL_ASSISTANT);
        }else if (sharedMaster.getMasterRole() == MasterRole.CHAIRMAN){
            master.setMasterRole(servermodels.users.MasterRole.CHAIRMAN);
        }else {
            master.setMasterRole(servermodels.users.MasterRole.MASTER);
        }
        if (sharedMaster.getGrade() == MasterGrade.ASSISTANT_PROFESSOR){
            master.setGrade(servermodels.users.MasterGrade.ASSISTANT_PROFESSOR);
        }else if (sharedMaster.getGrade() == MasterGrade.ASSOCIATE_PROFESSOR){
            master.setGrade(servermodels.users.MasterGrade.ASSOCIATE_PROFESSOR);
        }else {
            master.setGrade(servermodels.users.MasterGrade.FULL_PROFESSOR);
        }
        Update.update(master);
        for (Course course : courses) {
            Update.update(course);
        }
        String editMasterNotice = config.getProperty(String.class, "editMasterNotice");
        response.setErrorMessage(editMasterNotice);
        findClientAndSendResponse(clientId, response);
    }

    private void addStudent(int clientId, Request request) {
        Response response = new Response();
        String editorId = (String) request.getData("editorId");
        Master editor = Load.fetch(Master.class, editorId);
        SharedStudent sharedStudent = (SharedStudent) request.getData("newStudent");
        String password = (String) request.getData("password");
        String helperMasterId = (String) request.getData("helperMasterId");
        Master helperMaster = Load.fetch(Master.class, helperMasterId);
        Student student = new Student(sharedStudent.getUsername(), password);
        student.setHelperMaster(helperMaster);
        student.setDepartment(editor.getDepartment());
        editor.getDepartment().getStudents().add(student);
        student.setFullName(sharedStudent.getFullName());
        student.setNationalCode(sharedStudent.getNationalCode());
        student.setPhoneNumber(sharedStudent.getPhoneNumber());
        student.setEmailAddress(sharedStudent.getEmailAddress());
        student.setEnteringYear(sharedStudent.getEnteringYear());
        if (sharedStudent.getGrade() == StudentGrade.GRADUATED){
            student.setGrade(servermodels.users.StudentGrade.GRADUATED);
        }else if (sharedStudent.getGrade() == StudentGrade.UNDERGRADUATE){
            student.setGrade(servermodels.users.StudentGrade.UNDERGRADUATE);
        }else {
            student.setGrade(servermodels.users.StudentGrade.PHD);
        }
        student.setAverage(0);
        student.setUnits(0);
        student.setCourses(new ArrayList<>());
        student.setPassedCourses(new ArrayList<>());
        student.setTemporaryCourses(new ArrayList<>());
        Save.save(student);
        Update.update(editor.getDepartment());
        String addStudentNotice = config.getProperty(String.class, "addStudentNotice");
        response.setErrorMessage(addStudentNotice);
        findClientAndSendResponse(clientId, response);
    }

    private void addMaster(int clientId, Request request) {
        Response response = new Response();
        String editorId = (String) request.getData("editorId");
        Master editor = Load.fetch(Master.class, editorId);
        Department department = editor.getDepartment();
        SharedMaster sharedMaster = (SharedMaster) request.getData("newMaster");
        String username = sharedMaster.getUsername();
        String password = (String) request.getData("password");
        Master master = new Master();
        master.setUsername(username);
        master.setPassword(password);
        master.setDepartment(department);
        department.getMasters().add(master);
        ArrayList<String> coursesIDs = (ArrayList<String>) request.getData("coursesIDs");
        ArrayList<Course> courses = new ArrayList<>();
        for (String courseID : coursesIDs) {
            Course course = Load.fetch(Course.class, courseID);
            courses.add(course);
            course.setMaster(master);
        }
        master.setCourses(courses);
        master.setFullName(sharedMaster.getFullName());
        master.setNationalCode(sharedMaster.getNationalCode());
        master.setPhoneNumber(sharedMaster.getPhoneNumber());
        master.setEmailAddress(sharedMaster.getEmailAddress());
        master.setRoomNumber(sharedMaster.getRoomNumber());
        if (sharedMaster.getMasterRole() == MasterRole.EDUCATIONAL_ASSISTANT){
            master.setMasterRole(servermodels.users.MasterRole.EDUCATIONAL_ASSISTANT);
        }else if (sharedMaster.getMasterRole() == MasterRole.CHAIRMAN){
            master.setMasterRole(servermodels.users.MasterRole.CHAIRMAN);
        }else {
            master.setMasterRole(servermodels.users.MasterRole.MASTER);
        }
        if (sharedMaster.getGrade() == MasterGrade.ASSISTANT_PROFESSOR){
            master.setGrade(servermodels.users.MasterGrade.ASSISTANT_PROFESSOR);
        }else if (sharedMaster.getGrade() == MasterGrade.ASSOCIATE_PROFESSOR){
            master.setGrade(servermodels.users.MasterGrade.ASSOCIATE_PROFESSOR);
        }else {
            master.setGrade(servermodels.users.MasterGrade.FULL_PROFESSOR);
        }
        Update.update(department);
        Save.save(master);
        for (Course course : courses) {
            Update.update(course);
        }
        String addMasterNotice = config.getProperty(String.class, "addMasterNotice");
        response.setErrorMessage(addMasterNotice);
        findClientAndSendResponse(clientId, response);
    }

    private void editCourse(int clientId, Request request) {
        Response response = new Response();
        sharedmodels.department.Course editedCourse = (sharedmodels.department.Course) request.getData("course");
        String departmentId = (String) request.getData("departmentId");
        String masterId = (String) request.getData("masterId");
        String prerequisiteId = (String) request.getData("prerequisiteId");
        ArrayList<String> studentIDs = (ArrayList<String>) request.getData("studentIDs");
        ArrayList<String> tAsIds = (ArrayList<String>) request.getData("tAsIds");
        Course course = Load.fetch(Course.class, editedCourse.getId());
        course.setUnit(editedCourse.getUnit());
        course.setWeeklyTime(editedCourse.getWeeklyTime());
        course.setExamTime(editedCourse.getExamTime());
        course.setCapacity(editedCourse.getCapacity());
        Department department = Load.fetch(Department.class, departmentId);
        Master master = Load.fetch(Master.class, masterId);
        Course prerequisiteCourse = Load.fetch(Course.class, prerequisiteId);
        course.setDepartment(department);
        course.setPrerequisiteId(prerequisiteCourse.getPrerequisiteId());
        course.setMaster(master);
        department.getCourses().add(course);
        master.getCourses().add(course);
        ArrayList<Student> studentsHaveCourse = new ArrayList<>();
        ArrayList<Student> TAs = new ArrayList<>();
        for (String studentID : studentIDs) {
            Student student = Load.fetch(Student.class, studentID);
            student.getCourses().add(course);
            studentsHaveCourse.add(student);
        }
        for (String studentID : tAsIds) {
            Student student = Load.fetch(Student.class, studentID);
            student.getCourses().add(course);
            TAs.add(student);
        }
        course.setTeacherAssistants(TAs);
        course.setStudentsHaveCourse(studentsHaveCourse);
        Update.update(course);
        Update.update(department);
        Update.update(prerequisiteCourse);
        for (Student student : studentsHaveCourse) {
            Update.update(student);
        }
        for (Student ta : TAs) {
            Update.update(ta);
        }
        String editCourseNotice = config.getProperty(String.class, "editCourseNotice");
        response.setErrorMessage(editCourseNotice);
        findClientAndSendResponse(clientId, response);
    }

    private void addNewCourse(int clientId, Request request) {
        Response response = new Response();
        sharedmodels.department.Course editedCourse = (sharedmodels.department.Course) request.getData("course");
        String departmentId = (String) request.getData("departmentId");
        String masterId = (String) request.getData("masterId");
        String prerequisiteId = (String) request.getData("prerequisiteId");
        ArrayList<String> studentIDs = (ArrayList<String>) request.getData("studentIDs");
        ArrayList<String> tAsIds = (ArrayList<String>) request.getData("tAsIds");
        Course course = new Course();
        course.setName(editedCourse.getName());
        course.setId(editedCourse.getId());
        course.setUnit(editedCourse.getUnit());
        course.setWeeklyTime(editedCourse.getWeeklyTime());
        course.setExamTime(editedCourse.getExamTime());
        course.setCapacity(editedCourse.getCapacity());
        Department department = Load.fetch(Department.class, departmentId);
        Master master = Load.fetch(Master.class, masterId);
        Course prerequisiteCourse = Load.fetch(Course.class, prerequisiteId);
        course.setDepartment(department);
        course.setPrerequisiteId(prerequisiteCourse.getPrerequisiteId());
        course.setMaster(master);
        department.getCourses().add(course);
        master.getCourses().add(course);
        ArrayList<Student> studentsHaveCourse = new ArrayList<>();
        ArrayList<Student> TAs = new ArrayList<>();
        for (String studentID : studentIDs) {
            Student student = Load.fetch(Student.class, studentID);
            student.getCourses().add(course);
            studentsHaveCourse.add(student);
        }
        for (String studentID : tAsIds) {
            Student student = Load.fetch(Student.class, studentID);
            student.getCourses().add(course);
            TAs.add(student);
        }
        course.setTeacherAssistants(TAs);
        course.setStudentsHaveCourse(studentsHaveCourse);
        Save.save(course);
//        Update.update(department);
//        Update.update(prerequisiteCourse);
//        for (Student student : studentsHaveCourse) {
//            Update.update(student);
//        }
//        for (Student ta : TAs) {
//            Update.update(ta);
//        }
        String addCourseNotice = config.getProperty(String.class, "addCourseNotice");
        response.setErrorMessage(addCourseNotice);
        findClientAndSendResponse(clientId, response);
    }

    private void deleteMAster(int clientId, Request request) {
        Response response = new Response();
        String deleteMasterNotice = config.getProperty(String.class, "deleteMasterNotice");
        response.setErrorMessage(deleteMasterNotice);
        String masterIdSelected = (String) request.getData("usernameSelected");
        Load.delete(Master.class, masterIdSelected);
        findClientAndSendResponse(clientId, response);
    }

    private void deleteCourse(int clientId, Request request) {
        Response response = new Response();
        String deleteCourseNotice = config.getProperty(String.class, "deleteCourseNotice");
        response.setErrorMessage(deleteCourseNotice);
        String courseId = (String) request.getData("courseId");
//        Load.delete(Course.class, courseId);
        Course course = Load.fetch(Course.class, courseId);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(course);
        session.getTransaction().commit();
        session.close();
        findClientAndSendResponse(clientId, response);
    }

    private void changePhoneNumber(int clientId, Request request) {
        Response response = new Response();
        String changePhoneNotice = config.getProperty(String.class, "changePhoneNotice");
        response.setErrorMessage(changePhoneNotice);
        String username = (String) request.getData("username");
        User user = Load.fetch(User.class, username);
        String newPhone = (String) request.getData("newPhone");
        user.setPhoneNumber(newPhone);
        Update.update(user);
        findClientAndSendResponse(clientId, response);
    }

    private void changeEmail(int clientId, Request request) {
        Response response = new Response();
        String emailChangeNotice = config.getProperty(String.class, "emailChangeNotice");
        response.setErrorMessage(emailChangeNotice);
        String username = (String) request.getData("username");
        User user = Load.fetch(User.class, username);
        String newEmail = (String) request.getData("newEmail");
        user.setEmailAddress(newEmail);
        Update.update(user);
        findClientAndSendResponse(clientId, response);
    }

    private void sendSingleStudentData(int clientId, Request request) {
        String username = (String) request.getData("username");
        SharedStudent student = (SharedStudent) (Load.fetch(Student.class, username).toShared());
        Response response = new Response();
        response.addData("user", student);
        findClientAndSendResponse(clientId, response);
    }

    private void sendAllMasterData(int clientId, Request request) {
        Response response = new Response();
        String username = (String) request.getData("username");
        Master master = Load.fetch(Master.class, username);
        response.addData("user", master.toShared());
        ArrayList<Course> courses = (ArrayList<Course>) Load.fetchAll(Course.class);
        ArrayList<sharedmodels.department.Course> courses1 = new ArrayList<>();
        for (Course course : courses) {
            sharedmodels.department.Course course1 = course.toShared();
            courses1.add(course1);
        }
        response.addData("courses", courses1);
        ArrayList<Master> masters = (ArrayList<Master>) Load.fetchAll(Master.class);
        ArrayList<SharedMaster> masters1 = new ArrayList<>();
        for (Master master1 : masters) {
            masters1.add((SharedMaster) master1.toShared());
        }
        response.addData("masters", masters1);
        List<PassedCourse> passedCourses = Load.fetchAll(PassedCourse.class);
        ArrayList<sharedmodels.department.PassedCourse> passedCourses1 = new ArrayList<>();
        for (PassedCourse passedCourse : passedCourses) {
            passedCourses1.add(passedCourse.toShared());
        }
        ArrayList<Chat> chats = getAllChats(username);
        List<Student> students =  Load.fetchAll(Student.class);
        ArrayList<SharedStudent> students1 = new ArrayList<>();
        for (Student student1 : students) {
            students1.add((SharedStudent) student1.toShared());
        }
        response.addData("chats", chats);
        response.addData("allStudents", students1);
        response.addData("passedCourses", passedCourses1);
        response.addData("department", master.getDepartment().toShared());
        List<TemporaryCourse> temporaryCourses = Load.fetchAll(TemporaryCourse.class);
        ArrayList<sharedmodels.department.TemporaryCourse> temporaryCourses1 = new ArrayList<>();
        for (TemporaryCourse temporaryCourse : temporaryCourses) {
            temporaryCourses1.add(temporaryCourse.toShared());
        }
        response.addData("temporaryCourses", temporaryCourses1);
        ArrayList<sharedmodels.department.Course> coursesHave = new ArrayList<>();
        for (Course course : master.getCourses()) {
            coursesHave.add(course.toShared());
        }
        response.addData("coursesHave",coursesHave );
        List<Student> studentList;
        if (master.getMasterRole() == servermodels.users.MasterRole.CHAIRMAN || master.getMasterRole() == servermodels.users.MasterRole.EDUCATIONAL_ASSISTANT){
            studentList = master.getDepartment().getStudents();
        }else {
            studentList = master.getStudentsIsHelperMaster();
        }
        ArrayList<SharedStudent> sharedStudents = new ArrayList<>();
        for (Student student : studentList) {
            sharedStudents.add((SharedStudent) student.toShared());
        }
        response.addData("students", sharedStudents);
        findClientAndSendResponse(clientId, response);
    }

    private void sendAllStudentData(int clientId, Request request) {
        Response response = new Response();
        String username = (String) request.getData("username");
        Student student = Load.fetch(Student.class, username);
        response.addData("user", student.toShared());
        response.addData("helperMaster", student.getHelperMaster().toShared());
        List<Course> courses = Load.fetchAll(Course.class);
        ArrayList<sharedmodels.department.Course> courses1 = new ArrayList<>();
        for (Course course : courses) {
            courses1.add(course.toShared());
        }
        response.addData("courses", courses1);
        List<Master> masters = Load.fetchAll(Master.class);
        ArrayList<SharedMaster> masters1 = new ArrayList<>();
        for (Master master : masters) {
            masters1.add((SharedMaster) master.toShared());
        }
        response.addData("masters", masters1);
        ArrayList<Chat> chats = getAllChats(username);
        List<Student> students =  student.getDepartment().getStudents();
        ArrayList<SharedStudent> students1 = new ArrayList<>();
        for (Student student1 : students) {
            if (student1.getUsername().equals(username))continue;
            students1.add((SharedStudent) student1.toShared());
        }
        response.addData("chats", chats);
        response.addData("students", students1);
        response.addData("helperMaster", student.getHelperMaster().toShared());
        response.addData("department", student.getDepartment().toShared());

        List<TemporaryCourse> temporaryCourses = student.getTemporaryCourses();
        ArrayList<sharedmodels.department.TemporaryCourse> temporaryCourses1 = new ArrayList<>();
        for (TemporaryCourse temporaryCourse : temporaryCourses) {
            temporaryCourses1.add(temporaryCourse.toShared());
        }
        response.addData("temporaryCourses", temporaryCourses1);
        List<PassedCourse> passedCourses = student.getPassedCourses();
        ArrayList<sharedmodels.department.PassedCourse>passedCourses1 = new ArrayList<>();
        for (PassedCourse passedCourse : passedCourses) {
            passedCourses1.add(passedCourse.toShared());
        }
        response.addData("passedCourses", passedCourses1);
        List<Course> coursesHave = student.getCourses();
        ArrayList<sharedmodels.department.Course> courses2 = new ArrayList<>();
        for (Course course : coursesHave) {
            courses2.add(course.toShared());
        }
        response.addData("coursesHave", courses2);
        List<Course> courseList1 = student.getStarredCourses();
        ArrayList<sharedmodels.department.Course> starredCourses = new ArrayList<>();
        for (Course course : courseList1) {
            starredCourses.add(course.toShared());
        }
        response.addData("starredCourses", starredCourses);
        response.addData("suggestedCourses", starredCourses);
        //send suggested courses for student

        findClientAndSendResponse(clientId, response);
    }

    private ArrayList<Chat> getAllChats(String username) {
        List<Message> messages = Load.fetchAll(Message.class);
        ArrayList<Chat> chats = new ArrayList<>();
        ArrayList<String> chatWith = new ArrayList<>();
        for (Message message : messages) {
            String sender = message.getSender().getUsername();
            String receiver = message.getReceiver().getUsername();
            if (sender.equals(username)){
                if (isOk(chatWith, receiver)){
                    chatWith.add(receiver);
                }
            }else if (receiver.equals(username)){
                if (isOk(chatWith, sender))chatWith.add(sender);
            }
        }
        for (String id : chatWith) {
            ArrayList<sharedmodels.chatroom.Message> messages1 = getAllMessages(messages, username, id);
            Chat newChat = new Chat();
            newChat.setMessages(messages1);
            newChat.setSenderId(username);
            newChat.setReceiverId(id);
            User user = Load.fetch(User.class, id);
            newChat.setReceiverName(user.getFullName());
            newChat.setReceiverImageByes(user.getUserImageBytes());
            sharedmodels.chatroom.Message lastMessage = messages1.get(messages1.size() - 1);
            if (lastMessage.getMessageType() == sharedmodels.chatroom.MessageType.TEXT){
                newChat.setLastMessage(lastMessage.getMessageText());
            }else {
                newChat.setLastMessage("Shared a file!");
            }
            chats.add(newChat);
        }
        return chats;
    }

    private ArrayList<sharedmodels.chatroom.Message> getAllMessages(List<Message> messages, String username, String id) {
        ArrayList<sharedmodels.chatroom.Message> messages1 = new ArrayList<>();
        for (Message message : messages) {
            if ((message.getSender().getUsername().equals(username)
                    && message.getReceiver().getUsername().equals(id))||(message.getReceiver().getUsername().equals(username)
                    && message.getSender().getUsername().equals(id))){
                messages1.add(message.toShared());
            }
        }
        return messages1;
    }

    private boolean isOk(ArrayList<String> ids, String username) {
        for (String id : ids) {
            if (id.equals(username))return false;
        }
        return true;
    }

    private void sendAllMohseniData(int clientId, Request request) {
        Response response = new Response();
        String username = (String) request.getData("username");
        User mohseni = Load.fetch(User.class, username);
        response.addData("user", mohseni.toShared());
        ArrayList<Student> students = (ArrayList<Student>) Load.fetchAll(Student.class);
        ArrayList<SharedStudent> sharedStudents = new ArrayList<>();
        for (Student student : students) {
            sharedStudents.add((SharedStudent) student.toShared());
        }
        response.addData("students", sharedStudents);
        findClientAndSendResponse(clientId, response);
    }

    private void sendConnectionState(int clientId, Request request) {
        Response response = new Response();
        findClientAndSendResponse(clientId, response);
    }

    private void newMessage(int clientId, Request request) {
        Message message = new Message();
        message.setMessageText((String) request.getData("text"));
        User sender = Load.fetch(User.class, (String)request.getData("sender"));
        User receiver = Load.fetch(User.class, (String)request.getData("receiver"));
        message.setSender(sender);
        message.setReceiver(receiver);
        String type = (String) request.getData("type");
        if (type.equals("FILE"))message.setMessageType(MessageType.FILE);
        else message.setMessageType(MessageType.TEXT);
        message.setTime(DateAndTime.getDateAndTime());
        message.setFileType("");
        Save.save(message);
    }

    private void sendAllAdminData(int clientId) {
        Response response = new Response();
        response.setHashMap();
        User user = Load.fetch(User.class, "1");
        SharedUser sharedUser = user.toShared();
        response.addData("user", sharedUser);
        List<Message> messages = Load.fetchAll(Message.class);
        ArrayList<sharedmodels.chatroom.Message> messages1 = new ArrayList<>();
        for (Message message : messages) {
            if (message.getReceiver().getUsername().equals("1")){
                messages1.add(message.toShared());
            }
        }
        response.addData("messages", messages1);
        findClientAndSendResponse(clientId, response);
    }

    private void changePassword(int clientId, Request request) {
        String username = (String)request.getData("username");
        String prePass = (String)request.getData("prePass");
        String newPass = (String) request.getData("newPass");
        User user = Load.fetch(User.class, username);
        Response response = new Response();
        if (user.getPassword().equals(prePass)){
            user.setPassword(newPass);
            String timeNow = DateAndTime.getDateAndTime();
            user.setLastLogin(timeNow);
            response.setStatus(ResponseStatus.OK);
            String changePassNotice = config.getProperty(String.class, "changePassNotice");
            response.setErrorMessage(changePassNotice);
        }else {
            String changePassError = config.getProperty(String.class, "changePassError");
            response.setErrorMessage(changePassError);
        }
        Update.update(user);
        findClientAndSendResponse(clientId, response);
    }

    private void sendCaptchaResponse(int clientId) {
        String randomCaptcha = Captcha.getRandomCaptcha();
        String captchaNumber = Captcha.getNumberOfCaptcha(randomCaptcha);
        String captchaBytes = EncodeDecodeFile.encode(randomCaptcha);
        Response response = new Response();
        response.setHashMap();
        response.addData("captchaPic", captchaBytes);
        response.addData("captchaNumber", captchaNumber);
        findClientAndSendResponse(clientId, response);
    }

    private void sendLoginResponse(int clientId, Request request) {
        String username = (String) request.getData("username");
        String password = (String) request.getData("password");
        Response response = new Response();
        response.setHashMap();
        if (!Load.isExistUser(username)){
            String existanceUserError = config.getProperty(String.class, "existanceUserError");
            response.setErrorMessage(existanceUserError);
            response.setStatus(ResponseStatus.ERROR);
        }else if (!Load.isCorrectPass(username, password)){
            String passIncorrectError = config.getProperty(String.class, "passIncorrectError");
            response.setErrorMessage(passIncorrectError);
            response.setStatus(ResponseStatus.ERROR);
        }else {
            User user = Load.fetch(User.class, username);
            String lastLogin = user.getLastLogin();
            String timeNow = DateAndTime.getDateAndTime();
            boolean checkTime = CheckDateTime.isOverTime(lastLogin, timeNow);
            if (checkTime){
                response.setStatus(ResponseStatus.TIME_LIMIT);
                response.addData("user", user.toShared());
            }else {
                response.setStatus(ResponseStatus.OK);
                SharedUser sharedUser = user.toShared();
                response.addData("user", sharedUser);
            }
        }
        findClientAndSendResponse(clientId, response);
    }


}
