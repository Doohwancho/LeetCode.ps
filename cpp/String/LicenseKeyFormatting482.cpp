

//solution1

//Runtime: 56 ms, faster than 11.46 % of C++ online submissions for License Key Formatting.
//Memory Usage : 284.1 MB, less than 5.11 % of C++ online submissions for License Key Formatting.

//�ð� ��� ���� �ϱ� �ߴµ�

//���ɵ� ������ �������� �巯�� ��� 

//���ϴ�

//rst += tmp; ��� rst.append(tmp); �ߴµ�, ������ 4ms���� ������

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

//�� solution1�� �ǰ� ��� �ȵ���?

//���� ���� string input�� k=1������ �ִ°ſ��� ����

//�Ƹ� solution1������ ���ÿ� ������ rst�� ���� ��, string.push_back()���µ�, �� �� char node .next�� ���༭ ������ O(N)�ε�

//�ٵ� �갰�� ��쿡,÷���� ������ string�� +������ �ϴϱ�,

//new char + rst �ε�,

//�տ� ���� �� ����, ���� rst�� full scan �ؾ��ϴϱ�, O(N^2)���� �ȵǳ� ��

//��;;;

//�׳� for������ '-'�����ϰ� ���ڸ� uppercase�� ���ڷ� �� ���� ������, �ڿ������� for�� ���鼭 k��°���� '-' insert�ϸ� ���� ������

//�ٵ� �̰� string���� �ϸ�, trial01�� ������ ������ ������ �״ϱ�

//linkedlist�� �ؾ��ϳ�?

//linkedlist�� '-' ���� ����, �ٽ� ÷���� ������ ���鼭 string.push_back() ����� �ϴϱ�, 

//��������δ� O(3N) �̾ O(N)�̰ڳ�?

//�ٵ� �̷��� �غ��� �ᱹ O(2N)���� solution1���� �����ݾ�?

//��...


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

//�б� �������µ�, ������ �ȵ�θ޴ٱ����� ������

//rst.insert()�� ������ ������ ��

//cpp���� stringbuilder������ ����?

//stringstream�̶��� �ִµ�, ������ �����ٰ� ��


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


//linkedlist�� �õ��� ��