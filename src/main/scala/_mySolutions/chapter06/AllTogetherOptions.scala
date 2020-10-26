package _mySolutions.chapter06

import scala.util.Try

/*
Add together all the options to create a new option.
Add together all the sequences to create a new sequence.
Add together all the trys to create a new try.
Use a for comprehension for each. It shouldn’t take you long!
 */
object AllTogether {
  val opt1 = Some(1)
  val opt2 = Some(2)
  val opt3 = Some(3)

  def allTogetherOptions(opt1: Option[Int], opt2: Option[Int], opt3: Option[Int]): Option[Int] = {
    for {
    o1 <- opt1
    o2 <- opt2
    o3 <- opt3
    } yield (o1 + o2 + o3)
  }

  val seq1: Seq[Int] = Seq(1)
  val seq2: Seq[Int] = Seq(2)
  val seq3: Seq[Int] = Seq(3)

  def allTogetherSequences(seq1: Seq[Int], seq2: Seq[Int], seq3: Seq[Int]): Seq[Int] = {
    for {
      s1 <- seq1
      s2 <- seq2
      s3 <- seq3
    } yield (s1 + s2 + s3)
  }

  val try1 = Try(1)
  val try2 = Try(2)
  val try3 = Try(3)

  def allTogetherTries(try1: Try[Int], try2: Try[Int], try3: Try[Int]): Try[Int] = {
    for {
      t1 <- try1
      t2 <- try2
      t3 <- try3
    } yield (t1 + t2 + t3)
  }

  def main(args: Array[String]): Unit = {
    val example = scala.collection.mutable.TreeMap[String, Int]()

    example("l") = 7
    println(example)

  }


}
