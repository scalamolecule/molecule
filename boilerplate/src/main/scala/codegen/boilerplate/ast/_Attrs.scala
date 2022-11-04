package codegen.boilerplate.ast

import codegen.BoilerplateGenBase

object _Attrs {

  def generate = {
    MakeAttr("One", "Man").generate
    MakeAttr("One", "Opt").generate
    MakeAttr("One", "Tac").generate
    MakeAttr("Set", "Man").generate
    MakeAttr("Set", "Opt").generate
    MakeAttr("Set", "Tac").generate
  }

  case class MakeAttr(card: String, mode: String)
    extends BoilerplateGenBase("Attr" + card + mode, "/ast") {
    val cardTpe = card match {
      case "One"   => (baseType: String) => baseType
      case "Set"   => (baseType: String) => s"Set[$baseType]"
      case "Array" => (baseType: String) => s"Array[$baseType]"
      case "Map"   => (baseType: String) => s"Map[String, $baseType]"
    }

    val content = {
      val attrClasses = baseTypes.map(body).mkString("\n")
      s"""// GENERATED CODE ********************************
         |package molecule.boilerplate.ast
         |
         |import java.net.URI
         |import java.util.{Date, UUID}
         |
         |trait ${fileName}_ { self: ModelBase =>
         |
         |  sealed trait Attr$card$mode extends Attr$card
         |  $attrClasses
         |}""".stripMargin
    }

    def body(baseType: String): String = {
      val tpe = cardTpe(baseType)
      val vs  = if (mode == "Opt") s"Option[Seq[$tpe]] = None" else s"Seq[$tpe] = Nil"
      s"""
         |  case class ${fileName}$baseType(
         |    ns          : String,
         |    attr        : String,
         |    op          : Op = V,
         |    vs          : $vs,
         |    defaultValue: Option[$tpe] = None,
         |    validation  : Option[Validate$baseType] = None,
         |    sort        : Option[String] = None
         |  ) extends Attr$card$mode""".stripMargin
    }
  }
}