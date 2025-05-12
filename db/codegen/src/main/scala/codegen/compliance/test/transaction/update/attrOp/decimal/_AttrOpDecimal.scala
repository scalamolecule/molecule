package codegen.compliance.test.transaction.update.attrOp.decimal

import java.nio.file.{Files, Paths}
import codegen.base.CodeGenBase
import codegen.compliance.SpiTestGenBase

object _AttrOpDecimal extends CodeGenBase {

  def generate(): Unit = Seq(
    ("Float", "float"),
    ("BigDecimal", "bigDecimal"),
  ).foreach { case (tpe, v) =>
    TransformFile(tpe, v).generate()
  }

  case class TransformFile(tpe: String, v: String, imp: String = "")
    extends SpiTestGenBase(s"AttrOpDecimal_$tpe", "/transaction/update/attrOp/decimal") {

    override val content = {
      new String(Files.readAllBytes(Paths.get(path, "AttrOpDecimal_Double.scala")), "UTF-8")
        .replace("package", "// GENERATED CODE ********************************\npackage")
        .replace("[Double]", s"[$tpe]")
        .replace("Double extends", tpe + "_ extends")
        .replace("double", v)
        .replace("Double", tpe)
    }
  }
}
