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
            case a: AttrOne =>
              a match {
                case a: AttrOneMan =>
                  val attrOneManResolver = resolveAttrOneMan(a, tplIndex)
                  resolve(nsMap, tail, resolvers :+ attrOneManResolver, tplIndex + 1)

                case a: AttrOneOpt =>
                  val attrOneOptResolver = resolveAttrOneOpt(a, tplIndex)
                  resolve(nsMap, tail, resolvers :+ attrOneOptResolver, tplIndex + 1)

                case a: AttrOneTac => throw new Exception(
                  "Can't use tacit attributes in insert molecule (except in tx meta data part). Found: " + a
                )
              }
            case a: AttrSet =>
              a match {
                case a: AttrSetMan =>
                  val attrsSetManResolver = resolveAttrSetMan(a, tplIndex)
                  resolve(nsMap, tail, resolvers :+ attrsSetManResolver, tplIndex + 1)

                case a: AttrSetOpt =>
                  val attrSetOptResolver = resolveAttrSetOpt(a, tplIndex)
                  resolve(nsMap, tail, resolvers :+ attrSetOptResolver, tplIndex + 1)

                case a: AttrSetTac => throw new Exception(
                  "Can't use tacit attributes in insert molecule (except in tx meta data part). Found: " + a
                )
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
}