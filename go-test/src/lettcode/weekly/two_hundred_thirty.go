package main

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
