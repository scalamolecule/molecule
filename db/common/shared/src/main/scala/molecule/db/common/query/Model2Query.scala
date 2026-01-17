package molecule.db.common.query

import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import molecule.core.dataModel.*
import molecule.core.error.ModelError
import molecule.db.common.util.ModelUtils

trait Model2Query extends QueryExpr with ModelUtils {

  private var level         = 1
  private val sortsPerLevel = mutable.Map[Int, List[Int]](1 -> Nil)
  private var hasBinding    = false
  private var hasAggr       = false
  private var hasAggrSet    = false
  private var hasFilterAttr = false

  private[query] val expectedFilterAttrs = mutable.Set.empty[String]
  private[query] var optNestedLeafIsSet  = Option.empty[Boolean]

  final var nestedOptRef       = false
  final var hasOptRef          = false
  final var hasOptEntityAttrs  = false
  final var isNested           = false
  final var isManNested        = false
  final var isOptNested        = false
  final var insideOptEntity    = false
  final var insideSubQuery     = false
  final var insideJoinSubQuery = false // true when building JOIN subquery
  final var hasManSubQueryAttr = false


  @tailrec
  final def resolve(elements: List[Element]): Unit = elements match {
    case element :: tail => element match {
      case a: AttrOne => a.attr match {
        case "id" =>
          if (a.filterAttr.nonEmpty && !insideSubQuery) noIdFiltering()
          a match {
            case a: AttrOneMan => queryIdMan(a); resolve(tail)
            case a: AttrOneTac => queryIdTac(a); resolve(tail)
            case _             => unexpectedElement(a)
          }
        case _    =>
          if (a.filterAttr.exists(_._3.attr == "id") && !insideSubQuery) noIdFiltering()
          a match {
            case a: AttrOneMan => queryAttrOneMan(a); resolve(tail)
            case a: AttrOneTac => queryAttrOneTac(a); resolve(tail)
            case a: AttrOneOpt => queryAttrOneOpt(a); resolve(tail)
          }
      }

      case a: AttrSet if a.ref.isDefined => a match {
        case a: AttrSetMan => queryRefAttrSetMan(a); resolve(tail)
        case a: AttrSetTac => queryRefAttrSetTac(a); resolve(tail)
        case a: AttrSetOpt => queryRefAttrSetOpt(a); resolve(tail)
      }

      case a: AttrSet => a match {
        case a: AttrSetMan => queryAttrSetMan(a); resolve(tail)
        case a: AttrSetTac => queryAttrSetTac(a); resolve(tail)
        case a: AttrSetOpt => queryAttrSetOpt(a); resolve(tail)
      }

      case a: AttrSeq => a match {
        case a: AttrSeqMan => queryAttrSeqMan(a); resolve(tail)
        case a: AttrSeqTac => queryAttrSeqTac(a); resolve(tail)
        case a: AttrSeqOpt => queryAttrSeqOpt(a); resolve(tail)
      }

      case a: AttrMap => a match {
        case a: AttrMapMan => queryAttrMapMan(a); resolve(tail)
        case a: AttrMapTac => queryAttrMapTac(a); resolve(tail)
        case a: AttrMapOpt => queryAttrMapOpt(a); resolve(tail)
      }

      case ref: Ref                                   => queryRef(ref, tail); resolve(tail)
      case backRef: BackRef                           => queryBackRef(backRef, tail); resolve(tail)
      case OptRef(ref, refElements)                   => queryOptRef(ref, refElements); resolve(tail)
      case OptEntity(refElements)                     => queryOptEntity(refElements); resolve(tail)
      case SubQuery(subElements, optLimit, optOffset) => querySubQuery(subElements, optLimit, optOffset); resolve(tail)
      case Nested(ref, nestedElements)                => queryNested(ref, nestedElements); resolve(tail)
      case OptNested(nestedRef, nestedElements)       => queryOptNested(nestedRef, nestedElements); resolve(tail)
    }
    case Nil             => ()
  }

