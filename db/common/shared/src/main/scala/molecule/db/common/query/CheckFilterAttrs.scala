package molecule.db.common.query

import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import molecule.core.dataModel.*
import molecule.core.error.ModelError

class CheckFilterAttrs(
  elements: List[Element],
  initialEnt: String,
  addFilterAttr: Option[(List[String], Attr) => Unit],
  m2q: Model2Query
) {
  // coordinates
  val entAttrPaths       = mutable.Map.empty[String, List[List[String]]]
  val qualifiedPaths     = mutable.Map.empty[List[String], List[List[String]]]
  val directions         = ListBuffer.empty[List[String]]
  val compareSubsToCheck = ListBuffer.empty[SubQuery]
  var path               = List(initialEnt)
  var qualifiedPath      = List(initialEnt)
  var filterAttrVars     = Map.empty[List[String], String]
  var level              = 0

  @tailrec
  private def check(elements: List[Element]): Unit = {
    elements match {
      case element :: tail =>
        element match {
          case a: Attr      => checkAttr(a); check(tail)
          case r: Ref       => handleRef(r); check(tail)
          case _: BackRef   => handleBackRef(); check(tail)
          case _: OptRef    => ???
          case _: OptEntity => ???
          case s: SubQuery  => checkExplicitSubQuery(s); check(tail)
          case n: Nested    => handleNested(n.ref); check(n.elements ++ tail)
          case n: OptNested => handleNested(n.ref); check(n.elements ++ tail)
        }
      case Nil             => ()
    }
  }

  def checkSubQuery(s: SubQuery): Unit = {
    // Validate that subquery attributes don't conflict with outer query attributes
    // (This is for comparison subqueries stored in a.subquery field)
    s.elements.foreach {
      case a: Attr =>
        if (entAttrPaths.contains(a.cleanName)) {
          throw ModelError(
            s"Filter attribute ${a.cleanName} should be tacit."
          )
        }
      case _       => ()
    }
  }

  def checkExplicitSubQuery(s: SubQuery): Unit = {
    // Explicit subqueries (from .sub()) can be either:
    // - Correlated (with filter attributes linking to outer query)
    // - Uncorrelated (CROSS JOIN - cartesian product)
    // Both are valid use cases

    // Validate that subquery attributes don't conflict with outer query attributes
    s.elements.foreach {
      case a: Attr =>
        if (entAttrPaths.contains(a.cleanName)) {
          throw ModelError(
            s"Filter attribute ${a.cleanName} should be tacit."
          )
        }
      case _       => ()
    }
  }

  def checkAttr(a: Attr): Unit = {
    val entAttr = a.cleanName
    entAttrPaths(entAttr) = entAttrPaths.get(entAttr).fold(List(path)) {
      case paths if !paths.contains(path) => paths :+ path
      case paths                          => paths
    }

    qualifiedPath = path :+ a.cleanAttr
    qualifiedPaths(qualifiedPath) = qualifiedPaths.get(qualifiedPath).fold(List(path)) {
      case paths if !paths.contains(path) => paths :+ path
      case paths                          => paths
    }

    directions += qualifiedPath

    // Collect subqueries for deferred validation
    a.subquery.foreach { subQuery =>
      compareSubsToCheck += subQuery
    }

    if (a.filterAttr.nonEmpty) {
      val (_, filterPath, fa) = a.filterAttr.get
      val filterEntAttr       = fa.cleanName

      if (fa.filterAttr.nonEmpty) {
        throw ModelError(s"Filter attributes inside filter attributes not allowed in ${a.ent}.${a.attr}")
      } else if (filterPath :+ fa.cleanAttr == path :+ a.cleanAttr) {
        throw ModelError(s"Can't filter by the same attribute `${a.name}`")
      } else if (fa.isInstanceOf[Mandatory]) {
        throw ModelError(s"Filter attribute $filterEntAttr pointing to other entity should be tacit.")
      } else if (filterPath != path && fa.op != V) {
        throw ModelError("Filtering inside cross-entity attribute filter not allowed.")
      } else {
        // Expect filter attribute in other entity
        m2q.expectedFilterAttrs += fa.cleanName
      }

      // Callback (if any) from implementation
      val pathAttr = filterPath :+ fa.cleanAttr
      addFilterAttr.foreach(_(pathAttr, a))

      filterAttrVars.get(pathAttr).fold {
        filterAttrVars = filterAttrVars + (pathAttr.takeRight(2) -> entAttr)
      }(_ => throw ModelError(s"Can't refer to ambiguous filter attribute $filterEntAttr"))
    }
  }

  def handleRef(ref: Ref): Unit = {
    if (path.isEmpty) {
      path = List(ref.ent, ref.refAttr, ref.ref)
    } else {
      path ++= List(ref.refAttr, ref.ref)
    }
  }
  def handleBackRef(): Unit = {
    path = path.dropRight(2)
  }
  def handleNested(ref: Ref): Unit = {
    level += 1
    handleRef(ref)
  }

  check(elements)

  // Now validate subquery operations after all attributes have been collected
  compareSubsToCheck.foreach { sq =>
    checkSubQuery(sq)
  }

  path = List(initialEnt)
  qualifiedPath = List(initialEnt)
  var prevAttrName        = ""
  var prevWasFilterCallee = false
  var i                   = -1
  @tailrec
  final def prepare(elements: List[Element], acc: List[Element]): List[Element] = {
    elements match {
      case element :: tail =>
        element match {
          case a: Attr      => prepare(tail, acc ++ prepareAttr(a))
          case r: Ref       => prepare(tail, acc :+ prepareRef(r))
          case b: BackRef   => prepare(tail, acc :+ prepareBackRef(b))
          case _: OptRef    => ???
          case _: OptEntity => ???
          case s: SubQuery  => prepare(tail, acc :+ prepareSubQuery(s))
          case n: Nested    => prepare(tail, acc :+ prepareNested(n))
          case n: OptNested => prepare(tail, acc :+ prepareOptNested(n))
        }
      case Nil             => acc
    }
  }

  def prepareSubQuery(subQuery: SubQuery): SubQuery = {
    val wasInsideSubQuery = m2q.insideSubQuery
    m2q.insideSubQuery = true
    val result = SubQuery(prepare(subQuery.elements, Nil), subQuery.optLimit, subQuery.optOffset)
    m2q.insideSubQuery = wasInsideSubQuery
    result
  }

  def prepareAttr(a: Attr): List[Attr] = {
    i += 1
    if (shouldOmitNonAdjacentAttr(a)) {
      Nil
    } else {
      prevAttrName = a.name
      a.filterAttr match {
        case None                                => handleNonFilterAttr(a)
        case Some((_, filterPath, filterAttr)) => handleFilterAttr(a, filterPath, filterAttr)
      }
    }
  }

  def shouldOmitNonAdjacentAttr(a: Attr): Boolean = {
    prevWasFilterCallee && prevAttrName == a.cleanName && qualifiedPath != path
  }

  def handleNonFilterAttr(a: Attr): List[Attr] = {
    prevWasFilterCallee = false
    qualifiedPath = Nil
    val entAttr = path.takeRight(1) :+ a.cleanAttr
    if ((a.isInstanceOf[AttrSetMan] || a.isInstanceOf[AttrSeqMan]) && filterAttrVars.contains(entAttr) && a.op != V) {
      throw ModelError(s"Cardinality-many filter attributes (${a.cleanName}) not allowed to do additional filtering.")
    }
    List(a)
  }

  def handleFilterAttr(a: Attr, filterPath: List[String], filterAttr: Attr): List[Attr] = {
    if (shouldTreatAsCorrelationAttr(filterAttr)) {
      resetFilterState()
      List(a)
    } else {
      processFilterAttr(a, filterPath, filterAttr)
    }
  }

  def shouldTreatAsCorrelationAttr(filterAttr: Attr): Boolean = {
    if (filterAttr.cleanAttr == "id") {
      if (m2q.insideSubQuery) {
        true
      } else {
        m2q.noIdFiltering()
      }
    } else {
      m2q.insideSubQuery
    }
  }

  def resetFilterState(): Unit = {
    prevWasFilterCallee = false
    qualifiedPath = Nil
  }

  def processFilterAttr(a: Attr, filterPath: List[String], filterAttr: Attr): List[Attr] = {
    prevWasFilterCallee = true
    qualifiedPath = filterPath

    val filterPathAttr      = filterPath :+ filterAttr.cleanAttr
    val filterAttrName      = filterAttr.cleanName
    val qualifiedFilterPath = resolveQualifiedFilterPath(filterPathAttr, filterAttrName, filterPath, filterAttr)
    val fullFilterAttrPath  = qualifiedFilterPath :+ filterAttr.cleanAttr
    val dir                 = calculateDirection(fullFilterAttrPath)
    val qualifiedFilterAttr = copyAttrWithFilterInfo(a, dir, qualifiedFilterPath, filterAttr)

    List(qualifiedFilterAttr)
  }

  def resolveQualifiedFilterPath(
    filterPathAttr: List[String],
    filterAttrName: String,
    filterPath: List[String],
    filterAttr: Attr
  ): List[String] = {
    qualifiedPaths.get(filterPathAttr) match {
      case None            => resolveFromEntAttrPaths(filterAttrName, filterAttr)
      case Some(fullPaths) => selectFromFullPaths(fullPaths, filterAttrName, filterPath)
    }
  }

  def resolveFromEntAttrPaths(filterAttrName: String, filterAttr: Attr): List[String] = {
    val entPaths = entAttrPaths.getOrElse(filterAttrName,
      throw ModelError(s"Please add missing filter attribute $filterAttrName")
    )

    entPaths.length match {
      case 1 => entPaths.head
      case 0 => throw ModelError(s"Unexpectedly found entPaths for filter attribute $filterAttrName")
      case _ => throw ModelError(
        s"Please qualify filter attribute $filterAttrName to an unambiguous path:\n  " +
          buildPrefixes(entPaths, filterAttr).mkString("\n  ")
      )
    }
  }

  def buildPrefixes(entPaths: List[List[String]], filterAttr: Attr): List[String] = {
    entPaths.distinct.map { tokens =>
      var prefix = tokens.head
      var even   = false
      tokens.foreach {
        case token if even =>
          prefix += "." + token.head.toTitleCase + token.tail
          even = !even
        case _             => even = !even
      }
      prefix + "." + filterAttr.cleanAttr
    }
  }

  def selectFromFullPaths(fullPaths: List[List[String]], filterAttrName: String, filterPath: List[String]): List[String] = {
    fullPaths.length match {
      case 1                                => fullPaths.head
      case n if n > 1 && path == filterPath => fullPaths.head
      case 0                                =>
        throw ModelError(s"Unexpectedly found no fullPaths for filter attribute $filterAttrName")
      case _                                =>
        throw ModelError(s"Found multiple fullPaths for filter attribute $filterAttrName:\n  " +
          fullPaths.mkString("\n  ")
        )
    }
  }

  def calculateDirection(fullFilterAttrPath: List[String]): Int = {
    directions.indexOf(fullFilterAttrPath) match {
      case -1                                   =>
        throw ModelError(s"Unexpectedly couldn't find direction index for $fullFilterAttrPath")
      case _ if path == fullFilterAttrPath.init => 0  //  same entity
      case j if j < i                           => -1 // filter attribute points backwards
      case _                                    => 1  //  filter attribute points forward
    }
  }

  def copyAttrWithFilterInfo(a: Attr, dir: Int, qualifiedFilterPath: List[String], filterAttr: Attr): Attr = {
    val filterInfo = Some((dir, qualifiedFilterPath, filterAttr))

    a match {
      case a: AttrOneTac => copyOneTacWithFilter(a, filterInfo)
      case a: AttrOneMan => copyOneManWithFilter(a, filterInfo)
      case a: AttrSet    =>
        if (level != 0) {
          throw ModelError("Card-Set filter attributes not allowed in nested molecules.")
        }
        copySetWithFilter(a, filterInfo)
      case other         => other
    }
  }

  def copyOneTacWithFilter(a: AttrOneTac, filterInfo: Option[(Int, List[String], Attr)]): Attr = a match {
    case a: AttrOneTacID             => a.copy(filterAttr = filterInfo)
    case a: AttrOneTacString         => a.copy(filterAttr = filterInfo)
    case a: AttrOneTacInt            => a.copy(filterAttr = filterInfo)
    case a: AttrOneTacLong           => a.copy(filterAttr = filterInfo)
    case a: AttrOneTacFloat          => a.copy(filterAttr = filterInfo)
    case a: AttrOneTacDouble         => a.copy(filterAttr = filterInfo)
    case a: AttrOneTacBoolean        => a.copy(filterAttr = filterInfo)
    case a: AttrOneTacBigInt         => a.copy(filterAttr = filterInfo)
    case a: AttrOneTacBigDecimal     => a.copy(filterAttr = filterInfo)
    case a: AttrOneTacDate           => a.copy(filterAttr = filterInfo)
    case a: AttrOneTacDuration       => a.copy(filterAttr = filterInfo)
    case a: AttrOneTacInstant        => a.copy(filterAttr = filterInfo)
    case a: AttrOneTacLocalDate      => a.copy(filterAttr = filterInfo)
    case a: AttrOneTacLocalTime      => a.copy(filterAttr = filterInfo)
    case a: AttrOneTacLocalDateTime  => a.copy(filterAttr = filterInfo)
    case a: AttrOneTacOffsetTime     => a.copy(filterAttr = filterInfo)
    case a: AttrOneTacOffsetDateTime => a.copy(filterAttr = filterInfo)
    case a: AttrOneTacZonedDateTime  => a.copy(filterAttr = filterInfo)
    case a: AttrOneTacUUID           => a.copy(filterAttr = filterInfo)
    case a: AttrOneTacURI            => a.copy(filterAttr = filterInfo)
    case a: AttrOneTacByte           => a.copy(filterAttr = filterInfo)
    case a: AttrOneTacShort          => a.copy(filterAttr = filterInfo)
    case a: AttrOneTacChar           => a.copy(filterAttr = filterInfo)
  }

  def copyOneManWithFilter(a: AttrOneMan, filterInfo: Option[(Int, List[String], Attr)]): Attr = a match {
    case a: AttrOneManID             => a.copy(filterAttr = filterInfo)
    case a: AttrOneManString         => a.copy(filterAttr = filterInfo)
    case a: AttrOneManInt            => a.copy(filterAttr = filterInfo)
    case a: AttrOneManLong           => a.copy(filterAttr = filterInfo)
    case a: AttrOneManFloat          => a.copy(filterAttr = filterInfo)
    case a: AttrOneManDouble         => a.copy(filterAttr = filterInfo)
    case a: AttrOneManBoolean        => a.copy(filterAttr = filterInfo)
    case a: AttrOneManBigInt         => a.copy(filterAttr = filterInfo)
    case a: AttrOneManBigDecimal     => a.copy(filterAttr = filterInfo)
    case a: AttrOneManDate           => a.copy(filterAttr = filterInfo)
    case a: AttrOneManDuration       => a.copy(filterAttr = filterInfo)
    case a: AttrOneManInstant        => a.copy(filterAttr = filterInfo)
    case a: AttrOneManLocalDate      => a.copy(filterAttr = filterInfo)
    case a: AttrOneManLocalTime      => a.copy(filterAttr = filterInfo)
    case a: AttrOneManLocalDateTime  => a.copy(filterAttr = filterInfo)
    case a: AttrOneManOffsetTime     => a.copy(filterAttr = filterInfo)
    case a: AttrOneManOffsetDateTime => a.copy(filterAttr = filterInfo)
    case a: AttrOneManZonedDateTime  => a.copy(filterAttr = filterInfo)
    case a: AttrOneManUUID           => a.copy(filterAttr = filterInfo)
    case a: AttrOneManURI            => a.copy(filterAttr = filterInfo)
    case a: AttrOneManByte           => a.copy(filterAttr = filterInfo)
    case a: AttrOneManShort          => a.copy(filterAttr = filterInfo)
    case a: AttrOneManChar           => a.copy(filterAttr = filterInfo)
  }

  def copySetWithFilter(a: AttrSet, filterInfo: Option[(Int, List[String], Attr)]): Attr = a match {
    case a: AttrSetMan => copySetManWithFilter(a, filterInfo)
    case a: AttrSetTac => copySetTacWithFilter(a, filterInfo)
    case other         => other
  }

  def copySetManWithFilter(a: AttrSetMan, filterInfo: Option[(Int, List[String], Attr)]): Attr = a match {
    case a: AttrSetManID             => a.copy(filterAttr = filterInfo)
    case a: AttrSetManString         => a.copy(filterAttr = filterInfo)
    case a: AttrSetManInt            => a.copy(filterAttr = filterInfo)
    case a: AttrSetManLong           => a.copy(filterAttr = filterInfo)
    case a: AttrSetManFloat          => a.copy(filterAttr = filterInfo)
    case a: AttrSetManDouble         => a.copy(filterAttr = filterInfo)
    case a: AttrSetManBoolean        => a.copy(filterAttr = filterInfo)
    case a: AttrSetManBigInt         => a.copy(filterAttr = filterInfo)
    case a: AttrSetManBigDecimal     => a.copy(filterAttr = filterInfo)
    case a: AttrSetManDate           => a.copy(filterAttr = filterInfo)
    case a: AttrSetManDuration       => a.copy(filterAttr = filterInfo)
    case a: AttrSetManInstant        => a.copy(filterAttr = filterInfo)
    case a: AttrSetManLocalDate      => a.copy(filterAttr = filterInfo)
    case a: AttrSetManLocalTime      => a.copy(filterAttr = filterInfo)
    case a: AttrSetManLocalDateTime  => a.copy(filterAttr = filterInfo)
    case a: AttrSetManOffsetTime     => a.copy(filterAttr = filterInfo)
    case a: AttrSetManOffsetDateTime => a.copy(filterAttr = filterInfo)
    case a: AttrSetManZonedDateTime  => a.copy(filterAttr = filterInfo)
    case a: AttrSetManUUID           => a.copy(filterAttr = filterInfo)
    case a: AttrSetManURI            => a.copy(filterAttr = filterInfo)
    case a: AttrSetManByte           => a.copy(filterAttr = filterInfo)
    case a: AttrSetManShort          => a.copy(filterAttr = filterInfo)
    case a: AttrSetManChar           => a.copy(filterAttr = filterInfo)
  }

  def copySetTacWithFilter(a: AttrSetTac, filterInfo: Option[(Int, List[String], Attr)]): Attr = a match {
    case a: AttrSetTacID             => a.copy(filterAttr = filterInfo)
    case a: AttrSetTacString         => a.copy(filterAttr = filterInfo)
    case a: AttrSetTacInt            => a.copy(filterAttr = filterInfo)
    case a: AttrSetTacLong           => a.copy(filterAttr = filterInfo)
    case a: AttrSetTacFloat          => a.copy(filterAttr = filterInfo)
    case a: AttrSetTacDouble         => a.copy(filterAttr = filterInfo)
    case a: AttrSetTacBoolean        => a.copy(filterAttr = filterInfo)
    case a: AttrSetTacBigInt         => a.copy(filterAttr = filterInfo)
    case a: AttrSetTacBigDecimal     => a.copy(filterAttr = filterInfo)
    case a: AttrSetTacDate           => a.copy(filterAttr = filterInfo)
    case a: AttrSetTacDuration       => a.copy(filterAttr = filterInfo)
    case a: AttrSetTacInstant        => a.copy(filterAttr = filterInfo)
    case a: AttrSetTacLocalDate      => a.copy(filterAttr = filterInfo)
    case a: AttrSetTacLocalTime      => a.copy(filterAttr = filterInfo)
    case a: AttrSetTacLocalDateTime  => a.copy(filterAttr = filterInfo)
    case a: AttrSetTacOffsetTime     => a.copy(filterAttr = filterInfo)
    case a: AttrSetTacOffsetDateTime => a.copy(filterAttr = filterInfo)
    case a: AttrSetTacZonedDateTime  => a.copy(filterAttr = filterInfo)
    case a: AttrSetTacUUID           => a.copy(filterAttr = filterInfo)
    case a: AttrSetTacURI            => a.copy(filterAttr = filterInfo)
    case a: AttrSetTacByte           => a.copy(filterAttr = filterInfo)
    case a: AttrSetTacShort          => a.copy(filterAttr = filterInfo)
    case a: AttrSetTacChar           => a.copy(filterAttr = filterInfo)
  }

  def prepareRef(ref: Ref): Ref = {
    path ++= List(ref.refAttr, ref.ref)
    ref
  }
  def prepareBackRef(backRef: BackRef): BackRef = {
    path = path.dropRight(2)
    backRef
  }
  def prepareNested(nested: Nested): Nested = {
    path ++= List(nested.ref.refAttr, nested.ref.ref)
    Nested(nested.ref, prepare(nested.elements, Nil))
  }
  def prepareOptNested(nested: OptNested): OptNested = {
    path ++= List(nested.ref.refAttr, nested.ref.ref)
    OptNested(nested.ref, prepare(nested.elements, Nil))
  }
}
