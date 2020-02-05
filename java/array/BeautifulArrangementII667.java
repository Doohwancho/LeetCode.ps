package array;

public class BeautifulArrangementII667 {
	
	/*
	//<Trial01>
	
	//[1,2,3,4] k = 1
    //[1,2,4,3] k = 2
    //[1,4,2,3] k = 3
    
    //step01 - 마지막 숫자를 뺀다
    //step02 - arr.size-k부터 끝까지 오른쪽으로 한칸씩 민다
    //step03 - arr.size-k번째 인덱스에 마지막 숫자를 넣는다.
	
	//arr.size가 5이상부터는 안먹히는 방법이네
	//어쩐지 너무 쉽게 풀리더라니
    public static int[] constructArray(int n, int k) { 
        int[] rst = new int[n];
        int num = 1;
        int idx = 0;
        while(idx < n){
            if(idx == n-k){
                rst[idx++] = n;
            }
            else{
                rst[idx] = num;
                idx++;
                num++;
            }
        }
        return rst;
    }
    */
	
	//<문제풀이1>
	
	////n = 6, k = 4
	
	//라고 가정하면,
	
	//답이 이런식으로 나온다. [1,6,2,5,4,3] 
	
	//자세히 보면 일정 패턴이 보이는데,
	
	//1->6->2->5
	
	//가장 작은수, 가장 큰수, 두번째로 작은수, 두번째로 큰수 ..
	
	//이런식으로 흘러가고, 수의 차가 +5,+4,+3 ..
	
	//이런 일정한 패턴이 있음.
	
	//우리가 할 일은, index 0부터 k-1까지 숫자를 작은수,큰수 교차로 넣고,(k-1까지 차이가 확보)
	
	//나머지 빈 공간들을 가장 마지막에 등장한 수의 1 차이나는 수로 메꿔주면 됨(나머지 1확보)
	
	//Runtime: 1 ms, faster than 85.07% of Java online submissions for Beautiful Arrangement II.
	//Memory Usage: 40.7 MB, less than 25.00% of Java online submissions for Beautiful Arrangement II.
    public static int[] constructArray(int n, int k) {
        int[] rst = new int[n];
        boolean flag = true;

        for(int i = 0, str = 1, end = n; i < n; i++){
            if(flag){
            	if(i < k) {          //if(i<k)를 이용하여 k번째까지 교차로 넣기
                    rst[i] = str++;
                    flag = false;
            	}
            	else {               //else문은 k-1번째까지 수가 모두 채워지면, 나머지 숫자들을 1차이나는 숫자로 채우기
            		rst[i] = end--;
            	}
            } else {
            	if(i < k) {
                    rst[i] = end--;
                    flag = true;		
            	}
            	else {
            		rst[i] = str++;
            	}
            }
        }
        return rst;
    }
	
    public static void main(String[] args) {
		for(int i : constructArray(6,4)) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
}
