package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveSubFoldersFromFileSystem1233 {
	
	/*
	//<Trial01>
	
	//머리 깨지겄네
	
	//rsts에 넣어두, rsts for문 돌릴 때 4번째 element랑 연관있는데 1번째 element에서 나가리면 break문에 걸려서 문제.
	
	//	java concurrent modification exception
	//	https://m.blog.naver.com/tmondev/220393974518
	//  List<String> targetList = new LinkedList<>();  ---- ERROR 
	//	List<String> targetList = new CopyOnWriteArrayList<String>(); ---- OKAY
		
	//	String str = "/a/b/c";
	//	
	//	String notRelated = "/c/a"; //                       -2  add 
	//	String a = "/a/b";          // 2   add (delete str)
	//	String b = "/a/b/c";        //                                     0   pass
	//	String ba ="/a/b/ca";       //                       -1
	//	String bb ="/a/b/ca/x";     //                       -3
	//	String c = "/a/b/c/d";      //                       -2  pass
	//	String d = "/a/b/b/d";      // 1   add
	//	String e = "/a";            // 4   add (delete str)
	//	String f = "/a/b/c/a";      //                       -2  pass
	//	
	//	
	//	System.out.println("not related: "+str.compareTo(notRelated));
	//	System.out.println("a: "+str.compareTo(a));
	//	System.out.println("b: "+str.compareTo(b));
	//	System.out.println("ba: "+str.compareTo(ba));
	//	System.out.println("bb: "+str.compareTo(bb));
	//	System.out.println("c: "+str.compareTo(c));
	//	System.out.println("d: "+str.compareTo(d));
	//	System.out.println("e: "+str.compareTo(e));
	//	System.out.println("f: "+str.compareTo(f));
	//	
	//	System.out.println(a.compareTo(str));
	//	System.out.println(d.compareTo(str));
	//	
	//	System.out.println("split: "+e.split("/")[1]);
	//	
	//	System.out.println("b".compareTo("a"));
	//	System.out.println("b".compareTo("b"));
	//	System.out.println("b".compareTo("c"));
		
	//	System.out.println("c".compareTo("ca"));
	//	System.out.println("c".compareTo("cb"));
	//	System.out.println("c".compareTo("cz"));
	//	System.out.println("c".compareTo("d"));
	//	System.out.println("c".compareTo("b"));
	//
	//	System.out.println("ca".compareTo("c"));
	
	
	class Solution {
	    public List<String> removeSubfolders(String[] folder) {
	        // List<String> rsts = new LinkedList<>();
	        List<String> rsts = new CopyOnWriteArrayList<>();
	        rsts.add(folder[0]);
	        
	        for(String str : folder){
	            // a/b/ca
	            int size = rsts.size();
	            for(String rst : rsts){
	                System.out.println("str: "+str+"  rst: "+rst+ " size: "+size);
	                
	                if(str.split("/")[1].equals(rst.split("/")[1])){
	                    //equal
	                    if(str.compareTo(rst) == 0){
	                        System.out.println("equal");
	                        break;
	                    }
	                    //more
	                    else if(str.compareTo(rst) > 0){
	                        System.out.println("more");
	                        //more-if
	                        if(str.length() > rst.length()){
	                            System.out.println("more-if");
	                            //rst.fullStr()가 str.substr()에 완전히 일치하면, str지우고 rst넣
	                            boolean flag = true;
	                            String[] tmp1 = str.split("/");
	                            String[] tmp2 = rst.split("/");

	                            for(int i = 0; i < tmp2.length; i++) {
	                                if(!tmp2[i].equals(tmp1[i])) {
	                                    flag = false;
	                                }
	                            }
	                            if(flag){
	                                System.out.println("more-if-flag");
	                                // rsts.remove(rst);
	                                // rsts.add(str);
	                                continue;
	                            }
	                            else {
	                                //완전히 일치하지 않고 ?맨 마지막만 다르면? - 그냥 else -
	                                System.out.println("more-if-not flag");
	                                rsts.add(str);
	                                break;
	                            }
	                        } 
	                        //more-else
	                        else {
	                            System.out.println("more-else");
	                            rsts.add(str);
	                            break;
	                        }
	                    }
	                    //less
	                    else {
	                        System.out.println("less");
	                        //less-if
	                        if(str.length() > rst.length()){
	                            System.out.println("less-if");
	                            rsts.add(rst);   
	                        }
	                        //less-else
	                        else {
	                            System.out.println("less-else");
	                            continue;
	                        }
	                    } 
	                }
	                else {
	                    System.out.println("just else triggered");
	                    if(size <= 1){
	                        rsts.add(str);    
	                    }
	                    else {
	                        size--;
	                        continue;   
	                    }
	                }
	            }
	            System.out.print("rsts : ");
	            for(String i : rsts) System.out.print(i+", ");
	            System.out.println();
	        }    
	        return rsts;
	    }
	}
	*/
	
	//<문제풀이1 by sschangi>
	
	//	Your input : ["/a","/a/b","/c/d","/c/d/e","/c/f"]
	
	//	stdout
	
	//	folder[i]: /a/b j: 2 
	//	[/a]
	
	//	folder[i]: /c/d j: 4
	//	if triggered
	//	[/a, /c/d]
	
	//	folder[i]: /c/f j: 4
	//	if triggered
	//	[/a, /c/f, /c/d]
	
	//	folder[i]: /c/d/e j: 4
	//	[/a, /c/f, /c/d]	

	//	Output :  ["/a","/c/d","/c/f"]
	
	//Runtime: 46 ms, faster than 67.98% of Java online submissions for Remove Sub-Folders from the Filesystem.
	//Memory Usage: 55.1 MB, less than 100.00% of Java online submissions for Remove Sub-Folders from the Filesystem.

	public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder, (a, b) -> a.length() - b.length()); //긴게 먼저 set에 들어가면 set(긴거).constains(짧은거)에서 에러나니까 짧은것 부터 넣으려고 정렬
        Set<String> set = new HashSet();
        List<String> ans = new ArrayList();
        ans.add(folder[0]);
        set.add(folder[0]);
        
        for (int i = 1; i < folder.length; ++i ) {
            int j = 1;
            for (; j < folder[i].length(); ++j) {
                if (set.contains(folder[i].substring(0,j)) && folder[i].charAt(j) == '/') { //set에 있는게 folder[i].subString()이면 해당 인덱스 j를 keep. 만약 set에 있는게 folder[i]와 아예 달라서 관계없다면, j == folder[i].length. 왜냐면 마지막 for문까지 돌꺼니까.
                    break;
                }
            }
            if (j == folder[i].length()) { //folder[i]("/a/b")가 set의 어떠한 string("/a") + 알파면, j != folder[i].length이니까 그냥 넘어감. 만약 set의 어떠한 string과 folder[i]가 달라서 위에 for문이 끝까지 돌았다면, j == folder[i]니까 ans에 더해줌.
               ans.add(folder[i]);
               set.add(folder[i]); 
            }  
        }
        
        return ans;
    }
}