package molecule.base.util

import java.net.URI
import java.time._
import java.time.format.DateTimeFormatter
import java.util.{Date, UUID}
import molecule.base.util.exceptions.MoleculeException

trait BaseHelpers extends DateHandling {

  def clean(attr: String): String = attr.last match {
    case '_' => attr.init
    case '$' => attr.init
    case _   => attr
  }
  //  def orig(attr: String, mode: String): String = mode match {
  //    case "man" => attr
  //    case "opt" => attr + "$"
  //    case "tac" => attr + "_"
  //  }


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

  protected def double(arg: Any): String = "__n__" + arg + (if (arg.toString.contains(".")) "" else ".0")
  protected def bigDec(arg: Any): BigDecimal = BigDecimal(withDecimal(arg))

  def padS(longest: Int, str: String): String = pad(longest, str.length)

  def pad(longest: Int, shorter: Int): String = if (longest > shorter) " " * (longest - shorter) else ""

  // Ensure decimal digits on JS platform output
  def jsNumber(tpe: String, v: Any): Any = {
    (tpe, v) match {
      case ("Double", v: Double)                                          => if (v.isWhole) s"${v.toLong}.0" else v
      case ("BigDecimal", v: BigDecimal)                                  => if (v.isWhole) s"${v.toBigInt}.0" else v
      case ("Double" | "BigDecimal", v) if v.toString.startsWith("__n__") => v.toString.drop(5)
      case (_, v)                                                         => v
    }
  }

  final def os(opt: Option[Set[_]]): String = opt.fold("None")(vs => s"""Some(${vs.map(render).mkString(", ")})""")

  final def o(opt: Option[Any]): String = opt.fold("None")(v => s"""Some(${render(v)})""")
  final def opt(opt: Option[Any]): String = opt.fold("None")(v => s"""Some($v)""")


  final def oStr(opt: Option[String]): String = if (opt.isEmpty) "None" else s"""Some("${opt.get}")"""
  final def oStr2(opt: Option[String]): String = if (opt.isEmpty) "None" else {
    val s = escStr(opt.get)
    if (s.contains("\n"))
    //      s"Some(\n\"\"\"$s\"\"\")"
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

  final def sq[T](values: Iterable[T]): String = if (values.isEmpty) "Nil" else {
    values.map {
      case set: Set[_] => if (set.isEmpty) "Nil" else set.map(render).mkString("Set(", ", ", ")")
      case seq: Seq[_] => if (seq.isEmpty) "Nil" else seq.map(render).mkString("Seq(", ", ", ")")
      case (a, b)      => s"${render(a)} -> ${render(b)}"
      case v           => render(v)
    }.mkString("Seq(", ", ", ")")
  }


  final def sqOLD[T](values: Seq[T]): String =
    values.map {
      case set: Set[_] => set.map(render).mkString("Set(", ", ", ")")
      case seq: Seq[_] => seq.map(render).mkString("Seq(", ", ", ")")
      case (a, b)      => s"${render(a)} -> ${render(b)}"
      case v           => render(v)
    }.mkString("Seq(", ", ", ")")

  final def untupled(rawData: Iterable[Seq[Any]]): Iterable[Seq[Any]] = {
    if (this.toString.contains("compositeOutMolecule")) {
      rawData.map(_ flatMap tupleToSeq)
    } else {
      rawData
    }
  }

  final protected def tupleToSeq(arg: Any): Seq[Any] = arg match {
    case l: Seq[_]  => l
    case Some(v)    => Seq(v)
    case None       => Seq()
    case p: Product => p match {
      case t: (_, _)                                                             => Seq(t._1, t._2)
      case t: (_, _, _)                                                          => Seq(t._1, t._2, t._3)
      case t: (_, _, _, _)                                                       => Seq(t._1, t._2, t._3, t._4)
      case t: (_, _, _, _, _)                                                    => Seq(t._1, t._2, t._3, t._4, t._5)
      case t: (_, _, _, _, _, _)                                                 => Seq(t._1, t._2, t._3, t._4, t._5, t._6)
      case t: (_, _, _, _, _, _, _)                                              => Seq(t._1, t._2, t._3, t._4, t._5, t._6, t._7)
      case t: (_, _, _, _, _, _, _, _)                                           => Seq(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8)
      case t: (_, _, _, _, _, _, _, _, _)                                        => Seq(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9)
      case t: (_, _, _, _, _, _, _, _, _, _)                                     => Seq(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10)
      case t: (_, _, _, _, _, _, _, _, _, _, _)                                  => Seq(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11)
      case t: (_, _, _, _, _, _, _, _, _, _, _, _)                               => Seq(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12)
      case t: (_, _, _, _, _, _, _, _, _, _, _, _, _)                            => Seq(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13)
      case t: (_, _, _, _, _, _, _, _, _, _, _, _, _, _)                         => Seq(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14)
      case t: (_, _, _, _, _, _, _, _, _, _, _, _, _, _, _)                      => Seq(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15)
      case t: (_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _)                   => Seq(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16)
      case t: (_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _)                => Seq(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17)
      case t: (_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _)             => Seq(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18)
      case t: (_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _)          => Seq(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19)
      case t: (_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _)       => Seq(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20)
      case t: (_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _)    => Seq(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21)
      case t: (_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _) => Seq(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22)
    }
    case a          => Seq(a)
  }

  private val time0     = System.currentTimeMillis()
  private var prevTime  = time0
  private val times     = collection.mutable.Map.empty[Int, Long]
  private val formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS")

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
    case _                                             => throw MoleculeException(
      s"Invalid attribute name `$name`. " +
        "Expecting attribute name in the format `:<Ns>/<attr>` or `:<part_Ns>/<attr>`"
    )
  }
  protected def okEnumIdent(enumIdent: String): String = enumIdent match {
    case r":[a-zA-Z][a-zA-Z0-9_]+\.[a-zA-Z0-9_]+/[a-zA-Z0-9]+" => enumIdent
    case _                                                     => throw MoleculeException(
      s"Invalid enum attribute name `$enumIdent`. " +
        "Expecting enum attribute name in the format `:<Ns>.<attr>/<enum>` or `:<part_Ns>.<attr>/<enum>`"
    )
  }
  protected def okNamespaceName(name: String): String = name match {
    case r"[a-zA-Z][a-zA-Z0-9_]+" => name
    case _                        => throw MoleculeException(
      s"Invalid namespace name `$name`. Expecting namespace name in the format `[a-zA-Z][a-zA-Z0-9_]+`"
    )
  }
  protected def okPartitionName(name: String): String = name match {
    case r"[a-z][a-zA-Z0-9]+" => name
    case _                    => throw MoleculeException(
      s"Invalid partition name `$name`. Expecting partition name in the format `[a-z][a-zA-Z0-9]+`"
    )
  }
}