package molecule.db.base.util

import java.net.URI
import java.time._
import java.time.format.DateTimeFormatter
import java.util.{Date, UUID}

object BaseHelpers extends BaseHelpers
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
  final def optFilterAttr(opt: Option[(Int, List[String], Any)]): String = opt.fold("None") {
    case (dir, filterPath, filterAttr) =>
      val path = filterPath.mkString("List(\"", "\", \"", "\")")
      s"""Some($dir, $path, \n    $filterAttr\n  )"""
  }

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

  private var time0          = System.currentTimeMillis()
  private var prevTime       = time0
  private lazy val times     = collection.mutable.Map.empty[Int, Long]
  private lazy val formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS")

  protected final def resetTimer(): Unit = {
    time0 = System.currentTimeMillis()
    prevTime = time0
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

  def diff(s1: String, s2: String): Unit = {
    val lines1 = s1.split('\n')
    val lines2 = s2.split('\n')
    val max1   = lines1.map(_.length).max + 3
    val lines  = if (lines1.length >= lines2.length) {
      val it2 = lines2.iterator
      lines1.map { l1 =>
        l1 + padS(max1, l1) + "  |  " + (if (it2.hasNext) it2.next() else "")
      }
    } else {
      val it1 = lines1.iterator
      lines2.map { l2 =>
        val s1 = if (it1.hasNext) it1.next() else ""
        s1 + padS(max1, s1) + "  |  " + l2
      }
    }
    println(lines.mkString("\n"))
  }
}