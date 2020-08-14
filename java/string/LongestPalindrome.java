package augustChallenge;

public class LongestPalindrome {
	
	//<Trial01>
	
	//짝수개 전부 + 홀수개 중에 잴 큰애 아닌가?
	
	//"civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlo
	//ngendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportion
	//ofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmi
	//ghtliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecanno
	//tdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddead
	//whostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworl
	//dadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheyd
	//idhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichthey
	//whofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothe
	//greattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiont
	//othatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresol
	//vethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbir
	//thoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperish
	//fromtheearth"
	
	//output:655, expected: 983
	
    public int longestPalindrome(String s) {
        int rst = 0;
        int[] a = new int[58];
        
        for(char c : s.toCharArray()){
            a[c-'A']++;
        }
        int maxOdd = 0;
        
        for(int evenCheck : a){
            if(evenCheck % 2 == 1){
                maxOdd = Math.max(maxOdd, evenCheck);
            } else {
                rst += evenCheck;
            }
        }
        return maxOdd+rst;
    }
    
    //<문제풀이1 by yuxiangmusic>
    
    //아 내가 똥멍청이었네
    
    //홀수개 있는 애들은 한개씩만 빼면 짝수개가 되잖어!!!!
    
    //정신차리자 정신!!!
    
    //Runtime: 1 ms
    //Memory Usage: 37.7 MB
    
    public int longestPalindrome(String s) {
        int arr[] = new int[1 << 7], l = 0;
        for (char c : s.toCharArray()) arr[c]++;
        for (int i : arr) l += i >> 1 << 1; //홀수면 맨 끝에 1만 날려
        return l == s.length() ? l : l + 1; //l == s.length()이 만족하는 경우의 수는 s의 모든 알파벳이 짝수일 경우. 한개라도 홀수면 1개 이상이 비니까. 모두 짝수면 l반환. 홀수가 한개라도 있으면 +1해서 반환.
    }
}
