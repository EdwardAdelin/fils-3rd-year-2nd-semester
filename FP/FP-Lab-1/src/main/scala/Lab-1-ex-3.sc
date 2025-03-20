//Write a tail-recursive function takes an integer n and computes the value 1+22+32+…+(n−1)2+n2

//(Hint: use inner functions)

def sumSquares(n: Int): Int = {
  def sumSquaresHelper(i:Int, acc:Int): Int = {
    if(i>n) acc
    else sumSquaresHelper(i+1, acc+i*i)
  }
  sumSquaresHelper(1, 0)
}
val sumSquaresVal=sumSquares(10)