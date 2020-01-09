package Array;

import java.util.LinkedList;
import java.util.Queue;

public class CapacityToShipPackagesWithinDDays1011 {
	/*
	//<Trial01>
	
	//일단 5개 쌓아놓고, 하나씩 오른쪽에서 들어올 때마다, 한칸씩 미는 방법을 생각했는데
	
	//생각해보니 리스트 길이가 졸라길면
	
	//한칸밀고 총합 구해서 안민거의 총합과 비교하고를 리스트의길이-1번 해야하니까
	
	//time limit exceeded뜰것 같음
	
	private int shift(int[] container, Queue<Integer> q, int adder){
        int[] copy = new int[container.length];
        System.arraycopy(container, 0, copy, 0, container.length);
        //생각해보니 전체 shift가 아니잖아?
    }
    
    private int calcMax(int[] container, int adder){
        container[0] += adder;
        int maxNum = 0;
        for(int i : container){
            maxNum = Math.max(maxNum, i);
        }
        return maxNum;
    }
    
    public int shipWithinDays(int[] weights, int D) {
        int[] container = new int[D];
        Queue<Integer> q = new LinkedList<>();
        
        for(int i = weights.length-1, d = D-1; i >=0; i--, d--){
            q.add(weights[i]);
            if(d>=0){
                container[d] = weights[i];  
            }
            else {
                //현재(container[0]+= weights[i]) max
                int comp1 = calcMax(container, weights[i]);
                
                //한칸 옮긴 후 max
//                int comp2 = ?
                //if 현재 max >= 옮긴 max, 옮김 !!!문제점 : 전체 shift가 아님.
//                if(comp1 > comp2){
                    //옮김
                }
                //else container[0]+= weights[i]
//                else {
//                    container[0]+= weights[i];
//                    q.add(weights);
//                }
                
            }
            
            
        }
        int rst = 0;
        // for(int k : container) Math.max(rst, k);
        // System.out.println();
        
        return rst;
    }
    
	*/
	/*
	//<문제풀이 by lee215>
	  
	//binary search 
	
//	Explanation of this solution:
//
//	The key is left = max(weights), right = sum(weights),
//	which are the minimum and maximum possible weight capacity of the ship.
//
//	Therefore the question becomes Binary Search to find the minimum weight capacity of the ship between left and right.
//	We start from
//	mid = (left + right) / 2 as our current weight capacity of the ship.
//	need = days needed == 1
//	cur = current cargo in the ship == 0
//
//	Start put cargo into ship in order.
//	when need > D, it means the current ship is too small, we modify left = mid + 1 and continue
//	If all the cargo is successfully put into ships, we might have a chance to find a smaller ship, so let right = mid and continue.
//	Finally, when our left == right, we reach our answer
	
	//그는 천재인가
	
	//Runtime: 12 ms, faster than 40.31% of Java online submissions for Capacity To Ship Packages Within D Days.
	//Memory Usage: 44 MB, less than 38.46% of Java online submissions for Capacity To Ship Packages Within D Days.
    public int shipWithinDays(int[] weights, int D) {
        int left = 0, right = 0;
        for (int w: weights) {
            left = Math.max(left, w);
            right += w;
        }
        while (left < right) {
            int mid = (left + right) / 2, need = 1, cur = 0;
            for (int w: weights) {
                if (cur + w > mid) {
                    need += 1;
                    cur = 0;
                }
                cur += w;
            }
            if (need > D) left = mid + 1;
            else right = mid;
        }
        return left;
    }
    */
    
    //<문제풀이2 by Sithis>
    
    //같은 로직(binary search), 성능개선
    
    //Runtime: 8 ms, faster than 75.93% of Java online submissions for Capacity To Ship Packages Within D Days.
    //Memory Usage: 42.8 MB, less than 69.23% of Java online submissions for Capacity To Ship Packages Within D Days.
    
    
    public int shipWithinDays(int[] weights, int d) {
        int lo = getMax(weights);
        int hi = getSum(weights);
        while (lo < hi) {
            int capacity = (lo + hi) >>> 1; // avoid overflow. same as (lo + hi) / 2
            int requiredDays = getRequiredDays(weights, capacity);

            if (requiredDays > d) {
                lo = capacity + 1;
            } else {
                hi = capacity;
            }
        }
        return lo;
    }

    private int getRequiredDays(int[] weights, int maxCapacity) {
        int requiredDays = 1;
        int capacity = 0;
        for (int weight : weights) {
            capacity += weight;
            if (capacity > maxCapacity) {
                requiredDays++;
                capacity = weight;
            }
        }
        return requiredDays;
    }

    private int getSum(int[] arr) {
        int sum = 0;
        for (int val : arr) {
            sum += val;
        }
        return sum;
    }

    private int getMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int val : arr) {
            max = Math.max(max, val);
        }
        return max;
    }
}
