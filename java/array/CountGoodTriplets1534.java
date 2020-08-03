package array;

public class CountGoodTriplets1534 {
	
	//<Trial01>
	
	//오름차순 정렬 할 필요가 없네?
	
    // [7,3,7,3,12,1,12,2,3]
    // 5
    // 8
    // 1
    //[1,2,3,3,3,7,7,12,12]
	
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int rst = 0;
        
        Arrays.sort(arr);
        
        for(int i = 0; i < arr.length-2; i++){
            for(int j = i+1; j < arr.length-1; j++){
                for(int k = j+1; k < arr.length; k++){
                    if(Math.abs(arr[i] - arr[j]) <= a && Math.abs(arr[j] - arr[k]) <= b && Math.abs(arr[i] - arr[k]) <= c) rst++;        
                }
            }
        }
        
        return rst;
    }
    
    
    
    //<Trial02>
    
    //생각해보니까 a,b,c가 유동적인게 아니라 i,j,k이랑 a,b,c딱딱 정해져있네?
    
    
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int rst = 0;
        int largest_c = Math.max(a,Math.max(b,c));
        int second_b = a == largest_c ? Math.max(b,c) : b == largest_c ? Math.max(a,c) : Math.max(a,b);
        int first_a = a+b+c - (largest_c + second_b);
        
        Arrays.sort(arr);
        for(int i = 0; i < arr.length; i++){
            int l = 0, r = arr.length-1, at = -1;
            while(l <= r){
                int m = (l+r)/2;
                if(arr[m]-arr[i] == largest_c){
                    at = m;
                    break;
                } else if(arr[m]-arr[i] < largest_c){
                    l = m+1;
                } else {
                    r = m-1;
                }
            }
            if(at != -1){
                for(int j = i+1; j < at; j++){
                    if(Math.abs(arr[j]-arr[i]) <= first_a && Math.abs(arr[at]-arr[j]) <= second_b) rst++;
                }
            } else{
                if(l == arr.length) l--;
                for(int j = i+1; j < l; j++){
                    if(Math.abs(arr[j]-arr[i]) <= first_a && Math.abs(arr[l]-arr[j]) <= second_b) rst++;
                }
            }
        }
        
        return rst;
    }
    
    
    //<문제풀이1>
    
    //쉬운걸 어렵게 꼬아서 생각했네;;
    
    //Runtime: 14 ms, faster than 80.00% of Java online submissions for Count Good Triplets.
    //Memory Usage: 37.3 MB, less than 100.00% of Java online submissions for Count Good Triplets.
    
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int rst = 0;
        for(int i = 0; i < arr.length-2; i++){
            for(int j = i+1; j < arr.length-1; j++){
                for(int k = j+1; k < arr.length; k++){
                    if(Math.abs(arr[i] - arr[j]) <= a && Math.abs(arr[j] - arr[k]) <= b && Math.abs(arr[i] - arr[k]) <= c) rst++;        
                }
            }
        }
        
        return rst;
    }
    
    //<문제풀이2 by foobarfoo>
    
    //성능이 구리지만 깔끔해서 마음에 드는 버전
    
    //Runtime: 33 ms, faster than 20.00% of Java online submissions for Count Good Triplets.
    //Memory Usage: 39.5 MB, less than 100.00% of Java online submissions for Count Good Triplets.
    
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int rst = 0;
    
        for(int i = 0; i < arr.length-2; i++){
            for(int j = i+1; j < arr.length-1; j++){
                for(int k = j+1; k < arr.length; k++){
                    if(isGood(new int[]{arr[i],arr[j],arr[k]}, a,b,c)) rst++;        
                }
            }
        }
        
        return rst;
    }
    
    private boolean isGood(int[] arr, int a, int b, int c){
        if(Math.abs(arr[0]-arr[1]) <= a &&
           Math.abs(arr[1]-arr[2]) <= b &&
           Math.abs(arr[0]-arr[2]) <= c) return true;
        return false;
    }
    
    
    //<문제풀이3 by SleepyFarmer>
    
    //어씨 모르겠다 개어렵네
    
    //Runtime: 4 ms, faster than 100.00% of Java online submissions for Count Good Triplets.
    //Memory Usage: 38.9 MB, less than 100.00% of Java online submissions for Count Good Triplets.
    
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int n = arr.length;
        int ans = 0;
        int[] pre = new int[1001];
        int[] post = new int[1001];  // stores suffix sum
        pre[arr[0]] = 1;
        for(int i = n-1; i > 1; i--) {
            post[arr[i]]++;            //arr[i]에+1이 되면
        }
        for(int i = 1; i <= 1000; i++) {
            post[i] = post[i-1] + post[i]; //그 다음것들이 누적으로 +1됨
        }
        
        for(int j = 1; j < n-1; j++) {
            int v = arr[j]; //가운데 숫자
            int p1 = Math.max(0, v-a);   //가운데 숫자에서 +-a 범위 p1~p2
            int p2 = Math.min(1000, v+a);
            int t1 = Math.max(0, v-b); //가운데 숫자에서 +-b 범위 t1~t2
            int t2 = Math.min(1000, v+b);
            
            for(int s = p1; s <= p2; s++) { //s는 가운데 숫자에서 +-a 범위 p1~p2
                if (pre[s] == 0) continue; //? - pre[s]를 한번도 지나쳐 오지 않았으면 일단 패스 
                int m1 = Math.max(t1, s-c); //+-b범위와 +-c범위에서 겹치는 부분 m1~m2
                int m2 = Math.min(t2, s+c);
                if (m2 >= m1) { //이곳이 중간 element기준으로 a,b,c다 겹치는 sweetspot
                    if (m1 == 0) { //m1이 0이라는 말은 b범위랑 c범위에 왼쪽부분의 끝이 index0라는 뜻
                        ans += pre[s] * post[m2]; //어짜피 0부터 m2까지니까 post[m2]만큼 곱함. post는 하나 올라갈때마다 +1이었고, 중간에 arr[i]가 껴있으면 +1이 더 누적되는 방식이었음. pre[s]를 곱하는 이유는, 얘가 나온만큼 [(3,0,1), (3,0,1),...] 같이 반복되는 애들만큼 곱해주는 것 
                    } else {
                        ans += pre[s] * (post[m2] - post[m1-1]); //얘는 0부터가 아니라 b~c의 범위중 중간애 기준으로 더 짧은 유효거리 안에 있는애들의 총 합이니까, m2 - (m1-1)해줌
                    }
                }
            }
            pre[v]++; //지나간 숫자+1
            for(int i = arr[j+1]; i <= 1000; i++) {
                post[i]--; //왜하는거지 이건
            }
        }
        return ans;
    }
}
