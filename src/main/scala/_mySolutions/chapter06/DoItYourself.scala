package _mySolutions.chapter06

object DoItYourself {

//  Write a method to find the smallest element of a Seq[Int].
  def smallestElement(sequence: Seq[Int]): Int = {
    sequence.foldRight(Int.MaxValue) { (n, m) => math.min(n, m) }
  }

  /*
    Given Seq(1, 1, 2, 4, 3, 4) create the sequence containing each number only once.
    Order is not important, so Seq(1, 2, 4, 3) or Seq(4, 3, 2, 1) are equally valid answers.
    Hint: Use contains to check if a sequence contains a value.
   */
  def onlyOnce(sequence: Seq[Int]): Seq[Int] = {
    sequence.foldRight(Seq.empty[Int]) { (current, result) =>
      if (result.contains(current)) {
        result
      } else {
        current +: result
      }
    }
  }

  /*
  Write a function that reverses the elements of a sequence.
  Your output does not have to use the same concrete implementation as the input.
  Hint: use foldLeft.
   */
  def reversingFunction[A](sequence: Seq[A]): Seq[A] = {
    sequence.foldLeft(Seq.empty[A]) { (b, a) => a +: b }
  }

// Write map in terms of foldRight.
  def myMap[A, B](sequenceA: Seq[A], f: A => B): Seq[B] = {
    sequenceA.foldRight(Seq.empty[B]) { (a, sequenceB) => f(a) +: sequenceB }
  }

  /*
Write your own implementation of foldLeft that uses foreach and mutable state.
Remember you can create a mutable variable using the var keyword, and assign a new value using = .
   */
  def myFoldLeft[A, B](seq: Seq[A], zero: B, f: (B, A) => B): B = {
    var result = zero
    seq.foreach(a => result = f(result, a))

    result
  }


  def main(args: Array[String]): Unit = {
    val intSequence = Seq(1, 1, 2, 4, 3, 4)

    println(onlyOnce(intSequence))
  }
}
