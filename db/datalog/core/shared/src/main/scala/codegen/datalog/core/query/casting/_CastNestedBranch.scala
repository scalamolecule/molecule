package codegen.datalog.core.query.casting

import codegen.DatomicGenBase

object _CastNestedBranch extends DatomicGenBase("CastNestedBranch", "/query/casting") {

  val content = {
    val resolveX       = (1 to 21).map(i => s"case ${caseN(i)} => cast$i[T](casters)").mkString("\n      ")
    val resolveMethods = (1 to 21).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.datalog.core.query.casting
       |
       |import molecule.datalog.core.query.DatomicQueryBase
       |
       |
       |trait $fileName_ { self: DatomicQueryBase =>
       |
       |  final protected def castBranch[T](
       |    casts: List[AnyRef => AnyRef],
       |    firstAttrIndex: AttrIndex,
       |  ): (Row, NestedTpls) => T = {
       |    val casters = casts.zipWithIndex.map { case (cast, i) =>
       |      (row: Row, _: NestedTpls) => cast(row.get(firstAttrIndex + i))
       |    } :+ ((_: Row, nested: NestedTpls) => nested)
       |
       |    casters.length match {
       |      case 0 => cast0[T]
       |      $resolveX
       |    }
       |  }
       |
       |  final private def cast0[T]: (Row, NestedTpls) => T = {
       |    (_: Row, nested: NestedTpls) => nested.asInstanceOf[T]
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val casters  = (1 to i).map("c" + _).mkString(", ")
    val castings = (1 to i).map { j => s"c$j(row, nested)" }.mkString(",\n        ")
    val body     =
      s"""
         |  final private def cast$i[T](casters: List[(Row, NestedTpls) => Any]): (Row, NestedTpls) => T = {
         |    val List($casters) = casters
         |    (row: Row, nested: NestedTpls) =>
         |      (
         |        $castings
         |      ).asInstanceOf[T]
         |  }""".stripMargin
  }
}
