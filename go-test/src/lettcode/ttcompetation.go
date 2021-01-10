package main

import "fmt"

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
	tmp2 := head
	var number1 = 0
	var number2 = 0
	var i = 1
	var count = 0
	for tmp2 != nil {
		if i == k {
			number1 = tmp2.Val
			count++
		}
		if i == k2 {
			number2 = tmp2.Val
			count++
		}
		if count == 2{
			break
		}
		i++
		tmp2 = tmp2.Next
	}

	tmp3 := head
	i = 1
	count = 0
	for tmp3 != nil {
		if i == k {
			tmp3.Val = number2
			count++
		}
		if i == k2 {
			tmp3.Val = number1
			count++
		}
		if count == 2{
			break
		}
		i++
		tmp3 = tmp3.Next
	}
	return head
}

func main() {
	fmt.Printf("the result : %d\n",decode([]int{1,2,3},1))
	fmt.Printf("the result : %d\n",decode([]int{6,2,7,3},4))
}
