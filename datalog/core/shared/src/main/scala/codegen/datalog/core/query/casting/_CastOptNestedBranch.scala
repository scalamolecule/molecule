package codegen.datalog.core.query.casting

import codegen.DatomicGenBase

object _CastOptNestedBranch extends DatomicGenBase("CastOptNestedBranch", "/query/casting") {

  val content = {
    val pullBranchX    = (1 to 21).map(i => s"case ${caseN(i)} => pullBranch$i(pullCasts, optComparator, refDepth)").mkString("\n      ")
    val resolveMethods = (1 to 21).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.datomic.query.casting
       |
       |import java.util.{Collections, Comparator, ArrayList => jArrayList, Iterator => jIterator, List => jList, Map => jMap}
       |import molecule.core.query.Model2Query
       |import molecule.datomic.query.DatomicQueryBase
       |import scala.annotation.tailrec
       |
       |
       |trait $fileName_[Tpl] { self: Model2Query with DatomicQueryBase =>
       |
       |  @tailrec
       |  final private def resolveArities(
       |    arities: List[List[Int]],
       |    casts: List[jIterator[_] => Any],
       |    pullNested: jIterator[_] => List[Any],
       |    acc: List[jIterator[_] => Any],
       |  ): List[jIterator[_] => Any] = {
       |    arities match {
       |      case List(1) :: as =>
       |        resolveArities(as, casts.tail, pullNested, acc :+ casts.head)
       |
       |      // Nested
       |      case List(-1) :: Nil =>
       |        resolveArities(Nil, Nil, pullNested, acc :+ pullNested)
       |
       |      case _ => acc
       |    }
       |  }
       |
       |  final protected def pullBranch(
       |    arities: List[List[Int]],
       |    pullCasts0: List[jIterator[_] => Any],
       |    pullSorts: List[Int => (Row, Row) => Int],
       |    pullNested: jIterator[_] => List[Any],
       |    refDepth: Int
       |  ): jIterator[_] => List[Any] = {
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
       |    val pullCasts     = resolveArities(arities, pullCasts0, pullNested, Nil)
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
       |    handleMaps: jList[_] => List[Any]
       |  ): jIterator[_] => List[Any] = {
       |    (it: jIterator[_]) =>
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
         |    pullCasts: List[jIterator[_] => Any],
         |    optComparator: Option[Comparator[Row]],
         |    refDepth: Int
         |  ): jIterator[_] => List[Any] = {
         |    val List($casters) = pullCasts
         |    val cast = (it: jIterator[_]) =>
         |      (
         |        $castings
         |      )
         |    resolve(
         |      optComparator.fold {
         |        val list = new jArrayList[Any]($i)
         |        (rows: jList[_]) =>
         |          rows.asScala.toList.map {
         |            case row: jMap[_, _] =>
         |              list.clear()
         |              cast(flatten(list, row, refDepth, 0).iterator)
         |          }
         |      } { comparator =>
         |        (rows: jList[_]) =>
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
