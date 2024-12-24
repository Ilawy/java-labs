import internal.StringUtils;

public class StringCompare {

    public static void main(String[] args) {
        String string1 = "Very tall";
        String string2 = "better";
        String longer = StringUtils.betterString(string1, string2, (s1, s2) -> s1.length() > s2.length());
        String first = StringUtils.betterString(string1, string2, (s1, s2) -> false);
        System.out.printf("%s\n%s\n", longer, first);        
    }

}