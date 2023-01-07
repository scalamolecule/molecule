package molecule.core.marshalling.pack

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model._
import molecule.core.util.ModelUtils
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer


trait PackTuple extends ModelUtils { self: Tpls2DTO =>

  private val prevRefs: ListBuffer[String] = ListBuffer.empty[String]

  @tailrec
  final protected def resolvePackers(
    elements: Seq[Element],
    packers: List[Product => Unit],
    level: Int,
    tplIndex: Int
  ): List[Product => Unit] = {
    elements match {
      case element :: tail => element match {
        case a: Attr =>
          a match {
            case a: AttrOne =>
              a match {
                case a: AttrOneMan => resolvePackers(tail, packers :+ packAttrOneMan(a, tplIndex), level, tplIndex + 1)
                case a: AttrOneOpt => resolvePackers(tail, packers :+ packAttrOneOpt(a, tplIndex), level, tplIndex + 1)
                case _: AttrOneTac => resolvePackers(tail, packers, level, tplIndex)
              }
            case a: AttrSet =>
              a match {
                case a: AttrSetMan => resolvePackers(tail, packers :+ packAttrSetMan(a, tplIndex), level, tplIndex + 1)
                case a: AttrSetOpt => resolvePackers(tail, packers :+ packAttrSetOpt(a, tplIndex), level, tplIndex + 1)
                case _: AttrSetTac => resolvePackers(tail, packers, level, tplIndex)
              }
          }

        case Ref(_, refAttr, _, _) =>
          prevRefs += refAttr
          resolvePackers(tail, packers, level, tplIndex)

        case BackRef(backRefNs) =>
          tail.head match {
            case Ref(_, refAttr, _, _) if prevRefs.contains(refAttr) => throw MoleculeException(
              s"Can't re-use previous namespace ${refAttr.capitalize} after backref _$backRefNs."
            )
            case _                                                   => // ok
          }
          resolvePackers(tail, packers, level, tplIndex)

        case Nested(_, nestedElements) =>
          prevRefs.clear()
          resolvePackers(tail, packers :+ packNested(level + 1, tplIndex, nestedElements), level, tplIndex + 1)

        case NestedOpt(_, nestedElements) =>
          prevRefs.clear()
          resolvePackers(tail, packers :+ packNested(level + 1, tplIndex, nestedElements), level, tplIndex + 1)

        case Composite(compositeElements) =>
          packComposite(level, tplIndex, compositeElements) match {
            case Nil         => resolvePackers(tail, packers, level, tplIndex)
            case Seq(packer) => resolvePackers(tail, packers :+ packer, level, tplIndex + 1)
          }

        case TxMetaData(txMetaDataElements) =>
          // Tx meta data is last attribute values in top level tuple
          // TxMetaData is only packed for queries. Inserts handle tx meta elements separately after all tpl inserts.
          packers ++ packTxMeta(txMetaDataElements, level, tplIndex)

        case other => unexpected(other)
      }
      case Nil             => packers
    }
  }


  private def packTxMeta(
    txMetaDataElements: Seq[Element],
    level: Int,
    tplIndex: Int
  ): List[Product => Unit] = {
    resolvePackers(txMetaDataElements, Nil, level, tplIndex)
  }

  private def packComposite(
    level: Int,
    tplIndex: Int,
    compositeElements: Seq[Element]
  ): Seq[Product => Unit] = {
    lazy val packCompositeData = getPacker(compositeElements, level)
    // Start from initial entity id for each composite sub group
    countValueAttrs(compositeElements) match {
      case 0 => Nil
      case 1 => Seq((tpl: Product) => packCompositeData(Tuple1(tpl.productElement(tplIndex))))
      case _ => Seq((tpl: Product) => packCompositeData(tpl.productElement(tplIndex).asInstanceOf[Product]))
    }
  }

  private def packNested(
    level: Int,
    tplIndex: Int,
    nestedElements: Seq[Element]
  ): Product => Unit = {
    // Recursively pack nested levels
    val packNestedData = getPacker(nestedElements, level)
    countValueAttrs(nestedElements) match {
      case 1 => // Single nested values
        (tpl: Product) => {
          val nestedValues = tpl.productElement(tplIndex).asInstanceOf[Seq[Any]]
          levelCounts(level).addOne(nestedValues.size)
          nestedValues.foreach { nestedValue =>
            packNestedData(Tuple1(nestedValue))
          }
        }
      case _ =>
        (tpl: Product) => {
          val nestedTpls = tpl.productElement(tplIndex).asInstanceOf[Seq[Product]]
          levelCounts(level).addOne(nestedTpls.size)
          nestedTpls.foreach(packNestedData)
        }
    }
  }

