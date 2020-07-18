package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class MinDiffBtwLargestAndSmallestValInThreeMoves1509 {

	
	//<Trial01>
	
	//input : [6,6,0,1,1,4,6]
	
	//Output : 5
	//Expected : 2
	
	//각 숫자당 빈도수를 뽑고
	
	//빈도수가 젤 적은애 순으로 3개 제거한 후
	
	//남은 애들의 최댓값-최솟값 하면 될 줄 알았는데,
	
	//input이 [6,6,0,1,1,4,6] 일때 내 방법을 쓰면
	
	//0,4,1이 제거되서 [6,6,1,6]이 남음
	
	//4가 한번밖에 안나왔어도 살리고 0,1,1을 없애야 하는디..
	
	class Freq{
        int num, freq;
        Freq(int num, int freq){
            this.num = num;
            this.freq = freq;
        }
    }
    
    public int minDifference(int[] nums) {
        PriorityQueue<Freq> pq = new PriorityQueue<>((a,b)->a.freq-b.freq);
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int n : nums){
            map.put(n, map.getOrDefault(n, 0)+1);
        }
        
        for(int key : map.keySet()){
            pq.offer(new Freq(key, map.get(key)));
        }
        
        int cnt = 3;
        while(cnt > 0 && !pq.isEmpty()){    
            Freq tmp = pq.poll();
            if(tmp.freq > cnt){
                cnt = 0;
                tmp.freq -= cnt;
                pq.offer(tmp);
            } else {
                cnt -= tmp.freq;
            }
        }
        
        
        int largest = Integer.MIN_VALUE;
        int smallest = Integer.MAX_VALUE;
        
        while(!pq.isEmpty()){
            int val = pq.poll().num;
            largest = Math.max(largest, val);
            smallest = Math.min(smallest, val);
        }
        
        return largest-smallest;
    }
    
    
    //<문제풀이1 by lee215>
    
    //수학적으로 푼 경우
    
    //O(quick sort)
    
    //Explanation
	//We have 4 plans:
	
	//kill 3 biggest elements
	//kill 2 biggest elements + 1 smallest elements
	//kill 1 biggest elements + 2 smallest elements
	//kill 3 smallest elements
	
	//Example from @himanshusingh11:
	
	//A = [1,5,6,13,14,15,16,17]
	//n = 8
	
	//Case 1: kill 3 biggest elements
	
	//All three biggest elements can be replaced with 14
	//[1,5,6,13,14,15,16,17] -> [1,5,6,13,14,14,14,14] == can be written as A[n-4] - A[0] == (14-1 = 13)
	
	//Case 2: kill 2 biggest elements + 1 smallest elements
	
	//[1,5,6,13,14,15,16,17] -> [5,5,6,13,14,15,15,15] == can be written as A[n-3] - A[1] == (15-5 = 10)
	
	//Case 3: kill 1 biggest elements + 2 smallest elements
	
	//[1,5,6,13,14,15,16,17] -> [6,6,6,13,14,15,16,16] == can be written as A[n-2] - A[2] == (16-6 = 10)
	
	//Case 4: kill 3 smallest elements
	
	//[1,5,6,13,14,15,16,17] -> [13,13,13,13,14,15,16,17] == can be written as A[n-1] - A[3] == (17-13 = 4)
	
	//Answer is minimum of all these cases!
    
    //Runtime: 19 ms, faster than 75.56% of Java online submissions for Minimum Difference Between Largest and Smallest Value in Three Moves.
    //Memory Usage: 59.1 MB, less than 100.00% of Java online submissions for Minimum Difference Between Largest and Smallest Value in Three Moves.
    
    public int minDifference(int[] A) {
        int n = A.length, res = Integer.MAX_VALUE;
        if (n < 5) return 0;
        Arrays.sort(A);
        for (int i = 0; i < 4; ++i) {
            res = Math.min(res, A[n - 4 + i] - A[i]);
        }
        return res;
    }
    
    
    //<문제풀이2 by logan138>
    
    //dfs로 푼 경우
    
    //성능이 좀 구리...지가 않구나? pq보다 훨빠른데?
    
    //Runtime: 20 ms, faster than 60.25% of Java online submissions for Minimum Difference Between Largest and Smallest Value in Three Moves.
    //Memory Usage: 55 MB, less than 100.00% of Java online submissions for Minimum Difference Between Largest and Smallest Value in Three Moves.
    private int dfs(int[] nums, int left, int right, int bound){
        if(bound == 0) return nums[right]-nums[left];
        bound--;
        return Math.min(dfs(nums, left+1,right,bound),dfs(nums,left,right-1,bound));
    }
    
    public int minDifference(int[] nums) {
        int left = 0, right = nums.length-1;
        if(right <= 3) return 0;
        Arrays.sort(nums);
        return dfs(nums, left, right, 3);
    }
    
    //<문제풀이3 by mayank12559>
    
    //priority queue로 푼 경우
    
    //O(n + 4log4)
    
    //Runtime: 48 ms, faster than 5.65% of Java online submissions for Minimum Difference Between Largest and Smallest Value in Three Moves.
    //Memory Usage: 68.9 MB, less than 100.00% of Java online submissions for Minimum Difference Between Largest and Smallest Value in Three Moves.
    
    public int minDifference(int[] nums) {
        PriorityQueue<Integer> max = new PriorityQueue();
        PriorityQueue<Integer> min = new PriorityQueue(Collections.reverseOrder());
        for(int i: nums){
            if(max.size() < 4){
                max.add(i);
                min.add(i);
            }else{
                max.add(Math.max(max.poll(), i)); //가장 큰 원소 4개 
                min.add(Math.min(min.poll(), i)); //가장 작은 원소 4개 연속으로 담음
                //max : [4,6,6,6]
                //min : [4,1,1,0]
            }
        }
        List<Integer> al = new ArrayList();
        while(!min.isEmpty()){ //가장 작은 원소 4개 리스트에 담음(역순)
            al.add(min.poll()); //al : [4,1,1,0]
        }
        int ans = Integer.MAX_VALUE;
        for(int i=al.size()-1;i>=0;i--){
            ans = Math.min(ans, max.poll() - al.get(i)); //가장 큰 원소중에 젤 작은애랑, 가장 작은애중에서 젤 큰애랑 차이 중에 젤 작은애가 답.
            //input : [6,6,0,1,1,4,6]
            //max : [4,6,6,6]
            //min : [0,1,1,4]
            //(4-0),(6-1),(6-1),(6-4) -> 2 is smallest
            
            //왜 (젤큰애중 3번째, 가장 작은애), (젤큰애중 2번째, 2번째로 작은애 )... 등등 4번 페어링 하냐면
            //문제풀이 1과 이유가 동일.
        }
        return ans;
    }
    
    
    
    //<문제풀이3.5 by mayank12559>
    
    //굳이 min pq넣을때 까꾸로 넣고 그걸 또 arraylist에 넣고 뒤에서 뽑을 필요가 없어서 조금 최적화함.
    
    public int minDifference(int[] nums) {
        PriorityQueue<Integer> max = new PriorityQueue();
        PriorityQueue<Integer> min = new PriorityQueue();
        for(int i: nums){
            min.add(i);
            if(max.size() < 4){
                max.add(i);
            }else{
                max.add(Math.max(max.poll(), i));
            }
        }
        
        int ans = Integer.MAX_VALUE;
        for(int i=0, cnt = 0; i < min.size() && cnt < 4;i++, cnt++){
            ans = Math.min(ans, max.poll() - min.poll());
        }
        return ans;
    }
}
