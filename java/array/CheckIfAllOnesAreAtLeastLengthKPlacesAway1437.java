package array;

public class CheckIfAllOnesAreAtLeastLengthKPlacesAway1437 {
	
	//<문제풀이1>
	
	//1이 나왔을때부터 카운트 해주기위해 d(distance)를 null로 줘놓고,
	
	//1이 나오면 0으로 초기화.
	
	//그 후엔 0이 나올때마다 distance+1씩 해주다가
	
	//else문에서 1이 나왔을 때, 여태껏 떨어진 distance와 k값을 비교하여
	
	//허용범위인 k보다 더 좁은 distance라면 return false
	
	//loop다 돌아도 아무 이상 없으면 return true
	
	//Runtime: 1 ms, faster than 100.00% of Java online submissions for Check If All 1's Are at Least Length K Places Away.
	//Memory Usage: 49.2 MB, less than 100.00% of Java online submissions for Check If All 1's Are at Least Length K Places Away.
    public boolean kLengthApart(int[] nums, int k) {
        Integer d = null;
        for(int n : nums){
            if(d == null && n == 1){
                d = 0;
            } else if(d != null){
                if(n == 0){
                    d++;
                } else {
                    if(d < k) return false;
                    d = 0;
                }
            }
        }
        return true;
    }
}
