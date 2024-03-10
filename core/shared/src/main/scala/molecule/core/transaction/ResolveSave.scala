package molecule.core.transaction

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.ConnProxy
import molecule.core.transaction.ops.SaveOps
import molecule.core.util.ModelUtils
import scala.annotation.tailrec

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

            case a: AttrArr => a match {
              case a: AttrArrMan => resolveAttrArrayMan(a); resolve(tail)
              case a: AttrArrOpt => resolveAttrArrayOpt(a); resolve(tail)
              case a: AttrArrTac => resolveAttrArrayTac(a); resolve(tail)
            }

            case a: AttrMap =>
              //              a match {
              //                case a: AttrMapMan => resolveAttrMapMan(a); resolve(tail)
              //                case a: AttrMapOpt => resolveAttrMapOpt(a); resolve(tail)
              //                case a: AttrMapTac => resolveAttrMapTac(a); resolve(tail)
              //              }
              ???
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
    transformValue: T => Any
  ): Option[Any] = {
    vs match {
      case Seq(v) => Some(transformValue(v))
      case Nil    => None
      case vs     => throw ExecutionError(
        s"Can only save one value for attribute `$ns.$attr`. Found: " + vs.mkString(", ")
      )
    }
  }
  private def resolveAttrOneMan(a: AttrOneMan): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrOneManID             => addOne(ns, attr, oneV(ns, attr, a.vs, transformID), handleID, extsID)
      case a: AttrOneManString         => addOne(ns, attr, oneV(ns, attr, a.vs, transformString), handleString, extsString)
      case a: AttrOneManInt            => addOne(ns, attr, oneV(ns, attr, a.vs, transformInt), handleInt, extsInt)
      case a: AttrOneManLong           => addOne(ns, attr, oneV(ns, attr, a.vs, transformLong), handleLong, extsLong)
      case a: AttrOneManFloat          => addOne(ns, attr, oneV(ns, attr, a.vs, transformFloat), handleFloat, extsFloat)
      case a: AttrOneManDouble         => addOne(ns, attr, oneV(ns, attr, a.vs, transformDouble), handleDouble, extsDouble)
      case a: AttrOneManBoolean        => addOne(ns, attr, oneV(ns, attr, a.vs, transformBoolean), handleBoolean, extsBoolean)
      case a: AttrOneManBigInt         => addOne(ns, attr, oneV(ns, attr, a.vs, transformBigInt), handleBigInt, extsBigInt)
      case a: AttrOneManBigDecimal     => addOne(ns, attr, oneV(ns, attr, a.vs, transformBigDecimal), handleBigDecimal, extsBigDecimal)
      case a: AttrOneManDate           => addOne(ns, attr, oneV(ns, attr, a.vs, transformDate), handleDate, extsDate)
      case a: AttrOneManDuration       => addOne(ns, attr, oneV(ns, attr, a.vs, transformDuration), handleDuration, extsDuration)
      case a: AttrOneManInstant        => addOne(ns, attr, oneV(ns, attr, a.vs, transformInstant), handleInstant, extsInstant)
      case a: AttrOneManLocalDate      => addOne(ns, attr, oneV(ns, attr, a.vs, transformLocalDate), handleLocalDate, extsLocalDate)
      case a: AttrOneManLocalTime      => addOne(ns, attr, oneV(ns, attr, a.vs, transformLocalTime), handleLocalTime, extsLocalTime)
      case a: AttrOneManLocalDateTime  => addOne(ns, attr, oneV(ns, attr, a.vs, transformLocalDateTime), handleLocalDateTime, extsLocalDateTime)
      case a: AttrOneManOffsetTime     => addOne(ns, attr, oneV(ns, attr, a.vs, transformOffsetTime), handleOffsetTime, extsOffsetTime)
      case a: AttrOneManOffsetDateTime => addOne(ns, attr, oneV(ns, attr, a.vs, transformOffsetDateTime), handleOffsetDateTime, extsOffsetDateTime)
      case a: AttrOneManZonedDateTime  => addOne(ns, attr, oneV(ns, attr, a.vs, transformZonedDateTime), handleZonedDateTime, extsZonedDateTime)
      case a: AttrOneManUUID           => addOne(ns, attr, oneV(ns, attr, a.vs, transformUUID), handleUUID, extsUUID)
      case a: AttrOneManURI            => addOne(ns, attr, oneV(ns, attr, a.vs, transformURI), handleURI, extsURI)
      case a: AttrOneManByte           => addOne(ns, attr, oneV(ns, attr, a.vs, transformByte), handleByte, extsByte)
      case a: AttrOneManShort          => addOne(ns, attr, oneV(ns, attr, a.vs, transformShort), handleShort, extsShort)
      case a: AttrOneManChar           => addOne(ns, attr, oneV(ns, attr, a.vs, transformChar), handleChar, extsChar)
    }
  }
  private def resolveAttrOneTac(a: AttrOneTac): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrOneTacID             => addOne(ns, attr, oneV(ns, attr, a.vs, transformID), handleID, extsID)
      case a: AttrOneTacString         => addOne(ns, attr, oneV(ns, attr, a.vs, transformString), handleString, extsString)
      case a: AttrOneTacInt            => addOne(ns, attr, oneV(ns, attr, a.vs, transformInt), handleInt, extsInt)
      case a: AttrOneTacLong           => addOne(ns, attr, oneV(ns, attr, a.vs, transformLong), handleLong, extsLong)
      case a: AttrOneTacFloat          => addOne(ns, attr, oneV(ns, attr, a.vs, transformFloat), handleFloat, extsFloat)
      case a: AttrOneTacDouble         => addOne(ns, attr, oneV(ns, attr, a.vs, transformDouble), handleDouble, extsDouble)
      case a: AttrOneTacBoolean        => addOne(ns, attr, oneV(ns, attr, a.vs, transformBoolean), handleBoolean, extsBoolean)
      case a: AttrOneTacBigInt         => addOne(ns, attr, oneV(ns, attr, a.vs, transformBigInt), handleBigInt, extsBigInt)
      case a: AttrOneTacBigDecimal     => addOne(ns, attr, oneV(ns, attr, a.vs, transformBigDecimal), handleBigDecimal, extsBigDecimal)
      case a: AttrOneTacDate           => addOne(ns, attr, oneV(ns, attr, a.vs, transformDate), handleDate, extsDate)
      case a: AttrOneTacDuration       => addOne(ns, attr, oneV(ns, attr, a.vs, transformDuration), handleDuration, extsDuration)
      case a: AttrOneTacInstant        => addOne(ns, attr, oneV(ns, attr, a.vs, transformInstant), handleInstant, extsInstant)
      case a: AttrOneTacLocalDate      => addOne(ns, attr, oneV(ns, attr, a.vs, transformLocalDate), handleLocalDate, extsLocalDate)
      case a: AttrOneTacLocalTime      => addOne(ns, attr, oneV(ns, attr, a.vs, transformLocalTime), handleLocalTime, extsLocalTime)
      case a: AttrOneTacLocalDateTime  => addOne(ns, attr, oneV(ns, attr, a.vs, transformLocalDateTime), handleLocalDateTime, extsLocalDateTime)
      case a: AttrOneTacOffsetTime     => addOne(ns, attr, oneV(ns, attr, a.vs, transformOffsetTime), handleOffsetTime, extsOffsetTime)
      case a: AttrOneTacOffsetDateTime => addOne(ns, attr, oneV(ns, attr, a.vs, transformOffsetDateTime), handleOffsetDateTime, extsOffsetDateTime)
      case a: AttrOneTacZonedDateTime  => addOne(ns, attr, oneV(ns, attr, a.vs, transformZonedDateTime), handleZonedDateTime, extsZonedDateTime)
      case a: AttrOneTacUUID           => addOne(ns, attr, oneV(ns, attr, a.vs, transformUUID), handleUUID, extsUUID)
      case a: AttrOneTacURI            => addOne(ns, attr, oneV(ns, attr, a.vs, transformURI), handleURI, extsURI)
      case a: AttrOneTacByte           => addOne(ns, attr, oneV(ns, attr, a.vs, transformByte), handleByte, extsByte)
      case a: AttrOneTacShort          => addOne(ns, attr, oneV(ns, attr, a.vs, transformShort), handleShort, extsShort)
      case a: AttrOneTacChar           => addOne(ns, attr, oneV(ns, attr, a.vs, transformChar), handleChar, extsChar)
    }
  }


  private def oneOptV[T](
    ns: String,
    attr: String,
    optVs: Option[Seq[T]],
    transformValue: T => Any
  ): Option[Any] = {
    optVs.flatMap {
      case Seq(v) => Some(transformValue(v))
      case Nil    => None
      case vs     => throw ExecutionError(
        s"Can only save one value for optional attribute `$ns.$attr`. Found: " + vs.mkString(", ")
      )
    }
  }
  private def resolveAttrOneOpt(a: AttrOneOpt): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrOneOptID             => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformID), handleID, extsID)
      case a: AttrOneOptString         => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformString), handleString, extsString)
      case a: AttrOneOptInt            => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformInt), handleInt, extsInt)
      case a: AttrOneOptLong           => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformLong), handleLong, extsLong)
      case a: AttrOneOptFloat          => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformFloat), handleFloat, extsFloat)
      case a: AttrOneOptDouble         => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformDouble), handleDouble, extsDouble)
      case a: AttrOneOptBoolean        => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformBoolean), handleBoolean, extsBoolean)
      case a: AttrOneOptBigInt         => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformBigInt), handleBigInt, extsBigInt)
      case a: AttrOneOptBigDecimal     => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformBigDecimal), handleBigDecimal, extsBigDecimal)
      case a: AttrOneOptDate           => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformDate), handleDate, extsDate)
      case a: AttrOneOptDuration       => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformDuration), handleDuration, extsDuration)
      case a: AttrOneOptInstant        => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformInstant), handleInstant, extsInstant)
      case a: AttrOneOptLocalDate      => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformLocalDate), handleLocalDate, extsLocalDate)
      case a: AttrOneOptLocalTime      => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformLocalTime), handleLocalTime, extsLocalTime)
      case a: AttrOneOptLocalDateTime  => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformLocalDateTime), handleLocalDateTime, extsLocalDateTime)
      case a: AttrOneOptOffsetTime     => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformOffsetTime), handleOffsetTime, extsOffsetTime)
      case a: AttrOneOptOffsetDateTime => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformOffsetDateTime), handleOffsetDateTime, extsOffsetDateTime)
      case a: AttrOneOptZonedDateTime  => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformZonedDateTime), handleZonedDateTime, extsZonedDateTime)
      case a: AttrOneOptUUID           => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformUUID), handleUUID, extsUUID)
      case a: AttrOneOptURI            => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformURI), handleURI, extsURI)
      case a: AttrOneOptByte           => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformByte), handleByte, extsByte)
      case a: AttrOneOptShort          => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformShort), handleShort, extsShort)
      case a: AttrOneOptChar           => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformChar), handleChar, extsChar)
    }
  }


  private def oneSet[T](
    ns: String,
    attr: String,
    sets: Seq[Set[T]],
    transformValue: T => Any
  ): Option[Set[Any]] = {
    sets match {
      case Seq(set)     => Some(set.map(transformValue))
      case Nil          => None
      case multipleSets => throw ExecutionError(
        s"Can only save one Set of values for Set attribute `$ns.$attr`. Found: " + multipleSets.mkString(", ")
      )
    }
  }
  private def resolveAttrSetMan(a: AttrSetMan): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrSetManID             => addSet(ns, attr, oneSet(ns, attr, a.vs, transformID), transformID, set2arrayID, refNs, extsID, value2jsonID)
      case a: AttrSetManString         => addSet(ns, attr, oneSet(ns, attr, a.vs, transformString), transformString, set2arrayString, refNs, extsString, value2jsonString)
      case a: AttrSetManInt            => addSet(ns, attr, oneSet(ns, attr, a.vs, transformInt), transformInt, set2arrayInt, refNs, extsInt, value2jsonInt)
      case a: AttrSetManLong           => addSet(ns, attr, oneSet(ns, attr, a.vs, transformLong), transformLong, set2arrayLong, refNs, extsLong, value2jsonLong)
      case a: AttrSetManFloat          => addSet(ns, attr, oneSet(ns, attr, a.vs, transformFloat), transformFloat, set2arrayFloat, refNs, extsFloat, value2jsonFloat)
      case a: AttrSetManDouble         => addSet(ns, attr, oneSet(ns, attr, a.vs, transformDouble), transformDouble, set2arrayDouble, refNs, extsDouble, value2jsonDouble)
      case a: AttrSetManBoolean        => addSet(ns, attr, oneSet(ns, attr, a.vs, transformBoolean), transformBoolean, set2arrayBoolean, refNs, extsBoolean, value2jsonBoolean)
      case a: AttrSetManBigInt         => addSet(ns, attr, oneSet(ns, attr, a.vs, transformBigInt), transformBigInt, set2arrayBigInt, refNs, extsBigInt, value2jsonBigInt)
      case a: AttrSetManBigDecimal     => addSet(ns, attr, oneSet(ns, attr, a.vs, transformBigDecimal), transformBigDecimal, set2arrayBigDecimal, refNs, extsBigDecimal, value2jsonBigDecimal)
      case a: AttrSetManDate           => addSet(ns, attr, oneSet(ns, attr, a.vs, transformDate), transformDate, set2arrayDate, refNs, extsDate, value2jsonDate)
      case a: AttrSetManDuration       => addSet(ns, attr, oneSet(ns, attr, a.vs, transformDuration), transformDuration, set2arrayDuration, refNs, extsDuration, value2jsonDuration)
      case a: AttrSetManInstant        => addSet(ns, attr, oneSet(ns, attr, a.vs, transformInstant), transformInstant, set2arrayInstant, refNs, extsInstant, value2jsonInstant)
      case a: AttrSetManLocalDate      => addSet(ns, attr, oneSet(ns, attr, a.vs, transformLocalDate), transformLocalDate, set2arrayLocalDate, refNs, extsLocalDate, value2jsonLocalDate)
      case a: AttrSetManLocalTime      => addSet(ns, attr, oneSet(ns, attr, a.vs, transformLocalTime), transformLocalTime, set2arrayLocalTime, refNs, extsLocalTime, value2jsonLocalTime)
      case a: AttrSetManLocalDateTime  => addSet(ns, attr, oneSet(ns, attr, a.vs, transformLocalDateTime), transformLocalDateTime, set2arrayLocalDateTime, refNs, extsLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSetManOffsetTime     => addSet(ns, attr, oneSet(ns, attr, a.vs, transformOffsetTime), transformOffsetTime, set2arrayOffsetTime, refNs, extsOffsetTime, value2jsonOffsetTime)
      case a: AttrSetManOffsetDateTime => addSet(ns, attr, oneSet(ns, attr, a.vs, transformOffsetDateTime), transformOffsetDateTime, set2arrayOffsetDateTime, refNs, extsOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSetManZonedDateTime  => addSet(ns, attr, oneSet(ns, attr, a.vs, transformZonedDateTime), transformZonedDateTime, set2arrayZonedDateTime, refNs, extsZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSetManUUID           => addSet(ns, attr, oneSet(ns, attr, a.vs, transformUUID), transformUUID, set2arrayUUID, refNs, extsUUID, value2jsonUUID)
      case a: AttrSetManURI            => addSet(ns, attr, oneSet(ns, attr, a.vs, transformURI), transformURI, set2arrayURI, refNs, extsURI, value2jsonURI)
      case a: AttrSetManByte           => addSet(ns, attr, oneSet(ns, attr, a.vs, transformByte), transformByte, set2arrayByte, refNs, extsByte, value2jsonByte)
      case a: AttrSetManShort          => addSet(ns, attr, oneSet(ns, attr, a.vs, transformShort), transformShort, set2arrayShort, refNs, extsShort, value2jsonShort)
      case a: AttrSetManChar           => addSet(ns, attr, oneSet(ns, attr, a.vs, transformChar), transformChar, set2arrayChar, refNs, extsChar, value2jsonChar)
    }
  }
  private def resolveAttrSetTac(a: AttrSetTac): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrSetTacID             => addSet(ns, attr, oneSet(ns, attr, a.vs, transformID), transformID, set2arrayID, refNs, extsID, value2jsonID)
      case a: AttrSetTacString         => addSet(ns, attr, oneSet(ns, attr, a.vs, transformString), transformString, set2arrayString, refNs, extsString, value2jsonString)
      case a: AttrSetTacInt            => addSet(ns, attr, oneSet(ns, attr, a.vs, transformInt), transformInt, set2arrayInt, refNs, extsInt, value2jsonInt)
      case a: AttrSetTacLong           => addSet(ns, attr, oneSet(ns, attr, a.vs, transformLong), transformLong, set2arrayLong, refNs, extsLong, value2jsonLong)
      case a: AttrSetTacFloat          => addSet(ns, attr, oneSet(ns, attr, a.vs, transformFloat), transformFloat, set2arrayFloat, refNs, extsFloat, value2jsonFloat)
      case a: AttrSetTacDouble         => addSet(ns, attr, oneSet(ns, attr, a.vs, transformDouble), transformDouble, set2arrayDouble, refNs, extsDouble, value2jsonDouble)
      case a: AttrSetTacBoolean        => addSet(ns, attr, oneSet(ns, attr, a.vs, transformBoolean), transformBoolean, set2arrayBoolean, refNs, extsBoolean, value2jsonBoolean)
      case a: AttrSetTacBigInt         => addSet(ns, attr, oneSet(ns, attr, a.vs, transformBigInt), transformBigInt, set2arrayBigInt, refNs, extsBigInt, value2jsonBigInt)
      case a: AttrSetTacBigDecimal     => addSet(ns, attr, oneSet(ns, attr, a.vs, transformBigDecimal), transformBigDecimal, set2arrayBigDecimal, refNs, extsBigDecimal, value2jsonBigDecimal)
      case a: AttrSetTacDate           => addSet(ns, attr, oneSet(ns, attr, a.vs, transformDate), transformDate, set2arrayDate, refNs, extsDate, value2jsonDate)
      case a: AttrSetTacDuration       => addSet(ns, attr, oneSet(ns, attr, a.vs, transformDuration), transformDuration, set2arrayDuration, refNs, extsDuration, value2jsonDuration)
      case a: AttrSetTacInstant        => addSet(ns, attr, oneSet(ns, attr, a.vs, transformInstant), transformInstant, set2arrayInstant, refNs, extsInstant, value2jsonInstant)
      case a: AttrSetTacLocalDate      => addSet(ns, attr, oneSet(ns, attr, a.vs, transformLocalDate), transformLocalDate, set2arrayLocalDate, refNs, extsLocalDate, value2jsonLocalDate)
      case a: AttrSetTacLocalTime      => addSet(ns, attr, oneSet(ns, attr, a.vs, transformLocalTime), transformLocalTime, set2arrayLocalTime, refNs, extsLocalTime, value2jsonLocalTime)
      case a: AttrSetTacLocalDateTime  => addSet(ns, attr, oneSet(ns, attr, a.vs, transformLocalDateTime), transformLocalDateTime, set2arrayLocalDateTime, refNs, extsLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSetTacOffsetTime     => addSet(ns, attr, oneSet(ns, attr, a.vs, transformOffsetTime), transformOffsetTime, set2arrayOffsetTime, refNs, extsOffsetTime, value2jsonOffsetTime)
      case a: AttrSetTacOffsetDateTime => addSet(ns, attr, oneSet(ns, attr, a.vs, transformOffsetDateTime), transformOffsetDateTime, set2arrayOffsetDateTime, refNs, extsOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSetTacZonedDateTime  => addSet(ns, attr, oneSet(ns, attr, a.vs, transformZonedDateTime), transformZonedDateTime, set2arrayZonedDateTime, refNs, extsZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSetTacUUID           => addSet(ns, attr, oneSet(ns, attr, a.vs, transformUUID), transformUUID, set2arrayUUID, refNs, extsUUID, value2jsonUUID)
      case a: AttrSetTacURI            => addSet(ns, attr, oneSet(ns, attr, a.vs, transformURI), transformURI, set2arrayURI, refNs, extsURI, value2jsonURI)
      case a: AttrSetTacByte           => addSet(ns, attr, oneSet(ns, attr, a.vs, transformByte), transformByte, set2arrayByte, refNs, extsByte, value2jsonByte)
      case a: AttrSetTacShort          => addSet(ns, attr, oneSet(ns, attr, a.vs, transformShort), transformShort, set2arrayShort, refNs, extsShort, value2jsonShort)
      case a: AttrSetTacChar           => addSet(ns, attr, oneSet(ns, attr, a.vs, transformChar), transformChar, set2arrayChar, refNs, extsChar, value2jsonChar)
    }
  }

  private def oneOptSet[T](
    ns: String,
    attr: String,
    optSets: Option[Seq[Set[T]]],
    transformValue: T => Any
  ): Option[Set[Any]] = {
    optSets.flatMap {
      case Seq(set) => Some(set.map(transformValue))
      case Nil      => None
      case vs       => throw ExecutionError(
        s"Can only save one Set of values for optional Set attribute `$ns.$attr`. Found: " + vs.mkString(", ")
      )
    }
  }

  private def resolveAttrSetOpt(a: AttrSetOpt): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrSetOptID             => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformID), handleID, set2arrayID, refNs, extsID, value2jsonID)
      case a: AttrSetOptString         => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformString), handleString, set2arrayString, refNs, extsString, value2jsonString)
      case a: AttrSetOptInt            => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformInt), handleInt, set2arrayInt, refNs, extsInt, value2jsonInt)
      case a: AttrSetOptLong           => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformLong), handleLong, set2arrayLong, refNs, extsLong, value2jsonLong)
      case a: AttrSetOptFloat          => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformFloat), handleFloat, set2arrayFloat, refNs, extsFloat, value2jsonFloat)
      case a: AttrSetOptDouble         => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformDouble), handleDouble, set2arrayDouble, refNs, extsDouble, value2jsonDouble)
      case a: AttrSetOptBoolean        => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformBoolean), handleBoolean, set2arrayBoolean, refNs, extsBoolean, value2jsonBoolean)
      case a: AttrSetOptBigInt         => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformBigInt), handleBigInt, set2arrayBigInt, refNs, extsBigInt, value2jsonBigInt)
      case a: AttrSetOptBigDecimal     => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformBigDecimal), handleBigDecimal, set2arrayBigDecimal, refNs, extsBigDecimal, value2jsonBigDecimal)
      case a: AttrSetOptDate           => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformDate), handleDate, set2arrayDate, refNs, extsDate, value2jsonDate)
      case a: AttrSetOptDuration       => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformDuration), handleDuration, set2arrayDuration, refNs, extsDuration, value2jsonDuration)
      case a: AttrSetOptInstant        => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformInstant), handleInstant, set2arrayInstant, refNs, extsInstant, value2jsonInstant)
      case a: AttrSetOptLocalDate      => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformLocalDate), handleLocalDate, set2arrayLocalDate, refNs, extsLocalDate, value2jsonLocalDate)
      case a: AttrSetOptLocalTime      => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformLocalTime), handleLocalTime, set2arrayLocalTime, refNs, extsLocalTime, value2jsonLocalTime)
      case a: AttrSetOptLocalDateTime  => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformLocalDateTime), handleLocalDateTime, set2arrayLocalDateTime, refNs, extsLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSetOptOffsetTime     => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformOffsetTime), handleOffsetTime, set2arrayOffsetTime, refNs, extsOffsetTime, value2jsonOffsetTime)
      case a: AttrSetOptOffsetDateTime => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformOffsetDateTime), handleOffsetDateTime, set2arrayOffsetDateTime, refNs, extsOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSetOptZonedDateTime  => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformZonedDateTime), handleZonedDateTime, set2arrayZonedDateTime, refNs, extsZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSetOptUUID           => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformUUID), handleUUID, set2arrayUUID, refNs, extsUUID, value2jsonUUID)
      case a: AttrSetOptURI            => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformURI), handleURI, set2arrayURI, refNs, extsURI, value2jsonURI)
      case a: AttrSetOptByte           => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformByte), handleByte, set2arrayByte, refNs, extsByte, value2jsonByte)
      case a: AttrSetOptShort          => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformShort), handleShort, set2arrayShort, refNs, extsShort, value2jsonShort)
      case a: AttrSetOptChar           => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformChar), handleChar, set2arrayChar, refNs, extsChar, value2jsonChar)
    }
  }


  private def noMultipleArrays[T](ns: String, attr: String, multipleArrays: Seq[Array[T]]) = {
    throw ExecutionError(
      s"Can only save one Array of values for Array attribute `$ns.$attr`. Found multiple arrays:\n" +
        multipleArrays.map(a => s"Array(${a.mkString(", )})")})").mkString("\n")
    )
  }
  private def oneArray[T](
    ns: String,
    attr: String,
    arrays: Seq[Array[T]],
    transformValue: T => Any
  ): Option[Array[Any]] = {
    arrays match {
      case Seq(array)     => Some(array.map(transformValue))
      case Nil            => None
      case multipleArrays => noMultipleArrays(ns, attr, multipleArrays)
    }
  }
  private def oneByteArray(
    ns: String,
    attr: String,
    arrays: Seq[Array[Byte]],
  ): Option[Array[Byte]] = {
    arrays match {
      case Seq(array)     => Some(array)
      case Nil            => None
      case multipleArrays => noMultipleArrays(ns, attr, multipleArrays)
    }
  }
  private def resolveAttrArrayMan(a: AttrArrMan): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrArrManID             => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformID), transformID) //, set2arrayID, extsID, value2jsonID)
      case a: AttrArrManString         => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformString), transformString) //, set2arrayString, extsString, value2jsonString)
      case a: AttrArrManInt            => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformInt), transformInt) //, set2arrayInt, extsInt, value2jsonInt)
      case a: AttrArrManLong           => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformLong), transformLong) //, set2arrayLong, extsLong, value2jsonLong)
      case a: AttrArrManFloat          => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformFloat), transformFloat) //, set2arrayFloat, extsFloat, value2jsonFloat)
      case a: AttrArrManDouble         => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformDouble), transformDouble) //, set2arrayDouble, extsDouble, value2jsonDouble)
      case a: AttrArrManBoolean        => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformBoolean), transformBoolean) //, set2arrayBoolean, extsBoolean, value2jsonBoolean)
      case a: AttrArrManBigInt         => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformBigInt), transformBigInt) //, set2arrayBigInt, extsBigInt, value2jsonBigInt)
      case a: AttrArrManBigDecimal     => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformBigDecimal), transformBigDecimal) //, set2arrayBigDecimal, extsBigDecimal, value2jsonBigDecimal)
      case a: AttrArrManDate           => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformDate), transformDate) //, set2arrayDate, extsDate, value2jsonDate)
      case a: AttrArrManDuration       => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformDuration), transformDuration) //, set2arrayDuration, extsDuration, value2jsonDuration)
      case a: AttrArrManInstant        => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformInstant), transformInstant) //, set2arrayInstant, extsInstant, value2jsonInstant)
      case a: AttrArrManLocalDate      => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformLocalDate), transformLocalDate) //, set2arrayLocalDate, extsLocalDate, value2jsonLocalDate)
      case a: AttrArrManLocalTime      => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformLocalTime), transformLocalTime) //, set2arrayLocalTime, extsLocalTime, value2jsonLocalTime)
      case a: AttrArrManLocalDateTime  => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformLocalDateTime), transformLocalDateTime) //, set2arrayLocalDateTime, extsLocalDateTime, value2jsonLocalDateTime)
      case a: AttrArrManOffsetTime     => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformOffsetTime), transformOffsetTime) //, set2arrayOffsetTime, extsOffsetTime, value2jsonOffsetTime)
      case a: AttrArrManOffsetDateTime => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformOffsetDateTime), transformOffsetDateTime) //, set2arrayOffsetDateTime, extsOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrArrManZonedDateTime  => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformZonedDateTime), transformZonedDateTime) //, set2arrayZonedDateTime, extsZonedDateTime, value2jsonZonedDateTime)
      case a: AttrArrManUUID           => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformUUID), transformUUID) //, set2arrayUUID, extsUUID, value2jsonUUID)
      case a: AttrArrManURI            => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformURI), transformURI) //, set2arrayURI, extsURI, value2jsonURI)
      case a: AttrArrManByte           => addByteArray(ns, attr, oneByteArray(ns, attr, a.vs))
      case a: AttrArrManShort          => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformShort), transformShort) //, set2arrayShort, extsShort, value2jsonShort)
      case a: AttrArrManChar           => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformChar), transformChar) //, set2arrayChar, extsChar, value2jsonChar)
    }
  }
  private def resolveAttrArrayTac(a: AttrArrTac): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrArrTacID             => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformID), transformID) //, set2arrayID, extsID, value2jsonID)
      case a: AttrArrTacString         => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformString), transformString) //, set2arrayString, extsString, value2jsonString)
      case a: AttrArrTacInt            => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformInt), transformInt) //, set2arrayInt, extsInt, value2jsonInt)
      case a: AttrArrTacLong           => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformLong), transformLong) //, set2arrayLong, extsLong, value2jsonLong)
      case a: AttrArrTacFloat          => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformFloat), transformFloat) //, set2arrayFloat, extsFloat, value2jsonFloat)
      case a: AttrArrTacDouble         => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformDouble), transformDouble) //, set2arrayDouble, extsDouble, value2jsonDouble)
      case a: AttrArrTacBoolean        => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformBoolean), transformBoolean) //, set2arrayBoolean, extsBoolean, value2jsonBoolean)
      case a: AttrArrTacBigInt         => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformBigInt), transformBigInt) //, set2arrayBigInt, extsBigInt, value2jsonBigInt)
      case a: AttrArrTacBigDecimal     => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformBigDecimal), transformBigDecimal) //, set2arrayBigDecimal, extsBigDecimal, value2jsonBigDecimal)
      case a: AttrArrTacDate           => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformDate), transformDate) //, set2arrayDate, extsDate, value2jsonDate)
      case a: AttrArrTacDuration       => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformDuration), transformDuration) //, set2arrayDuration, extsDuration, value2jsonDuration)
      case a: AttrArrTacInstant        => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformInstant), transformInstant) //, set2arrayInstant, extsInstant, value2jsonInstant)
      case a: AttrArrTacLocalDate      => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformLocalDate), transformLocalDate) //, set2arrayLocalDate, extsLocalDate, value2jsonLocalDate)
      case a: AttrArrTacLocalTime      => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformLocalTime), transformLocalTime) //, set2arrayLocalTime, extsLocalTime, value2jsonLocalTime)
      case a: AttrArrTacLocalDateTime  => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformLocalDateTime), transformLocalDateTime) //, set2arrayLocalDateTime, extsLocalDateTime, value2jsonLocalDateTime)
      case a: AttrArrTacOffsetTime     => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformOffsetTime), transformOffsetTime) //, set2arrayOffsetTime, extsOffsetTime, value2jsonOffsetTime)
      case a: AttrArrTacOffsetDateTime => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformOffsetDateTime), transformOffsetDateTime) //, set2arrayOffsetDateTime, extsOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrArrTacZonedDateTime  => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformZonedDateTime), transformZonedDateTime) //, set2arrayZonedDateTime, extsZonedDateTime, value2jsonZonedDateTime)
      case a: AttrArrTacUUID           => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformUUID), transformUUID) //, set2arrayUUID, extsUUID, value2jsonUUID)
      case a: AttrArrTacURI            => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformURI), transformURI) //, set2arrayURI, extsURI, value2jsonURI)
      case a: AttrArrTacByte           => addByteArray(ns, attr, oneByteArray(ns, attr, a.vs))
      case a: AttrArrTacShort          => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformShort), transformShort) //, set2arrayShort, extsShort, value2jsonShort)
      case a: AttrArrTacChar           => addArray(ns, attr, refNs, oneArray(ns, attr, a.vs, transformChar), transformChar) //, set2arrayChar, extsChar, value2jsonChar)
    }
  }

  private def oneOptArray[T](
    ns: String,
    attr: String,
    optArrays: Option[Seq[Array[T]]],
    transformValue: T => Any
  ): Option[Array[Any]] = {
    optArrays.flatMap {
      case Seq(array)     => Some(array.map(transformValue))
      case Nil            => None
      case multipleArrays => noMultipleArrays(ns, attr, multipleArrays)
    }
  }
  private def oneOptByteArray(
    ns: String,
    attr: String,
    optArrays: Option[Seq[Array[Byte]]]
  ): Option[Array[Byte]] = {
    optArrays.flatMap {
      case Seq(array)     => Some(array)
      case Nil            => None
      case multipleArrays => noMultipleArrays(ns, attr, multipleArrays)
    }
  }

  private def resolveAttrArrayOpt(a: AttrArrOpt): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrArrOptID             => addArray(ns, attr, refNs, oneOptArray(ns, attr, a.vs, transformID), handleID) //, set2arrayID, extsID, value2jsonID)
      case a: AttrArrOptString         => addArray(ns, attr, refNs, oneOptArray(ns, attr, a.vs, transformString), handleString) //, set2arrayString, extsString, value2jsonString)
      case a: AttrArrOptInt            => addArray(ns, attr, refNs, oneOptArray(ns, attr, a.vs, transformInt), handleInt) //, set2arrayInt, extsInt, value2jsonInt)
      case a: AttrArrOptLong           => addArray(ns, attr, refNs, oneOptArray(ns, attr, a.vs, transformLong), handleLong) //, set2arrayLong, extsLong, value2jsonLong)
      case a: AttrArrOptFloat          => addArray(ns, attr, refNs, oneOptArray(ns, attr, a.vs, transformFloat), handleFloat) //, set2arrayFloat, extsFloat, value2jsonFloat)
      case a: AttrArrOptDouble         => addArray(ns, attr, refNs, oneOptArray(ns, attr, a.vs, transformDouble), handleDouble) //, set2arrayDouble, extsDouble, value2jsonDouble)
      case a: AttrArrOptBoolean        => addArray(ns, attr, refNs, oneOptArray(ns, attr, a.vs, transformBoolean), handleBoolean) //, set2arrayBoolean, extsBoolean, value2jsonBoolean)
      case a: AttrArrOptBigInt         => addArray(ns, attr, refNs, oneOptArray(ns, attr, a.vs, transformBigInt), handleBigInt) //, set2arrayBigInt, extsBigInt, value2jsonBigInt)
      case a: AttrArrOptBigDecimal     => addArray(ns, attr, refNs, oneOptArray(ns, attr, a.vs, transformBigDecimal), handleBigDecimal) //, set2arrayBigDecimal, extsBigDecimal, value2jsonBigDecimal)
      case a: AttrArrOptDate           => addArray(ns, attr, refNs, oneOptArray(ns, attr, a.vs, transformDate), handleDate) //, set2arrayDate, extsDate, value2jsonDate)
      case a: AttrArrOptDuration       => addArray(ns, attr, refNs, oneOptArray(ns, attr, a.vs, transformDuration), handleDuration) //, set2arrayDuration, extsDuration, value2jsonDuration)
      case a: AttrArrOptInstant        => addArray(ns, attr, refNs, oneOptArray(ns, attr, a.vs, transformInstant), handleInstant) //, set2arrayInstant, extsInstant, value2jsonInstant)
      case a: AttrArrOptLocalDate      => addArray(ns, attr, refNs, oneOptArray(ns, attr, a.vs, transformLocalDate), handleLocalDate) //, set2arrayLocalDate, extsLocalDate, value2jsonLocalDate)
      case a: AttrArrOptLocalTime      => addArray(ns, attr, refNs, oneOptArray(ns, attr, a.vs, transformLocalTime), handleLocalTime) //, set2arrayLocalTime, extsLocalTime, value2jsonLocalTime)
      case a: AttrArrOptLocalDateTime  => addArray(ns, attr, refNs, oneOptArray(ns, attr, a.vs, transformLocalDateTime), handleLocalDateTime) //, set2arrayLocalDateTime, extsLocalDateTime, value2jsonLocalDateTime)
      case a: AttrArrOptOffsetTime     => addArray(ns, attr, refNs, oneOptArray(ns, attr, a.vs, transformOffsetTime), handleOffsetTime) //, set2arrayOffsetTime, extsOffsetTime, value2jsonOffsetTime)
      case a: AttrArrOptOffsetDateTime => addArray(ns, attr, refNs, oneOptArray(ns, attr, a.vs, transformOffsetDateTime), handleOffsetDateTime) //, set2arrayOffsetDateTime, extsOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrArrOptZonedDateTime  => addArray(ns, attr, refNs, oneOptArray(ns, attr, a.vs, transformZonedDateTime), handleZonedDateTime) //, set2arrayZonedDateTime, extsZonedDateTime, value2jsonZonedDateTime)
      case a: AttrArrOptUUID           => addArray(ns, attr, refNs, oneOptArray(ns, attr, a.vs, transformUUID), handleUUID) //, set2arrayUUID, extsUUID, value2jsonUUID)
      case a: AttrArrOptURI            => addArray(ns, attr, refNs, oneOptArray(ns, attr, a.vs, transformURI), handleURI) //, set2arrayURI, extsURI, value2jsonURI)
      case a: AttrArrOptByte           => addByteArray(ns, attr, oneOptByteArray(ns, attr, a.vs))
      case a: AttrArrOptShort          => addArray(ns, attr, refNs, oneOptArray(ns, attr, a.vs, transformShort), handleShort) //, set2arrayShort, extsShort, value2jsonShort)
      case a: AttrArrOptChar           => addArray(ns, attr, refNs, oneOptArray(ns, attr, a.vs, transformChar), handleChar) //, set2arrayChar, extsChar, value2jsonChar)
    }
  }


  //  private def oneMap[T](
  //    ns: String,
  //    attr: String,
  //    sets: Seq[Map[String, T]],
  //    transformValue: T => Any
  //  ): Option[Map[String, Any]] = {
  //    sets match {
  //      case Seq(set)     => Some(set.map(transformValue))
  //      case Nil          => None
  //      case multipleSets => throw ExecutionError(
  //        s"Can only save one Set of values for Set attribute `$ns.$attr`. Found: " + multipleSets.mkString(", ")
  //      )
  //    }
  //  }
  //  private def resolveAttrMapMan(a: AttrMapMan): Unit = {
  //    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
  //    a match {
  //      case a: AttrMapManID             => addMap(ns, attr, oneMap(ns, attr, a.vs, transformID), transformID, set2arrayID, refNs, extsID, value2jsonID)
  //      case a: AttrMapManString         => addMap(ns, attr, oneMap(ns, attr, a.vs, transformString), transformString, set2arrayString, refNs, extsString, value2jsonString)
  //      case a: AttrMapManInt            => addMap(ns, attr, oneMap(ns, attr, a.vs, transformInt), transformInt, set2arrayInt, refNs, extsInt, value2jsonInt)
  //      case a: AttrMapManLong           => addMap(ns, attr, oneMap(ns, attr, a.vs, transformLong), transformLong, set2arrayLong, refNs, extsLong, value2jsonLong)
  //      case a: AttrMapManFloat          => addMap(ns, attr, oneMap(ns, attr, a.vs, transformFloat), transformFloat, set2arrayFloat, refNs, extsFloat, value2jsonFloat)
  //      case a: AttrMapManDouble         => addMap(ns, attr, oneMap(ns, attr, a.vs, transformDouble), transformDouble, set2arrayDouble, refNs, extsDouble, value2jsonDouble)
  //      case a: AttrMapManBoolean        => addMap(ns, attr, oneMap(ns, attr, a.vs, transformBoolean), transformBoolean, set2arrayBoolean, refNs, extsBoolean, value2jsonBoolean)
  //      case a: AttrMapManBigInt         => addMap(ns, attr, oneMap(ns, attr, a.vs, transformBigInt), transformBigInt, set2arrayBigInt, refNs, extsBigInt, value2jsonBigInt)
  //      case a: AttrMapManBigDecimal     => addMap(ns, attr, oneMap(ns, attr, a.vs, transformBigDecimal), transformBigDecimal, set2arrayBigDecimal, refNs, extsBigDecimal, value2jsonBigDecimal)
  //      case a: AttrMapManDate           => addMap(ns, attr, oneMap(ns, attr, a.vs, transformDate), transformDate, set2arrayDate, refNs, extsDate, value2jsonDate)
  //      case a: AttrMapManDuration       => addMap(ns, attr, oneMap(ns, attr, a.vs, transformDuration), transformDuration, set2arrayDuration, refNs, extsDuration, value2jsonDuration)
  //      case a: AttrMapManInstant        => addMap(ns, attr, oneMap(ns, attr, a.vs, transformInstant), transformInstant, set2arrayInstant, refNs, extsInstant, value2jsonInstant)
  //      case a: AttrMapManLocalDate      => addMap(ns, attr, oneMap(ns, attr, a.vs, transformLocalDate), transformLocalDate, set2arrayLocalDate, refNs, extsLocalDate, value2jsonLocalDate)
  //      case a: AttrMapManLocalTime      => addMap(ns, attr, oneMap(ns, attr, a.vs, transformLocalTime), transformLocalTime, set2arrayLocalTime, refNs, extsLocalTime, value2jsonLocalTime)
  //      case a: AttrMapManLocalDateTime  => addMap(ns, attr, oneMap(ns, attr, a.vs, transformLocalDateTime), transformLocalDateTime, set2arrayLocalDateTime, refNs, extsLocalDateTime, value2jsonLocalDateTime)
  //      case a: AttrMapManOffsetTime     => addMap(ns, attr, oneMap(ns, attr, a.vs, transformOffsetTime), transformOffsetTime, set2arrayOffsetTime, refNs, extsOffsetTime, value2jsonOffsetTime)
  //      case a: AttrMapManOffsetDateTime => addMap(ns, attr, oneMap(ns, attr, a.vs, transformOffsetDateTime), transformOffsetDateTime, set2arrayOffsetDateTime, refNs, extsOffsetDateTime, value2jsonOffsetDateTime)
  //      case a: AttrMapManZonedDateTime  => addMap(ns, attr, oneMap(ns, attr, a.vs, transformZonedDateTime), transformZonedDateTime, set2arrayZonedDateTime, refNs, extsZonedDateTime, value2jsonZonedDateTime)
  //      case a: AttrMapManUUID           => addMap(ns, attr, oneMap(ns, attr, a.vs, transformUUID), transformUUID, set2arrayUUID, refNs, extsUUID, value2jsonUUID)
  //      case a: AttrMapManURI            => addMap(ns, attr, oneMap(ns, attr, a.vs, transformURI), transformURI, set2arrayURI, refNs, extsURI, value2jsonURI)
  //      case a: AttrMapManByte           => addMap(ns, attr, oneMap(ns, attr, a.vs, transformByte), transformByte, set2arrayByte, refNs, extsByte, value2jsonByte)
  //      case a: AttrMapManShort          => addMap(ns, attr, oneMap(ns, attr, a.vs, transformShort), transformShort, set2arrayShort, refNs, extsShort, value2jsonShort)
  //      case a: AttrMapManChar           => addMap(ns, attr, oneMap(ns, attr, a.vs, transformChar), transformChar, set2arrayChar, refNs, extsChar, value2jsonChar)
  //    }
  //  }
  //  private def resolveAttrMapTac(a: AttrMapTac): Unit = {
  //    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
  //    a match {
  //      case a: AttrMapTacID             => addMap(ns, attr, oneMap(ns, attr, a.vs, transformID), transformID, set2arrayID, refNs, extsID, value2jsonID)
  //      case a: AttrMapTacString         => addMap(ns, attr, oneMap(ns, attr, a.vs, transformString), transformString, set2arrayString, refNs, extsString, value2jsonString)
  //      case a: AttrMapTacInt            => addMap(ns, attr, oneMap(ns, attr, a.vs, transformInt), transformInt, set2arrayInt, refNs, extsInt, value2jsonInt)
  //      case a: AttrMapTacLong           => addMap(ns, attr, oneMap(ns, attr, a.vs, transformLong), transformLong, set2arrayLong, refNs, extsLong, value2jsonLong)
  //      case a: AttrMapTacFloat          => addMap(ns, attr, oneMap(ns, attr, a.vs, transformFloat), transformFloat, set2arrayFloat, refNs, extsFloat, value2jsonFloat)
  //      case a: AttrMapTacDouble         => addMap(ns, attr, oneMap(ns, attr, a.vs, transformDouble), transformDouble, set2arrayDouble, refNs, extsDouble, value2jsonDouble)
  //      case a: AttrMapTacBoolean        => addMap(ns, attr, oneMap(ns, attr, a.vs, transformBoolean), transformBoolean, set2arrayBoolean, refNs, extsBoolean, value2jsonBoolean)
  //      case a: AttrMapTacBigInt         => addMap(ns, attr, oneMap(ns, attr, a.vs, transformBigInt), transformBigInt, set2arrayBigInt, refNs, extsBigInt, value2jsonBigInt)
  //      case a: AttrMapTacBigDecimal     => addMap(ns, attr, oneMap(ns, attr, a.vs, transformBigDecimal), transformBigDecimal, set2arrayBigDecimal, refNs, extsBigDecimal, value2jsonBigDecimal)
  //      case a: AttrMapTacDate           => addMap(ns, attr, oneMap(ns, attr, a.vs, transformDate), transformDate, set2arrayDate, refNs, extsDate, value2jsonDate)
  //      case a: AttrMapTacDuration       => addMap(ns, attr, oneMap(ns, attr, a.vs, transformDuration), transformDuration, set2arrayDuration, refNs, extsDuration, value2jsonDuration)
  //      case a: AttrMapTacInstant        => addMap(ns, attr, oneMap(ns, attr, a.vs, transformInstant), transformInstant, set2arrayInstant, refNs, extsInstant, value2jsonInstant)
  //      case a: AttrMapTacLocalDate      => addMap(ns, attr, oneMap(ns, attr, a.vs, transformLocalDate), transformLocalDate, set2arrayLocalDate, refNs, extsLocalDate, value2jsonLocalDate)
  //      case a: AttrMapTacLocalTime      => addMap(ns, attr, oneMap(ns, attr, a.vs, transformLocalTime), transformLocalTime, set2arrayLocalTime, refNs, extsLocalTime, value2jsonLocalTime)
  //      case a: AttrMapTacLocalDateTime  => addMap(ns, attr, oneMap(ns, attr, a.vs, transformLocalDateTime), transformLocalDateTime, set2arrayLocalDateTime, refNs, extsLocalDateTime, value2jsonLocalDateTime)
  //      case a: AttrMapTacOffsetTime     => addMap(ns, attr, oneMap(ns, attr, a.vs, transformOffsetTime), transformOffsetTime, set2arrayOffsetTime, refNs, extsOffsetTime, value2jsonOffsetTime)
  //      case a: AttrMapTacOffsetDateTime => addMap(ns, attr, oneMap(ns, attr, a.vs, transformOffsetDateTime), transformOffsetDateTime, set2arrayOffsetDateTime, refNs, extsOffsetDateTime, value2jsonOffsetDateTime)
  //      case a: AttrMapTacZonedDateTime  => addMap(ns, attr, oneMap(ns, attr, a.vs, transformZonedDateTime), transformZonedDateTime, set2arrayZonedDateTime, refNs, extsZonedDateTime, value2jsonZonedDateTime)
  //      case a: AttrMapTacUUID           => addMap(ns, attr, oneMap(ns, attr, a.vs, transformUUID), transformUUID, set2arrayUUID, refNs, extsUUID, value2jsonUUID)
  //      case a: AttrMapTacURI            => addMap(ns, attr, oneMap(ns, attr, a.vs, transformURI), transformURI, set2arrayURI, refNs, extsURI, value2jsonURI)
  //      case a: AttrMapTacByte           => addMap(ns, attr, oneMap(ns, attr, a.vs, transformByte), transformByte, set2arrayByte, refNs, extsByte, value2jsonByte)
  //      case a: AttrMapTacShort          => addMap(ns, attr, oneMap(ns, attr, a.vs, transformShort), transformShort, set2arrayShort, refNs, extsShort, value2jsonShort)
  //      case a: AttrMapTacChar           => addMap(ns, attr, oneMap(ns, attr, a.vs, transformChar), transformChar, set2arrayChar, refNs, extsChar, value2jsonChar)
  //    }
  //  }
  //
  //  private def oneOptMap[T](
  //    ns: String,
  //    attr: String,
  //    optSets: Option[Seq[Map[String, T]]],
  //    transformValue: T => Any
  //  ): Option[Map[String, Any]] = {
  //    optSets.flatMap {
  //      case Seq(set) => Some(set.map(transformValue))
  //      case Nil      => None
  //      case vs       => throw ExecutionError(
  //        s"Can only save one Set of values for optional Set attribute `$ns.$attr`. Found: " + vs.mkString(", ")
  //      )
  //    }
  //  }
  //
  //  private def resolveAttrMapOpt(a: AttrMapOpt): Unit = {
  //    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
  //    a match {
  //      case a: AttrMapOptID             => addMap(ns, attr, oneOptMap(ns, attr, a.vs, transformID), handleID, set2arrayID, refNs, extsID, value2jsonID)
  //      case a: AttrMapOptString         => addMap(ns, attr, oneOptMap(ns, attr, a.vs, transformString), handleString, set2arrayString, refNs, extsString, value2jsonString)
  //      case a: AttrMapOptInt            => addMap(ns, attr, oneOptMap(ns, attr, a.vs, transformInt), handleInt, set2arrayInt, refNs, extsInt, value2jsonInt)
  //      case a: AttrMapOptLong           => addMap(ns, attr, oneOptMap(ns, attr, a.vs, transformLong), handleLong, set2arrayLong, refNs, extsLong, value2jsonLong)
  //      case a: AttrMapOptFloat          => addMap(ns, attr, oneOptMap(ns, attr, a.vs, transformFloat), handleFloat, set2arrayFloat, refNs, extsFloat, value2jsonFloat)
  //      case a: AttrMapOptDouble         => addMap(ns, attr, oneOptMap(ns, attr, a.vs, transformDouble), handleDouble, set2arrayDouble, refNs, extsDouble, value2jsonDouble)
  //      case a: AttrMapOptBoolean        => addMap(ns, attr, oneOptMap(ns, attr, a.vs, transformBoolean), handleBoolean, set2arrayBoolean, refNs, extsBoolean, value2jsonBoolean)
  //      case a: AttrMapOptBigInt         => addMap(ns, attr, oneOptMap(ns, attr, a.vs, transformBigInt), handleBigInt, set2arrayBigInt, refNs, extsBigInt, value2jsonBigInt)
  //      case a: AttrMapOptBigDecimal     => addMap(ns, attr, oneOptMap(ns, attr, a.vs, transformBigDecimal), handleBigDecimal, set2arrayBigDecimal, refNs, extsBigDecimal, value2jsonBigDecimal)
  //      case a: AttrMapOptDate           => addMap(ns, attr, oneOptMap(ns, attr, a.vs, transformDate), handleDate, set2arrayDate, refNs, extsDate, value2jsonDate)
  //      case a: AttrMapOptDuration       => addMap(ns, attr, oneOptMap(ns, attr, a.vs, transformDuration), handleDuration, set2arrayDuration, refNs, extsDuration, value2jsonDuration)
  //      case a: AttrMapOptInstant        => addMap(ns, attr, oneOptMap(ns, attr, a.vs, transformInstant), handleInstant, set2arrayInstant, refNs, extsInstant, value2jsonInstant)
  //      case a: AttrMapOptLocalDate      => addMap(ns, attr, oneOptMap(ns, attr, a.vs, transformLocalDate), handleLocalDate, set2arrayLocalDate, refNs, extsLocalDate, value2jsonLocalDate)
  //      case a: AttrMapOptLocalTime      => addMap(ns, attr, oneOptMap(ns, attr, a.vs, transformLocalTime), handleLocalTime, set2arrayLocalTime, refNs, extsLocalTime, value2jsonLocalTime)
  //      case a: AttrMapOptLocalDateTime  => addMap(ns, attr, oneOptMap(ns, attr, a.vs, transformLocalDateTime), handleLocalDateTime, set2arrayLocalDateTime, refNs, extsLocalDateTime, value2jsonLocalDateTime)
  //      case a: AttrMapOptOffsetTime     => addMap(ns, attr, oneOptMap(ns, attr, a.vs, transformOffsetTime), handleOffsetTime, set2arrayOffsetTime, refNs, extsOffsetTime, value2jsonOffsetTime)
  //      case a: AttrMapOptOffsetDateTime => addMap(ns, attr, oneOptMap(ns, attr, a.vs, transformOffsetDateTime), handleOffsetDateTime, set2arrayOffsetDateTime, refNs, extsOffsetDateTime, value2jsonOffsetDateTime)
  //      case a: AttrMapOptZonedDateTime  => addMap(ns, attr, oneOptMap(ns, attr, a.vs, transformZonedDateTime), handleZonedDateTime, set2arrayZonedDateTime, refNs, extsZonedDateTime, value2jsonZonedDateTime)
  //      case a: AttrMapOptUUID           => addMap(ns, attr, oneOptMap(ns, attr, a.vs, transformUUID), handleUUID, set2arrayUUID, refNs, extsUUID, value2jsonUUID)
  //      case a: AttrMapOptURI            => addMap(ns, attr, oneOptMap(ns, attr, a.vs, transformURI), handleURI, set2arrayURI, refNs, extsURI, value2jsonURI)
  //      case a: AttrMapOptByte           => addMap(ns, attr, oneOptMap(ns, attr, a.vs, transformByte), handleByte, set2arrayByte, refNs, extsByte, value2jsonByte)
  //      case a: AttrMapOptShort          => addMap(ns, attr, oneOptMap(ns, attr, a.vs, transformShort), handleShort, set2arrayShort, refNs, extsShort, value2jsonShort)
  //      case a: AttrMapOptChar           => addMap(ns, attr, oneOptMap(ns, attr, a.vs, transformChar), handleChar, set2arrayChar, refNs, extsChar, value2jsonChar)
  //    }
  //  }
}