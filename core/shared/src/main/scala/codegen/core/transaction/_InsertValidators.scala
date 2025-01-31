package codegen.core.transaction

import codegen.CoreGenBase

object _InsertValidators extends CoreGenBase("InsertValidators", "/transaction") {

  override val content = {
    s"""// GENERATED CODE ********************************
       |package molecule.core.transaction
       |
       |import java.net.URI
       |import java.time._
       |import java.util.{Date, UUID}
       |import molecule.core.ast.DataModel._
       |import molecule.core.validation.insert.InsertValueResolvers_
       |
       |trait $fileName_ extends InsertValueResolvers_ {
       |  $validators
       |}""".stripMargin
  }

  private def validators: String = {
    baseTypes.map { baseTpe0 =>
      val baseTpe = if (baseTpe0 == "ID") "String" else baseTpe0
      s"""
         |  protected def validator$baseTpe0(
         |    optValidator: Option[Validate$baseTpe0],
         |    a: Attr,
         |    curElements: List[Element]
         |  ): Option[Product => $baseTpe => Seq[String]] = {
         |    optValidator.fold(
         |      Option.empty[Product => $baseTpe => Seq[String]]
         |    ) { validator =>
         |      if (a.valueAttrs.isEmpty) {
         |        Some((_: Product) => (v: $baseTpe) => validator.validate(v))
         |      } else {
         |        val tpl2values = tpl2valueResolver(a, curElements)
         |        Some(
         |          (tpl: Product) => {
         |            val values = tpl2values(tpl)
         |            (v: $baseTpe) =>
         |              validator.withValues(values).validate(v)
         |          }
         |        )
         |      }
         |    }
         |  }""".stripMargin
    }.mkString("\n")
  }
}
