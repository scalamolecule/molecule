package molecule.boilerplate.ops

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.util.exceptions.ExecutionError
import molecule.boilerplate.api.Keywords.Kw
import molecule.boilerplate.ast.Model._
import scala.util.{Try, Success, Failure}

trait ModelTransformations {

  def unexpected(element: Element) = throw ExecutionError("Unexpected element: " + element)

  protected def toInt(es: List[Element], kw: Kw): List[Element] = {
    val last = es.last match {
      case a: AttrOneMan => AttrOneManInt(a.ns, a.attr, Fn(kw.toString))
      case a: AttrSetMan => AttrSetManInt(a.ns, a.attr, Fn(kw.toString))
      case a             => unexpected(a)
    }
    es.init :+ last
  }

  protected def toDouble(es: List[Element], kw: Kw): List[Element] = {
    val last = es.last match {
      case a: AttrOneMan => AttrOneManDouble(a.ns, a.attr, Fn(kw.toString))
      case a: AttrSetMan => AttrSetManDouble(a.ns, a.attr, Fn(kw.toString))
      case a             => unexpected(a)
    }
    es.init :+ last
  }

  protected def toSet(es: List[Element], kw: Kw, n: Option[Int] = None): List[Element] = {
    val last = es.last match {
      case a: AttrOneMan => a match {
        case a: AttrOneManString     => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManInt        => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManLong       => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManDouble     => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManBoolean    => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManBigInt     => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManBigDecimal => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManDate       => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManUUID       => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManURI        => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManByte       => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManShort      => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManFloat      => a.copy(op = Fn(kw.toString, n))
        case a: AttrOneManChar       => a.copy(op = Fn(kw.toString, n))
      }
      case a: AttrSetMan => a match {
        case a: AttrSetManString     => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManInt        => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManLong       => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManDouble     => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManBoolean    => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManBigInt     => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManBigDecimal => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManDate       => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManUUID       => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManURI        => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManByte       => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManShort      => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManFloat      => a.copy(op = Fn(kw.toString, n))
        case a: AttrSetManChar       => a.copy(op = Fn(kw.toString, n))
      }
      case a             => unexpected(a)
    }
    es.init :+ last
  }

  protected def asIs(es: List[Element], kw: Kw): List[Element] = {
    val last = es.last match {
      case a: AttrOneMan => a match {
        case a: AttrOneManString     => a.copy(op = Fn(kw.toString))
        case a: AttrOneManInt        => a.copy(op = Fn(kw.toString))
        case a: AttrOneManLong       => a.copy(op = Fn(kw.toString))
        case a: AttrOneManDouble     => a.copy(op = Fn(kw.toString))
        case a: AttrOneManBoolean    => a.copy(op = Fn(kw.toString))
        case a: AttrOneManBigInt     => a.copy(op = Fn(kw.toString))
        case a: AttrOneManBigDecimal => a.copy(op = Fn(kw.toString))
        case a: AttrOneManDate       => a.copy(op = Fn(kw.toString))
        case a: AttrOneManUUID       => a.copy(op = Fn(kw.toString))
        case a: AttrOneManURI        => a.copy(op = Fn(kw.toString))
        case a: AttrOneManByte       => a.copy(op = Fn(kw.toString))
        case a: AttrOneManShort      => a.copy(op = Fn(kw.toString))
        case a: AttrOneManFloat      => a.copy(op = Fn(kw.toString))
        case a: AttrOneManChar       => a.copy(op = Fn(kw.toString))
      }
      case a: AttrSetMan => a match {
        case a: AttrSetManString     => a.copy(op = Fn(kw.toString))
        case a: AttrSetManInt        => a.copy(op = Fn(kw.toString))
        case a: AttrSetManLong       => a.copy(op = Fn(kw.toString))
        case a: AttrSetManDouble     => a.copy(op = Fn(kw.toString))
        case a: AttrSetManBoolean    => a.copy(op = Fn(kw.toString))
        case a: AttrSetManBigInt     => a.copy(op = Fn(kw.toString))
        case a: AttrSetManBigDecimal => a.copy(op = Fn(kw.toString))
        case a: AttrSetManDate       => a.copy(op = Fn(kw.toString))
        case a: AttrSetManUUID       => a.copy(op = Fn(kw.toString))
        case a: AttrSetManURI        => a.copy(op = Fn(kw.toString))
        case a: AttrSetManByte       => a.copy(op = Fn(kw.toString))
        case a: AttrSetManShort      => a.copy(op = Fn(kw.toString))
        case a: AttrSetManFloat      => a.copy(op = Fn(kw.toString))
        case a: AttrSetManChar       => a.copy(op = Fn(kw.toString))
      }
      case a             => unexpected(a)
    }
    es.init :+ last
  }


