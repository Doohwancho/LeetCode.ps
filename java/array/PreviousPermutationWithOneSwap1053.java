package array;

public class PreviousPermutationWithOneSwap1053 {
	
	
	//<Trial01>
	
	//맞는거 같은데 왜?
	
	//찾아보니 test case가 잘못됬다는 말이 많음...새로만들어진 질문이라그런가
	
	//Test case 50:
	//input :
	//{1,1,9,4,9,7,7,5,3,10,4,10,2,3,4,9,4,6,5,10,7,2,9,4,10,7,10,5,10,9,5,3,6,9,3,1,2,9,1,4,5,1,3,2,10,7,9,6,9,6,9,9,1,8,7,8,9,5,9,8,6,1,10,9}
	//expected output:
	//{1,1,9,4,9,7,7,5,3,10,4,10,2,3,4,9,4,6,5,10,7,2,9,4,10,7,10,5,10,9,5,3,6,9,3,1,2,9,1,4,5,1,3,2,10,7,9,6,9,6,9,9,1,8,7,8,9,5,9,8,6,1,9,10}
	//
	//This is wrong because the expected output is bigger than input. Notice the last two numbers {10, 9} and {9, 10}.
	//{9, 10} > {10, 9}
	//
	//The right expected output should be:
	//{1,1,9,4,9,7,7,5,3,10,4,10,2,3,4,9,4,6,5,10,7,2,9,4,10,7,10,5,10,9,5,3,6,9,3,1,2,9,1,4,5,1,3,2,10,7,9,6,9,6,9,9,1,8,7,8,9,5,9,8,6,10,1,9}
	
	//logic : 맨 끝자리에서 가장 가까운 숫자와 한번 뒤집어야 그다음으로 작은 숫자가 나옴. 단, 뒤집는 가장 가까운 숫자가 맨 끝자리 index:iterate보다 더 큰 숫자여야 함.

	private static int[] swap(int[] A, int i, int iterate) {
		int tmp = A[i];
		A[i] = A[iterate];
		A[iterate] = tmp;
		return A;
	}

	public static int[] prevPermOpt1(int[] A) {
		int iterate = A.length - 1;
		while (iterate > 0) {
			for (int i = iterate - 1; i >= 0; i--) {
				if (A[i] > A[iterate]) {
					return swap(A, i, iterate);
				} else if (A[i] == A[iterate]) {
					break;
				}
			}
			iterate--;
		}
		return A;
	}
	
	
	/*
	//<문제풀이1 by kylewzk>
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Previous Permutation With One Swap.
	//Memory Usage: 42.6 MB, less than 100.00% of Java online submissions for Previous Permutation With One Swap.
	
	public static int[] prevPermOpt1(int[] A) {
        for(int i = A.length - 1; i >= 0; i--) { //맨 끝자리부터 돌면서
            if(i == A.length - 1 || A[i] <= A[i+1]) continue; //바로 옆칸이 그 윗자리보다 더 큰 숫자의 인덱스를 찾음. 여기까지 동일.
            else {
                int j = A.length - 1;
                while(A[j] >= A[i] || A[j] == A[j-1]) j--; //A[j] >= A[i]는 뒤에숫자가 더 크면, 바꾼 후 숫자가 더 커지면 안되서 그렇고, A[j] == A[j-1]은 나란이 있는 숫자가 같다면, 앞에있는걸 바꿔주는게 뒤에있는거와 바꾸는것보다 더 큰 수이므로. 
                int t = A[i];
                A[i] = A[j];
                A[j] = t;
                break;
            }
        }
        return A;
    }
	*/
	
	
	public static void main(String[] args) {
		int[] test = new int[] {3,3,2,1,2,3};
		for(int ele : prevPermOpt1(test)) {
			System.out.print(ele+" ");
		}
	}

}
