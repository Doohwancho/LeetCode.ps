package String;

import java.util.stream.Collectors;


//Q1. java8 lambda, FP style, how?



public class SumOfDigitsOfStringAfterConvert1945 {

	//Trial01
	
	//input: "hvmhoasabaymnmsd", 1
	//output: 61
	//expected: 79
	
	
	//"zbax",2 랑 "iiii", 1랑 "leetcode",2 은 잘 되는데 왜 쟤만 안되지?
	
	//input: "hvmhoasabaymnmsd", 1
	
	//8 22 13 8 15 1 19 1 2 1 25 13 14 13 19 4 
	
	//8 4 4 8 6 1 1 1 2 1 7 4 5 4 1 4
	
	//61

	//맞는데?
	
	//아 19 -> 10 그냥 냅두면 되는데, 괜히 복잡하게 생각해서 19 -> 10 -> 1 이리 생각했네 -_-;;
	
	
    public int stringToDigits(String str){
        return str
            .chars()
            .mapToObj(ch -> (char)ch-96)
            .collect(Collectors.toList())
            .stream()
            .map(i -> i == 19 ? 1 : (i/10+i%10))
            .reduce(0, Integer::sum);
    }
    
    public int sumOfDigits(int n, int k){
        if(k == 1) return n;
        return sumOfDigits(n/100+((n%100)/10+(n%10)), k-1);
    }
    
    public int getLucky(String s, int k) {
        int n = stringToDigits(s);
        System.out.println(n);
        return sumOfDigits(n, k);
    }
    
    
    
    //trial02
    
    //input: "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
    //2
    
    //output: 10
    //expected: 1
    
    //왠지 n/100+((n%100)/10+(n%10) 이 부분에서 문제난 듯 하다.
    //저것도 구현해줘야겠네
    
    public int stringToDigits(String str){
        return str
            .chars()
            .mapToObj(ch -> (char)ch-96)
            .collect(Collectors.toList())
            .stream()
            .map(i -> i/10+i%10)
            .reduce(0, Integer::sum);
    }
    
    public int sumOfDigits(int n, int k){
        if(k == 1) return n;
        return sumOfDigits(n/100+((n%100)/10+(n%10)), k-1);
    }
    
    public int getLucky(String s, int k) {
        int n = stringToDigits(s);
        return sumOfDigits(n, k);
    }
	
    
    
    //Solution1
    
    //Runtime: 6 ms, faster than 38.13% of Java online submissions for Sum of Digits of String After Convert.
    //Memory Usage: 40.8 MB, less than 88.87% of Java online submissions for Sum of Digits of String After Convert.
    
    //오랜만이구만 이 맛
    
    //FP style로 하니까 재밌네 
    
        
    public int stringToDigits(String str){
        return str
            .chars()
            .mapToObj(ch -> (char)ch-96)
            .collect(Collectors.toList())
            .stream()
            .map(i -> i/10+i%10)
            .reduce(0, Integer::sum);
    }
    
    public int reduceCiphers(int n){
        int a = n / 1000;
        int b = n % 1000 / 100;
        int c = n % 100 / 10;
        int d = n % 10;
        return a+b+c+d;
    }
    
    public int sumOfDigits(int n, int k){
        if(k == 1) return n;
        return sumOfDigits(reduceCiphers(n), k-1);
    }
    
    public int getLucky(String s, int k) {
        int n = stringToDigits(s);
        return sumOfDigits(n, k);
    }
   


}
