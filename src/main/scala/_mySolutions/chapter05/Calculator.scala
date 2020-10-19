package _mySolutions.chapter05

import scala.Double.NaN
import scala.math.sqrt

object Calculator {
  sealed trait Expression {

    def lift2(
        e1: Expression,
        e2: Expression,
        f: (Double, Double) => Sum[String, Double]
    ): Sum[String, Double] =
      e1.eval.flatMap(d1 => e2.eval.flatMap(d2 => f(d1, d2)))

    def eval: Sum[String, Double] =
      this match {
        case Addition(left, right) =>
          lift2(left, right, (l, r) => Success(l + r))

        case Subtraction(left, right) =>
          lift2(left, right, (l, r) => Success(l - r))

        case Division(left, right) =>
          lift2(left, right,
            (l, r) =>
              if (r == 0) {
                Failure("Cannot divide by 0")
              } else {
                Success(l / r)
              }
          )

        case SquareRoot(e) =>
          e.eval.flatMap(v =>
            sqrt(v) match {
              case NaN => Failure("Not a number")
              case n   => Success(n)
            }
          )

        case Number(n) => Success(n)
      }
  }

  final case class Addition(left: Expression, right: Expression)
      extends Expression
  final case class Subtraction(left: Expression, right: Expression)
      extends Expression
  final case class Division(left: Expression, right: Expression)
      extends Expression
  final case class SquareRoot(expr: Expression) extends Expression
  final case class Number(n: Double) extends Expression
  /*
  Now implement a method eval: Sum[String, Double] on Expression.
    Use flatMap and map on Sum and introduce any utility methods you see fit to make the code more compact.
 */
}
