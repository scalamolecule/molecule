package codegen.compliance.test.filter.one.types

import java.nio.file.{Files, Paths}
import codegen.base.CodeGenBase
import codegen.compliance.SpiTestGenBase

object _FilterOne extends CodeGenBase {

  def generate(): Unit = tpeVarImp.foreach { case (name, tpe, v, imp) =>
    TransformFile(name, tpe, v, imp).generate()
  }

  case class TransformFile(name: String, tpe: String, v: String, imp: String = "")
    extends SpiTestGenBase(s"FilterOne_$name", "/filter/one/types") {

    override val content = {
      val src =
        new String(Files.readAllBytes(Paths.get(path, "FilterOne_Int.scala")), "UTF-8")
          .replace("package", "// GENERATED CODE ********************************\npackage")
          .replace("[Int]", s"[$tpe]")
          .replace("Int extends", name + "_ extends")
          .replace("int", v)

      if (imp.isEmpty) src else src.replace("\n\nimport", s"\n\nimport $imp\nimport")
    }
  }
}
