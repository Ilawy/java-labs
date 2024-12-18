import java.util.Random;
import stdx.RandSearch;
class FindMax{
	public static void main(String[] args){
		int[] result = RandSearch.populate();
		int maxVal = result[0];
		int minVal = result[0];
		long aTime = System.nanoTime();
		for(int i = 0; i < 1000; i++){
			if(result[i] > maxVal)maxVal = result[i];
			if(result[i] < minVal)minVal = result[i];
			// System.out.println(result[i]);
		}
		System.out.println("Max: " + maxVal);
		System.out.println("Min: " + minVal);
				System.out.println("took " + (System.nanoTime() - aTime) + "ns");

	}
}
