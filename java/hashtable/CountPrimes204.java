package HashTable;

import java.math.BigInteger;

public class CountPrimes204 {
	
	/*
	//prime - ver1
	//가장 basic한 prime number 구하는 법
	//1부터 n까지의 수중에서, 1과 n을 제외한 수 중, 하나라도 나누어 떨어지면 prime number가 아님.
	public static boolean isPrime(int n) {
		for(int i = 2; i < n; i++) if(n%i == 0) return false;
		return true;
	}
	*/
	
	/*
	//<Trial01>
	
	//BigInt를 이용하면 소수를 쉽게 구할 수 있다고 한다.
	
	//isProbablePrime()안에 비트를 넣으면, 해당 비트 안의 숫자가 소수인지 판별해 준다고 한다.
	
	//근데 input이 엄청 커버리면, 비트를 Integer.MAX_VALUE;해도 안되서 실패.
	 * 
	public static int countPrimes(int n) {
		int prime = 0;
		if(n < 2) return 0;
		for(int i = 2; i < n; i++) {
			BigInteger bigInt = BigInteger.valueOf(i);
	        if(bigInt.isProbablePrime(10000000)) prime++;	
		}
		return prime;
    }
    */
	
	//<문제풀이1>
	
	//소수 구할땐, 맨 처음 n이 2로 나누어 떨어진다면, 볼것도 없이 소수가 아니므로 false반환.
	
	//나머지는 3부터 +2의 단위로 나누어 떨어지는지 확인.(2의 배수가 아니라 3,5,7,9,11 ...)
	
	//41을 예로들면, 루트 41은 대략 6.xx인데, 3부터 +2씩 해서 3,5만 체크해서 41이 3이나 5로 나누어 떨어지지만 않으면 소수
	
	//Runtime: 295 ms, faster than 17.54% of Java online submissions for Count Primes.
	//Memory Usage: 33.1 MB, less than 39.62% of Java online submissions for Count Primes.
	
	private static boolean isPrime(int n) {
	    if (n%2==0) return false;
	    for(int i=3;i*i<=n;i+=2) if(n%i==0) return false;
	    return true;
	}
	public static int countPrimes(int n) {
		if(n < 3) return 0;
		int prime = 1;
		for(int i = 3; i < n; i++) if(isPrime(i)) prime++;
		return prime;
	}
	
	public static void main(String[] args) {
		int n = 10;
		System.out.println(countPrimes(n));
	}
}
