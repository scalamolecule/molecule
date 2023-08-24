package molecule.core.transaction

import molecule.base.ast.SchemaAST._
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.transaction.ops.UpdateOps
import scala.annotation.tailrec

class ResolveUpdate(
  uniqueAttrs: List[String],
  val isUpsert: Boolean
) { self: UpdateOps =>

  val update = if (isUpsert) "upsert" else "update"

  @tailrec
  final def resolve(elements: List[Element]): Unit = {
    elements match {
      case element :: tail => element match {
        case a: Attr =>
          a match {
            case a: AttrOne =>
              a match {
                case a: AttrOneTac => resolveAttrOneTac(a); resolve(tail)
                case a: AttrOneMan => resolveAttrOneMan(a); resolve(tail)
                case _: AttrOneOpt => throw ModelError(s"Can't $update optional values. Found:\n" + a)
              }

            case a: AttrSet =>
              a match {
                case a: AttrSetMan => a.op match {
                  case Eq     => resolveAttrSetMan(a); resolve(tail)
                  case Add    => resolveAttrSetAdd(a); resolve(tail)
                  case Swap   => resolveAttrSetSwap(a); resolve(tail)
                  case Remove => resolveAttrSetRemove(a); resolve(tail)
                  case _      => throw ModelError(s"Unexpected $update operation for card-many attribute. Found:\n" + a)
                }
                case _: AttrSetTac => throw ModelError("Can only lookup entity with card-one attribute value. Found:\n" + a)
                case _: AttrSetOpt => throw ModelError(s"Can't $update optional values. Found:\n" + a)
              }
          }

        case _: Nested    => throw ModelError(s"Nested data structure not allowed in $update molecule.")
        case _: NestedOpt => throw ModelError(s"Optional nested data structure not allowed in $update molecule.")

        case Ref(_, _, "Tx", CardOne, _)  => resolve(tail) // todo
        case ref@Ref(_, _, _, CardOne, _) => handleRefNs(ref); resolve(tail)
        case ref: Ref                     => throw ModelError(
          s"Can't $update attributes in card-many referenced namespace `${ref.refAttr.capitalize}`"
        )

        case b: BackRef =>
          handleBackRef(b)
          resolve(tail)

        case Composite(es) =>
          resolveSubElements(es)
          resolve(tail)

        case TxMetaData(es) =>
          handleTxMetaData()
          resolveSubElements(es)
          resolve(tail)
      }
      case Nil             => ()
    }
  }

  private def resolveSubElements(elements: List[Element]): Unit = resolve(elements)

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
    case AttrOneTacLong(_, "id", Eq, ids1, _, _, _, _, _, _) => handleIds(ids1)

    case a if a.attr == "id" || a.attr == "tx" => throw ModelError(
      s"Generic attributes not allowed in update molecule. Found:\n" + a)

    case a if uniqueAttrs.contains(a.name) => handleUniqueFilterAttr(a)
    case a                                 => handleFilterAttr(a)
  }

  private def resolveAttrSetMan(dataAttr: AttrSetMan): Unit = dataAttr match {
    case a: AttrSetManString     => updateSetEq(a, a.vs, transformString, set2arrayString)
    case a: AttrSetManInt        => updateSetEq(a, a.vs, transformInt, set2arrayInt)
    case a: AttrSetManLong       => updateSetEq(a, a.vs, transformLong, set2arrayLong)
    case a: AttrSetManFloat      => updateSetEq(a, a.vs, transformFloat, set2arrayFloat)
    case a: AttrSetManDouble     => updateSetEq(a, a.vs, transformDouble, set2arrayDouble)
    case a: AttrSetManBoolean    => updateSetEq(a, a.vs, transformBoolean, set2arrayBoolean)
    case a: AttrSetManBigInt     => updateSetEq(a, a.vs, transformBigInt, set2arrayBigInt)
    case a: AttrSetManBigDecimal => updateSetEq(a, a.vs, transformBigDecimal, set2arrayBigDecimal)
    case a: AttrSetManDate       => updateSetEq(a, a.vs, transformDate, set2arrayDate)
    case a: AttrSetManUUID       => updateSetEq(a, a.vs, transformUUID, set2arrayUUID)
    case a: AttrSetManURI        => updateSetEq(a, a.vs, transformURI, set2arrayURI)
    case a: AttrSetManByte       => updateSetEq(a, a.vs, transformByte, set2arrayByte)
    case a: AttrSetManShort      => updateSetEq(a, a.vs, transformShort, set2arrayShort)
    case a: AttrSetManChar       => updateSetEq(a, a.vs, transformChar, set2arrayChar)
  }

  private def resolveAttrSetAdd(attr: AttrSetMan): Unit = attr match {
    case a: AttrSetManString     => updateSetAdd(a, a.vs, transformString, set2arrayString)
    case a: AttrSetManInt        => updateSetAdd(a, a.vs, transformInt, set2arrayInt)
    case a: AttrSetManLong       => updateSetAdd(a, a.vs, transformLong, set2arrayLong)
    case a: AttrSetManFloat      => updateSetAdd(a, a.vs, transformFloat, set2arrayFloat)
    case a: AttrSetManDouble     => updateSetAdd(a, a.vs, transformDouble, set2arrayDouble)
    case a: AttrSetManBoolean    => updateSetAdd(a, a.vs, transformBoolean, set2arrayBoolean)
    case a: AttrSetManBigInt     => updateSetAdd(a, a.vs, transformBigInt, set2arrayBigInt)
    case a: AttrSetManBigDecimal => updateSetAdd(a, a.vs, transformBigDecimal, set2arrayBigDecimal)
    case a: AttrSetManDate       => updateSetAdd(a, a.vs, transformDate, set2arrayDate)
    case a: AttrSetManUUID       => updateSetAdd(a, a.vs, transformUUID, set2arrayUUID)
    case a: AttrSetManURI        => updateSetAdd(a, a.vs, transformURI, set2arrayURI)
    case a: AttrSetManByte       => updateSetAdd(a, a.vs, transformByte, set2arrayByte)
    case a: AttrSetManShort      => updateSetAdd(a, a.vs, transformShort, set2arrayShort)
    case a: AttrSetManChar       => updateSetAdd(a, a.vs, transformChar, set2arrayChar)
  }

  private def resolveAttrSetSwap(attr: AttrSetMan): Unit = attr match {
    case a: AttrSetManString     => updateSetSwab(a, a.vs, transformString, set2arrayString)
    case a: AttrSetManInt        => updateSetSwab(a, a.vs, transformInt, set2arrayInt)
    case a: AttrSetManLong       => updateSetSwab(a, a.vs, transformLong, set2arrayLong)
    case a: AttrSetManFloat      => updateSetSwab(a, a.vs, transformFloat, set2arrayFloat)
    case a: AttrSetManDouble     => updateSetSwab(a, a.vs, transformDouble, set2arrayDouble)
    case a: AttrSetManBoolean    => updateSetSwab(a, a.vs, transformBoolean, set2arrayBoolean)
    case a: AttrSetManBigInt     => updateSetSwab(a, a.vs, transformBigInt, set2arrayBigInt)
    case a: AttrSetManBigDecimal => updateSetSwab(a, a.vs, transformBigDecimal, set2arrayBigDecimal)
    case a: AttrSetManDate       => updateSetSwab(a, a.vs, transformDate, set2arrayDate)
    case a: AttrSetManUUID       => updateSetSwab(a, a.vs, transformUUID, set2arrayUUID)
    case a: AttrSetManURI        => updateSetSwab(a, a.vs, transformURI, set2arrayURI)
    case a: AttrSetManByte       => updateSetSwab(a, a.vs, transformByte, set2arrayByte)
    case a: AttrSetManShort      => updateSetSwab(a, a.vs, transformShort, set2arrayShort)
    case a: AttrSetManChar       => updateSetSwab(a, a.vs, transformChar, set2arrayChar)
  }

  private def resolveAttrSetRemove(attr: AttrSetMan): Unit = attr match {
    case a: AttrSetManString     => updateSetRemove(a, a.vs.head, transformString)
    case a: AttrSetManInt        => updateSetRemove(a, a.vs.head, transformInt)
    case a: AttrSetManLong       => updateSetRemove(a, a.vs.head, transformLong)
    case a: AttrSetManFloat      => updateSetRemove(a, a.vs.head, transformFloat)
    case a: AttrSetManDouble     => updateSetRemove(a, a.vs.head, transformDouble)
    case a: AttrSetManBoolean    => updateSetRemove(a, a.vs.head, transformBoolean)
    case a: AttrSetManBigInt     => updateSetRemove(a, a.vs.head, transformBigInt)
    case a: AttrSetManBigDecimal => updateSetRemove(a, a.vs.head, transformBigDecimal)
    case a: AttrSetManDate       => updateSetRemove(a, a.vs.head, transformDate)
    case a: AttrSetManUUID       => updateSetRemove(a, a.vs.head, transformUUID)
    case a: AttrSetManURI        => updateSetRemove(a, a.vs.head, transformURI)
    case a: AttrSetManByte       => updateSetRemove(a, a.vs.head, transformByte)
    case a: AttrSetManShort      => updateSetRemove(a, a.vs.head, transformShort)
    case a: AttrSetManChar       => updateSetRemove(a, a.vs.head, transformChar)
    case _                       => throw ExecutionError(
      s"Can only remove one Set of values for Set attribute `${attr.name}`. Found: $attr"
    )
  }
}