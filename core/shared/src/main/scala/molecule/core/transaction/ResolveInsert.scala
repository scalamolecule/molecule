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
  private def noEmpty(a: Attr) = throw new Exception("Can't use tacit attributes in insert molecule (${a.name}).")


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
            throw ModelError(s"Can't insert attributes with an applied value (${a.name}).")
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

        case Ref(ns, refAttr, refNs, card, _, _) =>
          prevRefs += refAttr
          val refResolver = addRef(ns, refAttr, refNs, card)
          resolve(nsMap, tail, resolvers :+ refResolver, tplIndex)

        case OptRef(Ref(ns, refAttr, refNs, _, _, _), optRefElements) => throw ModelError(
          "Optional ref not allowed in insert molecule. Please use mandatory ref instead."
        )

        case BackRef(backRefNs, _, _) =>
          tail.head match {
            case Ref(_, refAttr, _, _, _, _) if prevRefs.contains(refAttr) => throw ModelError(
              s"Can't re-use previous namespace ${refAttr.capitalize} after backref _$backRefNs."
            )
            case _                                                         => // ok
          }
          val backRefResolver = addBackRef(backRefNs)
          resolve(nsMap, tail, resolvers :+ backRefResolver, tplIndex)

        case Nested(Ref(ns, refAttr, refNs, _, _, _), nestedElements) =>
          prevRefs.clear()
          val nestedResolver = addNested(nsMap, tplIndex, ns, refAttr, refNs, nestedElements)
          resolve(nsMap, tail, resolvers :+ nestedResolver, tplIndex)

        case NestedOpt(Ref(ns, refAttr, refNs, _, _, _), nestedElements) =>
          // (same behaviour as mandatory nested - the list can have data or not)
          prevRefs.clear()
          val optNestedResolver = addNested(nsMap, tplIndex, ns, refAttr, refNs, nestedElements)
          resolve(nsMap, tail, resolvers :+ optNestedResolver, tplIndex)
      }
      case Nil             => resolvers
    }
  }


  private def resolveAttrOneMan(a: AttrOneMan, tplIndex: Int): Product => Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case _: AttrOneManID             => addOne(ns, attr, tplIndex, transformID, extsID)
      case _: AttrOneManString         => addOne(ns, attr, tplIndex, transformString, extsString)
      case _: AttrOneManInt            => addOne(ns, attr, tplIndex, transformInt, extsInt)
      case _: AttrOneManLong           => addOne(ns, attr, tplIndex, transformLong, extsLong)
      case _: AttrOneManFloat          => addOne(ns, attr, tplIndex, transformFloat, extsFloat)
      case _: AttrOneManDouble         => addOne(ns, attr, tplIndex, transformDouble, extsDouble)
      case _: AttrOneManBoolean        => addOne(ns, attr, tplIndex, transformBoolean, extsBoolean)
      case _: AttrOneManBigInt         => addOne(ns, attr, tplIndex, transformBigInt, extsBigInt)
      case _: AttrOneManBigDecimal     => addOne(ns, attr, tplIndex, transformBigDecimal, extsBigDecimal)
      case _: AttrOneManDate           => addOne(ns, attr, tplIndex, transformDate, extsDate)
      case _: AttrOneManDuration       => addOne(ns, attr, tplIndex, transformDuration, extsDuration)
      case _: AttrOneManInstant        => addOne(ns, attr, tplIndex, transformInstant, extsInstant)
      case _: AttrOneManLocalDate      => addOne(ns, attr, tplIndex, transformLocalDate, extsLocalDate)
      case _: AttrOneManLocalTime      => addOne(ns, attr, tplIndex, transformLocalTime, extsLocalTime)
      case _: AttrOneManLocalDateTime  => addOne(ns, attr, tplIndex, transformLocalDateTime, extsLocalDateTime)
      case _: AttrOneManOffsetTime     => addOne(ns, attr, tplIndex, transformOffsetTime, extsOffsetTime)
      case _: AttrOneManOffsetDateTime => addOne(ns, attr, tplIndex, transformOffsetDateTime, extsOffsetDateTime)
      case _: AttrOneManZonedDateTime  => addOne(ns, attr, tplIndex, transformZonedDateTime, extsZonedDateTime)
      case _: AttrOneManUUID           => addOne(ns, attr, tplIndex, transformUUID, extsUUID)
      case _: AttrOneManURI            => addOne(ns, attr, tplIndex, transformURI, extsURI)
      case _: AttrOneManByte           => addOne(ns, attr, tplIndex, transformByte, extsByte)
      case _: AttrOneManShort          => addOne(ns, attr, tplIndex, transformShort, extsShort)
      case _: AttrOneManChar           => addOne(ns, attr, tplIndex, transformChar, extsChar)
    }
  }

  private def resolveAttrOneOpt(a: AttrOneOpt, tplIndex: Int): Product => Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case _: AttrOneOptID             => addOneOpt(ns, attr, tplIndex, transformID, extsID)
      case _: AttrOneOptString         => addOneOpt(ns, attr, tplIndex, transformString, extsString)
      case _: AttrOneOptInt            => addOneOpt(ns, attr, tplIndex, transformInt, extsInt)
      case _: AttrOneOptLong           => addOneOpt(ns, attr, tplIndex, transformLong, extsLong)
      case _: AttrOneOptFloat          => addOneOpt(ns, attr, tplIndex, transformFloat, extsFloat)
      case _: AttrOneOptDouble         => addOneOpt(ns, attr, tplIndex, transformDouble, extsDouble)
      case _: AttrOneOptBoolean        => addOneOpt(ns, attr, tplIndex, transformBoolean, extsBoolean)
      case _: AttrOneOptBigInt         => addOneOpt(ns, attr, tplIndex, transformBigInt, extsBigInt)
      case _: AttrOneOptBigDecimal     => addOneOpt(ns, attr, tplIndex, transformBigDecimal, extsBigDecimal)
      case _: AttrOneOptDate           => addOneOpt(ns, attr, tplIndex, transformDate, extsDate)
      case _: AttrOneOptDuration       => addOneOpt(ns, attr, tplIndex, transformDuration, extsDuration)
      case _: AttrOneOptInstant        => addOneOpt(ns, attr, tplIndex, transformInstant, extsInstant)
      case _: AttrOneOptLocalDate      => addOneOpt(ns, attr, tplIndex, transformLocalDate, extsLocalDate)
      case _: AttrOneOptLocalTime      => addOneOpt(ns, attr, tplIndex, transformLocalTime, extsLocalTime)
      case _: AttrOneOptLocalDateTime  => addOneOpt(ns, attr, tplIndex, transformLocalDateTime, extsLocalDateTime)
      case _: AttrOneOptOffsetTime     => addOneOpt(ns, attr, tplIndex, transformOffsetTime, extsOffsetTime)
      case _: AttrOneOptOffsetDateTime => addOneOpt(ns, attr, tplIndex, transformOffsetDateTime, extsOffsetDateTime)
      case _: AttrOneOptZonedDateTime  => addOneOpt(ns, attr, tplIndex, transformZonedDateTime, extsZonedDateTime)
      case _: AttrOneOptUUID           => addOneOpt(ns, attr, tplIndex, transformUUID, extsUUID)
      case _: AttrOneOptURI            => addOneOpt(ns, attr, tplIndex, transformURI, extsURI)
      case _: AttrOneOptByte           => addOneOpt(ns, attr, tplIndex, transformByte, extsByte)
      case _: AttrOneOptShort          => addOneOpt(ns, attr, tplIndex, transformShort, extsShort)
      case _: AttrOneOptChar           => addOneOpt(ns, attr, tplIndex, transformChar, extsChar)
    }
  }

  private def resolveAttrSetMan(a: AttrSetMan, tplIndex: Int): Product => Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case _: AttrSetManID             => addSet(ns, attr, refNs, tplIndex, transformID, extsID, set2arrayID, value2jsonID)
      case _: AttrSetManString         => addSet(ns, attr, refNs, tplIndex, transformString, extsString, set2arrayString, value2jsonString)
      case _: AttrSetManInt            => addSet(ns, attr, refNs, tplIndex, transformInt, extsInt, set2arrayInt, value2jsonInt)
      case _: AttrSetManLong           => addSet(ns, attr, refNs, tplIndex, transformLong, extsLong, set2arrayLong, value2jsonLong)
      case _: AttrSetManFloat          => addSet(ns, attr, refNs, tplIndex, transformFloat, extsFloat, set2arrayFloat, value2jsonFloat)
      case _: AttrSetManDouble         => addSet(ns, attr, refNs, tplIndex, transformDouble, extsDouble, set2arrayDouble, value2jsonDouble)
      case _: AttrSetManBoolean        => addSet(ns, attr, refNs, tplIndex, transformBoolean, extsBoolean, set2arrayBoolean, value2jsonBoolean)
      case _: AttrSetManBigInt         => addSet(ns, attr, refNs, tplIndex, transformBigInt, extsBigInt, set2arrayBigInt, value2jsonBigInt)
      case _: AttrSetManBigDecimal     => addSet(ns, attr, refNs, tplIndex, transformBigDecimal, extsBigDecimal, set2arrayBigDecimal, value2jsonBigDecimal)
      case _: AttrSetManDate           => addSet(ns, attr, refNs, tplIndex, transformDate, extsDate, set2arrayDate, value2jsonDate)
      case _: AttrSetManDuration       => addSet(ns, attr, refNs, tplIndex, transformDuration, extsDuration, set2arrayDuration, value2jsonDuration)
      case _: AttrSetManInstant        => addSet(ns, attr, refNs, tplIndex, transformInstant, extsInstant, set2arrayInstant, value2jsonInstant)
      case _: AttrSetManLocalDate      => addSet(ns, attr, refNs, tplIndex, transformLocalDate, extsLocalDate, set2arrayLocalDate, value2jsonLocalDate)
      case _: AttrSetManLocalTime      => addSet(ns, attr, refNs, tplIndex, transformLocalTime, extsLocalTime, set2arrayLocalTime, value2jsonLocalTime)
      case _: AttrSetManLocalDateTime  => addSet(ns, attr, refNs, tplIndex, transformLocalDateTime, extsLocalDateTime, set2arrayLocalDateTime, value2jsonLocalDateTime)
      case _: AttrSetManOffsetTime     => addSet(ns, attr, refNs, tplIndex, transformOffsetTime, extsOffsetTime, set2arrayOffsetTime, value2jsonOffsetTime)
      case _: AttrSetManOffsetDateTime => addSet(ns, attr, refNs, tplIndex, transformOffsetDateTime, extsOffsetDateTime, set2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case _: AttrSetManZonedDateTime  => addSet(ns, attr, refNs, tplIndex, transformZonedDateTime, extsZonedDateTime, set2arrayZonedDateTime, value2jsonZonedDateTime)
      case _: AttrSetManUUID           => addSet(ns, attr, refNs, tplIndex, transformUUID, extsUUID, set2arrayUUID, value2jsonUUID)
      case _: AttrSetManURI            => addSet(ns, attr, refNs, tplIndex, transformURI, extsURI, set2arrayURI, value2jsonURI)
      case _: AttrSetManByte           => addSet(ns, attr, refNs, tplIndex, transformByte, extsByte, set2arrayByte, value2jsonByte)
      case _: AttrSetManShort          => addSet(ns, attr, refNs, tplIndex, transformShort, extsShort, set2arrayShort, value2jsonShort)
      case _: AttrSetManChar           => addSet(ns, attr, refNs, tplIndex, transformChar, extsChar, set2arrayChar, value2jsonChar)
    }
  }

  private def resolveAttrSetOpt(a: AttrSetOpt, tplIndex: Int): Product => Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case _: AttrSetOptID             => addSetOpt(ns, attr, refNs, tplIndex, transformID, extsID, set2arrayID, value2jsonID)
      case _: AttrSetOptString         => addSetOpt(ns, attr, refNs, tplIndex, transformString, extsString, set2arrayString, value2jsonString)
      case _: AttrSetOptInt            => addSetOpt(ns, attr, refNs, tplIndex, transformInt, extsInt, set2arrayInt, value2jsonInt)
      case _: AttrSetOptLong           => addSetOpt(ns, attr, refNs, tplIndex, transformLong, extsLong, set2arrayLong, value2jsonLong)
      case _: AttrSetOptFloat          => addSetOpt(ns, attr, refNs, tplIndex, transformFloat, extsFloat, set2arrayFloat, value2jsonFloat)
      case _: AttrSetOptDouble         => addSetOpt(ns, attr, refNs, tplIndex, transformDouble, extsDouble, set2arrayDouble, value2jsonDouble)
      case _: AttrSetOptBoolean        => addSetOpt(ns, attr, refNs, tplIndex, transformBoolean, extsBoolean, set2arrayBoolean, value2jsonBoolean)
      case _: AttrSetOptBigInt         => addSetOpt(ns, attr, refNs, tplIndex, transformBigInt, extsBigInt, set2arrayBigInt, value2jsonBigInt)
      case _: AttrSetOptBigDecimal     => addSetOpt(ns, attr, refNs, tplIndex, transformBigDecimal, extsBigDecimal, set2arrayBigDecimal, value2jsonBigDecimal)
      case _: AttrSetOptDate           => addSetOpt(ns, attr, refNs, tplIndex, transformDate, extsDate, set2arrayDate, value2jsonDate)
      case _: AttrSetOptDuration       => addSetOpt(ns, attr, refNs, tplIndex, transformDuration, extsDuration, set2arrayDuration, value2jsonDuration)
      case _: AttrSetOptInstant        => addSetOpt(ns, attr, refNs, tplIndex, transformInstant, extsInstant, set2arrayInstant, value2jsonInstant)
      case _: AttrSetOptLocalDate      => addSetOpt(ns, attr, refNs, tplIndex, transformLocalDate, extsLocalDate, set2arrayLocalDate, value2jsonLocalDate)
      case _: AttrSetOptLocalTime      => addSetOpt(ns, attr, refNs, tplIndex, transformLocalTime, extsLocalTime, set2arrayLocalTime, value2jsonLocalTime)
      case _: AttrSetOptLocalDateTime  => addSetOpt(ns, attr, refNs, tplIndex, transformLocalDateTime, extsLocalDateTime, set2arrayLocalDateTime, value2jsonLocalDateTime)
      case _: AttrSetOptOffsetTime     => addSetOpt(ns, attr, refNs, tplIndex, transformOffsetTime, extsOffsetTime, set2arrayOffsetTime, value2jsonOffsetTime)
      case _: AttrSetOptOffsetDateTime => addSetOpt(ns, attr, refNs, tplIndex, transformOffsetDateTime, extsOffsetDateTime, set2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case _: AttrSetOptZonedDateTime  => addSetOpt(ns, attr, refNs, tplIndex, transformZonedDateTime, extsZonedDateTime, set2arrayZonedDateTime, value2jsonZonedDateTime)
      case _: AttrSetOptUUID           => addSetOpt(ns, attr, refNs, tplIndex, transformUUID, extsUUID, set2arrayUUID, value2jsonUUID)
      case _: AttrSetOptURI            => addSetOpt(ns, attr, refNs, tplIndex, transformURI, extsURI, set2arrayURI, value2jsonURI)
      case _: AttrSetOptByte           => addSetOpt(ns, attr, refNs, tplIndex, transformByte, extsByte, set2arrayByte, value2jsonByte)
      case _: AttrSetOptShort          => addSetOpt(ns, attr, refNs, tplIndex, transformShort, extsShort, set2arrayShort, value2jsonShort)
      case _: AttrSetOptChar           => addSetOpt(ns, attr, refNs, tplIndex, transformChar, extsChar, set2arrayChar, value2jsonChar)
    }
  }

  private def resolveAttrSeqMan(a: AttrSeqMan, tplIndex: Int): Product => Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case _: AttrSeqManID             => addSeq(ns, attr, refNs, tplIndex, transformID, extsID, seq2arrayID, value2jsonID)
      case _: AttrSeqManString         => addSeq(ns, attr, refNs, tplIndex, transformString, extsString, seq2arrayString, value2jsonString)
      case _: AttrSeqManInt            => addSeq(ns, attr, refNs, tplIndex, transformInt, extsInt, seq2arrayInt, value2jsonInt)
      case _: AttrSeqManLong           => addSeq(ns, attr, refNs, tplIndex, transformLong, extsLong, seq2arrayLong, value2jsonLong)
      case _: AttrSeqManFloat          => addSeq(ns, attr, refNs, tplIndex, transformFloat, extsFloat, seq2arrayFloat, value2jsonFloat)
      case _: AttrSeqManDouble         => addSeq(ns, attr, refNs, tplIndex, transformDouble, extsDouble, seq2arrayDouble, value2jsonDouble)
      case _: AttrSeqManBoolean        => addSeq(ns, attr, refNs, tplIndex, transformBoolean, extsBoolean, seq2arrayBoolean, value2jsonBoolean)
      case _: AttrSeqManBigInt         => addSeq(ns, attr, refNs, tplIndex, transformBigInt, extsBigInt, seq2arrayBigInt, value2jsonBigInt)
      case _: AttrSeqManBigDecimal     => addSeq(ns, attr, refNs, tplIndex, transformBigDecimal, extsBigDecimal, seq2arrayBigDecimal, value2jsonBigDecimal)
      case _: AttrSeqManDate           => addSeq(ns, attr, refNs, tplIndex, transformDate, extsDate, seq2arrayDate, value2jsonDate)
      case _: AttrSeqManDuration       => addSeq(ns, attr, refNs, tplIndex, transformDuration, extsDuration, seq2arrayDuration, value2jsonDuration)
      case _: AttrSeqManInstant        => addSeq(ns, attr, refNs, tplIndex, transformInstant, extsInstant, seq2arrayInstant, value2jsonInstant)
      case _: AttrSeqManLocalDate      => addSeq(ns, attr, refNs, tplIndex, transformLocalDate, extsLocalDate, seq2arrayLocalDate, value2jsonLocalDate)
      case _: AttrSeqManLocalTime      => addSeq(ns, attr, refNs, tplIndex, transformLocalTime, extsLocalTime, seq2arrayLocalTime, value2jsonLocalTime)
      case _: AttrSeqManLocalDateTime  => addSeq(ns, attr, refNs, tplIndex, transformLocalDateTime, extsLocalDateTime, seq2arrayLocalDateTime, value2jsonLocalDateTime)
      case _: AttrSeqManOffsetTime     => addSeq(ns, attr, refNs, tplIndex, transformOffsetTime, extsOffsetTime, seq2arrayOffsetTime, value2jsonOffsetTime)
      case _: AttrSeqManOffsetDateTime => addSeq(ns, attr, refNs, tplIndex, transformOffsetDateTime, extsOffsetDateTime, seq2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case _: AttrSeqManZonedDateTime  => addSeq(ns, attr, refNs, tplIndex, transformZonedDateTime, extsZonedDateTime, seq2arrayZonedDateTime, value2jsonZonedDateTime)
      case _: AttrSeqManUUID           => addSeq(ns, attr, refNs, tplIndex, transformUUID, extsUUID, seq2arrayUUID, value2jsonUUID)
      case _: AttrSeqManURI            => addSeq(ns, attr, refNs, tplIndex, transformURI, extsURI, seq2arrayURI, value2jsonURI)
      case a: AttrSeqManByte           => addByteArray(ns, attr, tplIndex)
      case _: AttrSeqManShort          => addSeq(ns, attr, refNs, tplIndex, transformShort, extsShort, seq2arrayShort, value2jsonShort)
      case _: AttrSeqManChar           => addSeq(ns, attr, refNs, tplIndex, transformChar, extsChar, seq2arrayChar, value2jsonChar)
    }
  }

  private def resolveAttrSeqOpt(a: AttrSeqOpt, tplIndex: Int): Product => Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case _: AttrSeqOptID             => addSeqOpt(ns, attr, refNs, tplIndex, transformID, extsID, seq2arrayID, value2jsonID)
      case _: AttrSeqOptString         => addSeqOpt(ns, attr, refNs, tplIndex, transformString, extsString, seq2arrayString, value2jsonString)
      case _: AttrSeqOptInt            => addSeqOpt(ns, attr, refNs, tplIndex, transformInt, extsInt, seq2arrayInt, value2jsonInt)
      case _: AttrSeqOptLong           => addSeqOpt(ns, attr, refNs, tplIndex, transformLong, extsLong, seq2arrayLong, value2jsonLong)
      case _: AttrSeqOptFloat          => addSeqOpt(ns, attr, refNs, tplIndex, transformFloat, extsFloat, seq2arrayFloat, value2jsonFloat)
      case _: AttrSeqOptDouble         => addSeqOpt(ns, attr, refNs, tplIndex, transformDouble, extsDouble, seq2arrayDouble, value2jsonDouble)
      case _: AttrSeqOptBoolean        => addSeqOpt(ns, attr, refNs, tplIndex, transformBoolean, extsBoolean, seq2arrayBoolean, value2jsonBoolean)
      case _: AttrSeqOptBigInt         => addSeqOpt(ns, attr, refNs, tplIndex, transformBigInt, extsBigInt, seq2arrayBigInt, value2jsonBigInt)
      case _: AttrSeqOptBigDecimal     => addSeqOpt(ns, attr, refNs, tplIndex, transformBigDecimal, extsBigDecimal, seq2arrayBigDecimal, value2jsonBigDecimal)
      case _: AttrSeqOptDate           => addSeqOpt(ns, attr, refNs, tplIndex, transformDate, extsDate, seq2arrayDate, value2jsonDate)
      case _: AttrSeqOptDuration       => addSeqOpt(ns, attr, refNs, tplIndex, transformDuration, extsDuration, seq2arrayDuration, value2jsonDuration)
      case _: AttrSeqOptInstant        => addSeqOpt(ns, attr, refNs, tplIndex, transformInstant, extsInstant, seq2arrayInstant, value2jsonInstant)
      case _: AttrSeqOptLocalDate      => addSeqOpt(ns, attr, refNs, tplIndex, transformLocalDate, extsLocalDate, seq2arrayLocalDate, value2jsonLocalDate)
      case _: AttrSeqOptLocalTime      => addSeqOpt(ns, attr, refNs, tplIndex, transformLocalTime, extsLocalTime, seq2arrayLocalTime, value2jsonLocalTime)
      case _: AttrSeqOptLocalDateTime  => addSeqOpt(ns, attr, refNs, tplIndex, transformLocalDateTime, extsLocalDateTime, seq2arrayLocalDateTime, value2jsonLocalDateTime)
      case _: AttrSeqOptOffsetTime     => addSeqOpt(ns, attr, refNs, tplIndex, transformOffsetTime, extsOffsetTime, seq2arrayOffsetTime, value2jsonOffsetTime)
      case _: AttrSeqOptOffsetDateTime => addSeqOpt(ns, attr, refNs, tplIndex, transformOffsetDateTime, extsOffsetDateTime, seq2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case _: AttrSeqOptZonedDateTime  => addSeqOpt(ns, attr, refNs, tplIndex, transformZonedDateTime, extsZonedDateTime, seq2arrayZonedDateTime, value2jsonZonedDateTime)
      case _: AttrSeqOptUUID           => addSeqOpt(ns, attr, refNs, tplIndex, transformUUID, extsUUID, seq2arrayUUID, value2jsonUUID)
      case _: AttrSeqOptURI            => addSeqOpt(ns, attr, refNs, tplIndex, transformURI, extsURI, seq2arrayURI, value2jsonURI)
      case a: AttrSeqOptByte           => addByteArray(ns, attr, tplIndex)
      case _: AttrSeqOptShort          => addSeqOpt(ns, attr, refNs, tplIndex, transformShort, extsShort, seq2arrayShort, value2jsonShort)
      case _: AttrSeqOptChar           => addSeqOpt(ns, attr, refNs, tplIndex, transformChar, extsChar, seq2arrayChar, value2jsonChar)
    }
  }

  private def resolveAttrMapMan(a: AttrMapMan, tplIndex: Int): Product => Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case _: AttrMapManID             => addMap(ns, attr, refNs, tplIndex, transformID, value2jsonID)
      case _: AttrMapManString         => addMap(ns, attr, refNs, tplIndex, transformString, value2jsonString)
      case _: AttrMapManInt            => addMap(ns, attr, refNs, tplIndex, transformInt, value2jsonInt)
      case _: AttrMapManLong           => addMap(ns, attr, refNs, tplIndex, transformLong, value2jsonLong)
      case _: AttrMapManFloat          => addMap(ns, attr, refNs, tplIndex, transformFloat, value2jsonFloat)
      case _: AttrMapManDouble         => addMap(ns, attr, refNs, tplIndex, transformDouble, value2jsonDouble)
      case _: AttrMapManBoolean        => addMap(ns, attr, refNs, tplIndex, transformBoolean, value2jsonBoolean)
      case _: AttrMapManBigInt         => addMap(ns, attr, refNs, tplIndex, transformBigInt, value2jsonBigInt)
      case _: AttrMapManBigDecimal     => addMap(ns, attr, refNs, tplIndex, transformBigDecimal, value2jsonBigDecimal)
      case _: AttrMapManDate           => addMap(ns, attr, refNs, tplIndex, transformDate, value2jsonDate)
      case _: AttrMapManDuration       => addMap(ns, attr, refNs, tplIndex, transformDuration, value2jsonDuration)
      case _: AttrMapManInstant        => addMap(ns, attr, refNs, tplIndex, transformInstant, value2jsonInstant)
      case _: AttrMapManLocalDate      => addMap(ns, attr, refNs, tplIndex, transformLocalDate, value2jsonLocalDate)
      case _: AttrMapManLocalTime      => addMap(ns, attr, refNs, tplIndex, transformLocalTime, value2jsonLocalTime)
      case _: AttrMapManLocalDateTime  => addMap(ns, attr, refNs, tplIndex, transformLocalDateTime, value2jsonLocalDateTime)
      case _: AttrMapManOffsetTime     => addMap(ns, attr, refNs, tplIndex, transformOffsetTime, value2jsonOffsetTime)
      case _: AttrMapManOffsetDateTime => addMap(ns, attr, refNs, tplIndex, transformOffsetDateTime, value2jsonOffsetDateTime)
      case _: AttrMapManZonedDateTime  => addMap(ns, attr, refNs, tplIndex, transformZonedDateTime, value2jsonZonedDateTime)
      case _: AttrMapManUUID           => addMap(ns, attr, refNs, tplIndex, transformUUID, value2jsonUUID)
      case _: AttrMapManURI            => addMap(ns, attr, refNs, tplIndex, transformURI, value2jsonURI)
      case _: AttrMapManByte           => addMap(ns, attr, refNs, tplIndex, transformByte, value2jsonByte)
      case _: AttrMapManShort          => addMap(ns, attr, refNs, tplIndex, transformShort, value2jsonShort)
      case _: AttrMapManChar           => addMap(ns, attr, refNs, tplIndex, transformChar, value2jsonChar)
    }
  }

  private def resolveAttrMapOpt(a: AttrMapOpt, tplIndex: Int): Product => Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case _: AttrMapOptID             => addMapOpt(ns, attr, refNs, tplIndex, transformID, value2jsonID)
      case _: AttrMapOptString         => addMapOpt(ns, attr, refNs, tplIndex, transformString, value2jsonString)
      case _: AttrMapOptInt            => addMapOpt(ns, attr, refNs, tplIndex, transformInt, value2jsonInt)
      case _: AttrMapOptLong           => addMapOpt(ns, attr, refNs, tplIndex, transformLong, value2jsonLong)
      case _: AttrMapOptFloat          => addMapOpt(ns, attr, refNs, tplIndex, transformFloat, value2jsonFloat)
      case _: AttrMapOptDouble         => addMapOpt(ns, attr, refNs, tplIndex, transformDouble, value2jsonDouble)
      case _: AttrMapOptBoolean        => addMapOpt(ns, attr, refNs, tplIndex, transformBoolean, value2jsonBoolean)
      case _: AttrMapOptBigInt         => addMapOpt(ns, attr, refNs, tplIndex, transformBigInt, value2jsonBigInt)
      case _: AttrMapOptBigDecimal     => addMapOpt(ns, attr, refNs, tplIndex, transformBigDecimal, value2jsonBigDecimal)
      case _: AttrMapOptDate           => addMapOpt(ns, attr, refNs, tplIndex, transformDate, value2jsonDate)
      case _: AttrMapOptDuration       => addMapOpt(ns, attr, refNs, tplIndex, transformDuration, value2jsonDuration)
      case _: AttrMapOptInstant        => addMapOpt(ns, attr, refNs, tplIndex, transformInstant, value2jsonInstant)
      case _: AttrMapOptLocalDate      => addMapOpt(ns, attr, refNs, tplIndex, transformLocalDate, value2jsonLocalDate)
      case _: AttrMapOptLocalTime      => addMapOpt(ns, attr, refNs, tplIndex, transformLocalTime, value2jsonLocalTime)
      case _: AttrMapOptLocalDateTime  => addMapOpt(ns, attr, refNs, tplIndex, transformLocalDateTime, value2jsonLocalDateTime)
      case _: AttrMapOptOffsetTime     => addMapOpt(ns, attr, refNs, tplIndex, transformOffsetTime, value2jsonOffsetTime)
      case _: AttrMapOptOffsetDateTime => addMapOpt(ns, attr, refNs, tplIndex, transformOffsetDateTime, value2jsonOffsetDateTime)
      case _: AttrMapOptZonedDateTime  => addMapOpt(ns, attr, refNs, tplIndex, transformZonedDateTime, value2jsonZonedDateTime)
      case _: AttrMapOptUUID           => addMapOpt(ns, attr, refNs, tplIndex, transformUUID, value2jsonUUID)
      case _: AttrMapOptURI            => addMapOpt(ns, attr, refNs, tplIndex, transformURI, value2jsonURI)
      case _: AttrMapOptByte           => addMapOpt(ns, attr, refNs, tplIndex, transformByte, value2jsonByte)
      case _: AttrMapOptShort          => addMapOpt(ns, attr, refNs, tplIndex, transformShort, value2jsonShort)
      case _: AttrMapOptChar           => addMapOpt(ns, attr, refNs, tplIndex, transformChar, value2jsonChar)
    }
  }
}