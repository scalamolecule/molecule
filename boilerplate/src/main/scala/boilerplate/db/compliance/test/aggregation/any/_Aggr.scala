package boilerplate.db.compliance.test.aggregation.any

import java.nio.file.{Files, Paths}
import boilerplate.Base
import boilerplate.db.compliance.DbComplianceGenBase

object _Aggr extends Base {

  //    def generate(): Unit = tpeVarImp.take(1).foreach { case (name, tpe, v, imp) =>
  def generate(): Unit = tpeVarImp.foreach { case (name, tpe, v, imp) =>
    TransformFile(name, tpe, v, imp).generate()
  }

  case class TransformFile(name: String, tpe: String, v: String, imp: String = "")
    extends DbComplianceGenBase(s"Aggr_$name", "/aggregation/any") {

    override val content = {
      val src =
        new String(Files.readAllBytes(Paths.get(path, "Aggr_Int.scala")), "UTF-8")
          .replace("package", "// GENERATED CODE ********************************\npackage")
          .replace("[Int]", s"[$tpe]")
          .replace("Int(", name + "_(")
          .replace("int", v)
          .replace(v + "ersect", "intersect")

      if (imp.isEmpty) src else src.replace("\n\nimport", s"\n\nimport $imp\nimport")
    }
  }
}
