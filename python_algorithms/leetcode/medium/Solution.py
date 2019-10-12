import collections
from typing import List

class Solution:

    def __init__(self):
        pass

    def permute(self, nums):
        def backtrack(first=0):
            if first == n:
                output.append(nums[:])
            for i in range(first, n):
                nums[first], nums[i] = nums[i], nums[first]
                backtrack(first + 1)
                nums[first], nums[i] = nums[i], nums[first]

        n = len(nums)
        output = []
        backtrack()
        return output

    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        if len(nums) == 0:
            return []
        nums.sort()
        used = [False] * len(nums)
        res = []
        self.__dfs(nums, 0, [], used, res)
        return res

    def __dfs(self, nums, index, pre, used, res):
        if index == len(nums):
            res.append(pre.copy())
            return
        for i in range(len(nums)):
            if not used[i]:
                if i > 0 and nums[i] == nums[i-1] and not used[i-1]:
                    continue
                used[i] = True
                pre.append(nums[i])
                self.__dfs(nums,index+1,pre,used,res)
                used[i] = False
                pre.pop()

    def rotate(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        length = len(matrix)
        for i in range(length):
            for j in range(i, length):
                matrix[j][i], matrix[i][j] = matrix[i][j], matrix[j][i]
        for i in range(length):
            matrix[i].reverse()

    def groupAnagrams(self, strs):
        """
        :type strs: List[str]
        :rtype: List[List[str]]
        """
        ans = collections.defaultdict(list)
        for s in strs:
            ans[tuple(sorted(s))].append(s)
        return ans.values()

    def myPow(self, x: float, n: int) -> float:
        """
        :type x: float
        :type n: int
        :rtype: float
        """
        if n < 0:
            x = 1 / x
        return self.myPowHelper(x * x, int(n/2), 1) if n % 2 == 0 else self.myPowHelper(x * x, int(n/2), x)

    def myPowHelper(self, x, n, re) -> float:
        if n == 0:
            return re
        elif n == 1:
            return x * re;
        else:
            return self.myPowHelper(x * x, int(n/2), re) if n % 2 == 0 else self.myPowHelper(x * x, int(n/2), x * re)

if __name__ == '__main__':

    solution = Solution()
    output = solution.myPow(2.0, 10)
    print(output)