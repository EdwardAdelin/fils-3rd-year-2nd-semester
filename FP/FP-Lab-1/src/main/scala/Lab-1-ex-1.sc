//def func()={
//  println("Hello, World!")
//}
//val x= func()

//functie pentru factorialul unui numar
def fact (n: Int): Int = {
  def aux_fact(i: Int, acc: Int): Int =
    if (i==0) acc
    else aux_fact(i-1, i*acc)
  aux_fact(n, 1)
}

//testarea functiei
val y=fact(5)