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

        case r@Ref(_, _, _, CardOne) => handleRefNs(r); resolve(tail)
        case br: BackRef             => handleBackRef(br); resolve(tail)

        case ref: Ref     => throw ModelError(
          s"Can't $update attributes in card-many referenced namespace `${ref.refAttr.capitalize}`"
        )
        case _: Nested    => throw ModelError(s"Nested data structure not allowed in $update molecule.")
        case _: NestedOpt => throw ModelError(s"Optional nested data structure not allowed in $update molecule.")
      }
      case Nil             => ()
    }
  }


  private def resolveAttrOneMan(dataAttr: AttrOneMan): Unit = {
    dataAttr match {
      case a if a.attr == "id" => throw ModelError(
        s"Generic id attribute not allowed in update molecule. Found:\n" + a)

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

    case a if a.attr == "id" => throw ModelError(
      s"Generic id attribute not allowed in update molecule. Found:\n" + a)

    case a if uniqueAttrs.contains(a.name) => handleUniqueFilterAttr(a)
    case a                                 => handleFilterAttr(a)
  }

  private def resolveAttrSetMan(dataAttr: AttrSetMan): Unit = dataAttr match {
    case a: AttrSetManString     => updateSetEq(a, a.vs, transformString, set2arrayString, a.refNs)
    case a: AttrSetManInt        => updateSetEq(a, a.vs, transformInt, set2arrayInt, a.refNs)
    case a: AttrSetManLong       => updateSetEq(a, a.vs, transformLong, set2arrayLong, a.refNs)
    case a: AttrSetManFloat      => updateSetEq(a, a.vs, transformFloat, set2arrayFloat, a.refNs)
    case a: AttrSetManDouble     => updateSetEq(a, a.vs, transformDouble, set2arrayDouble, a.refNs)
    case a: AttrSetManBoolean    => updateSetEq(a, a.vs, transformBoolean, set2arrayBoolean, a.refNs)
    case a: AttrSetManBigInt     => updateSetEq(a, a.vs, transformBigInt, set2arrayBigInt, a.refNs)
    case a: AttrSetManBigDecimal => updateSetEq(a, a.vs, transformBigDecimal, set2arrayBigDecimal, a.refNs)
    case a: AttrSetManDate       => updateSetEq(a, a.vs, transformDate, set2arrayDate, a.refNs)
    case a: AttrSetManUUID       => updateSetEq(a, a.vs, transformUUID, set2arrayUUID, a.refNs)
    case a: AttrSetManURI        => updateSetEq(a, a.vs, transformURI, set2arrayURI, a.refNs)
    case a: AttrSetManByte       => updateSetEq(a, a.vs, transformByte, set2arrayByte, a.refNs)
    case a: AttrSetManShort      => updateSetEq(a, a.vs, transformShort, set2arrayShort, a.refNs)
    case a: AttrSetManChar       => updateSetEq(a, a.vs, transformChar, set2arrayChar, a.refNs)
  }

  private def resolveAttrSetAdd(attr: AttrSetMan): Unit = attr match {
    case a: AttrSetManString     => updateSetAdd(a, a.vs, transformString, set2arrayString, a.refNs)
    case a: AttrSetManInt        => updateSetAdd(a, a.vs, transformInt, set2arrayInt, a.refNs)
    case a: AttrSetManLong       => updateSetAdd(a, a.vs, transformLong, set2arrayLong, a.refNs)
    case a: AttrSetManFloat      => updateSetAdd(a, a.vs, transformFloat, set2arrayFloat, a.refNs)
    case a: AttrSetManDouble     => updateSetAdd(a, a.vs, transformDouble, set2arrayDouble, a.refNs)
    case a: AttrSetManBoolean    => updateSetAdd(a, a.vs, transformBoolean, set2arrayBoolean, a.refNs)
    case a: AttrSetManBigInt     => updateSetAdd(a, a.vs, transformBigInt, set2arrayBigInt, a.refNs)
    case a: AttrSetManBigDecimal => updateSetAdd(a, a.vs, transformBigDecimal, set2arrayBigDecimal, a.refNs)
    case a: AttrSetManDate       => updateSetAdd(a, a.vs, transformDate, set2arrayDate, a.refNs)
    case a: AttrSetManUUID       => updateSetAdd(a, a.vs, transformUUID, set2arrayUUID, a.refNs)
    case a: AttrSetManURI        => updateSetAdd(a, a.vs, transformURI, set2arrayURI, a.refNs)
    case a: AttrSetManByte       => updateSetAdd(a, a.vs, transformByte, set2arrayByte, a.refNs)
    case a: AttrSetManShort      => updateSetAdd(a, a.vs, transformShort, set2arrayShort, a.refNs)
    case a: AttrSetManChar       => updateSetAdd(a, a.vs, transformChar, set2arrayChar, a.refNs)
  }

  private def resolveAttrSetSwap(attr: AttrSetMan): Unit = attr match {
    case a: AttrSetManString     => updateSetSwap(a, a.vs, transformString, handleString, dbTypeString, a.refNs)
    case a: AttrSetManInt        => updateSetSwap(a, a.vs, transformInt, handleInt, dbTypeInt, a.refNs)
    case a: AttrSetManLong       => updateSetSwap(a, a.vs, transformLong, handleLong, dbTypeLong, a.refNs)
    case a: AttrSetManFloat      => updateSetSwap(a, a.vs, transformFloat, handleFloat, dbTypeFloat, a.refNs)
    case a: AttrSetManDouble     => updateSetSwap(a, a.vs, transformDouble, handleDouble, dbTypeDouble, a.refNs)
    case a: AttrSetManBoolean    => updateSetSwap(a, a.vs, transformBoolean, handleBoolean, dbTypeBoolean, a.refNs)
    case a: AttrSetManBigInt     => updateSetSwap(a, a.vs, transformBigInt, handleBigInt, dbTypeBigInt, a.refNs)
    case a: AttrSetManBigDecimal => updateSetSwap(a, a.vs, transformBigDecimal, handleBigDecimal, dbTypeBigDecimal, a.refNs)
    case a: AttrSetManDate       => updateSetSwap(a, a.vs, transformDate, handleDate, dbTypeDate, a.refNs)
    case a: AttrSetManUUID       => updateSetSwap(a, a.vs, transformUUID, handleUUID, dbTypeUUID, a.refNs)
    case a: AttrSetManURI        => updateSetSwap(a, a.vs, transformURI, handleURI, dbTypeURI, a.refNs)
    case a: AttrSetManByte       => updateSetSwap(a, a.vs, transformByte, handleByte, dbTypeByte, a.refNs)
    case a: AttrSetManShort      => updateSetSwap(a, a.vs, transformShort, handleShort, dbTypeShort, a.refNs)
    case a: AttrSetManChar       => updateSetSwap(a, a.vs, transformChar, handleChar, dbTypeChar, a.refNs)
  }

  private def resolveAttrSetRemove(attr: AttrSetMan): Unit = attr match {
    case a: AttrSetManString     => updateSetRemove(a, a.vs.head, transformString, handleString, dbTypeString, a.refNs)
    case a: AttrSetManInt        => updateSetRemove(a, a.vs.head, transformInt, handleInt, dbTypeInt, a.refNs)
    case a: AttrSetManLong       => updateSetRemove(a, a.vs.head, transformLong, handleLong, dbTypeLong, a.refNs)
    case a: AttrSetManFloat      => updateSetRemove(a, a.vs.head, transformFloat, handleFloat, dbTypeFloat, a.refNs)
    case a: AttrSetManDouble     => updateSetRemove(a, a.vs.head, transformDouble, handleDouble, dbTypeDouble, a.refNs)
    case a: AttrSetManBoolean    => updateSetRemove(a, a.vs.head, transformBoolean, handleBoolean, dbTypeBoolean, a.refNs)
    case a: AttrSetManBigInt     => updateSetRemove(a, a.vs.head, transformBigInt, handleBigInt, dbTypeBigInt, a.refNs)
    case a: AttrSetManBigDecimal => updateSetRemove(a, a.vs.head, transformBigDecimal, handleBigDecimal, dbTypeBigDecimal, a.refNs)
    case a: AttrSetManDate       => updateSetRemove(a, a.vs.head, transformDate, handleDate, dbTypeDate, a.refNs)
    case a: AttrSetManUUID       => updateSetRemove(a, a.vs.head, transformUUID, handleUUID, dbTypeUUID, a.refNs)
    case a: AttrSetManURI        => updateSetRemove(a, a.vs.head, transformURI, handleURI, dbTypeURI, a.refNs)
    case a: AttrSetManByte       => updateSetRemove(a, a.vs.head, transformByte, handleByte, dbTypeByte, a.refNs)
    case a: AttrSetManShort      => updateSetRemove(a, a.vs.head, transformShort, handleShort, dbTypeShort, a.refNs)
    case a: AttrSetManChar       => updateSetRemove(a, a.vs.head, transformChar, handleChar, dbTypeChar, a.refNs)
    case _                       => throw ExecutionError(
      s"Can only remove one Set of values for Set attribute `${attr.name}`. Found: $attr"
    )
  }
}