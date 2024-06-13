package molecule.sql.core.transaction

import molecule.base.ast.{CardOne, CardSet}
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.util.ModelUtils
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

trait UpdateUtils extends ModelUtils {

  sealed trait UpdateStage

  case class FindAllIds(refPath: List[String], elements: List[Element]) extends UpdateStage {
    override def toString: String = {
      s"""FindAllIds(
         |  refPath : $refPath
         |  elements: ${elements.mkString("\n    ", "\n    ", "")}
         |)""".stripMargin
    }
  }

  case class FindKnownIds(refPath: List[String],
                          //                          isCardOne: Boolean,
                          elements: List[Element]) extends UpdateStage {
    override def toString: String = {
      s"""FindKnownIds(
         |  refPath  : $refPath
         |  elements : ${elements.mkString("\n    ", "\n    ", "")}
         |)""".stripMargin
      //         |  isCardOne: $isCardOne
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
      isCardOne: Boolean,
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
                  rec(es, stages, refPath, idsModel, a :: updateModel, isCardOne, nsHasFilter, nextNsHasFilter, makeIdsOptRef)
                case a: AttrOneTac =>
                  rec(es, stages, refPath, a :: idsModel, updateModel, isCardOne, true, nextNsHasFilter, makeIdsOptRef)
                case a: AttrOneOpt => noOptional(a)
              }

              case a: AttrSet => a match {
                case a: AttrSetMan =>
                  rec(es, stages, refPath, idsModel, a :: updateModel, isCardOne, nsHasFilter, nextNsHasFilter, makeIdsOptRef)
                case a: AttrSetTac =>
                  if (a.op == Eq) {
                    throw ModelError(s"Filtering by collection equality (${a.name}) not supported in updates.")
                  }
                  rec(es, stages, refPath, a :: idsModel, updateModel, isCardOne, true, nextNsHasFilter, makeIdsOptRef)
                case _: AttrSetOpt => noOptional(a)
              }

              case a: AttrSeq => a match {
                case a: AttrSeqMan =>
                  rec(es, stages, refPath, idsModel, a :: updateModel, isCardOne, nsHasFilter, nextNsHasFilter, makeIdsOptRef)
                case a: AttrSeqTac =>
                  if (a.op == Eq) {
                    throw ModelError(s"Filtering by collection equality (${a.name}) not supported in updates.")
                  }
                  rec(es, stages, refPath, a :: idsModel, updateModel, isCardOne, true, nextNsHasFilter, makeIdsOptRef)
                case _: AttrSeqOpt => noOptional(a)
              }

              case a: AttrMap => a match {
                case a: AttrMapMan =>
                  rec(es, stages, refPath, idsModel, a :: updateModel, isCardOne, nsHasFilter, nextNsHasFilter, makeIdsOptRef)
                case a: AttrMapTac =>
                  rec(es, stages, refPath, a :: idsModel, updateModel, isCardOne, true, nextNsHasFilter, makeIdsOptRef)
                case _: AttrMapOpt => noOptional(a)
              }
            }

