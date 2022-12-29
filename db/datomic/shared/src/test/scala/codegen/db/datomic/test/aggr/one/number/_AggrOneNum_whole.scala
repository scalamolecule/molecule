package codegen.db.datomic.test.aggr.one.number

import java.nio.file.{Files, Paths}
import codegen.DatomicTestGenBase
import molecule.base.util.{BaseHelpers, CodeGenBase}

object _AggrOneNum_whole extends CodeGenBase with BaseHelpers {

  def generate: Unit = {
    TransformFile("BigInt", "BigInt", "bigInt").generate
    TransformFile("Byte", "Byte", "byte").generate
    TransformFile("Long", "Long", "long").generate
    TransformFile("Short", "Short", "short").generate
    TransformFile("ref", "Long", "ref").generate
  }

  case class TransformFile(name: String, tpe: String, v: String, imp: String = "")
    extends DatomicTestGenBase(s"AggrOneNum_$name", "/test/aggr/one/number") {

    val content = {
      new String(Files.readAllBytes(Paths.get(path, "AggrOneNum_Int.scala")), "UTF-8")
        .replace("package", "// GENERATED CODE ********************************\npackage")
        .replace("[Int]", s"[$tpe]")
        .replace("Int extends", name + "_ extends")
        .replace("int", v)
    }
  }
}
