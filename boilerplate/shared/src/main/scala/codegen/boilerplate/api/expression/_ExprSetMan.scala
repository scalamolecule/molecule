package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprSetMan extends BoilerplateGenBase( "ExprSetMan", "/api/expression") {
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
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Ns[${`_, _`}]] extends ExprBase {
         |  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns[${`A..V`}, t] = ???
         |}
         |
         |trait ${fileName}_$arity[${`A..V`}, t, Ns[${`_, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Ns]
         |    with Aggregates_$arity[${`A..V`}, t, Ns] {
         |  def apply (                             )               : Ns[${`A..V`}, t] = _exprSetMan(Appl  , Nil                       )
         |  def apply (v    : t, vs: t*             )               : Ns[${`A..V`}, t] = _exprSetMan(Appl  , (v +: vs).map(v => Set(v)))
         |  def apply (vs   : Seq[t]                )(implicit x: X): Ns[${`A..V`}, t] = _exprSetMan(Appl  , vs.map(v => Set(v))       )
         |  def apply (set  : Set[t], sets: Set[t]* )               : Ns[${`A..V`}, t] = _exprSetMan(Appl  , set +: sets               )
         |  def apply (sets : Seq[Set[t]]           )               : Ns[${`A..V`}, t] = _exprSetMan(Appl  , sets                      )
         |  def not   (v    : t, vs: t*             )               : Ns[${`A..V`}, t] = _exprSetMan(Not   , (v +: vs).map(v => Set(v)))
         |  def not   (vs   : Seq[t]                )(implicit x: X): Ns[${`A..V`}, t] = _exprSetMan(Not   , vs.map(v => Set(v))       )
         |  def not   (set  : Set[t], sets: Set[t]* )               : Ns[${`A..V`}, t] = _exprSetMan(Not   , set +: sets               )
         |  def not   (sets : Seq[Set[t]]           )               : Ns[${`A..V`}, t] = _exprSetMan(Not   , sets                      )
         |  def ==    (set  : Set[t]                )               : Ns[${`A..V`}, t] = _exprSetMan(Eq    , Seq(set)                  )
         |  def ==    (set  : Set[t], sets: Set[t]* )               : Ns[${`A..V`}, t] = _exprSetMan(Eq    , set +: sets               )
         |  def ==    (sets : Seq[Set[t]]           )               : Ns[${`A..V`}, t] = _exprSetMan(Eq    , sets                      )
         |  def !=    (set  : Set[t]                )               : Ns[${`A..V`}, t] = _exprSetMan(Neq   , Seq(set)                  )
         |  def !=    (set  : Set[t], sets: Set[t]* )               : Ns[${`A..V`}, t] = _exprSetMan(Neq   , set +: sets               )
         |  def !=    (sets : Seq[Set[t]]           )               : Ns[${`A..V`}, t] = _exprSetMan(Neq   , sets                      )
         |  def <     (upper: t                     )               : Ns[${`A..V`}, t] = _exprSetMan(Lt    , Seq(Set(upper))           )
         |  def <=    (upper: t                     )               : Ns[${`A..V`}, t] = _exprSetMan(Le    , Seq(Set(upper))           )
         |  def >     (lower: t                     )               : Ns[${`A..V`}, t] = _exprSetMan(Gt    , Seq(Set(lower))           )
         |  def >=    (lower: t                     )               : Ns[${`A..V`}, t] = _exprSetMan(Ge    , Seq(Set(lower))           )
         |  def add   (v    : t, vs: t*             )               : Ns[${`A..V`}, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
         |  def add   (vs   : Iterable[t]           )               : Ns[${`A..V`}, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
         |  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns[${`A..V`}, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
         |  def swap  (abs  : Seq[(t, t)]           )               : Ns[${`A..V`}, t] = _exprSetMan(Swap  , abs2sets(abs)             )
         |  def remove(v    : t, vs: t*             )               : Ns[${`A..V`}, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
         |  def remove(vs   : Iterable[t]           )               : Ns[${`A..V`}, t] = _exprSetMan(Remove, Seq(vs.toSet)             )
         |}""".stripMargin
  }
}
