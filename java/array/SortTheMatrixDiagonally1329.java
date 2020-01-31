package array;

public class SortTheMatrixDiagonally1329 {
	
	//<문제풀이1>
	
	//Runtime: 2 ms, faster than 98.22% of Java online submissions for Sort the Matrix Diagonally.
	//Memory Usage: 41.3 MB, less than 100.00% of Java online submissions for Sort the Matrix Diagonally.
	private static void diagnal(int[][] mat, int i, int j){
        int[] container = new int[101]; //mat에 등장하는 숫자가 0부터 100까지니까 101개 사이즈의 리스트 생성
        int i_ = i;
        int j_ = j;
        while(i_ < mat.length && j_ < mat[0].length){ //리스트에 숫자의 갯수를 담음
            container[mat[i_++][j_++]]++;
        }
        while(i < mat.length && j < mat[0].length){ //mat을 돌면서 리스트에 저장되있는 숫자 차례대로 넣음
            for(int k = 0; k < 101; k++){
                if(container[k] > 0){
                    mat[i++][j++] = k;
                    container[k]--;
                    break;
                }
            }
        }
    }
    
    public static int[][] diagonalSort(int[][] mat) {
    	//for문에 if-else문은 우하향 대각선 그을 때, 처음 시작하는 곳을 가리킴.
        for(int i = mat.length-1; i >= 0; i--){
            if(i == 0){
            	//맨 왼쪽 세로줄 다 돌았으면 가장 윗줄 가로
                for(int j = 0; j < mat[0].length; j++){
                    diagnal(mat, i, j);  //우하향 대각선의 시작점부터 출발하여 오름차순 정렬시키는 diagnal()함수 사용
                }
            } else {
            	//처음엔 맨 왼쪽 세로줄만
                diagnal(mat, i, 0);
            }
        }
        return mat;
    }
    
    public static void main(String[] args) {
		int[][] mat = {{3,3,1,1},{2,2,1,2},{1,1,1,2}};
		diagonalSort(mat);
		for(int[] row : mat) {
			for(int ele : row) {
				System.out.print(ele+" ");
			}
			System.out.println();
		}
	}
}
