package array;

public class FindTheDuplicateNumber287 {
	
	/*
	//<Trial01>
	
	//그냥 풀면 이거긴 한데
	
	//2. You must use only constant, O(1) extra space.
	//3. Your runtime complexity should be less than O(n2).
	
	//이 조건에 안맞아서 틀림. bitmask이용하는건가?
	
	//Arrays.sort()한다음에 binary search...는 
	
	//1. You must not modify the array (assume the array is read only).
	
	//이 조건에 어긋나네
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Find the Duplicate Number.
	//Memory Usage: 42.4 MB, less than 5.09% of Java online submissions for Find the Duplicate Number.
	
    public int findDuplicate(int[] nums) {
        int[] container = new int[nums.length];
        
        for(int i : nums){
            if(container[i] > 0){
                return i;
            }
            container[i]++;
        }
        return 0;
    }
    */
	
	/*
	//<Trial02>
	
	//생각해 보니까 1부터 nums.length-1까지의 순서대로 수가 나오는거 + 그중의 하나 중복이니까,
	
	//1부터 nums.length-1까지의 수를 모두 더한 다음 중복 빼면, -중복값이 나오는걸 활용하면 되지 않을까?
	
	//[1,2,3,4,2] 이면, 1부터 n까지의 총합이 (1+2+3+4)해서 10인데, 저 리스트의 총합이 12이니까, 10-12하면 -2, 답은 -(-2)하면 되지 않을까?
	
	//Input : [2,2,2,2,2]
	//Output : 0
	//Expected : 2
	
	//중복값이 이쁘게 2번만 나오면 되는데, 2번보다 더 많이 나올땐 안먹힘 제길.
	
	public int findDuplicate(int[] nums) {
        if(nums.length == 2) return nums[0];
        
        int total = (1+nums.length-1)*((nums.length-1)/2);
        
        for(int i : nums){
            total -= i;
        }
        return -total;
    }
    */
	
	/*
	//<문제풀이1 by qgambit2>
	
	//똑똑허이
	
	//숫자가 1부터 n까지 등장하는데, 이 숫자를 bitmask에 인덱스 삼아, 1이면 0000 0010, 3이면 0000 1000, 이런식으로 처리하는데,
	
	//XOR방식을 써서, 기존거랑 다르면(새로운거면) 누적해나감. 1다음에 3이 나왔아면, 0000 1010이 되겠지.
	
	//java.math.BigInteger을 쓴 이유는, 0000 0000이건 2의 7승까지밖에 기록을 못하니까, 무한대까지 쓸수있는 java.math.BigInteger을 씀.
	
	//그렇게 for문을 돌다가 같은 수가 중복해서 나오면, XOR때문에 해당 자리가 0이되어 수가 줄어들어버림.
	
	//그 때, test1.compareTo(test)를 이용해서, 해당 숫자를 반환함.
	
	//똑똑하구만 기래
	
	//문제 조건엔 안맞는것 같긴 하지만 참신했음.
	
	//Runtime: 120 ms, faster than 5.05% of Java online submissions for Find the Duplicate Number.
	//Memory Usage: 43.3 MB, less than 5.09% of Java online submissions for Find the Duplicate Number.
	
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
    */
    
	/*
    //<문제풀이2 by pratik_patil>
    
    //Floyd's Cycle Detection Algorithm
	
	//The idea is to consider array items as linked list nodes. Any particular index is pointing to the value at that index, like index -> A[index] -> A[A[index]] and so on.
	//For example- given array is A = [1, 2, 3, 4, 5, 6, 3]. In case of duplicate, two indexes will have same value and they will form a cycle just like in the image given below: So we can find the entry point of cycle in the linked-list and that will be our duplicate element.

	//https://leetcode.com/problems/find-the-duplicate-number/discuss/222352/Java-Solutions
	
	//Algorithm:
	
	//1. We maintain two pointers fast and slow
	//2. For each step fast will move to the index that is equal toA[A[fast]] which is two steps at a time and slow will move to the index A[slow] which is one step at a time. Eventually, fast and slow will meet in a cycle and the entry point of that circle will be the duplicate element.
	//3. When fast == slow that means now we are in a cycle.
	//4. Now we need to find entry point so we will start with slow = 0 and visit one step at a time for both fast and slow. When fast == slow that will be entry point.
	//5. Return the entry point.
	
	//Time complexity: O(n)
	//Space Complexity: O(1)
    
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Find the Duplicate Number.
    //Memory Usage: 42.8 MB, less than 5.09% of Java online submissions for Find the Duplicate Number.
    
    public int findDuplicate(int[] a) {
        int s = 0, f = 0;
        
        //First we need to find an entry where the index and nums[index] is not the same, so that we can get into the chain;
        do {
            s = a[s];
            f = a[a[f]];
        } while(s != f);
        
        //1 -> 3 -> 2 -> 4 
        //3 -> 4 -> 4 -> 4
        
        //Once we are in the chain, nums[index] is very similar with ListNode.next operation. So by setting a fast and a slow pointer, we can find where the loop begins just like we did with the linked list.
        s = 0;
        while(s != f) {
            s = a[s];
            f = a[f];
        }

        //0 -> 1 -> 3 -> 2
        //4 -> 2 -> 4 -> 2
        
        return s;
    }
    */
	
	//<문제풀이3 by pratik_patil>
	
	//The idea is when visiting a number i, flip the number at index i - 1 to negative. 
	//If the number at index i - 1 is already negative, then i is the number that occurs more than once.
	
	//Time complexity: O(n)
	//Space complexity: O(1)
	
	//근데 조건 1. You must not modify the array (assume the array is read only)에 해당하지 않네.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Find the Duplicate Number.
	//Memory Usage: 42.9 MB, less than 5.09% of Java online submissions for Find the Duplicate Number.
	
	public int findDuplicate(int[] A)
    {
		//[1,3,4,2,2]
		
		//i = 0, index = 0, A[0] = 0, [0,3,4,2,2]
		//i = 1, index = 2, A[2] = -4, [0, 3, -4, 2, 2]
		//i = 2, index = 2, A[2] < 0, return 2
		//i = 3,
		//i = 4,
		
        for(int i = 0; i < A.length; i++) 
		{
			int index = Math.abs(A[i]) - 1;

			if(A[index] < 0)
				return index + 1;
            else
			    A[index] = -A[index];
		}
        return -1;
    }

}
