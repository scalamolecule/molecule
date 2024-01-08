package molecule.core.transaction

import molecule.base.ast._
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.marshalling.ConnProxy
import molecule.core.transaction.ops.UpdateOps
import molecule.core.util.ModelUtils
import scala.annotation.tailrec

class ResolveUpdate(
  proxy: ConnProxy,
  val isUpsert: Boolean,
) extends ModelUtils { self: UpdateOps =>

  val isUpdate = !isUpsert
  val update   = if (isUpsert) "upsert" else "update"

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

        case r@Ref(_, _, _, CardOne, _, _) => handleRefNs(r); resolve(tail)
        case br: BackRef                   => handleBackRef(br); resolve(tail)

        case ref: Ref     => throw ModelError(
          s"Can't $update attributes in card-many referenced namespace `${ref.refAttr.capitalize}`"
        )
        case _: Nested    => throw ModelError(s"Nested data structure not allowed in $update molecule.")
        case _: NestedOpt => throw ModelError(s"Optional nested data structure not allowed in $update molecule.")
      }
      case Nil             => ()
    }
  }


  private def resolveAttrOneMan(a: AttrOneMan): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a if a.attr == "id" => throw ModelError(
        s"Generic id attribute not allowed in update molecule. Found:\n" + a)

      case a if a.op != Eq => throw ModelError(
        s"Can't $update attributes without an applied value. Found:\n" + a)

      case a: AttrOneManID             => updateOne(ns, attr, a.vs, a.owner, transformID, handleID)
      case a: AttrOneManString         => updateOne(ns, attr, a.vs, a.owner, transformString, handleString)
      case a: AttrOneManInt            => updateOne(ns, attr, a.vs, a.owner, transformInt, handleInt)
      case a: AttrOneManLong           => updateOne(ns, attr, a.vs, a.owner, transformLong, handleLong)
      case a: AttrOneManFloat          => updateOne(ns, attr, a.vs, a.owner, transformFloat, handleFloat)
      case a: AttrOneManDouble         => updateOne(ns, attr, a.vs, a.owner, transformDouble, handleDouble)
      case a: AttrOneManBoolean        => updateOne(ns, attr, a.vs, a.owner, transformBoolean, handleBoolean)
      case a: AttrOneManBigInt         => updateOne(ns, attr, a.vs, a.owner, transformBigInt, handleBigInt)
      case a: AttrOneManBigDecimal     => updateOne(ns, attr, a.vs, a.owner, transformBigDecimal, handleBigDecimal)
      case a: AttrOneManDate           => updateOne(ns, attr, a.vs, a.owner, transformDate, handleDate)
      case a: AttrOneManDuration       => updateOne(ns, attr, a.vs, a.owner, transformDuration, handleDuration)
      case a: AttrOneManInstant        => updateOne(ns, attr, a.vs, a.owner, transformInstant, handleInstant)
      case a: AttrOneManLocalDate      => updateOne(ns, attr, a.vs, a.owner, transformLocalDate, handleLocalDate)
      case a: AttrOneManLocalTime      => updateOne(ns, attr, a.vs, a.owner, transformLocalTime, handleLocalTime)
      case a: AttrOneManLocalDateTime  => updateOne(ns, attr, a.vs, a.owner, transformLocalDateTime, handleLocalDateTime)
      case a: AttrOneManOffsetTime     => updateOne(ns, attr, a.vs, a.owner, transformOffsetTime, handleOffsetTime)
      case a: AttrOneManOffsetDateTime => updateOne(ns, attr, a.vs, a.owner, transformOffsetDateTime, handleOffsetDateTime)
      case a: AttrOneManZonedDateTime  => updateOne(ns, attr, a.vs, a.owner, transformZonedDateTime, handleZonedDateTime)
      case a: AttrOneManUUID           => updateOne(ns, attr, a.vs, a.owner, transformUUID, handleUUID)
      case a: AttrOneManURI            => updateOne(ns, attr, a.vs, a.owner, transformURI, handleURI)
      case a: AttrOneManByte           => updateOne(ns, attr, a.vs, a.owner, transformByte, handleByte)
      case a: AttrOneManShort          => updateOne(ns, attr, a.vs, a.owner, transformShort, handleShort)
      case a: AttrOneManChar           => updateOne(ns, attr, a.vs, a.owner, transformChar, handleChar)
    }
  }

  private def resolveAttrOneTac(a: AttrOneTac): Unit = {
    a match {
      case AttrOneTacID(ns, "id", Eq, ids1, _, _, _, _, _, _, _, _) => handleIds(ns, ids1)

      case a if a.attr == "id" => throw ModelError(
        s"Generic id attribute not allowed in update molecule. Found:\n" + a)

      case a if proxy.uniqueAttrs.contains(a.cleanName) => handleUniqueFilterAttr(a)
      case a                                            => handleFilterAttr(a)
    }
  }

  private def resolveAttrSetMan(a: AttrSetMan): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrSetManID             => updateSetEq(ns, attr, a.vs, a.refNs, a.owner, transformID, set2arrayID, extsID, value2jsonID)
      case a: AttrSetManString         => updateSetEq(ns, attr, a.vs, a.refNs, a.owner, transformString, set2arrayString, extsString, value2jsonString)
      case a: AttrSetManInt            => updateSetEq(ns, attr, a.vs, a.refNs, a.owner, transformInt, set2arrayInt, extsInt, value2jsonInt)
      case a: AttrSetManLong           => updateSetEq(ns, attr, a.vs, a.refNs, a.owner, transformLong, set2arrayLong, extsLong, value2jsonLong)
      case a: AttrSetManFloat          => updateSetEq(ns, attr, a.vs, a.refNs, a.owner, transformFloat, set2arrayFloat, extsFloat, value2jsonFloat)
      case a: AttrSetManDouble         => updateSetEq(ns, attr, a.vs, a.refNs, a.owner, transformDouble, set2arrayDouble, extsDouble, value2jsonDouble)
      case a: AttrSetManBoolean        => updateSetEq(ns, attr, a.vs, a.refNs, a.owner, transformBoolean, set2arrayBoolean, extsBoolean, value2jsonBoolean)
      case a: AttrSetManBigInt         => updateSetEq(ns, attr, a.vs, a.refNs, a.owner, transformBigInt, set2arrayBigInt, extsBigInt, value2jsonBigInt)
      case a: AttrSetManBigDecimal     => updateSetEq(ns, attr, a.vs, a.refNs, a.owner, transformBigDecimal, set2arrayBigDecimal, extsBigDecimal, value2jsonBigDecimal)
      case a: AttrSetManDate           => updateSetEq(ns, attr, a.vs, a.refNs, a.owner, transformDate, set2arrayDate, extsDate, value2jsonDate)
      case a: AttrSetManDuration       => updateSetEq(ns, attr, a.vs, a.refNs, a.owner, transformDuration, set2arrayDuration, extsDuration, value2jsonDuration)
      case a: AttrSetManInstant        => updateSetEq(ns, attr, a.vs, a.refNs, a.owner, transformInstant, set2arrayInstant, extsInstant, value2jsonInstant)
      case a: AttrSetManLocalDate      => updateSetEq(ns, attr, a.vs, a.refNs, a.owner, transformLocalDate, set2arrayLocalDate, extsLocalDate, value2jsonLocalDate)
      case a: AttrSetManLocalTime      => updateSetEq(ns, attr, a.vs, a.refNs, a.owner, transformLocalTime, set2arrayLocalTime, extsLocalTime, value2jsonLocalTime)
      case a: AttrSetManLocalDateTime  => updateSetEq(ns, attr, a.vs, a.refNs, a.owner, transformLocalDateTime, set2arrayLocalDateTime, extsLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSetManOffsetTime     => updateSetEq(ns, attr, a.vs, a.refNs, a.owner, transformOffsetTime, set2arrayOffsetTime, extsOffsetTime, value2jsonOffsetTime)
      case a: AttrSetManOffsetDateTime => updateSetEq(ns, attr, a.vs, a.refNs, a.owner, transformOffsetDateTime, set2arrayOffsetDateTime, extsOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSetManZonedDateTime  => updateSetEq(ns, attr, a.vs, a.refNs, a.owner, transformZonedDateTime, set2arrayZonedDateTime, extsZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSetManUUID           => updateSetEq(ns, attr, a.vs, a.refNs, a.owner, transformUUID, set2arrayUUID, extsUUID, value2jsonUUID)
      case a: AttrSetManURI            => updateSetEq(ns, attr, a.vs, a.refNs, a.owner, transformURI, set2arrayURI, extsURI, value2jsonURI)
      case a: AttrSetManByte           => updateSetEq(ns, attr, a.vs, a.refNs, a.owner, transformByte, set2arrayByte, extsByte, value2jsonByte)
      case a: AttrSetManShort          => updateSetEq(ns, attr, a.vs, a.refNs, a.owner, transformShort, set2arrayShort, extsShort, value2jsonShort)
      case a: AttrSetManChar           => updateSetEq(ns, attr, a.vs, a.refNs, a.owner, transformChar, set2arrayChar, extsChar, value2jsonChar)
    }
  }

  private def resolveAttrSetAdd(a: AttrSetMan): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrSetManID             => updateSetAdd(ns, attr, a.vs, a.refNs, a.owner, transformID, set2arrayID, extsID, value2jsonID)
      case a: AttrSetManString         => updateSetAdd(ns, attr, a.vs, a.refNs, a.owner, transformString, set2arrayString, extsString, value2jsonString)
      case a: AttrSetManInt            => updateSetAdd(ns, attr, a.vs, a.refNs, a.owner, transformInt, set2arrayInt, extsInt, value2jsonInt)
      case a: AttrSetManLong           => updateSetAdd(ns, attr, a.vs, a.refNs, a.owner, transformLong, set2arrayLong, extsLong, value2jsonLong)
      case a: AttrSetManFloat          => updateSetAdd(ns, attr, a.vs, a.refNs, a.owner, transformFloat, set2arrayFloat, extsFloat, value2jsonFloat)
      case a: AttrSetManDouble         => updateSetAdd(ns, attr, a.vs, a.refNs, a.owner, transformDouble, set2arrayDouble, extsDouble, value2jsonDouble)
      case a: AttrSetManBoolean        => updateSetAdd(ns, attr, a.vs, a.refNs, a.owner, transformBoolean, set2arrayBoolean, extsBoolean, value2jsonBoolean)
      case a: AttrSetManBigInt         => updateSetAdd(ns, attr, a.vs, a.refNs, a.owner, transformBigInt, set2arrayBigInt, extsBigInt, value2jsonBigInt)
      case a: AttrSetManBigDecimal     => updateSetAdd(ns, attr, a.vs, a.refNs, a.owner, transformBigDecimal, set2arrayBigDecimal, extsBigDecimal, value2jsonBigDecimal)
      case a: AttrSetManDate           => updateSetAdd(ns, attr, a.vs, a.refNs, a.owner, transformDate, set2arrayDate, extsDate, value2jsonDate)
      case a: AttrSetManDuration       => updateSetAdd(ns, attr, a.vs, a.refNs, a.owner, transformDuration, set2arrayDuration, extsDuration, value2jsonDuration)
      case a: AttrSetManInstant        => updateSetAdd(ns, attr, a.vs, a.refNs, a.owner, transformInstant, set2arrayInstant, extsInstant, value2jsonInstant)
      case a: AttrSetManLocalDate      => updateSetAdd(ns, attr, a.vs, a.refNs, a.owner, transformLocalDate, set2arrayLocalDate, extsLocalDate, value2jsonLocalDate)
      case a: AttrSetManLocalTime      => updateSetAdd(ns, attr, a.vs, a.refNs, a.owner, transformLocalTime, set2arrayLocalTime, extsLocalTime, value2jsonLocalTime)
      case a: AttrSetManLocalDateTime  => updateSetAdd(ns, attr, a.vs, a.refNs, a.owner, transformLocalDateTime, set2arrayLocalDateTime, extsLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSetManOffsetTime     => updateSetAdd(ns, attr, a.vs, a.refNs, a.owner, transformOffsetTime, set2arrayOffsetTime, extsOffsetTime, value2jsonOffsetTime)
      case a: AttrSetManOffsetDateTime => updateSetAdd(ns, attr, a.vs, a.refNs, a.owner, transformOffsetDateTime, set2arrayOffsetDateTime, extsOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSetManZonedDateTime  => updateSetAdd(ns, attr, a.vs, a.refNs, a.owner, transformZonedDateTime, set2arrayZonedDateTime, extsZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSetManUUID           => updateSetAdd(ns, attr, a.vs, a.refNs, a.owner, transformUUID, set2arrayUUID, extsUUID, value2jsonUUID)
      case a: AttrSetManURI            => updateSetAdd(ns, attr, a.vs, a.refNs, a.owner, transformURI, set2arrayURI, extsURI, value2jsonURI)
      case a: AttrSetManByte           => updateSetAdd(ns, attr, a.vs, a.refNs, a.owner, transformByte, set2arrayByte, extsByte, value2jsonByte)
      case a: AttrSetManShort          => updateSetAdd(ns, attr, a.vs, a.refNs, a.owner, transformShort, set2arrayShort, extsShort, value2jsonShort)
      case a: AttrSetManChar           => updateSetAdd(ns, attr, a.vs, a.refNs, a.owner, transformChar, set2arrayChar, extsChar, value2jsonChar)
    }
  }

  private def resolveAttrSetSwap(a: AttrSetMan): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrSetManID             => updateSetSwap(ns, attr, a.vs, a.refNs, a.owner, transformID, handleID, extsID, value2jsonID, one2jsonID)
      case a: AttrSetManString         => updateSetSwap(ns, attr, a.vs, a.refNs, a.owner, transformString, handleString, extsString, value2jsonString, one2jsonString)
      case a: AttrSetManInt            => updateSetSwap(ns, attr, a.vs, a.refNs, a.owner, transformInt, handleInt, extsInt, value2jsonInt, one2jsonInt)
      case a: AttrSetManLong           => updateSetSwap(ns, attr, a.vs, a.refNs, a.owner, transformLong, handleLong, extsLong, value2jsonLong, one2jsonLong)
      case a: AttrSetManFloat          => updateSetSwap(ns, attr, a.vs, a.refNs, a.owner, transformFloat, handleFloat, extsFloat, value2jsonFloat, one2jsonFloat)
      case a: AttrSetManDouble         => updateSetSwap(ns, attr, a.vs, a.refNs, a.owner, transformDouble, handleDouble, extsDouble, value2jsonDouble, one2jsonDouble)
      case a: AttrSetManBoolean        => updateSetSwap(ns, attr, a.vs, a.refNs, a.owner, transformBoolean, handleBoolean, extsBoolean, value2jsonBoolean, one2jsonBoolean)
      case a: AttrSetManBigInt         => updateSetSwap(ns, attr, a.vs, a.refNs, a.owner, transformBigInt, handleBigInt, extsBigInt, value2jsonBigInt, one2jsonBigInt)
      case a: AttrSetManBigDecimal     => updateSetSwap(ns, attr, a.vs, a.refNs, a.owner, transformBigDecimal, handleBigDecimal, extsBigDecimal, value2jsonBigDecimal, one2jsonBigDecimal)
      case a: AttrSetManDate           => updateSetSwap(ns, attr, a.vs, a.refNs, a.owner, transformDate, handleDate, extsDate, value2jsonDate, one2jsonDate)
      case a: AttrSetManDuration       => updateSetSwap(ns, attr, a.vs, a.refNs, a.owner, transformDuration, handleDuration, extsDuration, value2jsonDuration, one2jsonDuration)
      case a: AttrSetManInstant        => updateSetSwap(ns, attr, a.vs, a.refNs, a.owner, transformInstant, handleInstant, extsInstant, value2jsonInstant, one2jsonInstant)
      case a: AttrSetManLocalDate      => updateSetSwap(ns, attr, a.vs, a.refNs, a.owner, transformLocalDate, handleLocalDate, extsLocalDate, value2jsonLocalDate, one2jsonLocalDate)
      case a: AttrSetManLocalTime      => updateSetSwap(ns, attr, a.vs, a.refNs, a.owner, transformLocalTime, handleLocalTime, extsLocalTime, value2jsonLocalTime, one2jsonLocalTime)
      case a: AttrSetManLocalDateTime  => updateSetSwap(ns, attr, a.vs, a.refNs, a.owner, transformLocalDateTime, handleLocalDateTime, extsLocalDateTime, value2jsonLocalDateTime, one2jsonLocalDateTime)
      case a: AttrSetManOffsetTime     => updateSetSwap(ns, attr, a.vs, a.refNs, a.owner, transformOffsetTime, handleOffsetTime, extsOffsetTime, value2jsonOffsetTime, one2jsonOffsetTime)
      case a: AttrSetManOffsetDateTime => updateSetSwap(ns, attr, a.vs, a.refNs, a.owner, transformOffsetDateTime, handleOffsetDateTime, extsOffsetDateTime, value2jsonOffsetDateTime, one2jsonOffsetDateTime)
      case a: AttrSetManZonedDateTime  => updateSetSwap(ns, attr, a.vs, a.refNs, a.owner, transformZonedDateTime, handleZonedDateTime, extsZonedDateTime, value2jsonZonedDateTime, one2jsonZonedDateTime)
      case a: AttrSetManUUID           => updateSetSwap(ns, attr, a.vs, a.refNs, a.owner, transformUUID, handleUUID, extsUUID, value2jsonUUID, one2jsonUUID)
      case a: AttrSetManURI            => updateSetSwap(ns, attr, a.vs, a.refNs, a.owner, transformURI, handleURI, extsURI, value2jsonURI, one2jsonURI)
      case a: AttrSetManByte           => updateSetSwap(ns, attr, a.vs, a.refNs, a.owner, transformByte, handleByte, extsByte, value2jsonByte, one2jsonByte)
      case a: AttrSetManShort          => updateSetSwap(ns, attr, a.vs, a.refNs, a.owner, transformShort, handleShort, extsShort, value2jsonShort, one2jsonShort)
      case a: AttrSetManChar           => updateSetSwap(ns, attr, a.vs, a.refNs, a.owner, transformChar, handleChar, extsChar, value2jsonChar, one2jsonChar)
    }
  }

  private def resolveAttrSetRemove(a: AttrSetMan): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrSetManID             => updateSetRemove(ns, attr, a.vs.head, a.refNs, a.owner, transformID, handleID, extsID, one2jsonID)
      case a: AttrSetManString         => updateSetRemove(ns, attr, a.vs.head, a.refNs, a.owner, transformString, handleString, extsString, one2jsonString)
      case a: AttrSetManInt            => updateSetRemove(ns, attr, a.vs.head, a.refNs, a.owner, transformInt, handleInt, extsInt, one2jsonInt)
      case a: AttrSetManLong           => updateSetRemove(ns, attr, a.vs.head, a.refNs, a.owner, transformLong, handleLong, extsLong, one2jsonLong)
      case a: AttrSetManFloat          => updateSetRemove(ns, attr, a.vs.head, a.refNs, a.owner, transformFloat, handleFloat, extsFloat, one2jsonFloat)
      case a: AttrSetManDouble         => updateSetRemove(ns, attr, a.vs.head, a.refNs, a.owner, transformDouble, handleDouble, extsDouble, one2jsonDouble)
      case a: AttrSetManBoolean        => updateSetRemove(ns, attr, a.vs.head, a.refNs, a.owner, transformBoolean, handleBoolean, extsBoolean, one2jsonBoolean)
      case a: AttrSetManBigInt         => updateSetRemove(ns, attr, a.vs.head, a.refNs, a.owner, transformBigInt, handleBigInt, extsBigInt, one2jsonBigInt)
      case a: AttrSetManBigDecimal     => updateSetRemove(ns, attr, a.vs.head, a.refNs, a.owner, transformBigDecimal, handleBigDecimal, extsBigDecimal, one2jsonBigDecimal)
      case a: AttrSetManDate           => updateSetRemove(ns, attr, a.vs.head, a.refNs, a.owner, transformDate, handleDate, extsDate, one2jsonDate)
      case a: AttrSetManDuration       => updateSetRemove(ns, attr, a.vs.head, a.refNs, a.owner, transformDuration, handleDuration, extsDuration, one2jsonDuration)
      case a: AttrSetManInstant        => updateSetRemove(ns, attr, a.vs.head, a.refNs, a.owner, transformInstant, handleInstant, extsInstant, one2jsonInstant)
      case a: AttrSetManLocalDate      => updateSetRemove(ns, attr, a.vs.head, a.refNs, a.owner, transformLocalDate, handleLocalDate, extsLocalDate, one2jsonLocalDate)
      case a: AttrSetManLocalTime      => updateSetRemove(ns, attr, a.vs.head, a.refNs, a.owner, transformLocalTime, handleLocalTime, extsLocalTime, one2jsonLocalTime)
      case a: AttrSetManLocalDateTime  => updateSetRemove(ns, attr, a.vs.head, a.refNs, a.owner, transformLocalDateTime, handleLocalDateTime, extsLocalDateTime, one2jsonLocalDateTime)
      case a: AttrSetManOffsetTime     => updateSetRemove(ns, attr, a.vs.head, a.refNs, a.owner, transformOffsetTime, handleOffsetTime, extsOffsetTime, one2jsonOffsetTime)
      case a: AttrSetManOffsetDateTime => updateSetRemove(ns, attr, a.vs.head, a.refNs, a.owner, transformOffsetDateTime, handleOffsetDateTime, extsOffsetDateTime, one2jsonOffsetDateTime)
      case a: AttrSetManZonedDateTime  => updateSetRemove(ns, attr, a.vs.head, a.refNs, a.owner, transformZonedDateTime, handleZonedDateTime, extsZonedDateTime, one2jsonZonedDateTime)
      case a: AttrSetManUUID           => updateSetRemove(ns, attr, a.vs.head, a.refNs, a.owner, transformUUID, handleUUID, extsUUID, one2jsonUUID)
      case a: AttrSetManURI            => updateSetRemove(ns, attr, a.vs.head, a.refNs, a.owner, transformURI, handleURI, extsURI, one2jsonURI)
      case a: AttrSetManByte           => updateSetRemove(ns, attr, a.vs.head, a.refNs, a.owner, transformByte, handleByte, extsByte, one2jsonByte)
      case a: AttrSetManShort          => updateSetRemove(ns, attr, a.vs.head, a.refNs, a.owner, transformShort, handleShort, extsShort, one2jsonShort)
      case a: AttrSetManChar           => updateSetRemove(ns, attr, a.vs.head, a.refNs, a.owner, transformChar, handleChar, extsChar, one2jsonChar)
    }
  }
}