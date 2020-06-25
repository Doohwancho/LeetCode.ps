package juneChallenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindTheDuplicateNumber {

	//<Trial01>
	
	//duplicate number가 이쁘게 딱 2개만 나오는 줄 알았자너~
	
	//There is only one duplicate number in the array, but it could be repeated more than once.
	
	//문제를 제대로 안읽었네~
	
    //1,2,3,4,5
    
    //5/2 * (1+5) + 5/2+1
    
    //1,2,3,4
    
    //4/2 * (1+4)
    
    public int findDuplicate(int[] nums) {
        int total = 0;
        int len = nums.length;
        int sum = len % 2 == 1 ? len/2 * (1+len) + len/2 +1 : len/2 * (1+len);
            
        for(int n : nums){
            total += n;
        }
        return len - (sum - total);
    }
    
    //<Trial02>
    
    //set이 중복숫자가 안들어간다는 점을 이용한 방법인데 
    
    //You must use only constant, O(1) extra space.
    
    //조건에 안맞음
    
    //Runtime: 2 ms
    //Memory Usage: 40.5 MB
    public int findDuplicate(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for(int n : nums){
            if(!s.add(n)){
                return n;
            }
        }
        return -1;
    }
    
    //<Trial03>
    
    //binary search
    
    //걍 함 해봄 ㅎㅎ
    
    //Runtime: 4 ms
    //Memory Usage: 42.2 MB
    
    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        int l = 0, r = nums.length;
        while(l < r){
            int m = (l+r)/2;
            if(nums[m] < m+1){
                r = m;
            } else {
                l = m+1;
            }
        }
        return l;
    }
    
    //<문제풀이1 by HelloWorld123456>
    
    //이거 binary search 걍 어거지로 갔다 쓴거같은데 성능도 구리고
    
    //오름차순 정렬도 안되있는거에다 막갔다 써버리면;; 흠;;
    
    //Runtime: 4 ms
    //Memory Usage: 42.4 MB
    
    public int findDuplicate(int[] nums) {
        if (nums.length == 0 || nums == null) return 0;
        int low = 1, high = nums.length - 1, mid;
        while (low < high) {
            mid = low + (high - low) / 2;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid)
                    count++;
            }
            if (count > mid)
                high = mid;
            else
                low = mid + 1;
        }
        return low;
    }
    
    //<문제풀이2 by LHearen>
    
    //토끼와 거북이 솔루션인데
    
    //이게 여태껏 솔루션중에 그나마 좀 낫다
    
    //근데 singly linked list가 아닌데 이걸 쓴다는게 좀 그렇기도 하고
    
    //솔직히 그냥 two pointer로 해도 똑같잖아?
    
    //아 다시생각해보니까 그냥 two pointer와는 다르네
    
    //토끼가 nums.length를 벗어나버리면 다시 리셋해준다는 점에서 
    
    //이게 젤 낫다 야
    
    //Runtime: 0 ms
    //Memory Usage: 39.2 MB
    
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int slow = nums[0], fast = nums[0];
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
                fast = nums[0];
                while (fast != slow) {
                    slow = nums[slow];
                    fast = nums[fast];
                }
                return fast;
            }
        }
    }
    
    //<문제풀이3 by qgambit2> 
    
    //성능 개나줘버린 힙스터한 솔루션
    
    //nums를 iterate할때 나오는 n들을 2의 n승으로 만들어 버리면,
    
    //bit이 하나만 켜지니까, xor해서 bit이 꺼질때 
    
    //Runtime: 194 ms
    //Memory Usage: 115.3 MB
    
    public int findDuplicate(int[] nums) {
        java.math.BigInteger test = new java.math.BigInteger("0");
        for (int n:nums){
            java.math.BigInteger twoPowN = new java.math.BigInteger("2").pow(n);
            java.math.BigInteger test1 = test.xor(twoPowN);
            if (test1.compareTo(test) < 0){
                return n;
            }
            test = test1;
        }
        return 0;
    }

}
