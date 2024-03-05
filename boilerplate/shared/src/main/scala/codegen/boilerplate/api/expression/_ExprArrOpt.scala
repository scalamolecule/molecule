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
         |trait ${fileName}Ops_$arity[${`A..V`}, t0, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends ExprAttr_$arity[${`A..V, `}t0, Ns1, Ns2] {
         |  protected def _exprArrOpt[t <: t0: ClassTag](op: Op, optArrays: Option[Seq[Array[t]]]): Ns1[${`A..V`}, t] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t0, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t0, Ns1, Ns2]{
         |  def apply[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[${`A..V`}, t] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
         |  def apply[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[${`A..V`}, t] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
         |  def apply[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[${`A..V`}, t] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
         |  def apply[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[${`A..V`}, t] = _exprArrOpt(Eq   , arrays                        )
         |  def not  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[${`A..V`}, t] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
         |  def not  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[${`A..V`}, t] = _exprArrOpt(Neq  , arrays                        )
         |  def has  [t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[${`A..V`}, t] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
         |  def has  [t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[${`A..V`}, t] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
         |  def has  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[${`A..V`}, t] = _exprArrOpt(Has  , array.map(array => Seq(array)))
         |  def has  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[${`A..V`}, t] = _exprArrOpt(Has  , arrays                        )
         |  def hasNo[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[${`A..V`}, t] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
         |  def hasNo[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[${`A..V`}, t] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
         |  def hasNo[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[${`A..V`}, t] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
         |  def hasNo[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[${`A..V`}, t] = _exprArrOpt(HasNo, arrays                        )
         |}""".stripMargin
  }
}
