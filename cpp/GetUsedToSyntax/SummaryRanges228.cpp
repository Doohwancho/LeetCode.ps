
//ù�λ�: ���Ҹ�?

//if the number is consecutive like 0, 1, 2, 3, it forms a range 0->3
//if not, it forms multiple range.A range can be only one number

//��....


//solution1

//Runtime: 0 ms, faster than 100.00% of C++ online submissions for Summary Ranges.
//Memory Usage : 7 MB, less than 13.52 % of C++ online submissions for Summary Ranges.

//cpp������ (string)���� ����ȯ ���ϰ� std:to_string()���� ����ȯ �ϴ±���

//empty vector return�϶�淡 return new vector<string>; �ߴ��� ������. �ڹٿ��� �� ��µ�;;

//�ٵ� �� �ű��Ѱ� �ʱ�ȭ ���� �� �����ϸ� �� ��. cpp������ new�� �Ƚ�µ��� �޸𸮰� �Ҵ�ǳ���.

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