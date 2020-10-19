package _mySolutions.chapter05

/*
Imagine we have a list of Int user IDs, and a func on which, given a user ID, returns a User record.
We want to get a list of user records for all the IDs in the list.
Written as types we have List[Int] and a function Int => User, and we want to get a List[User].
 */

final case class User(id: Int)
object UserManager {
  def getUserById(id: Int): User = ???
  def getUserFromDb(id: Int): Maybe[User] = ???
}

object User {
  val userIds: List[Int] = List(1, 2, 3)
  val users: List[User] = userIds.map(UserManager.getUserById)
}

/*
Imagine we have an optional value representing a user record loaded from the database and a function
that will load their most recent order.
If we have a record we want to then lookup the user’s most recent order.
That is, we have a Maybe[User] and a function User => Order, and we want a Maybe[Order].
 */
case class Order(orderNumber: Int)

object OrderManager {
  def getMostRecentOrder(user: User): Order = ???
}

object User2 {
  val maybeUser: Maybe[User] = UserManager.getUserFromDb(123)
  val maybeOrder: Maybe[Order] = maybeUser.map(OrderManager.getMostRecentOrder)
}

/*
Imagine we have a sum type representing an error message or a completed order.
If we have a completed order we want to get the total value of the order.
That is, we have a Sum[String, Order] and a function Order => Double, and we want Sum[String, Double].
 */
object User3 {
  val order: Order = Order(4)
  val completedOrder: Sum[String, Order] = Success(order)

  def orderTotal(order: Order): Double = ???

  val total: Sum[String, Double] = completedOrder.map(orderTotal)
}
