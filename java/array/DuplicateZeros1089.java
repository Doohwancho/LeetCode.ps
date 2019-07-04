/*
	Given a fixed length array arr of integers, duplicate each occurrence of zero, shifting the remaining elements to the right.
	
	Note that elements beyond the length of the original array are not written.
	
	Do the above modifications to the input array in place, do not return anything from your function.
	
	 
	
	Example 1:
	
	Input: [1,0,2,3,0,4,5,0]
	Output: null
	Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
	Example 2:
	
	Input: [1,2,3]
	Output: null
	Explanation: After calling your function, the input array is modified to: [1,2,3]
	
	
	
	<문제>
	
	리스트가 [1,0,2,3,0,4,5,0] 이렇게 주어지면, 0을 두개로 복사시켜서 [1,0,0,2,3,0,0,4]로 만들어라.
	
	단, 처음 리스트의 길이와 같아야 하기 때문에, 짤리는채로 두고, in-place 방식으로 원본만 수정하라.
	
	
	
	
	<문제풀이>
	
	two-pointer방식을 사용.
	
	먼저 길이 제한 없이, 모든 0을 두번씩 복사했을때 길이를 구하기 위해, arr에 나타난 0의 갯수+arr의 길이를 합해준다.
	
	for문을 사용, i는 arr의 길이-1, j는 모든0을 두번씩 복사했을때 길이-1로 해서, 마지막 인덱스부터 0번째 인덱스까지 순차적으로 적용
	
	i번째의 arr값이 0이 아니고 j가 arr의 길이 안에 있다면, i의 값이 j의 값이 되고,
	
	0이면, i의 값을 j로 넣어주고, j를 한칸 앞으로 당긴 후, 다시 i의 값을 j로 넣어줌(0를 복사함)
 * 
 */


package Array;

public class DuplicateZeros1089 {
	
	public static void duplicateZeros(int[] arr) 
	{		
		int cntZero = 0;
		
		for(int i = 0; i < arr.length; i++)
		{
			if(arr[i]==0) cntZero++;
		}
		
		int len = arr.length + cntZero;
		
		for(int i = arr.length-1, j = len-1; i >= 0 && j >= 0; i--, j--)
		{
			if(arr[i]!=0)
			{
				if(j < arr.length) arr[j]=arr[i];
			}
			else
			{
				if(j < arr.length) arr[j]=arr[i];
				j--;
				if(j < arr.length) arr[j]=arr[i];
			}
		}
	}
	public static void main(String[] args) {
		int[] arr = {1,0,2,3,0,4,5,0};
		duplicateZeros(arr);
		for(int element : arr) { System.out.print(element); }
	}
}
