/*
	Design a HashSet without using any built-in hash table libraries.
	
	To be specific, your design should include these functions:
	
	add(value): Insert a value into the HashSet. 
	contains(value) : Return whether the value exists in the HashSet or not.
	remove(value): Remove a value in the HashSet. If the value does not exist in the HashSet, do nothing.
	
	Example:
	
	MyHashSet hashSet = new MyHashSet();
	hashSet.add(1);         
	hashSet.add(2);         
	hashSet.contains(1);    // returns true
	hashSet.contains(3);    // returns false (not found)
	hashSet.add(2);          
	hashSet.contains(2);    // returns true
	hashSet.remove(2);          
	hashSet.contains(2);    // returns false (already removed)
	
	
	
	
	<문제>
	
	set 내장함수를 사용하지 않고, set함수중 .add / .contains / .remove를 만들어라.
	
 */

package HashTable;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DesignHashSet705 {
	
	/*
	//<Trial01>
	
	//논리적으로 맞는 것 같으나, 왜 에러가 뜨는지 파악 못함.
	
	//아마 key가 0일때, value도 0가 들어가는데, 원래 new int[]할때 만들어지는 디폴트값이 0니까 에러가 생기는 것 같음.
	
	//또한 매번 몇만개를 for-loop 돌린다는게 엄청 비효율적.
	
	private static int idx = 0;
    public static int[] container = new int[30000];
    
    public MyHashSet() {
        
    }
    
    public void add(int key) {
        boolean checkDuplicate = true;
        for(int i = 0; i < container.length; i++)
        {
            if(checkDuplicate && container[i] == key) checkDuplicate = false;
        }
        if(checkDuplicate) container[idx++] = key;
    }
    
    public void remove(int key) {
        for(int j = 0; j < container.length; j++)
        {
            if(container[j] == key) container[j] = 0;
        }
    }
    
    public boolean contains(int key) {
        for(int k = 0; k < container.length; k++)
        {
            if(container[k] == key) return true;
        }
        return false;
    }
	
	
	public static void main(String[] args) {
		int[] container = new int[19];
		container.len
	}
	*/
	
	
	/*
	//<문제풀이1 by shtanriverdi>
	
	//trial01의 문제점을 보완한 방법.
	
	//숫자가 1~1000001까지 나오기 때문에, 1000001크기의 int[]을 선언해 놓고, 저장할 땐, 키값을 매개변수 받은 값으로 써버림.
	
	//물론 빈공간이 많이 남아서 메모리를 낭비하긴 하지만 굉장히 직관적이고 해당 매개변수를 찾을 때,
	
	//for문으로 몇만개의 int[]를 일일이 다 돌면서 if문으로 체크하는게 아니라 인덱스가 명시되어있으므로 효율적.
	
	//st[0] = -1; 의 경우는 원래 int[]선언시 디폴트값이 0이라서, 0을 넣으면 어짜피 0이기 때문에, 0만 따로 -1처리 해준 것.
	
	//Runtime: 61 ms, faster than 48.40% of Java online submissions for Design HashSet.
	//Memory Usage: 59.3 MB, less than 22.22% of Java online submissions for Design HashSet.


	int st[];
	
    public MyHashSet() {
        st = new int[1000001];
		st[0] = -1; // Specific Case For "0" Value
    }
    
    public void add(int key) {
        st[key] = key;
    }
    
    public void remove(int key) {
        st[key] = -1;
    }
    
    public boolean contains(int key) {
        if(st[key]==key) {
			return true;
		}
		return false;
    }
    */
	
	
	
	/*
	//<문제풀이2 by wangzi6147>
	
	//LinkedList로 풀이한 방법
	
	//new int[1000001]에서 불필요한 공간을 줄여주기 때문에, 메모리 사용이 더욱 효율적.
	
	//다만 문제풀이1보다는 조금 느리다.
	
	//어레이리스트에서 인덱스로 찾는것보다, 링크드리스트에서 주솟값을 꼬리물어서 하나하나씩 찾는게 더 비효율적이기 때문. 
	
	//Runtime: 62 ms, faster than 40.25% of Java online submissions for Design HashSet.
	//Memory Usage: 56.8 MB, less than 33.33% of Java online submissions for Design HashSet.
	
	class Node {
        int val;
        Node next;
        Node(int val) {
            this.val = val;
        }
    }
    
    private Node[] nodes;
    private int len;

    //Initialize your data structure here. 
    public MyHashSet() {
        len = 10000;
        nodes = new Node[len];
        for (int i = 0; i < len; i++) {
            nodes[i] = new Node(-1);
        }
    }
    
    public void add(int key) {
        int index = key % len;
        Node cur = nodes[index];

        while (cur.next != null && cur.next.val != key) { //여기서와 이후에 등장하는 cur.next != null는, 이미 모든 노드를 -1로 초기화해주었기 때문에, null은 linkedlist의 가장 마지막 노드일 때 예외처리다.
            cur = cur.next;
        }
        if (cur.next != null) {
            return;
        }
        cur.next = new Node(key);
    }
    
    public void remove(int key) {
        int index = key % len;
        Node cur = nodes[index];
        while (cur.next != null && cur.next.val != key) {
            cur = cur.next;
        }
        if (cur.next != null) {
            cur.next = cur.next.next;
        }
    }
    
    // Returns true if this set contains the specified element 
    public boolean contains(int key) {
        int index = key % len;
        Node cur = nodes[index];
        while (cur.next != null && cur.next.val != key) {
            cur = cur.next;
        }
        return cur.next != null;
    }
	*/
	
	
	
	
	//<문제풀이3 by naveenkothamasu>
	
	//길이가 10인 리스트에 .add()하다가 길이가 10되면 길이가 두배인 리스트를 다시 만들어서 원랫것을 넣는 것처럼,
	//밑의 방식도 길이가 1000인 linkedlist만들어 놓고, loadFactor만큼의 양이 차면, 원래 길이의 2배인 linkedlist를 새로 만드는 방식
	
	//메모리 최적화에 신경을 쓴 편. 걸리는 시간은 같지만 메모리 사용량이 적다.
	
	//Runtime: 63 ms, faster than 34.00% of Java online submissions for Design HashSet.
	//Memory Usage: 54.4 MB, less than 66.67% of Java online submissions for Design HashSet.
	
	List<Integer>[] container = null;
    int cap = 1000;
    double loadFactor = 0.75;
    int count = 0; 

    //초기화
    public DesignHashSet705() {
        container = new LinkedList[cap];
    }
    
    public void add(int key) {
        if(loadFactor*cap == count){
            count = 0;
            //rehash
            cap *= 2;
            List<Integer>[] oldC = container;
            container = new LinkedList[cap];
            for(int i=0; i < oldC.length; i++){
                List<Integer> list = oldC[i];
                if(list != null){
                    for(int entry : list)
                       this.add(entry); 
                }
            }
        }
        int hash = key % cap;
        if(container[hash] == null)
            container[hash] = new LinkedList<>();
        container[hash].add(key);
        ++count;
    }
    
    public void remove(int key) {
        int hash = key % cap;
        List<Integer> list = container[hash];
        if(list != null){
            Iterator<Integer> itr = list.iterator();
            while(itr.hasNext())
                if(itr.next() == key){
                    itr.remove();
                    --count;
                }
        }
    }
    
    public boolean contains(int key) {
        int hash = key % cap;
        List<Integer> list = container[hash];
        if(list != null){
            Iterator<Integer> itr = list.iterator();
            while(itr.hasNext())
                if(itr.next() == key)
                    return true;
        }
        return false;
    }
	
	
	
}
