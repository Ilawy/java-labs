package stdx;

import java.util.Random;

public class RandSearch{

		public static int []populate(){
			Random rand = new Random();
			int[] result = new int [1000];
			for(int i = 0; i < 1000; i++){
				result[i] = (rand.nextInt(2048));
			}
			return result;
		}

		public static int binarySearch(int[] sorted, int value){
			int left = 0;
			int right = sorted.length - 1;
			while(left <= right){
				int mid = (left + right) / 2;
				if(sorted[mid] == value){
					return mid;
				}
				if(sorted[mid] < value){
					left = mid + 1;
				}else{
					right = mid - 1;
				}
			}
			return -1;
		}
	
}
