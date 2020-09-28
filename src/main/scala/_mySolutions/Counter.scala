package _mySolutions

// Implement a Counter class. The constructor should take an Int.
//  The methods inc and dec should increment and decrement the counter respectively returning a new Counter .
class Counter(val count: Int) {
  def inc = new Counter(count + 1)

  def dec = new Counter(count - 1)

// Reimplement Counter as a case class, using copy where appropriate.
//  Additionally initialise count to a default value of 0.
//case class CaseCounter(count: Int = 0) {
//  def dec: CaseCounter = copy(count = count - 1)
//  def inc: CaseCounter = copy(count = count + 1)
//}

//  Augment the Counter from the previous exercise to allow the user
//    can optionally pass an Int parameter to inc and dec.
//  If the parameter is omi ed it should default to 1.
  def inc2(n: Int): Counter = new Counter(count + n)
  def dec2(n: Int): Counter = new Counter(count - n)

// Extend Counter to add a method called adjust . This method should accept
//  an Adder and return a new Counter with the result of applying the Adder to the count.
  def adjust(a: Adder): Counter = new Counter(a.add(count))
}

class Adder(amount: Int) {
  def add(in: Int): Int = in + amount
}
