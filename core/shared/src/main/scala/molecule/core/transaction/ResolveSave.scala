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


  private def oneSet[T](
    ns: String,
    attr: String,
    sets: Seq[Set[T]],
  ): Option[Set[T]] = {
    sets match {
      case Seq(set)     => Some(set)
      case Nil          => None
      case multipleSets => throw ExecutionError(
        s"Can only save one Set of values for Set attribute `$ns.$attr`. Found: " + multipleSets.mkString(", ")
      )
    }
  }
  private def resolveAttrSetMan(a: AttrSetMan): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrSetManID             => addSet(ns, attr, oneSet(ns, attr, a.vs), transformID, set2arrayID, refNs, extsID, value2jsonID)
      case a: AttrSetManString         => addSet(ns, attr, oneSet(ns, attr, a.vs), transformString, set2arrayString, refNs, extsString, value2jsonString)
      case a: AttrSetManInt            => addSet(ns, attr, oneSet(ns, attr, a.vs), transformInt, set2arrayInt, refNs, extsInt, value2jsonInt)
      case a: AttrSetManLong           => addSet(ns, attr, oneSet(ns, attr, a.vs), transformLong, set2arrayLong, refNs, extsLong, value2jsonLong)
      case a: AttrSetManFloat          => addSet(ns, attr, oneSet(ns, attr, a.vs), transformFloat, set2arrayFloat, refNs, extsFloat, value2jsonFloat)
      case a: AttrSetManDouble         => addSet(ns, attr, oneSet(ns, attr, a.vs), transformDouble, set2arrayDouble, refNs, extsDouble, value2jsonDouble)
      case a: AttrSetManBoolean        => addSet(ns, attr, oneSet(ns, attr, a.vs), transformBoolean, set2arrayBoolean, refNs, extsBoolean, value2jsonBoolean)
      case a: AttrSetManBigInt         => addSet(ns, attr, oneSet(ns, attr, a.vs), transformBigInt, set2arrayBigInt, refNs, extsBigInt, value2jsonBigInt)
      case a: AttrSetManBigDecimal     => addSet(ns, attr, oneSet(ns, attr, a.vs), transformBigDecimal, set2arrayBigDecimal, refNs, extsBigDecimal, value2jsonBigDecimal)
      case a: AttrSetManDate           => addSet(ns, attr, oneSet(ns, attr, a.vs), transformDate, set2arrayDate, refNs, extsDate, value2jsonDate)
      case a: AttrSetManDuration       => addSet(ns, attr, oneSet(ns, attr, a.vs), transformDuration, set2arrayDuration, refNs, extsDuration, value2jsonDuration)
      case a: AttrSetManInstant        => addSet(ns, attr, oneSet(ns, attr, a.vs), transformInstant, set2arrayInstant, refNs, extsInstant, value2jsonInstant)
      case a: AttrSetManLocalDate      => addSet(ns, attr, oneSet(ns, attr, a.vs), transformLocalDate, set2arrayLocalDate, refNs, extsLocalDate, value2jsonLocalDate)
      case a: AttrSetManLocalTime      => addSet(ns, attr, oneSet(ns, attr, a.vs), transformLocalTime, set2arrayLocalTime, refNs, extsLocalTime, value2jsonLocalTime)
      case a: AttrSetManLocalDateTime  => addSet(ns, attr, oneSet(ns, attr, a.vs), transformLocalDateTime, set2arrayLocalDateTime, refNs, extsLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSetManOffsetTime     => addSet(ns, attr, oneSet(ns, attr, a.vs), transformOffsetTime, set2arrayOffsetTime, refNs, extsOffsetTime, value2jsonOffsetTime)
      case a: AttrSetManOffsetDateTime => addSet(ns, attr, oneSet(ns, attr, a.vs), transformOffsetDateTime, set2arrayOffsetDateTime, refNs, extsOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSetManZonedDateTime  => addSet(ns, attr, oneSet(ns, attr, a.vs), transformZonedDateTime, set2arrayZonedDateTime, refNs, extsZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSetManUUID           => addSet(ns, attr, oneSet(ns, attr, a.vs), transformUUID, set2arrayUUID, refNs, extsUUID, value2jsonUUID)
      case a: AttrSetManURI            => addSet(ns, attr, oneSet(ns, attr, a.vs), transformURI, set2arrayURI, refNs, extsURI, value2jsonURI)
      case a: AttrSetManByte           => addSet(ns, attr, oneSet(ns, attr, a.vs), transformByte, set2arrayByte, refNs, extsByte, value2jsonByte)
      case a: AttrSetManShort          => addSet(ns, attr, oneSet(ns, attr, a.vs), transformShort, set2arrayShort, refNs, extsShort, value2jsonShort)
      case a: AttrSetManChar           => addSet(ns, attr, oneSet(ns, attr, a.vs), transformChar, set2arrayChar, refNs, extsChar, value2jsonChar)
    }
  }
  private def resolveAttrSetTac(a: AttrSetTac): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrSetTacID             => addSet(ns, attr, oneSet(ns, attr, a.vs), transformID, set2arrayID, refNs, extsID, value2jsonID)
      case a: AttrSetTacString         => addSet(ns, attr, oneSet(ns, attr, a.vs), transformString, set2arrayString, refNs, extsString, value2jsonString)
      case a: AttrSetTacInt            => addSet(ns, attr, oneSet(ns, attr, a.vs), transformInt, set2arrayInt, refNs, extsInt, value2jsonInt)
      case a: AttrSetTacLong           => addSet(ns, attr, oneSet(ns, attr, a.vs), transformLong, set2arrayLong, refNs, extsLong, value2jsonLong)
      case a: AttrSetTacFloat          => addSet(ns, attr, oneSet(ns, attr, a.vs), transformFloat, set2arrayFloat, refNs, extsFloat, value2jsonFloat)
      case a: AttrSetTacDouble         => addSet(ns, attr, oneSet(ns, attr, a.vs), transformDouble, set2arrayDouble, refNs, extsDouble, value2jsonDouble)
      case a: AttrSetTacBoolean        => addSet(ns, attr, oneSet(ns, attr, a.vs), transformBoolean, set2arrayBoolean, refNs, extsBoolean, value2jsonBoolean)
      case a: AttrSetTacBigInt         => addSet(ns, attr, oneSet(ns, attr, a.vs), transformBigInt, set2arrayBigInt, refNs, extsBigInt, value2jsonBigInt)
      case a: AttrSetTacBigDecimal     => addSet(ns, attr, oneSet(ns, attr, a.vs), transformBigDecimal, set2arrayBigDecimal, refNs, extsBigDecimal, value2jsonBigDecimal)
      case a: AttrSetTacDate           => addSet(ns, attr, oneSet(ns, attr, a.vs), transformDate, set2arrayDate, refNs, extsDate, value2jsonDate)
      case a: AttrSetTacDuration       => addSet(ns, attr, oneSet(ns, attr, a.vs), transformDuration, set2arrayDuration, refNs, extsDuration, value2jsonDuration)
      case a: AttrSetTacInstant        => addSet(ns, attr, oneSet(ns, attr, a.vs), transformInstant, set2arrayInstant, refNs, extsInstant, value2jsonInstant)
      case a: AttrSetTacLocalDate      => addSet(ns, attr, oneSet(ns, attr, a.vs), transformLocalDate, set2arrayLocalDate, refNs, extsLocalDate, value2jsonLocalDate)
      case a: AttrSetTacLocalTime      => addSet(ns, attr, oneSet(ns, attr, a.vs), transformLocalTime, set2arrayLocalTime, refNs, extsLocalTime, value2jsonLocalTime)
      case a: AttrSetTacLocalDateTime  => addSet(ns, attr, oneSet(ns, attr, a.vs), transformLocalDateTime, set2arrayLocalDateTime, refNs, extsLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSetTacOffsetTime     => addSet(ns, attr, oneSet(ns, attr, a.vs), transformOffsetTime, set2arrayOffsetTime, refNs, extsOffsetTime, value2jsonOffsetTime)
      case a: AttrSetTacOffsetDateTime => addSet(ns, attr, oneSet(ns, attr, a.vs), transformOffsetDateTime, set2arrayOffsetDateTime, refNs, extsOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSetTacZonedDateTime  => addSet(ns, attr, oneSet(ns, attr, a.vs), transformZonedDateTime, set2arrayZonedDateTime, refNs, extsZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSetTacUUID           => addSet(ns, attr, oneSet(ns, attr, a.vs), transformUUID, set2arrayUUID, refNs, extsUUID, value2jsonUUID)
      case a: AttrSetTacURI            => addSet(ns, attr, oneSet(ns, attr, a.vs), transformURI, set2arrayURI, refNs, extsURI, value2jsonURI)
      case a: AttrSetTacByte           => addSet(ns, attr, oneSet(ns, attr, a.vs), transformByte, set2arrayByte, refNs, extsByte, value2jsonByte)
      case a: AttrSetTacShort          => addSet(ns, attr, oneSet(ns, attr, a.vs), transformShort, set2arrayShort, refNs, extsShort, value2jsonShort)
      case a: AttrSetTacChar           => addSet(ns, attr, oneSet(ns, attr, a.vs), transformChar, set2arrayChar, refNs, extsChar, value2jsonChar)
    }
  }

  private def oneOptSet[T](
    ns: String,
    attr: String,
    optSets: Option[Seq[Set[T]]],
  ): Option[Set[T]] = {
    optSets.flatMap {
      case Seq(set) => Some(set)
      case Nil      => None
      case vs       => throw ExecutionError(
        s"Can only save one Set of values for optional Set attribute `$ns.$attr`. Found: " + vs.mkString(", ")
      )
    }
  }

  private def resolveAttrSetOpt(a: AttrSetOpt): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrSetOptID             => addSet(ns, attr, oneOptSet(ns, attr, a.vs), transformID, set2arrayID, refNs, extsID, value2jsonID)
      case a: AttrSetOptString         => addSet(ns, attr, oneOptSet(ns, attr, a.vs), transformString, set2arrayString, refNs, extsString, value2jsonString)
      case a: AttrSetOptInt            => addSet(ns, attr, oneOptSet(ns, attr, a.vs), transformInt, set2arrayInt, refNs, extsInt, value2jsonInt)
      case a: AttrSetOptLong           => addSet(ns, attr, oneOptSet(ns, attr, a.vs), transformLong, set2arrayLong, refNs, extsLong, value2jsonLong)
      case a: AttrSetOptFloat          => addSet(ns, attr, oneOptSet(ns, attr, a.vs), transformFloat, set2arrayFloat, refNs, extsFloat, value2jsonFloat)
      case a: AttrSetOptDouble         => addSet(ns, attr, oneOptSet(ns, attr, a.vs), transformDouble, set2arrayDouble, refNs, extsDouble, value2jsonDouble)
      case a: AttrSetOptBoolean        => addSet(ns, attr, oneOptSet(ns, attr, a.vs), transformBoolean, set2arrayBoolean, refNs, extsBoolean, value2jsonBoolean)
      case a: AttrSetOptBigInt         => addSet(ns, attr, oneOptSet(ns, attr, a.vs), transformBigInt, set2arrayBigInt, refNs, extsBigInt, value2jsonBigInt)
      case a: AttrSetOptBigDecimal     => addSet(ns, attr, oneOptSet(ns, attr, a.vs), transformBigDecimal, set2arrayBigDecimal, refNs, extsBigDecimal, value2jsonBigDecimal)
      case a: AttrSetOptDate           => addSet(ns, attr, oneOptSet(ns, attr, a.vs), transformDate, set2arrayDate, refNs, extsDate, value2jsonDate)
      case a: AttrSetOptDuration       => addSet(ns, attr, oneOptSet(ns, attr, a.vs), transformDuration, set2arrayDuration, refNs, extsDuration, value2jsonDuration)
      case a: AttrSetOptInstant        => addSet(ns, attr, oneOptSet(ns, attr, a.vs), transformInstant, set2arrayInstant, refNs, extsInstant, value2jsonInstant)
      case a: AttrSetOptLocalDate      => addSet(ns, attr, oneOptSet(ns, attr, a.vs), transformLocalDate, set2arrayLocalDate, refNs, extsLocalDate, value2jsonLocalDate)
      case a: AttrSetOptLocalTime      => addSet(ns, attr, oneOptSet(ns, attr, a.vs), transformLocalTime, set2arrayLocalTime, refNs, extsLocalTime, value2jsonLocalTime)
      case a: AttrSetOptLocalDateTime  => addSet(ns, attr, oneOptSet(ns, attr, a.vs), transformLocalDateTime, set2arrayLocalDateTime, refNs, extsLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSetOptOffsetTime     => addSet(ns, attr, oneOptSet(ns, attr, a.vs), transformOffsetTime, set2arrayOffsetTime, refNs, extsOffsetTime, value2jsonOffsetTime)
      case a: AttrSetOptOffsetDateTime => addSet(ns, attr, oneOptSet(ns, attr, a.vs), transformOffsetDateTime, set2arrayOffsetDateTime, refNs, extsOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSetOptZonedDateTime  => addSet(ns, attr, oneOptSet(ns, attr, a.vs), transformZonedDateTime, set2arrayZonedDateTime, refNs, extsZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSetOptUUID           => addSet(ns, attr, oneOptSet(ns, attr, a.vs), transformUUID, set2arrayUUID, refNs, extsUUID, value2jsonUUID)
      case a: AttrSetOptURI            => addSet(ns, attr, oneOptSet(ns, attr, a.vs), transformURI, set2arrayURI, refNs, extsURI, value2jsonURI)
      case a: AttrSetOptByte           => addSet(ns, attr, oneOptSet(ns, attr, a.vs), transformByte, set2arrayByte, refNs, extsByte, value2jsonByte)
      case a: AttrSetOptShort          => addSet(ns, attr, oneOptSet(ns, attr, a.vs), transformShort, set2arrayShort, refNs, extsShort, value2jsonShort)
      case a: AttrSetOptChar           => addSet(ns, attr, oneOptSet(ns, attr, a.vs), transformChar, set2arrayChar, refNs, extsChar, value2jsonChar)
    }
  }

  private def noMultipleSeqs[T](ns: String, attr: String, seqs: Seq[Seq[T]]) = {
    throw ExecutionError(
      s"Can only save one Seq of values for Seq attribute `$ns.$attr`. Found multiple seqs:\n" +
        seqs.mkString("\n")
    )
  }
  private def oneSeq[T](
    ns: String,
    attr: String,
    seqs: Seq[Seq[T]],
  ): Option[Seq[T]] = {
    seqs match {
      case Seq(seq)     => Some(seq)
      case Nil          => None
      case multipleSeqs => noMultipleSeqs(ns, attr, multipleSeqs)
    }
  }

  private def resolveAttrSeqMan(a: AttrSeqMan): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrSeqManID             => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformID, seq2arrayID, extsID, value2jsonID)
      case a: AttrSeqManString         => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformString, seq2arrayString, extsString, value2jsonString)
      case a: AttrSeqManInt            => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformInt, seq2arrayInt, extsInt, value2jsonInt)
      case a: AttrSeqManLong           => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformLong, seq2arrayLong, extsLong, value2jsonLong)
      case a: AttrSeqManFloat          => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformFloat, seq2arrayFloat, extsFloat, value2jsonFloat)
      case a: AttrSeqManDouble         => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformDouble, seq2arrayDouble, extsDouble, value2jsonDouble)
      case a: AttrSeqManBoolean        => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformBoolean, seq2arrayBoolean, extsBoolean, value2jsonBoolean)
      case a: AttrSeqManBigInt         => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformBigInt, seq2arrayBigInt, extsBigInt, value2jsonBigInt)
      case a: AttrSeqManBigDecimal     => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformBigDecimal, seq2arrayBigDecimal, extsBigDecimal, value2jsonBigDecimal)
      case a: AttrSeqManDate           => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformDate, seq2arrayDate, extsDate, value2jsonDate)
      case a: AttrSeqManDuration       => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformDuration, seq2arrayDuration, extsDuration, value2jsonDuration)
      case a: AttrSeqManInstant        => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformInstant, seq2arrayInstant, extsInstant, value2jsonInstant)
      case a: AttrSeqManLocalDate      => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformLocalDate, seq2arrayLocalDate, extsLocalDate, value2jsonLocalDate)
      case a: AttrSeqManLocalTime      => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformLocalTime, seq2arrayLocalTime, extsLocalTime, value2jsonLocalTime)
      case a: AttrSeqManLocalDateTime  => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformLocalDateTime, seq2arrayLocalDateTime, extsLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSeqManOffsetTime     => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformOffsetTime, seq2arrayOffsetTime, extsOffsetTime, value2jsonOffsetTime)
      case a: AttrSeqManOffsetDateTime => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformOffsetDateTime, seq2arrayOffsetDateTime, extsOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSeqManZonedDateTime  => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformZonedDateTime, seq2arrayZonedDateTime, extsZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSeqManUUID           => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformUUID, seq2arrayUUID, extsUUID, value2jsonUUID)
      case a: AttrSeqManURI            => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformURI, seq2arrayURI, extsURI, value2jsonURI)
      case a: AttrSeqManByte           => addByteArray(ns, attr, optByteArray(ns, attr, a.vs))
      case a: AttrSeqManShort          => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformShort, seq2arrayShort, extsShort, value2jsonShort)
      case a: AttrSeqManChar           => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformChar, seq2arrayChar, extsChar, value2jsonChar)
    }
  }
  private def resolveAttrSeqTac(a: AttrSeqTac): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrSeqTacID             => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformID, seq2arrayID, extsID, value2jsonID)
      case a: AttrSeqTacString         => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformString, seq2arrayString, extsString, value2jsonString)
      case a: AttrSeqTacInt            => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformInt, seq2arrayInt, extsInt, value2jsonInt)
      case a: AttrSeqTacLong           => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformLong, seq2arrayLong, extsLong, value2jsonLong)
      case a: AttrSeqTacFloat          => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformFloat, seq2arrayFloat, extsFloat, value2jsonFloat)
      case a: AttrSeqTacDouble         => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformDouble, seq2arrayDouble, extsDouble, value2jsonDouble)
      case a: AttrSeqTacBoolean        => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformBoolean, seq2arrayBoolean, extsBoolean, value2jsonBoolean)
      case a: AttrSeqTacBigInt         => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformBigInt, seq2arrayBigInt, extsBigInt, value2jsonBigInt)
      case a: AttrSeqTacBigDecimal     => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformBigDecimal, seq2arrayBigDecimal, extsBigDecimal, value2jsonBigDecimal)
      case a: AttrSeqTacDate           => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformDate, seq2arrayDate, extsDate, value2jsonDate)
      case a: AttrSeqTacDuration       => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformDuration, seq2arrayDuration, extsDuration, value2jsonDuration)
      case a: AttrSeqTacInstant        => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformInstant, seq2arrayInstant, extsInstant, value2jsonInstant)
      case a: AttrSeqTacLocalDate      => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformLocalDate, seq2arrayLocalDate, extsLocalDate, value2jsonLocalDate)
      case a: AttrSeqTacLocalTime      => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformLocalTime, seq2arrayLocalTime, extsLocalTime, value2jsonLocalTime)
      case a: AttrSeqTacLocalDateTime  => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformLocalDateTime, seq2arrayLocalDateTime, extsLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSeqTacOffsetTime     => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformOffsetTime, seq2arrayOffsetTime, extsOffsetTime, value2jsonOffsetTime)
      case a: AttrSeqTacOffsetDateTime => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformOffsetDateTime, seq2arrayOffsetDateTime, extsOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSeqTacZonedDateTime  => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformZonedDateTime, seq2arrayZonedDateTime, extsZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSeqTacUUID           => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformUUID, seq2arrayUUID, extsUUID, value2jsonUUID)
      case a: AttrSeqTacURI            => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformURI, seq2arrayURI, extsURI, value2jsonURI)
      case a: AttrSeqTacByte           => addByteArray(ns, attr, optByteArray(ns, attr, a.vs))
      case a: AttrSeqTacShort          => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformShort, seq2arrayShort, extsShort, value2jsonShort)
      case a: AttrSeqTacChar           => addSeq(ns, attr, refNs, oneSeq(ns, attr, a.vs), transformChar, seq2arrayChar, extsChar, value2jsonChar)
    }
  }

  private def oneOptSeq[T](
    ns: String,
    attr: String,
    optSeqs: Option[Seq[Seq[T]]],
  ): Option[Seq[T]] = {
    optSeqs.flatMap {
      case Seq(seq)     => Some(seq)
      case Nil          => None
      case multipleSeqs => noMultipleSeqs(ns, attr, multipleSeqs)
    }
  }

  private def resolveAttrSeqOpt(a: AttrSeqOpt): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrSeqOptID             => addSeq(ns, attr, refNs, oneOptSeq(ns, attr, a.vs), transformID, seq2arrayID, extsID, value2jsonID)
      case a: AttrSeqOptString         => addSeq(ns, attr, refNs, oneOptSeq(ns, attr, a.vs), transformString, seq2arrayString, extsString, value2jsonString)
      case a: AttrSeqOptInt            => addSeq(ns, attr, refNs, oneOptSeq(ns, attr, a.vs), transformInt, seq2arrayInt, extsInt, value2jsonInt)
      case a: AttrSeqOptLong           => addSeq(ns, attr, refNs, oneOptSeq(ns, attr, a.vs), transformLong, seq2arrayLong, extsLong, value2jsonLong)
      case a: AttrSeqOptFloat          => addSeq(ns, attr, refNs, oneOptSeq(ns, attr, a.vs), transformFloat, seq2arrayFloat, extsFloat, value2jsonFloat)
      case a: AttrSeqOptDouble         => addSeq(ns, attr, refNs, oneOptSeq(ns, attr, a.vs), transformDouble, seq2arrayDouble, extsDouble, value2jsonDouble)
      case a: AttrSeqOptBoolean        => addSeq(ns, attr, refNs, oneOptSeq(ns, attr, a.vs), transformBoolean, seq2arrayBoolean, extsBoolean, value2jsonBoolean)
      case a: AttrSeqOptBigInt         => addSeq(ns, attr, refNs, oneOptSeq(ns, attr, a.vs), transformBigInt, seq2arrayBigInt, extsBigInt, value2jsonBigInt)
      case a: AttrSeqOptBigDecimal     => addSeq(ns, attr, refNs, oneOptSeq(ns, attr, a.vs), transformBigDecimal, seq2arrayBigDecimal, extsBigDecimal, value2jsonBigDecimal)
      case a: AttrSeqOptDate           => addSeq(ns, attr, refNs, oneOptSeq(ns, attr, a.vs), transformDate, seq2arrayDate, extsDate, value2jsonDate)
      case a: AttrSeqOptDuration       => addSeq(ns, attr, refNs, oneOptSeq(ns, attr, a.vs), transformDuration, seq2arrayDuration, extsDuration, value2jsonDuration)
      case a: AttrSeqOptInstant        => addSeq(ns, attr, refNs, oneOptSeq(ns, attr, a.vs), transformInstant, seq2arrayInstant, extsInstant, value2jsonInstant)
      case a: AttrSeqOptLocalDate      => addSeq(ns, attr, refNs, oneOptSeq(ns, attr, a.vs), transformLocalDate, seq2arrayLocalDate, extsLocalDate, value2jsonLocalDate)
      case a: AttrSeqOptLocalTime      => addSeq(ns, attr, refNs, oneOptSeq(ns, attr, a.vs), transformLocalTime, seq2arrayLocalTime, extsLocalTime, value2jsonLocalTime)
      case a: AttrSeqOptLocalDateTime  => addSeq(ns, attr, refNs, oneOptSeq(ns, attr, a.vs), transformLocalDateTime, seq2arrayLocalDateTime, extsLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSeqOptOffsetTime     => addSeq(ns, attr, refNs, oneOptSeq(ns, attr, a.vs), transformOffsetTime, seq2arrayOffsetTime, extsOffsetTime, value2jsonOffsetTime)
      case a: AttrSeqOptOffsetDateTime => addSeq(ns, attr, refNs, oneOptSeq(ns, attr, a.vs), transformOffsetDateTime, seq2arrayOffsetDateTime, extsOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSeqOptZonedDateTime  => addSeq(ns, attr, refNs, oneOptSeq(ns, attr, a.vs), transformZonedDateTime, seq2arrayZonedDateTime, extsZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSeqOptUUID           => addSeq(ns, attr, refNs, oneOptSeq(ns, attr, a.vs), transformUUID, seq2arrayUUID, extsUUID, value2jsonUUID)
      case a: AttrSeqOptURI            => addSeq(ns, attr, refNs, oneOptSeq(ns, attr, a.vs), transformURI, seq2arrayURI, extsURI, value2jsonURI)
      case a: AttrSeqOptByte           => addByteArray(ns, attr, a.vs.flatMap(vs => optByteArray(ns, attr, vs)))
      case a: AttrSeqOptShort          => addSeq(ns, attr, refNs, oneOptSeq(ns, attr, a.vs), transformShort, seq2arrayShort, extsShort, value2jsonShort)
      case a: AttrSeqOptChar           => addSeq(ns, attr, refNs, oneOptSeq(ns, attr, a.vs), transformChar, seq2arrayChar, extsChar, value2jsonChar)
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