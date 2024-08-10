package codegen.datalog.core.query.casting

import codegen.DatomicGenBase

object _CastOptRefBranch extends DatomicGenBase("CastOptRefBranch", "/query/casting") {

  val content = {
    val pullBranchX    = (1 to 21).map(i => s"case ${caseN(i)} => pullBranch$i(pullCasts, refDepth)").mkString("\n      ")
    val resolveMethods = (1 to 21).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.datalog.core.query.casting
       |
       |import java.util.{ArrayList => jArrayList, Iterator => jIterator, Map => jMap}
       |import molecule.datalog.core.query.DatomicQueryBase
       |import scala.annotation.tailrec
       |
       |
       |trait $fileName_ { self: DatomicQueryBase =>
       |
       |  @tailrec
       |  final private def resolveArities(
       |    arities: List[Int],
       |    casts: List[jIterator[_] => Any],
       |    pullNested: jIterator[_] => Option[Any],
       |    acc: List[jIterator[_] => Any],
       |  ): List[jIterator[_] => Any] = {
       |    arities match {
       |      case 1 :: as =>
       |        resolveArities(as, casts.tail, pullNested, acc :+ casts.head)
       |
       |      // Nested
       |      case -1 :: Nil =>
       |        resolveArities(Nil, Nil, pullNested, acc :+ pullNested)
       |
       |      case _ => acc
       |    }
       |  }
       |
       |  final protected def pullOptRefBranch(
       |    arities: List[Int],
       |    pullCasts0: List[jIterator[_] => Any],
       |    pullNested: jIterator[_] => Option[Any],
       |    refDepth: Int
       |  ): jIterator[_] => Option[Any] = {
       |    val pullCasts = resolveArities(arities, pullCasts0, pullNested, Nil)
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
       |    cast: java.util.Iterator[_] => Any
       |  ): jIterator[_] => Option[Any] = {
       |    val list      = new jArrayList[Any](arity)
       |    val handleMap = (optionalData: jMap[_, _]) => {
       |      list.clear()
       |      Some(cast(flatten(list, optionalData, refDepth, 0).iterator()))
       |    }
       |    (it: jIterator[_]) =>
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
         |    pullCasts: List[jIterator[_] => Any],
         |    refDepth: Int
         |  ): jIterator[_] => Option[Any] = {
         |    val List($casters) = pullCasts
         |    resolve($i, refDepth, (it: java.util.Iterator[_]) =>
         |      (
         |        $castings
         |      )
         |    )
         |  }""".stripMargin
  }
}
