package main

import "fmt"

func minOperations(s string) int {
	var result = 0
	var first = s[0]
	for i:=1;i<len(s);i++{
		if s[i] == first {
			first = ('1'-s[i])+48
			result++
		}else {
			first = s[i]
		}

	}
	if result <= len(s)-result {
		return result
	}
	return len(s)-result
}

/**
不需要记录每个字符出现的次数，只需要记一下每个子串即可，因为每个子串都是独立的
 */
func countHomogenous(s string) int {
	var result = 0
	var count = 0
	var res = 1000000007
	for index:= range s {
		count++
		if index==len(s)-1 || s[index] != s[index+1] {
			result += (count*(count+1)/2)%res
			count=0
		}
	}
	return result
}

func minimumSize(nums []int, maxOperations int) int {
	var l = 1
	var r = 1000000007
	var ret int
	for ; l<=r;  {
		mid := (l+r)/2
		if check(nums,mid,maxOperations) {
			r = mid -1
			ret = mid
		}else{
			l = mid + 1
		}
	}
	return ret
}

func check(nums []int, cost int, maxOperations int) bool  {
	var count = 0
	for i:=0; i<len(nums); i++ {
		if nums[i]%cost==0 {
			count += nums[i]/cost - 1
		}else{
			count += nums[i]/cost
		}
	}
	return count <= maxOperations
}

func main() {
	fmt.Printf("minOperations result=%d\n",minOperations("0100"))
	fmt.Printf("minOperations result=%d\n",minOperations("10"))
	fmt.Printf("minOperations result=%d\n",minOperations("001010"))

	fmt.Printf("countHomogenous result=%d\n",countHomogenous("abbcccaa"))
	fmt.Printf("countHomogenous result=%d\n",countHomogenous("xy"))
	fmt.Printf("countHomogenous result=%d\n",countHomogenous("zzzzz"))

	fmt.Printf("minimumSize result=%d\n",minimumSize([]int{9},2))
	fmt.Printf("minimumSize result=%d\n",minimumSize([]int{2,4,8,2},4))
	fmt.Printf("minimumSize result=%d\n",minimumSize([]int{7,17},2))
}
