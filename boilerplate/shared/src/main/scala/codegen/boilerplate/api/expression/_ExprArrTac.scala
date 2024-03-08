package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprArrTac extends BoilerplateGenBase("ExprArrTac", "/api/expression") {
  val content = {
    val traits = (0 to 22).map(arity => Trait(arity).body).mkString("\n")
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
         |  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[${`A..V, `}t] = _attrTac(Eq   , a)
         |  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[${`A..V, `}t] = _attrTac(Neq  , a)
         |  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[${`A..V, `}t] = _attrTac(Has  , a)
         |  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[${`A..V, `}t] = _attrTac(HasNo, a)
         |
         |  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[${`A..V, `}Array[t], t] = _attrMan(Eq   , a)
         |  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[${`A..V, `}Array[t], t] = _attrMan(Neq  , a)
         |  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[${`A..V, `}X       , t] = _attrMan(Has  , a)
         |  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[${`A..V, `}X       , t] = _attrMan(HasNo, a)""".stripMargin
    }

    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V, `}t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends ExprAttr_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  protected def _exprArrTac[u <: t: ClassTag](op: Op, vs: Seq[Array[u]]): Ns1[${`A..V, `}u] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V, `}t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  def apply [u <: t: ClassTag](                                   )               : Ns1[${`A..V, `}u] = _exprArrTac(NoValue, Nil                         )
         |  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[${`A..V, `}u] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
         |  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[${`A..V, `}u] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
         |  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[${`A..V, `}u] = _exprArrTac(Eq     , array +: arrays             )
         |  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[${`A..V, `}u] = _exprArrTac(Eq     , arrays                      )
         |  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[${`A..V, `}u] = _exprArrTac(Neq    , array +: arrays             )
         |  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[${`A..V, `}u] = _exprArrTac(Neq    , arrays                      )
         |  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[${`A..V, `}u] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
         |  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[${`A..V, `}u] = _exprArrTac(Has    , vs.map(v => Array(v))       )
         |  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[${`A..V, `}u] = _exprArrTac(Has    , array +: arrays             )
         |  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[${`A..V, `}u] = _exprArrTac(Has    , arrays                      )
         |  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[${`A..V, `}u] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
         |  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[${`A..V, `}u] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
         |  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[${`A..V, `}u] = _exprArrTac(HasNo  , array +: arrays             )
         |  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[${`A..V, `}u] = _exprArrTac(HasNo  , arrays                      )
         |  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[${`A..V, `}u] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
         |  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[${`A..V, `}u] = _exprArrTac(Add    , Seq(vs.toArray)             )
         |  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[${`A..V, `}u] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
         |  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[${`A..V, `}u] = _exprArrTac(Remove , Seq(vs.toArray)             )
         |  $attrExprs
         |}""".stripMargin
  }
}
