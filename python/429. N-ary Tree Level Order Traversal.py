'''
#문제

트리의 level별로 값을 리턴하라

 

#문제풀이

#step01 - 변수 q와 rst를 만들어, 각각 [root]와 빈 리스트를 넣어준다. 변수 q는 while문으로 돌면서 

#step02 - q를 while문으로 돌면서, 먼저 해당 노드의 값을 rst에 append()를써서 리스트 형태로 넣어준다.(level1)

#step03 - comprehension과 이중 for문을 사용하여, 현재 위치해 있는 노드의 자식들을 리스트에 담아 q에 넣는다.
(level1의 자식들이므로 level2가 q가 된다. while문을 돌면서, level2에 위치하는 모든 노드의 값들이 rst에 넣어지면, 다시 level2의 자식들인 level3의 노드들이 q가되고, 이는 반복된다.)
'''


"""
# Definition for a Node.
class Node:
    def __init__(self, val, children):
        self.val = val
        self.children = children
"""
class Solution:
    def levelOrder(self, root: 'Node') -> List[List[int]]:
        q, rst = [root], []
        if root:
            while q:
                rst.append([node.val for node in q])
                q = [child for node in q for child in node.children]
            return rst
        else:
            return []
