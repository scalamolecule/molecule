package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase

object _ExprSetTac extends BoilerplateGenBase("ExprSetTac", "/api/expression") {
  val content = {
    val traits = (0 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api.expression
       |
       |import molecule.boilerplate.ast.MoleculeModel._
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V, `}t, Ns[${`_, _`}]] extends ExprBase {
         |  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns[${`A..V, `}t] = ???
         |}
         |
         |trait ${fileName}_$arity[${`A..V, `}t, Ns[${`_, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V, `}t, Ns] {
         |  def apply  (                            )               : Ns[${`A..V, `}t] = _exprSetTac(NoValue, Nil                       )
         |  def apply  (v    : t, vs: t*            )               : Ns[${`A..V, `}t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
         |  def apply  (vs   : Seq[t]               )(implicit x: X): Ns[${`A..V, `}t] = _exprSetTac(Appl   , vs.map(v => Set(v))       )
         |  def apply  (set  : Set[t], sets: Set[t]*)               : Ns[${`A..V, `}t] = _exprSetTac(Appl   , set +: sets               )
         |  def apply  (sets : Seq[Set[t]]          )               : Ns[${`A..V, `}t] = _exprSetTac(Appl   , sets                      )
         |  def not    (v    : t, vs: t*            )               : Ns[${`A..V, `}t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
         |  def not    (vs   : Seq[t]               )(implicit x: X): Ns[${`A..V, `}t] = _exprSetTac(Not    , vs.map(v => Set(v))       )
         |  def not    (set  : Set[t], sets: Set[t]*)               : Ns[${`A..V, `}t] = _exprSetTac(Not    , set +: sets               )
         |  def not    (sets : Seq[Set[t]]          )               : Ns[${`A..V, `}t] = _exprSetTac(Not    , sets                      )
         |  def ==     (set  : Set[t]               )               : Ns[${`A..V, `}t] = _exprSetTac(Eq     , Seq(set)                  )
         |  def ==     (set  : Set[t], sets: Set[t]*)               : Ns[${`A..V, `}t] = _exprSetTac(Eq     , set +: sets               )
         |  def ==     (sets : Seq[Set[t]]          )               : Ns[${`A..V, `}t] = _exprSetTac(Eq     , sets                      )
         |  def !=     (set  : Set[t]               )               : Ns[${`A..V, `}t] = _exprSetTac(Neq    , Seq(set)                  )
         |  def !=     (set  : Set[t], sets: Set[t]*)               : Ns[${`A..V, `}t] = _exprSetTac(Neq    , set +: sets               )
         |  def !=     (sets : Seq[Set[t]]          )               : Ns[${`A..V, `}t] = _exprSetTac(Neq    , sets                      )
         |  def <      (upper: t                    )               : Ns[${`A..V, `}t] = _exprSetTac(Lt     , Seq(Set(upper))           )
         |  def <=     (upper: t                    )               : Ns[${`A..V, `}t] = _exprSetTac(Le     , Seq(Set(upper))           )
         |  def >      (lower: t                    )               : Ns[${`A..V, `}t] = _exprSetTac(Gt     , Seq(Set(lower))           )
         |  def >=     (lower: t                    )               : Ns[${`A..V, `}t] = _exprSetTac(Ge     , Seq(Set(lower))           )
         |  def add    (v    : t, vs: t*            )               : Ns[${`A..V, `}t] = _exprSetTac(Add    , Seq((v +: vs).toSet)      )
         |  def add    (vs   : Seq[t]               )               : Ns[${`A..V, `}t] = _exprSetTac(Add    , Seq(vs.toSet)             )
         |  def replace(ab   : (t, t), abs: (t, t)* )               : Ns[${`A..V, `}t] = _exprSetTac(Replace, abs2sets(ab +: abs)       )
         |  def replace(abs  : Seq[(t, t)]          )               : Ns[${`A..V, `}t] = _exprSetTac(Replace, abs2sets(abs)             )
         |  def delete (v    : t, vs: t*            )               : Ns[${`A..V, `}t] = _exprSetTac(Delete , Seq((v +: vs).toSet)      )
         |  def delete (vs   : Seq[t]               )               : Ns[${`A..V, `}t] = _exprSetTac(Delete , Seq(vs.toSet)             )
         |}""".stripMargin
  }
}
