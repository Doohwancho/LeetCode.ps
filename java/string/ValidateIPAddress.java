package juneChallenge;

public class ValidateIPAddress {
	
	//<Trial01>
	
	//문제설명 참 뭐같이 하네
	
	//여기서 막힘
	
	//Input: "20EE:FGb8:85a3:0:0:8A2E:0370:7334"
	//Output: "IPv6"
	//Expected: "Neither"
	
	//근데 막힌 이유가 G가 나와서임. 알파벳은 a부터 f가지 나와야 됨.
	
	//근데 존나 어이없는게 문제에서 이걸 언급안함.
	
	//IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits. The groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one. Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper cases).
	
	//여기서 어디에 a~f만쓰라고 나와있냐 쓰발롬들아
	
    public static String validIPAddress(String IP) {
    	if(IP.contains(".")) {
    		if(IP.endsWith(".")) return "Neither";
    		String[] IP_ = IP.split("\\.");
    		if(IP_.length != 4) return "Neither";
    		for(String str : IP_) {
    			try {
    				if(Integer.parseInt(str) > 255 ) {
    					return "Neither";
    				}
    			} catch(Exception e){
    				return "Neither";
    			}
    			if(str.indexOf('0') == 0) return "Neither";
    		}
    		return "IPv4";
    	} else {
            if(IP.endsWith(":")) return "Neither";
    		String[] IP_ = IP.split("\\:");
    		if(IP_.length != 8) return "Neither";
    		for(String str : IP_) {
    			if(str.length() > 4 || str.length() == 0) return "Neither";
    		}
    		return "IPv6";
    	}
    }
    
    //<문제풀이1 by fabrizio3>
    
    //에휴 의미없다
    
    //Runtime: 1 ms
    //Memory Usage: 37.1 MB
    
    
    public String validIPAddress(String IP) {
        if(IP.equals("")) return "Neither";
        if(isIP4(IP)) return "IPv4";
        if(isIP6(IP)) return "IPv6";
        return "Neither";
    }
    
    public boolean isIP4(String IP){
        if(IP.charAt(0)=='.' || IP.charAt(IP.length()-1)=='.')return false;
        String[] temp = IP.split("\\.");
        if(temp.length!=4)return false;
        for(int i=0;i<4;i++){
            try{
                if(temp[i].startsWith("0")&&temp[i].length()>1) return false;
                if(Integer.parseInt(temp[i])>255 || temp[i].charAt(0)=='-' || temp[i].charAt(0)=='+')return false;    
            }
            catch(NumberFormatException e){
                System.out.println("ERROR");
                return false;
            }
        }
        return true;
    }
    
    public boolean isIP6(String IP){
        if(IP.charAt(0)==':' || IP.charAt(IP.length()-1)==':')return false;
        String[] temp = IP.split(":");
        if(temp.length!=8)return false;
        for(int i=0;i<8;i++){
            if(temp[i].length()>4||temp[i].length()==0)return false;
            for(int j=0;j<temp[i].length();j++){
                if((temp[i].charAt(j)>='0' && temp[i].charAt(j)<='9') || (temp[i].charAt(j)>='a' && temp[i].charAt(j)<='f') || (temp[i].charAt(j)>='A' && temp[i].charAt(j)<='F')){}
                else return false;
            }
        }
        return true;   
    }
}
