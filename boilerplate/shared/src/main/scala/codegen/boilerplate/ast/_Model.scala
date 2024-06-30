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
    ("Seq", "Man", "Mandatory"),
    ("Seq", "Opt", "Optional"),
    ("Seq", "Tac", "Tacit"),
    ("Map", "Man", "Mandatory"),
    ("Map", "Opt", "Optional"),
    ("Map", "Tac", "Tacit"),
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
    // Render attribute toString method so that a printout can be directly used as valid Scala code
    def body(baseTpe0: String): String = {
      val baseTpe   = if (baseTpe0 == "ID") "Long" else baseTpe0
      val attrType  = s"Attr$card$mode$baseTpe0"
      val vs        = (card, mode, baseTpe) match {
        case ("One", "Opt", _)      => s"vs: Option[Seq[$baseTpe]] = None"
        case ("One", _, _)          => s"vs: Seq[$baseTpe] = Nil"
        case ("Set", "Opt", _)      => s"vs: Option[Set[$baseTpe]] = None"
        case ("Set", _, _)          => s"vs: Set[$baseTpe] = Set.empty[$baseTpe]"
        case ("Seq", "Opt", "Byte") => s"vs: Option[Array[Byte]] = None"
        case ("Seq", _, "Byte")     => s"vs: Array[Byte] = Array.empty[Byte]"
        case ("Seq", "Opt", _)      => s"vs: Option[Seq[$baseTpe]] = None"
        case ("Seq", _, _)          => s"vs: Seq[$baseTpe] = Nil"

        case ("Map", "Man", _)      =>
          s"""map: Map[String, $baseTpe] = Map.empty[String, $baseTpe],
             |    override val keys: Seq[String] = Nil""".stripMargin

        case ("Map", "Opt", _)          =>
          s"""map: Option[Map[String, $baseTpe]] = None,
             |    override val keys: Seq[String] = Nil""".stripMargin

        case ("Map", "Tac", _)      =>
          s"""map: Map[String, $baseTpe] = Map.empty[String, $baseTpe],
             |    override val keys: Seq[String] = Nil,
             |    values: Seq[$baseTpe] = Nil""".stripMargin
      }
      val format_?  = !List("Int", "Double", "Boolean").contains(baseTpe)
      val format    = baseTpe match {
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
      val nullCheck = baseTpe match {
        case "Long" | "Float" | "Byte" | "Short" | "Char" => """v.toString == "0""""
        case _                                            => "v == null"
      }

      val attrStr = card match {
        case "One"                      => mode match {
          case "Opt" =>
            if (format_?)
              s"""def format(v: $baseTpe): String = $format
                 |      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $${optFilterAttr(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin
            else
              s"""def vss: String = vs.fold("None")(_.mkString("Some(Seq(", ", ", "))"))
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $${optFilterAttr(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin
          case _     =>
            if (format_?)
              s"""def format(v: $baseTpe): String = $format
                 |      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $${optFilterAttr(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin
            else
              s"""def vss: String = vs.mkString("Seq(", ", ", ")")
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $${optFilterAttr(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin
        }
        case "Set"                      => mode match {
          case "Opt" =>
            if (format_?)
              s"""def format(v: $baseTpe): String = $format
                 |      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Set(", ", ", "))"))
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $${optFilterAttr(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin
            else
              s"""def vss: String = vs.fold("None")(_.mkString("Some(Set(", ", ", "))"))
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $${optFilterAttr(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin
          case _     =>
            if (format_?)
              s"""def format(v: $baseTpe): String = $format
                 |      def vss: String = vs.map(format).mkString("Set(", ", ", ")")
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $${optFilterAttr(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin
            else
              s"""def vss: String = vs.mkString("Set(", ", ", ")")
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $${optFilterAttr(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin
        }
        case "Seq" if baseTpe == "Byte" => mode match {
          case "Opt" =>
            s"""def format(v: $baseTpe): String = $format
               |      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Array(", ", ", "))"))
               |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $${optFilterAttr(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin
          case _     =>
            s"""def format(v: $baseTpe): String = $format
               |      def vss: String = vs.map(format).mkString("Array(", ", ", ")")
               |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $${optFilterAttr(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin
        }
        case "Seq"                      => mode match {
          case "Opt" =>
            if (format_?)
              s"""def format(v: $baseTpe): String = $format
                 |      def vss: String = vs.fold("None")(_.map(format).mkString("Some(Seq(", ", ", "))"))
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $${optFilterAttr(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin
            else
              s"""def vss: String = vs.fold("None")(_.mkString("Some(Seq(", ", ", "))"))
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $${optFilterAttr(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin
          case _     =>
            if (format_?)
              s"""def format(v: $baseTpe): String = $format
                 |      def vss: String = vs.map(format).mkString("Seq(", ", ", ")")
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $${optFilterAttr(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin
            else
              s"""def vss: String = vs.mkString("Seq(", ", ", ")")
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$vss, $${optFilterAttr(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin
        }
        case "Map"                      => mode match {
          case "Man"     =>
            if (format_?)
              s"""def format(v: $baseTpe): String = if ($nullCheck) "null" else $format
                 |      def pairs: String = map.map { case (k, v) => s\"\"\"("$$k", $${format(v)})\"\"\" }.mkString("Map(", ", ", ")")
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$pairs, $$ks, $${optFilterAttr(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin
            else
              s"""def pairs: String = map.map { case (k, v) => s\"\"\"("$$k", $$v)\"\"\" }.mkString("Map(", ", ", ")")
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$pairs, $$ks, $${optFilterAttr(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin

          case "Opt" =>
            if (format_?)
              s"""def format(v: $baseTpe): String = if ($nullCheck) "null" else $format
                 |      def pairs: String = map.fold("None")(_.map { case (k, v) => s\"\"\"("$$k", $${format(v)})\"\"\" }.mkString("Some(Map(", ", ", "))"))
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$pairs, $$ks, $${optFilterAttr(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin
            else
              s"""def pairs: String = map.fold("None")(_.map { case (k, v) => s\"\"\"("$$k", $$v)\"\"\" }.mkString("Some(Map(", ", ", "))"))
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$pairs, $$ks, $${optFilterAttr(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin

          case "Tac" =>
            if (format_?)
              s"""def format(v: $baseTpe): String = if ($nullCheck) "null" else $format
                 |      def pairs: String = map.map { case (k, v) => s\"\"\"("$$k", $${format(v)})\"\"\" }.mkString("Map(", ", ", ")")
                 |      def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$pairs, $$ks, $$vs, $${optFilterAttr(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin
            else
              s"""def pairs: String = map.map { case (k, v) => s\"\"\"("$$k", $$v)\"\"\" }.mkString("Map(", ", ", ")")
                 |      def vs: String = if (values.isEmpty) "Nil" else values.mkString("Seq(", ", ", ")")
                 |      s\"\"\"$attrType("$$ns", "$$attr", $$op, $$pairs, $$ks, $$vs, $${optFilterAttr(filterAttr)}, $${opt(validator)}, $$errs, $$vats, $${oStr(refNs)}, $${oStr(sort)}, $$coords)\"\"\"""".stripMargin
        }
      }

      s"""
         |  case class Attr$card$mode$baseTpe0(
         |    override val ns: String,
         |    override val attr: String,
         |    override val op: Op = V,
         |    $vs,
         |    override val filterAttr: Option[(Int, List[String], Attr)] = None,
         |    override val validator: Option[Validate$baseTpe0] = None,
         |    override val valueAttrs: Seq[String] = Nil,
         |    override val errors: Seq[String] = Nil,
         |    override val refNs: Option[String] = None,
         |    override val sort: Option[String] = None,
         |    override val coord: Seq[Int] = Nil
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
