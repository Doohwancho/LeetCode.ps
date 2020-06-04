package juneChallenge;

public class ReverseString {
	
	//<문제풀이1>
	
	//그냥 two-pointer써서 swap하면 되잖어.
	
	//오늘껀 쉬운거구만
	
    //Runtime: 0 ms
    //Memory Usage: 45.7 MB
    public void reverseString(char[] s) {
        int l = 0, r = s.length-1;
        while(l<r){
            char tmp = s[l];
            s[l] = s[r];
            s[r] = tmp;
            l++;
            r--;
        }
    }
}
