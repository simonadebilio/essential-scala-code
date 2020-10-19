package _mySolutions.chapter05

/*
Implement a trait Sum[A, B] with two subtypes Left and Right.
Create type parameters so that Left and Right can wrap up values of two different types.
Hint: you will need to put both type parameters on all three types.
Example usage:
  Left[Int, String](1).value
  // res9: Int = 1
  Right[Int, String]("foo").value
  // res10: String = foo
  val sum: Sum[Int, String] = Right("foo")
  // sum: sum.Sum[Int,String] = Right(foo)
  sum match {
    case Left(x) => x.toString
    case Right(x) => x
  }
 */
trait Sum[A, B] {
  def fold[C](l: A => C, r: B => C): C =
    this match {
      case Failure(value) => l(value)
      case Success(value) => r(value)
    }

  /*
  Now things are going to get a bit trickier. We are going to implement map and flatMap,
  again using pattern matching in the Sum trait.
  Start with map. The general recipe for map is to start with a type like F[A] and apply a function
  A => B to get F[B].
  Sum however has two generic type parameters. To make it fit the F[A] pattern
  we’re going to fix one of these parameters and allow map to alter the other one.
  The natural choice is to fix the type parameter associated with Failure and allow map to alter a Success.
  This corresponds to “fail-fast” behaviour. If our Sum has failed, any sequenced computations don’t get run.
  In summary map should have type: def map[C](f: B => C): Sum[A, C]
   */
  def map[C](f: B => C): Sum[A, C] =
    this match {
      case Failure(value) => Failure(value)
      case Success(value) => Success(f(value))
    }

//  Now implement flatMap using the same logic as map.
  def flatMap[C](f: B => Sum[A, C]): Sum[A, C] =
    this match {
      case Failure(value) => Failure(value)
      case Success(value) => f(value)
    }

}
case class Failure[A, B](value: A) extends Sum[A, B]
case class Success[A, B](value: B) extends Sum[A, B]

object Main {
  def main(args: Array[String]): Unit = {
    Failure[Int, String](1).value
    // res9: Int = 1
    Success[Int, String]("foo").value
    // res10: String = foo
    val sum: Sum[Int, String] = Success("foo")
    // sum: sum.Sum[Int,String] = Right(foo)
    println(sum match {
      case Failure(x) => x.toString
      case Success(x) => x
    })




  }
}
