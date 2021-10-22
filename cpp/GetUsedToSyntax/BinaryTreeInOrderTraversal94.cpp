
//solution1

//Runtime: 0 ms, faster than 100.00 % of C++ online submissions for Binary Tree Inorder Traversal.
//Memory Usage : 8.3 MB, less than 63.42 % of C++ online submissions for Binary Tree Inorder Traversal.


class Solution {

public:
    void inOrder(TreeNode* root, vector<int>& v) {
        if (root == nullptr) return;
        inOrder(root->left, v);
        v.push_back(root->val);
        inOrder(root->right, v);
    }

    vector<int> inorderTraversal(TreeNode* root) {
        vector<int> v;
        inOrder(root, v);
        return v;
    }
};