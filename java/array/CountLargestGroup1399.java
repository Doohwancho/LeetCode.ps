package array;

public class CountLargestGroup1399 {
	
	//<문제풀이1>
	
	//Runtime: 3 ms, faster than 98.82% of Java online submissions for Count Largest Group.
	//Memory Usage: 36.2 MB, less than 100.00% of Java online submissions for Count Largest Group.
	public int countLargestGroup(int n) {
        int[] container = new int[100];
        
        for(int i = 1; i <= n; i++){
        	int i_ = i;
            int num = 0;
            while(i_ > 0) {
            	num += i_%10; //마지막 숫자 하나씩 빼서 num에 더해줌. 192에서 2 빼고
            	i_/=10; //19가 남음
            }
            container[num-1]++; // 다 더해주면 1+9+2 하면 12네. 11번 인덱스에 +1해줌
        }
        
        int maxSize = 0;
        int rst = 0;
        
        for(int element : container) { //element는 같은 num이 나온 숫자들의 빈도수임. 
        	if(element == maxSize) {  //이 빈도수가 max일땐 rst+1해줌
        		rst++;
        	}
        	else if(element > maxSize) { //더 큰 빈도수가 나오면 rst=1로 리셋해줌
        		maxSize = element;
        		rst = 1;
        	} 
        }
        return rst;
    }
}
