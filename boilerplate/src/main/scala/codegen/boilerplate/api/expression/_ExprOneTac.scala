package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprOneTac extends BoilerplateGenBase( "ExprOneTac", "/api/expression") {
  val content = {
    val traits = (0 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api.expression
       |
       |import molecule.boilerplate.api.Keywords.unify
       |import molecule.boilerplate.ast.MoleculeModel._
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V, `}t, Ns[${`_, _`}]] {
         |  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns[${`A..V, `}t] = ???
         |}
         |
         |trait ${fileName}_$arity[${`A..V, `}t, $nsIn]
         |  extends ${fileName}Ops_$arity[${`A..V, `}t, Ns]{
         |  def apply()                : Ns[${`A..V, `}t] = _exprOneTac(NoValue, Nil       )
         |  def apply(unify: unify    ): Ns[${`A..V, `}t] = _exprOneTac(Unify  , Nil       )
         |  def apply(v    : t, vs: t*): Ns[${`A..V, `}t] = _exprOneTac(Appl   , v +: vs   )
         |  def apply(vs   : Seq[t]   ): Ns[${`A..V, `}t] = _exprOneTac(Appl   , vs        )
         |  def not  (v    : t, vs: t*): Ns[${`A..V, `}t] = _exprOneTac(Not    , v +: vs   )
         |  def not  (vs   : Seq[t]   ): Ns[${`A..V, `}t] = _exprOneTac(Not    , vs        )
         |  def <    (upper: t        ): Ns[${`A..V, `}t] = _exprOneTac(Lt     , Seq(upper))
         |  def <=   (upper: t        ): Ns[${`A..V, `}t] = _exprOneTac(Le     , Seq(upper))
         |  def >    (lower: t        ): Ns[${`A..V, `}t] = _exprOneTac(Gt     , Seq(lower))
         |  def >=   (lower: t        ): Ns[${`A..V, `}t] = _exprOneTac(Ge     , Seq(lower))
         |}""".stripMargin
  }
}