  private def unexpected(element: Element) =
    throw MoleculeException("Unexpected element: " + element)

  private def packAttrOneMan(a: AttrOneMan, tplIndex: Int): Product => Unit = {
    a.op match {
      case Fn(kw, _) => kw match {
        case "count" | "countDistinct"                          => packAttrOneManInt(tplIndex)
        case "distinct" | "mins" | "maxs" | "rands" | "samples" => packAttrOneManSet(a, tplIndex)
        case "avg" | "variance" | "stddev"                      => packAttrOneManDouble(tplIndex)
        case _                                                  => packAttrOneManV(a, tplIndex)
      }
      case _         => packAttrOneManV(a, tplIndex)
    }
  }
  private def packAttrOneManInt(tplIndex: Int): Product => Unit = {
    (tpl: Product) => oneInt.addOne(tpl.productElement(tplIndex).toString.toInt)
  }
  private def packAttrOneManDouble(tplIndex: Int): Product => Unit = {
    (tpl: Product) => oneDouble.addOne(tpl.productElement(tplIndex).asInstanceOf[Double])
  }
  private def packAttrOneManV(a: AttrOneMan, tplIndex: Int): Product => Unit = {
    a match {
      case _: AttrOneManString => (tpl: Product) => oneString.addOne(tpl.productElement(tplIndex).asInstanceOf[String])
      case _: AttrOneManInt    => (tpl: Product) => oneInt.addOne(tpl.productElement(tplIndex).toString.toInt)
      case _: AttrOneManLong   => (tpl: Product) => oneLong.addOne(tpl.productElement(tplIndex).asInstanceOf[Long])
      case _: AttrOneManFloat  => (tpl: Product) =>
        tpl.productElement(tplIndex) match {
          case f: Float  => oneFloat.addOne(f)
          case d: Double => oneFloat.addOne(d.toFloat)
        }

      case _: AttrOneManDouble     => (tpl: Product) => oneDouble.addOne(tpl.productElement(tplIndex).asInstanceOf[Double])
      case _: AttrOneManBoolean    => (tpl: Product) => oneBoolean.addOne(tpl.productElement(tplIndex).asInstanceOf[Boolean])
      case _: AttrOneManBigInt     => (tpl: Product) => oneBigInt.addOne(tpl.productElement(tplIndex).asInstanceOf[BigInt])
      case _: AttrOneManBigDecimal => (tpl: Product) => oneBigDecimal.addOne(tpl.productElement(tplIndex).asInstanceOf[BigDecimal])
      case _: AttrOneManDate       => (tpl: Product) => oneDate.addOne(tpl.productElement(tplIndex).asInstanceOf[Date])
      case _: AttrOneManUUID       => (tpl: Product) => oneUUID.addOne(tpl.productElement(tplIndex).asInstanceOf[UUID])
      case _: AttrOneManURI        => (tpl: Product) => oneURI.addOne(tpl.productElement(tplIndex).asInstanceOf[URI])
      case _: AttrOneManByte       => (tpl: Product) => oneByte.addOne(tpl.productElement(tplIndex).asInstanceOf[Byte])
      case _: AttrOneManShort      => (tpl: Product) => oneShort.addOne(tpl.productElement(tplIndex).asInstanceOf[Short])
      case _: AttrOneManChar       => (tpl: Product) => oneChar.addOne(tpl.productElement(tplIndex).asInstanceOf[Char])
    }
  }

