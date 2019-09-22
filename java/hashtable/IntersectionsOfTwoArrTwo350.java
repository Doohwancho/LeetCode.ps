package HashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntersectionsOfTwoArrTwo350 {
	
	/*
	//<Trial01 - sudo code>
	
	//map에 nums1원소는 더하고, nums2원소는 빼면, 두곳 동시에 나온 숫자만 +- 0니까, 빈도수가 0인것만 빼내면 되겠네?
	
	//라고 생각했는데, 다시 생각해 보면, nums1에 1이 2번나오고, nums2에 1이 한번나오면, 1의 빈도수가 +1이 되기 때문에, 두개 모두 카운트를 못해줌.
	
	public static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        int i = 0, j = 0, arraylist;
        while(i < nums1.length) map.put(nums1[i++], map.getOrDefault(nums1[i], 0)+1);
        while(j < nums2.length) map.put(nums2[j++], map.getOrDefault(nums1[j], 0)-1);	
        for(loop map) if map.value == 0, list.add(key)
        return arraylist.tolist();
    }
    */
	
	/*
	//<문제풀이1>
	
	//먼저 nums1과 nums2를 오름차순 정렬을 하고, for문으로 두개를 동시에 loop돌면서, 중복되는 단어를 추출해 list라는 이름에 arraylist에 담음.
	
	//그 arraylist를 int[]로 형변환 해주고 반환.
	
	//나름 빠른데 오름차순 정렬시 메모리가 많이듬.
	
	//Runtime: 2 ms, faster than 92.08% of Java online submissions for Intersection of Two Arrays II.
	//Memory Usage: 37.8 MB, less than 43.55% of Java online submissions for Intersection of Two Arrays II.
	
	public static int[] intersect(int[] nums1, int[] nums2) {
		List<Integer> list = new ArrayList<>();
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		
		for(int i = 0, j = 0; i < nums1.length && j < nums2.length;) {
			if(nums1[i] == nums2[j]) {
				list.add(nums1[i]);
				i++;
				j++;
			}
			else if(nums1[i] > nums2[j]) j++;
			else i++;
		}
		
		int[] rst = new int[list.size()];
		for(int k = 0; k < rst.length; k++) {
			rst[k] = list.get(k);
		}
		return rst;
	}
	*/
	
	/*
	//<문제풀이2>
	
	//2중 for문돌면서, 중복되는건 리스트에 넣고, 해당값을 다시 카운팅하면 안되니까 다른 임의의 값으로 바꾸는 방법.
	
	//문제풀이1 대비, 메모리는 덜먹으나 8ms느림.
	
	//Runtime: 10 ms, faster than 14.09% of Java online submissions for Intersection of Two Arrays II.
	//Memory Usage: 36.9 MB, less than 83.87% of Java online submissions for Intersection of Two Arrays II.
	
	public static int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
		for(int i = 0; i < nums1.length; i++) {
			for(int j = 0; j < nums2.length; j++) {
				if(nums1[i] != Integer.MAX_VALUE && nums1[i] == nums2[j]) {
					list.add(nums1[i]);
					nums1[i] = Integer.MAX_VALUE;
					nums2[j] = Integer.MAX_VALUE;
				}
			}
		}
		int[] rst = new int[list.size()];
		for(int k = 0; k < rst.length; k++) {
			rst[k] = list.get(k);
		}
		return rst; 
    }
    */
	
	//<문제풀이 by xietao0221>
	
	//문제풀이1과 방식은 같으나, arraylist대신, int[]를 씀.
	
	//Runtime: 1 ms, faster than 100.00% of Java online submissions for Intersection of Two Arrays II.
	//Memory Usage: 37.8 MB, less than 45.16% of Java online submissions for Intersection of Two Arrays II.
	
	public static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int p1 = 0, p2 = 0, count = 0;
        int[] tmp = new int[nums1.length >= nums2.length ? nums1.length : nums2.length], res;

        while(p1 < nums1.length && p2 < nums2.length) {
            if(nums1[p1] == nums2[p2]) {
                tmp[count++] = nums1[p1];
                p1++;
                p2++;    
            } else if(nums1[p1] < nums2[p2]) {
                p1++;
            } else {
                p2++;
            }
        }
        res = new int[count];
        for(int i=0; i<count; i++) res[i] = tmp[i];
        return res;
    }
	
	public static void main(String[] args) {
		int[] nums1 = {1,2,2,1};
		int[] nums2 = {2,2};
		System.out.println(intersect(nums1, nums2));
	}
}
