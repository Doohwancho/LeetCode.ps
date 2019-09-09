/*
	Design a HashMap without using any built-in hash table libraries.
	
	To be specific, your design should include these functions:
	
	put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
	get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
	remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.
	
	Example:
	
	MyHashMap hashMap = new MyHashMap();
	hashMap.put(1, 1);          
	hashMap.put(2, 2);         
	hashMap.get(1);            // returns 1
	hashMap.get(3);            // returns -1 (not found)
	hashMap.put(2, 1);          // update the existing value
	hashMap.get(2);            // returns 1 
	hashMap.remove(2);          // remove the mapping for 2
	hashMap.get(2);            // returns -1 (not found) 
	
	All keys and values will be in the range of [0, 1000000].
	The number of operations will be in the range of [1, 10000].
	Please do not use the built-in HashMap library.




	<문제>
	
	hashmap의 원래 put, get, remove 메서드를 이용하지 않고, hashmap의 put, get remove 메서드를 만들어 보라.

 */



package HashTable;

/**
//<문제풀이 by albeit2008>
	
//int[]를 이용해 푸는 법.

//input data range가 작고 integer타입이라 가능한 방법.

//Runtime: 66 ms, faster than 50.41% of Java online submissions for Design HashMap.
//Memory Usage: 60 MB, less than 18.92% of Java online submissions for Design HashMap.
	
public class DesignHashMap706 {

	private int[] table;

    // Initialize your data structure here. 
    public DesignHashMap706() {
        table = new int[1000001];        
    }
    
    // value will always be non-negative. 
    public void put(int key, int value) {
        table[key] = value+1;
    }
    
    // Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key 
    public int get(int key) {
        return table[key]-1;       
    }
    
    // Removes the mapping of the specified value key if this map contains a mapping for the key 
    public void remove(int key) {
        table[key]=0;        
    }
}
*/

//<문제풀이2 by climberig>

//주석참고

//Runtime: 62 ms, faster than 86.37% of Java online submissions for Design HashMap.
//Memory Usage: 50.5 MB, less than 97.30% of Java online submissions for Design HashMap.

public class DesignHashMap706 
{

    final ListNode[] nodes = new ListNode[10000]; //10000개 크기의 ListNode타입 배열을 만듬. 여기에 각 key와 value를 넣어줄 것임.

	int idx(int key)
    {
        /*	hashmap에서 key를 만들 때 쓰는 방법. 
         *	그럼 10001번째는요? 1번째랑 겹치는데요? 
         *	그게 바로 이 방법의 단점. 
         *	그거 풀려고 세계 각지에 많은 컴공들이 머리 굴리는 중. 
         *	이 문제는 숫자가 input 숫자의 range가 1~10000이니까 괜춘. */
        return Integer.hashCode(key) % nodes.length; 
    }
	ListNode find(ListNode bucket, int key)
    {
        ListNode node = bucket, prev = null;
        while(node != null && node.key != key) //&&앞에 node != null을 붙인 이유는 이게 false면 and 다음 확인 안하고 넘어가서 더 빠르기 때문...?!
        {
            prev = node;
            node = node.next;
        }
        return prev; //이전 노드 찾아줌
    }

    public void put(int key, int val)
    {
        int index = idx(key);
        if(nodes[index] == null) nodes[index] = new ListNode(-1,-1); //초기화
        ListNode prev = find(nodes[index], key);
        if(prev.next == null) prev.next = new ListNode(key, val); //찾는 key의 이전 노드가 없으면, 새로운 key,value를 가진 노드 삽입
        else prev.next.val = val; //있으면 value를 새롭게 바꿔줌
    }

	public int get(int key)
    {
        int index = idx(key);
        if(nodes[index] == null) return -1;
        ListNode prev = find(nodes[index], key);
        return prev.next == null ? -1 : prev.next.val;
    }

    public void remove(int key)
    {
        int index = idx(key);
        if(nodes[index] == null) return;
        ListNode prev = find(nodes[index], key);
        if(prev.next == null) return;
        prev.next = prev.next.next; //prev의 다음 값(찾고자 한 노드)가 없으면, 그 다음 노드의 주솟값으로 바꿔줌
    }

    class ListNode
    {
        int key, val;
        ListNode next; //다음 노드의 주소와 현재 노드를 이어주는 변수

        ListNode(int key, int val)
        {
            this.key = key;
            this.val = val;
        }
    }
}