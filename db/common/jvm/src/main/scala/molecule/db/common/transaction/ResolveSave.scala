package molecule.db.common.transaction

import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.dataModel.*
import molecule.db.common.transaction.ops.SaveOps
import scala.annotation.tailrec

trait ResolveSave { self: SaveOps =>

  @tailrec
  final def resolve(elements: List[Element]): Unit = {
    elements match {
      case element :: tail => element match {
        case a: Attr =>
          if (a.op != Eq) {
            throw ModelError(s"Missing applied value for attribute ${a.ent}.${a.attr}")
          }
          a match {
            case a: AttrOne => a match {
              case a: AttrOneMan => resolveAttrOneMan(a); resolve(tail)
              case a: AttrOneOpt => resolveAttrOneOpt(a); resolve(tail)
              case a: AttrOneTac => resolveAttrOneTac(a); resolve(tail)
            }

            case a: AttrSet => a match {
              case a: AttrSetMan => resolveAttrSetMan(a); resolve(tail)
              case a: AttrSetOpt => resolveAttrSetOpt(a); resolve(tail)
              case a: AttrSetTac => resolveAttrSetTac(a); resolve(tail)
            }

            case a: AttrSeq => a match {
              case a: AttrSeqMan => resolveAttrSeqMan(a); resolve(tail)
              case a: AttrSeqOpt => resolveAttrSeqOpt(a); resolve(tail)
              case a: AttrSeqTac => resolveAttrSeqTac(a); resolve(tail)
            }

            case a: AttrMap => a match {
              case a: AttrMapMan => resolveAttrMapMan(a); resolve(tail)
              case a: AttrMapOpt => resolveAttrMapOpt(a); resolve(tail)
              case a: AttrMapTac => resolveAttrMapTac(a); resolve(tail)
            }
          }

        case Ref(ent, refAttr, ref, card, _, _) =>
          addRef(ent, refAttr, ref, card)
          resolve(tail)

        case BackRef(backRef, _, _) =>
          addBackRef(backRef)
          resolve(tail)

        case _: OptRef => throw ModelError(
          "Optional ref not allowed in save molecule. Please use mandatory ref or insert instead."
        )

        case _: OptEntity => throw ModelError(
          "Optional entity not allowed in save molecule. Please use mandatory entity or insert instead."
        )

        case _: Nested    => throw ModelError(
          "Nested data structure not allowed in save molecule. Please use insert instead."
        )
        case _: OptNested => throw ModelError(
          "Optional nested data structure not allowed in save molecule. Please use insert instead."
        )
      }
      case Nil             => ()
    }
  }

