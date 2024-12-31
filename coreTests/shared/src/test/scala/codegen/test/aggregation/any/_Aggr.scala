package codegen.test.aggregation.any

import java.nio.file.{Files, Paths}
import codegen.SpiTestGenBase
import molecule.base.util.{BaseHelpers, CodeGenBase}

object _Aggr extends CodeGenBase with BaseHelpers {

  //  def generate(): Unit = tpeVarImp.take(1).foreach { case (name, tpe, v, imp) =>
  def generate(): Unit = tpeVarImp.foreach { case (name, tpe, v, imp) =>
    TransformFile(name, tpe, v, imp).generate()
  }

  case class TransformFile(name: String, tpe: String, v: String, imp: String = "")
    extends SpiTestGenBase(s"Aggr_$name", "/aggregation/any") {

    override val content = {
      val src =
        new String(Files.readAllBytes(Paths.get(path, "Aggr_Int.scala")), "UTF-8")
          .replace("package", "// GENERATED CODE ********************************\npackage")
          .replace("[Int]", s"[$tpe]")
          .replace("Int extends", name + "_ extends")
          .replace("int", v)
          .replace(v + "ersect", "intersect")

      if (imp.isEmpty) src else src.replace("\n\nimport", s"\n\nimport $imp\nimport")
    }
  }
}
