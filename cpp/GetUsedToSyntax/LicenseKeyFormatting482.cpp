

//solution1

//Runtime: 56 ms, faster than 11.46 % of C++ online submissions for License Key Formatting.
//Memory Usage : 284.1 MB, less than 5.11 % of C++ online submissions for License Key Formatting.

//시간 없어서 빨리 하긴 했는데

//성능도 구리고 냄새나는 드러운 방법 

//역하다

class Solution {
public:
    string licenseKeyFormatting(string s, int k) {
        stack<string> st;
        string str;
        string rst;
        int cnt = k;


        for (int i = s.size() - 1; i >= 0; i--) {
            if (s.at(i) != '-') {
                if (cnt > 0) {
                    char upperedLetter = std::toupper(s.at(i));
                    str = (upperedLetter + str);
                    cnt--;
                }
                else {
                    st.push(str);
                    str = std::toupper(s.at(i));
                    cnt = k - 1;
                }
            }
        }
        if (str.length() > 0) {
            st.push(str);
        }

        while (st.size() > 0) {
            string tmp = st.top();
            tmp.push_back('-');
            rst += tmp;
            st.pop();
        }
        return rst.substr(0, rst.size() - 1);
    }
};