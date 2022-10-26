package molecule.boilerplate.ops

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.api.Keywords.Kw
import molecule.boilerplate.ast.MoleculeModel._

trait ModelTransformations {

  def unexpected(element: Element) = throw MoleculeException("Unexpected element: " + element)

  protected def toInt(es: Seq[Element], kw: Kw): Seq[Element] = {
    val last = es.last match {
      case a: AtomOneMan => AtomOneManInt(a.ns, a.attr, Fn(kw.toString))
      case a             => unexpected(a)
    }
    es.init :+ last
  }

  protected def toDouble(es: Seq[Element], kw: Kw): Seq[Element] = {
    val last = es.last match {
      case a: AtomOneMan => AtomOneManDouble(a.ns, a.attr, Fn(kw.toString))
      case a             => unexpected(a)
    }
    es.init :+ last
  }

  protected def toList(es: Seq[Element], kw: Kw, n: Option[Int]): Seq[Element] = {
    val last = es.last match {
      case a: AtomOneMan => a match {
        case a: AtomOneManString     => a.copy(op = Fn(kw.toString, n))
        case a: AtomOneManInt        => a.copy(op = Fn(kw.toString, n))
        case a: AtomOneManLong       => a.copy(op = Fn(kw.toString, n))
        case a: AtomOneManFloat      => a.copy(op = Fn(kw.toString, n))
        case a: AtomOneManDouble     => a.copy(op = Fn(kw.toString, n))
        case a: AtomOneManBoolean    => a.copy(op = Fn(kw.toString, n))
        case a: AtomOneManBigInt     => a.copy(op = Fn(kw.toString, n))
        case a: AtomOneManBigDecimal => a.copy(op = Fn(kw.toString, n))
        case a: AtomOneManDate       => a.copy(op = Fn(kw.toString, n))
        case a: AtomOneManUUID       => a.copy(op = Fn(kw.toString, n))
        case a: AtomOneManURI        => a.copy(op = Fn(kw.toString, n))
        case a: AtomOneManChar       => a.copy(op = Fn(kw.toString, n))
        case a: AtomOneManByte       => a.copy(op = Fn(kw.toString, n))
        case a: AtomOneManShort      => a.copy(op = Fn(kw.toString, n))
        case a                       => unexpected(a)
      }
      case a             => unexpected(a)
    }
    es.init :+ last
  }

  protected def asIs(es: Seq[Element], kw: Kw): Seq[Element] = {
    val last = es.last match {
      case a: AtomOneMan => a match {
        case a: AtomOneManString     => a.copy(op = Fn(kw.toString))
        case a: AtomOneManInt        => a.copy(op = Fn(kw.toString))
        case a: AtomOneManLong       => a.copy(op = Fn(kw.toString))
        case a: AtomOneManFloat      => a.copy(op = Fn(kw.toString))
        case a: AtomOneManDouble     => a.copy(op = Fn(kw.toString))
        case a: AtomOneManBoolean    => a.copy(op = Fn(kw.toString))
        case a: AtomOneManBigInt     => a.copy(op = Fn(kw.toString))
        case a: AtomOneManBigDecimal => a.copy(op = Fn(kw.toString))
        case a: AtomOneManDate       => a.copy(op = Fn(kw.toString))
        case a: AtomOneManUUID       => a.copy(op = Fn(kw.toString))
        case a: AtomOneManURI        => a.copy(op = Fn(kw.toString))
        case a: AtomOneManChar       => a.copy(op = Fn(kw.toString))
        case a: AtomOneManByte       => a.copy(op = Fn(kw.toString))
        case a: AtomOneManShort      => a.copy(op = Fn(kw.toString))
        case a                       => unexpected(a)
      }
      case a             => unexpected(a)
    }
    es.init :+ last
  }

