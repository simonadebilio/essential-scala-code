package _mySolutions

// Define a trait called Shape and give it three abstract methods:
//    • sides returns the number of sides;
//    • perimeter returns the total length of the sides;
//    • area returns the area.
//  Implement Shape with three classes: Circle, Rectangle, and Square.
//  In each case provide implementations of each of the three methods.
//  Ensure that the main constructor parameters of each shape (e.g. the radius of the circle)
//  are accessible as fields.
trait Shape {
  def sides: Int
  def perimeter: Double
  def area: Double
}

case class Circle(radius: Int) extends Shape {
  val sides: Int = 1
  val perimeter: Double = math.Pi * (radius * 2)
  val area: Double = math.Pi * radius * radius
}

case class Rectangle(base: Double, height: Double) extends Shape {
  val sides: Int = 4
  val perimeter: Double = (base * 2) + (height * 2)
  val area: Double = base * height
}

case class Square(sideLenght: Int) extends Shape {
  val sides: Int = 4
  val perimeter: Double = sideLenght * 4
  val area: Double = sideLenght * sideLenght
}