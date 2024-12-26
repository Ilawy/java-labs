package liblib;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    static String repeat(String value, int count){
        String result = "";
        for(int i = 0; i < count; i++)result += value;
        return result;
    }


    public static String[][] to2DStringArray(ArrayList<List<String>> list) {
        String[][] array = new String[list.size()][]; // Create the outer array
        for (int i = 0; i < list.size(); i++) {
            // Convert each inner ArrayList<String> to a String[]
            array[i] = list.get(i).toArray(new String[0]);
        }
        return array;
    }
}