  protected def addVs[T](es: Seq[Element], op: Op, vs: Seq[T]): Seq[Element] = {
    val last = es.last match {
      case a: AtomOneMan => a match {
        case a: AtomOneManString     => a.copy(op = op, vs = vs.asInstanceOf[Seq[String]])
        case a: AtomOneManInt        => a.copy(op = op, vs = vs.asInstanceOf[Seq[Int]])
        case a: AtomOneManLong       => a.copy(op = op, vs = vs.asInstanceOf[Seq[Long]])
        case a: AtomOneManFloat      => a.copy(op = op, vs = vs.asInstanceOf[Seq[Float]])
        case a: AtomOneManDouble     => a.copy(op = op, vs = vs.asInstanceOf[Seq[Double]])
        case a: AtomOneManBoolean    => a.copy(op = op, vs = vs.asInstanceOf[Seq[Boolean]])
        case a: AtomOneManBigInt     => a.copy(op = op, vs = vs.asInstanceOf[Seq[BigInt]])
        case a: AtomOneManBigDecimal => a.copy(op = op, vs = vs.asInstanceOf[Seq[BigDecimal]])
        case a: AtomOneManDate       => a.copy(op = op, vs = vs.asInstanceOf[Seq[Date]])
        case a: AtomOneManUUID       => a.copy(op = op, vs = vs.asInstanceOf[Seq[UUID]])
        case a: AtomOneManURI        => a.copy(op = op, vs = vs.asInstanceOf[Seq[URI]])
        case a: AtomOneManChar       => a.copy(op = op, vs = vs.asInstanceOf[Seq[Char]])
        case a: AtomOneManByte       => a.copy(op = op, vs = vs.asInstanceOf[Seq[Byte]])
        case a: AtomOneManShort      => a.copy(op = op, vs = vs.asInstanceOf[Seq[Short]])
        case a                       => unexpected(a)
      }
      case a: AtomOneTac => a match {
        case a: AtomOneTacString     => a.copy(op = op, vs = vs.asInstanceOf[Seq[String]])
        case a: AtomOneTacInt        => a.copy(op = op, vs = vs.asInstanceOf[Seq[Int]])
        case a: AtomOneTacLong       => a.copy(op = op, vs = vs.asInstanceOf[Seq[Long]])
        case a: AtomOneTacFloat      => a.copy(op = op, vs = vs.asInstanceOf[Seq[Float]])
        case a: AtomOneTacDouble     => a.copy(op = op, vs = vs.asInstanceOf[Seq[Double]])
        case a: AtomOneTacBoolean    => a.copy(op = op, vs = vs.asInstanceOf[Seq[Boolean]])
        case a: AtomOneTacBigInt     => a.copy(op = op, vs = vs.asInstanceOf[Seq[BigInt]])
        case a: AtomOneTacBigDecimal => a.copy(op = op, vs = vs.asInstanceOf[Seq[BigDecimal]])
        case a: AtomOneTacDate       => a.copy(op = op, vs = vs.asInstanceOf[Seq[Date]])
        case a: AtomOneTacUUID       => a.copy(op = op, vs = vs.asInstanceOf[Seq[UUID]])
        case a: AtomOneTacURI        => a.copy(op = op, vs = vs.asInstanceOf[Seq[URI]])
        case a: AtomOneTacChar       => a.copy(op = op, vs = vs.asInstanceOf[Seq[Char]])
        case a: AtomOneTacByte       => a.copy(op = op, vs = vs.asInstanceOf[Seq[Byte]])
        case a: AtomOneTacShort      => a.copy(op = op, vs = vs.asInstanceOf[Seq[Short]])
        case a                       => unexpected(a)
      }
      case a             => unexpected(a)
    }
    es.init :+ last
  }

  protected def addOptVs[T](es: Seq[Element], op: Op, vs: Option[Seq[T]]): Seq[Element] = {
    val last = es.last match {
      case a: AtomOneOpt => a match {
        case a: AtomOneOptString     => a.copy(op = op, vs = vs.getOrElse(Nil).asInstanceOf[Seq[String]])
        case a: AtomOneOptInt        => a.copy(op = op, vs = vs.getOrElse(Nil).asInstanceOf[Seq[Int]])
        case a: AtomOneOptLong       => a.copy(op = op, vs = vs.getOrElse(Nil).asInstanceOf[Seq[Long]])
        case a: AtomOneOptFloat      => a.copy(op = op, vs = vs.getOrElse(Nil).asInstanceOf[Seq[Float]])
        case a: AtomOneOptDouble     => a.copy(op = op, vs = vs.getOrElse(Nil).asInstanceOf[Seq[Double]])
        case a: AtomOneOptBoolean    => a.copy(op = op, vs = vs.getOrElse(Nil).asInstanceOf[Seq[Boolean]])
        case a: AtomOneOptBigInt     => a.copy(op = op, vs = vs.getOrElse(Nil).asInstanceOf[Seq[BigInt]])
        case a: AtomOneOptBigDecimal => a.copy(op = op, vs = vs.getOrElse(Nil).asInstanceOf[Seq[BigDecimal]])
        case a: AtomOneOptDate       => a.copy(op = op, vs = vs.getOrElse(Nil).asInstanceOf[Seq[Date]])
        case a: AtomOneOptUUID       => a.copy(op = op, vs = vs.getOrElse(Nil).asInstanceOf[Seq[UUID]])
        case a: AtomOneOptURI        => a.copy(op = op, vs = vs.getOrElse(Nil).asInstanceOf[Seq[URI]])
        case a: AtomOneOptChar       => a.copy(op = op, vs = vs.getOrElse(Nil).asInstanceOf[Seq[Char]])
        case a: AtomOneOptByte       => a.copy(op = op, vs = vs.getOrElse(Nil).asInstanceOf[Seq[Byte]])
        case a: AtomOneOptShort      => a.copy(op = op, vs = vs.getOrElse(Nil).asInstanceOf[Seq[Short]])
        case a                       => unexpected(a)
      }
      case a             => unexpected(a)
    }
    es.init :+ last
  }

  protected def addSort(es: Seq[Element], sort: String): Seq[Element] = {
    val last = es.last match {
      case a: AtomOneOpt => a match {
        case a: AtomOneOptString     => a.copy(sort = sort)
        case a: AtomOneOptInt        => a.copy(sort = sort)
        case a: AtomOneOptLong       => a.copy(sort = sort)
        case a: AtomOneOptFloat      => a.copy(sort = sort)
        case a: AtomOneOptDouble     => a.copy(sort = sort)
        case a: AtomOneOptBoolean    => a.copy(sort = sort)
        case a: AtomOneOptBigInt     => a.copy(sort = sort)
        case a: AtomOneOptBigDecimal => a.copy(sort = sort)
        case a: AtomOneOptDate       => a.copy(sort = sort)
        case a: AtomOneOptUUID       => a.copy(sort = sort)
        case a: AtomOneOptURI        => a.copy(sort = sort)
        case a: AtomOneOptChar       => a.copy(sort = sort)
        case a: AtomOneOptByte       => a.copy(sort = sort)
        case a: AtomOneOptShort      => a.copy(sort = sort)
        case a                       => unexpected(a)
      }
      case a             => unexpected(a)
    }
    es.init :+ last
  }
}
