package array;

public class CorporateFlightBookings1109 {
	
	/*
	//<문제풀이1>
	
	//brute-force
	
	//Runtime: 1315 ms, faster than 8.32% of online submissions for Corporate Flight Bookings.
	//Memory Usage: 56.1 MB, less than 100.00% of online submissions for Corporate Flight Bookings.
	
	public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] rst = new int[n];
        
        for(int[] each : bookings){
            for(int i = each[0]-1; i < each[1]; i++){
                rst[i] += each[2];
            }
        }
        
        return rst;
    }
	*/
	
	//<문제풀이2 by lee215>
	
	//와 쓰바 성능차이 오지네
	
	//Runtime: 2 ms, faster than 100.00% of online submissions for Corporate Flight Bookings.
	//Memory Usage: 55.8 MB, less than 100.00% of online submissions for Corporate Flight Bookings.
	
	public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] res = new int[n];
        for (int[] b : bookings) {
            res[b[0] - 1] += b[2]; //여기부터 오른쪽으로 계속 누적해서 더할거다
            if (b[1] < n) res[b[1]] -= b[2]; //b[1]-1까지. 근데 b[1]가 되면 이미 -b[2]이기 때문에, +b[2]하면 +-0가 된다. 존나 위트있네
        }
        for (int i = 1; i < n; ++i)
            res[i] += res[i - 1];
        return res;
    }
}
