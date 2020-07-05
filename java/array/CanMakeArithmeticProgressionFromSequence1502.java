package julyChallenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CanMakeArithmeticProgressionFromSequence1502 {

	//<문제풀이1>
	
	//Runtime: 1 ms, faster than 100.00% of Java online submissions for Can Make Arithmetic Progression From Sequence.
	//Memory Usage: 38.8 MB, less than 100.00% of Java online submissions for Can Make Arithmetic Progression From Sequence.
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int gap = arr[1]-arr[0];
        for(int i = 2; i < arr.length; i++){
            if(arr[i]-arr[i-1] != gap) return false;
        }
        return true;
    }
    
    //<문제풀이2 by rock>
    
    //위처럼 푸는게 잴 쉽고 성능도 좋지만, 다른방식으로 푼 사람이 있어서 가져와봄.
    
    //arr에서 젤큰애랑 작은애를 구하고, 둘의 차이를 구해. 모든 element를 set에 넣고.
    
    //max-min한걸 n-1로 나누면, arr애들의 평균 차이가 됌.
    
    //예를들어, [1,3,5]에서 max=5, min=1, n(arr.length) = 3인데,
    
    //diff(max-min) = 4이고,
    
    //1->3할때 한번, 3->5갈때 한번 총 2번(n-1)로 나누면 2, 이게 element간 평균 차이임.
    
    //diff % (n-1) != 0, return false는, 모든 element가 일정한 간격으로 떨어져 있지 않다면, 나머지가 있을수밖에 없어서 그렇고,
    
    //set을 굳이 쓴 이유는, 만약 다 다른애들인데 두놈만 duplicate이면 어짜피 diff%(n-1) != 0, return false에서 걸러지고
    
    //set.contains(minimum number)을 활용하기 위함.
    
    //Runtime: 2 ms, faster than 100.00% of Java online submissions for Can Make Arithmetic Progression From Sequence.
    //Memory Usage: 39.5 MB, less than 100.00% of Java online submissions for Can Make Arithmetic Progression From Sequence.
    
    public boolean canMakeArithmeticProgression(int[] arr) {
        Set<Integer> s = new HashSet<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int n = arr.length;
        for(int e : arr){
            s.add(e);
            if(e < min) min = e;
            if(e > max) max = e;
        }
        
        int diff = max-min;
        
        if (diff % (n - 1) != 0) {
            return false;
        }
        
        diff /= (n - 1);
        
        while (--n > 0) {
            if (!s.contains(min)) {
                return false;
            }
            min += diff;
        }
        return true;
    }
}
