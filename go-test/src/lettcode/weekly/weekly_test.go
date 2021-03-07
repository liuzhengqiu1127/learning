package main

import (
	"testing"
)

func TestMinOperations(t *testing.T){
	t.Logf("minOperations result=%d\n",minOperations("0100"))
	t.Logf("minOperations result=%d\n",minOperations("10"))
	t.Logf("minOperations result=%d\n",minOperations("001010"))
}

func TestCountHomogenous(t *testing.T)  {
	t.Logf("countHomogenous result=%d\n",countHomogenous("abbcccaa"))
	t.Logf("countHomogenous result=%d\n",countHomogenous("xy"))
	t.Logf("countHomogenous result=%d\n",countHomogenous("zzzzz"))
}

func TestMinimumSize(t *testing.T) {
	t.Logf("minimumSize result=%d\n",minimumSize([]int{9},2))
	t.Logf("minimumSize result=%d\n",minimumSize([]int{2,4,8,2},4))
	t.Logf("minimumSize result=%d\n",minimumSize([]int{7,17},2))
}

func TestCountMatches(t *testing.T)  {
	t.Logf("countMatches result=%d\n",countMatches([][]string{{"phone","blue","pixel"},
		{"computer","silver","lenovo"},{"phone","gold","iphone"}},"color","silver"))
	t.Logf("countMatches result=%d\n",countMatches([][]string{{"phone","blue","pixel"},
		{"computer","silver","lenovo"},{"phone","gold","iphone"}},"type","phone"))
}

func TestCheckOnesSegment(t *testing.T){
	t.Logf("checkOnesSegment result=%t\n",checkOnesSegment("1001"))
	t.Logf("checkOnesSegment result=%t\n",checkOnesSegment("110"))
}

func TestMinElements(t *testing.T){
	t.Logf("minElements result=%d\n",minElements([]int{1,-1,1},3,-4))
	t.Logf("minElements result=%d\n",minElements([]int{1,-10,9,1},100,0))
}
