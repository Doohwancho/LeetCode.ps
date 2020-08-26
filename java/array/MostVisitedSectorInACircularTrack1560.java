package array;

import java.util.ArrayList;
import java.util.List;

public class MostVisitedSectorInACircularTrack1560 {

	//<문제풀이1>
	
	//brute-force
	
	//어씨 방금전 문제보다가 이거보니까 난이도차 무엇?
	
	//Runtime: 2 ms, faster than 82.62% of Java online submissions for Most Visited Sector in a Circular Track.
	//Memory Usage: 39.5 MB, less than 92.88% of Java online submissions for Most Visited Sector in a Circular Track.
	
    public List<Integer> mostVisited(int n, int[] rounds) {
        int[] a = new int[n];
        int max = 0;
        int adder = 1;
        
        for(int i = 1; i < rounds.length; i++){
            if(rounds[i-1] <= rounds[i]){
                for(int j = rounds[i-1]-adder; j < rounds[i]-adder; j++){ //adder처리 하는 이유는, 맨 첨엔 0,1,2까지 가면, 그 담부턴 도착한 지점+1부터 가야되서.
                    a[j]++;
                    adder = 0;
                }
            } else {
                for(int x = rounds[i-1]-adder; x < n; x++){
                    a[x]++;
                    adder = 0;
                }
                for(int y = 0; y < rounds[i]; y++){
                    a[y]++;
                    adder = 0;
                }
            }
        }
        for(int ele : a){
            max = Math.max(max, ele); //가장 빈도수 많은애를 뽑아
        }
        
        List<Integer> rst = new ArrayList<>();
        
        for(int p = 0; p < n; p++){
            if(a[p] == max){ //가장 빈도수 많은애부터 순서대로 넣어
                rst.add(p+1);
            }
        }
        return rst;
    }
    
    
    //<문제풀이2 by lee215>
    
    //brain teaser.
    
    //if start <= end,
    
	//s --------------------- n
	//1 ---------------e
	
    //start~end까지 뽑으면 된다.
    
    
    //if ends < start,
    
	//                s ----- n 
	//1 --------------------- n //가운데에 몇개가 있던 무시됨
	//1 --------------------- n //가운데애 몇개가 있던 무시된
	//1 ----- e
    
    //start부터 끝까지, 1부터 end까지 뽑으면 된다.
    
    //Runtime: 1 ms, faster than 97.57% of Java online submissions for Most Visited Sector in a Circular Track.
    //Memory Usage: 39.6 MB, less than 89.47% of Java online submissions for Most Visited Sector in a Circular Track.
    
    public List<Integer> mostVisited(int n, int[] A) {
        List<Integer> res = new ArrayList<>();
        for (int i = A[0]; i <= A[A.length - 1]; ++i) //if ends < start, 이 for문은 무시됨.
            res.add(i);
        if (res.size() > 0) return res; //if ends < start, 이 if문은 무시됨.
        for (int i = 1; i <= A[A.length - 1]; ++i) //if start <= ends, 이 for문은 무시됨. 
            res.add(i);
        for (int i = A[0]; i <= n; ++i) //if starts <= ends, 이 for문은 무시됨
            res.add(i);
        return res;
    }
}
