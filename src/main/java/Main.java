import config.Config;
import load.CheckSelectionUnit;
import network.Server;
import org.hibernate.SessionFactory;
import save.Save;
import time.DateAndTime;

public class Main {
    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = Save.sessionFactory;
            Integer port = Config.getConfig().getProperty(Integer.class, "serverPort");
            String endTimeSelectionUnit = Config.getConfig().getProperty(String.class,"endSelectionTime");
            if (DateAndTime.isOver(endTimeSelectionUnit)){
                CheckSelectionUnit.checkCourses();
            }
            Server server = new Server(port);
            server.start();
        }catch (Exception e){
            System.err.println("connection to database failed");
            throw e;
        }
    }
}
