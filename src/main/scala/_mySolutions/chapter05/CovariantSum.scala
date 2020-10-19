package _mySolutions.chapter05

sealed trait CovariantSum[+A, +B] {
  def flatMap[AA >: A, C](f: B => CovariantSum[AA, C]): CovariantSum[AA, C] =
    this match {
      case CovLeft(a) => CovLeft(a)
      case CovRight(a) => f(a)
    }
}
final case class CovLeft[A](a: A) extends CovariantSum[A, Nothing]
final case class CovRight[B](a: B) extends CovariantSum[Nothing, B]

