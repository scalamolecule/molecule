package codegen.core.api.expression

import codegen.CoreGenBase


object _ExprSeqOpt extends CoreGenBase( "ExprSeqOpt", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.core.api.expression
       |
       |import molecule.db.core.ast._
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Entity1[${`_, _`}], Entity2[${`_, _, _`}]] extends ExprAttr_$arity[${`A..V, `}t, Entity1, Entity2] {
         |  protected def _exprSeqOpt(op: Op, optSeq: Option[Seq[t]]): Entity1[${`A..V`}, t] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t, Entity1[${`_, _`}], Entity2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Entity1, Entity2]{
         |  def apply(optSeq: Option[Seq[t]]): Entity1[${`A..V`}, t] = _exprSeqOpt(Eq, optSeq)
         |}""".stripMargin
  }
}
