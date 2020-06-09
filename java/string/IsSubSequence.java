package juneChallenge;

public class IsSubSequence {
	
	//<문제풀이1>
	
	//bruteforce긴 한데 성능도 나쁘지 않고 잘 되긴 하네
	
	//Runtime: 1 ms
	//Memory Usage: 37.4 MB
	
    public boolean isSubsequence(String s, String t) {
        if(s.length() == 0) return true;
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        
        for(int i = 0, j_ = -1; i < s.length(); i++){
            for(int j = j_+1; j < t.length(); j++){
                if(sChar[i] == tChar[j]){
                    if(i == s.length()-1) return true;
                    j_ = j;
                    break;
                }
                if(j == t.length()-1 && i != s.length()-1){
                    return false;
                }
            }
        }
        return false;
    }
    
    
    //<문제풀이2 by fhqplzj>
    
    //이게 좀 더 간지남
    
    //String.indexOf(char, fromIndex)하면,
    
    //char이 fromIndex부터 끝까지 중에 포함되있으면 해당 char의 인덱스를 반환하고, 없으면 -1을 반환함.
    
    //없어서 -1을 반환하면, 바로 false return해주면 되고
    
    //있으면, 다음 char을 체크할때 확인해야 하는 t의 범위를 좁혀야 되잖어
    
    //그니까 fromIndex+1부터 끝까지로 업데이트 시키지. 
    
    //Runtime: 0 ms
    //Memory Usage: 39.1 MB
    
    public boolean isSubsequence(String s, String t) {
        int fromIndex = 0;
        for (char c : s.toCharArray()) {
            fromIndex = t.indexOf(c, fromIndex);
            if (fromIndex++ < 0) {
                return false;
            }
        }
        return true;
    }
}
