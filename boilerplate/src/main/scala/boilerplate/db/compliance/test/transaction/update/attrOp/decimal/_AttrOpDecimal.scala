package boilerplate.db.compliance.test.transaction.update.attrOp.decimal

import java.nio.file.{Files, Paths}
import boilerplate.Base
import boilerplate.db.compliance.ComplianceGenBase

object _AttrOpDecimal extends Base {

  def generate(): Unit = Seq(
    ("Float", "float"),
    ("BigDecimal", "bigDecimal"),
  ).foreach { case (tpe, v) =>
    TransformFile(tpe, v).generate()
  }

  case class TransformFile(tpe: String, v: String, imp: String = "")
    extends ComplianceGenBase(s"AttrOpDecimal_$tpe", "/action/update/attrOp/decimal") {

    override val content = {
      new String(Files.readAllBytes(Paths.get(path, "AttrOpDecimal_Double.scala")), "UTF-8")
        .replace("package", "// GENERATED CODE ********************************\npackage")
        .replace("[Double]", s"[$tpe]")
        .replace("Double(", tpe + "_(")
        .replace("double", v)
        .replace("Double", tpe)
    }
  }
}
