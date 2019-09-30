package HashTable;

/*
	You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.
	
	Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows. 
	
	Please note that both secret number and friend's guess may contain duplicate digits.
	
	Example 1:
	
	Input: secret = "1807", guess = "7810"
	
	Output: "1A3B"
	
	Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
	Example 2:
	
	Input: secret = "1123", guess = "0111"
	
	Output: "1A1B"
	
	Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
	
	
	
	
	
	<문제>
	
	친구와 너가 게임을 한다. 너는 4개의 숫자로 이루어진 secret을 가지고 있다.
	
	친구는 이 숫자를 추축해서 맞춰야 한다고 하자.
	
	친구가 4개의 숫자를 추측했을 때, 숫자가 정확하게 해당 자리에 있는 경우, A의 카운트를 올리고, 
	
	예측한 숫자가 secret에 포함되있긴 한데, 자리가 틀렸다면, B의 카운트를 올려서 A와 B를 반환해라.
	
	예를들어서, secret = "1234", guess = "1243"이라면,
	
	1과 2는 secret에 있으면서 자리도 맞으므로, 2A이고,
	
	3와 4는 secret에 있긴 하지만 자리에서 벗어났으므로, 2B니까,
	
	"2A2B"륿 반환하면 된다.


 */

public class BullsAndCows299 {
	
	
	
	//<문제풀이1>
	
	//A의 조건은 A-1. secret에 포함되는 알파벳, A-2. 위치까지 같음.
	//B의 조건은 B-1. secret에 포함되는 알파벳, B-2. 위치는 동일위치가 아님.
	
	//먼저 첫번째 loop로 A를 카운팅한다. 이 때, secret과 guess에서 한번 카운팅 한 숫자는 다음에 또 카운팅하면 안되기 때문에,
	
	//secret의 카운팅 한 숫자는 a로 바꾸고, guess에 카운팅 한 숫자는 b로 바꾼다.
	
	//두번째 loop에선 같은 방법으로 B를 찾는다.
	
	//한번에 loop으로 A,B 동시에 찾기가 왜 어렵냐면, 
	
	//secret = "110", guess = "011"의 경우, 
		
	//	0a1   b10   0   1
	//	0aa   bb0   0   2
	//	aaa   bbb   0   3
	//	0A3B	
	
	//먼저 올바른 위치에 알파벳이 동시에 나오는걸 찾지 못하는 경우, secret.indexOf() = 'a'가 오만데를 지칭할 수 있기 때문.
	
	//메모리는 잘썼는데 속도 에바참치..
	
	//Runtime: 18 ms, faster than 5.14% of Java online submissions for Bulls and Cows.
	//Memory Usage: 36.8 MB, less than 100.00% of Java online submissions for Bulls and Cows.
	/*
	public static String getHint(String secret, String guess) {
        char[] secretCharset = secret.toCharArray();
        char[] guessCharset = guess.toCharArray();
        int A = 0;
        int B = 0;
		
		for(int i = 0; i < secret.length(); i++) {
        	if(secretCharset[i] == guess.charAt(i)) {
        		A++;
        		secretCharset[secret.indexOf(guess.charAt(i))] = 'a';
        		secret = String.valueOf(secretCharset);
        		
        		guessCharset[i] = 'b';
        		guess = String.valueOf(guessCharset);
        	}        
		}
		
		for(int i = 0; i < secret.length(); i++) {
        	if(secret.contains(guess.charAt(i)+"")) {
        		B++;
        		secretCharset[secret.indexOf(guess.charAt(i))] = 'a';
        		secret = String.valueOf(secretCharset);
        	
        		guessCharset[i] = 'b';
        		guess = String.valueOf(guessCharset);
        	}
		}
		return A+"A"+B+"B";
    }
	*/
	
	//<문제풀이2>
	
	//방식은 문제풀이1과 같으나, char[]대신 stringbuilder씀.
	
	//10ms 빨라짐.
	
	//Runtime: 8 ms, faster than 14.66% of Java online submissions for Bulls and Cows.
	//Memory Usage: 36.6 MB, less than 100.00% of Java online submissions for Bulls and Cows.
	/*
	public static String getHint(String secret, String guess) {
		StringBuilder sb1 = new StringBuilder(secret);
		StringBuilder sb2 = new StringBuilder(guess);
		int A = 0;
		int B = 0;
		
		for(int i = 0; i < secret.length(); i++) {
			if(secret.charAt(i) == guess.charAt(i)) {
				A++;
				sb1.setCharAt(i, 'a');
				sb2.setCharAt(i, 'b');
			}
		}
		
		
		for(int j = 0; j < guess.length(); j++) {
			if(sb1.indexOf(sb2.charAt(j)+"") >= 0) {
				B++;
				sb1.deleteCharAt(sb1.indexOf(guess.charAt(j)+""));
				sb2.setCharAt(j, 'b');
			}
		}
		
		return A+"A"+B+"B";
	}
	*/
	
	
	//<문제풀이 by aonecode>
	
	//if문으로 A조건 보면서, else문으로 B조건을 보는데,
	
	//어짜피 숫자가 0부터 9까지 나오니까, int[10]을 만들어서 빈도수 파악함.
	
	//secret에 나오면 +1, guess에 나오면 -1해주는 방식으로.
	
	//단 +-1을 했을때 0보다 >=(secret) <=(guess)의 경우는, 이미 한번 나왔다는 뜻이므로  B+1을함.
	
	//완-벽
	
	//Runtime: 1 ms, faster than 100.00% of Java online submissions for Bulls and Cows.
	//Memory Usage: 36.1 MB, less than 100.00% of Java online submissions for Bulls and Cows.
	
	public static String getHint(String secret, String guess) {
        int a=0,b=0;
        int[] digits=new int[10];
        for(int i=0;i<secret.length();i++){
	        if(secret.charAt(i)==guess.charAt(i)) a++;
            else{
                if(++digits[secret.charAt(i)-'0']<=0) b++;
                if(--digits[guess.charAt(i)-'0']>=0) b++;
            }
         }
         return a+"A"+b+"B";
	}
	
	public static void main(String[] args) {
		//String secret = "1807";
		//String guess = "7810";
		
		String secret = "11";
		String guess = "10";
			
		//String secret = "011";
		//String guess = "110";
		
		System.out.println(getHint(secret, guess)); 
	}
}
