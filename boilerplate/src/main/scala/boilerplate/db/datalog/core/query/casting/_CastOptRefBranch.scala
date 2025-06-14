package boilerplate.db.datalog.core.query.casting

import boilerplate.db.datalog.DbDatalogBase

object _CastOptRefBranch extends DbDatalogBase("CastOptRefBranch", "/query/casting") {

  val content = {
    val pullBranchX    = (1 to 21).map(i => s"case ${caseN(i)} => pullBranch$i(pullCasts, refDepth)").mkString("\n      ")
    val resolveMethods = (1 to 21).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.datalog.core.query.casting
       |
       |import java.util.{ArrayList as jArrayList, Iterator as jIterator, Map as jMap}
       |import molecule.core.util.JavaConversions
       |
       |
       |trait $fileName_ extends JavaConversions {
       |
       |  final protected def pullOptRefBranch(
       |    pullCasts0: List[jIterator[?] => Any],
       |    pullNested: jIterator[?] => Option[Any],
       |    refDepth: Int
       |  ): jIterator[?] => Option[Any] = {
       |    val pullCasts = pullCasts0 :+ pullNested
       |    pullCasts.length match {
       |      $pullBranchX
       |    }
       |  }
       |
       |  final private def flatten(
       |    list: jArrayList[Any],
       |    map: jMap[?, ?],
       |    max: Int,
       |    cur: Int
       |  ): jArrayList[Any] = {
       |    map.values.asScala.foreach {
       |      case map: jMap[_, _] if cur == max => list.add(map)
       |      case map: jMap[_, _]               => flatten(list, map, max, cur + 1)
       |      case v                             => list.add(v)
       |    }
       |    list
       |  }
       |
       |  final private def resolve(
       |    arity: Int,
       |    refDepth: Int,
       |    cast: java.util.Iterator[?] => Any
       |  ): jIterator[?] => Option[Any] = {
       |    val list      = new jArrayList[Any](arity)
       |    val handleMap = (optionalData: jMap[?, ?]) => {
       |      list.clear()
       |      Some(cast(flatten(list, optionalData, refDepth, 0).iterator()))
       |    }
       |    (it: jIterator[?]) =>
       |      try {
       |        it.next match {
       |          case maps: jMap[_, _] => handleMap(maps)
       |          case _                => None
       |        }
       |      } catch {
       |        case _: NullValueException => None
       |        case e: Throwable          => throw e
       |      }
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val casters  = (1 to i).map { j => s"c$j" }.mkString(", ")
    val castings = (1 to i).map { j => s"c$j(it)" }.mkString(",\n        ")
    val body     =
      s"""
         |  final private def pullBranch$i(
         |    pullCasts: List[jIterator[?] => Any],
         |    refDepth: Int
         |  ): jIterator[?] => Option[Any] = {
         |    val List($casters) = pullCasts
         |    resolve($i, refDepth, (it: java.util.Iterator[?]) =>
         |      (
         |        $castings
         |      )
         |    )
         |  }""".stripMargin
  }
}
