package login;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckDateTime {
    public static boolean isOverTime(String lastTime, String newTime) {
        if (lastTime == null || lastTime == "") return false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 'At' HH:mm:ss");
        LocalDateTime last = LocalDateTime.parse(lastTime, formatter);
        LocalDateTime now = LocalDateTime.parse(newTime, formatter);
        last = last.plusHours(3);
        if (now.isAfter(last)) {
            return true;
        } else {
            return false;
        }
    }
}
