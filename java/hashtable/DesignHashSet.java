package julyChallenge;

import java.util.LinkedList;
import java.util.List;

public class DesignHashSet {

	
	//<Trial01>
	
	//아 쓰빠!!!! 개아깝네? 2개 나갔네?
	
	//binary search

	
//	["MyHashSet","contains","remove","add","add","contains","remove","contains","contains","add","add","add","add","remove","add","add","add","add","add","add","add","add","add","add","contains","add","contains","add","add","contains","add","add","remove","add","add","add","add","add","contains","add","add","add","remove","contains","add","contains","add","add","add","add","add","contains","remove","remove","add","remove","contains","add","remove","add","add","add","add","contains","contains","add","remove","remove","remove","remove","add","add","contains","add","add","remove","add","add","add","add","add","add","add","add","remove","add","remove","remove","add","remove","add","remove","add","add","add","remove","remove","remove","add","contains","add"]
//	[[],[72],[91],[48],[41],[96],[87],[48],[49],[84],[82],[24],[7],[56],[87],[81],[55],[19],[40],[68],[23],[80],[53],[76],[93],[95],[95],[67],[31],[80],[62],[73],[97],[33],[28],[62],[81],[57],[40],[11],[89],[28],[97],[86],[20],[5],[77],[52],[57],[88],[20],[48],[42],[86],[49],[62],[53],[43],[98],[32],[15],[42],[50],[19],[32],[67],[84],[60],[8],[85],[43],[59],[65],[40],[81],[55],[56],[54],[59],[78],[53],[0],[24],[7],[53],[33],[69],[86],[7],[1],[16],[58],[61],[34],[53],[84],[21],[58],[25],[45],[3]]

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
	
	//0 3 7 11 15 16 19 20 23 24 25 28 31 32 33 34 40 42 43 48 41 43 49 50 52 53 54 55 56 57 59 61 62 67 68 73 76 77 78 80 81 84 82 87 88 95 89 
	
	//보니까 잘 드가다가 중간에 이상한게 껴있네?
	
	List<Integer> list;

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
	
	
	//<Trial03>
	
	//어씨 add드디어 고침 근데 52th가 말썽이네?
	
	//0 3 7 11 15 16 19 20 23 24 25 28 31 32 33 34 40 41 42 43 48 49 50 52 53 54 55 56 57 59 61 62 67 68 73 76 77 78 80 81 82 84 87 88 89 95
	
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
	            if((list.size() > 0 && list.get(l) == key) || (l > 0 && list.get(l-1) == key)){
	                return;
	            }
	            if(l == list.size()-1 && list.get(l) < key){
	                list.add(key);
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
	}
	
	//<문제풀이1>
	
	//binary search
	
	//찢.었.따
	
	//왠지 구현해도 TLE뜰거같았는데 
	
	//구현은 해서 쥰~내 뿌듯하네 와 시바 이거만들려고 몇시간을 쓴거야
	
	//binary search는 레알 전설이다.
	
	//Runtime: 2144 ms
	//Memory Usage: 47.6 MB
	
	
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
	        if((list.size() > 0 && list.get(l) == key) || (l > 0 && list.get(l-1) == key)){
	            return;
	        }
	        if(l == list.size()-1 && list.get(l) < key){
	            list.add(key);
	        } else {
	            list.add(l, key);
	        }
	    }

	    public void remove(int key) {
	        int l = 0, r = list.size() - 1;
	        while (l <= r) {
	            int m = (l + r) / 2;
	            if(list.get(m) == key){
	                list.remove(m);
	                return;
	            }
	            else if (list.get(m) > key) {
	                r = m - 1;
	            } else {
	                l = m + 1;
	            }
	        }
	    }

	    /** Returns true if this set contains the specified element */
	    public boolean contains(int key) {
	        int l = 0, r = list.size() - 1;
	        while (l <= r) {
	            int m = (l + r) / 2;
	            if (list.get(m) == key) {
	                return true;
	            } else if (list.get(m) > key) {
	                r = m - 1;
	            } else {
	                l = m + 1;
	            }
	        }
	        return false;
	    }
	}
	
	
	
	
	
	//<문제풀이1 by naveen_kothamasu>
	
	//75%가 찰때마다 사이즈 2배씩 늘림
	
	//Runtime: 14 ms
	//Memory Usage: 47.5 MB
	
	class MyHashSet {
	    List<Integer>[] container = null;
	    int cap = 1000;
	    double loadFactor = 0.75;
	    int count = 0; 
	    /** Initialize your data structure here. */
	    public MyHashSet() {
	        container = new LinkedList[cap];
	    }
	    
	    public void add(int key) {
	        if(contains(key)) return;
	        if(loadFactor*cap == count){
	            count = 0;
	            //rehash
	            cap *= 2; //사이즈 2배 뾰뵤뵹?
	            List<Integer>[] oldC = container;
	            container = new LinkedList[cap];
	            for(int i=0; i < oldC.length; i++){
	                List<Integer> list = oldC[i];
	                if(list != null){
	                    for(int entry : list)
	                       this.add(entry); //다 일일이 재귀로 다시넣음. 이때 hash코드가 key% new cap이니까 다른숫자로 바뀜.
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
	    
	    /** Returns true if this set contains the specified element */
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
	
}
