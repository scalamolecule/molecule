package codegen.db.datomic.test.aggrSet.any

import java.nio.file.{Files, Paths}
import codegen.DatomicTestGenBase
import molecule.base.util.{BaseHelpers, CodeGenBase}

object _AggrSet_tpe extends CodeGenBase with BaseHelpers {

  def generate: Unit = {
    tpeVarImp.filterNot(x => x._1 == "Int" || x._1 == "Boolean").foreach { case (tpe, (v, imp)) =>
      TransformFile(tpe, v, imp).generate
    }
  }

  case class TransformFile(tpe: String, v: String, imp: String = "")
    extends DatomicTestGenBase(s"AggrSet_$tpe", "/test/aggrSet/any") {

    val content = {
      val src =
        new String(Files.readAllBytes(Paths.get(path, "AggrSet_Int.scala")), "UTF-8")
          .replace("package", "// GENERATED CODE ********************************\npackage")
          .replace("Int", tpe)
          .replace("int", v)
          .replace(v + "ersect", "intersect")
          .replace(" extends", "_ extends")

      if (imp.isEmpty) src else src.replace("\n\nimport", s"\n\nimport $imp\nimport")
    }
  }
}
