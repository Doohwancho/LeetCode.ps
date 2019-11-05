/*
	At a lemonade stand, each lemonade costs $5. 
	
	Customers are standing in a queue to buy from you, and order one at a time (in the order specified by bills).
	
	Each customer will only buy one lemonade and pay with either a $5, $10, or $20 bill.  You must provide the correct change to each customer, so that the net transaction is that the customer pays $5.
	
	Note that you don't have any change in hand at first.
	
	Return true if and only if you can provide every customer with correct change.
	
	 
	
	Example 1:
	
	Input: [5,5,5,10,20]
	Output: true
	Explanation: 
	From the first 3 customers, we collect three $5 bills in order.
	From the fourth customer, we collect a $10 bill and give back a $5.
	From the fifth customer, we give a $10 bill and a $5 bill.
	Since all customers got correct change, we output true.
	Example 2:
	
	Input: [5,5,10]
	Output: true
	Example 3:
	
	Input: [10,10]
	Output: false
	Example 4:
	
	Input: [5,5,10,10,20]
	Output: false
	Explanation: 
	From the first two customers in order, we collect two $5 bills.
	For the next two customers in order, we collect a $10 bill and give back a $5 bill.
	For the last customer, we can't give change of $15 back because we only have two $10 bills.
	Since not every customer received correct change, the answer is false.
	 
	
	Note:
	
	0 <= bills.length <= 10000
	bills[i] will be either 5, 10, or 20.
	
	
	
	
	<문제>
	
	[5,5,5,10,20]
	
	어레이의 숫자는, 손님이 레몬에이드를 사는데 지불한 가격이다.
	
	레몬에이드는 5달러 이기 때문에, 5달러를 받으면 퉁이지만, 10달러를 받거나 20달러를 받으면 내가 가진 돈으로 거스름돈을 주어야 한다.
	
	10달러를 받으면 5달러를 거스름돈으로 주어야 하고,
	
	20달러를 받았으면, 10달러 하나 5달러 하나를 주거나, 5달러짜리 3개를 주면 된다.
	
	만약 레몬에이드를 팔기 시작한 시점에 내 돈이 0원이여서, 레몬에이드를 판 돈으로만 거스름돈을 주어야 한다고 가정한다면
	
	거스름 돈을 모두 지불할 수 있다면 true를 반환하고, 지불할 수 없다면 false를 반환하라.
 */

package Greedy;

public class LemonadeChange860 {
	
	/*
	//<문제풀이1>
	
	//5달러,10달러,20달러짜리 몇개를 key:value로 표시하는 dictionary보다 빠른 int[]를 사용. 
	
	//Runtime: 2 ms, faster than 91.00% of Java online submissions for Lemonade Change.
	//Memory Usage: 39.9 MB, less than 100.00% of Java online submissions for Lemonade Change.
	
	public static boolean lemonadeChange(int[] bills) {
        int[] wallet = new int[21];
        
		for(int bill : bills) {
			wallet[bill]++;
			if(bill == 20) {       //20달러 지폐를 받았는데
                if(wallet[10]>0){  //10달러짜리가 있다면,
                  	wallet[10]--;  //10달러짜리 하나
				    wallet[5]--;   //5달러짜리 하나 거스름돈으로 주고                   
                }
                else{ 			   //10달러짜리가 없다면
                    wallet[5] -= 3;//5달러짜리 3개를 거스름돈으로 줌.
                }
			}
			else if(bill == 10) {  //만약 10달러짜리 지폐를 받았다면,
				wallet[5]--;       //5달러짜리 지폐 하나만 줌
			}
            if(wallet[10] < 0 || wallet[5] < 0) return false; //매 loop마다 5,10짜리 지폐들이 -인경우, 본인 소유보다 더 주었다는 것을 의미 -> false            
		}
		return true;
    }
	*/
	
	//<문제풀이 by lee215>
	
	//int[]안쓰고 five와 ten 변수만 씀. 어짜피 20달러짜리는 받아도 거스름돈으로 못주니까 애초에 신경쓸 필요 없음. 
	//또한 int[21]만들면 5와 10을 제외한 19개의 메모리공간 낭비 + wallet[5]이나 wallet[10]할때 배열에서 찾아쓰는것에 시간뺏김 방지.
	
	//Runtime: 2 ms, faster than 91.00% of Java online submissions for Lemonade Change.
	//Memory Usage: 38.8 MB, less than 100.00% of Java online submissions for Lemonade Change.
	
    public static boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int i : bills) {
            if (i == 5) five++;
            else if (i == 10) {five--; ten++;}
            else if (ten > 0) {ten--; five--;}
            else five -= 3;
            if (five < 0) return false;
        }
        return true;
    }
	
	public static void main(String[] args) {
		//int[] bills = {5,5,5,10,20};
		int[] bills = {5,5,10,10,20};
		System.out.println(lemonadeChange(bills));
	}
}
