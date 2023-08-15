package molecule.core.transaction

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.ast.SchemaAST._
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.transaction.ops.UpdateOps
import scala.annotation.tailrec

class UpdateExtraction(
  uniqueAttrs: List[String],
  val isUpsert: Boolean
) { self: UpdateOps =>

  val update = if (isUpsert) "upsert" else "update"

  @tailrec
  final def resolve(elements: List[Element]): Unit = {
    elements match {
      case element :: tail => element match {
        case a: Attr => a match {
          case a: AttrOneTac => resolveAttrOneTac(a); resolve(tail)
          case a: AttrOneMan => resolveAttrOneMan(a); resolve(tail)

          case a: AttrSetMan => a.op match {
            case Eq     => resolveAttrSetMan(a); resolve(tail)
            case Add    => resolveAttrSetAdd(a); resolve(tail)
            case Swap   => resolveAttrSetSwap(a); resolve(tail)
            case Remove => resolveAttrSetRemove(a); resolve(tail)
            case _      => throw ModelError(s"Unexpected $update operation for card-many attribute. Found:\n" + a)
          }
          case _: AttrOneOpt => throw ModelError(s"Can't $update optional values. Found:\n" + a)
          case _: AttrSetTac => throw ModelError("Can only lookup entity with card-one attribute value. Found:\n" + a)
          case _: AttrSetOpt => throw ModelError(s"Can't $update optional values. Found:\n" + a)
        }

        case _: Nested    => throw ModelError(s"Nested data structure not allowed in $update molecule.")
        case _: NestedOpt => throw ModelError(s"Optional nested data structure not allowed in $update molecule.")

        case Ref(_, _, "Tx", CardOne, _)  => resolve(tail) // todo
        case ref@Ref(_, _, _, CardOne, _) => handleRefNs(ref); resolve(tail)
        case ref: Ref                     => throw ModelError(
          s"Can't $update attributes in card-many referenced namespaces. Found `${ref.refAttr.capitalize}`"
        )

        case b: BackRef =>
          handleBackRef(b)
          resolve(tail)

        case Composite(es) =>
          extractSubElements(es)
          resolve(tail)

        case TxMetaData(es) =>
          handleTxMetaData()
          extractSubElements(es)
          resolve(tail)
      }
      case Nil             => ()
    }
  }

  private def extractSubElements(elements: List[Element]): Unit = resolve(elements)

  private def resolveAttrOneMan(attr: AttrOneMan): Unit = {
    attr match {
      case a if a.attr == "id" || a.attr == "tx" => throw ModelError(
        s"Generic attributes not allowed in update molecule. Found:\n" + a)

      case a if a.op != Eq => throw ModelError(
        s"Can't $update attributes without an applied value. Found:\n" + a)

      case AttrOneManString(_, _, _, vs, _, _, _, _, _, _)     => updateOne[String](attr, vs, transformString)
      case AttrOneManInt(_, _, _, vs, _, _, _, _, _, _)        => updateOne[Int](attr, vs, transformInt)
      case AttrOneManLong(_, _, _, vs, _, _, _, _, _, _)       => updateOne[Long](attr, vs, transformLong)
      case AttrOneManFloat(_, _, _, vs, _, _, _, _, _, _)      => updateOne[Float](attr, vs, transformFloat)
      case AttrOneManDouble(_, _, _, vs, _, _, _, _, _, _)     => updateOne[Double](attr, vs, transformDouble)
      case AttrOneManBoolean(_, _, _, vs, _, _, _, _, _, _)    => updateOne[Boolean](attr, vs, transformBoolean)
      case AttrOneManBigInt(_, _, _, vs, _, _, _, _, _, _)     => updateOne[BigInt](attr, vs, transformBigInt)
      case AttrOneManBigDecimal(_, _, _, vs, _, _, _, _, _, _) => updateOne[BigDecimal](attr, vs, transformBigDecimal)
      case AttrOneManDate(_, _, _, vs, _, _, _, _, _, _)       => updateOne[Date](attr, vs, transformDate)
      case AttrOneManUUID(_, _, _, vs, _, _, _, _, _, _)       => updateOne[UUID](attr, vs, transformUUID)
      case AttrOneManURI(_, _, _, vs, _, _, _, _, _, _)        => updateOne[URI](attr, vs, transformURI)
      case AttrOneManByte(_, _, _, vs, _, _, _, _, _, _)       => updateOne[Byte](attr, vs, transformByte)
      case AttrOneManShort(_, _, _, vs, _, _, _, _, _, _)      => updateOne[Short](attr, vs, transformShort)
      case AttrOneManChar(_, _, _, vs, _, _, _, _, _, _)       => updateOne[Char](attr, vs, transformChar)
    }
  }

  private def resolveAttrOneTac(filterAttr: AttrOneTac): Unit = filterAttr match {
    case AttrOneTacLong(_, "id", Eq, ids1, _, _, _, _, _, _)             => handleIds(ids1)

    case a if a.attr == "id" || a.attr == "tx" => throw ModelError(
      s"Generic attributes not allowed in update molecule. Found:\n" + a)

    case uniqueFilterAttr if uniqueAttrs.contains(uniqueFilterAttr.name) => handleUniqueFilterAttr(filterAttr)
    case _                                                               => handleFilterAttr(filterAttr)
  }

  private def resolveAttrSetMan(dataAttr: AttrSetMan): Unit = {
    updateSetEq(dataAttr)
    resolveAttrSetAdd(dataAttr, true)
  }

  private def resolveAttrSetAdd(attr: AttrSetMan, retractCur: Boolean = false): Unit = attr match {
    case AttrSetManString(_, _, _, sets, _, _, _, _, _, _)     => updateSetAdd[String](attr, sets, transformString, retractCur)
    case AttrSetManInt(_, _, _, sets, _, _, _, _, _, _)        => updateSetAdd[Int](attr, sets, transformInt, retractCur)
    case AttrSetManLong(_, _, _, sets, _, _, _, _, _, _)       => updateSetAdd[Long](attr, sets, transformLong, retractCur)
    case AttrSetManFloat(_, _, _, sets, _, _, _, _, _, _)      => updateSetAdd[Float](attr, sets, transformFloat, retractCur)
    case AttrSetManDouble(_, _, _, sets, _, _, _, _, _, _)     => updateSetAdd[Double](attr, sets, transformDouble, retractCur)
    case AttrSetManBoolean(_, _, _, sets, _, _, _, _, _, _)    => updateSetAdd[Boolean](attr, sets, transformBoolean, retractCur)
    case AttrSetManBigInt(_, _, _, sets, _, _, _, _, _, _)     => updateSetAdd[BigInt](attr, sets, transformBigInt, retractCur)
    case AttrSetManBigDecimal(_, _, _, sets, _, _, _, _, _, _) => updateSetAdd[BigDecimal](attr, sets, transformBigDecimal, retractCur)
    case AttrSetManDate(_, _, _, sets, _, _, _, _, _, _)       => updateSetAdd[Date](attr, sets, transformDate, retractCur)
    case AttrSetManUUID(_, _, _, sets, _, _, _, _, _, _)       => updateSetAdd[UUID](attr, sets, transformUUID, retractCur)
    case AttrSetManURI(_, _, _, sets, _, _, _, _, _, _)        => updateSetAdd[URI](attr, sets, transformURI, retractCur)
    case AttrSetManByte(_, _, _, sets, _, _, _, _, _, _)       => updateSetAdd[Byte](attr, sets, transformByte, retractCur)
    case AttrSetManShort(_, _, _, sets, _, _, _, _, _, _)      => updateSetAdd[Short](attr, sets, transformShort, retractCur)
    case AttrSetManChar(_, _, _, sets, _, _, _, _, _, _)       => updateSetAdd[Char](attr, sets, transformChar, retractCur)
  }

  private def resolveAttrSetSwap(attr: AttrSetMan): Unit = attr match {
    case AttrSetManString(_, _, _, sets, _, _, _, _, _, _)     => updateSetSwab[String](attr, sets, transformString)
    case AttrSetManInt(_, _, _, sets, _, _, _, _, _, _)        => updateSetSwab[Int](attr, sets, transformInt)
    case AttrSetManLong(_, _, _, sets, _, _, _, _, _, _)       => updateSetSwab[Long](attr, sets, transformLong)
    case AttrSetManFloat(_, _, _, sets, _, _, _, _, _, _)      => updateSetSwab[Float](attr, sets, transformFloat)
    case AttrSetManDouble(_, _, _, sets, _, _, _, _, _, _)     => updateSetSwab[Double](attr, sets, transformDouble)
    case AttrSetManBoolean(_, _, _, sets, _, _, _, _, _, _)    => updateSetSwab[Boolean](attr, sets, transformBoolean)
    case AttrSetManBigInt(_, _, _, sets, _, _, _, _, _, _)     => updateSetSwab[BigInt](attr, sets, transformBigInt)
    case AttrSetManBigDecimal(_, _, _, sets, _, _, _, _, _, _) => updateSetSwab[BigDecimal](attr, sets, transformBigDecimal)
    case AttrSetManDate(_, _, _, sets, _, _, _, _, _, _)       => updateSetSwab[Date](attr, sets, transformDate)
    case AttrSetManUUID(_, _, _, sets, _, _, _, _, _, _)       => updateSetSwab[UUID](attr, sets, transformUUID)
    case AttrSetManURI(_, _, _, sets, _, _, _, _, _, _)        => updateSetSwab[URI](attr, sets, transformURI)
    case AttrSetManByte(_, _, _, sets, _, _, _, _, _, _)       => updateSetSwab[Byte](attr, sets, transformByte)
    case AttrSetManShort(_, _, _, sets, _, _, _, _, _, _)      => updateSetSwab[Short](attr, sets, transformShort)
    case AttrSetManChar(_, _, _, sets, _, _, _, _, _, _)       => updateSetSwab[Char](attr, sets, transformChar)
  }

  private def resolveAttrSetRemove(attr: AttrSetMan): Unit = attr match {
    case AttrSetManString(_, _, _, Seq(set), _, _, _, _, _, _)     => updateSetRemove[String](attr, set, transformString)
    case AttrSetManInt(_, _, _, Seq(set), _, _, _, _, _, _)        => updateSetRemove[Int](attr, set, transformInt)
    case AttrSetManLong(_, _, _, Seq(set), _, _, _, _, _, _)       => updateSetRemove[Long](attr, set, transformLong)
    case AttrSetManFloat(_, _, _, Seq(set), _, _, _, _, _, _)      => updateSetRemove[Float](attr, set, transformFloat)
    case AttrSetManDouble(_, _, _, Seq(set), _, _, _, _, _, _)     => updateSetRemove[Double](attr, set, transformDouble)
    case AttrSetManBoolean(_, _, _, Seq(set), _, _, _, _, _, _)    => updateSetRemove[Boolean](attr, set, transformBoolean)
    case AttrSetManBigInt(_, _, _, Seq(set), _, _, _, _, _, _)     => updateSetRemove[BigInt](attr, set, transformBigInt)
    case AttrSetManBigDecimal(_, _, _, Seq(set), _, _, _, _, _, _) => updateSetRemove[BigDecimal](attr, set, transformBigDecimal)
    case AttrSetManDate(_, _, _, Seq(set), _, _, _, _, _, _)       => updateSetRemove[Date](attr, set, transformDate)
    case AttrSetManUUID(_, _, _, Seq(set), _, _, _, _, _, _)       => updateSetRemove[UUID](attr, set, transformUUID)
    case AttrSetManURI(_, _, _, Seq(set), _, _, _, _, _, _)        => updateSetRemove[URI](attr, set, transformURI)
    case AttrSetManByte(_, _, _, Seq(set), _, _, _, _, _, _)       => updateSetRemove[Byte](attr, set, transformByte)
    case AttrSetManShort(_, _, _, Seq(set), _, _, _, _, _, _)      => updateSetRemove[Short](attr, set, transformShort)
    case AttrSetManChar(_, _, _, Seq(set), _, _, _, _, _, _)       => updateSetRemove[Char](attr, set, transformChar)
    case _                                                         => throw ExecutionError(
      s"Can only remove one Set of values for Set attribute `${attr.name}`. Found: $attr"
    )
  }
}