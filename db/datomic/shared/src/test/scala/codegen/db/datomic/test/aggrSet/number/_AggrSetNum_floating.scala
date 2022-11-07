package codegen.db.datomic.test.aggrSet.number

import java.nio.file.{Files, Paths}
import codegen.DatomicTestGenBase
import molecule.base.util.{BaseHelpers, CodeGenBase}

object _AggrSetNum_floating extends CodeGenBase with BaseHelpers {

  def generate: Unit = {
    TransformFile("BigDecimal", "bigDecimal").generate
    TransformFile("Float", "float").generate
  }

  case class TransformFile(tpe: String, v: String)
    extends DatomicTestGenBase(s"AggrSetNum_$tpe", "/test/aggrSet/number") {

    val content = {
      val src =
        new String(Files.readAllBytes(Paths.get(path, "AggrSetNum_Double.scala")), "UTF-8")
          .replace("package", "// GENERATED CODE ********************************\npackage")
          .replace("Double", tpe)
          .replace("double", v)
          .replace(" extends", "_ extends")

      // Floating precision differences
      tpe match {
        case "BigDecimal" => src
          .replace("(1, Set(3.3000000000000003))", "(1, Set(3.3))")
          .replace("(1, Set(1.6500000000000001))", "(1, Set(1.65))")

        case "Float" => src
          // sum
          .replace("11", "11.00000011920929")
          .replace("(1, Set(3.3000000000000003))", "(1, Set(3.3000000715255737))")
          .replace("(2, Set(9.9))", "(2, Set(9.900000095367432))")
          // median
          .replace("1, Set(1.0)", "1, Set(1.0f)")
          .replace("2, Set(3.3)", "2, Set(3.3f)")
          // avg
          .replace("2.75", "2.7500000298023224")
          .replace("1.6500000000000001", "1.6500000357627869")
          .replace("3.3000000000000003", "3.300000031789144")
          // variance
          .replace("1.5125000000000002", "1.5125000327825573")
          .replace("0.30250000000000005", "0.302500013113022")
          .replace("0.8066666666666668", "0.8066667016347285")
          // stddev
          .replace("1.2298373876248845", "1.2298374009528892")
          .replace("0.55", "0.550000011920929")
          .replace("0.8981462390204987", "0.8981462584872959")

        case _ => src
      }
    }
  }
}
