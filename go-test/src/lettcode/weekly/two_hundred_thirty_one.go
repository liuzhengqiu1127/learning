package main

import "math"

func checkOnesSegment(s string) bool {
	result := true
	count := 0
	for _,rune := range s {
		if rune == '1' && count == 0 {
			if result{
				count++
			}else {
				return false
			}
		}

		if rune == '0' && count == 1{
			result = false
			count = 0
		}
	}
	return true
}

func minElements(nums []int, limit int, goal int) int {
	sum := 0
	for _,num := range nums {
		sum += num
	}
	value := goal - sum
	if value == 0{
		return 0
	} else if value > 0{
		return int(math.Ceil(float64(value)/float64(limit)))
	}else{
		return int(math.Ceil(float64(-value)/float64(limit)))
	}
}
