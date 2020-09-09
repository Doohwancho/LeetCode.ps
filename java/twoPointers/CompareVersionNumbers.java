package september;

public class CompareVersionNumbers {
	
	
	//<Trial01>
	
	//1.1
	//1.10에서 막힘
	
	//0따로 if else로 처리할 수 있긴 한데
	
	//생각보다 덕지덕지 붙어서 개더럽네
	
	//.split()으로 깔끔하게 다시해야겠다.
	
    public int compareVersion(String version1, String version2) {
        int i = 0, j = 0;
        while(i < version1.length() && j < version2.length()){
            while(i < version1.length()-1 && version1.charAt(i)=='0') i++;
            while(j < version2.length()-1 && version2.charAt(j)=='0') j++;
            if(version1.charAt(i) == '.' && version2.charAt(j) == '.'){
                i++;
                j++;
                continue;
            } else if(version1.charAt(i) == '.'){
                return -1;
            } else if(version2.charAt(j) == '.'){
                return 1;
            } else {
                if(version1.charAt(i) > version2.charAt(j)){
                    return 1;
                } else if(version2.charAt(j) > version1.charAt(i)){
                    return -1;
                } else {
                    i++;
                    j++;
                    continue;
                }
            }
        }
        
        return version1.charAt(i-1) == version2.charAt(j-1) ? 0 : version1.charAt(i-1) > version2.charAt(j-1) ? 1 : -1;
    }
    
    
    
    //<문제풀이1>
    
    //two pointer
    
    //주의사항) string.split("."); 하면 안됨. string.split("\\."); 해야 정상적으로 돌아감.
    
    //Runtime: 1 ms
    //Memory Usage: 37.3 MB
    
    public int compareVersion(String version1, String version2) {
        String[] s1 = version1.split("\\.");
		String[] s2 = version2.split("\\.");
        
        int len1 = s1.length;
        int len2 = s2.length;
        int maxLen = Math.max(len1, len2);
        
        for(int i = 0, num1 = -1, num2 = -1; i < maxLen; i++){
        	
        	//1
        	//1.0.000.000000.000000 을 비교할때, 불필요한 0을 지워주는 역할
            if(i == len1 || i == len2){
                if(i == len1){
                    while(i < len2 && Integer.parseInt(s2[i]) == 0) i++;
                    if(i == len2) return 0;
                    else return -1;
                } else {
                    while(i < len1 && Integer.parseInt(s1[i]) == 0) i++;
                    if(i == len1) return 0;
                    else return 1;
                }    
            }
            
            num1 = Integer.parseInt(s1[i]);
            num2 = Integer.parseInt(s2[i]);
            
            if(num1 > num2){
                return 1;
            } else if(num2 > num1){
                return -1;
            }
        }
        
        return 0;
    }
    
    
    //<문제풀이2 by pavel-shlyk>
    
    //문제풀이1와 같은로직, 간단하게 줄인 것
    
    //Runtime: 1 ms
    //Memory Usage: 37.4 MB
    
    public int compareVersion(String version1, String version2) {
        String[] s1 = version1.split("\\.");
		String[] s2 = version2.split("\\.");
        int maxLen = Math.max(version1.length(), version2.length());
        
        for(int i = 0; i < maxLen; i++){
            Integer n1 = i < s1.length ? Integer.parseInt(s1[i]) : 0;
            Integer n2 = i < s2.length ? Integer.parseInt(s2[i]) : 0;
            int c = n1.compareTo(n2);
            if(c != 0){
                return c;
            }
        }
        
        return 0;
    }
}
