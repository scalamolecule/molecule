package codegen.db.datomic.test.aggrSet.number

import java.nio.file.{Files, Paths}
import codegen.DatomicTestGenBase
import molecule.base.util.{BaseHelpers, CodeGenBase}

object _AggrSetNum_whole extends CodeGenBase with BaseHelpers {

  def generate: Unit = {
    TransformFile("BigInt", "BigInt", "bigInt").generate
    TransformFile("Byte", "Byte", "byte").generate
    TransformFile("Long", "Long", "long").generate
    TransformFile("Short", "Short", "short").generate
    TransformFile("ref", "Long", "ref").generate
  }

  case class TransformFile(name: String, tpe: String, v: String)
    extends DatomicTestGenBase(s"AggrSetNum_$name", "/test/aggrSet/number") {

    val content = {
      new String(Files.readAllBytes(Paths.get(path, "AggrSetNum_Int.scala")), "UTF-8")
        .replace("package", "// GENERATED CODE ********************************\npackage")
        .replace("[Int]", s"[$tpe]")
        .replace("Int extends", name + "_ extends")
        .replace("int", v)
        .replace(v + "ersect", "intersect")
    }
  }
}