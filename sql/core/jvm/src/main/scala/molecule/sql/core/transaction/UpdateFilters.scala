package molecule.sql.core.transaction

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.util.ModelUtils
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

trait UpdateFilters extends ModelUtils {

  @tailrec
  final def getUpsertFilters(
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
                case a: AttrOneManID             => AttrOneOptID(a.ns, a.attr, owner = a.owner)
                case a: AttrOneManString         => AttrOneOptString(a.ns, a.attr)
                case a: AttrOneManInt            => AttrOneTacInt(a.ns, a.attr)
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
              getUpsertFilters(tail, optValueAttr :: filterElements, hasFilter, true, requiredNsPaths)

            case a: AttrOneTac => getUpsertFilters(
              tail, a :: filterElements, hasFilter || a.op != NoValue, true, requiredNsPaths
            )
            case a: AttrOneOpt => noOptional(a)
          }

          case a: AttrSet => a match {
            case a: AttrSetMan => a.op match {
              case _ =>
                // Retrieve current Set values for retraction
                val optSet = a match {
                  case a: AttrSetManID             => AttrSetOptID(a.ns, a.attr, owner = a.owner)
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
                getUpsertFilters(
                  tail, optSet :: filterElements, hasFilter, requireNs || a.op != Remove, requiredNsPaths
                )
            }

            case a: AttrSetTac =>
              if (a.op == Eq) {
                throw ModelError(s"Filtering by collection equality (${a.name}) not supported in updates.")
              }
              getUpsertFilters(tail, a :: filterElements,
                hasFilter || a.op != NoValue, requireNs, requiredNsPaths)

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
              getUpsertFilters(
                tail, optSet :: filterElements, hasFilter, requireNs || a.op != Remove, requiredNsPaths
              )


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
              getUpsertFilters(
                tail, filterAttributes ::: filterElements, hasFilter || a.op != NoValue, requireNs, requiredNsPaths
              )

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
              getUpsertFilters(
                tail, optSet :: filterElements, hasFilter, requireNs || a.op != Remove, requiredNsPaths
              )

            case a: AttrMapTac => getUpsertFilters(tail, a :: filterElements,
              hasFilter || a.op != NoValue, requireNs, requiredNsPaths)

            case _: AttrMapOpt => noOptional(a)
          }
        }

        case r: Ref => if (hasFilter) {
          getUpsertFilters(tail, r :: AttrOneManID(r.refNs, "id") :: filterElements, true)
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

          // ref has no filters after it, so we don't know if there's a relationship. Look for optional ref id then.
          getUpsertFilters(tail, AttrOneOptID(r.ns, r.refAttr, refNs = Some(r.refNs)) :: Nil, false, requiredNsPaths = requiredNsPaths1)
        }

