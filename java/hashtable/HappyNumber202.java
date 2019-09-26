package HashTable;

public class HappyNumber202 {
	
	
	//<문제풀이 1>
	
	//%로 1의자리를, /로 나머지 자리를 구하는 방법으로 각 자릿수의 합들을 제곱해 더해서 총 합이 1이 되는지 확인. 
	
	//무한반복되면 스택오버플로우 걸리니까 cnt변수로 일정량 이상 돌면 false를 반환시킴.
	
	//속도는 빠르나 메모리를 많이 먹는 편.
	
	//다른사람이 푼것도 봤는데 이거랑 성능은 같은건 있어도 더 빠르거나 메모리를 잘쓴것 못찾음.
	
	//Runtime: 1 ms, faster than 95.61% of Java online submissions for Happy Number.
	//Memory Usage: 33.4 MB, less than 10.60% of Java online submissions for Happy Number.
	
	public static boolean isHappy(int n) {
		int total = 0;
		int cnt = 0;
		while(n != 1) {
			if(cnt > 10) return false;
			while(n > 0) {
				int lastDigit = n % 10;
				n /= 10;
				total += (int)Math.pow(lastDigit, 2);
			}
			n = total;
			total = 0;
			cnt++;
		}
		return true;
	}
		
	
	public static void main(String[] args) {
		int n = 19;
		System.out.println(isHappy(n));
	}
	
}
