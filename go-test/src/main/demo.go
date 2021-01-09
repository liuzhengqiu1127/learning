package main

import "fmt"

var container = []string{"zero","one","two"}

func main() {
	container := map[int]string{0:"zero",1:"one",2:"two"}
	//value, ok := interface{}(container).([]string)
	fmt.Printf("The element is %q.\n",container[1])
	//fmt.Printf("The value is %s and %s",value,ok)
	var srcInt = int16(-255)
	dstInt := int8(srcInt)
	fmt.Printf("element is %d",dstInt)
}
