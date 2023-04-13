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
    val attrResolvers = if (arity == 22) {
      s"""
         |  protected def _attrTac[ns1[_]](op: Op, a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = ???""".stripMargin
    } else {
      s"""
         |  protected def _attrTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[${`A..V`},    t] = ???
         |  protected def _attrMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] = ???""".stripMargin
    }

    val attrExprs = if (arity == 22) {
      s"""
         |  def apply[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(Appl, a)
         |  def not  [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(Not , a)
         |  def <    [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(Lt  , a)
         |  def <=   [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(Le  , a)
         |  def >    [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(Gt  , a)
         |  def >=   [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(Ge  , a)""".stripMargin
    } else {
      s"""
         |  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V`}, t] = _attrTac(Appl, a)
         |  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V`}, t] = _attrTac(Not , a)
         |  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V`}, t] = _attrTac(Lt  , a)
         |  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V`}, t] = _attrTac(Le  , a)
         |  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V`}, t] = _attrTac(Gt  , a)
         |  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V`}, t] = _attrTac(Ge  , a)
         |
         |  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] = _attrMan(Appl, a)
         |  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] = _attrMan(Not , a)
         |  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] = _attrMan(Lt  , a)
         |  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] = _attrMan(Le  , a)
         |  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] = _attrMan(Gt  , a)
         |  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] = _attrMan(Ge  , a)""".stripMargin
    }
    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends ExprBase {
         |  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[${`A..V`}, t] = ???
         |  $attrResolvers
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Ns1, Ns2]
         |    with Aggregates_$arity[${`A..V`}, t, Ns1] {
         |  def apply (                             )               : Ns1[${`A..V`}, t] = _exprSetMan(Appl  , Nil                       )
         |  def apply (v    : t, vs: t*             )               : Ns1[${`A..V`}, t] = _exprSetMan(Appl  , (v +: vs).map(v => Set(v)))
         |  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[${`A..V`}, t] = _exprSetMan(Appl  , vs.map(v => Set(v))       )
         |  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[${`A..V`}, t] = _exprSetMan(Appl  , set +: sets               )
         |  def apply (sets : Seq[Set[t]]           )               : Ns1[${`A..V`}, t] = _exprSetMan(Appl  , sets                      )
         |  def not   (v    : t, vs: t*             )               : Ns1[${`A..V`}, t] = _exprSetMan(Not   , (v +: vs).map(v => Set(v)))
         |  def not   (vs   : Seq[t]                )(implicit x: X): Ns1[${`A..V`}, t] = _exprSetMan(Not   , vs.map(v => Set(v))       )
         |  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[${`A..V`}, t] = _exprSetMan(Not   , set +: sets               )
         |  def not   (sets : Seq[Set[t]]           )               : Ns1[${`A..V`}, t] = _exprSetMan(Not   , sets                      )
         |  def ==    (set  : Set[t]                )               : Ns1[${`A..V`}, t] = _exprSetMan(Eq    , Seq(set)                  )
         |  def ==    (set  : Set[t], sets: Set[t]* )               : Ns1[${`A..V`}, t] = _exprSetMan(Eq    , set +: sets               )
         |  def ==    (sets : Seq[Set[t]]           )               : Ns1[${`A..V`}, t] = _exprSetMan(Eq    , sets                      )
         |  def !=    (set  : Set[t]                )               : Ns1[${`A..V`}, t] = _exprSetMan(Neq   , Seq(set)                  )
         |  def !=    (set  : Set[t], sets: Set[t]* )               : Ns1[${`A..V`}, t] = _exprSetMan(Neq   , set +: sets               )
         |  def !=    (sets : Seq[Set[t]]           )               : Ns1[${`A..V`}, t] = _exprSetMan(Neq   , sets                      )
         |  def <     (upper: t                     )               : Ns1[${`A..V`}, t] = _exprSetMan(Lt    , Seq(Set(upper))           )
         |  def <=    (upper: t                     )               : Ns1[${`A..V`}, t] = _exprSetMan(Le    , Seq(Set(upper))           )
         |  def >     (lower: t                     )               : Ns1[${`A..V`}, t] = _exprSetMan(Gt    , Seq(Set(lower))           )
         |  def >=    (lower: t                     )               : Ns1[${`A..V`}, t] = _exprSetMan(Ge    , Seq(Set(lower))           )
         |  def add   (v    : t, vs: t*             )               : Ns1[${`A..V`}, t] = _exprSetMan(Add   , Seq((v +: vs).toSet)      )
         |  def add   (vs   : Iterable[t]           )               : Ns1[${`A..V`}, t] = _exprSetMan(Add   , Seq(vs.toSet)             )
         |  def swap  (ab   : (t, t), abs: (t, t)*  )               : Ns1[${`A..V`}, t] = _exprSetMan(Swap  , abs2sets(ab +: abs)       )
         |  def swap  (abs  : Seq[(t, t)]           )               : Ns1[${`A..V`}, t] = _exprSetMan(Swap  , abs2sets(abs)             )
         |  def remove(v    : t, vs: t*             )               : Ns1[${`A..V`}, t] = _exprSetMan(Remove, Seq((v +: vs).toSet)      )
         |  def remove(vs   : Iterable[t]           )               : Ns1[${`A..V`}, t] = _exprSetMan(Remove, Seq(vs.toSet)             )
         |  $attrExprs
         |}""".stripMargin
  }
}
