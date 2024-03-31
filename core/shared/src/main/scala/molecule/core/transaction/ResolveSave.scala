package molecule.core.transaction

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.ConnProxy
import molecule.core.transaction.ops.SaveOps
import molecule.core.util.ModelUtils
import scala.annotation.tailrec
import scala.collection.immutable.ArraySeq

class ResolveSave
  extends ModelUtils with MoleculeLogging { self: SaveOps =>

  @tailrec
  final def resolve(elements: List[Element]): Unit = {
    elements match {
      case element :: tail => element match {
        case a: Attr =>
          if (a.op != Eq) {
            throw ModelError(s"Missing applied value for attribute ${a.ns}.${a.attr}")
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

        case Ref(ns, refAttr, refNs, card, owner, _) =>
          addRef(ns, refAttr, refNs, card, owner)
          resolve(tail)

        case BackRef(backRefNs, _, _) =>
          addBackRef(backRefNs)
          resolve(tail)

        case _: Nested    => throw ModelError(
          "Nested data structure not allowed in save molecule. Please use insert instead."
        )
        case _: NestedOpt => throw ModelError(
          "Optional nested data structure not allowed in save molecule. Please use insert instead."
        )
      }
      case Nil             => ()
    }
  }

  private def oneV[T](
    ns: String,
    attr: String,
    vs: Seq[T],
  ): Option[T] = {
    vs match {
      case Seq(v) => Some(v)
      case Nil    => None
      case vs     => throw ExecutionError(
        s"Can only save one value for attribute `$ns.$attr`. Found: " + vs.mkString(", ")
      )
    }
  }
  private def resolveAttrOneMan(a: AttrOneMan): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrOneManID             => addOne(ns, attr, oneV(ns, attr, a.vs), transformID, extsID)
      case a: AttrOneManString         => addOne(ns, attr, oneV(ns, attr, a.vs), transformString, extsString)
      case a: AttrOneManInt            => addOne(ns, attr, oneV(ns, attr, a.vs), transformInt, extsInt)
      case a: AttrOneManLong           => addOne(ns, attr, oneV(ns, attr, a.vs), transformLong, extsLong)
      case a: AttrOneManFloat          => addOne(ns, attr, oneV(ns, attr, a.vs), transformFloat, extsFloat)
      case a: AttrOneManDouble         => addOne(ns, attr, oneV(ns, attr, a.vs), transformDouble, extsDouble)
      case a: AttrOneManBoolean        => addOne(ns, attr, oneV(ns, attr, a.vs), transformBoolean, extsBoolean)
      case a: AttrOneManBigInt         => addOne(ns, attr, oneV(ns, attr, a.vs), transformBigInt, extsBigInt)
      case a: AttrOneManBigDecimal     => addOne(ns, attr, oneV(ns, attr, a.vs), transformBigDecimal, extsBigDecimal)
      case a: AttrOneManDate           => addOne(ns, attr, oneV(ns, attr, a.vs), transformDate, extsDate)
      case a: AttrOneManDuration       => addOne(ns, attr, oneV(ns, attr, a.vs), transformDuration, extsDuration)
      case a: AttrOneManInstant        => addOne(ns, attr, oneV(ns, attr, a.vs), transformInstant, extsInstant)
      case a: AttrOneManLocalDate      => addOne(ns, attr, oneV(ns, attr, a.vs), transformLocalDate, extsLocalDate)
      case a: AttrOneManLocalTime      => addOne(ns, attr, oneV(ns, attr, a.vs), transformLocalTime, extsLocalTime)
      case a: AttrOneManLocalDateTime  => addOne(ns, attr, oneV(ns, attr, a.vs), transformLocalDateTime, extsLocalDateTime)
      case a: AttrOneManOffsetTime     => addOne(ns, attr, oneV(ns, attr, a.vs), transformOffsetTime, extsOffsetTime)
      case a: AttrOneManOffsetDateTime => addOne(ns, attr, oneV(ns, attr, a.vs), transformOffsetDateTime, extsOffsetDateTime)
      case a: AttrOneManZonedDateTime  => addOne(ns, attr, oneV(ns, attr, a.vs), transformZonedDateTime, extsZonedDateTime)
      case a: AttrOneManUUID           => addOne(ns, attr, oneV(ns, attr, a.vs), transformUUID, extsUUID)
      case a: AttrOneManURI            => addOne(ns, attr, oneV(ns, attr, a.vs), transformURI, extsURI)
      case a: AttrOneManByte           => addOne(ns, attr, oneV(ns, attr, a.vs), transformByte, extsByte)
      case a: AttrOneManShort          => addOne(ns, attr, oneV(ns, attr, a.vs), transformShort, extsShort)
      case a: AttrOneManChar           => addOne(ns, attr, oneV(ns, attr, a.vs), transformChar, extsChar)
    }
  }
  private def resolveAttrOneTac(a: AttrOneTac): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrOneTacID             => addOne(ns, attr, oneV(ns, attr, a.vs), transformID, extsID)
      case a: AttrOneTacString         => addOne(ns, attr, oneV(ns, attr, a.vs), transformString, extsString)
      case a: AttrOneTacInt            => addOne(ns, attr, oneV(ns, attr, a.vs), transformInt, extsInt)
      case a: AttrOneTacLong           => addOne(ns, attr, oneV(ns, attr, a.vs), transformLong, extsLong)
      case a: AttrOneTacFloat          => addOne(ns, attr, oneV(ns, attr, a.vs), transformFloat, extsFloat)
      case a: AttrOneTacDouble         => addOne(ns, attr, oneV(ns, attr, a.vs), transformDouble, extsDouble)
      case a: AttrOneTacBoolean        => addOne(ns, attr, oneV(ns, attr, a.vs), transformBoolean, extsBoolean)
      case a: AttrOneTacBigInt         => addOne(ns, attr, oneV(ns, attr, a.vs), transformBigInt, extsBigInt)
      case a: AttrOneTacBigDecimal     => addOne(ns, attr, oneV(ns, attr, a.vs), transformBigDecimal, extsBigDecimal)
      case a: AttrOneTacDate           => addOne(ns, attr, oneV(ns, attr, a.vs), transformDate, extsDate)
      case a: AttrOneTacDuration       => addOne(ns, attr, oneV(ns, attr, a.vs), transformDuration, extsDuration)
      case a: AttrOneTacInstant        => addOne(ns, attr, oneV(ns, attr, a.vs), transformInstant, extsInstant)
      case a: AttrOneTacLocalDate      => addOne(ns, attr, oneV(ns, attr, a.vs), transformLocalDate, extsLocalDate)
      case a: AttrOneTacLocalTime      => addOne(ns, attr, oneV(ns, attr, a.vs), transformLocalTime, extsLocalTime)
      case a: AttrOneTacLocalDateTime  => addOne(ns, attr, oneV(ns, attr, a.vs), transformLocalDateTime, extsLocalDateTime)
      case a: AttrOneTacOffsetTime     => addOne(ns, attr, oneV(ns, attr, a.vs), transformOffsetTime, extsOffsetTime)
      case a: AttrOneTacOffsetDateTime => addOne(ns, attr, oneV(ns, attr, a.vs), transformOffsetDateTime, extsOffsetDateTime)
      case a: AttrOneTacZonedDateTime  => addOne(ns, attr, oneV(ns, attr, a.vs), transformZonedDateTime, extsZonedDateTime)
      case a: AttrOneTacUUID           => addOne(ns, attr, oneV(ns, attr, a.vs), transformUUID, extsUUID)
      case a: AttrOneTacURI            => addOne(ns, attr, oneV(ns, attr, a.vs), transformURI, extsURI)
      case a: AttrOneTacByte           => addOne(ns, attr, oneV(ns, attr, a.vs), transformByte, extsByte)
      case a: AttrOneTacShort          => addOne(ns, attr, oneV(ns, attr, a.vs), transformShort, extsShort)
      case a: AttrOneTacChar           => addOne(ns, attr, oneV(ns, attr, a.vs), transformChar, extsChar)
    }
  }


  private def oneOptV[T](
    ns: String,
    attr: String,
    optVs: Option[Seq[T]],
  ): Option[T] = {
    optVs.flatMap {
      case Seq(v) => Some(v)
      case Nil    => None
      case vs     => throw ExecutionError(
        s"Can only save one value for optional attribute `$ns.$attr`. Found: " + vs.mkString(", ")
      )
    }
  }
  private def resolveAttrOneOpt(a: AttrOneOpt): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrOneOptID             => addOne(ns, attr, oneOptV(ns, attr, a.vs), transformID, extsID)
      case a: AttrOneOptString         => addOne(ns, attr, oneOptV(ns, attr, a.vs), transformString, extsString)
      case a: AttrOneOptInt            => addOne(ns, attr, oneOptV(ns, attr, a.vs), transformInt, extsInt)
      case a: AttrOneOptLong           => addOne(ns, attr, oneOptV(ns, attr, a.vs), transformLong, extsLong)
      case a: AttrOneOptFloat          => addOne(ns, attr, oneOptV(ns, attr, a.vs), transformFloat, extsFloat)
      case a: AttrOneOptDouble         => addOne(ns, attr, oneOptV(ns, attr, a.vs), transformDouble, extsDouble)
      case a: AttrOneOptBoolean        => addOne(ns, attr, oneOptV(ns, attr, a.vs), transformBoolean, extsBoolean)
      case a: AttrOneOptBigInt         => addOne(ns, attr, oneOptV(ns, attr, a.vs), transformBigInt, extsBigInt)
      case a: AttrOneOptBigDecimal     => addOne(ns, attr, oneOptV(ns, attr, a.vs), transformBigDecimal, extsBigDecimal)
      case a: AttrOneOptDate           => addOne(ns, attr, oneOptV(ns, attr, a.vs), transformDate, extsDate)
      case a: AttrOneOptDuration       => addOne(ns, attr, oneOptV(ns, attr, a.vs), transformDuration, extsDuration)
      case a: AttrOneOptInstant        => addOne(ns, attr, oneOptV(ns, attr, a.vs), transformInstant, extsInstant)
      case a: AttrOneOptLocalDate      => addOne(ns, attr, oneOptV(ns, attr, a.vs), transformLocalDate, extsLocalDate)
      case a: AttrOneOptLocalTime      => addOne(ns, attr, oneOptV(ns, attr, a.vs), transformLocalTime, extsLocalTime)
      case a: AttrOneOptLocalDateTime  => addOne(ns, attr, oneOptV(ns, attr, a.vs), transformLocalDateTime, extsLocalDateTime)
      case a: AttrOneOptOffsetTime     => addOne(ns, attr, oneOptV(ns, attr, a.vs), transformOffsetTime, extsOffsetTime)
      case a: AttrOneOptOffsetDateTime => addOne(ns, attr, oneOptV(ns, attr, a.vs), transformOffsetDateTime, extsOffsetDateTime)
      case a: AttrOneOptZonedDateTime  => addOne(ns, attr, oneOptV(ns, attr, a.vs), transformZonedDateTime, extsZonedDateTime)
      case a: AttrOneOptUUID           => addOne(ns, attr, oneOptV(ns, attr, a.vs), transformUUID, extsUUID)
      case a: AttrOneOptURI            => addOne(ns, attr, oneOptV(ns, attr, a.vs), transformURI, extsURI)
      case a: AttrOneOptByte           => addOne(ns, attr, oneOptV(ns, attr, a.vs), transformByte, extsByte)
      case a: AttrOneOptShort          => addOne(ns, attr, oneOptV(ns, attr, a.vs), transformShort, extsShort)
      case a: AttrOneOptChar           => addOne(ns, attr, oneOptV(ns, attr, a.vs), transformChar, extsChar)
    }
  }


  private def optSet[T](set: Set[T]): Option[Set[T]] = if (set.nonEmpty) Some(set) else None

  private def resolveAttrSetMan(a: AttrSetMan): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrSetManID             => addSet(ns, attr, refNs, optSet(a.vs), transformID, extsID, set2arrayID, value2jsonID)
      case a: AttrSetManString         => addSet(ns, attr, refNs, optSet(a.vs), transformString, extsString, set2arrayString, value2jsonString)
      case a: AttrSetManInt            => addSet(ns, attr, refNs, optSet(a.vs), transformInt, extsInt, set2arrayInt, value2jsonInt)
      case a: AttrSetManLong           => addSet(ns, attr, refNs, optSet(a.vs), transformLong, extsLong, set2arrayLong, value2jsonLong)
      case a: AttrSetManFloat          => addSet(ns, attr, refNs, optSet(a.vs), transformFloat, extsFloat, set2arrayFloat, value2jsonFloat)
      case a: AttrSetManDouble         => addSet(ns, attr, refNs, optSet(a.vs), transformDouble, extsDouble, set2arrayDouble, value2jsonDouble)
      case a: AttrSetManBoolean        => addSet(ns, attr, refNs, optSet(a.vs), transformBoolean, extsBoolean, set2arrayBoolean, value2jsonBoolean)
      case a: AttrSetManBigInt         => addSet(ns, attr, refNs, optSet(a.vs), transformBigInt, extsBigInt, set2arrayBigInt, value2jsonBigInt)
      case a: AttrSetManBigDecimal     => addSet(ns, attr, refNs, optSet(a.vs), transformBigDecimal, extsBigDecimal, set2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSetManDate           => addSet(ns, attr, refNs, optSet(a.vs), transformDate, extsDate, set2arrayDate, value2jsonDate)
      case a: AttrSetManDuration       => addSet(ns, attr, refNs, optSet(a.vs), transformDuration, extsDuration, set2arrayDuration, value2jsonDuration)
      case a: AttrSetManInstant        => addSet(ns, attr, refNs, optSet(a.vs), transformInstant, extsInstant, set2arrayInstant, value2jsonInstant)
      case a: AttrSetManLocalDate      => addSet(ns, attr, refNs, optSet(a.vs), transformLocalDate, extsLocalDate, set2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSetManLocalTime      => addSet(ns, attr, refNs, optSet(a.vs), transformLocalTime, extsLocalTime, set2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSetManLocalDateTime  => addSet(ns, attr, refNs, optSet(a.vs), transformLocalDateTime, extsLocalDateTime, set2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSetManOffsetTime     => addSet(ns, attr, refNs, optSet(a.vs), transformOffsetTime, extsOffsetTime, set2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSetManOffsetDateTime => addSet(ns, attr, refNs, optSet(a.vs), transformOffsetDateTime, extsOffsetDateTime, set2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSetManZonedDateTime  => addSet(ns, attr, refNs, optSet(a.vs), transformZonedDateTime, extsZonedDateTime, set2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSetManUUID           => addSet(ns, attr, refNs, optSet(a.vs), transformUUID, extsUUID, set2arrayUUID, value2jsonUUID)
      case a: AttrSetManURI            => addSet(ns, attr, refNs, optSet(a.vs), transformURI, extsURI, set2arrayURI, value2jsonURI)
      case a: AttrSetManByte           => addSet(ns, attr, refNs, optSet(a.vs), transformByte, extsByte, set2arrayByte, value2jsonByte)
      case a: AttrSetManShort          => addSet(ns, attr, refNs, optSet(a.vs), transformShort, extsShort, set2arrayShort, value2jsonShort)
      case a: AttrSetManChar           => addSet(ns, attr, refNs, optSet(a.vs), transformChar, extsChar, set2arrayChar, value2jsonChar)
    }
  }

  private def resolveAttrSetTac(a: AttrSetTac): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrSetTacID             => addSet(ns, attr, refNs, optSet(a.vs), transformID, extsID, set2arrayID, value2jsonID)
      case a: AttrSetTacString         => addSet(ns, attr, refNs, optSet(a.vs), transformString, extsString, set2arrayString, value2jsonString)
      case a: AttrSetTacInt            => addSet(ns, attr, refNs, optSet(a.vs), transformInt, extsInt, set2arrayInt, value2jsonInt)
      case a: AttrSetTacLong           => addSet(ns, attr, refNs, optSet(a.vs), transformLong, extsLong, set2arrayLong, value2jsonLong)
      case a: AttrSetTacFloat          => addSet(ns, attr, refNs, optSet(a.vs), transformFloat, extsFloat, set2arrayFloat, value2jsonFloat)
      case a: AttrSetTacDouble         => addSet(ns, attr, refNs, optSet(a.vs), transformDouble, extsDouble, set2arrayDouble, value2jsonDouble)
      case a: AttrSetTacBoolean        => addSet(ns, attr, refNs, optSet(a.vs), transformBoolean, extsBoolean, set2arrayBoolean, value2jsonBoolean)
      case a: AttrSetTacBigInt         => addSet(ns, attr, refNs, optSet(a.vs), transformBigInt, extsBigInt, set2arrayBigInt, value2jsonBigInt)
      case a: AttrSetTacBigDecimal     => addSet(ns, attr, refNs, optSet(a.vs), transformBigDecimal, extsBigDecimal, set2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSetTacDate           => addSet(ns, attr, refNs, optSet(a.vs), transformDate, extsDate, set2arrayDate, value2jsonDate)
      case a: AttrSetTacDuration       => addSet(ns, attr, refNs, optSet(a.vs), transformDuration, extsDuration, set2arrayDuration, value2jsonDuration)
      case a: AttrSetTacInstant        => addSet(ns, attr, refNs, optSet(a.vs), transformInstant, extsInstant, set2arrayInstant, value2jsonInstant)
      case a: AttrSetTacLocalDate      => addSet(ns, attr, refNs, optSet(a.vs), transformLocalDate, extsLocalDate, set2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSetTacLocalTime      => addSet(ns, attr, refNs, optSet(a.vs), transformLocalTime, extsLocalTime, set2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSetTacLocalDateTime  => addSet(ns, attr, refNs, optSet(a.vs), transformLocalDateTime, extsLocalDateTime, set2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSetTacOffsetTime     => addSet(ns, attr, refNs, optSet(a.vs), transformOffsetTime, extsOffsetTime, set2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSetTacOffsetDateTime => addSet(ns, attr, refNs, optSet(a.vs), transformOffsetDateTime, extsOffsetDateTime, set2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSetTacZonedDateTime  => addSet(ns, attr, refNs, optSet(a.vs), transformZonedDateTime, extsZonedDateTime, set2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSetTacUUID           => addSet(ns, attr, refNs, optSet(a.vs), transformUUID, extsUUID, set2arrayUUID, value2jsonUUID)
      case a: AttrSetTacURI            => addSet(ns, attr, refNs, optSet(a.vs), transformURI, extsURI, set2arrayURI, value2jsonURI)
      case a: AttrSetTacByte           => addSet(ns, attr, refNs, optSet(a.vs), transformByte, extsByte, set2arrayByte, value2jsonByte)
      case a: AttrSetTacShort          => addSet(ns, attr, refNs, optSet(a.vs), transformShort, extsShort, set2arrayShort, value2jsonShort)
      case a: AttrSetTacChar           => addSet(ns, attr, refNs, optSet(a.vs), transformChar, extsChar, set2arrayChar, value2jsonChar)
    }
  }

  private def resolveAttrSetOpt(a: AttrSetOpt): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrSetOptID             => addSet(ns, attr, refNs, a.vs, transformID, extsID, set2arrayID, value2jsonID)
      case a: AttrSetOptString         => addSet(ns, attr, refNs, a.vs, transformString, extsString, set2arrayString, value2jsonString)
      case a: AttrSetOptInt            => addSet(ns, attr, refNs, a.vs, transformInt, extsInt, set2arrayInt, value2jsonInt)
      case a: AttrSetOptLong           => addSet(ns, attr, refNs, a.vs, transformLong, extsLong, set2arrayLong, value2jsonLong)
      case a: AttrSetOptFloat          => addSet(ns, attr, refNs, a.vs, transformFloat, extsFloat, set2arrayFloat, value2jsonFloat)
      case a: AttrSetOptDouble         => addSet(ns, attr, refNs, a.vs, transformDouble, extsDouble, set2arrayDouble, value2jsonDouble)
      case a: AttrSetOptBoolean        => addSet(ns, attr, refNs, a.vs, transformBoolean, extsBoolean, set2arrayBoolean, value2jsonBoolean)
      case a: AttrSetOptBigInt         => addSet(ns, attr, refNs, a.vs, transformBigInt, extsBigInt, set2arrayBigInt, value2jsonBigInt)
      case a: AttrSetOptBigDecimal     => addSet(ns, attr, refNs, a.vs, transformBigDecimal, extsBigDecimal, set2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSetOptDate           => addSet(ns, attr, refNs, a.vs, transformDate, extsDate, set2arrayDate, value2jsonDate)
      case a: AttrSetOptDuration       => addSet(ns, attr, refNs, a.vs, transformDuration, extsDuration, set2arrayDuration, value2jsonDuration)
      case a: AttrSetOptInstant        => addSet(ns, attr, refNs, a.vs, transformInstant, extsInstant, set2arrayInstant, value2jsonInstant)
      case a: AttrSetOptLocalDate      => addSet(ns, attr, refNs, a.vs, transformLocalDate, extsLocalDate, set2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSetOptLocalTime      => addSet(ns, attr, refNs, a.vs, transformLocalTime, extsLocalTime, set2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSetOptLocalDateTime  => addSet(ns, attr, refNs, a.vs, transformLocalDateTime, extsLocalDateTime, set2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSetOptOffsetTime     => addSet(ns, attr, refNs, a.vs, transformOffsetTime, extsOffsetTime, set2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSetOptOffsetDateTime => addSet(ns, attr, refNs, a.vs, transformOffsetDateTime, extsOffsetDateTime, set2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSetOptZonedDateTime  => addSet(ns, attr, refNs, a.vs, transformZonedDateTime, extsZonedDateTime, set2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSetOptUUID           => addSet(ns, attr, refNs, a.vs, transformUUID, extsUUID, set2arrayUUID, value2jsonUUID)
      case a: AttrSetOptURI            => addSet(ns, attr, refNs, a.vs, transformURI, extsURI, set2arrayURI, value2jsonURI)
      case a: AttrSetOptByte           => addSet(ns, attr, refNs, a.vs, transformByte, extsByte, set2arrayByte, value2jsonByte)
      case a: AttrSetOptShort          => addSet(ns, attr, refNs, a.vs, transformShort, extsShort, set2arrayShort, value2jsonShort)
      case a: AttrSetOptChar           => addSet(ns, attr, refNs, a.vs, transformChar, extsChar, set2arrayChar, value2jsonChar)
    }
  }

  private def optSeq[T](seq: Seq[T]): Option[Seq[T]] = if (seq.nonEmpty) Some(seq) else None

  private def resolveAttrSeqMan(a: AttrSeqMan): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrSeqManID             => addSeq(ns, attr, refNs, optSeq(a.vs), transformID, extsID, seq2arrayID, value2jsonID)
      case a: AttrSeqManString         => addSeq(ns, attr, refNs, optSeq(a.vs), transformString, extsString, seq2arrayString, value2jsonString)
      case a: AttrSeqManInt            => addSeq(ns, attr, refNs, optSeq(a.vs), transformInt, extsInt, seq2arrayInt, value2jsonInt)
      case a: AttrSeqManLong           => addSeq(ns, attr, refNs, optSeq(a.vs), transformLong, extsLong, seq2arrayLong, value2jsonLong)
      case a: AttrSeqManFloat          => addSeq(ns, attr, refNs, optSeq(a.vs), transformFloat, extsFloat, seq2arrayFloat, value2jsonFloat)
      case a: AttrSeqManDouble         => addSeq(ns, attr, refNs, optSeq(a.vs), transformDouble, extsDouble, seq2arrayDouble, value2jsonDouble)
      case a: AttrSeqManBoolean        => addSeq(ns, attr, refNs, optSeq(a.vs), transformBoolean, extsBoolean, seq2arrayBoolean, value2jsonBoolean)
      case a: AttrSeqManBigInt         => addSeq(ns, attr, refNs, optSeq(a.vs), transformBigInt, extsBigInt, seq2arrayBigInt, value2jsonBigInt)
      case a: AttrSeqManBigDecimal     => addSeq(ns, attr, refNs, optSeq(a.vs), transformBigDecimal, extsBigDecimal, seq2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSeqManDate           => addSeq(ns, attr, refNs, optSeq(a.vs), transformDate, extsDate, seq2arrayDate, value2jsonDate)
      case a: AttrSeqManDuration       => addSeq(ns, attr, refNs, optSeq(a.vs), transformDuration, extsDuration, seq2arrayDuration, value2jsonDuration)
      case a: AttrSeqManInstant        => addSeq(ns, attr, refNs, optSeq(a.vs), transformInstant, extsInstant, seq2arrayInstant, value2jsonInstant)
      case a: AttrSeqManLocalDate      => addSeq(ns, attr, refNs, optSeq(a.vs), transformLocalDate, extsLocalDate, seq2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSeqManLocalTime      => addSeq(ns, attr, refNs, optSeq(a.vs), transformLocalTime, extsLocalTime, seq2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSeqManLocalDateTime  => addSeq(ns, attr, refNs, optSeq(a.vs), transformLocalDateTime, extsLocalDateTime, seq2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSeqManOffsetTime     => addSeq(ns, attr, refNs, optSeq(a.vs), transformOffsetTime, extsOffsetTime, seq2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSeqManOffsetDateTime => addSeq(ns, attr, refNs, optSeq(a.vs), transformOffsetDateTime, extsOffsetDateTime, seq2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSeqManZonedDateTime  => addSeq(ns, attr, refNs, optSeq(a.vs), transformZonedDateTime, extsZonedDateTime, seq2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSeqManUUID           => addSeq(ns, attr, refNs, optSeq(a.vs), transformUUID, extsUUID, seq2arrayUUID, value2jsonUUID)
      case a: AttrSeqManURI            => addSeq(ns, attr, refNs, optSeq(a.vs), transformURI, extsURI, seq2arrayURI, value2jsonURI)
      case a: AttrSeqManByte           => addByteArray(ns, attr, optArray(a.vs), extsByte)
      case a: AttrSeqManShort          => addSeq(ns, attr, refNs, optSeq(a.vs), transformShort, extsShort, seq2arrayShort, value2jsonShort)
      case a: AttrSeqManChar           => addSeq(ns, attr, refNs, optSeq(a.vs), transformChar, extsChar, seq2arrayChar, value2jsonChar)
    }
  }
  private def resolveAttrSeqTac(a: AttrSeqTac): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrSeqTacID             => addSeq(ns, attr, refNs, optSeq(a.vs), transformID, extsID, seq2arrayID, value2jsonID)
      case a: AttrSeqTacString         => addSeq(ns, attr, refNs, optSeq(a.vs), transformString, extsString, seq2arrayString, value2jsonString)
      case a: AttrSeqTacInt            => addSeq(ns, attr, refNs, optSeq(a.vs), transformInt, extsInt, seq2arrayInt, value2jsonInt)
      case a: AttrSeqTacLong           => addSeq(ns, attr, refNs, optSeq(a.vs), transformLong, extsLong, seq2arrayLong, value2jsonLong)
      case a: AttrSeqTacFloat          => addSeq(ns, attr, refNs, optSeq(a.vs), transformFloat, extsFloat, seq2arrayFloat, value2jsonFloat)
      case a: AttrSeqTacDouble         => addSeq(ns, attr, refNs, optSeq(a.vs), transformDouble, extsDouble, seq2arrayDouble, value2jsonDouble)
      case a: AttrSeqTacBoolean        => addSeq(ns, attr, refNs, optSeq(a.vs), transformBoolean, extsBoolean, seq2arrayBoolean, value2jsonBoolean)
      case a: AttrSeqTacBigInt         => addSeq(ns, attr, refNs, optSeq(a.vs), transformBigInt, extsBigInt, seq2arrayBigInt, value2jsonBigInt)
      case a: AttrSeqTacBigDecimal     => addSeq(ns, attr, refNs, optSeq(a.vs), transformBigDecimal, extsBigDecimal, seq2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSeqTacDate           => addSeq(ns, attr, refNs, optSeq(a.vs), transformDate, extsDate, seq2arrayDate, value2jsonDate)
      case a: AttrSeqTacDuration       => addSeq(ns, attr, refNs, optSeq(a.vs), transformDuration, extsDuration, seq2arrayDuration, value2jsonDuration)
      case a: AttrSeqTacInstant        => addSeq(ns, attr, refNs, optSeq(a.vs), transformInstant, extsInstant, seq2arrayInstant, value2jsonInstant)
      case a: AttrSeqTacLocalDate      => addSeq(ns, attr, refNs, optSeq(a.vs), transformLocalDate, extsLocalDate, seq2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSeqTacLocalTime      => addSeq(ns, attr, refNs, optSeq(a.vs), transformLocalTime, extsLocalTime, seq2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSeqTacLocalDateTime  => addSeq(ns, attr, refNs, optSeq(a.vs), transformLocalDateTime, extsLocalDateTime, seq2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSeqTacOffsetTime     => addSeq(ns, attr, refNs, optSeq(a.vs), transformOffsetTime, extsOffsetTime, seq2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSeqTacOffsetDateTime => addSeq(ns, attr, refNs, optSeq(a.vs), transformOffsetDateTime, extsOffsetDateTime, seq2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSeqTacZonedDateTime  => addSeq(ns, attr, refNs, optSeq(a.vs), transformZonedDateTime, extsZonedDateTime, seq2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSeqTacUUID           => addSeq(ns, attr, refNs, optSeq(a.vs), transformUUID, extsUUID, seq2arrayUUID, value2jsonUUID)
      case a: AttrSeqTacURI            => addSeq(ns, attr, refNs, optSeq(a.vs), transformURI, extsURI, seq2arrayURI, value2jsonURI)
      case a: AttrSeqTacByte           => addByteArray(ns, attr, optArray(a.vs), extsByte)
      case a: AttrSeqTacShort          => addSeq(ns, attr, refNs, optSeq(a.vs), transformShort, extsShort, seq2arrayShort, value2jsonShort)
      case a: AttrSeqTacChar           => addSeq(ns, attr, refNs, optSeq(a.vs), transformChar, extsChar, seq2arrayChar, value2jsonChar)
    }
  }

  private def resolveAttrSeqOpt(a: AttrSeqOpt): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrSeqOptID             => addSeq(ns, attr, refNs, a.vs, transformID, extsID, seq2arrayID, value2jsonID)
      case a: AttrSeqOptString         => addSeq(ns, attr, refNs, a.vs, transformString, extsString, seq2arrayString, value2jsonString)
      case a: AttrSeqOptInt            => addSeq(ns, attr, refNs, a.vs, transformInt, extsInt, seq2arrayInt, value2jsonInt)
      case a: AttrSeqOptLong           => addSeq(ns, attr, refNs, a.vs, transformLong, extsLong, seq2arrayLong, value2jsonLong)
      case a: AttrSeqOptFloat          => addSeq(ns, attr, refNs, a.vs, transformFloat, extsFloat, seq2arrayFloat, value2jsonFloat)
      case a: AttrSeqOptDouble         => addSeq(ns, attr, refNs, a.vs, transformDouble, extsDouble, seq2arrayDouble, value2jsonDouble)
      case a: AttrSeqOptBoolean        => addSeq(ns, attr, refNs, a.vs, transformBoolean, extsBoolean, seq2arrayBoolean, value2jsonBoolean)
      case a: AttrSeqOptBigInt         => addSeq(ns, attr, refNs, a.vs, transformBigInt, extsBigInt, seq2arrayBigInt, value2jsonBigInt)
      case a: AttrSeqOptBigDecimal     => addSeq(ns, attr, refNs, a.vs, transformBigDecimal, extsBigDecimal, seq2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSeqOptDate           => addSeq(ns, attr, refNs, a.vs, transformDate, extsDate, seq2arrayDate, value2jsonDate)
      case a: AttrSeqOptDuration       => addSeq(ns, attr, refNs, a.vs, transformDuration, extsDuration, seq2arrayDuration, value2jsonDuration)
      case a: AttrSeqOptInstant        => addSeq(ns, attr, refNs, a.vs, transformInstant, extsInstant, seq2arrayInstant, value2jsonInstant)
      case a: AttrSeqOptLocalDate      => addSeq(ns, attr, refNs, a.vs, transformLocalDate, extsLocalDate, seq2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSeqOptLocalTime      => addSeq(ns, attr, refNs, a.vs, transformLocalTime, extsLocalTime, seq2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSeqOptLocalDateTime  => addSeq(ns, attr, refNs, a.vs, transformLocalDateTime, extsLocalDateTime, seq2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSeqOptOffsetTime     => addSeq(ns, attr, refNs, a.vs, transformOffsetTime, extsOffsetTime, seq2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSeqOptOffsetDateTime => addSeq(ns, attr, refNs, a.vs, transformOffsetDateTime, extsOffsetDateTime, seq2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSeqOptZonedDateTime  => addSeq(ns, attr, refNs, a.vs, transformZonedDateTime, extsZonedDateTime, seq2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSeqOptUUID           => addSeq(ns, attr, refNs, a.vs, transformUUID, extsUUID, seq2arrayUUID, value2jsonUUID)
      case a: AttrSeqOptURI            => addSeq(ns, attr, refNs, a.vs, transformURI, extsURI, seq2arrayURI, value2jsonURI)
      case a: AttrSeqOptByte           => addByteArray(ns, attr, a.vs, extsByte)
      case a: AttrSeqOptShort          => addSeq(ns, attr, refNs, a.vs, transformShort, extsShort, seq2arrayShort, value2jsonShort)
      case a: AttrSeqOptChar           => addSeq(ns, attr, refNs, a.vs, transformChar, extsChar, seq2arrayChar, value2jsonChar)
    }
  }


  private def oneMap[T](map: Map[String, T], transformValue: T => Any): Option[Map[String, T]] = {
    if (map.nonEmpty) Some(map) else None
  }
  private def resolveAttrMapMan(a: AttrMapMan): Unit = a match {
    case a: AttrMapManID             => addMap(a.ns, a.attr, oneMap(a.vs, transformID), transformID) //, set2arrayID, refNs, extsID, value2jsonID)
    case a: AttrMapManString         => addMap(a.ns, a.attr, oneMap(a.vs, transformString), transformString) //, set2arrayString, refNs, extsString, value2jsonString)
    case a: AttrMapManInt            => addMap(a.ns, a.attr, oneMap(a.vs, transformInt), transformInt) //, set2arrayInt, refNs, extsInt, value2jsonInt)
    case a: AttrMapManLong           => addMap(a.ns, a.attr, oneMap(a.vs, transformLong), transformLong) //, set2arrayLong, refNs, extsLong, value2jsonLong)
    case a: AttrMapManFloat          => addMap(a.ns, a.attr, oneMap(a.vs, transformFloat), transformFloat) //, set2arrayFloat, refNs, extsFloat, value2jsonFloat)
    case a: AttrMapManDouble         => addMap(a.ns, a.attr, oneMap(a.vs, transformDouble), transformDouble) //, set2arrayDouble, refNs, extsDouble, value2jsonDouble)
    case a: AttrMapManBoolean        => addMap(a.ns, a.attr, oneMap(a.vs, transformBoolean), transformBoolean) //, set2arrayBoolean, refNs, extsBoolean, value2jsonBoolean)
    case a: AttrMapManBigInt         => addMap(a.ns, a.attr, oneMap(a.vs, transformBigInt), transformBigInt) //, set2arrayBigInt, refNs, extsBigInt, value2jsonBigInt)
    case a: AttrMapManBigDecimal     => addMap(a.ns, a.attr, oneMap(a.vs, transformBigDecimal), transformBigDecimal) //, set2arrayBigDecimal, refNs, extsBigDecimal, value2jsonBigDecimal)
    case a: AttrMapManDate           => addMap(a.ns, a.attr, oneMap(a.vs, transformDate), transformDate) //, set2arrayDate, refNs, extsDate, value2jsonDate)
    case a: AttrMapManDuration       => addMap(a.ns, a.attr, oneMap(a.vs, transformDuration), transformDuration) //, set2arrayDuration, refNs, extsDuration, value2jsonDuration)
    case a: AttrMapManInstant        => addMap(a.ns, a.attr, oneMap(a.vs, transformInstant), transformInstant) //, set2arrayInstant, refNs, extsInstant, value2jsonInstant)
    case a: AttrMapManLocalDate      => addMap(a.ns, a.attr, oneMap(a.vs, transformLocalDate), transformLocalDate) //, set2arrayLocalDate, refNs, extsLocalDate, value2jsonLocalDate)
    case a: AttrMapManLocalTime      => addMap(a.ns, a.attr, oneMap(a.vs, transformLocalTime), transformLocalTime) //, set2arrayLocalTime, refNs, extsLocalTime, value2jsonLocalTime)
    case a: AttrMapManLocalDateTime  => addMap(a.ns, a.attr, oneMap(a.vs, transformLocalDateTime), transformLocalDateTime) //, set2arrayLocalDateTime, refNs, extsLocalDateTime, value2jsonLocalDateTime)
    case a: AttrMapManOffsetTime     => addMap(a.ns, a.attr, oneMap(a.vs, transformOffsetTime), transformOffsetTime) //, set2arrayOffsetTime, refNs, extsOffsetTime, value2jsonOffsetTime)
    case a: AttrMapManOffsetDateTime => addMap(a.ns, a.attr, oneMap(a.vs, transformOffsetDateTime), transformOffsetDateTime) //, set2arrayOffsetDateTime, refNs, extsOffsetDateTime, value2jsonOffsetDateTime)
    case a: AttrMapManZonedDateTime  => addMap(a.ns, a.attr, oneMap(a.vs, transformZonedDateTime), transformZonedDateTime) //, set2arrayZonedDateTime, refNs, extsZonedDateTime, value2jsonZonedDateTime)
    case a: AttrMapManUUID           => addMap(a.ns, a.attr, oneMap(a.vs, transformUUID), transformUUID) //, set2arrayUUID, refNs, extsUUID, value2jsonUUID)
    case a: AttrMapManURI            => addMap(a.ns, a.attr, oneMap(a.vs, transformURI), transformURI) //, set2arrayURI, refNs, extsURI, value2jsonURI)
    case a: AttrMapManByte           => addMap(a.ns, a.attr, oneMap(a.vs, transformByte), transformByte) //, set2arrayByte, refNs, extsByte, value2jsonByte)
    case a: AttrMapManShort          => addMap(a.ns, a.attr, oneMap(a.vs, transformShort), transformShort) //, set2arrayShort, refNs, extsShort, value2jsonShort)
    case a: AttrMapManChar           => addMap(a.ns, a.attr, oneMap(a.vs, transformChar), transformChar) //, set2arrayChar, refNs, extsChar, value2jsonChar)
  }
  private def resolveAttrMapTac(a: AttrMapTac): Unit = a match {
    case a: AttrMapTacID             => addMap(a.ns, a.attr, oneMap(a.vs, transformID), transformID) //, set2arrayID, refNs, extsID, value2jsonID)
    case a: AttrMapTacString         => addMap(a.ns, a.attr, oneMap(a.vs, transformString), transformString) //, set2arrayString, refNs, extsString, value2jsonString)
    case a: AttrMapTacInt            => addMap(a.ns, a.attr, oneMap(a.vs, transformInt), transformInt) //, set2arrayInt, refNs, extsInt, value2jsonInt)
    case a: AttrMapTacLong           => addMap(a.ns, a.attr, oneMap(a.vs, transformLong), transformLong) //, set2arrayLong, refNs, extsLong, value2jsonLong)
    case a: AttrMapTacFloat          => addMap(a.ns, a.attr, oneMap(a.vs, transformFloat), transformFloat) //, set2arrayFloat, refNs, extsFloat, value2jsonFloat)
    case a: AttrMapTacDouble         => addMap(a.ns, a.attr, oneMap(a.vs, transformDouble), transformDouble) //, set2arrayDouble, refNs, extsDouble, value2jsonDouble)
    case a: AttrMapTacBoolean        => addMap(a.ns, a.attr, oneMap(a.vs, transformBoolean), transformBoolean) //, set2arrayBoolean, refNs, extsBoolean, value2jsonBoolean)
    case a: AttrMapTacBigInt         => addMap(a.ns, a.attr, oneMap(a.vs, transformBigInt), transformBigInt) //, set2arrayBigInt, refNs, extsBigInt, value2jsonBigInt)
    case a: AttrMapTacBigDecimal     => addMap(a.ns, a.attr, oneMap(a.vs, transformBigDecimal), transformBigDecimal) //, set2arrayBigDecimal, refNs, extsBigDecimal, value2jsonBigDecimal)
    case a: AttrMapTacDate           => addMap(a.ns, a.attr, oneMap(a.vs, transformDate), transformDate) //, set2arrayDate, refNs, extsDate, value2jsonDate)
    case a: AttrMapTacDuration       => addMap(a.ns, a.attr, oneMap(a.vs, transformDuration), transformDuration) //, set2arrayDuration, refNs, extsDuration, value2jsonDuration)
    case a: AttrMapTacInstant        => addMap(a.ns, a.attr, oneMap(a.vs, transformInstant), transformInstant) //, set2arrayInstant, refNs, extsInstant, value2jsonInstant)
    case a: AttrMapTacLocalDate      => addMap(a.ns, a.attr, oneMap(a.vs, transformLocalDate), transformLocalDate) //, set2arrayLocalDate, refNs, extsLocalDate, value2jsonLocalDate)
    case a: AttrMapTacLocalTime      => addMap(a.ns, a.attr, oneMap(a.vs, transformLocalTime), transformLocalTime) //, set2arrayLocalTime, refNs, extsLocalTime, value2jsonLocalTime)
    case a: AttrMapTacLocalDateTime  => addMap(a.ns, a.attr, oneMap(a.vs, transformLocalDateTime), transformLocalDateTime) //, set2arrayLocalDateTime, refNs, extsLocalDateTime, value2jsonLocalDateTime)
    case a: AttrMapTacOffsetTime     => addMap(a.ns, a.attr, oneMap(a.vs, transformOffsetTime), transformOffsetTime) //, set2arrayOffsetTime, refNs, extsOffsetTime, value2jsonOffsetTime)
    case a: AttrMapTacOffsetDateTime => addMap(a.ns, a.attr, oneMap(a.vs, transformOffsetDateTime), transformOffsetDateTime) //, set2arrayOffsetDateTime, refNs, extsOffsetDateTime, value2jsonOffsetDateTime)
    case a: AttrMapTacZonedDateTime  => addMap(a.ns, a.attr, oneMap(a.vs, transformZonedDateTime), transformZonedDateTime) //, set2arrayZonedDateTime, refNs, extsZonedDateTime, value2jsonZonedDateTime)
    case a: AttrMapTacUUID           => addMap(a.ns, a.attr, oneMap(a.vs, transformUUID), transformUUID) //, set2arrayUUID, refNs, extsUUID, value2jsonUUID)
    case a: AttrMapTacURI            => addMap(a.ns, a.attr, oneMap(a.vs, transformURI), transformURI) //, set2arrayURI, refNs, extsURI, value2jsonURI)
    case a: AttrMapTacByte           => addMap(a.ns, a.attr, oneMap(a.vs, transformByte), transformByte) //, set2arrayByte, refNs, extsByte, value2jsonByte)
    case a: AttrMapTacShort          => addMap(a.ns, a.attr, oneMap(a.vs, transformShort), transformShort) //, set2arrayShort, refNs, extsShort, value2jsonShort)
    case a: AttrMapTacChar           => addMap(a.ns, a.attr, oneMap(a.vs, transformChar), transformChar) //, set2arrayChar, refNs, extsChar, value2jsonChar)
  }

  private def resolveAttrMapOpt(a: AttrMapOpt): Unit = {
    a match {
      case a: AttrMapOptID             => addMap(a.ns, a.attr, a.vs, transformID) //, set2arrayID, refNs, extsID, value2jsonID)
      case a: AttrMapOptString         => addMap(a.ns, a.attr, a.vs, transformString) //, set2arrayString, refNs, extsString, value2jsonString)
      case a: AttrMapOptInt            => addMap(a.ns, a.attr, a.vs, transformInt) //, set2arrayInt, refNs, extsInt, value2jsonInt)
      case a: AttrMapOptLong           => addMap(a.ns, a.attr, a.vs, transformLong) //, set2arrayLong, refNs, extsLong, value2jsonLong)
      case a: AttrMapOptFloat          => addMap(a.ns, a.attr, a.vs, transformFloat) //, set2arrayFloat, refNs, extsFloat, value2jsonFloat)
      case a: AttrMapOptDouble         => addMap(a.ns, a.attr, a.vs, transformDouble) //, set2arrayDouble, refNs, extsDouble, value2jsonDouble)
      case a: AttrMapOptBoolean        => addMap(a.ns, a.attr, a.vs, transformBoolean) //, set2arrayBoolean, refNs, extsBoolean, value2jsonBoolean)
      case a: AttrMapOptBigInt         => addMap(a.ns, a.attr, a.vs, transformBigInt) //, set2arrayBigInt, refNs, extsBigInt, value2jsonBigInt)
      case a: AttrMapOptBigDecimal     => addMap(a.ns, a.attr, a.vs, transformBigDecimal) //, set2arrayBigDecimal, refNs, extsBigDecimal, value2jsonBigDecimal)
      case a: AttrMapOptDate           => addMap(a.ns, a.attr, a.vs, transformDate) //, set2arrayDate, refNs, extsDate, value2jsonDate)
      case a: AttrMapOptDuration       => addMap(a.ns, a.attr, a.vs, transformDuration) //, set2arrayDuration, refNs, extsDuration, value2jsonDuration)
      case a: AttrMapOptInstant        => addMap(a.ns, a.attr, a.vs, transformInstant) //, set2arrayInstant, refNs, extsInstant, value2jsonInstant)
      case a: AttrMapOptLocalDate      => addMap(a.ns, a.attr, a.vs, transformLocalDate) //, set2arrayLocalDate, refNs, extsLocalDate, value2jsonLocalDate)
      case a: AttrMapOptLocalTime      => addMap(a.ns, a.attr, a.vs, transformLocalTime) //, set2arrayLocalTime, refNs, extsLocalTime, value2jsonLocalTime)
      case a: AttrMapOptLocalDateTime  => addMap(a.ns, a.attr, a.vs, transformLocalDateTime) //, set2arrayLocalDateTime, refNs, extsLocalDateTime, value2jsonLocalDateTime)
      case a: AttrMapOptOffsetTime     => addMap(a.ns, a.attr, a.vs, transformOffsetTime) //, set2arrayOffsetTime, refNs, extsOffsetTime, value2jsonOffsetTime)
      case a: AttrMapOptOffsetDateTime => addMap(a.ns, a.attr, a.vs, transformOffsetDateTime) //, set2arrayOffsetDateTime, refNs, extsOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrMapOptZonedDateTime  => addMap(a.ns, a.attr, a.vs, transformZonedDateTime) //, set2arrayZonedDateTime, refNs, extsZonedDateTime, value2jsonZonedDateTime)
      case a: AttrMapOptUUID           => addMap(a.ns, a.attr, a.vs, transformUUID) //, set2arrayUUID, refNs, extsUUID, value2jsonUUID)
      case a: AttrMapOptURI            => addMap(a.ns, a.attr, a.vs, transformURI) //, set2arrayURI, refNs, extsURI, value2jsonURI)
      case a: AttrMapOptByte           => addMap(a.ns, a.attr, a.vs, transformByte) //, set2arrayByte, refNs, extsByte, value2jsonByte)
      case a: AttrMapOptShort          => addMap(a.ns, a.attr, a.vs, transformShort) //, set2arrayShort, refNs, extsShort, value2jsonShort)
      case a: AttrMapOptChar           => addMap(a.ns, a.attr, a.vs, transformChar) //, set2arrayChar, refNs, extsChar, value2jsonChar)
    }
  }
}