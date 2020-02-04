/*
	Given an array arr that is a permutation of [0, 1, ..., arr.length - 1], we split the array into some number of "chunks" (partitions), and individually sort each chunk.  After concatenating them, the result equals the sorted array.
	
	What is the most number of chunks we could have made?
	
	Example 1:
	
	Input: arr = [4,3,2,1,0]
	Output: 1
	Explanation:
	Splitting into two or more chunks will not return the required result.
	For example, splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1, 2], which isn't sorted.
	Example 2:
	
	Input: arr = [1,0,2,3,4]
	Output: 4
	Explanation:
	We can split into two chunks, such as [1, 0], [2, 3, 4].
	However, splitting into [1, 0], [2], [3], [4] is the highest number of chunks possible.
	Note:
	
	arr will have length in range [1, 10].
	arr[i] will be a permutation of [0, 1, ..., arr.length - 1].
	
	
	
	
	<문제>
	
	arr = [4,3,2,1,0]
	
	이 리스트는 0부터 arr.size-1까지의 수의 랜덤한 순열(permutation)이다.
	
	이제 이 리스트를 부분으로 쪼갤건데,                   //[4,3]랑 [2,1,0]로 쪼개든 [4,3,2,1]랑 [0]으로 쪼개든 너 맘이고
	
	쪼갠 리스트를 오름차순 청렬해서 순서대로 다시 붙였을 때,     //[3,4]랑 [0,1,2] -> [3,4,0,1,2]
	
	처음 주어진 arr의 오름차순 정렬된 것과 같게 만들고 싶다면,   //[3,4,0,1,2]는 처음 주어진 arr의 오름차순 정렬인 [0,1,2,3,4]와 다르므로 이건 아님.
	
	'최대'로 몇번 쪼갤 수 있을까?
	
	예를들어, [1, 0, 2, 3, 4]의 경우,  [1,0]과 [2,3,4] 2개로 쪼개서 각각 정렬해서 합치면 [0,1,2,3,4]를 만들 수 있지만,
	
	[1,0]와 [2] [3] [4] 이렇게 '최대'로 쪼개서 정렬해서 합쳐도 [0,1,2,3,4]를 만들 수 있으니까
	
	이 경우 답은 2가 아니라 4가 된다.
 */
package array;

public class MaxChunksToMakeSorted769 {
	
	//<문제풀이1>
	
	//크.. 이거지
	
	//[4,3,2,1,0,5] 을 보면, 숫자를 쪼개서 오름차순 정렬하려면, 초갠 리스트 안의 가장 큰 숫자가 제자리로 갔을때 +1을 카운트 해야 한다는걸 알 수 있다.
	
	//4가 0에 자리에 왔을때까지 카운트를 안하다가, 4가 0의자리(index 4)에 왔을 때 +1을 해주어야 한다. 5는 index 5에있으니 그냥 +1해주고.
	
	//이걸 이용한 방법.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Max Chunks To Make Sorted.
	//Memory Usage: 36.1 MB, less than 10.00% of Java online submissions for Max Chunks To Make Sorted.
	public int maxChunksToSorted(int[] arr) {
        int rst = 0;
        int offMax = -1;
        
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != i){
                offMax = Math.max(offMax, arr[i]);
                if(offMax == i){
                    rst++;
                    offMax = -1;
                }
            } else if(offMax == -1){
                rst++;   
            }  
        }
        return rst;
    }
}
