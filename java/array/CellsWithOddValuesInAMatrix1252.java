/*
	Given n and m which are the dimensions of a matrix initialized by zeros and given an array indices where indices[i] = [ri, ci]. For each pair of [ri, ci] you have to increment all cells in row ri and column ci by 1.
	
	Return the number of cells with odd values in the matrix after applying the increment to all indices.
	
	 
	
	Example 1:
	
	
	Input: n = 2, m = 3, indices = [[0,1],[1,1]]
	Output: 6
	Explanation: Initial matrix = [[0,0,0],[0,0,0]].
	After applying first increment it becomes [[1,2,1],[0,1,0]].
	The final matrix will be [[1,3,1],[1,3,1]] which contains 6 odd numbers.
	Example 2:
	
	
	Input: n = 2, m = 2, indices = [[1,1],[0,0]]
	Output: 0
	Explanation: Final matrix = [[2,2],[2,2]]. There is no odd number in the final matrix.
	
	
	
	
	<문제>
	
	n,m,indices가 다음과 같이 주어진다.
	
	n = 2, m = 3, indices = [[0,1],[1,1]]
	
	indice[0]는 row, indice[1]는 column을 나타낸다.
	
	n x m 의 matrix를 만들고,
	
	indices를 for문돌면서, row,column에 있는 모든 숫자를 +1하고,
	
	for문이 끝났을 때, matrix에 있는 숫자들 중, 홀수들의 갯수를 반환하라.
 */
package Array;

public class CellsWithOddValuesInAMatrix1252 {
	
	/*
	//<문제풀이1>
	
	//brute-force
	
	//Runtime: 4 ms, faster than 7.47% of Java online submissions for Cells with Odd Values in a Matrix.
	//Memory Usage: 39 MB, less than 100.00% of Java online submissions for Cells with Odd Values in a Matrix.
	public int oddCells(int n, int m, int[][] indices) {
        int[][] matrix = new int[n][m];
        int rst = 0;
        
        for(int[] turn : indices){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    if(i == turn[0]){
                        matrix[i][j]++;
                    }
                    if(j == turn[1]){
                        matrix[i][j]++;
                    }
                }
            }
        }
        
        for(int[] row : matrix){
            for(int element:row){
                if(element%2==1){
                    rst++;
                }
            }
        }
        return rst;
    }
    */
	
	//<문제풀이2>
	
	//어씨 짱구 겁나 굴렸네
	
	//결국 반환하라는게 matrix의 홀수갯수면, 무식하게 다 연산할 필요 없고 홀수만 세주면 됨.
	
	//그렇기 위해선 먼저 int[] row랑 column이 몇번 등장하는지 세준 후,
	
	//row부터 그 경우가 홀수번이면 m만큼 rst에 더해줌.
	
	//그리고 availRows에 빈 row가 몇개있는지 세어줌.
	
	//왜냐면 column구할 때, 이미 있는 row는 1더하면 어짜피 제자리니까 빈 row의 수만큼 더해줘야 하기 때문.
	
	//그리고 rst -= (n - availRows); 는 이미 홀수여서 +1이 된 row들을의 값 만큼 다시 0으로 만들어주어야 하기 때문에 적음.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Cells with Odd Values in a Matrix.
	//Memory Usage: 39 MB, less than 100.00% of Java online submissions for Cells with Odd Values in a Matrix.
	
	public int oddCells(int n, int m, int[][] indices) {
        int[] row = new int[n];
        int[] column = new int[m];
        int availRows = 0;
        int rst = 0;
        
        for(int[] turn : indices){
            row[turn[0]]++;
            column[turn[1]]++;
        }
        
        for(int r : row){
            if(r % 2 == 1){
                rst += m; 
            } 
            else {
                availRows++;
            }
        }
        for(int c : column){
            if(c % 2 == 1){
                rst += availRows;
                rst -= (n - availRows);
            }
        }  
        
        return rst;
	}
}

