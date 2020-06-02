package array;

public class MakeTwoArraysEqualByReversingSubArr1460 {
	
	//<문제풀이1>
	
	//문제에선 순서를 뒤집니 어쩌니 하면서 훼이크 주고있는데
	
	//다 필요없고 target이랑 arr이랑 같은 숫자들이 같은 빈도수로 나왔는지만 비교하면 됨.
	
	//왜냐면 같은 숫자들이 똑같은 빈도수로 뒤섞여 있어도, 
	
	//서로 자리 바꾸기를 노가다로 겁~~~나 많이 하면 언젠간 똑같이 만들 수 있자너
	
	//문제에서 숫자가 1부터 1000까지 나온다그랬으니까, 
	
	//int[] 사이즈를 1001로 만들어서 빈도수를 비교하면 됨.
	
	//Runtime: 1 ms, faster than 98.76% of Java online submissions for Make Two Arrays Equal by Reversing Sub-arrays.
	//Memory Usage: 39.5 MB, less than 100.00% of Java online submissions for Make Two Arrays Equal by Reversing Sub-arrays.
	
    public boolean canBeEqual(int[] target, int[] arr) {
        int[] c = new int[1001];
        for(int a : arr) c[a]++;
        for(int t : target){
            if(c[t] == 0){
                return false;
            }
            c[t]--;
        } 
        return true;
    }
}
