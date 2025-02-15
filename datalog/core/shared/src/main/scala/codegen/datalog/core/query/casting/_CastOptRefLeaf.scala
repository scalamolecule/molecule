package codegen.datalog.core.query.casting

import codegen.DatomicGenBase

object _CastOptRefLeaf extends DatomicGenBase("CastOptRefLeaf", "/query/casting") {

  val content = {
    val pullLeafX      = (1 to 22).map(i => s"case ${caseN(i)} => pullLeaf$i(pullCasts)").mkString("\n      ")
    val resolveMethods = (1 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.datalog.core.query.casting
       |
       |import java.util.{ArrayList => jArrayList, Iterator => jIterator, Map => jMap}
       |import molecule.core.util.JavaConversions
       |
       |
       |trait $fileName_ extends JavaConversions {
       |
       |  final protected def pullOptRefLeaf(
       |    pullCasts: List[jIterator[_] => Any]
       |  ): jIterator[_] => Option[Any] = {
       |    pullCasts.length match {
       |      $pullLeafX
       |    }
       |  }
       |
       |  final private def flatten(
       |    list: jArrayList[Any],
       |    map: jMap[_, _]
       |  ): jArrayList[Any] = {
       |    map.values.asScala.foreach {
       |      case map: jMap[_, _] => flatten(list, map)
       |      case v               => list.add(v)
       |    }
       |    list
       |  }
       |
       |  final private def resolve(
       |    arity: Int,
       |    cast: java.util.Iterator[_] => Any
       |  ): jIterator[_] => Option[Any] = {
       |    val list = new jArrayList[Any](arity)
       |    val handleMap = (optionalData: jMap[_, _]) => {
       |      list.clear()
       |      Some(cast(flatten(list, optionalData).iterator()))
       |    }
       |    (it: jIterator[_]) =>
       |      try {
       |        it.next match {
       |          case map: jMap[_, _] => handleMap(map)
       |          case _               => None
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
         |  final private def pullLeaf$i(
         |    pullCasts: List[jIterator[_] => Any]
         |  ): jIterator[_] => Option[Any] = {
         |    val List($casters) = pullCasts
         |    resolve($i, (it: java.util.Iterator[_]) =>
         |      (
         |        $castings
         |      )
         |    )
         |  }""".stripMargin
  }
}
