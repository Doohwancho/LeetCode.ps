package julyChallenge;

public class DetectCapital {
	
	//<문제풀이1>
	
	//아 근데 뭔가 섹시하지 않아.

	//뭔가 난잡해.
	
	//true: ABC, Abc, abc
	//false: aBc, abC
	
	//filter1) aBc
	//filter2) abC
	
	//Runtime: 2 ms
	//Memory Usage: 39.7 MB
	
    public boolean detectCapitalUse(String word) {
        char[] w = word.toCharArray();  
        
        //filter1
        if(w.length > 1 && Character.isLowerCase(w[0]) && Character.isUpperCase(w[1])){
            return false;
        }
        //filter2
        for(int i = 2; i < w.length; i++){
            if(Character.isUpperCase(w[i]) != Character.isUpperCase(w[i-1])){
                return false;
            }
        }
        return true;
    }
    
    //<문제풀이2 by lixx2100>
    
    //이거지
    
    //[A-Z]+ : 대문자 최소 한개 이상의 단어
    // | : or
    //[a-z]+ : 소문자 최소 한개 이상의 단어
    //[A-Z][a-z]* : 대문자로 시작하고 그 다음 소문자가 0개~n개 붙어있는 단어.
    
    //Runtime: 9 ms
    //Memory Usage: 39.7 MB
    
    public boolean detectCapitalUse(String word) {
        return word.matches("[A-Z]+|[a-z]+|[A-Z][a-z]*");
    }
    
    //<문제풀이3 by compton_scatter>
    
    //와 이것도 섹시하네
    
    //Runtime: 4 ms
    //Memory Usage: 39.2 MB
    
    public boolean detectCapitalUse(String word) {
        return word.equals(word.toUpperCase()) || word.equals(word.toLowerCase()) || Character.isUpperCase(word.charAt(0)) && word.substring(1).equals(word.substring(1).toLowerCase());
    }
    
    
    //<문제풀이4 by compton_scatter>
    
    //이게 성능 젤 좋다. 위트도 있고.
    
    //Runtime: 1 ms
    //Memory Usage: 37.9 MB
    
    public boolean detectCapitalUse(String word) {
        int a = 0;
        for(char c : word.toCharArray()){
            if(Character.isUpperCase(c)) a++;
        }
        if(a == 1) return Character.isUpperCase(word.charAt(0));
        return a == 0 || a == word.length();
    }
    
}
