package array;

public class GlobalAndLocalInversions775 {

	
	//<문제풀이1>
	
	//간.파 했다구 친구
	
	//괜히 permutation준게 아니그등
	
	//예시 준것들 보면 하나의 패턴이 있음
	
	//Example 1:
	//
	//Input: A = [1,0,2]
	//Output: true
	//Explanation: There is 1 global inversion, and 1 local inversion.
	//Example 2:
	//
	//Input: A = [1,2,0]
	//Output: false
	//Explanation: There are 2 global inversions, and 1 local inversion.
	
	//falase나오는 것들은 죄다 A[i]의 값과 그것의 인덱스가 2이상씩 차이남
	
	//Runtime: 1 ms, faster than 95.03% of Java online submissions for Global and Local Inversions.
	//Memory Usage: 40.1 MB, less than 100.00% of Java online submissions for Global and Local Inversions.
    public static boolean isIdealPermutation(int[] A) {
        for(int i = 0; i < A.length; i++){
            if(Math.abs(i - A[i]) > 1) return false;
        }
        return true;
    }
}
