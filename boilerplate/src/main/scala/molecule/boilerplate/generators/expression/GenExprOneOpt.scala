package molecule.boilerplate.generators.expression

import molecule.boilerplate.generators._Template

object GenExprOneOpt extends _Template( "exprOneOpt", "expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""package molecule.boilerplate.api.expression
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
         |trait ${Name}Ops_$arity[${`A..N`}, t, Ns[${`_, _`}]] {
         |  protected def _exprOpt(op: Op, vs: Option[Seq[t]]): Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns]
         |}
         |
         |trait ${Name}_$arity[${`A..N`}, t, Ns[${`_, _`}]]
         |  extends ${Name}Ops_$arity[${`A..N`}, t, Ns]
         |    with SortAttrs_$arity[${`A..N`}, t, Ns] {
         |  def apply(v    : Option[t]     )                           : Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns] = _exprOpt(Eq, v.map(Seq(_)))
         |  def apply(vs   : Option[Seq[t]])(implicit d: DummyImplicit): Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns] = _exprOpt(Eq, vs)
         |  def ==   (v    : Option[t]     )                           : Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns] = _exprOpt(Eq, v.map(Seq(_)))
         |  def ==   (vs   : Option[Seq[t]])(implicit d: DummyImplicit): Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns] = _exprOpt(Eq, vs)
         |  def not  (v    : Option[t]     )                           : Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns] = _exprOpt(Eq, v.map(Seq(_)))
         |  def not  (vs   : Option[Seq[t]])(implicit d: DummyImplicit): Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns] = _exprOpt(Eq, vs)
         |  def !=   (v    : Option[t]     )                           : Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns] = _exprOpt(Eq, v.map(Seq(_)))
         |  def !=   (vs   : Option[Seq[t]])(implicit d: DummyImplicit): Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns] = _exprOpt(Eq, vs)
         |  def <    (upper: Option[t]     )                           : Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns] = _exprOpt(Lt, upper.map(Seq(_)))
         |  def <=   (upper: Option[t]     )                           : Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns] = _exprOpt(Le, upper.map(Seq(_)))
         |  def >    (lower: Option[t]     )                           : Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns] = _exprOpt(Gt, lower.map(Seq(_)))
         |  def >=   (lower: Option[t]     )                           : Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns] = _exprOpt(Ge, lower.map(Seq(_)))
         |}""".stripMargin
  }
}
