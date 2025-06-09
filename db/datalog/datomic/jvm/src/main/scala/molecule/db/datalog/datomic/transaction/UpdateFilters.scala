package molecule.db.datalog.datomic.transaction

import molecule.base.error.ModelError
import molecule.core.ast.*
import molecule.db.core.util.ModelUtils
import scala.annotation.tailrec


trait UpdateFilters extends ModelUtils {

  @tailrec
  final def getUpsertFilters(
    reversedElements: List[Element],
    filterElements: List[Element] = Nil,
    hasFilter: Boolean = false,
    requireEnt: Boolean = false,
    requiredEntPaths: List[List[String]] = List.empty[List[String]]
  ): (List[Element], List[List[String]]) = {
    reversedElements match {
      case element :: tail => element match {
        case a: Attr => a match {
          case a: AttrOne => a match {
            case a: AttrOneMan =>
              val optValueAttr = a match {
                case a: AttrOneManID             => AttrOneOptID(a.ent, a.attr)
                case a: AttrOneManString         => AttrOneOptString(a.ent, a.attr)
                case a: AttrOneManInt            => AttrOneOptInt(a.ent, a.attr)
                case a: AttrOneManLong           => AttrOneOptLong(a.ent, a.attr)
                case a: AttrOneManFloat          => AttrOneOptFloat(a.ent, a.attr)
                case a: AttrOneManDouble         => AttrOneOptDouble(a.ent, a.attr)
                case a: AttrOneManBoolean        => AttrOneOptBoolean(a.ent, a.attr)
                case a: AttrOneManBigInt         => AttrOneOptBigInt(a.ent, a.attr)
                case a: AttrOneManBigDecimal     => AttrOneOptBigDecimal(a.ent, a.attr)
                case a: AttrOneManDate           => AttrOneOptDate(a.ent, a.attr)
                case a: AttrOneManDuration       => AttrOneOptDuration(a.ent, a.attr)
                case a: AttrOneManInstant        => AttrOneOptInstant(a.ent, a.attr)
                case a: AttrOneManLocalDate      => AttrOneOptLocalDate(a.ent, a.attr)
                case a: AttrOneManLocalTime      => AttrOneOptLocalTime(a.ent, a.attr)
                case a: AttrOneManLocalDateTime  => AttrOneOptLocalDateTime(a.ent, a.attr)
                case a: AttrOneManOffsetTime     => AttrOneOptOffsetTime(a.ent, a.attr)
                case a: AttrOneManOffsetDateTime => AttrOneOptOffsetDateTime(a.ent, a.attr)
                case a: AttrOneManZonedDateTime  => AttrOneOptZonedDateTime(a.ent, a.attr)
                case a: AttrOneManUUID           => AttrOneOptUUID(a.ent, a.attr)
                case a: AttrOneManURI            => AttrOneOptURI(a.ent, a.attr)
                case a: AttrOneManByte           => AttrOneOptByte(a.ent, a.attr)
                case a: AttrOneManShort          => AttrOneOptShort(a.ent, a.attr)
                case a: AttrOneManChar           => AttrOneOptChar(a.ent, a.attr)
              }
              getUpsertFilters(tail, optValueAttr :: filterElements, hasFilter, true, requiredEntPaths)

            case a: AttrOneTac => getUpsertFilters(
              tail, a :: filterElements, hasFilter || a.op != NoValue, true, requiredEntPaths
            )
            case a: AttrOneOpt => noOptional(a)
          }

          case a: AttrSet => a match {
            case a: AttrSetMan => a.op match {
              case _ =>
                // Retrieve current Set values for retraction
                val optSet = a match {
                  case a: AttrSetManID             => AttrSetOptID(a.ent, a.attr)
                  case a: AttrSetManString         => AttrSetOptString(a.ent, a.attr)
                  case a: AttrSetManInt            => AttrSetOptInt(a.ent, a.attr)
                  case a: AttrSetManLong           => AttrSetOptLong(a.ent, a.attr)
                  case a: AttrSetManFloat          => AttrSetOptFloat(a.ent, a.attr)
                  case a: AttrSetManDouble         => AttrSetOptDouble(a.ent, a.attr)
                  case a: AttrSetManBoolean        => AttrSetOptBoolean(a.ent, a.attr)
                  case a: AttrSetManBigInt         => AttrSetOptBigInt(a.ent, a.attr)
                  case a: AttrSetManBigDecimal     => AttrSetOptBigDecimal(a.ent, a.attr)
                  case a: AttrSetManDate           => AttrSetOptDate(a.ent, a.attr)
                  case a: AttrSetManDuration       => AttrSetOptDuration(a.ent, a.attr)
                  case a: AttrSetManInstant        => AttrSetOptInstant(a.ent, a.attr)
                  case a: AttrSetManLocalDate      => AttrSetOptLocalDate(a.ent, a.attr)
                  case a: AttrSetManLocalTime      => AttrSetOptLocalTime(a.ent, a.attr)
                  case a: AttrSetManLocalDateTime  => AttrSetOptLocalDateTime(a.ent, a.attr)
                  case a: AttrSetManOffsetTime     => AttrSetOptOffsetTime(a.ent, a.attr)
                  case a: AttrSetManOffsetDateTime => AttrSetOptOffsetDateTime(a.ent, a.attr)
                  case a: AttrSetManZonedDateTime  => AttrSetOptZonedDateTime(a.ent, a.attr)
                  case a: AttrSetManUUID           => AttrSetOptUUID(a.ent, a.attr)
                  case a: AttrSetManURI            => AttrSetOptURI(a.ent, a.attr)
                  case a: AttrSetManByte           => AttrSetOptByte(a.ent, a.attr)
                  case a: AttrSetManShort          => AttrSetOptShort(a.ent, a.attr)
                  case a: AttrSetManChar           => AttrSetOptChar(a.ent, a.attr)
                }
                getUpsertFilters(
                  tail, optSet :: filterElements, hasFilter, requireEnt || a.op != Remove, requiredEntPaths
                )
            }

            case a: AttrSetTac =>
              if (a.op == Eq) {
                throw ModelError(s"Filtering by collection equality (${a.name}) not supported in updates.")
              }
              getUpsertFilters(tail, a :: filterElements,
                hasFilter || a.op != NoValue, requireEnt, requiredEntPaths)

            case _: AttrSetOpt => noOptional(a)
          }

          case a: AttrSeq => a match {
            case a: AttrSeqMan =>
              // Retrieve current Seq values for retraction
              val optSet = a match {
                case a: AttrSeqManID             => AttrSeqOptID(a.ent, a.attr)
                case a: AttrSeqManString         => AttrSeqOptString(a.ent, a.attr)
                case a: AttrSeqManInt            => AttrSeqOptInt(a.ent, a.attr)
                case a: AttrSeqManLong           => AttrSeqOptLong(a.ent, a.attr)
                case a: AttrSeqManFloat          => AttrSeqOptFloat(a.ent, a.attr)
                case a: AttrSeqManDouble         => AttrSeqOptDouble(a.ent, a.attr)
                case a: AttrSeqManBoolean        => AttrSeqOptBoolean(a.ent, a.attr)
                case a: AttrSeqManBigInt         => AttrSeqOptBigInt(a.ent, a.attr)
                case a: AttrSeqManBigDecimal     => AttrSeqOptBigDecimal(a.ent, a.attr)
                case a: AttrSeqManDate           => AttrSeqOptDate(a.ent, a.attr)
                case a: AttrSeqManDuration       => AttrSeqOptDuration(a.ent, a.attr)
                case a: AttrSeqManInstant        => AttrSeqOptInstant(a.ent, a.attr)
                case a: AttrSeqManLocalDate      => AttrSeqOptLocalDate(a.ent, a.attr)
                case a: AttrSeqManLocalTime      => AttrSeqOptLocalTime(a.ent, a.attr)
                case a: AttrSeqManLocalDateTime  => AttrSeqOptLocalDateTime(a.ent, a.attr)
                case a: AttrSeqManOffsetTime     => AttrSeqOptOffsetTime(a.ent, a.attr)
                case a: AttrSeqManOffsetDateTime => AttrSeqOptOffsetDateTime(a.ent, a.attr)
                case a: AttrSeqManZonedDateTime  => AttrSeqOptZonedDateTime(a.ent, a.attr)
                case a: AttrSeqManUUID           => AttrSeqOptUUID(a.ent, a.attr)
                case a: AttrSeqManURI            => AttrSeqOptURI(a.ent, a.attr)
                case a: AttrSeqManByte           => AttrSeqOptByte(a.ent, a.attr)
                case a: AttrSeqManShort          => AttrSeqOptShort(a.ent, a.attr)
                case a: AttrSeqManChar           => AttrSeqOptChar(a.ent, a.attr)
              }
              getUpsertFilters(
                tail, optSet :: filterElements, hasFilter, requireEnt || a.op != Remove, requiredEntPaths
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
                tail, filterAttributes ::: filterElements, hasFilter || a.op != NoValue, requireEnt, requiredEntPaths
              )

            case _: AttrSeqOpt => noOptional(a)
          }

          case a: AttrMap => a match {
            case a: AttrMapMan =>
              // Retrieve current Map for retraction
              val optSet = a match {
                case a: AttrMapManID             => AttrMapOptID(a.ent, a.attr)
                case a: AttrMapManString         => AttrMapOptString(a.ent, a.attr)
                case a: AttrMapManInt            => AttrMapOptInt(a.ent, a.attr)
                case a: AttrMapManLong           => AttrMapOptLong(a.ent, a.attr)
                case a: AttrMapManFloat          => AttrMapOptFloat(a.ent, a.attr)
                case a: AttrMapManDouble         => AttrMapOptDouble(a.ent, a.attr)
                case a: AttrMapManBoolean        => AttrMapOptBoolean(a.ent, a.attr)
                case a: AttrMapManBigInt         => AttrMapOptBigInt(a.ent, a.attr)
                case a: AttrMapManBigDecimal     => AttrMapOptBigDecimal(a.ent, a.attr)
                case a: AttrMapManDate           => AttrMapOptDate(a.ent, a.attr)
                case a: AttrMapManDuration       => AttrMapOptDuration(a.ent, a.attr)
                case a: AttrMapManInstant        => AttrMapOptInstant(a.ent, a.attr)
                case a: AttrMapManLocalDate      => AttrMapOptLocalDate(a.ent, a.attr)
                case a: AttrMapManLocalTime      => AttrMapOptLocalTime(a.ent, a.attr)
                case a: AttrMapManLocalDateTime  => AttrMapOptLocalDateTime(a.ent, a.attr)
                case a: AttrMapManOffsetTime     => AttrMapOptOffsetTime(a.ent, a.attr)
                case a: AttrMapManOffsetDateTime => AttrMapOptOffsetDateTime(a.ent, a.attr)
                case a: AttrMapManZonedDateTime  => AttrMapOptZonedDateTime(a.ent, a.attr)
                case a: AttrMapManUUID           => AttrMapOptUUID(a.ent, a.attr)
                case a: AttrMapManURI            => AttrMapOptURI(a.ent, a.attr)
                case a: AttrMapManByte           => AttrMapOptByte(a.ent, a.attr)
                case a: AttrMapManShort          => AttrMapOptShort(a.ent, a.attr)
                case a: AttrMapManChar           => AttrMapOptChar(a.ent, a.attr)
              }
              getUpsertFilters(
                tail, optSet :: filterElements, hasFilter, requireEnt || a.op != Remove, requiredEntPaths
              )

            case a: AttrMapTac => getUpsertFilters(tail, a :: filterElements,
              hasFilter || a.op != NoValue, requireEnt, requiredEntPaths)

            case _: AttrMapOpt => noOptional(a)
          }
        }

        case r: Ref => if (hasFilter) {
          // Pad current paths
          val requiredEntPaths1 = requiredEntPaths.map(cur => List(r.ent, r.refAttr) ::: cur)
          getUpsertFilters(
            tail,
            r :: AttrOneManID(r.ref, "id") :: filterElements,
            true,
            requiredEntPaths = requiredEntPaths1
          )
        } else {
          // Skip tail and start over since we can't know if this ref is asserted without any filter values after it.
          // From here on and backwards we only might have a reference
          val requiredEntPaths1 = if (requireEnt) {
            List(r.ent, r.refAttr, r.ref) :: requiredEntPaths.map(cur => List(r.ent, r.refAttr) ::: cur)
          } else requiredEntPaths

          // ref has no filters after it, so we don't know if theres a relationship. Look for optional ref id then.
          getUpsertFilters(tail, AttrOneOptID(r.ent, r.refAttr) :: Nil, requiredEntPaths = requiredEntPaths1)
        }

        case _: BackRef => getUpsertFilters(tail)
        case _: OptRef  => noOptRef
        case _          => noNested
      }

      case Nil => (filterElements, requiredEntPaths)
    }
  }


