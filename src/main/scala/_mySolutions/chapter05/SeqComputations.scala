package _mySolutions.chapter05

/*
We’re going to use Scala’s built-in List class for this exercise as it has a flatMap method.
Given this list: val list = List(1, 2, 3) return a List[Int] containing
both all the elements and their negation. Order is not important.
Hint: Given an element create a list containing it and its negation.
 */
object SeqComputations {

  def addNegation(ints: List[Int]): List[Int] = {
    val createListWithNegation: Int => List[Int] = (n: Int) => List(n, -n)

    ints.flatMap(createListWithNegation)
  }

  /*
    Given this list
      val list: List[Maybe[Int]] = List(Full(3), Full(2), Full(1))
    return a List[Maybe[Int]] containing None for the odd elements.
    Hint: If x % 2 == 0 then x is even.
   */
  def noOddElements(element: List[Maybe[Int]]): List[Maybe[Int]] = {
    val oddOrEven: Maybe[Int] => Maybe[Int] = (n: Maybe[Int]) =>
    n.flatMap(i => if(i % 2 == 0) Full(i) else Empty())

    element.map(oddOrEven)
  }

  // flatmap = f[A](A => f[B]): f[B]
  // map = f[A](A => B): f[B]

  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3)

//    println(s"${addNegation(list)} = ${list.flatMap(n => List(n, -n))}")

    val list2: List[Maybe[Int]] = List(Full(3), Full(2), Full(1))

    println(noOddElements(list2))
  }

}
