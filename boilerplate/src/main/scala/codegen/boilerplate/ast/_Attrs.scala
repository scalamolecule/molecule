package codegen.boilerplate.ast

import codegen.BoilerplateGenBase

object _Attrs {

  def generate = {
    MakeAttr("One", "Man", "Mandatory").generate
    MakeAttr("One", "Opt", "Optional").generate
    MakeAttr("One", "Tac", "Tacit").generate
    MakeAttr("Set", "Man", "Mandatory").generate
    MakeAttr("Set", "Opt", "Optional").generate
    MakeAttr("Set", "Tac", "Tacit").generate
  }

  case class MakeAttr(card: String, mode: String, modeFull: String)
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
         |  sealed trait Attr$card$mode extends Attr$card with $modeFull
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


  //  class ValInt extends ValidateInt {
  //    override def validate(v: Int, test: => Boolean): Either[String, Int] = {
  //      try Right {
  //        test
  //        v
  //      } catch {
  //        case e: NumberFormatException => Left("Number should be positive. Was " + v)
  //      }
  //    }
  //  }
  //  val valInt: ValInt = new ValInt
  //
  //  val attrInt = AttrOneManInt(
  //    "Ns", "int", V, Nil, None, Some(valInt)
  //  )
  //
  //  attrInt.validation.fold(println("No validator found")) { validator =>
  //    validator.validate(-3) match {
  //      case Left(err) => println(err)
  //      case Right(v)  => v / 0
  //    }
  //  }
  //  val a1: ValidateInt             = attrInt.validation.get
  //  val a2: Either[String, Boolean] = a1.validate(-3)
}
