package _mySolutions.chapter07
import scala.math.abs

object MyOrdering {

  /*
  Define an Ordering that orders Ints from lowest to highest by absolute value.
  The following test cases should pass.

    assert(List(-4, -1, 0, 2, 3).sorted(absOrdering) == List(0, -1, 2, 3, -4))
    assert(List(-4, -3, -2, -1).sorted(absOrdering) == List(-1, -2, -3, -4))

  Now make your ordering an implicit value, so the following test cases work.

    assert(List(-4, -1, 0, 2, 3).sorted == List(0, -1, 2, 3, -4))
    assert(List(-4, -3, -2, -1).sorted == List(-1, -2, -3, -4))
   */

  implicit val absOrdering: Ordering[Int] = Ordering.fromLessThan[Int](abs(_) < abs(_))

  /*
  Scala does not have a class to represent rational numbers, but we can easily implement one ourselves.
        final case class Rational(numerator: Int, denominator: Int)
  Implement an Ordering for Rational to order rationals from smallest to largest.
  The following test case should pass.
     assert(List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted ==
     List(Rational(1, 3), Rational(1, 2), Rational(3, 4)))
   */

  final case class Rational(numerator: Int, denominator: Int)

  case object Rational {
    def realValue(r: Rational): Double = r.numerator.toDouble / r.denominator

    implicit val rationalOrdering: Ordering[Rational] = Ordering.fromLessThan[Rational](
      realValue(_) < realValue(_)
    )
  }

  def main(args: Array[String]): Unit = {
    assert(List(-4, -1, 0, 2, 3).sorted(absOrdering) == List(0, -1, 2, 3, -4))
    assert(List(-4, -3, -2, -1).sorted(absOrdering) == List(-1, -2, -3, -4))

    assert(List(-4, -1, 0, 2, 3).sorted == List(0, -1, 2, 3, -4))
    assert(List(-4, -3, -2, -1).sorted == List(-1, -2, -3, -4))

    assert(List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted ==
      List(Rational(1, 3), Rational(1, 2), Rational(3, 4)))
  }
}
