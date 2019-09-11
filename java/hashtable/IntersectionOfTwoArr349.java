package HashTable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoArr349 {
    //method1 - 2중 for문으로 해당 원소가 나오는 순간 int[]에 담고 break;
	//method2 - nums1.toString()을 한 후, nums2를 for문으로 돌면서 nums1.toString().contains()에  (String)으로 형변환 한게 있는지 확인
	//method3 - nums1, nums2 중복을 없애고, 두 arr를 합친 후, 두개의 같은 원소가 연속되게 나오면 int[]에 담고 반환
	//method4 - num1, num2를 Arrays.sort()로 오름차순 정렬 한 후, two-pointer방법으로 i==j는 리스트 추가, i!=j는 nums1[i]가 크면 j++ 하는 형식
	//method5 - set에 .contains()를 활용하는 방법. set에 담고 
	
	//이 방법들 중, 시간이 가장 빠른것에서 느릴것 같은 순서는, 5 -> 1 -> 4 -> 2 -> 3
	
	//5 - set에 .contains는 빠름. 
	//1 - 2중 for문 중 해당 원소를 찾기만 하면 break거니 for문을 나름 적게돔. 
	//4 - Arrays.sort()는 생각보다 시간이 오래걸림. 
	//2 - 매loop마다 형변환 + contains도 2중for문과 다를바없음. 
	//1 - 중복 없애는거 시간 오래걸림. 두 arr합치는건 내장함수쓰면 나름 빠른 것 같음.



	/*
	//<문제풀이1 by divingboy89>

	//method5

	//Runtime: 2 ms, faster than 98.23% of Java online submissions for Intersection of Two Arrays.
	//Memory Usage: 36.7 MB, less than 89.19% of Java online submissions for Intersection of Two Arrays.
	
	 public static int[] intersection(int[] nums1, int[] nums2) {
	        Set<Integer> set = new HashSet<>();
	        Set<Integer> intersect = new HashSet<>();
	        for (int i : nums1) {
	            set.add(i);
	        }
	        for (int j : nums2) {
	            if (set.contains(j)) {
	                intersect.add(j);
	            }
	        }
	        int[] result = new int[intersect.size()];
	        int i = 0;
	        for (Integer num : intersect) {
	            result[i++] = num;
	        }
	        return result;
	    }
	    */
	
	//<문제풀이2 by divingboy89>
	
	//method4
	
	//위에 Arrays.sort()방법은 빠르지 않다고 했는데 아니고, 빠르긴 한데 메모리를 많이 잡아먹음.
	//Arrays.sort()를 뜯어보면, 작은 배열은 Insertion sort를 하고(https://doohwancho.tistory.com/322?category=1042623)
	//크기가 7보다 크면 merge sort를 하게 되어있는데(https://doohwancho.tistory.com/325?category=1042623), 
	//mergesort를 할 때, n번 쪼갤 때, 그만큼 변수를 할당할 메모리를 써야 함으로 메로리 소모량이 높음
	
	//Runtime: 2 ms, faster than 98.23% of Java online submissions for Intersection of Two Arrays.
	//Memory Usage: 37.6 MB, less than 58.11% of Java online submissions for Intersection of Two Arrays.
	
	public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                set.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] result = new int[set.size()];
        int k = 0;
        for (Integer num : set) {
            result[k++] = num;
        }
        return result;
    }
	
	
	public static void main(String[] args) {
		int[] nums1 = {4,9,5};
		int[] nums2 = {9,4,9,8,4};
		System.out.println(intersection(nums1, nums2));
}

}
