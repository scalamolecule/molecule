package codegen.db.datomic.test.aggrOne.number

import java.nio.file.{Files, Paths}
import codegen.DatomicTestGenBase
import codegen.db.datomic.test.aggrSet.number._AggrSetNum_floating.TransformFile
import molecule.base.util.{BaseHelpers, CodeGenBase}

object _AggrOneNum_floating extends CodeGenBase with BaseHelpers {

  def generate: Unit = {
    TransformFile("BigDecimal", "bigDecimal").generate
    TransformFile("Float", "float").generate
  }

  case class TransformFile(tpe: String, v: String, imp: String = "")
    extends DatomicTestGenBase(s"AggrOneNum_$tpe", "/test/aggrOne/number") {

    val content = {
      val src =
        new String(Files.readAllBytes(Paths.get(path, "AggrOneNum_Double.scala")), "UTF-8")
          .replace("package", "// GENERATED CODE ********************************\npackage")
          .replace("Double", tpe)
          .replace("double", v)
          .replace(" extends", "_ extends")

      // Floating precision differences
      val src2 = tpe match {
        case "BigDecimal" => src
          .replace("6.6000000000000005", "6.6")
          .replace("3.3000000000000003", "3.3")

        case "Float" => src
          // sum
          .replace("7.7", "7.700000166893005")
          .replace("1.1", "1.100000023841858")
          .replace("6.6000000000000005", "6.6000001430511475")
          // median
          .replace("2.2", "2.2f")
          // avg
          .replace("2.566666666666667", "2.5666667222976685")
          .replace("3.3000000000000003", "3.3000000715255737")
          // variance
          .replace("1.8822222222222225", "1.882222303814359")
          .replace("1.2100000000000002", "1.210000052452088")
          // stddev
          .replace("1.3719410418171119", "1.371941071553133")

        case _ => src
      }

      if (imp.isEmpty) src2 else
        src2.replace("\n\nimport", s"\n\nimport $imp\nimport")
    }
  }
}
