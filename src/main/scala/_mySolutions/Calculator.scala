package _mySolutions

// An Expression is an Addition, Subtraction, or a Number;
// An Addition has a left and right Expression;
// A Subtraction has a left and right Expression; or
// A Number has a value of type Double .
sealed trait Calculation
final case class Success(result: Double) extends Calculation
final case class Failure(reason: String) extends Calculation

sealed trait Expression {
  // Now implement a method `eval` that converts an Expression to a Double.
  //  Use polymorphism or pattern matching as you see fit. Explain your choice of
  //  implementation method.
  def eval(element: Expression): Calculation = {
    element match {
      case Addition(left, right) =>
        eval(left) match {
          case Failure(reason) => Failure(reason)
          case Success(result1) =>
            eval(right) match {
              case Success(result2) => Success(result1 + result2)
              case Failure(reason)  => Failure(reason)
            }
        }

      case Subtraction(left, right) =>
        eval(left) match {
          case Failure(reason) => Failure(reason)
          case Success(result1) =>
            eval(right) match {
              case Success(result2) => Success(result1 - result2)
              case Failure(reason)  => Failure(reason)
            }
        }

      case Number(value) => Success(value)

      case Division(left, right) =>
        eval(left) match {
          case Failure(reason) => Failure(reason)
          case Success(result1) =>
            eval(right) match {
              case Failure(reason) => Failure(reason)
              case Success(result2) =>
                if (result2 == 0)
                  Failure("Division by zero")
                else
                  Success(result1 / result2)
            }
        }
      case SquareRoot(element) =>
        eval(element) match {
          case Failure(reason) => Failure(reason)
          case Success(element) =>
            if (element < 0)
              Failure("Square root of negative number")
            else
//            Success(element /2)
              Success(Math.sqrt(element))
        }
    }
  }
}

case class Addition(left: Expression, right: Expression) extends Expression
case class Subtraction(left: Expression, right: Expression) extends Expression
case class Number(value: Double) extends Expression

// We’re now going to add some expressions that call fail: division and square root.
//  Start by extending the abstract syntax tree to include representations
//  for Division and SquareRoot.
case class Division(left: Expression, right: Expression) extends Expression
case class SquareRoot(element: Expression) extends Expression

// Now we’re going to change eval to represent that a computation can fail.
//  (Double uses NaN to indicate a computation failed, but we want to be helpful
//   to the user and tell them why the computation failed.)
//   Implement an appropriate algebraic data type, then change `eval`
//   to return your result type.
