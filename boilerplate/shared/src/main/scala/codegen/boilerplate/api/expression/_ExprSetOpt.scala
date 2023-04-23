package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprSetOpt extends BoilerplateGenBase( "ExprSetOpt", "/api/expression") {
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
         |  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[${`A..V`}, t] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Ns1, Ns2]{
         |  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[${`A..V`}, t] = _exprSetOpt(Eq   , v.map(v => Seq(Set(v)))    )
         |  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[${`A..V`}, t] = _exprSetOpt(Eq   , vs.map(_.map(v => Set(v))) )
         |  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[${`A..V`}, t] = _exprSetOpt(Eq   , set.map(set => Seq(set))   )
         |  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[${`A..V`}, t] = _exprSetOpt(Eq   , sets                       )
         |  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[${`A..V`}, t] = _exprSetOpt(Neq  , set.map(set => Seq(set))   )
         |  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[${`A..V`}, t] = _exprSetOpt(Neq  , sets                       )
         |  def has  (v    : Option[t]          )(implicit x: X)            : Ns1[${`A..V`}, t] = _exprSetOpt(Has  , v.map(v => Seq(Set(v)))    )
         |  def has  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[${`A..V`}, t] = _exprSetOpt(Has  , vs.map(_.map(v => Set(v))) )
         |  def has  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[${`A..V`}, t] = _exprSetOpt(Has  , set.map(set => Seq(set))   )
         |  def has  (sets : Option[Seq[Set[t]]])                           : Ns1[${`A..V`}, t] = _exprSetOpt(Has  , sets                       )
         |  def hasNo(v    : Option[t]          )(implicit x: X)            : Ns1[${`A..V`}, t] = _exprSetOpt(HasNo, v.map(v => Seq(Set(v)))    )
         |  def hasNo(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[${`A..V`}, t] = _exprSetOpt(HasNo, vs.map(_.map(v => Set(v))) )
         |  def hasNo(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[${`A..V`}, t] = _exprSetOpt(HasNo, set.map(set => Seq(set))   )
         |  def hasNo(sets : Option[Seq[Set[t]]])                           : Ns1[${`A..V`}, t] = _exprSetOpt(HasNo, sets                       )
         |  def hasLt(upper: Option[t]          )                           : Ns1[${`A..V`}, t] = _exprSetOpt(HasLt, upper.map(v => Seq(Set(v))))
         |  def hasLe(upper: Option[t]          )                           : Ns1[${`A..V`}, t] = _exprSetOpt(HasLe, upper.map(v => Seq(Set(v))))
         |  def hasGt(lower: Option[t]          )                           : Ns1[${`A..V`}, t] = _exprSetOpt(HasGt, lower.map(v => Seq(Set(v))))
         |  def hasGe(lower: Option[t]          )                           : Ns1[${`A..V`}, t] = _exprSetOpt(HasGe, lower.map(v => Seq(Set(v))))
         |}""".stripMargin
  }
}
