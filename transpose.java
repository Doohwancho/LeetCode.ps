package LeetCode09;


class transpose {
    public int[][] transpose1(int[][] A) {
    
        int numRows = A.length;
        int numCols = A[0].length;
        
        if(numRows != numCols){
            int[][] transMatrix = new int[numCols][numRows];
            int i = 0;
            for(int[] row : A){
                for(int j=0;j<numCols;j++){
                    transMatrix[j][i] = row[j];
                }
                i++;
             }
            return transMatrix;
        } else {
             for(int i=0;i<numRows;i++){
                for(int j=1+i;j<numCols;j++){
                    int temp = A[i][j];
                    A[i][j] = A[j][i];
                    A[j][i] = temp;
                }
            }
            return A;
        }
    }
}