package julyChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
	
	//<Trial01>
    
    public List<List<Integer>> threeSum(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> rst = new ArrayList<>();
        
        for(int l = 0; l < num.length-2; l++){
            if(l > 0 && num[l] == num[l-1]) continue;
            
            for(int r = num.length-1, m = 0, l_ = l, r_ = r; l+1 < r; r--){
                while(l_ < r_){
                    m = (l_+r_)/2;
                    if(num[l]+num[m]+num[r] <= 0){
                        l_ = m+1;
                    } else {
                        r_ = m;
                    }
                }
                if(num[l_]+num[m]+num[r_] == 0){
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(num[l_]);
                    tmp.add(num[m]);
                    tmp.add(num[r_]);
                    rst.add(tmp);
                }
            }
        }
        return rst;
    }
    
    
    //<문제풀이1 by yavinci>
    
    //Runtime: 28 ms
    //Memory Usage: 56.3 MB
    
    public List<List<Integer>> threeSum(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> rst = new ArrayList<>();
        
        for(int i = 0; i < num.length-2; i++){
            if(num[i] > 0) break; //i가 양수면, i보다 큰 lo+hi의 합이 i와 같아질 수 없다.
            if(i == 0 || num[i] != num[i-1]){ //skip duplicates
                int lo = i+1, hi = num.length-1;
                while(lo < hi){
                    if(num[lo]+num[hi] == -num[i]){
                        rst.add(Arrays.asList(num[i], num[lo], num[hi]));
                        while(lo < hi && num[lo] == num[lo+1]) lo++; //skip duplicates
                        while(lo < hi && num[hi] == num[hi-1]) hi--; //skip duplicates
                        lo++;
                        hi--;
                    } else if(num[lo]+num[hi] < -num[i]){
                        lo++;
                    } else {
                        hi--;
                    }
                }
            }
        }
        return rst;
    }
}
