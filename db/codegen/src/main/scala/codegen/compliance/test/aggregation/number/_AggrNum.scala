package codegen.compliance.test.aggregation.number

import java.nio.file.{Files, Paths}
import codegen.base.CodeGenBase
import codegen.compliance.SpiTestGenBase

object _AggrNum extends CodeGenBase  {

  def generate(): Unit = numberTypes.foreach { case (name, tpe, v) =>
    TransformFile(name, tpe, v).generate()
  }

  case class TransformFile(name: String, tpe: String, v: String)
    extends SpiTestGenBase(s"AggrNum_$name", "/aggregation/number") {

    override val content = {
      new String(Files.readAllBytes(Paths.get(path, "AggrNum_Int.scala")), "UTF-8")
        .replace("package", "// GENERATED CODE ********************************\npackage")
        .replace("[Int]", s"[$tpe]")
        .replace("Int extends", name + "_ extends")
        .replace("println", "prindln")
        .replace("tolerantIntEquality(toleranceInt)", s"tolerant${tpe}Equality(tolerance$tpe)")
        .replace("int", v)
        .replace("prindln", "println")
    }
  }
}
