package Array;

//two pointer method by caikehe
//리스트가 오름차순 정렬되어 있으니, 가장 처음에 작은것과 가장 마지막에 큰것을 합쳐서 target과 비교하여, 
//target보다 더 크면 마지막의 큰것을 한칸 줄이고, 작으면 처음것의 작은것을 한칸 늘리는 식으로 조정


//Runtime: 0 ms, faster than 100.00% of Java online submissions for Two Sum II - Input array is sorted.
//Memory Usage: 38.3 MB, less than 74.63% of Java online submissions for Two Sum II - Input array is sorted.
public class TwoSumIISortedInputArray167ver2 
{
	public static int[] twoSum(int[] numbers, int target)
	{
		int l = 0, r = numbers.length-1;
		int[] rst = new int[2];
		
		while(l<r)
		{
			if(numbers[l]+numbers[r] > target)
			{
				r--;
			}
			else if(numbers[l]+numbers[r] < target)
			{
				l++;
			}
			else
			{
				rst[0]=l+1;
				rst[1]=r+1;
				break;
			}
		}
		return rst;
	}
	
	public static void main(String[] args) 
	{
		int[] numbers = {2,7,11,15};
		int target = 9;
		System.out.println(twoSum(numbers, target));
	}
}
