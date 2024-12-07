package codegen.test.filter.one.number

import java.nio.file.{Files, Paths}
import codegen.SpiTestGenBase
import molecule.base.util.{BaseHelpers, CodeGenBase}

object _FilterOneInteger extends CodeGenBase with BaseHelpers {

  def generate(): Unit = Seq(
    ("Long", "long"),
    ("BigInt", "bigInt"),
    ("Byte", "byte"),
    ("Short", "short"),
  ).foreach { case (tpe, v) =>
    TransformFile(tpe, v).generate()
  }

  case class TransformFile(tpe: String, v: String, imp: String = "")
    extends SpiTestGenBase(s"FilterOneInteger_$tpe", "/filter/one/number") {

    override val content = {
      new String(Files.readAllBytes(Paths.get(path, "FilterOneInteger_Int.scala")), "UTF-8")
        .replace("package", "// GENERATED CODE ********************************\npackage")
        .replace("[Int]", s"[$tpe]")
        .replace("Int extends", tpe + "_ extends")
        .replace("int", v)
        .replace("-byte1", "-1.toByte")
        .replace("-byte2", "-2.toByte")
        .replace("-short1", "-1.toShort")
        .replace("-short2", "-2.toShort")

    }
  }
}
