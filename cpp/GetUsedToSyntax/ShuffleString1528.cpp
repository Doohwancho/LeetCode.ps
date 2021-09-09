#include <vector>
#include <iostream>

using namespace std;


/*
Java

Runtime: 2 ms, faster than 38.15% of Java online submissions for Shuffle String.
Memory Usage: 41.9 MB, less than 5.49% of Java online submissions for Shuffle String.

    public String restoreString(String s, int[] indices) {
        char[] c = new char[s.length()];
        for(int i = 0; i < s.length(); i++){
            c[indices[i]] = s.charAt(i);
        }
        return new String(c);
    }

*/


/*

dynamic-stack-buffer-overflow 에러남;;

public:
    string restoreString(string s, vector<int>& indices) {
        int size = s.length();
        char c[size];
        for(int i = 0; i < s.length(); i++){
            c[indices[i]] = s.at(i);
        }
        string str(c);

        return str;
    }
*/


//마음엔 안들지만...일단 시간없으니 패스

//Runtime: 10 ms, faster than 39.04 % of C++ online submissions for Shuffle String.
//Memory Usage : 15.2 MB, less than 76.58 % of C++ online submissions for Shuffle String.

class Solution {
public:
    string restoreString(string s, vector<int>& indices) {
        vector<char>v(s.size());
        for (int i = 0; i < s.size(); i++) {
            v[indices[i]] = s[i];
        }
        string ans = "";
        for (int i = 0; i < v.size(); i++) {
            ans += v[i];
        }
        return ans;
    }
};