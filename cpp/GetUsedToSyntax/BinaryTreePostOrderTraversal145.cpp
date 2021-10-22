
//solution1

//Runtime: 0 ms, faster than 100.00% of C++ online submissions for Binary Tree Postorder Traversal.
//Memory Usage : 8.6 MB, less than 36.06 % of C++ online submissions for Binary Tree Postorder Traversal.


class Solution {

public:
    void postOrder(TreeNode* root, vector<int>& v) {
        if (root == nullptr) return;
        postOrder(root->left, v);
        postOrder(root->right, v);
        v.push_back(root->val);
    }

    vector<int> postorderTraversal(TreeNode* root) {
        vector<int> v;
        postOrder(root, v);
        return v;
    }
};