  private def packAttrOneManSet(a: AttrOneMan, tplIndex: Int): Product => Unit = {
    a match {
      case _: AttrOneManString     => (tpl: Product) => setString.addOne(tpl.productElement(tplIndex).asInstanceOf[Set[String]])
      case _: AttrOneManInt        => (tpl: Product) => setInt.addOne(tpl.productElement(tplIndex).asInstanceOf[Set[Int]])
      case _: AttrOneManLong       => (tpl: Product) => setLong.addOne(tpl.productElement(tplIndex).asInstanceOf[Set[Long]])
      case _: AttrOneManFloat      => (tpl: Product) => setFloat.addOne(tpl.productElement(tplIndex).asInstanceOf[Set[Float]])
      case _: AttrOneManDouble     => (tpl: Product) => setDouble.addOne(tpl.productElement(tplIndex).asInstanceOf[Set[Double]])
      case _: AttrOneManBoolean    => (tpl: Product) => setBoolean.addOne(tpl.productElement(tplIndex).asInstanceOf[Set[Boolean]])
      case _: AttrOneManBigInt     => (tpl: Product) => setBigInt.addOne(tpl.productElement(tplIndex).asInstanceOf[Set[BigInt]])
      case _: AttrOneManBigDecimal => (tpl: Product) => setBigDecimal.addOne(tpl.productElement(tplIndex).asInstanceOf[Set[BigDecimal]])
      case _: AttrOneManDate       => (tpl: Product) => setDate.addOne(tpl.productElement(tplIndex).asInstanceOf[Set[Date]])
      case _: AttrOneManUUID       => (tpl: Product) => setUUID.addOne(tpl.productElement(tplIndex).asInstanceOf[Set[UUID]])
      case _: AttrOneManURI        => (tpl: Product) => setURI.addOne(tpl.productElement(tplIndex).asInstanceOf[Set[URI]])
      case _: AttrOneManByte       => (tpl: Product) => setByte.addOne(tpl.productElement(tplIndex).asInstanceOf[Set[Byte]])
      case _: AttrOneManShort      => (tpl: Product) => setShort.addOne(tpl.productElement(tplIndex).asInstanceOf[Set[Short]])
      case _: AttrOneManChar       => (tpl: Product) => setChar.addOne(tpl.productElement(tplIndex).asInstanceOf[Set[Char]])
    }
  }

