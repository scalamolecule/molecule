package molecule.db.datomic.query

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.query.Model2Query
import molecule.db.datomic.query.casting._
import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer


class DatomicModel2Query[Tpl](elements: Seq[Element])
  extends Model2Query[Tpl]
    with ResolveExprOne[Tpl]
    with ResolveExprSet[Tpl]
    with ResolveRef[Tpl]
    with ResolveNestedPull[Tpl]
    with Base[Tpl]
    with CastNestedBranch_[Tpl]
    with CastRow2Tpl_[Tpl]
    with CastNestedOptBranch_[Tpl]
    with CastNestedOptLeaf_[Tpl]
    with Nest[Tpl]
    with NestOpt_[Tpl] {

  final lazy protected val preInputs: Seq[AnyRef] = renderRules(rules ++ preRules) ++ preArgs
  final lazy protected val inputs   : Seq[AnyRef] = renderRules(rules) ++ args

  final def getQueries(optimized: Boolean): (String, String) = {

    // Add 4th tx var to first attribute if tx value is needed
    @tailrec
    def checkTx(elements: Seq[Element]): Unit = {
      elements match {
        case element :: tail =>
          element match {
            case _: TxMetaData                                      => addTxVar = true
            case AttrOneManLong("_Generic", "tx", _, _, _, _, _, _) => addTxVar = true
            case AttrOneTacLong("_Generic", "tx", _, _, _, _, _, _) => addTxVar = true
            case Composite(elements)                                => checkTx(elements ++ tail)
            case Nested(_, elements)                                => checkTx(tail ++ elements)
            case _                                                  => checkTx(tail)
          }
        case Nil             => ()
      }
    }
    checkTx(elements)

    // Remember first entity id variable for subsequent composite groups
    firstEid = vv

    // Recursively resolve molecule elements
    resolve(List(firstEid), elements)

    // Build Datomic query string(s)
    val mainQuery = renderQuery(
      nestedIds, find, widh, in ++ inPost, where ++ wherePost, rules.nonEmpty, optimized
    )

    // Pre-query if needed
    val preQuery = if (preWhere.isEmpty) "" else {
      val preSortIds = ArrayBuffer.empty[String]
      val preFind    = ArrayBuffer("?a")
      val hasRules   = preRules.nonEmpty
      val preQuery   = renderQuery(
        preSortIds, preFind, widh, in ++ preIn, where ++ preWhere, hasRules, optimized
      )
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

    println("\n\n--- QUERY ------------------------------------------------------------------------")
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

  final def getEidQueryWithInputs: (Att, Seq[AnyRef]) = {
    (getQueries(false)._2, inputs)
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
    elements: Seq[Element]
  ): List[Var] = elements match {
    case element :: tail => element match {
      case a: AttrOne                           => a match {
        case a: AttrOneMan => resolve(resolveAttrOneMan(es, a), tail)
        case a: AttrOneOpt => resolve(resolveAttrOneOpt(es, a), tail)
        case a: AttrOneTac => resolve(resolveAttrOneTac(es, a), tail)
        case other         => unexpectedElement(other)
      }
      case a: AttrSet                           => a match {
        case a: AttrSetMan => resolve(resolveAttrSetMan(es, a), tail)
        case a: AttrSetOpt => resolve(resolveAttrSetOpt(es, a), tail)
        case a: AttrSetTac => resolve(resolveAttrSetTac(es, a), tail)
        case other         => unexpectedElement(other)
      }
      case ref: Ref                             => resolve(resolveRef(es, ref), tail)
      case _: BackRef                           => resolve(es.init, tail)
      case Composite(compositeElements)         => resolve(resolveComposite(compositeElements), tail)
      case Nested(ref, nestedElements)          => resolve(resolveNested(es, ref, nestedElements), tail)
      case NestedOpt(nestedRef, nestedElements) => resolve(resolveNestedOpt(es, nestedRef, nestedElements), tail)
      case TxMetaData(txElements)               => resolveTxMetaData(txElements)
      case other                                => unexpectedElement(other)
    }
    case Nil             => es
  }


  final private def resolveComposite(compositeElements: Seq[Element]): List[Var] = {
    aritiesComposite()
    // Use first entity id in each composite sub group
    resolve(List(firstEid), compositeElements)
  }

  final private def resolveNested(
    es: List[Var], ref: Ref, nestedElements: Seq[Element]
  ): List[Var] = {
    isNested = true
    if (isNestedOpt)
      noMixedNestedModes
    validateRefNs(ref, nestedElements)

    aritiesNested()
    resolve(resolveNestedRef(es, ref), nestedElements)
  }

  final private def resolveNestedOpt(
    es: List[Var], nestedRef: Ref, nestedElements: Seq[Element]
  ): List[Var] = {
    isNestedOpt = true
    if (isNested)
      noMixedNestedModes
    validateRefNs(nestedRef, nestedElements)

    aritiesNested()
    val e = es.last
    resolveNestedOptRef(e, nestedRef)
    resolveNestedOptElements(e, nestedRef, nestedElements)
    es
  }

  final private def resolveTxMetaData(txElements: Seq[Element]): List[Var] = {
    isTxMetaData = true
    // Use txVar as first entity id var for composite elements
    firstEid = txVar
    resolve(List(txVar), txElements)
  }

  final private def validateRefNs(ref: Ref, nestedElements: Seq[Element]): Unit = {
    val refName  = ref.refAttr.capitalize
    val nestedNs = nestedElements.head match {
      case a: Attr       => a.ns
      case r: Ref        => r.ns
      case Composite(es) => es.head match {
        case a: Attr => a.ns
        case other   => unexpectedElement(other)
      }
      case other         => unexpectedElement(other)
    }
    if (ref.refNs != nestedNs)
      throw MoleculeException(s"`$refName` can only nest to `${ref.refNs}`. Found: `$nestedNs`")
  }

}