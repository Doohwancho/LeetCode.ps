/*
	Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.
	
	Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.
	
	So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.
	
	Note:
	
	Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
	Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
	As long as a house is in the heaters' warm radius range, it can be warmed.
	All the heaters follow your radius standard and the warm radius will the same.
	 
	
	Example 1:
	
	Input: [1,2,3],[2]
	Output: 1
	Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
	 
	
	Example 2:
	
	Input: [1,2,3,4],[1,4]
	Output: 1
	Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.



	<문제>
	
	input이 다음과 같이 주어진다.

	[1,2,3,4],[1,4]
	
	난로가 방 전체를 따듯하게 만들기 위해 필요한 반경을 반환하라.
	
	첫번째 어레이는 방을 나타내고, 두번째 어레이는 방 안 몇번째에 난로를 놓을지 나타낸다.
	
	위의 경우, 첫번째와 네번째에 난로를 넣으면, [난로, 빈공간, 빈공간, 난로] 이런식으로 되는데,
	
	이때 방 안 전체를 따듯하게 만드려면, 난로에 반경이 1이어야 한다.
	
	 만약 [난로, 빈공간, 빈공간, 빈공간, 난로, 빈공간] 이었다면, 난로의 반경이 2여야 모든 방이 데워질 것이다.
	 
 */

package BinarySearch;

import java.util.Arrays;

public class Heaters475 {
	
	/*
	//<Trial01>
	
	//1. 0번째 인덱스부터 첫번째 난로까지 빈공간의 수 
	//2. 난로들 사이의 빈공간의 수들 중 가장 큰 수 
	//3. 마지막 난로부터 마지막 인덱스까지 빈공간의 수
	//4. 1,2,3중 최대숫자 반환.
	
	//input이 이렇게 나와서 에러.
	
	//[1,2,3,5,15]
	//[2,30]
	
	//답안이 13이라는데, 답안이 성립하려면, houses의 마지막 15에서 2를 뺀 13라는 말인데...
	
	//그럼 30을 왜준거지
	
	//그리고 15가 집의 index를 뜻한다면 답이 3이되어야 하는데.. 흠.
	
	public static int findRadius(int[] houses, int[] heaters) {

		int first = heaters[0]-1;
		int last = houses[houses.length-1] - heaters[heaters.length-1];
		int betweens = 0;
		
		for(int i = 1; i < heaters.length; i++) {
			int diff = heaters[i]-heaters[i-1];
			betweens = Math.max(betweens, diff/2);
		}
		return Math.max(Math.max(first, last), betweens);
    }
	 
	 */

	//<문제풀이 by StefanPochmann>
	
	//비트전환연산자 ~는 아래 링크 참조
	
	//http://blog.kurien.co.kr/492
	
	//Arrays.binarySearch(a, b)가 a에서 b를 binary-search 방법으로 찾는데,
	
	//만약 a에 b가 없으면, 마이너스가 붙고, 원래 있어야 했던 자리의 인덱스+1을 반환해준다.
	
	//예를들어, a = [1,3,5], b = 2, binarySearch(a,b)를 하면, 
	
	//2가 a에 없으니 마이너스가 붙고, 2는 1과 3사이에 있어야 하니 index1이되어 -(index+1)인, -2을 반환한다.
	
	//어렵다..ㅜㅜ
	
	//Runtime: 11 ms, faster than 64.94% of Java online submissions for Heaters.
	//Memory Usage: 38.6 MB, less than 100.00% of Java online submissions for Heaters.
	
	public static int findRadius(int[] houses, int[] heaters) {
		Arrays.sort(heaters);
	    int result = 0;
	    
	    for (int house : houses) {
	        int index = Arrays.binarySearch(heaters, house);
	        
	        if (index < 0) {
	            index = ~index; //부호를 바꾸어 +면 +1, -면 -1을 해줌.
	            
	            int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
	            int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;
	            
	            result = Math.max(result, Math.min(dist1, dist2));
	        }
	    }
	    
	    return result;
	}

	
	public static void main(String[] args) {
		int[] houses = {1,2,3,5,15};
		int[] heaters = {2,30};
		System.out.println(findRadius(houses, heaters));
	}
}
