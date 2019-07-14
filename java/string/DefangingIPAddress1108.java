/*
	Given a valid (IPv4) IP address, return a defanged version of that IP address.
	
	A defanged IP address replaces every period "." with "[.]".
	
	 
	
	Example 1:
	
	Input: address = "1.1.1.1"
	Output: "1[.]1[.]1[.]1"
	Example 2:
	
	Input: address = "255.100.50.0"
	Output: "255[.]100[.]50[.]0"
	
	
	
	
	<문제>
	
	아이피 주소가 "255.100.50.0" 이렇게 주어지면, .을 [.]으로 바꿔서 "255[.]100[.]50[.]0"이렇게 반환하라.
	
	
	
	<문제풀이>
	
	.replace()함수를 써서 (바꾸고 싶은 string, 본인이 원하는 바꾼 후 string)을 넣어준다.
	
	Runtime: 2 ms, faster than 49.16% of Java online submissions for Defanging an IP Address.
	Memory Usage: 34.5 MB, less than 100.00% of Java online submissions for Defanging an IP Address
	
	
	더 빠른 방법:
	
	StringBuilder sb = new StringBuilder();
    for (int i = 0; i < address.length(); ++i)
        sb.append(address.charAt(i) == '.' ? "[.]" : address.charAt(i));
    return sb.toString();
    
   	.replace()를 쓰는것이 비효율적인 것 같다.
   	
   	Runtime: 0 ms, faster than 100.00% of Java online submissions for Defanging an IP Address.
	Memory Usage: 34.2 MB, less than 100.00% of Java online submissions for Defanging an IP Address.
	
 */


package String;

public class DefangingIPAddress1108 {
	public static String defangIPaddr(String address) {
        return address.replace(".", "[.]");
    }

	public static void main(String[] args) {
		String address = "1.1.1.1";
		System.out.println(defangIPaddr(address));
	}
}
