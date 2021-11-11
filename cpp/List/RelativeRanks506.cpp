
//solution1

//Runtime: 4 ms, faster than 99.92 % of C++ online submissions for Relative Ranks.
//Memory Usage : 11.3 MB, less than 29.90 % of C++ online submissions for Relative Ranks.

//음 정렬방법을 merge sort을 써도 재밌을거같은데


class Solution {
public:

    static bool compare(const pair<int, int>& a, const pair<int, int>& b) {
        //If the first number is same
        if (a.first == b.first)
            return a.second > b.second; //The second number in Descending order
        return a.first > b.first; //The first number of bigger numbers to move forward -> Descending order
    }

    vector<string> findRelativeRanks(vector<int>& score) {
        vector<pair<int, int>> v;

        int n = score.size();

        for (int i = 0; i < n; i++) {
            v.push_back(make_pair(score.at(i), i));
        }

        //Descending order
        sort(v.begin(), v.end(), compare);

        int tmp[n];
        for (int i = 0; i < n; i++) {
            tmp[v.at(i).second] = i + 1;
        }

        vector<string> rst;
        for (int i = 0; i < n; i++) {
            if (tmp[i] < 4) {
                switch (tmp[i]) {
                case 1:
                    rst.push_back("Gold Medal");
                    break;
                case 2:
                    rst.push_back("Silver Medal");
                    break;
                case 3:
                    rst.push_back("Bronze Medal");
                    break;
                }
            }
            else {
                rst.push_back(to_string(tmp[i]));
            }
        }

        return rst;
    }
};