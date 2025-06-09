package boilerplate.db.compliance.test.filter.seq.types

import java.nio.file.{Files, Paths}
import boilerplate.Base
import boilerplate.db.compliance.ComplianceGenBase

object _FilterSeq extends Base {

  def generate(): Unit = tpeVarImp.filterNot(
    coord => coord._3 == "ref" || coord._3 == "byte"
  ).foreach { case (name, tpe, v, imp) =>
    TransformFile(name, tpe, v, imp).generate()
  }

  case class TransformFile(name: String, tpe: String, v: String, imp: String = "")
    extends ComplianceGenBase(s"FilterSeq_$name", "/filter/seq/types") {

    override val content = {
      val src =
        new String(Files.readAllBytes(Paths.get(path, "FilterSeq_Int.scala")), "UTF-8")
          .replace("package", "// GENERATED CODE ********************************\npackage")
          .replace("[Int]", s"[$tpe]")
          .replace("FilterSeq_Int", "FilterSeq_" + name + "_")
          .replace("int", v)

      if (imp.isEmpty) src else src.replace("\n\nimport", s"\n\nimport $imp\nimport")
    }
  }
}
