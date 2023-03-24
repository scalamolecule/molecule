package molecule.base.codegen.render

import molecule.base.ast.SchemaAST._


case class Dsl_Arities(schema: MetaSchema, partPrefix: String, namespace: MetaNs, arity: Int)
  extends DslFormatting(schema, namespace, arity) {

  val man = List.newBuilder[String]
  val opt = List.newBuilder[String]
  val tac = List.newBuilder[String]
  val res = List.newBuilder[String]
  val ref = List.newBuilder[String]

  val first = arity == 0
  val last  = arity == schema.maxArity

  var hasOne = false
  var hasSet = false
  var hasArr = false
  var hasMap = false

  val maxCardPad = attrs.map(a => a.card match {
    case CardOne => hasOne = true; 0
    case CardSet => hasSet = true; 5
    case CardArr => hasArr = true; 7
    case CardMap => hasMap = true; 13
  }).max

  val pOne = " " * maxCardPad
  val pSet = " " * (maxCardPad - 5)
  val pArr = " " * (maxCardPad - 7)
  val pMap = " " * (maxCardPad - 13)

  attrs.foreach {
    case MetaAttr(attr, card, tpe0, _, _, _, _, _) =>
      val c   = card.marker
      val tpe = getTpe(tpe0)

      val padA = padAttr(attr)
      val pad1 = padType(tpe)
      val pad2 = " " * (maxTpe + "Option[]".length + maxCardPad)

      val (tM, tO) = card match {
        case CardOne => (tpe + pOne, s"Option[$tpe]" + pOne)
        case CardSet => (s"Set[$tpe]" + pSet, s"Option[Set[$tpe]]" + pSet)
        case CardArr => (s"Array[$tpe]" + pArr, s"Option[Array[$tpe]]" + pArr)
        case CardMap => (s"Map[String, $tpe]" + pMap, s"Option[Map[String, $tpe]]" + pMap)
      }

      lazy val tpesM = s"${`A..V, `}$tM$pad1        , $tpe$pad1"
      lazy val tpesO = s"${`A..V, `}$tO$pad1, $tpe$pad1"
      lazy val tpesT = if (arity == 0)
        s"${`A..V`}$pad2  $tpe$pad1"
      else if (arity == schema.maxArity)
        s"${`A..V`}, $tpe$pad1"
      else
        s"${`A..V`}  $pad2, $tpe$pad1"

      lazy val elemsM = s"elements :+ ${attr}_man$padA"
      lazy val elemsO = s"elements :+ ${attr}_opt$padA"
      lazy val elemsT = s"elements :+ ${attr}_tac$padA"

      lazy val exprM = s"Expr${c}Man${_1}[$tpesM, $ns_1]"
      lazy val exprO = s"Expr${c}Opt${_1}[$tpesO, $ns_1]"
      lazy val exprT = s"Expr${c}Tac${_0}[$tpesT, $ns_0]"

      if (!last) {
        man += s"""lazy val ${attr}  $padA = new $ns_1[$tpesM]($elemsM) with $exprM"""
        if (!genericAttrs.contains(attr))
          opt += s"""lazy val ${attr}_?$padA = new $ns_1[$tpesO]($elemsO) with $exprO"""
      }
      tac += s"""lazy val ${attr}_ $padA = new $ns_0[$tpesT]($elemsT) with $exprT"""
  }

  if (first) {
    if (hasOne)
      res += s"override protected def _exprOneTac(op: Op, vs: Seq[t]$pOne) = new $ns_0[t](addOne(elements, op, vs))"
    if (hasSet)
      res += s"override protected def _exprSetTac(op: Op, vs: Seq[Set[t]]$pSet) = new $ns_0[t](addSet(elements, op, vs))"

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
      res += s"$one1[$tA___](addOne   (elements, op, vs)) with SortAttrs_$arity[$tA___, $ns_0]"
      res += s"$one2[$tA___](addOptOne(elements, op, vs)) with SortAttrs_$arity[$tA___, $ns_0]"
      res += s"$one3[$tA___](addOne   (elements, op, vs))"
    }
    if (hasSet) {
      res += s"$set1[$tA___](addSet   (elements, op, vs))"
      res += s"$set2[$tA___](addOptSet(elements, op, vs))"
      res += s"$set3[$tA___](addSet   (elements, op, vs))"
    }
    res += s"$one4[$tA___](addSort  (elements, sort  ))"
  }

  refs.foreach {
    case MetaAttr(attr, card, _, refNsOpt, _, _, _, _) =>
      val refCls   = partPrefix + camel(attr)
      val refNs    = partPrefix + refNsOpt.get
      val refObj   = s"""Model.Ref("$ns", "$attr", "$refNs", $card)"""
      val pRefAttr = padRefAttr(attr)
      val pRefNs   = padRefNs(refNs)
      val nested   = if (card == CardOne) "" else s" with Nested${_0}${`[A..V]`}"
      ref += s"object $refCls$pRefAttr extends $refNs${_0}$pRefNs[${`A..V, `}t](elements :+ $refObj)$nested"
  }

  val manAttrs = if (last) "" else man.result().mkString("", "\n  ", "\n\n  ")
  val optAttrs = if (last) "" else opt.result().mkString("", "\n  ", "\n\n  ")
  val tacAttrs = tac.result().mkString("\n  ")

  val elements = "override val elements: List[Element]"
  val modelOps = s"ModelOps_$arity[${`A..V, `}t, $ns_0]"

  val resolvers = res.result().mkString("\n  ")

  val refResult = ref.result()
  val refDefs   = if (refResult.isEmpty) "" else refResult.mkString("\n\n  ", "\n  ", "")

  val backRefDefs = if (backRefs.isEmpty) "" else backRefs.map { backRef0 =>
    val backRef = partPrefix + backRef0
    s"""object _$backRef extends $backRef${_0}[${`A..V, `}t](elements :+ Model.BackRef("$backRef"))"""
  }.mkString("\n\n  ", "\n  ", "")

  def get: String =
    s"""class $ns_0[${`A..V, `}t]($elements) extends $ns with $modelOps {
       |  $manAttrs$optAttrs$tacAttrs
       |
       |  $resolvers$refDefs$backRefDefs
       |}
       |""".stripMargin
}
