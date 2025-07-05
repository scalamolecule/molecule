package boilerplate.db.core.validation.insert

import boilerplate.db.core.DbCoreBase

object _InsertValidationResolvers extends DbCoreBase("InsertValidationResolvers", "/validation/insert") {

  val content = {
    val validateX         = (1 to 22).map(i => s"case ${caseN(i)} => validate$i(validators)").mkString("\n      ")
    val validationMethods = (1 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.core.validation.insert
       |
       |import molecule.base.error.InsertError
       |import molecule.core.dataModel.*
       |import molecule.db.core.api.MetaDb
       |
       |trait $fileName_ {
       |
       |  def getValidators(
       |    metaDb: MetaDb,
       |    elements: List[Element],
       |    validators: List[Product => Seq[InsertError]],
       |    tplIndex: Int,
       |    prevRefs: List[String]
       |  ): List[Product => Seq[InsertError]]
       |
       |  def getInsertValidator(
       |    metaDb: MetaDb,
       |    elements: List[Element],
       |  ): Product => Seq[InsertError] = {
       |    val validators: List[Product => Seq[InsertError]] =
       |      getValidators(metaDb, elements, Nil, 0, Nil)
       |
       |    validators.length match {
       |      $validateX
       |      case n  =>
       |        (tpl: Product) =>
       |          var i          = n - 1
       |          var errorLists = List.empty[Seq[InsertError]]
       |          while (i >= 0) {
       |            errorLists = validators(i)(tpl) +: errorLists
       |            i -= 1
       |          }
       |          errorLists.flatten
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
