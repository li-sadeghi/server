import config.Config;
import network.Server;

public class Main {
    public static void main(String[] args) {
        Integer port = Config.getConfig().getProperty(Integer.class, "serverPort");
        Server server = new Server(port);
        server.start();
    }
}