  @tailrec
  final def getUpdateFilters(
    reversedElements: List[Element],
    filterElements: List[Element] = Nil,
    hasFilter: Boolean = false,
    requireEnt: Boolean = false,
    requiredEntPaths: List[List[String]] = List(List())
  ): (List[Element], List[List[String]]) = {
    reversedElements match {
      case element :: tail => element match {
        case a: Attr => a match {
          case a: AttrOne => a match {
            case a: AttrOneMan =>
              val optValueAttr = a match {
                case a: AttrOneManID             => AttrOneManID(a.ent, a.attr)
                case a: AttrOneManString         => AttrOneManString(a.ent, a.attr)
                case a: AttrOneManInt            => AttrOneManInt(a.ent, a.attr)
                case a: AttrOneManLong           => AttrOneManLong(a.ent, a.attr)
                case a: AttrOneManFloat          => AttrOneManFloat(a.ent, a.attr)
                case a: AttrOneManDouble         => AttrOneManDouble(a.ent, a.attr)
                case a: AttrOneManBoolean        => AttrOneManBoolean(a.ent, a.attr)
                case a: AttrOneManBigInt         => AttrOneManBigInt(a.ent, a.attr)
                case a: AttrOneManBigDecimal     => AttrOneManBigDecimal(a.ent, a.attr)
                case a: AttrOneManDate           => AttrOneManDate(a.ent, a.attr)
                case a: AttrOneManDuration       => AttrOneManDuration(a.ent, a.attr)
                case a: AttrOneManInstant        => AttrOneManInstant(a.ent, a.attr)
                case a: AttrOneManLocalDate      => AttrOneManLocalDate(a.ent, a.attr)
                case a: AttrOneManLocalTime      => AttrOneManLocalTime(a.ent, a.attr)
                case a: AttrOneManLocalDateTime  => AttrOneManLocalDateTime(a.ent, a.attr)
                case a: AttrOneManOffsetTime     => AttrOneManOffsetTime(a.ent, a.attr)
                case a: AttrOneManOffsetDateTime => AttrOneManOffsetDateTime(a.ent, a.attr)
                case a: AttrOneManZonedDateTime  => AttrOneManZonedDateTime(a.ent, a.attr)
                case a: AttrOneManUUID           => AttrOneManUUID(a.ent, a.attr)
                case a: AttrOneManURI            => AttrOneManURI(a.ent, a.attr)
                case a: AttrOneManByte           => AttrOneManByte(a.ent, a.attr)
                case a: AttrOneManShort          => AttrOneManShort(a.ent, a.attr)
                case a: AttrOneManChar           => AttrOneManChar(a.ent, a.attr)
              }
              getUpdateFilters(tail, optValueAttr :: filterElements, hasFilter, true, requiredEntPaths)

            case a: AttrOneTac => getUpdateFilters(
              tail, a :: filterElements, hasFilter || a.op != NoValue, true, requiredEntPaths
            )
            case a: AttrOneOpt => noOptional(a)
          }

          case a: AttrSet => a match {
            case a: AttrSetMan => a.op match {
              case _ =>
                // Retrieve current Set values for retraction
                val filterElement = a match {
                  case a: AttrSetManID             => AttrSetManID(a.ent, a.attr)
                  case a: AttrSetManString         => AttrSetManString(a.ent, a.attr)
                  case a: AttrSetManInt            => AttrSetManInt(a.ent, a.attr)
                  case a: AttrSetManLong           => AttrSetManLong(a.ent, a.attr)
                  case a: AttrSetManFloat          => AttrSetManFloat(a.ent, a.attr)
                  case a: AttrSetManDouble         => AttrSetManDouble(a.ent, a.attr)
                  case a: AttrSetManBoolean        => AttrSetManBoolean(a.ent, a.attr)
                  case a: AttrSetManBigInt         => AttrSetManBigInt(a.ent, a.attr)
                  case a: AttrSetManBigDecimal     => AttrSetManBigDecimal(a.ent, a.attr)
                  case a: AttrSetManDate           => AttrSetManDate(a.ent, a.attr)
                  case a: AttrSetManDuration       => AttrSetManDuration(a.ent, a.attr)
                  case a: AttrSetManInstant        => AttrSetManInstant(a.ent, a.attr)
                  case a: AttrSetManLocalDate      => AttrSetManLocalDate(a.ent, a.attr)
                  case a: AttrSetManLocalTime      => AttrSetManLocalTime(a.ent, a.attr)
                  case a: AttrSetManLocalDateTime  => AttrSetManLocalDateTime(a.ent, a.attr)
                  case a: AttrSetManOffsetTime     => AttrSetManOffsetTime(a.ent, a.attr)
                  case a: AttrSetManOffsetDateTime => AttrSetManOffsetDateTime(a.ent, a.attr)
                  case a: AttrSetManZonedDateTime  => AttrSetManZonedDateTime(a.ent, a.attr)
                  case a: AttrSetManUUID           => AttrSetManUUID(a.ent, a.attr)
                  case a: AttrSetManURI            => AttrSetManURI(a.ent, a.attr)
                  case a: AttrSetManByte           => AttrSetManByte(a.ent, a.attr)
                  case a: AttrSetManShort          => AttrSetManShort(a.ent, a.attr)
                  case a: AttrSetManChar           => AttrSetManChar(a.ent, a.attr)
                }
                getUpdateFilters(
                  tail, filterElement :: filterElements, hasFilter, requireEnt || a.op != Remove, requiredEntPaths
                )
            }

            case a: AttrSetTac =>
              if (a.op == Eq) {
                throw ModelError(s"Filtering by collection equality (${a.name}) not supported in updates.")
              }
              getUpdateFilters(
                tail, a :: filterElements, hasFilter || a.op != NoValue, requireEnt, requiredEntPaths
              )

            case _: AttrSetOpt => noOptional(a)
          }

          case a: AttrSeq => a match {
            case a: AttrSeqMan =>
              // Retrieve current Seq values for retraction
              val filterElement = a match {
                case a: AttrSeqManID             => AttrSeqManID(a.ent, a.attr)
                case a: AttrSeqManString         => AttrSeqManString(a.ent, a.attr)
                case a: AttrSeqManInt            => AttrSeqManInt(a.ent, a.attr)
                case a: AttrSeqManLong           => AttrSeqManLong(a.ent, a.attr)
                case a: AttrSeqManFloat          => AttrSeqManFloat(a.ent, a.attr)
                case a: AttrSeqManDouble         => AttrSeqManDouble(a.ent, a.attr)
                case a: AttrSeqManBoolean        => AttrSeqManBoolean(a.ent, a.attr)
                case a: AttrSeqManBigInt         => AttrSeqManBigInt(a.ent, a.attr)
                case a: AttrSeqManBigDecimal     => AttrSeqManBigDecimal(a.ent, a.attr)
                case a: AttrSeqManDate           => AttrSeqManDate(a.ent, a.attr)
                case a: AttrSeqManDuration       => AttrSeqManDuration(a.ent, a.attr)
                case a: AttrSeqManInstant        => AttrSeqManInstant(a.ent, a.attr)
                case a: AttrSeqManLocalDate      => AttrSeqManLocalDate(a.ent, a.attr)
                case a: AttrSeqManLocalTime      => AttrSeqManLocalTime(a.ent, a.attr)
                case a: AttrSeqManLocalDateTime  => AttrSeqManLocalDateTime(a.ent, a.attr)
                case a: AttrSeqManOffsetTime     => AttrSeqManOffsetTime(a.ent, a.attr)
                case a: AttrSeqManOffsetDateTime => AttrSeqManOffsetDateTime(a.ent, a.attr)
                case a: AttrSeqManZonedDateTime  => AttrSeqManZonedDateTime(a.ent, a.attr)
                case a: AttrSeqManUUID           => AttrSeqManUUID(a.ent, a.attr)
                case a: AttrSeqManURI            => AttrSeqManURI(a.ent, a.attr)
                case a: AttrSeqManByte           => AttrSeqManByte(a.ent, a.attr)
                case a: AttrSeqManShort          => AttrSeqManShort(a.ent, a.attr)
                case a: AttrSeqManChar           => AttrSeqManChar(a.ent, a.attr)
              }
              getUpdateFilters(
                tail, filterElement :: filterElements, hasFilter, requireEnt || a.op != Remove, requiredEntPaths
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
              getUpdateFilters(
                tail, filterAttributes ::: filterElements, hasFilter || a.op != NoValue, requireEnt, requiredEntPaths
              )

            case _: AttrSeqOpt => noOptional(a)
          }

          case a: AttrMap => a match {
            case a: AttrMapMan =>
              // Retrieve current Map for retraction
              val filterElement = a match {
                case a: AttrMapManID             => AttrMapManID(a.ent, a.attr)
                case a: AttrMapManString         => AttrMapManString(a.ent, a.attr)
                case a: AttrMapManInt            => AttrMapManInt(a.ent, a.attr)
                case a: AttrMapManLong           => AttrMapManLong(a.ent, a.attr)
                case a: AttrMapManFloat          => AttrMapManFloat(a.ent, a.attr)
                case a: AttrMapManDouble         => AttrMapManDouble(a.ent, a.attr)
                case a: AttrMapManBoolean        => AttrMapManBoolean(a.ent, a.attr)
                case a: AttrMapManBigInt         => AttrMapManBigInt(a.ent, a.attr)
                case a: AttrMapManBigDecimal     => AttrMapManBigDecimal(a.ent, a.attr)
                case a: AttrMapManDate           => AttrMapManDate(a.ent, a.attr)
                case a: AttrMapManDuration       => AttrMapManDuration(a.ent, a.attr)
                case a: AttrMapManInstant        => AttrMapManInstant(a.ent, a.attr)
                case a: AttrMapManLocalDate      => AttrMapManLocalDate(a.ent, a.attr)
                case a: AttrMapManLocalTime      => AttrMapManLocalTime(a.ent, a.attr)
                case a: AttrMapManLocalDateTime  => AttrMapManLocalDateTime(a.ent, a.attr)
                case a: AttrMapManOffsetTime     => AttrMapManOffsetTime(a.ent, a.attr)
                case a: AttrMapManOffsetDateTime => AttrMapManOffsetDateTime(a.ent, a.attr)
                case a: AttrMapManZonedDateTime  => AttrMapManZonedDateTime(a.ent, a.attr)
                case a: AttrMapManUUID           => AttrMapManUUID(a.ent, a.attr)
                case a: AttrMapManURI            => AttrMapManURI(a.ent, a.attr)
                case a: AttrMapManByte           => AttrMapManByte(a.ent, a.attr)
                case a: AttrMapManShort          => AttrMapManShort(a.ent, a.attr)
                case a: AttrMapManChar           => AttrMapManChar(a.ent, a.attr)
              }
              getUpdateFilters(
                tail, filterElement :: filterElements, hasFilter, requireEnt || a.op != Remove, requiredEntPaths
              )

            case a: AttrMapTac => getUpdateFilters(tail, a :: filterElements,
              hasFilter || a.op != NoValue, requireEnt, requiredEntPaths)

            case _: AttrMapOpt => noOptional(a)
          }
        }

        case r: Ref      => getUpdateFilters(tail, r :: AttrOneManID(r.ref, "id") :: filterElements, true)
        case br: BackRef => getUpdateFilters(tail, br :: filterElements, hasFilter)
        case r: OptRef   => noOptRef
        case _           => noNested
      }

      case Nil => (filterElements, requiredEntPaths)
    }
  }
}
