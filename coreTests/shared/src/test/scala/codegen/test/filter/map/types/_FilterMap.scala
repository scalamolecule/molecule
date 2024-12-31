package codegen.test.filter.map.types

import java.nio.file.{Files, Paths}
import codegen.SpiTestGenBase
import molecule.base.util.{BaseHelpers, CodeGenBase}

object _FilterMap extends CodeGenBase with BaseHelpers {

  //  def generate(): Unit = (("Boolean", "Boolean", "boolean", "") +: tpeVarImp)
  //    def generate(): Unit = tpeVarImp.take(1) // String
  def generate(): Unit = tpeVarImp // all
    .filterNot(_._1 == "ref")
    .foreach { case (name, tpe, v, imp) =>
      TransformFile(name, tpe, v, imp).generate()
    }

  case class TransformFile(name: String, tpe: String, v: String, imp: String = "")
    extends SpiTestGenBase(s"FilterMap_$name", "/filter/map/types") {

    override val content = {
      val src =
        new String(Files.readAllBytes(Paths.get(path, "FilterMap_Int.scala")), "UTF-8")
          .replace("package", "// GENERATED CODE ********************************\npackage")
          .replace("[Int]", s"[$tpe]")
          .replace("FilterMap_Int", "FilterMap_" + name + "_")
          .replace("int", v)

      if (imp.isEmpty) src else src.replace("\n\nimport", s"\n\nimport $imp\nimport")
    }
  }
}
