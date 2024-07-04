package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprMapMan extends BoilerplateGenBase("ExprMapMan", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api.expression
       |
       |import molecule.boilerplate.ast.Model._
       |import scala.language.higherKinds
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |
         |trait $fileName_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ExprMapTacOps_$arity[${`A..V`}, t, Ns1, Ns2] {
         |  def apply (                                       ): Ns1[${`A..V`}, t] = _exprMap (NoValue, Map.empty[String, t] )
         |  def apply (map  : Map[String, t]                  ): Ns1[${`A..V`}, t] = _exprMap (Eq     , map                  )
         |  def apply (key  : String                          ): Ns1[${`A..t`}, t] = _exprMapK(Has    , Seq(key)             )
         |  def add   (pair : (String, t), pairs: (String, t)*): Ns1[${`A..V`}, t] = _exprMap (Add    , (pair +: pairs).toMap)
         |  def add   (pairs: Iterable[(String, t)]           ): Ns1[${`A..V`}, t] = _exprMap (Add    , pairs.toMap          )
         |  def remove(key  : String, keys: String*           ): Ns1[${`A..t`}, t] = _exprMapK(Remove , Seq(key) ++ keys     )
         |  def remove(keys : Seq[String]                     ): Ns1[${`A..t`}, t] = _exprMapK(Remove , keys                 )
         |}""".stripMargin
  }
}
