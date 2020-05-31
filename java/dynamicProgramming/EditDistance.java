package mayChallenge;

public class EditDistance {
	
	
	
	//<Trial01 - <time limit exceeded> by Rajat Mishra>
	
	//dp
	
	//충분히 잘만든 방법인데 아쉽게도 이 방법이 모든 경우의 수를 보다보니 input이 길어지면 좀 느림
	
	//n-1을 insert라고 표현하고, m-1를 remove라고 표현하고, n-1, m-1를 replace로 표현한게 인상적.
	
	public static int min(int x, int y, int z) { 
        if (x <= y && x <= z) 
            return x; 
        if (y <= x && y <= z) 
            return y; 
        else
            return z; 
    } 
  
    public static int editDist(String str1, String str2, int m, int n) { 
        // If first string is empty, the only option is to 
        // insert all characters of second string into first 
        if (m == 0) 
            return n; 
  
        // If second string is empty, the only option is to 
        // remove all characters of first string 
        if (n == 0) 
            return m; 
  
        // If last characters of two strings are same, nothing 
        // much to do. Ignore last characters and get count for 
        // remaining strings. 
        if (str1.charAt(m - 1) == str2.charAt(n - 1)) 
            return editDist(str1, str2, m - 1, n - 1); 
  
        // If last characters are not same, consider all three 
        // operations on last character of first string, recursively 
        // compute minimum cost for all three operations and take 
        // minimum of three values. 
        return 1 + min(editDist(str1, str2, m, n - 1), // Insert 
                       editDist(str1, str2, m - 1, n), // Remove 
                       editDist(str1, str2, m - 1, n - 1) // Replace 
                       ); 
    } 
    
    public int minDistance(String str1, String str2) {
        return editDist(str1, str2, str1.length(), str2.length());
    }
    
    
    //<문제풀이1 by jiaming2>
    
    //Trial01과 방식은 같음. 다만 2차원 배열로 표현해서 훨씬 빠른덧
    
    //2, Do one operation on word1[i-1][j]. dp[i][j] = dp[i-1][j] + 1

	//3, Do one operation on word2[i][j-1]. dp[i][j] = dp[i][j-1] + 1

	//for 2 and 3, the reason it works is that we know the optimal ways to transfrom word1(0,i) to word2(0,j-1) 
    
    //and word1(0,i-1) to word(0,j) ( Delete ("abc" to "ab") or Insert ("ab" to "abc") ). 
    
    //Now all we need to one more operation.
    
	//Runtime: 5 ms
	//Memory Usage: 39 MB
    
    public int minDistance(String word1, String word2) {
        if (word1.equals(word2)) {
            return 0;
        }
        if (word1.length() == 0 || word2.length() == 0) {
            return Math.abs(word1.length() - word2.length());
        }
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) { //if same, skip.
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
    
}
