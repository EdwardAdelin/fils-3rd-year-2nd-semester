//exercise 2.4
def improve(xn: Double, a: Double): Double = {
  (xn + a / xn) / 2
}
//exercise 2.5
def nth_guess(n: Int, a: Double): Double = {
  def iterate(guess: Double, count: Int): Double = {
    if (count == 0) guess
    else iterate(improve(guess, a), count - 1)
  }
  iterate(1.0, n)
}
//exercise 2.6
import scala.math._

def acceptable(xn: Double, a: Double): Boolean = {
  abs(xn * xn - a) <= 0.001
}
//exercise 2.7
def mySqrt(a: Double): Double = {
  def improve(xn: Double): Double = {
    (xn + a / xn) / 2
  }

  def acceptable(xn: Double): Boolean = {
    scala.math.abs(xn * xn - a) / a <= 0.001
  }

  def tailSqrt(estimate: Double): Double = {
    if (acceptable(estimate)) estimate
    else tailSqrt(improve(estimate))
  }

  tailSqrt(1.0)
}

val improved = improve(1.0, 2.0)
val guess = nth_guess(10, 2.0)
val accept = acceptable(1.4142156862745097, 2.0)
val sqrt2 = mySqrt(2)
val sqrtComplicat = mySqrt(2.0e50)