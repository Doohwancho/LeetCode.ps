
//����

//array�� �����ϸ� ���� ������, getMin()���� �ɸ���

//.push()�� ���� ���� ������ ����س���, pop()�Ѵ��� .getMin()�ϸ� ��¯ �����ݾ�?

//�׷��� �� �������� 10������ ����� �θ� ���� ������? -> ���� array size�� �鸸���ε�, pop()�� �鸸��-1 �� ���� getMin()�� ���� ���ݾ�?

//�׷��ٰ� getMin()�� ������ ��ü array�� for loop���ڴ� �ſ� ��ȿ�� ������ ��¼��?



//�ٵ� �̰� �����ϴµ� STack�ᵵ �Ƿ���? �׳� Stack�����ϴ°Ÿ� Stack���� �ȵǴ°� ����ε�, �̰� MinSTack�����ϴ°Ŵϱ� ���� ������?





//Trial01 - Time Limit Exceeded

//���� �ΰ� �غ��ؼ� .push(n)�� �� n�� a.top()���� ������ ��� �״ٰ�,
//n�� �� ū�� ������, a�� b�ʿ� �Űܽ״µ�, �״� ����, n�� ���ؼ�, n�� �Ż縮 ��������
//b�� �� ������, b�� �� ū�� �����ִ� ���̴ϱ�, �ٽ� a�� �ű�鼭 ��������



//["MinStack", "push", "push", "push", "getMin", "pop", "top", "getMin"]
//[[], [-2], [0], [-3], [], [], [], []]

//��� pass�ϴµ�

//["MinStack", "push", "push", "push", "getMin", "pop", "top", "getMin"]
//[[], [-2], [0], [-3], [], [], [], []]

//��� pass �ȵ�

//�ϱ� push pop�Ҷ����� �̸��Ű�� �����Ű���ϴ°� �� ������



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

//�� a->b�� ���� ���µ�, b->a�� �� ������ �����?

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



