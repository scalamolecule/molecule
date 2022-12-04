package codegen.db.datomic.query.casting

import codegen.DatomicGenBase

object _CastNestedOptBranch extends DatomicGenBase("CastNestedOptBranch", "/query/casting") {

  val content = {
    val pullBranchX    = (1 to 21).map(i => s"case $i => pullBranch$i(pullCasts, pullLeaf)").mkString("\n      ")
    val resolveMethods = (1 to 21).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.datomic.query.casting
       |
       |import java.util.{Iterator => jIterator, List => jList, Map => jMap}
       |import molecule.core.query.Model2Query
       |import molecule.db.datomic.query.Base
       |
       |
       |trait ${fileName}_[Tpl] {
       |  self: Model2Query[Tpl] with Base[Tpl] =>
       |
       |  final protected def pullBranch(
       |    pullCasts: List[jIterator[_] => Any],
       |    pullLeaf: jIterator[_] => List[Any]
       |  ): jIterator[_] => List[Any] = {
       |    pullCasts.length match {
       |      $pullBranchX
       |    }
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val casters  = (1 to i).map { j => s"c$j" }.mkString(", ")
    val castings = (1 to i).map { j => s"c$j(it)" }.mkString(",\n                ")
    val body     =
      s"""
         |  final private def pullBranch$i(
         |    pullCasts: List[jIterator[_] => Any],
         |    pullLeaf: jIterator[_] => List[Any]
         |  ): jIterator[_] => List[Any] = {
         |    val List($casters) = pullCasts
         |    (it: jIterator[_]) =>
         |      try {
         |        it.next match {
         |          case vs: jList[_] => vs.asScala.toList.map {
         |            case map: jMap[_, _] =>
         |              val it = map.values.iterator
         |              (
         |                $castings,
         |                pullLeaf(it)
         |              )
         |          }
         |          case _            => Nil
         |        }
         |      } catch {
         |        case _: NullValueException => Nil
         |        case e: Throwable          => throw e
         |      }
         |  }""".stripMargin
  }
}