          case ref@Ref(ns, refAttr, refNs, _, _, _) if nsHasFilter || nextNsHasFilter =>
            val curRefPath   = if (refPath.isEmpty) {
              // Start ref path
              List(ns, refAttr, refNs)
            } else {
              // Extend ref path from following namespaces
              List(ns, refAttr) ++ refPath
            }
            val updateNsData = if (updateModel.isEmpty) {
              Nil
            } else if (refPath.isEmpty) {
              List(UpdateNsData(curRefPath, updateModel))
            } else {
              List(UpdateNsData(List(ns, refAttr, refNs), updateModel))
            }
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
              rec(es, stages1, curRefPath, idsModel1, Nil, isCardOne, false, true, true)
            } else {
              val idsModel1 = ref :: AttrOneManID(refNs, "id") :: idsModel
              val stages1   = updateNsData ::: stages
              rec(es, stages1, curRefPath, idsModel1, Nil, isCardOne, false, true, makeIdsOptRef)
            }

          case Ref(ns, refAttr, refNs, card, _, _) =>
            // use ref path for this namespace only
            val refPath1   = List(ns, refAttr, refNs)
            val isCardOne1 = card.isInstanceOf[CardOne]

            val newRef        = if (isCardOne1) {
              CompleteCurRef(
                refPath1,
                (ids: List[String]) => List(
                  AttrOneManID(ns, "id", Eq, ids),
                  AttrOneOptID(ns, refAttr, refNs = Some(refNs))
                )
              )
            } else {
              CompleteCurRef(
                refPath1,
                (ids: List[String]) => List(
                  AttrOneManID(ns, "id", Eq, ids),
                  AttrOneOptID(ns, refAttr, refNs = Some(refNs))
                )
              )
            }
            val updatedStages = if (stages.isEmpty) {
              Nil
            } else {
              // Prepend current ref path
              val curRefPath = List(ns, refAttr) ++ refPath
              stages.map {
                case UpdateNsData(_, elements)   => UpdateNsData(curRefPath, elements)
                case CompleteCurRef(_, elements) => CompleteCurRef(curRefPath, elements)
                case other                       => other
              }
            }
            val stages1       = if (updateModel.isEmpty) {
              newRef :: updatedStages
            } else {
              newRef :: UpdateNsData(refPath1, updateModel) :: updatedStages
            }
            rec(es, stages1, refPath1, Nil, Nil, isCardOne1, false, nextNsHasFilter, makeIdsOptRef)

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
          val refId                    = if (isCardOne)
            AttrOneOptID(ns, refAttr, refNs = Some(refNs))
          else
            NestedOpt(
              Ref(ns, refAttr, refNs, CardSet, false),
              List(
                AttrOneManID(refNs, "id")))
          val idsModel1                = AttrOneManID(ns, "id") +: idsModel :+ refId
          val findKnownIds             = FindKnownIds(refPath, idsModel1)
          val stages1                  = if (stages.isEmpty) Nil else stages.tail
          if (updateModel.isEmpty) {
            findKnownIds :: stages1
          } else {
            findKnownIds :: UpdateNsData(refPath, updateModel) :: stages1
          }
      }
    }

    rec(elements.reverse, Nil, Nil, Nil, Nil, true, false, false, false)
  }

  def noCollectionFilterEq(attr: String) = throw ModelError(
    s"Filtering by collection equality ($attr) not supported in updates."
  )

  final def getUpdateIdsModel(elements: List[Element]): (Int, List[Element]) = {
    var hasData     = false
    var arity       = 0
    val firstNs     = getInitialNs(elements)
    var curNs       = firstNs
    val filterModel = ListBuffer.empty[Element]

    // Since updates work on fully present ref structures we can accumulate forward
    elements.foreach {
      case a: Attr =>
        a match {
          case a: AttrOne => a match {
            case a: AttrOneMan => hasData = true
            case a: AttrOneTac => filterModel += a
            case a: AttrOneOpt => noOptional(a)
          }

          case a: AttrSet => a match {
            case a: AttrSetMan => hasData = true
            case a: AttrSetTac =>
              if (a.op == Eq) {
                noCollectionFilterEq(a.name)
              }
              filterModel += a
            case _: AttrSetOpt => noOptional(a)
          }

          case a: AttrSeq => a match {
            case a: AttrSeqMan => hasData = true
            case a: AttrSeqTac =>
              if (a.op == Eq) {
                noCollectionFilterEq(a.name)
              }
              filterModel += a
            case _: AttrSeqOpt => noOptional(a)
          }

          case a: AttrMap => a match {
            case a: AttrMapMan => hasData = true
            case a: AttrMapTac =>
              if (a.op == Eq) {
                noCollectionFilterEq(a.name)
              }
              filterModel += a
            case _: AttrMapOpt => noOptional(a)
          }
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
