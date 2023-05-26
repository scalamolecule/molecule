package codegen.test.crud.update.set.ops

import java.nio.file.{Files, Paths}
import codegen.SpiTestGenBase
import molecule.base.util.{BaseHelpers, CodeGenBase}

object _UpdateSetOps_tpe extends CodeGenBase with BaseHelpers {

  def generate(): Unit = tpeVarImp.foreach { case (name, tpe, v, imp) =>
    TransformFile(name, tpe, v, imp).generate()
  }

  case class TransformFile(name: String, tpe: String, v: String, imp: String = "")
    extends SpiTestGenBase(s"UpdateSetOps_$name", "/crud/update/set/ops") {

    override val content = {
      val src =
        new String(Files.readAllBytes(Paths.get(path, "UpdateSetOps_Int.scala")), "UTF-8")
          .replace("package", "// GENERATED CODE ********************************\npackage")
          .replace("[Int]", s"[$tpe]")
          .replace("[(Int, Int)]", s"[($tpe, $tpe)]")
          .replace("Int extends", name + "_ extends")
          .replace("int", v)
          .replace(v + "ersect", "intersect")

      if (imp.isEmpty) src else src.replace("\n\nimport", s"\n\nimport $imp\nimport")
    }
  }
}
