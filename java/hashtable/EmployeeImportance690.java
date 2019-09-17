package HashTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	
	
	//<Trial01>
	
	//dfs방식으로 풀다가 막힘..
	
	/*
	private int dfs(Employee emp, Map map, int rst){
        //if no subordinates, return itself's value
        if(emp.subordinates.isEmpty()) return (int)map.get(emp.id);
        //loop subordinates using dfs-recursive
        while(!emp.subordinates.isEmpty()){   //생각해보니 subordinates가 이어진게 아니네?
            rst += dfs(emp, map, rst);        //emp의 subordinates를 보내야 하는데 Employee타입이 아니네?
            //remove the subordinate
            emp.subordinates.remove(0);
        }
        return 0;
    }
    
    public int getImportance(List<Employee> employees, int id) {
        int rst = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(Employee emp : employees) map.put(emp.id, emp.importance);
        for(Employee emp : employees){
            if(emp.id == id){
                rst += dfs(emp, map, rst);
            }
        }
        return rst;
    }
    */
    
	//<문제풀이 by miaoz>
	
	//map에 저장할땐, 난 key를 id, value를 그 사원의 value로 저장했는데, 이사람은 value를 그 사원 전체 정보(사원번호, value, subordinates)저장함.
	
	//난 가장 밑에단까지 가서 값을 빼오고, 해당 subordinates를 지울 생각을 했는데, 
	
	//이 사람은 위에부터 본인꺼 value먼저 빼고, subordinates꺼 차례로 빼는 순서를 택함.
	
	//Runtime: 5 ms, faster than 99.68% of Java online submissions for Employee Importance.
	//Memory Usage: 46.9 MB, less than 95.24% of Java online submissions for Employee Importance.
    
    
	 private int dfs(Map<Integer, Employee> map, int id){
	        Employee curr = map.get(id);
	        int rst = curr.importance;
	        for(int sub : curr.subordinates) rst += dfs(map, sub);
	        return rst;
	    }
	    
	    public int getImportance(List<Employee> employees, int id) {
	        Map<Integer, Employee> map = new HashMap<>();
	        for(Employee emp : employees) map.put(emp.id, emp);
	        return dfs(map, id);
	    }
}

