package molecule.base.util

import java.net.URI
import java.time._
import java.time.format.DateTimeFormatter
import java.util.{Date, UUID}
import molecule.base.error.ModelError

trait BaseHelpers extends DateHandling {

  def firstLow(str: Any): String = str.toString.head.toLower.toString + str.toString.tail

  def getKwName(kw: String): String = kw.substring(kw.indexOf('/') + 1)

  def thousands(i: Long): String =
    i.toString.reverse.grouped(3).mkString(" ").reverse

  def indent(tabs: Int): String = "  " * tabs

  def escStr(s: String): String =
    s.replace("""\""", """\\""").replace(""""""", """\"""")

  def unescStr(s: String): String =
    s.replace("""\"""", """"""").replace("""\\""", """\""")

  def withDecimal(v: Any): String = {
    val s = v.toString
    if (s.contains(".")) s else s + ".0"
  }

  def ss(a: String, b: String): String = s"${a}_$b".replace("__", "_")
  def ss(a: String, b: String, c: String): String = s"${a}_${b}_$c".replace("__", "_")

  protected def double(arg: Any): String = "__n__" + arg + (if (arg.toString.contains(".")) "" else ".0")
  protected def bigDec(arg: Any): BigDecimal = BigDecimal(withDecimal(arg))

  def padS(longest: Int, str: String): String = pad(longest, str.length)

  def pad(longest: Int, shorter: Int): String = if (longest > shorter) " " * (longest - shorter) else ""

  final def o(opt: Option[Any]): String = opt.fold("None")(v => s"""Some(${render(v)})""")
  final def opt(opt: Option[Any]): String = opt.fold("None")(v => s"""Some($v)""")

  final def oStr(opt: Option[String]): String = if (opt.isEmpty) "None" else s"""Some("${opt.get}")"""
  final def oStr2(opt: Option[String]): String = if (opt.isEmpty) "None" else {
    val s = escStr(opt.get)
    if (s.contains("\n"))
      s"Some(\n" + "\"\"\"" + s + "\"\"\")"
    else
      s"""Some("$s")"""
  }

  final def render(value: Any): String = value match {
    case (a, b)    => s"(${render(a)}, ${render(b)})"
    case v: Long   => v.toString + "L"
    case v: Float  => v.toString + "f"
    case v: String => "\"" + escStr(v) + "\""
    case d: Date   => d.toString // Lets us know if it's a Date and not a String representation
    case v: UUID   => "\"" + v + "\""
    case v: URI    => "\"" + v + "\""
    case v         => v.toString
  }

  final def renderValidations(validations: Seq[(String, String)]): String = {
    if (validations.isEmpty) {
      "Nil"
    } else {
      validations.map {
        case (test, error) =>
          val errorStr = if (error.isEmpty) "\"\"" else s"""\"\"\"$error\"\"\""""
          s"""            (
             |              \"\"\"$test\"\"\",
             |              $errorStr
             |            )""".stripMargin
      }.mkString("Seq(\n", ",\n", ")")
    }
  }

  final def sq[T](values: Iterable[T]): String = if (values.isEmpty) "Nil" else {
    values.map {
      case set: Set[_] => if (set.isEmpty) "Nil" else set.map(render).mkString("Set(", ", ", ")")
      case seq: Seq[_] => if (seq.isEmpty) "Nil" else seq.map(render).mkString("Seq(", ", ", ")")
      case (a, b)      => s"${render(a)} -> ${render(b)}"
      case v           => render(v)
    }.mkString("Seq(", ", ", ")")
  }

  private var time0     = System.currentTimeMillis()
  private var prevTime  = time0
  private val times     = collection.mutable.Map.empty[Int, Long]
  private val formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS")

  protected final def resetTimer(): Unit = {
    time0     = System.currentTimeMillis()
    prevTime  = time0
    times.clear()
  }
  protected final def time(n: Int, prev: Int = 0): Unit = {
    if (n < 1 || prev < 0)
      throw new IllegalArgumentException(s"Identifiers have to be positive numbers")

    if (times.nonEmpty && n <= times.keys.max)
      throw new IllegalArgumentException(
        s"Identifier have to be incremental. `$n` is smaller than or equal to previous `${times.keys.max}`")

    if (times.keys.toSeq.contains(n))
      throw new IllegalArgumentException(s"Can't use same time identifier `$n` multiple times")

    val time1   = if (prev > 0) times(prev) else prevTime
    val time2   = System.currentTimeMillis()
    val elapsed = time2 - time1
    times += n -> time2
    prevTime = time2
    val d = LocalDateTime.ofInstant(Instant.ofEpochMilli(elapsed), ZoneOffset.UTC)
    println(s"TIME $n: " + formatter.format(d))
  }

  protected def okIdent(name: String): String = name match {
    case r":-?[a-zA-Z][a-zA-Z0-9_]+/[a-z][a-zA-Z0-9]+" => name
    case _                                             => throw ModelError(
      s"Invalid attribute name `$name`. " +
        "Expecting attribute name in the format `:<Ns>/<attr>` or `:<part_Ns>/<attr>`"
    )
  }
  protected def okEnumIdent(enumIdent: String): String = enumIdent match {
    case r":[a-zA-Z][a-zA-Z0-9_]+\.[a-zA-Z0-9_]+/[a-zA-Z0-9]+" => enumIdent
    case _                                                     => throw ModelError(
      s"Invalid enum attribute name `$enumIdent`. " +
        "Expecting enum attribute name in the format `:<Ns>.<attr>/<enum>` or `:<part_Ns>.<attr>/<enum>`"
    )
  }
  protected def okNamespaceName(name: String): String = name match {
    case r"[a-zA-Z][a-zA-Z0-9_]+" => name
    case _                        => throw ModelError(
      s"Invalid namespace name `$name`. Expecting namespace name in the format `[a-zA-Z][a-zA-Z0-9_]+`"
    )
  }
  protected def okPartitionName(name: String): String = name match {
    case r"[a-z][a-zA-Z0-9]+" => name
    case _                    => throw ModelError(
      s"Invalid partition name `$name`. Expecting partition name in the format `[a-z][a-zA-Z0-9]+`"
    )
  }
}