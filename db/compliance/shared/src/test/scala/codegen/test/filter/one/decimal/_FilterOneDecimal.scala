package codegen.test.filter.one.decimal

import java.nio.file.{Files, Paths}
import codegen.SpiTestGenBase
import molecule.db.base.util.{BaseHelpers, CodeGenBase}

object _FilterOneDecimal extends CodeGenBase with BaseHelpers {

  def generate(): Unit = Seq(
    ("Float", "float"),
    ("BigDecimal", "bigDecimal"),
  ).foreach { case (tpe, v) =>
    TransformFile(tpe, v).generate()
  }

  case class TransformFile(tpe: String, v: String, imp: String = "")
    extends SpiTestGenBase(s"FilterOneDecimal_$tpe", "/filter/one/decimal") {

    override val content = {
      new String(Files.readAllBytes(Paths.get(path, "FilterOneDecimal_Double.scala")), "UTF-8")
        .replace("package", "// GENERATED CODE ********************************\npackage")
        .replace("[Double]", s"[$tpe]")
        .replace("Double extends", tpe + "_ extends")
        .replace("double", v)
    }
  }
}
