package codegen.datalog.core.query.casting

import codegen.datalog.DatomicGenBase

object _CastOptNestedBranch extends DatomicGenBase("CastOptNestedBranch", "/query/casting") {

  val content = {
    val pullBranchX    = (1 to 21).map(i => s"case ${caseN(i)} => pullBranch$i(pullCasts, optComparator, refDepth)").mkString("\n      ")
    val resolveMethods = (1 to 21).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.datalog.core.query.casting
       |
       |import java.util.{Collections, Comparator, ArrayList => jArrayList, Iterator => jIterator, List => jList, Map => jMap}
       |import molecule.db.datalog.core.query.DatomicQueryBase
       |
       |
       |trait $fileName_ { self: DatomicQueryBase =>
       |
       |  final protected def pullOptNestedBranch(
       |    pullCasts0: List[jIterator[?] => Any],
       |    pullSorts: List[Int => (Row, Row) => Int],
       |    pullNested: jIterator[?] => List[Any],
       |    refDepth: Int
       |  ): jIterator[?] => List[Any] = {
       |    val optComparator = {
       |      if (pullSorts.nonEmpty) {
       |        val n = pullSorts.length
       |        Some(
       |          new Comparator[Row] {
       |            override def compare(a: Row, b: Row): Int = {
       |              var i      = 0
       |              var result = 0
       |              result = pullSorts(i)(0)(a, b)
       |              i += 1
       |              while (result == 0 && i != n) {
       |                result = pullSorts(i)(0)(a, b)
       |                i += 1
       |              }
       |              result
       |            }
       |          }
       |        )
       |      } else None
       |    }
       |    val pullCasts     = pullCasts0 :+ pullNested
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
       |    handleMaps: jList[?] => List[Any]
       |  ): jIterator[?] => List[Any] = {
       |    (it: jIterator[?]) =>
       |      try {
       |        it.next match {
       |          case maps: jList[_] => handleMaps(maps)
       |          case _              => Nil
       |        }
       |      } catch {
       |        case _: NullValueException => Nil
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
         |    optComparator: Option[Comparator[Row]],
         |    refDepth: Int
         |  ): jIterator[?] => List[Any] = {
         |    val List($casters) = pullCasts
         |    val cast = (it: jIterator[?]) =>
         |      (
         |        $castings
         |      )
         |    resolve(
         |      optComparator.fold {
         |        val list = new jArrayList[Any]($i)
         |        (rows: jList[?]) =>
         |          rows.asScala.toList.map {
         |            case row: jMap[_, _] =>
         |              list.clear()
         |              cast(flatten(list, row, refDepth, 0).iterator)
         |          }
         |      } { comparator =>
         |        (rows: jList[?]) =>
         |          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
         |          rows.asScala.foreach {
         |            case row: jMap[_, _] =>
         |              val list = new jArrayList[Any]($i)
         |              sortedRows.add(flatten(list, row, refDepth, 0).asInstanceOf[Row])
         |          }
         |          Collections.sort(sortedRows, comparator)
         |          sortedRows.asScala.map { row => cast(row.iterator) }.toList
         |      }
         |    )
         |  }""".stripMargin
  }
}
