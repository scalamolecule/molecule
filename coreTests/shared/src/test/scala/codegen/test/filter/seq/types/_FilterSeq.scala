package codegen.test.filter.seq.types

import java.nio.file.{Files, Paths}
import codegen.SpiTestGenBase
import molecule.base.util.{BaseHelpers, CodeGenBase}

object _FilterSeq extends CodeGenBase with BaseHelpers {

  def generate(): Unit = tpeVarImp.filterNot(
      coord => coord._3 == "ref" || coord._3 == "byte"
    ).foreach { case (name, tpe, v, imp) =>
      TransformFile(name, tpe, v, imp).generate()
    }

  case class TransformFile(name: String, tpe: String, v: String, imp: String = "")
    extends SpiTestGenBase(s"FilterSeq_$name", "/filter/seq/types") {

    override val content = {
      val src =
        new String(Files.readAllBytes(Paths.get(path, "FilterSeq_Int.scala")), "UTF-8")
          .replace("package", "// GENERATED CODE ********************************\npackage")
          .replace("[Int]", s"[$tpe]")
          .replace("Int extends", name + "_ extends")
          .replace("int", v)

      if (imp.isEmpty) src else src.replace("\n\nimport", s"\n\nimport $imp\nimport")
    }
  }
}
