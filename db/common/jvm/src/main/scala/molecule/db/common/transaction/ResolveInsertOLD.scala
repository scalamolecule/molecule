package molecule.db.common.transaction

import scala.annotation.tailrec
import molecule.core.dataModel.*
import molecule.core.error.ModelError
import molecule.db.common.validation.insert.InsertValidators_

trait ResolveInsertOLD extends InsertResolvers with InsertValidators_ { self: SqlInsertOLD =>

  @tailrec
  final override def resolve(
    elements: List[Element],
    resolvers: List[Product => Unit],
    tplIndex: Int,
    prevRefs: List[String]
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
                resolve(tail, resolvers :+ attrOneManResolver, tplIndex + 1, prevRefs)

              case a: AttrOneOpt =>
                val attrOneOptResolver = resolveAttrOneOpt(a, tplIndex)
                resolve(tail, resolvers :+ attrOneOptResolver, tplIndex + 1, prevRefs)

              case a => noEmpty(a)
            }
            case a: AttrSet => a match {
              case a: AttrSetMan =>
                val attrsSetManResolver = resolveAttrSetMan(a, tplIndex)
                resolve(tail, resolvers :+ attrsSetManResolver, tplIndex + 1, prevRefs)

              case a: AttrSetOpt =>
                val attrSetOptResolver = resolveAttrSetOpt(a, tplIndex)
                resolve(tail, resolvers :+ attrSetOptResolver, tplIndex + 1, prevRefs)

              case a => noEmpty(a)
            }

            case a: AttrSeq => a match {
              case a: AttrSeqMan =>
                val attrsSetManResolver = resolveAttrSeqMan(a, tplIndex)
                resolve(tail, resolvers :+ attrsSetManResolver, tplIndex + 1, prevRefs)

              case a: AttrSeqOpt =>
                val attrSetOptResolver = resolveAttrSeqOpt(a, tplIndex)
                resolve(tail, resolvers :+ attrSetOptResolver, tplIndex + 1, prevRefs)

              case a => noEmpty(a)
            }

