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

  private def resolveAttrOneMan(dataAttr: AttrOneMan): Unit = {
    dataAttr match {
      case a if a.attr == "id" || a.attr == "tx" => throw ModelError(
        s"Generic attributes not allowed in update molecule. Found:\n" + a)

      case a if a.op != Eq => throw ModelError(
        s"Can't $update attributes without an applied value. Found:\n" + a)

      case a: AttrOneManString     => updateOne(a, a.vs, transformString, handleString)
      case a: AttrOneManInt        => updateOne(a, a.vs, transformInt, handleInt)
      case a: AttrOneManLong       => updateOne(a, a.vs, transformLong, handleLong)
      case a: AttrOneManFloat      => updateOne(a, a.vs, transformFloat, handleFloat)
      case a: AttrOneManDouble     => updateOne(a, a.vs, transformDouble, handleDouble)
      case a: AttrOneManBoolean    => updateOne(a, a.vs, transformBoolean, handleBoolean)
      case a: AttrOneManBigInt     => updateOne(a, a.vs, transformBigInt, handleBigInt)
      case a: AttrOneManBigDecimal => updateOne(a, a.vs, transformBigDecimal, handleBigDecimal)
      case a: AttrOneManDate       => updateOne(a, a.vs, transformDate, handleDate)
      case a: AttrOneManUUID       => updateOne(a, a.vs, transformUUID, handleUUID)
      case a: AttrOneManURI        => updateOne(a, a.vs, transformURI, handleURI)
      case a: AttrOneManByte       => updateOne(a, a.vs, transformByte, handleByte)
      case a: AttrOneManShort      => updateOne(a, a.vs, transformShort, handleShort)
      case a: AttrOneManChar       => updateOne(a, a.vs, transformChar, handleChar)
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
    case a: AttrSetManString    => updateSetAdd(attr, a.vs, transformString, retractCur)
    case a: AttrSetManInt       => updateSetAdd(attr, a.vs, transformInt, retractCur)
    case a: AttrSetManLong      => updateSetAdd(attr, a.vs, transformLong, retractCur)
    case a: AttrSetManFloat     => updateSetAdd(attr, a.vs, transformFloat, retractCur)
    case a: AttrSetManDouble    => updateSetAdd(attr, a.vs, transformDouble, retractCur)
    case a: AttrSetManBoolean   => updateSetAdd(attr, a.vs, transformBoolean, retractCur)
    case a: AttrSetManBigInt    => updateSetAdd(attr, a.vs, transformBigInt, retractCur)
    case a: AttrSetManBigDecimal=> updateSetAdd(attr, a.vs, transformBigDecimal, retractCur)
    case a: AttrSetManDate      => updateSetAdd(attr, a.vs, transformDate, retractCur)
    case a: AttrSetManUUID      => updateSetAdd(attr, a.vs, transformUUID, retractCur)
    case a: AttrSetManURI       => updateSetAdd(attr, a.vs, transformURI, retractCur)
    case a: AttrSetManByte      => updateSetAdd(attr, a.vs, transformByte, retractCur)
    case a: AttrSetManShort     => updateSetAdd(attr, a.vs, transformShort, retractCur)
    case a: AttrSetManChar      => updateSetAdd(attr, a.vs, transformChar, retractCur)
  }

  private def resolveAttrSetSwap(attr: AttrSetMan): Unit = attr match {
    case a: AttrSetManString     => updateSetSwab(attr, a.vs, transformString)
    case a: AttrSetManInt        => updateSetSwab(attr, a.vs, transformInt)
    case a: AttrSetManLong       => updateSetSwab(attr, a.vs, transformLong)
    case a: AttrSetManFloat      => updateSetSwab(attr, a.vs, transformFloat)
    case a: AttrSetManDouble     => updateSetSwab(attr, a.vs, transformDouble)
    case a: AttrSetManBoolean    => updateSetSwab(attr, a.vs, transformBoolean)
    case a: AttrSetManBigInt     => updateSetSwab(attr, a.vs, transformBigInt)
    case a: AttrSetManBigDecimal => updateSetSwab(attr, a.vs, transformBigDecimal)
    case a: AttrSetManDate       => updateSetSwab(attr, a.vs, transformDate)
    case a: AttrSetManUUID       => updateSetSwab(attr, a.vs, transformUUID)
    case a: AttrSetManURI        => updateSetSwab(attr, a.vs, transformURI)
    case a: AttrSetManByte       => updateSetSwab(attr, a.vs, transformByte)
    case a: AttrSetManShort      => updateSetSwab(attr, a.vs, transformShort)
    case a: AttrSetManChar       => updateSetSwab(attr, a.vs, transformChar)
  }

  private def resolveAttrSetRemove(attr: AttrSetMan): Unit = attr match {
    case a:AttrSetManString    => updateSetRemove(attr, a.vs.head, transformString)
    case a:AttrSetManInt       => updateSetRemove(attr, a.vs.head, transformInt)
    case a:AttrSetManLong      => updateSetRemove(attr, a.vs.head, transformLong)
    case a:AttrSetManFloat     => updateSetRemove(attr, a.vs.head, transformFloat)
    case a:AttrSetManDouble    => updateSetRemove(attr, a.vs.head, transformDouble)
    case a:AttrSetManBoolean   => updateSetRemove(attr, a.vs.head, transformBoolean)
    case a:AttrSetManBigInt    => updateSetRemove(attr, a.vs.head, transformBigInt)
    case a:AttrSetManBigDecimal=> updateSetRemove(attr, a.vs.head, transformBigDecimal)
    case a:AttrSetManDate      => updateSetRemove(attr, a.vs.head, transformDate)
    case a:AttrSetManUUID      => updateSetRemove(attr, a.vs.head, transformUUID)
    case a:AttrSetManURI       => updateSetRemove(attr, a.vs.head, transformURI)
    case a:AttrSetManByte      => updateSetRemove(attr, a.vs.head, transformByte)
    case a:AttrSetManShort     => updateSetRemove(attr, a.vs.head, transformShort)
    case a:AttrSetManChar      => updateSetRemove(attr, a.vs.head, transformChar)
    case _                                                         => throw ExecutionError(
      s"Can only remove one Set of values for Set attribute `${attr.name}`. Found: $attr"
    )
  }
}