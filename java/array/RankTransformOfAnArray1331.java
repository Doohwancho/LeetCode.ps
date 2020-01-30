package array;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class RankTransformOfAnArray1331 {
	/*
	//<Trial01 - Time limit exceeded>
	
	public int[] arrayRankTransform(int[] arr) {
		//step1 - 주어진 arr과 Arrays.sort()로 오름차순 정렬된 originalArr 생성
        int[] originalArr = new int[arr.length];
        for(int k = 0; k < arr.length; k++){
            originalArr[k] = arr[k];
        }
        Arrays.sort(arr);
        
        //step02 - arr을 for문으로 오름차순 순으로 돌면서 순서대로 originalArr에 rank를 넣어줌. 이때, rank가 추후 arr의 원소와 같을 수 있으므로, 겹치지 않게 +10000을 더해줌 
        for(int i = 0, rank = 1; i < arr.length; i++){
            if(i > 0 && arr[i] != arr[i-1]){
                rank++;
            }
            for(int j = 0; j < arr.length; j++){
                if(arr[i] == originalArr[j]){
                    originalArr[j] = rank+10000;
                    break;
                }
            }
        }
        
        //step03 - 겹치지 않게 더해줬던 10000을 빼줌
        for(int q = 0; q < arr.length; q++){
            originalArr[q] -= 10000;
        }
        
        return originalArr;
    }
    */
	
	/*
	//<문제풀이1>
	
	//Runtime: 25 ms, faster than 86.98% of Java online submissions for Rank Transform of an Array.
	//Memory Usage: 55.3 MB, less than 100.00% of Java online submissions for Rank Transform of an Array.
    public int[] arrayRankTransform(int[] arr) {
    	//오름차순으로 rank를 매겨줄 딕셔너리(맵)생성
        Map<Integer, Integer> map = new HashMap<>();
        int[] originalArr = new int[arr.length];
        int rank = 1;
        
        //오름차순 정렬된 arr과 원본 리스트 originalArr생성
        for(int k = 0; k < arr.length; k++){
            originalArr[k] = arr[k];
        }
        Arrays.sort(arr); //생각해보니 원본 데이터 바꾸는건 좀 그러니까 새롭게 만든 리스트를 정렬할껄.
        
        //오름차순으로 rank를 매김
        for(int element : arr){
            if(map.getOrDefault(element, 0) == 0){
                map.put(element, rank++);
            }
        }
        
        //원본 리스트를 돌면서 딕셔너리(맵)에 저장된 rank를 매겨줌
        for(int i = 0; i < arr.length; i++){
            originalArr[i] = map.get(originalArr[i]);
        }
        
        return originalArr;
    }
    */
	
	
	//<문제풀이2 by xiaoxiang615>
	
	//별건 없는데 hashset말고 treeset이란걸 이용했네?
	
	//treeset, hashset, linkedhashset 세개가 있다는데,
	
	//treeset의 특징은 저장할때 부터 바로 정렬함. 그러면 위에서 했던 Arrays.sort()할 필요가 없구나.
	
	//Runtime: 44 ms, faster than 65.10% of Java online submissions for Rank Transform of an Array.
	//Memory Usage: 56.7 MB, less than 100.00% of Java online submissions for Rank Transform of an Array.
    public int[] arrayRankTransform(int[] arr) {
        TreeSet<Integer> set = new TreeSet<>();
        Map<Integer, Integer> posMap = new HashMap<>();
        for(int i=0;i<arr.length;i++){
            set.add(arr[i]);
        }
        int cnt = 0;
        for(int n : set){
            posMap.put(n, ++cnt);
        }
        for(int i=0;i<arr.length;i++){
            arr[i] = posMap.get(arr[i]);
        }
        return arr;
    }
    
    
    /*
    //<Trial02 - time limit exceeded>
    
    //문제풀이2에 map이 굳이 필요 없을것 같아서 뺄라그랬는데 필요하데.
      
    //+- 10000안해도 rank와 arr[i]가 같아지는 부분 처리를 못함 
    
    public int[] arrayRankTransform(int[] arr) {
        TreeSet<Integer> set = new TreeSet<>();
        int rank = 0;
        
        for(int element : arr){
            set.add(element);
        }
        
        for(int n : set){
            rank++;
            for(int i = 0; i < arr.length; i++){
                if(n == arr[i]){
                    arr[i] = rank+10000;
                }
            }
        }
        
        for(int j = 0; j < arr.length; j++){
            arr[j]-=10000;
        }
        
        return arr;
    }
    */
}
