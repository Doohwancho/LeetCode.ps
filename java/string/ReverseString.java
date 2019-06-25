package String;

public class ReverseString {
	public static void main(String[] args) {
		char[] s = {'h','e','l','l','o'};
		
		int i,j=s.length-1;
		
        for(i=0;i<s.length&&j>=i;i++)
	        {
        	System.out.println(s);
	        char temp=s[j];
	        s[j]=s[i];
	        s[i]=temp;
	        j--;
	        }
        
        System.out.println(s);    
	}
}
