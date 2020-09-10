package september;

import java.util.HashSet;
import java.util.Set;

public class BullsAndCows {

	//<Trial01>
	
	//"1122"
	//"1222"
	
	//이렇게 들어갔을 때,
	
	//"3A0B"나와야 되는데,
	
	//"3A1B"이렇게 나오는 경우를 대비하는게 핵심.
	
	//일단 더 간단하게, secret = "112", guess = "122"라고 치자.
	
	//secret "112"를 int[]에 넣으면 [0,2,1, ...]이지?
	
	//이걸 for문으로 iterate할 때,
	
	//첫빠따엔 1한개 까고 a++해줘. 그럼, [0,1,1] a=1이지?
	
	//2빠따엔 b한개 까고 b++해줘. 그럼, [0,1,0] a=1, b=1이지?
	
	//이번턴이 중요함.
	
	//3빠따엔 2한개 까고 a+1해야하는데 이미 이전애가 먹었지?
	
	//우린 b카운트로바 a를 우선적으로 해야하잖아? 그니까
	
	//secret(i) == guess(i)인 경우에 (지금은 인덱스2에 둘다 2)
	
	//이미 카운트 해 놓은게 0인 경우라면(== 예전에 b가 낚아채갔다면)
	
	//b-1ㅎ 해주고 a+1해줘.
	
	
	
	
    public String getHint(String secret, String guess) {
        int[] digit = new int[10];
        StringBuilder sb = new StringBuilder();
        int a = 0;
        int b = 0;
        
        for(char c : secret.toCharArray()){
            digit[c-'0']++;
        }
        
        Character s = null;
        Character g = null;
        
        for(int i = 0; i < secret.length(); i++){
            s = secret.charAt(i);
            g = guess.charAt(i);
            if(s==g){
                a++;
                if(digit[s-'0'] == 0){ //예전에 b가 이미 낚아채갔으면, 동일선상에 있는애인 a가 우선시되어야 하기 때문에
                    b--;  //b를 하나 빼주고 a로 옮겨줌.
                } else {
                    digit[s-'0']--;
                }
                
            } else if(digit[g-'0'] > 0){
                b++;
                digit[g-'0']--;
            }
        }
        if(a > 0){
            sb.append(a);
            sb.append("A");    
        } else {
            sb.append("0A");
        }
        if(b > 0){
            sb.append(b);
            sb.append("B");    
        } else {
            sb.append("0B");
        }
        
        return sb.toString();
    }
}
