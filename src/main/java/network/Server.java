package network;

import load.Load;
import login.CheckDateTime;
import request.Request;
import request.RequestType;
import response.Response;
import response.ResponseStatus;
import save.Save;
import save.Update;
import servermodels.chatroom.Message;
import servermodels.chatroom.MessageType;
import servermodels.security.Captcha;
import servermodels.users.Student;
import servermodels.users.User;
import sharedmodels.users.SharedStudent;
import sharedmodels.users.SharedUser;
import time.DateAndTime;
import util.extra.EncodeDecodeFile;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private final ArrayList<ClientHandler> clients;
    private static int clientCount = 0;

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
                sendAllMastterData(clientId, request);
            }
        }
    }

    private void sendAllMastterData(int clientId, Request request) {
    }

    private void sendAllStudentData(int clientId, Request request) {
    }

    private void sendAllMohseniData(int clientId, Request request) {
        Response response = new Response();
        String username = (String) request.getData("username");
        User mohseni = Load.fetch(User.class, username);
        response.addData("user", mohseni);
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
//        message.setMessageType((MessageType) request.getData("type"));
        Save.save(message);
    }

    private void sendAllAdminData(int clientId) {
        Response response = new Response();
        response.setHashMap();
        User user = Load.fetch(User.class, "1");
        SharedUser sharedUser = user.toShared();
        response.addData("user", sharedUser);
//        ArrayList<Message> messages = (ArrayList<Message>) Load.fetchWithCondition(Message.class, "receiver", user);
        ArrayList<sharedmodels.chatroom.Message> messages1 = new ArrayList<>();
//        for (Message message : messages) {
//            messages1.add(message.toShared());
//        }
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
            response.setErrorMessage("your password changed successfully!");
        }else {
            response.setErrorMessage("your previous password is wrong!");
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
        System.out.println(username);
        Response response = new Response();
        response.setHashMap();
        if (!Load.isExistUser(username)){
            response.setErrorMessage("user is not exists");
            response.setStatus(ResponseStatus.ERROR);
        }else if (!Load.isCorrectPass(username, password)){
            response.setErrorMessage("password is incorrect");
            response.setStatus(ResponseStatus.ERROR);
        }else {
            User user = Load.fetch(User.class, username);
            String lastLogin = user.getLastLogin();
            String timeNow = DateAndTime.getDateAndTime();
            boolean checkTime = CheckDateTime.isOverTime(lastLogin, timeNow);
            if (checkTime){
                response.setStatus(ResponseStatus.TIME_LIMIT);
            }else {
                response.setStatus(ResponseStatus.OK);
                SharedUser sharedUser = user.toShared();
                System.out.println(sharedUser.getFullName());
                response.addData("user", sharedUser);
            }
        }
        findClientAndSendResponse(clientId, response);
    }


}
