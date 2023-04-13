package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprSetTac extends BoilerplateGenBase("ExprSetTac", "/api/expression") {
  val content = {
    val traits = (0 to 22).map(arity => Trait(arity).body).mkString("\n")
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
         |  protected def _attrTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[${`A..V, `}   t] = ???
         |  protected def _attrMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V, `}X, t] = ???""".stripMargin
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
         |  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V, `}t] = _attrTac(Appl, a)
         |  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V, `}t] = _attrTac(Not , a)
         |  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V, `}t] = _attrTac(Lt  , a)
         |  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V, `}t] = _attrTac(Le  , a)
         |  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V, `}t] = _attrTac(Gt  , a)
         |  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V, `}t] = _attrTac(Ge  , a)
         |
         |  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V, `}X, t] = _attrMan(Appl, a)
         |  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V, `}X, t] = _attrMan(Not , a)
         |  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V, `}X, t] = _attrMan(Lt  , a)
         |  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V, `}X, t] = _attrMan(Le  , a)
         |  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V, `}X, t] = _attrMan(Gt  , a)
         |  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V, `}X, t] = _attrMan(Ge  , a)""".stripMargin
    }
    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V, `}t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends ExprBase {
         |  protected def _exprSetTac(op: Op, vs: Seq[Set[t]]): Ns1[${`A..V, `}t] = ???
         |  $attrResolvers
         |}
         |
         |trait $fileName_$arity[${`A..V, `}t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  def apply (                            )               : Ns1[${`A..V, `}t] = _exprSetTac(NoValue, Nil                       )
         |  def apply (v    : t, vs: t*            )               : Ns1[${`A..V, `}t] = _exprSetTac(Appl   , (v +: vs).map(v => Set(v)))
         |  def apply (vs   : Seq[t]               )(implicit x: X): Ns1[${`A..V, `}t] = _exprSetTac(Appl   , vs.map(v => Set(v))       )
         |  def apply (set  : Set[t], sets: Set[t]*)               : Ns1[${`A..V, `}t] = _exprSetTac(Appl   , set +: sets               )
         |  def apply (sets : Seq[Set[t]]          )               : Ns1[${`A..V, `}t] = _exprSetTac(Appl   , sets                      )
         |  def not   (v    : t, vs: t*            )               : Ns1[${`A..V, `}t] = _exprSetTac(Not    , (v +: vs).map(v => Set(v)))
         |  def not   (vs   : Seq[t]               )(implicit x: X): Ns1[${`A..V, `}t] = _exprSetTac(Not    , vs.map(v => Set(v))       )
         |  def not   (set  : Set[t], sets: Set[t]*)               : Ns1[${`A..V, `}t] = _exprSetTac(Not    , set +: sets               )
         |  def not   (sets : Seq[Set[t]]          )               : Ns1[${`A..V, `}t] = _exprSetTac(Not    , sets                      )
         |  def ==    (set  : Set[t]               )               : Ns1[${`A..V, `}t] = _exprSetTac(Eq     , Seq(set)                  )
         |  def ==    (set  : Set[t], sets: Set[t]*)               : Ns1[${`A..V, `}t] = _exprSetTac(Eq     , set +: sets               )
         |  def ==    (sets : Seq[Set[t]]          )               : Ns1[${`A..V, `}t] = _exprSetTac(Eq     , sets                      )
         |  def !=    (set  : Set[t]               )               : Ns1[${`A..V, `}t] = _exprSetTac(Neq    , Seq(set)                  )
         |  def !=    (set  : Set[t], sets: Set[t]*)               : Ns1[${`A..V, `}t] = _exprSetTac(Neq    , set +: sets               )
         |  def !=    (sets : Seq[Set[t]]          )               : Ns1[${`A..V, `}t] = _exprSetTac(Neq    , sets                      )
         |  def <     (upper: t                    )               : Ns1[${`A..V, `}t] = _exprSetTac(Lt     , Seq(Set(upper))           )
         |  def <=    (upper: t                    )               : Ns1[${`A..V, `}t] = _exprSetTac(Le     , Seq(Set(upper))           )
         |  def >     (lower: t                    )               : Ns1[${`A..V, `}t] = _exprSetTac(Gt     , Seq(Set(lower))           )
         |  def >=    (lower: t                    )               : Ns1[${`A..V, `}t] = _exprSetTac(Ge     , Seq(Set(lower))           )
         |  def add   (v    : t, vs: t*            )               : Ns1[${`A..V, `}t] = _exprSetTac(Add    , Seq((v +: vs).toSet)      )
         |  def add   (vs   : Iterable[t]          )               : Ns1[${`A..V, `}t] = _exprSetTac(Add    , Seq(vs.toSet)             )
         |  def swap  (ab   : (t, t), abs: (t, t)* )               : Ns1[${`A..V, `}t] = _exprSetTac(Swap   , abs2sets(ab +: abs)       )
         |  def swap  (abs  : Seq[(t, t)]          )               : Ns1[${`A..V, `}t] = _exprSetTac(Swap   , abs2sets(abs)             )
         |  def remove(v    : t, vs: t*            )               : Ns1[${`A..V, `}t] = _exprSetTac(Remove , Seq((v +: vs).toSet)      )
         |  def remove(vs   : Iterable[t]          )               : Ns1[${`A..V, `}t] = _exprSetTac(Remove , Seq(vs.toSet)             )
         |  $attrExprs
         |}""".stripMargin
  }
}
