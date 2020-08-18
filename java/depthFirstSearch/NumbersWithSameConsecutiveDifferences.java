package augustChallenge;

import java.util.ArrayList;
import java.util.List;

public class NumbersWithSameConsecutiveDifferences {
	
	
	//<문제풀이1>
	
	//dfs
	
	//stringbuilder -> accumulate num * 10 할수도있겠네. 그럼 좀 빨라질듯
	
	//Runtime: 3 ms
	//Memory Usage: 39.6 MB
	
	List<Integer> list;
    
    private void helper(StringBuilder sb, int i_, int N, int K){
        if(N == 0){
            list.add(Integer.parseInt(sb.toString()));
            return;
        };
        if(i_ - K >= 0){
            StringBuilder sb1 = new StringBuilder(sb);
            sb1.append(i_-K);
            helper(sb1, i_-K, N-1, K);
        }
        if(K != 0 && i_ + K < 10){
            StringBuilder sb2 = new StringBuilder(sb);
            sb2.append(i_+K);
            helper(sb2, i_+K, N-1, K);
        }
    }
    
    public int[] numsSameConsecDiff(int N, int K) {
        list = new ArrayList<>();
        if(N == 1) list.add(0);
        
        for(int i = 1; i < 10; i++){
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            helper(sb, i, N-1, K);
        }
        int[] rst = new int[list.size()];
        for(int i = 0; i < rst.length; i++){
            rst[i] = list.get(i);
        }
        return rst;
    }
}
