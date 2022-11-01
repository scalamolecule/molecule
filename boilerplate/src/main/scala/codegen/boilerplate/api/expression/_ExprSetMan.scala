package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase

object _ExprSetMan extends BoilerplateGenBase( "ExprSetMan", "/api/expression") {
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
         |  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = ???
         |}
         |
         |trait ${fileName}_$arity[${`A..V`}, t, Ns[${`_, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Ns]
         |    with Aggregates_$arity[${`A..V`}, t, Ns]
         |    with SortAttrs_$arity[${`A..V`}, t, Ns] { self: Ns[${`_, _`}] =>
         |  def apply  (v    : t, vs: t*             )               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetMan(Appl   , (v +: vs).map(v => Set(v)))
         |  def apply  (vs   : Seq[t]                )(implicit x: X): Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetMan(Appl   , vs.map(v => Set(v)))
         |  def apply  (set  : Set[t], sets: Set[t]* )               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetMan(Appl   , set +: sets)
         |  def apply  (sets : Seq[Set[t]]           )               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetMan(Appl   , sets)
         |  def not    (v    : t, vs: t*             )               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetMan(Not    , (v +: vs).map(v => Set(v)))
         |  def not    (vs   : Seq[t]                )(implicit x: X): Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetMan(Not    , vs.map(v => Set(v)))
         |  def not    (set  : Set[t], sets: Set[t]* )               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetMan(Not    , set +: sets)
         |  def not    (sets : Seq[Set[t]]           )               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetMan(Not    , sets)
         |  def ==     (set  : Set[t]                )               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetMan(Eq     , Seq(set))
         |  def ==     (set  : Set[t], sets: Set[t]* )               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetMan(Eq     , sets)
         |  def ==     (sets : Seq[Set[t]]           )               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetMan(Eq     , sets)
         |  def !=     (set  : Set[t]                )               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetMan(Neq    , Seq(set))
         |  def !=     (set  : Set[t], sets: Set[t]* )               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetMan(Neq    , set +: sets)
         |  def !=     (sets : Seq[Set[t]]           )               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetMan(Neq    , sets)
         |  def <      (upper: t                     )               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetMan(Lt     , Seq(Set(upper)))
         |  def <=     (upper: t                     )               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetMan(Le     , Seq(Set(upper)))
         |  def >      (lower: t                     )               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetMan(Gt     , Seq(Set(lower)))
         |  def >=     (lower: t                     )               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetMan(Ge     , Seq(Set(lower)))
         |  def add    (v    : t, vs: t*             )               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetMan(Add    , Seq((v +: vs).toSet) )
         |  def add    (vs   : Seq[t]                )               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetMan(Add    , Seq(vs.toSet))
         |  def replace(ab   : (t, t), abs: (t, t)*  )               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetMan(Replace, abs2sets(ab +: abs))
         |  def replace(abs  : Seq[(t, t)]           )               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetMan(Replace, abs2sets(abs))
         |  def delete (v    : t, vs: t*             )               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetMan(Delete , Seq((v +: vs).toSet))
         |  def delete (vs   : Seq[t]                )               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprSetMan(Delete , Seq(vs.toSet))
         |}""".stripMargin
  }
}
