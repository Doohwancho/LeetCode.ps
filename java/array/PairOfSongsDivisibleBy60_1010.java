/*
In a list of songs, the i-th song has a duration of time[i] seconds. 

Return the number of pairs of songs for which their total duration in seconds is divisible by 60.  Formally, we want the number of indices i < j with (time[i] + time[j]) % 60 == 0.

 

Example 1:

Input: [30,20,150,100,40]
Output: 3
Explanation: Three pairs have a total duration divisible by 60:
(time[0] = 30, time[2] = 150): total duration 180
(time[1] = 20, time[3] = 100): total duration 120
(time[1] = 20, time[4] = 40): total duration 60
Example 2:

Input: [60,60,60]
Output: 3
Explanation: All three pairs have a total duration of 120, which is divisible by 60.



<문제>

리스트 안에 두 element를 합한 값이 60으로 나눴을 때, 몇번 나머지가 없이 딱 떨어지는지 반환하라.

 */


package Array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import java.util.Iterator;

public class PairOfSongsDivisibleBy60_1010 {
	/*
	//<문제풀이 - Trial01_Time Limit Exceeded>
	
	//2중 for문으로 모든 combination의 조합을 돌면서 연산하는 방법
	 * 
	public static int numPairsDivisibleBy60(int[] time) 
	{
		for(int i = 0; i < time.length; i++)
		{
			time[i] %= 60;
		}
		
		int rst = 0;
		
	    for(int i = 0; i < time.length-1; i++)
	    {
	    	for(int j = i+1 ; j < time.length; j++ )
	    	{
	    		if((time[i]+time[j])%60 == 0)
	    		{
	    			rst++;
	    		}
	    	}
	    }
	    
	    return rst;
	}

	*/
	
	/*
	//<문제풀이 - by SanD91>
	
	//각 element를 60으로 나눈 것의 나머지와, 다른 element의 60으로 나눈값의 합이 60으로 나누어 떨어진다면 rst++
	
	//for문으로 돌면서, 60으로 나눈 값의 나머지를 map에 넣으면서 이 값과 합해서 60이 되는 숫자가 map에 있는지 확인.
	
	//Runtime: 27 ms, faster than 7.65% of Java online submissions for Pairs of Songs With Total Durations Divisible by 60.
	//Memory Usage: 40.8 MB, less than 97.55% of Java online submissions for Pairs of Songs With Total Durations Divisible by 60.
	
	
	public static int numPairsDivisibleBy60(int[] time) 
	{
		Arrays.sort(time);
        Map<Integer, Integer> map = new HashMap<>();
        int key = 0, total = 0;
        for (int t : time) {
            key = 60 - (t % 60);
            if (map.containsKey(key)) {
                total += map.get(key);
            }
            if (t % 60 == 0) {
                map.put(60, map.getOrDefault(60, 0)+1);
            } else {
                map.put(t % 60, map.getOrDefault(t % 60, 0)+1);
            }
//            System.out.println(t+" "+key+" "+(t%60)+" "+total);
//            Iterator<Integer> keys = map.keySet().iterator();
//            while( keys.hasNext() ){
//                Integer keyy = keys.next();
//                System.out.println( String.format("키 : %s, 값 : %s", keyy, map.get(keyy)) );
//            }
//            System.out.println();
        }
        

        return total;	
	}
	*/
	
	
//	<문제풀이 - by lee215>
//	
//	위와 같은 방식인데 단순히 int[]을 이용한 경우
//	
//	Runtime: 3 ms, faster than 83.65% of Java online submissions for Pairs of Songs With Total Durations Divisible by 60.
//	Memory Usage: 42.7 MB, less than 35.15% of Java online submissions for Pairs of Songs With Total Durations Divisible by 60.
	
    public static int numPairsDivisibleBy60(int[] time) {
        int c[]  = new int[60], res = 0;
        for (int t : time) {
            res += c[(600 - t) % 60];
            c[t % 60] += 1;
        }
        return res;
    }
    
	
	public static void main(String[] args) 
	{
		
		//int[] time = {30,20,150,100,40};
		int[] time = {60,60,60};
		System.out.println(numPairsDivisibleBy60(time));
	}

}
