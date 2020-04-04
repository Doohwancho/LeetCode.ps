package thirtyDayChallenge;

public class MoveZeroes {
	
	
	/*
	//<문제풀이1>
	
    //[0,1,0,0,2,3,4,5] 이걸
    
    //[1,2,3,4,5,0,0,0] 이렇게 만들어야 되잖어?
	
	//먼저 if(i < nums.length-totalZeros) 을 한 이유는, 인덱스 0부터 전체 arr길이-총 0의 갯수까지 숫자가 쭈루룩 나오고(위 경우엔 8 - 3 = 5이후) 
	
	//그 뒤엔 0만 나오니까, else에 0을 박은거
	
	//0을 마주쳤잖어? 그 후에 붙어있는 0들이 쪼로록 있으면 걔들 그룹으로 묶은 다음에, 0들이후에 등장하는 숫자형님이랑 자리를 바꿔치기 혀
	
	//그럼 [0,1,0,0,2,3,4,5] 이걸 한번 바꿔치기 하면 0이랑 1이랑 바꿔치기 하니까
	
	//[1,0,0,0,2,3,4,5] 이 되겠네? 그럼 0이 3개가 쪼로록 뭉쳐있네? 그럼 0 이후에 등장하는 2,3,4 형님들 이랑 바꿔치기 혀
	
	//[1,2,3,4,0,0,0,5]가 되겠네? 3,4는 건너뛰고 다음 0에서 5랑 바꾸면 끝
	
	//이후엔 nums.length-totalZeros(3)조건에 안맞으니까 어짜피 다 0으로 채울거
	
	//아따 개같은 설명 이거
	
	//21 / 21 test cases passed.
	//Status: Accepted
	//Runtime: 1 ms
	//Memory Usage: 39.9 MB
	public void moveZeroes(int[] nums) {
        for(int i = 0, zero = 0, totalZeros = 0; i < nums.length; i++){
            if(i < nums.length-totalZeros){
                if(nums[i] == 0){
                    int j = i;
                    while(j < nums.length && nums[j] == 0){
                        zero++;
                        j++;
                    }
                    if(zero > totalZeros){
                        totalZeros = zero;
                    }
                    int i_ = i;
                    while(j < nums.length && zero > 0){
                        nums[i_] = nums[j];
                        nums[j] = 0;
                        i_++;
                        j++;
                        zero--;
                    }
                }
            } else {
                nums[i] = 0;
            }
        }
    }
    */
	
	
	//<문제풀이 2 by caikeke>
	
	//아따 아름답게 짜시네
	
	//0이 아닌 숫자면 zero == l이니까 nums[zero] = nums[l]은 걍 지 스스로 줬다뺏는거
	
	//근데 0이 늘어나는 순간 l만 +1되고 0은 제자리니까, 다음에 l과 0이랑 바꿔치기 할 때, 이제는 자리가 바뀌는겨
	
	public void moveZeroes(int[] nums) {
	    int zero = 0, l = 0, r = nums.length;
	    while (l < r) {
	        if (nums[l] != 0) {
	            int tmp = nums[zero];
	            nums[zero++] = nums[l];
	            nums[l] = tmp;
	        }
	        l++;
	    }
	}
}
