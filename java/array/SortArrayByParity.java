package augustChallenge;

public class SortArrayByParity {
	
	//<문제풀이1>
	
	//two pointer
	
	//Runtime: 2 ms
	//Memory Usage: 46.1 MB
	
    public int[] sortArrayByParity(int[] A) {
        for(int i = 0, j = A.length-1; i < j; ){
            while(i < j && A[i]%2==0) i++;
            while(i < j && A[j]%2==1) j--;
            swap(A,i++,j--);
        }
        return A;
    }
    
    private void swap(int[] A, int i, int j){
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
    
    
    //<문제풀이2 by lee215>
    
    //two pointer
    
    //인데 얜 내가한것처럼 양 끝단에서 가운데로 오는게 아니라, 왼쪽에서부터 둘다 출발하는데
    
    //i가 홀수, j가 짝수.
    
    //짝수 걸리면 홀수애랑 바꿔줌
    
    //홀수가 나오기 전 계속 짝수만 나온다고 생각해 보면,
    
    //계속 지 스스로랑 swap하고 i랑 j둘다 한칸 땡겨짐
    
    //그러다 홀수나오면 i는 홀수에 있고 j는 다음 짝수 나올떄까지 넘어감
    
    //그러다 j가 다음 짝수에 걸리면 i랑 바꾸는 식
    
    //전부 짝수밖에 없으면 비효율적. N번도는데 계속 지 스스로랑 바꾸고 앉아있으니까.
    
    //Runtime: 1 ms
    //Memory Usage: 40.6 MB
    
    public int[] sortArrayByParity(int[] A) {
        for (int i = 0, j = 0; j < A.length; j++)
            if (A[j] % 2 == 0) {
                int tmp = A[i];
                A[i++] = A[j];
                A[j] = tmp;;
            }
        return A;
    }
}
