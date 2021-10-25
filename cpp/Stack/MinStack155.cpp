
//생각

//array로 구현하면 될지 싶은데, getMin()에서 걸리네

//.push()할 때마 제일 작은수 기억해놔도, pop()한다음 .getMin()하면 말짱 꽝이잖아?

//그러면 젤 작은수를 10개정도 기억해 두면 되지 않을까? -> 만약 array size가 백만개인데, pop()을 백만번-1 한 다음 getMin()할 수도 있잖아?

//그렇다고 getMin()할 때마다 전체 array를 for loop돌자니 매우 비효율 같은데 어쩌지?



//근데 이거 구현하는데 STack써도 되려나? 그냥 Stack구현하는거면 Stack쓰면 안되는건 상식인데, 이건 MinSTack구현하는거니까 되지 않을까?





//Trial01 - Time Limit Exceeded

//스택 두개 준비해서 .push(n)할 때 n이 a.top()보다 작으면 계속 쌓다가,
//n이 더 큰게 나오면, a를 b쪽에 옮겨쌓는데, 쌓는 도중, n과 비교해서, n을 꼽사리 끼워넣음
//b에 다 쌓으면, b는 젤 큰게 위에있는 식이니까, 다시 a로 옮기면서 뒤집어줌



//["MinStack", "push", "push", "push", "getMin", "pop", "top", "getMin"]
//[[], [-2], [0], [-3], [], [], [], []]

//얘는 pass하는데

//["MinStack", "push", "push", "push", "getMin", "pop", "top", "getMin"]
//[[], [-2], [0], [-3], [], [], [], []]

//얘는 pass 안됨

//하긴 push pop할때마다 이리옮겼다 저리옮겼다하는건 좀 에바지



class MinStack {

    stack<int> st;
    stack<int> a;
    stack<int> b;

public:
    MinStack() {

    }

    void push(int val) {
        st.push(val);

        if (a.size() == 0 || a.top() >= val) {
            a.push(val);
        }
        else {
            while (a.size() > 0) {
                int aTop = a.top();
                if (aTop > val) {
                    b.push(val);
                    continue;
                }
                b.push(aTop);
                a.pop();
            }
            if (b.top() < val) {
                b.push(val);
            }
            while (b.size() > 0) {
                a.push(b.top());
                b.pop();
            }
        }
    }

    void pop() {
        int target = st.top();
        st.pop();

        while (a.size() > 0) {
            if (a.top() == target) {
                a.pop();
                continue;
            }
            else {
                b.push(a.top());
                a.pop();
            }
        }
        while (b.size() > 0) {
            a.push(b.top());
            b.pop();
        }
    }

    int top() {
        return st.top();
    }

    int getMin() {
        return a.top();
    }
};



//Trial02

//["MinStack", "push", "push", "push", "getMin", "pop", "top", "getMin"]
//[[], [-2], [0], [-3], [], [], [], []]

//아 a->b는 문제 없는데, b->a할 때 문제가 생기네?

class MinStack {

    stack<int> st;
    stack<int> a;
    queue<int> b;
    bool flag = true;

public:
    MinStack() {

    }

    void push(int val) {
        st.push(val);

        if (flag) {
            if (a.size() == 0 || a.top() >= val) {
                a.push(val);
            }
            else {
                while (a.size() > 0) {
                    int aTop = a.top();
                    if (aTop > val) {
                        b.push(val);
                        continue;
                    }
                    b.push(aTop);
                    a.pop();
                }
                if (b.front() < val) {
                    b.push(val);
                }
                flag = false;
            }
        }
        else {
            if (b.size() == 0 || b.front() >= val) {
                b.push(val);
            }
            else {
                while (b.size() > 0) {
                    int bFront = b.front();
                    if (bFront > val) {
                        a.push(val);
                        continue;
                    }
                    a.push(bFront);
                    b.pop();
                }
                if (a.top() > val) {
                    a.push(val);
                }
                flag = true;
            }
        }
    }

    void pop() {
        int target = st.top();
        st.pop();

        if (flag) {
            while (a.size() > 0) {
                if (a.top() == target) {
                    a.pop();
                    continue;
                }
                else {
                    b.push(a.top());
                    a.pop();
                }
            }
            flag = false;
        }
        else {
            while (b.size() > 0) {
                if (b.front() == target) {
                    b.pop();
                    continue;
                }
                else {
                    a.push(b.front());
                    b.pop();
                }
            }
            flag = true;
        }


    }

    int top() {
        return st.top();
    }

    int getMin() {
        int rst = 0;
        if (flag && a.size() > 0) {
            return a.top();
        }
        else if (!flag && b.size() > 0) {
            return b.front();
        }
        return rst;
    }
};



