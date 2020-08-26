package array;

public class ThreeConsecutiveOdds {
	
    public boolean threeConsecutiveOdds(int[] arr) {
        int cnt = 0;
        for(int a : arr){
            if(a%2 == 1){
                cnt++;
                if(cnt == 3) return true;
            } else{
                cnt = 0;
            }
        }
        return false;
    }
}
