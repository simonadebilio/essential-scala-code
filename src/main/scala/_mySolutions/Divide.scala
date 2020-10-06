package _mySolutions

// Create an object called "divide" with an apply method that accepts two Ints
//  and returns DivisionResult . DivisionResult should be a sealed trait with
//  two subtypes: a Finite type encapsulating the result of a valid division, and
//  an Infinite type representing the result of dividing by 0 .

sealed trait DivisionResult
final case class Finite(value: Int) extends DivisionResult
case object Infinite extends DivisionResult

object Divide {
  def apply(num: Int, den: Int): DivisionResult = {
    den match {
      case 0 => Infinite
      case _ => Finite(num / den)
    }
  }
}

// Finally, write some sample code that calls divide, matches on the result,
// and returns a sensible description.
object Main {
  def main(args: Array[String]): Unit = {
    println(Divide.apply(4, 2) match {
      case Finite(value) => s"It is Finite: $value"
      case Infinite      => s"The result is infinite!"
    })

  }
}