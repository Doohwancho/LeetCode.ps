
 
 struct ListNode {
      int val;
      ListNode *next;
      ListNode() : val(0), next(nullptr) {}
      ListNode(int x) : val(x), next(nullptr) {}
      ListNode(int x, ListNode *next) : val(x), next(next) {}
  };
 

 //trial01 - time limit exceeded

 //The program was checked, and found you were accessing memory beyond the declared variables. I skimmed and didn't see it right away, but that is definitely the problem.


class Solution {
public:
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        ListNode* node;
        ListNode* rst = node;
        while (l1 != NULL && l2 != NULL) {
            int l = l1 == NULL ? -101 : l1->val;
            int r = l2 == NULL ? -101 : l2->val;
            if (l < r) {
                ListNode* tmp = new ListNode;
                tmp->val = r;
                node->next = tmp;
                node = tmp;
            }
            else {
                ListNode* tmp = new ListNode;
                tmp->val = l;
                node->next = tmp;
                node = tmp;
            }
        }
        node->next = NULL;
        return rst;
    }
};



//trial02

//메모리 에러에서 벗어남

//ListNode* node; 요롷게 선언하고 쓰려고 했는데, 값 할당하려니까 메모리 부족하다고 나와서 new ListNode로 할당해줌

//또한 포인터를 원래는 그냥 NULL썼었는데 cpp에 nullptr 따로 있는 거 같아 이렇게 씀

class Solution {
public:
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        ListNode* head = new ListNode;
        ListNode* node = new ListNode;
        head = node;
        while (l1 != nullptr && l2 != nullptr) {
            int l = l1 == nullptr ? -101 : l1->val;
            int r = l2 == nullptr ? -101 : l2->val;
            if (l < r) {
                ListNode* tmp = new ListNode;
                tmp->val = r;
                node->next = tmp;
                node = tmp;
                l2 = l2->next;
            }
            else {
                ListNode* tmp = new ListNode;
                tmp->val = l;
                node->next = tmp;
                node = tmp;
                l1 = l1->next;
            }
        }
        node->next = nullptr;
        return head;
    }
};


//trial03 - 거의 다옴

//Your input
//[1, 2, 4]
//[1, 3, 4]

//Output
//[1, 1, 2, 3, 4]

//Expected
//[1, 1, 2, 3, 4, 4]

//맨 마지막에 왜 4가 안들어가지?

class Solution {
public:
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        ListNode* head = new ListNode;
        ListNode* node = new ListNode;
        head = node;
        while (l1 != nullptr && l2 != nullptr) {
            int l = l1 == nullptr ? 101 : l1->val;
            int r = l2 == nullptr ? 101 : l2->val;
            if (l < r) {
                ListNode* tmp = new ListNode;
                tmp->val = l;
                node->next = tmp;
                node = tmp;
                l1 = l1->next;
            }
            else {
                ListNode* tmp = new ListNode;
                tmp->val = r;
                node->next = tmp;
                node = tmp;
                l2 = l2->next;
            }
        }
        node->next = nullptr;
        return head->next;
    }
};


//solution1

//Runtime: 12 ms, faster than 36.80 % of C++ online submissions for Merge Two Sorted Lists.
//Memory Usage : 15.1 MB, less than 10.28 % of C++ online submissions for Merge Two Sorted Lists.

//and가 아니고 or써야 됬었음

//아 근데 성능이 좀 구리네?

//일단 l2맨 처음 노드의 값을 빼. 그리고 l1을 돌면서l2에서 뺀 애보다 클때까지 돌다가, 큰애가 나왔다 싶으면, l2에서 이번엔 l1큰애보다 큰애가 나올때까지 돌아
//그리고 l2애를 한번에 l1 현재노드 바로 직전에 샥 붙이는거지

//아 근데 이 방법은 원래 방법보다 매 loop마다 l1,l2 비교 안해서 좋기는 한데, 파라미터의 원래값을 훼손시키잖아?




class Solution {
public:
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        ListNode* head = new ListNode;
        ListNode* node = new ListNode;
        head = node;
        while (l1 != nullptr || l2 != nullptr) {
            int l = l1 == nullptr ? 101 : l1->val;
            int r = l2 == nullptr ? 101 : l2->val;
            if (l < r) {
                ListNode* tmp = new ListNode;
                tmp->val = l;
                node->next = tmp;
                node = tmp;
                l1 = l1->next;
            }
            else {
                ListNode* tmp = new ListNode;
                tmp->val = r;
                node->next = tmp;
                node = tmp;
                l2 = l2->next;
            }
        }
        node->next = nullptr;
        return head->next;
    }
};

