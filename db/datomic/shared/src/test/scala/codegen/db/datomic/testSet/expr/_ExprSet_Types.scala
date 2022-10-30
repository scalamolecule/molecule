package codegen.db.datomic.testSet.expr

import java.nio.file.{Files, Paths}
import codegen.DatomicTestGenBase
import molecule.base.util.{BaseHelpers, CodeGenBase}

object _ExprSet_Types extends CodeGenBase with BaseHelpers {


  def generate: Unit = {
    tpeVarImp.filterNot(x => x._1 == "Int" || x._1 == "Boolean").foreach { case (tpe, (v, imp)) =>
      TransformFile("One", tpe, v, imp).generate
    }
  }

  case class TransformFile(card: String, tpe: String, v: String, imp: String = "")
    extends DatomicTestGenBase(s"Expr${card}_${tpe}_", "/testSet/expr") {
    val cardTpe = card match {
      case "One"   => (baseType: String) => baseType
      case "Set"   => (baseType: String) => s"Set[$baseType]"
      case "Array" => (baseType: String) => s"Array[$baseType]"
      case "Map"   => (baseType: String) => s"Map[String, $baseType]"
    }

    val content = {
      val src =
        new String(Files.readAllBytes(Paths.get(path, "ExprSet_Int.scala")), "UTF-8")
          .replace("package", "// GENERATED CODE ********************************\npackage")
          .replace("Int", tpe)
          .replace("int", v)
          .replace(" extends", "_ extends")

      if (imp.isEmpty) src else src.replace("\n\nimport", s"\n\nimport $imp\nimport")
    }
  }
}
