package codegen.datalog.core.query.casting

import codegen.DatomicGenBase

object _CastRow2AnyTpl extends DatomicGenBase("CastRow2AnyTpl", "/query/casting") {

  val content = {
    val resolveX       = (1 to 22).map(i => s"case ${caseN(i)} => cast$i(casters)").mkString("\n      ")
    val resolveMethods = (1 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.datomic.query.casting
       |
       |import molecule.core.query.Model2Query
       |import molecule.datomic.query.DatomicQueryBase
       |import scala.annotation.tailrec
       |
       |
       |trait $fileName_[Tpl] { self: Model2Query with DatomicQueryBase =>
       |
       |  @tailrec
       |  final private def resolveArities(
       |    arities: List[Int],
       |    casts: List[AnyRef => AnyRef],
       |    attrIndex: AttrIndex,
       |    acc: List[Row => Any]
       |  ): List[Row => Any] = {
       |    arities match {
       |      case 0 :: as =>
       |        // Attribute
       |        val cast = (row: Row) => casts.head(row.get(attrIndex))
       |        resolveArities(as, casts.tail, attrIndex + 1, acc :+ cast)
       |
       |      case -2 :: as =>
       |        // Optional ref data
       |        val cast = (row: Row) => casts.head(row.get(attrIndex))
       |        resolveArities(as, casts.tail, 0, acc :+ cast)
       |
       |      case _ => acc
       |    }
       |  }
       |
       |  final protected def castRow2AnyTpl(
       |    arities: List[List[Int]],
       |    casts: List[AnyRef => AnyRef],
       |    attrIndex: AttrIndex,
       |    nested: Option[NestedTpls]
       |  ): Row => Any = {
       |    val casters = resolveArities(arities, casts, attrIndex, Nil)
       |    arities.length match {
       |      $resolveX
       |    }
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val casters  = (1 to i).map("c" + _).mkString(", ")
    val castings = (1 to i).map { j => s"c$j(row)" }.mkString(",\n        ")
    val body     =
      s"""
         |  final private def cast$i(casters: List[Row => Any]): Row => Any = {
         |    val List($casters) = casters
         |    (row: Row) =>
         |      (
         |        $castings
         |      )
         |  }""".stripMargin
  }
}
