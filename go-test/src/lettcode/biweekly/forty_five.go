package main

import (
	"fmt"
)

func sumOfUnique(nums []int) int {
	var m = make(map[int]int)
	for _,v:= range nums{
		m[v]++
	}
	var result = 0
	for key,value:= range m{
		if value == 1 {
			result += key
		}
	}
	return result
}

func maxAbsoluteSum(nums []int) int {
	var result = 0
	var sum = 0
	var temp = false
	for _,value:= range nums {
		if temp {
			sum -= value
		}else{
			sum += value
		}

		if sum < 0 { // 如果相加为负数，说明反了

			sum = (0-sum)+result // 取反，然后之前累积的结果去掉
			temp = !temp // 符号取反
			result = sum

		}else{
			if sum > result {
				result = sum
			}
		}

	}
	return result
}

func main() {
	fmt.Printf("sumOfUnique result:%d\n",sumOfUnique([]int{1,2,3,2}))
	fmt.Printf("sumOfUnique result:%d\n",sumOfUnique([]int{1,1,1,1,1}))
	fmt.Printf("sumOfUnique result:%d\n",sumOfUnique([]int{1,2,3,4,5}))

	fmt.Printf("maxAbsoluteSum result:%d\n",maxAbsoluteSum([]int{1,-3,2,3,-4}))
	fmt.Printf("maxAbsoluteSum result:%d\n",maxAbsoluteSum([]int{2,-5,1,-4,3,-2}))

	fmt.Printf("longestNiceSubstring result:%s\n",longestNiceSubstring("YazaAay"))
	fmt.Printf("longestNiceSubstring result:%s\n",longestNiceSubstring("Bb"))
	fmt.Printf("longestNiceSubstring result:%s\n",longestNiceSubstring("c"))
	fmt.Printf("longestNiceSubstring result:%s\n",longestNiceSubstring("dDzeE"))

	fmt.Printf("canChoose result:%t\n",canChoose([][]int{{1,-1,-1},{3,-2,0}},[]int{1,-1,0,1,-1,-1,3,-2,0}))
	fmt.Printf("canChoose result:%t\n",canChoose([][]int{{6551094,9427527,2052462,3481286,-7620442},{8495362,-1820796},
		{-1005271,-6911519},{-9667242,9997184,-9316362},
		{-9278108,-7479063,-7573091,-1775876,-2612810,-241649}},
	[]int{6551094,6551094,9427527,2052462,3481286,-7620442,-7620442,8495362,-1820796,
		-1005271,-6911519,-9667242,9997184,-9316362,9997184,-9278108,-7479063,-7573091,-1775876,-2612810,-241649}))


}
