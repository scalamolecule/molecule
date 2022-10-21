package molecule.boilerplate.generators.expression

import molecule.boilerplate.generators._Template

object GenExprOneTac extends _Template( "exprOneTac", "expression") {
  val content = {
    val traits = (0 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""package molecule.boilerplate.api.expression
       |
       |import molecule.boilerplate.api.keywords.unify
       |import molecule.boilerplate.ast.MoleculeModel._
       |$traits""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |
         |trait ${Name}Ops_$arity[${`A..N, `}t, Ns[${`_, _`}]] {
         |  protected def _exprTac(op: Op, vs: Seq[t]): Ns[${`A..N, `}t]
         |}
         |
         |trait ${Name}_$arity[${`A..N, `}t, $nsIn]
         |  extends ${Name}Ops_$arity[${`A..N, `}t, Ns] {
         |  def apply()                : Ns[${`A..N, `}t] = _exprTac(Eq, Nil)
         |  def apply(unify: unify)    : Ns[${`A..N, `}t] = _exprTac(Unify, Nil)
         |  def apply(v    : t, vs: t*): Ns[${`A..N, `}t] = _exprTac(Eq, v +: vs)
         |  def apply(vs   : Seq[t])   : Ns[${`A..N, `}t] = _exprTac(Eq, vs)
         |  def ==   (v    : t)        : Ns[${`A..N, `}t] = _exprTac(Eq, Seq(v))
         |  def ==   (v    : t, vs: t*): Ns[${`A..N, `}t] = _exprTac(Eq, v +: vs)
         |  def ==   (vs   : Seq[t])   : Ns[${`A..N, `}t] = _exprTac(Eq, vs)
         |  def not  (v    : t, vs: t*): Ns[${`A..N, `}t] = _exprTac(Neq, v +: vs)
         |  def not  (vs   : Seq[t])   : Ns[${`A..N, `}t] = _exprTac(Neq, vs)
         |  def !=   (v    : t)        : Ns[${`A..N, `}t] = _exprTac(Neq, Seq(v))
         |  def !=   (v    : t, vs: t*): Ns[${`A..N, `}t] = _exprTac(Neq, v +: vs)
         |  def !=   (vs   : Seq[t])   : Ns[${`A..N, `}t] = _exprTac(Neq, vs)
         |  def <    (upper: t)        : Ns[${`A..N, `}t] = _exprTac(Lt, Seq(upper))
         |  def <=   (upper: t)        : Ns[${`A..N, `}t] = _exprTac(Le, Seq(upper))
         |  def >    (lower: t)        : Ns[${`A..N, `}t] = _exprTac(Gt, Seq(lower))
         |  def >=   (lower: t)        : Ns[${`A..N, `}t] = _exprTac(Ge, Seq(lower))
         |}""".stripMargin
  }
}
