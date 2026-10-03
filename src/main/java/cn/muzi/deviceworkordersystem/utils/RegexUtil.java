package cn.muzi.deviceworkordersystem.utils;

public class RegexUtil {
    public static boolean isPhone(String phone) {
        if (phone == null || phone.isBlank()) {
            return false;
        }
        return phone.matches("1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}");
    }
}
