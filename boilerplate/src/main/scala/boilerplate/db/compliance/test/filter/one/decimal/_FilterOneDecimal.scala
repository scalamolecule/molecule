package boilerplate.db.compliance.test.filter.one.decimal

import java.nio.file.{Files, Paths}
import boilerplate.Base
import boilerplate.db.compliance.DbComplianceGenBase

object _FilterOneDecimal extends Base {

  def generate(): Unit = Seq(
    ("Float", "float"),
    // ("BigDecimal", "bigDecimal"), // special case for SQlite
  ).foreach { case (tpe, v) =>
    TransformFile(tpe, v).generate()
  }

  case class TransformFile(tpe: String, v: String, imp: String = "")
    extends DbComplianceGenBase(s"FilterOneDecimal_$tpe", "/filter/one/decimal") {

    override val content = {
      new String(Files.readAllBytes(Paths.get(path, "FilterOneDecimal_Double.scala")), "UTF-8")
        .replace("package", "// GENERATED CODE ********************************\npackage")
        .replace("[Double]", s"[$tpe]")
        .replace("Double(", tpe + "_(")
        .replace("double", v)
    }
  }
}
