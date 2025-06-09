package boilerplate.db.compliance.test.transaction.update.attrOp.number

import java.nio.file.{Files, Paths}
import boilerplate.Base
import boilerplate.db.compliance.DbComplianceGenBase

object _AttrOpInteger extends Base {

  def generate(): Unit = Seq(
    ("Long", "long"),
    // ("BigInt", "bigInt"), // special case for SQlite
    ("Byte", "byte"),
    ("Short", "short"),
  ).foreach { case (tpe, v) =>
    TransformFile(tpe, v).generate()
  }

  case class TransformFile(tpe: String, v: String, imp: String = "")
    extends DbComplianceGenBase(s"AttrOpInteger_$tpe", "/action/update/attrOp/number") {

    override val content = {
      new String(Files.readAllBytes(Paths.get(path, "AttrOpInteger_Int.scala")), "UTF-8")
        .replace("package", "// GENERATED CODE ********************************\npackage")
        .replace("[Int]", s"[$tpe]")
        .replace("Int(", tpe + "_(")
        .replace("int", v)
        .replace("-byte1", "-1.toByte")
        .replace("-byte2", "-2.toByte")
        .replace("-short1", "-1.toShort")
        .replace("-short2", "-2.toShort")
    }
  }
}
