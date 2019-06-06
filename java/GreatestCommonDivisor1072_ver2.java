

package String;

//Solution by kylewzk

//e.g)
//String str1 = "TAUXXTAUXXTAUXXTAUXXTAUXX";
//String str2 = "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX";
//
//TAUXXTAUXXTAUXXTAUXXTAUXX    TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX
//TAUXXTAUXXTAUXXTAUXXTAUXX    TAUXXTAUXXTAUXXTAUXX
//TAUXXTAUXXTAUXXTAUXX    TAUXX
//TAUXX

public class GreatestCommonDivisor1072_ver2 {
	public static String gcdOfStrings(String str1, String str2)
	{
		//먼저 더 짧은 문자열을 str1에 담고, 긴 문자열을 str2에 넣는다.
		if(str1.length() > str2.length()) {
            String s = str1;
            str1 = str2;
            str2 = s;
        }
        //만약 str2가 str1로 시작되지 않는다면, 아예 겹치는 단어가 없다는 말이므로, ""를 반환한다.
        if(!str2.startsWith(str1)) return "";
        
        //만약 긴 문자열의 길이를 짧은 문자열의 길이만큼 나누었을 때, 딱 떨어지게 나누어진다면, 가장 긴 common words는 str1이다.
        if(str2.length() % str1.length() == 0) return str1;
        
        //재귀용법을 써서 위의 로직을 다시 돌린다. 다음 로직때는 긴 문자열 str2가 기존의 짧은 문자열 str1이 되고, 
        //짧은 문자열 str1은 긴 문자열에서 짧은 문자열을 뺀 값이 들어간다.
        return gcdOfStrings(str1, str2.substring(str1.length()));
	}
	
	public static void main(String[] args) 
	{
		String str1 = "TAUXXTAUXXTAUXXTAUXXTAUXX";
		String str2 = "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX";
		
		System.out.println(gcdOfStrings(str1, str2));
	}
}
