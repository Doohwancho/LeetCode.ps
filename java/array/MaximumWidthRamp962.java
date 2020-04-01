/*
	Given an array A of integers, a ramp is a tuple (i, j) for which i < j and A[i] <= A[j].  The width of such a ramp is j - i.
	
	Find the maximum width of a ramp in A.  If one doesn't exist, return 0.
	
	 
	
	Example 1:
	
	Input: [6,0,8,2,1,5]
	Output: 4
	Explanation: 
	The maximum width ramp is achieved at (i, j) = (1, 5): A[1] = 0 and A[5] = 5.
	Example 2:
	
	Input: [9,8,1,0,1,9,4,0,4,1]
	Output: 7
	Explanation: 
	The maximum width ramp is achieved at (i, j) = (2, 9): A[2] = 1 and A[9] = 1.
	 
	
	Note:
	
	2 <= A.length <= 50000
	0 <= A[i] <= 50000
	
	
	
	
	<문제>
	
	[6,0,8,2,1,5]
	
	이렇게 어레이가 나오잖어?
	
	인덱스 i,j가 있어.
	
	i<j 야.
	
	A[i] <= A[j]조건을 만족시키는 것 중에
	
	j-i가 가장 큰 수를 뽑아.
 */

package array;

import java.util.Stack;

public class MaximumWidthRamp962 {
	
	/*
	//<문제풀이1>
	
	//brute-force
	
	//어씨 개느리네
	
	//Runtime: 1195 ms, faster than 13.31% of Java online submissions for Maximum Width Ramp.
	//Memory Usage: 47.4 MB, less than 100.00% of Java online submissions for Maximum Width Ramp.
    public int maxWidthRamp(int[] A) {
        int rst = 0;
        for(int r = A.length-1; r-rst > 0; r--){ 
            for(int l = 0; r-l > rst; l++){      //r-l>rst한 이유는 어짜피 r-l이 이전에 뽑은것보다 더 작아버리면 loop돌 의미가 없자너
                if(A[l] <= A[r]){
                    rst = Math.max(rst, r-l);
                }
            }
        }
        return rst;
    }
    */
    
	/*
    //<문제풀이2 by leetcode solution>
    
    //Your input : [6,0,8,2,1,5]
    
	//stdout
	//int[] B = 1 4 3 5 0 2 
    
	//ans: 0 m: 1
	//ans: 3 m: 1
	//ans: 3 m: 1
	//ans: 4 m: 1
	//ans: 4 m: 0
	//ans: 4 m: 0

    //Output : 4
	
	//Runtime: 42 ms, faster than 35.43% of Java online submissions for Maximum Width Ramp.
	//Memory Usage: 48.9 MB, less than 100.00% of Java online submissions for Maximum Width Ramp.
    public int maxWidthRamp(int[] A) {
        int len = A.length;
        Integer[] B = new Integer[len];
        for(int i = 0; i < A.length; i++){
            B[i] = i;
        }
        
        Arrays.sort(B, (i,j)-> ((Integer) A[i]).compareTo(A[j])); //.compareTo()는 Integer클래스의 library이므로 B선언시 primitive type인 int로 선언 안하고 Integer로 선언함. 
        
        int rst = 0;
        int m = len;
        
        for(Integer b : B){
            rst = Math.max(rst, b-m);
            m = Math.min(m, b);
        }
        return rst;
    }
    */
	
	/*
	//<문제풀이 3 by aakarshmadhavan>
	
	//int[] test = {6,0,8,2,1,5};
	//map: {6=0}
	//rst: 0
	//map: {0=1, 6=0}
	//rst: 0
	//map: {0=1, 6=0}
	//rst: 2
	//map: {0=1, 6=0}
	//rst: 2
	//map: {0=1, 6=0}
	//rst: 3
	//map: {0=1, 6=0}
	//rst: 4
	
	//map에있는 keySet중에서 A[i]보다 같거나 작은거 다 for문 돌아야 할 것 같은데, 
	
	//신기하게도 map.floorKey()로 A[i]보다 같거나 작은것 중에 젤 큰것만 체크해도 되네
	
	//Runtime: 44 ms, faster than 30.15% of Java online submissions for Maximum Width Ramp.
	//Memory Usage: 48.5 MB, less than 100.00% of Java online submissions for Maximum Width Ramp.
    public static int maxWidthRamp(int[] A) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int rst = 0;
        
        for(int i = 0; i < A.length; i++){
            if(map.floorKey(A[i]) == null){ //insert only when there is no lesser value than A[i] 
                map.put(A[i], i);
            } else {
                rst = Math.max(rst, i - map.get(map.floorKey(A[i]))); //map.floorKey() Returns: the greatest key less than or equal to key,or null if there is no such key
            }
        }
        return rst;
    }
    */
	
	//<문제풀이4 by lee215>
	
	//int[] test = {3,2,1,0,7,8,9};
    
	//stack : [0, 1, 2, 3]
	//i: 6 stack: [] res: 6
	//ans : 6
	
	//극도로 최적화했네
	
	//Runtime: 7 ms, faster than 81.91% of Java online submissions for Maximum Width Ramp.
	//Memory Usage: 47.6 MB, less than 100.00% of Java online submissions for Maximum Width Ramp.

    public static int maxWidthRamp(int[] A) {
        Stack<Integer> s = new Stack<>();
        int res = 0, n = A.length;
        for (int i = 0; i < n; ++i) { //줄어들면 더해. stack의 맨 마지막꺼보다 더 커지면 어짜피 인덱스는 늘어났는데 값도 커졌네? 그럼 이전거 쓰면 되니까 필요 없잖어.
            if (s.empty() || A[s.peek()] > A[i]) { //.peek() returns object at the top of the stack
                s.add(i);
            }
        }
        System.out.println(s);
        for (int i = n - 1; i > res; --i) {
            while (!s.empty() && A[s.peek()] <= A[i]) { //
                res = Math.max(res, i - s.pop()); //.pop()으로 제거하는 이유는, i가 한칸씩 -1되서 앞으로 땡겨지면, stack에 있는 index도 손해자너
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
    	int[] test = {3,2,1,0,7,8,9};
    	System.out.println(maxWidthRamp(test));
	}
}
