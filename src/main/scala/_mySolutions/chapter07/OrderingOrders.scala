package _mySolutions.chapter07

object OrderingOrders {

  /*
  Here is a case class to store orders of some arbitrary item.
      final case class Order(units: Int, unitPrice: Double) {
        val totalPrice: Double = units * unitPrice
      }
  We have a requirement to order Orders in three different ways:
      1. by totalPrice;
      2. by number of units; and
      3. by unitPrice.
  Implement and package implicits to provide these orderings
   */

  final case class Order(units: Int, unitPrice: Double) {
    val totalPrice: Double = units * unitPrice
  }

  object Order {
    implicit val byTotalPrice: Ordering[Order] = Ordering.fromLessThan(
      _.totalPrice < _.totalPrice
    )
  }

  object ByNumberOfUnits {
    implicit val byNumberOfUnits: Ordering[Order] = Ordering.fromLessThan(
      _.units < _.units
    )
  }

  object ByUnitPrice {
    implicit val byUnitPrice: Ordering[Order] = Ordering.fromLessThan(
      _.unitPrice < _.unitPrice
    )
  }

}
