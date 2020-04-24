package thirtyDayChallenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LRUCache {
	
	/*
	//<Trial01>
	
//	["LRUCache","get","put","get","put","put","get","get"]
//	[[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
//	Output: [null,-1,null,-1,null,null,2,-1]
//	Expected: [null,-1,null,-1,null,null,2,6]
	
	//[[1,5],[2,6]]이 있는 map에 [1,2]를 넣으면, 아무일도 안일어나야한다. 왜냐하면 문제에서
	//put(key, value) - Set or insert the value if the key is not already present.
	
	//라고 했기 때문에, if the key is already present, DO ?
	
	//라는 말이 없다.
	
	//이 말이 맞는 말이라면, 맨 마지막에 2,6으로 끝나는게 아니라, 5,6으로 끝나야 한다.
	
	//put [2,6] -> [[2,6]]
	//put [1,5] -> [[1,5],[2,6]]
	//put [1,2] -> [[1,5],[2,6]] -- Do Nothing
	//get(1) = 4
	//get(2) = 6
	
	//만약 testcase가 맞으려면, 
	//1) Set or insert the value even if the key is already present.
	//라고 문제가 바뀌어야 한다.
	
	//개같은 설명.
	  
	
	
    Map<Integer, Integer> map;
    List<int[]> list;
    int limit;
    
    public LRUCache(int capacity) {
        map = new HashMap<>();
        list = new ArrayList<>();
        limit = capacity;
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
        	int idx = 0;
        	for(int i = 0; i < list.size(); i++) {
        		if(list.get(i)[0] == key) {
        			idx = i;
        			break;
        		}
        	}
            int[] tmp = list.get(idx);
            list.remove(idx);
            list.add(0, tmp);
            
            
            return map.get(key);
        } else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        map.put(key, value);
        list.add(0, new int[] {key,value});

        if(list.size() > limit){
        	int[] tmp = list.remove(list.size()-1);
        	int deleteKey = -1;
            for(int mapKey : map.keySet()){
                if(mapKey == tmp[0] && map.get(mapKey) == tmp[1]) {	
                	deleteKey = mapKey;
                	break;
                }
            }
            map.remove(deleteKey);
        }
    }
    */
	
	//<문제풀이1 by HelloWorld123456>
	
	//첨 봤을때 arraylist.add(0, key)하면 느리니까 linkedlist로 하면 어떨까 했는데
	
	//막상 구현하려니 이전노드랑 다음노드 어떻게 구현해야 하나 하고 얼타나가 그냥 arraylist썼는데
	
	//이건 linkedlist(중에서도 doublely linked list) 구현방식 잘 해놨네
	
	//18 / 18 test cases passed.
	//Status: Accepted
	//Runtime: 13 ms
	//Memory Usage: 47.7 MB
	
	class Node {
        int key;
        int value;
        Node pre;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    HashMap<Integer, Node> map;
    int capicity, count;
    Node head, tail; //head는 맨 첫번째 노드 앞에있는 노드. tail도 마찬가지로 맨 뒤에있는 노드 뒤에 있는 노드.
    
    public void deleteNode(Node node) { //요게 해당 element빼고, element.pre랑 element.next 연결해주는 메서드
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public void addToHead(Node node) {
        node.next = head.next;
        node.next.pre = node; //이전 맨 앞에 있던 노드.pre가 head에서 파라미터로 새롭게 받은 노드로 바뀌고
        node.pre = head; //이제 맨 앞에 있는 노드 앞에 head노드가 붙는다.
        head.next = node;
    }
    
    public LRUCache(int capacity) {
        this.capicity = capacity;
        map = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.pre = head;
        head.pre = null;
        tail.next = null;
        count = 0;
    }
    
    public int get(int key) {
        if (map.get(key) != null) {
            Node node = map.get(key);
            int result = node.value;
            deleteNode(node);
            addToHead(node);
            return result;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if (map.get(key) != null) {
            Node node = map.get(key);
            node.value = value;
            deleteNode(node);
            addToHead(node);
        } else {
            Node node = new Node(key, value); 
            map.put(key, node);
            if (count < capicity) {
                count++;
                addToHead(node);
            } else {
                map.remove(tail.pre.key);
                deleteNode(tail.pre);
                addToHead(node);
            }
        }
    }
    
    
	public static void main(String[] args) {
		int capacity = 2;
		LRUCache obj = new LRUCache(capacity);
		obj.put(1, 1);
		obj.put(2, 2);
		obj.get(1);
		obj.put(3, 3);    // evicts key 2
		obj.get(2);       // returns -1 (not found)
		obj.put(4, 4);    // evicts key 1
		obj.get(1);       // returns -1 (not found)
		obj.get(3);       // returns 3
		obj.get(4);  
		
		System.out.println(obj);
	}
}
