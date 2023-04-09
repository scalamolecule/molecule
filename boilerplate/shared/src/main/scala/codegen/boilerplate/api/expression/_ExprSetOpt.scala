package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprSetOpt extends BoilerplateGenBase( "ExprSetOpt", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api.expression
       |
       |import molecule.boilerplate.ast.Model._
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Ns[${`_, _`}]] extends ExprBase {
         |  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns[${`A..V`}, t] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t, Ns[${`_, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Ns]{
         |  def apply(v    : Option[t]          )(implicit x: X)            : Ns[${`A..V`}, t] = _exprSetOpt(Appl, v.map(v => Seq(Set(v)))    )
         |  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[${`A..V`}, t] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))) )
         |  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[${`A..V`}, t] = _exprSetOpt(Appl, set.map(set => Seq(set))   )
         |  def apply(sets : Option[Seq[Set[t]]])                           : Ns[${`A..V`}, t] = _exprSetOpt(Appl, sets                       )
         |  def not  (v    : Option[t]          )(implicit x: X)            : Ns[${`A..V`}, t] = _exprSetOpt(Not , v.map(v => Seq(Set(v)))    )
         |  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[${`A..V`}, t] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))) )
         |  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[${`A..V`}, t] = _exprSetOpt(Not , set.map(set => Seq(set))   )
         |  def not  (sets : Option[Seq[Set[t]]])                           : Ns[${`A..V`}, t] = _exprSetOpt(Not , sets                       )
         |  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[${`A..V`}, t] = _exprSetOpt(Eq  , set.map(set => Seq(set))   )
         |  def ==   (sets : Option[Seq[Set[t]]])                           : Ns[${`A..V`}, t] = _exprSetOpt(Eq  , sets                       )
         |  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[${`A..V`}, t] = _exprSetOpt(Neq , set.map(set => Seq(set))   )
         |  def !=   (sets : Option[Seq[Set[t]]])                           : Ns[${`A..V`}, t] = _exprSetOpt(Neq , sets                       )
         |  def <    (upper: Option[t]          )                           : Ns[${`A..V`}, t] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
         |  def <=   (upper: Option[t]          )                           : Ns[${`A..V`}, t] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
         |  def >    (lower: Option[t]          )                           : Ns[${`A..V`}, t] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
         |  def >=   (lower: Option[t]          )                           : Ns[${`A..V`}, t] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))
         |}""".stripMargin
  }
}
