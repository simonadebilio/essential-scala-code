package _mySolutions

// A traffic light is red, green, or yellow. Translate this description into Scala code.
//sealed trait TrafficLight
//final class Red extends TrafficLight
//final class Yellow extends TrafficLight
//final class Green extends TrafficLight

// Using polymorphism and then using pattern matching
// implement a method called 'next' which returns the next TrafficLight
// in the standard Red -> Green -> Yellow -> Red cycle.
// Do you think it is better to implement this method inside or outside the class?
// If inside, would you use pattern matching or polymorphism? Why?

// Polymorphism:
//sealed trait TrafficLight{
//  def next: TrafficLight
//}
//
//case object Green extends TrafficLight {
//  def next: TrafficLight = Yellow
//}
//
//case object Yellow extends TrafficLight {
//  def next: TrafficLight = Red
//}
//
//case object Red extends TrafficLight {
//  def next: TrafficLight = Green
//}

// Pattern matching:
sealed trait TrafficLight {
  def next: TrafficLight =
    this match {
      case Green => Yellow
      case Yellow => Red
      case Red => Green
    }
}

case object Green extends TrafficLight
case object Yellow extends TrafficLight
case object Red extends TrafficLight


