package molecule.sql.core.transaction

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.util.ModelUtils
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

trait UpdateFilters extends ModelUtils {

  sealed trait UpdateStage

  case class FindAllIds(refPath: List[String], elements: List[Element]) extends UpdateStage {
    override def toString: String = {
      s"""FindAllIds(
         |  refPath : $refPath
         |  elements: ${elements.mkString("\n    ", "\n    ", "")}
         |)""".stripMargin
    }
  }

  case class FindKnownIds(refPath: List[String], elements: List[Element]) extends UpdateStage {
    override def toString: String = {
      s"""FindKnownIds(
         |  refPath : $refPath
         |  elements: ${elements.mkString("\n    ", "\n    ", "")}
         |)""".stripMargin
    }
  }

  case class CompleteCurRef(refPath: List[String], idsResolver: List[String] => List[Element]) extends UpdateStage {
    override def toString: String = {
      s"""CompleteCurRef(
         |  refPath    : $refPath
         |  idsResolver: List(<ids>) => List(
         |    ${idsResolver(List("<ids>")).mkString("\n    ")}
         |  )
         |)""".stripMargin
    }
  }

  case class UpdateNsData(refPath: List[String], elements: List[Element]) extends UpdateStage {
    override def toString: String = {
      s"""UpdateNsData(
         |  refPath : $refPath
         |  elements: ${elements.mkString("\n    ", "\n    ", "")}
         |)""".stripMargin
    }
  }

