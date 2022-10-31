package codegen.db.datomic.test.aggr.number

import java.nio.file.{Files, Paths}
import codegen.DatomicTestGenBase
import molecule.base.util.{BaseHelpers, CodeGenBase}

object _AggrNum_Types extends CodeGenBase with BaseHelpers {

  def generate: Unit = {
    tpeVarImpNum.filterNot(_._1 == "Int").foreach { case (tpe, (v, imp)) =>
      TransformFile(tpe, v, imp).generate
    }
  }

  case class TransformFile(tpe: String, v: String, imp: String = "")
    extends DatomicTestGenBase(s"AggrNum_${tpe}_", "/test/aggr/number") {

    val content = {
      val src =
        new String(Files.readAllBytes(Paths.get(path, "AggrNum_Int.scala")), "UTF-8")
          .replace("package", "// GENERATED CODE ********************************\npackage")
          .replace("Int", tpe)
          .replace("int", v)
          .replace(v + "ersect", "intersect")
          .replace(" extends", "_ extends")

      // Variations from type to type:
      val src2 = tpe match {
        case "Double" => src
          .replace("(double1 + double2 + double3)", "6.6000000000000005")
          .replace("1.5555555555555554", "1.8822222222222225")
          .replaceFirst("2, 1.0", "2, 1.2100000000000002")
          .replace("1.247219128924647", "1.3719410418171119")
          .replace("2, 1.0", "2, 1.1")

        case "Float" => src
          .replace("(float1 + float2 + float3)", "6.600000023841858")
          .replace("(float1 + float2 + float4) / 3.0", "2.5666667222976685")
          .replace("1.5555555555555554", "1.882222303814359")
          .replace("float1 / 1.0", "1.100000023841858")
          .replace("(float2 + float4) / 2.0", "3.3000000715255737")
          .replaceFirst("2, 1.0", "2, 1.210000052452088")
          .replace("1.247219128924647", "1.371941071553133")
          .replace("2, 1.0", "2, 1.100000023841858")

        case "BigInt" => src
          .replace(
            "(bigInt1 + bigInt2 + bigInt4) / 3.0",
            "(BigDecimal(bigInt1 + bigInt2 + bigInt4) / BigDecimal(3)).toDouble"
          )
          .replace("bigInt1 / 1.0", "bigInt1 / BigInt(1)")
          .replace("(bigInt2 + bigInt4) / 2.0", "(bigInt2 + bigInt4) / BigInt(2)")

        case "BigDecimal" => src
          .replace(
            "(bigDecimal1 + bigDecimal2 + bigDecimal4) / 3.0",
            "((bigDecimal1 + bigDecimal2 + bigDecimal4) / 3.0).toDouble"
          )
          .replace("1.5555555555555554", "1.8822222222222225")
          .replaceFirst("2, 1.0", "2, 1.2100000000000002")
          .replace("1.247219128924647", "1.3719410418171119")
          .replace("2, 1.0", "2, 1.1")


        case "Byte" => src
          .replace("(2, 3.0),", "(2, 4), // why is this 3 and not 3???")

        case "Short" => src
          .replace("(2, 3.0),", "(2, 4), // why is this 3 and not 3???")

        case _ => src
      }

      if (imp.isEmpty) src2 else
        src2.replace("\n\nimport", s"\n\nimport $imp\nimport")
    }
  }
}
