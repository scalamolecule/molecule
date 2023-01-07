package codegen.boilerplate.ast

import java.io.File
import codegen.BoilerplateGenBase
import molecule.base.util.exceptions.MoleculeException
import scala.io.Source

object _Model extends BoilerplateGenBase("Model", "/ast") {

  private val customCode = {
    val it        = Source.fromFile(new File(path + "/Model.scala"))
    val lines     = it.getLines().toList
    val delimiter = "// GENERATED (edit in _Model generator)"
    if (!lines.exists(_.contains(delimiter)))
      throw MoleculeException(
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

    def body(baseType: String): String = {
      val tpe   = cardTpe(baseType)
      val isRef = if (baseType == "Long" && Seq("One", "Set").contains(card))
        ",\n    isRef: Boolean = false" else ""
      val vs    = if (mode == "Opt") s"Option[Seq[$tpe]] = None" else s"Seq[$tpe] = Nil"
      s"""
         |  case class Attr$card$mode$baseType(
         |    ns: String,
         |    attr: String,
         |    op: Op = V,
         |    vs: $vs,
         |    defaultValue: Option[$tpe] = None,
         |    validation: Option[Validate$baseType] = None,
         |    sort: Option[String] = None$isRef
         |  ) extends Attr$card$mode""".stripMargin
    }

    val attrClasses = baseTypes.map(body).mkString("\n")
    s"""
       |  sealed trait Attr$card$mode extends Attr$card with $modeFull
       |  $attrClasses
       |""".stripMargin
  }

  override def generate: Unit = {
    mkFile(fileName, content)
    logger.info(s"Generated $path/$fileName.scala")
  }
}
