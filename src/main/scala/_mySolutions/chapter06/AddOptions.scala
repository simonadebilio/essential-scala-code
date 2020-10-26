package _mySolutions.chapter06

import java.lang.Exception

import jdk.vm.ci.meta.ResolvedJavaMethod.Parameter

import scala.io.StdIn

/*
Write a method addOptions that accepts two parameters of type Option[Int] and adds them together.
Use a for comprehension to structure your code.
Write a second version of your code using map and flatMap instead of a for comprehension.
 */
object AddOptions {
  def addOptions(par1: Option[Int], par2: Option[Int]): Option[Int] = {
    for {
      p1 <- par1
      p2 <- par2
    } yield p1 + p2
  }

  def addOptions2(par1: Option[Int], par2: Option[Int]): Option[Int] = {
    par1.flatMap(p1 => par2.map(p2 => p1 + p2))
  }

  /*
Overload addOptions with another implementation that accepts three Option[Int] parameters
and adds them all together.
Write a second version of your code using map and flatMap instead of a for comprehension.
   */

  def addOptionsOverload(
      par1: Option[Int],
      par2: Option[Int],
      par3: Option[Int]
  ): Option[Int] = {
    for {
      p1 <- par1
      p2 <- par2
      p3 <- par3
    } yield p1 + p2 + p3
  }

  def addOptionsOverload2(
      par1: Option[Int],
      par2: Option[Int],
      par3: Option[Int]
  ): Option[Int] = {
    par1.flatMap(p1 => par2.flatMap(p2 => par3.map(p3 => p1 + p2 + p3)))
  }

  /*
Write a method divide that accepts two Int parameters and divides one by the other.
Use Option to avoid exceptions when the denominator is 0 .
   */
  def divide(numerator: Int, denominator: Int): Option[Int] = {
    if (denominator == 0)
      None
    else
      Some(numerator / denominator)
  }

  /*
Using your divide method and a for comprehension, write a method called divideOptions
that accepts two parameters of type Option[Int] and divides one by the other
   */
  def divideOptions(parameter1: Option[Int], parameter2: Option[Int]) = {
//    parameter1.flatMap(p1 => parameter2.map(p2 => divide(p1, p2)).flatten)
    for {
      p1 <- parameter1
      p2 <- parameter2
      c <- divide(p1, p2)
    } yield c
  }

  /*
Write a method called `calculator` that accepts three string parameters and behaves as follows:
  1. Convert the operands to Ints;
  2. Perform the desired mathematical operator on the two operands:
    • provide support for at least four operations: + , - , * and / ;
    • use Option to guard against errors (invalid inputs or division by zero).
  3. Finally print the result or a generic error message.
Tip: Start by supporting just one operator before extending your method to other cases.
   */
  def calculator(operand1: String, operator: String, operand2: String): Unit = {
    def readInt(input: String): Option[Int] = {
      try {
        Some(input.toInt)
      } catch {
        case _: NumberFormatException => None
      }
    }

    def readOperator(input: String): Option[Int => Int => Int] =
      input match {
        case "+" => Some((i1: Int) => (i2: Int) => i1 + i2)
        case "-" => Some((i1: Int) => (i2: Int) => i1 - i2)
        case "*" => Some((i1: Int) => (i2: Int) => i1 * i2)
        case "/" => Some((i1: Int) => (i2: Int) => i1 / i2)
        case _   => None
      }

    val inputs: Option[(Int, Int, Int => Int => Int)] = for {
      o1 <- readInt(operand1)
      o2 <- readInt(operand2)
      op <- readOperator(operator)
    } yield (o1, o2, op)

    inputs match {
      case Some((o1, o2, op)) =>
        try {
          println(op(o1)(o2))
        } catch {
          case e: ArithmeticException => println(s"Something went wrong when calculating: ${e.getMessage}")
        }
      case None => println("Something went wrong when reading inputs")
    }
  }

  def calculator2(operand1: String, operator: String, operand2: String): Unit = {
    def readInt(input: String): Option[Int] = {
      try {
        Some(input.toInt)
      } catch {
        case _: NumberFormatException => None
      }
    }

    def readOperator(input: String): Option[Int => Int => Option[Int]] =
      input match {
        case "+" => Some((i1: Int) => (i2: Int) => Some(i1 + i2))
        case "-" => Some((i1: Int) => (i2: Int) => Some(i1 - i2))
        case "*" => Some((i1: Int) => (i2: Int) => Some(i1 * i2))
        case "/" => Some((i1: Int) => (i2: Int) => Some(i1 / i2))
        case _   => None
      }

    val inputs: Option[(Int, Int, Int => Int => Option[Int])] = for {
      o1 <- readInt(operand1)
      o2 <- readInt(operand2)
      op <- readOperator(operator)
    } yield (o1, o2, op)

    inputs match {
      case Some((o1, o2, op)) =>
        try {
          println(op(o1)(o2))
        } catch {
          case e: ArithmeticException => println(s"Something went wrong when calculating: ${e.getMessage}")
        }
      case None => println("Something went wrong when reading inputs")
    }
  }

  def main(args: Array[String]): Unit = {
    val operand1 = "10"
    val operand2 = "2"
    val operator = "/"

    calculator2(operand1, operator, operand2)
  }
}
