/*
You are given a string representing an attendance record for a student. The record only contains the following three characters:
'A' : Absent.
'L' : Late.
'P' : Present.
A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

You need to return whether the student could be rewarded according to his attendance record.

Example 1:
Input: "PPALLP"
Output: True
Example 2:
Input: "PPALLL"
Output: False
 */

/*
 * 문제
 * 
 * 학생이 학교에 출석을 얼마나 잘 하느냐에 따라 true/false를 반환한다.
 * 
 * 먼저 P는 학교에 지각하지 않고 잘 왔다는 뜻이고, L은 지각이고, A는 결석이다.
 * 
 * 학생이 3연속으로 지각을 하거나 결석을 총 2번이상 했을 경우 false를 반환하고, 아니면 true를 반환한다.
 * 
 * 
 * 
 * #문제풀이
 * 
 * 1. 결석체크 2.지각체크 순으로 한다.
 * 파라미터로 받은 문자열 s를 .toCharArray();를 이용하여 for문을 돌리기 적합한 형태로 바꿔준다.
 * for문을 돌면서 absence라는 변수에 해당 학생이 몇번 결석했는지 카운트 해준다. 
 * 만약 2번 이상 결석을 했으면 false를 반환하고 메소드를 마무리한다.
 * 
 * 결석체크 후, 아무이상이 없다면 지각체크를 한다.
 * 3연속 지각을 체크해야하기 때문에, index는 2부터 시작한다. 조건은 index-2,index-1,index가 모두 'L'이면 false를 반환해 준다.
 * 
 * 처음에 코드를 짤 땐, 결석과 지각을 동시에 처리하려고 했으나, index가 서로 달라서 따로 분리시켰다.
 * 만약 주어진 문자열의 길이가 3보다 작아서 index-2 체크가 힘든 경우는 무시해도 된다.
 * 어짜피 최대 지각 횟수가 LL이기 때문에 true를 반환할 수 밖에 없기 때문이다.
 * 
 */

package String;

public class StudentAttendenceRecordI551 {
	public static boolean checkRecord(String s) {
		int absence = 0;
		char[] dailyRecords = s.toCharArray();
		int length = s.length();
		
		//check if absence >= 2 first,
		for(int j = 0; j < length; j++)
		{
			if(dailyRecords[j] == 'A')
			{
				absence++;
			}
		}	
		if(absence > 1) return false;
		
		//check if 3 consecutive tardies next
		for(int i = 2; i < length;i++)
		{
			if(dailyRecords[i] == 'L' && dailyRecords[i-1] == 'L' && dailyRecords[i-2] == 'L')
			{
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
	//String s = "PPALLP";
	//String s = "LLLL";
	String s = "AA";
	System.out.println(checkRecord(s));
	
}
}
