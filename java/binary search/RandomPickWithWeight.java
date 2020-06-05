package juneChallenge;

public class RandomPickWithWeight {

	//<Trial01 - memory limit exceeded>
	
	//문제 설명이 뭐같아서 일단 문제설명 링크 참조
	
    //question explained
    
    //https://leetcode.com/problems/random-pick-with-weight/discuss/671445/Question-explained
	
	//w[i]/total 이 확률이라 그랬으니까,
	
	//total 크기만큼의 int[]를 만들고, i의 weight인 w[i]만큼 i를 우겨넣은 다음에
	
	//랜덤으로 0에서 total-1까지의 숫자를 Math.random()함수로 뽑아서, 그걸 인덱스 삼아 값을 찾으려고 했으나,
	
	//역시 인풋이 18만개 들어가면 못버팀
    
    int[] a;
    int total = 0;
    
    public Solution(int[] w) {
        for(int w_ : w){
            total += w_;
        }
        a = new int[total]; 
        
        for(int i = 0, j = 0, accum = 0; i < w.length; i++){
            while(j < total && j < (w[i]+accum)){
                a[j] = i;
                j++;
            }
            accum = j;
        }
    }
    
    public int pickIndex() {
        double dValue = Math.random();
	    int iValue = (int)(dValue * total);
        return a[iValue];
    }
    
    
    //<문제풀이1>
    
    //binary search
    
    //trial01에서 total구하고 w[i]/total의 확률을 쓰는것과 
    
    //double dValue = Math.random();이랑 int iValue = (int)(dValue * total); 쓰는건 맞다고 생각해서 킵함
    
    //int[]는 사이즈가 너무 커져서 버림
    
    //대신 total크기의 int[]가 아니라, w랑 같은 사이즈의 int[]인데 중요도를 누적으로 쌓는 int[]를 만듬
    
    //예를들어, 인풋이 [1,3,1]이라면,
    
    //int[] a는 [1,4,5]가 되는거임. 중요도를 누적으로 쌓자너
    
    //일단 Math.random()으로 0부터 4(5-1)까지의 랜덤한 iValue가 구해지면, 
    
    //binary search로 [1,4,5]에서 찾음.
    
    //iValue가 3이라고 해보자.
    
    //3을 [1,4,5]에서 찾는데, a[m]은 4지? 근데 4는 3보단 크니까 일단 한칸 내림
    
    //그럼 1이지? 그럼 3이 0~1에 무조건 포함이 안되니까 한칸 올림
    
    //그럼 l = 1이되고, r은 1인상태니까, l<r이 성립이 안되서 while문을 나오고 l을 반환하면, 우리가 찾는 1이 나옴.
    
    //Runtime: 22 ms
    //Memory Usage: 43.4 MB
    
    int total = 0;
    int[] a;
    
    public Solution(int[] w) {
        a = new int[w.length];
        
        for(int w_ : w){
            total += w_;
        }
        
        for(int i = 0, j = 0; i < w.length; i++){
            a[i] = w[i] + j;
            j = a[i];
        }
    }
    
    public int pickIndex() {
        double dValue = Math.random();
	    int iValue = (int)(dValue * total);
        int l = 0, r = a.length;
        while(l<r){
            int m = (l+r)/2;
            if(a[m] > iValue){
                r = m;
            } else {
                l = m+1;
            }
        }
        return l;
    }
}
