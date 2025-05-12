package codegen.datalog.core.query.casting

import codegen.datalog.DatomicGenBase

object _CastRow2AnyTpl extends DatomicGenBase("CastRow2AnyTpl", "/query/casting") {

  val content = {
    val resolveX       = (1 to 22).map(i => s"case ${caseN(i)} => cast$i(casters)").mkString("\n      ")
    val resolveMethods = (1 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.datalog.core.query.casting
       |
       |import molecule.db.datalog.core.query.DatomicQueryBase
       |
       |
       |trait $fileName_ { self: DatomicQueryBase =>
       |
       |  final protected def castRow2AnyTpl(
       |    casts: List[AnyRef => AnyRef],
       |    attrIndex: AttrIndex
       |  ): Row => Any = {
       |    val casters: List[Row => Any] = casts.zipWithIndex.map {
       |      case (cast, i) =>
       |        (row: Row) => cast(row.get(attrIndex + i))
       |    }
       |    casters.length match {
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
