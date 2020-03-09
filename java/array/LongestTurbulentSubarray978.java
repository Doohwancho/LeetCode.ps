package array;

public class LongestTurbulentSubarray978 {
	
	//<문제풀이1>
	
	//이거지
	
	//Runtime: 4 ms, faster than 95.62% of Java online submissions for Longest Turbulent Subarray.
	//Memory Usage: 43.9 MB, less than 62.50% of Java online submissions for Longest Turbulent Subarray.
    public static int maxTurbulenceSize(int[] A) {
        boolean flag = true; int t1 = 1, t2 = 1, rst = 1; // t1은 ><><>... t2는 <><><... 1 <= A.length <= 40000니까 최소 하나는 있다는 말이므로 t1,t2,rst는 1로 시작
        for(int i = 1; i < A.length; i++){
            if(A[i] > A[i-1]){
                if(flag){ //t1이 ><><>이 될수도 있고, t2가 ><><>가 될수도 있는게 flag에 따라 변함.
                    t1++;
                    t2 = 1;
                    flag = false;
                } else {
                    t2++;
                    t1 = 1;
                    flag = true;
                }
            } else if(A[i] < A[i-1]){
                if(flag){
                    t2++;
                    t1 = 1;
                    flag = false;
                } else {
                    t1++;
                    t2 = 1;
                    flag = true;
                }
            } else { //이전거와 같으면 둘다 1로 초기화
                t1 = 1;
                t2 = 1;
            }
            rst = Math.max(rst, Math.max(t1, t2));
        }
        return rst;
    }
    
    public static void main(String[] args) {
//		int[] test = new int[] {9,4,2,10,7,8,8,1,9};
    	int[] test = new int[] {4,8,12,16};
		System.out.println(maxTurbulenceSize(test));
	}
}
