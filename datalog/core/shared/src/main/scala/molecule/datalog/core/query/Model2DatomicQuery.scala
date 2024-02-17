package molecule.datalog.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.query.Model2QueryBase
import molecule.datalog.core.query.casting._
import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer


class Model2DatomicQuery[Tpl](elements0: List[Element])
  extends Model2QueryBase
    with ResolveExprOne[Tpl]
    with ResolveExprOne_id[Tpl]
    with ResolveExprSet[Tpl]
    with ResolveRef[Tpl]
    with ResolveNestedPull[Tpl]
    with DatomicQueryBase
    with CastNestedBranch_
    with CastRow2Tpl_
    with CastNestedOptBranch_
    with CastNestedOptLeaf_
    with Nest[Tpl]
    with NestOpt_[Tpl]
    with MoleculeLogging {

  final lazy val preInputs: Seq[AnyRef] = renderRules(rules ++ preRules) ++ preArgs
  final lazy val inputs   : Seq[AnyRef] = renderRules(rules) ++ args

  // Some specialized expressions require a pre-query to extract entity ids for the main query
  // Returns (preQuery, mainQuery, query string for inspection)
  final def getDatomicQueries(
    optimized: Boolean,
    altElements: List[Element] = Nil
  ): (String, String, String) = {
    val elements          = if (altElements.isEmpty) elements0 else altElements
    val (elements1, _, _) = validateQueryModel(elements, Some(addFilterAttrCallback))

    // Remember first entity id variable
    firstId = vv
    varPath = List(firstId)
    path = List(getInitialNs(elements1))

    // Recursively resolve molecule elements
    resolve(List(firstId), elements1)

    // Build Datomic query string(s) from mutated query sections
    val mainQuery = renderQuery(
      nestedIds, find, widh, in ++ inPost, where ++ wherePost, rules.nonEmpty, optimized
    )

    // Pre-query if needed
    val preQuery = if (preWhere.isEmpty) "" else {
      val preSortIds = ArrayBuffer.empty[String]
      val preFind1   = ArrayBuffer(preFind)
      val hasRules   = preRules.nonEmpty
      renderQuery(
        preSortIds, preFind1, widh, in ++ preIn, where ++ preWhere, hasRules, optimized
      )
    }

    val preQueryStrs = if (preQuery.nonEmpty) Seq(
      s"\nPRE-QUERY:\n$preQuery\n\nMAIN QUERY (takes ids from pre-query as input):") else Nil
    val inputsStrs   = if (inputs.nonEmpty) {
      "" +: inputs.map {
        case a: Array[_] => a.toList.toString
        case other       => other.toString
      }
    } else Nil
    val queryStrs    = (preQueryStrs ++ (mainQuery +: inputsStrs)).mkString("\n").trim
    logger.debug(queryStrs)

    (preQuery, mainQuery, queryStrs)
  }

  final private def addFilterAttrCallback: (List[String], Model.Attr) => Unit =
    (pathAttr: List[String], a: Attr) => {
      filterAttrVars.get(pathAttr).fold {
        // Create datomic variable for this filter attribute
        filterAttrVars = filterAttrVars + (pathAttr -> vv)
      }(_ => throw ModelError(s"Can't refer to ambiguous filter attribute $pathAttr"))
    }

  final def getIdQueryWithInputs: (Att, Seq[AnyRef]) = {
    (getDatomicQueries(false)._2, inputs)
  }

  final private def renderQuery(
    nestedIds: ArrayBuffer[String],
    find: ArrayBuffer[String],
    widh: ArrayBuffer[String],
    in: ArrayBuffer[String],
    where: ArrayBuffer[(String, Int)],
    hasRules: Boolean,
    optimized: Boolean
  ): String = {
    val find1       = (nestedIds ++ find)
      .map(v => if (v.startsWith("?")) v else s"\n        $v")
      .mkString(" ").trim
    val widh1       = if (widh.isEmpty) "" else widh.distinct.mkString("\n :with  ", " ", "")
    val r           = if (hasRules) "% " else ""
    val in1         = if (!hasRules && in.isEmpty) "" else in.mkString("\n :in    $ " + r, " ", "")
    val where1      = where.distinct
    val clausePairs = if (optimized) where1.sortBy(_._2) else where1
    val where2      = clausePairs.map(_._1).mkString("\n        ")
    s"""[:find  $find1 $widh1 $in1
       | :where $where2]""".stripMargin
  }

  final private def renderRules(rules: ArrayBuffer[String]): Seq[String] = {
    if (rules.isEmpty) Nil else Seq(rules.mkString("[\n  ", "\n  ", "\n]"))
  }

  @tailrec
  final private def resolve(
    es: List[Var],
    elements: List[Element]
  ): List[Var] = elements match {
    case element :: tail => element match {
      case a: AttrOne                           =>
        a.attr match {
          case "id" =>
            if (a.filterAttr.nonEmpty) throw ModelError(noIdFiltering)
            a match {
              case a: AttrOneMan => resolve(resolveIdMan(es, a), tail)
              case a: AttrOneTac => resolve(resolveIdTac(es, a), tail)
              case _             => unexpectedElement(a)
            }
          case _    =>
            a.filterAttr.collect {
              case filterAttr if filterAttr._3.attr == "id" => throw ModelError(noIdFiltering)
            }
            a match {
              case a: AttrOneMan => resolve(resolveAttrOneMan(es, a), tail)
              case a: AttrOneOpt => resolve(resolveAttrOneOpt(es, a), tail)
              case a: AttrOneTac => resolve(resolveAttrOneTac(es, a), tail)
            }
        }
      case a: AttrSet                           =>
        isRefAttr = a.refNs.isDefined
        a match {
          case a: AttrSetMan => resolve(resolveAttrSetMan(es, a), tail)
          case a: AttrSetOpt => resolve(resolveAttrSetOpt(es, a), tail)
          case a: AttrSetTac => resolve(resolveAttrSetTac(es, a), tail)
        }
      case ref: Ref                             => resolve(resolveRef(es, ref), tail)
      case _: BackRef                           => resolve(resolveBackRef(es), tail)
      case Nested(ref, nestedElements)          => resolve(resolveNested(es, ref, nestedElements), tail)
      case NestedOpt(nestedRef, nestedElements) => resolve(resolveNestedOpt(es, nestedRef, nestedElements), tail)
    }
    case Nil             => es
  }


  final private def resolveNested(
    es: List[Var], ref: Ref, nestedElements: List[Element]
  ): List[Var] = {
    isNested = true
    if (isNestedOpt)
      noMixedNestedModes
    validateRefNs(ref, nestedElements)

    aritiesNested()
    resolve(resolveNestedRef(es, ref), nestedElements)
  }

  final private def resolveNestedOpt(
    es: List[Var], nestedRef: Ref, nestedElements: List[Element]
  ): List[Var] = {
    isNestedOpt = true
    if (isNested) {
      noMixedNestedModes
    }
    if (expectedFilterAttrs.nonEmpty) {
      throw ModelError("Filter attributes not allowed in optional nested queries.")
    }
    validateRefNs(nestedRef, nestedElements)

    // On top level, move past nested pull date to tx meta data (if any)
    attrIndex += 1

    aritiesNested()
    val e = es.last
    resolveNestedOptRef(e, nestedRef)
    resolveNestedOptElements(e, nestedRef, nestedElements)
    es
  }

  final private def validateRefNs(ref: Ref, nestedElements: List[Element]): Unit = {
    val refName  = ref.refAttr.capitalize
    val nestedNs = nestedElements.head match {
      case a: Attr => a.ns
      case r: Ref  => r.ns
      case other   => unexpectedElement(other)
    }
    if (ref.refNs != nestedNs)
      throw ModelError(s"`$refName` can only nest to `${ref.refNs}`. Found: `$nestedNs`")
  }

}