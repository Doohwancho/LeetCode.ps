package dynamicProgramming;

public class ParlindromicSubstring647 {
	
	
	//<문제풀이1 by shawngao>
	
	//for문으로 알파벳 하나하나 돌면서, i번째를 palindrome의 중앙 기준으로 잡고, i번째 기준에서 양끝단에 갈수있는 최대까지 palindrome인지 보는 방법.
	
	//Runtime: 2 ms, faster than 96.87% of Java online submissions for Palindromic Substrings.
	//Memory Usage: 37.4 MB, less than 54.18% of Java online submissions for Palindromic Substrings.
	
    public int countSubstrings(String s) {
        int count=0;
        for(int i=0;i<s.length();i++){
            count+=extractPalindrome(s,i,i);//odd length
            count+=extractPalindrome(s,i,i+1);//even length
        }
        return count;
    }
    public int extractPalindrome(String s, int left, int right){
        int count=0;
        while(left>=0 && right<s.length() && (s.charAt(left)==s.charAt(right))){
            left--;
            right++;
            count++;
        }
        return count;
    }
    
    //<문제풀이2 by li-_-il>
    
    //dp
    
    //testcase:"abcba"
    
    //  a b c b a
    //a 1 0 0 0 1
    //b 0 1 0 1 0
    //c 0 0 1 0 0
    //b 0 0 0 1 0
    //a 0 0 0 0 1
    
    //j - i  + 1 < 3 는 "aa"같이 길이가 2 이하인 string이 있는 경우에 쓰려고 넣음
    
    //중요한 조건은 2가지
    
    //s.charAt(i) == s.charAt(j) 얘랑
    
    //dp[i + 1][j - 1] 얘
    
    //일단 첫번째 s.charAt(i) == s.charAt(j) 얘는 palindrome이려면 필수적으로 넣어야 겠지
    
    //그럼 "abcba"에서 각각의 개별 알파벳 'a','b','c','b','a'는 카운트 되겠지
    
    //그럼 그 다음에 생각해야 하는건 "bcb"랑 "abcba"를 어떻게 넣냐인데,
    
    //그걸 가능하게 만드는게 두번째 놈 dp[i + 1][j - 1] 얘임
    
    //"bcb"를 어떻게 카운트 하는지 생각해보자
    
    //일단 'c'에서 s.charAt(i) == s.charAt(j)때문에 +1이 boolean[][]dp 에 박힘
    
    //그 다음 'b'차례에서 for문으로 'b' -> 'c' -> 'b' -> 'a' 갈 때,
    
    //'b' -> 'c' -> ***'b'*** -> 'a'
    
    //이 부분에서 i번째 'b'랑 j번째 'b'가 같아지지? "bcb"니까
    
    //여기서 i는 "bcb"에서 가장 왼쪽에 위치한 인덱스고, j는 가장 오른쪽에 위치한 인덱스임. 그러니까, 원래 주어진 string에서 "abcba"에선 i는 1, j는 3이 되겠지
    
    //여기서 우리가 신경써야하는 부분은, "bcb"이전단계가 palindrome인지 봐야겠지?
    
    //만약 "b??b"에서 양끝단에 b가 똑같아도, 가운데 ??두개가 "bxyb"면 palindrome이 아니잖아. "baab"그 전단계인 "aa"도 palindrome이여야 그 다음단계인 "baab"를 보고 +1을 해줄 수 있잖아.
    
    //그 이전단계를 보는게 dp[i+1][j-1]임. 
    
    //i(left most index)+1이랑 j(right most index)-1을 봐서 그 전단계가 palindrome이었다면 1이 박혀있었겠지.
    
    //  a b c b a
    //a 1 0 0 0 1
    //b 0 1 0 1 0
    //c 0 0 1 0 0
    //b 0 0 0 1 0
    //a 0 0 0 0 1
    
    //보면 i가 1, j가 3인 시점에서, 그 전단계인 i가 2, j가 2일 때, 'c'일 때, palindrome이었으므로, "bcb"도 palinedrome이구나 해서 +1 해준거임.
    
    //"abcba"도 마찬가지로, 그 전단계인 "bcb"자리에 +1이 박혀있고, 양끝단인 'a'가 서로 같으므로, +1해준거임.
    
    //Runtime: 10 ms, faster than 39.71% of Java online submissions for Palindromic Substrings.
    //Memory Usage: 39.2 MB, less than 28.79% of Java online submissions for Palindromic Substrings.
    
    public int countSubstrings(String s) {
        int n = s.length();
        int res = 0;
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i  + 1 < 3 || dp[i + 1][j - 1]);
                if(dp[i][j]) ++res;
            }
        }
        return res;
    }
}
