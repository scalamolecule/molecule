package boilerplate.db.compliance.test.filter.set.types

import java.nio.file.{Files, Paths}
import boilerplate.Base
import boilerplate.db.compliance.DbComplianceGenBase

object _FilterSet extends Base {

  def generate(): Unit = tpeVarImp.filterNot(_._1 == "ref").foreach { case (name, tpe, v, imp) =>
    TransformFile(name, tpe, v, imp).generate()
  }

  case class TransformFile(name: String, tpe: String, v: String, imp: String = "")
    extends DbComplianceGenBase(s"FilterSet_$name", "/filter/set/types") {

    override val content = {
      val src =
        new String(Files.readAllBytes(Paths.get(path, "FilterSet_Int.scala")), "UTF-8")
          .replace("package", "// GENERATED CODE ********************************\npackage")
          .replace("[Int]", s"[$tpe]")
          .replace("FilterSet_Int", "FilterSet_" + name + "_")
          .replace("int", v)

      if (imp.isEmpty) src else src.replace("\n\nimport", s"\n\nimport $imp\nimport")
    }
  }
}
