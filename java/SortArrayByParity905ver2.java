
/* 
	<문제풀이 by lee215>
	
	주어진 A를 for문으로 돌면서, 2로 나누었을때 나머지가 0인 수(짝수)를 발견하면, j인덱스와 위치를 바꾼다. 
	인덱스 j는 0부터 시작하여, 자리를 swap할 때 마다, +1씩 더해준다.
	
	문제의 특성상, 짝수들과 홀수들 사이의 순서의 제약이 없으니, 위와같은 풀이가 가능하다.
	
 */

package Array;


public class SortArrayByParity905ver2 {
	public static int[] sortArrayByParity(int[] A)
	{
		for (int i = 0, j = 0; j < A.length; j++)
            if (A[j] % 2 == 0) {
                int tmp = A[i];
                A[i++] = A[j];
                A[j] = tmp;;
            }
        return A;
	}
	
	
	public static void main(String[] args) 
	{
		int[] A = {3,1,2,4};
		System.out.println(sortArrayByParity(A));
	}
}
