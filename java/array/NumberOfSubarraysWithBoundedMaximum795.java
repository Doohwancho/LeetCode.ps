package array;

public class NumberOfSubarraysWithBoundedMaximum795 {
	
	/*
	//<Trial01>
	
	//brute-force
	
	//모든 combination 경우의 수를 구한 다음, 해당 리스트의 총 합이 L <= total < R인지 구하는 방식.
	
	//아 근데 contiguous subarray여야 하네. 
	
	//정신차려 이친구야
	
	public int numSubarrayBoundedMax(int[] A, int L, int R) {
        Arrays.sort(A);
        int rst = 0;
        
        List<List<Integer>> container = new ArrayList<>();
        container.add(new ArrayList<>());
        container.add(new ArrayList<>());
        container.get(1).add(A[0]);
        
        int nprev = 1;
        for(int i = 1; i < A.length; i++){
            int size = container.size();
            if(A[i] != A[i-1]){
                nprev = size;
            }
            for(int j = size-nprev; j < size; j++){
                List<Integer> cp = new ArrayList<>(container.get(j));
                cp.add(A[i]);
                container.add(cp);
            }
        }
        
        for(List<Integer> temp : container){
            int total = 0;
            for(int element : temp){
                total += element;
            }
            if(total >= L && total <= R){
                rst++;
            }
        }
        return rst;
    }
    */
	
	/*
	//<Trial02>
	
	//문제를 계속 잘못 이해하네 이친구가
	
	//Maximum element가 L>= <=R이기만 하면 돼. 모든걸 다 더할 필요가 없어 이친구야.
	
	//정신머리좀 챙기고 다니자 친구야
	
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int rst = 0;
        for(int i = 0; i < A.length; i++){
            if(A[i] >= L && A[i] <= R){
                rst++;
            }
            int total = A[i];
            while(total <= R){
                for(int j = i+1; j < A.length; j++){
                    total += A[j];
                    if(total >= L && total <= R){
                        rst++;
                    }
                }
                break;
            }
        }
        return rst;
    }
	
	*/
	
	/*
	//<문제풀이1>
	
	//Runtime: 8 ms, faster than 10.53% of Java online submissions for Number of Subarrays with Bounded Maximum.
	//Memory Usage: 45.5 MB, less than 100.00% of Java online submissions for Number of Subarrays with Bounded Maximum.
	
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int rst = 0;
        for(int i = 0; i < A.length; i++){
            if(A[i] > R){ //해당 숫자가 이미 R을 벗어나면, 그 후 숫자에 상관없이 패스 L<= max_number <=R 에 이미 안맞기 때문
                continue;
            }
            else if(A[i] >= L){ //만약 A[i]이 L<= A[i] <=R 조건에 부합한다면, 
                rst++; //해당 숫자 하나 더해주고
                for(int j = i+1, maxNum = A[i]; j < A.length; j++){ //그 이후 오른쪽 숫자 돌면서 R을 벗어나지 않는 범위내에서 rst++해준다.
                    maxNum = Math.max(maxNum, A[j]);
                    if(maxNum > R){
                        break;
                    } else {
                        rst++;
                    }
                }
            } else { //만약 A[i]가 L보다 작으면, 그 이후의 숫자가 R보다 크지 않으면서 L<= max_number<=R에 부합하는지 찾는다.
                boolean flag = false;
                for(int j = i+1; j < A.length; j++){ 
                    if(A[j] > R){ //R을 벗어나면 볼필요없이 스킵
                        break;
                    } else if(A[j] >= L && A[j] <= R){ //만약 L<= max_number<=R 조건에 부합하는 숫자가 나오면, rst+1을 해준 후,
                        rst++;
                        flag = true;
                    } else if(flag){ //이후의 숫자가 R을 벗어나지만 않는다면 계속 +1을 해준다.
                        rst++;
                    }
                }
            }
        }
        
        return rst;
    }
    */
	
    
    //<문제풀이2 by shawngao>
    
    //문제풀이1과 같은 로직인데 더 압축해 놓은 것.
    
    //else if와 else를 합쳐놓은것
    
	//Runtime: 5 ms, faster than 21.05% of Java online submissions for Number of Subarrays with Bounded Maximum.
	//Memory Usage: 45.4 MB, less than 100.00% of Java online submissions for Number of Subarrays with Bounded Maximum.
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int rst = 0;
        for(int i = 0; i < A.length; i++){
            if(A[i] > R) continue;
            int maximum = Integer.MIN_VALUE;
            for(int j = i; j < A.length; j++){
                if(A[j] > R) break;
                maximum = Math.max(maximum, A[j]);
                if(maximum >= L) rst++; 
            }
        }
        return rst;
    }
}
