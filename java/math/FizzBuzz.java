package augustChallenge;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {

	//<문제풀이1>
	
	//EZ
	
	//if (i % 3 == 0 && i % 5 == 0) {
	//    list.add("FizzBuzz");
	//} else if (i % 3 == 0) {
	//    list.add("Fizz");
	//} else if (i % 5 == 0) {
	//    list.add("Buzz");
	//} else {
	//    list.add(String.valueOf(i));
	//}
	
	//이게 좀더 깔끔하네. 어짜피 거의 똑같긴 하지만
	
	//아닌가? 내껀 보통 숫자일땐 if-else if-else 이렇게 3번 거치는데
	
	//얘꺼는 보통 숫자일땐  if-else if-else if-else 이렇게 4번 거치니까
	
	//별로 아닐까? 보통숫자가 3이랑 5의 배수보다 훨~씬 더 많을거아냐.
	
	//얘껀 코드가 깔끔하다 외에 장점이 3과 5의 공배수가 나왔을때 원큐로 간다인데
	
	//내껀 2번 거쳐야 하고. 근데 15의 배수가 한번 나올때 다른 보통 숫자들은 훨씬 많이 나오니까
	
	//실제적으로 돌아가는거 고려하면 쪼금 떨어지는것 같긴 헌디
	
	//Runtime: 1 ms
	//Memory Usage: 40.9 MB
	
    public List<String> fizzBuzz(int n) {
        List<String> rst = new ArrayList<>();
        for(int i = 1; i < n+1; i++){
            if(i % 3 == 0){
                if(i % 5 == 0){
                    rst.add("FizzBuzz");
                } else {
                    rst.add("Fizz");
                }
            } else if(i % 5 == 0){
                if(i % 3 == 0){
                    rst.add("FizzBuzz");
                } else {
                    rst.add("Buzz");
                }
            } else {
                rst.add(Integer.toString(i));
            }
        }
        return rst;
    }
    
    
}