            case a: AttrMap => a match {
              case a: AttrMapMan =>
                val attrsSetManResolver = resolveAttrMapMan(a, tplIndex)
                resolve(tail, resolvers :+ attrsSetManResolver, tplIndex + 1, prevRefs)

              case a: AttrMapOpt =>
                val attrSetOptResolver = resolveAttrMapOpt(a, tplIndex)
                resolve(tail, resolvers :+ attrSetOptResolver, tplIndex + 1, prevRefs)

              case a => noEmpty(a)
            }
          }

        case Ref(ent, refAttr, ref, relationship, _, _, reverseRefAttr) =>
          val refResolver = addRef(ent, refAttr, ref, relationship, reverseRefAttr)
          resolve(tail, resolvers :+ refResolver, tplIndex, prevRefs :+ refAttr)

        case BackRef(backRef, _, _) =>
          noEntityReUseAfterBackref(tail.head, prevRefs, backRef)
          val backRefResolver = addBackRef(backRef)
          resolve(tail, resolvers :+ backRefResolver, tplIndex, Nil)

        case OptRef(Ref(ent, refAttr, ref, _, _, _, _), optRefElements) =>
          val optRefResolver = addOptRef(tplIndex, ent, refAttr, ref, optRefElements)
          resolve(tail, resolvers :+ optRefResolver, tplIndex + 1, Nil)

        case OptEntity(attrs) =>
          val optEntityResolver = addOptEntity(attrs)
          resolve(tail, resolvers :+ optEntityResolver, tplIndex + 1, Nil)

        case Nested(Ref(ent, refAttr, ref, relationship, _, _, revRefAttr), nestedElements) =>
          val nestedResolver = addNested(tplIndex, ent, refAttr, ref, relationship, nestedElements, revRefAttr.get)
          resolve(tail, resolvers :+ nestedResolver, tplIndex, Nil)

        case OptNested(Ref(ent, refAttr, ref, relationship, _, _, revRefAttr), nestedElements) =>
          // (same behavior as mandatory nested - the list can have data or not)
          val optNestedResolver = addNested(tplIndex, ent, refAttr, ref, relationship, nestedElements, revRefAttr.get)
          resolve(tail, resolvers :+ optNestedResolver, tplIndex, Nil)
      }
      case Nil             => resolvers
    }
  }

  private def noEmpty(a: Attr) = throw new Exception(
    s"Can't use tacit attributes in insert molecule (${a.name})."
  )


  private def resolveAttrOneMan(a: AttrOneMan, tplIndex: Int): Product => Unit = {
    val (ent, attr) = (a.ent, a.attr)
    a match {
      case _: AttrOneManID             => addOne(ent, attr, tplIndex, transformID, extsID)
      case _: AttrOneManString         => addOne(ent, attr, tplIndex, transformString, extsString)
      case _: AttrOneManInt            => addOne(ent, attr, tplIndex, transformInt, extsInt)
      case _: AttrOneManLong           => addOne(ent, attr, tplIndex, transformLong, extsLong)
      case _: AttrOneManFloat          => addOne(ent, attr, tplIndex, transformFloat, extsFloat)
      case _: AttrOneManDouble         => addOne(ent, attr, tplIndex, transformDouble, extsDouble)
      case _: AttrOneManBoolean        => addOne(ent, attr, tplIndex, transformBoolean, extsBoolean)
      case _: AttrOneManBigInt         => addOne(ent, attr, tplIndex, transformBigInt, extsBigInt)
      case _: AttrOneManBigDecimal     => addOne(ent, attr, tplIndex, transformBigDecimal, extsBigDecimal)
      case _: AttrOneManDate           => addOne(ent, attr, tplIndex, transformDate, extsDate)
      case _: AttrOneManDuration       => addOne(ent, attr, tplIndex, transformDuration, extsDuration)
      case _: AttrOneManInstant        => addOne(ent, attr, tplIndex, transformInstant, extsInstant)
      case _: AttrOneManLocalDate      => addOne(ent, attr, tplIndex, transformLocalDate, extsLocalDate)
      case _: AttrOneManLocalTime      => addOne(ent, attr, tplIndex, transformLocalTime, extsLocalTime)
      case _: AttrOneManLocalDateTime  => addOne(ent, attr, tplIndex, transformLocalDateTime, extsLocalDateTime)
      case _: AttrOneManOffsetTime     => addOne(ent, attr, tplIndex, transformOffsetTime, extsOffsetTime)
      case _: AttrOneManOffsetDateTime => addOne(ent, attr, tplIndex, transformOffsetDateTime, extsOffsetDateTime)
      case _: AttrOneManZonedDateTime  => addOne(ent, attr, tplIndex, transformZonedDateTime, extsZonedDateTime)
      case _: AttrOneManUUID           => addOne(ent, attr, tplIndex, transformUUID, extsUUID)
      case _: AttrOneManURI            => addOne(ent, attr, tplIndex, transformURI, extsURI)
      case _: AttrOneManByte           => addOne(ent, attr, tplIndex, transformByte, extsByte)
      case _: AttrOneManShort          => addOne(ent, attr, tplIndex, transformShort, extsShort)
      case _: AttrOneManChar           => addOne(ent, attr, tplIndex, transformChar, extsChar)
    }
  }

  private def resolveAttrOneOpt(a: AttrOneOpt, tplIndex: Int): Product => Unit = {
    val (ent, attr) = (a.ent, a.attr)
    a match {
      case _: AttrOneOptID             => addOneOpt(ent, attr, tplIndex, transformID, extsID)
      case _: AttrOneOptString         => addOneOpt(ent, attr, tplIndex, transformString, extsString)
      case _: AttrOneOptInt            => addOneOpt(ent, attr, tplIndex, transformInt, extsInt)
      case _: AttrOneOptLong           => addOneOpt(ent, attr, tplIndex, transformLong, extsLong)
      case _: AttrOneOptFloat          => addOneOpt(ent, attr, tplIndex, transformFloat, extsFloat)
      case _: AttrOneOptDouble         => addOneOpt(ent, attr, tplIndex, transformDouble, extsDouble)
      case _: AttrOneOptBoolean        => addOneOpt(ent, attr, tplIndex, transformBoolean, extsBoolean)
      case _: AttrOneOptBigInt         => addOneOpt(ent, attr, tplIndex, transformBigInt, extsBigInt)
      case _: AttrOneOptBigDecimal     => addOneOpt(ent, attr, tplIndex, transformBigDecimal, extsBigDecimal)
      case _: AttrOneOptDate           => addOneOpt(ent, attr, tplIndex, transformDate, extsDate)
      case _: AttrOneOptDuration       => addOneOpt(ent, attr, tplIndex, transformDuration, extsDuration)
      case _: AttrOneOptInstant        => addOneOpt(ent, attr, tplIndex, transformInstant, extsInstant)
      case _: AttrOneOptLocalDate      => addOneOpt(ent, attr, tplIndex, transformLocalDate, extsLocalDate)
      case _: AttrOneOptLocalTime      => addOneOpt(ent, attr, tplIndex, transformLocalTime, extsLocalTime)
      case _: AttrOneOptLocalDateTime  => addOneOpt(ent, attr, tplIndex, transformLocalDateTime, extsLocalDateTime)
      case _: AttrOneOptOffsetTime     => addOneOpt(ent, attr, tplIndex, transformOffsetTime, extsOffsetTime)
      case _: AttrOneOptOffsetDateTime => addOneOpt(ent, attr, tplIndex, transformOffsetDateTime, extsOffsetDateTime)
      case _: AttrOneOptZonedDateTime  => addOneOpt(ent, attr, tplIndex, transformZonedDateTime, extsZonedDateTime)
      case _: AttrOneOptUUID           => addOneOpt(ent, attr, tplIndex, transformUUID, extsUUID)
      case _: AttrOneOptURI            => addOneOpt(ent, attr, tplIndex, transformURI, extsURI)
      case _: AttrOneOptByte           => addOneOpt(ent, attr, tplIndex, transformByte, extsByte)
      case _: AttrOneOptShort          => addOneOpt(ent, attr, tplIndex, transformShort, extsShort)
      case _: AttrOneOptChar           => addOneOpt(ent, attr, tplIndex, transformChar, extsChar)
    }
  }

  private def resolveAttrSetMan(a: AttrSetMan, tplIndex: Int): Product => Unit = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case _: AttrSetManID             => addSet(ent, attr, ref, tplIndex, transformID, extsID, set2arrayID, value2jsonID)
      case _: AttrSetManString         => addSet(ent, attr, ref, tplIndex, transformString, extsString, set2arrayString, value2jsonString)
      case _: AttrSetManInt            => addSet(ent, attr, ref, tplIndex, transformInt, extsInt, set2arrayInt, value2jsonInt)
      case _: AttrSetManLong           => addSet(ent, attr, ref, tplIndex, transformLong, extsLong, set2arrayLong, value2jsonLong)
      case _: AttrSetManFloat          => addSet(ent, attr, ref, tplIndex, transformFloat, extsFloat, set2arrayFloat, value2jsonFloat)
      case _: AttrSetManDouble         => addSet(ent, attr, ref, tplIndex, transformDouble, extsDouble, set2arrayDouble, value2jsonDouble)
      case _: AttrSetManBoolean        => addSet(ent, attr, ref, tplIndex, transformBoolean, extsBoolean, set2arrayBoolean, value2jsonBoolean)
      case _: AttrSetManBigInt         => addSet(ent, attr, ref, tplIndex, transformBigInt, extsBigInt, set2arrayBigInt, value2jsonBigInt)
      case _: AttrSetManBigDecimal     => addSet(ent, attr, ref, tplIndex, transformBigDecimal, extsBigDecimal, set2arrayBigDecimal, value2jsonBigDecimal)
      case _: AttrSetManDate           => addSet(ent, attr, ref, tplIndex, transformDate, extsDate, set2arrayDate, value2jsonDate)
      case _: AttrSetManDuration       => addSet(ent, attr, ref, tplIndex, transformDuration, extsDuration, set2arrayDuration, value2jsonDuration)
      case _: AttrSetManInstant        => addSet(ent, attr, ref, tplIndex, transformInstant, extsInstant, set2arrayInstant, value2jsonInstant)
      case _: AttrSetManLocalDate      => addSet(ent, attr, ref, tplIndex, transformLocalDate, extsLocalDate, set2arrayLocalDate, value2jsonLocalDate)
      case _: AttrSetManLocalTime      => addSet(ent, attr, ref, tplIndex, transformLocalTime, extsLocalTime, set2arrayLocalTime, value2jsonLocalTime)
      case _: AttrSetManLocalDateTime  => addSet(ent, attr, ref, tplIndex, transformLocalDateTime, extsLocalDateTime, set2arrayLocalDateTime, value2jsonLocalDateTime)
      case _: AttrSetManOffsetTime     => addSet(ent, attr, ref, tplIndex, transformOffsetTime, extsOffsetTime, set2arrayOffsetTime, value2jsonOffsetTime)
      case _: AttrSetManOffsetDateTime => addSet(ent, attr, ref, tplIndex, transformOffsetDateTime, extsOffsetDateTime, set2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case _: AttrSetManZonedDateTime  => addSet(ent, attr, ref, tplIndex, transformZonedDateTime, extsZonedDateTime, set2arrayZonedDateTime, value2jsonZonedDateTime)
      case _: AttrSetManUUID           => addSet(ent, attr, ref, tplIndex, transformUUID, extsUUID, set2arrayUUID, value2jsonUUID)
      case _: AttrSetManURI            => addSet(ent, attr, ref, tplIndex, transformURI, extsURI, set2arrayURI, value2jsonURI)
      case _: AttrSetManByte           => addSet(ent, attr, ref, tplIndex, transformByte, extsByte, set2arrayByte, value2jsonByte)
      case _: AttrSetManShort          => addSet(ent, attr, ref, tplIndex, transformShort, extsShort, set2arrayShort, value2jsonShort)
      case _: AttrSetManChar           => addSet(ent, attr, ref, tplIndex, transformChar, extsChar, set2arrayChar, value2jsonChar)
    }
  }

  private def resolveAttrSetOpt(a: AttrSetOpt, tplIndex: Int): Product => Unit = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case _: AttrSetOptID             => addSetOpt(ent, attr, ref, tplIndex, transformID, extsID, set2arrayID, value2jsonID)
      case _: AttrSetOptString         => addSetOpt(ent, attr, ref, tplIndex, transformString, extsString, set2arrayString, value2jsonString)
      case _: AttrSetOptInt            => addSetOpt(ent, attr, ref, tplIndex, transformInt, extsInt, set2arrayInt, value2jsonInt)
      case _: AttrSetOptLong           => addSetOpt(ent, attr, ref, tplIndex, transformLong, extsLong, set2arrayLong, value2jsonLong)
      case _: AttrSetOptFloat          => addSetOpt(ent, attr, ref, tplIndex, transformFloat, extsFloat, set2arrayFloat, value2jsonFloat)
      case _: AttrSetOptDouble         => addSetOpt(ent, attr, ref, tplIndex, transformDouble, extsDouble, set2arrayDouble, value2jsonDouble)
      case _: AttrSetOptBoolean        => addSetOpt(ent, attr, ref, tplIndex, transformBoolean, extsBoolean, set2arrayBoolean, value2jsonBoolean)
      case _: AttrSetOptBigInt         => addSetOpt(ent, attr, ref, tplIndex, transformBigInt, extsBigInt, set2arrayBigInt, value2jsonBigInt)
      case _: AttrSetOptBigDecimal     => addSetOpt(ent, attr, ref, tplIndex, transformBigDecimal, extsBigDecimal, set2arrayBigDecimal, value2jsonBigDecimal)
      case _: AttrSetOptDate           => addSetOpt(ent, attr, ref, tplIndex, transformDate, extsDate, set2arrayDate, value2jsonDate)
      case _: AttrSetOptDuration       => addSetOpt(ent, attr, ref, tplIndex, transformDuration, extsDuration, set2arrayDuration, value2jsonDuration)
      case _: AttrSetOptInstant        => addSetOpt(ent, attr, ref, tplIndex, transformInstant, extsInstant, set2arrayInstant, value2jsonInstant)
      case _: AttrSetOptLocalDate      => addSetOpt(ent, attr, ref, tplIndex, transformLocalDate, extsLocalDate, set2arrayLocalDate, value2jsonLocalDate)
      case _: AttrSetOptLocalTime      => addSetOpt(ent, attr, ref, tplIndex, transformLocalTime, extsLocalTime, set2arrayLocalTime, value2jsonLocalTime)
      case _: AttrSetOptLocalDateTime  => addSetOpt(ent, attr, ref, tplIndex, transformLocalDateTime, extsLocalDateTime, set2arrayLocalDateTime, value2jsonLocalDateTime)
      case _: AttrSetOptOffsetTime     => addSetOpt(ent, attr, ref, tplIndex, transformOffsetTime, extsOffsetTime, set2arrayOffsetTime, value2jsonOffsetTime)
      case _: AttrSetOptOffsetDateTime => addSetOpt(ent, attr, ref, tplIndex, transformOffsetDateTime, extsOffsetDateTime, set2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case _: AttrSetOptZonedDateTime  => addSetOpt(ent, attr, ref, tplIndex, transformZonedDateTime, extsZonedDateTime, set2arrayZonedDateTime, value2jsonZonedDateTime)
      case _: AttrSetOptUUID           => addSetOpt(ent, attr, ref, tplIndex, transformUUID, extsUUID, set2arrayUUID, value2jsonUUID)
      case _: AttrSetOptURI            => addSetOpt(ent, attr, ref, tplIndex, transformURI, extsURI, set2arrayURI, value2jsonURI)
      case _: AttrSetOptByte           => addSetOpt(ent, attr, ref, tplIndex, transformByte, extsByte, set2arrayByte, value2jsonByte)
      case _: AttrSetOptShort          => addSetOpt(ent, attr, ref, tplIndex, transformShort, extsShort, set2arrayShort, value2jsonShort)
      case _: AttrSetOptChar           => addSetOpt(ent, attr, ref, tplIndex, transformChar, extsChar, set2arrayChar, value2jsonChar)
    }
  }

  private def resolveAttrSeqMan(a: AttrSeqMan, tplIndex: Int): Product => Unit = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case _: AttrSeqManID             => addSeq(ent, attr, ref, tplIndex, transformID, extsID, seq2arrayID, value2jsonID)
      case _: AttrSeqManString         => addSeq(ent, attr, ref, tplIndex, transformString, extsString, seq2arrayString, value2jsonString)
      case _: AttrSeqManInt            => addSeq(ent, attr, ref, tplIndex, transformInt, extsInt, seq2arrayInt, value2jsonInt)
      case _: AttrSeqManLong           => addSeq(ent, attr, ref, tplIndex, transformLong, extsLong, seq2arrayLong, value2jsonLong)
      case _: AttrSeqManFloat          => addSeq(ent, attr, ref, tplIndex, transformFloat, extsFloat, seq2arrayFloat, value2jsonFloat)
      case _: AttrSeqManDouble         => addSeq(ent, attr, ref, tplIndex, transformDouble, extsDouble, seq2arrayDouble, value2jsonDouble)
      case _: AttrSeqManBoolean        => addSeq(ent, attr, ref, tplIndex, transformBoolean, extsBoolean, seq2arrayBoolean, value2jsonBoolean)
      case _: AttrSeqManBigInt         => addSeq(ent, attr, ref, tplIndex, transformBigInt, extsBigInt, seq2arrayBigInt, value2jsonBigInt)
      case _: AttrSeqManBigDecimal     => addSeq(ent, attr, ref, tplIndex, transformBigDecimal, extsBigDecimal, seq2arrayBigDecimal, value2jsonBigDecimal)
      case _: AttrSeqManDate           => addSeq(ent, attr, ref, tplIndex, transformDate, extsDate, seq2arrayDate, value2jsonDate)
      case _: AttrSeqManDuration       => addSeq(ent, attr, ref, tplIndex, transformDuration, extsDuration, seq2arrayDuration, value2jsonDuration)
      case _: AttrSeqManInstant        => addSeq(ent, attr, ref, tplIndex, transformInstant, extsInstant, seq2arrayInstant, value2jsonInstant)
      case _: AttrSeqManLocalDate      => addSeq(ent, attr, ref, tplIndex, transformLocalDate, extsLocalDate, seq2arrayLocalDate, value2jsonLocalDate)
      case _: AttrSeqManLocalTime      => addSeq(ent, attr, ref, tplIndex, transformLocalTime, extsLocalTime, seq2arrayLocalTime, value2jsonLocalTime)
      case _: AttrSeqManLocalDateTime  => addSeq(ent, attr, ref, tplIndex, transformLocalDateTime, extsLocalDateTime, seq2arrayLocalDateTime, value2jsonLocalDateTime)
      case _: AttrSeqManOffsetTime     => addSeq(ent, attr, ref, tplIndex, transformOffsetTime, extsOffsetTime, seq2arrayOffsetTime, value2jsonOffsetTime)
      case _: AttrSeqManOffsetDateTime => addSeq(ent, attr, ref, tplIndex, transformOffsetDateTime, extsOffsetDateTime, seq2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case _: AttrSeqManZonedDateTime  => addSeq(ent, attr, ref, tplIndex, transformZonedDateTime, extsZonedDateTime, seq2arrayZonedDateTime, value2jsonZonedDateTime)
      case _: AttrSeqManUUID           => addSeq(ent, attr, ref, tplIndex, transformUUID, extsUUID, seq2arrayUUID, value2jsonUUID)
      case _: AttrSeqManURI            => addSeq(ent, attr, ref, tplIndex, transformURI, extsURI, seq2arrayURI, value2jsonURI)
      case a: AttrSeqManByte           => addByteArray(ent, attr, tplIndex)
      case _: AttrSeqManShort          => addSeq(ent, attr, ref, tplIndex, transformShort, extsShort, seq2arrayShort, value2jsonShort)
      case _: AttrSeqManChar           => addSeq(ent, attr, ref, tplIndex, transformChar, extsChar, seq2arrayChar, value2jsonChar)
    }
  }

  private def resolveAttrSeqOpt(a: AttrSeqOpt, tplIndex: Int): Product => Unit = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case _: AttrSeqOptID             => addSeqOpt(ent, attr, ref, tplIndex, transformID, extsID, seq2arrayID, value2jsonID)
      case _: AttrSeqOptString         => addSeqOpt(ent, attr, ref, tplIndex, transformString, extsString, seq2arrayString, value2jsonString)
      case _: AttrSeqOptInt            => addSeqOpt(ent, attr, ref, tplIndex, transformInt, extsInt, seq2arrayInt, value2jsonInt)
      case _: AttrSeqOptLong           => addSeqOpt(ent, attr, ref, tplIndex, transformLong, extsLong, seq2arrayLong, value2jsonLong)
      case _: AttrSeqOptFloat          => addSeqOpt(ent, attr, ref, tplIndex, transformFloat, extsFloat, seq2arrayFloat, value2jsonFloat)
      case _: AttrSeqOptDouble         => addSeqOpt(ent, attr, ref, tplIndex, transformDouble, extsDouble, seq2arrayDouble, value2jsonDouble)
      case _: AttrSeqOptBoolean        => addSeqOpt(ent, attr, ref, tplIndex, transformBoolean, extsBoolean, seq2arrayBoolean, value2jsonBoolean)
      case _: AttrSeqOptBigInt         => addSeqOpt(ent, attr, ref, tplIndex, transformBigInt, extsBigInt, seq2arrayBigInt, value2jsonBigInt)
      case _: AttrSeqOptBigDecimal     => addSeqOpt(ent, attr, ref, tplIndex, transformBigDecimal, extsBigDecimal, seq2arrayBigDecimal, value2jsonBigDecimal)
      case _: AttrSeqOptDate           => addSeqOpt(ent, attr, ref, tplIndex, transformDate, extsDate, seq2arrayDate, value2jsonDate)
      case _: AttrSeqOptDuration       => addSeqOpt(ent, attr, ref, tplIndex, transformDuration, extsDuration, seq2arrayDuration, value2jsonDuration)
      case _: AttrSeqOptInstant        => addSeqOpt(ent, attr, ref, tplIndex, transformInstant, extsInstant, seq2arrayInstant, value2jsonInstant)
      case _: AttrSeqOptLocalDate      => addSeqOpt(ent, attr, ref, tplIndex, transformLocalDate, extsLocalDate, seq2arrayLocalDate, value2jsonLocalDate)
      case _: AttrSeqOptLocalTime      => addSeqOpt(ent, attr, ref, tplIndex, transformLocalTime, extsLocalTime, seq2arrayLocalTime, value2jsonLocalTime)
      case _: AttrSeqOptLocalDateTime  => addSeqOpt(ent, attr, ref, tplIndex, transformLocalDateTime, extsLocalDateTime, seq2arrayLocalDateTime, value2jsonLocalDateTime)
      case _: AttrSeqOptOffsetTime     => addSeqOpt(ent, attr, ref, tplIndex, transformOffsetTime, extsOffsetTime, seq2arrayOffsetTime, value2jsonOffsetTime)
      case _: AttrSeqOptOffsetDateTime => addSeqOpt(ent, attr, ref, tplIndex, transformOffsetDateTime, extsOffsetDateTime, seq2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case _: AttrSeqOptZonedDateTime  => addSeqOpt(ent, attr, ref, tplIndex, transformZonedDateTime, extsZonedDateTime, seq2arrayZonedDateTime, value2jsonZonedDateTime)
      case _: AttrSeqOptUUID           => addSeqOpt(ent, attr, ref, tplIndex, transformUUID, extsUUID, seq2arrayUUID, value2jsonUUID)
      case _: AttrSeqOptURI            => addSeqOpt(ent, attr, ref, tplIndex, transformURI, extsURI, seq2arrayURI, value2jsonURI)
      case a: AttrSeqOptByte           => addByteArray(ent, attr, tplIndex)
      case _: AttrSeqOptShort          => addSeqOpt(ent, attr, ref, tplIndex, transformShort, extsShort, seq2arrayShort, value2jsonShort)
      case _: AttrSeqOptChar           => addSeqOpt(ent, attr, ref, tplIndex, transformChar, extsChar, seq2arrayChar, value2jsonChar)
    }
  }

  private def resolveAttrMapMan(a: AttrMapMan, tplIndex: Int): Product => Unit = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case _: AttrMapManID             => addMap(ent, attr, ref, tplIndex, transformID, value2jsonID)
      case _: AttrMapManString         => addMap(ent, attr, ref, tplIndex, transformString, value2jsonString)
      case _: AttrMapManInt            => addMap(ent, attr, ref, tplIndex, transformInt, value2jsonInt)
      case _: AttrMapManLong           => addMap(ent, attr, ref, tplIndex, transformLong, value2jsonLong)
      case _: AttrMapManFloat          => addMap(ent, attr, ref, tplIndex, transformFloat, value2jsonFloat)
      case _: AttrMapManDouble         => addMap(ent, attr, ref, tplIndex, transformDouble, value2jsonDouble)
      case _: AttrMapManBoolean        => addMap(ent, attr, ref, tplIndex, transformBoolean, value2jsonBoolean)
      case _: AttrMapManBigInt         => addMap(ent, attr, ref, tplIndex, transformBigInt, value2jsonBigInt)
      case _: AttrMapManBigDecimal     => addMap(ent, attr, ref, tplIndex, transformBigDecimal, value2jsonBigDecimal)
      case _: AttrMapManDate           => addMap(ent, attr, ref, tplIndex, transformDate, value2jsonDate)
      case _: AttrMapManDuration       => addMap(ent, attr, ref, tplIndex, transformDuration, value2jsonDuration)
      case _: AttrMapManInstant        => addMap(ent, attr, ref, tplIndex, transformInstant, value2jsonInstant)
      case _: AttrMapManLocalDate      => addMap(ent, attr, ref, tplIndex, transformLocalDate, value2jsonLocalDate)
      case _: AttrMapManLocalTime      => addMap(ent, attr, ref, tplIndex, transformLocalTime, value2jsonLocalTime)
      case _: AttrMapManLocalDateTime  => addMap(ent, attr, ref, tplIndex, transformLocalDateTime, value2jsonLocalDateTime)
      case _: AttrMapManOffsetTime     => addMap(ent, attr, ref, tplIndex, transformOffsetTime, value2jsonOffsetTime)
      case _: AttrMapManOffsetDateTime => addMap(ent, attr, ref, tplIndex, transformOffsetDateTime, value2jsonOffsetDateTime)
      case _: AttrMapManZonedDateTime  => addMap(ent, attr, ref, tplIndex, transformZonedDateTime, value2jsonZonedDateTime)
      case _: AttrMapManUUID           => addMap(ent, attr, ref, tplIndex, transformUUID, value2jsonUUID)
      case _: AttrMapManURI            => addMap(ent, attr, ref, tplIndex, transformURI, value2jsonURI)
      case _: AttrMapManByte           => addMap(ent, attr, ref, tplIndex, transformByte, value2jsonByte)
      case _: AttrMapManShort          => addMap(ent, attr, ref, tplIndex, transformShort, value2jsonShort)
      case _: AttrMapManChar           => addMap(ent, attr, ref, tplIndex, transformChar, value2jsonChar)
    }
  }

  private def resolveAttrMapOpt(a: AttrMapOpt, tplIndex: Int): Product => Unit = {
    val (ent, attr, ref) = (a.ent, a.attr, a.ref)
    a match {
      case _: AttrMapOptID             => addMapOpt(ent, attr, ref, tplIndex, transformID, value2jsonID)
      case _: AttrMapOptString         => addMapOpt(ent, attr, ref, tplIndex, transformString, value2jsonString)
      case _: AttrMapOptInt            => addMapOpt(ent, attr, ref, tplIndex, transformInt, value2jsonInt)
      case _: AttrMapOptLong           => addMapOpt(ent, attr, ref, tplIndex, transformLong, value2jsonLong)
      case _: AttrMapOptFloat          => addMapOpt(ent, attr, ref, tplIndex, transformFloat, value2jsonFloat)
      case _: AttrMapOptDouble         => addMapOpt(ent, attr, ref, tplIndex, transformDouble, value2jsonDouble)
      case _: AttrMapOptBoolean        => addMapOpt(ent, attr, ref, tplIndex, transformBoolean, value2jsonBoolean)
      case _: AttrMapOptBigInt         => addMapOpt(ent, attr, ref, tplIndex, transformBigInt, value2jsonBigInt)
      case _: AttrMapOptBigDecimal     => addMapOpt(ent, attr, ref, tplIndex, transformBigDecimal, value2jsonBigDecimal)
      case _: AttrMapOptDate           => addMapOpt(ent, attr, ref, tplIndex, transformDate, value2jsonDate)
      case _: AttrMapOptDuration       => addMapOpt(ent, attr, ref, tplIndex, transformDuration, value2jsonDuration)
      case _: AttrMapOptInstant        => addMapOpt(ent, attr, ref, tplIndex, transformInstant, value2jsonInstant)
      case _: AttrMapOptLocalDate      => addMapOpt(ent, attr, ref, tplIndex, transformLocalDate, value2jsonLocalDate)
      case _: AttrMapOptLocalTime      => addMapOpt(ent, attr, ref, tplIndex, transformLocalTime, value2jsonLocalTime)
      case _: AttrMapOptLocalDateTime  => addMapOpt(ent, attr, ref, tplIndex, transformLocalDateTime, value2jsonLocalDateTime)
      case _: AttrMapOptOffsetTime     => addMapOpt(ent, attr, ref, tplIndex, transformOffsetTime, value2jsonOffsetTime)
      case _: AttrMapOptOffsetDateTime => addMapOpt(ent, attr, ref, tplIndex, transformOffsetDateTime, value2jsonOffsetDateTime)
      case _: AttrMapOptZonedDateTime  => addMapOpt(ent, attr, ref, tplIndex, transformZonedDateTime, value2jsonZonedDateTime)
      case _: AttrMapOptUUID           => addMapOpt(ent, attr, ref, tplIndex, transformUUID, value2jsonUUID)
      case _: AttrMapOptURI            => addMapOpt(ent, attr, ref, tplIndex, transformURI, value2jsonURI)
      case _: AttrMapOptByte           => addMapOpt(ent, attr, ref, tplIndex, transformByte, value2jsonByte)
      case _: AttrMapOptShort          => addMapOpt(ent, attr, ref, tplIndex, transformShort, value2jsonShort)
      case _: AttrMapOptChar           => addMapOpt(ent, attr, ref, tplIndex, transformChar, value2jsonChar)
    }
  }
}