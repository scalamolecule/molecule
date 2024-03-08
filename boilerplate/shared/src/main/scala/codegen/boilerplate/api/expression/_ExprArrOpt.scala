package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprArrOpt extends BoilerplateGenBase( "ExprArrOpt", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api.expression
       |
       |import molecule.boilerplate.ast.Model._
       |import scala.reflect.ClassTag
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends ExprAttr_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  protected def _exprArrOpt[u <: t: ClassTag](op: Op, optArrays: Option[Seq[Array[u]]]): Ns1[${`A..V`}, u] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Ns1, Ns2]{
         |  def apply[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[${`A..V`}, u] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
         |  def apply[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[${`A..V`}, u] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
         |  def apply[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[${`A..V`}, u] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
         |  def apply[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[${`A..V`}, u] = _exprArrOpt(Eq   , arrays                        )
         |  def not  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[${`A..V`}, u] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
         |  def not  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[${`A..V`}, u] = _exprArrOpt(Neq  , arrays                        )
         |  def has  [u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[${`A..V`}, u] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
         |  def has  [u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[${`A..V`}, u] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
         |  def has  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[${`A..V`}, u] = _exprArrOpt(Has  , array.map(array => Seq(array)))
         |  def has  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[${`A..V`}, u] = _exprArrOpt(Has  , arrays                        )
         |  def hasNo[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[${`A..V`}, u] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
         |  def hasNo[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[${`A..V`}, u] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
         |  def hasNo[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[${`A..V`}, u] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
         |  def hasNo[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[${`A..V`}, u] = _exprArrOpt(HasNo, arrays                        )
         |}""".stripMargin
  }
}
