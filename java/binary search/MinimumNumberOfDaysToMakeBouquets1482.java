package array;

import java.util.Arrays;

public class MinimumNumberOfDaysToMakeBouquets1482 {
	
	//<Trial01 - time limit exceeded>
	
	//아이고
	
	public int minDays(int[] bloomDay, int m, int k) {
        if(bloomDay.length < m*k) return -1; //애초에 조건에 안맞자너~
        
        int[] copy = bloomDay.clone();
		Arrays.sort(copy); 
        
        int idx = m * k - 1; //가장 작은수 부터 m*k-1번째로 큰 수 이전까지는 애초에 볼 필요가 없자너~ 
        int i = copy[idx];
        
        while(i <= copy[copy.length-1]){
            int bouquet = 0;
            
            for(int j = 0, consq = 0; j < bloomDay.length; j++){ 
                if(bloomDay[j] <= i){
                    consq++;
                } else {
                    consq = 0;
                }
                if(consq == k){
                    consq = 0;
                    bouquet++;
                }
            }
            
            if(bouquet >= m) return i;
        
            i = copy[idx++];
        }
        
        return -1;
    }
	
	
	//<문제풀이1 by lee215>
	
	//오름차순 정렬이 안된 int[]를 binary search시
	
	//value기준으로 찾을 수 있다.
	
	//첨엔 (1+1000000000)/2 일을 기준으로 하니까, 왠만큼 bloomDay[i]가 크지 않으면 bouq가 최대치겠지?
	
	//그럼 right = mid;로 해서 반씩 뚝뚝 잘라서 범위를 좁혀가는거임
	
	//그러다가 드디어 bouq < m이 오면 right bound를 그대로 두고 left bound를 좁혀가는거임
	
	//right bound가 bouq == m이 아니더라도, left bound를 최대한 bouq < m가 아닐 때 까지 땡겨오니까,
	
	//맨 마지막에 left을 뽑았을 때 최소치가 나옴.
	
	//Runtime: 36 ms, faster than 34.89% of Java online submissions for Minimum Number of Days to Make m Bouquets.
	//Memory Usage: 88.8 MB, less than 33.33% of Java online submissions for Minimum Number of Days to Make m Bouquets.
	
    public int minDays(int[] A, int m, int k) {
        int n = A.length, left = 1, right = (int)1e9; //1000000000 (10의 9승. (int)형변환 안하면 1.09E9라고 뜸)
        if (m * k > n) return -1;
        while (left < right) {
            int mid = (left + right) / 2, flow = 0, bouq = 0;
            for (int j = 0; j < n; ++j) {
                if (A[j] > mid) {
                    flow = 0;
                } else if (++flow >= k) {
                    bouq++;
                    flow = 0;
                }
            }
            if (bouq < m) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
	
}
