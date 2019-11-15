/*
	You are given a data structure of employee information, which includes the employee's unique id, his importance value and his direct subordinates' id.
	
	For example, employee 1 is the leader of employee 2, and employee 2 is the leader of employee 3. They have importance value 15, 10 and 5, respectively. Then employee 1 has a data structure like [1, 15, [2]], and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []]. Note that although employee 3 is also a subordinate of employee 1, the relationship is not direct.
	
	Now given the employee information of a company, and an employee id, you need to return the total importance value of this employee and all his subordinates.
	
	Example 1:
	
	Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
	Output: 11
	Explanation:
	Employee 1 has importance value 5, and he has two direct subordinates: employee 2 and employee 3. They both have importance value 3. So the total importance value of employee 1 is 5 + 3 + 3 = 11.
	 
	
	Note:
	
	One employee has at most one direct leader and may have several subordinates.
	The maximum number of employees won't exceed 2000.
	
	
	
	
	
	<문제>
	
	사원정보 리스트와 아이디가 다음과 같이 주어진다.
	
	리스트 = [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]]
	아이디 = 1
	
	리스트안의 각 노드는 3가지 값을 가지고 있는데,
	
	첫번째 값은 사원id, 두번째 값은 importance, 세번째 값은 부하직원이다.
	
	예를들어, [1, 5, [2, 3]]의 경우,
	
	사원id가 1인 사람은 5의 중요도를 가지고 있고, 부하직원의 아이디는 2와 3이다.
	
	부하직원의 정보를 보면
	
	[2, 3, []], [3, 3, []]
	
	요 두명이다. 
	
	id2인 부하직원의 중요도는 3, id3인 부하직원의 중요도는 3이고, 해당 부하직원들은 그 밑의 부하직원이 없다. 세번쨰 칸이 빈 리스트이기 때문.
	
	이렇다고 할 때, 아이디 n을 넣으면, 해당 아이디를 가진 직원 + 그 직원 부하직원들의 중요도 총 합을 반환하라.
	
	예를들어, 이 경우, 1을 넣으면 5+3+3해서 11이되고, 2를 넣으면 3, 3을 넣으면 3이 된다.
 */

package DepthFirstSearch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class EmployeeImportance690 {
	
	class Employee {
	    // It's the unique id of each node;
	    // unique id of this employee
	    public int id;
	    // the importance value of this employee
	    public int importance;
	    // the id of direct subordinates
	    public List<Integer> subordinates;
	};
	/*
	//<문제풀이1>
	  
	//매 loop마다 필요 id를 set에 더하는 방법.
	
	//set에 찾고자 하는 root.id를 담아놓고, for문으로 전체를 돌면서 
	
	//해당 아이디에 부합하는 직원(set.contains(직원.id))의 importance를 더해주고 해당 직원의 값은 이미 더했으니 set에서 그 직원id를 빼준다.
	
	//그 후,그 직원의 부하직원 id들을 for문으로 set에 더해준다.
	
	//set에 id가 없을 때 까지 반복.
	
	//Runtime: 9 ms, faster than 24.37% of Java online submissions for Employee Importance.
	//Memory Usage: 47.7 MB, less than 95.24% of Java online submissions for Employee Importance.
	
	public static int getImportance(List<Employee> employees, int id) {
        if(employees == null) return 0;
        Set<Integer> set = new HashSet<>();
        set.add(id);
        int totalValue = 0;
        
        while(set.size() > 0){
            for(int i = 0; i < employees.size(); i++){
            Employee node = employees.get(i);
            if(set.contains(node.id)){
                set.remove(node.id);
                totalValue += node.importance;
                for(int j = 0; j < node.subordinates.size(); j++){
                    set.add(node.subordinates.get(j));
                }
            }
        }    
        }
        return totalValue;
    }
	*/
	
	/*
	//<문제풀이2 by xuyirui>
	
	//크... 아름답다.
	
	//Runtime: 5 ms, faster than 99.32% of Java online submissions for Employee Importance.
	//Memory Usage: 47.2 MB, less than 95.24% of Java online submissions for Employee Importance.
	
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer,Employee> map = new HashMap<>();
        for(Employee e : employees){
            map.put(e.id, e);
        }
        return getImportanceHelper(employees, id, map);
    }
    
    private int getImportanceHelper(List<Employee> employees, int id, Map<Integer,Employee> map){
        Employee node = map.get(id); //문제풀이1에선 전체loop해서 찾았는데, map.get(id)로 찾으니까 간-편
        int values = node.importance;
        for(int subord : node.subordinates){
            values += getImportanceHelper(employees, subord, map);  //부하직원 아이디만 넘겨줌. 개꿀    
        }
        return values;
    }
    */
	
	//<문제풀이3 by xuyirui>
	
	//BFS
	
	//문제풀이2에선 부하직원 id를 파라미터로 보내서 재귀돌렸다면, 여긴 queue에 담아서 .offer()로 하나씩 뽑아씀.
    
	//Runtime: 6 ms, faster than 54.94% of Java online submissions for Employee Importance.
	//Memory Usage: 46.5 MB, less than 95.24% of Java online submissions for Employee Importance.
    
	public int getImportance(List<Employee> employees, int id) {
        int total = 0; 
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        Queue<Employee> queue = new LinkedList<>();
        queue.offer(map.get(id));
        while (!queue.isEmpty()) {
            Employee current = queue.poll();
            total += current.importance;
            for (int subordinate : current.subordinates) {
                queue.offer(map.get(subordinate));
            }
        }
        return total;
    }
	
}
