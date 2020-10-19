package _mySolutions.chapter05

/*
Create a generic trait called Maybe of a generic type A with two subtypes,
Full containing an A, and Empty containing no value. Example usage:
  val perhaps: Maybe[Int] = Empty[Int]
  val perhaps: Maybe[Int] = Full(1)
 */
trait Maybe[A] {
  def flatMap[B](f: A => Maybe[B]): Maybe[B] =
    this match {
      case Empty()     => Empty()
      case Full(value) => f(value)
    }

  // Implement `map` for Maybe
  def map[B](f: A => B): Maybe[B] =
    this match {
      case Empty()     => Empty()
      case Full(value) => Full(f(value))
    }

  // For bonus points, implement map in terms of flatMap.
  def mapInTermsOfFlatMap[B](fn: A => B): Maybe[B] =
    flatMap(a => Full(fn(a)))

}
final case class Full[A](value: A) extends Maybe[A]
final case class Empty[A]() extends Maybe[A]
