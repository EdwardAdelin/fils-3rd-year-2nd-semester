//Define the function foldWith which uses an
//operation op, and an initial value b to reduce
//a range of integers to a value. For instance, given that op
//is addition (+), the result of folding the range 1 to 3
//with b = 0 will be  ( 0 + 1 ) + 2 + 3 = 6. foldWith should
//be curried (it will take the operation and
//return another function which expects the bounds).
//ex 3.1
def foldWith(b: Int)(op: (Int, Int) => Int)(start: Int, stop: Int): Int = {
  @annotation.tailrec
  def tail_fold(crt: Int, acc: Int): Int = {
    if (crt > stop) acc  // Base case: stop when crt exceeds stop
    else tail_fold(crt + 1, op(acc, crt))  // Recursive case
  }

  tail_fold(start, b)  // Start recursion with initial value
}

val subtractRangeVal = foldWith(10)((x, y) => x + y)(12, 13)

//ex 3.2
//Define the function foldConditional which extends
//foldWith by also adding a predicate p: Int ⇒ Int.   foldConditional will reduce
//only those elements of a range which satisfy the predicate.

def foldConditional(b: Int)(op: (Int, Int) => Int)(p: Int => Boolean)(start: Int, stop: Int): Int = {
  @annotation.tailrec
  def tail_fold(crt: Int, acc: Int): Int = {
    if (crt > stop) acc  // Base case: stop when crt exceeds stop
    else if (p(crt)) tail_fold(crt + 1, op(acc, crt))  // Recursive case
    else tail_fold(crt + 1, acc)
  }

  tail_fold(start, b)  // Start recursion with initial value
}

val foldConditionalVal = foldConditional(10)((x, y) => x + y)(_ % 2 == 0)(9, 13)

//ex 3.3
//Implement the function foldRight which has the same behaviour as foldWith,
//but the order in which the operation is performed is now: 1+(2+(3+0))=6.
//What is the simplest way to implement it?

def foldRight(b: Int)(op: (Int, Int) => Int)(start: Int, stop: Int): Int = {
  @annotation.tailrec
  def tail_fold(crt: Int, acc: Int): Int = {
    if (crt < start) acc  // Base case: stop when crt exceeds stop
    else tail_fold(crt - 1, op(acc, crt))  // Recursive case
  }

  tail_fold(stop, b)  // Start recursion with initial value
}

val foldRightVal = foldRight(0)((x, y) => x + y)(1, 3)

//ex 3.4
//Write a function foldMap which takes values a1,a2,…,ak from a
// range and computes f(a1)opf(a2)op…opf(ak) .

def foldMap(op: (Int,Int) => Int, f: Int => Int)(start: Int, stop: Int): Int = {
  @annotation.tailrec
  def tail_fold(crt: Int, acc: Int): Int = {
    if (crt > stop) acc  // Base case: stop when crt exceeds stop
    else tail_fold(crt + 1, op(acc, f(crt)))  // Recursive case
  }

  tail_fold(start, 0)  // Start recursion with initial value
}
val foldMapEx=  foldMap((x, y) => x + y, x => x * x)(1, 3)



//3.5
//Write a function which computes 1+2^2+3^2+…+(n−1)^2+n^2 using foldMap.

def sumSquares(n: Int): Int = {
  def square(x: Int): Int = x * x
  foldMap((x, y) => x + y, square)(1, n)
}
val sumSquaresVal = sumSquares(10)


//3.6
//Write a function hasDivisor which checks if a
// range contains a multiple of k. Use foldMap and choose f carefully.

def hasDivisor(k: Int, start: Int, stop: Int): Boolean = {
  def isDivisor(x: Int): Int = if (x % k == 0) 1 else 0
  foldMap((x, y) => x + y, isDivisor)(start, stop) > 0
}
val hasDivisorVal = hasDivisor(4, 5, 7)

//3.7
// We can compute the sum of an area defined by a function
// within a range a,b (the integral of that function given the range),
// using the following recursive scheme:
//
//    if the range is small enough, we treat f as a
//    line (and the area as a trapeze). It's area is (f(a)+f(b))(b−a)/2
//
//    .
//    otherwise, we compute the mid of the range,
//    we recursively compute the integral from "a" to mid
//    and from mid to b, and add-up the result.
//
//Implement the function integrate which
// computes the integral of a function f given a range:

//def integrate(f: Double => Double)(start: Double, stop: Double): Double = {
//  val step = 0.0001
//  val trapeze = (f(start) + f(stop)) * (stop - start) / 2
//  if (stop - start < step) trapeze
//  else {
//    val mid = (start + stop) / 2
//    integrate(f)(start, mid) + integrate(f)(mid, stop)
//  }
//}

def integrate(f: Double => Double)(start: Double, stop: Double): Double = {
  def trapeze = (f(start) + f(stop))*(stop-start)/2
  val step = 1
  if (stop - start <= step) trapeze else{
    val mid = (start + stop ) / 2
    integrate(f)(start, mid)+integrate(f)(mid, stop)

  }
}



val integrateVal = integrate(x => x * x)(0, 10)

//3.8
//We define Line2D to be lines in a 2-dimensional space,
// and we represent them as functions. Write a function which
// takes a Line2D and translates it up on the Ox axis
// by a given offset. For instance,
// translateOx of 2 on y=x+1 will return y=x+3.

type Line2D = Int => Int
def translateOx(offset: Int)(l: Line2D): Line2D = x => l(x) + offset
//def translateOx(offset: Int)(l: Line2D): Line2D = x => l(x - offset)
val translateOxVal = translateOx(2)(x => x + 1)(7)
//val line: Line2D = x => x + 1
//val newLine = translateOx(2)(line)


//3.9
//(!) Write a function which takes a Line2D and
// translates it up on the Oy axis by a given offset.

def translateOy(offset: Int)(l: Line2D): Line2D = x => l(x) - offset

val translateOyVal = translateOy(2)(x => x + 1)(5)


//3.10
//Write a function which takes two lines,
// and checks if there exist integer coordinates
// within a given range for x, where the lines intersect.

def intersect(l1: Line2D, l2: Line2D)(start: Int, stop: Int): Boolean = {
  @annotation.tailrec
  def check(x: Int): Boolean = {
    if (x > stop) false
    else if (l1(x) == l2(x)) true
    else check(x + 1)
  }

  check(start)
}

// Test example
val line1: Line2D = x => 2*x + 1
val line2: Line2D = x => -x + 10
val hasIntersection = intersect(line1, line2)(0, 10) // Should be true as they intersect at x=3

//3.11
//Write a function which takes two lines and
// a range of integers, and checks if l1 has
// larger y values than l2 over the entire range.

def larger(l1: Line2D, l2: Line2D)(start: Int, stop: Int): Boolean = {
  @annotation.tailrec
  def check(x: Int): Boolean = {
    if (x > stop)  false
    else if (l1(x) <= l2(x)) true
    else check(x + 1)
  }

  check(start)
}

// Test example
val line3: Line2D = x => 2*x + 1
val line4: Line2D = x => -x + 10
val isLarger = larger(line3, line4)(0, 10) // Should be true as line3 has larger y values than line4