  protected def addOne[T](es: List[Element], op: Op, vs: Seq[T]): List[Element] = {
    val last = es.last match {
      case a: AttrOneMan => a match {
        case a: AttrOneManString     => a.copy(op = op, vs = vs.asInstanceOf[Seq[String]])
        case a: AttrOneManInt        =>
          val vs1    = vs.asInstanceOf[Seq[Int]]
          val errors = a.validation.fold(Seq.empty[String]) { validator =>
            vs1.flatMap(v => validator.validate(v))
          }
          a.copy(op = op, vs = vs1, errors = errors)
        case a: AttrOneManLong       => a.copy(op = op, vs = vs.asInstanceOf[Seq[Long]])
        case a: AttrOneManDouble     => a.copy(op = op, vs = vs.asInstanceOf[Seq[Double]])
        case a: AttrOneManBoolean    => a.copy(op = op, vs = vs.asInstanceOf[Seq[Boolean]])
        case a: AttrOneManBigInt     => a.copy(op = op, vs = vs.asInstanceOf[Seq[BigInt]])
        case a: AttrOneManBigDecimal => a.copy(op = op, vs = vs.asInstanceOf[Seq[BigDecimal]])
        case a: AttrOneManDate       => a.copy(op = op, vs = vs.asInstanceOf[Seq[Date]])
        case a: AttrOneManUUID       => a.copy(op = op, vs = vs.asInstanceOf[Seq[UUID]])
        case a: AttrOneManURI        => a.copy(op = op, vs = vs.asInstanceOf[Seq[URI]])
        case a: AttrOneManByte       => a.copy(op = op, vs = vs.asInstanceOf[Seq[Byte]])
        case a: AttrOneManShort      => a.copy(op = op, vs = vs.asInstanceOf[Seq[Short]])
        case a: AttrOneManFloat      => a.copy(op = op, vs = vs.asInstanceOf[Seq[Float]])
        case a: AttrOneManChar       => a.copy(op = op, vs = vs.asInstanceOf[Seq[Char]])
      }
      case a: AttrOneTac => a match {
        case a: AttrOneTacString     => a.copy(op = op, vs = vs.asInstanceOf[Seq[String]])
        case a: AttrOneTacInt        => a.copy(op = op, vs = vs.asInstanceOf[Seq[Int]])
        case a: AttrOneTacLong       => a.copy(op = op, vs = vs.asInstanceOf[Seq[Long]])
        case a: AttrOneTacDouble     => a.copy(op = op, vs = vs.asInstanceOf[Seq[Double]])
        case a: AttrOneTacBoolean    => a.copy(op = op, vs = vs.asInstanceOf[Seq[Boolean]])
        case a: AttrOneTacBigInt     => a.copy(op = op, vs = vs.asInstanceOf[Seq[BigInt]])
        case a: AttrOneTacBigDecimal => a.copy(op = op, vs = vs.asInstanceOf[Seq[BigDecimal]])
        case a: AttrOneTacDate       => a.copy(op = op, vs = vs.asInstanceOf[Seq[Date]])
        case a: AttrOneTacUUID       => a.copy(op = op, vs = vs.asInstanceOf[Seq[UUID]])
        case a: AttrOneTacURI        => a.copy(op = op, vs = vs.asInstanceOf[Seq[URI]])
        case a: AttrOneTacByte       => a.copy(op = op, vs = vs.asInstanceOf[Seq[Byte]])
        case a: AttrOneTacShort      => a.copy(op = op, vs = vs.asInstanceOf[Seq[Short]])
        case a: AttrOneTacFloat      => a.copy(op = op, vs = vs.asInstanceOf[Seq[Float]])
        case a: AttrOneTacChar       => a.copy(op = op, vs = vs.asInstanceOf[Seq[Char]])
      }
      case a             => unexpected(a)
    }
    es.init :+ last
  }

