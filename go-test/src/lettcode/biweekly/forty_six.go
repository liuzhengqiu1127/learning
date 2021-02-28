package main

func longestNiceSubstring(s string) string {
	var res = ""
	for i := 0; i < len(s); i++ {
		for j := len(s) - 1; j > i; j-- {
			flag := true
			set := map[uint8]int{} //对每一个子串进行遍历
			for k := i; k <= j; k++ {
				set[s[k]]++
			}
			if len(set)%2 != 0 { //子串为奇数，肯定会出现多出来的一个字符
				continue
			}
			for key := range set {
				if key < 'a' { //key小于a时
					_, ok := set[key+32]
					if !ok {
						flag = false
						break
					}
				}
				if key >= 'a' { //key大于等于a
					_, ok := set[key-32]
					if !ok {
						flag = false
						break
					}
				}
			}
			if flag && j-i+1 > len(res) {
				res = s[i : j+1]
			}
		}
	}
	return res
}

func canChoose(groups [][]int, nums []int) bool {
	var startIndex = 0
	for i := 0; i < len(groups); i++ {
		startIndex = nextStartIndex(groups[i],nums, startIndex)
		if startIndex == -1{
			return false
		}
	}
	return true
}
func nextStartIndex(group []int, nums []int, startIndex int) int {
	value := group[0]
	isCanStart := false
	j := 1
	for i := startIndex; i < len(nums); i++ {
		if nums[i] == value && !isCanStart{ //记录第一次相等
			isCanStart = true
			startIndex = i
			continue
		}
		if isCanStart && j >= len(group){ //结束
			return i
		}
		if isCanStart && group[j] == nums[i]{ //后面每次都要相等
			j++
			continue
		}
		if isCanStart &&  group[j] != nums[i]{ // 如果不相等，重新开始比较
			j = 1
			i = startIndex
			isCanStart = false
		}
	}
	if isCanStart && j >= len(group){
		return len(nums)
	}
	return -1
}
