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

func main() {
	fmt.Printf("the result : %d\n",decode([]int{1,2,3},1))
	fmt.Printf("the result : %d\n",decode([]int{6,2,7,3},4))
	fmt.Printf("countGoodRectangles result : %d\n",countGoodRectangles([][]int{{5,8},{3,9},{5,12},{16,5}}))
	fmt.Printf("countGoodRectangles result : %d\n",countGoodRectangles([][]int{{2,3},{3,7},{4,3},{3,7}}))
	fmt.Printf("tupleSameProduct result : %d\n",tupleSameProduct([]int{2,3,4,6}))
	fmt.Printf("tupleSameProduct result : %d\n",tupleSameProduct([]int{1,2,4,5,10}))
	fmt.Printf("tupleSameProduct result : %d\n",tupleSameProduct([]int{2,3,4,6,8,12}))
	fmt.Printf("tupleSameProduct result : %d\n",tupleSameProduct([]int{2,3,5,7}))
}
