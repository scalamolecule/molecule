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
         |  def apply[ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[${`A..V`}, t] = _attrTac(Eq   , a)
         |  def not  [ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[${`A..V`}, t] = _attrTac(Neq  , a)
         |  def has  [ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[${`A..V`}, t] = _attrTac(Has  , a)
         |  def hasNo[ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[${`A..V`}, t] = _attrTac(HasNo, a)""".stripMargin
    } else {
      s"""
         |  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[${`A..V`}, t] = _attrTac(Eq   , a)
         |  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[${`A..V`}, t] = _attrTac(Neq  , a)
         |  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[${`A..V`}, t] = _attrTac(Has  , a)
         |  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[${`A..V`}, t] = _attrTac(HasNo, a)
         |
         |  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[${`A..V`}, Array[t], t] = _attrMan(Eq   , a)
         |  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[${`A..V`}, Array[t], t] = _attrMan(Neq  , a)
         |  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[${`A..V`}, X       , t] = _attrMan(Has  , a)
         |  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[${`A..V`}, X       , t] = _attrMan(HasNo, a)""".stripMargin
    }

    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends ExprAttr_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  protected def _exprArrMan[u <: t: ClassTag](op: Op, arrays: Seq[Array[u]]): Ns1[${`A..V`}, u] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Ns1, Ns2]
         |    with Aggregates_$arity[${`A..V`}, t, Ns1] {
         |  def apply [u <: t: ClassTag](                                   )               : Ns1[${`A..V`}, u] = _exprArrMan(Eq    , Nil                         )
         |  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[${`A..V`}, u] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
         |  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[${`A..V`}, u] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
         |  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[${`A..V`}, u] = _exprArrMan(Eq    , array +: arrays             )
         |  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[${`A..V`}, u] = _exprArrMan(Eq    , arrays                      )
         |  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[${`A..V`}, u] = _exprArrMan(Neq   , array +: arrays             )
         |  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[${`A..V`}, u] = _exprArrMan(Neq   , arrays                      )
         |  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[${`A..V`}, u] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
         |  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[${`A..V`}, u] = _exprArrMan(Has   , vs.map(v => Array(v))       )
         |  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[${`A..V`}, u] = _exprArrMan(Has   , array +: arrays             )
         |  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[${`A..V`}, u] = _exprArrMan(Has   , arrays                      )
         |  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[${`A..V`}, u] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
         |  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[${`A..V`}, u] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
         |  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[${`A..V`}, u] = _exprArrMan(HasNo , array +: arrays             )
         |  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[${`A..V`}, u] = _exprArrMan(HasNo , arrays                      )
         |  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[${`A..V`}, u] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
         |  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[${`A..V`}, u] = _exprArrMan(Add   , Seq(vs.toArray)             )
         |  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[${`A..V`}, u] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
         |  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[${`A..V`}, u] = _exprArrMan(Remove, Seq(vs.toArray)             )
         |  $attrExprs
         |}""".stripMargin
  }
}
