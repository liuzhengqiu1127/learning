package main

import (
	"math"
)

func countMatches(items [][]string, ruleKey string, ruleValue string) int {
	var result = 0
	for _,item:= range items {
		if ruleKey == "type" {
			if item[0] == ruleValue {
				result++
			}
		}else if ruleKey == "color"{
			if item[1] == ruleValue{
				result++
			}
		}else{
			if item[2] == ruleValue{
				result++
			}
		}
	}
	return result
}

func closestCost(baseCosts []int, toppingCosts []int, target int) int {
	var n = len(baseCosts)
	var m = len(toppingCosts)

	ret := baseCosts[0]
	cur := 0

	for i:=0; i<n; i++ {
		for j:=0; j<(1<<m); j++  {
			for k:=j; k<(1<<m);k++  {
				cur = baseCosts[i]
				for l:=0; l<m; l++ {
					if j&(1<<l) {
						cur += toppingCosts[l]
					}
					if k&(1<<l) {
						cur += toppingCosts[l]
					}
				}
				if math.Abs(float64(ret-target)) > math.Abs(float64(cur-target)){
					ret = cur
				}else if math.Abs(float64(ret-target)) == math.Abs(float64(cur-target)) {
					ret = int(math.Min(float64(ret),float64(cur)))
				}
			}
		}
	}
	return ret

}
