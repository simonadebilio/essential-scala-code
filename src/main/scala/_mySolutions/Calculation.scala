package _mySolutions

// A calculation may succeed (with an Int result) or fail (with a String message).
//  Implement this.
sealed trait Calculation
final case class Success(result: Int) extends Calculation
final case class Failure(reason: String) extends Calculation

// Create a Calculator object. On Calculator define methods + and - that accept
// a Calculation and an Int , and return a new Calculation.
// Here are some examples:
//    assert(Calculator.+(Success(1), 1) == Success(2))
//    assert(Calculator.-(Success(1), 1) == Success(0))
//    assert(Calculator.+(Failure("Badness"), 1) == Failure("Badness"))
case object Calculator {
  def +(calc: Calculation, n: Int): Calculation =
    calc match {
      case Success(result) => Success(result + n)
      case Failure(reason) => Failure(reason)
    }

  def -(calc: Calculation, n: Int): Calculation =
    calc match {
      case Success(result) => Success(result - n)
      case Failure(reason) => Failure(reason)
    }

  // Now write a division method that fails if the divisor is 0.
  // The following tests should pass. Note the behavior for the last test.
  // This indicates “fail fast” behavior. If a calculation has already failed
  // we keep that failure and don’t process any more data even if, as is the case in the test,
  // doing so would lead to another failure.
  //assert(Calculator./(Success(4), 2) == Success(2))
  //assert(Calculator./(Success(4), 0) == Failure("Division by zero"))
  //assert(Calculator./(Failure("Badness"), 0) == Failure("Badness"))
  def /(calc: Calculation, n: Int): Calculation =
    calc match {
      case Success(result) => Success(result / n)
      case Failure(reason) =>
        n match {
          case 0 => Failure("Division by zero")
          case _ => Failure(reason)
        }
    }

}

// Boiled water has a size (an Int ), a source (which is a well, spring, or tap), and
//  a Boolean carbonated. Implement this in Scala.
sealed trait Source
object Well extends Source
object Spring extends Source
object Tap extends Source

sealed trait Water
final class Bottled(size: Int, source: Source, carbonated: Boolean)
    extends Water
