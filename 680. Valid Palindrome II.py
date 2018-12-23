"""
Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True
Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.
"""
#SUCCESS
#다만 미친듯한 양의 input넣으면 time limit exceed 뜸

#s="abca"
s="cbbcc"
S=list(s)

if(s[::-1]==s):
    print(True)
for i in range(len(s)):
    S1=S.copy()
    S1.pop(i)
    S1=''.join(S1)
    if(S1==S1[::-1]):
        print(True)
        break
print(False)