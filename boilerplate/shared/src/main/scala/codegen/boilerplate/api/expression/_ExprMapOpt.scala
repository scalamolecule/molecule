package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprMapOpt extends BoilerplateGenBase( "ExprMapOpt", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api.expression
       |
       |import molecule.boilerplate.ast.Model._
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends ExprAttr_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  protected def _exprMapOptK(op: Op, optKeys: Option[Seq[String]]): Ns1[${`A..V`}, t] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Ns1, Ns2]{
         |  def apply(key : Option[String]     )(implicit x: X)            : Ns1[${`A..V`}, t] = _exprMapOptK(Eq   , key )
         |  def apply(keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[${`A..V`}, t] = _exprMapOptK(Eq   , keys)
         |  def not  (key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[${`A..V`}, t] = _exprMapOptK(Neq  , key )
         |  def not  (keys: Option[Seq[String]])                           : Ns1[${`A..V`}, t] = _exprMapOptK(Neq  , keys)
         |  def has  (key : Option[String]     )(implicit x: X)            : Ns1[${`A..V`}, t] = _exprMapOptK(Has  , key )
         |  def has  (keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[${`A..V`}, t] = _exprMapOptK(Has  , keys)
         |  def hasNo(key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[${`A..V`}, t] = _exprMapOptK(HasNo, key )
         |  def hasNo(keys: Option[Seq[String]])                           : Ns1[${`A..V`}, t] = _exprMapOptK(HasNo, keys)
         |}""".stripMargin
  }
}
