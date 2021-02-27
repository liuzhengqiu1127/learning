package main

import (
	"fmt"
	"geektime/decorator"
	"geektime/pipeline"
	gen "geektime/template"
	"geektime/visitor"
	"strings"
)

func MapStrToStr(arr []string, fn func(s string) string) []string{
	var newArray = []string{}
	for _,it := range arr {
		newArray = append(newArray, fn(it))
	}
	return newArray
}

func MapStrToInt(arr []string, fn func(s string) int) []int{
	var newArray = []int{}
	for _,it := range arr {
		newArray = append(newArray, fn(it))
	}
	return newArray
}

func testMap(){
	var list = []string{"liu","zheng","qiu"}
	x := MapStrToStr(list, func(s string) string {
		return strings.ToUpper(s)
	})
	fmt.Println(x)
	y := MapStrToInt(list, func(s string) int {
		return len(s)
	})
	fmt.Println(y)
}

func main() {
	testMap()
	gen.GenerateUint32Example()
	gen.GenerateStringExample()
	gen.FilterEmployeeExample()
	decorator.Decorator(decorator.Hello)("Hello,World!")

	sum1 := decorator.TimedSumFunc(decorator.Sum1)
	sum2 := decorator.TimedSumFunc(decorator.Sum2)

	fmt.Printf("%d,%d\n", sum1(-10000,10000000), sum2(-10000,10000000))

	pipeline.Test01()
	pipeline.Test02()
	pipeline.Test03()

	visitor.Test01()
}
