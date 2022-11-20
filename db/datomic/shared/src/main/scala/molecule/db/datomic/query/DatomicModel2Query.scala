package molecule.db.datomic.query

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.query.Model2Query
import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer


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
    with CastNestedOptBranch_[Tpl]
    with CastNestedOptLeaf_[Tpl]
    with Nest[Tpl]
    with NestOpt_[Tpl] {

  final lazy protected val preInputs: Seq[AnyRef] = renderRules(rules ++ preRules) ++ preArgs
  final lazy protected val inputs   : Seq[AnyRef] = renderRules(rules) ++ args

  final protected def getQueries(optimized: Boolean): (String, String) = {
    // Reset mutable accumulators
    reset

    // Recursively resolve molecule elements
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
    val find1       = (nestedIds ++ find ++ renderPull)
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

  final private def renderPull: Seq[String] = {
    import LambdasOne._
    @tailrec
    def addPullAttrs(
      refAttr: String,
      elements: Seq[Element],
      level: Int,
      acc: String = ""
    ): (String, Option[NestedOpt]) = {
      val i = "  " * (level + 5)
      elements match {
        case head :: tail =>
          head match {
            case nested: NestedOpt =>
              (acc, Some(nested))
            case a: AttrOneMan     =>
              pullCasts += (a match {
                case _: AttrOneManString     => it2String
                case _: AttrOneManInt        => it2Int
                case _: AttrOneManLong       => it2Long
                case _: AttrOneManFloat      => it2Float
                case _: AttrOneManDouble     => it2Double
                case _: AttrOneManBoolean    => it2Boolean
                case _: AttrOneManBigInt     => it2BigInt
                case _: AttrOneManBigDecimal => it2BigDecimal
                case _: AttrOneManDate       => it2Date
                case _: AttrOneManUUID       => it2UUID
                case _: AttrOneManURI        => it2URI
                case _: AttrOneManByte       => it2Byte
                case _: AttrOneManShort      => it2Short
                case _: AttrOneManChar       => it2Char
              })
              val pull1 = s"\n$i(:${a.ns}/${a.attr} :limit nil)"
              addPullAttrs(refAttr, tail, level, acc + pull1)

            case a: AttrOneOpt     =>
              pullCasts += (a match {
                case _: AttrOneOptString     => it2OptString
                case _: AttrOneOptInt        => it2OptInt
                case _: AttrOneOptLong       => it2OptLong
                case _: AttrOneOptFloat      => it2OptFloat
                case _: AttrOneOptDouble     => it2OptDouble
                case _: AttrOneOptBoolean    => it2OptBoolean
                case _: AttrOneOptBigInt     => it2OptBigInt
                case _: AttrOneOptBigDecimal => it2OptBigDecimal
                case _: AttrOneOptDate       => it2OptDate
                case _: AttrOneOptUUID       => it2OptUUID
                case _: AttrOneOptURI        => it2OptURI
                case _: AttrOneOptByte       => it2OptByte
                case _: AttrOneOptShort      => it2OptShort
                case _: AttrOneOptChar       => it2OptChar
              })
              val pull1 = s"""\n$i(:${a.ns}/${a.attr} :limit nil :default "__none__")"""
              addPullAttrs(refAttr, tail, level, acc + pull1)

            case other             => throw MoleculeException(
              "Unexpected element in optional nested molecule: " + other
            )
          }
        case Nil          => (acc, None)
      }
    }
    def resolvePull(ref: Ref, elements: Seq[Element], level: Int): String = {
      val indent  = "  " * (level + 5)
      val refAttr = s":${ref.ns}/${ref.refAttr}"
      addPullAttrs(refAttr, elements, level + 1, "") match {
        case (pullAttrs, None) =>
          s"""\n$indent{($refAttr :limit nil :default "__none__") [$pullAttrs]}"""

        case (pullAttrs, Some(NestedOpt(ref1, elements1))) =>
          pullCastss = pullCastss :+ pullCasts.toList
          pullCasts.clear()
          val nestedPulls = resolvePull(ref1, elements1, level + 1)
          s"""\n$indent{($refAttr :limit nil :default "__none__") [$pullAttrs$nestedPulls]}"""
      }
    }
    pull.fold(Seq.empty[String]) { case (e, NestedOpt(ref, elements)) =>
      Seq(s"(pull $e [${resolvePull(ref, elements, 0)}])")
    }
  }

  @tailrec
  final protected def resolve(es: List[Var], elements: Seq[Element]): List[Var] = elements match {
    case element :: tail => element match {
      case a: AttrOne            => a match {
        case a: AttrOneMan =>
          resolve(resolveAttrOneMan(es, a), tail)
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
      case n: NestedOpt          =>
        resolve(resolveNestedOptRef(es, n), tail)
      case other                 => unexpected(other)
    }
    case Nil             => es
  }

  def resolveNested(es: List[Var], ref: Ref, elements: Seq[Element]): List[Var] = {
    resolve(resolveNestedRef(es, ref), elements)
  }

  //  def resolveNestedOpt(es: List[Var], nestedOpt: NestedOpt, elements: Seq[Element]): List[Var] = {
  //    resolve(resolveNestedOptRef(es, nestedOpt), elements)
  //  }
}