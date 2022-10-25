package molecule.boilerplate.ops

import molecule.boilerplate.api.Keywords.Kw
import molecule.boilerplate.ast.MoleculeModel._
import scala.collection.immutable.Seq

trait ModelTransformations {
  protected def toInt(es: Seq[Element], kw: Kw): Seq[Element] = {
    val last = es.last match {
      case AtomOneManString(ns, attr, _, _, _, _, _) => AtomOneManInt(ns, attr, Fn(kw.toString))
      case AtomOneManInt(ns, attr, _, _, _, _, _)    => AtomOneManInt(ns, attr, Fn(kw.toString))
      case other                                     => other
    }
    es.init :+ last
  }

  protected def toDouble(es: Seq[Element], kw: Kw): Seq[Element] = {
    val last = es.last match {
      case AtomOneManString(ns, attr, _, _, _, _, _) => AtomOneManDouble(ns, attr, Fn(kw.toString))
      case AtomOneManInt(ns, attr, _, _, _, _, _)    => AtomOneManDouble(ns, attr, Fn(kw.toString))
      case other                                     => other
    }
    es.init :+ last
  }

  protected def toList(es: Seq[Element], kw: Kw, n: Option[Int]): Seq[Element] = {
    val last = es.last match {
      case a: AtomOneManString => a.copy(op = Fn(kw.toString, n))
      case a: AtomOneManInt    => a.copy(op = Fn(kw.toString, n))
      case other               => other
    }
    es.init :+ last
  }

  protected def asIs(es: Seq[Element], kw: Kw): Seq[Element] = {
    val last = es.last match {
      case a: AtomOneManString => a.copy(op = Fn(kw.toString))
      case a: AtomOneManInt    => a.copy(op = Fn(kw.toString))
      case other               => other
    }
    es.init :+ last
  }

  protected def addVs[T](es: Seq[Element], op: Op, vs: Seq[T]): Seq[Element] = {
    val last = es.last match {
      case a: AtomOneManString => a.copy(op = op, vs = vs.asInstanceOf[Seq[String]])
      case a: AtomOneManInt    => a.copy(op = op, vs = vs.asInstanceOf[Seq[Int]])
      case other               => other
    }
    es.init :+ last
  }

  protected def addOptVs[T](es: Seq[Element], op: Op, vs: Option[Seq[T]]): Seq[Element] = {
    val last = es.last match {
      case a: AtomOneOptString => a.copy(op = op, vs = vs.getOrElse(Nil).asInstanceOf[Seq[String]])
      case a: AtomOneOptInt    => a.copy(op = op, vs = vs.getOrElse(Nil).asInstanceOf[Seq[Int]])
      case other               => other
    }
    es.init :+ last
  }

  protected def addSort(es: Seq[Element], sort: String): Seq[Element] = {
    val last = es.last match {
      case a: AtomOneManString => a.copy(sort = sort)
      case a: AtomOneManInt    => a.copy(sort = sort)
      case other               => other
    }
    es.init :+ last
  }
}
