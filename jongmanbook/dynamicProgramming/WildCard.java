package dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Q. 와일드카드 패턴과 함께 문자열이 주어졌을 때, 그중 패턴에 대응되는 파일명을 찾아내는 프로그램을 만드시오

public class WildCard {
	
	//Q. wildcard
	
	private static List<String> questionMark(String pattern, String[] test) {
		List<String> rst = new ArrayList<>();
		
		for(String str : test) {
			if(str.length() != pattern.length()) continue;
			boolean flag = true;
			for(int i = 0; i < str.length(); i++) {
				if(pattern.charAt(i) != '?' && pattern.charAt(i) != str.charAt(i)) {
					flag = false;
					break;
				}
			}
			if(flag) {
				rst.add(str);
			}
		}
		
		return rst;
	}
	
	//Q.exclamation mark
	
	private static List<String> exclamationMark(String pattern, String[] test) {
		if(pattern == "**") return null;
		List<String> rst = new ArrayList<>();
		
		if(pattern.charAt(0) == '*' && pattern.charAt(pattern.length()-1) == '*') {
			String pat = pattern.substring(pattern.indexOf('*')+1, pattern.lastIndexOf('*'));
			for(String str : test) {
				if(str.contains(pat)) {
					rst.add(str);
				}
			}
		} else if(pattern.charAt(0) == '*') {
			String pat = pattern.substring(pattern.indexOf('*')+1);
			for(String str : test) {
				if(str.contains(pat)) {
					rst.add(str);
				}
			}
		} else if(pattern.charAt(pattern.length()-1) == '*') {
			String pat = pattern.substring(0, pattern.lastIndexOf('*'));
			for(String str : test) {
				if(str.contains(pat)) {
					rst.add(str);
				}
			}
		} else if(pattern.contains("*")) {
			for(String str : test) {
				boolean flag = true;
				for(int i = 0, j = str.length()-1, pj = pattern.length()-1; i < pj; ) {
					if(pattern.charAt(i) != '*') {
						if(pattern.charAt(i) != str.charAt(i)) {
							flag = false;
							break;
						}
						i++;
					}
					if(pattern.charAt(pj) != '*') {
						if(pattern.charAt(pj) != str.charAt(j)) {
							flag = false;
							break;
						}
						j--;
						pj--;
					}
				}
				if(flag) {
					rst.add(str);
				}
			}
		} else {
			return null;
		}
		return rst;
	}
	
	//Q. wildcard + exclamation mark
	
	//실패
	
	private static List<String> both(String pattern, String[] test){
		List<String> rst = new ArrayList<>();
		
		for(String str : test) {
			boolean flag = true;
			for(int i = 0, j = 0; i < pattern.length() && j < str.length(); ) {
				if(pattern.charAt(i) == '?') {
					i++;
					j++;
					continue;
				} else if(pattern.charAt(i) == '*') {
					i++;
					while(pattern.charAt(i) == '?') {
						i++;
						j++;
					}
					while(j < str.length() && str.charAt(j) != pattern.charAt(i)) { //? what if pattern = "a*?d", test = "aabcd"
						j++;
					}
				}
				if(j == str.length() || pattern.charAt(i) != str.charAt(j)) {
					System.out.println("false");
					flag = false;
					break;
				}
				i++;
				j++;
				System.out.print(str+" i: "+i+" j: "+j);
			}
			if(flag) {
				rst.add(str);
			}
			System.out.println();
		}
		
		return rst;
	}
	
	
	//Q. Wildcard + Exclamation mark
	
	//완전탐색
	
	private static boolean match(String p, String s){
		int pos = 0;
		
		while(pos < s.length() && pos < p.length() && (p.charAt(pos) == '?' || p.charAt(pos) == s.charAt(pos))) {
			pos++;
		}
		if(pos == p.length()) {
			return pos == s.length();
		}
		
		if(p.charAt(pos) == '*') {
			for(int skip = 0; pos+skip <= s.length(); skip++) {
				if(match(p.substring(pos+1), s.substring(pos+skip))) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	//Q. Wildcard + Exclamation mark
	
	//완전탐색+메모이제이션
	
	//O(N^3)

	/*
	//-1: unvisited 0: X match 1: O match
	//사이즈는 가장 String length가 큰애+1
	//이유는 s+skip <= S.length() -> cached[w][s] = (s == S.length() ? 1 : 0);  에서 런타임 에러남
	static int[][] cached = new int[10][10]; 
	static String W,S;
	
	private static boolean matchMemoized(int w, int s){
		int ret = cached[w][s];
		
		if(ret != -1) return ret == 1;
		
		while(s < S.length() && w < W.length() && (W.charAt(w) == '?' || W.charAt(w) == S.charAt(s))) {
			w++;
			s++;
		}
		if(w == W.length()) {
			cached[w][s] = (s == S.length() ? 1 : 0); 
			return cached[w][s] == 1;
		}
		
		if(W.charAt(w) == '*') {
			for(int skip = 0; s+skip <= S.length(); skip++) {
				if(matchMemoized(w+1, s+skip)) {
					cached[w][s] = 1;
					return true;
				}
			}
		}
		cached[w][s] = 0;
		return false;
	}
	*/
	
	//Q. Wildcard + Exclamation mark
	
	//완전탐색+메모이제이션+최적화(for loop없앰)
	
	//O(N^2)
	
	static int[][] cached = new int[10][10]; 
	static String W,S;
	
	private static boolean matchMemoized(int w, int s){
		int ret = cached[w][s];
		
		if(ret != -1) return ret == 1;
		
		while(s < S.length() && w < W.length() && (W.charAt(w) == '?' || W.charAt(w) == S.charAt(s))) {
			return matchMemoized(w+1, s+1); 
		}
		if(w == W.length()) {
			cached[w][s] = (s == S.length() ? 1 : 0); 
			return cached[w][s] == 1;
		}
		
		if(W.charAt(w) == '*') {
			if(matchMemoized(w+1, s) || (s < S.length() && matchMemoized(w, s+1))) {//0글자 되응되는 경우 +한글자 대응되는 경우
				cached[w][s] = 1;
				return true;
			}
		}
		cached[w][s] = 0;
		return false;
	}

	public static void main(String[] args) {
//		String[] test1 = {"abc", "adc", "abcd", "a&c"};
//		String[] test2 = {"papazones","hello","pizza"};
		String[] test2 = {"abc"};
		
//		for(String t1 : questionMark("a?c", test1)) {
//			System.out.println(t1+" ");
//		};
//		System.out.println();
		
//		for(String t2 : exclamationMark("p*", test2)) {
//			System.out.println(t2+" ");
//		};
		
		for(String t3 : test2) {
			for(int i = 0; i < cached.length; i++) {
	            Arrays.fill(cached[i], -1);
	        }
			
			W = "a*";
			S = t3;
			if(matchMemoized(0,0)) {
				System.out.println("success!: "+t3);
			} else {
				System.out.println("failed!: "+t3);
			}
		};
		System.out.println();
		
	}
}
