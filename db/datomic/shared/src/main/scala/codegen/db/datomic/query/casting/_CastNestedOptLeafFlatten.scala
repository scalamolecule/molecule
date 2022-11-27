package codegen.db.datomic.query.casting

import codegen.DatomicGenBase

object _CastNestedOptLeafFlatten extends DatomicGenBase("CastNestedOptLeafFlatten", "/query/casting") {

  val content = {
    val pullLeafFlattenX = (1 to 22).map(i => s"case $i => pullLeafFlatten$i(pullCasts)").mkString("\n      ")
    val resolveMethods   = (1 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.datomic.query.casting
       |
       |import java.util.{ArrayList => jArrayList, Iterator => jIterator, List => jList, Map => jMap}
       |import molecule.core.query.Model2Query
       |import molecule.db.datomic.query.Base
       |
       |
       |trait ${fileName}_[Tpl] { self: Model2Query[Tpl] with Base[Tpl] =>
       |
       |  final protected def pullLeafFlatten(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
       |    pullCasts.length match {
       |      $pullLeafFlattenX
       |    }
       |  }
       |
       |  final private def flatten(list: jArrayList[Any], map: jMap[_, _]): jArrayList[Any] = {
       |    map.values.forEach {
       |      case map: jMap[_, _] => flatten(list, map)
       |      case v               => list.add(v)
       |    }
       |    list
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val casters  = (0 until i).map { j => s"val c$j = pullCasts($j)" }.mkString("\n    ")
    val castings = (0 until i).map { j => s"c$j(it)" }.mkString(",\n                ")
    val body     =
      s"""
         |  final private def pullLeafFlatten$i(pullCasts: List[jIterator[_] => Any]): jIterator[_] => List[Any] = {
         |    $casters
         |    val list = new jArrayList[Any]($i)
         |    (it: jIterator[_]) =>
         |      try {
         |        it.next match {
         |          case vs: jList[_] => vs.asScala.toList.map {
         |            case map: jMap[_, _] =>
         |              list.clear()
         |              val it = flatten(list, map).iterator
         |              (
         |                $castings
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
