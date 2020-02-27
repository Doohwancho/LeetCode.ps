package array;

public class MinimumDominoRotationsForEqualRow1007 {
	
	//<Trial01>
	
	//개추하게 풀었는데도 못품 제기랄
	
	/*
	public int minDominoRotations(int[] A, int[] B) {
        if(A.length == 1) return 0;
        
        int targetA = A[0] == A[1] || A[0] == B[1] ? A[0] : -1;
        int targetB = B[0] == B[1] || B[0] == A[1] ? B[0] : -1;
        
        if(targetA+targetB == -2){
            return -1;
        } else {
            int[] ab = new int[2];
            
            for(int i = 1; i < A.length; i++){
               if(A[i] == targetA){
                    continue;
               }else if(B[i] == targetA){
                   ab[0]++;
               }else{
                   ab[0] -= 20000;
                   break;
               }
            }
            
            for(int i = 1; i < A.length; i++){
               if(B[i] == targetB){
                    continue;
               }else if(A[i] == targetB){
                   ab[1]++;
               }else{
                   ab[1] -= 20000;
                   break;
               }
            }
            if(ab[0] > 0 || ab[1] > 0){
                int a = Math.min(ab[0], A.length-ab[0]);
                int b = Math.min(ab[1], A.length-ab[1]);
                
                if(a < 0){
                    return b;
                }
                else if(b < 0){
                    return a;
                }
                else {
                    return Math.min(a,b);
                }
            } else if(ab[0] == 0 || ab[1] == 0){
                return 0;
            }
            
            
            return -1;
        }
    }
    */
	
	//<문제풀이1 by lee215>
	
	//아 존나깔끔하게 푸네
	
	//Runtime: 5 ms, faster than 63.13% of Java online submissions for Minimum Domino Rotations For Equal Row.
	//Memory Usage: 45.7 MB, less than 56.25% of Java online submissions for Minimum Domino Rotations For Equal Row.
	
    public int minDominoRotations(int[] A, int[] B) {
        int[] countA = new int[7], countB = new int[7], same = new int[7];
        int n = A.length;
        for (int i = 0; i < n; ++i) {
            countA[A[i]]++;
            countB[B[i]]++;
            if (A[i] == B[i])
                same[A[i]]++;
        }
        for (int i  = 1; i < 7; ++i)
            if (countA[i] + countB[i] - same[i] == n)    //Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]의 경우, 맨 끝에 2한번 겹치는것만 빼주면 A.length의 길이가 나옴
                return n - Math.max(countA[i], countB[i]); //전체길이 - A줄과 B줄에서 더 긴것은 최소 옮기는 수. 위의 예시에서 2를 2번 옮기나 6-2해서 4번옮기냐 임
        return -1;
    }
}
