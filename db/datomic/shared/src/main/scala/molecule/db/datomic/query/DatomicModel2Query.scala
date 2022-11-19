package molecule.db.datomic.query

import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.query.Model2Query
import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer;


class DatomicModel2Query[Tpl](elements: Seq[Element])
  extends Model2Query[Tpl]
    with ResolveExprOne[Tpl]
    with ResolveExprSet[Tpl]
    with ResolveRef[Tpl]
    with Sort_[Tpl]
    with Base[Tpl]
    with CastFlat_[Tpl]
    with CastNestedBranch_[Tpl]
    with CastNestedLeaf_[Tpl]
    with Nest[Tpl]
    {

  final lazy protected val preInputs: Seq[AnyRef] = renderRules(rules ++ preRules) ++ preArgs
  final lazy protected val inputs   : Seq[AnyRef] = renderRules(rules) ++ args

  final protected def getQueries(optimized: Boolean): (String, String) = {
    // Reset mutable accumulators
    reset

    // Recursively resolve molecule
    resolve(List(vv), elements)

    // Build Datomic query string(s)
    val mainQuery = renderQuery(nestedIds, find, widh, in ++ inPost, where ++ wherePost, rules.nonEmpty, optimized)

    // Pre-query if needed
    val preQuery = if (preWhere.isEmpty) "" else {
      val preSortIds = ArrayBuffer.empty[String]
      val preFind    = ArrayBuffer("?a")
      val hasRules   = preRules.nonEmpty
      val preQuery   = renderQuery(preSortIds, preFind, widh, in ++ preIn, where ++ preWhere, hasRules, optimized)
      println("\n--- PRE-QUERY -------------------------------------------------")
      println(preQuery)
      if (preInputs.nonEmpty) {
        preInputs.foreach {
          case a: Array[_] => println(a.toList)
          case other       => println(other)
        }
      }
      preQuery
    }

    println("\n--- QUERY -------------------------------------------------------")
    elements.foreach(println)
    println("---")
    println(mainQuery)
    if (inputs.nonEmpty) {
      println("---")
      inputs.foreach {
        case a: Array[_] => println(a.toList)
        case other       => println(other)
      }
    }
    (preQuery, mainQuery)
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
    val find1       = (nestedIds ++ find).map(v => if (v.startsWith("?")) v else s"\n        $v").mkString(" ").trim
    val widh1       = if (widh.isEmpty) "" else widh.distinct.mkString("\n :with  ", " ", "")
    val r           = if (hasRules) "% " else ""
    val in1         = if (!hasRules && in.isEmpty) "" else in.mkString("\n :in    $ " + r, " ", "")
    val where1      = where.distinct
    val clausePairs = if (optimized) where1.sortBy(_._2) else where1
    val where2      = clausePairs.map(_._1).mkString("\n        ")
    s"""[:find  $find1 $widh1 $in1
       | :where $where2]""".stripMargin
  }

  final protected def renderRules(rules: ArrayBuffer[String]): Seq[String] = {
    if (rules.isEmpty) Nil else Seq(rules.mkString("[\n  ", "\n  ", "\n]"))
  }

  @tailrec
  final protected def resolve(es: List[Var], elements: Seq[Element]): List[Var] = elements match {
    case element :: tail => element match {
      case a: AttrOne            => a match {
        case a: AttrOneMan => resolve(resolveAttrOneMan(es, a), tail)
        case a: AttrOneOpt => resolve(resolveAttrOneOpt(es, a), tail)
        case a: AttrOneTac => resolve(resolveAttrOneTac(es, a), tail)
        case other         => unexpected(other)
      }
      case a: AttrSet            => a match {
        case a: AttrSetMan => resolve(resolveAttrSetMan(es, a), tail)
        case a: AttrSetOpt => resolve(resolveAttrSetOpt(es, a), tail)
        case a: AttrSetTac => resolve(resolveAttrSetTac(es, a), tail)
        case other         => unexpected(other)
      }
      case ref: Ref              => resolve(resolveRef(es, ref), tail)
      case _: BackRef            => resolve(resolveBackRef(es), tail)
      case Nested(ref, elements) => resolve(resolveNested(es, ref, elements), tail)
      case other                 => unexpected(other)
    }
    case Nil             => es
  }

  def resolveNested(es: List[Var], ref: Ref, elements: Seq[Element]): List[Var] = {
    resolve(resolveNestedRef(es, ref), elements)
  }
}