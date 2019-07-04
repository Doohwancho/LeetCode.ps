/*
	Alice and Bob have candy bars of different sizes: A[i] is the size of the i-th bar of candy that Alice has, and B[j] is the size of the j-th bar of candy that Bob has.
	
	Since they are friends, they would like to exchange one candy bar each so that after the exchange, they both have the same total amount of candy.  (The total amount of candy a person has is the sum of the sizes of candy bars they have.)
	
	Return an integer array ans where ans[0] is the size of the candy bar that Alice must exchange, and ans[1] is the size of the candy bar that Bob must exchange.
	
	If there are multiple answers, you may return any one of them.  It is guaranteed an answer exists.
	
	 
	
	Example 1:
	
	Input: A = [1,1], B = [2,2]
	Output: [1,2]
	Example 2:
	
	Input: A = [1,2], B = [2,3]
	Output: [1,2]
	Example 3:
	
	Input: A = [2], B = [1,3]
	Output: [2,3]
	Example 4:
	
	Input: A = [1,2,5], B = [2,4]
	Output: [5,4]
	
	
	<문제>
	
	A와 B의 리스트가 A = {2}, B = {1,3} 이런식으로 주어진다.
	
	A와 B는 본인의 리스트 안의 한개의 element를 서로 바꿔서, A리스트의 합과 B리스트의 합을 갖게 만들면 된다.
	
	[A가 주어야 하는 element, B가 주어야 하는 element]를 반환하라.
	
	
	<문제 풀이 - trial01>
	
	가장 기초적인 방법으로, 모든 element를 2중 for문으로 돌아가며 A와 B의 합을 비교 후, 맞으면 반환
	
	...을 짜려고 했으나, if(swapSum(A,B,i,j)) 이 부분에서 in-place방식으로 원본 A,B가 수정되서 에러.

 * 
 */
//package Array;
//
//import java.util.stream.IntStream;
//
//public class FairCandySwap888 {
//	
//	public static boolean swapSum(int[] A, int[] B,int idxA, int idxB)
//	{
//		int tmp;
//		tmp = A[idxA];
//		A[idxA] = B[idxB];
//		B[idxB] = tmp;
//		
//		return IntStream.of(A).sum() == IntStream.of(B).sum();
//	}
//	
//	public static int[] fairCandySwap(int[] A, int[] B) 
//	{
//        for(int i = 0; i < A.length; i++)
//        {
//        	for(int j = 0; j < B.length; j++)
//        	{
//        		if(swapSum(A,B,i,j))                         //바꾼다음에 어떻게 추가 메모리 생성없이 제자리로 돌리지?
//        		{
//        			int[] rst = new int[2];
//        			rst[0]=A[i];
//        			rst[1]=B[j];
//        			return rst;
//        		}
//        	}
//        }
//		return null;                                         
//    }
//	
//	public static void main(String[] args) 
//	{
//		int[] A = {2}, B = {1,3};
//		for(int element : fairCandySwap(A,B)) { System.out.println(element); }
//	}
//}

/*
 * <문제풀이2 - greedy approach by ankit041288>
 * 
 * 복잡하게 따로 swap함수를 만들지 않고, sum of A - A[i] + B[j]와 sum of B - B[j] + A[i]만으로 비교 가능.
 * 
 * Runtime: 531 ms, faster than 5.03% of Java online submissions for Fair Candy Swap.
   Memory Usage: 40.7 MB, less than 93.87% of Java online submissions for Fair Candy Swap.
 * 
 */


package Array;

import java.util.stream.IntStream;

public class FairCandySwap888 {
	
	public static int[] fairCandySwap(int[] A, int[] B) {
        int s1=0;
        int s2=0;
        //Calculating sum without swapping 
        for(int i =0; i < A.length; i ++) s1+=A[i];
        for(int i =0; i < B.length; i ++) s2+=B[i];
        int[] res = new int[2];
        for(int i=0; i <A.length; i++){
            for(int j=0; j<B.length; j++){
                //Core Logic 
                if(s1-A[i]+B[j]==s2-B[j]+A[i]){
                    res[0]=A[i]; res[1]=B[j];
                    break;
                }

            }
        }
        return res;
    }
	
	public static void main(String[] args) 
	{
		int[] A = {2}, B = {1,3};
		for(int element : fairCandySwap(A,B)) { System.out.println(element); }
	}
}