  private def oneV[T](
    ent: String,
    attr: String,
    vs: Seq[T],
  ): Option[T] = {
    vs match {
      case Seq(v) => Some(v)
      case Nil    => None
      case vs     => throw ExecutionError(
        s"Can only save one value for attribute `$ent.$attr`. Found: " + vs.mkString(", ")
      )
    }
  }
  private def resolveAttrOneMan(a: AttrOneMan): Unit = {
    val (ent, attr) = (a.ent, a.attr)
    a match {
      case a: AttrOneManID             => addOne(ent, attr, oneV(ent, attr, a.vs), transformID, extsID)
      case a: AttrOneManString         => addOne(ent, attr, oneV(ent, attr, a.vs), transformString, extsString)
      case a: AttrOneManInt            => addOne(ent, attr, oneV(ent, attr, a.vs), transformInt, extsInt)
      case a: AttrOneManLong           => addOne(ent, attr, oneV(ent, attr, a.vs), transformLong, extsLong)
      case a: AttrOneManFloat          => addOne(ent, attr, oneV(ent, attr, a.vs), transformFloat, extsFloat)
      case a: AttrOneManDouble         => addOne(ent, attr, oneV(ent, attr, a.vs), transformDouble, extsDouble)
      case a: AttrOneManBoolean        => addOne(ent, attr, oneV(ent, attr, a.vs), transformBoolean, extsBoolean)
      case a: AttrOneManBigInt         => addOne(ent, attr, oneV(ent, attr, a.vs), transformBigInt, extsBigInt)
      case a: AttrOneManBigDecimal     => addOne(ent, attr, oneV(ent, attr, a.vs), transformBigDecimal, extsBigDecimal)
      case a: AttrOneManDate           => addOne(ent, attr, oneV(ent, attr, a.vs), transformDate, extsDate)
      case a: AttrOneManDuration       => addOne(ent, attr, oneV(ent, attr, a.vs), transformDuration, extsDuration)
      case a: AttrOneManInstant        => addOne(ent, attr, oneV(ent, attr, a.vs), transformInstant, extsInstant)
      case a: AttrOneManLocalDate      => addOne(ent, attr, oneV(ent, attr, a.vs), transformLocalDate, extsLocalDate)
      case a: AttrOneManLocalTime      => addOne(ent, attr, oneV(ent, attr, a.vs), transformLocalTime, extsLocalTime)
      case a: AttrOneManLocalDateTime  => addOne(ent, attr, oneV(ent, attr, a.vs), transformLocalDateTime, extsLocalDateTime)
      case a: AttrOneManOffsetTime     => addOne(ent, attr, oneV(ent, attr, a.vs), transformOffsetTime, extsOffsetTime)
      case a: AttrOneManOffsetDateTime => addOne(ent, attr, oneV(ent, attr, a.vs), transformOffsetDateTime, extsOffsetDateTime)
      case a: AttrOneManZonedDateTime  => addOne(ent, attr, oneV(ent, attr, a.vs), transformZonedDateTime, extsZonedDateTime)
      case a: AttrOneManUUID           => addOne(ent, attr, oneV(ent, attr, a.vs), transformUUID, extsUUID)
      case a: AttrOneManURI            => addOne(ent, attr, oneV(ent, attr, a.vs), transformURI, extsURI)
      case a: AttrOneManByte           => addOne(ent, attr, oneV(ent, attr, a.vs), transformByte, extsByte)
      case a: AttrOneManShort          => addOne(ent, attr, oneV(ent, attr, a.vs), transformShort, extsShort)
      case a: AttrOneManChar           => addOne(ent, attr, oneV(ent, attr, a.vs), transformChar, extsChar)
    }
  }
  private def resolveAttrOneTac(a: AttrOneTac): Unit = {
    val (ent, attr) = (a.ent, a.attr)
    a match {
      case a: AttrOneTacID             => addOne(ent, attr, oneV(ent, attr, a.vs), transformID, extsID)
      case a: AttrOneTacString         => addOne(ent, attr, oneV(ent, attr, a.vs), transformString, extsString)
      case a: AttrOneTacInt            => addOne(ent, attr, oneV(ent, attr, a.vs), transformInt, extsInt)
      case a: AttrOneTacLong           => addOne(ent, attr, oneV(ent, attr, a.vs), transformLong, extsLong)
      case a: AttrOneTacFloat          => addOne(ent, attr, oneV(ent, attr, a.vs), transformFloat, extsFloat)
      case a: AttrOneTacDouble         => addOne(ent, attr, oneV(ent, attr, a.vs), transformDouble, extsDouble)
      case a: AttrOneTacBoolean        => addOne(ent, attr, oneV(ent, attr, a.vs), transformBoolean, extsBoolean)
      case a: AttrOneTacBigInt         => addOne(ent, attr, oneV(ent, attr, a.vs), transformBigInt, extsBigInt)
      case a: AttrOneTacBigDecimal     => addOne(ent, attr, oneV(ent, attr, a.vs), transformBigDecimal, extsBigDecimal)
      case a: AttrOneTacDate           => addOne(ent, attr, oneV(ent, attr, a.vs), transformDate, extsDate)
      case a: AttrOneTacDuration       => addOne(ent, attr, oneV(ent, attr, a.vs), transformDuration, extsDuration)
      case a: AttrOneTacInstant        => addOne(ent, attr, oneV(ent, attr, a.vs), transformInstant, extsInstant)
      case a: AttrOneTacLocalDate      => addOne(ent, attr, oneV(ent, attr, a.vs), transformLocalDate, extsLocalDate)
      case a: AttrOneTacLocalTime      => addOne(ent, attr, oneV(ent, attr, a.vs), transformLocalTime, extsLocalTime)
      case a: AttrOneTacLocalDateTime  => addOne(ent, attr, oneV(ent, attr, a.vs), transformLocalDateTime, extsLocalDateTime)
      case a: AttrOneTacOffsetTime     => addOne(ent, attr, oneV(ent, attr, a.vs), transformOffsetTime, extsOffsetTime)
      case a: AttrOneTacOffsetDateTime => addOne(ent, attr, oneV(ent, attr, a.vs), transformOffsetDateTime, extsOffsetDateTime)
      case a: AttrOneTacZonedDateTime  => addOne(ent, attr, oneV(ent, attr, a.vs), transformZonedDateTime, extsZonedDateTime)
      case a: AttrOneTacUUID           => addOne(ent, attr, oneV(ent, attr, a.vs), transformUUID, extsUUID)
      case a: AttrOneTacURI            => addOne(ent, attr, oneV(ent, attr, a.vs), transformURI, extsURI)
      case a: AttrOneTacByte           => addOne(ent, attr, oneV(ent, attr, a.vs), transformByte, extsByte)
      case a: AttrOneTacShort          => addOne(ent, attr, oneV(ent, attr, a.vs), transformShort, extsShort)
      case a: AttrOneTacChar           => addOne(ent, attr, oneV(ent, attr, a.vs), transformChar, extsChar)
    }
  }


