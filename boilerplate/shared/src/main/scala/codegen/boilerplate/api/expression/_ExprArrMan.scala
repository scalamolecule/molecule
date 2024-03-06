package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprArrMan extends BoilerplateGenBase("ExprArrMan", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api.expression
       |
       |import molecule.base.ast._
       |import molecule.boilerplate.api._
       |import molecule.boilerplate.ast.Model._
       |import scala.reflect.ClassTag
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val attrExprs = if (arity == 22) {
      s"""
         |  def apply[ns1[_]](a: ModelOps_0[t0, ns1, X2]): Ns1[${`A..V`}, t0] = _attrTac(Eq   , a)
         |  def not  [ns1[_]](a: ModelOps_0[t0, ns1, X2]): Ns1[${`A..V`}, t0] = _attrTac(Neq  , a)
         |  def has  [ns1[_]](a: ModelOps_0[t0, ns1, X2]): Ns1[${`A..V`}, t0] = _attrTac(Has  , a)
         |  def hasNo[ns1[_]](a: ModelOps_0[t0, ns1, X2]): Ns1[${`A..V`}, t0] = _attrTac(HasNo, a)""".stripMargin
    } else {
      s"""
         |  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[${`A..V`}, t0] = _attrTac(Eq   , a)
         |  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[${`A..V`}, t0] = _attrTac(Neq  , a)
         |  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[${`A..V`}, t0] = _attrTac(Has  , a)
         |  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[${`A..V`}, t0] = _attrTac(HasNo, a)
         |
         |  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[${`A..V`}, Array[t0], t0] = _attrMan(Eq   , a)
         |  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[${`A..V`}, Array[t0], t0] = _attrMan(Neq  , a)
         |  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[${`A..V`}, X        , t0] = _attrMan(Has  , a)
         |  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[${`A..V`}, X        , t0] = _attrMan(HasNo, a)""".stripMargin
    }

    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V`}, t0, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends ExprAttr_$arity[${`A..V, `}t0, Ns1, Ns2] {
         |  protected def _exprArrMan[t <: t0: ClassTag](op: Op, arrays: Seq[Array[t]]): Ns1[${`A..V`}, t] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t0, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t0, Ns1, Ns2]
         |    with Aggregates_$arity[${`A..V`}, t0, Ns1] {
         |  def apply [t <: t0: ClassTag](                                   )               : Ns1[${`A..V`}, t] = _exprArrMan(Eq    , Nil                         )
         |  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[${`A..V`}, t] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
         |  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[${`A..V`}, t] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
         |  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[${`A..V`}, t] = _exprArrMan(Eq    , array +: arrays             )
         |  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[${`A..V`}, t] = _exprArrMan(Eq    , arrays                      )
         |  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[${`A..V`}, t] = _exprArrMan(Neq   , array +: arrays             )
         |  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[${`A..V`}, t] = _exprArrMan(Neq   , arrays                      )
         |  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[${`A..V`}, t] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
         |  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[${`A..V`}, t] = _exprArrMan(Has   , vs.map(v => Array(v))       )
         |  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[${`A..V`}, t] = _exprArrMan(Has   , array +: arrays             )
         |  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[${`A..V`}, t] = _exprArrMan(Has   , arrays                      )
         |  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[${`A..V`}, t] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
         |  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[${`A..V`}, t] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
         |  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[${`A..V`}, t] = _exprArrMan(HasNo , array +: arrays             )
         |  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[${`A..V`}, t] = _exprArrMan(HasNo , arrays                      )
         |  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[${`A..V`}, t] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
         |  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[${`A..V`}, t] = _exprArrMan(Add   , Seq(vs.toArray)             )
         |  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[${`A..V`}, t] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
         |  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[${`A..V`}, t] = _exprArrMan(Remove, Seq(vs.toArray)             )
         |  $attrExprs
         |}""".stripMargin
  }
}
