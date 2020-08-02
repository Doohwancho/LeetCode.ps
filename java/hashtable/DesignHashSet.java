package julyChallenge;

import java.util.LinkedList;
import java.util.List;

public class DesignHashSet {

	
	//<Trial01>
	
	//아 쓰빠!!!! 개아깝네? 2개 나갔네?
	
	//binary search

	
	//["MyHashSet","contains","remove","add","add","contains","remove","contains","contains","add","add","add","add","remove","add","add","add","add","add","add","add","add","add","add","contains","add","contains","add","add","contains","add","add","remove","add","add","add","add","add","contains","add","add","add","remove","contains","add","contains","add","add","add","add","add","contains","remove","remove","add","remove","contains","add","remove","add","add","add","add","contains","contains","add","remove","remove","remove","remove","add","add","contains","add","add","remove","add","add","add","add","add","add","add","add","remove","add","remove","remove","add","remove","add","remove","add","add","add","remove","remove","remove","add","contains","add"]
	//[[],[72],[91],[48],[41],[96],[87],[48],[49],[84],[82],[24],[7],[56],[87],[81],[55],[19],[40],[68],[23],[80],[53],[76],[93],[95],[95],[67],[31],[80],[62],[73],[97],[33],[28],[62],[81],[57],[40],[11],[89],[28],[97],[86],[20],[5],[77],[52],[57],[88],[20],[48],[42],[86],[49],[62],[53],[43],[98],[32],[15],[42],[50],[19],[32],[67],[84],[60],[8],[85],[43],[59],[65],[40],[81],[55],[56],[54],[59],[78],[53],[0],[24],[7],[53],[33],[69],[86],[7],[1],[16],[58],[61],[34],[53],[84],[21],[58],[25],[45],[3]]

	//52th, 64th error. 둘다 contains()에서 에러.
	
	//my answer : [null,false,null,null,null,false,null,true,false,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,false,null,true,null,null,true,null,null,null,null,null,null,null,null,true,null,null,null,null,false,null,false,null,null,null,null,null,false,null,null,null,null,true,null,null,null,null,null,null,false,true,null,null,null,null,null,null,null,false,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,false,null]
	
	//asdfasdf : [null,false,null,null,null,false,null,true,false,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,false,null,true,null,null,true,null,null,null,null,null,null,null,null,true,null,null,null,null,false,null,false,null,null,null,null,null,"false",null,null,null,null,true,null,null,null,null,null,null,"false",true,null,null,null,null,null,null,null,false,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,false,null]
	//           [null,false,null,null,null,false,null,true,false,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,false,null,true,null,null,true,null,null,null,null,null,null,null,null,true,null,null,null,null,false,null,false,null,null,null,null,null,false,null,null,null,null,true,null,null,null,null,null,null,true,true,null,null,null,null,null,null,null,false,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,false,null]
	//leetcode : [null,false,null,null,null,false,null,true,false,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,false,null,true,null,null,true,null,null,null,null,null,null,null,null,true,null,null,null,null,false,null,false,null,null,null,null,null,true,null,null,null,null,true,null,null,null,null,null,null,true,true,null,null,null,null,null,null,null,false,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,false,null]

	//맨 마지막 add하고 나온 linked list
	
	//48 82 84 41 24 31 32 33 33 34 40 40 42 43 43 49 50 52 53 53 54 56 57 57 59 59 61 62 67 67 68 73 76 77 78 80 81 87 7 19 95 23 81 28 89 11 28 88 20 20 81 15 0 24 7 7 16 25 3 

	//어씨바 순서 왜이래 
	
	//add를 잘못만들었네
	
	class MyHashSet {

		List<Integer> list;

		/** Initialize your data structure here. */
		public MyHashSet() {
			list = new LinkedList<>();
		}

		public void add(int key) {
			int l = 0, r = list.size() - 1;
			while (l < r) {
				int m = (l + r) / 2;
				if (list.get(m) > key) {
					r = m;
				} else {
					l = m + 1;
				}
			}
			if (l == 0) {
				list.add(key);
			} else if (l < list.size() && list.get(l) == key) {
				return;
			} else {
				list.add(l, key);
			}
		}

		public void remove(int key) {
			int l = 0, r = list.size() - 1;
			while (l < r) {
				int m = (l + r) / 2;
				if (list.get(m) > key) {
					r = m;
				} else {
					l = m + 1;
				}
			}
			if (l < list.size() && list.get(l) == key) {
				list.remove(l);
			}
		}

		/** Returns true if this set contains the specified element */
		public boolean contains(int key) {
			int l = 0, r = list.size() - 1;
			while (l < r) {
				int m = (l + r) / 2;
				if (list.get(m) == key) {
					return true;
				} else if (list.get(m) > key) {
					r = m;
				} else {
					l = m + 1;
				}
			}
			return l < list.size() ? list.get(l) == key : false;
		}
	}
	
	//<Trial02>
	
	//아~~~~~ add는 고침
	
	//같은 input에서 막힘.
	
	//64th는 해결됬는데 52th에서 막힘
	
	List<Integer> list;

	/** Initialize your data structure here. */
	public MyHashSet() {
		list = new LinkedList<>();
	}

	public void add(int key) {
		int l = 0, r = list.size() - 1;
		while (l < r) {
			int m = (l + r) / 2;
			if (list.get(m) > key) {
				r = m;
			} else {
				l = m + 1;
			}
		}
        if(l == list.size()-1){
            if((l > 0 && list.get(l-1) == key) || list.get(l) == key){
                return;
            }
            list.add(key);
        } else {
            if(l > 0 && list.get(l-1) == key){
                return;
            }
            list.add(l, key);
        }
	}

	public void remove(int key) {
		int l = 0, r = list.size() - 1;
		while (l < r) {
			int m = (l + r) / 2;
			if (list.get(m) > key) {
				r = m;
			} else {
				l = m + 1;
			}
		}
        if(l < list.size() && list.get(l) == key){
            list.remove(l);
            return;
        }
	}

	/** Returns true if this set contains the specified element */
	public boolean contains(int key) {
        
		int l = 0, r = list.size() - 1;
		while (l < r) {
			int m = (l + r) / 2;
			if (list.get(m) == key) {
				return true;
			} else if (list.get(m) > key) {
				r = m;
			} else {
				l = m + 1;
			}
		}
		return l < list.size() ? list.get(l) == key : false;
	}
	
	/*
	
	private static void test(List<Integer> list, int key) {
		int l = 0, r = list.size() - 1;
		while (l < r) {
			int m = (l + r) / 2;
			if (list.get(m) > key) {
				r = m;
			} else {
				l = m + 1;
			}
		}
		System.out.println("l: "+l); 
		list.add(l, key);
		
	}
	
	public static void main(String[] args) {
		List<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(3);
		list.add(5);
		list.add(6);
		
		for(int ele : list) System.out.print(ele+" ");
		System.out.println();
		
		test(list, 3);
		for(int ele : list) System.out.print(ele+" ");
		System.out.println();
	}
	*/
}