  private def oneOptV[T](
    ent: String,
    attr: String,
    optVs: Option[Seq[T]],
  ): Option[T] = {
    optVs.flatMap {
      case Seq(v) => Some(v)
      case Nil    => None
      case vs     => throw ExecutionError(
        s"Can only save one value for optional attribute `$ent.$attr`. Found: " + vs.mkString(", ")
      )
    }
  }
  private def resolveAttrOneOpt(a: AttrOneOpt): Unit = {
    val (ent, attr) = (a.ent, a.attr)
    a match {
      case a: AttrOneOptID             => addOne(ent, attr, oneOptV(ent, attr, a.vs), transformID, extsID)
      case a: AttrOneOptString         => addOne(ent, attr, oneOptV(ent, attr, a.vs), transformString, extsString)
      case a: AttrOneOptInt            => addOne(ent, attr, oneOptV(ent, attr, a.vs), transformInt, extsInt)
      case a: AttrOneOptLong           => addOne(ent, attr, oneOptV(ent, attr, a.vs), transformLong, extsLong)
      case a: AttrOneOptFloat          => addOne(ent, attr, oneOptV(ent, attr, a.vs), transformFloat, extsFloat)
      case a: AttrOneOptDouble         => addOne(ent, attr, oneOptV(ent, attr, a.vs), transformDouble, extsDouble)
      case a: AttrOneOptBoolean        => addOne(ent, attr, oneOptV(ent, attr, a.vs), transformBoolean, extsBoolean)
      case a: AttrOneOptBigInt         => addOne(ent, attr, oneOptV(ent, attr, a.vs), transformBigInt, extsBigInt)
      case a: AttrOneOptBigDecimal     => addOne(ent, attr, oneOptV(ent, attr, a.vs), transformBigDecimal, extsBigDecimal)
      case a: AttrOneOptDate           => addOne(ent, attr, oneOptV(ent, attr, a.vs), transformDate, extsDate)
      case a: AttrOneOptDuration       => addOne(ent, attr, oneOptV(ent, attr, a.vs), transformDuration, extsDuration)
      case a: AttrOneOptInstant        => addOne(ent, attr, oneOptV(ent, attr, a.vs), transformInstant, extsInstant)
      case a: AttrOneOptLocalDate      => addOne(ent, attr, oneOptV(ent, attr, a.vs), transformLocalDate, extsLocalDate)
      case a: AttrOneOptLocalTime      => addOne(ent, attr, oneOptV(ent, attr, a.vs), transformLocalTime, extsLocalTime)
      case a: AttrOneOptLocalDateTime  => addOne(ent, attr, oneOptV(ent, attr, a.vs), transformLocalDateTime, extsLocalDateTime)
      case a: AttrOneOptOffsetTime     => addOne(ent, attr, oneOptV(ent, attr, a.vs), transformOffsetTime, extsOffsetTime)
      case a: AttrOneOptOffsetDateTime => addOne(ent, attr, oneOptV(ent, attr, a.vs), transformOffsetDateTime, extsOffsetDateTime)
      case a: AttrOneOptZonedDateTime  => addOne(ent, attr, oneOptV(ent, attr, a.vs), transformZonedDateTime, extsZonedDateTime)
      case a: AttrOneOptUUID           => addOne(ent, attr, oneOptV(ent, attr, a.vs), transformUUID, extsUUID)
      case a: AttrOneOptURI            => addOne(ent, attr, oneOptV(ent, attr, a.vs), transformURI, extsURI)
      case a: AttrOneOptByte           => addOne(ent, attr, oneOptV(ent, attr, a.vs), transformByte, extsByte)
      case a: AttrOneOptShort          => addOne(ent, attr, oneOptV(ent, attr, a.vs), transformShort, extsShort)
      case a: AttrOneOptChar           => addOne(ent, attr, oneOptV(ent, attr, a.vs), transformChar, extsChar)
    }
  }


  private def optSet[T](set: Set[T]): Option[Set[T]] = if (set.nonEmpty) Some(set) else None

