package molecule.sql.postgres.query

import molecule.sql.core.query.{ResolveExprSetRefAttr, SqlQueryBase}
import scala.reflect.ClassTag

trait ResolveExprSetRefAttr_postgres
  extends ResolveExprSetRefAttr
    with LambdasSet_postgres { self: SqlQueryBase =>

  private def refMatchArray(sqlArray: (String, Int)): String = {
    s"""(
       |    SELECT
       |      ${sqlArray._1} <@ ARRAY_AGG($joinTable.$ref_id) AND
       |      CARDINALITY(ARRAY_AGG($joinTable.$ref_id)) = ${sqlArray._2}
       |    FROM $joinTable
       |    WHERE $joinTable.$ns_id = $nsId
       |  )""".stripMargin
  }

  private def refMatchArrays[T](sets: Seq[Set[T]], set2sqlArray: Set[T] => String): String = {
    sets.map(set => refMatchArray((set2sqlArray(set), set.size))).mkString("(\n    ", " OR\n    ", "\n  )")
  }

  override protected def refEqual[T](sets: Seq[Set[T]], res: ResSet[T]): Unit = {
    val setsNonEmpty = sets.filterNot(_.isEmpty)
    setsNonEmpty.length match {
      case 0 => where += (("FALSE", ""))
      case 1 => where += (("", refMatchArray((res.set2sqlArray(setsNonEmpty.head), setsNonEmpty.head.size))))
      case _ => where += (("", refMatchArrays(setsNonEmpty, res.set2sqlArray)))
    }
  }

  override protected def refNeq[T](sets: Seq[Set[T]], res: ResSet[T]): Unit = {
    val setsNonEmpty = sets.filterNot(_.isEmpty)
    setsNonEmpty.length match {
      case 0 => ()
      case 1 => where += (("", "NOT (" +
        refMatchArray((res.set2sqlArray(setsNonEmpty.head), setsNonEmpty.head.size)) +
        ")"))
      case _ => where += (("", "NOT (" +
        refMatchArrays(setsNonEmpty, res.set2sqlArray) +
        ")"))
    }
  }

  override protected def refHas[T: ClassTag](sets: Seq[Set[T]]): Unit = {
    sets.length match {
      case 0 => where += (("FALSE", ""))
      case 1 =>
        val set = sets.head
        set.size match {
          case 0 => where += (("FALSE", ""))
          case 1 => where += (("", arrayMatches(s"  ${set.head} = ANY(ARRAY_AGG($joinTable.$ref_id))")))
          case _ =>
            val arrayContains = s"ARRAY[${set.mkString(", ")}]::bigint[] <@ ARRAY_AGG($joinTable.$ref_id)"
            where += (("", arrayMatches(arrayContains)))
        }
      case _ =>
        val arrayContains = sets.filterNot(_.isEmpty).map { set =>
          s"ARRAY[${set.mkString(", ")}]::bigint[] <@ ARRAY_AGG($joinTable.$ref_id)"
        }.mkString(" OR\n      ")
        where += (("", arrayMatches(arrayContains)))
    }
  }

  override protected def refHasNo[T](sets: Seq[Set[T]]): Unit = {
    sets.length match {
      case 0 => ()
      case 1 =>
        val set = sets.head
        set.size match {
          case 0 => ()
          case 1 => where += (("", arrayMatches(s"  ${set.head} != ALL(ARRAY_AGG($joinTable.$ref_id))")))
          case _ =>
            val arrayContains = s"NOT (ARRAY[${set.mkString(", ")}]::bigint[] <@ ARRAY_AGG($joinTable.$ref_id))"
            where += (("", arrayMatches(arrayContains)))
        }
      case _ =>
        val arrayContains = sets.filterNot(_.isEmpty).map(set =>
          s"NOT (ARRAY[${set.mkString(", ")}]::bigint[] <@ ARRAY_AGG($joinTable.$ref_id))"
        ).mkString(" AND\n      ")
        where += (("", arrayMatches(arrayContains)))
    }
  }
}