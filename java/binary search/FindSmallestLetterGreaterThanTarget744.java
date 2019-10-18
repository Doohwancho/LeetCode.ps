/*
	Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.
	
	Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.
	
	Examples:
	Input:
	letters = ["c", "f", "j"]
	target = "a"
	Output: "c"
	
	Input:
	letters = ["c", "f", "j"]
	target = "c"
	Output: "f"
	
	Input:
	letters = ["c", "f", "j"]
	target = "d"
	Output: "f"
	
	Input:
	letters = ["c", "f", "j"]
	target = "g"
	Output: "j"
	
	Input:
	letters = ["c", "f", "j"]
	target = "j"
	Output: "c"
	
	Input:
	letters = ["c", "f", "j"]
	target = "k"
	Output: "c"
	Note:
	letters has a length in range [2, 10000].
	letters consists of lowercase letters, and contains at least 2 unique letters.
	target is a lowercase letter.
	
	
	
	
	
	<문제>
	
	알파벳을 담은 어레이 letter과, 알파벳 하나인 target이 다음과 같이 주어진다.
	
	letters = ["c", "f", "j"]
	target = "c"
	
	letter가 사전적 순서로 정렬이 되었다고 했을 때, target보다 사전적으로는 뒤에 나오는 알파벳 중, 가장 첫번째로 나타나는 알파벳을 반환하라.
	
	이 경우, c보다 사전적으로 뒤에 나오면서, 가장 첫번째로 나오는 "f"를 반환한다.
	
	만약, target에 "z"이고, letters가 ["a","b"]인 경우에는 예외로,
	
	... x - y - z - a - b - c ...
	
	순으로 순환되기 때문에, 이 경우엔 "a"를 반환한다.
	
 */

package BinarySearch;

public class FindSmallestLetterGreaterThanTarget744 {
	
	
	//<문제풀이1>
	
	//Character.compare(char1, char2)를 이용하면 사전적으로 char1과 char2중 어떤게 앞에나오고 뒤에나왔는지 파악 가능
	
	//그보다 더 쉽고 빠른게 'a'<'b'로 비교하는 방법.
	
	//만약 target을 loop 다돌았는데도 못찾았으면, target = 'z' letters = {'a','b'}같은거일 꺼니까,
	
	//그냥 가장 앞에 알파벳을 반환해주면 된다.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Smallest Letter Greater Than Target.
	//Memory Usage: 38.7 MB, less than 100.00% of Java online submissions for Find Smallest Letter Greater Than Target.
	
	public static char nextGreatestLetter(char[] letters, char target) {		
		for(char c : letters) if(target < c) return c;
		return letters[0];
    }
	
	public static void main(String[] args) {
		char[] letters = {'c', 'f', 'j'};
		char target = 'c';
		System.out.println(nextGreatestLetter(letters, target));
	}
}
