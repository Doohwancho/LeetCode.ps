package HashTable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MinIndexSumOfTwoList599 {

	/*
	 * //<Trial01>
	 * 
	 * //둘이 공통적으로 좋아하는 음식점 중, 가장 먼저 나타나는 음식점을 반환하라는 것으로 해석함.
	 * 
	 * //그런데 문제의 포인트는, 2명의 사람이 지들만의 음식점 순위를 매겨놓은 거라서, 그 순위를 합산했을 때, 합산점수가 같다면, 점수가
	 * 같은 모든 음식점을 반환해야 함.
	 * 
	 * //예를들어, 아래의 예시의 경우,
	 * 
	 * //list1 = ["Shogun","Tapioca Express","Burger King","KFC"] //list2 =
	 * ["KFC","Burger King","Tapioca Express","Shogun"]
	 * 
	 * //list1은 샷건, 타피오카, 버거킹, 케엪씨를 1,2,3,4등으로 매겼고, //list2는 완전 반대로, 케엪씨, 버거킹, 타피오카,
	 * 샷건을 1,2,3,4등으로 매겼다면,
	 * 
	 * //샷건 = 1+4 = 5 //타피오카 = 2+3 = 5 //버거킹 = 3+2 = 5 //케엪씨 = 4+1 = 5
	 * 
	 * //모두 동점이므로, 모든 음식점을 반환하면 되겠다.
	 * 
	 * public static String[] findRestaurant(String[] list1, String[] list2) { int
	 * idx1 = 0, idx2 = 0; int firstLen = list1.length; int secondLen =
	 * list2.length; Set<String> set = new HashSet<>();
	 * 
	 * while(idx1 < firstLen && idx2 < secondLen) { if(!set.add(list1[idx1])) {
	 * return new String[] {list1[idx1]}; } else idx1++; if(!set.add(list2[idx2])) {
	 * return new String[] {list2[idx2]}; } else idx2++; } return new String[0]; }
	 */

	/*
	 * //<문제풀이1>
	 * 
	 * //음식점의 순서가 몇번째에 등장하는지 구하면서 중복되는 음식점은 따로 duplicates라는 리스트에 담아둠,
	 * 
	 * //중복되는 단어들 중, 가장 index가 앞쪽에 있는(작은)값을 Math.min()를 통해 구해줌
	 * 
	 * //중복되는 음식점들인 duplicates를 loop 돌면서 해당 음식점의 우선순위를 방금 구한 가장 인덱스가 장은 값과 일치하다면,뽑아서
	 * String[]에 담음.
	 * 
	 * //메모리는 잘 썼는데 영 느림.
	 * 
	 * //Runtime: 15 ms, faster than 21.29% of Java online submissions for Minimum
	 * Index Sum of Two Lists. //Memory Usage: 38.6 MB, less than 100.00% of Java
	 * online submissions for Minimum Index Sum of Two Lists.
	 * 
	 * public static String[] findRestaurant(String[] list1, String[] list2) {
	 * List<String> duplicates = new ArrayList<>(); List<String> rst = new
	 * ArrayList<>(); Map<String, Integer> map = new HashMap<>(); int minimum =
	 * Integer.MAX_VALUE;
	 * 
	 * for(int i = 0; i < list1.length; i++) { map.put(list1[i],
	 * map.getOrDefault(list1[i], 0)+i); } for(int i = 0; i < list2.length; i++) {
	 * if(map.containsKey(list2[i])) { duplicates.add(list2[i]); minimum =
	 * Math.min(minimum, map.get(list2[i]) + i); } map.put(list2[i],
	 * map.getOrDefault(list2[i], 0)+i); }
	 * 
	 * for(String s : duplicates) { if(map.get(s) == minimum) rst.add(s); } return
	 * rst.toArray(new String[0]); }
	 */

	
	//<문제풀이 by compton_scatter>
	
	//2배 빠름.
	
	//따로 duplicates같은 어레이 리스트 안만들고, map.containsKey()도 안쓰고 그냥 j != null로 퉁침.
	
	//rst 어레이 리스트도 첨부턴 만들지 않고, 조건에 만족할 때마다 linkedlist에 채워가다가, 조건에 부합하지 않으면 바로 없애고 다시만듬.
	
	//똑똑허이
	
	//Runtime: 8 ms, faster than 81.88% of Java online submissions for Minimum Index Sum of Two Lists.
	//Memory Usage: 38.5 MB, less than 100.00% of Java online submissions for Minimum Index Sum of Two Lists.
	
	public static String[] findRestaurant(String[] list1, String[] list2) {
		Map<String, Integer> map = new HashMap<>();
		List<String> res = new LinkedList<>();
		int minSum = Integer.MAX_VALUE;
		for (int i = 0; i < list1.length; i++) map.put(list1[i], i);
		for (int i = 0; i < list2.length; i++) {
			Integer j = map.get(list2[i]);
			if (j != null && i + j <= minSum) {
				if (i + j < minSum) {
					res.clear();
					minSum = i + j;
				}
				res.add(list2[i]);
			}
		}
		return res.toArray(new String[res.size()]);
	}

	public static void main(String[] args) {
		// String[] list1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
		// String[] list2 = {"KFC", "Shogun", "Burger King"};

		String[] list1 = { "Shogun", "Tapioca Express", "Burger King", "KFC", "b" };
		String[] list2 = { "KFC", "Burger King", "Tapioca Express", "Shogun", "a" };

		System.out.println(findRestaurant(list1, list2));
	}
}
