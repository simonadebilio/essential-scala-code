package _mySolutions.chapter04

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
  def color: Color
}

case class Circle(radius: Int, color: Color) extends Shape {
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

case class Square(sideLength: Double, color: Color) extends Rectangular {
  val base: Double = sideLength
  val height: Double = sideLength
}

case class Rectangle(base: Double, height: Double, color: Color) extends Rectangular

// Write a sealed trait Color to make our shapes more interesting.
//    • give Color three properties for its RGB values;
//    • create three predefined colours: Red, Yellow, and Pink;
//    • provide a means for people to produce their own custom Colors with their own RGB values;
//    • provide a means for people to tell whether any Color is “light” or “dark”.
sealed trait Lightness
object Dark extends Lightness
object Light extends Lightness

sealed trait Color {
  def red: Int
  def green: Int
  def blue: Int

  def lightness: Lightness
}

object Red extends Color {
  override def red: Int = 255

  override def green: Int = 0

  override def blue: Int = 0

  override def lightness: Lightness = Light
}

object Yellow extends Color {
  override def red: Int = 255

  override def green: Int = 255

  override def blue: Int = 0

  override def lightness: Lightness = Light
}

object Pink extends Color {
  override def red: Int = 255

  override def green: Int = 127

  override def blue: Int = 127

  override def lightness: Lightness = Light
}

case class CustomColor(red: Int, green: Int, blue: Int) extends Color {
  override def lightness: Lightness =
    if ((red >= 127 && green >= 127) ||
        (red >= 127 && blue >= 127) ||
        (green >= 127 && blue >= 127)) Light
    else Dark
}

//  Finally, update the code for Draw.apply to print the colour of the argument
//   as well as its shape and dimensions:
//    • if the argument is a predefined colour, print that colour by name
object Draw {
  def apply(shape: Shape): String = {
    shape match {
      case Circle(radius, color) =>
        s"Draw a ${Draw(color)} circle of radius $radius cm"
      case rectangular: Rectangular =>
        rectangular match {
          case Square(sideLength, color) =>
            s"Draw ${Draw(color)} a square with side length $sideLength cm"
          case Rectangle(base, height, color) =>
            s"Draw a ${Draw(color)} rectangle of base $base cm, and height $height cm"
        }
    }
  }

  def apply(color: Color): String = {
    color match {
      case Red => "red"
      case Yellow => "yellow"
      case Pink => "pink"
      case CustomColor(red, green, blue) => " "
    }
  }

  def main(args: Array[String]): Unit = {
    println(Draw(Circle(10, Pink)))
  }
}
