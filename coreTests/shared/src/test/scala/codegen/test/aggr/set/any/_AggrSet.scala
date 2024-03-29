package codegen.test.aggr.set.any

import java.nio.file.{Files, Paths}
import codegen.SpiTestGenBase
import molecule.base.util.{BaseHelpers, CodeGenBase}

object _AggrSet extends CodeGenBase with BaseHelpers {

  def generate(): Unit = tpeVarImp.filterNot(_._1 == "ref").foreach { case (name, tpe, v, imp) =>
    TransformFile(name, tpe, v, imp).generate()
  }

  case class TransformFile(name: String, tpe: String, v: String, imp: String = "")
    extends SpiTestGenBase(s"AggrSet_$name", "/aggr/set/any") {

    override val content = {
      val src =
        new String(Files.readAllBytes(Paths.get(path, "AggrSet_Int.scala")), "UTF-8")
          .replace("package", "// GENERATED CODE ********************************\npackage")
          .replace("[Int]", s"[$tpe]")
          .replace("Int extends", name + "_ extends")
          .replace(": Int", s": $tpe")
          .replace("int", v)
          .replace(v + "ersect", "intersect")

      if (imp.isEmpty) src else src.replace("\n\nimport", s"\n\nimport $imp\nimport")
    }
  }
}
