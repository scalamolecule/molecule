package codegen.test.aggr.set.number

import java.nio.file.{Files, Paths}
import codegen.SpiTestGenBase
import molecule.base.util.{BaseHelpers, CodeGenBase}

object _AggrSetNum extends CodeGenBase with BaseHelpers {

  def generate(): Unit = numberTypes.foreach { case (name, tpe, v) =>
    TransformFile(name, tpe, v).generate()
  }


  case class TransformFile(name: String, tpe: String, v: String)
    extends SpiTestGenBase(s"AggrSetNum_$name", "/aggr/set/number") {

    override val content = {
      new String(Files.readAllBytes(Paths.get(path, "AggrSetNum_Int.scala")), "UTF-8")
        .replace("package", "// GENERATED CODE ********************************\npackage")
        .replace("[Int]", s"[$tpe]")
        .replace("Int extends", name + "_ extends")
        .replace("int", v)
        .replace(v + "ersect", "intersect")
    }
  }
}
