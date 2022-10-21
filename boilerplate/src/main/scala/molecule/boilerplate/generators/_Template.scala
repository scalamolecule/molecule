package molecule.boilerplate.generators

import java.io.{BufferedWriter, File, FileWriter}


abstract class _Template(val name: String, dirName: String = "") {
  val dir      = if (dirName.nonEmpty) "/" + dirName else ""
  val basePath = s"boilerplate/src/main/scala/molecule/boilerplate/api$dir"
  val Name     = name.capitalize

  class TemplateVals(arity: Int) {
    lazy val T        = ('A' + arity - 1).toChar
//    lazy val TT       = T // just for aligning code in code generators
//    lazy val s        = " "
    lazy val tpes     = (0 until arity) map (n => (n + 'A').toChar)
    lazy val `A..N`   = tpes.mkString(", ")
    lazy val `A..N, ` = if (tpes.isEmpty) "" else tpes.mkString("", ", ", ", ")
    lazy val `, A..N` = if (tpes.isEmpty) "" else tpes.mkString(", ", ", ", "")
    lazy val `A..M, ` = if (tpes.size <= 1) "" else tpes.init.mkString("", ", ", ", ")

    lazy val `[A..V]` = if (arity == 0) "" else tpes.mkString("[", ", ", "]")
    val `A..V,` = arity match {
      case 0 => ""
      case 1 => "A, "
      case _ => s"(${`A..N`}), "
    }
    val `[A0]`  = arity match {
      case 0 => " " * 68
      case 1 => s"[A" + (" " * 68) + "]"
      case _ => s"[(${`A..N`})" + (" " * 68) + "]"
    }
    lazy val `_, _`   = Seq.fill(arity + 1)("_").mkString(", ")
    lazy val `, _, _` = Seq.fill(arity + 1)("_").mkString(", ", ", ", "")
//    lazy val `_, _`   = if (arity == 0) "" else Seq.fill(arity)("_").mkString(", ")
//    lazy val `, _, _` = if (arity == 0) "" else Seq.fill(arity)("_").mkString(", ", ", ", "")
    lazy val ns_1     = s"Ns_" + (arity + 1)
    lazy val `_1`     = Seq.fill(arity + 1)("_").mkString(", ")
    //    lazy val nsIn     = s"Ns[_, _${`, _, _`}]"
    //    lazy val nsOut    = s"Ns[Attr, t${`, A..N`}]"
    lazy val nsIn     = s"Ns[${`_, _`}]"
    lazy val nsOut    = s"Ns[${`A..N`}]"

    def padN(n: Int) = if (n < 10) s"0$n" else n
  }

  def content: String

  def generate: Unit = {
    //    mkDir(dirName)
    //    (minArity to maxArity).foreach(arity => mkFile(dirName, name, arity, content))


    mkFile(name, content)
    println(s"Generated $basePath/$name.scala")
  }

  //  val targetDirPath = if (parentDirName.nonEmpty) basePath + s"/$parentDirName" else basePath

  //  def mkDir(dirName: String): Unit = {
  //    if (parentDirName.nonEmpty) {
  //      val parentDir = new File(targetDirPath)
  //      if (!parentDir.exists())
  //        parentDir.mkdir()
  //    }
  //
  //    val path = targetDirPath + "/" + dirName
  //    val dir  = new File(path)
  //    if (!dir.exists()) {
  //      dir.mkdir()
  //    }
  //  }

  def mkFile(name: String, body: String): Unit = {
    val filePath = s"$basePath/$name.scala"

    val bw = new BufferedWriter(new FileWriter(new File(filePath)))
    bw.write(body)
    bw.close()
  }
}
