package molecule.datalog.datomic.transaction

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.util.ModelUtils
import scala.annotation.tailrec

trait UpdateFilters extends ModelUtils {

  @tailrec
  final def getUpsertFilters(
    reversedElements: List[Element],
    filterElements: List[Element] = Nil,
    hasFilter: Boolean = false,
    requireNs: Boolean = false,
    requiredNsPaths: List[List[String]] = List.empty[List[String]]
  ): (List[Element], List[List[String]]) = {
    reversedElements match {
      case element :: tail => element match {
        case a: Attr => a match {
          case a: AttrOne => a match {
            case a: AttrOneMan =>
              val optValueAttr = a match {
                case a: AttrOneManID             => AttrOneOptID(a.ns, a.attr, owner = a.owner)
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
          // Pad current paths. We can safely do this since backrefs are not
          // allowed in upserts, so we'll have a straight ns path.
          val requiredNsPaths1 = requiredNsPaths.map(cur => List(r.ns, r.refAttr) ::: cur)
          getUpsertFilters(
            tail,
            r :: AttrOneManID(r.refNs, "id") :: filterElements,
            true,
            requiredNsPaths = requiredNsPaths1
          )
        } else {
          // Skip tail and start over since we can't know if this ref is asserted without any filter values after it.
          // From here on and backwards we only might have a reference
          val requiredNsPaths1 = if (requireNs) {
            List(r.ns, r.refAttr, r.refNs) :: requiredNsPaths.map(cur => List(r.ns, r.refAttr) ::: cur)
          } else requiredNsPaths

          // ref has no filters after it, so we don't know if theres a relationship. Look for optional ref id then.
          getUpsertFilters(tail, AttrOneOptID(r.ns, r.refAttr) :: Nil, requiredNsPaths = requiredNsPaths1)
        }

        case _: BackRef => throw ModelError("Back refs not allowed in upserts")
        case _          => noNested
      }

      case Nil => (filterElements, requiredNsPaths)
    }
  }


  @tailrec
  final def getUpdateFilters(
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
                case a: AttrOneManID             => AttrOneManID(a.ns, a.attr, owner = a.owner)
                case a: AttrOneManString         => AttrOneManString(a.ns, a.attr)
                case a: AttrOneManInt            => AttrOneManInt(a.ns, a.attr)
                case a: AttrOneManLong           => AttrOneManLong(a.ns, a.attr)
                case a: AttrOneManFloat          => AttrOneManFloat(a.ns, a.attr)
                case a: AttrOneManDouble         => AttrOneManDouble(a.ns, a.attr)
                case a: AttrOneManBoolean        => AttrOneManBoolean(a.ns, a.attr)
                case a: AttrOneManBigInt         => AttrOneManBigInt(a.ns, a.attr)
                case a: AttrOneManBigDecimal     => AttrOneManBigDecimal(a.ns, a.attr)
                case a: AttrOneManDate           => AttrOneManDate(a.ns, a.attr)
                case a: AttrOneManDuration       => AttrOneManDuration(a.ns, a.attr)
                case a: AttrOneManInstant        => AttrOneManInstant(a.ns, a.attr)
                case a: AttrOneManLocalDate      => AttrOneManLocalDate(a.ns, a.attr)
                case a: AttrOneManLocalTime      => AttrOneManLocalTime(a.ns, a.attr)
                case a: AttrOneManLocalDateTime  => AttrOneManLocalDateTime(a.ns, a.attr)
                case a: AttrOneManOffsetTime     => AttrOneManOffsetTime(a.ns, a.attr)
                case a: AttrOneManOffsetDateTime => AttrOneManOffsetDateTime(a.ns, a.attr)
                case a: AttrOneManZonedDateTime  => AttrOneManZonedDateTime(a.ns, a.attr)
                case a: AttrOneManUUID           => AttrOneManUUID(a.ns, a.attr)
                case a: AttrOneManURI            => AttrOneManURI(a.ns, a.attr)
                case a: AttrOneManByte           => AttrOneManByte(a.ns, a.attr)
                case a: AttrOneManShort          => AttrOneManShort(a.ns, a.attr)
                case a: AttrOneManChar           => AttrOneManChar(a.ns, a.attr)
              }
              getUpdateFilters(tail, optValueAttr :: filterElements, hasFilter, true, requiredNsPaths)

            case a: AttrOneTac => getUpdateFilters(
              tail, a :: filterElements, hasFilter || a.op != NoValue, true, requiredNsPaths
            )
            case a: AttrOneOpt => noOptional(a)
          }

          case a: AttrSet => a match {
            case a: AttrSetMan => a.op match {
              case _ =>
                // Retrieve current Set values for retraction
                val filterElement = a match {
                  case a: AttrSetManID             => AttrSetManID(a.ns, a.attr, owner = a.owner)
                  case a: AttrSetManString         => AttrSetManString(a.ns, a.attr)
                  case a: AttrSetManInt            => AttrSetManInt(a.ns, a.attr)
                  case a: AttrSetManLong           => AttrSetManLong(a.ns, a.attr)
                  case a: AttrSetManFloat          => AttrSetManFloat(a.ns, a.attr)
                  case a: AttrSetManDouble         => AttrSetManDouble(a.ns, a.attr)
                  case a: AttrSetManBoolean        => AttrSetManBoolean(a.ns, a.attr)
                  case a: AttrSetManBigInt         => AttrSetManBigInt(a.ns, a.attr)
                  case a: AttrSetManBigDecimal     => AttrSetManBigDecimal(a.ns, a.attr)
                  case a: AttrSetManDate           => AttrSetManDate(a.ns, a.attr)
                  case a: AttrSetManDuration       => AttrSetManDuration(a.ns, a.attr)
                  case a: AttrSetManInstant        => AttrSetManInstant(a.ns, a.attr)
                  case a: AttrSetManLocalDate      => AttrSetManLocalDate(a.ns, a.attr)
                  case a: AttrSetManLocalTime      => AttrSetManLocalTime(a.ns, a.attr)
                  case a: AttrSetManLocalDateTime  => AttrSetManLocalDateTime(a.ns, a.attr)
                  case a: AttrSetManOffsetTime     => AttrSetManOffsetTime(a.ns, a.attr)
                  case a: AttrSetManOffsetDateTime => AttrSetManOffsetDateTime(a.ns, a.attr)
                  case a: AttrSetManZonedDateTime  => AttrSetManZonedDateTime(a.ns, a.attr)
                  case a: AttrSetManUUID           => AttrSetManUUID(a.ns, a.attr)
                  case a: AttrSetManURI            => AttrSetManURI(a.ns, a.attr)
                  case a: AttrSetManByte           => AttrSetManByte(a.ns, a.attr)
                  case a: AttrSetManShort          => AttrSetManShort(a.ns, a.attr)
                  case a: AttrSetManChar           => AttrSetManChar(a.ns, a.attr)
                }
                getUpdateFilters(
                  tail, filterElement :: filterElements, hasFilter, requireNs || a.op != Remove, requiredNsPaths
                )
            }

            case a: AttrSetTac =>
              if (a.op == Eq) {
                throw ModelError(s"Filtering by collection equality (${a.name}) not supported in updates.")
              }
              getUpdateFilters(
                tail, a :: filterElements, hasFilter || a.op != NoValue, requireNs, requiredNsPaths
              )

            case _: AttrSetOpt => noOptional(a)
          }

          case a: AttrSeq => a match {
            case a: AttrSeqMan =>
              // Retrieve current Seq values for retraction
              val filterElement = a match {
                case a: AttrSeqManID             => AttrSeqManID(a.ns, a.attr)
                case a: AttrSeqManString         => AttrSeqManString(a.ns, a.attr)
                case a: AttrSeqManInt            => AttrSeqManInt(a.ns, a.attr)
                case a: AttrSeqManLong           => AttrSeqManLong(a.ns, a.attr)
                case a: AttrSeqManFloat          => AttrSeqManFloat(a.ns, a.attr)
                case a: AttrSeqManDouble         => AttrSeqManDouble(a.ns, a.attr)
                case a: AttrSeqManBoolean        => AttrSeqManBoolean(a.ns, a.attr)
                case a: AttrSeqManBigInt         => AttrSeqManBigInt(a.ns, a.attr)
                case a: AttrSeqManBigDecimal     => AttrSeqManBigDecimal(a.ns, a.attr)
                case a: AttrSeqManDate           => AttrSeqManDate(a.ns, a.attr)
                case a: AttrSeqManDuration       => AttrSeqManDuration(a.ns, a.attr)
                case a: AttrSeqManInstant        => AttrSeqManInstant(a.ns, a.attr)
                case a: AttrSeqManLocalDate      => AttrSeqManLocalDate(a.ns, a.attr)
                case a: AttrSeqManLocalTime      => AttrSeqManLocalTime(a.ns, a.attr)
                case a: AttrSeqManLocalDateTime  => AttrSeqManLocalDateTime(a.ns, a.attr)
                case a: AttrSeqManOffsetTime     => AttrSeqManOffsetTime(a.ns, a.attr)
                case a: AttrSeqManOffsetDateTime => AttrSeqManOffsetDateTime(a.ns, a.attr)
                case a: AttrSeqManZonedDateTime  => AttrSeqManZonedDateTime(a.ns, a.attr)
                case a: AttrSeqManUUID           => AttrSeqManUUID(a.ns, a.attr)
                case a: AttrSeqManURI            => AttrSeqManURI(a.ns, a.attr)
                case a: AttrSeqManByte           => AttrSeqManByte(a.ns, a.attr)
                case a: AttrSeqManShort          => AttrSeqManShort(a.ns, a.attr)
                case a: AttrSeqManChar           => AttrSeqManChar(a.ns, a.attr)
              }
              getUpdateFilters(
                tail, filterElement :: filterElements, hasFilter, requireNs || a.op != Remove, requiredNsPaths
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
                tail, filterAttributes ::: filterElements, hasFilter || a.op != NoValue, requireNs, requiredNsPaths
              )

            case _: AttrSeqOpt => noOptional(a)
          }

          case a: AttrMap => a match {
            case a: AttrMapMan =>
              // Retrieve current Map for retraction
              val filterElement = a match {
                case a: AttrMapManID             => AttrMapManID(a.ns, a.attr)
                case a: AttrMapManString         => AttrMapManString(a.ns, a.attr)
                case a: AttrMapManInt            => AttrMapManInt(a.ns, a.attr)
                case a: AttrMapManLong           => AttrMapManLong(a.ns, a.attr)
                case a: AttrMapManFloat          => AttrMapManFloat(a.ns, a.attr)
                case a: AttrMapManDouble         => AttrMapManDouble(a.ns, a.attr)
                case a: AttrMapManBoolean        => AttrMapManBoolean(a.ns, a.attr)
                case a: AttrMapManBigInt         => AttrMapManBigInt(a.ns, a.attr)
                case a: AttrMapManBigDecimal     => AttrMapManBigDecimal(a.ns, a.attr)
                case a: AttrMapManDate           => AttrMapManDate(a.ns, a.attr)
                case a: AttrMapManDuration       => AttrMapManDuration(a.ns, a.attr)
                case a: AttrMapManInstant        => AttrMapManInstant(a.ns, a.attr)
                case a: AttrMapManLocalDate      => AttrMapManLocalDate(a.ns, a.attr)
                case a: AttrMapManLocalTime      => AttrMapManLocalTime(a.ns, a.attr)
                case a: AttrMapManLocalDateTime  => AttrMapManLocalDateTime(a.ns, a.attr)
                case a: AttrMapManOffsetTime     => AttrMapManOffsetTime(a.ns, a.attr)
                case a: AttrMapManOffsetDateTime => AttrMapManOffsetDateTime(a.ns, a.attr)
                case a: AttrMapManZonedDateTime  => AttrMapManZonedDateTime(a.ns, a.attr)
                case a: AttrMapManUUID           => AttrMapManUUID(a.ns, a.attr)
                case a: AttrMapManURI            => AttrMapManURI(a.ns, a.attr)
                case a: AttrMapManByte           => AttrMapManByte(a.ns, a.attr)
                case a: AttrMapManShort          => AttrMapManShort(a.ns, a.attr)
                case a: AttrMapManChar           => AttrMapManChar(a.ns, a.attr)
              }
              getUpdateFilters(
                tail, filterElement :: filterElements, hasFilter, requireNs || a.op != Remove, requiredNsPaths
              )

            case a: AttrMapTac => getUpdateFilters(tail, a :: filterElements,
              hasFilter || a.op != NoValue, requireNs, requiredNsPaths)

            case _: AttrMapOpt => noOptional(a)
          }
        }

        case r: Ref      => getUpdateFilters(tail, r :: AttrOneManID(r.refNs, "id") :: filterElements, true)
        case br: BackRef => getUpdateFilters(tail, br :: filterElements, hasFilter)
        case _           => noNested
      }

      case Nil => (filterElements, requiredNsPaths)
    }
  }
}
