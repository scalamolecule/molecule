package molecule.base.codeGeneration.render

import molecule.base.ast.SchemaAST.{MetaAttr, MetaNs, MetaSchema}


case class Dsl_Arities(schema: MetaSchema, namespace: MetaNs, arity: Int)
  extends DslFormatting(schema, namespace, arity) {

  val man = List.newBuilder[String]
  val opt = List.newBuilder[String]
  val tac = List.newBuilder[String]
  val res = List.newBuilder[String]
  val ref = List.newBuilder[String]

  val first = arity == 0
  val last  = arity == schema.maxArity

  attrs.foreach {
    case MetaAttr(attr, card, tpe0, refNs, options, descr, alias, validation) =>
      val c    = card.marker
      val tpe  = getTpe(tpe0)
      val padA = padAttr(attr)
      val pad1 = padType(tpe)
      val pad2 = padTpe2(tpe)

      lazy val tpesM = s"${`A..V, `}$tpe        $pad1, $tpe$pad1"
      lazy val tpesO = s"${`A..V, `}Option[$tpe]$pad1, $tpe$pad1"
      lazy val tpesT = s"${`A..V, `}$tpe        $pad2"

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
    res += s"override protected def _exprTac(op: Op, vs: Seq[t]) = new $ns_0[t](addVs(elements, op, vs))"
  } else {
    val t1 = s"${`..U`}Int    , Int   "
    val t2 = s"${`..U`}Double , Double"
    val t3 = s"${`..U`}List[t], t     "
    val t4 = s"${`..U`}t      , t     "
    val t5 = s"${`A..V`}      , t     "
    val t6 = s"${`A..V`}      , t     "
    val t7 = s"${`A..V`}      , t     "
    val t8 = s"${`A..V`}      , t     "

    val def1 = s"override protected def _aggrInt   (kw: Kw                    ) = new $ns_0"
    val def2 = s"override protected def _aggrDouble(kw: Kw                    ) = new $ns_0"
    val def3 = s"override protected def _aggrList  (kw: Kw, n: Option[Int]    ) = new $ns_0"
    val def4 = s"override protected def _aggrT     (kw: Kw                    ) = new $ns_0"
    val def5 = s"override protected def _exprMan   (op: Op, vs: Seq[t]        ) = new $ns_0"
    val def6 = s"override protected def _exprOpt   (op: Op, vs: Option[Seq[t]]) = new $ns_0"
    val def7 = s"override protected def _exprTac   (op: Op, vs: Seq[t]        ) = new $ns_0"
    val def8 = s"override protected def _addSort   (sort: String              ) = new $ns_0"

    res += s"$def1[$t1](toInt   (elements, kw    )) with SortAttrs_$arity[$t1, $ns_0]"
    res += s"$def2[$t2](toDouble(elements, kw    )) with SortAttrs_$arity[$t2, $ns_0]"
    res += s"$def3[$t3](toList  (elements, kw, n )) with SortAttrs_$arity[$t3, $ns_0]"
    res += s"$def4[$t4](asIs    (elements, kw    )) with SortAttrs_$arity[$t4, $ns_0]"
    res += s"$def5[$t5](addVs   (elements, op, vs)) with SortAttrs_$arity[$t5, $ns_0]"
    res += s"$def6[$t6](addOptVs(elements, op, vs)) with SortAttrs_$arity[$t6, $ns_0]"
    res += s"$def7[$t7](addVs   (elements, op, vs))"
    res += s"$def8[$t8](addSort (elements, sort  ))"
  }

  refs.foreach {
    case MetaAttr(attr, card, _, refNsOpt, options, descr, alias, validation) =>
      val Ref   = camel(attr)
      val refNs = refNsOpt.get
      val bond  = s"""Bond("$ns", "$attr", "$refNs", $card)"""
      ref += s"object $Ref extends $refNs${_0}[${`A..V, `}t](elements :+ $bond)"
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
