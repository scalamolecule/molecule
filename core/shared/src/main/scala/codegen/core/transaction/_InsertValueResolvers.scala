package codegen.core.transaction

import codegen.CoreGenBase

object _InsertValueResolvers extends CoreGenBase("InsertValueResolvers", "/transaction") {

  val content = {
    val resolveX       = (1 to 22).map(i => s"case $i => resolve$i(valueResolvers)").mkString("\n      ")
    val resolveMethods = (1 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.core.transaction
       |
       |import java.net.URI
       |import java.util.{Date, UUID}
       |import molecule.boilerplate.ast.Model._
       |
       |trait $fileName_ {
       |
       |  def tpl2valueResolver(a: Attr, curElements: List[Element]): Product => Seq[Value] = {
       |    val valueResolvers: Seq[Product => Value] = a.valueAttrs.flatMap { attr =>
       |      curElements.zipWithIndex.collectFirst {
       |        case (a1: Attr, i) if a1.attr == attr => (attr, a1, i)
       |      }
       |    }.sortBy(_._1).map { case (_, a, i) =>
       |      a match {
       |        case _: AttrOneManString     => (tpl: Product) => OneString(tpl.productElement(i).asInstanceOf[String])
       |        case _: AttrOneManInt        => (tpl: Product) => OneInt(tpl.productElement(i).asInstanceOf[Int])
       |        case _: AttrOneManLong       => (tpl: Product) => OneLong(tpl.productElement(i).asInstanceOf[Long])
       |        case _: AttrOneManFloat      => (tpl: Product) => OneFloat(tpl.productElement(i).asInstanceOf[Float])
       |        case _: AttrOneManDouble     => (tpl: Product) => OneDouble(tpl.productElement(i).asInstanceOf[Double])
       |        case _: AttrOneManBoolean    => (tpl: Product) => OneBoolean(tpl.productElement(i).asInstanceOf[Boolean])
       |        case _: AttrOneManBigInt     => (tpl: Product) => OneBigInt(tpl.productElement(i).asInstanceOf[BigInt])
       |        case _: AttrOneManBigDecimal => (tpl: Product) => OneBigDecimal(tpl.productElement(i).asInstanceOf[BigDecimal])
       |        case _: AttrOneManDate       => (tpl: Product) => OneDate(tpl.productElement(i).asInstanceOf[Date])
       |        case _: AttrOneManUUID       => (tpl: Product) => OneUUID(tpl.productElement(i).asInstanceOf[UUID])
       |        case _: AttrOneManURI        => (tpl: Product) => OneURI(tpl.productElement(i).asInstanceOf[URI])
       |        case _: AttrOneManByte       => (tpl: Product) => OneByte(tpl.productElement(i).asInstanceOf[Byte])
       |        case _: AttrOneManShort      => (tpl: Product) => OneShort(tpl.productElement(i).asInstanceOf[Short])
       |        case _: AttrOneManChar       => (tpl: Product) => OneChar(tpl.productElement(i).asInstanceOf[Char])
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
