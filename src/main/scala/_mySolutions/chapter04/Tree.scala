package _mySolutions.chapter04

// A binary tree of integers can be defined as follows:
//  A Tree is a Node with a left and right Tree or a Leaf with an element of type Int.
//sealed trait Tree
//final case class Node(left: Tree, right: Tree) extends Tree
//final case class Leaf(element: Int) extends Tree
//
//// Implement sum and double on Tree using pattern matching.
//object Operations {
//  def sum(tree: Tree): Int =
//    tree match {
//      case Node(left, right) => sum(left) + sum(right)
//      case Leaf(element) => element
//    }
//
//  def double (tree: Tree): Int =
//    tree match {
//      case Node(left, right) => double(left) + double(right)
//      case Leaf(element) => element * 2
//    }
//
//  def double2 (tree: Tree): Tree =
//    tree match {
//      case Node(left, right) => Node(double2(left), double2(right))
//      case Leaf(element) => Leaf(element * 2)
//    }
//}

// Implement sum and double on Tree using polymorphism.
trait Tree {
  def sum: Int
  def double: Tree
}

case class Node(left: Tree, right: Tree) extends Tree {
  def sum: Int = left.sum + right.sum
  def double: Tree = Node(left.double, right.double)

}

case class Leaf(element: Int) extends Tree {
  def sum: Int = element
  def double: Tree = Leaf(element * 2)

}

