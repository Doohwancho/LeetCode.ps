package juneChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LargestDivisibleSubset {
	
	//<Trial01>
	
	//헤깔리니까 한번 그려보자
	
	//[1,2,3,4,8,9]
	
	//  0,1,2,3,4,5,8,9
	//0 0
	//1   0 1 1 1 1 1 1
	//2   0 0 2 2 2 2 2
	//3   0 1 0 3 3 3 3
	//4   0 0 1 0 4 4 4 
	//5   0 1 2 1 0 5 5
	//8   0 0 2 0 3 0 8
	//9   0 1 0 1 4 1 0
	
	//[1,2,4,8] -- taken
	//[1,3,9]   -- not taken
	
	//세로로 0의 갯수가 가장 많은애를 담으면 되겠네?
	
	//근데 1은 반칙이니까 1빼고 0이 젤 많은 세로줄의 인덱스 들을 더하면 되지 않을까?
	
	//응 안돼~
	
	//Input: [1,2,3,4,6,24]
	//Output: [2,4,6,24,1]
	//Expected: [1,2,4,24]
	
	//0 6 4 3 2 2 1 
	//0 0 1 1 1 1 1 
	//0 0 0 2 2 2 2 
	//0 0 1 0 3 3 3 
	//0 0 0 1 0 4 4 
	//0 0 0 0 2 0 6 
	//0 0 0 0 0 0 0 
	
	//6이 꼽사리 끼네? 6이랑 4랑 호환 안되는걸 못거름 
	
	//recursive로 하면 분명 인풋 미친놈이 한개쯤 꼭 있어서 time limit뜰거같은디
	
    public List<Integer> largestDivisibleSubset(int[] nums) {
    	//유효성 검사
        int n = nums.length;
        if(n == 0) return new ArrayList<>();
        
        //1의 여부 확인
        boolean one = false;
        for(int num : nums){
            if(num == 1){
                one = true;
                break;
            }
        }
        
        //2차원 배열로 두 수가 %해서 0되는지 확인 후, 0이면 해당 줄의 가장 윗부분에 count+1
        int[][] a = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                a[i][j] = nums[i-1]%nums[j-1];
                if(a[i][j] == 0) a[0][j]++;
            }
        }
            
        //1을 제외한 세로줄 중에 가장 %로 많이 나눠지는 줄 확인
        int largestRow = 0;
        for(int p = 1, j = a[0][0]; p <= n; p++){
            if(a[0][p] > j && nums[p-1] != 1){
                largestRow = p;
                j = a[0][p];
            }
        }
        
        //삽입
        List<Integer> rst = new ArrayList<>(n);
        
        for(int q = 0, k = 1; q < a[0][largestRow]; q++){
            while(k <= n && a[k][largestRow] > 0){
                k++;
            }
            rst.add(nums[k++-1]);
        }
        
        //1은 모든 수를 %할 수 있으므로, 1이 있다면 1도 삽입
        if(one) rst.add(1);
        
        return rst;
    }
    
    
    
    //<문제풀이1 by zhugejunwei>
    
    
    //input : [1,2,3,4,6,24]
    
    //i: 1 j: 0 max: 2 mi: 1
	//121111
	//000000
    
	//i: 2 j: 0 max: 2 mi: 1
	//122111
	//000000
    
	//i: 3 j: 1 max: 3 mi: 3
	//122311
	//000100
    
	//i: 4 j: 2 max: 3 mi: 3
	//122331
	//000120
    
	//i: 5 j: 4 max: 4 mi: 5
	//122334
	//000124      -- nums[5]->nums[4]->nums[2]->nums[1]순서로 담네
    
    //[1,3,6,24]
    
    //Runtime: 15 ms
    //Memory Usage: 39.3 MB

    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        List<Integer> rst = new LinkedList<>();
        if(n == 0) return rst;
        Arrays.sort(nums);      //가장 작은숫자부터 그 다음으로 작은숫자씩 하나하나 비교할 것이기 때문에 오름차순 정렬.
        int[] maxi = new int[n]; //총 몇번 %로 나눌 수 있는지 누적으로 확인
        int[] prev = new int[n]; //i%j가 성립한다면, i자리에 j를 넣어줌으로 써, backtracking가능. 
        int max = 1; //maxi의 최대값. 1로 시작하는 이유는 n==1인 경우를 처리하기 위함.
        int mi = 0;
        
        Arrays.fill(maxi, 1);
        
        for(int i = 0; i < n; i++){
            for(int j = i-1; j >= 0; j--){
                if(nums[i]%nums[j] == 0 && maxi[j] >= maxi[i]){ //&& maxi[j] >= maxi[i]를 하는 이유는, 이걸 빼면, [1,2,4]에서 i=2, j=1일때 i자리에 j가 이쁘게 들어가지만, 1을 만나면 1이 2의자리를 덮어쓰게됨. 근데 우리가 원하는건 max갱신시 index를 연쇄적으로 적어주길 원하니까, 중간에 끊기지 말라고 적어줌.
                    maxi[i] = maxi[j]+1;
                    prev[i] = j;
                    
                    if(maxi[i] > max){
                        max = maxi[i];
                        mi = i;    
                    }
                }
            }
        }
        while(max > 0){
            rst.add(0, nums[mi]);
            mi = prev[mi];
            max--;
        }
        return rst;
    }
}