  private def packAttrOneOpt(a: AttrOneOpt, tplIndex: Int): Product => Unit = {
    a match {
      case _: AttrOneOptString     => (tpl: Product) => oneOptString.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[String]])
      case _: AttrOneOptInt        => (tpl: Product) => oneOptInt.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[Int]])
      case _: AttrOneOptLong       => (tpl: Product) => oneOptLong.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[Long]])
      case _: AttrOneOptFloat      => (tpl: Product) => oneOptFloat.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[Float]])
      case _: AttrOneOptDouble     => (tpl: Product) => oneOptDouble.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[Double]])
      case _: AttrOneOptBoolean    => (tpl: Product) => oneOptBoolean.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[Boolean]])
      case _: AttrOneOptBigInt     => (tpl: Product) => oneOptBigInt.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[BigInt]])
      case _: AttrOneOptBigDecimal => (tpl: Product) => oneOptBigDecimal.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[BigDecimal]])
      case _: AttrOneOptDate       => (tpl: Product) => oneOptDate.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[Date]])
      case _: AttrOneOptUUID       => (tpl: Product) => oneOptUUID.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[UUID]])
      case _: AttrOneOptURI        => (tpl: Product) => oneOptURI.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[URI]])
      case _: AttrOneOptByte       => (tpl: Product) => oneOptByte.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[Byte]])
      case _: AttrOneOptShort      => (tpl: Product) => oneOptShort.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[Short]])
      case _: AttrOneOptChar       => (tpl: Product) => oneOptChar.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[Char]])
    }
  }

  private def packAttrSetMan(a: AttrSetMan, tplIndex: Int): Product => Unit = {
    a.op match {
      case Fn(kw, _) => kw match {
        case "count" | "countDistinct"             => packAttrSetManInt(tplIndex)
        case "distinct"                            => packAttrSetManSet(a, tplIndex)
        case "mins" | "maxs" | "rands" | "samples" => packAttrSetManV(a, tplIndex)
        case "avg" | "variance" | "stddev"         => packAttrSetManDouble(tplIndex)
        case _                                     => packAttrSetManV(a, tplIndex)
      }
      case _         => packAttrSetManV(a, tplIndex)
    }
  }
  private def packAttrSetManInt(tplIndex: Int): Product => Unit = {
    (tpl: Product) => oneInt.addOne(tpl.productElement(tplIndex).asInstanceOf[Int])
  }
  private def packAttrSetManDouble(tplIndex: Int): Product => Unit = {
    (tpl: Product) => setDouble.addOne(tpl.productElement(tplIndex).asInstanceOf[Set[Double]])
  }
  private def packAttrSetManV(a: AttrSetMan, tplIndex: Int): Product => Unit = {
    a match {
      case _: AttrSetManString     => packSet[String](tplIndex, setString)
      case _: AttrSetManInt        => packSet[Int](tplIndex, setInt)
      case _: AttrSetManLong       => packSet[Long](tplIndex, setLong)
      case _: AttrSetManFloat      => packSet[Float](tplIndex, setFloat)
      case _: AttrSetManDouble     => packSet[Double](tplIndex, setDouble)
      case _: AttrSetManBoolean    => packSet[Boolean](tplIndex, setBoolean)
      case _: AttrSetManBigInt     => packSet[BigInt](tplIndex, setBigInt)
      case _: AttrSetManBigDecimal => packSet[BigDecimal](tplIndex, setBigDecimal)
      case _: AttrSetManDate       => packSet[Date](tplIndex, setDate)
      case _: AttrSetManUUID       => packSet[UUID](tplIndex, setUUID)
      case _: AttrSetManURI        => packSet[URI](tplIndex, setURI)
      case _: AttrSetManByte       => packSet[Byte](tplIndex, setByte)
      case _: AttrSetManShort      => packSet[Short](tplIndex, setShort)
      case _: AttrSetManChar       => packSet[Char](tplIndex, setChar)
    }
  }
  private def packAttrSetManSet(a: AttrSetMan, tplIndex: Int): Product => Unit = {
    a match {
      case _: AttrSetManString     => packSets[String](tplIndex, setString)
      case _: AttrSetManInt        => packSets[Int](tplIndex, setInt)
      case _: AttrSetManLong       => packSets[Long](tplIndex, setLong)
      case _: AttrSetManFloat      => packSets[Float](tplIndex, setFloat)
      case _: AttrSetManDouble     => packSets[Double](tplIndex, setDouble)
      case _: AttrSetManBoolean    => packSets[Boolean](tplIndex, setBoolean)
      case _: AttrSetManBigInt     => packSets[BigInt](tplIndex, setBigInt)
      case _: AttrSetManBigDecimal => packSets[BigDecimal](tplIndex, setBigDecimal)
      case _: AttrSetManDate       => packSets[Date](tplIndex, setDate)
      case _: AttrSetManUUID       => packSets[UUID](tplIndex, setUUID)
      case _: AttrSetManURI        => packSets[URI](tplIndex, setURI)
      case _: AttrSetManByte       => packSets[Byte](tplIndex, setByte)
      case _: AttrSetManShort      => packSets[Short](tplIndex, setShort)
      case _: AttrSetManChar       => packSets[Char](tplIndex, setChar)
    }
  }
  private def packSet[T](tplIndex: Int, buf: ListBuffer[Set[T]]): Product => Unit = {
    (tpl: Product) => buf.addOne(tpl.productElement(tplIndex).asInstanceOf[Set[T]])
  }
  private def packSets[T](tplIndex: Int, buf: ListBuffer[Set[T]]): Product => Unit = {
    (tpl: Product) => {
      val sets = tpl.productElement(tplIndex).asInstanceOf[Set[Set[T]]]
      levelCounts.head.addOne(sets.size)
      sets.foreach(set => buf.addOne(set))
    }
  }

  private def packAttrSetOpt(a: AttrSetOpt, tplIndex: Int): Product => Unit = {
    a match {
      case _: AttrSetOptString     => (tpl: Product) => setOptString.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[Set[String]]])
      case _: AttrSetOptInt        => (tpl: Product) => setOptInt.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[Set[Int]]])
      case _: AttrSetOptLong       => (tpl: Product) => setOptLong.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[Set[Long]]])
      case _: AttrSetOptFloat      => (tpl: Product) => setOptFloat.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[Set[Float]]])
      case _: AttrSetOptDouble     => (tpl: Product) => setOptDouble.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[Set[Double]]])
      case _: AttrSetOptBoolean    => (tpl: Product) => setOptBoolean.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[Set[Boolean]]])
      case _: AttrSetOptBigInt     => (tpl: Product) => setOptBigInt.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[Set[BigInt]]])
      case _: AttrSetOptBigDecimal => (tpl: Product) => setOptBigDecimal.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[Set[BigDecimal]]])
      case _: AttrSetOptDate       => (tpl: Product) => setOptDate.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[Set[Date]]])
      case _: AttrSetOptUUID       => (tpl: Product) => setOptUUID.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[Set[UUID]]])
      case _: AttrSetOptURI        => (tpl: Product) => setOptURI.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[Set[URI]]])
      case _: AttrSetOptByte       => (tpl: Product) => setOptByte.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[Set[Byte]]])
      case _: AttrSetOptShort      => (tpl: Product) => setOptShort.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[Set[Short]]])
      case _: AttrSetOptChar       => (tpl: Product) => setOptChar.addOne(tpl.productElement(tplIndex).asInstanceOf[Option[Set[Char]]])
    }
  }
}
