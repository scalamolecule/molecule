package molecule.base.codegen.render

import molecule.base.ast.SchemaAST._


case class Dsl_Arities(schema: MetaSchema, partPrefix: String, namespace: MetaNs, arity: Int)
  extends DslFormatting(schema, namespace, arity) {

  val man = List.newBuilder[String]
  val opt = List.newBuilder[String]
  val tac = List.newBuilder[String]
  val res = List.newBuilder[String]
  val ref = List.newBuilder[String]

  val first      = arity == 0
  val last       = arity == schema.maxArity
  val secondLast = arity == schema.maxArity - 1

  var hasOne = false
  var hasSet = false

  val maxCardPad = attrs.map(a => a.card match {
    case _: CardOne => hasOne = true; 0
    case _: CardSet => hasSet = true; 5
  }).max

  val pOne = " " * maxCardPad
  val pSet = " " * (maxCardPad - 5)

  attrs.foreach {
    case MetaAttr(attr, card, tpe0, refNs, _, _, _, _, _, _) =>
      val c   = card._marker
      val tpe = getTpe(tpe0)

      val padA = padAttr(attr)
      val pad0 = padType(tpe)
      val pad1 = padType(tpe)
      val pad3 = pad1 + " " * (maxTpe + "Option[]".length - maxTpe)
      val pad4 = " " * (maxTpe + "Option[]".length + maxCardPad)

      val (tM, tO) = card match {
        case _: CardOne => (tpe + pOne, s"Option[$tpe]" + pOne)
        case _: CardSet => (s"Set[$tpe]" + pSet, s"Option[Set[$tpe]]" + pSet)
      }

      lazy val tpesM = s"${`A..V, `}$tM$pad3, $tpe$pad1"
      lazy val tpesO = s"${`A..V, `}$tO$pad0, $tpe$pad1"
      lazy val tpesT = if (arity == 0)
        s"${`A..V`}$pad4  $tpe$pad1"
      else if (arity == schema.maxArity)
        s"${`A..V`}, $tpe$pad1"
      else
        s"${`A..V`}  $pad4, $tpe$pad1"

      lazy val elemsM = s"elements :+ ${attr}_man$padA"
      lazy val elemsO = s"elements :+ ${attr}_opt$padA"
      lazy val elemsT = s"elements :+ ${attr}_tac$padA"

      val nextNextNs = if (secondLast) s"X${arity + 3}" else ns_2
      val nextNs     = if (last) s"X${arity + 2}" else ns_1

      val filters = if (card == CardOne && attr != "id" && refNs.isEmpty) tpe0 match {
        case "String" => "_String "
        case "Int"    => "_Number "
        case "Long"   => "_Number "
        case "BigInt" => "_Number "
        case "Byte"   => "_Number "
        case "Short"  => "_Number "
        case _        => "        "
      } else "        "

      lazy val exprM = s"Expr${c}Man${_1}$filters[$tpesM, $ns_1, $nextNextNs]"
      lazy val exprO = s"Expr${c}Opt${_1}        [$tpesO, $ns_1, $nextNextNs]"
      lazy val exprT = s"Expr${c}Tac${_0}$filters[$tpesT, $ns_0, $nextNs]"

      if (!last) {
        man += s"""lazy val $attr  $padA = new $ns_1[$tpesM]($elemsM) with $exprM with $card"""
        //        if (!genericAttrs.contains(attr))
        if (attr != "id")
          opt += s"""lazy val ${attr}_?$padA = new $ns_1[$tpesO]($elemsO) with $exprO with $card"""
      }
      tac += s"""lazy val ${attr}_ $padA = new $ns_0[$tpesT]($elemsT) with $exprT with $card"""
  }

  if (first) {
    if (hasOne)
      res += s"override protected def _exprOneTac(op: Op, vs: Seq[t]$pOne) = new $ns_0[t](addOne(elements, op, vs)) with CardOne"
    if (hasSet)
      res += s"override protected def _exprSetTac(op: Op, vs: Seq[Set[t]]$pSet) = new $ns_0[t](addSet(elements, op, vs)) with CardSet"

  } else {
    val tInt_ = s"${`A..U`}Int   , Int   "
    val tDoub = s"${`A..U`}Double, Double"
    val tDist = s"${`A..U`}Set[$V], t     "
    val tSet_ = s"${`A..U`}Set[t], t     "
    val tA___ = s"${`A..V`}     , t     "

    def agg1 = s"override protected def _aggrInt   (kw: Kw                    $pOne) = new $ns_0"
    def agg2 = s"override protected def _aggrDouble(kw: Kw                    $pOne) = new $ns_0"
    def agg3 = s"override protected def _aggrDist  (kw: Kw                    $pOne) = new $ns_0"
    def agg4 = s"override protected def _aggrSet   (kw: Kw, n: Option[Int]    $pOne) = new $ns_0"
    def agg5 = s"override protected def _aggrTsort (kw: Kw                    $pOne) = new $ns_0"
    def agg6 = s"override protected def _aggrT     (kw: Kw                    $pOne) = new $ns_0"

    def one1 = s"override protected def _exprOneMan(op: Op, vs: Seq[t]        $pOne) = new $ns_0"
    def one2 = s"override protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]$pOne) = new $ns_0"
    def one3 = s"override protected def _exprOneTac(op: Op, vs: Seq[t]        $pOne) = new $ns_0"
    def one4 = s"override protected def _sort      (sort: String              $pOne) = new $ns_0"

    def set1 = s"override protected def _exprSetMan(op: Op, vs: Seq[Set[t]]        $pSet) = new $ns_0"
    def set2 = s"override protected def _exprSetOpt(op: Op, vs: Option[Seq[Set[t]]]$pSet) = new $ns_0"
    def set3 = s"override protected def _exprSetTac(op: Op, vs: Seq[Set[t]]        $pSet) = new $ns_0"

    if (hasOne || hasSet) {
      res += s"$agg1[$tInt_](toInt    (elements, kw    )) with SortAttrs_$arity[$tInt_, $ns_0]"
      res += s"$agg2[$tDoub](toDouble (elements, kw    )) with SortAttrs_$arity[$tDoub, $ns_0]"
      res += s"$agg3[$tDist](asIs     (elements, kw    ))"
      res += s"$agg4[$tSet_](asIs     (elements, kw, n ))"
      res += s"$agg5[$tA___](asIs     (elements, kw    )) with SortAttrs_$arity[$tA___, $ns_0]"
      res += s"$agg6[$tA___](asIs     (elements, kw    ))"
    }
    if (hasOne) {
      res += s"$one1[$tA___](addOne   (elements, op, vs)) with SortAttrs_$arity[$tA___, $ns_0] with CardOne"
      res += s"$one2[$tA___](addOptOne(elements, op, vs)) with SortAttrs_$arity[$tA___, $ns_0]"
      res += s"$one3[$tA___](addOne   (elements, op, vs)) with CardOne"
    }
    if (hasSet) {
      res += s"$set1[$tA___](addSet   (elements, op, vs)) with CardSet"
      res += s"$set2[$tA___](addOptSet(elements, op, vs))"
      res += s"$set3[$tA___](addSet   (elements, op, vs)) with CardSet"
    }
    res += s"$one4[$tA___](addSort  (elements, sort  ))"
  }

  val hasCardSet = refs.exists(_.card == CardSet)
  lazy val withNestedInit = s" with NestedInit"
  lazy val nestedPad      = " " * withNestedInit.length
  refs.collect {
    case MetaAttr(attr, card, _, refNsOpt, _, _, _, _, _, _) if attr != "tx" =>
      val refCls        = partPrefix + camel(attr)
      val refNs         = partPrefix + refNsOpt.get
      val pRefAttr      = padRefAttr(attr)
      val pRefNs        = padRefNs(refNs)
      val refObj        = s"""Model.Ref("$ns", "$attr"$pRefAttr, "$refNs"$pRefNs, $card)"""
      val refObjBi      = s"""Model.Ref("$ns", "$attr", "$refNs", $card, true)"""
      val nested        = if (hasCardSet) {
        if (arity < maxArity && ns != "Tx")
          if (card == CardOne) nestedPad else withNestedInit
        else
          ""
      } else ""
      val nestedBi      = if (card == CardOne || arity == maxArity) "" else withNestedInit
      val bidirectional = if (ns == refNs)
        s""" { def apply(makeDirectional: bi) = new $refNs${_0}$pRefNs[${`A..V, `}t](elements.init :+ $refObjBi)$nestedBi }""" else ""
      ref += s"""object $refCls$pRefAttr extends $refNs${_0}$pRefNs[${`A..V, `}t](elements :+ $refObj)$nested$bidirectional"""
  }

  val manAttrs = if (last) "" else man.result().mkString("", "\n  ", "\n\n  ")
  val optAttrs = if (last) "" else opt.result().mkString("", "\n  ", "\n\n  ")
  val tacAttrs = tac.result().mkString("\n  ")

  val elements = "override val elements: List[Element]"

  val lastNs        = if (last) s"X${arity + 2}" else ns_1
  val modelOps      = s"ModelOps_$arity[${`A..V, `}t, $ns_0, $lastNs]"
  val compositeInit = if (custom) s"with CompositeInit_$arity[${`A..V, `}$tx_n]" else s"with TxMetaData_$arity${`[A..V]`}"

  val resolvers = res.result().mkString("\n  ")

  val filterAttrs = if (first) {
    s"""
       |
       |  override protected def _attrTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]) = new $ns_0[   t](filterAttr(elements, op, a))
       |  override protected def _attrMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]) = new $ns_1[X, t](filterAttr(elements, op, a))""".stripMargin
  } else if (last) {
    s"""
       |
       |  override protected def _attrSortTac[ns1[_], ns2[_, _]](op: Op, a: ModelOps_0[t, ns1, ns2] with CardOne) = new $ns_0[${`A..V`}, t](filterAttr(elements, op, a)) with SortAttrs${_0}[${`A..V`}, t, $ns_0]
       |  override protected def _attrTac    [ns1[_], ns2[_, _]](op: Op, a: ModelOps_0[t, ns1, ns2]             ) = new $ns_0[${`A..V`}, t](filterAttr(elements, op, a))""".stripMargin
  } else {
    s"""
       |
       |  override protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne) = new $ns_0[${`A..V`},    t](filterAttr(elements, op, a)) with SortAttrs${_0}[${`A..V`},    t, $ns_0]
       |  override protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[$V, t, ns1, ns2]             ) = new $ns_1[${`A..V`}, $V, t](filterAttr(elements, op, a)) with SortAttrs${_1}[${`A..V`}, $V, t, $ns_1]
       |  override protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ) = new $ns_0[${`A..V`},    t](filterAttr(elements, op, a))
       |  override protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ) = new $ns_1[${`A..V`}, X, t](filterAttr(elements, op, a))""".stripMargin
  }

  val compositeInitResolvers = if (custom) {
    if (first) {
      s"""
         |
         |  override protected def _compositeInit00    (nextMolecule: List[Element]) = Composite_00(List(Composite(elements), Composite(nextMolecule)), new Txs_("$ns"))
         |  override protected def _compositeInit01[T2](nextMolecule: List[Element]) = Composite_01(List(Composite(elements), Composite(nextMolecule)), new Txs_("$ns"))""".stripMargin
    } else {
      s"""
         |
         |  override protected def _compositeInit01    (nextMolecule: List[Element]) = Composite_01(List(Composite(elements), Composite(nextMolecule)), new Txs_("$ns"))
         |  override protected def _compositeInit02[T2](nextMolecule: List[Element]) = Composite_02(List(Composite(elements), Composite(nextMolecule)), new Txs_("$ns"))""".stripMargin
    }
  } else ""

  val nested2tx = if (hasCardSet && arity < maxArity) {
    s"""
       |
       |  trait NestedInit extends NestedOp_$arity[${`A..V, `}Tx${_1}] with Nested_$arity[${`A..V, `}Tx${_1}] { self: Elements =>
       |    override protected def _nestedMan2tx[NestedTpl](nestedElements: List[Element]): Nested2TxInit_$n0[${`A..V, `}NestedTpl, Tx${_1}] = { val es = elements.init :+ Nested   (elements.last.asInstanceOf[Ref], nestedElements); new Nested2TxInit_$n0(es, new Tx${_1}[${`A..V, `}Seq[NestedTpl], Nothing](es)) }
       |    override protected def _nestedOpt2tx[NestedTpl](nestedElements: List[Element]): Nested2TxInit_$n0[${`A..V, `}NestedTpl, Tx${_1}] = { val es = elements.init :+ NestedOpt(elements.last.asInstanceOf[Ref], nestedElements); new Nested2TxInit_$n0(es, new Tx${_1}[${`A..V, `}Seq[NestedTpl], Nothing](es)) }
       |  }""".stripMargin
  } else ""

  val tx = if (custom) s"""\n\n  def Tx = new Tx_$arity[${`A..V, `}t](elements :+ Model.Ref("$ns", "tx", "Tx", CardOne)) with TxMetaData_$arity${`[A..V]`}""" else ""

  val refResult = ref.result()
  val refDefs   = if (refResult.isEmpty) "" else refResult.mkString("\n\n  ", "\n  ", "")

  val backRefDefs = if (backRefs.isEmpty) "" else {
    val max = backRefs.map(_.length).max
    backRefs.flatMap { backRef0 =>
      val backRef = partPrefix + backRef0
      if (ns == backRef) None else {
        val pad = padS(max, backRef)
        Some(s"""object _$backRef$pad extends $backRef${_0}$pad[${`A..V, `}t](elements :+ Model.BackRef("$backRef", "$ns"))""")
      }
    }.mkString("\n\n  ", "\n  ", "")
  }

  def get: String =
    s"""class $ns_0[${`A..V, `}t]($elements) extends ${ns}_base with $modelOps $compositeInit {
       |  $manAttrs$optAttrs$tacAttrs
       |
       |  $resolvers$filterAttrs$compositeInitResolvers$nested2tx$tx$refDefs$backRefDefs
       |}
       |""".stripMargin
}