  def validateQueryModel(
    elements: List[Element],
    addFilterAttr: Option[(List[String], Attr) => Unit] = None,
    optHandleRef: Option[(String, String) => Unit] = None,
    optHandleBackRef: Option[() => Unit] = None,
  ): (List[Element], String, Boolean) = {
    elements match {
      case List(a: Attr) if a.attr == "id" && !insideSubQuery => throw ModelError(
        "Querying for the entity id only is not allowed. " +
          "Please add at least one attribute (can be tacit).")

      case _ if optHandleRef.isDefined && optHandleBackRef.isDefined =>
        val handleRef     = optHandleRef.get
        val handleBackRef = optHandleBackRef.get

        @tailrec
        def validate(elements: List[Element], prevElements: List[Element] = Nil): Unit = {
          elements match {
            case element :: tail =>
              element match {
                case a: Attr =>
                  validateAttr(a)
                  validate(tail, prevElements :+ a)

                case r: Ref =>
                  handleRef(r.refAttr, r.ref)
                  validate(tail, prevElements)

                case _: BackRef =>
                  handleBackRef.apply()
                  validate(tail, prevElements)

                case OptRef(_, es) =>
                  validate(es ++ tail, prevElements)

                case OptEntity(attrs) =>
                  if (tail.isEmpty)
                    throw ModelError(
                      "Query for optional entity only is not allowed. " +
                        "Please use normal entity query, or continue after optional entity with other ref."
                    )
                  validate(validateOptEntity(attrs) ++ tail, prevElements)

                case SubQuery(es, _, _) =>
                  validateJoinSubQueryCorrelationAttrs(es)
                  validateSubQuery()
                  validate(es ++ tail, prevElements)

                case Nested(r, es) =>
                  handleRef(r.refAttr, r.ref)
                  validateNested()
                  validate(es, prevElements)

                case OptNested(r, es) =>
                  handleRef(r.refAttr, r.ref)
                  validateOptNested(prevElements)
                  validate(es, prevElements)
              }
            case Nil             => ()
          }
        }
        validate(elements)

      case _ =>
        @tailrec
        def validate(elements: List[Element], prevElements: List[Element] = Nil): Unit = {
          elements match {
            case element :: tail =>
              element match {
                case a: Attr          => validateAttr(a); validate(tail, prevElements :+ a)
                case OptRef(_, es)    => validate(es ++ tail, prevElements)
                case OptEntity(attrs) => validate(attrs ++ tail, prevElements)
                case Nested(_, es)    => validateNested(); validate(es, prevElements)
                case OptNested(_, es) => validateOptNested(prevElements); validate(es, prevElements)
                case _                => validate(tail, prevElements)
              }
            case Nil             => ()
          }
        }
        validate(elements)
    }

    checkSorting()
    checkBinding()

    val initialEntity = getInitialEntity(elements)

    val elements1 = if (hasFilterAttr) {
      new CheckFilterAttrs(elements, initialEntity, addFilterAttr, this).prepare(elements, Nil)
    } else elements

    (elements1, initialEntity, hasFilterAttr)
  }

  private def validateAttr(a: Attr): Unit = {
    if (a.sort.nonEmpty) {
      sortsPerLevel(level) = sortsPerLevel(level) :+ a.sort.get.substring(1, 2).toInt
    }
    a.filterAttr.foreach(_ => hasFilterAttr = true)
    a.subquery.foreach(_ => hasFilterAttr = true)
    if (a.isInstanceOf[Mandatory] || a.isInstanceOf[Tacit]) {
      hasBinding = true
    }
  }

  private def validateOptEntity(elements: List[Element]): List[Element] = {
    elements.map {
      case a: Attr => a
      case other   => throw ModelError(
        "Only attributes of initial entity allowed in optional entity. Found:\n" + other
      )
    }
  }

  private def validateJoinSubQueryCorrelationAttrs(elements: List[Element]): Unit = {
    var correlationCount = 0
    elements.foreach {
      case a: Attr =>
        // Check if this attribute has a filterAttr (correlation in join subquery)
        a.filterAttr.foreach { case (_, _, correlAttr) =>
          correlationCount += 1
          // Ensure correlation attribute is tacit
          if (correlAttr.isInstanceOf[Mandatory]) {
            throw ModelError(s"Correlation attribute ${correlAttr.cleanName} should be tacit.")
          }
        }
        // Also check if this attribute has a subquery with correlation
        a.subquery.foreach { sq =>
          sq.elements.foreach {
            case correlAttr: Attr if correlAttr.isInstanceOf[Mandatory] =>
              throw ModelError(s"Correlation attribute ${correlAttr.cleanName} should be tacit.")
            case _                                                      => ()
          }
        }
      case _       => ()
    }
    // Ensure at most one correlation attribute
    if (correlationCount > 1) {
      throw ModelError("Only one correlation attribute allowed per subquery.")
    }
  }

  private def validateSubQuery(): Unit = {
    level += 1
    sortsPerLevel += level -> Nil
  }

  private def validateNested(): Unit = {
    level += 1
    sortsPerLevel += level -> Nil
  }

