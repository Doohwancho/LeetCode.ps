package dynamicProgramming;

import java.util.Arrays;

public class CountSortedVowelStrings1641 {
	
	//<문제풀이1>
	
	//It's all about finding a pattern.
	
	
	//let's start with simple one.
	
	//when n = 1, we get 
	
	//[
	//'a',
	//'e',
	//'i',
	//'o',
	//'u'
	//] 
	
	//= [1,1,1,1,1] -> total of 5.
	
	//when n = 2, we get
	
	//[
	//'aa','ae','ai','ao','au',
	//'ee','ei','eo','eu',
	//'ii','io','iu',
	//'oo','ou',
	//'uu'
	//]
	
	//=[5,4,3,2,1] -> total of 15.
	
	
	
	//then, what about n = 3?
	
	//it gets complicated after 2. so again, let's start with easy one and try to find a pattern.
	
	//[
	//'a??' ...,
	//'e??' ...,
	//'i??' ...,
	//'o??' ...,
	//'uuu'
	//]
	
	//'u' part will always be 1. because u is last alphabet, lexicographically speaking.
	
	//next part is 'o'. how many cases can 'o' have when n=3?
	
	//we can find a clue from previous step(when n = 2).
	
	//when n = 2, we had a case like this.
	
	//[
	//'aa','ae','ai','ao','au',
	//'ee','ei','eo','eu',
	//'ii','io','iu',
	//'oo','ou',
	//'uu'
	//]
	
	//now, we're looking for anything that can be placed after 'o', which are,
	
	//[
	//'oo','ou',
	//'uu'
	//]
	
	//total of 3.
	
	
	//what about 'i' when n = 3?
	
	//[
	//'ii','io','iu',
	//'oo','ou',
	//'uu'
	//]
	
	
	//total of 6.
	
	//now, we found a pattern.
	
	//when n = x, and [first, second, third, fourth, fifth],
	
	//if we want third of when n = x, 
	
	//we go back to when n = x-1, and add everything after third.
	
	//so, third(n=3) is, third+fourth+fifth(n=2).
	
	//similarly, if we want first(n=3),
	
	//we go back to when n = x-1, in this case n =2,
	
	//and add everything after first, including the first.
	
	//so, first(n=3) is, first+second+third+fourth+fifth(n=2).
	
	
	
	//when n = 1,
	//[1,1,1,1,1]
	
	//when n = 2,
	//[5,4,3,2,1]
	
	//when n = 3,
	//[15,10,6,3,1]
	
	//when n = 4,
	//[35,20,10,4,1]
	
	//... and so on.
	
	
	
	
	//one little trick with solution is that it reversed the order of alphabet, 
	
	//from ['a','e','i','o','u'] to ['u','o','i','e','a']. 
	
	//reversing the order of alphabet doesn't affect result since all we want is total number of cases when n is certain number.
	
	//so after reversing the order, it becomes,
	
	//when n = 1,
	//[1,1,1,1,1]
	
	//when n = 2,
	//[1,2,3,4,5]
	
	//when n = 3,
	//[1,3,6,10,15]
	
	//when n = 4,
	//[1,4,10,20,35]
	
	//... and so on.
	
	
	//one last trick is, that total number of cases when n = 3, is the last number of when n = 4.
	
	//equally speaking, total number of cases when n = x, is the last number of when n = x+1.
	
	//for example, when n = 3, total is 35(1+3+6+10+15), which is the last number of n = 4.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Count Sorted Vowel Strings.
	//Memory Usage: 35.8 MB, less than 65.80% of Java online submissions for Count Sorted Vowel Strings.
	
    public int countVowelStrings(int n) {
        int[] c = new int[5];
        Arrays.fill(c, 1);
        
        for(int i = 0; i < n; i++){
            for(int j = 1; j < 5; j++){
                c[j] += c[j-1];
            }    
        }
        
        return c[4];
    }
}
