package augustChallenge;

import java.util.Arrays;
import java.util.List;

public class PascalsTriangle {
	
	//<문제풀이1>
	
	//EEEEEEEEEZZZZZZZZZZZZZZZZZZZZZZZ
	
	//Runtime: 0 ms
	//Memory Usage: 37 MB
	
    public List<Integer> getRow(int rowIndex) {
        int[] a = new int[rowIndex+1];
        a[0] = 1;
        int idx = 0;
        while(idx < rowIndex){
            for(int i = 1, prev = 1, tmp = 0; i < idx+1; i++){
                tmp = a[i];
                a[i] += prev;
                prev = tmp;
            }
            a[idx+1] = 1;
            idx++;
        }
        List<Integer> rst = new ArrayList<>(a.length);
        for(int i : a){
            rst.add(i);
        }
        return rst;
    }
    
    
    //<문제풀이2>
    
    //똑같은건데, int[] -> arraylist변환이 간지가 안나서
    
    //Arrays.asList()로 바꿈.
    
    //int[] -> ArrayList로 바꾸고 싶을때, Arrays.asList() 쓰면 안되는데,
    
    //Integer[] -> ArrayList시, Arrays.asList()쓰면 됨.
    
    //ArrayList이 primitive type받는게 아니라 reference type받아서 그런듯.
    
    //또한 Arrays.fill(a, 0);로 모든 원소를 0으로 초기화 해주는 해주는게 좋음.
    
    //넣지도 않았는데, .get()해서 부르려고 하면 null pointer exception뜨기 때문.
    
    //Runtime: 0 ms
    //Memory Usage: 37.1 MB
    
    public List<Integer> getRow(int rowIndex) {
        Integer[] a = new Integer[rowIndex+1];
        //Arrays.fill(a, 0);
        a[0] = 1;
        int idx = 0;
        while(idx < rowIndex){
            for(int i = 1, prev = 1, tmp = 0; i < idx+1; i++){
                tmp = a[i];
                a[i] += prev;
                prev = tmp;
            }
            a[idx+1] = 1;
            idx++;
        }
        return Arrays.asList(a);
    }
    
    
    
    //<문제풀이3 by jeantimex>
    
    //얜 뒤에서부터 했네. 그러면 tmp랑 prev변수로 이리주고 저리주고 안해도 되고 깔끔하게 떨어지네
    
    //clean code ㅇㅈ
    
    //Runtime: 0 ms
    //Memory Usage: 37.2 MB
    
    public List<Integer> getRow(int k) {
        Integer[] arr = new Integer[k + 1];
        Arrays.fill(arr, 0);
        arr[0] = 1;
        
        for (int i = 1; i <= k; i++) 
            for (int j = i; j > 0; j--) 
                arr[j] = arr[j] + arr[j - 1];
        
        return Arrays.asList(arr);
    }
}
