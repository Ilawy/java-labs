package stdx;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.StringTokenizer;



public class Estring{

	public static String stringRepeat(String str, int times){
		String result = "";
		for(int i = 0; i < times; i++){
			result += str;
		}
		return result;
	}

	public static int findOccurs(String str, String target){
		return str.split(target, -1).length - 1;
	}

	public static int findOccurs3(String str, String target){
			Pattern pattern = Pattern.compile(target);
			Matcher matcher = pattern.matcher(str);
			int result = 0;
			while(matcher.find()){
				result++;
			}
			return result;
	}

	public static int findOccurs2(String str, String target){
			int result = 0;
			int left = 0;
			while(left < str.length()){
				int val = str.substring(left).indexOf(target);
				if(val == -1) break;
				left += val+target.length();
				result++;
			}
			return result;
	}

	public static int findOccurs4(String str, String target){
		int result = 0;
		StringTokenizer st1 = new StringTokenizer(str, " ");
		while (st1.hasMoreTokens()){
			String tok = st1.nextToken();
			if(tok.equals(target))result++;
		}
		return result;
	}
	
}
