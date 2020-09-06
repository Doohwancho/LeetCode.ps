package september;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ImageOverlap {
	
	//<Trial01>
	
	//가로에만 총 몇개있는지, 세로에만 총 몇개있는지 구하고,
	
	//a랑 b를 sliding window 하면 될 줄 알았는데,
	
	//[[1,1,1],
	// [1,0,0],
	// [0,1,1]]
	
	//[[1,1,0],
	// [1,1,1],
	// [1,1,0]]
	
	//이 예제가 나오면, 막힘.
	
	//[1,0,1]과 [0,1,0]을 비교해보면,
	
	//가로는 101,010이라 sliding-window해서 나오는 값이 1이고,
	
	//세로도 2,1이라, sliding-window해서 나오는값이 1
	
	//얘가 얘 혼자만 있으면 이방식이 가능한데, 다른 여러개 애들이랑 슦까져있으면 안되더라고.
	
    private void insert(int[][] C, int[] c, boolean row, boolean extra){
        int len = C.length;
        
        if(row){
            if(extra){
                for(int i = 0, i_ = len-1, cnt = 0; i < len; i++, i_++){
                    for(int j = 0; j < len; j++){
                        if(C[i][j] == 1) cnt++;
                    }
                    c[i_] = cnt;
                    cnt = 0;
                }
            } else {
                for(int i = 0, cnt = 0; i < len; i++){
                    for(int j = 0; j < len; j++){
                        if(C[i][j] == 1) cnt++;
                    }
                    c[i] = cnt;
                    cnt = 0;
                }
            }
        } else {
            if(extra){
                for(int i = 0, i_ = len-1, cnt = 0; i < len; i++, i_++){
                    for(int j = 0; j < len; j++){
                        if(C[j][i] == 1) cnt++;
                    }
                    c[i_] = cnt;
                    cnt = 0;
                }
            } else {
                for(int i = 0, cnt = 0; i < len; i++){
                    for(int j = 0; j < len; j++){
                        if(C[j][i] == 1) cnt++;
                    }
                    c[i] = cnt;
                    cnt = 0;
                }   
            }
        }
    }
    
    private int slidingWindow(int[] a, int[] b){
        int max = 0;
        int len = b.length;
        
        for(int i = 0, acc = 0; i < len*2-1; i++){
            for(int i_ = i, j = 0; j < len; i_++, j++){
                acc += Math.min(a[i_], b[j]);
            }
            max = Math.max(max, acc);
            acc = 0;
        }
        
        return max;
    }
    
    public int largestOverlap(int[][] A, int[][] B) {
        int[] aRow = new int[A.length*3-2];
        int[] aColumn = new int[A.length*3-2];
        int[] bRow = new int[B.length];
        int[] bColumn = new int[B.length];
        
        insert(A, aRow, true, true);
        insert(A, aColumn, false, true);
        insert(B, bRow, true, false);
        insert(B, bColumn, false, false);
        
        return Math.min(slidingWindow(aRow, bRow), slidingWindow(aColumn, bColumn));
    }
    
    
    
    //<문제풀이1 by lkjhlkjhasdf1>
    
    //brute-force
    
    //그림설명 : https://leetcode.com/problems/image-overlap/discuss/832126/Question-Explained-or-Java-Code
    
    //Runtime: 43 ms
    //Memory Usage: 38.7 MB
    
    public int largestOverlap(int[][] A, int[][] B) {
        int largestOverlap = 0;
        for (int row = -A.length + 1; row < A.length; row++)
            for (int col = -A[0].length + 1; col < A[0].length; col++)
                largestOverlap = Math.max(largestOverlap, overlappingOnes(A, B, row, col));
        return largestOverlap;
    }

    public int overlappingOnes(int[][] A, int[][] B, int rowOffset, int colOffset) {
        int overlapOnes = 0;
        for (int row = 0; row < A.length; row++) {
            for (int col = 0; col < A[0].length; col++) {
                if ((row + rowOffset < 0 || row + rowOffset >= A.length) || (col + colOffset < 0 || col + colOffset >= A.length) || (A[row][col] + B[row + rowOffset][col + colOffset] != 2))
                    continue;
                overlapOnes++;
            }
        }
        return overlapOnes;
    }
    
    
    //<문제풀이2 by lee215>
    
	//Assume A and B are 3 * 3 matrix.:
    
	//A:
	//1,0,1
	//1,0,0
	//1,1,1
    
	//B:
	//0,0,1
	//0,1,1
	//1,1,1
    
	//Flatten each of them to 1-D array:
	//flattened idx: 0,1,2,3,4,5,6,7,8
	//flattened A: 1,0,1,1,0,0,1,1,1 -> 0,2,3,6,7,8 : LA
	//flattened B: 0,0,1,0,1,1,1,1,1 -> 2,4,5,6,7,8 : LB
    
	//Each '1' in A can be overlapped with each '1' in B for different offset.
	//Iterate through every overlap situation for '1' (at i) in LA and '1' (at j) in LB, group by offset (i - j).
    
	//Final step is to find the largest number of overlapped '1's among all offsets.
    
    
    
    //만약 
    
    //[[1,1,0],
    // [0,1,0],
    // [0,1,0]]
    
    //[[0,0,0],
    // [0,1,1],
    // [0,0,1]]
    
    //을 넣으면, counts가 아래와 같이 나옴
    
    //키 : 0, 값 : 1
	//키 : -1, 값 : 2
	//키 : -100, 값 : 1
	//키 : 99, 값 : 1
	//키 : -101, 값 : 3
	//키 : 100, 값 : 1
	//키 : -102, 값 : 1
	//키 : -201, 값 : 1
	//키 : -202, 값 : 1
    
    //아 쥰내 똑똑하네.
    
    //같은 거리만큼 떨어져있는걸 기록한거아녀
    
    //Runtime: 60 ms
    //Memory Usage: 40.2 MB
    
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
                count.put(i - j, count.getOrDefault(i - j, 0) + 1); //?
        int res = 0;
        for (int i : count.values())
            res = Math.max(res, i);
        return res;
    }
}
