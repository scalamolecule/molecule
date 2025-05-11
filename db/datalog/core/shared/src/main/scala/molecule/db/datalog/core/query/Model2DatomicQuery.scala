package molecule.db.datalog.core.query

import molecule.db.base.error.ModelError
import molecule.db.core.ast.DataModel
import molecule.db.core.query.QueryExpr
import molecule.db.core.util.MoleculeLogging
import molecule.db.datalog.core.query.casting.*
import molecule.db.datalog.core.query.casting.*
import scala.collection.mutable.ArrayBuffer
import molecule.db.core.ast._


class Model2DatomicQuery[Tpl](elements0: List[Element])
  extends QueryExpr
    with QueryExprOne[Tpl]
    with QueryExprOneId[Tpl]
    with QueryExprSet[Tpl]
    with QueryExprSeq[Tpl]
    with QueryExprMap[Tpl]
    with QueryExprRef[Tpl]
    with ResolveNestedPull[Tpl]
    with ResolveOptRefPull[Tpl]
    with CastNestedBranch_
    with CastRow2AnyTpl_
    with CastOptNestedBranch_
    with CastOptNestedLeaf_
    with CastOptRefBranch_
    with CastOptRefLeaf_
    with Nest[Tpl]
    with NestOpt
    with OptRefNested[Tpl]
    with DatomicQueryBase
    with MoleculeLogging {

  final lazy val preInputs: Seq[AnyRef] = renderRules(rules ++ preRules) ++ preArgs
  final lazy val inputs   : Seq[AnyRef] = renderRules(rules) ++ args


  // Some specialized expressions require a pre-query to extract entity ids for the main query
  // Returns (preQuery, mainQuery, query string for inspection)
  final def getDatomicQueries(
    optimized: Boolean,
    altElements: List[Element] = Nil,
    validate: Boolean = true
  ): (String, String, String) = {
    val elements  = if (altElements.isEmpty) elements0 else altElements
    val elements1 = if (validate)
      validateQueryModel(elements, Some(addFilterAttrCallback()))._1 else elements

    // Remember first entity id variable
    firstId = vv
    varPath = List(firstId)
    path = List(getInitialEntity(elements1))

    es = List(firstId)
    // Recursively resolve molecule elements
    resolve(elements1)

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

  final private def addFilterAttrCallback(): (List[String], Attr) => Unit = {
    (pathAttr: List[String], a: Attr) => {
      filterAttrVars.get(pathAttr).fold {
        // Create datomic variable for this filter attribute
        filterAttrVars = filterAttrVars + (pathAttr -> vv)
      }(_ => throw ModelError(s"Can't refer to ambiguous filter attribute $pathAttr"))
    }
  }

  final def getIdQueryWithInputs: (Att, Seq[AnyRef]) = {
    (getDatomicQueries(false, validate = false)._2, inputs)
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


  final protected def sorterOneOptLong(
    at: Attr,
    attrIndex: Int
  ): Option[(Int, Int => (Row, Row) => Int)] = {
    if (at.ref.isDefined)
      sortOneOptLongRef(at, attrIndex)
    else
      sortOneOptLong(at, attrIndex)
  }
}