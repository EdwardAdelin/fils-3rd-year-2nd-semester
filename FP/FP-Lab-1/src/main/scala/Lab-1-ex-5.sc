// 2.5. (!) Write a function which takes an initial value x and a range of values x0,x1,…,xn
// and computes ((x−x0)−x1)−…xn . Use the most appropriate type of recursion for this task.

def subtractRange(x: Int, start: Int, stop: Int): Int = {
  if (start > stop ) 0
  else x - start 
}

//def sumNats(start: Int, stop: Int): Int = {
//  if(start > stop) 0
//  else start + sumNats(start + 1, stop)
//}
//
//def tailSumNats(start: Int, stop: Int): Int = {
//  def helper(startH: Int, stopH: Int, acc: Int): Int = {
//    if (startH > stopH) acc
//    else helper(startH + 1, stopH, acc + startH)
//  }
//  helper(start, stop, 0)
//}
//
//val sum1 = sumNats(0, 100)
//val sum2 = tailSumNats( 0, 50)
