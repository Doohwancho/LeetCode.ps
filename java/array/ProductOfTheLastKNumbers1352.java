package array;

import java.util.ArrayList;
import java.util.List;

public class ProductOfTheLastKNumbers1352 {
	
	/*
	//<Trial01 - time limit exceeded>
	
	//brute-force
	
	class ProductOfNumbers {
	
	    List<Integer> list;
	    
	    public ProductOfNumbers() {
	        list = new ArrayList<>();
	    }
	    
	    public void add(int num) {
	        list.add(num);
	    }
	    
	    public int getProduct(int k) {
	        int rst = 1;
	        for(int i = list.size()-1; i >= (list.size()-k); i--){
	            rst *= list.get(i);
	        }
	        return rst;
	    }
	}
    */
	
	
	//<문제풀이1>
	
	//trial01로 하니까 testcase가 미친놈처럼 많이들어가면 time limit이 뜨더라고. 
	
	//getProduct()함수가 시간을 많이 잡아먹잖아.
	
	//그래서 어떻게 꼼수를 부릴까 고민하다가
	
	//input이 예를들어 1,2,3,4면, 누적곱을 int[]에 미리 담아놓는거야.
	
	//1,2,6,24
	
	//그리고 뒤에서부터 k번만큼의 곱을 구하라고 하면, k가 2라고 하자.
	
	//그럼 24/2 == a[i]/a[i-k] == 12 == 3*4 잖아
	
	//가장 뼈대가 되는 개념은 이거고
	
	//근데 만약에 중간에 0이 꼽싸리 껴있을 수도 있잖아.
	
	//1,2,3,4,0,5,6이면,
	
	//1,2,6,24,0,0,0 이될거아냐. 0은 뭘곱해도 0이니까, 누적곱하면 클나지.
	
	//그러면 0이 나온 자리빼고, 그후엔 리셋해서 다시 곱해주는거야.
	
	//1,2,6,24,0,5,30 이렇게.
	
	//이때, getProduct()했는데 k가 4라고 쳐봐?
	
	//근데 마지막으로 0이나온 index가 4잖아.
	
	//list.size()-k 안에 있는 숫자를 다 곱해서 리턴해야 하는데, 그 사이에 0이 껴있다는거아니야.
	
	//그래서 if(lastZero > (i-1-k) ) return 0;
    
	//0이 범위 안에 걸리면 걍 0리턴해주는거지.
	
	//add할때마다 0을 더해주면 마지막 0의 인덱스를 업데이트 해주면서.
	
	//int[]사이즈를 40000으로 한 이유는, 문제에서 iterate를 최대 4만번한다 그래서 그랫엉.
	
	//Runtime: 16 ms, faster than 39.00% of Java online submissions for Product of the Last K Numbers.
	//Memory Usage: 61 MB, less than 100.00% of Java online submissions for Product of the Last K Numbers.
	
	class ProductOfNumbers {
	    
	    int[] a;
	    int i = 0;
	    int lastZero = -1;
	    
	    public ProductOfNumbers() {
	        a = new int[40000];
	    }
	    
	    public void add(int num) {
	        if(num == 0){
	            lastZero = i;
	        } else {
	            if(i == 0 || a[i-1] == 0){
	                a[i] = num;
	            } else {
	                a[i] = a[i-1] * num;
	            }
	        }
	        i++;
	    }
	    
	    public int getProduct(int k) {
	        int cnt = 0;
	        if(lastZero > (i-1-k) ){
	            return 0;
	        } else {
	            if(i-k == 0 || a[i-1-k] == 0){
	                return a[i-1];
	            } else {
	                return a[i-1]/a[i-1-k];
	            }
	            
	        }
	    }
	}

	
	//<문제풀이2 by lee215>
	
	//이게 훨씬 더 간단하고 직관적이다 야.
	
	//0이나오면 리스트를 아예 리셋해버리네.
	
	//Runtime: 14 ms, faster than 97.60% of Java online submissions for Product of the Last K Numbers.
	//Memory Usage: 63.8 MB, less than 100.00% of Java online submissions for Product of the Last K Numbers.
	
	class ProductOfNumbers {
	    
	    ArrayList<Integer> A;
	    public ProductOfNumbers() {
	        add(0);
	    }

	    public void add(int a) {
	        if (a > 0)
	            A.add(A.get(A.size() - 1) * a);
	        else {
	            A = new ArrayList();
	            A.add(1);
	        }
	    }

	    public int getProduct(int k) {
	        int n = A.size();
	        return k < n ? A.get(n - 1) / A.get(n - k - 1)  : 0;
	    }
	}
}
