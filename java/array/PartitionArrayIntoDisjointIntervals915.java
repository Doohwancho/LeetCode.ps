package array;

public class PartitionArrayIntoDisjointIntervals915 {
	
	/*
	//<Trial01>
	
	//아래에서 막힘.. 
	
	//Input : [1,1,1,0,6,12]
			
	//stdout	
	//i: 0 largest: 1 smallest: 1 partition: 0
	//i: 1 largest: 1 smallest: 1 partition: 0
	//i: 2 largest: 1 smallest: 1 partition: 0
	//i: 3 largest: 1 smallest: 0 partition: 4
	//i: 4 largest: 6 smallest: 0 partition: 4
	//i: 5 largest: 12 smallest: 0 partition: 5
	
	//Output : 5
	//Expected : 4
	
	public int partitionDisjoint(int[] A) {
        if(A.length == 2) return 1;
        int partition = 0;
        for(int i = 0, largest = A[0], smallest = A[0]; i < A.length; i++){
            if(A[i] > largest){ 
                partition = i;
            }
            if(A[i] < smallest){
                partition = i+1;
            }
            largest = Math.max(largest, A[i]);
            smallest = Math.min(smallest, A[i]);
        }
        return partition;
    }
    */
	
	//<문제풀이1 by srvstvnhl>
	
	//[3,2,1,5,4] 이 있다고 했을 때,
	
	//이걸 탑을 쌓으면
 
	//   0 
	//   00
	//0  00
	//00 00
	//00000
	
	//이거자너. 로그찍으면
	
	//arr[i]: 2 cmax: 3 omax: 3 idx: 1
	//arr[i]: 1 cmax: 3 omax: 3 idx: 2
	//arr[i]: 5 cmax: 3 omax: 5 idx: 2
	//arr[i]: 4 cmax: 3 omax: 5 idx: 2
	
	//답은 3이고.
	
	//보면 cmax를 기준선 삼아 그 밑으로 내려갈때마다 idx를 업데이트 해줌.
	
	//그리고 arr[i]이 5일때 omax가 바뀜.
	
	//그러나 그렇다고 arr[i]가 4일때 cmax기준선 그대로 감.
	
	//그러다가 만약에 아래처럼 cmax기준선 밑으로 숫자가 하나 튀어나오면
	   
	//      0
	//   0  0
	//   00 0
	//0  00 0
	//00 0000
	//0000000
	
	//arr[i]: 2 cmax: 3 omax: 3 idx: 1
	//arr[i]: 1 cmax: 3 omax: 3 idx: 2
	//arr[i]: 5 cmax: 3 omax: 5 idx: 2
	//arr[i]: 4 cmax: 3 omax: 5 idx: 2
	//arr[i]: 2 cmax: 5 omax: 5 idx: 5
	//arr[i]: 8 cmax: 5 omax: 8 idx: 5
	
	//answer : 6
	
	//cmax기준이 아까 쟁여놨던 omax로 바뀌고 idx도 그에따라 바뀜.
	
	//새롭게 바뀐 cmax는 여태껏 iterate한 숫자들 중에 가장 큰 숫자인 omax로 바뀌는 것임.
	
	//Runtime: 4 ms, faster than 14.02% of Java online submissions for Partition Array into Disjoint Intervals.
	//Memory Usage: 40.8 MB, less than 33.33% of Java online submissions for Partition Array into Disjoint Intervals.
	
	public static int partitionDisjoint(int[] arr) {
        if(arr.length==0){
            return 0;
        }
        int cmax = arr[0];
        int omax = arr[0];
        int idx = 0;
        for(int i=1;i<arr.length;i++){
            if(arr[i] < cmax){ 
                idx = i;
                cmax = omax;
            }else{
                omax = Math.max(omax,arr[i]); 
            }
        }
        return idx + 1;
    }
	
	public static void main(String[] args) {
		int[] test = {3,2,1,5,4,2,6};
		System.out.println(partitionDisjoint(test));
	}
}
