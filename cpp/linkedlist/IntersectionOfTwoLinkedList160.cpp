#include <iostream>
#include <map>
#include <hash_map>
#include <list>

using namespace std;


struct ListNode {
	int val;
	ListNode* next;
	ListNode(int x) : val(x), next(NULL) {}
};


//둘이 붙인 다음에 처음부터 loop돌면서 set에 담고 !set.add() 하는거 return하는 식으로 하면, A랑 B붙일 때 원본훼손해서 안되잖아?

//O(N^2), O(N*M)말고 간지나는 방법 없나



//trial01

//너무 비효율 + sudo code

class Solution {
public:
    ListNode* getIntersectionNode(ListNode* headA, ListNode* headB) {
        hash_map<int, list<ListNode*>> map;
        ListNode* A = headA;
        ListNode* B = headB;
        ListNode* rst = new ListNode(0);

        while (A != nullptr && B != nullptr) {
            if (A != nullptr) {
                if (map.find(A->val)) {
                    if (map.find(A->val).contains(A) {
                        return new ListNode(A->val);
                    }
                    map.find(A->val).add(A);
                }
                else {
                    map.insert(make_pair(A->val), A);
                }
                A = A->next;
            }
            if (B != nullptr) {
                if (map.find(B->val)) {
                    if (map.find(B->val).contains(B) {
                        return new ListNode(B->val);
                    }
                    map.find(B->val).add(B);
                }
                else {
                    map.insert(make_pair(B->val), B);
                }
                B = B->next;
            }
        }

        return rst;
    }
};


//solution1


//Runtime: 68 ms, faster than 28.37% of C++ online submissions for Intersection of Two Linked Lists.
//Memory Usage : 21.3 MB, less than 5.30 % of C++ online submissions for Intersection of Two Linked Lists.

//time complexity: O(N+M)
//space complexity: O(N)


//cpp에선 map.put()안하고 map[A] = B; 이런식으로 넣는구나


//trial01을 좀 더 괜찮은 방법으로 바꾼 것. 근데 아직 성능이 구림

//A를 다 돌기 전에 찾는 방법 없으려나?


class Solution {
public:
    ListNode* getIntersectionNode(ListNode* headA, ListNode* headB) {
        map<ListNode*, int> map;
        ListNode* A = headA;
        ListNode* B = headB;

        while (A) {
            map[A]++;
            A = A->next;
        }
        while (B) {
            if (map[B] > 0) {
                return new ListNode(B->val);
            }
            B = B->next;
        }

        return nullptr;
    }
};