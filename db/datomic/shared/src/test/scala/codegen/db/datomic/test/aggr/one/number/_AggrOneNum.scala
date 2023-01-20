package codegen.db.datomic.test.aggr.one.number

import java.nio.file.{Files, Paths}
import codegen.DatomicTestGenBase
import molecule.base.util.{BaseHelpers, CodeGenBase}

object _AggrOneNum extends CodeGenBase with BaseHelpers {

  def generate: Unit = numberTypes.foreach{ case (name, tpe, v) =>
    TransformFile(name, tpe, v).generate()
  }

  case class TransformFile(name: String, tpe: String, v: String)
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
