package boilerplate.db.compliance.test.filter.one.types

import java.nio.file.{Files, Paths}
import boilerplate.Base
import boilerplate.db.compliance.DbComplianceGenBase

object _FilterOne extends Base {

  def generate(): Unit = tpeVarImp
    .filterNot(_._1 == "ref")
    .foreach { case (name, tpe, v, imp) =>
      TransformFile(name, tpe, v, imp).generate()
    }

  case class TransformFile(name: String, tpe: String, v: String, imp: String = "")
    extends DbComplianceGenBase(s"FilterOne_$name", "/filter/one/types") {

    override val content = {
      val src =
        new String(Files.readAllBytes(Paths.get(path, "FilterOne_Int.scala")), "UTF-8")
          .replace("package", "// GENERATED CODE ********************************\npackage")
          .replace("[Int]", s"[$tpe]")
          .replace("Int(", name + "_(")
          .replace("int", v)

      if (imp.isEmpty) src else src.replace("\n\nimport", s"\n\nimport $imp\nimport")
    }
  }
}
