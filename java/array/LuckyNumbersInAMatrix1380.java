package array;

import java.util.ArrayList;
import java.util.List;

public class LuckyNumbersInAMatrix1380 {
	
	//<문제풀이1>
	
	//Runtime: 1 ms, faster than 98.81% of Java online submissions for Lucky Numbers in a Matrix.
	//Memory Usage: 42 MB, less than 100.00% of Java online submissions for Lucky Numbers in a Matrix.
	public List<Integer> luckyNumbers (int[][] matrix) {
        List<Integer> rst = new ArrayList<>();
        int[] container = new int[matrix[0].length]; //먼저 row에서 가장 작은수를 뽑아서 그 수의 column index와 같은 곳에 넣음
        
        for(int i = 0; i < matrix.length; i++){
            int minNum = Integer.MAX_VALUE;
            int j_ = 0;
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] < minNum){
                    minNum = matrix[i][j];
                    j_ = j;
                }
            }
            container[j_] = Math.max(container[j_], minNum); //int[]의 초기값은 0. 만약 이전에 넣었던적이 있다면, 같은 column에 두개 값이 있다는건데, 그 땐 둘 중 더 큰수를 넣어줌. 어짜피 밑에 for문에서 더 큰수를 뽑을것이기 때문.
        }
        
        for(int p = 0; p < matrix[0].length; p++){
            if(container[p] == 0) continue; //int[]의 초기값은 0이고, 문제에서 matrix[m][n]은 1부터 10^5까지의 수라고 했으니까, 값이 0이라는건 암것도 안들어갔다는 말이기 때문에 볼필요없어서 패스.
            int maxNum = container[p];
            boolean flag = true;
            for(int q = 0; q < matrix.length; q++){
                if(matrix[q][p] > maxNum){
                    flag = false;
                    break;
                }
            }
            if(flag) rst.add(maxNum); //최댓값이 container에서 뽑은 maxNum이면 고대로 넣고, 해당 column의 다른 숫자가 최댓값이 였다면, flag가 false니까 rst에 안넣고 넘어감.
        }
        return rst;
    }
}
