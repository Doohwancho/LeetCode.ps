package array;

import java.util.HashSet;
import java.util.Set;

public class LengthOfLongestFibonacciSubsequence873 {
	
	/*
	//<Trial01 - Time Limit Exceeded>
	
	//아 쫌 봐주라 인간적으로다가
	
	public int lenLongestFibSubseq(int[] A) {
        List<List<Integer>> container;
        int rst = 0;
        for(int i = 0; i < A.length; i++){
            container = new ArrayList<>(); //매 loop마다 첫번째 숫자를 달리 시작함
            List<Integer> tmp = new ArrayList<>();
            tmp.add(A[i]);
            container.add(tmp); //[[A[i]]] 를 시작으로 피보나치 수열을 붙여 나갈 것
            
            for(int j = i+1; j < A.length; j++){
                int idx = container.size()-1; //container안 리스트 확인하는데, 기존에 있던애들만 봐야해서 idx설정해줌. for문으로 걍 돌리면 concurrent exception 뜸. loop도중 container에 .add(list)했기 때문
                while(idx >= 0){
                    List<Integer> temp = container.get(idx--);
                    if(temp.size() < 2){ //피보나치수열의 법칙(A[i-1]+A[i] = A[i+1])은 세번째 인덱스 부터 시작하기 때문에, [[A[i]]라 하나뿐이면, 두개까지는 채워준다.
                        temp.add(A[j]);
                        if(container.get(container.size()-1).size() > 1){ //맨 마지막꺼가 [[A[i]]]라면, 굳이 새로운 A[i]를 더해줄 필요가 없다. 시간/메모리 절약
                            List<Integer> tmp2 = new ArrayList<>();
                            tmp2.add(A[i]);
                            container.add(tmp2); 
                        }
                    }
                    else if(A[j]-temp.get(temp.size()-1) == temp.get(temp.size()-2)){ //이게 피보나치 방법 적용한 것. 지금 숫자 A[j]가 해당 리스트의 마지막 숫자를 뺀게, 그 리스트 이전이전의 숫자와 같으면, 피보나치방법이 성립되므로, A[j]를 더해준다.
                        temp.add(A[j]);
                    }
                }
            }
            
            for(List<Integer> temp : container){
                rst = Math.max(rst, temp.size() > 2 ? temp.size() : 0); //만약에 [1,7] ,[1,4]이런것만 있으면, 이건 피보나치 수열이 아님. A[i-1]+A[i] = A[i+1] 이공식이 안쓰이잖아. 그러면 0처리함.
            }
        }
        return rst;
    }
    */
	
	//<문제풀이1 by lee215>
	
	//똑똑허네
	
	//Runtime: 73 ms, faster than 59.16% of Java online submissions for Length of Longest Fibonacci Subsequence.
	//Memory Usage: 41.7 MB, less than 14.29% of Java online submissions for Length of Longest Fibonacci Subsequence.
	public int lenLongestFibSubseq(int[] A) {
        Set<Integer> s = new HashSet<Integer>();
        for (int x : A) s.add(x);
        int res = 2;
        for (int i = 0; i < A.length; ++i)
            for (int j = i + 1; j < A.length; ++j) {
                int a = A[i], b = A[j], l = 2;
                while (s.contains(a + b)) {
                    b = a + b;
                    a = b - a;
                    l++;
                }
                res = Math.max(res, l);
            }
        return res > 2 ? res : 0;
    }
}
