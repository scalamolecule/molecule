package codegen.db.datomic.test.expr.one

import java.nio.file.{Files, Paths}
import codegen.DatomicTestGenBase
import molecule.base.util.{BaseHelpers, CodeGenBase}

object _ExprOne_tpe extends CodeGenBase with BaseHelpers {

  def generate: Unit = tpeVarImp.foreach { case (name, tpe, v, imp) =>
    TransformFile(name, tpe, v, imp).generate()
  }

  case class TransformFile(name: String, tpe: String, v: String, imp: String = "")
    extends DatomicTestGenBase(s"ExprOne_$name", "/test/expr/one") {

    val content = {
      val src =
        new String(Files.readAllBytes(Paths.get(path, "ExprOne_Int.scala")), "UTF-8")
          .replace("package", "// GENERATED CODE ********************************\npackage")
          .replace("[Int]", s"[$tpe]")
          .replace("Int extends", name + "_ extends")
          .replace("int", v)

      if (imp.isEmpty) src else src.replace("\n\nimport", s"\n\nimport $imp\nimport")
    }
  }
}
