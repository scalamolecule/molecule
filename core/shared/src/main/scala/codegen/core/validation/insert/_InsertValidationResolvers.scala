package codegen.core.validation.insert

import codegen.CoreGenBase

object _InsertValidationResolvers extends CoreGenBase("InsertValidationResolvers", "/validation/insert") {

  val content = {
    val validateX         = (1 to 22).map(i => s"case ${caseN(i)} => validate$i(validators)").mkString("\n      ")
    val validationMethods = (1 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.core.validation.insert
       |
       |import molecule.base.ast._
       |import molecule.base.error.InsertError
       |import molecule.boilerplate.ast.DataModel._
       |
       |trait $fileName_ {
       |
       |  def getValidators(
       |    entityMap: Map[String, MetaEntity],
       |    elements: List[Element],
       |    validators: List[Product => Seq[InsertError]],
       |    tplIndex: Int,
       |    prevRefs: List[String]
       |  ): List[Product => Seq[InsertError]]
       |
       |  def getInsertValidator(
       |    entityMap: Map[String, MetaEntity],
       |    elements: List[Element],
       |  ): Product => Seq[InsertError] = {
       |    val validators: List[Product => Seq[InsertError]] =
       |      getValidators(entityMap, elements, Nil, 0, Nil)
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
