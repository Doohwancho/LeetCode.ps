package array;

public class FlipStringToMonotoneIncreasing926 {
	
	/*
	//<Trial01>
	
	//java.lang.NumberFormatException: For input string: "100000001010000"
	//  at line 68, java.base/java.lang.NumberFormatException.forInputString
	//  at line 658, java.base/java.lang.Integer.parseInt
	//  at line 776, java.base/java.lang.Integer.parseInt
	//  at line 3, Solution.minFlipsMonoIncr
	//  at line 57, __DriverSolution__.__helper__
	//  at line 85, __Driver__.main
	//Last executed input:"100000001010000"
	
	//미친숫자가 나와서 java.math.BigInteger도 쓰고 int말고 long썼는데도
	
	//Input : "11011011010010110011"
	//stdout : convertedNum = -7435733063699441605
	//Output : 0
	//Expected : 8
	
	//long type으로 conversion할때 망함. 개커서.
	//bitmask를 이용해서 1의자리만 쏙 빼는법 없나
	
	public int minFlipsMonoIncr(String S) {
        java.math.BigInteger num = new java.math.BigInteger(S);
        long convertedNum = num.longValue();
        int ones = 0;
		int zeroes = 0;
        
		if(convertedNum%10 == 0) {
			for(long i = 1; i < convertedNum; i*=10) {
				if(convertedNum/i%10 == 1) {
					ones++;
				}else {
					zeroes++;
				}
			}
			return ones > zeroes ? zeroes : ones;
		}
		else {
			boolean flag = false;
			for(long i = 1; i < convertedNum; i*=10) {
				if(flag && convertedNum/i%10 == 1) {
					ones++;
				}else if(convertedNum/i%10 == 0){
					zeroes++;
					flag = true;
				}
			}
			return ones > zeroes ? zeroes : ones;
		}
    }
	*/
	/*
	//<문제풀이1 by kylewzk>
	
	//아 로그 뜯으면 어떻게 동작하는지는 보이는데 백지에서 만들라카면 못만들겠네
	
	//Input : "000110001"
	//stdout
	//i 0 1 -> dp[i][0]은 1을 몇번 flip해야 하는지, dp[i][1]은 0을 몇번 flip해야 하는지 계산
	//dp[i][0]은 1을 몇번 flip해야 하는지 계산함. 따라서 1이 나올때마다 +1을 해줌.
	//dp[i][1]은 0을 몇번 flip해야 하는지 계산함. 근데 Math.min()을 써서 여태껏 1을 총 몇번 flip해야 하는지를 고려해서 계산함. 
	
	//0 0 1
	//1 0 1
	//2 0 1
	//3 1 0
	//4 2 0
	//5 2 1
	//6 2 2
	//7 2 3
	//8 3 2
	
	//Runtime: 9 ms, faster than 17.34% of Java online submissions for Flip String to Monotone Increasing.
	//Memory Usage: 40.8 MB, less than 20.00% of Java online submissions for Flip String to Monotone Increasing.
	
	public static int minFlipsMonoIncr(String S) {
        if(S.length() == 0) return 0;
        
        int[][] dp = new int[S.length()][2];
        dp[0][0] = S.charAt(0) == '1' ? 1 : 0;
        dp[0][1] = S.charAt(0) == '0' ? 1 : 0;
        
        for(int i = 1; i < S.length(); i++) {
            if(S.charAt(i) == '1') {
                dp[i][0] = dp[i-1][0] + 1;
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][1]);
            } else {
                dp[i][0] = dp[i-1][0];
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][1]) + 1;
            }
            System.out.println(i+" "+dp[i][0]+" "+dp[i][1]);
        }
        
        return Math.min(dp[S.length()-1][0], dp[S.length()-1][1]);
    }
	*/
	
	/*
	//<문제풀이 2 by votrubac>
	
	//설명 아래 링크
	
	//https://leetcode.com/problems/flip-string-to-monotone-increasing/discuss/183851/C%2B%2BJava-4-lines-O(n)-or-O(1)-DP
	
	//The basic idea is to travel from left to right. At every step i:
	//(1) flip every 1 to the left of i(including) to 0
	//(2) flip every 0 to the right of i(excluding) to 1
	//Find the minimum along the way.
	
	//Runtime: 3 ms, faster than 87.57% of Java online submissions for Flip String to Monotone Increasing.
	//Memory Usage: 38.9 MB, less than 20.00% of Java online submissions for Flip String to Monotone Increasing.
    public int minFlipsMonoIncr(String S) {
        int f0 = 0, f1 = 0;
        for (int i = 0; i < S.length(); ++i) {
            f0 += S.charAt(i) - '0';
            f1 = Math.min(f0, f1 + 1 - (S.charAt(i) - '0'));
        }
        return f1;
    }
    */
	
    //<문제풀이3 by yuanb10>
    
    //same as 문제풀이2, easier code
    
    //Runtime: 3 ms, faster than 87.57% of Java online submissions for Flip String to Monotone Increasing.
    //Memory Usage: 38.5 MB, less than 20.00% of Java online submissions for Flip String to Monotone Increasing.
    public static int minFlipsMonoIncr(String S) {
        int r0 = 0, l1 = 0;        
       for (int i = 0; i < S.length(); i++) {
           if (S.charAt(i) == '0') {
               r0++;
           } 
       }        
       int res = r0;        
       for (int j = 0; j < S.length(); j++) {
           if (S.charAt(j) == '0') {
               r0--;
           } else {
               l1++;
           }
           res = Math.min(l1 + r0, res);
       }
       return res;          
   }
	
    

	
	public static void main(String[] args) {
//		String S = "010110";
//		String S = "11011";
		String S = "000110001";
		System.out.println(minFlipsMonoIncr(S));
	}
	
	
}
