import config.Config;
import network.Server;
import org.hibernate.SessionFactory;
import save.Save;

public class Main {
    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = Save.sessionFactory;
            Integer port = Config.getConfig().getProperty(Integer.class, "serverPort");
            Server server = new Server(port);
            server.start();
        }catch (Exception e){
            System.err.println("connection to database failed");
            throw e;
        }
    }
}