  private def validateOptNested(prevElements: List[Element]): Unit = {
    level += 1
    sortsPerLevel += level -> Nil
    val prevElementsWithoutId = prevElements.filterNot {
      case a: Attr if a.attr == "id" => true
      case _                         => false
    }
    if (prevElementsWithoutId.length == 1) {
      prevElementsWithoutId.head match {
        case _: AttrOneOpt | _: AttrSetOpt | _: AttrSeqOpt | _: AttrMapOpt =>
          throw ModelError(
            s"Single optional attribute before optional nested data structure " +
              s"is not allowed (id attribute doesn't count)."
          )
        case _                                                             => ()
      }
    }
  }

  private def checkSorting(): Unit = {
    sortsPerLevel.foreach {
      case (_, Nil)         => ()
      case (level, indexes) => indexes.sorted match {
        // Index 6s are for entity ids on each nested level.
        // Is always last on each level to allow user sort indexes to sort first.
        case Nil                    =>
        case List(1)                =>
        case List(6)                =>
        case List(1, 2)             =>
        case List(1, 6)             =>
        case List(1, 2, 3)          =>
        case List(1, 2, 6)          =>
        case List(1, 2, 3, 4)       =>
        case List(1, 2, 3, 6)       =>
        case List(1, 2, 3, 4, 5)    =>
        case List(1, 2, 3, 4, 6)    =>
        case List(1, 2, 3, 4, 5, 6) =>
        case other                  =>
          val levelMsg = if (level == 1) "" else " on level " + level
          throw ModelError(
            s"Sort index 1 should be present and additional indexes continuously increase (in any order). " +
              s"Found non-unique sort index(es)$levelMsg: " + other.mkString(", ")
          )
      }
    }
  }

  private def checkBinding(): Unit = {
    if (!hasBinding) {
      throw ModelError(
        "Query needs at least 1 mandatory/tacit attribute (id not counting)."
      )
    }
  }

  protected def checkAggrOne(): Unit = {
    if (hasAggrSet) {
      noMultiAggrSet()
    }
    hasAggr = true
  }

  private def noMultiAggrSet() = throw ModelError(
    "Only a single aggregation is allowed with card-set attributes."
  )

  def noIdFiltering() = throw ModelError(
    "Filter attributes not allowed to involve entity ids."
  )

  protected def unexpectedElement(element: Element): Nothing =
    throw ModelError("Unexpected element: " + element)

  protected def unexpectedOp(op: Op): Nothing =
    throw ModelError("Unexpected operation: " + op)

  protected def unexpectedKw(kw: String): Nothing =
    throw ModelError("Unexpected keyword: " + kw)


  protected def noMixedNestedModes: Nothing = throw ModelError(
    "Can't mix mandatory/optional nested queries."
  )
  protected def onlyOneValueInsideOptRef(ref: Ref): Nothing = throw ModelError(
    s"Only cardinality-one refs allowed in optional ref queries (${ref.ent}.${ref.refAttr})."
  )
  protected def noCardManyInsideOptRef(): Unit = if (nestedOptRef) {
    throw ModelError(
      "Cardinality-many nesting not allowed inside optional ref."
    )
  }

  protected def noCollectionMatching(attr: Attr): Nothing = {
    noCollectionMatching(attr.cleanName)
  }
  protected def noCollectionMatching(attr: String): Nothing = {
    throw ModelError(s"Matching collections ($attr) not supported in queries.")
  }

  protected def noApplyNothing(attr: Attr): Nothing = {
    val a = attr.cleanName
    throw ModelError(
      s"Applying nothing to mandatory attribute ($a) is reserved for updates to retract."
    )
  }

  protected def noCardManyFilterAttrExpr(attr: Attr): Nothing = {
    throw ModelError(
      s"Cardinality-many filter attributes not allowed to do additional filtering (${attr.name}).")
  }

  protected def checkOnlyOptRef(): Unit = if (hasOptRef && !insideOptEntity) {
    throw ModelError("Only further optional refs allowed after optional ref.")
  }
  protected def checkOnlyRefAfterOptEntity(): Unit = if (insideOptEntity) {
    throw ModelError("Only mandatory ref allowed after optional entity.")
  }

  protected def validateRefEntity(ref: Ref, nestedElements: List[Element]): Unit = {
    val nestedEntity = nestedElements.head match {
      case a: Attr   => a.ent
      case r: Ref    => r.ent
      case r: OptRef => r.ref.ent
      case other     => unexpectedElement(other)
    }
    if (ref.ref != nestedEntity) {
      val refName = ref.refAttr.capitalize
      throw ModelError(s"`$refName` can only nest to `${ref.ref}`. Found: `$nestedEntity`")
    }
  }
}
