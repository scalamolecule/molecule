package boilerplate

import java.io.{BufferedWriter, File, FileWriter}

abstract class Util(val fileName: String, dir: String, basePath: String) extends Base {
  val path: String = basePath + dir
  val fileName_    = if (fileName == "package") "package" else fileName + "_"
  def caseN(n: Int) = if (n < 10) s"$n " else n

  class TemplateVals(arity: Int) {
    lazy val s          = " "
    lazy val V          = ('A' + arity - 1).toChar
    lazy val X          = ('A' + arity - 1).toChar
    lazy val tpes       = (0 until arity) map (n => (n + 'A').toChar)
    lazy val tpesSmall  = (0 until arity) map (n => (n + 'a').toChar)
    lazy val `A..V`     = tpes.mkString(", ")
    lazy val `(A..V)`   = if (arity == 1) "A" else tpes.mkString("(", ", ", ")")
    lazy val `a..v`     = tpesSmall.mkString(", ")
    lazy val `a:A..v:V` = tpesSmall.zip(tpes).map { case (a, aa) => s"$a: $aa" }.mkString(", ")
    lazy val `A..V, `   = if (tpes.isEmpty) "" else tpes.mkString("", ", ", ", ")
    lazy val `A..U, `   = if (tpes.size <= 1) "" else tpes.init.mkString("", ", ", ", ")
    lazy val `[A..V]`   = if (arity == 0) "" else tpes.mkString("[", ", ", "]")
    lazy val `A..t`     = if (arity == 1) "t" else tpes.init.mkString("", ", ", ", t")
    lazy val `A..t, `   = arity match {
      case 0 => ""
      case 1 => "t, "
      case _ => tpes.init.mkString("", ", ", ", t, ")
    }

    lazy val `_, _`    = Seq.fill(arity + 1)("_").mkString(", ")
    lazy val `_, _, _` = Seq.fill(arity + 2)("_").mkString(", ")
    lazy val a0        = "_" + arity
    lazy val a1        = "_" + (arity + 1)

    def padN(n: Int) = if (n < 10) s"0$n" else n
    val n0 = padN(arity)
    val n1 = padN(arity + 1)

    val tpl = arity match {
      case 0 => "Nothing"
      case 1 => "A"
      case _ => s"(${`A..V`})"
    }
  }

  // Implement in sub classes
  def content: String

  def generate(): Unit = {
    mkFile(fileName_, content)
    println(s"Generated $path/$fileName_.scala")
  }

  protected def mkFile(fileName: String, body: String): Unit = {
    val filePath = s"$path/$fileName.scala"
    val bw       = new BufferedWriter(new FileWriter(new File(filePath)))
    bw.write(body)
    bw.close()
  }
}
