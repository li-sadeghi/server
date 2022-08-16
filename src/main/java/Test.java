import load.Load;
import servermodels.users.User;

public class Test {
    public static void main(String[] args) {
        User user = Load.fetch(User.class, "1");
        System.out.println(user.toString());
    }
}
