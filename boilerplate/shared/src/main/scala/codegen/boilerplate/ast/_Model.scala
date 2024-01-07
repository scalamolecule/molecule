package codegen.boilerplate.ast

import java.io.File
import codegen.BoilerplateGenBase
import molecule.base.error._
import scala.io.Source

object _Model extends BoilerplateGenBase("Model", "/ast") {

  private val customCode = {
    val it        = Source.fromFile(new File(path + "/Model.scala"))
    val lines     = it.getLines().toList
    val delimiter = "// GENERATED from here and below (edit in _Model generator)"
    if (!lines.exists(_.contains(delimiter)))
      throw ExecutionError(
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
         |  // GENERATED from here and below (edit in _Model generator) ======================================
         |  $attrClasses}""".stripMargin
  }

  private def makeAttrGroup(card: String, mode: String, modeFull: String): String = {
    val cardTpe = card match {
      case "One"   => (baseTpe: String) => baseTpe
      case "Set"   => (baseTpe: String) => s"Set[$baseTpe]"
      case "Array" => (baseTpe: String) => s"Array[$baseTpe]"
      case "Map"   => (baseTpe: String) => s"Map[String, $baseTpe]"
    }

    // Render attribute toString method so that a printout can be directly used as valid Scala code
    def body(baseTpe0: String): String = {
      val baseTpe  = if (baseTpe0 == "ID") "String" else baseTpe0
      val tpe      = cardTpe(baseTpe)
      val attrType = s"Attr$card$mode$baseTpe0"
      val vs       = if (mode == "Opt") s"Option[Seq[$tpe]] = None" else s"Seq[$tpe] = Nil"
      val format_? = !List("Int", "Double", "Boolean").contains(baseTpe)
      val format   = baseTpe match {
        case "Id"             => """"\"" + escStr(v) + "\"""""
        case "String"         => """"\"" + escStr(v) + "\"""""
        case "Int"            => "v"
        case "Long"           => """v.toString + "L""""
        case "Float"          => """v.toString + "f""""
        case "Double"         => "v"
        case "Boolean"        => "v"
        case "BigInt"         => """"BigInt(" + v + ")""""
        case "BigDecimal"     => """"BigDecimal(" + v + ")""""
        case "Date"           => """"new Date(" + v.getTime + ")""""
        case "Duration"       => """"Duration.ofSeconds(" + v.getSeconds + ", " + v.getNano + ")""""
        case "Instant"        => """"Instant.ofEpochSecond(" + v.getEpochSecond + ", " + v.getNano + ")""""
        case "LocalDate"      => """"LocalDate.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ")""""
        case "LocalTime"      => """"LocalTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")""""
        case "LocalDateTime"  => """"LocalDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ")""""
        case "OffsetTime"     => """"OffsetTime.of(" + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")""""
        case "OffsetDateTime" => """"OffsetDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getOffset + ")""""
        case "ZonedDateTime"  => """"ZonedDateTime.of(" + v.getYear + ", " + v.getMonth + ", " + v.getDayOfMonth + ", " + v.getHour + ", " + v.getMinute + ", " + v.getSecond + ", " + v.getNano + ", " + v.getZone + ")""""
        case "UUID"           => """"UUID.fromString(\"" + v.toString + "\")""""
        case "URI"            => """"new URI(\"" + v.toString + "\")""""
        case "Byte"           => """s"$v.toByte""""
        case "Short"          => """s"$v.toShort""""
        case "Char"           => """s"'$v'""""
      }
      val attrStr  = card match {
        case "One" => mode match {
          case "Opt" =>
            if (format_?)
              s"""def format(v: $baseTpe): String = $format
                 |      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $${opt(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin
            else
              s"""def vss: String = vs.fold("None")(_.mkString("Some(Seq(", ", ", "))"))
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $${opt(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin
          case _     =>
            if (format_?)
              s"""def format(v: $baseTpe): String = $format
                 |      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $${opt(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin
            else
              s"""def vss: String = vs.mkString("Seq(", ", ", ")")
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $${opt(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin
        }
        case "Set" => mode match {
          case "Opt" =>
            if (format_?)
              s"""def format(v: $baseTpe): String = $format
                 |      def vss: String = vs.fold("None")(_.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $${opt(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin
            else
              s"""def vss: String = vs.fold("None")(_.map(_.mkString("Set(", ", ", ")")).mkString("Some(Seq(", ", ", "))"))
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $${opt(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin
          case _     =>
            if (format_?)
              s"""def format(v: $baseTpe): String = $format
                 |      def vss: String = vs.map(set => set.map(format).mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $${opt(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin
            else
              s"""def vss: String = vs.map(set => set.mkString("Set(", ", ", ")")).mkString("Seq(", ", ", ")")
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $${opt(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin
        }
      }

      val owner = if (baseTpe0 == "ID") s",\n    override val owner: Boolean = false" else ""

      s"""
         |  case class Attr$card$mode$baseTpe0(
         |    override val ns: String,
         |    override val attr: String,
         |    override val op: Op = V,
         |    vs: $vs,
         |    override val filterAttr: Option[Attr] = None,
         |    override val validator: Option[Validate$baseTpe0] = None,
         |    override val valueAttrs: Seq[String] = Nil,
         |    override val errors: Seq[String] = Nil,
         |    override val refNs: Option[String] = None,
         |    override val sort: Option[String] = None,
         |    override val coord: Seq[Int] = Nil$owner
         |  ) extends Attr$card$mode {
         |    override def toString: String = {
         |      $attrStr
         |    }
         |  }""".stripMargin
    }

    //    val attrClasses = ("ID" +: baseTypes).map(body).mkString("\n")
    val attrClasses = (baseTypes).map(body).mkString("\n")
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
