package augustChallenge;

public class ImplementRand10UsingRand7 {
	

	//<문제풀이1 by lee215>
	
	//fourty = (rand7()-1)*7;
	
	//만 해도, 숫자는 최대 42까지 나와서 문제 없겠지? 라는 생각을 했는데,
	
	//1-> 0 -> 0       ->  1
	//2-> 1 -> 7       ->  8
	//3-> 2 -> 14      ->  5
	//4-> 3 -> 21      ->  2  
	//5-> 4 -> 28      ->  9
	//6-> 5 -> 35      ->  6
	//7-> 6 -> 42 -> 39 -> 10
	
	//evenly distributed 되지 않아서 pass안됨.
	
	//3,4,7이 안나옴.
	
	//굳이 (rand7()-1)*7 + (rand7()-1); 까지 하는 이유는, 
	
	//저기 세번째 줄에 랜덤한 0~6까지 더해주면 모든 숫자가 커버되기 때문.
	
	//두번째 (rand7()-1) 파트가 진짜 랜덤으로 배정하는 역할을 하고
	
	//첫번째 (rand7()-1)*7 파트는 0~42까지 틀을 만드는 역할을 한다고 보면 됨
	
	//Runtime: 5 ms
	//Memory Usage: 45 MB
	
    public int rand10() {
        int fourty = Integer.MAX_VALUE;
        while(fourty >= 40){
            fourty = (rand7()-1)*7 + (rand7()-1);
        }
        return fourty%10+1;
    }
}
