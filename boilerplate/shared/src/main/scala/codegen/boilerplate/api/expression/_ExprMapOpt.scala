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
         |  protected def _exprMapOpK(op: Op, optKeys: Option[Seq[String]]): Ns1[${`A..V`}, t] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Ns1, Ns2]{
         |  def apply(key : Option[String]     )(implicit x: X)            : Ns1[${`A..V`}, t] = _exprMapOpK(Eq   , key.map(Seq(_)))
         |  def apply(keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[${`A..V`}, t] = _exprMapOpK(Eq   , keys           )
         |  def not  (key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[${`A..V`}, t] = _exprMapOpK(Neq  , key.map(Seq(_)))
         |  def not  (keys: Option[Seq[String]])                           : Ns1[${`A..V`}, t] = _exprMapOpK(Neq  , keys           )
         |  def has  (key : Option[String]     )(implicit x: X)            : Ns1[${`A..V`}, t] = _exprMapOpK(Has  , key.map(Seq(_)))
         |  def has  (keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[${`A..V`}, t] = _exprMapOpK(Has  , keys           )
         |  def hasNo(key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[${`A..V`}, t] = _exprMapOpK(HasNo, key.map(Seq(_)))
         |  def hasNo(keys: Option[Seq[String]])                           : Ns1[${`A..V`}, t] = _exprMapOpK(HasNo, keys           )
         |}""".stripMargin
  }
}
