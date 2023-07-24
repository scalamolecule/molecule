package molecule.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import scala.annotation.tailrec

trait Model2Query {

  // Database specific type for row data
  type Row

  def validateQueryModel(elements: List[Element]): Unit = {
    // Generic validation of model for queries

    // We don't do this validation in ModelTransformations_ since we want to catch
    // exceptions within the api action call and not already while composing molecules.
    @tailrec
    def validate(elements: List[Element]): Unit = {
      elements match {
        case element :: tail =>
          element match {
            case a: Attr => validateAttr(a); validate(tail)
            case _       => validate(tail)
          }
        case Nil             => ()
      }
    }

    def validateAttr(a: Attr): Unit = {
      a.filterAttr.foreach { fa =>
        if (fa.name == a.name)
          throw ModelError(s"Can't filter by the same attribute `${a.name}`")
      }
    }

    elements match {
      case List(a: Attr) if a.attr == "id" =>
        throw ModelError("Can't query for id only. Please add at least one attribute.")

      case _ => validate(elements)
    }
  }
}
