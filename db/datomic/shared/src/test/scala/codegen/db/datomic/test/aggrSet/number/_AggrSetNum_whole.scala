package codegen.db.datomic.test.aggrSet.number

import java.nio.file.{Files, Paths}
import codegen.DatomicTestGenBase
import molecule.base.util.{BaseHelpers, CodeGenBase}

object _AggrSetNum_whole extends CodeGenBase with BaseHelpers {

  def generate: Unit = {
    TransformFile("BigInt", "bigInt").generate
    TransformFile("Byte", "byte").generate
    TransformFile("Long", "long").generate
    TransformFile("Short", "short").generate
  }

  case class TransformFile(tpe: String, v: String)
    extends DatomicTestGenBase(s"AggrSetNum_$tpe", "/test/aggrSet/number") {

    val content = {
      new String(Files.readAllBytes(Paths.get(path, "AggrSetNum_Int.scala")), "UTF-8")
        .replace("package", "// GENERATED CODE ********************************\npackage")
        .replace("Int", tpe)
        .replace("int", v)
        .replace(v + "ersect", "intersect")
        .replace(" extends", "_ extends")
    }
  }
}
