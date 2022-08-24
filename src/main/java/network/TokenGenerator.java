package network;

import config.Config;

import java.security.SecureRandom;

public class TokenGenerator {
    public static Config config = Config.getConfig();
    private static final SecureRandom random = new SecureRandom();

    public static String generateToken() {
        int byteSize = config.getProperty(Integer.class, "byteSize");
        byte[] bytes = new byte[byteSize];
        random.nextBytes(bytes);
        StringBuilder sb = new StringBuilder("");
        for (byte aByte : bytes) {
            sb.append((char) aByte);
        }
        return sb.toString();
    }
}
