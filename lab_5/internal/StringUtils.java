package internal;



public class StringUtils {

    public static String betterString(String s1, String s2, BetterCallback cb){
        return cb.apply(s1, s2) ? s1 : s2;
    }
}