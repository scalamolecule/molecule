package molecule.core.transaction

import molecule.base.error.*
import molecule.core.ast.DataModel.*
import molecule.core.transaction.ops.UpdateOps
import molecule.core.util.ModelUtils
import scala.annotation.tailrec

trait ResolveUpdate
  extends ModelUtils { self: UpdateOps =>

  val isUpsert: Boolean

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
        case _: OptRef   => throw ModelError("Optional ref in update not supported.")
        case _           => noNested
      }
      case Nil             => ()
    }
  }


  private def resolveAttrOneMan(a: AttrOneMan): Unit = {
    val (ent, attr) = (a.ent, a.attr)
    a match {
      case a if a.attr == "id" => throw ModelError(
        s"Generic id attribute not allowed in update molecule (${a.name}).")

      case a: AttrOneManID             => updateOne(ent, attr, a.op, a.vs, transformID, extsID)
      case a: AttrOneManString         => updateOne(ent, attr, a.op, a.vs, transformString, extsString)
      case a: AttrOneManInt            => updateOne(ent, attr, a.op, a.vs, transformInt, extsInt)
      case a: AttrOneManLong           => updateOne(ent, attr, a.op, a.vs, transformLong, extsLong)
      case a: AttrOneManFloat          => updateOne(ent, attr, a.op, a.vs, transformFloat, extsFloat)
      case a: AttrOneManDouble         => updateOne(ent, attr, a.op, a.vs, transformDouble, extsDouble)
      case a: AttrOneManBoolean        => updateOne(ent, attr, a.op, a.vs, transformBoolean, extsBoolean)
      case a: AttrOneManBigInt         => updateOne(ent, attr, a.op, a.vs, transformBigInt, extsBigInt)
      case a: AttrOneManBigDecimal     => updateOne(ent, attr, a.op, a.vs, transformBigDecimal, extsBigDecimal)
      case a: AttrOneManDate           => updateOne(ent, attr, a.op, a.vs, transformDate, extsDate)
      case a: AttrOneManDuration       => updateOne(ent, attr, a.op, a.vs, transformDuration, extsDuration)
      case a: AttrOneManInstant        => updateOne(ent, attr, a.op, a.vs, transformInstant, extsInstant)
      case a: AttrOneManLocalDate      => updateOne(ent, attr, a.op, a.vs, transformLocalDate, extsLocalDate)
      case a: AttrOneManLocalTime      => updateOne(ent, attr, a.op, a.vs, transformLocalTime, extsLocalTime)
      case a: AttrOneManLocalDateTime  => updateOne(ent, attr, a.op, a.vs, transformLocalDateTime, extsLocalDateTime)
      case a: AttrOneManOffsetTime     => updateOne(ent, attr, a.op, a.vs, transformOffsetTime, extsOffsetTime)
      case a: AttrOneManOffsetDateTime => updateOne(ent, attr, a.op, a.vs, transformOffsetDateTime, extsOffsetDateTime)
      case a: AttrOneManZonedDateTime  => updateOne(ent, attr, a.op, a.vs, transformZonedDateTime, extsZonedDateTime)
      case a: AttrOneManUUID           => updateOne(ent, attr, a.op, a.vs, transformUUID, extsUUID)
      case a: AttrOneManURI            => updateOne(ent, attr, a.op, a.vs, transformURI, extsURI)
      case a: AttrOneManByte           => updateOne(ent, attr, a.op, a.vs, transformByte, extsByte)
      case a: AttrOneManShort          => updateOne(ent, attr, a.op, a.vs, transformShort, extsShort)
      case a: AttrOneManChar           => updateOne(ent, attr, a.op, a.vs, transformChar, extsChar)
    }
  }

  private def resolveAttrOneTac(a: AttrOneTac): Unit = {
    a match {
      case AttrOneTacID(ent, "id", Eq, ids1, _, _, _, _, _, _, _) => handleIds(ent, ids1)

      case a if a.attr == "id" => throw ModelError(
        s"Generic id attribute not allowed in update molecule (${a.name}).")

      case a => handleFilterAttr(a)
    }
  }

  private def resolveAttrSetMan(a: AttrSetMan): Unit = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrSetManID             => updateSetEq(ent, attr, ref, a.vs, transformID, extsID, set2arrayID, value2jsonID)
      case a: AttrSetManString         => updateSetEq(ent, attr, ref, a.vs, transformString, extsString, set2arrayString, value2jsonString)
      case a: AttrSetManInt            => updateSetEq(ent, attr, ref, a.vs, transformInt, extsInt, set2arrayInt, value2jsonInt)
      case a: AttrSetManLong           => updateSetEq(ent, attr, ref, a.vs, transformLong, extsLong, set2arrayLong, value2jsonLong)
      case a: AttrSetManFloat          => updateSetEq(ent, attr, ref, a.vs, transformFloat, extsFloat, set2arrayFloat, value2jsonFloat)
      case a: AttrSetManDouble         => updateSetEq(ent, attr, ref, a.vs, transformDouble, extsDouble, set2arrayDouble, value2jsonDouble)
      case a: AttrSetManBoolean        => updateSetEq(ent, attr, ref, a.vs, transformBoolean, extsBoolean, set2arrayBoolean, value2jsonBoolean)
      case a: AttrSetManBigInt         => updateSetEq(ent, attr, ref, a.vs, transformBigInt, extsBigInt, set2arrayBigInt, value2jsonBigInt)
      case a: AttrSetManBigDecimal     => updateSetEq(ent, attr, ref, a.vs, transformBigDecimal, extsBigDecimal, set2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSetManDate           => updateSetEq(ent, attr, ref, a.vs, transformDate, extsDate, set2arrayDate, value2jsonDate)
      case a: AttrSetManDuration       => updateSetEq(ent, attr, ref, a.vs, transformDuration, extsDuration, set2arrayDuration, value2jsonDuration)
      case a: AttrSetManInstant        => updateSetEq(ent, attr, ref, a.vs, transformInstant, extsInstant, set2arrayInstant, value2jsonInstant)
      case a: AttrSetManLocalDate      => updateSetEq(ent, attr, ref, a.vs, transformLocalDate, extsLocalDate, set2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSetManLocalTime      => updateSetEq(ent, attr, ref, a.vs, transformLocalTime, extsLocalTime, set2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSetManLocalDateTime  => updateSetEq(ent, attr, ref, a.vs, transformLocalDateTime, extsLocalDateTime, set2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSetManOffsetTime     => updateSetEq(ent, attr, ref, a.vs, transformOffsetTime, extsOffsetTime, set2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSetManOffsetDateTime => updateSetEq(ent, attr, ref, a.vs, transformOffsetDateTime, extsOffsetDateTime, set2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSetManZonedDateTime  => updateSetEq(ent, attr, ref, a.vs, transformZonedDateTime, extsZonedDateTime, set2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSetManUUID           => updateSetEq(ent, attr, ref, a.vs, transformUUID, extsUUID, set2arrayUUID, value2jsonUUID)
      case a: AttrSetManURI            => updateSetEq(ent, attr, ref, a.vs, transformURI, extsURI, set2arrayURI, value2jsonURI)
      case a: AttrSetManByte           => updateSetEq(ent, attr, ref, a.vs, transformByte, extsByte, set2arrayByte, value2jsonByte)
      case a: AttrSetManShort          => updateSetEq(ent, attr, ref, a.vs, transformShort, extsShort, set2arrayShort, value2jsonShort)
      case a: AttrSetManChar           => updateSetEq(ent, attr, ref, a.vs, transformChar, extsChar, set2arrayChar, value2jsonChar)
    }
  }

  private def resolveAttrSetAdd(a: AttrSetMan): Unit = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrSetManID             => updateSetAdd(ent, attr, ref, a.vs, transformID, extsID, set2arrayID, value2jsonID)
      case a: AttrSetManString         => updateSetAdd(ent, attr, ref, a.vs, transformString, extsString, set2arrayString, value2jsonString)
      case a: AttrSetManInt            => updateSetAdd(ent, attr, ref, a.vs, transformInt, extsInt, set2arrayInt, value2jsonInt)
      case a: AttrSetManLong           => updateSetAdd(ent, attr, ref, a.vs, transformLong, extsLong, set2arrayLong, value2jsonLong)
      case a: AttrSetManFloat          => updateSetAdd(ent, attr, ref, a.vs, transformFloat, extsFloat, set2arrayFloat, value2jsonFloat)
      case a: AttrSetManDouble         => updateSetAdd(ent, attr, ref, a.vs, transformDouble, extsDouble, set2arrayDouble, value2jsonDouble)
      case a: AttrSetManBoolean        => updateSetAdd(ent, attr, ref, a.vs, transformBoolean, extsBoolean, set2arrayBoolean, value2jsonBoolean)
      case a: AttrSetManBigInt         => updateSetAdd(ent, attr, ref, a.vs, transformBigInt, extsBigInt, set2arrayBigInt, value2jsonBigInt)
      case a: AttrSetManBigDecimal     => updateSetAdd(ent, attr, ref, a.vs, transformBigDecimal, extsBigDecimal, set2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSetManDate           => updateSetAdd(ent, attr, ref, a.vs, transformDate, extsDate, set2arrayDate, value2jsonDate)
      case a: AttrSetManDuration       => updateSetAdd(ent, attr, ref, a.vs, transformDuration, extsDuration, set2arrayDuration, value2jsonDuration)
      case a: AttrSetManInstant        => updateSetAdd(ent, attr, ref, a.vs, transformInstant, extsInstant, set2arrayInstant, value2jsonInstant)
      case a: AttrSetManLocalDate      => updateSetAdd(ent, attr, ref, a.vs, transformLocalDate, extsLocalDate, set2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSetManLocalTime      => updateSetAdd(ent, attr, ref, a.vs, transformLocalTime, extsLocalTime, set2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSetManLocalDateTime  => updateSetAdd(ent, attr, ref, a.vs, transformLocalDateTime, extsLocalDateTime, set2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSetManOffsetTime     => updateSetAdd(ent, attr, ref, a.vs, transformOffsetTime, extsOffsetTime, set2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSetManOffsetDateTime => updateSetAdd(ent, attr, ref, a.vs, transformOffsetDateTime, extsOffsetDateTime, set2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSetManZonedDateTime  => updateSetAdd(ent, attr, ref, a.vs, transformZonedDateTime, extsZonedDateTime, set2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSetManUUID           => updateSetAdd(ent, attr, ref, a.vs, transformUUID, extsUUID, set2arrayUUID, value2jsonUUID)
      case a: AttrSetManURI            => updateSetAdd(ent, attr, ref, a.vs, transformURI, extsURI, set2arrayURI, value2jsonURI)
      case a: AttrSetManByte           => updateSetAdd(ent, attr, ref, a.vs, transformByte, extsByte, set2arrayByte, value2jsonByte)
      case a: AttrSetManShort          => updateSetAdd(ent, attr, ref, a.vs, transformShort, extsShort, set2arrayShort, value2jsonShort)
      case a: AttrSetManChar           => updateSetAdd(ent, attr, ref, a.vs, transformChar, extsChar, set2arrayChar, value2jsonChar)
    }
  }

  private def resolveAttrSetRemove(a: AttrSetMan): Unit = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrSetManID             => updateSetRemove(ent, attr, ref, a.vs, transformID, extsID, one2jsonID, set2arrayID)
      case a: AttrSetManString         => updateSetRemove(ent, attr, ref, a.vs, transformString, extsString, one2jsonString, set2arrayString)
      case a: AttrSetManInt            => updateSetRemove(ent, attr, ref, a.vs, transformInt, extsInt, one2jsonInt, set2arrayInt)
      case a: AttrSetManLong           => updateSetRemove(ent, attr, ref, a.vs, transformLong, extsLong, one2jsonLong, set2arrayLong)
      case a: AttrSetManFloat          => updateSetRemove(ent, attr, ref, a.vs, transformFloat, extsFloat, one2jsonFloat, set2arrayFloat)
      case a: AttrSetManDouble         => updateSetRemove(ent, attr, ref, a.vs, transformDouble, extsDouble, one2jsonDouble, set2arrayDouble)
      case a: AttrSetManBoolean        => updateSetRemove(ent, attr, ref, a.vs, transformBoolean, extsBoolean, one2jsonBoolean, set2arrayBoolean)
      case a: AttrSetManBigInt         => updateSetRemove(ent, attr, ref, a.vs, transformBigInt, extsBigInt, one2jsonBigInt, set2arrayBigInt)
      case a: AttrSetManBigDecimal     => updateSetRemove(ent, attr, ref, a.vs, transformBigDecimal, extsBigDecimal, one2jsonBigDecimal, set2arrayBigDecimal)
      case a: AttrSetManDate           => updateSetRemove(ent, attr, ref, a.vs, transformDate, extsDate, one2jsonDate, set2arrayDate)
      case a: AttrSetManDuration       => updateSetRemove(ent, attr, ref, a.vs, transformDuration, extsDuration, one2jsonDuration, set2arrayDuration)
      case a: AttrSetManInstant        => updateSetRemove(ent, attr, ref, a.vs, transformInstant, extsInstant, one2jsonInstant, set2arrayInstant)
      case a: AttrSetManLocalDate      => updateSetRemove(ent, attr, ref, a.vs, transformLocalDate, extsLocalDate, one2jsonLocalDate, set2arrayLocalDate)
      case a: AttrSetManLocalTime      => updateSetRemove(ent, attr, ref, a.vs, transformLocalTime, extsLocalTime, one2jsonLocalTime, set2arrayLocalTime)
      case a: AttrSetManLocalDateTime  => updateSetRemove(ent, attr, ref, a.vs, transformLocalDateTime, extsLocalDateTime, one2jsonLocalDateTime, set2arrayLocalDateTime)
      case a: AttrSetManOffsetTime     => updateSetRemove(ent, attr, ref, a.vs, transformOffsetTime, extsOffsetTime, one2jsonOffsetTime, set2arrayOffsetTime)
      case a: AttrSetManOffsetDateTime => updateSetRemove(ent, attr, ref, a.vs, transformOffsetDateTime, extsOffsetDateTime, one2jsonOffsetDateTime, set2arrayOffsetDateTime)
      case a: AttrSetManZonedDateTime  => updateSetRemove(ent, attr, ref, a.vs, transformZonedDateTime, extsZonedDateTime, one2jsonZonedDateTime, set2arrayZonedDateTime)
      case a: AttrSetManUUID           => updateSetRemove(ent, attr, ref, a.vs, transformUUID, extsUUID, one2jsonUUID, set2arrayUUID)
      case a: AttrSetManURI            => updateSetRemove(ent, attr, ref, a.vs, transformURI, extsURI, one2jsonURI, set2arrayURI)
      case a: AttrSetManByte           => updateSetRemove(ent, attr, ref, a.vs, transformByte, extsByte, one2jsonByte, set2arrayByte)
      case a: AttrSetManShort          => updateSetRemove(ent, attr, ref, a.vs, transformShort, extsShort, one2jsonShort, set2arrayShort)
      case a: AttrSetManChar           => updateSetRemove(ent, attr, ref, a.vs, transformChar, extsChar, one2jsonChar, set2arrayChar)
    }
  }

  private def resolveAttrSeqMan(a: AttrSeqMan): Unit = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrSeqManID             => updateSeqEq(ent, attr, ref, a.vs, transformID, extsID, seq2arrayID, value2jsonID)
      case a: AttrSeqManString         => updateSeqEq(ent, attr, ref, a.vs, transformString, extsString, seq2arrayString, value2jsonString)
      case a: AttrSeqManInt            => updateSeqEq(ent, attr, ref, a.vs, transformInt, extsInt, seq2arrayInt, value2jsonInt)
      case a: AttrSeqManLong           => updateSeqEq(ent, attr, ref, a.vs, transformLong, extsLong, seq2arrayLong, value2jsonLong)
      case a: AttrSeqManFloat          => updateSeqEq(ent, attr, ref, a.vs, transformFloat, extsFloat, seq2arrayFloat, value2jsonFloat)
      case a: AttrSeqManDouble         => updateSeqEq(ent, attr, ref, a.vs, transformDouble, extsDouble, seq2arrayDouble, value2jsonDouble)
      case a: AttrSeqManBoolean        => updateSeqEq(ent, attr, ref, a.vs, transformBoolean, extsBoolean, seq2arrayBoolean, value2jsonBoolean)
      case a: AttrSeqManBigInt         => updateSeqEq(ent, attr, ref, a.vs, transformBigInt, extsBigInt, seq2arrayBigInt, value2jsonBigInt)
      case a: AttrSeqManBigDecimal     => updateSeqEq(ent, attr, ref, a.vs, transformBigDecimal, extsBigDecimal, seq2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSeqManDate           => updateSeqEq(ent, attr, ref, a.vs, transformDate, extsDate, seq2arrayDate, value2jsonDate)
      case a: AttrSeqManDuration       => updateSeqEq(ent, attr, ref, a.vs, transformDuration, extsDuration, seq2arrayDuration, value2jsonDuration)
      case a: AttrSeqManInstant        => updateSeqEq(ent, attr, ref, a.vs, transformInstant, extsInstant, seq2arrayInstant, value2jsonInstant)
      case a: AttrSeqManLocalDate      => updateSeqEq(ent, attr, ref, a.vs, transformLocalDate, extsLocalDate, seq2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSeqManLocalTime      => updateSeqEq(ent, attr, ref, a.vs, transformLocalTime, extsLocalTime, seq2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSeqManLocalDateTime  => updateSeqEq(ent, attr, ref, a.vs, transformLocalDateTime, extsLocalDateTime, seq2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSeqManOffsetTime     => updateSeqEq(ent, attr, ref, a.vs, transformOffsetTime, extsOffsetTime, seq2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSeqManOffsetDateTime => updateSeqEq(ent, attr, ref, a.vs, transformOffsetDateTime, extsOffsetDateTime, seq2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSeqManZonedDateTime  => updateSeqEq(ent, attr, ref, a.vs, transformZonedDateTime, extsZonedDateTime, seq2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSeqManUUID           => updateSeqEq(ent, attr, ref, a.vs, transformUUID, extsUUID, seq2arrayUUID, value2jsonUUID)
      case a: AttrSeqManURI            => updateSeqEq(ent, attr, ref, a.vs, transformURI, extsURI, seq2arrayURI, value2jsonURI)
      case a: AttrSeqManShort          => updateSeqEq(ent, attr, ref, a.vs, transformShort, extsShort, seq2arrayShort, value2jsonShort)
      case a: AttrSeqManChar           => updateSeqEq(ent, attr, ref, a.vs, transformChar, extsChar, seq2arrayChar, value2jsonChar)
      case a: AttrSeqManByte           => updateByteArray(ent, attr, a.vs)
    }
  }

  private def resolveAttrSeqAdd(a: AttrSeqMan): Unit = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrSeqManID             => updateSeqAdd(ent, attr, ref, a.vs, transformID, extsID, seq2arrayID, value2jsonID)
      case a: AttrSeqManString         => updateSeqAdd(ent, attr, ref, a.vs, transformString, extsString, seq2arrayString, value2jsonString)
      case a: AttrSeqManInt            => updateSeqAdd(ent, attr, ref, a.vs, transformInt, extsInt, seq2arrayInt, value2jsonInt)
      case a: AttrSeqManLong           => updateSeqAdd(ent, attr, ref, a.vs, transformLong, extsLong, seq2arrayLong, value2jsonLong)
      case a: AttrSeqManFloat          => updateSeqAdd(ent, attr, ref, a.vs, transformFloat, extsFloat, seq2arrayFloat, value2jsonFloat)
      case a: AttrSeqManDouble         => updateSeqAdd(ent, attr, ref, a.vs, transformDouble, extsDouble, seq2arrayDouble, value2jsonDouble)
      case a: AttrSeqManBoolean        => updateSeqAdd(ent, attr, ref, a.vs, transformBoolean, extsBoolean, seq2arrayBoolean, value2jsonBoolean)
      case a: AttrSeqManBigInt         => updateSeqAdd(ent, attr, ref, a.vs, transformBigInt, extsBigInt, seq2arrayBigInt, value2jsonBigInt)
      case a: AttrSeqManBigDecimal     => updateSeqAdd(ent, attr, ref, a.vs, transformBigDecimal, extsBigDecimal, seq2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSeqManDate           => updateSeqAdd(ent, attr, ref, a.vs, transformDate, extsDate, seq2arrayDate, value2jsonDate)
      case a: AttrSeqManDuration       => updateSeqAdd(ent, attr, ref, a.vs, transformDuration, extsDuration, seq2arrayDuration, value2jsonDuration)
      case a: AttrSeqManInstant        => updateSeqAdd(ent, attr, ref, a.vs, transformInstant, extsInstant, seq2arrayInstant, value2jsonInstant)
      case a: AttrSeqManLocalDate      => updateSeqAdd(ent, attr, ref, a.vs, transformLocalDate, extsLocalDate, seq2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSeqManLocalTime      => updateSeqAdd(ent, attr, ref, a.vs, transformLocalTime, extsLocalTime, seq2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSeqManLocalDateTime  => updateSeqAdd(ent, attr, ref, a.vs, transformLocalDateTime, extsLocalDateTime, seq2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSeqManOffsetTime     => updateSeqAdd(ent, attr, ref, a.vs, transformOffsetTime, extsOffsetTime, seq2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSeqManOffsetDateTime => updateSeqAdd(ent, attr, ref, a.vs, transformOffsetDateTime, extsOffsetDateTime, seq2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSeqManZonedDateTime  => updateSeqAdd(ent, attr, ref, a.vs, transformZonedDateTime, extsZonedDateTime, seq2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSeqManUUID           => updateSeqAdd(ent, attr, ref, a.vs, transformUUID, extsUUID, seq2arrayUUID, value2jsonUUID)
      case a: AttrSeqManURI            => updateSeqAdd(ent, attr, ref, a.vs, transformURI, extsURI, seq2arrayURI, value2jsonURI)
      case a: AttrSeqManShort          => updateSeqAdd(ent, attr, ref, a.vs, transformShort, extsShort, seq2arrayShort, value2jsonShort)
      case a: AttrSeqManChar           => updateSeqAdd(ent, attr, ref, a.vs, transformChar, extsChar, seq2arrayChar, value2jsonChar)
      case a: AttrSeqManByte           => throw ModelError(s"Operations on byte arrays (${a.ent}.${a.attr}) not allowed.")
    }
  }

  private def resolveAttrSeqRemove(a: AttrSeqMan): Unit = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrSeqManID             => updateSeqRemove(ent, attr, ref, a.vs, transformID, extsID, one2jsonID, seq2arrayID)
      case a: AttrSeqManString         => updateSeqRemove(ent, attr, ref, a.vs, transformString, extsString, one2jsonString, seq2arrayString)
      case a: AttrSeqManInt            => updateSeqRemove(ent, attr, ref, a.vs, transformInt, extsInt, one2jsonInt, seq2arrayInt)
      case a: AttrSeqManLong           => updateSeqRemove(ent, attr, ref, a.vs, transformLong, extsLong, one2jsonLong, seq2arrayLong)
      case a: AttrSeqManFloat          => updateSeqRemove(ent, attr, ref, a.vs, transformFloat, extsFloat, one2jsonFloat, seq2arrayFloat)
      case a: AttrSeqManDouble         => updateSeqRemove(ent, attr, ref, a.vs, transformDouble, extsDouble, one2jsonDouble, seq2arrayDouble)
      case a: AttrSeqManBoolean        => updateSeqRemove(ent, attr, ref, a.vs, transformBoolean, extsBoolean, one2jsonBoolean, seq2arrayBoolean)
      case a: AttrSeqManBigInt         => updateSeqRemove(ent, attr, ref, a.vs, transformBigInt, extsBigInt, one2jsonBigInt, seq2arrayBigInt)
      case a: AttrSeqManBigDecimal     => updateSeqRemove(ent, attr, ref, a.vs, transformBigDecimal, extsBigDecimal, one2jsonBigDecimal, seq2arrayBigDecimal)
      case a: AttrSeqManDate           => updateSeqRemove(ent, attr, ref, a.vs, transformDate, extsDate, one2jsonDate, seq2arrayDate)
      case a: AttrSeqManDuration       => updateSeqRemove(ent, attr, ref, a.vs, transformDuration, extsDuration, one2jsonDuration, seq2arrayDuration)
      case a: AttrSeqManInstant        => updateSeqRemove(ent, attr, ref, a.vs, transformInstant, extsInstant, one2jsonInstant, seq2arrayInstant)
      case a: AttrSeqManLocalDate      => updateSeqRemove(ent, attr, ref, a.vs, transformLocalDate, extsLocalDate, one2jsonLocalDate, seq2arrayLocalDate)
      case a: AttrSeqManLocalTime      => updateSeqRemove(ent, attr, ref, a.vs, transformLocalTime, extsLocalTime, one2jsonLocalTime, seq2arrayLocalTime)
      case a: AttrSeqManLocalDateTime  => updateSeqRemove(ent, attr, ref, a.vs, transformLocalDateTime, extsLocalDateTime, one2jsonLocalDateTime, seq2arrayLocalDateTime)
      case a: AttrSeqManOffsetTime     => updateSeqRemove(ent, attr, ref, a.vs, transformOffsetTime, extsOffsetTime, one2jsonOffsetTime, seq2arrayOffsetTime)
      case a: AttrSeqManOffsetDateTime => updateSeqRemove(ent, attr, ref, a.vs, transformOffsetDateTime, extsOffsetDateTime, one2jsonOffsetDateTime, seq2arrayOffsetDateTime)
      case a: AttrSeqManZonedDateTime  => updateSeqRemove(ent, attr, ref, a.vs, transformZonedDateTime, extsZonedDateTime, one2jsonZonedDateTime, seq2arrayZonedDateTime)
      case a: AttrSeqManUUID           => updateSeqRemove(ent, attr, ref, a.vs, transformUUID, extsUUID, one2jsonUUID, seq2arrayUUID)
      case a: AttrSeqManURI            => updateSeqRemove(ent, attr, ref, a.vs, transformURI, extsURI, one2jsonURI, seq2arrayURI)
      case a: AttrSeqManShort          => updateSeqRemove(ent, attr, ref, a.vs, transformShort, extsShort, one2jsonShort, seq2arrayShort)
      case a: AttrSeqManChar           => updateSeqRemove(ent, attr, ref, a.vs, transformChar, extsChar, one2jsonChar, seq2arrayChar)
      case a: AttrSeqManByte           => throw ModelError(s"Operations on byte arrays (${a.ent}.${a.attr}) not allowed.")
    }
  }


  private def resolveAttrMapMan(a: AttrMapMan): Unit = {
    val (ent, attr, ref, noValue) = (a.ent, a.attr, a.ref, a.op == NoValue)
    a match {
      case a: AttrMapManID             => updateMapEq(ent, attr, ref, noValue, a.map, transformID, value2jsonID)
      case a: AttrMapManString         => updateMapEq(ent, attr, ref, noValue, a.map, transformString, value2jsonString)
      case a: AttrMapManInt            => updateMapEq(ent, attr, ref, noValue, a.map, transformInt, value2jsonInt)
      case a: AttrMapManLong           => updateMapEq(ent, attr, ref, noValue, a.map, transformLong, value2jsonLong)
      case a: AttrMapManFloat          => updateMapEq(ent, attr, ref, noValue, a.map, transformFloat, value2jsonFloat)
      case a: AttrMapManDouble         => updateMapEq(ent, attr, ref, noValue, a.map, transformDouble, value2jsonDouble)
      case a: AttrMapManBoolean        => updateMapEq(ent, attr, ref, noValue, a.map, transformBoolean, value2jsonBoolean)
      case a: AttrMapManBigInt         => updateMapEq(ent, attr, ref, noValue, a.map, transformBigInt, value2jsonBigInt)
      case a: AttrMapManBigDecimal     => updateMapEq(ent, attr, ref, noValue, a.map, transformBigDecimal, value2jsonBigDecimal)
      case a: AttrMapManDate           => updateMapEq(ent, attr, ref, noValue, a.map, transformDate, value2jsonDate)
      case a: AttrMapManDuration       => updateMapEq(ent, attr, ref, noValue, a.map, transformDuration, value2jsonDuration)
      case a: AttrMapManInstant        => updateMapEq(ent, attr, ref, noValue, a.map, transformInstant, value2jsonInstant)
      case a: AttrMapManLocalDate      => updateMapEq(ent, attr, ref, noValue, a.map, transformLocalDate, value2jsonLocalDate)
      case a: AttrMapManLocalTime      => updateMapEq(ent, attr, ref, noValue, a.map, transformLocalTime, value2jsonLocalTime)
      case a: AttrMapManLocalDateTime  => updateMapEq(ent, attr, ref, noValue, a.map, transformLocalDateTime, value2jsonLocalDateTime)
      case a: AttrMapManOffsetTime     => updateMapEq(ent, attr, ref, noValue, a.map, transformOffsetTime, value2jsonOffsetTime)
      case a: AttrMapManOffsetDateTime => updateMapEq(ent, attr, ref, noValue, a.map, transformOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrMapManZonedDateTime  => updateMapEq(ent, attr, ref, noValue, a.map, transformZonedDateTime, value2jsonZonedDateTime)
      case a: AttrMapManUUID           => updateMapEq(ent, attr, ref, noValue, a.map, transformUUID, value2jsonUUID)
      case a: AttrMapManURI            => updateMapEq(ent, attr, ref, noValue, a.map, transformURI, value2jsonURI)
      case a: AttrMapManByte           => updateMapEq(ent, attr, ref, noValue, a.map, transformByte, value2jsonByte)
      case a: AttrMapManShort          => updateMapEq(ent, attr, ref, noValue, a.map, transformShort, value2jsonShort)
      case a: AttrMapManChar           => updateMapEq(ent, attr, ref, noValue, a.map, transformChar, value2jsonChar)
    }
  }

  private def resolveAttrMapAdd(a: AttrMapMan): Unit = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrMapManID             => updateMapAdd(ent, attr, ref, a.map, transformID, extsID, value2jsonID)
      case a: AttrMapManString         => updateMapAdd(ent, attr, ref, a.map, transformString, extsString, value2jsonString)
      case a: AttrMapManInt            => updateMapAdd(ent, attr, ref, a.map, transformInt, extsInt, value2jsonInt)
      case a: AttrMapManLong           => updateMapAdd(ent, attr, ref, a.map, transformLong, extsLong, value2jsonLong)
      case a: AttrMapManFloat          => updateMapAdd(ent, attr, ref, a.map, transformFloat, extsFloat, value2jsonFloat)
      case a: AttrMapManDouble         => updateMapAdd(ent, attr, ref, a.map, transformDouble, extsDouble, value2jsonDouble)
      case a: AttrMapManBoolean        => updateMapAdd(ent, attr, ref, a.map, transformBoolean, extsBoolean, value2jsonBoolean)
      case a: AttrMapManBigInt         => updateMapAdd(ent, attr, ref, a.map, transformBigInt, extsBigInt, value2jsonBigInt)
      case a: AttrMapManBigDecimal     => updateMapAdd(ent, attr, ref, a.map, transformBigDecimal, extsBigDecimal, value2jsonBigDecimal)
      case a: AttrMapManDate           => updateMapAdd(ent, attr, ref, a.map, transformDate, extsDate, value2jsonDate)
      case a: AttrMapManDuration       => updateMapAdd(ent, attr, ref, a.map, transformDuration, extsDuration, value2jsonDuration)
      case a: AttrMapManInstant        => updateMapAdd(ent, attr, ref, a.map, transformInstant, extsInstant, value2jsonInstant)
      case a: AttrMapManLocalDate      => updateMapAdd(ent, attr, ref, a.map, transformLocalDate, extsLocalDate, value2jsonLocalDate)
      case a: AttrMapManLocalTime      => updateMapAdd(ent, attr, ref, a.map, transformLocalTime, extsLocalTime, value2jsonLocalTime)
      case a: AttrMapManLocalDateTime  => updateMapAdd(ent, attr, ref, a.map, transformLocalDateTime, extsLocalDateTime, value2jsonLocalDateTime)
      case a: AttrMapManOffsetTime     => updateMapAdd(ent, attr, ref, a.map, transformOffsetTime, extsOffsetTime, value2jsonOffsetTime)
      case a: AttrMapManOffsetDateTime => updateMapAdd(ent, attr, ref, a.map, transformOffsetDateTime, extsOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrMapManZonedDateTime  => updateMapAdd(ent, attr, ref, a.map, transformZonedDateTime, extsZonedDateTime, value2jsonZonedDateTime)
      case a: AttrMapManUUID           => updateMapAdd(ent, attr, ref, a.map, transformUUID, extsUUID, value2jsonUUID)
      case a: AttrMapManURI            => updateMapAdd(ent, attr, ref, a.map, transformURI, extsURI, value2jsonURI)
      case a: AttrMapManByte           => updateMapAdd(ent, attr, ref, a.map, transformByte, extsByte, value2jsonByte)
      case a: AttrMapManShort          => updateMapAdd(ent, attr, ref, a.map, transformShort, extsShort, value2jsonShort)
      case a: AttrMapManChar           => updateMapAdd(ent, attr, ref, a.map, transformChar, extsChar, value2jsonChar)
    }
  }

  private def resolveAttrMapRemove(a: AttrMapMan): Unit = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrMapManID             => updateMapRemove(ent, attr, ref, a.keys, extsID)
      case a: AttrMapManString         => updateMapRemove(ent, attr, ref, a.keys, extsString)
      case a: AttrMapManInt            => updateMapRemove(ent, attr, ref, a.keys, extsInt)
      case a: AttrMapManLong           => updateMapRemove(ent, attr, ref, a.keys, extsLong)
      case a: AttrMapManFloat          => updateMapRemove(ent, attr, ref, a.keys, extsFloat)
      case a: AttrMapManDouble         => updateMapRemove(ent, attr, ref, a.keys, extsDouble)
      case a: AttrMapManBoolean        => updateMapRemove(ent, attr, ref, a.keys, extsBoolean)
      case a: AttrMapManBigInt         => updateMapRemove(ent, attr, ref, a.keys, extsBigInt)
      case a: AttrMapManBigDecimal     => updateMapRemove(ent, attr, ref, a.keys, extsBigDecimal)
      case a: AttrMapManDate           => updateMapRemove(ent, attr, ref, a.keys, extsDate)
      case a: AttrMapManDuration       => updateMapRemove(ent, attr, ref, a.keys, extsDuration)
      case a: AttrMapManInstant        => updateMapRemove(ent, attr, ref, a.keys, extsInstant)
      case a: AttrMapManLocalDate      => updateMapRemove(ent, attr, ref, a.keys, extsLocalDate)
      case a: AttrMapManLocalTime      => updateMapRemove(ent, attr, ref, a.keys, extsLocalTime)
      case a: AttrMapManLocalDateTime  => updateMapRemove(ent, attr, ref, a.keys, extsLocalDateTime)
      case a: AttrMapManOffsetTime     => updateMapRemove(ent, attr, ref, a.keys, extsOffsetTime)
      case a: AttrMapManOffsetDateTime => updateMapRemove(ent, attr, ref, a.keys, extsOffsetDateTime)
      case a: AttrMapManZonedDateTime  => updateMapRemove(ent, attr, ref, a.keys, extsZonedDateTime)
      case a: AttrMapManUUID           => updateMapRemove(ent, attr, ref, a.keys, extsUUID)
      case a: AttrMapManURI            => updateMapRemove(ent, attr, ref, a.keys, extsURI)
      case a: AttrMapManByte           => updateMapRemove(ent, attr, ref, a.keys, extsByte)
      case a: AttrMapManShort          => updateMapRemove(ent, attr, ref, a.keys, extsShort)
      case a: AttrMapManChar           => updateMapRemove(ent, attr, ref, a.keys, extsChar)
    }
  }
}