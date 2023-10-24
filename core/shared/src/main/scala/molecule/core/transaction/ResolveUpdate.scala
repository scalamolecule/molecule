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

  //  private val checkReservedKeywords = proxy.reserved.isDefined
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

        case r@Ref(_, _, _, CardOne, _) => handleRefNs(r); resolve(tail)
        case br: BackRef                => handleBackRef(br); resolve(tail)

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

      case a: AttrOneManID             => updateOne(ns, attr, a.vs, transformId, handleId)
      case a: AttrOneManString         => updateOne(ns, attr, a.vs, transformString, handleString)
      case a: AttrOneManInt            => updateOne(ns, attr, a.vs, transformInt, handleInt)
      case a: AttrOneManLong           => updateOne(ns, attr, a.vs, transformLong, handleLong)
      case a: AttrOneManFloat          => updateOne(ns, attr, a.vs, transformFloat, handleFloat)
      case a: AttrOneManDouble         => updateOne(ns, attr, a.vs, transformDouble, handleDouble)
      case a: AttrOneManBoolean        => updateOne(ns, attr, a.vs, transformBoolean, handleBoolean)
      case a: AttrOneManBigInt         => updateOne(ns, attr, a.vs, transformBigInt, handleBigInt)
      case a: AttrOneManBigDecimal     => updateOne(ns, attr, a.vs, transformBigDecimal, handleBigDecimal)
      case a: AttrOneManDate           => updateOne(ns, attr, a.vs, transformDate, handleDate)
      case a: AttrOneManDuration       => updateOne(ns, attr, a.vs, transformDuration, handleDuration)
      case a: AttrOneManInstant        => updateOne(ns, attr, a.vs, transformInstant, handleInstant)
      case a: AttrOneManLocalDate      => updateOne(ns, attr, a.vs, transformLocalDate, handleLocalDate)
      case a: AttrOneManLocalTime      => updateOne(ns, attr, a.vs, transformLocalTime, handleLocalTime)
      case a: AttrOneManLocalDateTime  => updateOne(ns, attr, a.vs, transformLocalDateTime, handleLocalDateTime)
      case a: AttrOneManOffsetTime     => updateOne(ns, attr, a.vs, transformOffsetTime, handleOffsetTime)
      case a: AttrOneManOffsetDateTime => updateOne(ns, attr, a.vs, transformOffsetDateTime, handleOffsetDateTime)
      case a: AttrOneManZonedDateTime  => updateOne(ns, attr, a.vs, transformZonedDateTime, handleZonedDateTime)
      case a: AttrOneManUUID           => updateOne(ns, attr, a.vs, transformUUID, handleUUID)
      case a: AttrOneManURI            => updateOne(ns, attr, a.vs, transformURI, handleURI)
      case a: AttrOneManByte           => updateOne(ns, attr, a.vs, transformByte, handleByte)
      case a: AttrOneManShort          => updateOne(ns, attr, a.vs, transformShort, handleShort)
      case a: AttrOneManChar           => updateOne(ns, attr, a.vs, transformChar, handleChar)
    }
  }

  private def resolveAttrOneTac(a: AttrOneTac): Unit = {
    a match {
      case AttrOneTacID(_, "id", Eq, ids1, _, _, _, _, _, _, _) => handleIds(ids1)

      case a if a.attr == "id" => throw ModelError(
        s"Generic id attribute not allowed in update molecule. Found:\n" + a)

      case a if proxy.uniqueAttrs.contains(a.cleanName) => handleUniqueFilterAttr(a)
      case a                                            => handleFilterAttr(a)
    }
  }

  private def resolveAttrSetMan(a: AttrSetMan): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrSetManID             => updateSetEq(ns, attr, a.vs, transformId, set2arrayId, a.refNs, extsId, value2jsonId)
      case a: AttrSetManString         => updateSetEq(ns, attr, a.vs, transformString, set2arrayString, a.refNs, extsString, value2jsonString)
      case a: AttrSetManInt            => updateSetEq(ns, attr, a.vs, transformInt, set2arrayInt, a.refNs, extsInt, value2jsonInt)
      case a: AttrSetManLong           => updateSetEq(ns, attr, a.vs, transformLong, set2arrayLong, a.refNs, extsLong, value2jsonLong)
      case a: AttrSetManFloat          => updateSetEq(ns, attr, a.vs, transformFloat, set2arrayFloat, a.refNs, extsFloat, value2jsonFloat)
      case a: AttrSetManDouble         => updateSetEq(ns, attr, a.vs, transformDouble, set2arrayDouble, a.refNs, extsDouble, value2jsonDouble)
      case a: AttrSetManBoolean        => updateSetEq(ns, attr, a.vs, transformBoolean, set2arrayBoolean, a.refNs, extsBoolean, value2jsonBoolean)
      case a: AttrSetManBigInt         => updateSetEq(ns, attr, a.vs, transformBigInt, set2arrayBigInt, a.refNs, extsBigInt, value2jsonBigInt)
      case a: AttrSetManBigDecimal     => updateSetEq(ns, attr, a.vs, transformBigDecimal, set2arrayBigDecimal, a.refNs, extsBigDecimal, value2jsonBigDecimal)
      case a: AttrSetManDate           => updateSetEq(ns, attr, a.vs, transformDate, set2arrayDate, a.refNs, extsDate, value2jsonDate)
      case a: AttrSetManDuration       => updateSetEq(ns, attr, a.vs, transformDuration, set2arrayDuration, a.refNs, extsDuration, value2jsonDuration)
      case a: AttrSetManInstant        => updateSetEq(ns, attr, a.vs, transformInstant, set2arrayInstant, a.refNs, extsInstant, value2jsonInstant)
      case a: AttrSetManLocalDate      => updateSetEq(ns, attr, a.vs, transformLocalDate, set2arrayLocalDate, a.refNs, extsLocalDate, value2jsonLocalDate)
      case a: AttrSetManLocalTime      => updateSetEq(ns, attr, a.vs, transformLocalTime, set2arrayLocalTime, a.refNs, extsLocalTime, value2jsonLocalTime)
      case a: AttrSetManLocalDateTime  => updateSetEq(ns, attr, a.vs, transformLocalDateTime, set2arrayLocalDateTime, a.refNs, extsLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSetManOffsetTime     => updateSetEq(ns, attr, a.vs, transformOffsetTime, set2arrayOffsetTime, a.refNs, extsOffsetTime, value2jsonOffsetTime)
      case a: AttrSetManOffsetDateTime => updateSetEq(ns, attr, a.vs, transformOffsetDateTime, set2arrayOffsetDateTime, a.refNs, extsOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSetManZonedDateTime  => updateSetEq(ns, attr, a.vs, transformZonedDateTime, set2arrayZonedDateTime, a.refNs, extsZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSetManUUID           => updateSetEq(ns, attr, a.vs, transformUUID, set2arrayUUID, a.refNs, extsUUID, value2jsonUUID)
      case a: AttrSetManURI            => updateSetEq(ns, attr, a.vs, transformURI, set2arrayURI, a.refNs, extsURI, value2jsonURI)
      case a: AttrSetManByte           => updateSetEq(ns, attr, a.vs, transformByte, set2arrayByte, a.refNs, extsByte, value2jsonByte)
      case a: AttrSetManShort          => updateSetEq(ns, attr, a.vs, transformShort, set2arrayShort, a.refNs, extsShort, value2jsonShort)
      case a: AttrSetManChar           => updateSetEq(ns, attr, a.vs, transformChar, set2arrayChar, a.refNs, extsChar, value2jsonChar)
    }
  }

  private def resolveAttrSetAdd(a: AttrSetMan): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrSetManID             => updateSetAdd(ns, attr, a.vs, transformId, set2arrayId, a.refNs, extsId, value2jsonId)
      case a: AttrSetManString         => updateSetAdd(ns, attr, a.vs, transformString, set2arrayString, a.refNs, extsString, value2jsonString)
      case a: AttrSetManInt            => updateSetAdd(ns, attr, a.vs, transformInt, set2arrayInt, a.refNs, extsInt, value2jsonInt)
      case a: AttrSetManLong           => updateSetAdd(ns, attr, a.vs, transformLong, set2arrayLong, a.refNs, extsLong, value2jsonLong)
      case a: AttrSetManFloat          => updateSetAdd(ns, attr, a.vs, transformFloat, set2arrayFloat, a.refNs, extsFloat, value2jsonFloat)
      case a: AttrSetManDouble         => updateSetAdd(ns, attr, a.vs, transformDouble, set2arrayDouble, a.refNs, extsDouble, value2jsonDouble)
      case a: AttrSetManBoolean        => updateSetAdd(ns, attr, a.vs, transformBoolean, set2arrayBoolean, a.refNs, extsBoolean, value2jsonBoolean)
      case a: AttrSetManBigInt         => updateSetAdd(ns, attr, a.vs, transformBigInt, set2arrayBigInt, a.refNs, extsBigInt, value2jsonBigInt)
      case a: AttrSetManBigDecimal     => updateSetAdd(ns, attr, a.vs, transformBigDecimal, set2arrayBigDecimal, a.refNs, extsBigDecimal, value2jsonBigDecimal)
      case a: AttrSetManDate           => updateSetAdd(ns, attr, a.vs, transformDate, set2arrayDate, a.refNs, extsDate, value2jsonDate)
      case a: AttrSetManDuration       => updateSetAdd(ns, attr, a.vs, transformDuration, set2arrayDuration, a.refNs, extsDuration, value2jsonDuration)
      case a: AttrSetManInstant        => updateSetAdd(ns, attr, a.vs, transformInstant, set2arrayInstant, a.refNs, extsInstant, value2jsonInstant)
      case a: AttrSetManLocalDate      => updateSetAdd(ns, attr, a.vs, transformLocalDate, set2arrayLocalDate, a.refNs, extsLocalDate, value2jsonLocalDate)
      case a: AttrSetManLocalTime      => updateSetAdd(ns, attr, a.vs, transformLocalTime, set2arrayLocalTime, a.refNs, extsLocalTime, value2jsonLocalTime)
      case a: AttrSetManLocalDateTime  => updateSetAdd(ns, attr, a.vs, transformLocalDateTime, set2arrayLocalDateTime, a.refNs, extsLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSetManOffsetTime     => updateSetAdd(ns, attr, a.vs, transformOffsetTime, set2arrayOffsetTime, a.refNs, extsOffsetTime, value2jsonOffsetTime)
      case a: AttrSetManOffsetDateTime => updateSetAdd(ns, attr, a.vs, transformOffsetDateTime, set2arrayOffsetDateTime, a.refNs, extsOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSetManZonedDateTime  => updateSetAdd(ns, attr, a.vs, transformZonedDateTime, set2arrayZonedDateTime, a.refNs, extsZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSetManUUID           => updateSetAdd(ns, attr, a.vs, transformUUID, set2arrayUUID, a.refNs, extsUUID, value2jsonUUID)
      case a: AttrSetManURI            => updateSetAdd(ns, attr, a.vs, transformURI, set2arrayURI, a.refNs, extsURI, value2jsonURI)
      case a: AttrSetManByte           => updateSetAdd(ns, attr, a.vs, transformByte, set2arrayByte, a.refNs, extsByte, value2jsonByte)
      case a: AttrSetManShort          => updateSetAdd(ns, attr, a.vs, transformShort, set2arrayShort, a.refNs, extsShort, value2jsonShort)
      case a: AttrSetManChar           => updateSetAdd(ns, attr, a.vs, transformChar, set2arrayChar, a.refNs, extsChar, value2jsonChar)
    }
  }

  private def resolveAttrSetSwap(a: AttrSetMan): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrSetManID             => updateSetSwap(ns, attr, a.vs, transformId, handleId, a.refNs, extsId, value2jsonId, one2jsonId)
      case a: AttrSetManString         => updateSetSwap(ns, attr, a.vs, transformString, handleString, a.refNs, extsString, value2jsonString, one2jsonString)
      case a: AttrSetManInt            => updateSetSwap(ns, attr, a.vs, transformInt, handleInt, a.refNs, extsInt, value2jsonInt, one2jsonInt)
      case a: AttrSetManLong           => updateSetSwap(ns, attr, a.vs, transformLong, handleLong, a.refNs, extsLong, value2jsonLong, one2jsonLong)
      case a: AttrSetManFloat          => updateSetSwap(ns, attr, a.vs, transformFloat, handleFloat, a.refNs, extsFloat, value2jsonFloat, one2jsonFloat)
      case a: AttrSetManDouble         => updateSetSwap(ns, attr, a.vs, transformDouble, handleDouble, a.refNs, extsDouble, value2jsonDouble, one2jsonDouble)
      case a: AttrSetManBoolean        => updateSetSwap(ns, attr, a.vs, transformBoolean, handleBoolean, a.refNs, extsBoolean, value2jsonBoolean, one2jsonBoolean)
      case a: AttrSetManBigInt         => updateSetSwap(ns, attr, a.vs, transformBigInt, handleBigInt, a.refNs, extsBigInt, value2jsonBigInt, one2jsonBigInt)
      case a: AttrSetManBigDecimal     => updateSetSwap(ns, attr, a.vs, transformBigDecimal, handleBigDecimal, a.refNs, extsBigDecimal, value2jsonBigDecimal, one2jsonBigDecimal)
      case a: AttrSetManDate           => updateSetSwap(ns, attr, a.vs, transformDate, handleDate, a.refNs, extsDate, value2jsonDate, one2jsonDate)
      case a: AttrSetManDuration       => updateSetSwap(ns, attr, a.vs, transformDuration, handleDuration, a.refNs, extsDuration, value2jsonDuration, one2jsonDuration)
      case a: AttrSetManInstant        => updateSetSwap(ns, attr, a.vs, transformInstant, handleInstant, a.refNs, extsInstant, value2jsonInstant, one2jsonInstant)
      case a: AttrSetManLocalDate      => updateSetSwap(ns, attr, a.vs, transformLocalDate, handleLocalDate, a.refNs, extsLocalDate, value2jsonLocalDate, one2jsonLocalDate)
      case a: AttrSetManLocalTime      => updateSetSwap(ns, attr, a.vs, transformLocalTime, handleLocalTime, a.refNs, extsLocalTime, value2jsonLocalTime, one2jsonLocalTime)
      case a: AttrSetManLocalDateTime  => updateSetSwap(ns, attr, a.vs, transformLocalDateTime, handleLocalDateTime, a.refNs, extsLocalDateTime, value2jsonLocalDateTime, one2jsonLocalDateTime)
      case a: AttrSetManOffsetTime     => updateSetSwap(ns, attr, a.vs, transformOffsetTime, handleOffsetTime, a.refNs, extsOffsetTime, value2jsonOffsetTime, one2jsonOffsetTime)
      case a: AttrSetManOffsetDateTime => updateSetSwap(ns, attr, a.vs, transformOffsetDateTime, handleOffsetDateTime, a.refNs, extsOffsetDateTime, value2jsonOffsetDateTime, one2jsonOffsetDateTime)
      case a: AttrSetManZonedDateTime  => updateSetSwap(ns, attr, a.vs, transformZonedDateTime, handleZonedDateTime, a.refNs, extsZonedDateTime, value2jsonZonedDateTime, one2jsonZonedDateTime)
      case a: AttrSetManUUID           => updateSetSwap(ns, attr, a.vs, transformUUID, handleUUID, a.refNs, extsUUID, value2jsonUUID, one2jsonUUID)
      case a: AttrSetManURI            => updateSetSwap(ns, attr, a.vs, transformURI, handleURI, a.refNs, extsURI, value2jsonURI, one2jsonURI)
      case a: AttrSetManByte           => updateSetSwap(ns, attr, a.vs, transformByte, handleByte, a.refNs, extsByte, value2jsonByte, one2jsonByte)
      case a: AttrSetManShort          => updateSetSwap(ns, attr, a.vs, transformShort, handleShort, a.refNs, extsShort, value2jsonShort, one2jsonShort)
      case a: AttrSetManChar           => updateSetSwap(ns, attr, a.vs, transformChar, handleChar, a.refNs, extsChar, value2jsonChar, one2jsonChar)
    }
  }

  private def resolveAttrSetRemove(a: AttrSetMan): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrSetManID             => updateSetRemove(ns, attr, a.vs.head, transformId, handleId, a.refNs, extsId, one2jsonId)
      case a: AttrSetManString         => updateSetRemove(ns, attr, a.vs.head, transformString, handleString, a.refNs, extsString, one2jsonString)
      case a: AttrSetManInt            => updateSetRemove(ns, attr, a.vs.head, transformInt, handleInt, a.refNs, extsInt, one2jsonInt)
      case a: AttrSetManLong           => updateSetRemove(ns, attr, a.vs.head, transformLong, handleLong, a.refNs, extsLong, one2jsonLong)
      case a: AttrSetManFloat          => updateSetRemove(ns, attr, a.vs.head, transformFloat, handleFloat, a.refNs, extsFloat, one2jsonFloat)
      case a: AttrSetManDouble         => updateSetRemove(ns, attr, a.vs.head, transformDouble, handleDouble, a.refNs, extsDouble, one2jsonDouble)
      case a: AttrSetManBoolean        => updateSetRemove(ns, attr, a.vs.head, transformBoolean, handleBoolean, a.refNs, extsBoolean, one2jsonBoolean)
      case a: AttrSetManBigInt         => updateSetRemove(ns, attr, a.vs.head, transformBigInt, handleBigInt, a.refNs, extsBigInt, one2jsonBigInt)
      case a: AttrSetManBigDecimal     => updateSetRemove(ns, attr, a.vs.head, transformBigDecimal, handleBigDecimal, a.refNs, extsBigDecimal, one2jsonBigDecimal)
      case a: AttrSetManDate           => updateSetRemove(ns, attr, a.vs.head, transformDate, handleDate, a.refNs, extsDate, one2jsonDate)
      case a: AttrSetManDuration       => updateSetRemove(ns, attr, a.vs.head, transformDuration, handleDuration, a.refNs, extsDuration, one2jsonDuration)
      case a: AttrSetManInstant        => updateSetRemove(ns, attr, a.vs.head, transformInstant, handleInstant, a.refNs, extsInstant, one2jsonInstant)
      case a: AttrSetManLocalDate      => updateSetRemove(ns, attr, a.vs.head, transformLocalDate, handleLocalDate, a.refNs, extsLocalDate, one2jsonLocalDate)
      case a: AttrSetManLocalTime      => updateSetRemove(ns, attr, a.vs.head, transformLocalTime, handleLocalTime, a.refNs, extsLocalTime, one2jsonLocalTime)
      case a: AttrSetManLocalDateTime  => updateSetRemove(ns, attr, a.vs.head, transformLocalDateTime, handleLocalDateTime, a.refNs, extsLocalDateTime, one2jsonLocalDateTime)
      case a: AttrSetManOffsetTime     => updateSetRemove(ns, attr, a.vs.head, transformOffsetTime, handleOffsetTime, a.refNs, extsOffsetTime, one2jsonOffsetTime)
      case a: AttrSetManOffsetDateTime => updateSetRemove(ns, attr, a.vs.head, transformOffsetDateTime, handleOffsetDateTime, a.refNs, extsOffsetDateTime, one2jsonOffsetDateTime)
      case a: AttrSetManZonedDateTime  => updateSetRemove(ns, attr, a.vs.head, transformZonedDateTime, handleZonedDateTime, a.refNs, extsZonedDateTime, one2jsonZonedDateTime)
      case a: AttrSetManUUID           => updateSetRemove(ns, attr, a.vs.head, transformUUID, handleUUID, a.refNs, extsUUID, one2jsonUUID)
      case a: AttrSetManURI            => updateSetRemove(ns, attr, a.vs.head, transformURI, handleURI, a.refNs, extsURI, one2jsonURI)
      case a: AttrSetManByte           => updateSetRemove(ns, attr, a.vs.head, transformByte, handleByte, a.refNs, extsByte, one2jsonByte)
      case a: AttrSetManShort          => updateSetRemove(ns, attr, a.vs.head, transformShort, handleShort, a.refNs, extsShort, one2jsonShort)
      case a: AttrSetManChar           => updateSetRemove(ns, attr, a.vs.head, transformChar, handleChar, a.refNs, extsChar, one2jsonChar)
    }
  }
}