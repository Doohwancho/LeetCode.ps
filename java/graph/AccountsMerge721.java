package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AccountsMerge721 {

	
	//<Trial01>
	
	//Union Circle
	
	//까비 아깝소
	
	//넣는것 까진 했는디 맨 마지막에 가져올 때
	
	//smith->ny->00에서 
	
	//00부터 나오면 
	
	//[00],[smith,ny]이런식으로 넣어짐.
	
	//undirected graph를 순회하는 방법을 알아야 하는데 bool[] visited 쓰는 방법으로 했다가 죽씀;;
	
    class UnionCircle{
        List<List<String>> rst;
        Map<String, String> map;
        Map<String, Boolean> visited;
        
        public UnionCircle(List<List<String>> accounts){
            rst = new ArrayList<>();
            map = new HashMap<>();
            visited = new HashMap<>();
            
            for(List<String> list : accounts){
                String user = list.get(0);
                for(int i = 1; i < list.size(); i++){
                    String nameAndEmail = user+" "+list.get(i);
                    map.put(nameAndEmail, nameAndEmail);
                    visited.put(nameAndEmail, false);
                }
            }
        }
        
        private String find(String x){
            while(!map.get(x).equals(x)) x = map.get(x);
            return x;
        }
        
        public void union(String s1, String s2){
            String s1_ = find(s1);
            String s2_ = find(s2);
            if(s1_.equals(s2_)) return;
            map.put(s1_, s2_);
        }
        
        public void helper(List<String> container, String key){
            while(!map.get(key).equals(key)){
                visited.put(key, true);
                key = map.get(key);
                if(visited.get(key) == false){
                    container.add(key.split(" ")[1]);    
                }
            }
            visited.put(key, true);
            Collections.sort(container);
            return;
        }
        
                
        public void extract(){
            for( Map.Entry<String, String> elem : map.entrySet() ){
                if(visited.get(elem.getKey()) == false){
                    List<String> list = new LinkedList<>();
                    String[] SplittedStr = elem.getKey().split(" ");
                    
                    list.add(SplittedStr[1]);
                    helper(list, elem.getKey());
                    list.add(0, SplittedStr[0]);
                    rst.add(list);
                }

            }
        }
        
        
        public List<List<String>> returnAns(){
            return rst;
        }
    }
    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        UnionCircle uc = new UnionCircle(accounts);
        for(List<String> account : accounts){
            for(int i = 1; i < account.size()-1; i++){
                uc.union(account.get(0)+" "+account.get(i), account.get(0)+" "+account.get(i+1));
            }
        }
        uc.extract();
        return uc.returnAns();
    }
    
    
    //<문제풀이1 by legendaryengineer>
    
    //union-circle
    
    //아까울 수준까진 아니었음ㅋㅋ;
    
    //accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
    
    //이걸 예시로 들면,
    
    //주솟값: 0x1 이름:John 이메일:"johnsmith@mail.com"(0x2에 같은 주소인애가 등장할때 지워짐), "john00@mail.com"
    
    //주솟값: 0x2 이름:John 이메일:"johnsmith@mail.com", "john_newyork@mail.com"
    
    //주솟값: 0x3 이름:John(동명이인) 이메일:"johnnybravo@mail.com"
    
    //0x1 -> 0x2
    
    //그래서 0x1 != parent(0x1) 임. parent(0x1)는 0x2니까. 그러면 아들들이 parent인 0x2에 .addAll()로 이메일 몰빵해줌.
    
    //이 방법으로 undirected graph에서 A->B->C에서 C가 먼저 튀나오고 A가 다음으로 튀나와서 [C],[A,B]같이 끊기는 문제 해결
    
    //Runtime: 33 ms, faster than 75.55% of Java online submissions for Accounts Merge.
    //Memory Usage: 47.2 MB, less than 5.08% of Java online submissions for Accounts Merge.
    
    class Person {
        String name;
        Set<String> emails;
        public Person(String name) {
            this.name = name;
            this.emails = new HashSet<>();
        }
        public List<String> toList() {
            List<String> list = new ArrayList<>();
            list.addAll(emails);
            Collections.sort(list);
            list.add(0, name);
            return list;
        }
    }
    
    private Map<Person, Person> parents;
    private Map<String, Person> map;
    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        parents = new HashMap<>();
        map = new HashMap<>();
        
        for (List<String> list : accounts) {
            Person p = new Person(list.get(0));
            parents.put(p, p);
            for (int i = 1; i < list.size(); i++) {
                String email = list.get(i);
                p.emails.add(email);
                if (map.containsKey(email)) {
                    unite(map.get(email), p); //["John", "johnsmith@mail.com", "john00@mail.com"], 얘랑 ["John", "johnsmith@mail.com", "john_newyork@mail.com"], 얘에서 johnsmith@mail.com이 같으니까 얘네 둘이 같은사람이니 이어줌.
                }
                map.put(email, p);
            }
        }
        
        Set<Person> parentSet = new HashSet<>();
        for (Person p : parents.keySet()) { //난 A->B->C라는 undirected graph에서 B->C이미 돌아서 [B,C] A->null->null 이렇게 되는게 문제였는데, 얘는 A->B->C에서 parent(A)먹이면 A랑 다르니(C) 종착역 이전애 애들을 parent에 모두 .addAll()해주는게 똑똑하네 이거
            Person parent = parent(p);
            if (p != parent) {
                parent.emails.addAll(p.emails);
            }
            parentSet.add(parent);
        }
        
        List<List<String>> res = new ArrayList<>();
        for (Person p : parentSet) res.add(p.toList());
        return res;
    }
    
    private void unite(Person p1, Person p2) {
        parents.put(parent(p1), parent(p2));
    }
    
    private Person parent(Person p) {
        while (parents.get(p) != p) {
            //parents.put(p, parents.get(parents.get(p))); //이거 없어도 잘돌아감. 아나 쓸데없이 고민에 에너지소모만 했잖아 ㅋㅋㅋㅋ 
            p = parents.get(p);
        }
        return parents.get(p);
    }
    
}
