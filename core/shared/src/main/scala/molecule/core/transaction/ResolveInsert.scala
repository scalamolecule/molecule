package molecule.core.transaction

import molecule.base.ast._
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.marshalling.ConnProxy
import molecule.core.transaction.ops.InsertOps
import molecule.core.util.ModelUtils
import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

class ResolveInsert
  extends InsertResolvers_ with InsertValidators_ with ModelUtils { self: InsertOps =>

  private val prevRefs: ListBuffer[AnyRef] = ListBuffer.empty[AnyRef]
  private def noEmpty(a: Attr) = throw new Exception("Can't use tacit attributes in insert molecule. Found: " + a)


  @tailrec
  final override def resolve(
    nsMap: Map[String, MetaNs],
    elements: List[Element],
    resolvers: List[Product => Unit],
    tplIndex: Int
  ): List[Product => Unit] = {
    elements match {
      case element :: tail => element match {
        case a: Attr =>
          if (a.op != V) {
            throw ModelError("Can't insert attributes with an applied value. Found:\n" + a)
          }
          a match {
            case a: AttrOne => a match {
              case a: AttrOneMan =>
                val attrOneManResolver = resolveAttrOneMan(a, tplIndex)
                resolve(nsMap, tail, resolvers :+ attrOneManResolver, tplIndex + 1)

              case a: AttrOneOpt =>
                val attrOneOptResolver = resolveAttrOneOpt(a, tplIndex)
                resolve(nsMap, tail, resolvers :+ attrOneOptResolver, tplIndex + 1)

              case a => noEmpty(a)
            }
            case a: AttrSet => a match {
              case a: AttrSetMan =>
                val attrsSetManResolver = resolveAttrSetMan(a, tplIndex)
                resolve(nsMap, tail, resolvers :+ attrsSetManResolver, tplIndex + 1)

              case a: AttrSetOpt =>
                val attrSetOptResolver = resolveAttrSetOpt(a, tplIndex)
                resolve(nsMap, tail, resolvers :+ attrSetOptResolver, tplIndex + 1)

              case a => noEmpty(a)
            }

            case a: AttrSeq => a match {
              case a: AttrSeqMan =>
                val attrsSetManResolver = resolveAttrSeqMan(a, tplIndex)
                resolve(nsMap, tail, resolvers :+ attrsSetManResolver, tplIndex + 1)

              case a: AttrSeqOpt =>
                val attrSetOptResolver = resolveAttrSeqOpt(a, tplIndex)
                resolve(nsMap, tail, resolvers :+ attrSetOptResolver, tplIndex + 1)

              case a => noEmpty(a)
            }

            case a: AttrMap => a match {
              case a: AttrMapMan =>
                val attrsSetManResolver = resolveAttrMapMan(a, tplIndex)
                resolve(nsMap, tail, resolvers :+ attrsSetManResolver, tplIndex + 1)

              case a: AttrMapOpt =>
                val attrSetOptResolver = resolveAttrMapOpt(a, tplIndex)
                resolve(nsMap, tail, resolvers :+ attrSetOptResolver, tplIndex + 1)

              case a => noEmpty(a)
            }
          }

        case Ref(ns, refAttr, refNs, card, owner, _) =>
          prevRefs += refAttr
          val refResolver = addRef(ns, refAttr, refNs, card, owner)
          resolve(nsMap, tail, resolvers :+ refResolver, tplIndex)

        case BackRef(backRefNs, _, _) =>
          tail.head match {
            case Ref(_, refAttr, _, _, _, _) if prevRefs.contains(refAttr) => throw ModelError(
              s"Can't re-use previous namespace ${refAttr.capitalize} after backref _$backRefNs."
            )
            case _                                                         => // ok
          }
          val backRefResolver = addBackRef(backRefNs)
          resolve(nsMap, tail, resolvers :+ backRefResolver, tplIndex)

        case Nested(Ref(ns, refAttr, refNs, _, owner, _), nestedElements) =>
          prevRefs.clear()
          val nestedResolver = addNested(nsMap, tplIndex, ns, refAttr, refNs, owner, nestedElements)
          resolve(nsMap, tail, resolvers :+ nestedResolver, tplIndex)

        case NestedOpt(Ref(ns, refAttr, refNs, _, owner, _), nestedElements) =>
          prevRefs.clear()
          val optNestedResolver = addNested(nsMap, tplIndex, ns, refAttr, refNs, owner, nestedElements)
          resolve(nsMap, tail, resolvers :+ optNestedResolver, tplIndex)
      }
      case Nil             => resolvers
    }
  }


  private def resolveAttrOneMan(a: AttrOneMan, tplIndex: Int): Product => Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case _: AttrOneManID             => addOne(ns, attr, tplIndex, transformID, handleID, extsID)
      case _: AttrOneManString         => addOne(ns, attr, tplIndex, transformString, handleString, extsString)
      case _: AttrOneManInt            => addOne(ns, attr, tplIndex, transformInt, handleInt, extsInt)
      case _: AttrOneManLong           => addOne(ns, attr, tplIndex, transformLong, handleLong, extsLong)
      case _: AttrOneManFloat          => addOne(ns, attr, tplIndex, transformFloat, handleFloat, extsFloat)
      case _: AttrOneManDouble         => addOne(ns, attr, tplIndex, transformDouble, handleDouble, extsDouble)
      case _: AttrOneManBoolean        => addOne(ns, attr, tplIndex, transformBoolean, handleBoolean, extsBoolean)
      case _: AttrOneManBigInt         => addOne(ns, attr, tplIndex, transformBigInt, handleBigInt, extsBigInt)
      case _: AttrOneManBigDecimal     => addOne(ns, attr, tplIndex, transformBigDecimal, handleBigDecimal, extsBigDecimal)
      case _: AttrOneManDate           => addOne(ns, attr, tplIndex, transformDate, handleDate, extsDate)
      case _: AttrOneManDuration       => addOne(ns, attr, tplIndex, transformDuration, handleDuration, extsDuration)
      case _: AttrOneManInstant        => addOne(ns, attr, tplIndex, transformInstant, handleInstant, extsInstant)
      case _: AttrOneManLocalDate      => addOne(ns, attr, tplIndex, transformLocalDate, handleLocalDate, extsLocalDate)
      case _: AttrOneManLocalTime      => addOne(ns, attr, tplIndex, transformLocalTime, handleLocalTime, extsLocalTime)
      case _: AttrOneManLocalDateTime  => addOne(ns, attr, tplIndex, transformLocalDateTime, handleLocalDateTime, extsLocalDateTime)
      case _: AttrOneManOffsetTime     => addOne(ns, attr, tplIndex, transformOffsetTime, handleOffsetTime, extsOffsetTime)
      case _: AttrOneManOffsetDateTime => addOne(ns, attr, tplIndex, transformOffsetDateTime, handleOffsetDateTime, extsOffsetDateTime)
      case _: AttrOneManZonedDateTime  => addOne(ns, attr, tplIndex, transformZonedDateTime, handleZonedDateTime, extsZonedDateTime)
      case _: AttrOneManUUID           => addOne(ns, attr, tplIndex, transformUUID, handleUUID, extsUUID)
      case _: AttrOneManURI            => addOne(ns, attr, tplIndex, transformURI, handleURI, extsURI)
      case _: AttrOneManByte           => addOne(ns, attr, tplIndex, transformByte, handleByte, extsByte)
      case _: AttrOneManShort          => addOne(ns, attr, tplIndex, transformShort, handleShort, extsShort)
      case _: AttrOneManChar           => addOne(ns, attr, tplIndex, transformChar, handleChar, extsChar)
    }
  }

  private def resolveAttrOneOpt(a: AttrOneOpt, tplIndex: Int): Product => Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case _: AttrOneOptID             => addOneOpt(ns, attr, tplIndex, transformID, handleID, extsID)
      case _: AttrOneOptString         => addOneOpt(ns, attr, tplIndex, transformString, handleString, extsString)
      case _: AttrOneOptInt            => addOneOpt(ns, attr, tplIndex, transformInt, handleInt, extsInt)
      case _: AttrOneOptLong           => addOneOpt(ns, attr, tplIndex, transformLong, handleLong, extsLong)
      case _: AttrOneOptFloat          => addOneOpt(ns, attr, tplIndex, transformFloat, handleFloat, extsFloat)
      case _: AttrOneOptDouble         => addOneOpt(ns, attr, tplIndex, transformDouble, handleDouble, extsDouble)
      case _: AttrOneOptBoolean        => addOneOpt(ns, attr, tplIndex, transformBoolean, handleBoolean, extsBoolean)
      case _: AttrOneOptBigInt         => addOneOpt(ns, attr, tplIndex, transformBigInt, handleBigInt, extsBigInt)
      case _: AttrOneOptBigDecimal     => addOneOpt(ns, attr, tplIndex, transformBigDecimal, handleBigDecimal, extsBigDecimal)
      case _: AttrOneOptDate           => addOneOpt(ns, attr, tplIndex, transformDate, handleDate, extsDate)
      case _: AttrOneOptDuration       => addOneOpt(ns, attr, tplIndex, transformDuration, handleDuration, extsDuration)
      case _: AttrOneOptInstant        => addOneOpt(ns, attr, tplIndex, transformInstant, handleInstant, extsInstant)
      case _: AttrOneOptLocalDate      => addOneOpt(ns, attr, tplIndex, transformLocalDate, handleLocalDate, extsLocalDate)
      case _: AttrOneOptLocalTime      => addOneOpt(ns, attr, tplIndex, transformLocalTime, handleLocalTime, extsLocalTime)
      case _: AttrOneOptLocalDateTime  => addOneOpt(ns, attr, tplIndex, transformLocalDateTime, handleLocalDateTime, extsLocalDateTime)
      case _: AttrOneOptOffsetTime     => addOneOpt(ns, attr, tplIndex, transformOffsetTime, handleOffsetTime, extsOffsetTime)
      case _: AttrOneOptOffsetDateTime => addOneOpt(ns, attr, tplIndex, transformOffsetDateTime, handleOffsetDateTime, extsOffsetDateTime)
      case _: AttrOneOptZonedDateTime  => addOneOpt(ns, attr, tplIndex, transformZonedDateTime, handleZonedDateTime, extsZonedDateTime)
      case _: AttrOneOptUUID           => addOneOpt(ns, attr, tplIndex, transformUUID, handleUUID, extsUUID)
      case _: AttrOneOptURI            => addOneOpt(ns, attr, tplIndex, transformURI, handleURI, extsURI)
      case _: AttrOneOptByte           => addOneOpt(ns, attr, tplIndex, transformByte, handleByte, extsByte)
      case _: AttrOneOptShort          => addOneOpt(ns, attr, tplIndex, transformShort, handleShort, extsShort)
      case _: AttrOneOptChar           => addOneOpt(ns, attr, tplIndex, transformChar, handleChar, extsChar)
    }
  }

  private def resolveAttrSetMan(a: AttrSetMan, tplIndex: Int): Product => Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case _: AttrSetManID             => addSet(ns, attr, set2arrayID, refNs, tplIndex, transformID, extsID, value2jsonID)
      case _: AttrSetManString         => addSet(ns, attr, set2arrayString, refNs, tplIndex, transformString, extsString, value2jsonString)
      case _: AttrSetManInt            => addSet(ns, attr, set2arrayInt, refNs, tplIndex, transformInt, extsInt, value2jsonInt)
      case _: AttrSetManLong           => addSet(ns, attr, set2arrayLong, refNs, tplIndex, transformLong, extsLong, value2jsonLong)
      case _: AttrSetManFloat          => addSet(ns, attr, set2arrayFloat, refNs, tplIndex, transformFloat, extsFloat, value2jsonFloat)
      case _: AttrSetManDouble         => addSet(ns, attr, set2arrayDouble, refNs, tplIndex, transformDouble, extsDouble, value2jsonDouble)
      case _: AttrSetManBoolean        => addSet(ns, attr, set2arrayBoolean, refNs, tplIndex, transformBoolean, extsBoolean, value2jsonBoolean)
      case _: AttrSetManBigInt         => addSet(ns, attr, set2arrayBigInt, refNs, tplIndex, transformBigInt, extsBigInt, value2jsonBigInt)
      case _: AttrSetManBigDecimal     => addSet(ns, attr, set2arrayBigDecimal, refNs, tplIndex, transformBigDecimal, extsBigDecimal, value2jsonBigDecimal)
      case _: AttrSetManDate           => addSet(ns, attr, set2arrayDate, refNs, tplIndex, transformDate, extsDate, value2jsonDate)
      case _: AttrSetManDuration       => addSet(ns, attr, set2arrayDuration, refNs, tplIndex, transformDuration, extsDuration, value2jsonDuration)
      case _: AttrSetManInstant        => addSet(ns, attr, set2arrayInstant, refNs, tplIndex, transformInstant, extsInstant, value2jsonInstant)
      case _: AttrSetManLocalDate      => addSet(ns, attr, set2arrayLocalDate, refNs, tplIndex, transformLocalDate, extsLocalDate, value2jsonLocalDate)
      case _: AttrSetManLocalTime      => addSet(ns, attr, set2arrayLocalTime, refNs, tplIndex, transformLocalTime, extsLocalTime, value2jsonLocalTime)
      case _: AttrSetManLocalDateTime  => addSet(ns, attr, set2arrayLocalDateTime, refNs, tplIndex, transformLocalDateTime, extsLocalDateTime, value2jsonLocalDateTime)
      case _: AttrSetManOffsetTime     => addSet(ns, attr, set2arrayOffsetTime, refNs, tplIndex, transformOffsetTime, extsOffsetTime, value2jsonOffsetTime)
      case _: AttrSetManOffsetDateTime => addSet(ns, attr, set2arrayOffsetDateTime, refNs, tplIndex, transformOffsetDateTime, extsOffsetDateTime, value2jsonOffsetDateTime)
      case _: AttrSetManZonedDateTime  => addSet(ns, attr, set2arrayZonedDateTime, refNs, tplIndex, transformZonedDateTime, extsZonedDateTime, value2jsonZonedDateTime)
      case _: AttrSetManUUID           => addSet(ns, attr, set2arrayUUID, refNs, tplIndex, transformUUID, extsUUID, value2jsonUUID)
      case _: AttrSetManURI            => addSet(ns, attr, set2arrayURI, refNs, tplIndex, transformURI, extsURI, value2jsonURI)
      case _: AttrSetManByte           => addSet(ns, attr, set2arrayByte, refNs, tplIndex, transformByte, extsByte, value2jsonByte)
      case _: AttrSetManShort          => addSet(ns, attr, set2arrayShort, refNs, tplIndex, transformShort, extsShort, value2jsonShort)
      case _: AttrSetManChar           => addSet(ns, attr, set2arrayChar, refNs, tplIndex, transformChar, extsChar, value2jsonChar)
    }
  }

  private def resolveAttrSetOpt(a: AttrSetOpt, tplIndex: Int): Product => Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case _: AttrSetOptID             => addSetOpt(ns, attr, set2arrayID, refNs, tplIndex, transformID, extsID, value2jsonID)
      case _: AttrSetOptString         => addSetOpt(ns, attr, set2arrayString, refNs, tplIndex, transformString, extsString, value2jsonString)
      case _: AttrSetOptInt            => addSetOpt(ns, attr, set2arrayInt, refNs, tplIndex, transformInt, extsInt, value2jsonInt)
      case _: AttrSetOptLong           => addSetOpt(ns, attr, set2arrayLong, refNs, tplIndex, transformLong, extsLong, value2jsonLong)
      case _: AttrSetOptFloat          => addSetOpt(ns, attr, set2arrayFloat, refNs, tplIndex, transformFloat, extsFloat, value2jsonFloat)
      case _: AttrSetOptDouble         => addSetOpt(ns, attr, set2arrayDouble, refNs, tplIndex, transformDouble, extsDouble, value2jsonDouble)
      case _: AttrSetOptBoolean        => addSetOpt(ns, attr, set2arrayBoolean, refNs, tplIndex, transformBoolean, extsBoolean, value2jsonBoolean)
      case _: AttrSetOptBigInt         => addSetOpt(ns, attr, set2arrayBigInt, refNs, tplIndex, transformBigInt, extsBigInt, value2jsonBigInt)
      case _: AttrSetOptBigDecimal     => addSetOpt(ns, attr, set2arrayBigDecimal, refNs, tplIndex, transformBigDecimal, extsBigDecimal, value2jsonBigDecimal)
      case _: AttrSetOptDate           => addSetOpt(ns, attr, set2arrayDate, refNs, tplIndex, transformDate, extsDate, value2jsonDate)
      case _: AttrSetOptDuration       => addSetOpt(ns, attr, set2arrayDuration, refNs, tplIndex, transformDuration, extsDuration, value2jsonDuration)
      case _: AttrSetOptInstant        => addSetOpt(ns, attr, set2arrayInstant, refNs, tplIndex, transformInstant, extsInstant, value2jsonInstant)
      case _: AttrSetOptLocalDate      => addSetOpt(ns, attr, set2arrayLocalDate, refNs, tplIndex, transformLocalDate, extsLocalDate, value2jsonLocalDate)
      case _: AttrSetOptLocalTime      => addSetOpt(ns, attr, set2arrayLocalTime, refNs, tplIndex, transformLocalTime, extsLocalTime, value2jsonLocalTime)
      case _: AttrSetOptLocalDateTime  => addSetOpt(ns, attr, set2arrayLocalDateTime, refNs, tplIndex, transformLocalDateTime, extsLocalDateTime, value2jsonLocalDateTime)
      case _: AttrSetOptOffsetTime     => addSetOpt(ns, attr, set2arrayOffsetTime, refNs, tplIndex, transformOffsetTime, extsOffsetTime, value2jsonOffsetTime)
      case _: AttrSetOptOffsetDateTime => addSetOpt(ns, attr, set2arrayOffsetDateTime, refNs, tplIndex, transformOffsetDateTime, extsOffsetDateTime, value2jsonOffsetDateTime)
      case _: AttrSetOptZonedDateTime  => addSetOpt(ns, attr, set2arrayZonedDateTime, refNs, tplIndex, transformZonedDateTime, extsZonedDateTime, value2jsonZonedDateTime)
      case _: AttrSetOptUUID           => addSetOpt(ns, attr, set2arrayUUID, refNs, tplIndex, transformUUID, extsUUID, value2jsonUUID)
      case _: AttrSetOptURI            => addSetOpt(ns, attr, set2arrayURI, refNs, tplIndex, transformURI, extsURI, value2jsonURI)
      case _: AttrSetOptByte           => addSetOpt(ns, attr, set2arrayByte, refNs, tplIndex, transformByte, extsByte, value2jsonByte)
      case _: AttrSetOptShort          => addSetOpt(ns, attr, set2arrayShort, refNs, tplIndex, transformShort, extsShort, value2jsonShort)
      case _: AttrSetOptChar           => addSetOpt(ns, attr, set2arrayChar, refNs, tplIndex, transformChar, extsChar, value2jsonChar)
    }
  }

  private def resolveAttrSeqMan(a: AttrSeqMan, tplIndex: Int): Product => Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case _: AttrSeqManID             => addSeq(ns, attr, refNs, tplIndex, transformID) //, set2arrayID, extsID, value2jsonID)
      case _: AttrSeqManString         => addSeq(ns, attr, refNs, tplIndex, transformString) //, set2arrayString, extsString, value2jsonString)
      case _: AttrSeqManInt            => addSeq(ns, attr, refNs, tplIndex, transformInt) //, set2arrayInt, extsInt, value2jsonInt)
      case _: AttrSeqManLong           => addSeq(ns, attr, refNs, tplIndex, transformLong) //, set2arrayLong, extsLong, value2jsonLong)
      case _: AttrSeqManFloat          => addSeq(ns, attr, refNs, tplIndex, transformFloat) //, set2arrayFloat, extsFloat, value2jsonFloat)
      case _: AttrSeqManDouble         => addSeq(ns, attr, refNs, tplIndex, transformDouble) //, set2arrayDouble, extsDouble, value2jsonDouble)
      case _: AttrSeqManBoolean        => addSeq(ns, attr, refNs, tplIndex, transformBoolean) //, set2arrayBoolean, extsBoolean, value2jsonBoolean)
      case _: AttrSeqManBigInt         => addSeq(ns, attr, refNs, tplIndex, transformBigInt) //, set2arrayBigInt, extsBigInt, value2jsonBigInt)
      case _: AttrSeqManBigDecimal     => addSeq(ns, attr, refNs, tplIndex, transformBigDecimal) //, set2arrayBigDecimal, extsBigDecimal, value2jsonBigDecimal)
      case _: AttrSeqManDate           => addSeq(ns, attr, refNs, tplIndex, transformDate) //, set2arrayDate, extsDate, value2jsonDate)
      case _: AttrSeqManDuration       => addSeq(ns, attr, refNs, tplIndex, transformDuration) //, set2arrayDuration, extsDuration, value2jsonDuration)
      case _: AttrSeqManInstant        => addSeq(ns, attr, refNs, tplIndex, transformInstant) //, set2arrayInstant, extsInstant, value2jsonInstant)
      case _: AttrSeqManLocalDate      => addSeq(ns, attr, refNs, tplIndex, transformLocalDate) //, set2arrayLocalDate, extsLocalDate, value2jsonLocalDate)
      case _: AttrSeqManLocalTime      => addSeq(ns, attr, refNs, tplIndex, transformLocalTime) //, set2arrayLocalTime, extsLocalTime, value2jsonLocalTime)
      case _: AttrSeqManLocalDateTime  => addSeq(ns, attr, refNs, tplIndex, transformLocalDateTime) //, set2arrayLocalDateTime, extsLocalDateTime, value2jsonLocalDateTime)
      case _: AttrSeqManOffsetTime     => addSeq(ns, attr, refNs, tplIndex, transformOffsetTime) //, set2arrayOffsetTime, extsOffsetTime, value2jsonOffsetTime)
      case _: AttrSeqManOffsetDateTime => addSeq(ns, attr, refNs, tplIndex, transformOffsetDateTime) //, set2arrayOffsetDateTime, extsOffsetDateTime, value2jsonOffsetDateTime)
      case _: AttrSeqManZonedDateTime  => addSeq(ns, attr, refNs, tplIndex, transformZonedDateTime) //, set2arrayZonedDateTime, extsZonedDateTime, value2jsonZonedDateTime)
      case _: AttrSeqManUUID           => addSeq(ns, attr, refNs, tplIndex, transformUUID) //, set2arrayUUID, extsUUID, value2jsonUUID)
      case _: AttrSeqManURI            => addSeq(ns, attr, refNs, tplIndex, transformURI) //, set2arrayURI, extsURI, value2jsonURI)
      case a: AttrSeqManByte           => addByteArray(ns, attr, tplIndex, optByteArray(ns, attr, a.vs)) //, set2arrayByte, extsByte, value2jsonByte)
      case _: AttrSeqManShort          => addSeq(ns, attr, refNs, tplIndex, transformShort) //, set2arrayShort, extsShort, value2jsonShort)
      case _: AttrSeqManChar           => addSeq(ns, attr, refNs, tplIndex, transformChar) //, set2arrayChar, extsChar, value2jsonChar)
    }
  }

  private def resolveAttrSeqOpt(a: AttrSeqOpt, tplIndex: Int): Product => Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case _: AttrSeqOptID             => addSeqOpt(ns, attr, refNs, tplIndex, transformID) //, set2arrayID,  extsID, value2jsonID)
      case _: AttrSeqOptString         => addSeqOpt(ns, attr, refNs, tplIndex, transformString) //, set2arrayString,  extsString, value2jsonString)
      case _: AttrSeqOptInt            => addSeqOpt(ns, attr, refNs, tplIndex, transformInt) //, set2arrayInt,  extsInt, value2jsonInt)
      case _: AttrSeqOptLong           => addSeqOpt(ns, attr, refNs, tplIndex, transformLong) //, set2arrayLong,  extsLong, value2jsonLong)
      case _: AttrSeqOptFloat          => addSeqOpt(ns, attr, refNs, tplIndex, transformFloat) //, set2arrayFloat,  extsFloat, value2jsonFloat)
      case _: AttrSeqOptDouble         => addSeqOpt(ns, attr, refNs, tplIndex, transformDouble) //, set2arrayDouble,  extsDouble, value2jsonDouble)
      case _: AttrSeqOptBoolean        => addSeqOpt(ns, attr, refNs, tplIndex, transformBoolean) //, set2arrayBoolean,  extsBoolean, value2jsonBoolean)
      case _: AttrSeqOptBigInt         => addSeqOpt(ns, attr, refNs, tplIndex, transformBigInt) //, set2arrayBigInt,  extsBigInt, value2jsonBigInt)
      case _: AttrSeqOptBigDecimal     => addSeqOpt(ns, attr, refNs, tplIndex, transformBigDecimal) //, set2arrayBigDecimal,  extsBigDecimal, value2jsonBigDecimal)
      case _: AttrSeqOptDate           => addSeqOpt(ns, attr, refNs, tplIndex, transformDate) //, set2arrayDate,  extsDate, value2jsonDate)
      case _: AttrSeqOptDuration       => addSeqOpt(ns, attr, refNs, tplIndex, transformDuration) //, set2arrayDuration,  extsDuration, value2jsonDuration)
      case _: AttrSeqOptInstant        => addSeqOpt(ns, attr, refNs, tplIndex, transformInstant) //, set2arrayInstant,  extsInstant, value2jsonInstant)
      case _: AttrSeqOptLocalDate      => addSeqOpt(ns, attr, refNs, tplIndex, transformLocalDate) //, set2arrayLocalDate,  extsLocalDate, value2jsonLocalDate)
      case _: AttrSeqOptLocalTime      => addSeqOpt(ns, attr, refNs, tplIndex, transformLocalTime) //, set2arrayLocalTime,  extsLocalTime, value2jsonLocalTime)
      case _: AttrSeqOptLocalDateTime  => addSeqOpt(ns, attr, refNs, tplIndex, transformLocalDateTime) //, set2arrayLocalDateTime,  extsLocalDateTime, value2jsonLocalDateTime)
      case _: AttrSeqOptOffsetTime     => addSeqOpt(ns, attr, refNs, tplIndex, transformOffsetTime) //, set2arrayOffsetTime,  extsOffsetTime, value2jsonOffsetTime)
      case _: AttrSeqOptOffsetDateTime => addSeqOpt(ns, attr, refNs, tplIndex, transformOffsetDateTime) //, set2arrayOffsetDateTime,  extsOffsetDateTime, value2jsonOffsetDateTime)
      case _: AttrSeqOptZonedDateTime  => addSeqOpt(ns, attr, refNs, tplIndex, transformZonedDateTime) //, set2arrayZonedDateTime,  extsZonedDateTime, value2jsonZonedDateTime)
      case _: AttrSeqOptUUID           => addSeqOpt(ns, attr, refNs, tplIndex, transformUUID) //, set2arrayUUID,  extsUUID, value2jsonUUID)
      case _: AttrSeqOptURI            => addSeqOpt(ns, attr, refNs, tplIndex, transformURI) //, set2arrayURI,  extsURI, value2jsonURI)
      case a: AttrSeqOptByte           => addByteArray(ns, attr, tplIndex, a.vs.flatMap(vs => optByteArray(ns, attr, vs))) //, set2arrayByte, extsByte, value2jsonByte)
      case _: AttrSeqOptShort          => addSeqOpt(ns, attr, refNs, tplIndex, transformShort) //, set2arrayShort,  extsShort, value2jsonShort)
      case _: AttrSeqOptChar           => addSeqOpt(ns, attr, refNs, tplIndex, transformChar) //, set2arrayChar,  extsChar, value2jsonChar)
    }
  }

  private def resolveAttrMapMan(a: AttrMapMan, tplIndex: Int): Product => Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case _: AttrMapManID             => addMap(ns, attr, set2arrayID, refNs, tplIndex, transformID, extsID, value2jsonID)
      case _: AttrMapManString         => addMap(ns, attr, set2arrayString, refNs, tplIndex, transformString, extsString, value2jsonString)
      case _: AttrMapManInt            => addMap(ns, attr, set2arrayInt, refNs, tplIndex, transformInt, extsInt, value2jsonInt)
      case _: AttrMapManLong           => addMap(ns, attr, set2arrayLong, refNs, tplIndex, transformLong, extsLong, value2jsonLong)
      case _: AttrMapManFloat          => addMap(ns, attr, set2arrayFloat, refNs, tplIndex, transformFloat, extsFloat, value2jsonFloat)
      case _: AttrMapManDouble         => addMap(ns, attr, set2arrayDouble, refNs, tplIndex, transformDouble, extsDouble, value2jsonDouble)
      case _: AttrMapManBoolean        => addMap(ns, attr, set2arrayBoolean, refNs, tplIndex, transformBoolean, extsBoolean, value2jsonBoolean)
      case _: AttrMapManBigInt         => addMap(ns, attr, set2arrayBigInt, refNs, tplIndex, transformBigInt, extsBigInt, value2jsonBigInt)
      case _: AttrMapManBigDecimal     => addMap(ns, attr, set2arrayBigDecimal, refNs, tplIndex, transformBigDecimal, extsBigDecimal, value2jsonBigDecimal)
      case _: AttrMapManDate           => addMap(ns, attr, set2arrayDate, refNs, tplIndex, transformDate, extsDate, value2jsonDate)
      case _: AttrMapManDuration       => addMap(ns, attr, set2arrayDuration, refNs, tplIndex, transformDuration, extsDuration, value2jsonDuration)
      case _: AttrMapManInstant        => addMap(ns, attr, set2arrayInstant, refNs, tplIndex, transformInstant, extsInstant, value2jsonInstant)
      case _: AttrMapManLocalDate      => addMap(ns, attr, set2arrayLocalDate, refNs, tplIndex, transformLocalDate, extsLocalDate, value2jsonLocalDate)
      case _: AttrMapManLocalTime      => addMap(ns, attr, set2arrayLocalTime, refNs, tplIndex, transformLocalTime, extsLocalTime, value2jsonLocalTime)
      case _: AttrMapManLocalDateTime  => addMap(ns, attr, set2arrayLocalDateTime, refNs, tplIndex, transformLocalDateTime, extsLocalDateTime, value2jsonLocalDateTime)
      case _: AttrMapManOffsetTime     => addMap(ns, attr, set2arrayOffsetTime, refNs, tplIndex, transformOffsetTime, extsOffsetTime, value2jsonOffsetTime)
      case _: AttrMapManOffsetDateTime => addMap(ns, attr, set2arrayOffsetDateTime, refNs, tplIndex, transformOffsetDateTime, extsOffsetDateTime, value2jsonOffsetDateTime)
      case _: AttrMapManZonedDateTime  => addMap(ns, attr, set2arrayZonedDateTime, refNs, tplIndex, transformZonedDateTime, extsZonedDateTime, value2jsonZonedDateTime)
      case _: AttrMapManUUID           => addMap(ns, attr, set2arrayUUID, refNs, tplIndex, transformUUID, extsUUID, value2jsonUUID)
      case _: AttrMapManURI            => addMap(ns, attr, set2arrayURI, refNs, tplIndex, transformURI, extsURI, value2jsonURI)
      case _: AttrMapManByte           => addMap(ns, attr, set2arrayByte, refNs, tplIndex, transformByte, extsByte, value2jsonByte)
      case _: AttrMapManShort          => addMap(ns, attr, set2arrayShort, refNs, tplIndex, transformShort, extsShort, value2jsonShort)
      case _: AttrMapManChar           => addMap(ns, attr, set2arrayChar, refNs, tplIndex, transformChar, extsChar, value2jsonChar)
    }
  }

  private def resolveAttrMapOpt(a: AttrMapOpt, tplIndex: Int): Product => Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case _: AttrMapOptID             => addMapOpt(ns, attr, set2arrayID, refNs, tplIndex, transformID, extsID, value2jsonID)
      case _: AttrMapOptString         => addMapOpt(ns, attr, set2arrayString, refNs, tplIndex, transformString, extsString, value2jsonString)
      case _: AttrMapOptInt            => addMapOpt(ns, attr, set2arrayInt, refNs, tplIndex, transformInt, extsInt, value2jsonInt)
      case _: AttrMapOptLong           => addMapOpt(ns, attr, set2arrayLong, refNs, tplIndex, transformLong, extsLong, value2jsonLong)
      case _: AttrMapOptFloat          => addMapOpt(ns, attr, set2arrayFloat, refNs, tplIndex, transformFloat, extsFloat, value2jsonFloat)
      case _: AttrMapOptDouble         => addMapOpt(ns, attr, set2arrayDouble, refNs, tplIndex, transformDouble, extsDouble, value2jsonDouble)
      case _: AttrMapOptBoolean        => addMapOpt(ns, attr, set2arrayBoolean, refNs, tplIndex, transformBoolean, extsBoolean, value2jsonBoolean)
      case _: AttrMapOptBigInt         => addMapOpt(ns, attr, set2arrayBigInt, refNs, tplIndex, transformBigInt, extsBigInt, value2jsonBigInt)
      case _: AttrMapOptBigDecimal     => addMapOpt(ns, attr, set2arrayBigDecimal, refNs, tplIndex, transformBigDecimal, extsBigDecimal, value2jsonBigDecimal)
      case _: AttrMapOptDate           => addMapOpt(ns, attr, set2arrayDate, refNs, tplIndex, transformDate, extsDate, value2jsonDate)
      case _: AttrMapOptDuration       => addMapOpt(ns, attr, set2arrayDuration, refNs, tplIndex, transformDuration, extsDuration, value2jsonDuration)
      case _: AttrMapOptInstant        => addMapOpt(ns, attr, set2arrayInstant, refNs, tplIndex, transformInstant, extsInstant, value2jsonInstant)
      case _: AttrMapOptLocalDate      => addMapOpt(ns, attr, set2arrayLocalDate, refNs, tplIndex, transformLocalDate, extsLocalDate, value2jsonLocalDate)
      case _: AttrMapOptLocalTime      => addMapOpt(ns, attr, set2arrayLocalTime, refNs, tplIndex, transformLocalTime, extsLocalTime, value2jsonLocalTime)
      case _: AttrMapOptLocalDateTime  => addMapOpt(ns, attr, set2arrayLocalDateTime, refNs, tplIndex, transformLocalDateTime, extsLocalDateTime, value2jsonLocalDateTime)
      case _: AttrMapOptOffsetTime     => addMapOpt(ns, attr, set2arrayOffsetTime, refNs, tplIndex, transformOffsetTime, extsOffsetTime, value2jsonOffsetTime)
      case _: AttrMapOptOffsetDateTime => addMapOpt(ns, attr, set2arrayOffsetDateTime, refNs, tplIndex, transformOffsetDateTime, extsOffsetDateTime, value2jsonOffsetDateTime)
      case _: AttrMapOptZonedDateTime  => addMapOpt(ns, attr, set2arrayZonedDateTime, refNs, tplIndex, transformZonedDateTime, extsZonedDateTime, value2jsonZonedDateTime)
      case _: AttrMapOptUUID           => addMapOpt(ns, attr, set2arrayUUID, refNs, tplIndex, transformUUID, extsUUID, value2jsonUUID)
      case _: AttrMapOptURI            => addMapOpt(ns, attr, set2arrayURI, refNs, tplIndex, transformURI, extsURI, value2jsonURI)
      case _: AttrMapOptByte           => addMapOpt(ns, attr, set2arrayByte, refNs, tplIndex, transformByte, extsByte, value2jsonByte)
      case _: AttrMapOptShort          => addMapOpt(ns, attr, set2arrayShort, refNs, tplIndex, transformShort, extsShort, value2jsonShort)
      case _: AttrMapOptChar           => addMapOpt(ns, attr, set2arrayChar, refNs, tplIndex, transformChar, extsChar, value2jsonChar)
    }
  }
}