package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FriendsOfAppropriateAge825 {
	
	
	//<문제풀이 1>
	
	//드디어 풀었네
	
	//binary search

	//Runtime: 31 ms, faster than 13.20% of Java online submissions for Friends Of Appropriate Ages.
	//Memory Usage: 41.9 MB, less than 10.00% of Java online submissions for Friends Of Appropriate Ages.
    public static int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int rst = 0;
        for(int i = ages.length-1, j = 0; i >= 0; i--){
        	if(i < ages.length-1 && ages[i] == ages[i+1] && ages[i] > 0.5*ages[i]+7) rst+=++j;
            else j = 0;
            int l = 0;
            int r = i-1;
            while(l<=r){ 
                int m = (l+r)/2;
                if(ages[m] <= 0.5*ages[i]+7){
                    l = m+1;
                } else{
                    r = m-1;
                }
            }
            rst += i-l;
        }
        return rst;
    }
}
