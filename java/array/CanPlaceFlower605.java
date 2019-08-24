/*
	Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.
	
	Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.
	
	Example 1:
	Input: flowerbed = [1,0,0,0,1], n = 1
	Output: True
	Example 2:
	Input: flowerbed = [1,0,0,0,1], n = 2
	Output: False
	
	
	
	<문제>
	
	flowerbed와 n이 flowerbed = [1,0,0,0,1], n = 1 이렇게 주어진다.
	
	[1,0,0,0,1]에서 1은 꽃이 심어져 있는 자리, 0은 비어있는 자리를 뜻한다.
	
	꽃은 바로 옆에 있으면 물/영양분 경쟁하다 둘 다 죽으므로, 꽃 사이에 최소 1칸씩 띄어놔야한다.
	
	[1,0,1,0,1]처럼 말이다.
	
	[1,0,0,0,1]에서 새롭게 꽃을 심을 수 있는 자리의 갯수가 n만큼 있거나 더 크면, true를 반환하라.
	
 */
package Array;

public class CanPlaceFlower605 {
	
	/*
	//<문제풀이>
	
	//속도와 메모리 수치는 잘 나왔으나 코드가좀 난잡..
	
	//먼저 두가지 경우의 수로 나뉜다.
	
	//첫번째는, flowerbed의 길이가 3미만인 경우, 두번째는 3이상인 경우다.
	
	//flowerbed가 [0,0]나 [1]처럼 있으면, 경우의 수가 몇가지 되지가 않아 따로 처리를 해줬다.
	
	
	////////////////////////
	//flowerbed - n
	
    //1개이상 - 1개      false
    //1개이상 - 0개      true
    
    //0개 - 2개이상       false
    //0개 - 1개             true
    //0개 - 0개             true
	////////////////////////

	//두번째 경우(flowerbed의 길이가 3이상)는, for문을 돌면서 i-1번째, i번째, i+1번째가 모두 0인경우, i번째에 1을 넣어주고 available+1을 해주는 식으로 짰다.
	
	//그런데 위 로직의 경우,i가 1에서 시작해서 마지막 인덱스-1에서 끝나니까, 양 끝 부분을 처리해주어야 해서
	
	//위 아래로 처름과 마지막 부분을 처리하는 for문을 넣었다.
	
	
	//Runtime: 1 ms, faster than 100.00% of Java online submissions for Can Place Flowers.
	//Memory Usage: 38.4 MB, less than 100.00% of Java online submissions for Can Place Flowers.
	
	public static boolean canPlaceFlowers(int[] flowerbed, int n) {
		if(flowerbed.length < 3)
        {
            int cnt = 0;
            
            for(int j : flowerbed)
            {
                if(j == 1) cnt++;
            }
            
            if(cnt > 0 && n == 0) return true;
            else if(cnt > 0 && n > 0) return false;
            else if(n >= 2) return false;
            else return true;
        }
        
        int available = 0;
        
        if(flowerbed[0] == 0 && flowerbed[1] == 0)
        {
        	flowerbed[0] = 1;
        	available++;
        }
		
		for(int i = 1; i < flowerbed.length-1; i++)
		{
			if(flowerbed[i-1] == 0 && flowerbed[i] == 0 && flowerbed[i+1] == 0)
			{
				flowerbed[i] = 1;
				available++;
			}
		}
		
        if(flowerbed[flowerbed.length-2] == 0 && flowerbed[flowerbed.length-1] == 0)
        {
        	flowerbed[flowerbed.length-1] = 1;
        	available++;
        }
		
		
		return available >= n;
    }
    */
	
	//<문제풀이 by alexander>
	
	//위에꺼보다 훨씬 깔끔..
	
	//위에꺼랑 로직은 같은데, 불필요한 flowerbed.length < 3부분을 지우고
	
	//맨 첫번째 부분과 마지막 부분때문에 for문으로 나눠놓은 것을
	
	//조건식안에 넣었다.
	
	//i-1번재와 i+1번째 체크시 array out of bound 뜰 것을 염려하여 or뒤에 표현하였다
	
	//또한 따로 cnt변수 선언 없이 n에서 빼주고 n <= 0을 반환함
	
	//Runtime: 1 ms, faster than 100.00% of Java online submissions for Can Place Flowers.
	//Memory Usage: 39.1 MB, less than 100.00% of Java online submissions for Can Place Flowers.
	
	public static boolean canPlaceFlowers(int[] flowerbed, int n) {
		
		for(int i = 0; i < flowerbed.length; i++)
		{
			if(flowerbed[i] == 0 && (i == 0 || flowerbed[i-1] == 0) && (i == flowerbed.length-1 || flowerbed[i+1] == 0))
			{
				flowerbed[i] = 1;
				n--;
			}
		}
		return n <= 0;
	}
	
	public static void main(String[] args) {
		int[] flowerbed = {1,0,0,0,1};
		//int[] flowerbed = {0,0,0,0,0,1};
		//int[] flowerbed = {1};
		int n = 1;
		//int[] flowerbed = {0,0,0,0,1};
		//int[] flowerbed = {1,0,1,0,1,0,1};
		//int n = 1;
		
		System.out.println(canPlaceFlowers(flowerbed, n));
	}
}
