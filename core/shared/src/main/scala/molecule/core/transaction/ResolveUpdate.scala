package molecule.core.transaction

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.marshalling.ConnProxy
import molecule.core.transaction.ops.UpdateOps
import molecule.core.util.ModelUtils
import scala.annotation.tailrec

class ResolveUpdate(
  val proxy: ConnProxy,
  val isUpsert: Boolean,
) extends ModelUtils { self: UpdateOps =>

  def noOptional(a: Attr): Nothing = throw ModelError(s"Can't update optional values. Found:\n" + a)
  def noNested: Nothing = throw ModelError(s"Nested data structure not allowed in update molecule.")


  @tailrec
  final def getFilters(
    reversedElements: List[Element],
    filterElements: List[Element] = Nil,
    hasFilter: Boolean = false,
    requireNs: Boolean = false,
    requiredNsPaths: List[List[String]] = List(List())
  ): (List[Element], List[List[String]]) = {
    reversedElements match {
      case element :: tail => element match {
        case a: Attr => a match {
          case a: AttrOne => a match {
            case a: AttrOneMan =>
              val optValueAttr = a match {
                case a: AttrOneManID             => AttrOneOptID(a.ns, a.attr)
                case a: AttrOneManString         => AttrOneOptString(a.ns, a.attr)
                case a: AttrOneManInt            => AttrOneOptInt(a.ns, a.attr)
                case a: AttrOneManLong           => AttrOneOptLong(a.ns, a.attr)
                case a: AttrOneManFloat          => AttrOneOptFloat(a.ns, a.attr)
                case a: AttrOneManDouble         => AttrOneOptDouble(a.ns, a.attr)
                case a: AttrOneManBoolean        => AttrOneOptBoolean(a.ns, a.attr)
                case a: AttrOneManBigInt         => AttrOneOptBigInt(a.ns, a.attr)
                case a: AttrOneManBigDecimal     => AttrOneOptBigDecimal(a.ns, a.attr)
                case a: AttrOneManDate           => AttrOneOptDate(a.ns, a.attr)
                case a: AttrOneManDuration       => AttrOneOptDuration(a.ns, a.attr)
                case a: AttrOneManInstant        => AttrOneOptInstant(a.ns, a.attr)
                case a: AttrOneManLocalDate      => AttrOneOptLocalDate(a.ns, a.attr)
                case a: AttrOneManLocalTime      => AttrOneOptLocalTime(a.ns, a.attr)
                case a: AttrOneManLocalDateTime  => AttrOneOptLocalDateTime(a.ns, a.attr)
                case a: AttrOneManOffsetTime     => AttrOneOptOffsetTime(a.ns, a.attr)
                case a: AttrOneManOffsetDateTime => AttrOneOptOffsetDateTime(a.ns, a.attr)
                case a: AttrOneManZonedDateTime  => AttrOneOptZonedDateTime(a.ns, a.attr)
                case a: AttrOneManUUID           => AttrOneOptUUID(a.ns, a.attr)
                case a: AttrOneManURI            => AttrOneOptURI(a.ns, a.attr)
                case a: AttrOneManByte           => AttrOneOptByte(a.ns, a.attr)
                case a: AttrOneManShort          => AttrOneOptShort(a.ns, a.attr)
                case a: AttrOneManChar           => AttrOneOptChar(a.ns, a.attr)
              }
              getFilters(tail, optValueAttr :: filterElements, hasFilter, true, requiredNsPaths)

            case a: AttrOneTac => getFilters(tail, a :: filterElements,
              hasFilter || a.op != NoValue, true, requiredNsPaths)

            case a: AttrOneOpt => noOptional(a)
          }

          case a: AttrSet => a match {
            case a: AttrSetMan => a.op match {
              case _ =>
                // Retrieve current Set values for retraction
                val optSet = a match {
                  case a: AttrSetManID             => AttrSetOptID(a.ns, a.attr)
                  case a: AttrSetManString         => AttrSetOptString(a.ns, a.attr)
                  case a: AttrSetManInt            => AttrSetOptInt(a.ns, a.attr)
                  case a: AttrSetManLong           => AttrSetOptLong(a.ns, a.attr)
                  case a: AttrSetManFloat          => AttrSetOptFloat(a.ns, a.attr)
                  case a: AttrSetManDouble         => AttrSetOptDouble(a.ns, a.attr)
                  case a: AttrSetManBoolean        => AttrSetOptBoolean(a.ns, a.attr)
                  case a: AttrSetManBigInt         => AttrSetOptBigInt(a.ns, a.attr)
                  case a: AttrSetManBigDecimal     => AttrSetOptBigDecimal(a.ns, a.attr)
                  case a: AttrSetManDate           => AttrSetOptDate(a.ns, a.attr)
                  case a: AttrSetManDuration       => AttrSetOptDuration(a.ns, a.attr)
                  case a: AttrSetManInstant        => AttrSetOptInstant(a.ns, a.attr)
                  case a: AttrSetManLocalDate      => AttrSetOptLocalDate(a.ns, a.attr)
                  case a: AttrSetManLocalTime      => AttrSetOptLocalTime(a.ns, a.attr)
                  case a: AttrSetManLocalDateTime  => AttrSetOptLocalDateTime(a.ns, a.attr)
                  case a: AttrSetManOffsetTime     => AttrSetOptOffsetTime(a.ns, a.attr)
                  case a: AttrSetManOffsetDateTime => AttrSetOptOffsetDateTime(a.ns, a.attr)
                  case a: AttrSetManZonedDateTime  => AttrSetOptZonedDateTime(a.ns, a.attr)
                  case a: AttrSetManUUID           => AttrSetOptUUID(a.ns, a.attr)
                  case a: AttrSetManURI            => AttrSetOptURI(a.ns, a.attr)
                  case a: AttrSetManByte           => AttrSetOptByte(a.ns, a.attr)
                  case a: AttrSetManShort          => AttrSetOptShort(a.ns, a.attr)
                  case a: AttrSetManChar           => AttrSetOptChar(a.ns, a.attr)
                }
                getFilters(tail, optSet :: filterElements,
                  hasFilter, requireNs || a.op != Remove, requiredNsPaths)
            }

            case a: AttrSetTac =>
              if (a.op == Eq) {
                throw ModelError(s"Filtering by collection equality (${a.name}) not supported in updates.")
              }
              getFilters(tail, a :: filterElements, hasFilter || a.op != NoValue, requireNs, requiredNsPaths)

            case _: AttrSetOpt => noOptional(a)
          }

          case a: AttrSeq => a match {
            case a: AttrSeqMan =>
              // Retrieve current Seq values for retraction
              val optSet = a match {
                case a: AttrSeqManID             => AttrSeqOptID(a.ns, a.attr)
                case a: AttrSeqManString         => AttrSeqOptString(a.ns, a.attr)
                case a: AttrSeqManInt            => AttrSeqOptInt(a.ns, a.attr)
                case a: AttrSeqManLong           => AttrSeqOptLong(a.ns, a.attr)
                case a: AttrSeqManFloat          => AttrSeqOptFloat(a.ns, a.attr)
                case a: AttrSeqManDouble         => AttrSeqOptDouble(a.ns, a.attr)
                case a: AttrSeqManBoolean        => AttrSeqOptBoolean(a.ns, a.attr)
                case a: AttrSeqManBigInt         => AttrSeqOptBigInt(a.ns, a.attr)
                case a: AttrSeqManBigDecimal     => AttrSeqOptBigDecimal(a.ns, a.attr)
                case a: AttrSeqManDate           => AttrSeqOptDate(a.ns, a.attr)
                case a: AttrSeqManDuration       => AttrSeqOptDuration(a.ns, a.attr)
                case a: AttrSeqManInstant        => AttrSeqOptInstant(a.ns, a.attr)
                case a: AttrSeqManLocalDate      => AttrSeqOptLocalDate(a.ns, a.attr)
                case a: AttrSeqManLocalTime      => AttrSeqOptLocalTime(a.ns, a.attr)
                case a: AttrSeqManLocalDateTime  => AttrSeqOptLocalDateTime(a.ns, a.attr)
                case a: AttrSeqManOffsetTime     => AttrSeqOptOffsetTime(a.ns, a.attr)
                case a: AttrSeqManOffsetDateTime => AttrSeqOptOffsetDateTime(a.ns, a.attr)
                case a: AttrSeqManZonedDateTime  => AttrSeqOptZonedDateTime(a.ns, a.attr)
                case a: AttrSeqManUUID           => AttrSeqOptUUID(a.ns, a.attr)
                case a: AttrSeqManURI            => AttrSeqOptURI(a.ns, a.attr)
                case a: AttrSeqManByte           => AttrSeqOptByte(a.ns, a.attr)
                case a: AttrSeqManShort          => AttrSeqOptShort(a.ns, a.attr)
                case a: AttrSeqManChar           => AttrSeqOptChar(a.ns, a.attr)
              }
              getFilters(tail, optSet :: filterElements,
                hasFilter, requireNs || a.op != Remove, requiredNsPaths)


            case a: AttrSeqTac =>
              if (a.op == Eq) {
                throw ModelError(s"Filtering by collection equality (${a.name}) not supported in updates.")
              }
              val filterAttributes = a.op match {
                case Has | HasNo =>
                  // Add same tacit attribute to avoid missing Datomic binding
                  val cleanTacitAttr = a match {
                    case a: AttrSeqTacID             => a.copy(op = V, vs = Nil)
                    case a: AttrSeqTacString         => a.copy(op = V, vs = Nil)
                    case a: AttrSeqTacInt            => a.copy(op = V, vs = Nil)
                    case a: AttrSeqTacLong           => a.copy(op = V, vs = Nil)
                    case a: AttrSeqTacFloat          => a.copy(op = V, vs = Nil)
                    case a: AttrSeqTacDouble         => a.copy(op = V, vs = Nil)
                    case a: AttrSeqTacBoolean        => a.copy(op = V, vs = Nil)
                    case a: AttrSeqTacBigInt         => a.copy(op = V, vs = Nil)
                    case a: AttrSeqTacBigDecimal     => a.copy(op = V, vs = Nil)
                    case a: AttrSeqTacDate           => a.copy(op = V, vs = Nil)
                    case a: AttrSeqTacDuration       => a.copy(op = V, vs = Nil)
                    case a: AttrSeqTacInstant        => a.copy(op = V, vs = Nil)
                    case a: AttrSeqTacLocalDate      => a.copy(op = V, vs = Nil)
                    case a: AttrSeqTacLocalTime      => a.copy(op = V, vs = Nil)
                    case a: AttrSeqTacLocalDateTime  => a.copy(op = V, vs = Nil)
                    case a: AttrSeqTacOffsetTime     => a.copy(op = V, vs = Nil)
                    case a: AttrSeqTacOffsetDateTime => a.copy(op = V, vs = Nil)
                    case a: AttrSeqTacZonedDateTime  => a.copy(op = V, vs = Nil)
                    case a: AttrSeqTacUUID           => a.copy(op = V, vs = Nil)
                    case a: AttrSeqTacURI            => a.copy(op = V, vs = Nil)
                    case a: AttrSeqTacByte           => a.copy(op = V, vs = Array.empty[Byte])
                    case a: AttrSeqTacShort          => a.copy(op = V, vs = Nil)
                    case a: AttrSeqTacChar           => a.copy(op = V, vs = Nil)
                  }
                  List(cleanTacitAttr, a)

                case _ => List(a)
              }
              getFilters(tail, filterAttributes ::: filterElements,
                hasFilter || a.op != NoValue, requireNs, requiredNsPaths)

            case _: AttrSeqOpt => noOptional(a)
          }

          case a: AttrMap => a match {
            case a: AttrMapMan =>
              // Retrieve current Map for retraction
              val optSet = a match {
                case a: AttrMapManID             => AttrMapOptID(a.ns, a.attr)
                case a: AttrMapManString         => AttrMapOptString(a.ns, a.attr)
                case a: AttrMapManInt            => AttrMapOptInt(a.ns, a.attr)
                case a: AttrMapManLong           => AttrMapOptLong(a.ns, a.attr)
                case a: AttrMapManFloat          => AttrMapOptFloat(a.ns, a.attr)
                case a: AttrMapManDouble         => AttrMapOptDouble(a.ns, a.attr)
                case a: AttrMapManBoolean        => AttrMapOptBoolean(a.ns, a.attr)
                case a: AttrMapManBigInt         => AttrMapOptBigInt(a.ns, a.attr)
                case a: AttrMapManBigDecimal     => AttrMapOptBigDecimal(a.ns, a.attr)
                case a: AttrMapManDate           => AttrMapOptDate(a.ns, a.attr)
                case a: AttrMapManDuration       => AttrMapOptDuration(a.ns, a.attr)
                case a: AttrMapManInstant        => AttrMapOptInstant(a.ns, a.attr)
                case a: AttrMapManLocalDate      => AttrMapOptLocalDate(a.ns, a.attr)
                case a: AttrMapManLocalTime      => AttrMapOptLocalTime(a.ns, a.attr)
                case a: AttrMapManLocalDateTime  => AttrMapOptLocalDateTime(a.ns, a.attr)
                case a: AttrMapManOffsetTime     => AttrMapOptOffsetTime(a.ns, a.attr)
                case a: AttrMapManOffsetDateTime => AttrMapOptOffsetDateTime(a.ns, a.attr)
                case a: AttrMapManZonedDateTime  => AttrMapOptZonedDateTime(a.ns, a.attr)
                case a: AttrMapManUUID           => AttrMapOptUUID(a.ns, a.attr)
                case a: AttrMapManURI            => AttrMapOptURI(a.ns, a.attr)
                case a: AttrMapManByte           => AttrMapOptByte(a.ns, a.attr)
                case a: AttrMapManShort          => AttrMapOptShort(a.ns, a.attr)
                case a: AttrMapManChar           => AttrMapOptChar(a.ns, a.attr)
              }
              getFilters(tail, optSet :: filterElements,
                hasFilter, requireNs || a.op != Remove, requiredNsPaths)

            case a: AttrMapTac => getFilters(tail, a :: filterElements,
              hasFilter || a.op != NoValue, requireNs, requiredNsPaths)

            case _: AttrMapOpt => noOptional(a)
          }
        }

        case r: Ref => if (hasFilter) {
          // There are filters after this ref, so we know that the reference will exist
          getFilters(tail, r :: AttrOneManID(r.refNs, "id") :: filterElements, true)

        } else {
          // Skip tail and start over since we can't know if this ref is asserted without any filter values after it.
          // From here on and backwards we only might have a reference

          val requiredNsPaths1 = if (requireNs) {
            requiredNsPaths match {
              case Nil :: tail => List(r.ns, r.refAttr, r.refNs) :: tail
              case cur :: tail => (List(r.ns, r.refAttr) ::: cur) :: tail
              case Nil         => Nil
            }
          } else requiredNsPaths

          // ref has no filters after it, so we don't know if theres a relationship. Look for optional ref id then.
          getFilters(tail, AttrOneOptID(r.ns, r.refAttr) :: Nil, false, requiredNsPaths = requiredNsPaths1)
        }

        case br: BackRef => getFilters(tail, br :: filterElements, hasFilter)

        case _ => noNested
      }

      case Nil =>
        if (!hasFilter) {
          throw ModelError("Please add at least one tacit filter attribute (applying empty value not counting).")
        }
        (filterElements, requiredNsPaths)
    }
  }

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
                case _            => throw ModelError(s"Unexpected update operation for card-many attribute. Found:\n" + a)
              }
              case a: AttrSetTac => handleFilterAttr(a); resolve(tail)
              case _: AttrSetOpt => noOptional(a)
            }

            case a: AttrSeq => a match {
              case a: AttrSeqMan => a.op match {
                case Eq | NoValue => resolveAttrSeqMan(a); resolve(tail)
                case Add          => resolveAttrSeqAdd(a); resolve(tail)
                case Remove       => resolveAttrSeqRemove(a); resolve(tail)
                case _            => throw ModelError(s"Unexpected update operation for card-many attribute. Found:\n" + a)
              }
              case a: AttrSeqTac => handleFilterAttr(a); resolve(tail)
              case _: AttrSeqOpt => noOptional(a)
            }

            case a: AttrMap => a match {
              case a: AttrMapMan => a.op match {
                case Eq | NoValue => resolveAttrMapMan(a); resolve(tail)
                case Add          => resolveAttrMapAdd(a); resolve(tail)
                case Remove       => resolveAttrMapRemove(a); resolve(tail)
                case _            => throw ModelError(s"Unexpected update operation for card-many attribute. Found:\n" + a)
              }
              case a: AttrMapTac => handleFilterAttr(a); resolve(tail)
              case a: AttrMapOpt => noOptional(a)
            }
          }

        case r: Ref      => handleRefNs(r); resolve(tail)
        case br: BackRef => handleBackRef(br); resolve(tail)
        case _           => noNested
      }
      case Nil             => ()
    }
  }


  private def resolveAttrOneMan(a: AttrOneMan): Unit = {
    val (ns, attr, owner) = (a.ns, a.attr, a.owner)
    a match {
      case a if a.attr == "id" => throw ModelError(
        s"Generic id attribute not allowed in update molecule. Found:\n" + a)

      case a if a.op != Eq && a.op != NoValue => throw ModelError(
        s"Can't update attributes without an applied value. Found:\n" + a)

      case a: AttrOneManID             => updateOne(ns, attr, owner, a.vs, transformID)
      case a: AttrOneManString         => updateOne(ns, attr, owner, a.vs, transformString)
      case a: AttrOneManInt            => updateOne(ns, attr, owner, a.vs, transformInt)
      case a: AttrOneManLong           => updateOne(ns, attr, owner, a.vs, transformLong)
      case a: AttrOneManFloat          => updateOne(ns, attr, owner, a.vs, transformFloat)
      case a: AttrOneManDouble         => updateOne(ns, attr, owner, a.vs, transformDouble)
      case a: AttrOneManBoolean        => updateOne(ns, attr, owner, a.vs, transformBoolean)
      case a: AttrOneManBigInt         => updateOne(ns, attr, owner, a.vs, transformBigInt)
      case a: AttrOneManBigDecimal     => updateOne(ns, attr, owner, a.vs, transformBigDecimal)
      case a: AttrOneManDate           => updateOne(ns, attr, owner, a.vs, transformDate)
      case a: AttrOneManDuration       => updateOne(ns, attr, owner, a.vs, transformDuration)
      case a: AttrOneManInstant        => updateOne(ns, attr, owner, a.vs, transformInstant)
      case a: AttrOneManLocalDate      => updateOne(ns, attr, owner, a.vs, transformLocalDate)
      case a: AttrOneManLocalTime      => updateOne(ns, attr, owner, a.vs, transformLocalTime)
      case a: AttrOneManLocalDateTime  => updateOne(ns, attr, owner, a.vs, transformLocalDateTime)
      case a: AttrOneManOffsetTime     => updateOne(ns, attr, owner, a.vs, transformOffsetTime)
      case a: AttrOneManOffsetDateTime => updateOne(ns, attr, owner, a.vs, transformOffsetDateTime)
      case a: AttrOneManZonedDateTime  => updateOne(ns, attr, owner, a.vs, transformZonedDateTime)
      case a: AttrOneManUUID           => updateOne(ns, attr, owner, a.vs, transformUUID)
      case a: AttrOneManURI            => updateOne(ns, attr, owner, a.vs, transformURI)
      case a: AttrOneManByte           => updateOne(ns, attr, owner, a.vs, transformByte)
      case a: AttrOneManShort          => updateOne(ns, attr, owner, a.vs, transformShort)
      case a: AttrOneManChar           => updateOne(ns, attr, owner, a.vs, transformChar)
    }
  }

  private def resolveAttrOneTac(a: AttrOneTac): Unit = {
    a match {
      case AttrOneTacID(ns, "id", Eq, ids1, _, _, _, _, _, _, _, _) => handleIds(ns, ids1)

      case a if a.attr == "id" => throw ModelError(
        s"Generic id attribute not allowed in update molecule. Found:\n" + a)

      //      case a if proxy.uniqueAttrs.contains(a.cleanName) => handleUniqueFilterAttr(a)
      case a => handleFilterAttr(a)
    }
  }

  private def resolveAttrSetMan(a: AttrSetMan): Unit = {
    val (ns, attr, refNs, owner) = (a.ns, a.attr, a.refNs, a.owner)
    a match {
      case a: AttrSetManID             => updateSetEq(ns, attr, refNs, owner, a.vs, transformID, extsID, set2arrayID, value2jsonID)
      case a: AttrSetManString         => updateSetEq(ns, attr, refNs, owner, a.vs, transformString, extsString, set2arrayString, value2jsonString)
      case a: AttrSetManInt            => updateSetEq(ns, attr, refNs, owner, a.vs, transformInt, extsInt, set2arrayInt, value2jsonInt)
      case a: AttrSetManLong           => updateSetEq(ns, attr, refNs, owner, a.vs, transformLong, extsLong, set2arrayLong, value2jsonLong)
      case a: AttrSetManFloat          => updateSetEq(ns, attr, refNs, owner, a.vs, transformFloat, extsFloat, set2arrayFloat, value2jsonFloat)
      case a: AttrSetManDouble         => updateSetEq(ns, attr, refNs, owner, a.vs, transformDouble, extsDouble, set2arrayDouble, value2jsonDouble)
      case a: AttrSetManBoolean        => updateSetEq(ns, attr, refNs, owner, a.vs, transformBoolean, extsBoolean, set2arrayBoolean, value2jsonBoolean)
      case a: AttrSetManBigInt         => updateSetEq(ns, attr, refNs, owner, a.vs, transformBigInt, extsBigInt, set2arrayBigInt, value2jsonBigInt)
      case a: AttrSetManBigDecimal     => updateSetEq(ns, attr, refNs, owner, a.vs, transformBigDecimal, extsBigDecimal, set2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSetManDate           => updateSetEq(ns, attr, refNs, owner, a.vs, transformDate, extsDate, set2arrayDate, value2jsonDate)
      case a: AttrSetManDuration       => updateSetEq(ns, attr, refNs, owner, a.vs, transformDuration, extsDuration, set2arrayDuration, value2jsonDuration)
      case a: AttrSetManInstant        => updateSetEq(ns, attr, refNs, owner, a.vs, transformInstant, extsInstant, set2arrayInstant, value2jsonInstant)
      case a: AttrSetManLocalDate      => updateSetEq(ns, attr, refNs, owner, a.vs, transformLocalDate, extsLocalDate, set2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSetManLocalTime      => updateSetEq(ns, attr, refNs, owner, a.vs, transformLocalTime, extsLocalTime, set2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSetManLocalDateTime  => updateSetEq(ns, attr, refNs, owner, a.vs, transformLocalDateTime, extsLocalDateTime, set2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSetManOffsetTime     => updateSetEq(ns, attr, refNs, owner, a.vs, transformOffsetTime, extsOffsetTime, set2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSetManOffsetDateTime => updateSetEq(ns, attr, refNs, owner, a.vs, transformOffsetDateTime, extsOffsetDateTime, set2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSetManZonedDateTime  => updateSetEq(ns, attr, refNs, owner, a.vs, transformZonedDateTime, extsZonedDateTime, set2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSetManUUID           => updateSetEq(ns, attr, refNs, owner, a.vs, transformUUID, extsUUID, set2arrayUUID, value2jsonUUID)
      case a: AttrSetManURI            => updateSetEq(ns, attr, refNs, owner, a.vs, transformURI, extsURI, set2arrayURI, value2jsonURI)
      case a: AttrSetManByte           => updateSetEq(ns, attr, refNs, owner, a.vs, transformByte, extsByte, set2arrayByte, value2jsonByte)
      case a: AttrSetManShort          => updateSetEq(ns, attr, refNs, owner, a.vs, transformShort, extsShort, set2arrayShort, value2jsonShort)
      case a: AttrSetManChar           => updateSetEq(ns, attr, refNs, owner, a.vs, transformChar, extsChar, set2arrayChar, value2jsonChar)
    }
  }

  private def resolveAttrSetAdd(a: AttrSetMan): Unit = {
    val (ns, attr, refNs, owner) = (a.ns, a.attr, a.refNs, a.owner)
    a match {
      case a: AttrSetManID             => updateSetAdd(ns, attr, refNs, owner, a.vs, transformID, extsID, set2arrayID, value2jsonID)
      case a: AttrSetManString         => updateSetAdd(ns, attr, refNs, owner, a.vs, transformString, extsString, set2arrayString, value2jsonString)
      case a: AttrSetManInt            => updateSetAdd(ns, attr, refNs, owner, a.vs, transformInt, extsInt, set2arrayInt, value2jsonInt)
      case a: AttrSetManLong           => updateSetAdd(ns, attr, refNs, owner, a.vs, transformLong, extsLong, set2arrayLong, value2jsonLong)
      case a: AttrSetManFloat          => updateSetAdd(ns, attr, refNs, owner, a.vs, transformFloat, extsFloat, set2arrayFloat, value2jsonFloat)
      case a: AttrSetManDouble         => updateSetAdd(ns, attr, refNs, owner, a.vs, transformDouble, extsDouble, set2arrayDouble, value2jsonDouble)
      case a: AttrSetManBoolean        => updateSetAdd(ns, attr, refNs, owner, a.vs, transformBoolean, extsBoolean, set2arrayBoolean, value2jsonBoolean)
      case a: AttrSetManBigInt         => updateSetAdd(ns, attr, refNs, owner, a.vs, transformBigInt, extsBigInt, set2arrayBigInt, value2jsonBigInt)
      case a: AttrSetManBigDecimal     => updateSetAdd(ns, attr, refNs, owner, a.vs, transformBigDecimal, extsBigDecimal, set2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSetManDate           => updateSetAdd(ns, attr, refNs, owner, a.vs, transformDate, extsDate, set2arrayDate, value2jsonDate)
      case a: AttrSetManDuration       => updateSetAdd(ns, attr, refNs, owner, a.vs, transformDuration, extsDuration, set2arrayDuration, value2jsonDuration)
      case a: AttrSetManInstant        => updateSetAdd(ns, attr, refNs, owner, a.vs, transformInstant, extsInstant, set2arrayInstant, value2jsonInstant)
      case a: AttrSetManLocalDate      => updateSetAdd(ns, attr, refNs, owner, a.vs, transformLocalDate, extsLocalDate, set2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSetManLocalTime      => updateSetAdd(ns, attr, refNs, owner, a.vs, transformLocalTime, extsLocalTime, set2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSetManLocalDateTime  => updateSetAdd(ns, attr, refNs, owner, a.vs, transformLocalDateTime, extsLocalDateTime, set2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSetManOffsetTime     => updateSetAdd(ns, attr, refNs, owner, a.vs, transformOffsetTime, extsOffsetTime, set2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSetManOffsetDateTime => updateSetAdd(ns, attr, refNs, owner, a.vs, transformOffsetDateTime, extsOffsetDateTime, set2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSetManZonedDateTime  => updateSetAdd(ns, attr, refNs, owner, a.vs, transformZonedDateTime, extsZonedDateTime, set2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSetManUUID           => updateSetAdd(ns, attr, refNs, owner, a.vs, transformUUID, extsUUID, set2arrayUUID, value2jsonUUID)
      case a: AttrSetManURI            => updateSetAdd(ns, attr, refNs, owner, a.vs, transformURI, extsURI, set2arrayURI, value2jsonURI)
      case a: AttrSetManByte           => updateSetAdd(ns, attr, refNs, owner, a.vs, transformByte, extsByte, set2arrayByte, value2jsonByte)
      case a: AttrSetManShort          => updateSetAdd(ns, attr, refNs, owner, a.vs, transformShort, extsShort, set2arrayShort, value2jsonShort)
      case a: AttrSetManChar           => updateSetAdd(ns, attr, refNs, owner, a.vs, transformChar, extsChar, set2arrayChar, value2jsonChar)
    }
  }

  private def resolveAttrSetRemove(a: AttrSetMan): Unit = {
    val (ns, attr, refNs, owner) = (a.ns, a.attr, a.refNs, a.owner)
    a match {
      case a: AttrSetManID             => updateSetRemove(ns, attr, refNs, owner, a.vs, transformID, extsID, one2jsonID)
      case a: AttrSetManString         => updateSetRemove(ns, attr, refNs, owner, a.vs, transformString, extsString, one2jsonString)
      case a: AttrSetManInt            => updateSetRemove(ns, attr, refNs, owner, a.vs, transformInt, extsInt, one2jsonInt)
      case a: AttrSetManLong           => updateSetRemove(ns, attr, refNs, owner, a.vs, transformLong, extsLong, one2jsonLong)
      case a: AttrSetManFloat          => updateSetRemove(ns, attr, refNs, owner, a.vs, transformFloat, extsFloat, one2jsonFloat)
      case a: AttrSetManDouble         => updateSetRemove(ns, attr, refNs, owner, a.vs, transformDouble, extsDouble, one2jsonDouble)
      case a: AttrSetManBoolean        => updateSetRemove(ns, attr, refNs, owner, a.vs, transformBoolean, extsBoolean, one2jsonBoolean)
      case a: AttrSetManBigInt         => updateSetRemove(ns, attr, refNs, owner, a.vs, transformBigInt, extsBigInt, one2jsonBigInt)
      case a: AttrSetManBigDecimal     => updateSetRemove(ns, attr, refNs, owner, a.vs, transformBigDecimal, extsBigDecimal, one2jsonBigDecimal)
      case a: AttrSetManDate           => updateSetRemove(ns, attr, refNs, owner, a.vs, transformDate, extsDate, one2jsonDate)
      case a: AttrSetManDuration       => updateSetRemove(ns, attr, refNs, owner, a.vs, transformDuration, extsDuration, one2jsonDuration)
      case a: AttrSetManInstant        => updateSetRemove(ns, attr, refNs, owner, a.vs, transformInstant, extsInstant, one2jsonInstant)
      case a: AttrSetManLocalDate      => updateSetRemove(ns, attr, refNs, owner, a.vs, transformLocalDate, extsLocalDate, one2jsonLocalDate)
      case a: AttrSetManLocalTime      => updateSetRemove(ns, attr, refNs, owner, a.vs, transformLocalTime, extsLocalTime, one2jsonLocalTime)
      case a: AttrSetManLocalDateTime  => updateSetRemove(ns, attr, refNs, owner, a.vs, transformLocalDateTime, extsLocalDateTime, one2jsonLocalDateTime)
      case a: AttrSetManOffsetTime     => updateSetRemove(ns, attr, refNs, owner, a.vs, transformOffsetTime, extsOffsetTime, one2jsonOffsetTime)
      case a: AttrSetManOffsetDateTime => updateSetRemove(ns, attr, refNs, owner, a.vs, transformOffsetDateTime, extsOffsetDateTime, one2jsonOffsetDateTime)
      case a: AttrSetManZonedDateTime  => updateSetRemove(ns, attr, refNs, owner, a.vs, transformZonedDateTime, extsZonedDateTime, one2jsonZonedDateTime)
      case a: AttrSetManUUID           => updateSetRemove(ns, attr, refNs, owner, a.vs, transformUUID, extsUUID, one2jsonUUID)
      case a: AttrSetManURI            => updateSetRemove(ns, attr, refNs, owner, a.vs, transformURI, extsURI, one2jsonURI)
      case a: AttrSetManByte           => updateSetRemove(ns, attr, refNs, owner, a.vs, transformByte, extsByte, one2jsonByte)
      case a: AttrSetManShort          => updateSetRemove(ns, attr, refNs, owner, a.vs, transformShort, extsShort, one2jsonShort)
      case a: AttrSetManChar           => updateSetRemove(ns, attr, refNs, owner, a.vs, transformChar, extsChar, one2jsonChar)
    }
  }

  private def resolveAttrSeqMan(a: AttrSeqMan): Unit = {
    val (ns, attr, refNs, owner) = (a.ns, a.attr, a.refNs, a.owner)
    a match {
      case a: AttrSeqManID             => updateSeqEq(ns, attr, refNs, owner, a.vs, transformID, extsID, seq2arrayID, value2jsonID)
      case a: AttrSeqManString         => updateSeqEq(ns, attr, refNs, owner, a.vs, transformString, extsString, seq2arrayString, value2jsonString)
      case a: AttrSeqManInt            => updateSeqEq(ns, attr, refNs, owner, a.vs, transformInt, extsInt, seq2arrayInt, value2jsonInt)
      case a: AttrSeqManLong           => updateSeqEq(ns, attr, refNs, owner, a.vs, transformLong, extsLong, seq2arrayLong, value2jsonLong)
      case a: AttrSeqManFloat          => updateSeqEq(ns, attr, refNs, owner, a.vs, transformFloat, extsFloat, seq2arrayFloat, value2jsonFloat)
      case a: AttrSeqManDouble         => updateSeqEq(ns, attr, refNs, owner, a.vs, transformDouble, extsDouble, seq2arrayDouble, value2jsonDouble)
      case a: AttrSeqManBoolean        => updateSeqEq(ns, attr, refNs, owner, a.vs, transformBoolean, extsBoolean, seq2arrayBoolean, value2jsonBoolean)
      case a: AttrSeqManBigInt         => updateSeqEq(ns, attr, refNs, owner, a.vs, transformBigInt, extsBigInt, seq2arrayBigInt, value2jsonBigInt)
      case a: AttrSeqManBigDecimal     => updateSeqEq(ns, attr, refNs, owner, a.vs, transformBigDecimal, extsBigDecimal, seq2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSeqManDate           => updateSeqEq(ns, attr, refNs, owner, a.vs, transformDate, extsDate, seq2arrayDate, value2jsonDate)
      case a: AttrSeqManDuration       => updateSeqEq(ns, attr, refNs, owner, a.vs, transformDuration, extsDuration, seq2arrayDuration, value2jsonDuration)
      case a: AttrSeqManInstant        => updateSeqEq(ns, attr, refNs, owner, a.vs, transformInstant, extsInstant, seq2arrayInstant, value2jsonInstant)
      case a: AttrSeqManLocalDate      => updateSeqEq(ns, attr, refNs, owner, a.vs, transformLocalDate, extsLocalDate, seq2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSeqManLocalTime      => updateSeqEq(ns, attr, refNs, owner, a.vs, transformLocalTime, extsLocalTime, seq2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSeqManLocalDateTime  => updateSeqEq(ns, attr, refNs, owner, a.vs, transformLocalDateTime, extsLocalDateTime, seq2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSeqManOffsetTime     => updateSeqEq(ns, attr, refNs, owner, a.vs, transformOffsetTime, extsOffsetTime, seq2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSeqManOffsetDateTime => updateSeqEq(ns, attr, refNs, owner, a.vs, transformOffsetDateTime, extsOffsetDateTime, seq2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSeqManZonedDateTime  => updateSeqEq(ns, attr, refNs, owner, a.vs, transformZonedDateTime, extsZonedDateTime, seq2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSeqManUUID           => updateSeqEq(ns, attr, refNs, owner, a.vs, transformUUID, extsUUID, seq2arrayUUID, value2jsonUUID)
      case a: AttrSeqManURI            => updateSeqEq(ns, attr, refNs, owner, a.vs, transformURI, extsURI, seq2arrayURI, value2jsonURI)
      case a: AttrSeqManShort          => updateSeqEq(ns, attr, refNs, owner, a.vs, transformShort, extsShort, seq2arrayShort, value2jsonShort)
      case a: AttrSeqManChar           => updateSeqEq(ns, attr, refNs, owner, a.vs, transformChar, extsChar, seq2arrayChar, value2jsonChar)
      case a: AttrSeqManByte           => updateByteArray(ns, attr, a.vs)
    }
  }

  private def resolveAttrSeqAdd(a: AttrSeqMan): Unit = {
    val (ns, attr, refNs, owner) = (a.ns, a.attr, a.refNs, a.owner)
    a match {
      case a: AttrSeqManID             => updateSeqAdd(ns, attr, refNs, owner, a.vs, transformID, extsID, seq2arrayID, value2jsonID)
      case a: AttrSeqManString         => updateSeqAdd(ns, attr, refNs, owner, a.vs, transformString, extsString, seq2arrayString, value2jsonString)
      case a: AttrSeqManInt            => updateSeqAdd(ns, attr, refNs, owner, a.vs, transformInt, extsInt, seq2arrayInt, value2jsonInt)
      case a: AttrSeqManLong           => updateSeqAdd(ns, attr, refNs, owner, a.vs, transformLong, extsLong, seq2arrayLong, value2jsonLong)
      case a: AttrSeqManFloat          => updateSeqAdd(ns, attr, refNs, owner, a.vs, transformFloat, extsFloat, seq2arrayFloat, value2jsonFloat)
      case a: AttrSeqManDouble         => updateSeqAdd(ns, attr, refNs, owner, a.vs, transformDouble, extsDouble, seq2arrayDouble, value2jsonDouble)
      case a: AttrSeqManBoolean        => updateSeqAdd(ns, attr, refNs, owner, a.vs, transformBoolean, extsBoolean, seq2arrayBoolean, value2jsonBoolean)
      case a: AttrSeqManBigInt         => updateSeqAdd(ns, attr, refNs, owner, a.vs, transformBigInt, extsBigInt, seq2arrayBigInt, value2jsonBigInt)
      case a: AttrSeqManBigDecimal     => updateSeqAdd(ns, attr, refNs, owner, a.vs, transformBigDecimal, extsBigDecimal, seq2arrayBigDecimal, value2jsonBigDecimal)
      case a: AttrSeqManDate           => updateSeqAdd(ns, attr, refNs, owner, a.vs, transformDate, extsDate, seq2arrayDate, value2jsonDate)
      case a: AttrSeqManDuration       => updateSeqAdd(ns, attr, refNs, owner, a.vs, transformDuration, extsDuration, seq2arrayDuration, value2jsonDuration)
      case a: AttrSeqManInstant        => updateSeqAdd(ns, attr, refNs, owner, a.vs, transformInstant, extsInstant, seq2arrayInstant, value2jsonInstant)
      case a: AttrSeqManLocalDate      => updateSeqAdd(ns, attr, refNs, owner, a.vs, transformLocalDate, extsLocalDate, seq2arrayLocalDate, value2jsonLocalDate)
      case a: AttrSeqManLocalTime      => updateSeqAdd(ns, attr, refNs, owner, a.vs, transformLocalTime, extsLocalTime, seq2arrayLocalTime, value2jsonLocalTime)
      case a: AttrSeqManLocalDateTime  => updateSeqAdd(ns, attr, refNs, owner, a.vs, transformLocalDateTime, extsLocalDateTime, seq2arrayLocalDateTime, value2jsonLocalDateTime)
      case a: AttrSeqManOffsetTime     => updateSeqAdd(ns, attr, refNs, owner, a.vs, transformOffsetTime, extsOffsetTime, seq2arrayOffsetTime, value2jsonOffsetTime)
      case a: AttrSeqManOffsetDateTime => updateSeqAdd(ns, attr, refNs, owner, a.vs, transformOffsetDateTime, extsOffsetDateTime, seq2arrayOffsetDateTime, value2jsonOffsetDateTime)
      case a: AttrSeqManZonedDateTime  => updateSeqAdd(ns, attr, refNs, owner, a.vs, transformZonedDateTime, extsZonedDateTime, seq2arrayZonedDateTime, value2jsonZonedDateTime)
      case a: AttrSeqManUUID           => updateSeqAdd(ns, attr, refNs, owner, a.vs, transformUUID, extsUUID, seq2arrayUUID, value2jsonUUID)
      case a: AttrSeqManURI            => updateSeqAdd(ns, attr, refNs, owner, a.vs, transformURI, extsURI, seq2arrayURI, value2jsonURI)
      case a: AttrSeqManShort          => updateSeqAdd(ns, attr, refNs, owner, a.vs, transformShort, extsShort, seq2arrayShort, value2jsonShort)
      case a: AttrSeqManChar           => updateSeqAdd(ns, attr, refNs, owner, a.vs, transformChar, extsChar, seq2arrayChar, value2jsonChar)
      case a: AttrSeqManByte           => throw ModelError(s"Operations on byte arrays (${a.ns}.${a.attr}) not allowed.")
    }
  }

  private def resolveAttrSeqRemove(a: AttrSeqMan): Unit = {
    val (ns, attr, refNs, owner) = (a.ns, a.attr, a.refNs, a.owner)
    a match {
      case a: AttrSeqManID             => updateSeqRemove(ns, attr, refNs, owner, a.vs, transformID, extsID, one2jsonID)
      case a: AttrSeqManString         => updateSeqRemove(ns, attr, refNs, owner, a.vs, transformString, extsString, one2jsonString)
      case a: AttrSeqManInt            => updateSeqRemove(ns, attr, refNs, owner, a.vs, transformInt, extsInt, one2jsonInt)
      case a: AttrSeqManLong           => updateSeqRemove(ns, attr, refNs, owner, a.vs, transformLong, extsLong, one2jsonLong)
      case a: AttrSeqManFloat          => updateSeqRemove(ns, attr, refNs, owner, a.vs, transformFloat, extsFloat, one2jsonFloat)
      case a: AttrSeqManDouble         => updateSeqRemove(ns, attr, refNs, owner, a.vs, transformDouble, extsDouble, one2jsonDouble)
      case a: AttrSeqManBoolean        => updateSeqRemove(ns, attr, refNs, owner, a.vs, transformBoolean, extsBoolean, one2jsonBoolean)
      case a: AttrSeqManBigInt         => updateSeqRemove(ns, attr, refNs, owner, a.vs, transformBigInt, extsBigInt, one2jsonBigInt)
      case a: AttrSeqManBigDecimal     => updateSeqRemove(ns, attr, refNs, owner, a.vs, transformBigDecimal, extsBigDecimal, one2jsonBigDecimal)
      case a: AttrSeqManDate           => updateSeqRemove(ns, attr, refNs, owner, a.vs, transformDate, extsDate, one2jsonDate)
      case a: AttrSeqManDuration       => updateSeqRemove(ns, attr, refNs, owner, a.vs, transformDuration, extsDuration, one2jsonDuration)
      case a: AttrSeqManInstant        => updateSeqRemove(ns, attr, refNs, owner, a.vs, transformInstant, extsInstant, one2jsonInstant)
      case a: AttrSeqManLocalDate      => updateSeqRemove(ns, attr, refNs, owner, a.vs, transformLocalDate, extsLocalDate, one2jsonLocalDate)
      case a: AttrSeqManLocalTime      => updateSeqRemove(ns, attr, refNs, owner, a.vs, transformLocalTime, extsLocalTime, one2jsonLocalTime)
      case a: AttrSeqManLocalDateTime  => updateSeqRemove(ns, attr, refNs, owner, a.vs, transformLocalDateTime, extsLocalDateTime, one2jsonLocalDateTime)
      case a: AttrSeqManOffsetTime     => updateSeqRemove(ns, attr, refNs, owner, a.vs, transformOffsetTime, extsOffsetTime, one2jsonOffsetTime)
      case a: AttrSeqManOffsetDateTime => updateSeqRemove(ns, attr, refNs, owner, a.vs, transformOffsetDateTime, extsOffsetDateTime, one2jsonOffsetDateTime)
      case a: AttrSeqManZonedDateTime  => updateSeqRemove(ns, attr, refNs, owner, a.vs, transformZonedDateTime, extsZonedDateTime, one2jsonZonedDateTime)
      case a: AttrSeqManUUID           => updateSeqRemove(ns, attr, refNs, owner, a.vs, transformUUID, extsUUID, one2jsonUUID)
      case a: AttrSeqManURI            => updateSeqRemove(ns, attr, refNs, owner, a.vs, transformURI, extsURI, one2jsonURI)
      case a: AttrSeqManShort          => updateSeqRemove(ns, attr, refNs, owner, a.vs, transformShort, extsShort, one2jsonShort)
      case a: AttrSeqManChar           => updateSeqRemove(ns, attr, refNs, owner, a.vs, transformChar, extsChar, one2jsonChar)
      case a: AttrSeqManByte           => throw ModelError(s"Operations on byte arrays (${a.ns}.${a.attr}) not allowed.")
    }
  }


  private def resolveAttrMapMan(a: AttrMapMan): Unit = {
    val (ns, attr, refNs, noValue, owner) = (a.ns, a.attr, a.refNs, a.op == NoValue, a.owner)
    a match {
      case a: AttrMapManID             => updateMapEq(ns, attr, refNs, noValue, owner, a.vs, transformID, value2jsonID) //, extsID, set2arrayID)
      case a: AttrMapManString         => updateMapEq(ns, attr, refNs, noValue, owner, a.vs, transformString, value2jsonString) //, extsString, set2arrayString)
      case a: AttrMapManInt            => updateMapEq(ns, attr, refNs, noValue, owner, a.vs, transformInt, value2jsonInt) //, extsInt, set2arrayInt)
      case a: AttrMapManLong           => updateMapEq(ns, attr, refNs, noValue, owner, a.vs, transformLong, value2jsonLong) //, extsLong, set2arrayLong)
      case a: AttrMapManFloat          => updateMapEq(ns, attr, refNs, noValue, owner, a.vs, transformFloat, value2jsonFloat) //, extsFloat, set2arrayFloat)
      case a: AttrMapManDouble         => updateMapEq(ns, attr, refNs, noValue, owner, a.vs, transformDouble, value2jsonDouble) //, extsDouble, set2arrayDouble)
      case a: AttrMapManBoolean        => updateMapEq(ns, attr, refNs, noValue, owner, a.vs, transformBoolean, value2jsonBoolean) //, extsBoolean, set2arrayBoolean)
      case a: AttrMapManBigInt         => updateMapEq(ns, attr, refNs, noValue, owner, a.vs, transformBigInt, value2jsonBigInt) //, extsBigInt, set2arrayBigInt)
      case a: AttrMapManBigDecimal     => updateMapEq(ns, attr, refNs, noValue, owner, a.vs, transformBigDecimal, value2jsonBigDecimal) //, extsBigDecimal, set2arrayBigDecimal)
      case a: AttrMapManDate           => updateMapEq(ns, attr, refNs, noValue, owner, a.vs, transformDate, value2jsonDate) //, extsDate, set2arrayDate)
      case a: AttrMapManDuration       => updateMapEq(ns, attr, refNs, noValue, owner, a.vs, transformDuration, value2jsonDuration) //, extsDuration, set2arrayDuration)
      case a: AttrMapManInstant        => updateMapEq(ns, attr, refNs, noValue, owner, a.vs, transformInstant, value2jsonInstant) //, extsInstant, set2arrayInstant)
      case a: AttrMapManLocalDate      => updateMapEq(ns, attr, refNs, noValue, owner, a.vs, transformLocalDate, value2jsonLocalDate) //, extsLocalDate, set2arrayLocalDate)
      case a: AttrMapManLocalTime      => updateMapEq(ns, attr, refNs, noValue, owner, a.vs, transformLocalTime, value2jsonLocalTime) //, extsLocalTime, set2arrayLocalTime)
      case a: AttrMapManLocalDateTime  => updateMapEq(ns, attr, refNs, noValue, owner, a.vs, transformLocalDateTime, value2jsonLocalDateTime) //, extsLocalDateTime, set2arrayLocalDateTime)
      case a: AttrMapManOffsetTime     => updateMapEq(ns, attr, refNs, noValue, owner, a.vs, transformOffsetTime, value2jsonOffsetTime) //, extsOffsetTime, set2arrayOffsetTime)
      case a: AttrMapManOffsetDateTime => updateMapEq(ns, attr, refNs, noValue, owner, a.vs, transformOffsetDateTime, value2jsonOffsetDateTime) //, extsOffsetDateTime, set2arrayOffsetDateTime)
      case a: AttrMapManZonedDateTime  => updateMapEq(ns, attr, refNs, noValue, owner, a.vs, transformZonedDateTime, value2jsonZonedDateTime) //, extsZonedDateTime, set2arrayZonedDateTime)
      case a: AttrMapManUUID           => updateMapEq(ns, attr, refNs, noValue, owner, a.vs, transformUUID, value2jsonUUID) //, extsUUID, set2arrayUUID)
      case a: AttrMapManURI            => updateMapEq(ns, attr, refNs, noValue, owner, a.vs, transformURI, value2jsonURI) //, extsURI, set2arrayURI)
      case a: AttrMapManByte           => updateMapEq(ns, attr, refNs, noValue, owner, a.vs, transformByte, value2jsonByte) //, extsURI, set2arrayURI)
      case a: AttrMapManShort          => updateMapEq(ns, attr, refNs, noValue, owner, a.vs, transformShort, value2jsonShort) //, extsShort, set2arrayShort)
      case a: AttrMapManChar           => updateMapEq(ns, attr, refNs, noValue, owner, a.vs, transformChar, value2jsonChar) //, extsChar, set2arrayChar)
    }
  }

  private def resolveAttrMapAdd(a: AttrMapMan): Unit = {
    val (ns, attr, refNs, owner) = (a.ns, a.attr, a.refNs, a.owner)
    a match {
      case a: AttrMapManID             => updateMapAdd(ns, attr, refNs, owner, a.vs, transformID, extsID, value2jsonID) //, set2arrayID)
      case a: AttrMapManString         => updateMapAdd(ns, attr, refNs, owner, a.vs, transformString, extsString, value2jsonString) //, set2arrayString)
      case a: AttrMapManInt            => updateMapAdd(ns, attr, refNs, owner, a.vs, transformInt, extsInt, value2jsonInt) //, set2arrayInt)
      case a: AttrMapManLong           => updateMapAdd(ns, attr, refNs, owner, a.vs, transformLong, extsLong, value2jsonLong) //, set2arrayLong)
      case a: AttrMapManFloat          => updateMapAdd(ns, attr, refNs, owner, a.vs, transformFloat, extsFloat, value2jsonFloat) //, set2arrayFloat)
      case a: AttrMapManDouble         => updateMapAdd(ns, attr, refNs, owner, a.vs, transformDouble, extsDouble, value2jsonDouble) //, set2arrayDouble)
      case a: AttrMapManBoolean        => updateMapAdd(ns, attr, refNs, owner, a.vs, transformBoolean, extsBoolean, value2jsonBoolean) //, set2arrayBoolean)
      case a: AttrMapManBigInt         => updateMapAdd(ns, attr, refNs, owner, a.vs, transformBigInt, extsBigInt, value2jsonBigInt) //, set2arrayBigInt)
      case a: AttrMapManBigDecimal     => updateMapAdd(ns, attr, refNs, owner, a.vs, transformBigDecimal, extsBigDecimal, value2jsonBigDecimal) //, set2arrayBigDecimal)
      case a: AttrMapManDate           => updateMapAdd(ns, attr, refNs, owner, a.vs, transformDate, extsDate, value2jsonDate) //, set2arrayDate)
      case a: AttrMapManDuration       => updateMapAdd(ns, attr, refNs, owner, a.vs, transformDuration, extsDuration, value2jsonDuration) //, set2arrayDuration)
      case a: AttrMapManInstant        => updateMapAdd(ns, attr, refNs, owner, a.vs, transformInstant, extsInstant, value2jsonInstant) //, set2arrayInstant)
      case a: AttrMapManLocalDate      => updateMapAdd(ns, attr, refNs, owner, a.vs, transformLocalDate, extsLocalDate, value2jsonLocalDate) //, set2arrayLocalDate)
      case a: AttrMapManLocalTime      => updateMapAdd(ns, attr, refNs, owner, a.vs, transformLocalTime, extsLocalTime, value2jsonLocalTime) //, set2arrayLocalTime)
      case a: AttrMapManLocalDateTime  => updateMapAdd(ns, attr, refNs, owner, a.vs, transformLocalDateTime, extsLocalDateTime, value2jsonLocalDateTime) //, set2arrayLocalDateTime)
      case a: AttrMapManOffsetTime     => updateMapAdd(ns, attr, refNs, owner, a.vs, transformOffsetTime, extsOffsetTime, value2jsonOffsetTime) //, set2arrayOffsetTime)
      case a: AttrMapManOffsetDateTime => updateMapAdd(ns, attr, refNs, owner, a.vs, transformOffsetDateTime, extsOffsetDateTime, value2jsonOffsetDateTime) //, set2arrayOffsetDateTime)
      case a: AttrMapManZonedDateTime  => updateMapAdd(ns, attr, refNs, owner, a.vs, transformZonedDateTime, extsZonedDateTime, value2jsonZonedDateTime) //, set2arrayZonedDateTime)
      case a: AttrMapManUUID           => updateMapAdd(ns, attr, refNs, owner, a.vs, transformUUID, extsUUID, value2jsonUUID) //, set2arrayUUID)
      case a: AttrMapManURI            => updateMapAdd(ns, attr, refNs, owner, a.vs, transformURI, extsURI, value2jsonURI) //, set2arrayURI)
      case a: AttrMapManByte           => updateMapAdd(ns, attr, refNs, owner, a.vs, transformByte, extsByte, value2jsonByte) //, set2arrayURI)
      case a: AttrMapManShort          => updateMapAdd(ns, attr, refNs, owner, a.vs, transformShort, extsShort, value2jsonShort) //, set2arrayShort)
      case a: AttrMapManChar           => updateMapAdd(ns, attr, refNs, owner, a.vs, transformChar, extsChar, value2jsonChar) //, set2arrayChar)
    }
  }

  private def resolveAttrMapRemove(a: AttrMapMan): Unit = {
    val (ns, attr, refNs, owner) = (a.ns, a.attr, a.refNs, a.owner)
    a match {
      case a: AttrMapManID             => updateMapRemove(ns, attr, refNs, owner, a.vs, transformID, extsID, value2jsonID) //, one2jsonID)
      case a: AttrMapManString         => updateMapRemove(ns, attr, refNs, owner, a.vs, transformString, extsString, value2jsonString) //, one2jsonString)
      case a: AttrMapManInt            => updateMapRemove(ns, attr, refNs, owner, a.vs, transformInt, extsInt, value2jsonInt) //, one2jsonInt)
      case a: AttrMapManLong           => updateMapRemove(ns, attr, refNs, owner, a.vs, transformLong, extsLong, value2jsonLong) //, one2jsonLong)
      case a: AttrMapManFloat          => updateMapRemove(ns, attr, refNs, owner, a.vs, transformFloat, extsFloat, value2jsonFloat) //, one2jsonFloat)
      case a: AttrMapManDouble         => updateMapRemove(ns, attr, refNs, owner, a.vs, transformDouble, extsDouble, value2jsonDouble) //, one2jsonDouble)
      case a: AttrMapManBoolean        => updateMapRemove(ns, attr, refNs, owner, a.vs, transformBoolean, extsBoolean, value2jsonBoolean) //, one2jsonBoolean)
      case a: AttrMapManBigInt         => updateMapRemove(ns, attr, refNs, owner, a.vs, transformBigInt, extsBigInt, value2jsonBigInt) //, one2jsonBigInt)
      case a: AttrMapManBigDecimal     => updateMapRemove(ns, attr, refNs, owner, a.vs, transformBigDecimal, extsBigDecimal, value2jsonBigDecimal) //, one2jsonBigDecimal)
      case a: AttrMapManDate           => updateMapRemove(ns, attr, refNs, owner, a.vs, transformDate, extsDate, value2jsonDate) //, one2jsonDate)
      case a: AttrMapManDuration       => updateMapRemove(ns, attr, refNs, owner, a.vs, transformDuration, extsDuration, value2jsonDuration) //, one2jsonDuration)
      case a: AttrMapManInstant        => updateMapRemove(ns, attr, refNs, owner, a.vs, transformInstant, extsInstant, value2jsonInstant) //, one2jsonInstant)
      case a: AttrMapManLocalDate      => updateMapRemove(ns, attr, refNs, owner, a.vs, transformLocalDate, extsLocalDate, value2jsonLocalDate) //, one2jsonLocalDate)
      case a: AttrMapManLocalTime      => updateMapRemove(ns, attr, refNs, owner, a.vs, transformLocalTime, extsLocalTime, value2jsonLocalTime) //, one2jsonLocalTime)
      case a: AttrMapManLocalDateTime  => updateMapRemove(ns, attr, refNs, owner, a.vs, transformLocalDateTime, extsLocalDateTime, value2jsonLocalDateTime) //, one2jsonLocalDateTime)
      case a: AttrMapManOffsetTime     => updateMapRemove(ns, attr, refNs, owner, a.vs, transformOffsetTime, extsOffsetTime, value2jsonOffsetTime) //, one2jsonOffsetTime)
      case a: AttrMapManOffsetDateTime => updateMapRemove(ns, attr, refNs, owner, a.vs, transformOffsetDateTime, extsOffsetDateTime, value2jsonOffsetDateTime) //, one2jsonOffsetDateTime)
      case a: AttrMapManZonedDateTime  => updateMapRemove(ns, attr, refNs, owner, a.vs, transformZonedDateTime, extsZonedDateTime, value2jsonZonedDateTime) //, one2jsonZonedDateTime)
      case a: AttrMapManUUID           => updateMapRemove(ns, attr, refNs, owner, a.vs, transformUUID, extsUUID, value2jsonUUID) //, one2jsonUUID)
      case a: AttrMapManURI            => updateMapRemove(ns, attr, refNs, owner, a.vs, transformURI, extsURI, value2jsonURI) //, one2jsonURI)
      case a: AttrMapManByte           => updateMapRemove(ns, attr, refNs, owner, a.vs, transformByte, extsByte, value2jsonByte) //, one2jsonURI)
      case a: AttrMapManShort          => updateMapRemove(ns, attr, refNs, owner, a.vs, transformShort, extsShort, value2jsonShort) //, one2jsonShort)
      case a: AttrMapManChar           => updateMapRemove(ns, attr, refNs, owner, a.vs, transformChar, extsChar, value2jsonChar) //, one2jsonChar)
    }
  }
}