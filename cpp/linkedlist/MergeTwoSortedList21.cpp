
 
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



//trial04

//위에 적은거 대로 만들어 봤긴 했는데

//Accepted
//Runtime: 0 ms
//Your input
//[1, 2, 3, 4, 5, 11]
//[6, 7, 8, 9, 10, 12]
//Output
//[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
//Expected
//[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]

//요런건 되는데,

//Input
//[2]
//[1]
//Output
//[2, 1]
//Expected
//[1, 2]

//요런건 또 안됨


class Solution {
public:
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        if (l2 == nullptr) {
            return l1;
        }
        if (l1 == nullptr) {
            return l2;
        }

        ListNode* head = new ListNode;
        head->next = l1;
        int r = l2->val;

        while (l1->next != nullptr) {
            ListNode* prevL1;
            ListNode* prevL2;

            while (l1 != nullptr && l1->val <= r) {
                prevL1 = l1;
                l1 = l1->next;
            }
            prevL1->next = l2;
            while (l2 != nullptr && l2->val < l1->val) {
                prevL2 = l2;
                l2 = l2->next;
            }
            r = l2->val;
            prevL2->next = l1;
        }
        l1->next = l2;
        return head->next;
    }
};


//trial05

//Wrong Answer
//Runtime : 0 ms
//Your input
//[6, 7, 8, 9, 10, 12]
//[1, 2, 3, 4, 5, 11]
//Output
//[6, 7, 8, 9, 10, 11, 12]
//Expected
//[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]

//첫단추 꿰는게 문제

class Solution {
public:
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        if (l2 == nullptr) {
            return l1;
        }
        if (l1 == nullptr) {
            return l2;
        }
        ListNode* nullHead = new ListNode(101);
        ListNode* head = new ListNode;
        head->next = l1;
        int r = l2->val;

        while (l1->next != nullptr) {
            ListNode* prevL1 = nullHead;
            ListNode* prevL2 = nullHead;

            while (l1 != nullptr && l1->val <= r) {
                prevL1 = l1;
                l1 = l1->next;
            }
            if (prevL1->val != 101) prevL1->next = l2;

            while (l2 != nullptr && l2->val < l1->val) {
                prevL2 = l2;
                l2 = l2->next;
            }
            if (l2 != nullptr) r = l2->val;
            if (prevL2->val != 101) prevL2->next = l1;
        }
        l1->next = l2;
        return head->next;
    }
};


//trial06

// [1, 2, 3, 4, 5, 11]
// [6,7,8,9,10,12]

// 얘도 되고

//[6, 7, 8, 9, 10, 12]
//[1, 2, 3, 4, 5, 11]

//심지어 얘도 되는데

//Last executed input
//[5]
//[1, 2, 4]

//얘가안됨. nullptr에서 데이터 참조했다고 에러남. 근데 분명 조건문에 if != nullptr 적어놨는데?


class Solution {
public:
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {

        if (l2 == nullptr) {
            return l1;
        }
        if (l1 == nullptr) {
            return l2;
        }
        ListNode* nullHead = new ListNode(101);
        ListNode* head = new ListNode;

        ListNode* l;
        ListNode* r;

        if (l1->val <= l2->val) {
            l = l1;
            r = l2;
        }
        else {
            l = l2;
            r = l1;
        }
        head->next = l;

        while (l->next != nullptr) {
            ListNode* prevL = nullHead;
            ListNode* prevR = nullHead;

            while (l != nullptr && l->val <= r->val) {
                prevL = l;
                l = l->next;
            }
            if (prevL->val != 101) {
                prevL->next = r;
            }
            while (r != nullptr && r->val <= l->val) {
                prevR = r;
                r = r->next;
            }
            if (prevR->val != 101) {
                prevR->next = l;
            }
        }
        l->next = r;
        return head->next;
    }
};


//solution2

//Runtime: 11 ms, faster than 39.55% of C++ online submissions for Merge Two Sorted Lists.
//Memory Usage : 15 MB, less than 17.12 % of C++ online submissions for Merge Two Sorted Lists.


//아 submit 10번해보니

//solution 1의 성능은
//12,11,20,11,16,12,8,11,12,16 나옴

//solution 2의 성능은
//14,12,12,11,8,8,20,8,11,12 나옴


//solution1의 평균치: 12.9ms
//solution2의 평균치:11.6ms 

//1ms줄음 ㅋㅋㅋ ㅏ...

//위엣것도 O(N)
//이것도 O(N)이라 그런 듯

//solution1은 l1,l2 모든 노드 돌면서 서로 비교해서 더 작은놈은 head에 붙이고
//solution2도 l1,l2 모든 노드 돌면서 서로 비교하긴 하는데, 새로운 head에 붙이는게 아니고 원래 있던 l1,l2 사이에 붙이는 식으로 함
//큰 차이는 없음

//아예 접근법이 달라야 하나봄

//잘 보면 solution2는 20ms 극단값이 있음. l1갔다가 l2바꿔끼웠다가 다시 l1바꿔끼웠다가 다시 l2바꿔끼웠다가 무한반복 하면 저리되나봄

//근데 비교할 때 매번 r->val, l->val 참조하는게 아니라, 미리 뽑아놓은 값이랑 비교하면 더 빨라질 것 같기는 헌데..


class Solution {
public:
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        if (l2 == nullptr) {
            return l1;
        }
        if (l1 == nullptr) {
            return l2;
        }
        ListNode* nullHead = new ListNode(101);
        ListNode* head = new ListNode;

        ListNode* l;
        ListNode* r;

        if (l1->val <= l2->val) {
            l = l1;
            r = l2;
        }
        else {
            l = l2;
            r = l1;
        }
        head->next = l;

        while (l != nullptr) {
            ListNode* prevL = nullHead;
            ListNode* prevR = nullHead;

            while (l != nullptr && l->val <= r->val) {
                prevL = l;
                l = l->next;
            }
            if (prevL->val != 101) {
                prevL->next = r;
                if (l == nullptr) return head->next;
            }

            while (r != nullptr && r->val <= l->val) {
                prevR = r;
                r = r->next;
            }
            if (prevR->val != 101) {
                prevR->next = l;
                if (r == nullptr) return head->next;
            }
        }
        l->next = r;
        return head->next;
    }
};