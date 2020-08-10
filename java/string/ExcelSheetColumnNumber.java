package augustChallenge;

public class ExcelSheetColumnNumber {
	
	//<문제풀이1>
	
	//EEEEEEEEEEEEEZZZ
	
	//Runtime: 1 ms
	//Memory Usage: 38.1 MB
	
    public int titleToNumber(String s) {
        int size = s.length();
        int rst = 0;
        for(char c : s.toCharArray()){
            rst += (c-65+1) * Math.pow(26, --size);
        }
        return rst;
    }
}
