package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprOneOpt extends BoilerplateGenBase("ExprOneOpt", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api.expression
       |
       |import molecule.boilerplate.api._
       |import molecule.boilerplate.ast.Model._
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends ExprAttr_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Ns1, Ns2]
         |    with SortAttrs_$arity[${`A..V`}, t, Ns1] {
         |  def apply(v    : Option[t]     )(implicit x: X): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Eq , v.map(Seq(_))    )
         |  def apply(vs   : Option[Seq[t]])               : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Eq , vs               )
         |  def not  (v    : Option[t]     )(implicit x: X): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Neq, v.map(Seq(_))    )
         |  def not  (vs   : Option[Seq[t]])               : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Neq, vs               )
         |  def <    (upper: Option[t]     )               : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Lt , upper.map(Seq(_)))
         |  def <=   (upper: Option[t]     )               : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Le , upper.map(Seq(_)))
         |  def >    (lower: Option[t]     )               : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Gt , lower.map(Seq(_)))
         |  def >=   (lower: Option[t]     )               : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Ge , lower.map(Seq(_)))
         |}""".stripMargin

    // Doesn't seem to make sense to have none-optional filters on optional attributes...

    //         |trait $fileName_${arity}_String[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends $fileName_$arity[${`A..V`}, t, Ns1, Ns2] {
    //         |  def startsWith(prefix: t, offset: Int = 0): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(StartsWith, Some(Seq(prefix, offset.toString.asInstanceOf[t])                      ))
    //         |  def endsWith  (suffix: t                 ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(EndsWith  , Some(Seq(suffix)                                                       ))
    //         |  def contains  (needle: t                 ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Contains  , Some(Seq(needle)                                                       ))
    //         |  def matches   (regex : t                 ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Matches   , Some(Seq(regex)                                                        ))
    //         |  def take      (n     : Int               ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Take      , Some(Seq(n.toString.asInstanceOf[t])                                   ))
    //         |  def takeRight (n     : Int               ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(TakeRight , Some(Seq(n.toString.asInstanceOf[t])                                   ))
    //         |  def drop      (n     : Int               ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Drop      , Some(Seq(n.toString.asInstanceOf[t])                                   ))
    //         |  def dropRight (n     : Int               ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(DropRight , Some(Seq(n.toString.asInstanceOf[t])                                   ))
    //         |  def substring (start : Int, end: Int     ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(SubString , Some(Seq(start.toString.asInstanceOf[t], end.toString.asInstanceOf[t]) ))
    //         |  def slice     (from  : Int, until: Int   ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Slice     , Some(Seq(from.toString.asInstanceOf[t], until.toString.asInstanceOf[t])))
    //         |}
    //         |trait $fileName_${arity}_Number[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends $fileName_$arity[${`A..V`}, t, Ns1, Ns2] {
    //         |  def %(divider: t, remainder: t           ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Remainder , Some(Seq(divider, remainder)))
    //         |  def even                                  : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Even      , Some(Nil                    ))
    //         |  def odd                                   : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(UnEven    , Some(Nil                    ))
    //         |}
    //         |trait $fileName_${arity}_Decimal[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends $fileName_$arity[${`A..V`}, t, Ns1, Ns2] {
    //         |  def whole                                 : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Whole     , Some(Nil))
    //         |  def fractional                            : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Fractional, Some(Nil))
    //         |}""".stripMargin
  }
}
