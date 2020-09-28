package _mySolutions

// Define a trait called Shape and give it three abstract methods:
//    • sides returns the number of sides;
//    • perimeter returns the total length of the sides;
//    • area returns the area.
//  Implement Shape with three classes: Circle, Rectangle, and Square.
//  In each case provide implementations of each of the three methods.
//  Ensure that the main constructor parameters of each shape (e.g. the radius of the circle)
//  are accessible as fields.
//trait Shape {
//  def sides: Int
//  def perimeter: Double
//  def area: Double
//}

// Let’s revisit Shape:
//  - First make Shape a sealed trait
//  - Then write a singleton object called Draw with an apply method
//    that takes a Shape as an argument and returns a description of it on the console.
sealed trait Shape {
  def sides: Int
  def perimeter: Double
  def area: Double
}

object Draw {
  def apply(shape: Shape): String = {
    shape match {
      case Circle(radius) => s"Draw a circle of radius $radius cm"
      case rectangular: Rectangular =>
        rectangular match {
          case Square(sideLength) =>
            s"Draw a square with side length $sideLength cm"
          case Rectangle(base, height) =>
            s"Draw a rectangle of base $base cm, and height $height cm"
        }
    }
  }

  def main(args: Array[String]): Unit = {
    println(apply(Circle(10)))
  }
}

case class Circle(radius: Int) extends Shape {
  val sides: Int = 1
  val perimeter: Double = math.Pi * (radius * 2)
  val area: Double = math.Pi * radius * radius
}

//case class Rectangle(base: Double, height: Double) extends Shape {
//  val sides: Int = 4
//  val perimeter: Double = (base * 2) + (height * 2)
//  val area: Double = base * height
//}
//
//case class Square(sideLenght: Int) extends Shape {
//  val sides: Int = 4
//  val perimeter: Double = sideLenght * 4
//  val area: Double = sideLenght * sideLenght
//}

// The solution from the last exercise delivered three distinct types of shape.
//  However, it doesn’t model the relationships between the three correctly.
//  A Square isn’t just a Shape — it’s also a type of Rectangle where the width and
//  height are the same.
//  Refactor the solution to the last exercise so that Square and Rectangle are
//  subtypes of a common type Rectangular.
sealed trait Rectangular extends Shape {
  def base: Double
  def height: Double
  val sides: Int = 4

  override def area: Double = base * height
  override def perimeter: Double = (base * 2) + (height * 2)
}

case class Square(sideLenght: Double) extends Rectangular {
  val base: Double = sideLenght
  val height: Double = sideLenght
}

case class Rectangle(base: Double, height: Double) extends Rectangular
