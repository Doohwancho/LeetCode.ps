package thirtyDayChallenge;

public class CountingElements {
	
	/*
	//<문제풀이1>

	//35 / 35 test cases passed.
	//Status: Accepted
	//Runtime: 1 ms
	//Memory Usage: 39.5 MB
    public int countElements(int[] arr) {
        int[] container = new int[1001];
        for(int i : arr){
            container[i]++; //다 때려박어
        }
        
        int rst = 0;
        for(int i = 1; i < container.length; i++){
            if(container[i] > 0 && container[i-1] > 0){ //둘이 쭈룩 나오면
                while(container[i-1] > 0){ //뒤엣꺼만 까.  greedy. 
                    rst++;
                    container[i-1]--;
                }
            }
        }
        return rst;
    }
    */
    
    //<문제풀이2 by Losty>
    
    //이방법이 더 깔끔하네

	//35 / 35 test cases passed.
	//Status: Accepted
	//Runtime: 0 ms
	//Memory Usage: 37.1 MB
    public static int countElements(int[] arr) {
        int[] a = new int[1002];
        for (int i : arr) a[i] = 1;
        int count = 0;
        for (int i : arr) count += a[i + 1]; //근데 [1,1,2]는? a = {0,1,1,....}일텐데, 그럼 답 2나오잖아? 근데 문제에서 If there're duplicates in arr, count them seperately. 라고 했으면 2가 아니라 1이 정답이어야 하는거 아냐? 
        return count;
    }
    
    public static void main(String[] args) {
		int[] arr = {1,1,2};
		System.out.println(countElements(arr));
	}
}
