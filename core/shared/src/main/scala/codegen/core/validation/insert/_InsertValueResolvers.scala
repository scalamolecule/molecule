package codegen.core.validation.insert

import codegen.CoreGenBase

object _InsertValueResolvers extends CoreGenBase("InsertValueResolvers", "/validation/insert") {

  val content = {
    val resolveX       = (1 to 22).map(i => s"case $i => resolve$i(valueResolvers)").mkString("\n      ")
    val resolveMethods = (1 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.core.validation.insert
       |
       |import java.net.URI
       |import java.time._
       |import java.util.{Date, UUID}
       |import molecule.boilerplate.ast.Model._
       |
       |trait $fileName_ {
       |
       |def tpl2valueResolver(a: Attr, curElements: List[Element]): Product => Seq[Value] = {
       |    val valueResolvers: Seq[Product => Value] = a.valueAttrs.flatMap { attr =>
       |      curElements.zipWithIndex.collectFirst {
       |        case (a1: Attr, i) if a1.attr == attr => (attr, a1, i)
       |      }
       |    }.sortBy(_._1).map { case (_, a, i) =>
       |      a match {
       |        case a: AttrOneMan => a match {
       |          case _: AttrOneManString         => (tpl: Product) => OneString(tpl.productElement(i).asInstanceOf[String])
       |          case _: AttrOneManInt            => (tpl: Product) => OneInt(tpl.productElement(i).asInstanceOf[Int])
       |          case _: AttrOneManLong           => (tpl: Product) => OneLong(tpl.productElement(i).asInstanceOf[Long])
       |          case _: AttrOneManFloat          => (tpl: Product) => OneFloat(tpl.productElement(i).asInstanceOf[Float])
       |          case _: AttrOneManDouble         => (tpl: Product) => OneDouble(tpl.productElement(i).asInstanceOf[Double])
       |          case _: AttrOneManBoolean        => (tpl: Product) => OneBoolean(tpl.productElement(i).asInstanceOf[Boolean])
       |          case _: AttrOneManBigInt         => (tpl: Product) => OneBigInt(tpl.productElement(i).asInstanceOf[BigInt])
       |          case _: AttrOneManBigDecimal     => (tpl: Product) => OneBigDecimal(tpl.productElement(i).asInstanceOf[BigDecimal])
       |          case _: AttrOneManDate           => (tpl: Product) => OneDate(tpl.productElement(i).asInstanceOf[Date])
       |          case _: AttrOneManDuration       => (tpl: Product) => OneDuration(tpl.productElement(i).asInstanceOf[Duration])
       |          case _: AttrOneManInstant        => (tpl: Product) => OneInstant(tpl.productElement(i).asInstanceOf[Instant])
       |          case _: AttrOneManLocalDate      => (tpl: Product) => OneLocalDate(tpl.productElement(i).asInstanceOf[LocalDate])
       |          case _: AttrOneManLocalTime      => (tpl: Product) => OneLocalTime(tpl.productElement(i).asInstanceOf[LocalTime])
       |          case _: AttrOneManLocalDateTime  => (tpl: Product) => OneLocalDateTime(tpl.productElement(i).asInstanceOf[LocalDateTime])
       |          case _: AttrOneManOffsetTime     => (tpl: Product) => OneOffsetTime(tpl.productElement(i).asInstanceOf[OffsetTime])
       |          case _: AttrOneManOffsetDateTime => (tpl: Product) => OneOffsetDateTime(tpl.productElement(i).asInstanceOf[OffsetDateTime])
       |          case _: AttrOneManZonedDateTime  => (tpl: Product) => OneZonedDateTime(tpl.productElement(i).asInstanceOf[ZonedDateTime])
       |          case _: AttrOneManUUID           => (tpl: Product) => OneUUID(tpl.productElement(i).asInstanceOf[UUID])
       |          case _: AttrOneManURI            => (tpl: Product) => OneURI(tpl.productElement(i).asInstanceOf[URI])
       |          case _: AttrOneManByte           => (tpl: Product) => OneByte(tpl.productElement(i).asInstanceOf[Byte])
       |          case _: AttrOneManShort          => (tpl: Product) => OneShort(tpl.productElement(i).asInstanceOf[Short])
       |          case _: AttrOneManChar           => (tpl: Product) => OneChar(tpl.productElement(i).asInstanceOf[Char])
       |        }
       |
       |        case a: AttrSetMan => a match {
       |          case _: AttrSetManString         => (tpl: Product) => SetString(tpl.productElement(i).asInstanceOf[Set[String]])
       |          case _: AttrSetManInt            => (tpl: Product) => SetInt(tpl.productElement(i).asInstanceOf[Set[Int]])
       |          case _: AttrSetManLong           => (tpl: Product) => SetLong(tpl.productElement(i).asInstanceOf[Set[Long]])
       |          case _: AttrSetManFloat          => (tpl: Product) => SetFloat(tpl.productElement(i).asInstanceOf[Set[Float]])
       |          case _: AttrSetManDouble         => (tpl: Product) => SetDouble(tpl.productElement(i).asInstanceOf[Set[Double]])
       |          case _: AttrSetManBoolean        => (tpl: Product) => SetBoolean(tpl.productElement(i).asInstanceOf[Set[Boolean]])
       |          case _: AttrSetManBigInt         => (tpl: Product) => SetBigInt(tpl.productElement(i).asInstanceOf[Set[BigInt]])
       |          case _: AttrSetManBigDecimal     => (tpl: Product) => SetBigDecimal(tpl.productElement(i).asInstanceOf[Set[BigDecimal]])
       |          case _: AttrSetManDate           => (tpl: Product) => SetDate(tpl.productElement(i).asInstanceOf[Set[Date]])
       |          case _: AttrSetManDuration       => (tpl: Product) => SetDuration(tpl.productElement(i).asInstanceOf[Set[Duration]])
       |          case _: AttrSetManInstant        => (tpl: Product) => SetInstant(tpl.productElement(i).asInstanceOf[Set[Instant]])
       |          case _: AttrSetManLocalDate      => (tpl: Product) => SetLocalDate(tpl.productElement(i).asInstanceOf[Set[LocalDate]])
       |          case _: AttrSetManLocalTime      => (tpl: Product) => SetLocalTime(tpl.productElement(i).asInstanceOf[Set[LocalTime]])
       |          case _: AttrSetManLocalDateTime  => (tpl: Product) => SetLocalDateTime(tpl.productElement(i).asInstanceOf[Set[LocalDateTime]])
       |          case _: AttrSetManOffsetTime     => (tpl: Product) => SetOffsetTime(tpl.productElement(i).asInstanceOf[Set[OffsetTime]])
       |          case _: AttrSetManOffsetDateTime => (tpl: Product) => SetOffsetDateTime(tpl.productElement(i).asInstanceOf[Set[OffsetDateTime]])
       |          case _: AttrSetManZonedDateTime  => (tpl: Product) => SetZonedDateTime(tpl.productElement(i).asInstanceOf[Set[ZonedDateTime]])
       |          case _: AttrSetManUUID           => (tpl: Product) => SetUUID(tpl.productElement(i).asInstanceOf[Set[UUID]])
       |          case _: AttrSetManURI            => (tpl: Product) => SetURI(tpl.productElement(i).asInstanceOf[Set[URI]])
       |          case _: AttrSetManByte           => (tpl: Product) => SetByte(tpl.productElement(i).asInstanceOf[Set[Byte]])
       |          case _: AttrSetManShort          => (tpl: Product) => SetShort(tpl.productElement(i).asInstanceOf[Set[Short]])
       |          case _: AttrSetManChar           => (tpl: Product) => SetChar(tpl.productElement(i).asInstanceOf[Set[Char]])
       |        }
       |
       |        case a => throw new Exception("Unsupported attribute type for insert validation value resolvers: " + a)
       |      }
       |    }
       |
       |    valueResolvers.length match {
       |      $resolveX
       |    }
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val resolvers = (1 to i).map { j => s"r$j" }.mkString(", ")
    val calls     = (1 to i).map { j => s"r$j(tpl)" }.mkString(",\n        ")
    val body      =
      s"""
         |  final private def resolve$i(
         |    valueResolvers: Seq[Product => Value]
         |  ): Product => Seq[Value] = {
         |    val Seq($resolvers) = valueResolvers
         |    (tpl: Product) =>
         |      Seq(
         |        $calls
         |      )
         |  }""".stripMargin
  }
}
