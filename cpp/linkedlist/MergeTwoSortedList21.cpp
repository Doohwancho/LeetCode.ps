
 
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

//�޸� �������� ���

//ListNode* node; ���� �����ϰ� ������ �ߴµ�, �� �Ҵ��Ϸ��ϱ� �޸� �����ϴٰ� ���ͼ� new ListNode�� �Ҵ�����

//���� �����͸� ������ �׳� NULL����µ� cpp�� nullptr ���� �ִ� �� ���� �̷��� ��

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


//trial03 - ���� �ٿ�

//Your input
//[1, 2, 4]
//[1, 3, 4]

//Output
//[1, 1, 2, 3, 4]

//Expected
//[1, 1, 2, 3, 4, 4]

//�� �������� �� 4�� �ȵ���?

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

//and�� �ƴϰ� or��� �����

//�� �ٵ� ������ �� ������?

//�ϴ� l2�� ó�� ����� ���� ��. �׸��� l1�� ���鼭l2���� �� �ֺ��� Ŭ������ ���ٰ�, ū�ְ� ���Դ� ������, l2���� �̹��� l1ū�ֺ��� ū�ְ� ���ö����� ����
//�׸��� l2�ָ� �ѹ��� l1 ������ �ٷ� ������ �� ���̴°���

//�� �ٵ� �� ����� ���� ������� �� loop���� l1,l2 �� ���ؼ� ����� �ѵ�, �Ķ������ �������� �Ѽս�Ű�ݾ�?




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

//���� ������ ��� ����� �ñ� �ߴµ�

//Accepted
//Runtime: 0 ms
//Your input
//[1, 2, 3, 4, 5, 11]
//[6, 7, 8, 9, 10, 12]
//Output
//[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
//Expected
//[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]

//�䷱�� �Ǵµ�,

//Input
//[2]
//[1]
//Output
//[2, 1]
//Expected
//[1, 2]

//�䷱�� �� �ȵ�


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

//ù���� ��°� ����

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

// �굵 �ǰ�

//[6, 7, 8, 9, 10, 12]
//[1, 2, 3, 4, 5, 11]

//������ �굵 �Ǵµ�

//Last executed input
//[5]
//[1, 2, 4]

//�갡�ȵ�. nullptr���� ������ �����ߴٰ� ������. �ٵ� �и� ���ǹ��� if != nullptr ������µ�?


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


//�� submit 10���غ���

//solution 1�� ������
//12,11,20,11,16,12,8,11,12,16 ����

//solution 2�� ������
//14,12,12,11,8,8,20,8,11,12 ����


//solution1�� ���ġ: 12.9ms
//solution2�� ���ġ:11.6ms 

//1ms���� ������ ��...

//�����͵� O(N)
//�̰͵� O(N)�̶� �׷� ��

//solution1�� l1,l2 ��� ��� ���鼭 ���� ���ؼ� �� �������� head�� ���̰�
//solution2�� l1,l2 ��� ��� ���鼭 ���� ���ϱ� �ϴµ�, ���ο� head�� ���̴°� �ƴϰ� ���� �ִ� l1,l2 ���̿� ���̴� ������ ��
//ū ���̴� ����

//�ƿ� ���ٹ��� �޶�� �ϳ���

//�� ���� solution2�� 20ms �شܰ��� ����. l1���ٰ� l2�ٲ㳢���ٰ� �ٽ� l1�ٲ㳢���ٰ� �ٽ� l2�ٲ㳢���ٰ� ���ѹݺ� �ϸ� �����ǳ���

//�ٵ� ���� �� �Ź� r->val, l->val �����ϴ°� �ƴ϶�, �̸� �̾Ƴ��� ���̶� ���ϸ� �� ������ �� ����� �嵥..


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