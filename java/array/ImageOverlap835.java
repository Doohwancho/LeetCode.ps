/*
	Two images A and B are given, represented as binary, square matrices of the same size.  (A binary matrix has only 0s and 1s as values.)
	
	We translate one image however we choose (sliding it left, right, up, or down any number of units), and place it on top of the other image.  After, the overlap of this translation is the number of positions that have a 1 in both images.
	
	(Note also that a translation does not include any kind of rotation.)
	
	What is the largest possible overlap?
	
	Example 1:
	
	Input: A = [[1,1,0],
	            [0,1,0],
	            [0,1,0]]
	       B = [[0,0,0],
	            [0,1,1],
	            [0,0,1]]
	Output: 3
	Explanation: We slide A to right by 1 unit and down by 1 unit.
	Notes: 
	
	1 <= A.length = A[0].length = B.length = B[0].length <= 30
	0 <= A[i][j], B[i][j] <= 1
	
	
	
	<문제>
	
	A와 B는 2진수가 들어있는 2차원 리스트다.
	
	A 혹은 B를 상하좌우로 옮겨서 다른 한쪽에 겹쳤을 때, 1의 갯수가 가장 많이 겹쳐진 수를 반환하라.
	
 */
package Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ImageOverlap835 {
	
	/* 
	 
	//Trial01
	 
    //step01 두개를 겹친다.
    
    //step02 두개중에 더 작은거를 움직인다?? 
	
	//30x30이면 겁나 많이 돌아야 함. 에바참치.
	
	
	//Trial02
	
	//step01 A for문 돌면서 1을 만나면, dfs로 방향을 queue에 담으면서 돈곳은 0으로 만듬
    
    //step02 그 방향 순서대로 B를 돌면서 Math.max(rst); 
	
	//만약 뭉쳐진게 3이고 떨어진게 7인데, 뭉쳐진게 5고 떨어진게 3인 블록이 있으면 망함
	
	//그래서 이 방법은 떨어진 블록도 카운트 해야함
	 
	//그러면 brute-force와 별반 다를게 없음
	
	
	//방법3
	
	//step01 A+B합침
	 
	//step02 A와 B중 1의 숫자가 더 적은쪽을 이동하면서 2의 횟수를 Math.max()하는 방법
	  
	//근데 매 loop마다 1인거 -1해주고 +1해주고 if else 연산이 많아지는데 다가
	
	//이것도 brute-force와 별반 다를게 없음.
	 
	
	*/
	
	//<문제풀이 by lee215>
	
	//A의 1의 좌표를 hashtable형식으로 저장, B의 1의 좌표도 hashtable형태로 저장(*10000까지 안가고 *100선으로 함. 문제에서 2차원 리스트 최대길이가 30x30이라고 했기 때문)
	
	//1의 좌표만 2중 for문으로 돌면서, i-j(A의 1의 좌표 - B의 1의좌표)값이 같을 때마다 +1, 해주고 그 최대값을 리턴.
	
	
	//1(A)의 좌표가 (1,2)이고, 1(B)의 좌표가 (4,5)이면, 4-1 == 3, 5-2 == 3임.
	
	//3 * 100 + 3 == 303만큼의 차이를 가진 다른 1(A)와 1(B)를 구하는 것. 
	
	//만약 수평이동 했다고 쳐도, A.x - B.x 의 차이가 같을 것(A.y - B.y 는 0이고)
	
	//똑똑허이
	
	//Runtime: 51 ms, faster than 59.38% of Java online submissions for Image Overlap.
	//Memory Usage: 39.1 MB, less than 100.00% of Java online submissions for Image Overlap.
	
    public int largestOverlap(int[][] A, int[][] B) {
        int N = A.length;
        List<Integer> LA = new ArrayList<>(),  LB = new ArrayList<>();
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < N * N; ++i)
            if (A[i / N][i % N] == 1)
                LA.add(i / N * 100 + i % N);
        for (int i = 0; i < N * N; ++i)
            if (B[i / N][i % N] == 1)
                LB.add(i / N * 100 + i % N);
        for (int i : LA) for (int j : LB)
                count.put(i - j, count.getOrDefault(i - j, 0) + 1);
        int res = 0;
        for (int i : count.values())
            res = Math.max(res, i);
        return res;
    }	
}
