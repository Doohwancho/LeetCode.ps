package juneChallenge;

import java.util.HashSet;
import java.util.Set;

public class SingleNumberII {

	//<문제풀이1>
	
	//2연타 하드나오다가 오늘껀 그래도 할만한거 나왔네 어휴 힐링된다
	
	//[2,2,3,2] 이걸 보면,
	
	//다 더하면 9네?(== total:9)
	
	//하나씩만 나오는것만 다 더하면 5네?(== uniqTotal : 5)
	
	//total(9) - uniqTotal(5) 하면 4네? 그리고 이건 3개씩 나오는 숫자들을 2번씩 더한 값이네?
	
	//3개씩 나오는 숫자들을 2번씩 더한 값을 반으로 나누면, 모든 3개씩 나오는 숫자들의 합이 되겠네?
	
	//그걸 하나씩만 나오는것의 총 합(==uniqTotal)에서 빼면 우리가 찾는 한번만 나오는 숫자를 찾을 수 있겠네?
	
	//Runtime: 2 ms
	//Memory Usage: 39.1 MB
    public int singleNumber(int[] nums) {
        Set<Integer> s = new HashSet<>();
        int total = 0;
        int uniqTotal = 0;
        
        for(int n : nums){
            if(s.add(n)){
                uniqTotal += n;
            };
            total += n;
        }
        return uniqTotal - (total-uniqTotal)/2;
    }
    
    //<문제풀이2 by HelloWorld123456>
    
    //각 bit자릿수 보면서 sum에 더함. 잉건 전체 수를 걍 total += n한것과 다를바 없음
    
    //다만, bit한자리씩 더할때마다 %3을 해서, 그 자리에 unique number가 끼어있는지 확인하는 것.
    
    //만약 7번째자리 bit이 3개 똑같은 숫자가 나왔다면, sum에 다 더한 후, 3번 나왔으니까 %3하면 0되서 result에 변화가 없음
    
    //근데 만약 8번째 자리 bit이 3개 똑같은 숫자가 나오고 한개 unique number의 bit도 있으면, %3을 했을 때, 1개가 남아서 result에 불이 켜짐.
    
    //마찬가지로 9번째 자리에 3개 똑같은 숫자가 한개도 안나왔는데 unique number의 bit이 있다고 치면, 1%3은 1이기 때문에, result에 반영됨.
    
    //sum%3이 핵심. 
    
    //Runtime: 1 ms
    //Memory Usage: 39.3 MB
    
	public static int singleNumber(int[] nums) {
		int len = nums.length, result = 0;
		for (int i = 0; i < 32; i++) {
			int sum = 0;
			for (int j = 0; j < len; j++) {
				sum += (nums[j] >> i) & 1;
			}
			result |= (sum % 3) << i;
		}
		return result;
	}
}
