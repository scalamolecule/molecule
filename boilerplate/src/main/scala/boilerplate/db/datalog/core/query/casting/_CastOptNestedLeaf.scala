package boilerplate.db.datalog.core.query.casting

import boilerplate.db.datalog.DbDatalogBase

object _CastOptNestedLeaf extends DbDatalogBase("CastOptNestedLeaf", "/query/casting") {

  val content = {
    val pullLeafX      = (1 to 22).map(i => s"case ${caseN(i)} => pullLeaf$i(pullCasts, optComparator)").mkString("\n      ")
    val resolveMethods = (2 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.datalog.core.query.casting
       |
       |import java.util.{Collections, Comparator, ArrayList as jArrayList, Iterator as jIterator, List as jList, Map as jMap}
       |import molecule.db.datalog.core.query.DatomicQueryBase
       |import scala.collection.mutable.ListBuffer
       |import scala.util.control.NonFatal
       |
       |
       |trait $fileName_ { self: DatomicQueryBase =>
       |
       |  private val rowList = new ListBuffer[Any]
       |
       |  final protected def pullOptNestedLeaf(
       |    pullCasts: List[jIterator[?] => Any],
       |    pullSorts: List[Int => (Row, Row) => Int]
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
       |
       |    pullCasts.length match {
       |      $pullLeafX
       |    }
       |  }
       |
       |  final private def flatten(
       |    list: jArrayList[Any],
       |    map: jMap[?, ?]
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
       |    optComparator: Option[Comparator[Row]],
       |    cast: java.util.Iterator[?] => Any,
       |  ): jIterator[?] => List[Any] = {
       |    val handleMaps = optComparator.fold(
       |      handleRows(arity, cast)
       |    )(comparator =>
       |      handleRowsSorted(arity, cast, comparator)
       |    )
       |    resolveNested(handleMaps)
       |  }
       |
       |  private def handleRows(
       |    arity: Int,
       |    cast: java.util.Iterator[?] => Any
       |  ): jList[?] => List[Any] = {
       |    val list = new jArrayList[Any](arity)
       |    (rows: jList[?]) =>
       |      rowList.clear()
       |      rows.asScala.toList.foreach {
       |        case row: jMap[_, _] =>
       |          list.clear()
       |          try {
       |            rowList += cast(flatten(list, row).iterator)
       |          } catch {
       |            case _: NullValueException => ()
       |          }
       |      }
       |      rowList.toList
       |  }
       |
       |  private def handleRowsSorted(
       |    arity: Int,
       |    cast: java.util.Iterator[?] => Any,
       |    comparator: Comparator[Row]
       |  ): jList[?] => List[Any] = {
       |    (rows: jList[?]) =>
       |      val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
       |      rows.asScala.foreach {
       |        case row: jMap[_, _] =>
       |          val list = new jArrayList[Any](arity)
       |          sortedRows.add(flatten(list, row).asInstanceOf[Row])
       |      }
       |      Collections.sort(sortedRows, comparator)
       |      val tupleList = sortedRows.asScala.flatMap { row =>
       |        try {
       |          Some(cast(row.iterator))
       |        } catch {
       |          case _: NullValueException => None
       |          case NonFatal(e)           => throw e
       |        }
       |      }.toList
       |      tupleList
       |  }
       |
       |  final private def resolveNested(
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
       |
       |
       |  final private def pullLeaf1(
       |    pullCasts: List[jIterator[?] => Any],
       |    optComparator: Option[Comparator[Row]]
       |  ): jIterator[?] => List[Any] = {
       |    val List(c1) = pullCasts
       |    val cast     = (it: java.util.Iterator[?]) => c1(it)
       |    resolveNested(
       |      optComparator.fold {
       |        val list = new jArrayList[Any](1)
       |        (rows: jList[?]) =>
       |          val isSets = optNestedLeafIsSet.getOrElse {
       |            val rowsIt = rows.iterator()
       |            var isSet  = false
       |            var search = true
       |            while (search && rowsIt.hasNext) {
       |              val rowIt = rowsIt.next.asInstanceOf[jMap[?, ?]].values().iterator()
       |              while (search && rowIt.hasNext) {
       |                rowIt.next match {
       |                  case "__none__" => ()
       |
       |                  case value: jMap[_, _] if value.values().iterator().next.isInstanceOf[jList[?]] =>
       |                    isSet = true
       |                    search = false
       |                    optNestedLeafIsSet = Some(true)
       |
       |                  case _ =>
       |                    search = false
       |                    optNestedLeafIsSet = Some(false)
       |                }
       |              }
       |            }
       |            isSet
       |          }
       |
       |          rowList.clear()
       |          if (isSets) {
       |            rows.asScala.toList.foreach {
       |              case row: jMap[_, _] =>
       |                list.clear()
       |                try {
       |                  rowList += cast(flatten(list, row).iterator)
       |                } catch {
       |                  case _: NullValueException => ()
       |                }
       |            }
       |            // Coalesce Sets
       |            var coalescedSet = Set.empty[Any]
       |            rowList.foreach {
       |              case set: Set[_] =>
       |                coalescedSet = coalescedSet ++ set
       |            }
       |            if (coalescedSet.isEmpty) Nil else List(coalescedSet)
       |
       |          } else {
       |            rows.asScala.toList.foreach {
       |              case row: jMap[_, _] =>
       |                list.clear()
       |                try {
       |                  rowList += cast(flatten(list, row).iterator)
       |                } catch {
       |                  case _: NullValueException => ()
       |                }
       |            }
       |            rowList.toList
       |          }
       |
       |      } { comparator =>
       |        (rows: jList[?]) =>
       |          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
       |          rows.asScala.foreach {
       |            case row: jMap[_, _] =>
       |              val list = new jArrayList[Any](1)
       |              sortedRows.add(flatten(list, row).asInstanceOf[Row])
       |          }
       |          Collections.sort(sortedRows, comparator)
       |          sortedRows.asScala.map { row => cast(row.iterator) }.toList
       |      }
       |    )
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
         |    pullCasts: List[jIterator[?] => Any],
         |    optComparator: Option[Comparator[Row]]
         |  ): jIterator[?] => List[Any] = {
         |    val List($casters) = pullCasts
         |    val cast = (it: java.util.Iterator[?]) =>
         |      (
         |        $castings
         |      )
         |    resolve($i, optComparator, cast)
         |  }""".stripMargin
  }
}
