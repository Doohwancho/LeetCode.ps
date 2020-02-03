package array;

public class GrumpyBookstoreOwner1052 {
	
	/*
	//<문제풀이1>
	
	//step01 - customers의 누적합을 순차적으로 container라는 리스트에 넣는다. ex) {1,2,3,4,5} -> container = {1,3,6,10,15}
	//container에 i번째의 수는 1부터 i번째 수까지의 합이다.   ex) i == 4 && X == 3, 1~i의 합은  container[i] == 15
	//여기서 i-X번째까지의 합을 빼면,  ex) i-X인 1을 빼주면, container[i] - container[i-X] == container[4] - container[1] == 15 - 3 == 12
	
	//step02 - grumpy가 0일때의 customers수의 총 합을 구한다.(==total)
	
	//step03 - total에서 step01에서 구했던 누적합을 더한것의 가장 큰 값을 Math.max()로 구해주는데, 이 때, grumpy가 0일때의 customer[i]값은 이미 total에 더해졌으므로, 그 부분은 step01에서 구한 값에서 빼준다.
	
	//Runtime: 456 ms, faster than 8.86% of Java online submissions for Grumpy Bookstore Owner.
	//Memory Usage: 42.9 MB, less than 100.00% of Java online submissions for Grumpy Bookstore Owner.
	
	public static int maxSatisfied(int[] customers, int[] grumpy, int X) {
		//step01
		int[] container = new int[customers.length];
        
        for(int i = 0, sum = 0; i < customers.length; i++){
            container[i] = customers[i];
            int tmp = container[i];
            container[i] += sum;
            sum += tmp;
        }
         
        //step02
        int total = 0;
        int rst = 0;
        
        for(int i = 0; i < customers.length; i++){
            if(grumpy[i] == 0){
                total += customers[i];
            }
        }
        
        //step03
        for(int j = 0; j < container.length; j++){
            if(j >= (X-1)){
                int j_ = j;
                if(j >= X){
                    int num = container[j]-container[j-X];
                    while(j_ > j-X){
                        if(grumpy[j_] == 0){
                            num -= customers[j_];
                        }
                        j_--;
                    }
                    rst = Math.max(rst, total + num);
                    
                } else {
                    int num = container[j];
                    while(j_ >= 0){
                        if(grumpy[j_] == 0){
                            num -= customers[j_];
                        }
                        j_--;
                    }
                    rst = Math.max(rst, total + num);
                }
            }
        }
        
        return rst;
    }
	*/
	
	//<문제풀이2 by rock>
	
	//이친구는 내가 456ms걸리는거 3ms에 푸네
	
	//현타 5지9요
	
	//for문 한번으로 원큐에 해결해버리네
	
	//grumpy가 0인건 모두 던하고,
	
	//1이라저 제외되는것도 win이라는 변수에 계속 더하다가, X번 이상 더하면, 매 loop마다 더한 grumpy1의 가장 왼쪽값을 제거해 주는 방법.
	
	//그 win들중 최댓값을 구해 grumpy0의 값과 더하면 끝.
	
	//Runtime: 3 ms, faster than 88.28% of Java online submissions for Grumpy Bookstore Owner.
	//Memory Usage: 43.3 MB, less than 100.00% of Java online submissions for Grumpy Bookstore Owner.
	
    public static int maxSatisfied(int[] customers, int[] grumpy, int X) {
       int satisfied = 0, maxMakeSatisfied = 0;
       for (int i = 0, win = 0; i < grumpy.length; ++i) {
           if (grumpy[i] == 0) { satisfied += customers[i]; }
           else { win += customers[i]; }
           if (i >= X) { // window's size > X.
               win -= grumpy[i - X] * customers[i - X]; // to maintain the size as X, remove unsastified customers from left end of the window.
           }
           maxMakeSatisfied = Math.max(win, maxMakeSatisfied);
       }
       return satisfied + maxMakeSatisfied;
   }
	
	public static void main(String[] args) {
		int[] customers = new int[] {1,0,1,2,1,1,7,5};
		int[] grumpy = new int[] {0,1,0,1,0,1,0,1};
		int X = 3;
		System.out.println(maxSatisfied(customers, grumpy, X));
	}
}