  protected def addOptOne[T](es: List[Element], op: Op, vs: Option[Seq[T]]): List[Element] = {
    val last = es.last match {
      case a: AttrOneOpt => a match {
        case a: AttrOneOptString     => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[String]]])
        case a: AttrOneOptInt        => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[Int]]])
        case a: AttrOneOptLong       => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[Long]]])
        case a: AttrOneOptDouble     => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[Double]]])
        case a: AttrOneOptBoolean    => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[Boolean]]])
        case a: AttrOneOptBigInt     => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[BigInt]]])
        case a: AttrOneOptBigDecimal => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[BigDecimal]]])
        case a: AttrOneOptDate       => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[Date]]])
        case a: AttrOneOptUUID       => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[UUID]]])
        case a: AttrOneOptURI        => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[URI]]])
        case a: AttrOneOptByte       => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[Byte]]])
        case a: AttrOneOptShort      => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[Short]]])
        case a: AttrOneOptFloat      => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[Float]]])
        case a: AttrOneOptChar       => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[Char]]])
      }
      case a             => unexpected(a)
    }
    es.init :+ last
  }

  protected def addSet[T](es: List[Element], op: Op, vs: Seq[Set[T]]): List[Element] = {
    val last = es.last match {
      case a: AttrSetMan => a match {
        case a: AttrSetManString     => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[String]]])
        case a: AttrSetManInt        => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[Int]]])
        case a: AttrSetManLong       => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[Long]]])
        case a: AttrSetManDouble     => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[Double]]])
        case a: AttrSetManBoolean    => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[Boolean]]])
        case a: AttrSetManBigInt     => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[BigInt]]])
        case a: AttrSetManBigDecimal => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[BigDecimal]]])
        case a: AttrSetManDate       => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[Date]]])
        case a: AttrSetManUUID       => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[UUID]]])
        case a: AttrSetManURI        => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[URI]]])
        case a: AttrSetManByte       => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[Byte]]])
        case a: AttrSetManShort      => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[Short]]])
        case a: AttrSetManFloat      => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[Float]]])
        case a: AttrSetManChar       => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[Char]]])
      }
      case a: AttrSetTac => a match {
        case a: AttrSetTacString     => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[String]]])
        case a: AttrSetTacInt        => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[Int]]])
        case a: AttrSetTacLong       => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[Long]]])
        case a: AttrSetTacDouble     => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[Double]]])
        case a: AttrSetTacBoolean    => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[Boolean]]])
        case a: AttrSetTacBigInt     => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[BigInt]]])
        case a: AttrSetTacBigDecimal => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[BigDecimal]]])
        case a: AttrSetTacDate       => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[Date]]])
        case a: AttrSetTacUUID       => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[UUID]]])
        case a: AttrSetTacURI        => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[URI]]])
        case a: AttrSetTacByte       => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[Byte]]])
        case a: AttrSetTacShort      => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[Short]]])
        case a: AttrSetTacFloat      => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[Float]]])
        case a: AttrSetTacChar       => a.copy(op = op, vs = vs.asInstanceOf[Seq[Set[Char]]])
      }
      case a             => unexpected(a)
    }
    es.init :+ last
  }

  protected def addOptSet[T](es: List[Element], op: Op, vs: Option[Seq[Set[T]]]): List[Element] = {
    val last = es.last match {
      case a: AttrSetOpt => a match {
        case a: AttrSetOptString     => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[Set[String]]]])
        case a: AttrSetOptInt        => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[Set[Int]]]])
        case a: AttrSetOptLong       => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[Set[Long]]]])
        case a: AttrSetOptDouble     => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[Set[Double]]]])
        case a: AttrSetOptBoolean    => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[Set[Boolean]]]])
        case a: AttrSetOptBigInt     => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[Set[BigInt]]]])
        case a: AttrSetOptBigDecimal => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[Set[BigDecimal]]]])
        case a: AttrSetOptDate       => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[Set[Date]]]])
        case a: AttrSetOptUUID       => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[Set[UUID]]]])
        case a: AttrSetOptURI        => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[Set[URI]]]])
        case a: AttrSetOptByte       => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[Set[Byte]]]])
        case a: AttrSetOptShort      => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[Set[Short]]]])
        case a: AttrSetOptFloat      => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[Set[Float]]]])
        case a: AttrSetOptChar       => a.copy(op = op, vs = vs.asInstanceOf[Option[Seq[Set[Char]]]])
      }
      case a             => unexpected(a)
    }
    es.init :+ last
  }

  protected def addSort(es: List[Element], sort: String): List[Element] = {
    val last = es.last match {
      case a: AttrOneMan => a match {
        case a: AttrOneManString     => a.copy(sort = Some(sort))
        case a: AttrOneManInt        => a.copy(sort = Some(sort))
        case a: AttrOneManLong       => a.copy(sort = Some(sort))
        case a: AttrOneManDouble     => a.copy(sort = Some(sort))
        case a: AttrOneManBoolean    => a.copy(sort = Some(sort))
        case a: AttrOneManBigInt     => a.copy(sort = Some(sort))
        case a: AttrOneManBigDecimal => a.copy(sort = Some(sort))
        case a: AttrOneManDate       => a.copy(sort = Some(sort))
        case a: AttrOneManUUID       => a.copy(sort = Some(sort))
        case a: AttrOneManURI        => a.copy(sort = Some(sort))
        case a: AttrOneManByte       => a.copy(sort = Some(sort))
        case a: AttrOneManShort      => a.copy(sort = Some(sort))
        case a: AttrOneManFloat      => a.copy(sort = Some(sort))
        case a: AttrOneManChar       => a.copy(sort = Some(sort))
      }
      case a: AttrOneOpt => a match {
        case a: AttrOneOptString     => a.copy(sort = Some(sort))
        case a: AttrOneOptInt        => a.copy(sort = Some(sort))
        case a: AttrOneOptLong       => a.copy(sort = Some(sort))
        case a: AttrOneOptDouble     => a.copy(sort = Some(sort))
        case a: AttrOneOptBoolean    => a.copy(sort = Some(sort))
        case a: AttrOneOptBigInt     => a.copy(sort = Some(sort))
        case a: AttrOneOptBigDecimal => a.copy(sort = Some(sort))
        case a: AttrOneOptDate       => a.copy(sort = Some(sort))
        case a: AttrOneOptUUID       => a.copy(sort = Some(sort))
        case a: AttrOneOptURI        => a.copy(sort = Some(sort))
        case a: AttrOneOptByte       => a.copy(sort = Some(sort))
        case a: AttrOneOptShort      => a.copy(sort = Some(sort))
        case a: AttrOneOptFloat      => a.copy(sort = Some(sort))
        case a: AttrOneOptChar       => a.copy(sort = Some(sort))
      }
      case a             => unexpected(a)
    }
    es.init :+ last
  }


  protected def reverseTopLevelSorting(es: List[Element]): List[Element] = {
    es.map {
      case a: AttrOneMan => a match {
        case a@AttrOneManString(_, _, _, _, _, _, _, Some(sort))     => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManInt(_, _, _, _, _, _, _, Some(sort))        => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManLong(_, _, _, _, _, _, _, Some(sort))       => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManFloat(_, _, _, _, _, _, _, Some(sort))      => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManDouble(_, _, _, _, _, _, _, Some(sort))     => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManBoolean(_, _, _, _, _, _, _, Some(sort))    => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManBigInt(_, _, _, _, _, _, _, Some(sort))     => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManBigDecimal(_, _, _, _, _, _, _, Some(sort)) => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManDate(_, _, _, _, _, _, _, Some(sort))       => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManUUID(_, _, _, _, _, _, _, Some(sort))       => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManURI(_, _, _, _, _, _, _, Some(sort))        => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManByte(_, _, _, _, _, _, _, Some(sort))       => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManShort(_, _, _, _, _, _, _, Some(sort))      => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneManChar(_, _, _, _, _, _, _, Some(sort))       => a.copy(sort = Some(reverseSort(sort)))
        case a                                                       => a
      }
      case a: AttrOneOpt => a match {
        case a@AttrOneOptString(_, _, _, _, _, _, _, Some(sort))     => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptInt(_, _, _, _, _, _, _, Some(sort))        => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptLong(_, _, _, _, _, _, _, Some(sort))       => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptFloat(_, _, _, _, _, _, _, Some(sort))      => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptDouble(_, _, _, _, _, _, _, Some(sort))     => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptBoolean(_, _, _, _, _, _, _, Some(sort))    => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptBigInt(_, _, _, _, _, _, _, Some(sort))     => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptBigDecimal(_, _, _, _, _, _, _, Some(sort)) => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptDate(_, _, _, _, _, _, _, Some(sort))       => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptUUID(_, _, _, _, _, _, _, Some(sort))       => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptURI(_, _, _, _, _, _, _, Some(sort))        => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptByte(_, _, _, _, _, _, _, Some(sort))       => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptShort(_, _, _, _, _, _, _, Some(sort))      => a.copy(sort = Some(reverseSort(sort)))
        case a@AttrOneOptChar(_, _, _, _, _, _, _, Some(sort))       => a.copy(sort = Some(reverseSort(sort)))
        case a                                                       => a
      }
      case other         => other
    }
  }

  private def reverseSort(sort: String): String = sort.head match {
    case 'a' => "d" + sort.last
    case 'd' => "a" + sort.last
  }
}
