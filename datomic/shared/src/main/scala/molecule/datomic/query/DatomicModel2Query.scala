package molecule.datomic.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.query.Model2Query
import molecule.datomic.query.casting._
import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer


class DatomicModel2Query[Tpl](elements0: List[Element])
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
    with NestOpt_[Tpl]
    with MoleculeLogging {

  final lazy val preInputs: Seq[AnyRef] = renderRules(rules ++ preRules) ++ preArgs
  final lazy val inputs   : Seq[AnyRef] = renderRules(rules) ++ args

  // Some specialized expressions require a pre-query to extract entity ids for the main query
  // Returns (preQuery, mainQuery, query string for inspection)
  final def getQueries(
    optimized: Boolean,
    altElements: List[Element] = Nil
  ): (String, String, String) = {
    val elements = if (altElements.isEmpty) elements0 else altElements

    @tailrec
    def prepare(elements: List[Element], acc: List[Element]): List[Element] = {
      elements match {
        case element :: tail =>
          element match {
            case a: Attr       => prepare(tail, acc ++ prepareAttr(a))
            case e: Composite  => prepare(tail, acc :+ prepareComposite(e))
            case n: Nested     => prepare(tail, acc :+ prepareNested(n))
            case n: NestedOpt  => prepare(tail, acc :+ prepareNestedOpt(n))
            case t: TxMetaData => prepare(tail, acc :+ prepareTxMetaData(t))
            case refOrBackRef  => prepare(tail, acc :+ refOrBackRef)
          }
        case Nil             => acc
      }
    }

    def prepareAttr(a: Attr): List[Attr] = {
      availableAttrs += a.name
      if (a.ns == "_Generic" && a.attr == "tx") {
        addTxVar = true
        List(a)
      } else if (a.filterAttr.nonEmpty) {
        val fa = a.filterAttr.get
        if (fa.filterAttr.nonEmpty) {
          throw ModelError(s"Nested filter attributes not allowed in ${a.ns}.${a.attr}")
        }
        val filterAttr = fa.name
        filterAttrVars.get(filterAttr).fold {
          // Create datomic variable for this expression attribute
          filterAttrVars = filterAttrVars + (filterAttr -> vv)
        }(_ =>
          throw ModelError(s"Can't refer to ambiguous filter attribute $filterAttr")
        )
        if (fa.ns == a.ns) {
          // Add adjacent expression attribute after this attribute
          List(a, fa)
        } else if (fa.isInstanceOf[Mandatory]) {
          throw ModelError(s"Filter attribute $filterAttr pointing to other namespace should be tacit.")
        } else if (fa.op != V) {
          throw ModelError("Filtering inside cross-namespace attribute filter not allowed. Found:\n  " + fa)
        } else {
          // Expect expression attribute in other namespace
          expectedFilterAttrs += fa.name
          List(a)
        }
      } else {
        // Attribute is available for expressions
        List(a)
      }
    }
    def prepareComposite(composite: Composite): Composite = Composite(prepare(composite.elements, Nil))
    def prepareNested(nested: Nested): Nested = Nested(nested.ref, prepare(nested.elements, Nil))
    def prepareNestedOpt(nested: NestedOpt): NestedOpt = NestedOpt(nested.ref, prepare(nested.elements, Nil))
    def prepareTxMetaData(t: TxMetaData): TxMetaData = {
      addTxVar = true
      TxMetaData(prepare(t.elements, Nil))
    }

    val elements1 = prepare(elements, Nil)

    //    availableAttrs.foreach(println)
    //    println("---------")
    //    expectedExprAttrs.foreach(println)
    //    println("---------")
    //    expectedExprAttrs.intersect(availableAttrs).foreach(println)
    //    println("--------------------------------------")

    if (expectedFilterAttrs.nonEmpty && expectedFilterAttrs.intersect(availableAttrs) != expectedFilterAttrs) {
      throw ModelError("Please add missing filter attributes:\n  " + expectedFilterAttrs.mkString("\n  "))
    }

    // Remember first entity id variable for subsequent composite groups
    firstEid = vv

    // Recursively resolve molecule elements. Beware that this is mutational
    resolve(List(firstEid), elements1)

    // Build Datomic query string(s)
    val mainQuery = renderQuery(
      nestedIds, find, widh, in ++ inPost, where ++ wherePost, rules.nonEmpty, optimized
    )

    // Pre-query if needed
    val preQuery = if (preWhere.isEmpty) "" else {
      val preSortIds = ArrayBuffer.empty[String]
      val preFind1   = ArrayBuffer(preFind)
      val hasRules   = preRules.nonEmpty
      val preQuery   = renderQuery(
        preSortIds, preFind1, widh, in ++ preIn, where ++ preWhere, hasRules, optimized
      )
      preQuery
    }

    val preQueryStrs = if (preQuery.nonEmpty) Seq(s"\nPRE-QUERY:\n$preQuery") else Nil
    val inputsStrs   = if (inputs.nonEmpty) {
      "" +: inputs.map {
        case a: Array[_] => a.toList.toString
        case other       => other.toString
      }
    } else Nil
    val queryStrs    = ((elements1 :+ "" :+ mainQuery) ++ inputsStrs ++ preQueryStrs).mkString("\n").trim
    logger.debug(queryStrs)

    (preQuery, mainQuery, queryStrs)
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
    elements: List[Element]
  ): List[Var] = elements match {
    case element :: tail => element match {
      case a: AttrOne                           => a match {
        case a: AttrOneMan => resolve(resolveAttrOneMan(es, a), tail)
        case a: AttrOneOpt => resolve(resolveAttrOneOpt(es, a), tail)
        case a: AttrOneTac => resolve(resolveAttrOneTac(es, a), tail)
      }
      case a: AttrSet                           => a match {
        case a: AttrSetMan => resolve(resolveAttrSetMan(es, a), tail)
        case a: AttrSetOpt => resolve(resolveAttrSetOpt(es, a), tail)
        case a: AttrSetTac => resolve(resolveAttrSetTac(es, a), tail)
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


  final private def resolveComposite(compositeElements: List[Element]): List[Var] = {
    aritiesComposite()
    // Use first entity id in each composite sub group
    resolve(List(firstEid), compositeElements)
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
    if (isNested)
      noMixedNestedModes
    validateRefNs(nestedRef, nestedElements)

    // On top level, move past nested pull date to tx meta data (if any)
    attrIndex += 1

    aritiesNested()
    val e = es.last
    resolveNestedOptRef(e, nestedRef)
    resolveNestedOptElements(e, nestedRef, nestedElements)
    es
  }

  final private def resolveTxMetaData(txElements: List[Element]): List[Var] = {
    isTxMetaData = true
    // Use txVar as first entity id var for composite elements
    firstEid = txVar
    resolve(List(txVar), txElements)
  }

  final private def validateRefNs(ref: Ref, nestedElements: List[Element]): Unit = {
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
      throw ModelError(s"`$refName` can only nest to `${ref.refNs}`. Found: `$nestedNs`")
  }

}