package molecule.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import scala.annotation.tailrec
import scala.collection.mutable

trait Model2Query {

  // Database specific type for row data
  type Row

  private var level         = 1
  private val sortsPerLevel = mutable.Map[Int, List[Int]](1 -> Nil)

  def validateQueryModel(elements: List[Element]): Unit = {
    // Generic validation of model for queries

    // We don't do this validation in ModelTransformations_ since we want to catch
    // exceptions within the api action call and not already while composing molecules.
    @tailrec
    def validate(elements: List[Element], prevElements: List[Element] = Nil): Unit = {
      elements match {
        case element :: tail =>
          element match {
            case a: Attr          => validateAttr(a); validate(tail, prevElements :+ a)
            case Nested(_, es)    => validateNested(es, prevElements)
            case NestedOpt(_, es) => validateNestedOpt(es, prevElements)
            case _                => validate(tail, prevElements)
          }
        case Nil             => ()
      }
    }

    def validateAttr(a: Attr): Unit = {
      if (a.sort.nonEmpty) {
        sortsPerLevel(level) = sortsPerLevel(level) :+ a.sort.get.substring(1, 2).toInt
      }
      a.filterAttr.foreach { fa =>
        if (fa.name == a.name)
          throw ModelError(s"Can't filter by the same attribute `${a.name}`")
      }
    }

    def validateNested(es: List[Element], prevElements: List[Element]): Unit = {
      level += 1
      sortsPerLevel.addOne(level -> Nil)
      // Nested is the last element, so we can just validate next level elements
      validate(es, prevElements)
    }

    def validateNestedOpt(es: List[Element], prevElements: List[Element]): Unit = {
      level += 1
      sortsPerLevel.addOne(level -> Nil)
      if (prevElements.length == 1) {
        prevElements.head match {
          case _: AttrOneOpt => throw ModelError(
            s"Single optional attribute before optional nested data structure is not allowed."
          )
          case _             => ()
        }
      }
      // Nested is the last element, so we can just validate next level elements
      validate(es, prevElements)
    }

    // Traverse model
    elements match {
      case List(a: Attr) if a.attr == "id" => throw ModelError(
        "Querying for the entity id only is not allowed. " +
          "Please add at least one attribute (can be tacit).")

      case _ => validate(elements)
    }

    sortsPerLevel.foreach {
      case (_, Nil)         => ()
      case (level, indexes) => indexes.sorted match {
        case Nil                    =>
        case List(1)                =>
        case List(6)                =>
        case List(1, 2)             =>
        case List(1, 6)             =>
        case List(1, 2, 3)          =>
        case List(1, 2, 6)          =>
        case List(1, 2, 3, 4)       =>
        case List(1, 2, 3, 6)       =>
        case List(1, 2, 3, 4, 5)    =>
        case List(1, 2, 3, 4, 6)    =>
        case List(1, 2, 3, 4, 5, 6) =>
        case other                  =>
          val levelMsg = if (level == 1) "" else " on level " + level
          throw ModelError(
            s"Sort index 1 should be present and additional indexes continuously increase (in any order). " +
              s"Found non-unique sort index(es)$levelMsg: " + other.mkString(", ")
          )
      }
    }
  }
}
