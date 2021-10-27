
//첫인상: 뭔소리?

//if the number is consecutive like 0, 1, 2, 3, it forms a range 0->3
//if not, it forms multiple range.A range can be only one number

//ㅏ....


//solution1

//Runtime: 0 ms, faster than 100.00% of C++ online submissions for Summary Ranges.
//Memory Usage : 7 MB, less than 13.52 % of C++ online submissions for Summary Ranges.

//cpp에서는 (string)으로 형변환 안하고 std:to_string()으로 형변환 하는구나

//empty vector return하라길래 return new vector<string>; 했더니 에러남. 자바에선 잘 됬는데;;

//근데 또 신기한게 초기화 해준 후 리턴하면 잘 들어감. cpp에서는 new를 안썼는데도 메모리가 할당되나봄.

class Solution {

public:
    vector<string> summaryRanges(vector<int>& nums) {
        vector<string> rst;
        if (nums.size() == 0) return rst;
        string accum = std::to_string(nums[0]);

        for (int i = 0, j = 1; j < nums.size(); i++, j++) {
            if (nums[j] == nums[i] + 1) {
                if (j == nums.size()-1 || nums[j + 1] != nums[j] + 1) {
                    accum += "->" + std::to_string(nums[j]);
                }
            }
            else if (nums[i] == nums[j]) {
                rst.push_back(accum);
            }
            else {
                rst.push_back(accum);
                accum = std::to_string(nums[j]);
            }
        }
        rst.push_back(accum);
        return rst;
    }
};