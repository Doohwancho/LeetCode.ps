package LeetCode08;

class ReverseString {
	
	    public String reverseString01(String s) {
	        char[] arr = s.toCharArray();
	        int len = s.length();
	        for(int i=0;i*2<len;i++){
	            char tmp = arr[i];
	            arr[i] = arr[len-1-i];
	            arr[len-1-i] = tmp;
	        }
	        return new String(arr);
	   }
}
