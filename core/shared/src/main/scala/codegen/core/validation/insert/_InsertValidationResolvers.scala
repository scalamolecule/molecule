package codegen.core.validation.insert

import codegen.CoreGenBase

object _InsertValidationResolvers extends CoreGenBase("InsertValidationResolvers", "/validation/insert") {

  val content = {
    val validateX         = (1 to 22).map(i => s"case $i => validate$i(validators)").mkString("\n      ")
    val validationMethods = (1 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.core.validation.insert
       |
       |import molecule.base.ast.SchemaAST._
       |import molecule.base.error.InsertError
       |import molecule.boilerplate.ast.Model._
       |
       |trait $fileName_ {
       |
       |def getValidators(
       |    nsMap: Map[String, MetaNs],
       |    elements: List[Element],
       |    validators: List[Product => Seq[InsertError]],
       |    outerTpl: Int,
       |    tplIndex: Int
       |  ): List[Product => Seq[InsertError]]
       |
       |  def getInsertValidator(
       |    nsMap: Map[String, MetaNs],
       |    elements: List[Element],
       |    outerTpl: Int = 0
       |  ): Product => Seq[InsertError] = {
       |    val validators: List[Product => Seq[InsertError]] =
       |      getValidators(nsMap, elements, Nil, outerTpl, 0)
       |
       |    validators.length match {
       |      $validateX
       |    }
       |  }
       |$validationMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val validators = (1 to i).map { j => s"v$j" }.mkString(", ")
    val calls      = (1 to i).map { j => s"v$j(tpl)" }.mkString(",\n        ")
    val body       =
      s"""
         |  final private def validate$i(
         |    validators: List[Product => Seq[InsertError]]
         |  ): Product => Seq[InsertError] = {
         |    val List($validators) = validators
         |    (tpl: Product) =>
         |      Seq(
         |        $calls
         |      ).flatten
         |  }""".stripMargin
  }
}
