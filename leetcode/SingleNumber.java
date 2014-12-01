public class Solution {
    public int singleNumber(int[] A) {
    	int result = A[0];
    	int i=1;
    	while(i<A.length) {
    		result ^= A[i];
    		i++;
    	}
    	return result;
    }
}