  private def resolveAttrSetMan(a: AttrSetMan): Unit = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrSetManID             => addSet(ent, attr, ref, optSet(a.vs), transformID, extsID, set2arrayID, value2jsonID)
      case a: AttrSetManString         => addSet(ent, attr, ref, optSet(a.vs), transformString, extsString, set2arrayString, value2jsonString)
      case a: AttrSetManInt            => addSet(ent, attr, ref, optSet(a.vs), transformInt, extsInt, set2arrayInt, value2jsonInt)
      case a: AttrSetManLong           => addSet(ent, attr, ref, optSet(a.vs), transformLong, extsLong, set2arrayLong, value2jsonLong)
      case a: AttrSetManFloat          => addSet(ent, attr, ref, optSet(a.vs), transformFloat, extsFloat, set2arrayFloat, value2jsonFloat)
      case a: AttrSetManDouble         => addSet(ent, attr, ref, optSet(a.vs), transformDouble, extsDouble, set2arrayDouble, value2jsonDouble)
      case a: AttrSetManBoolean        => addSet(ent, attr, ref, optSet(a.vs), transformBoolean, extsBoolean, set2arrayBoolean, value2jsonBoolean)
      case a: AttrSetManBigInt         => addSet(ent, attr, ref, optSet(a.vs), transformBigInt, extsBigInt, set2arrayBigInt, value2jsonBigInt)
      case a: AttrSetManBigDecimal     => addSet(ent, attr, ref, optSet(a.vs), transformBigDecimal, extsBigDecimal, set2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSetManDate           => addSet(ent, attr, ref, optSet(a.vs), transformDate, extsDate, set2arrayDate, value2jsonDate)
      case a: AttrSetManDuration       => addSet(ent, attr, ref, optSet(a.vs), transformDuration, extsDuration, set2arrayDuration, value2jsonDuration)
      case a: AttrSetManInstant        => addSet(ent, attr, ref, optSet(a.vs), transformInstant, extsInstant, set2arrayInstant, value2jsonInstant)
      case a: AttrSetManLocalDate      => addSet(ent, attr, ref, optSet(a.vs), transformLocalDate, extsLocalDate, set2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSetManLocalTime      => addSet(ent, attr, ref, optSet(a.vs), transformLocalTime, extsLocalTime, set2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSetManLocalDateTime  => addSet(ent, attr, ref, optSet(a.vs), transformLocalDateTime, extsLocalDateTime, set2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSetManOffsetTime     => addSet(ent, attr, ref, optSet(a.vs), transformOffsetTime, extsOffsetTime, set2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSetManOffsetDateTime => addSet(ent, attr, ref, optSet(a.vs), transformOffsetDateTime, extsOffsetDateTime, set2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSetManZonedDateTime  => addSet(ent, attr, ref, optSet(a.vs), transformZonedDateTime, extsZonedDateTime, set2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSetManUUID           => addSet(ent, attr, ref, optSet(a.vs), transformUUID, extsUUID, set2arrayUUID, value2jsonUUID)
      case a: AttrSetManURI            => addSet(ent, attr, ref, optSet(a.vs), transformURI, extsURI, set2arrayURI, value2jsonURI)
      case a: AttrSetManByte           => addSet(ent, attr, ref, optSet(a.vs), transformByte, extsByte, set2arrayByte, value2jsonByte)
      case a: AttrSetManShort          => addSet(ent, attr, ref, optSet(a.vs), transformShort, extsShort, set2arrayShort, value2jsonShort)
      case a: AttrSetManChar           => addSet(ent, attr, ref, optSet(a.vs), transformChar, extsChar, set2arrayChar, value2jsonChar)
    }
  }

  private def resolveAttrSetTac(a: AttrSetTac): Unit = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrSetTacID             => addSet(ent, attr, ref, optSet(a.vs), transformID, extsID, set2arrayID, value2jsonID)
      case a: AttrSetTacString         => addSet(ent, attr, ref, optSet(a.vs), transformString, extsString, set2arrayString, value2jsonString)
      case a: AttrSetTacInt            => addSet(ent, attr, ref, optSet(a.vs), transformInt, extsInt, set2arrayInt, value2jsonInt)
      case a: AttrSetTacLong           => addSet(ent, attr, ref, optSet(a.vs), transformLong, extsLong, set2arrayLong, value2jsonLong)
      case a: AttrSetTacFloat          => addSet(ent, attr, ref, optSet(a.vs), transformFloat, extsFloat, set2arrayFloat, value2jsonFloat)
      case a: AttrSetTacDouble         => addSet(ent, attr, ref, optSet(a.vs), transformDouble, extsDouble, set2arrayDouble, value2jsonDouble)
      case a: AttrSetTacBoolean        => addSet(ent, attr, ref, optSet(a.vs), transformBoolean, extsBoolean, set2arrayBoolean, value2jsonBoolean)
      case a: AttrSetTacBigInt         => addSet(ent, attr, ref, optSet(a.vs), transformBigInt, extsBigInt, set2arrayBigInt, value2jsonBigInt)
      case a: AttrSetTacBigDecimal     => addSet(ent, attr, ref, optSet(a.vs), transformBigDecimal, extsBigDecimal, set2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSetTacDate           => addSet(ent, attr, ref, optSet(a.vs), transformDate, extsDate, set2arrayDate, value2jsonDate)
      case a: AttrSetTacDuration       => addSet(ent, attr, ref, optSet(a.vs), transformDuration, extsDuration, set2arrayDuration, value2jsonDuration)
      case a: AttrSetTacInstant        => addSet(ent, attr, ref, optSet(a.vs), transformInstant, extsInstant, set2arrayInstant, value2jsonInstant)
      case a: AttrSetTacLocalDate      => addSet(ent, attr, ref, optSet(a.vs), transformLocalDate, extsLocalDate, set2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSetTacLocalTime      => addSet(ent, attr, ref, optSet(a.vs), transformLocalTime, extsLocalTime, set2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSetTacLocalDateTime  => addSet(ent, attr, ref, optSet(a.vs), transformLocalDateTime, extsLocalDateTime, set2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSetTacOffsetTime     => addSet(ent, attr, ref, optSet(a.vs), transformOffsetTime, extsOffsetTime, set2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSetTacOffsetDateTime => addSet(ent, attr, ref, optSet(a.vs), transformOffsetDateTime, extsOffsetDateTime, set2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSetTacZonedDateTime  => addSet(ent, attr, ref, optSet(a.vs), transformZonedDateTime, extsZonedDateTime, set2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSetTacUUID           => addSet(ent, attr, ref, optSet(a.vs), transformUUID, extsUUID, set2arrayUUID, value2jsonUUID)
      case a: AttrSetTacURI            => addSet(ent, attr, ref, optSet(a.vs), transformURI, extsURI, set2arrayURI, value2jsonURI)
      case a: AttrSetTacByte           => addSet(ent, attr, ref, optSet(a.vs), transformByte, extsByte, set2arrayByte, value2jsonByte)
      case a: AttrSetTacShort          => addSet(ent, attr, ref, optSet(a.vs), transformShort, extsShort, set2arrayShort, value2jsonShort)
      case a: AttrSetTacChar           => addSet(ent, attr, ref, optSet(a.vs), transformChar, extsChar, set2arrayChar, value2jsonChar)
    }
  }

  private def resolveAttrSetOpt(a: AttrSetOpt): Unit = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrSetOptID             => addSet(ent, attr, ref, a.vs, transformID, extsID, set2arrayID, value2jsonID)
      case a: AttrSetOptString         => addSet(ent, attr, ref, a.vs, transformString, extsString, set2arrayString, value2jsonString)
      case a: AttrSetOptInt            => addSet(ent, attr, ref, a.vs, transformInt, extsInt, set2arrayInt, value2jsonInt)
      case a: AttrSetOptLong           => addSet(ent, attr, ref, a.vs, transformLong, extsLong, set2arrayLong, value2jsonLong)
      case a: AttrSetOptFloat          => addSet(ent, attr, ref, a.vs, transformFloat, extsFloat, set2arrayFloat, value2jsonFloat)
      case a: AttrSetOptDouble         => addSet(ent, attr, ref, a.vs, transformDouble, extsDouble, set2arrayDouble, value2jsonDouble)
      case a: AttrSetOptBoolean        => addSet(ent, attr, ref, a.vs, transformBoolean, extsBoolean, set2arrayBoolean, value2jsonBoolean)
      case a: AttrSetOptBigInt         => addSet(ent, attr, ref, a.vs, transformBigInt, extsBigInt, set2arrayBigInt, value2jsonBigInt)
      case a: AttrSetOptBigDecimal     => addSet(ent, attr, ref, a.vs, transformBigDecimal, extsBigDecimal, set2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSetOptDate           => addSet(ent, attr, ref, a.vs, transformDate, extsDate, set2arrayDate, value2jsonDate)
      case a: AttrSetOptDuration       => addSet(ent, attr, ref, a.vs, transformDuration, extsDuration, set2arrayDuration, value2jsonDuration)
      case a: AttrSetOptInstant        => addSet(ent, attr, ref, a.vs, transformInstant, extsInstant, set2arrayInstant, value2jsonInstant)
      case a: AttrSetOptLocalDate      => addSet(ent, attr, ref, a.vs, transformLocalDate, extsLocalDate, set2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSetOptLocalTime      => addSet(ent, attr, ref, a.vs, transformLocalTime, extsLocalTime, set2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSetOptLocalDateTime  => addSet(ent, attr, ref, a.vs, transformLocalDateTime, extsLocalDateTime, set2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSetOptOffsetTime     => addSet(ent, attr, ref, a.vs, transformOffsetTime, extsOffsetTime, set2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSetOptOffsetDateTime => addSet(ent, attr, ref, a.vs, transformOffsetDateTime, extsOffsetDateTime, set2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSetOptZonedDateTime  => addSet(ent, attr, ref, a.vs, transformZonedDateTime, extsZonedDateTime, set2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSetOptUUID           => addSet(ent, attr, ref, a.vs, transformUUID, extsUUID, set2arrayUUID, value2jsonUUID)
      case a: AttrSetOptURI            => addSet(ent, attr, ref, a.vs, transformURI, extsURI, set2arrayURI, value2jsonURI)
      case a: AttrSetOptByte           => addSet(ent, attr, ref, a.vs, transformByte, extsByte, set2arrayByte, value2jsonByte)
      case a: AttrSetOptShort          => addSet(ent, attr, ref, a.vs, transformShort, extsShort, set2arrayShort, value2jsonShort)
      case a: AttrSetOptChar           => addSet(ent, attr, ref, a.vs, transformChar, extsChar, set2arrayChar, value2jsonChar)
    }
  }

  private def optSeq[T](seq: Seq[T]): Option[Seq[T]] = if (seq.nonEmpty) Some(seq) else None

  private def resolveAttrSeqMan(a: AttrSeqMan): Unit = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrSeqManID             => addSeq(ent, attr, ref, optSeq(a.vs), transformID, extsID, seq2arrayID, value2jsonID)
      case a: AttrSeqManString         => addSeq(ent, attr, ref, optSeq(a.vs), transformString, extsString, seq2arrayString, value2jsonString)
      case a: AttrSeqManInt            => addSeq(ent, attr, ref, optSeq(a.vs), transformInt, extsInt, seq2arrayInt, value2jsonInt)
      case a: AttrSeqManLong           => addSeq(ent, attr, ref, optSeq(a.vs), transformLong, extsLong, seq2arrayLong, value2jsonLong)
      case a: AttrSeqManFloat          => addSeq(ent, attr, ref, optSeq(a.vs), transformFloat, extsFloat, seq2arrayFloat, value2jsonFloat)
      case a: AttrSeqManDouble         => addSeq(ent, attr, ref, optSeq(a.vs), transformDouble, extsDouble, seq2arrayDouble, value2jsonDouble)
      case a: AttrSeqManBoolean        => addSeq(ent, attr, ref, optSeq(a.vs), transformBoolean, extsBoolean, seq2arrayBoolean, value2jsonBoolean)
      case a: AttrSeqManBigInt         => addSeq(ent, attr, ref, optSeq(a.vs), transformBigInt, extsBigInt, seq2arrayBigInt, value2jsonBigInt)
      case a: AttrSeqManBigDecimal     => addSeq(ent, attr, ref, optSeq(a.vs), transformBigDecimal, extsBigDecimal, seq2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSeqManDate           => addSeq(ent, attr, ref, optSeq(a.vs), transformDate, extsDate, seq2arrayDate, value2jsonDate)
      case a: AttrSeqManDuration       => addSeq(ent, attr, ref, optSeq(a.vs), transformDuration, extsDuration, seq2arrayDuration, value2jsonDuration)
      case a: AttrSeqManInstant        => addSeq(ent, attr, ref, optSeq(a.vs), transformInstant, extsInstant, seq2arrayInstant, value2jsonInstant)
      case a: AttrSeqManLocalDate      => addSeq(ent, attr, ref, optSeq(a.vs), transformLocalDate, extsLocalDate, seq2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSeqManLocalTime      => addSeq(ent, attr, ref, optSeq(a.vs), transformLocalTime, extsLocalTime, seq2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSeqManLocalDateTime  => addSeq(ent, attr, ref, optSeq(a.vs), transformLocalDateTime, extsLocalDateTime, seq2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSeqManOffsetTime     => addSeq(ent, attr, ref, optSeq(a.vs), transformOffsetTime, extsOffsetTime, seq2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSeqManOffsetDateTime => addSeq(ent, attr, ref, optSeq(a.vs), transformOffsetDateTime, extsOffsetDateTime, seq2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSeqManZonedDateTime  => addSeq(ent, attr, ref, optSeq(a.vs), transformZonedDateTime, extsZonedDateTime, seq2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSeqManUUID           => addSeq(ent, attr, ref, optSeq(a.vs), transformUUID, extsUUID, seq2arrayUUID, value2jsonUUID)
      case a: AttrSeqManURI            => addSeq(ent, attr, ref, optSeq(a.vs), transformURI, extsURI, seq2arrayURI, value2jsonURI)
      case a: AttrSeqManByte           => addByteArray(ent, attr, optArray(a.vs))
      case a: AttrSeqManShort          => addSeq(ent, attr, ref, optSeq(a.vs), transformShort, extsShort, seq2arrayShort, value2jsonShort)
      case a: AttrSeqManChar           => addSeq(ent, attr, ref, optSeq(a.vs), transformChar, extsChar, seq2arrayChar, value2jsonChar)
    }
  }
  private def resolveAttrSeqTac(a: AttrSeqTac): Unit = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrSeqTacID             => addSeq(ent, attr, ref, optSeq(a.vs), transformID, extsID, seq2arrayID, value2jsonID)
      case a: AttrSeqTacString         => addSeq(ent, attr, ref, optSeq(a.vs), transformString, extsString, seq2arrayString, value2jsonString)
      case a: AttrSeqTacInt            => addSeq(ent, attr, ref, optSeq(a.vs), transformInt, extsInt, seq2arrayInt, value2jsonInt)
      case a: AttrSeqTacLong           => addSeq(ent, attr, ref, optSeq(a.vs), transformLong, extsLong, seq2arrayLong, value2jsonLong)
      case a: AttrSeqTacFloat          => addSeq(ent, attr, ref, optSeq(a.vs), transformFloat, extsFloat, seq2arrayFloat, value2jsonFloat)
      case a: AttrSeqTacDouble         => addSeq(ent, attr, ref, optSeq(a.vs), transformDouble, extsDouble, seq2arrayDouble, value2jsonDouble)
      case a: AttrSeqTacBoolean        => addSeq(ent, attr, ref, optSeq(a.vs), transformBoolean, extsBoolean, seq2arrayBoolean, value2jsonBoolean)
      case a: AttrSeqTacBigInt         => addSeq(ent, attr, ref, optSeq(a.vs), transformBigInt, extsBigInt, seq2arrayBigInt, value2jsonBigInt)
      case a: AttrSeqTacBigDecimal     => addSeq(ent, attr, ref, optSeq(a.vs), transformBigDecimal, extsBigDecimal, seq2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSeqTacDate           => addSeq(ent, attr, ref, optSeq(a.vs), transformDate, extsDate, seq2arrayDate, value2jsonDate)
      case a: AttrSeqTacDuration       => addSeq(ent, attr, ref, optSeq(a.vs), transformDuration, extsDuration, seq2arrayDuration, value2jsonDuration)
      case a: AttrSeqTacInstant        => addSeq(ent, attr, ref, optSeq(a.vs), transformInstant, extsInstant, seq2arrayInstant, value2jsonInstant)
      case a: AttrSeqTacLocalDate      => addSeq(ent, attr, ref, optSeq(a.vs), transformLocalDate, extsLocalDate, seq2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSeqTacLocalTime      => addSeq(ent, attr, ref, optSeq(a.vs), transformLocalTime, extsLocalTime, seq2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSeqTacLocalDateTime  => addSeq(ent, attr, ref, optSeq(a.vs), transformLocalDateTime, extsLocalDateTime, seq2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSeqTacOffsetTime     => addSeq(ent, attr, ref, optSeq(a.vs), transformOffsetTime, extsOffsetTime, seq2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSeqTacOffsetDateTime => addSeq(ent, attr, ref, optSeq(a.vs), transformOffsetDateTime, extsOffsetDateTime, seq2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSeqTacZonedDateTime  => addSeq(ent, attr, ref, optSeq(a.vs), transformZonedDateTime, extsZonedDateTime, seq2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSeqTacUUID           => addSeq(ent, attr, ref, optSeq(a.vs), transformUUID, extsUUID, seq2arrayUUID, value2jsonUUID)
      case a: AttrSeqTacURI            => addSeq(ent, attr, ref, optSeq(a.vs), transformURI, extsURI, seq2arrayURI, value2jsonURI)
      case a: AttrSeqTacByte           => addByteArray(ent, attr, optArray(a.vs))
      case a: AttrSeqTacShort          => addSeq(ent, attr, ref, optSeq(a.vs), transformShort, extsShort, seq2arrayShort, value2jsonShort)
      case a: AttrSeqTacChar           => addSeq(ent, attr, ref, optSeq(a.vs), transformChar, extsChar, seq2arrayChar, value2jsonChar)
    }
  }

  private def resolveAttrSeqOpt(a: AttrSeqOpt): Unit = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case a: AttrSeqOptID             => addSeq(ent, attr, ref, a.vs, transformID, extsID, seq2arrayID, value2jsonID)
      case a: AttrSeqOptString         => addSeq(ent, attr, ref, a.vs, transformString, extsString, seq2arrayString, value2jsonString)
      case a: AttrSeqOptInt            => addSeq(ent, attr, ref, a.vs, transformInt, extsInt, seq2arrayInt, value2jsonInt)
      case a: AttrSeqOptLong           => addSeq(ent, attr, ref, a.vs, transformLong, extsLong, seq2arrayLong, value2jsonLong)
      case a: AttrSeqOptFloat          => addSeq(ent, attr, ref, a.vs, transformFloat, extsFloat, seq2arrayFloat, value2jsonFloat)
      case a: AttrSeqOptDouble         => addSeq(ent, attr, ref, a.vs, transformDouble, extsDouble, seq2arrayDouble, value2jsonDouble)
      case a: AttrSeqOptBoolean        => addSeq(ent, attr, ref, a.vs, transformBoolean, extsBoolean, seq2arrayBoolean, value2jsonBoolean)
      case a: AttrSeqOptBigInt         => addSeq(ent, attr, ref, a.vs, transformBigInt, extsBigInt, seq2arrayBigInt, value2jsonBigInt)
      case a: AttrSeqOptBigDecimal     => addSeq(ent, attr, ref, a.vs, transformBigDecimal, extsBigDecimal, seq2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSeqOptDate           => addSeq(ent, attr, ref, a.vs, transformDate, extsDate, seq2arrayDate, value2jsonDate)
      case a: AttrSeqOptDuration       => addSeq(ent, attr, ref, a.vs, transformDuration, extsDuration, seq2arrayDuration, value2jsonDuration)
      case a: AttrSeqOptInstant        => addSeq(ent, attr, ref, a.vs, transformInstant, extsInstant, seq2arrayInstant, value2jsonInstant)
      case a: AttrSeqOptLocalDate      => addSeq(ent, attr, ref, a.vs, transformLocalDate, extsLocalDate, seq2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSeqOptLocalTime      => addSeq(ent, attr, ref, a.vs, transformLocalTime, extsLocalTime, seq2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSeqOptLocalDateTime  => addSeq(ent, attr, ref, a.vs, transformLocalDateTime, extsLocalDateTime, seq2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSeqOptOffsetTime     => addSeq(ent, attr, ref, a.vs, transformOffsetTime, extsOffsetTime, seq2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSeqOptOffsetDateTime => addSeq(ent, attr, ref, a.vs, transformOffsetDateTime, extsOffsetDateTime, seq2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSeqOptZonedDateTime  => addSeq(ent, attr, ref, a.vs, transformZonedDateTime, extsZonedDateTime, seq2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSeqOptUUID           => addSeq(ent, attr, ref, a.vs, transformUUID, extsUUID, seq2arrayUUID, value2jsonUUID)
      case a: AttrSeqOptURI            => addSeq(ent, attr, ref, a.vs, transformURI, extsURI, seq2arrayURI, value2jsonURI)
      case a: AttrSeqOptByte           => addByteArray(ent, attr, a.vs)
      case a: AttrSeqOptShort          => addSeq(ent, attr, ref, a.vs, transformShort, extsShort, seq2arrayShort, value2jsonShort)
      case a: AttrSeqOptChar           => addSeq(ent, attr, ref, a.vs, transformChar, extsChar, seq2arrayChar, value2jsonChar)
    }
  }


  private def oneMap[T](map: Map[String, T]): Option[Map[String, T]] = {
    if (map.nonEmpty) Some(map) else None
  }
  private def resolveAttrMapMan(a: AttrMapMan): Unit = a match {
    case a: AttrMapManID             => addMap(a.ent, a.attr, oneMap(a.map), transformID, value2jsonID)
    case a: AttrMapManString         => addMap(a.ent, a.attr, oneMap(a.map), transformString, value2jsonString)
    case a: AttrMapManInt            => addMap(a.ent, a.attr, oneMap(a.map), transformInt, value2jsonInt)
    case a: AttrMapManLong           => addMap(a.ent, a.attr, oneMap(a.map), transformLong, value2jsonLong)
    case a: AttrMapManFloat          => addMap(a.ent, a.attr, oneMap(a.map), transformFloat, value2jsonFloat)
    case a: AttrMapManDouble         => addMap(a.ent, a.attr, oneMap(a.map), transformDouble, value2jsonDouble)
    case a: AttrMapManBoolean        => addMap(a.ent, a.attr, oneMap(a.map), transformBoolean, value2jsonBoolean)
    case a: AttrMapManBigInt         => addMap(a.ent, a.attr, oneMap(a.map), transformBigInt, value2jsonBigInt)
    case a: AttrMapManBigDecimal     => addMap(a.ent, a.attr, oneMap(a.map), transformBigDecimal, value2jsonBigDecimal)
    case a: AttrMapManDate           => addMap(a.ent, a.attr, oneMap(a.map), transformDate, value2jsonDate)
    case a: AttrMapManDuration       => addMap(a.ent, a.attr, oneMap(a.map), transformDuration, value2jsonDuration)
    case a: AttrMapManInstant        => addMap(a.ent, a.attr, oneMap(a.map), transformInstant, value2jsonInstant)
    case a: AttrMapManLocalDate      => addMap(a.ent, a.attr, oneMap(a.map), transformLocalDate, value2jsonLocalDate)
    case a: AttrMapManLocalTime      => addMap(a.ent, a.attr, oneMap(a.map), transformLocalTime, value2jsonLocalTime)
    case a: AttrMapManLocalDateTime  => addMap(a.ent, a.attr, oneMap(a.map), transformLocalDateTime, value2jsonLocalDateTime)
    case a: AttrMapManOffsetTime     => addMap(a.ent, a.attr, oneMap(a.map), transformOffsetTime, value2jsonOffsetTime)
    case a: AttrMapManOffsetDateTime => addMap(a.ent, a.attr, oneMap(a.map), transformOffsetDateTime, value2jsonOffsetDateTime)
    case a: AttrMapManZonedDateTime  => addMap(a.ent, a.attr, oneMap(a.map), transformZonedDateTime, value2jsonZonedDateTime)
    case a: AttrMapManUUID           => addMap(a.ent, a.attr, oneMap(a.map), transformUUID, value2jsonUUID)
    case a: AttrMapManURI            => addMap(a.ent, a.attr, oneMap(a.map), transformURI, value2jsonURI)
    case a: AttrMapManByte           => addMap(a.ent, a.attr, oneMap(a.map), transformByte, value2jsonByte)
    case a: AttrMapManShort          => addMap(a.ent, a.attr, oneMap(a.map), transformShort, value2jsonShort)
    case a: AttrMapManChar           => addMap(a.ent, a.attr, oneMap(a.map), transformChar, value2jsonChar)
  }
  private def resolveAttrMapTac(a: AttrMapTac): Unit = a match {
    case a: AttrMapTacID             => addMap(a.ent, a.attr, oneMap(a.map), transformID, value2jsonID)
    case a: AttrMapTacString         => addMap(a.ent, a.attr, oneMap(a.map), transformString, value2jsonString)
    case a: AttrMapTacInt            => addMap(a.ent, a.attr, oneMap(a.map), transformInt, value2jsonInt)
    case a: AttrMapTacLong           => addMap(a.ent, a.attr, oneMap(a.map), transformLong, value2jsonLong)
    case a: AttrMapTacFloat          => addMap(a.ent, a.attr, oneMap(a.map), transformFloat, value2jsonFloat)
    case a: AttrMapTacDouble         => addMap(a.ent, a.attr, oneMap(a.map), transformDouble, value2jsonDouble)
    case a: AttrMapTacBoolean        => addMap(a.ent, a.attr, oneMap(a.map), transformBoolean, value2jsonBoolean)
    case a: AttrMapTacBigInt         => addMap(a.ent, a.attr, oneMap(a.map), transformBigInt, value2jsonBigInt)
    case a: AttrMapTacBigDecimal     => addMap(a.ent, a.attr, oneMap(a.map), transformBigDecimal, value2jsonBigDecimal)
    case a: AttrMapTacDate           => addMap(a.ent, a.attr, oneMap(a.map), transformDate, value2jsonDate)
    case a: AttrMapTacDuration       => addMap(a.ent, a.attr, oneMap(a.map), transformDuration, value2jsonDuration)
    case a: AttrMapTacInstant        => addMap(a.ent, a.attr, oneMap(a.map), transformInstant, value2jsonInstant)
    case a: AttrMapTacLocalDate      => addMap(a.ent, a.attr, oneMap(a.map), transformLocalDate, value2jsonLocalDate)
    case a: AttrMapTacLocalTime      => addMap(a.ent, a.attr, oneMap(a.map), transformLocalTime, value2jsonLocalTime)
    case a: AttrMapTacLocalDateTime  => addMap(a.ent, a.attr, oneMap(a.map), transformLocalDateTime, value2jsonLocalDateTime)
    case a: AttrMapTacOffsetTime     => addMap(a.ent, a.attr, oneMap(a.map), transformOffsetTime, value2jsonOffsetTime)
    case a: AttrMapTacOffsetDateTime => addMap(a.ent, a.attr, oneMap(a.map), transformOffsetDateTime, value2jsonOffsetDateTime)
    case a: AttrMapTacZonedDateTime  => addMap(a.ent, a.attr, oneMap(a.map), transformZonedDateTime, value2jsonZonedDateTime)
    case a: AttrMapTacUUID           => addMap(a.ent, a.attr, oneMap(a.map), transformUUID, value2jsonUUID)
    case a: AttrMapTacURI            => addMap(a.ent, a.attr, oneMap(a.map), transformURI, value2jsonURI)
    case a: AttrMapTacByte           => addMap(a.ent, a.attr, oneMap(a.map), transformByte, value2jsonByte)
    case a: AttrMapTacShort          => addMap(a.ent, a.attr, oneMap(a.map), transformShort, value2jsonShort)
    case a: AttrMapTacChar           => addMap(a.ent, a.attr, oneMap(a.map), transformChar, value2jsonChar)
  }

  private def resolveAttrMapOpt(a: AttrMapOpt): Unit = {
    a match {
      case a: AttrMapOptID             => addMap(a.ent, a.attr, a.map, transformID, value2jsonID)
      case a: AttrMapOptString         => addMap(a.ent, a.attr, a.map, transformString, value2jsonString)
      case a: AttrMapOptInt            => addMap(a.ent, a.attr, a.map, transformInt, value2jsonInt)
      case a: AttrMapOptLong           => addMap(a.ent, a.attr, a.map, transformLong, value2jsonLong)
      case a: AttrMapOptFloat          => addMap(a.ent, a.attr, a.map, transformFloat, value2jsonFloat)
      case a: AttrMapOptDouble         => addMap(a.ent, a.attr, a.map, transformDouble, value2jsonDouble)
      case a: AttrMapOptBoolean        => addMap(a.ent, a.attr, a.map, transformBoolean, value2jsonBoolean)
      case a: AttrMapOptBigInt         => addMap(a.ent, a.attr, a.map, transformBigInt, value2jsonBigInt)
      case a: AttrMapOptBigDecimal     => addMap(a.ent, a.attr, a.map, transformBigDecimal, value2jsonBigDecimal)
      case a: AttrMapOptDate           => addMap(a.ent, a.attr, a.map, transformDate, value2jsonDate)
      case a: AttrMapOptDuration       => addMap(a.ent, a.attr, a.map, transformDuration, value2jsonDuration)
      case a: AttrMapOptInstant        => addMap(a.ent, a.attr, a.map, transformInstant, value2jsonInstant)
      case a: AttrMapOptLocalDate      => addMap(a.ent, a.attr, a.map, transformLocalDate, value2jsonLocalDate)
      case a: AttrMapOptLocalTime      => addMap(a.ent, a.attr, a.map, transformLocalTime, value2jsonLocalTime)
      case a: AttrMapOptLocalDateTime  => addMap(a.ent, a.attr, a.map, transformLocalDateTime, value2jsonLocalDateTime)
      case a: AttrMapOptOffsetTime     => addMap(a.ent, a.attr, a.map, transformOffsetTime, value2jsonOffsetTime)
      case a: AttrMapOptOffsetDateTime => addMap(a.ent, a.attr, a.map, transformOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrMapOptZonedDateTime  => addMap(a.ent, a.attr, a.map, transformZonedDateTime, value2jsonZonedDateTime)
      case a: AttrMapOptUUID           => addMap(a.ent, a.attr, a.map, transformUUID, value2jsonUUID)
      case a: AttrMapOptURI            => addMap(a.ent, a.attr, a.map, transformURI, value2jsonURI)
      case a: AttrMapOptByte           => addMap(a.ent, a.attr, a.map, transformByte, value2jsonByte)
      case a: AttrMapOptShort          => addMap(a.ent, a.attr, a.map, transformShort, value2jsonShort)
      case a: AttrMapOptChar           => addMap(a.ent, a.attr, a.map, transformChar, value2jsonChar)
    }
  }
}