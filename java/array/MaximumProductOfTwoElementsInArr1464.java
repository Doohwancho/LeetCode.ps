package array;

public class MaximumProductOfTwoElementsInArr1464 {
	//<문제풀이1>
	
	//젤 큰수 두개를 구해야 하니까, 변수 a,b선언해주고
	
	//a가 첫번째로 큰 수, b가 두번째로 큰수라고 가정할 때,
	
	//nums를 iterate하면서 나오는 숫자들이, 일단 두번째 수보다 같거나 큰지 확인.
	
	//같거나 크다면, 다음으로 가장 큰 수 a보다 큰지 확인.
	
	//a보다 크면, a를 b로 옮긴 후, a를 젤 큰수로 업데이트 시키고,
	
	//a보다 작다면, b만 업데이트 해줌.
	
	//nums에 나오는 모든 숫자들이 양수들이라, 문제가 복잡해지지 않았음. 만약 음수가 나왔다면,
	
	//음수끼리 곱하는거랑 양수끼리 곱하는거랑로 고려해야 했었을텐데.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Maximum Product of Two Elements in an Array.
	//Memory Usage: 39.2 MB, less than 100.00% of Java online submissions for Maximum Product of Two Elements in an Array.
	
    public int maxProduct(int[] nums) {
        int a = 0;
        int b = 0;
        for(int n : nums){
            if(n >= b){
                if(n >= a){
                    b = a;
                    a = n;
                } else {
                    b = n;
                }
            }

        }
        return (a-1)*(b-1);
    }
}
