package molecule.base.codeGeneration.render

import molecule.base.ast.SchemaAST._


case class Dsl_Arities(schema: MetaSchema, namespace: MetaNs, arity: Int)
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
    case MetaAttr(attr, card, tpe0, refNs, options, descr, alias, validation) =>
      val c   = card.marker
      val tpe = getTpe(tpe0)

      val padA = padAttr(attr)
      val pad1 = padType(tpe)
      val pad2 = padTpe2(tpe)

      val (tM, tO) = card match {
        case CardOne => (tpe + pOne, s"Option[$tpe]" + pOne)
        case CardSet => (s"Set[$tpe]" + pSet, s"Option[Set[$tpe]]" + pSet)
        case CardArr => (s"Array[$tpe]" + pArr, s"Option[Array[$tpe]]" + pArr)
        case CardMap => (s"Map[String, $tpe]" + pMap, s"Option[Map[String, $tpe]]" + pMap)
      }

      lazy val tpesM = s"${`A..V, `}$tM$pad1        , $tpe$pad1"
      lazy val tpesO = s"${`A..V, `}$tO$pad1, $tpe$pad1"
      lazy val tpesT = s"${`A..V, `}$tM$pad2        "

      lazy val elemsM = s"elements :+ ${attr}_man$padA"
      lazy val elemsO = s"elements :+ ${attr}_opt$padA"
      lazy val elemsT = s"elements :+ ${attr}_tac$padA"

      lazy val exprM = s"Expr${c}Man${_1}[$tpesM, $ns_1]"
      lazy val exprO = s"Expr${c}Opt${_1}[$tpesO, $ns_1]"
      lazy val exprT = s"Expr${c}Tac${_0}[$tpesT, $ns_0]"

      if (!last) {
        man += s"""lazy val ${attr}  $padA = new $ns_1[$tpesM]($elemsM) with $exprM"""
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
    val tI = s"${`..U`}Int    , Int   "
    val tD = s"${`..U`}Double , Double"
    val tL = s"${`..U`}List[t], t     "
    val tt = s"${`..U`}t      , t     "
    val tA = s"${`A..V`}      , t     "

    def one1 = s"override protected def _aggrInt   (kw: Kw                    $pOne) = new $ns_0"
    def one2 = s"override protected def _aggrDouble(kw: Kw                    $pOne) = new $ns_0"
    def one3 = s"override protected def _aggrList  (kw: Kw, n: Option[Int]    $pOne) = new $ns_0"
    def one4 = s"override protected def _aggrT     (kw: Kw                    $pOne) = new $ns_0"
    def one5 = s"override protected def _exprOneMan(op: Op, vs: Seq[t]        $pOne) = new $ns_0"
    def one6 = s"override protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]$pOne) = new $ns_0"
    def one7 = s"override protected def _exprOneTac(op: Op, vs: Seq[t]        $pOne) = new $ns_0"
    def one8 = s"override protected def _sort      (sort: String              $pOne) = new $ns_0"

    def set1 = s"override protected def _exprSetMan(op: Op, vs: Seq[Set[t]]        $pSet) = new $ns_0"
    def set2 = s"override protected def _exprSetOpt(op: Op, vs: Option[Seq[Set[t]]]$pSet) = new $ns_0"
    def set3 = s"override protected def _exprSetTac(op: Op, vs: Seq[Set[t]]        $pSet) = new $ns_0"

    if (hasOne || hasSet) {
      res += s"$one1[$tI](toInt    (elements, kw    )) with SortAttrs_$arity[$tI, $ns_0]"
      res += s"$one2[$tD](toDouble (elements, kw    )) with SortAttrs_$arity[$tD, $ns_0]"
      res += s"$one3[$tL](toList   (elements, kw, n )) with SortAttrs_$arity[$tL, $ns_0]"
      res += s"$one4[$tt](asIs     (elements, kw    )) with SortAttrs_$arity[$tt, $ns_0]"
    }
    if (hasOne) {
      res += s"$one5[$tA](addOne   (elements, op, vs)) with SortAttrs_$arity[$tA, $ns_0]"
      res += s"$one6[$tA](addOptOne(elements, op, vs)) with SortAttrs_$arity[$tA, $ns_0]"
      res += s"$one7[$tA](addOne   (elements, op, vs))"
    }
    if (hasSet) {
      res += s"$set1[$tA](addSet   (elements, op, vs)) with SortAttrs_$arity[$tA, $ns_0]"
      res += s"$set2[$tA](addOptSet(elements, op, vs)) with SortAttrs_$arity[$tA, $ns_0]"
      res += s"$set3[$tA](addSet   (elements, op, vs))"
    }
    res += s"$one8[$tA](addSort  (elements, sort  ))"
  }

  refs.foreach {
    case MetaAttr(attr, card, _, refNsOpt, options, descr, alias, validation) =>
      val refCls = camel(attr)
      val refNs  = refNsOpt.get
      val refObj = s"""Ref("$ns", "$attr", "$refNs", $card)"""
      ref += s"object $refCls extends $refNs${_0}[${`A..V, `}t](elements :+ $refObj)"
  }

  val manAttrs = if (last) "" else man.result().mkString("", "\n  ", "\n\n  ")
  val optAttrs = if (last) "" else opt.result().mkString("", "\n  ", "\n\n  ")
  val tacAttrs = tac.result().mkString("\n  ")

  val resolvers = res.result().mkString("\n  ")

  val refResult = ref.result()
  val refDefs   = if (refResult.isEmpty) "" else refResult.mkString("\n\n  ", "\n  ", "")

  val tpes     = `A..V, ` + "t"
  val elements = "override val elements: Seq[Element]"
  val modelOps = s"ModelOps_$arity[$tpes, $ns_0]"

  def get =
    s"""class $ns_0[$tpes]($elements) extends $ns with $modelOps with $NS {
       |  $manAttrs$optAttrs$tacAttrs
       |
       |  $resolvers$refDefs
       |}
       |""".stripMargin
}
