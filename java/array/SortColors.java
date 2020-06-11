package juneChallenge;

public class SortColors {
	
	//<문제풀이1>
	
	//앞에 애가 뒤에애보다 더 크면, 둘을 swap해주고, cnt+1
	
	//cnt가 한번도 안찍힐때까지 반복
	
	//좀더 섹시한 솔루션이 있을텐디..
	
	//문제에서도 언급된건, int[]나 map같은거에 무슨 숫자가 몇번의 빈도수로 나왔는지 때려박고, 그걸 다시 nums에 순서대로 넣으라는건데, 이건 간지가 안남
	
	//그리고 모두 swap이 완료되어도, 결점이 없다는걸 확인하기 위해 for문을 한번 더 돔. 비효율.
	
	//Runtime: 2 ms
	///Memory Usage: 37.8 MB
    public void sortColors(int[] nums) {
        boolean flag = true;
        while(flag){
            int cnt = 0;
            for(int a = 0, b = 1; b < nums.length; a++, b++){
                if(nums[a] > nums[b]){
                    int tmp = nums[a];
                    nums[a] = nums[b];
                    nums[b] = tmp;
                    cnt++;
                } 
            }
            if(cnt == 0){
                flag = false;
            }
        }
    }
    
    
    
    //<문제풀이2 by chase1991>
    
    //이건 색이 세가지만 있을때'만' 유효한 답이지만 섹시하다고 쳐주지.
    
    //큰 구조부터 보면, 일단
    
    //index 0의 zero, 마지막 인덱스의 two, 그리고 그 사이(0부터 nums.length)까지를 오갈 count가 있고
    
    //while문이 2개 있어.
    
    //안쪽 while문을 보면, 조건이 두개가 있는데,
    
    //일단 count <= two은 array out of bound error를 피하기 위해서 있는거고
    
    //count >= zero는 count가 zero에 뒤쳐졌을때 while문을 나오기 위해서 쓰여.
    
    //안쪽 while문을 나오면 count++;을 해서 count+1을 해주거든. 그래서 count는 항상 zero인덱스보다 큰 상태이지.
    
    //그상태에서 count의 차리에 0이 있으면, 앞쪽에 0이랑 바꿔주고, 2가 있으면 맨 끝쪽에 2랑 바꿔주는거야. 
    
    //1이 나오면 어짜피 중간에 있어야 하는애가 1이니까 안바꾸고 count를 계속 +1씩 해가면서 그냥 넘기고. 
    
    //Runtime: 0 ms
    //Memory Usage: 38 MB
    
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int zero = 0, count = 0, two = nums.length - 1;
        while (count <= two && count >= zero) {
            while (count <= two && count >= zero) {
                if (nums[count] == 0) {
                    swap(nums, count, zero);
                    zero++;
                }
                if (nums[count] == 2) {
                    swap(nums, count, two);
                    two--;
                }
                if (nums[count] == 1) break;
            }
            count++;
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    
    //<문제풀이3 by Cheng_Zhang, editted by EdickCoding>
    
    //얘가 가독성이 가장 좋으면서 성능도 잡은 답이 아닐까
    
    //for로 원큐로 돌면서 0이면 left(starting from index 0)랑 swap하고, left+1,
    
    //2면 right(starting from the end of the array)랑 swap하고, right-1
    
    //근데 swap시 i-1을 해줌으로써, swap 후에 바뀐 i를 다시한번 확인함.
    
    //그래서 문제풀이 1처럼 여러번 array를 iterate할 필요없이, 해당 자리에 숫자가 완벽해질때까지 고 자리만 멤멤돌면서 swap해주는거라
    
    //훨씬 더 시간을 아낄 수 있음.
    
    //아 왜 이생각을 못했지 충분히 할만한거였는데
    
    //Runtime: 0 ms
    //Memory Usage: 37.9 MB
    
    public void sortColors(int[] nums) {
        int l = 0, r = nums.length - 1;
        for (int i = 0; i <= r; i++) {
            if (nums[i] == 0) {
                swap(nums, i, l++);
            } else if (nums[i] == 2) {
                swap(nums, i--, r--);
            }
        }
    }
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
