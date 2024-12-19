package stdx;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.StringTokenizer;
import java.util.Arrays;



public class Estring{

	public static String stringRepeat(String str, int times){
		String result = "";
		for(int i = 0; i < times; i++){
			result += str;
		}
		return result;
	}

	public static int FindOccurs(String str, String target){
		System.out.println(Arrays.toString(str.split(target, -1)) + " " +  str.split(target, -1).length);
		return str.split(target, -1).length - 1;
	}

	public static int FindOccurs3(String str, String target){
			Pattern pattern = Pattern.compile(target);
			Matcher matcher = pattern.matcher(str);
			int result = 0;
			while(matcher.find()){
				result++;
			}
			return result;
	}

	public static int FindOccurs2(String str, String target){
			int result = 0;
			int left = 0;
			while(left < str.length()){
				int val = str.substring(left).indexOf(target);
				if(val == -1) break;
				// System.out.println(val + " " + target);
				left += val+target.length();
				result++;
			}
			return result;
	}

	public static int FindOccurs4(String str, String target){
		int result = 0;
		StringTokenizer st1 = new StringTokenizer(str, target);
		System.out.println(st1.countTokens());
		return st1.countTokens() - 1;
	}
	
}
