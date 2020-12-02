package dynamicProgramming;

public class PartitionArrayForMaximumSum1043 {

	//<Trial01>
	
	//24 / 52 test cases passed.
	
	
	//가장 높은 숫자인 애, 를 뽑고, 걔 기준 양옆으로 한칸씩 전염시키려고 했는데,
	
	//[10,9,3,2]
	//2
	
	//이 경우엔, 가장 높은 숫자인 애를 뽑으면 안됨.
	
	//dp로 모든 불필요한 경우의 수 까지 다 봐야 함.
	
	//...보다 양쪽에 차이가 제일 큰애 위주로 돌리면 되지 않을까?
	
    public int maxSumAfterPartitioning(int[] arr, int k) {
        if(arr.length == 1) return arr[0];
        
        int len = arr.length;
        int cnt = 0;
        boolean[] visited = new boolean[len];
        
        while(cnt < len){
            int max = 0;
            int maxIdx = 0;
            for(int i = 0; i < len; i++){
                if(!visited[i] && arr[i] > max){
                    max = arr[i];
                    maxIdx = i;
                }
            }
            
            visited[maxIdx] = true;
            cnt++;
            
            for(int j = maxIdx, l = j-1, r = j+1, contaminate = 0; contaminate < k-1; ){
                if(l == -1){
                    if(visited[r]) break;
                    arr[r] = arr[j];
                    visited[r] = true;
                    r++;
                } else if(r == len){
                    if(visited[l]) break;
                    arr[l] = arr[j];
                    visited[l] = true;
                    l--;
                } else if(visited[l] && visited[r]){
                    break;
                } else {
                    if(arr[l] < arr[r]){
                        arr[l] = arr[j];
                        visited[l] = true;
                        l--;
                    } else if(arr[l] > arr[r]){
                        arr[r] = arr[j];
                        visited[r] = true;
                        r++;
                    }
                }
                cnt++;
                contaminate++;
            }
        }
        
        int rst = 0;
        for(int a : arr){
            rst += a;
        }
        
        return rst;
    }
}
