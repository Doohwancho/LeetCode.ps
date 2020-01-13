package Array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ArrayNesting565 {
	
	/*
	//<Trial01 - time limit exceeded>
	
	//brute-force
	
	//A = [5,4,0,3,1,6,2]
    //S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
    public int arrayNesting(int[] nums) {
        int rst = 0;
        
        for(int j : nums){
            Set<Integer> set = new HashSet<>();
            int num = j;
            
            while(set.add(nums[num])){
                num = nums[num];
            }    
            
            rst = Math.max(rst, set.size());
        }
        
        return rst;
    }
    */
	/*
	//<문제풀이1>
	
	//제한조건
	
	//N is an integer within the range [1, 20,000].
	//The elements of A are all distinct.
	
	//을 이용한 방법. 뭣도 모르면 int[]가 젤 빠르잖어
	
	//좀 괜찮은 부분은 while(++container[nums[num]] < 2) 이 부분
	
	//S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
	
	//를 예롤들면, 0으로 시작하나, 5,6,2로 시작하나 4라는 결과값은 같으니까,
	
	//0으로 한바퀴 돌면서 0,5,6,2를 +1해버리면, 다음에 똑같은걸 5,6,2로 또 돌때 < 2에 막혀서 중복 안돔.
	
	//Runtime: 6 ms, faster than 34.99% of Java online submissions for Array Nesting.
	//Memory Usage: 48.6 MB, less than 16.67% of Java online submissions for Array Nesting.
    
	public int arrayNesting(int[] nums) {
        int[] container = new int[20000];
    	int rst = 0;
        
        for(int j : nums){
        	int tmp = 0;
            int num = j;
            while(++container[nums[num]] < 2){ 
                num = nums[num];
                tmp++;
            }                
            rst = Math.max(rst, tmp);
        }
        return rst;
    }
	*/
	
	/*
	//<문제풀이2 by alexander>
	
	//문제풀이1과 이거랑 차이점은, 위는 +1밖에 안해서 while()문에서 한번이라도 도는데, 밑에꺼는 아예 -1로 해놔서, 이미 지나간곳은 안돔.
	
	//문제풀이1에서 지난간 곳을 +1 마킹한다는 아이디어는 좋았는데, 조금만 더 생각해서 아예 안가게 -1로 박아버릴껄.
	
	//이거랑 new int[20000]에 마킹하는게 5ms차이 나는 듯
	
	//Runtime: 1 ms, faster than 100.00% of Java online submissions for Array Nesting.
	//Memory Usage: 48.1 MB, less than 16.67% of Java online submissions for Array Nesting.
	
    public int arrayNesting(int[] a) {
        int maxsize = 0;
        for (int i = 0; i < a.length; i++) {
            int size = 0;
            for (int k = i; a[k] >= 0; size++) {
                int ak = a[k];
                a[k] = -1; // mark a[k] as visited;
                k = ak;
            }
            maxsize = Integer.max(maxsize, size);
        }
        return maxsize;
    }
	*/
	
	//<문제풀이3 by johnson9432>
	
	//문제풀이1의 문제점(다녀간데 표시) 해결 + 문제점2(리스트 크기를 nums크기로 하면 되는데 20000으로 박아버린 것)
	
	//+문제풀이2의 문제점(원본 리스트 nums의 값 훼손)을 해결한 가장 좋은 답안.
	
	//문제풀이1의 new int[20000]을 new int[nums.length]로 바꿨는데 2ms뜸. 
	
	//Runtime: 1 ms, faster than 100.00% of Java online submissions for Array Nesting.
	//Memory Usage: 48.3 MB, less than 16.67% of Java online submissions for Array Nesting.
	
    public int arrayNesting(int[] nums) {
        int maxLen = 0;
        boolean[] visited = new boolean[nums.length];
        for(int n : nums){
            int currLen = 0;
            while(!visited[n]){
                currLen++;
                visited[n] = true;
                n = nums[n];
            }
            maxLen = Math.max(maxLen, currLen);
        }
        return maxLen;
    }

}
