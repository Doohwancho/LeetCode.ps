package julyChallenge;

public class ReverseWordsInAString {

	//<문제풀이1>
	
	//regex
	
	//.split(" ")을 통해 스페이스바 기준 단어별로 끊어서
	
	//매 단어마다 stringbuilder에 첫번째 인덱스에 박아넣음(reverse)
	
	//.split(" +") 인 이유는
	
	//+가 regex에서 한개 혹은 그 이상이라는 뜻인데, testcase에서 보면 "my        dad"같이 space바가 쥰내 나오는데
	
	//output이 "dad my"로 리턴하라는게 있음.
	
	//이 경우, .split(" ")을 쓰면 스페이스바 한개만 생략되기 때문에, 여러개의 스페이스바를 나타내는 " +"를 씀.
	
	//단어를 붙일때마다 str+" "로 띄어쓰기를 임의로 넣어주고, 
	
	//맨 마지막 return시 .trim()을 써서 맨 오른쪽에 불필요한 스페이스 제거.
	
	//Runtime: 13 ms
	//Memory Usage: 42 MB
	
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        
        for(String str : s.split(" +")){
			sb.insert(0, str+" ");
		}
        
        return sb.toString().trim();
    }
}
