package codegen.core.transaction

import codegen.CoreGenBase

object _InsertValidators extends CoreGenBase("InsertValidators", "/transaction") {

  override val content = {
    s"""// GENERATED CODE ********************************
       |package molecule.core.transaction
       |
       |import java.net.URI
       |import java.util.{Date, UUID}
       |import molecule.boilerplate.ast.Model._
       |import molecule.core.validation.insert.InsertValueResolvers_
       |
       |trait $fileName_ extends InsertValueResolvers_ {
       |  $validators
       |}""".stripMargin
  }

  private def validators: String = {
    baseTypes.map { baseType =>
      s"""
         |  protected def validator$baseType(
         |    optValidator: Option[Validate$baseType],
         |    a: Attr,
         |    curElements: List[Element]
         |  ): Option[Product => $baseType => Seq[String]] = {
         |    optValidator.fold(
         |      Option.empty[Product => $baseType => Seq[String]]
         |    ) { validator =>
         |      if (a.valueAttrs.isEmpty) {
         |        Some((_: Product) => (v: $baseType) => validator.validate(v))
         |      } else {
         |        val tpl2values = tpl2valueResolver(a, curElements)
         |        Some(
         |          (tpl: Product) => {
         |            val values = tpl2values(tpl)
         |            (v: $baseType) =>
         |              validator.withValues(values).validate(v)
         |          }
         |        )
         |      }
         |    }
         |  }""".stripMargin
    }.mkString("\n")
  }
}
