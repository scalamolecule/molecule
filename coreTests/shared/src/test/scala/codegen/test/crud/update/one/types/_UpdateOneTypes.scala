package codegen.test.crud.update.one.types

import java.nio.file.{Files, Paths}
import codegen.SpiTestGenBase
import molecule.base.util.{BaseHelpers, CodeGenBase}

object _UpdateOneTypes extends CodeGenBase with BaseHelpers {

  def generate(): Unit = (("Boolean", "Boolean", "boolean", "") +: tpeVarImp).filterNot(_._1 == "ref").foreach { case (name, tpe, v, imp) =>
    TransformFile(name, tpe, v, imp).generate()
  }

  case class TransformFile(name: String, tpe: String, v: String, imp: String = "")
    extends SpiTestGenBase(s"UpdateOne_$name", "/crud/update/one/types") {

    override val content = {
      val src =
        new String(Files.readAllBytes(Paths.get(path, "UpdateOne_Int.scala")), "UTF-8")
          .replace("package", "// GENERATED CODE ********************************\npackage")
          .replace("[Int]", s"[$tpe]")
          .replace("Int extends", name + "_ extends")
          .replace("int", v)

      if (imp.isEmpty) src else src.replace("\n\nimport", s"\n\nimport $imp\nimport")
    }
  }
}
