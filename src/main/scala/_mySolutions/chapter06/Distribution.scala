package _mySolutions.chapter06

import scala.collection.TraversableOnce.MonadOps

/*
   Define a class `Distribution` that will wrap a List[(A, Double)].
   We should create some convenience constructors for Distribution.
   A useful one is `uniform` which will accept a List[A] and create a Distribution[A]
   where each element has equal probability.
 */
case class Distribution[A](events: List[(A, Double)]) {
  def map[B](f: A => B): Distribution[B] = {
    Distribution(events.map(pair => f(pair._1) -> pair._2))
  }

  /*
  Now implement flatMap.
  To do so you’ll need to combine the probability of an event with the probability of the event it depends on.
  The correct way to do so is to multiply the probabilities together.
  This may lead to unnormalised probabilities — probabilities that do not sum up to 1.
  You might find the following two utilities useful, though you don’t need to normalise probabilities or
  ensure that elements are unique for the model to work.
   */
  def normalize: Distribution[A] = {
    val totalWeight: Double = (events map { case (a, p) => p }).sum
    Distribution(events map { case (a, p) => a -> (p / totalWeight) })
  }

  def compact: Distribution[A] = {
    val distinct: List[A] = (events map { case (a, p) => a }).distinct
    def prob(a: A): Double =
      (events filter { case (x, p) => x == a } map { case (a, p) => p }).sum
    Distribution(distinct map { a => a -> prob(a) })
  }

  def flatMap[B](f: A => Distribution[B]): Distribution[B] = {
    Distribution(events.flatMap {
      case (a, probability1) =>
        f(a).events.map {
          case (b, probability2) => b -> (probability1 * probability2)
        }
    }).compact.normalize
  }

  def discrete[B](events: List[(B,Double)]): Distribution[B] =
    Distribution(events).compact.normalize
}

case object Distribution {
  def uniform[A](elements: List[A]): Distribution[A] = {
    val probability: Double = 1.0 / elements.length

    Distribution(elements.map(a => (a, probability)))
  }
}
