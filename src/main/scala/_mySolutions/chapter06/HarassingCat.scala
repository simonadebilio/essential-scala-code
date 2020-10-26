package _mySolutions.chapter06

/*
  I put my food into the oven and after some time it is ready to eat and
  produces delicious smell with probability 0.3 and otherwise it is still raw and
  produces no smell with probability 0.7.
  If there are delicious smells the cat comes to harass me with probability 0.8,
  and otherwise it stays asleep. If there is no smell the cat harasses me for the hell of it
  with probability 0.4 and otherwise stays asleep.
  Implement this model and answer the question: if the cat comes to harass me
  what is the probability my food is producing delicious smells (and therefore is ready to eat)?
 */
object HarassingCat {

  sealed trait Food

  case object Cooked extends Food

  case object Raw extends Food

  sealed trait Cat

  case object Harassing extends Cat

  case object Asleep extends Cat

  val probabilityFoodSmells: Distribution[Food] = Distribution(List(Raw -> 0.7, Cooked -> 0.3))

  def cat(food: Food): Distribution[Cat] =
    food match {
      case Cooked =>
        Distribution(List(Harassing -> 0.8, Asleep -> 0.2))
      case Raw =>
        Distribution(List(Harassing -> 0.4, Asleep -> 0.6))
    }

  val foodModel: Distribution[(Food, Cat)] = {
    //    probabilityFoodSmells.flatMap(f => cat(f).map(c => (f, c)))
    for {
    f <- probabilityFoodSmells
    c <- cat(f)
    } yield (f, c)
  }
}
