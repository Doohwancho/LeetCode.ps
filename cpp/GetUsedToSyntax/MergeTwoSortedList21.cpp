
 
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

