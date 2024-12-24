package liblib;

import java.util.stream.IntStream;

public class Utils {
    static String repeat(String value, int count){
        String result = "";
        for(int i = 0; i < count; i++)result += value;
        return result;
    }
}
