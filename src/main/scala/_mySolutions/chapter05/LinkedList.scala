package _mySolutions.chapter05

// Our IntList type was defined as:
//    sealed trait IntList
//    case object End extends IntList
//    final case class Pair(head: Int, tail: IntList) extends IntList

//  Change the name to LinkedList and make it generic in the type of data
//  stored in the list.

sealed trait Result[A]
case class Compute[A](result: A) extends Result[A]
case class Error[A](reason: String) extends Result[A]

sealed trait LinkedList[A] extends Result[A] {

  // Implement a method `map`
  def map[B](f: A => B): LinkedList[B] =
    this match {
      case End()        => End()
      case Pair(hd, tl) => Pair(f(hd), tl.map(f))
    }

  // Implement a method `fold`
  def fold[B](end: B, f: (A, B) => B): B =
    this match {
      case End()        => end
      case Pair(hd, tl) => f(hd, tl.fold(end, f))
    }

  // Implement `length`, returning the length of the LinkedList.
  def length: Int =
    this match {
      case End()         => 0
      case Pair(_, tail) => 1 + tail.length
    }

  // Implement a method `contains`
  //  that determines whether or not a given item is in the list.

  def contains(item: A): Boolean =
    item match {
      case Pair(head, tail) =>
        if (item == head) {
          true
        } else {
          tail.contains(item)
        }
      case End() => false
    }

  // Implement a method apply that returns the nth item in the list.
  //  Hint: if you need to signal an error in your code (there’s one situation in which
  //  you will need to do this), consider throwing an exception.
  //  Here is an example:
  //    throw new Exception("Bad things happened")
  def applyOnIntList(item: Int): Result[A] =
    this match {
      case Pair(head, tail) =>
        if (item == 0) {
          Compute(head)
        } else {
          tail.applyOnIntList(item - 1)
        }

      case End() => Error("Empty list")
    }
}

final case class End[A]() extends LinkedList[A]
final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]

/*
Given the following list
val list: LinkedList[Int] = Pair(1, Pair(2, Pair(3, End())))
• double all the elements in the list;
• add one to all the elements in the list; and
• divide by three all the elements in the list.
 */
object Mapping {
  val list: LinkedList[Int] = Pair(1, Pair(2, Pair(3, End())))

  list.map(_ * 2)
  list.map(_ + 1)
  list.map(_ / 3)
}
