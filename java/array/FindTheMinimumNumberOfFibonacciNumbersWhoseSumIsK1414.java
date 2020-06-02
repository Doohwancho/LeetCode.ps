package array;

import java.util.ArrayList;
import java.util.List;

public class FindTheMinimumNumberOfFibonacciNumbersWhoseSumIsK1414 {

	//<Trial01>
	
	//dp로 풀려그랬는데 잘안되네 아이고;
	
    private static int helper(List<Integer> l, int k){
    	if(l.size() == 0 || k <= 0) {
    		return Integer.MAX_VALUE;
    	}
        if(l.contains(k)){
            return 1;
        } 
        return 1 + Math.min(helper(l.subList(0, l.size()-1), k), helper(l, k-l.get(l.size()-1)));
    }
    
    public static int findMinFibonacciNumbers(int k) {
        List<Integer> l = new ArrayList<>();
        
        for(int i = 1, j = 0; i <= k; ){
            l.add(i);
            i = i+j;
            j = i-j;
        }
        
        return helper(l, k);
    }
    
    //<문제풀이1 by nihalanim9>
    
    //greedy
    
    //걍 피보나치 수열 k전까지 나열한 다음에, 가장 큰수부터 히나씩 까내려가면 된데.
    
    //fib[i]+fib[i+1] == fib[i+2]니까,
    
    //fib[i]+fib[i+1] 두개 먹는것보다 똑같은 숫자 fib[i+2] 하나 먹는게, 선호되제.
    
    //문제에서 "최소" 경우의 수를 내라고 했으니까.
    
    //k가 10(2+8)같이 여러 숫자가 더해진 거라면,
    
    //k는 최소 한칸 이상 띄어진 피보나치 숫자들의 합이겠지? i+(i+1)이 나란히 못나온다그랬으니까.
    
    //근데 여기서 분위기가 좀 싸 한게, 문제에 이렇게 나와있잖아?
    
    //whether a Fibonacci number could be used multiple times.
    
    //큰수부터 하나씩 까내려가다 보면, 중복되서 들어가는 경우는 어쩔껀데? 라고 물을 수 있잖아?
    
    //근데 같은 피보나치수의 중복([i]*2)은, [i-2]+[i+1]로 치환될 수 있어.
    
    //유도식은 아래랑 같아.
    
    //we have:
  	//fibo[i-2] + fibo[i-1] = fibo[i]
  	//fibo[i-1] + fibo[i] = fibo[i+1] -> we get fibo[i] = fibo[i+1] - fibo[i-1]
  	
  	//thus:
  	//fibo[i] + fibo[i] = (fibo[i-2] + fibo[i-1]) + (fibo[i+1] - fibo[i-1]) = fibo[i-2] + fibo[i+1];
  	
  	//so:
  	//fibo[i] * 2 = fibo[i - 2] + fibo[i + 1]
    
	//이제 중복걱정도 안해도 되니까, 
    
    //greedy로 젤 큰것부터 하나씩 까면서 더해주면 돼.
	
    public int findMinFibonacciNumbers(int k) {
        int rst = 0;
        List<Integer> l = new ArrayList<>();
        
        for(int i = 1, j = 0; i <= k; ){
            l.add(i);
            i = i+j;
            j = i-j;
        }
        
        for(int i = l.size() - 1; i >= 0; i--) {
            if(k == 0) return rst;
            if(l.get(i) <= k) {
                k -= l.get(i);
                rst++;
            } else continue;
        }
        return -1;
    }
    
}
