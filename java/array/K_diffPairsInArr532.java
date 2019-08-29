package Array;

import java.util.HashSet;
import java.util.Set;

public class K_diffPairsInArr532 {
	
	/*
	//<Trial01>
	
	//오름차순으로 정렬해 놓고, for문으로 순서대로 돌면서 k만큼 차이나는 것을 카운팅 해주는 로직.
	
	//nums = [1,1,1,1,1]; k = 0; 일때, 1이나와야 하는데 4가 나옴.
	//중복되는 것이 아예 필요 없는게 아니라 1쌍이상 필요
			
	public static int findPairs(int[] nums, int k) {
		int rst = 0;
		Arrays.sort(nums);
		for(int i = 1; i < nums.length; i++)
		{
			int j = i;
			while(true)
			{
				if(nums[i]-nums[j-1] < k)
				{
					if(j == 1) break;
					j--;
					continue;
				}
				else if(nums[i]-nums[j-1] == k)
				{
					rst++;
					break;
				}
				else
				{
					break;
				}
			}
		}
		return rst;
	}
	*/
	
	/*
	//<Trial02>
	
	//페어를 맺는거니까, nums에서 2개이상 중복된 원소 제거해서 temp라는 리스트 생성 후, 순서대로 k차이만큼 나면 rst++
	
	//nums = [0,0,0];
	//k = 0
	
	//에서 while(true)
	//{
	//	if(temp[i] == 0) break;
	
	//이 부분때문에 막힘.
	
	//근데 더 고치면 고칠수록 코드가 더러워짐..
	
	public static int findPairs(int[] nums, int k) {
        if(nums.length == 0 || nums.length == 1) return 0;
		Arrays.sort(nums);
		int[] temp = new int[nums.length];
		temp[0] = nums[0];
		int idx = 0;
		for(int j = 1; j < nums.length-1; j++)
		{
			if(nums[j] != temp[idx]) 
			{
				temp[++idx] = nums[j];
			}
			else
			{
				if(nums[j+1] != temp[idx])
				{
					temp[++idx] = nums[j];
				}
				else
				{
					continue;
				}
			}
		}
		temp[++idx] = nums[nums.length-1];

		int rst = 0;
		for(int i = 1; i < temp.length; i++)
		{
			int j = i;
			while(true)
			{
				if(temp[i] == 0) break;
				else if(temp[i]-temp[j-1] < k)
				{
					if(j == 1) break;
					j--;
					continue;
				}
				else if(temp[i]-temp[j-1] == k)
				{
					temp[j-1] = -999;
					rst++;
					break;
				}
				else
				{
					break;
				}
			}
		}
		return rst;
    }
    */
	
	/*
	//<문제 풀이 by compton_scatter>
	
	//nums를 for문으로 2번 돌면서, map에 nums[i]를 키, i를 벨류로 넣고,
	
	//nums[i]+k가 map에 있으면 cnt++를 해준다.
	
	//for문으로 2번 도는 이유는, ...3....5...7...이고 k=2일 경우,
	
	//3에 왔을 때, 3+2한 5가 map에 아직 저장되지 않아서이다.
	
	//Runtime: 13 ms, faster than 26.24% of Java online submissions for K-diff Pairs in an Array.
	//Memory Usage: 39.8 MB, less than 89.47% of Java online submissions for K-diff Pairs in an Array.
	
	public static int findPairs(int[] nums, int k) {
        if(k < 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        int cnt = 0, n = nums.length;
        for(int i = 0, j = 0; j<2*n; j++, i = j%n)
        {
        	if(!map.containsKey(nums[i])) map.put(nums[i], i);
        	int upper = map.getOrDefault(nums[i]+k, -1);
        	if(upper != -1 && upper != i)
        	{
        		cnt++;
        		map.put(nums[i]+k, -1);
        	}
        }
        return cnt;
    }
    */
	
	//<문제풀이 by alexander>
	
	//set은 add시 중복이 안들어간다는 점을 이용.
	
	//Runtime: 10 ms, faster than 62.37% of Java online submissions for K-diff Pairs in an Array.
	//Memory Usage: 40 MB, less than 89.47% of Java online submissions for K-diff Pairs in an Array.
	public static int findPairs(int[] nums, int k) {
		if(k < 0) return 0;
		Set<Integer> starters = new HashSet<>();
		Set<Integer> uniqs = new HashSet<>();
		for(int i = 0; i < nums.length; i++)
		{
			if(uniqs.contains(nums[i]-k)) starters.add(nums[i]-k);
			if(uniqs.contains(nums[i]+k)) starters.add(nums[i]);
			uniqs.add(nums[i]);
		}
		return starters.size();
	}

	public static void main(String[] args) {
		int[] nums = {3, 1, 4, 1, 5}; //1 1 3 4 5
		int k = 2;
		
//		int[] nums = {1, 1, 1, 2, 2}; //1 1 3 4 5
//		int k = 1;
		
//		int[] nums = {0, 0, 0}; //1 1 3 4 5
//		int k = 0;
		
		
		
		System.out.println(findPairs(nums, k));
	}
}
