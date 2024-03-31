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
            case a: AttrOne => a match {
              case a: AttrOneTac => resolveAttrOneTac(a); resolve(tail)
              case a: AttrOneMan => resolveAttrOneMan(a); resolve(tail)
              case _: AttrOneOpt => throw ModelError(s"Can't $update optional values. Found:\n" + a)
            }

            case a: AttrSet => a match {
              case a: AttrSetMan => a.op match {
                case Eq | NoValue => resolveAttrSetMan(a); resolve(tail)
                case Add          => resolveAttrSetAdd(a); resolve(tail)
                case Remove       => resolveAttrSetRemove(a); resolve(tail)
                case _            => throw ModelError(s"Unexpected $update operation for card-many attribute. Found:\n" + a)
              }
              case _: AttrSetTac => throw ModelError("Can only lookup entity with card-one attribute value. Found:\n" + a)
              case _: AttrSetOpt => throw ModelError(s"Can't $update optional values. Found:\n" + a)
            }

            case a: AttrSeq => a match {
              case a: AttrSeqMan => a.op match {
                case Eq | NoValue => resolveAttrSeqMan(a); resolve(tail)
                case Add          => resolveAttrSeqAdd(a); resolve(tail)
                case Remove       => resolveAttrSeqRemove(a); resolve(tail)
                case _            => throw ModelError(s"Unexpected $update operation for card-many attribute. Found:\n" + a)
              }
              case _: AttrSeqTac => throw ModelError("Can only lookup entity with card-one attribute value. Found:\n" + a)
              case _: AttrSeqOpt => throw ModelError(s"Can't $update optional values. Found:\n" + a)
            }

            case a: AttrMap => a match {
              case a: AttrMapMan => a.op match {
                case Eq | NoValue => resolveAttrMapMan(a); resolve(tail)
                case Add          => resolveAttrMapAdd(a); resolve(tail)
                case Remove       => resolveAttrMapRemove(a); resolve(tail)
                case _            => throw ModelError(s"Unexpected $update operation for card-many attribute. Found:\n" + a)
              }
              case _: AttrMapTac => throw ModelError("Can only lookup entity with card-one attribute value. Found:\n" + a)
              case a: AttrMapOpt => throw ModelError(s"Can't $update optional values. Found:\n" + a.toString)
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

      case a if a.op != Eq && a.op != NoValue => throw ModelError(
        s"Can't $update attributes without an applied value. Found:\n" + a)

      case a: AttrOneManID             => updateOne(ns, attr, a.vs, a.owner, transformID)
      case a: AttrOneManString         => updateOne(ns, attr, a.vs, a.owner, transformString)
      case a: AttrOneManInt            => updateOne(ns, attr, a.vs, a.owner, transformInt)
      case a: AttrOneManLong           => updateOne(ns, attr, a.vs, a.owner, transformLong)
      case a: AttrOneManFloat          => updateOne(ns, attr, a.vs, a.owner, transformFloat)
      case a: AttrOneManDouble         => updateOne(ns, attr, a.vs, a.owner, transformDouble)
      case a: AttrOneManBoolean        => updateOne(ns, attr, a.vs, a.owner, transformBoolean)
      case a: AttrOneManBigInt         => updateOne(ns, attr, a.vs, a.owner, transformBigInt)
      case a: AttrOneManBigDecimal     => updateOne(ns, attr, a.vs, a.owner, transformBigDecimal)
      case a: AttrOneManDate           => updateOne(ns, attr, a.vs, a.owner, transformDate)
      case a: AttrOneManDuration       => updateOne(ns, attr, a.vs, a.owner, transformDuration)
      case a: AttrOneManInstant        => updateOne(ns, attr, a.vs, a.owner, transformInstant)
      case a: AttrOneManLocalDate      => updateOne(ns, attr, a.vs, a.owner, transformLocalDate)
      case a: AttrOneManLocalTime      => updateOne(ns, attr, a.vs, a.owner, transformLocalTime)
      case a: AttrOneManLocalDateTime  => updateOne(ns, attr, a.vs, a.owner, transformLocalDateTime)
      case a: AttrOneManOffsetTime     => updateOne(ns, attr, a.vs, a.owner, transformOffsetTime)
      case a: AttrOneManOffsetDateTime => updateOne(ns, attr, a.vs, a.owner, transformOffsetDateTime)
      case a: AttrOneManZonedDateTime  => updateOne(ns, attr, a.vs, a.owner, transformZonedDateTime)
      case a: AttrOneManUUID           => updateOne(ns, attr, a.vs, a.owner, transformUUID)
      case a: AttrOneManURI            => updateOne(ns, attr, a.vs, a.owner, transformURI)
      case a: AttrOneManByte           => updateOne(ns, attr, a.vs, a.owner, transformByte)
      case a: AttrOneManShort          => updateOne(ns, attr, a.vs, a.owner, transformShort)
      case a: AttrOneManChar           => updateOne(ns, attr, a.vs, a.owner, transformChar)
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

  private def resolveAttrSetRemove(a: AttrSetMan): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrSetManID             => updateSetRemove(ns, attr, a.vs, a.refNs, a.owner, transformID, extsID, one2jsonID)
      case a: AttrSetManString         => updateSetRemove(ns, attr, a.vs, a.refNs, a.owner, transformString, extsString, one2jsonString)
      case a: AttrSetManInt            => updateSetRemove(ns, attr, a.vs, a.refNs, a.owner, transformInt, extsInt, one2jsonInt)
      case a: AttrSetManLong           => updateSetRemove(ns, attr, a.vs, a.refNs, a.owner, transformLong, extsLong, one2jsonLong)
      case a: AttrSetManFloat          => updateSetRemove(ns, attr, a.vs, a.refNs, a.owner, transformFloat, extsFloat, one2jsonFloat)
      case a: AttrSetManDouble         => updateSetRemove(ns, attr, a.vs, a.refNs, a.owner, transformDouble, extsDouble, one2jsonDouble)
      case a: AttrSetManBoolean        => updateSetRemove(ns, attr, a.vs, a.refNs, a.owner, transformBoolean, extsBoolean, one2jsonBoolean)
      case a: AttrSetManBigInt         => updateSetRemove(ns, attr, a.vs, a.refNs, a.owner, transformBigInt, extsBigInt, one2jsonBigInt)
      case a: AttrSetManBigDecimal     => updateSetRemove(ns, attr, a.vs, a.refNs, a.owner, transformBigDecimal, extsBigDecimal, one2jsonBigDecimal)
      case a: AttrSetManDate           => updateSetRemove(ns, attr, a.vs, a.refNs, a.owner, transformDate, extsDate, one2jsonDate)
      case a: AttrSetManDuration       => updateSetRemove(ns, attr, a.vs, a.refNs, a.owner, transformDuration, extsDuration, one2jsonDuration)
      case a: AttrSetManInstant        => updateSetRemove(ns, attr, a.vs, a.refNs, a.owner, transformInstant, extsInstant, one2jsonInstant)
      case a: AttrSetManLocalDate      => updateSetRemove(ns, attr, a.vs, a.refNs, a.owner, transformLocalDate, extsLocalDate, one2jsonLocalDate)
      case a: AttrSetManLocalTime      => updateSetRemove(ns, attr, a.vs, a.refNs, a.owner, transformLocalTime, extsLocalTime, one2jsonLocalTime)
      case a: AttrSetManLocalDateTime  => updateSetRemove(ns, attr, a.vs, a.refNs, a.owner, transformLocalDateTime, extsLocalDateTime, one2jsonLocalDateTime)
      case a: AttrSetManOffsetTime     => updateSetRemove(ns, attr, a.vs, a.refNs, a.owner, transformOffsetTime, extsOffsetTime, one2jsonOffsetTime)
      case a: AttrSetManOffsetDateTime => updateSetRemove(ns, attr, a.vs, a.refNs, a.owner, transformOffsetDateTime, extsOffsetDateTime, one2jsonOffsetDateTime)
      case a: AttrSetManZonedDateTime  => updateSetRemove(ns, attr, a.vs, a.refNs, a.owner, transformZonedDateTime, extsZonedDateTime, one2jsonZonedDateTime)
      case a: AttrSetManUUID           => updateSetRemove(ns, attr, a.vs, a.refNs, a.owner, transformUUID, extsUUID, one2jsonUUID)
      case a: AttrSetManURI            => updateSetRemove(ns, attr, a.vs, a.refNs, a.owner, transformURI, extsURI, one2jsonURI)
      case a: AttrSetManByte           => updateSetRemove(ns, attr, a.vs, a.refNs, a.owner, transformByte, extsByte, one2jsonByte)
      case a: AttrSetManShort          => updateSetRemove(ns, attr, a.vs, a.refNs, a.owner, transformShort, extsShort, one2jsonShort)
      case a: AttrSetManChar           => updateSetRemove(ns, attr, a.vs, a.refNs, a.owner, transformChar, extsChar, one2jsonChar)
    }
  }

  private def resolveAttrSeqMan(a: AttrSeqMan): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrSeqManID             => updateSeqEq(ns, attr, a.vs, a.refNs, a.owner, transformID, seq2arrayID, extsID, value2jsonID)
      case a: AttrSeqManString         => updateSeqEq(ns, attr, a.vs, a.refNs, a.owner, transformString, seq2arrayString, extsString, value2jsonString)
      case a: AttrSeqManInt            => updateSeqEq(ns, attr, a.vs, a.refNs, a.owner, transformInt, seq2arrayInt, extsInt, value2jsonInt)
      case a: AttrSeqManLong           => updateSeqEq(ns, attr, a.vs, a.refNs, a.owner, transformLong, seq2arrayLong, extsLong, value2jsonLong)
      case a: AttrSeqManFloat          => updateSeqEq(ns, attr, a.vs, a.refNs, a.owner, transformFloat, seq2arrayFloat, extsFloat, value2jsonFloat)
      case a: AttrSeqManDouble         => updateSeqEq(ns, attr, a.vs, a.refNs, a.owner, transformDouble, seq2arrayDouble, extsDouble, value2jsonDouble)
      case a: AttrSeqManBoolean        => updateSeqEq(ns, attr, a.vs, a.refNs, a.owner, transformBoolean, seq2arrayBoolean, extsBoolean, value2jsonBoolean)
      case a: AttrSeqManBigInt         => updateSeqEq(ns, attr, a.vs, a.refNs, a.owner, transformBigInt, seq2arrayBigInt, extsBigInt, value2jsonBigInt)
      case a: AttrSeqManBigDecimal     => updateSeqEq(ns, attr, a.vs, a.refNs, a.owner, transformBigDecimal, seq2arrayBigDecimal, extsBigDecimal, value2jsonBigDecimal)
      case a: AttrSeqManDate           => updateSeqEq(ns, attr, a.vs, a.refNs, a.owner, transformDate, seq2arrayDate, extsDate, value2jsonDate)
      case a: AttrSeqManDuration       => updateSeqEq(ns, attr, a.vs, a.refNs, a.owner, transformDuration, seq2arrayDuration, extsDuration, value2jsonDuration)
      case a: AttrSeqManInstant        => updateSeqEq(ns, attr, a.vs, a.refNs, a.owner, transformInstant, seq2arrayInstant, extsInstant, value2jsonInstant)
      case a: AttrSeqManLocalDate      => updateSeqEq(ns, attr, a.vs, a.refNs, a.owner, transformLocalDate, seq2arrayLocalDate, extsLocalDate, value2jsonLocalDate)
      case a: AttrSeqManLocalTime      => updateSeqEq(ns, attr, a.vs, a.refNs, a.owner, transformLocalTime, seq2arrayLocalTime, extsLocalTime, value2jsonLocalTime)
      case a: AttrSeqManLocalDateTime  => updateSeqEq(ns, attr, a.vs, a.refNs, a.owner, transformLocalDateTime, seq2arrayLocalDateTime, extsLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSeqManOffsetTime     => updateSeqEq(ns, attr, a.vs, a.refNs, a.owner, transformOffsetTime, seq2arrayOffsetTime, extsOffsetTime, value2jsonOffsetTime)
      case a: AttrSeqManOffsetDateTime => updateSeqEq(ns, attr, a.vs, a.refNs, a.owner, transformOffsetDateTime, seq2arrayOffsetDateTime, extsOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSeqManZonedDateTime  => updateSeqEq(ns, attr, a.vs, a.refNs, a.owner, transformZonedDateTime, seq2arrayZonedDateTime, extsZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSeqManUUID           => updateSeqEq(ns, attr, a.vs, a.refNs, a.owner, transformUUID, seq2arrayUUID, extsUUID, value2jsonUUID)
      case a: AttrSeqManURI            => updateSeqEq(ns, attr, a.vs, a.refNs, a.owner, transformURI, seq2arrayURI, extsURI, value2jsonURI)
      case a: AttrSeqManByte           => updateByteArray(ns, attr, a.vs)
      case a: AttrSeqManShort          => updateSeqEq(ns, attr, a.vs, a.refNs, a.owner, transformShort, seq2arrayShort, extsShort, value2jsonShort)
      case a: AttrSeqManChar           => updateSeqEq(ns, attr, a.vs, a.refNs, a.owner, transformChar, seq2arrayChar, extsChar, value2jsonChar)
    }
  }

  private def resolveAttrSeqAdd(a: AttrSeqMan): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrSeqManID             => updateSeqAdd(ns, attr, a.vs, a.refNs, a.owner, transformID, seq2arrayID, extsID, value2jsonID)
      case a: AttrSeqManString         => updateSeqAdd(ns, attr, a.vs, a.refNs, a.owner, transformString, seq2arrayString, extsString, value2jsonString)
      case a: AttrSeqManInt            => updateSeqAdd(ns, attr, a.vs, a.refNs, a.owner, transformInt, seq2arrayInt, extsInt, value2jsonInt)
      case a: AttrSeqManLong           => updateSeqAdd(ns, attr, a.vs, a.refNs, a.owner, transformLong, seq2arrayLong, extsLong, value2jsonLong)
      case a: AttrSeqManFloat          => updateSeqAdd(ns, attr, a.vs, a.refNs, a.owner, transformFloat, seq2arrayFloat, extsFloat, value2jsonFloat)
      case a: AttrSeqManDouble         => updateSeqAdd(ns, attr, a.vs, a.refNs, a.owner, transformDouble, seq2arrayDouble, extsDouble, value2jsonDouble)
      case a: AttrSeqManBoolean        => updateSeqAdd(ns, attr, a.vs, a.refNs, a.owner, transformBoolean, seq2arrayBoolean, extsBoolean, value2jsonBoolean)
      case a: AttrSeqManBigInt         => updateSeqAdd(ns, attr, a.vs, a.refNs, a.owner, transformBigInt, seq2arrayBigInt, extsBigInt, value2jsonBigInt)
      case a: AttrSeqManBigDecimal     => updateSeqAdd(ns, attr, a.vs, a.refNs, a.owner, transformBigDecimal, seq2arrayBigDecimal, extsBigDecimal, value2jsonBigDecimal)
      case a: AttrSeqManDate           => updateSeqAdd(ns, attr, a.vs, a.refNs, a.owner, transformDate, seq2arrayDate, extsDate, value2jsonDate)
      case a: AttrSeqManDuration       => updateSeqAdd(ns, attr, a.vs, a.refNs, a.owner, transformDuration, seq2arrayDuration, extsDuration, value2jsonDuration)
      case a: AttrSeqManInstant        => updateSeqAdd(ns, attr, a.vs, a.refNs, a.owner, transformInstant, seq2arrayInstant, extsInstant, value2jsonInstant)
      case a: AttrSeqManLocalDate      => updateSeqAdd(ns, attr, a.vs, a.refNs, a.owner, transformLocalDate, seq2arrayLocalDate, extsLocalDate, value2jsonLocalDate)
      case a: AttrSeqManLocalTime      => updateSeqAdd(ns, attr, a.vs, a.refNs, a.owner, transformLocalTime, seq2arrayLocalTime, extsLocalTime, value2jsonLocalTime)
      case a: AttrSeqManLocalDateTime  => updateSeqAdd(ns, attr, a.vs, a.refNs, a.owner, transformLocalDateTime, seq2arrayLocalDateTime, extsLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSeqManOffsetTime     => updateSeqAdd(ns, attr, a.vs, a.refNs, a.owner, transformOffsetTime, seq2arrayOffsetTime, extsOffsetTime, value2jsonOffsetTime)
      case a: AttrSeqManOffsetDateTime => updateSeqAdd(ns, attr, a.vs, a.refNs, a.owner, transformOffsetDateTime, seq2arrayOffsetDateTime, extsOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSeqManZonedDateTime  => updateSeqAdd(ns, attr, a.vs, a.refNs, a.owner, transformZonedDateTime, seq2arrayZonedDateTime, extsZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSeqManUUID           => updateSeqAdd(ns, attr, a.vs, a.refNs, a.owner, transformUUID, seq2arrayUUID, extsUUID, value2jsonUUID)
      case a: AttrSeqManURI            => updateSeqAdd(ns, attr, a.vs, a.refNs, a.owner, transformURI, seq2arrayURI, extsURI, value2jsonURI)
      case a: AttrSeqManByte           => throw ModelError(s"Operations on byte arrays (${a.ns}.${a.attr}) not allowed.")
      case a: AttrSeqManShort          => updateSeqAdd(ns, attr, a.vs, a.refNs, a.owner, transformShort, seq2arrayShort, extsShort, value2jsonShort)
      case a: AttrSeqManChar           => updateSeqAdd(ns, attr, a.vs, a.refNs, a.owner, transformChar, seq2arrayChar, extsChar, value2jsonChar)
    }
  }

  private def resolveAttrSeqRemove(a: AttrSeqMan): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrSeqManID             => updateSeqRemove(ns, attr, a.vs, a.refNs, a.owner, transformID, extsID, one2jsonID)
      case a: AttrSeqManString         => updateSeqRemove(ns, attr, a.vs, a.refNs, a.owner, transformString, extsString, one2jsonString)
      case a: AttrSeqManInt            => updateSeqRemove(ns, attr, a.vs, a.refNs, a.owner, transformInt, extsInt, one2jsonInt)
      case a: AttrSeqManLong           => updateSeqRemove(ns, attr, a.vs, a.refNs, a.owner, transformLong, extsLong, one2jsonLong)
      case a: AttrSeqManFloat          => updateSeqRemove(ns, attr, a.vs, a.refNs, a.owner, transformFloat, extsFloat, one2jsonFloat)
      case a: AttrSeqManDouble         => updateSeqRemove(ns, attr, a.vs, a.refNs, a.owner, transformDouble, extsDouble, one2jsonDouble)
      case a: AttrSeqManBoolean        => updateSeqRemove(ns, attr, a.vs, a.refNs, a.owner, transformBoolean, extsBoolean, one2jsonBoolean)
      case a: AttrSeqManBigInt         => updateSeqRemove(ns, attr, a.vs, a.refNs, a.owner, transformBigInt, extsBigInt, one2jsonBigInt)
      case a: AttrSeqManBigDecimal     => updateSeqRemove(ns, attr, a.vs, a.refNs, a.owner, transformBigDecimal, extsBigDecimal, one2jsonBigDecimal)
      case a: AttrSeqManDate           => updateSeqRemove(ns, attr, a.vs, a.refNs, a.owner, transformDate, extsDate, one2jsonDate)
      case a: AttrSeqManDuration       => updateSeqRemove(ns, attr, a.vs, a.refNs, a.owner, transformDuration, extsDuration, one2jsonDuration)
      case a: AttrSeqManInstant        => updateSeqRemove(ns, attr, a.vs, a.refNs, a.owner, transformInstant, extsInstant, one2jsonInstant)
      case a: AttrSeqManLocalDate      => updateSeqRemove(ns, attr, a.vs, a.refNs, a.owner, transformLocalDate, extsLocalDate, one2jsonLocalDate)
      case a: AttrSeqManLocalTime      => updateSeqRemove(ns, attr, a.vs, a.refNs, a.owner, transformLocalTime, extsLocalTime, one2jsonLocalTime)
      case a: AttrSeqManLocalDateTime  => updateSeqRemove(ns, attr, a.vs, a.refNs, a.owner, transformLocalDateTime, extsLocalDateTime, one2jsonLocalDateTime)
      case a: AttrSeqManOffsetTime     => updateSeqRemove(ns, attr, a.vs, a.refNs, a.owner, transformOffsetTime, extsOffsetTime, one2jsonOffsetTime)
      case a: AttrSeqManOffsetDateTime => updateSeqRemove(ns, attr, a.vs, a.refNs, a.owner, transformOffsetDateTime, extsOffsetDateTime, one2jsonOffsetDateTime)
      case a: AttrSeqManZonedDateTime  => updateSeqRemove(ns, attr, a.vs, a.refNs, a.owner, transformZonedDateTime, extsZonedDateTime, one2jsonZonedDateTime)
      case a: AttrSeqManUUID           => updateSeqRemove(ns, attr, a.vs, a.refNs, a.owner, transformUUID, extsUUID, one2jsonUUID)
      case a: AttrSeqManURI            => updateSeqRemove(ns, attr, a.vs, a.refNs, a.owner, transformURI, extsURI, one2jsonURI)
      case a: AttrSeqManByte           => throw ModelError(s"Operations on byte arrays (${a.ns}.${a.attr}) not allowed.")
      case a: AttrSeqManShort          => updateSeqRemove(ns, attr, a.vs, a.refNs, a.owner, transformShort, extsShort, one2jsonShort)
      case a: AttrSeqManChar           => updateSeqRemove(ns, attr, a.vs, a.refNs, a.owner, transformChar, extsChar, one2jsonChar)
    }
  }


  private def resolveAttrMapMan(a: AttrMapMan): Unit = {
    val (ns, attr, noValue) = (a.ns, a.attr, a.op == NoValue)
    a match {
      case a: AttrMapManID             => updateMapEq(ns, attr, a.vs, noValue, a.refNs, a.owner, transformID) //, set2arrayID, extsID, value2jsonID)
      case a: AttrMapManString         => updateMapEq(ns, attr, a.vs, noValue, a.refNs, a.owner, transformString) //, set2arrayString, extsString, value2jsonString)
      case a: AttrMapManInt            => updateMapEq(ns, attr, a.vs, noValue, a.refNs, a.owner, transformInt) //, set2arrayInt, extsInt, value2jsonInt)
      case a: AttrMapManLong           => updateMapEq(ns, attr, a.vs, noValue, a.refNs, a.owner, transformLong) //, set2arrayLong, extsLong, value2jsonLong)
      case a: AttrMapManFloat          => updateMapEq(ns, attr, a.vs, noValue, a.refNs, a.owner, transformFloat) //, set2arrayFloat, extsFloat, value2jsonFloat)
      case a: AttrMapManDouble         => updateMapEq(ns, attr, a.vs, noValue, a.refNs, a.owner, transformDouble) //, set2arrayDouble, extsDouble, value2jsonDouble)
      case a: AttrMapManBoolean        => updateMapEq(ns, attr, a.vs, noValue, a.refNs, a.owner, transformBoolean) //, set2arrayBoolean, extsBoolean, value2jsonBoolean)
      case a: AttrMapManBigInt         => updateMapEq(ns, attr, a.vs, noValue, a.refNs, a.owner, transformBigInt) //, set2arrayBigInt, extsBigInt, value2jsonBigInt)
      case a: AttrMapManBigDecimal     => updateMapEq(ns, attr, a.vs, noValue, a.refNs, a.owner, transformBigDecimal) //, set2arrayBigDecimal, extsBigDecimal, value2jsonBigDecimal)
      case a: AttrMapManDate           => updateMapEq(ns, attr, a.vs, noValue, a.refNs, a.owner, transformDate) //, set2arrayDate, extsDate, value2jsonDate)
      case a: AttrMapManDuration       => updateMapEq(ns, attr, a.vs, noValue, a.refNs, a.owner, transformDuration) //, set2arrayDuration, extsDuration, value2jsonDuration)
      case a: AttrMapManInstant        => updateMapEq(ns, attr, a.vs, noValue, a.refNs, a.owner, transformInstant) //, set2arrayInstant, extsInstant, value2jsonInstant)
      case a: AttrMapManLocalDate      => updateMapEq(ns, attr, a.vs, noValue, a.refNs, a.owner, transformLocalDate) //, set2arrayLocalDate, extsLocalDate, value2jsonLocalDate)
      case a: AttrMapManLocalTime      => updateMapEq(ns, attr, a.vs, noValue, a.refNs, a.owner, transformLocalTime) //, set2arrayLocalTime, extsLocalTime, value2jsonLocalTime)
      case a: AttrMapManLocalDateTime  => updateMapEq(ns, attr, a.vs, noValue, a.refNs, a.owner, transformLocalDateTime) //, set2arrayLocalDateTime, extsLocalDateTime, value2jsonLocalDateTime)
      case a: AttrMapManOffsetTime     => updateMapEq(ns, attr, a.vs, noValue, a.refNs, a.owner, transformOffsetTime) //, set2arrayOffsetTime, extsOffsetTime, value2jsonOffsetTime)
      case a: AttrMapManOffsetDateTime => updateMapEq(ns, attr, a.vs, noValue, a.refNs, a.owner, transformOffsetDateTime) //, set2arrayOffsetDateTime, extsOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrMapManZonedDateTime  => updateMapEq(ns, attr, a.vs, noValue, a.refNs, a.owner, transformZonedDateTime) //, set2arrayZonedDateTime, extsZonedDateTime, value2jsonZonedDateTime)
      case a: AttrMapManUUID           => updateMapEq(ns, attr, a.vs, noValue, a.refNs, a.owner, transformUUID) //, set2arrayUUID, extsUUID, value2jsonUUID)
      case a: AttrMapManURI            => updateMapEq(ns, attr, a.vs, noValue, a.refNs, a.owner, transformURI) //, set2arrayURI, extsURI, value2jsonURI)
      case a: AttrMapManByte           => updateMapEq(ns, attr, a.vs, noValue, a.refNs, a.owner, transformByte) //, set2arrayURI, extsURI, value2jsonURI)
      case a: AttrMapManShort          => updateMapEq(ns, attr, a.vs, noValue, a.refNs, a.owner, transformShort) //, set2arrayShort, extsShort, value2jsonShort)
      case a: AttrMapManChar           => updateMapEq(ns, attr, a.vs, noValue, a.refNs, a.owner, transformChar) //, set2arrayChar, extsChar, value2jsonChar)
    }
  }

  private def resolveAttrMapAdd(a: AttrMapMan): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrMapManID             => updateMapAdd(ns, attr, a.vs, a.refNs, a.owner, transformID) //, set2arrayID, extsID, value2jsonID)
      case a: AttrMapManString         => updateMapAdd(ns, attr, a.vs, a.refNs, a.owner, transformString) //, set2arrayString, extsString, value2jsonString)
      case a: AttrMapManInt            => updateMapAdd(ns, attr, a.vs, a.refNs, a.owner, transformInt) //, set2arrayInt, extsInt, value2jsonInt)
      case a: AttrMapManLong           => updateMapAdd(ns, attr, a.vs, a.refNs, a.owner, transformLong) //, set2arrayLong, extsLong, value2jsonLong)
      case a: AttrMapManFloat          => updateMapAdd(ns, attr, a.vs, a.refNs, a.owner, transformFloat) //, set2arrayFloat, extsFloat, value2jsonFloat)
      case a: AttrMapManDouble         => updateMapAdd(ns, attr, a.vs, a.refNs, a.owner, transformDouble) //, set2arrayDouble, extsDouble, value2jsonDouble)
      case a: AttrMapManBoolean        => updateMapAdd(ns, attr, a.vs, a.refNs, a.owner, transformBoolean) //, set2arrayBoolean, extsBoolean, value2jsonBoolean)
      case a: AttrMapManBigInt         => updateMapAdd(ns, attr, a.vs, a.refNs, a.owner, transformBigInt) //, set2arrayBigInt, extsBigInt, value2jsonBigInt)
      case a: AttrMapManBigDecimal     => updateMapAdd(ns, attr, a.vs, a.refNs, a.owner, transformBigDecimal) //, set2arrayBigDecimal, extsBigDecimal, value2jsonBigDecimal)
      case a: AttrMapManDate           => updateMapAdd(ns, attr, a.vs, a.refNs, a.owner, transformDate) //, set2arrayDate, extsDate, value2jsonDate)
      case a: AttrMapManDuration       => updateMapAdd(ns, attr, a.vs, a.refNs, a.owner, transformDuration) //, set2arrayDuration, extsDuration, value2jsonDuration)
      case a: AttrMapManInstant        => updateMapAdd(ns, attr, a.vs, a.refNs, a.owner, transformInstant) //, set2arrayInstant, extsInstant, value2jsonInstant)
      case a: AttrMapManLocalDate      => updateMapAdd(ns, attr, a.vs, a.refNs, a.owner, transformLocalDate) //, set2arrayLocalDate, extsLocalDate, value2jsonLocalDate)
      case a: AttrMapManLocalTime      => updateMapAdd(ns, attr, a.vs, a.refNs, a.owner, transformLocalTime) //, set2arrayLocalTime, extsLocalTime, value2jsonLocalTime)
      case a: AttrMapManLocalDateTime  => updateMapAdd(ns, attr, a.vs, a.refNs, a.owner, transformLocalDateTime) //, set2arrayLocalDateTime, extsLocalDateTime, value2jsonLocalDateTime)
      case a: AttrMapManOffsetTime     => updateMapAdd(ns, attr, a.vs, a.refNs, a.owner, transformOffsetTime) //, set2arrayOffsetTime, extsOffsetTime, value2jsonOffsetTime)
      case a: AttrMapManOffsetDateTime => updateMapAdd(ns, attr, a.vs, a.refNs, a.owner, transformOffsetDateTime) //, set2arrayOffsetDateTime, extsOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrMapManZonedDateTime  => updateMapAdd(ns, attr, a.vs, a.refNs, a.owner, transformZonedDateTime) //, set2arrayZonedDateTime, extsZonedDateTime, value2jsonZonedDateTime)
      case a: AttrMapManUUID           => updateMapAdd(ns, attr, a.vs, a.refNs, a.owner, transformUUID) //, set2arrayUUID, extsUUID, value2jsonUUID)
      case a: AttrMapManURI            => updateMapAdd(ns, attr, a.vs, a.refNs, a.owner, transformURI) //, set2arrayURI, extsURI, value2jsonURI)
      case a: AttrMapManByte           => updateMapAdd(ns, attr, a.vs, a.refNs, a.owner, transformByte) //, set2arrayURI, extsURI, value2jsonURI)
      case a: AttrMapManShort          => updateMapAdd(ns, attr, a.vs, a.refNs, a.owner, transformShort) //, set2arrayShort, extsShort, value2jsonShort)
      case a: AttrMapManChar           => updateMapAdd(ns, attr, a.vs, a.refNs, a.owner, transformChar) //, set2arrayChar, extsChar, value2jsonChar)
    }
  }

  private def resolveAttrMapRemove(a: AttrMapMan): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrMapManID             => updateMapRemove(ns, attr, a.vs, a.refNs, a.owner, transformID) //, handleID, extsID, one2jsonID)
      case a: AttrMapManString         => updateMapRemove(ns, attr, a.vs, a.refNs, a.owner, transformString) //, handleString, extsString, one2jsonString)
      case a: AttrMapManInt            => updateMapRemove(ns, attr, a.vs, a.refNs, a.owner, transformInt) //, handleInt, extsInt, one2jsonInt)
      case a: AttrMapManLong           => updateMapRemove(ns, attr, a.vs, a.refNs, a.owner, transformLong) //, handleLong, extsLong, one2jsonLong)
      case a: AttrMapManFloat          => updateMapRemove(ns, attr, a.vs, a.refNs, a.owner, transformFloat) //, handleFloat, extsFloat, one2jsonFloat)
      case a: AttrMapManDouble         => updateMapRemove(ns, attr, a.vs, a.refNs, a.owner, transformDouble) //, handleDouble, extsDouble, one2jsonDouble)
      case a: AttrMapManBoolean        => updateMapRemove(ns, attr, a.vs, a.refNs, a.owner, transformBoolean) //, handleBoolean, extsBoolean, one2jsonBoolean)
      case a: AttrMapManBigInt         => updateMapRemove(ns, attr, a.vs, a.refNs, a.owner, transformBigInt) //, handleBigInt, extsBigInt, one2jsonBigInt)
      case a: AttrMapManBigDecimal     => updateMapRemove(ns, attr, a.vs, a.refNs, a.owner, transformBigDecimal) //, handleBigDecimal, extsBigDecimal, one2jsonBigDecimal)
      case a: AttrMapManDate           => updateMapRemove(ns, attr, a.vs, a.refNs, a.owner, transformDate) //, handleDate, extsDate, one2jsonDate)
      case a: AttrMapManDuration       => updateMapRemove(ns, attr, a.vs, a.refNs, a.owner, transformDuration) //, handleDuration, extsDuration, one2jsonDuration)
      case a: AttrMapManInstant        => updateMapRemove(ns, attr, a.vs, a.refNs, a.owner, transformInstant) //, handleInstant, extsInstant, one2jsonInstant)
      case a: AttrMapManLocalDate      => updateMapRemove(ns, attr, a.vs, a.refNs, a.owner, transformLocalDate) //, handleLocalDate, extsLocalDate, one2jsonLocalDate)
      case a: AttrMapManLocalTime      => updateMapRemove(ns, attr, a.vs, a.refNs, a.owner, transformLocalTime) //, handleLocalTime, extsLocalTime, one2jsonLocalTime)
      case a: AttrMapManLocalDateTime  => updateMapRemove(ns, attr, a.vs, a.refNs, a.owner, transformLocalDateTime) //, handleLocalDateTime, extsLocalDateTime, one2jsonLocalDateTime)
      case a: AttrMapManOffsetTime     => updateMapRemove(ns, attr, a.vs, a.refNs, a.owner, transformOffsetTime) //, handleOffsetTime, extsOffsetTime, one2jsonOffsetTime)
      case a: AttrMapManOffsetDateTime => updateMapRemove(ns, attr, a.vs, a.refNs, a.owner, transformOffsetDateTime) //, handleOffsetDateTime, extsOffsetDateTime, one2jsonOffsetDateTime)
      case a: AttrMapManZonedDateTime  => updateMapRemove(ns, attr, a.vs, a.refNs, a.owner, transformZonedDateTime) //, handleZonedDateTime, extsZonedDateTime, one2jsonZonedDateTime)
      case a: AttrMapManUUID           => updateMapRemove(ns, attr, a.vs, a.refNs, a.owner, transformUUID) //, handleUUID, extsUUID, one2jsonUUID)
      case a: AttrMapManURI            => updateMapRemove(ns, attr, a.vs, a.refNs, a.owner, transformURI) //, handleURI, extsURI, one2jsonURI)
      case a: AttrMapManByte           => updateMapRemove(ns, attr, a.vs, a.refNs, a.owner, transformByte) //, handleURI, extsURI, one2jsonURI)
      case a: AttrMapManShort          => updateMapRemove(ns, attr, a.vs, a.refNs, a.owner, transformShort) //, handleShort, extsShort, one2jsonShort)
      case a: AttrMapManChar           => updateMapRemove(ns, attr, a.vs, a.refNs, a.owner, transformChar) //, handleChar, extsChar, one2jsonChar)
    }
  }
}