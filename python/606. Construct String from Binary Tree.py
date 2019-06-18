"""
You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.

The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis pairs that don't affect the one-to-one mapping relationship between the string and the original binary tree.

Example 1:
Input: Binary tree: [1,2,3,4]
       1
     /   \
    2     3
   /    
  4     

Output: "1(2(4))(3)"

Explanation: Originallay it needs to be "1(2(4)())(3()())", 
but you need to omit all the unnecessary empty parenthesis pairs. 
And it will be "1(2(4))(3)".
Example 2:
Input: Binary tree: [1,2,3,null,4]
       1
     /   \
    2     3
     \  
      4 

Output: "1(2()(4))(3)"

Explanation: Almost the same as the first example, 
except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.
"""
#문제

#preorder traversing 방법으로 트리를 순회하면서, 자식들을 괄호안에 담는 문자열을 만들되, 빈 문자열은 제거하라.
#단, left child의 괄호가 빈 경우는 남겨둔다.


#문제풀이

#재귀방식의 preorder traversing으로 순회한다.
#n, l, r = str(t.val), "("+self.tree2str(t.left)+")", "("+self.tree2str(t.right)+")"
#위 코드에서 실행순서는 n(node) -> l(left) -> r(right) 인데, 해당 노드의 값을 먼저 받고, left right 순으로 진행하기 때문이다.
#매 round마다 left child와 right child에 괄호를 씌워주고, return시 left child의 값이 없다면 괄호를 남기는 경우의 수를 추가해준다.






# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def tree2str(self, t: TreeNode) -> str:
        if not t: return ""
        n, l, r = str(t.val), "("+self.tree2str(t.left)+")", "("+self.tree2str(t.right)+")"
        return n+l+r if t.left and t.right else \
               n+l   if t.left and not t.right else \
               n+"()"+r if not t.left and t.right else \
               n
