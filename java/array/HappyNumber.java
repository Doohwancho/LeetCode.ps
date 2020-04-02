package thirtyDayChallenge;

public class HappyNumber {

	//<문제풀이1>
	
	//input : 19
	
	//Your stdout
	//n: 1 n_: 81
	//n: 0 n_: 82
	//n: 8 n_: 4
	//n: 0 n_: 68
	//n: 6 n_: 64
	//n: 0 n_: 100
	//n: 10 n_: 0
	//n: 1 n_: 0
	//n: 0 n_: 1
	
	//401 / 401 test cases passed.
	//Status: Accepted
	//Runtime: 0 ms
	//Memory Usage: 36.4 MB
	
    public boolean isHappy(int n) {
        int n_ = 0;
        int cnt = 0; //cnt가 없으면 무한으로 돌때가 있자너
        
        while(cnt < 25 && n > 0) {
			n_ += (n%10) * (n%10);
			n /= 10;
 			if(n == 0) {
                if(n_ == 1) return true;
 				n = n_;
 				n_ = 0;
 			}
            cnt++;
		}
        return false; //적당히 돌았는데도 안되있으면 false박아브러
    }
}
