/*
	The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the numbers in the set got duplicated to another number in the set, which results in repetition of one number and loss of another number.
	
	Given an array nums representing the data status of this set after the error. Your task is to firstly find the number occurs twice and then find the number that is missing. Return them in the form of an array.
	
	Example 1:
	Input: nums = [1,2,2,4]
	Output: [2,3]
	
	
	
	
	
	
	<문제>
	
	1부터 n까지의 숫자가 1씩 차례대로 증가하는 어레이 nums가 주어진다. 그런데 전산오류로, 원소들중 하나의 숫자가 옆숫자랑 겹쳐서, 원래 [1,2,3,4]여야 하는데, [1,2,2,4]로 바뀌었다고 하자.
	
	이 때, 어레이에 첫번째 값은 중복된 숫자로, 두번째 값은 원래 있었어야 했던 숫자로 채워서 반환하라. 위의 예시의 경우, 2가 중복되고, 원래 2 자리에 3이 있었어야 하니까 [2,3]을 반환하면 된다.
	
	주의할 점은, 문제에서 주어지는 nums가 친절하게 [1,2,3,4,4,6]같이 나오진 않고, [3,2,2]라던가, [2,2]라던가, 순서가 하나씩 작아지는 형태로 나올 수 도있다.
 */

package HashTable;

import java.util.HashSet;
import java.util.Set;

public class SetMismatch645 {
	
	
	
	//<문제풀이1>
	
	//답을 담을 2개 크기가진 int[]인 rst를 만들고, 먼저 nums를 loop돌면서 set에 안들어가는 숫자(중복숫자)를 rst에 첫번째 칸에 넣음.
	
	//두번째 loop은 1부터 nums의 크기까지 돌면서 안나온 숫자를 rst의 두번째칸에 넣음.
	
	//메모리는 효율적으로 썼는데 좀 느린편.
	
	//Runtime: 13 ms, faster than 36.29% of Java online submissions for Set Mismatch.
	//Memory Usage: 40.9 MB, less than 100.00% of Java online submissions for Set Mismatch.
	
	/*
	public static int[] findErrorNums(int[] nums) {
		int[] rst = new int[2];
		Set<Integer> set = new HashSet<>();
        for(int num : nums) if(!set.add(num)) rst[0] = num;
        for(int i = 1; i < nums.length+1; i++){
            if(!set.contains(i)){
                rst[1] = i; 
                break;
            }
        }
        return rst;
    }
    */
	
	//<문제풀이2>
	
	//불필요한 set빼고 int[]만 쓴 방법.
	
	//11ms 속도 증가와 1.9mb 메모리 아낌.
	
	//nums이 1부터 n까지 숫자가 차례대로 나오니깐, nums 어레이의 길이만큼 container를 만들면 크기가 딱 맞음.
	
	//container에 loop돌면서 해당 숫자 위치의 빈도수를 ++해서 카운팅 하다가, > 0(중복) 이면 rst변수 첫번째에 중복된 숫자를 넣어줌.
	
	//또다시 container를 loop돌면서 이번엔 원래 나왔어야 하는데 안나온 비어있는 숫자를 rst변수 두번째에 넣어줌.
	
	//아쉽게도 이거보다 빠른 솔루션 못찾음.
	
	//Runtime: 2 ms, faster than 96.84% of Java online submissions for Set Mismatch.
	//Memory Usage: 39 MB, less than 100.00% of Java online submissions for Set Mismatch.
	
	public static int[] findErrorNums(int[] nums) {
		int[] rst = new int[2];
		int[] container = new int[nums.length];
		for(int i : nums) {
			if(container[i-1] > 0) rst[0] = i;
			container[i-1]++;
		}
		for(int j = 0; j < container.length; j++){
            if(container[j] == 0){
                rst[1] = j+1;
                break;
            } 
        }
		return rst;
	}
	
	public static void main(String[] args) {
		int[] nums = {2,3,2};
		System.out.println(findErrorNums(nums));
	}
}
