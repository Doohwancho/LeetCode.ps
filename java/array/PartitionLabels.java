package september;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels {
	
	//<Trial01>
	
	//0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 
	//8 5 8 5 7 5 8 7 8 14 15 11 15 13 14 15 19 22 23 19 20 21 22 23
	
	//string.lastIndexOf(char)을 이용해, i번째 있는 char이 S에 마지막에서 몇번째인지 구함.
	
	//만약 구한 후, 바로 오른쪽 애들이 S에서 딱 한번등장하는 애들이거나(i == S.lastIndexOf(S.charAt(i))) 
	
	//이미 a......a 이미 두번째 a라, S.lastIndexOf(S.charAt(i))하면 본인이 나오는 애들(이것 역시 i == S.lastIndexOf(S.charAt(i)))
	
	//도 껴줌.
	
	//까비 아깝소? 거의 다 온거같은디
	
    public List<Integer> partitionLabels(String S) {
        List<Integer> rst = new ArrayList<>();
        int len = S.length();
        for(int i = 0, prev = 0, acc = 0; i < len; ){
            i = S.lastIndexOf(S.charAt(i));
            acc = i - prev + 1;
            i++;
            while(i < len && i == S.lastIndexOf(S.charAt(i))){
                i++;
                acc++;
            }
            prev = i;
            rst.add(acc);
        }
        return rst;
    }
    
    
    //<Trial02>
    
    //생각해보니, "hijhklij"의 경우에
    
    //i == S.lastIndexOf(S.charAt(i))를 해버리면, h->h찾은 후,
    
    //k,l,i,j모두 혼자밖에 없는애거나 두번째 등장한 애니까 주루룩 합쳐지네. 
    
    //근데 얘네들이 합쳐져야 하는 이유는, hijh에 포함되서이지, 독립적인 애들이니까 합쳐져야 하진 않음.
    
    //문제에서 독립적인애들로 "최대한 많이" 나누라고 했기 때문.
    
    //그래서 i > S.indexOf(S.charAt(i)) 로 바꿈. 
    
    //전에 나타난 적이 있으면 합치는걸로.
    
    //그런데 여기서 문제가, "hijhklij"에서
    
    //h->h한 후, k는 전에 안나왔으니까 분리되고, l도 분리되는데, 
    
    //i가 나왔을 때, 전에 나왔으니까 여태 누적된 k+l+i를 다 붙여줘야 하는데,
    
    //이미 리스트에 들어가버림. 흠..
    
    
    public List<Integer> partitionLabels(String S) {
        List<Integer> rst = new ArrayList<>();
        int len = S.length();
        for(int i = 0, prev = 0, acc = 0; i < len; ){
            System.out.println(i);
            i = S.lastIndexOf(S.charAt(i));
            acc = i - prev + 1;
            i++;
            while(i < len && i > S.indexOf(S.charAt(i))){ //바로 오른쪽에 붙어있는 경우
                i++;
                acc++;
            }
            prev = i;
            rst.add(acc);
        }
        return rst;
    }
    
    
    //<문제풀이1 by kay_deep>
    
    //last에 마지막에 나온 알파벳의 인덱스를 덮어쓰고,
    
    //파라미터로 주어진 String을 iterate하면서, i가 마지막에 나온애면 rst에 더해주는 방법.
    
    //얘가 trial02보다 나은 점은, 
    
    //Trial02은 String.lastIndexOf()로 찾는데, 만약 얘가 이미 한번 나와서 두번째로 나온 애라면,
    
    //이걸 확인하기 위해서 또 String.indexOf()를 써야함.
    
    //개오바
    
    //근데 문제풀이1은 애초에 마지막 인덱스를 뽑아놓고 시작하기 때문에, 
    
    //저지랄을 안해도 됨.
    
    //추가로, end = Math.max(end, last[S.charAt(i)-'a']); 을 하는 이유는,
    
    //0~i까지 등장한 애들중에 젤 큰애 뽑으려고.
    
    //"ababcbacadefegdehijhklij"
    //8 8 8 8 8 8 8 8 8 14 15 15 15 15 15 15 19 22 23 23 23 23 23 23 
    
    //만약 end = last[S.charAt(i)-'a']=로 하면,
    
    //8 5 8 5 7 5 8 7 8 14 15 11 15 13 14 15 19 22 23 19 20 21 22 23 
    
    //들쭉날쭉해서, 뚝심없이 [6,2,1,3,2,1,1,4,1,1,1,1] 요롷게 됨.
    
    //String에서 딱 한번만 나오는 애들은 무조건 독립으로 묶이고, n번 있는애들도 마지막에 있는애는 무조건 독립으로 묶이는 불상사 발생.
    
    //Runtime: 2 ms
    //Memory Usage: 38 MB
    public List<Integer> partitionLabels(String S) {
        List<Integer> rst = new ArrayList<>();
        int[] last = new int[26];
        char[] cs = S.toCharArray();
        int len = S.length();
        for(int i = 0; i < len; i++){
            last[S.charAt(i)-'a'] = i; 
        }
        int start = -1;
        int end = -1;
        for(int i = 0; i < len; i++){
            if(start == -1){
                start = i;
            }
            end = Math.max(end, last[S.charAt(i)-'a']); 
            if(end == i){
                rst.add(end-start+1);
                start = -1;
            }
        }
        return rst;
    } 
}
