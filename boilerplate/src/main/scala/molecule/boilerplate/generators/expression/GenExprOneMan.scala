package molecule.boilerplate.generators.expression

import molecule.boilerplate.generators._Template

object GenExprOneMan extends _Template( "exprOneMan", "expression") {
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
         |  protected def _exprMan(op: Op, vs: Seq[t]): Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns]
         |}
         |
         |trait ${Name}_$arity[${`A..N`}, t, Ns[${`_, _`}]]
         |  extends ${Name}Ops_$arity[${`A..N`}, t, Ns]
         |    with Aggregates_$arity[${`A..N`}, t, Ns]
         |    with SortAttrs_$arity[${`A..N`}, t, Ns] { self: Ns[${`_, _`}] =>
         |  def apply(v    : t, vs: t*): Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns] = _exprMan(Eq, v +: vs)
         |  def apply(vs   : Seq[t])   : Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns] = _exprMan(Eq, vs)
         |  def ==   (v    : t)        : Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns] = _exprMan(Eq, Seq(v))
         |  def ==   (v    : t, vs: t*): Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns] = _exprMan(Eq, v +: vs)
         |  def ==   (vs   : Seq[t])   : Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns] = _exprMan(Eq, vs)
         |  def not  (v    : t, vs: t*): Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns] = _exprMan(Neq, v +: vs)
         |  def not  (vs   : Seq[t])   : Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns] = _exprMan(Neq, vs)
         |  def !=   (v    : t)        : Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns] = _exprMan(Neq, Seq(v))
         |  def !=   (v    : t, vs: t*): Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns] = _exprMan(Neq, v +: vs)
         |  def !=   (vs   : Seq[t])   : Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns] = _exprMan(Neq, vs)
         |  def <    (upper: t)        : Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns] = _exprMan(Lt, Seq(upper))
         |  def <=   (upper: t)        : Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns] = _exprMan(Le, Seq(upper))
         |  def >    (lower: t)        : Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns] = _exprMan(Gt, Seq(lower))
         |  def >=   (lower: t)        : Ns[${`A..N`}, t] with SortAttrs_$arity[${`A..N`}, t, Ns] = _exprMan(Ge, Seq(lower))
         |}""".stripMargin
  }
}
