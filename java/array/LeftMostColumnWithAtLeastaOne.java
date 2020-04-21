package thirtyDayChallenge;

import java.util.List;

public class LeftMostColumnWithAtLeastaOne {

//	interface BinaryMatrix {
//		public int get(int x, int y) {}
//		public List<Integer> dimensions(){}
//	};
	
	//<문제풀이1>
	
	//이거거등
	
	//binary search
	
	//일단 중간 column을 스캔 해.
	
	//스캔했는데 1이 없으면 오른쪽을 스캔해. left most를 스캔한 구간+1로 잡아서.
	
	//스캔했는데 1이 있으면 왼쪽에 더 있을 수 있으니까 right most를 스캔한 구간 -1로 잡아서 다시 스캔해봐.

	//37 / 37 test cases passed.
	//Status: Accepted
	//Runtime: 0 ms
	//Memory Usage: 40.1 MB
	
	public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
		List<Integer> xy = binaryMatrix.dimensions();
		int n = xy.get(0);
		int m = xy.get(1);

		int l = 0;
		int r = n;
		int mid;
		int rst = -1;
        
		while(l<=r) {
			mid = (l+r)/2;
			int i = 0;
            if(l == r && rst == -1) return -1;
			while(i < n) {
				if(binaryMatrix.get(i, mid) == 1){
                    rst = mid;
                    break;
                }
				i++;
			}
			if(rst == mid) {
				r = mid-1;
			} else {
				l = mid+1;
			}
		}
		
		return rst;
	}
}