  def getUpsertStages(elements: List[Element]): List[UpdateStage] = {
    // Resolve model backwards to locate edge of known ids in ref structure
    @tailrec
    def rec(
      reversedElements: List[Element],
      stages: List[UpdateStage],
      refPath: List[String],
      idsModel: List[Element],
      updateModel: List[Element],
      nsHasFilter: Boolean,
      nextNsHasFilter: Boolean,
      makeIdsOptRef: Boolean,
    ): List[UpdateStage] = {
      reversedElements match {
        case lastElement :: es => lastElement match {
          case a: Attr =>
            a match {
              case a: AttrOne => a match {
                case a: AttrOneMan =>
                  rec(es, stages, refPath, idsModel, a :: updateModel, nsHasFilter, nextNsHasFilter, makeIdsOptRef)
                case a: AttrOneTac =>
                  rec(es, stages, refPath, a :: idsModel, updateModel, true, nextNsHasFilter, makeIdsOptRef)
                case a: AttrOneOpt => noOptional(a)
              }
              //              case a: AttrSet => a match {
              //                case a: AttrSetMan => ???
              //                case a: AttrSetTac =>
              //                  if (a.op == Eq) {
              //                    throw ModelError(s"Filtering by collection equality (${a.name}) not supported in updates.")
              //                  }
              //                  ???
              //                case _: AttrSetOpt => noOptional(a)
              //              }
              //
              //              case a: AttrSeq => a match {
              //                case a: AttrSeqMan => ???
              //                case a: AttrSeqTac =>
              //                  if (a.op == Eq) {
              //                    throw ModelError(s"Filtering by collection equality (${a.name}) not supported in updates.")
              //                  }
              //                  ???
              //                case _: AttrSeqOpt => noOptional(a)
              //              }
              //
              //              case a: AttrMap => a match {
              //                case a: AttrMapMan => ???
              //                case a: AttrMapTac => ???
              //                case _: AttrMapOpt => noOptional(a)
              //              }
              case _ => ???
            }

          case ref@Ref(ns, refAttr, refNs, _, _, _) if nsHasFilter || nextNsHasFilter =>
            val curRefPath   = if (refPath.isEmpty) {
              // Start ref path
              List(ns, refAttr, refNs)
            } else {
              // Extend ref path from following namespaces
              List(ns, refAttr) ++ refPath
            }
            val updateNsData = if (updateModel.isEmpty)
              Nil
            else if (refPath.isEmpty)
              List(UpdateNsData(curRefPath, updateModel))
            else
              List(UpdateNsData(List(ns, refAttr, refNs), updateModel))

            if (stages.nonEmpty && stages.head.isInstanceOf[CompleteCurRef]) {
              val idsResolver    = stages.head.asInstanceOf[CompleteCurRef].idsResolver
              val refAttrElement = idsResolver(Nil).last
              val idsModel1      = ref :: AttrOneManID(refNs, "id") :: (idsModel :+ refAttrElement)

              // Exclude CompleteCurRef stage since we'll find an optional ref id
              // Set current ref path
              val stagesWithoutCompleteCurRef = stages.tail.map {
                case UpdateNsData(_, elements)   => UpdateNsData(curRefPath, elements)
                case CompleteCurRef(_, elements) => CompleteCurRef(curRefPath, elements)
                case other                       => other
              }
              val stages1                     = updateNsData ::: stagesWithoutCompleteCurRef
              rec(es, stages1, curRefPath, idsModel1, Nil, false, true, true)
            } else {
              val idsModel1 = ref :: AttrOneManID(refNs, "id") :: idsModel
              val stages1   = updateNsData ::: stages
              rec(es, stages1, curRefPath, idsModel1, Nil, false, true, makeIdsOptRef)
            }

          case Ref(ns, refAttr, refNs, _, _, _) =>
            // use ref path for this namespace only
            val refPath1 = List(ns, refAttr, refNs)
            val newRef   = CompleteCurRef(
              refPath1,
              (ids: List[String]) => List(
                AttrOneManID(ns, "id", Eq, ids),
                AttrOneOptID(ns, refAttr, refNs = Some(refNs))
              )
            )

            val updatedStages = if (stages.isEmpty)
              Nil
            else {
              // Prepend current ref path
              val curRefPath = List(ns, refAttr) ++ refPath
              stages.map {
                case UpdateNsData(_, elements)   => UpdateNsData(curRefPath, elements)
                case CompleteCurRef(_, elements) => CompleteCurRef(curRefPath, elements)
                case other                       => other
              }
            }

            val stages1 = if (updateModel.isEmpty)
              newRef :: updatedStages
            else
              newRef :: UpdateNsData(refPath1, updateModel) :: updatedStages
            rec(es, stages1, refPath1, Nil, Nil, false, nextNsHasFilter, makeIdsOptRef)

          case _: BackRef => throw ModelError("Back refs not allowed in upserts")
          case _          => noNested
        }

        case Nil if makeIdsOptRef =>
          println(s"\n... 1 ......  $nsHasFilter  $nextNsHasFilter  $refPath  ${stages.length}")
          val refPath1     = refPath
          val ns           = refPath1.head
          val findKnownIds = FindKnownIds(refPath, AttrOneManID(ns, "id") +: idsModel)
          if (updateModel.isEmpty) {
            findKnownIds :: stages
          } else {
            findKnownIds :: UpdateNsData(List(ns), updateModel) :: stages
          }

        case Nil if nextNsHasFilter =>
          println(s"\n... 2 ......  $nsHasFilter  $nextNsHasFilter  $refPath  ${stages.length}")
          val ns         = refPath.head
          val findAllIds = FindAllIds(refPath, AttrOneManID(ns, "id") +: idsModel)
          if (updateModel.isEmpty) {
            findAllIds :: stages
          } else {
            findAllIds :: UpdateNsData(List(ns), updateModel) :: stages
          }

        case Nil =>
          println(s"\n... 3 ......  $nsHasFilter  $nextNsHasFilter  $refPath")
          val List(ns, refAttr, refNs) = refPath.take(3)
          val idsModel1                = AttrOneManID(ns, "id") +: idsModel :+ AttrOneOptID(ns, refAttr, refNs = Some(refNs))
          val findKnownIds             = FindKnownIds(refPath, idsModel1)
          val stages1                  = if (stages.isEmpty) Nil else stages.tail
          if (updateModel.isEmpty) {
            findKnownIds :: stages1
          } else {
            findKnownIds :: UpdateNsData(refPath, updateModel) :: stages1
            //            idsOptRef :: UpdateNsData(refPath.dropRight(2), updateModel) :: stages1
          }
      }
    }

    rec(elements.reverse, Nil, Nil, Nil, Nil, false, false, false)
  }


  final def getUpdateIdsModel(elements: List[Element]): (Int, List[Element]) = {
    var hasData     = false
    var arity       = 0
    val firstNs     = getInitialNs(elements)
    var curNs       = firstNs
    val filterModel = ListBuffer.empty[Element]

    // Since updates work on fully present ref structures we can accumulate forward
    elements.foreach {
      case a: Attr => a match {
        case a if a.isInstanceOf[Mandatory] => hasData = true
        case a if a.isInstanceOf[Tacit]     => filterModel += a
        case a                              => noOptional(a)

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

    // Handle last namespace
    if (hasData) {
      filterModel += AttrOneManID(curNs, "id")
      arity += 1
    }
    (arity, filterModel.toList)
  }
}
