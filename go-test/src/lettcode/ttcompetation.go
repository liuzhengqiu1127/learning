package main

import (
	"fmt"
	"sort"
)

/**
223周赛
 */
func decode(encoded []int, first int) []int {
	result := make([]int, len(encoded)+1)
	result[0] = first
	for i:=0; i<len(encoded); i++ {
		result[i+1] = result[i] ^ encoded[i]
	}
	return result
}

type ListNode struct {
	Val int
	Next *ListNode
}
func swapNodes(head *ListNode, k int) *ListNode {
	tmp := head
	var len = 0
	for tmp != nil {
		len++
		tmp = tmp.Next
	}
	var k2 = len - k + 1
	if k == k2 {
		return head
	}
	tmp = head
	var number1 = 0
	var number2 = 0
	var i = 1
	var count = 0
	for tmp != nil {
		if i == k {
			number1 = tmp.Val
			count++
		}
		if i == k2 {
			number2 = tmp.Val
			count++
		}
		if count == 2{
			break
		}
		i++
		tmp = tmp.Next
	}

	tmp = head
	i = 1
	count = 0
	for tmp != nil {
		if i == k {
			tmp.Val = number2
			count++
		}
		if i == k2 {
			tmp.Val = number1
			count++
		}
		if count == 2{
			break
		}
		i++
		tmp = tmp.Next
	}
	return head
}

/**
 224周赛
 */
func countGoodRectangles(rectangles [][]int) int {
	var result = 0
	var temp = 0
	for i:=0; i<len(rectangles); i++ {
		max := If(rectangles[i][0]>rectangles[i][1],rectangles[i][1],rectangles[i][0]).(int)
		if temp == max {
			result++
		}else if max > temp {
			result = 1
			temp = max
		}else{
			continue
		}
	}
	return result
}
func If(condition bool, trueVal, falseVal interface{}) interface{} {
	if condition {
		return trueVal
	}
	return falseVal
}

func tupleSameProduct(nums []int) int {
	var result = 0
	sort.Ints(nums)
	for i:=0; i<=len(nums)-4; i++ {
		for j:=len(nums)-1; j>=i+3; j--{
			temp := nums[i] * nums[j]
			k := i+1
			d := j-1
			for ; k < d; {
				temp1 := nums[k]*nums[d]
				if temp == temp1 {
					result += 8
					k++
					d--
				}else if temp1 > temp {
					d--
				}else{
					k++
				}
			}
		}
	}
	return result
}

/**
如何从数组中选择相同的，
1，先获取所有可能的乘积
2，相同个数的组合=(number)*(number-1)/2
3，结果=8*组合
 */
func tupleSameProduct2(nums []int) int {
	var result = 0
	sort.Ints(nums)
	var mp = map[int]int{}
	for i:=0; i<len(nums); i++ {
		for j:=i+1; j<len(nums); j++ {
			mp[nums[i]*nums[j]]++
		}
	}
	for _, v:= range mp{
		result += v*(v-1)/2
	}
	return result*8
}

func largestAltitude(gain []int) int {
	var result = 0
	var temp = 0
	for i:=0; i<len(gain); i++ {
		temp = temp + gain[i]
		if temp > result {
			result = temp
		}
	}
	return result
}

func minimumTeachings(n int, languages [][]int, friendships [][]int) int {
	var tmpList []int
	for i:=0; i<len(friendships); i++ {
		flag := false
		for j:=0; j<len(languages[friendships[i][0]-1]); j++ {
			for k:=0; k<len(languages[friendships[i][1]-1]); k++ {
				if languages[friendships[i][0]-1][j] == languages[friendships[i][1]-1][k]{
					flag = true
					break
				}
			}
			if flag {
				break
			}
		}
		if !flag {
			tmpList = append(tmpList, friendships[i][0]-1, friendships[i][1]-1)
		}
	}
	tmpList = removeRepByMap(tmpList)//算出所有不能沟通的人
	s := make([]int, n+1)
	for _,v1 := range tmpList {
		for i:=0; i<len(languages[v1]); i++ {
			s[languages[v1][i]]++ //找到不能沟通人之间共同语言最多的
		}
	}
	sort.Ints(s)
	return len(tmpList) - s[len(s)-1]
}
func removeRepByMap(slc []int) []int {
	var result []int
	tempMap := map[int]byte{}
	for _, e := range slc {
		l := len(tempMap)
		tempMap[e] = 0
		if len(tempMap) != l {
			result = append(result, e)
		}
	}
	return result
}

func main() {
	//fmt.Printf("the result : %d\n",decode([]int{1,2,3},1))
	//fmt.Printf("the result : %d\n",decode([]int{6,2,7,3},4))
	//fmt.Printf("countGoodRectangles result : %d\n",countGoodRectangles([][]int{{5,8},{3,9},{5,12},{16,5}}))
	//fmt.Printf("countGoodRectangles result : %d\n",countGoodRectangles([][]int{{2,3},{3,7},{4,3},{3,7}}))
	//fmt.Printf("tupleSameProduct result : %d\n",tupleSameProduct([]int{2,3,4,6}))
	//fmt.Printf("tupleSameProduct2 result : %d\n",tupleSameProduct2([]int{2,3,4,6}))
	fmt.Printf("largestAltitude result: %d\n",largestAltitude([]int{-5,1,5,0,-7}))
	fmt.Printf("largestAltitude result: %d\n",largestAltitude([]int{-4,-3,-2,-1,4,3,2}))
	fmt.Printf("minimumTeachings result: %d\n",minimumTeachings(3,[][]int{{2},{1,3},{1,2},{3}},[][]int{{1,4},{1,2},{3,4},{2,3}}))
}
