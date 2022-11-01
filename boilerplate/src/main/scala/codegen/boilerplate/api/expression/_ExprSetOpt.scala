package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase

object _ExprSetOpt extends BoilerplateGenBase( "ExprSetOpt", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api.expression
       |
       |import molecule.boilerplate.api._
       |import molecule.boilerplate.ast.MoleculeModel._
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Ns[${`_, _`}]] extends ExprBase {
         |  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = ???
         |}
         |
         |trait ${fileName}_$arity[${`A..V`}, t, Ns[${`_, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Ns]
         |    with SortAttrs_$arity[${`A..V`}, t, Ns] { //self: Ns[${`_, _`}] =>
         |  def apply(v    : Option[t]          )(implicit x: X)            : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetOpt(Appl, v.map(v => Seq(Set(v))))
         |  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))))
         |  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetOpt(Appl, set.map(set => Seq(set)))
         |  def apply(sets : Option[Seq[Set[t]]])                           : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetOpt(Appl, sets)
         |  def not  (v    : Option[t]          )(implicit x: X)            : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetOpt(Not , v.map(v => Seq(Set(v))))
         |  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))))
         |  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetOpt(Not , set.map(set => Seq(set)))
         |  def not  (sets : Option[Seq[Set[t]]])                           : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetOpt(Not , sets)
         |  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetOpt(Eq  , set.map(set => Seq(set)))
         |  def ==   (sets : Option[Seq[Set[t]]])                           : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetOpt(Eq  , sets)
         |  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetOpt(Neq , set.map(set => Seq(set)))
         |  def !=   (sets : Option[Seq[Set[t]]])                           : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetOpt(Neq , sets)
         |  def <    (upper: Option[t]          )                           : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
         |  def <=   (upper: Option[t]          )                           : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
         |  def >    (lower: Option[t]          )                           : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
         |  def >=   (lower: Option[t]          )                           : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))
         |}""".stripMargin
  }
}
