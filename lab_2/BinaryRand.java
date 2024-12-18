import java.util.Arrays;


import stdx.RandSearch;

class BinaryRand{
	public static void main(String[] args){
		int [] array = (RandSearch.populate());
		Arrays.sort(array);
		long aTime = System.nanoTime();
		int result = RandSearch.binarySearch(array, 3);
		System.out.println("took " + (System.nanoTime() - aTime) + "ns");
	}
}
