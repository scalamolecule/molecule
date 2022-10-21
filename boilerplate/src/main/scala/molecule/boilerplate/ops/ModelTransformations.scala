package molecule.boilerplate.ops

import molecule.boilerplate.api.keywords.Kw
import molecule.boilerplate.ast.moleculeModel._
import scala.collection.immutable.Seq

trait ModelTransformations {
  protected def toInt(es: Seq[Element], kw: Kw): Seq[Element] = {
    val last = es.last match {
      case AtomManString(ns, attr, _, _, _, _, _) => AtomManInt(ns, attr, Fn(kw.toString))
      case AtomManInt(ns, attr, _, _, _, _, _)    => AtomManInt(ns, attr, Fn(kw.toString))
      case other                                  => other
    }
    es.init :+ last
  }

  protected def toDouble(es: Seq[Element], kw: Kw): Seq[Element] = {
    val last = es.last match {
      case AtomManString(ns, attr, _, _, _, _, _) => AtomManDouble(ns, attr, Fn(kw.toString))
      case AtomManInt(ns, attr, _, _, _, _, _)    => AtomManDouble(ns, attr, Fn(kw.toString))
      case other                                  => other
    }
    es.init :+ last
  }

  protected def toList(es: Seq[Element], kw: Kw, n: Option[Int]): Seq[Element] = {
    val last = es.last match {
      case a: AtomManString => a.copy(op = Fn(kw.toString, n))
      case a: AtomManInt    => a.copy(op = Fn(kw.toString, n))
      case other            => other
    }
    es.init :+ last
  }

  protected def asIs(es: Seq[Element], kw: Kw): Seq[Element] = {
    val last = es.last match {
      case a: AtomManString => a.copy(op = Fn(kw.toString))
      case a: AtomManInt    => a.copy(op = Fn(kw.toString))
      case other            => other
    }
    es.init :+ last
  }

  protected def addVs[T](es: Seq[Element], op: Op, vs: Seq[T]): Seq[Element] = {
    val last = es.last match {
      case a: AtomManString => a.copy(op = op, vs = vs.asInstanceOf[Seq[String]])
      case a: AtomManInt    => a.copy(op = op, vs = vs.asInstanceOf[Seq[Int]])
      case other            => other
    }
    es.init :+ last
  }

  protected def addOptVs[T](es: Seq[Element], op: Op, vs: Option[Seq[T]]): Seq[Element] = {
    val last = es.last match {
      case a: AtomOptString => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[String]]])
      case a: AtomOptInt    => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[Int]]])
      case other            => other
    }
    es.init :+ last
  }

  protected def addSort(es: Seq[Element], sort: String): Seq[Element] = {
    val last = es.last match {
      case a: AtomManString => a.copy(sort = sort)
      case a: AtomManInt    => a.copy(sort = sort)
      case other            => other
    }
    es.init :+ last
  }
}
