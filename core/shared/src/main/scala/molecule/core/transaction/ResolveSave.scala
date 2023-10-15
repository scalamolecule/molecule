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
            case a: AttrOne =>
              a match {
                case a: AttrOneMan => resolveAttrOneMan(a); resolve(tail)
                case a: AttrOneOpt => resolveAttrOneOpt(a); resolve(tail)
                case a: AttrOneTac => resolveAttrOneTac(a); resolve(tail)
              }
            case a: AttrSet =>
              a match {
                case a: AttrSetMan => resolveAttrSetMan(a); resolve(tail)
                case a: AttrSetOpt => resolveAttrSetOpt(a); resolve(tail)
                case a: AttrSetTac => resolveAttrSetTac(a); resolve(tail)
              }
          }

        case Ref(ns, refAttr, refNs, card, _) => addRef(ns, refAttr, refNs, card); resolve(tail)
        case BackRef(backRefNs, _, _)         => addBackRef(backRefNs); resolve(tail)
        case _: Nested                        => throw ModelError(
          "Nested data structure not allowed in save molecule. Please use insert instead."
        )
        case _: NestedOpt                     => throw ModelError(
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
}