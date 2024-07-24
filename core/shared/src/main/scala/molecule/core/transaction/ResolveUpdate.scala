package molecule.core.transaction

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.marshalling.ConnProxy
import molecule.core.transaction.ops.UpdateOps
import molecule.core.util.ModelUtils
import scala.annotation.tailrec

class ResolveUpdate(
  val proxy: ConnProxy,
  val isUpsert: Boolean,
) extends ModelUtils { self: UpdateOps =>

  private def unexpectedOp(a: Attr) = throw ModelError(
    s"Unexpected update operation for card-many attribute (${a.name})." + a
  )

  @tailrec
  final def resolve(elements: List[Element]): Unit = {
    elements match {
      case element :: tail => element match {
        case a: Attr =>
          a match {
            case a: AttrOne => a match {
              case a: AttrOneMan => resolveAttrOneMan(a); resolve(tail)
              case a: AttrOneTac => resolveAttrOneTac(a); resolve(tail)
              case _: AttrOneOpt => noOptional(a)
            }

            case a: AttrSet => a match {
              case a: AttrSetMan => a.op match {
                case Eq | NoValue => resolveAttrSetMan(a); resolve(tail)
                case Add          => resolveAttrSetAdd(a); resolve(tail)
                case Remove       => resolveAttrSetRemove(a); resolve(tail)
                case _            => unexpectedOp(a)
              }
              case a: AttrSetTac => handleFilterAttr(a); resolve(tail)
              case _: AttrSetOpt => noOptional(a)
            }

            case a: AttrSeq => a match {
              case a: AttrSeqMan => a.op match {
                case Eq | NoValue => resolveAttrSeqMan(a); resolve(tail)
                case Add          => resolveAttrSeqAdd(a); resolve(tail)
                case Remove       => resolveAttrSeqRemove(a); resolve(tail)
                case _            => unexpectedOp(a)
              }
              case a: AttrSeqTac => handleFilterAttr(a); resolve(tail)
              case _: AttrSeqOpt => noOptional(a)
            }

            case a: AttrMap => a match {
              case a: AttrMapMan => a.op match {
                case Eq | NoValue => resolveAttrMapMan(a); resolve(tail)
                case Add          => resolveAttrMapAdd(a); resolve(tail)
                case Remove       => resolveAttrMapRemove(a); resolve(tail)
                case _            => unexpectedOp(a)
              }
              case a: AttrMapTac => handleFilterAttr(a); resolve(tail)
              case a: AttrMapOpt => noOptional(a)
            }
          }

        case r: Ref      => handleRef(r); resolve(tail)
        case br: BackRef => handleBackRef(br); resolve(tail)
        case r: OptRef   => ???
        case _           => noNested
      }
      case Nil             => ()
    }
  }


  private def resolveAttrOneMan(a: AttrOneMan): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a if a.attr == "id" => throw ModelError(
        s"Generic id attribute not allowed in update molecule (${a.name}).")

      case a if a.op != Eq && a.op != NoValue => throw ModelError(
        s"Can't update attributes without an applied value (${a.name}).")

      case a: AttrOneManID             => updateOne(ns, attr, a.vs, transformID, extsID)
      case a: AttrOneManString         => updateOne(ns, attr, a.vs, transformString, extsString)
      case a: AttrOneManInt            => updateOne(ns, attr, a.vs, transformInt, extsInt)
      case a: AttrOneManLong           => updateOne(ns, attr, a.vs, transformLong, extsLong)
      case a: AttrOneManFloat          => updateOne(ns, attr, a.vs, transformFloat, extsFloat)
      case a: AttrOneManDouble         => updateOne(ns, attr, a.vs, transformDouble, extsDouble)
      case a: AttrOneManBoolean        => updateOne(ns, attr, a.vs, transformBoolean, extsBoolean)
      case a: AttrOneManBigInt         => updateOne(ns, attr, a.vs, transformBigInt, extsBigInt)
      case a: AttrOneManBigDecimal     => updateOne(ns, attr, a.vs, transformBigDecimal, extsBigDecimal)
      case a: AttrOneManDate           => updateOne(ns, attr, a.vs, transformDate, extsDate)
      case a: AttrOneManDuration       => updateOne(ns, attr, a.vs, transformDuration, extsDuration)
      case a: AttrOneManInstant        => updateOne(ns, attr, a.vs, transformInstant, extsInstant)
      case a: AttrOneManLocalDate      => updateOne(ns, attr, a.vs, transformLocalDate, extsLocalDate)
      case a: AttrOneManLocalTime      => updateOne(ns, attr, a.vs, transformLocalTime, extsLocalTime)
      case a: AttrOneManLocalDateTime  => updateOne(ns, attr, a.vs, transformLocalDateTime, extsLocalDateTime)
      case a: AttrOneManOffsetTime     => updateOne(ns, attr, a.vs, transformOffsetTime, extsOffsetTime)
      case a: AttrOneManOffsetDateTime => updateOne(ns, attr, a.vs, transformOffsetDateTime, extsOffsetDateTime)
      case a: AttrOneManZonedDateTime  => updateOne(ns, attr, a.vs, transformZonedDateTime, extsZonedDateTime)
      case a: AttrOneManUUID           => updateOne(ns, attr, a.vs, transformUUID, extsUUID)
      case a: AttrOneManURI            => updateOne(ns, attr, a.vs, transformURI, extsURI)
      case a: AttrOneManByte           => updateOne(ns, attr, a.vs, transformByte, extsByte)
      case a: AttrOneManShort          => updateOne(ns, attr, a.vs, transformShort, extsShort)
      case a: AttrOneManChar           => updateOne(ns, attr, a.vs, transformChar, extsChar)
    }
  }

  private def resolveAttrOneTac(a: AttrOneTac): Unit = {
    a match {
      case AttrOneTacID(ns, "id", Eq, ids1, _, _, _, _, _, _, _) => handleIds(ns, ids1)

      case a if a.attr == "id" => throw ModelError(
        s"Generic id attribute not allowed in update molecule (${a.name}).")

      case a => handleFilterAttr(a)
    }
  }

  private def resolveAttrSetMan(a: AttrSetMan): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrSetManID             => updateSetEq(ns, attr, refNs, a.vs, transformID, extsID, set2arrayID, value2jsonID)
      case a: AttrSetManString         => updateSetEq(ns, attr, refNs, a.vs, transformString, extsString, set2arrayString, value2jsonString)
      case a: AttrSetManInt            => updateSetEq(ns, attr, refNs, a.vs, transformInt, extsInt, set2arrayInt, value2jsonInt)
      case a: AttrSetManLong           => updateSetEq(ns, attr, refNs, a.vs, transformLong, extsLong, set2arrayLong, value2jsonLong)
      case a: AttrSetManFloat          => updateSetEq(ns, attr, refNs, a.vs, transformFloat, extsFloat, set2arrayFloat, value2jsonFloat)
      case a: AttrSetManDouble         => updateSetEq(ns, attr, refNs, a.vs, transformDouble, extsDouble, set2arrayDouble, value2jsonDouble)
      case a: AttrSetManBoolean        => updateSetEq(ns, attr, refNs, a.vs, transformBoolean, extsBoolean, set2arrayBoolean, value2jsonBoolean)
      case a: AttrSetManBigInt         => updateSetEq(ns, attr, refNs, a.vs, transformBigInt, extsBigInt, set2arrayBigInt, value2jsonBigInt)
      case a: AttrSetManBigDecimal     => updateSetEq(ns, attr, refNs, a.vs, transformBigDecimal, extsBigDecimal, set2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSetManDate           => updateSetEq(ns, attr, refNs, a.vs, transformDate, extsDate, set2arrayDate, value2jsonDate)
      case a: AttrSetManDuration       => updateSetEq(ns, attr, refNs, a.vs, transformDuration, extsDuration, set2arrayDuration, value2jsonDuration)
      case a: AttrSetManInstant        => updateSetEq(ns, attr, refNs, a.vs, transformInstant, extsInstant, set2arrayInstant, value2jsonInstant)
      case a: AttrSetManLocalDate      => updateSetEq(ns, attr, refNs, a.vs, transformLocalDate, extsLocalDate, set2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSetManLocalTime      => updateSetEq(ns, attr, refNs, a.vs, transformLocalTime, extsLocalTime, set2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSetManLocalDateTime  => updateSetEq(ns, attr, refNs, a.vs, transformLocalDateTime, extsLocalDateTime, set2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSetManOffsetTime     => updateSetEq(ns, attr, refNs, a.vs, transformOffsetTime, extsOffsetTime, set2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSetManOffsetDateTime => updateSetEq(ns, attr, refNs, a.vs, transformOffsetDateTime, extsOffsetDateTime, set2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSetManZonedDateTime  => updateSetEq(ns, attr, refNs, a.vs, transformZonedDateTime, extsZonedDateTime, set2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSetManUUID           => updateSetEq(ns, attr, refNs, a.vs, transformUUID, extsUUID, set2arrayUUID, value2jsonUUID)
      case a: AttrSetManURI            => updateSetEq(ns, attr, refNs, a.vs, transformURI, extsURI, set2arrayURI, value2jsonURI)
      case a: AttrSetManByte           => updateSetEq(ns, attr, refNs, a.vs, transformByte, extsByte, set2arrayByte, value2jsonByte)
      case a: AttrSetManShort          => updateSetEq(ns, attr, refNs, a.vs, transformShort, extsShort, set2arrayShort, value2jsonShort)
      case a: AttrSetManChar           => updateSetEq(ns, attr, refNs, a.vs, transformChar, extsChar, set2arrayChar, value2jsonChar)
    }
  }

  private def resolveAttrSetAdd(a: AttrSetMan): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrSetManID             => updateSetAdd(ns, attr, refNs, a.vs, transformID, extsID, set2arrayID, value2jsonID)
      case a: AttrSetManString         => updateSetAdd(ns, attr, refNs, a.vs, transformString, extsString, set2arrayString, value2jsonString)
      case a: AttrSetManInt            => updateSetAdd(ns, attr, refNs, a.vs, transformInt, extsInt, set2arrayInt, value2jsonInt)
      case a: AttrSetManLong           => updateSetAdd(ns, attr, refNs, a.vs, transformLong, extsLong, set2arrayLong, value2jsonLong)
      case a: AttrSetManFloat          => updateSetAdd(ns, attr, refNs, a.vs, transformFloat, extsFloat, set2arrayFloat, value2jsonFloat)
      case a: AttrSetManDouble         => updateSetAdd(ns, attr, refNs, a.vs, transformDouble, extsDouble, set2arrayDouble, value2jsonDouble)
      case a: AttrSetManBoolean        => updateSetAdd(ns, attr, refNs, a.vs, transformBoolean, extsBoolean, set2arrayBoolean, value2jsonBoolean)
      case a: AttrSetManBigInt         => updateSetAdd(ns, attr, refNs, a.vs, transformBigInt, extsBigInt, set2arrayBigInt, value2jsonBigInt)
      case a: AttrSetManBigDecimal     => updateSetAdd(ns, attr, refNs, a.vs, transformBigDecimal, extsBigDecimal, set2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSetManDate           => updateSetAdd(ns, attr, refNs, a.vs, transformDate, extsDate, set2arrayDate, value2jsonDate)
      case a: AttrSetManDuration       => updateSetAdd(ns, attr, refNs, a.vs, transformDuration, extsDuration, set2arrayDuration, value2jsonDuration)
      case a: AttrSetManInstant        => updateSetAdd(ns, attr, refNs, a.vs, transformInstant, extsInstant, set2arrayInstant, value2jsonInstant)
      case a: AttrSetManLocalDate      => updateSetAdd(ns, attr, refNs, a.vs, transformLocalDate, extsLocalDate, set2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSetManLocalTime      => updateSetAdd(ns, attr, refNs, a.vs, transformLocalTime, extsLocalTime, set2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSetManLocalDateTime  => updateSetAdd(ns, attr, refNs, a.vs, transformLocalDateTime, extsLocalDateTime, set2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSetManOffsetTime     => updateSetAdd(ns, attr, refNs, a.vs, transformOffsetTime, extsOffsetTime, set2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSetManOffsetDateTime => updateSetAdd(ns, attr, refNs, a.vs, transformOffsetDateTime, extsOffsetDateTime, set2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSetManZonedDateTime  => updateSetAdd(ns, attr, refNs, a.vs, transformZonedDateTime, extsZonedDateTime, set2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSetManUUID           => updateSetAdd(ns, attr, refNs, a.vs, transformUUID, extsUUID, set2arrayUUID, value2jsonUUID)
      case a: AttrSetManURI            => updateSetAdd(ns, attr, refNs, a.vs, transformURI, extsURI, set2arrayURI, value2jsonURI)
      case a: AttrSetManByte           => updateSetAdd(ns, attr, refNs, a.vs, transformByte, extsByte, set2arrayByte, value2jsonByte)
      case a: AttrSetManShort          => updateSetAdd(ns, attr, refNs, a.vs, transformShort, extsShort, set2arrayShort, value2jsonShort)
      case a: AttrSetManChar           => updateSetAdd(ns, attr, refNs, a.vs, transformChar, extsChar, set2arrayChar, value2jsonChar)
    }
  }

  private def resolveAttrSetRemove(a: AttrSetMan): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrSetManID             => updateSetRemove(ns, attr, refNs, a.vs, transformID, extsID, one2jsonID, set2arrayID)
      case a: AttrSetManString         => updateSetRemove(ns, attr, refNs, a.vs, transformString, extsString, one2jsonString, set2arrayString)
      case a: AttrSetManInt            => updateSetRemove(ns, attr, refNs, a.vs, transformInt, extsInt, one2jsonInt, set2arrayInt)
      case a: AttrSetManLong           => updateSetRemove(ns, attr, refNs, a.vs, transformLong, extsLong, one2jsonLong, set2arrayLong)
      case a: AttrSetManFloat          => updateSetRemove(ns, attr, refNs, a.vs, transformFloat, extsFloat, one2jsonFloat, set2arrayFloat)
      case a: AttrSetManDouble         => updateSetRemove(ns, attr, refNs, a.vs, transformDouble, extsDouble, one2jsonDouble, set2arrayDouble)
      case a: AttrSetManBoolean        => updateSetRemove(ns, attr, refNs, a.vs, transformBoolean, extsBoolean, one2jsonBoolean, set2arrayBoolean)
      case a: AttrSetManBigInt         => updateSetRemove(ns, attr, refNs, a.vs, transformBigInt, extsBigInt, one2jsonBigInt, set2arrayBigInt)
      case a: AttrSetManBigDecimal     => updateSetRemove(ns, attr, refNs, a.vs, transformBigDecimal, extsBigDecimal, one2jsonBigDecimal, set2arrayBigDecimal)
      case a: AttrSetManDate           => updateSetRemove(ns, attr, refNs, a.vs, transformDate, extsDate, one2jsonDate, set2arrayDate)
      case a: AttrSetManDuration       => updateSetRemove(ns, attr, refNs, a.vs, transformDuration, extsDuration, one2jsonDuration, set2arrayDuration)
      case a: AttrSetManInstant        => updateSetRemove(ns, attr, refNs, a.vs, transformInstant, extsInstant, one2jsonInstant, set2arrayInstant)
      case a: AttrSetManLocalDate      => updateSetRemove(ns, attr, refNs, a.vs, transformLocalDate, extsLocalDate, one2jsonLocalDate, set2arrayLocalDate)
      case a: AttrSetManLocalTime      => updateSetRemove(ns, attr, refNs, a.vs, transformLocalTime, extsLocalTime, one2jsonLocalTime, set2arrayLocalTime)
      case a: AttrSetManLocalDateTime  => updateSetRemove(ns, attr, refNs, a.vs, transformLocalDateTime, extsLocalDateTime, one2jsonLocalDateTime, set2arrayLocalDateTime)
      case a: AttrSetManOffsetTime     => updateSetRemove(ns, attr, refNs, a.vs, transformOffsetTime, extsOffsetTime, one2jsonOffsetTime, set2arrayOffsetTime)
      case a: AttrSetManOffsetDateTime => updateSetRemove(ns, attr, refNs, a.vs, transformOffsetDateTime, extsOffsetDateTime, one2jsonOffsetDateTime, set2arrayOffsetDateTime)
      case a: AttrSetManZonedDateTime  => updateSetRemove(ns, attr, refNs, a.vs, transformZonedDateTime, extsZonedDateTime, one2jsonZonedDateTime, set2arrayZonedDateTime)
      case a: AttrSetManUUID           => updateSetRemove(ns, attr, refNs, a.vs, transformUUID, extsUUID, one2jsonUUID, set2arrayUUID)
      case a: AttrSetManURI            => updateSetRemove(ns, attr, refNs, a.vs, transformURI, extsURI, one2jsonURI, set2arrayURI)
      case a: AttrSetManByte           => updateSetRemove(ns, attr, refNs, a.vs, transformByte, extsByte, one2jsonByte, set2arrayByte)
      case a: AttrSetManShort          => updateSetRemove(ns, attr, refNs, a.vs, transformShort, extsShort, one2jsonShort, set2arrayShort)
      case a: AttrSetManChar           => updateSetRemove(ns, attr, refNs, a.vs, transformChar, extsChar, one2jsonChar, set2arrayChar)
    }
  }

  private def resolveAttrSeqMan(a: AttrSeqMan): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrSeqManID             => updateSeqEq(ns, attr, refNs, a.vs, transformID, extsID, seq2arrayID, value2jsonID)
      case a: AttrSeqManString         => updateSeqEq(ns, attr, refNs, a.vs, transformString, extsString, seq2arrayString, value2jsonString)
      case a: AttrSeqManInt            => updateSeqEq(ns, attr, refNs, a.vs, transformInt, extsInt, seq2arrayInt, value2jsonInt)
      case a: AttrSeqManLong           => updateSeqEq(ns, attr, refNs, a.vs, transformLong, extsLong, seq2arrayLong, value2jsonLong)
      case a: AttrSeqManFloat          => updateSeqEq(ns, attr, refNs, a.vs, transformFloat, extsFloat, seq2arrayFloat, value2jsonFloat)
      case a: AttrSeqManDouble         => updateSeqEq(ns, attr, refNs, a.vs, transformDouble, extsDouble, seq2arrayDouble, value2jsonDouble)
      case a: AttrSeqManBoolean        => updateSeqEq(ns, attr, refNs, a.vs, transformBoolean, extsBoolean, seq2arrayBoolean, value2jsonBoolean)
      case a: AttrSeqManBigInt         => updateSeqEq(ns, attr, refNs, a.vs, transformBigInt, extsBigInt, seq2arrayBigInt, value2jsonBigInt)
      case a: AttrSeqManBigDecimal     => updateSeqEq(ns, attr, refNs, a.vs, transformBigDecimal, extsBigDecimal, seq2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSeqManDate           => updateSeqEq(ns, attr, refNs, a.vs, transformDate, extsDate, seq2arrayDate, value2jsonDate)
      case a: AttrSeqManDuration       => updateSeqEq(ns, attr, refNs, a.vs, transformDuration, extsDuration, seq2arrayDuration, value2jsonDuration)
      case a: AttrSeqManInstant        => updateSeqEq(ns, attr, refNs, a.vs, transformInstant, extsInstant, seq2arrayInstant, value2jsonInstant)
      case a: AttrSeqManLocalDate      => updateSeqEq(ns, attr, refNs, a.vs, transformLocalDate, extsLocalDate, seq2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSeqManLocalTime      => updateSeqEq(ns, attr, refNs, a.vs, transformLocalTime, extsLocalTime, seq2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSeqManLocalDateTime  => updateSeqEq(ns, attr, refNs, a.vs, transformLocalDateTime, extsLocalDateTime, seq2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSeqManOffsetTime     => updateSeqEq(ns, attr, refNs, a.vs, transformOffsetTime, extsOffsetTime, seq2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSeqManOffsetDateTime => updateSeqEq(ns, attr, refNs, a.vs, transformOffsetDateTime, extsOffsetDateTime, seq2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSeqManZonedDateTime  => updateSeqEq(ns, attr, refNs, a.vs, transformZonedDateTime, extsZonedDateTime, seq2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSeqManUUID           => updateSeqEq(ns, attr, refNs, a.vs, transformUUID, extsUUID, seq2arrayUUID, value2jsonUUID)
      case a: AttrSeqManURI            => updateSeqEq(ns, attr, refNs, a.vs, transformURI, extsURI, seq2arrayURI, value2jsonURI)
      case a: AttrSeqManShort          => updateSeqEq(ns, attr, refNs, a.vs, transformShort, extsShort, seq2arrayShort, value2jsonShort)
      case a: AttrSeqManChar           => updateSeqEq(ns, attr, refNs, a.vs, transformChar, extsChar, seq2arrayChar, value2jsonChar)
      case a: AttrSeqManByte           => updateByteArray(ns, attr, a.vs)
    }
  }

  private def resolveAttrSeqAdd(a: AttrSeqMan): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrSeqManID             => updateSeqAdd(ns, attr, refNs, a.vs, transformID, extsID, seq2arrayID, value2jsonID)
      case a: AttrSeqManString         => updateSeqAdd(ns, attr, refNs, a.vs, transformString, extsString, seq2arrayString, value2jsonString)
      case a: AttrSeqManInt            => updateSeqAdd(ns, attr, refNs, a.vs, transformInt, extsInt, seq2arrayInt, value2jsonInt)
      case a: AttrSeqManLong           => updateSeqAdd(ns, attr, refNs, a.vs, transformLong, extsLong, seq2arrayLong, value2jsonLong)
      case a: AttrSeqManFloat          => updateSeqAdd(ns, attr, refNs, a.vs, transformFloat, extsFloat, seq2arrayFloat, value2jsonFloat)
      case a: AttrSeqManDouble         => updateSeqAdd(ns, attr, refNs, a.vs, transformDouble, extsDouble, seq2arrayDouble, value2jsonDouble)
      case a: AttrSeqManBoolean        => updateSeqAdd(ns, attr, refNs, a.vs, transformBoolean, extsBoolean, seq2arrayBoolean, value2jsonBoolean)
      case a: AttrSeqManBigInt         => updateSeqAdd(ns, attr, refNs, a.vs, transformBigInt, extsBigInt, seq2arrayBigInt, value2jsonBigInt)
      case a: AttrSeqManBigDecimal     => updateSeqAdd(ns, attr, refNs, a.vs, transformBigDecimal, extsBigDecimal, seq2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSeqManDate           => updateSeqAdd(ns, attr, refNs, a.vs, transformDate, extsDate, seq2arrayDate, value2jsonDate)
      case a: AttrSeqManDuration       => updateSeqAdd(ns, attr, refNs, a.vs, transformDuration, extsDuration, seq2arrayDuration, value2jsonDuration)
      case a: AttrSeqManInstant        => updateSeqAdd(ns, attr, refNs, a.vs, transformInstant, extsInstant, seq2arrayInstant, value2jsonInstant)
      case a: AttrSeqManLocalDate      => updateSeqAdd(ns, attr, refNs, a.vs, transformLocalDate, extsLocalDate, seq2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSeqManLocalTime      => updateSeqAdd(ns, attr, refNs, a.vs, transformLocalTime, extsLocalTime, seq2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSeqManLocalDateTime  => updateSeqAdd(ns, attr, refNs, a.vs, transformLocalDateTime, extsLocalDateTime, seq2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSeqManOffsetTime     => updateSeqAdd(ns, attr, refNs, a.vs, transformOffsetTime, extsOffsetTime, seq2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSeqManOffsetDateTime => updateSeqAdd(ns, attr, refNs, a.vs, transformOffsetDateTime, extsOffsetDateTime, seq2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSeqManZonedDateTime  => updateSeqAdd(ns, attr, refNs, a.vs, transformZonedDateTime, extsZonedDateTime, seq2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSeqManUUID           => updateSeqAdd(ns, attr, refNs, a.vs, transformUUID, extsUUID, seq2arrayUUID, value2jsonUUID)
      case a: AttrSeqManURI            => updateSeqAdd(ns, attr, refNs, a.vs, transformURI, extsURI, seq2arrayURI, value2jsonURI)
      case a: AttrSeqManShort          => updateSeqAdd(ns, attr, refNs, a.vs, transformShort, extsShort, seq2arrayShort, value2jsonShort)
      case a: AttrSeqManChar           => updateSeqAdd(ns, attr, refNs, a.vs, transformChar, extsChar, seq2arrayChar, value2jsonChar)
      case a: AttrSeqManByte           => throw ModelError(s"Operations on byte arrays (${a.ns}.${a.attr}) not allowed.")
    }
  }

  private def resolveAttrSeqRemove(a: AttrSeqMan): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrSeqManID             => updateSeqRemove(ns, attr, refNs, a.vs, transformID, extsID, one2jsonID, seq2arrayID)
      case a: AttrSeqManString         => updateSeqRemove(ns, attr, refNs, a.vs, transformString, extsString, one2jsonString, seq2arrayString)
      case a: AttrSeqManInt            => updateSeqRemove(ns, attr, refNs, a.vs, transformInt, extsInt, one2jsonInt, seq2arrayInt)
      case a: AttrSeqManLong           => updateSeqRemove(ns, attr, refNs, a.vs, transformLong, extsLong, one2jsonLong, seq2arrayLong)
      case a: AttrSeqManFloat          => updateSeqRemove(ns, attr, refNs, a.vs, transformFloat, extsFloat, one2jsonFloat, seq2arrayFloat)
      case a: AttrSeqManDouble         => updateSeqRemove(ns, attr, refNs, a.vs, transformDouble, extsDouble, one2jsonDouble, seq2arrayDouble)
      case a: AttrSeqManBoolean        => updateSeqRemove(ns, attr, refNs, a.vs, transformBoolean, extsBoolean, one2jsonBoolean, seq2arrayBoolean)
      case a: AttrSeqManBigInt         => updateSeqRemove(ns, attr, refNs, a.vs, transformBigInt, extsBigInt, one2jsonBigInt, seq2arrayBigInt)
      case a: AttrSeqManBigDecimal     => updateSeqRemove(ns, attr, refNs, a.vs, transformBigDecimal, extsBigDecimal, one2jsonBigDecimal, seq2arrayBigDecimal)
      case a: AttrSeqManDate           => updateSeqRemove(ns, attr, refNs, a.vs, transformDate, extsDate, one2jsonDate, seq2arrayDate)
      case a: AttrSeqManDuration       => updateSeqRemove(ns, attr, refNs, a.vs, transformDuration, extsDuration, one2jsonDuration, seq2arrayDuration)
      case a: AttrSeqManInstant        => updateSeqRemove(ns, attr, refNs, a.vs, transformInstant, extsInstant, one2jsonInstant, seq2arrayInstant)
      case a: AttrSeqManLocalDate      => updateSeqRemove(ns, attr, refNs, a.vs, transformLocalDate, extsLocalDate, one2jsonLocalDate, seq2arrayLocalDate)
      case a: AttrSeqManLocalTime      => updateSeqRemove(ns, attr, refNs, a.vs, transformLocalTime, extsLocalTime, one2jsonLocalTime, seq2arrayLocalTime)
      case a: AttrSeqManLocalDateTime  => updateSeqRemove(ns, attr, refNs, a.vs, transformLocalDateTime, extsLocalDateTime, one2jsonLocalDateTime, seq2arrayLocalDateTime)
      case a: AttrSeqManOffsetTime     => updateSeqRemove(ns, attr, refNs, a.vs, transformOffsetTime, extsOffsetTime, one2jsonOffsetTime, seq2arrayOffsetTime)
      case a: AttrSeqManOffsetDateTime => updateSeqRemove(ns, attr, refNs, a.vs, transformOffsetDateTime, extsOffsetDateTime, one2jsonOffsetDateTime, seq2arrayOffsetDateTime)
      case a: AttrSeqManZonedDateTime  => updateSeqRemove(ns, attr, refNs, a.vs, transformZonedDateTime, extsZonedDateTime, one2jsonZonedDateTime, seq2arrayZonedDateTime)
      case a: AttrSeqManUUID           => updateSeqRemove(ns, attr, refNs, a.vs, transformUUID, extsUUID, one2jsonUUID, seq2arrayUUID)
      case a: AttrSeqManURI            => updateSeqRemove(ns, attr, refNs, a.vs, transformURI, extsURI, one2jsonURI, seq2arrayURI)
      case a: AttrSeqManShort          => updateSeqRemove(ns, attr, refNs, a.vs, transformShort, extsShort, one2jsonShort, seq2arrayShort)
      case a: AttrSeqManChar           => updateSeqRemove(ns, attr, refNs, a.vs, transformChar, extsChar, one2jsonChar, seq2arrayChar)
      case a: AttrSeqManByte           => throw ModelError(s"Operations on byte arrays (${a.ns}.${a.attr}) not allowed.")
    }
  }


  private def resolveAttrMapMan(a: AttrMapMan): Unit = {
    val (ns, attr, refNs, noValue) = (a.ns, a.attr, a.refNs, a.op == NoValue)
    a match {
      case a: AttrMapManID             => updateMapEq(ns, attr, refNs, noValue, a.map, transformID, value2jsonID)
      case a: AttrMapManString         => updateMapEq(ns, attr, refNs, noValue, a.map, transformString, value2jsonString)
      case a: AttrMapManInt            => updateMapEq(ns, attr, refNs, noValue, a.map, transformInt, value2jsonInt)
      case a: AttrMapManLong           => updateMapEq(ns, attr, refNs, noValue, a.map, transformLong, value2jsonLong)
      case a: AttrMapManFloat          => updateMapEq(ns, attr, refNs, noValue, a.map, transformFloat, value2jsonFloat)
      case a: AttrMapManDouble         => updateMapEq(ns, attr, refNs, noValue, a.map, transformDouble, value2jsonDouble)
      case a: AttrMapManBoolean        => updateMapEq(ns, attr, refNs, noValue, a.map, transformBoolean, value2jsonBoolean)
      case a: AttrMapManBigInt         => updateMapEq(ns, attr, refNs, noValue, a.map, transformBigInt, value2jsonBigInt)
      case a: AttrMapManBigDecimal     => updateMapEq(ns, attr, refNs, noValue, a.map, transformBigDecimal, value2jsonBigDecimal)
      case a: AttrMapManDate           => updateMapEq(ns, attr, refNs, noValue, a.map, transformDate, value2jsonDate)
      case a: AttrMapManDuration       => updateMapEq(ns, attr, refNs, noValue, a.map, transformDuration, value2jsonDuration)
      case a: AttrMapManInstant        => updateMapEq(ns, attr, refNs, noValue, a.map, transformInstant, value2jsonInstant)
      case a: AttrMapManLocalDate      => updateMapEq(ns, attr, refNs, noValue, a.map, transformLocalDate, value2jsonLocalDate)
      case a: AttrMapManLocalTime      => updateMapEq(ns, attr, refNs, noValue, a.map, transformLocalTime, value2jsonLocalTime)
      case a: AttrMapManLocalDateTime  => updateMapEq(ns, attr, refNs, noValue, a.map, transformLocalDateTime, value2jsonLocalDateTime)
      case a: AttrMapManOffsetTime     => updateMapEq(ns, attr, refNs, noValue, a.map, transformOffsetTime, value2jsonOffsetTime)
      case a: AttrMapManOffsetDateTime => updateMapEq(ns, attr, refNs, noValue, a.map, transformOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrMapManZonedDateTime  => updateMapEq(ns, attr, refNs, noValue, a.map, transformZonedDateTime, value2jsonZonedDateTime)
      case a: AttrMapManUUID           => updateMapEq(ns, attr, refNs, noValue, a.map, transformUUID, value2jsonUUID)
      case a: AttrMapManURI            => updateMapEq(ns, attr, refNs, noValue, a.map, transformURI, value2jsonURI)
      case a: AttrMapManByte           => updateMapEq(ns, attr, refNs, noValue, a.map, transformByte, value2jsonByte)
      case a: AttrMapManShort          => updateMapEq(ns, attr, refNs, noValue, a.map, transformShort, value2jsonShort)
      case a: AttrMapManChar           => updateMapEq(ns, attr, refNs, noValue, a.map, transformChar, value2jsonChar)
    }
  }

  private def resolveAttrMapAdd(a: AttrMapMan): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrMapManID             => updateMapAdd(ns, attr, refNs, a.map, transformID, extsID, value2jsonID)
      case a: AttrMapManString         => updateMapAdd(ns, attr, refNs, a.map, transformString, extsString, value2jsonString)
      case a: AttrMapManInt            => updateMapAdd(ns, attr, refNs, a.map, transformInt, extsInt, value2jsonInt)
      case a: AttrMapManLong           => updateMapAdd(ns, attr, refNs, a.map, transformLong, extsLong, value2jsonLong)
      case a: AttrMapManFloat          => updateMapAdd(ns, attr, refNs, a.map, transformFloat, extsFloat, value2jsonFloat)
      case a: AttrMapManDouble         => updateMapAdd(ns, attr, refNs, a.map, transformDouble, extsDouble, value2jsonDouble)
      case a: AttrMapManBoolean        => updateMapAdd(ns, attr, refNs, a.map, transformBoolean, extsBoolean, value2jsonBoolean)
      case a: AttrMapManBigInt         => updateMapAdd(ns, attr, refNs, a.map, transformBigInt, extsBigInt, value2jsonBigInt)
      case a: AttrMapManBigDecimal     => updateMapAdd(ns, attr, refNs, a.map, transformBigDecimal, extsBigDecimal, value2jsonBigDecimal)
      case a: AttrMapManDate           => updateMapAdd(ns, attr, refNs, a.map, transformDate, extsDate, value2jsonDate)
      case a: AttrMapManDuration       => updateMapAdd(ns, attr, refNs, a.map, transformDuration, extsDuration, value2jsonDuration)
      case a: AttrMapManInstant        => updateMapAdd(ns, attr, refNs, a.map, transformInstant, extsInstant, value2jsonInstant)
      case a: AttrMapManLocalDate      => updateMapAdd(ns, attr, refNs, a.map, transformLocalDate, extsLocalDate, value2jsonLocalDate)
      case a: AttrMapManLocalTime      => updateMapAdd(ns, attr, refNs, a.map, transformLocalTime, extsLocalTime, value2jsonLocalTime)
      case a: AttrMapManLocalDateTime  => updateMapAdd(ns, attr, refNs, a.map, transformLocalDateTime, extsLocalDateTime, value2jsonLocalDateTime)
      case a: AttrMapManOffsetTime     => updateMapAdd(ns, attr, refNs, a.map, transformOffsetTime, extsOffsetTime, value2jsonOffsetTime)
      case a: AttrMapManOffsetDateTime => updateMapAdd(ns, attr, refNs, a.map, transformOffsetDateTime, extsOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrMapManZonedDateTime  => updateMapAdd(ns, attr, refNs, a.map, transformZonedDateTime, extsZonedDateTime, value2jsonZonedDateTime)
      case a: AttrMapManUUID           => updateMapAdd(ns, attr, refNs, a.map, transformUUID, extsUUID, value2jsonUUID)
      case a: AttrMapManURI            => updateMapAdd(ns, attr, refNs, a.map, transformURI, extsURI, value2jsonURI)
      case a: AttrMapManByte           => updateMapAdd(ns, attr, refNs, a.map, transformByte, extsByte, value2jsonByte)
      case a: AttrMapManShort          => updateMapAdd(ns, attr, refNs, a.map, transformShort, extsShort, value2jsonShort)
      case a: AttrMapManChar           => updateMapAdd(ns, attr, refNs, a.map, transformChar, extsChar, value2jsonChar)
    }
  }

  private def resolveAttrMapRemove(a: AttrMapMan): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrMapManID             => updateMapRemove(ns, attr, refNs, a.keys, extsID)
      case a: AttrMapManString         => updateMapRemove(ns, attr, refNs, a.keys, extsString)
      case a: AttrMapManInt            => updateMapRemove(ns, attr, refNs, a.keys, extsInt)
      case a: AttrMapManLong           => updateMapRemove(ns, attr, refNs, a.keys, extsLong)
      case a: AttrMapManFloat          => updateMapRemove(ns, attr, refNs, a.keys, extsFloat)
      case a: AttrMapManDouble         => updateMapRemove(ns, attr, refNs, a.keys, extsDouble)
      case a: AttrMapManBoolean        => updateMapRemove(ns, attr, refNs, a.keys, extsBoolean)
      case a: AttrMapManBigInt         => updateMapRemove(ns, attr, refNs, a.keys, extsBigInt)
      case a: AttrMapManBigDecimal     => updateMapRemove(ns, attr, refNs, a.keys, extsBigDecimal)
      case a: AttrMapManDate           => updateMapRemove(ns, attr, refNs, a.keys, extsDate)
      case a: AttrMapManDuration       => updateMapRemove(ns, attr, refNs, a.keys, extsDuration)
      case a: AttrMapManInstant        => updateMapRemove(ns, attr, refNs, a.keys, extsInstant)
      case a: AttrMapManLocalDate      => updateMapRemove(ns, attr, refNs, a.keys, extsLocalDate)
      case a: AttrMapManLocalTime      => updateMapRemove(ns, attr, refNs, a.keys, extsLocalTime)
      case a: AttrMapManLocalDateTime  => updateMapRemove(ns, attr, refNs, a.keys, extsLocalDateTime)
      case a: AttrMapManOffsetTime     => updateMapRemove(ns, attr, refNs, a.keys, extsOffsetTime)
      case a: AttrMapManOffsetDateTime => updateMapRemove(ns, attr, refNs, a.keys, extsOffsetDateTime)
      case a: AttrMapManZonedDateTime  => updateMapRemove(ns, attr, refNs, a.keys, extsZonedDateTime)
      case a: AttrMapManUUID           => updateMapRemove(ns, attr, refNs, a.keys, extsUUID)
      case a: AttrMapManURI            => updateMapRemove(ns, attr, refNs, a.keys, extsURI)
      case a: AttrMapManByte           => updateMapRemove(ns, attr, refNs, a.keys, extsByte)
      case a: AttrMapManShort          => updateMapRemove(ns, attr, refNs, a.keys, extsShort)
      case a: AttrMapManChar           => updateMapRemove(ns, attr, refNs, a.keys, extsChar)
    }
  }
}