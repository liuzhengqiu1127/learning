package decorator

import (
	"fmt"
	"reflect"
	"runtime"
	"time"
)

func Decorator(f func(s string)) func(s string){
	return func(s string) {
		fmt.Println("Started")
		f(s)
		fmt.Println("Done")
	}
}

func Hello(s string)  {
	fmt.Println(s)
}

type SumFunc func(int64, int64) int64

func getFunctionName(i interface{}) string {
	return runtime.FuncForPC(reflect.ValueOf(i).Pointer()).Name()
}

func TimedSumFunc(f SumFunc) SumFunc {
	return func(start, end int64) int64 {
		defer func(t time.Time){
			fmt.Printf("--- Time Elapsed (%s): %v ---\n", getFunctionName(f), time.Since(t))
		}(time.Now())

		return f(start, end)
	}
}

func Sum1(start, end int64) int64{
	var sum int64 = 0
	if start > end {
		start, end = end, start
	}
	for i:=start; i <= end; i++ {
		sum += i
	}
	return sum
}

func Sum2(start, end int64) int64  {
	if start > end {
		start, end = end, start
	}
	return (end - start + 1) * (end + start) / 2
}
