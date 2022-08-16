package servermodels.security;

import java.util.Random;

public class Captcha {
    private static String[] allCaptcha = {"captchaImages/1709.jpg", "captchaImages/9097.jpg",
            "captchaImages/8257.jpg", "captchaImages/7832.jpg",
            "captchaImages/7659.jpg", "captchaImages/7421.jpg",
            "captchaImages/6821.jpg", "captchaImages/6790.jpg",
            "captchaImages/6622.jpg", "captchaImages/6600.jpg",
            "captchaImages/6525.jpg", "captchaImages/6139.jpg",
            "captchaImages/6134.jpg", "captchaImages/5982.jpg",
            "captchaImages/5931.jpg", "captchaImages/5386.jpg",
            "captchaImages/4655.jpg", "captchaImages/4457.jpg",
            "captchaImages/4318.jpg", "captchaImages/3869.jpg",
            "captchaImages/3834.jpg", "captchaImages/3607.jpg",
            "captchaImages/3457.jpg", "captchaImages/3404.jpg",
            "captchaImages/3257.jpg", "captchaImages/2968.jpg",
            "captchaImages/2659.jpg", "captchaImages/2595.jpg",
            "captchaImages/2561.jpg", "captchaImages/2499.jpg",
            "captchaImages/2495.jpg", "captchaImages/2429.jpg",
            "captchaImages/2424.jpg", "captchaImages/2349.jpg", "captchaImages/1857.jpg",};

    public static String getRandomCaptcha() {
        Random random = new Random();
        int rndNumber = Math.abs(random.nextInt()) % 35;
        return "./src/main/resources/" +allCaptcha[rndNumber];
    }
    public static String getNumberOfCaptcha(String captchaPath) {
        return captchaPath.substring(35, 39);
    }
}
