/*
 * <문제풀이 by tianchi-seattle>
 * 
 * while문으로 주어진 char[]을 돌면서, 해당 알파벳의 빈도수를 구하고, 그 알파벳의 빈도수를 1. String으로 형변환 후, 2. toCharArray()로 바꿔서, 실시간으로 주어진 char[]에 다시 입력.
 * 예를들어, ["a","b","b","b","b","b","b","b","b","b","b"]는 ["a","1","b","1","0","b","b", ...]으로 된다. while문의 포인터가 그 다음으로 넘어갈수록
 * i의 인덱스도 넘어가게끔 설정. 마지막엔 i의 인덱스 반환.
 * 
 */

package String;

public class StringCompression443_ver2 {
	public static int compress(char[] chars)
	{
		int i = 0;
        int j = 0;
        
        while (j < chars.length) {
            chars[i++] = chars[j++];
            
            int count = 1;
            while (j < chars.length && chars[j] == chars[j - 1]) {
                j++;
                count++;
            }
            System.out.println(chars[i]+" "+count+" "+i);
            
            if (count > 1) {
                for (char c : String.valueOf(count).toCharArray()) {
                    chars[i++] = c;
                }
            }
            System.out.println("-----------------------------");
            System.out.println(chars[i]+" "+count+" "+i);
            for(char a: chars) { System.out.println(a);}
            System.out.println("============================");
        }
        
        return i;
    }
	
	
	public static void main(String[] args)
	{
		char[] chars = {'a','a','b','b','b','b','b','b','b','b','b','b','b','b','c'};
		//char[] chars = {'a'};
		//char[] chars = {'b','0','l','A',']','+','O','5','j','4'};
		System.out.println(compress(chars));
	}

}
