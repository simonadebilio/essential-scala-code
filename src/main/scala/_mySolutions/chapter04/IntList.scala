package _mySolutions.chapter04

// Define a method `length` that returns the length of the list.

sealed trait IntList
case object End extends IntList
final case class Pair(head: Int, tail: IntList) extends IntList

object Operations {
  def length(list: IntList): Int =
    list match {
      case End           => 0
      case Pair(_, tail) => 1 + length(tail)
    }

  // Define a method to compute the product of the elements in an IntList.
  def product(list: IntList): Int = {
    list match {
      case End              => 1
      case Pair(head, tail) => head * product(tail)
    }
  }

  // Define a method to double the value of each element in an IntList,
  // returning a new IntList.
  def double(list: IntList): IntList = {
    list match {
      case End              => End
      case Pair(head, tail) => Pair(head * 2, double(tail))
    }
  }
}
