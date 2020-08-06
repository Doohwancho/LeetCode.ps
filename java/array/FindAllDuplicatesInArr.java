package augustChallenge;

public class FindAllDuplicatesInArr {
	
	//<문제풀이1>
	
	//어씨 홍대병 걸렸나 쉬운걸 가지고 왜 bitmask부터 생각하고있지
	
	//Runtime: 7 ms
	//Memory Usage: 63.8 MB
	
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> rst = new ArrayList<>();
        int[] a = new int[nums.length+1];
        for(int n : nums){
            a[n]++;
            if(a[n] > 1){
                rst.add(n);
            }
        }
        return rst;
    }
    
    
    //<문제풀이2 by YuxinCao>
    
    //어씨 저번에 풀었었는데 또 놓혔네
    
    //https://www.youtube.com/watch?v=aMsSF1Il3IY&feature=youtu.be
    
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> rst = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            int idx = Math.abs(nums[i])-1; //또나오는 애는 마이너스 값이니까 Math.abs()
            
            if(nums[idx] < 0){ //idx가 3 한번 나오면 -로 바꾸겠지. 그러면  두번째 나왔을땐 마이너스 값일테니까
                rst.add(idx+1);  //idx+1을 더해줌. 원래 nums[i]가 idx+1이니까.
            }
            
            nums[idx] = -nums[idx];
        }
        return rst;
    }
    
   
}
