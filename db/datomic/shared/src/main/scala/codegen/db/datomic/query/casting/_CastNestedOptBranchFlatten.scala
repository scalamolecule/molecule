package codegen.db.datomic.query.casting

import codegen.DatomicGenBase

object _CastNestedOptBranchFlatten
  extends DatomicGenBase("CastNestedOptBranchFlatten", "/query/casting") {

  val content = {
    val pullBranchX    = (1 to 21).map(i =>
      s"case $i => pullBranch$i(pullCasts, pullLeaf, levelIndex)"
    ).mkString("\n      ")
    val resolveMethods = (1 to 21).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.datomic.query.casting
       |
       |import java.util.{ArrayList => jArrayList, Iterator => jIterator, List => jList, Map => jMap}
       |import molecule.core.query.Model2Query
       |import molecule.db.datomic.query.Base
       |
       |
       |trait ${fileName}_[Tpl] {
       |  self: Model2Query[Tpl] with Base[Tpl] =>
       |
       |  final protected def pullBranchFlatten(
       |    pullCasts: List[jIterator[_] => Any],
       |    pullLeaf: jIterator[_] => List[Any],
       |    levelIndex: Int
       |  ): jIterator[_] => List[Any] = {
       |    pullCasts.length match {
       |      $pullBranchX
       |    }
       |  }
       |
       |  final private def flatten(
       |    list: jArrayList[Any],
       |    map: jMap[_, _],
       |    max: Int,
       |    cur: Int
       |  ): jArrayList[Any] = {
       |    map.values.forEach {
       |      case map: jMap[_, _] if cur == max => list.add(map)
       |      case map: jMap[_, _]               => flatten(list, map, max, cur + 1)
       |      case v                             => list.add(v)
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
         |  final private def pullBranch$i(
         |    pullCasts: List[jIterator[_] => Any],
         |    pullLeaf: jIterator[_] => List[Any],
         |    levelIndex: Int
         |  ): jIterator[_] => List[Any] = {
         |    $casters
         |    val list = new jArrayList[Any](${i + 1})
         |    (it: jIterator[_]) =>
         |      try {
         |        it.next match {
         |          case vs: jList[_] => vs.asScala.toList.map {
         |            case map: jMap[_, _] =>
         |              list.clear()
         |              val it = flatten(list, map, pullDepths(levelIndex), 0).iterator
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
