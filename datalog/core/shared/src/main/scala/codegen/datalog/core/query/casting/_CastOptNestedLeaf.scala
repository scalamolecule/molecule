package codegen.datalog.core.query.casting

import codegen.DatomicGenBase

object _CastOptNestedLeaf extends DatomicGenBase("CastOptNestedLeaf", "/query/casting") {

  val content = {
    val pullLeafX    = (1 to 22).map(i => s"case ${caseN(i)} => pullLeaf$i(pullCasts, optComparator)").mkString("\n      ")
    val resolveMethods = (2 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.datalog.core.query.casting
       |
       |import java.util.{Collections, Comparator, ArrayList => jArrayList, Iterator => jIterator, List => jList, Map => jMap}
       |import molecule.core.query.Model2QueryBase
       |import molecule.datalog.core.query.DatomicQueryBase
       |import scala.annotation.tailrec
       |import scala.collection.mutable.ListBuffer
       |
       |
       |trait $fileName_
       |  extends CastRow2Tpl_ with CastIt2Tpl_ { self: Model2QueryBase with DatomicQueryBase =>
       |
       |  val rowList = new ListBuffer[Any]
       |
       |  @tailrec
       |  final private def resolveArities(
       |    arities: List[List[Int]],
       |    casts: List[jIterator[_] => Any],
       |    acc: List[jIterator[_] => Any],
       |  ): List[jIterator[_] => Any] = {
       |    arities match {
       |      case List(1) :: as =>
       |        resolveArities(as, casts.tail, acc :+ casts.head)
       |
       |      // Nested
       |      case List(-1) :: Nil =>
       |        resolveArities(Nil, casts.tail, acc :+ casts.head)
       |
       |      case _ => acc
       |    }
       |  }
       |
       |
       |  final protected def pullLeaf(
       |    arities: List[List[Int]],
       |    pullCasts0: List[jIterator[_] => Any],
       |    pullSorts: List[Int => (Row, Row) => Int]
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
       |    val pullCasts     = resolveArities(arities, pullCasts0, Nil)
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
       |    optComparator: Option[Comparator[Row]],
       |    cast: java.util.Iterator[_] => Any,
       |  ): jIterator[_] => List[Any] = {
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
       |    cast: java.util.Iterator[_] => Any
       |  ): jList[_] => List[Any] = {
       |    val list = new jArrayList[Any](arity)
       |    (rows: jList[_]) =>
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
       |    cast: java.util.Iterator[_] => Any,
       |    comparator: Comparator[Row]
       |  ): jList[_] => List[Any] = {
       |    (rows: jList[_]) =>
       |      val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
       |      rows.asScala.foreach {
       |        case row: jMap[_, _] =>
       |          val list = new jArrayList[Any](arity)
       |          sortedRows.add(flatten(list, row).asInstanceOf[Row])
       |      }
       |      Collections.sort(sortedRows, comparator)
       |      //      println("sortedRows: " + sortedRows)
       |      val tupleList = sortedRows.asScala.flatMap { row =>
       |        //        println("row: " + row)
       |        try {
       |          Some(cast(row.iterator))
       |        } catch {
       |          case _: NullValueException => None
       |          case NonFatal(e)           => throw e
       |        }
       |      }.toList
       |      //      println("tupleList: " + tupleList)
       |      tupleList
       |  }
       |
       |  final private def resolveNested(
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
       |
       |
       |  final private def pullLeaf1(
       |    pullCasts: List[jIterator[_] => Any],
       |    optComparator: Option[Comparator[Row]]
       |  ): jIterator[_] => List[Any] = {
       |    val List(c1) = pullCasts
       |    val cast     = (it: java.util.Iterator[_]) => c1(it)
       |    resolveNested(
       |      optComparator.fold {
       |        val list = new jArrayList[Any](1)
       |        (rows: jList[_]) =>
       |          val isSets = optNestedLeafIsSet.getOrElse {
       |            val rowsIt = rows.iterator()
       |            var isSet  = false
       |            var search = true
       |            while (search && rowsIt.hasNext) {
       |              val rowIt = rowsIt.next.asInstanceOf[jMap[_, _]].values().iterator()
       |              while (search && rowIt.hasNext) {
       |                rowIt.next match {
       |                  case "__none__" =>
       |                    ()
       |
       |                  case value: jMap[_, _] if value.values().iterator().next.isInstanceOf[jList[_]] =>
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
       |                  case _: NullValueException =>
       |                    ()
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
       |                  case _: NullValueException =>
       |                    ()
       |                }
       |            }
       |            rowList.toList
       |          }
       |
       |      } { comparator =>
       |        (rows: jList[_]) =>
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
         |    pullCasts: List[jIterator[_] => Any],
         |    optComparator: Option[Comparator[Row]]
         |  ): jIterator[_] => List[Any] = {
         |    val List($casters) = pullCasts
         |    val cast = (it: java.util.Iterator[_]) =>
         |      (
         |        $castings
         |      )
         |    resolve($i, optComparator, cast)
         |  }""".stripMargin
  }
}
