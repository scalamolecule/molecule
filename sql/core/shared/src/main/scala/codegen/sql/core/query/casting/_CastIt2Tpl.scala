package codegen.sql.core.query.casting

import codegen.sql.SqlGenBase

object _CastIt2Tpl extends SqlGenBase("CastIt2Tpl", "/query/casting") {

  val content = {
    val resolveX       = (1 to 22).map(i => s"case $i => cast$i(casts)").mkString("\n      ")
    val resolveMethods = (1 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.sql.core.query.casting
       |
       |import java.util.{Iterator => jIterator}
       |
       |
       |trait $fileName_ {
       |
       |  final protected def castIt2Tpl(
       |    casts: List[jIterator[_] => Any]
       |  ): jIterator[_] => Any = {
       |    casts.length match {
       |      $resolveX
       |    }
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val casters  = (1 to i).map("c" + _).mkString(", ")
    val castings = (1 to i).map { j => s"c$j(it)" }.mkString(",\n        ")
    val body     =
      s"""
         |  final private def cast$i(casters: List[jIterator[_] => Any]): jIterator[_] => Any = {
         |    val List($casters) = casters
         |    (it: jIterator[_]) =>
         |      (
         |        $castings
         |      )
         |  }""".stripMargin
  }
}
