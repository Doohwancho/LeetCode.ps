package augustChallenge;

public class PancakeSorting {

	//<문제풀이1>
	
	//맨 마지막에 올 애를 찾은 다음, 
	
	//걔를 맨 처음으로 보내고
	
	//걔가 4라면 0부터 4가 있어야하는곳까지 뒤집어
	
	//그리고 3으로 옮겨.
	
	//예를들어,
	
    //3,2,4,1 라면,
    
    //첫번째는 일단 4를 찾어서 index0부터 index2까지(4가있는곳) 뒤집어. 그럼 4가 맨 앞으로 오게 됨.
    
	//4,2,3,1
	
	//4가 맨 앞으로 왔으면, 원래 4가 있어야 하는 곳이랑 reverse. index0~index3까지. 그럼
	
    //1,3,2,4
	
	//이렇게 됨. 4는 제자리 찾아옴. 이제 다음 타겟은 4보다 하나 작은 숫자인 3.
    
    //3인 인덱스가 1이니까, index0~index1까지 reverse해. 그러면, 3이 맨 앞으로 옴.
    //3,1,2,4
	
	//이렇게. 그럼 맨 앞에있는 3이랑 원래 3이 있어야 할 index2랑 reverse
	
    //2,1,3,4
	
	//그럼 여태까지 3이랑 4가 제 자리를 찾게됨. 이런식.
	
	//다음에 2를 찾고 똑같이 하면 됨.
	
	//Runtime: 1 ms
	//Memory Usage: 39.5 MB
	
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> rst = new ArrayList<>();
        int t = A.length;
        int len = A.length;
        
        while(len > 0){
            for(int i = 0; i < len; i++){
                if(A[i] == t){
                    reverse(A, i);
                    reverse(A, len-1);
                    rst.add(i+1);
                    rst.add(len);
                    t--;
                    len--;
                    break;
                }
            }
        }
        return rst;
    }
    
    private void reverse(int[] A, int end){
        for(int i = 0, j = end; i < j; i++, j--){
            int tmp = A[i];
            A[i] = A[j];
            A[j] = tmp;
        }
    }
}
