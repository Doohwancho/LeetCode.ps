package array;

import java.util.Arrays;

public class FindLuckyIntegerInArr1394 {
	
	//<문제풀이1>
	
	//일단 뒤섞인걸 오름차순 정렬하고,
	
	//맨 뒤에서부터 lucky 숫자인지 보는데,
	
	//만약 [...3,3,3] 이라면, 맨 뒤3의 인덱스가 5라고 하면, 첫번째 3의 인덱스는 
	
	//맨 뒤의 3의 인덱스 - 3 + 1
	
	//이게 일단 맞는지 확인하고, 맞다면 이게
	
	//[...3,3,3,3,3,3]이라서 걸린건지, 아니면
	
	//[...2,3,3,3] 처럼 lucky넘버라서 걸린건지 그 다음 if문으로 확인
	
	//만약 3이 lucky넘버가 아니였다면, 해당 숫자는 더이상 볼 필요 없으므로 while문으로 건너뛰어줌
	
	//걍 int[]에 박아놓고 뒤에서부터 카운팅할껄 그랬나
	
	//Runtime: 1 ms, faster than 99.90% of Java online submissions for Find Lucky Integer in an Array.
	//Memory Usage: 39.3 MB, less than 100.00% of Java online submissions for Find Lucky Integer in an Array.
    public int findLucky(int[] arr) {
        Arrays.sort(arr);
        for(int i = arr.length-1; i >= 0; i--){
            int n = arr[i];
            if(i-n+1 >= 0 && n == arr[i-n+1]){
                if(i-n < 0 || (i-n>=0 && n != arr[i-n])){
                    return arr[i];
                }
            }
            while(i > 0 && arr[i] == arr[i-1]){
                i--;
            }
        }
        return -1;
    }
}
