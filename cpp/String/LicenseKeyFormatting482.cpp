

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

//rst.insert()가 성능이 구리나 봄. 매번 insert할 때마다 이전 string+1 공간만큼 만들어서 우겨넣나봄.

//cpp에선 stringbuilder같은거 없나?

//-> stringstream이란게 있는데, 성능이 구리다고 함


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


//doubly linked list로도 구현 가능할 거 같은데?

//s를 까꾸로 돌면서 대문자로 doubly linked list에 넣는데, k번째마다 '-' 넣어줌

//다 넣으면, doubly linked list를 순회로 돌면서, string rst;에 rst.push_back()해줌

//push_back()은 뒤에만 쏙 붙이는 애니까 성능도 괜찮겠지?

//solution3

//Runtime: 20 ms, faster than 14.23 % of C++ online submissions for License Key Formatting.
//Memory Usage : 17.7 MB, less than 5.23 % of C++ online submissions for License Key Formatting.

//O(N)

//56ms -> 20ms

//꽤 많이 줄었는데도 아직 갈길이 멀다.



class Solution {

    struct Node {
        char data;
        Node* next, * prev;
        Node() {
            next = prev = NULL;
            data = NULL;
        }
    };

public:
    string licenseKeyFormatting(string s, int k) {
        Node* node = new Node();
        int cnt = k;

        for (int i = s.size() - 1; i >= 0; ) {
            if (s.at(i) != '-') {
                if (cnt > 0) {
                    node->data = std::toupper(s.at(i));
                    node->prev = new Node();
                    Node* tmp = node;
                    node = node->prev;
                    node->next = tmp;
                    cnt--;
                    i--;
                }
                else {
                    node->data = '-';
                    node->prev = new Node();
                    Node* tmp = node;
                    node = node->prev;
                    node->next = tmp;
                    cnt = k;
                }
            }
            else {
                i--;
            }
        }

        node = node->next;

        string rst;

        while (node != NULL) {
            rst.push_back(node->data);
            node = node->next;
        }

        return rst;
    }
};



//s랑 길이 똑같은 rst만들어 놓고

//two pointer식으로 k 1씩 까면서 s.size() % k가 >0일 때, s를 붙이고, 0되면 '-'붙이고 k 원상복귀하는 식으로 하는게 답일거같네

//아 왠지 졸라 돌아온느낌

//아 근데 s에 포함된 dash 수가 랜덤하잖아? 그러면 %해서 고정된 값이 안나오겠네

//그냥 졸라 단순하게 뒤에서부터 for loop돌면서 k번째 이하일 때 '-'만나면 없애고, k번째일 때 '-'를 insert한다면?

//근데 아무리 string.remove()의 성능이 좋다고 해도, string.isert()의 성능이 똥이라는걸 위에서 봤는데, 같은실수 반복일듯





//solution 4 by yuxiangmusic

//Runtime: 0 ms, faster than 100.00 % of C++ online submissions for License Key Formatting.
//Memory Usage : 8.2 MB, less than 68.77 % of C++ online submissions for License Key Formatting.

//cpp에선 타입 추론할 때(int인지 char인지 명확하지 않을 때) auto 키워드 쓴다고 함. int로 바꿔도 무관할 듯?

//해서 auto->int로 바꿨더니 에러남.

//혹시 string의 마지막 애를 가르키난 해서 char이나 string써봤는데도 에러나는걸 보면, auto 키워드는 포인터 주소값 나타낼 때 쓰는 타입키워드인듯

//solution3이랑 로직은 똑같은데, 왜 성능차이가 20ms이상나는거지?

//s.size() % k하는거 대신, int cnt = k; 한 다음, if else로 cnt빼주고, 0되면 다시 k넣어주고 이러는거랑, doubly linked list만들어서 쓰는거가 20ms나 차이나나?

//이 사람은 res.push_back()안하고 res += toupper(*i);한거 보니, cpp에서는 자바랑 다르게 +=해도 성능이 좋나보네

//reverse 내장함수도 성능이 졸라 좋은 편인가봄.


class Solution {

public:
    string licenseKeyFormatting(string S, int K) {
        string res;
        for (auto i = S.rbegin(); i < S.rend(); i++)
            if (*i != '-') { // ignore '-' in original string
                if (res.size() % (K + 1) == K) res += '-'; // every (K+1)th char is '-' from tail
                res += toupper(*i);
            }

        reverse(res.begin(), res.end());
        return res;
    }
};
