package codegen.datalog.core.query.casting

import codegen.DatomicGenBase

object _CastNestedOptLeaf extends DatomicGenBase("CastNestedOptLeaf", "/query/casting") {

  val content = {
    val pullLeafX    = (1 to 22).map(i => s"case ${caseN(i)} => pullLeaf$i(pullCasts, optComparator)").mkString("\n      ")
    val resolveMethods = (2 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.datomic.query.casting
       |
       |import java.util.{Collections, Comparator, ArrayList => jArrayList, Iterator => jIterator, List => jList, Map => jMap}
       |import molecule.core.query.Model2Query
       |import molecule.datomic.query.DatomicQueryBase
       |import scala.annotation.tailrec
       |
       |
       |trait $fileName_[Tpl]
       |  extends CastRow2Tpl_ with CastIt2Tpl_ { self: Model2Query with DatomicQueryBase =>
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
       |  final private def pullLeaf1(
       |    pullCasts: List[jIterator[_] => Any],
       |    optComparator: Option[Comparator[Row]]
       |  ): jIterator[_] => List[Any] = {
       |
       |    val List(c1) = pullCasts
       |    val cast     = (it: java.util.Iterator[_]) => c1(it)
       |    resolve(
       |      optComparator.fold {
       |        val list = new jArrayList[Any](1)
       |        (rows: jList[_]) =>
       |          val isSets = flatten(list, rows.get(0).asInstanceOf[jMap[_, _]])
       |            .get(0).isInstanceOf[clojure.lang.PersistentVector]
       |
       |          if (isSets) {
       |            val res          = rows.asScala.toList.map {
       |              case row: jMap[_, _] =>
       |                list.clear()
       |                cast(flatten(list, row).iterator)
       |            }
       |            // Coalesce Sets
       |            var coalescedSet = Set.empty[Any]
       |            res.foreach {
       |              case set: Set[Any] => coalescedSet = coalescedSet ++ set
       |            }
       |            List(coalescedSet)
       |
       |          } else {
       |            rows.asScala.toList.map {
       |              case row: jMap[_, _] =>
       |                list.clear()
       |                cast(flatten(list, row).iterator)
       |            }
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
         |    resolve(
         |      optComparator.fold {
         |        val list = new jArrayList[Any]($i)
         |        (rows: jList[_]) =>
         |          rows.asScala.toList.map {
         |            case row: jMap[_, _] =>
         |              list.clear()
         |              cast(flatten(list, row).iterator)
         |          }
         |      } { comparator =>
         |        (rows: jList[_]) =>
         |          val sortedRows: jArrayList[Row] = new jArrayList(rows.size())
         |          rows.asScala.foreach {
         |            case row: jMap[_, _] =>
         |              val list = new jArrayList[Any]($i)
         |              sortedRows.add(flatten(list, row).asInstanceOf[Row])
         |          }
         |          Collections.sort(sortedRows, comparator)
         |          sortedRows.asScala.map { row => cast(row.iterator) }.toList
         |      }
         |    )
         |  }""".stripMargin
  }
}
