package codegen.boilerplate.ast

import codegen.BoilerplateGenBase

object _Atoms {

  def generate = {
    MakeAtom("One", "Man").generate
    MakeAtom("One", "Opt").generate
    MakeAtom("One", "Tac").generate
    MakeAtom("Set", "Man").generate
    MakeAtom("Set", "Opt").generate
    MakeAtom("Set", "Tac").generate
  }

  case class MakeAtom(card: String, mode: String)
    extends BoilerplateGenBase("Atom" + card + mode + "_", "/ast") {
    val cardTpe = card match {
      case "One"   => (baseType: String) => baseType
      case "Set"   => (baseType: String) => s"Set[$baseType]"
      case "Array" => (baseType: String) => s"Array[$baseType]"
      case "Map"   => (baseType: String) => s"Map[String, $baseType]"
    }

    val content = {
      val atomClasses = baseTypes.map(body).mkString("\n")
      s"""// GENERATED CODE ********************************
         |package molecule.boilerplate.ast
         |
         |import java.net.URI
         |import java.util.{Date, UUID}
         |
         |trait Atom$card${mode}_ { self: ModelBase =>
         |
         |  sealed trait Atom$card$mode extends Atom$card
         |  $atomClasses
         |}""".stripMargin
    }

    def body(baseType: String): String = {
      val tpe = cardTpe(baseType)
      val vs  = if (mode == "Opt") s"Option[Seq[$tpe]] = None" else s"Seq[$tpe] = Nil"
      s"""
         |  case class Atom$card$mode$baseType(
         |    ns          : String,
         |    attr        : String,
         |    op          : Op = V,
         |    vs          : $vs,
         |    defaultValue: Option[$tpe] = None,
         |    validation  : Option[Validate$baseType] = None,
         |    sort        : Option[String] = None
         |  ) extends Atom$card$mode""".stripMargin
    }
  }
}
