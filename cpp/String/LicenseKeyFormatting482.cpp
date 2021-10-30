

//solution1

//Runtime: 56 ms, faster than 11.46 % of C++ online submissions for License Key Formatting.
//Memory Usage : 284.1 MB, less than 5.11 % of C++ online submissions for License Key Formatting.

//시간 없어서 빨리 하긴 했는데

//성능도 구리고 냄새나는 드러운 방법 

//역하다

//rst += tmp; 대신 rst.append(tmp); 했는데, 성능이 4ms차이 빨라짐

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


//trial01

//time limit exceeded

//왜 solution1은 되고 얘는 안되지?

//졸라 많은 string input을 k=1단위로 넣는거에서 막힘

//아마 solution1에서는 스택에 넣은걸 rst로 붙일 때, string.push_back()쓰는데, 맨 뒤 char node .next만 해줘서 연산이 O(N)인듯

//근데 얘같은 경우에,첨부터 끝까지 string에 +연산을 하니까,

//new char + rst 인데,

//앞에 붙일 때 마다, 기존 rst을 full scan 해야하니까, O(N^2)떠서 안되나 봄

//음;;;

//그냥 for문으로 '-'무시하고 문자만 uppercase로 일자로 쫙 붙힌 다음에, 뒤에서부터 for문 돌면서 k번째마다 '-' insert하면 될지 싶은데

//근데 이걸 string으로 하면, trial01과 동일한 문제가 벌어질 테니까

//linkedlist로 해야하나?

//linkedlist로 '-' 넣은 다음, 다시 첨부터 끝까지 돌면서 string.push_back() 해줘야 하니까, 

//결과적으로는 O(3N) 이어서 O(N)이겠네?

//근데 이렇게 해봤자 결국 O(2N)도는 solution1보다 느리잖아?

//흠...


class Solution {
public:
    string licenseKeyFormatting(string s, int k) {
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
                    rst = rst.length() == 0 ? str : (str + '-' + rst);
                    str = std::toupper(s.at(i));
                    cnt = k - 1;
                }
            }
        }
        if (str.length() > 0) {
            rst = rst.length() == 0 ? str : (str + '-' + rst);
        }

        if (rst.at(0) == '-') {
            rst = rst.substr(1, rst.size());
        }
        return rst;
    }
};



//solution2

//Runtime: 432 ms, faster than 8.22 % of C++ online submissions for License Key Formatting.
//Memory Usage : 8.3 MB, less than 43.43 % of C++ online submissions for License Key Formatting.

//읽기 쉬워졌는데, 성능이 안드로메다급으로 구려짐

//rst.insert()가 성능이 구리나 봄

//cpp에선 stringbuilder같은거 없나?

//stringstream이란게 있는데, 성능이 구리다고 함


class Solution {
public:
    string licenseKeyFormatting(string s, int k) {
        string rst;

        for (int i = 0; i < s.size(); i++) {
            if (s.at(i) != '-') {
                rst.push_back(std::toupper(s.at(i)));
            }
        }

        int idx = rst.size() - k;

        while (idx > 0) {
            rst.insert(idx, "-");
            idx -= k;
        }

        return rst;
    }
};


//linkedlist로 시도한 것