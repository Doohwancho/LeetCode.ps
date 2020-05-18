package array;

public class NumberOfStudentsDoingHomeworkAtAGivenTime1450 {
	
	//<문제풀이1>
	
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int rst = 0;
        for(int i = 0; i < startTime.length; i++){
            if(queryTime >= startTime[i] && queryTime <= endTime[i]){
                rst++;
            }
        }
        return rst;
    }
}
