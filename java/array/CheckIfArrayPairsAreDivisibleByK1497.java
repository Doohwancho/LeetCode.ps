package array;

import java.util.HashMap;
import java.util.Map;

public class CheckIfArrayPairsAreDivisibleByK1497 {
	
	//<Trial01>
	
    //arr = [1,2,3,4,5,10,6,7,8,9], k = 5
    
    //1,2,3,4,0,0,1,2,3,4
	
	//i%k하고 k-i를 찾는 방법.
	
	//마이너스값이 나오면 막히네ㅠㅠ 까비 아깝소
	
	//[-1,1,-2,2,-3,3,-4,4], 3
	
	//일때 -1이랑 4를 매핑해야 하는디... 4가 1이되니 이걸 우찌할까
	
	//다 양수로 만드러 브러?
	
	//[-1,1,-2,2,-3,3,-4,4],3 이면
	
	//2,1,1,2,0,0,2,1
	
	//{0:2,1:3,2:3}
	
	//오잉? 유레카?
    
    public boolean canArrange(int[] arr, int k) {
        
        Map<Integer, Integer> map = new HashMap<>();
        int total = 0;
        
        for(int i = 0; i < arr.length; i++){
            total += arr[i];
            map.put(arr[i]%k, map.getOrDefault(arr[i]%k, 0)+1);
        }
        if(total % k > 0) return false;
        
        //find k-arr[i]
        for(Map.Entry<Integer, Integer> m : map.entrySet()){
            if(!map.containsKey((k-m.getKey())%k) || m.getValue() != map.get((k-m.getKey())%k)){
                return false;
            }
        }
        return true;
    }
    
    
    //<Trial02>
    
    //k=2155
    
    //{0=144, 2145=153, 1=166, 2=151, 2146=145, 2147=158, 3=124, 4=148, 2148=143, 2149=158, 5=127, 
    //2150=127, 6=158, 7=143, 2151=148, 8=158, 2152=124, 2153=151, 9=145, 2154=166, 10=153}
    
    //미친인풋이면 안된다. 왤까.
    
    //0은 패스, 2145는 막힘.
    
    public boolean canArrange(int[] arr, int k) {
        
        Map<Integer, Integer> map = new HashMap<>();
        int total = 0;
        
        for(int i = 0; i < arr.length; i++){
            total += arr[i];
            if(arr[i] < 0){
                while(arr[i] < 0){
                    arr[i]+=k;
                }   
            }
            map.put(arr[i]%k, map.getOrDefault(arr[i]%k, 0)+1);   
        }
        if(total % k > 0) return false;
        
        //find k-arr[i]
        for(Map.Entry<Integer, Integer> m : map.entrySet()){
            if(!map.containsKey(m.getKey() == 0 ? 0 :(k-m.getKey())) || m.getValue() != map.get(m.getKey() == 0 ? 0 : k-m.getKey())){
                return false;
            }
        }
        return true;
    }
    
    
    //<Trial03>
    
    //Integer끼비 비교는 ==쓰면 안되고 .equals()써야되네?
    
    //꼴에 객체라고 주솟값 비교라 이건가?
    
    //이거 돌리면 끝판왕 testcase나오면서 막히네
    
    public boolean canArrange(int[] arr, int k) {
        
        Map<Integer, Integer> map = new HashMap<>();
        int total = 0;
        
        for(int i = 0; i < arr.length; i++){
            total += arr[i];
            if(arr[i] < 0){
                while(arr[i] < 0){
                    arr[i]+=k;
                }   
            }
            map.put(arr[i]%k, map.getOrDefault(arr[i]%k, 0)+1);   
        }
        if(total % k > 0) return false;
        
        //find k-arr[i]
        for(Map.Entry<Integer, Integer> m : map.entrySet()){
            
            if(!map.containsKey(m.getKey() == 0 ? 0 :(k-m.getKey())) || !m.getValue().equals(map.get(m.getKey() == 0 ? 0 : k-m.getKey()))){
                return false;
            }
        }
        return true;
    }
    
    //<문제풀이1>
    
    //인간승리인가?
    
    //씹 어거지긴 한데 어쨌든 패스했긴 했네.
    
    //옛말에 머리가 나쁘면 손발이 고생한다던데 
    
    //나를 위한 말이었나? 슈바
    
    //Runtime: 90 ms, faster than 9.75% of Java online submissions for Check If Array Pairs Are Divisible by k.
    //Memory Usage: 96.7 MB, less than 100.00% of Java online submissions for Check If Array Pairs Are Divisible by k.
    
    public boolean canArrange(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int total = 0;
        
        for(int i = 0; i < arr.length; i++){
            total += arr[i];
            arr[i] %= k; //마이너스여도 %k가 되긴 됨. 다만 %한 결과값이 마이너스라서 그렇지.
            if(arr[i] < 0){
                arr[i] += k; //그럼 그냥 +k해주면 k보다 같거나 작은 양수가 됨.
            }
            map.put(arr[i]%k, map.getOrDefault(arr[i]%k, 0)+1);   
        }
        if(total % k > 0 || map.containsKey(0) && map.get(0)%2 == 1) return false; //i가 0이라는 말은 i가 0이거나 k였다가 %k당해서 0됬다는 건데, 어쨌든 얘내들이 짝수개여야지 페어했을때 남는게 없잖어
        
        for(Map.Entry<Integer, Integer> m : map.entrySet()){
        	//1) k-i가 없거나, 2)i의 갯수랑 k-i의 갯수가 같지 않거나
        	if(!map.containsKey((k-m.getKey())%k) || !m.getValue().equals(map.get((k-m.getKey())%k))){
                return false;
            }
        }
        return true;
    }
    
    //<문제풀이2 by harin_mehta>
    
    //맵쓸 필요가 없어. %k하고 어짜피 마이너스 양수화 하면 다 0부터 k-1까지 자너
    
    //int[]쓰니까 훨빠르지.
    
    //Runtime: 10 ms, faster than 62.22% of Java online submissions for Check If Array Pairs Are Divisible by k.
    //Memory Usage: 47.7 MB, less than 100.00% of Java online submissions for Check If Array Pairs Are Divisible by k.
    
    public boolean canArrange(int[] arr, int k) {
        int[] frequency = new int[k];
        for(int num : arr){
            num %= k;
            if(num < 0) num += k;
            frequency[num]++;
        }
        if(frequency[0]%2 != 0) return false;
        
        for(int i = 1; i <= k/2; i++)
            if(frequency[i] != frequency[k-i]) return false;
			
        return true;
    }
}
