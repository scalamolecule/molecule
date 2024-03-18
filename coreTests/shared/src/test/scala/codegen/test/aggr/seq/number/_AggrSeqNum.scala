package codegen.test.aggr.seq.number

import java.nio.file.{Files, Paths}
import codegen.SpiTestGenBase
import molecule.base.util.{BaseHelpers, CodeGenBase}

object _AggrSeqNum extends CodeGenBase with BaseHelpers {

  def generate(): Unit = numberTypes.filterNot(r => r._1 == "ref" || r._1 == "Byte").foreach { case (name, tpe, v) =>
    TransformFile(name, tpe, v).generate()
  }


  case class TransformFile(name: String, tpe: String, v: String)
    extends SpiTestGenBase(s"AggrSeqNum_$name", "/aggr/seq/number") {

    override val content = {
      val content1 = new String(Files.readAllBytes(Paths.get(path, "AggrSeqNum_Int.scala")), "UTF-8")
        .replace("package", "// GENERATED CODE ********************************\npackage")
        .replace("[Int]", s"[$tpe]")
        .replace("Int extends", name + "_ extends")
        .replace("tolerantIntEquality(toleranceInt)", s"tolerant${tpe}Equality(tolerance$tpe)")
        .replace("int", v)

      if (tpe == "BigInt") {
        content1.replace(" / ", ".toDouble / ")
      } else {
        content1
      }
    }
  }
}
