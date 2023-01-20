package codegen.boilerplate.ast

import java.io.File
import codegen.BoilerplateGenBase
import molecule.base.util.exceptions.MoleculeError
import scala.io.Source

object _Model extends BoilerplateGenBase("Model", "/ast") {

  private val customCode = {
    val it        = Source.fromFile(new File(path + "/Model.scala"))
    val lines     = it.getLines().toList
    val delimiter = "// GENERATED (edit in _Model generator)"
    if (!lines.exists(_.contains(delimiter)))
      throw MoleculeError(
        s"Couldn't find delimiting text '$delimiter' in file Model. " +
          "Please insert it before code to be generated."
      )
    lines.takeWhile(!_.contains(delimiter)).mkString("\n")
  }

  private val cardMode = Seq(
    ("One", "Man", "Mandatory"),
    ("One", "Opt", "Optional"),
    ("One", "Tac", "Tacit"),
    ("Set", "Man", "Mandatory"),
    ("Set", "Opt", "Optional"),
    ("Set", "Tac", "Tacit"),
  )

  override val content = {
    val attrClasses = cardMode.map {
      case (card, mode, modeFull) => makeAttrGroup(card, mode, modeFull)
    }.mkString("\n")
    customCode +
      s"""
         |  // GENERATED (edit in _Model generator) ======================================
         |  $attrClasses}""".stripMargin
  }

  private def makeAttrGroup(card: String, mode: String, modeFull: String): String = {
    val cardTpe = card match {
      case "One"   => (baseType: String) => baseType
      case "Set"   => (baseType: String) => s"Set[$baseType]"
      case "Array" => (baseType: String) => s"Array[$baseType]"
      case "Map"   => (baseType: String) => s"Map[String, $baseType]"
    }

    // Render attribute toString method so that a printout can be directly used as valid Scala code

    def body(baseType: String): String = {
      val tpe               = cardTpe(baseType)
      val attrType          = s"Attr$card$mode$baseType"
      val (isRef, isRefStr) = if (baseType == "Long" && Seq("One", "Set").contains(card))
        (",\n    isRef: Boolean = false", ", $isRef") else ("", "")
      val vs                = if (mode == "Opt") s"Option[Seq[$tpe]] = None" else s"Seq[$tpe] = Nil"
      val format_?          = !List("Int", "Double", "Boolean").contains(baseType)
      val format            = baseType match {
        case "String"     => """"\"" + escStr(v) + "\"""""
        case "Int"        => "v"
        case "Long"       => """v.toString + "L""""
        case "Float"      => """v.toString + "f""""
        case "Double"     => "v"
        case "Boolean"    => "v"
        case "BigInt"     => """"BigInt(" + v + ")""""
        case "BigDecimal" => """"BigDecimal(" + v + ")""""
        case "Date"       => """"new Date(" + v.getTime + ")""""
        case "UUID"       => """"UUID.fromString(\"" + v.toString + "\")""""
        case "URI"        => """"new URI(\"" + v.toString + "\")""""
        case "Byte"       => """s"$v.toByte""""
        case "Short"      => """s"$v.toShort""""
        case "Char"       => """s"'$v'""""
      }
      val attrStr           = card match {
        case "One" => mode match {
          case "Opt" =>
            if (format_?)
              s"""def format(v: $baseType): String = $format
                 |      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
                 |      def defV: String = defaultValue.fold("None")(v => s"Some($${format(v)})")
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $$defV, $${o(validation)}, $${oStr(sort)}$isRefStr)\"\"\"""".stripMargin
            else
              s"""def vss: String = vs.fold("None")(_.mkString("Some(Seq(", ", ", "))"))
                 |      def defV: String = defaultValue.fold("None")(v => s"Some($$v)")
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $$defV, $${opt(validation)}, $${oStr(sort)}$isRefStr)\"\"\"""".stripMargin
          case _     =>
            if (format_?)
              s"""def format(v: $baseType): String = $format
                 |      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
                 |      def defV: String = defaultValue.fold("None")(v => s"Some($${format(v)})")
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $$defV, $${o(validation)}, $${oStr(sort)}$isRefStr)\"\"\"""".stripMargin
            else
              s"""def vss: String = vs.mkString("Seq(", ", ", ")")
                 |      def defV: String = defaultValue.fold("None")(v => s"Some($$v)")
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $$defV, $${opt(validation)}, $${oStr(sort)}$isRefStr)\"\"\"""".stripMargin
        }
        case "Set" => mode match {
          case "Opt" =>
            if (format_?)
              s"""def format(v: $baseType): String = $format
                 |      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
                 |      def defV: String = defaultValue.fold("None")(set => s"Some($${set.map(format).mkString("Set(", ", ", ")")})")
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $$defV, $${o(validation)}, $${oStr(sort)}$isRefStr)\"\"\"""".stripMargin
            else
              s"""def vss: String = vs.fold("None")(_.map(_.mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
                 |      def defV: String = defaultValue.fold("None")(set => s"Some($${set.mkString("Set(", ", ", ")")})")
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $$defV, $${opt(validation)}, $${oStr(sort)}$isRefStr)\"\"\"""".stripMargin
          case _     =>
            if (format_?)
              s"""def format(v: $baseType): String = $format
                 |      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
                 |      def defV: String = defaultValue.fold("None")(set => s"Some($${set.map(format).mkString("Set(", ", ", ")")})")
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $$defV, $${o(validation)}, $${oStr(sort)}$isRefStr)\"\"\"""".stripMargin
            else
              s"""def vss: String = vs.map(set => set.mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
                 |      def defV: String = defaultValue.fold("None")(set => s"Some($${set.mkString("Set(", ", ", ")")})")
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $$defV, $${opt(validation)}, $${oStr(sort)}$isRefStr)\"\"\"""".stripMargin
        }
      }

      s"""
         |  case class Attr$card$mode$baseType(
         |    override val ns: String,
         |    override val attr: String,
         |    override val op: Op = V,
         |    vs: $vs,
         |    defaultValue: Option[$tpe] = None,
         |    validation: Option[Validate$baseType] = None,
         |    override val sort: Option[String] = None$isRef
         |  ) extends Attr$card$mode {
         |    override def toString: String = {
         |      $attrStr
         |    }
         |  }""".stripMargin
    }

    val attrClasses = baseTypes.map(body).mkString("\n")
    s"""
       |  sealed trait Attr$card$mode extends Attr$card with $modeFull
       |  $attrClasses
       |""".stripMargin
  }

  override def generate(): Unit = {
    mkFile(fileName, content)
    logger.info(s"Generated $path/$fileName.scala")
  }
}
