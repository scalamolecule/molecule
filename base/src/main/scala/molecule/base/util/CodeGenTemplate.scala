package molecule.base.util

import java.io.{BufferedWriter, File, FileWriter}


abstract class CodeGenTemplate(val fileName: String, dir: String, basePath: String) extends CodeGenBase {
  val path: String = basePath + dir

  class TemplateVals(arity: Int) {
    lazy val T          = ('A' + arity - 1).toChar
    lazy val TT         = T // just for aligning code in code generators
    lazy val tpes       = (0 until arity) map (n => (n + 'A').toChar)
    lazy val tpesSmall  = (0 until arity) map (n => (n + 'a').toChar)
    lazy val `A..V`     = tpes.mkString(", ")
    lazy val `(A..V)`   = if (arity == 1) "A" else tpes.mkString("(", ", ", ")")
    lazy val `a..v`     = tpesSmall.mkString(", ")
    lazy val `a:A..v:V` = tpesSmall.zip(tpes).map { case (a, aa) => s"$a: $aa" }.mkString(", ")
    lazy val `A..V, `   = if (tpes.isEmpty) "" else tpes.mkString("", ", ", ", ")
    lazy val `, A..V`   = if (tpes.isEmpty) "" else tpes.mkString(", ", ", ", "")
    lazy val `A..U, `   = if (tpes.size <= 1) "" else tpes.init.mkString("", ", ", ", ")

    lazy val `[A..V]` = if (arity == 0) "" else tpes.mkString("[", ", ", "]")
    val `A..V,` = arity match {
      case 0 => ""
      case 1 => "A, "
      case _ => s"${`(A..V)`}, "
    }
    val `[A0]`  = arity match {
      case 0 => " " * 68
      case 1 => s"[A" + (" " * 68) + "]"
      case _ => s"[${`(A..V)`}" + (" " * 68) + "]"
    }
    lazy val `_, _`   = Seq.fill(arity + 1)("_").mkString(", ")
    lazy val `, _, _` = Seq.fill(arity + 1)("_").mkString(", ", ", ", "")
    lazy val ns_1     = s"Ns_" + (arity + 1)
    lazy val `_1`     = Seq.fill(arity + 1)("_").mkString(", ")
    lazy val nsIn     = s"Ns[${`_, _`}]"
    lazy val nsOut    = s"Ns[${`A..V`}]"

    def padN(n: Int) = if (n < 10) s"0$n" else n
    val n0 = padN(arity)
    val n1 = padN(arity + 1)

    lazy val `..`  = " " * (if (arity == 1) 3 else 3 * (arity - 1) + 3)
    lazy val `..N` = if (arity >= 10) `..` + " " else `..`
  }

  // Implement in sub classes
  def content: String

  def generate: Unit = {
    mkFile(fileName, content)
    println(s"Generated $path/$fileName.scala")
  }

  private def mkFile(fileName: String, body: String): Unit = {
    val filePath = s"$path/$fileName.scala"
    val bw       = new BufferedWriter(new FileWriter(new File(filePath)))
    bw.write(body)
    bw.close()
  }
}
