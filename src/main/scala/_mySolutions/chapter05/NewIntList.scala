package _mySolutions.chapter05

// We started developing an abstraction over `sum`, `length`, and `product` which we sketched out as:
//    def abstraction(end: Int, f: ???): Int =
//      this match {
//        case End => end
//        case Pair(hd, tl) => f(hd, tl.abstraction(end, f))
//        }
//   Rename this function to `fold`, which is the name it is usually known as, and finish the implementation.
sealed trait NewIntList {
  def fold[A](end: A, f: (Int, A) => A): A =
    this match {
      case NewEnd          => end
      case NewPair(hd, tl) => f(hd, tl.fold(end, f))
    }

//  Now reimplement `sum`, `length`, and `product` in terms of `fold`.
  def length: Int = fold[Int](0, (_, accumulator) => 1 + accumulator)

  def product: Int = fold[Int](1, (current, accumulator) => current * accumulator)

  def sum: Int = fold[Int](0, (current, accumulator) => current + accumulator)

  def double: NewIntList =
    fold[NewIntList](
      NewEnd,
      (current, accumulator) => NewPair(current * 2, accumulator)
    )
//    this match {
//      case End => End
//      case Pair(hd, tl) => Pair(hd * 2, tl.double)
//    }
}

case object NewEnd extends NewIntList
final case class NewPair(hd: Int, tl: NewIntList) extends NewIntList

object Main {
  def main(args: Array[String]): Unit = {
    val l = NewPair(1, NewPair(2, NewPair(3, NewEnd)))
    println(s"Length: ${l.length}")
  }
}

// We started developing an abstraction over sum, length, and product which we sketched out as:
//    def abstraction(end: Int, f: ???): Int =
//      this match {
//        case End => end
//        case Pair(hd, tl) => f(hd, tl.abstraction(end, f))
//        }
//   Rename this function to `fold`, which is the name it is usually known as, and finish the implementation.
