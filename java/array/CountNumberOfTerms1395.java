package array;

import java.util.ArrayList;
import java.util.List;

public class CountNumberOfTerms1395 {
	
	/*
	//<Trial01 - Memory limit exceeded>
	
	//brute-force
	
	//정방향 역방향으로 도는건데 너무 날로먹으려고했나
	
	public int numTeams(int[] rating) {
        List<List<Integer>> list = new ArrayList<>();
        int rst = 0;
        
        for(int i = 0; i < rating.length; i++){
            int size = list.size();
            
            while(size > 0){
                List<Integer> temp = list.get(--size);
                if(rating[i] > temp.get(temp.size()-1)){
                    List<Integer> copy = new ArrayList<>(temp);
                    list.add(copy);
                    temp.add(rating[i]);
                }
            }
            List<Integer> tmp = new ArrayList<>();
            tmp.add(rating[i]);
            list.add(tmp);
        }
    
        for(List<Integer> temp: list){
            if(temp.size() == 3){
                rst++;
            }
        }
    
        list.clear();
    
        for(int i = rating.length-1; i >= 0; i--){
            int size = list.size();
            
            while(size > 0){
                List<Integer> temp = list.get(--size);
                if(rating[i] > temp.get(temp.size()-1)){
                    List<Integer> copy = new ArrayList<>(temp);
                    list.add(copy);
                    temp.add(rating[i]);
                }
            }
            List<Integer> tmp = new ArrayList<>();
            tmp.add(rating[i]);
            list.add(tmp);
        }
        
    
        for(List<Integer> temp : list){
            if(temp.size() == 3){
                rst++;
            }
        }

        return rst;
    }
    */
	
	/*
	//<Trial02>
	
    //1,4,3,5,6
    
    //0,1,1,3,4
    
    //1,4,5
    //1,4,6
    //1,3,5
    //1,3,6
    //1,5,6
    //4,5,6
    //3,5,6
	
	//ㅏㅏㅏㅏㅏ몰것다
	
    public int numTeams(int[] rating) {
        int[] container1 = new int[rating.length+1];
        int[] container2 = new int[rating.length+1];
        int rst = 0;
        
        for(int i = 0; i < rating.length; i++){
            int low = 0;
            for(int j = 0; j < rating[i]; j++){
                if(container1[j] > 0){
                    low++;
                }
            }
            if(low == 2){
                rst++;
            }
            container1[rating[i]]++;
        }
        for(int i = rating.length-1; i >= 0; i--){
            int low = 0;
            for(int j = 0; j < rating[i]; j++){
                if(container2[j] > 0){
                    low++;
                }
            }
            if(low == 2){
                rst++;
            }
            container2[rating[i]]++;
        }
        
        return rst;
    }
    */
	
	//<ZLBer>
	
	//양방향 한번에 검사하네 똑똑허이 친구
	
	//Runtime: 2 ms, faster than 98.82% of Java online submissions for Count Number of Teams.
	//Memory Usage: 37.4 MB, less than 100.00% of Java online submissions for Count Number of Teams.
	public int numTeams(int[] rating) {
        int[] smallLeft = new int[rating.length];
        int[] largeLeft = new int[rating.length];
        int[] smallRight = new int[rating.length];
        int[] largeRight = new int[rating.length];
        for (int i = 1; i < rating.length-1; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (rating[j] < rating[i]) {
                    smallLeft[i]++;
                }
                if (rating[j] > rating[i]) {
                    largeLeft[i]++;
                }
            }
            for (int j = i + 1; j < rating.length; j++) {
                if (rating[j] < rating[i]) {
                    smallRight[i]++;
                }
                if (rating[j] > rating[i]) {
                    largeRight[i]++;
                }
            }
        }

        int res = 0;
        for (int i = 1; i < rating.length - 1; i++) {
            res += smallLeft[i] * largeRight[i] + largeLeft[i] * smallRight[i];
        }
        return res;
    }

}
