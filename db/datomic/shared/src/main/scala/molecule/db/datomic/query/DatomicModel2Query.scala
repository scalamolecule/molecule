package molecule.db.datomic.query

import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.query.Model2Query
import scala.annotation.tailrec;


class DatomicModel2Query[Tpl](elements: Seq[Element])
  extends Model2Query[Tpl]
    with ExprOne[Tpl]
    with TypeResolvers
    with Sort[Tpl]
    with Base[Tpl]
    with Cast_[Tpl] {

  final lazy protected val query         : String      = renderQuery(true)
  final lazy protected val queryRaw      : String      = renderQuery(false)
  final lazy protected val rulesAndInputs: Seq[AnyRef] = renderRules ++ inputs

  final protected def renderQuery(optimized: Boolean): String = {
    // Recursively resolve molecule
    resolve(List(vv), elements)

    // Build Datomic query string
    val find1       = (sortIds ++ find).map(v => if (v.startsWith("?")) v else s"\n        $v").mkString(" ").trim
    val widh1       = if (widh.isEmpty) "" else widh.distinct.mkString("\n :with  ", " ", "")
    val hasRules    = rules.nonEmpty
    val r           = if (hasRules) "% " else ""
    val in1         = if (!hasRules && in.isEmpty) "" else in.mkString("\n :in    $ " + r, " ", "")
    val where1      = where.distinct
    val clausePairs = if (optimized) where1.sortBy(_._2) else where1
    val where2      = clausePairs.map(_._1).mkString("\n        ")
    val q           =
      s"""[:find  $find1 $widh1 $in1
         | :where $where2]""".stripMargin


    println("\n--- QUERY ---------------------------------------------------------")
    elements.foreach(println)
    println("---")
    println(q)
    if (rulesAndInputs.nonEmpty) {
      println("---")
      rulesAndInputs.foreach {
        case a: Array[_] =>
          //          a.toList.headOption.foreach(_.getClass)
          println(a.toList)
        case other       =>
          //          println(other.getClass)
          println(other)
      }
    }
    q
  }

  final protected def renderRules: Seq[String] = {
    if (rules.isEmpty) Nil else Seq(rules.mkString("[\n  ", "\n  ", "\n]"))
  }

  @tailrec
  final protected def resolve(es: List[Var], elements: Seq[Element]): List[Var] = elements match {
    case element :: tail => element match {
      case a: AtomOne => a match {
        case a: AtomOneMan => resolve(resolveAtomOneMan(es, a), tail)
        case a: AtomOneOpt => resolve(resolveAtomOneOpt(es, a), tail)
        case a: AtomOneTac => resolve(resolveAtomOneTac(es, a), tail)
        case other         => unexpected(other)
      }
      case b: Bond    => es
      case other      => unexpected(other)
    }
    case Nil             => es
  }
}