        case br: BackRef => getUpsertFilters(tail, br :: filterElements, hasFilter)
        case _           => noNested
      }

      case Nil => (filterElements, requiredNsPaths)
    }
  }


  final def getUpsertFilters3(elements: List[Element]): List[(Int, List[String], List[Element])] = {
    var hasData      = false
    var hasFilter    = false
    val filterModel  = ListBuffer.empty[Element]
    val filterModels = ListBuffer.empty[(Int, List[String], List[Element])]
    val firstNs      = getInitialNs(elements)
    var refPath      = List(firstNs)

    var prevNs      = ""
    var prevRefAttr = ""
    var curNs       = ""

    elements.foreach {
      case a: Attr => a match {
        case a: AttrOne => a match {
          case a: AttrOneMan =>
            hasData = true

          case a: AttrOneTac =>
            hasFilter = true
            filterModel += a

          case a: AttrOneOpt => noOptional(a)
        }
        case _          => ()
      }

      case r: Ref =>
        refPath ++= List(r.refAttr, r.refNs)
        if (hasData) {
          filterModel += AttrOneManID(r.ns, "id")
        } else if (hasFilter) {
          filterModels += ((1, refPath, filterModel.toList))
          filterModel.clear()
        }
        filterModel += r
        hasData = false
        hasFilter = false
        prevNs = r.ns
        prevRefAttr = r.refAttr
        curNs = r.refNs

      case br: BackRef => ???
      case _           => noNested
    }


    if (hasFilter) {
      if (hasData) {
        filterModel += AttrOneManID(curNs, "id")
        //        filterModel ++= List(
        //
        //          AttrOneManID(ns, "id"),
        //          AttrOneManID(refNs, "id")
        //        )
      }
      filterModels += ((1, refPath, filterModel.toList))
    } else {
      val prevModel = filterModels.last
      // Add id of current namespace and optional ref id
      val newModel  = prevModel._3 ++ List(
        AttrOneManID(prevNs, "id"),
        AttrOneOptID(prevNs, prevRefAttr, refNs = Some(curNs))
      )
      filterModels.update(filterModels.length - 1, (2, refPath, newModel))
    }

    filterModels.toList
  }


  final def getUpdateIdsModel(elements: List[Element]): (Int, List[Element]) = {
    var hasData     = false
    var arity       = 0
    val firstNs     = getInitialNs(elements)
    var curNs       = firstNs
    val filterModel = ListBuffer.empty[Element]

    elements.foreach {
      case a: Attr => a match {

        case a if a.isInstanceOf[Mandatory] => hasData = true
        case a if a.isInstanceOf[Tacit]     => filterModel += a
        case _                              => noOptional(a)

        //        case a: AttrOne => a match {
        //          case a: AttrOneMan => hasData = true
        //          case a: AttrOneTac => filterModel += a
        //          case a: AttrOneOpt => noOptional(a)
        //        }
        //
        //        //        case a: AttrSet => a match {
        //        //          case a: AttrSetMan =>
        //        //            // Retrieve current Set values for retraction
        //        //            val optSet = a match {
        //        //              case a: AttrSetManID             => AttrSetTacID(a.ns, a.attr, owner = a.owner)
        //        //              case a: AttrSetManString         => AttrSetTacString(a.ns, a.attr)
        //        //              case a: AttrSetManInt            => AttrSetTacInt(a.ns, a.attr)
        //        //              case a: AttrSetManLong           => AttrSetTacLong(a.ns, a.attr)
        //        //              case a: AttrSetManFloat          => AttrSetTacFloat(a.ns, a.attr)
        //        //              case a: AttrSetManDouble         => AttrSetTacDouble(a.ns, a.attr)
        //        //              case a: AttrSetManBoolean        => AttrSetTacBoolean(a.ns, a.attr)
        //        //              case a: AttrSetManBigInt         => AttrSetTacBigInt(a.ns, a.attr)
        //        //              case a: AttrSetManBigDecimal     => AttrSetTacBigDecimal(a.ns, a.attr)
        //        //              case a: AttrSetManDate           => AttrSetTacDate(a.ns, a.attr)
        //        //              case a: AttrSetManDuration       => AttrSetTacDuration(a.ns, a.attr)
        //        //              case a: AttrSetManInstant        => AttrSetTacInstant(a.ns, a.attr)
        //        //              case a: AttrSetManLocalDate      => AttrSetTacLocalDate(a.ns, a.attr)
        //        //              case a: AttrSetManLocalTime      => AttrSetTacLocalTime(a.ns, a.attr)
        //        //              case a: AttrSetManLocalDateTime  => AttrSetTacLocalDateTime(a.ns, a.attr)
        //        //              case a: AttrSetManOffsetTime     => AttrSetTacOffsetTime(a.ns, a.attr)
        //        //              case a: AttrSetManOffsetDateTime => AttrSetTacOffsetDateTime(a.ns, a.attr)
        //        //              case a: AttrSetManZonedDateTime  => AttrSetTacZonedDateTime(a.ns, a.attr)
        //        //              case a: AttrSetManUUID           => AttrSetTacUUID(a.ns, a.attr)
        //        //              case a: AttrSetManURI            => AttrSetTacURI(a.ns, a.attr)
        //        //              case a: AttrSetManByte           => AttrSetTacByte(a.ns, a.attr)
        //        //              case a: AttrSetManShort          => AttrSetTacShort(a.ns, a.attr)
        //        //              case a: AttrSetManChar           => AttrSetTacChar(a.ns, a.attr)
        //        //            }
        //        //            hasData = true
        //        //            getUpdateFilters(
        //        //              tail, optSet :: filterElements, hasFilter, requireNs || a.op != Remove, requiredNsPaths
        //        //            )
        //        //
        //        //          case a: AttrSetTac =>
        //        //            if (a.op == Eq) {
        //        //              throw ModelError(s"Filtering by collection equality (${a.name}) not supported in updates.")
        //        //            }
        //        //            getUpdateFilters(
        //        //              tail, a :: filterElements, hasFilter || a.op != NoValue, requireNs, requiredNsPaths
        //        //            )
        //        //
        //        //          case _: AttrSetOpt => noOptional(a)
        //        //        }
        //        //
        //        //        case a: AttrSeq => a match {
        //        //          case a: AttrSeqMan =>
        //        //            // Retrieve current Seq values for retraction
        //        //            val optSet = a match {
        //        //              case a: AttrSeqManID             => AttrSeqTacID(a.ns, a.attr)
        //        //              case a: AttrSeqManString         => AttrSeqTacString(a.ns, a.attr)
        //        //              case a: AttrSeqManInt            => AttrSeqTacInt(a.ns, a.attr)
        //        //              case a: AttrSeqManLong           => AttrSeqTacLong(a.ns, a.attr)
        //        //              case a: AttrSeqManFloat          => AttrSeqTacFloat(a.ns, a.attr)
        //        //              case a: AttrSeqManDouble         => AttrSeqTacDouble(a.ns, a.attr)
        //        //              case a: AttrSeqManBoolean        => AttrSeqTacBoolean(a.ns, a.attr)
        //        //              case a: AttrSeqManBigInt         => AttrSeqTacBigInt(a.ns, a.attr)
        //        //              case a: AttrSeqManBigDecimal     => AttrSeqTacBigDecimal(a.ns, a.attr)
        //        //              case a: AttrSeqManDate           => AttrSeqTacDate(a.ns, a.attr)
        //        //              case a: AttrSeqManDuration       => AttrSeqTacDuration(a.ns, a.attr)
        //        //              case a: AttrSeqManInstant        => AttrSeqTacInstant(a.ns, a.attr)
        //        //              case a: AttrSeqManLocalDate      => AttrSeqTacLocalDate(a.ns, a.attr)
        //        //              case a: AttrSeqManLocalTime      => AttrSeqTacLocalTime(a.ns, a.attr)
        //        //              case a: AttrSeqManLocalDateTime  => AttrSeqTacLocalDateTime(a.ns, a.attr)
        //        //              case a: AttrSeqManOffsetTime     => AttrSeqTacOffsetTime(a.ns, a.attr)
        //        //              case a: AttrSeqManOffsetDateTime => AttrSeqTacOffsetDateTime(a.ns, a.attr)
        //        //              case a: AttrSeqManZonedDateTime  => AttrSeqTacZonedDateTime(a.ns, a.attr)
        //        //              case a: AttrSeqManUUID           => AttrSeqTacUUID(a.ns, a.attr)
        //        //              case a: AttrSeqManURI            => AttrSeqTacURI(a.ns, a.attr)
        //        //              case a: AttrSeqManByte           => AttrSeqTacByte(a.ns, a.attr)
        //        //              case a: AttrSeqManShort          => AttrSeqTacShort(a.ns, a.attr)
        //        //              case a: AttrSeqManChar           => AttrSeqTacChar(a.ns, a.attr)
        //        //            }
        //        //            hasData = true
        //        //            getUpdateFilters(
        //        //              tail, optSet :: filterElements, hasFilter, requireNs || a.op != Remove, requiredNsPaths
        //        //            )
        //        //
        //        //
        //        //          case a: AttrSeqTac =>
        //        //            if (a.op == Eq) {
        //        //              throw ModelError(s"Filtering by collection equality (${a.name}) not supported in updates.")
        //        //            }
        //        //            val filterAttributes = a.op match {
        //        //              case Has | HasNo =>
        //        //                // Add same tacit attribute to avoid missing Datomic binding
        //        //                val cleanTacitAttr = a match {
        //        //                  case a: AttrSeqTacID             => a.copy(op = V, vs = Nil)
        //        //                  case a: AttrSeqTacString         => a.copy(op = V, vs = Nil)
        //        //                  case a: AttrSeqTacInt            => a.copy(op = V, vs = Nil)
        //        //                  case a: AttrSeqTacLong           => a.copy(op = V, vs = Nil)
        //        //                  case a: AttrSeqTacFloat          => a.copy(op = V, vs = Nil)
        //        //                  case a: AttrSeqTacDouble         => a.copy(op = V, vs = Nil)
        //        //                  case a: AttrSeqTacBoolean        => a.copy(op = V, vs = Nil)
        //        //                  case a: AttrSeqTacBigInt         => a.copy(op = V, vs = Nil)
        //        //                  case a: AttrSeqTacBigDecimal     => a.copy(op = V, vs = Nil)
        //        //                  case a: AttrSeqTacDate           => a.copy(op = V, vs = Nil)
        //        //                  case a: AttrSeqTacDuration       => a.copy(op = V, vs = Nil)
        //        //                  case a: AttrSeqTacInstant        => a.copy(op = V, vs = Nil)
        //        //                  case a: AttrSeqTacLocalDate      => a.copy(op = V, vs = Nil)
        //        //                  case a: AttrSeqTacLocalTime      => a.copy(op = V, vs = Nil)
        //        //                  case a: AttrSeqTacLocalDateTime  => a.copy(op = V, vs = Nil)
        //        //                  case a: AttrSeqTacOffsetTime     => a.copy(op = V, vs = Nil)
        //        //                  case a: AttrSeqTacOffsetDateTime => a.copy(op = V, vs = Nil)
        //        //                  case a: AttrSeqTacZonedDateTime  => a.copy(op = V, vs = Nil)
        //        //                  case a: AttrSeqTacUUID           => a.copy(op = V, vs = Nil)
        //        //                  case a: AttrSeqTacURI            => a.copy(op = V, vs = Nil)
        //        //                  case a: AttrSeqTacByte           => a.copy(op = V, vs = Array.empty[Byte])
        //        //                  case a: AttrSeqTacShort          => a.copy(op = V, vs = Nil)
        //        //                  case a: AttrSeqTacChar           => a.copy(op = V, vs = Nil)
        //        //                }
        //        //                List(cleanTacitAttr, a)
        //        //
        //        //              case _ => List(a)
        //        //            }
        //        //            getUpdateFilters(
        //        //              tail, filterAttributes ::: filterElements, hasFilter || a.op != NoValue, requireNs, requiredNsPaths
        //        //            )
        //        //
        //        //          case _: AttrSeqOpt => noOptional(a)
        //        //        }
        //        //
        //        //        case a: AttrMap => a match {
        //        //          case a: AttrMapMan =>
        //        //            // Retrieve current Map for retraction
        //        //            val optSet = a match {
        //        //              case a: AttrMapManID             => AttrMapTacID(a.ns, a.attr)
        //        //              case a: AttrMapManString         => AttrMapTacString(a.ns, a.attr)
        //        //              case a: AttrMapManInt            => AttrMapTacInt(a.ns, a.attr)
        //        //              case a: AttrMapManLong           => AttrMapTacLong(a.ns, a.attr)
        //        //              case a: AttrMapManFloat          => AttrMapTacFloat(a.ns, a.attr)
        //        //              case a: AttrMapManDouble         => AttrMapTacDouble(a.ns, a.attr)
        //        //              case a: AttrMapManBoolean        => AttrMapTacBoolean(a.ns, a.attr)
        //        //              case a: AttrMapManBigInt         => AttrMapTacBigInt(a.ns, a.attr)
        //        //              case a: AttrMapManBigDecimal     => AttrMapTacBigDecimal(a.ns, a.attr)
        //        //              case a: AttrMapManDate           => AttrMapTacDate(a.ns, a.attr)
        //        //              case a: AttrMapManDuration       => AttrMapTacDuration(a.ns, a.attr)
        //        //              case a: AttrMapManInstant        => AttrMapTacInstant(a.ns, a.attr)
        //        //              case a: AttrMapManLocalDate      => AttrMapTacLocalDate(a.ns, a.attr)
        //        //              case a: AttrMapManLocalTime      => AttrMapTacLocalTime(a.ns, a.attr)
        //        //              case a: AttrMapManLocalDateTime  => AttrMapTacLocalDateTime(a.ns, a.attr)
        //        //              case a: AttrMapManOffsetTime     => AttrMapTacOffsetTime(a.ns, a.attr)
        //        //              case a: AttrMapManOffsetDateTime => AttrMapTacOffsetDateTime(a.ns, a.attr)
        //        //              case a: AttrMapManZonedDateTime  => AttrMapTacZonedDateTime(a.ns, a.attr)
        //        //              case a: AttrMapManUUID           => AttrMapTacUUID(a.ns, a.attr)
        //        //              case a: AttrMapManURI            => AttrMapTacURI(a.ns, a.attr)
        //        //              case a: AttrMapManByte           => AttrMapTacByte(a.ns, a.attr)
        //        //              case a: AttrMapManShort          => AttrMapTacShort(a.ns, a.attr)
        //        //              case a: AttrMapManChar           => AttrMapTacChar(a.ns, a.attr)
        //        //            }
        //        //            hasData = true
        //        //            getUpdateFilters(
        //        //              tail, optSet :: filterElements, hasFilter, requireNs || a.op != Remove, requiredNsPaths
        //        //            )
        //        //
        //        //          case a: AttrMapTac => getUpdateFilters(tail, a :: filterElements,
        //        //            hasFilter || a.op != NoValue, requireNs, requiredNsPaths)
        //        //
        //        //          case _: AttrMapOpt => noOptional(a)
        //        //        }
        //        case _ => ()
      }

      case r: Ref =>
        if (hasData) {
          filterModel += AttrOneManID(curNs, "id")
          arity += 1
        }
        filterModel += r
        curNs = r.refNs
        hasData = false

      case BackRef(_, ns, _) => curNs = ns
      case _                 => noNested
    }

    if (hasData) {
      filterModel += AttrOneManID(curNs, "id")
      arity += 1
    }

    (arity, filterModel.toList)
  }
}
