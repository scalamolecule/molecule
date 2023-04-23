package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprSetMan extends BoilerplateGenBase( "ExprSetMan", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api.expression
       |
       |import molecule.base.ast.SchemaAST._
       |import molecule.boilerplate.api._
       |import molecule.boilerplate.ast.Model._
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val attrExprs = if (arity == 22) {
      s"""
         |  def apply[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(Eq   , a)
         |  def not  [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(Neq  , a)
         |  def has  [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(Has  , a)
         |  def hasNo[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(HasNo, a)
         |  def hasLt[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(HasLt, a)
         |  def hasLe[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(HasLe, a)
         |  def hasGt[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(HasGt, a)
         |  def hasGe[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(HasGe, a)""".stripMargin
    } else {
      s"""
         |  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[${`A..V`}, t] = _attrTac(Eq   , a)
         |  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet): Ns1[${`A..V`}, t] = _attrTac(Neq  , a)
         |  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[${`A..V`}, t] = _attrTac(Has  , a)
         |  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   ): Ns1[${`A..V`}, t] = _attrTac(HasNo, a)
         |  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[${`A..V`}, t] = _attrTac(HasLt, a)
         |  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[${`A..V`}, t] = _attrTac(HasLe, a)
         |  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[${`A..V`}, t] = _attrTac(HasGt, a)
         |  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[${`A..V`}, t] = _attrTac(HasGe, a)
         |
         |  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[${`A..V`}, Set[t], t] = _attrMan(Eq   , a)
         |  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[${`A..V`}, Set[t], t] = _attrMan(Neq  , a)
         |  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[${`A..V`}, X     , t] = _attrMan(Has  , a)
         |  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[${`A..V`}, X     , t] = _attrMan(HasNo, a)
         |  def hasLt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[${`A..V`}, t     , t] = _attrMan(HasLt, a)
         |  def hasLe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[${`A..V`}, t     , t] = _attrMan(HasLe, a)
         |  def hasGt[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[${`A..V`}, t     , t] = _attrMan(HasGt, a)
         |  def hasGe[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t     , t, ns1, ns2]             ): Ns2[${`A..V`}, t     , t] = _attrMan(HasGe, a)""".stripMargin
    }
    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends ExprAttr_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  protected def _exprSetMan(op: Op, sets: Seq[Set[t]]): Ns1[${`A..V`}, t] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Ns1, Ns2]
         |    with Aggregates_$arity[${`A..V`}, t, Ns1] {
         |  def apply (                             )               : Ns1[${`A..V`}, t] = _exprSetMan(Eq    , Nil                       )
         |  def apply (v    : t, vs: t*             )               : Ns1[${`A..V`}, t] = _exprSetMan(Eq    , (v +: vs).map(v => Set(v)))
         |  def apply (vs   : Seq[t]                )(implicit x: X): Ns1[${`A..V`}, t] = _exprSetMan(Eq    , vs.map(v => Set(v))       )
         |  def apply (set  : Set[t], sets: Set[t]* )               : Ns1[${`A..V`}, t] = _exprSetMan(Eq    , set +: sets               )
         |  def apply (sets : Seq[Set[t]]           )               : Ns1[${`A..V`}, t] = _exprSetMan(Eq    , sets                      )
         |  def not   (set  : Set[t], sets: Set[t]* )               : Ns1[${`A..V`}, t] = _exprSetMan(Neq   , set +: sets               )
         |  def not   (sets : Seq[Set[t]]           )               : Ns1[${`A..V`}, t] = _exprSetMan(Neq   , sets                      )
         |  def has   (v    : t, vs: t*             )               : Ns1[${`A..V`}, t] = _exprSetMan(Has   , (v +: vs).map(v => Set(v)))
         |  def has   (vs   : Seq[t]                )(implicit x: X): Ns1[${`A..V`}, t] = _exprSetMan(Has   , vs.map(v => Set(v))       )
         |  def has   (set  : Set[t], sets: Set[t]* )               : Ns1[${`A..V`}, t] = _exprSetMan(Has   , set +: sets               )
         |  def has   (sets : Seq[Set[t]]           )               : Ns1[${`A..V`}, t] = _exprSetMan(Has   , sets                      )
         |  def hasNo (v    : t, vs: t*             )               : Ns1[${`A..V`}, t] = _exprSetMan(HasNo , (v +: vs).map(v => Set(v)))
         |  def hasNo (vs   : Seq[t]                )(implicit x: X): Ns1[${`A..V`}, t] = _exprSetMan(HasNo , vs.map(v => Set(v))       )
         |  def hasNo (set  : Set[t], sets: Set[t]* )               : Ns1[${`A..V`}, t] = _exprSetMan(HasNo , set +: sets               )
         |  def hasNo (sets : Seq[Set[t]]           )               : Ns1[${`A..V`}, t] = _exprSetMan(HasNo , sets                      )
         |  def hasLt (upper: t                     )               : Ns1[${`A..V`}, t] = _exprSetMan(HasLt , Seq(Set(upper))           )
         |  def hasLe (upper: t                     )               : Ns1[${`A..V`}, t] = _exprSetMan(HasLe , Seq(Set(upper))           )
         |  def hasGt (lower: t                     )               : Ns1[${`A..V`}, t] = _exprSetMan(HasGt , Seq(Set(lower))           )
         |  def hasGe (lower: t                     )               : Ns1[${`A..V`}, t] = _exprSetMan(HasGe , Seq(Set(lower))           )
         |
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
