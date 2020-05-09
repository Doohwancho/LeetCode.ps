package array;

import java.util.ArrayList;
import java.util.List;

public class KidsWithGreatestNumOfCandies1431 {
	
	//<문제풀이1>
	
	//먼저 for문을 돌면서, 가장 많이 가진 애가 몇갠지 파악
	
	//두번째 for문 때는 내가 줄 수 있는 캔디의 최대량(extraCandies)이 가장 많이가진애꺼의 차이만큼 커버 가능한지 보고, 
	
	//3항연산자 이용해서 맞으면 true, 아니면 false 넣음
	
//	Runtime: 0 ms
//	Memory Usage: 39.2 MB
	
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> rst = new ArrayList<>();
        int max = -1;
        for(int c : candies) max = Math.max(max, c);
        for(int i = 0; i < candies.length; i++){
            rst.add(max-candies[i] <= extraCandies ? true : false);
        }
        return rst;
    }
}
