package main

import (
	"container/list"
	"fmt"
)

var container = []string{"zero","one","two"}

func test01()  {
	container := map[int]string{0:"zero",1:"one",2:"two"}
	//value, ok := interface{}(container).([]string)
	fmt.Printf("The element is %q.\n",container[1])
	//fmt.Printf("The value is %s and %s",value,ok)
	var srcInt = int16(-255)
	dstInt := int8(srcInt)
	fmt.Printf("element is %d",dstInt)
}

func test02()  {
	s1 := make([]int, 5)
	fmt.Printf("The length of s1: %d\n", len(s1))
	fmt.Printf("The capacity of s1:%d\n", cap(s1))
	fmt.Printf("The value of s1:%d\n",s1)
	s2 := make([]int, 5, 8)
	fmt.Printf("The length of s2: %d\n", len(s2))
	fmt.Printf("The capacity of s2:%d\n", cap(s2))
	fmt.Printf("The value of s2:%d\n",s2)
	s3 := []int{1,2,3,4,5,6,7,8}
	s4 :=s3[3:6]
	fmt.Printf("The length of s4: %d\n", len(s4))
	fmt.Printf("The capacity of s4:%d\n", cap(s4))
	fmt.Printf("The value of s4:%d\n",s4)
}

func test03()  {
	var l list.List
	e1 := l.PushBack(4)
	e4 := l.PushFront(1)
	l.InsertBefore(3,e1)
	l.InsertAfter(2,e4)
	for i := l.Front(); i != nil; i = i.Next(){
		fmt.Println(i.Value)
	}
}

func test04()  {
	aMap := map[string]int{
		"one": 1,
		"two": 2,
		"three": 3,
	}
	k := "two"
	v, ok := aMap[k]
	if ok {
		fmt.Printf("The element of key %q: %d\n", k, v)
	}else{
		fmt.Println("Not found!")
	}
}

func main() {
	//test01()
	//test02()
	test04()
}
