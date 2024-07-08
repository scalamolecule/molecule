package molecule.sql.postgres.query

import molecule.sql.core.query.{QueryExprSetRefAttr, SqlQueryBase}

trait QueryExprSetRefAttr_postgres
  extends QueryExprSetRefAttr
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


  override protected def setRefEqual[T](set: Set[T], res: ResSet[T]): Unit = {
    where += (("", refMatchArray((res.set2sqlArray(set), set.size))))
  }

  override protected def setRefNeq[T](set: Set[T], res: ResSet[T]): Unit = {
    where += (("", "NOT (" + refMatchArray((res.set2sqlArray(set), set.size)) + ")"))
  }

  override protected def refHas[T](set: Set[T]): Unit = {
    set.size match {
      case 0 => where += (("FALSE", ""))
      case 1 => where += (("", arrayMatches(s"  ${set.head} = ANY(ARRAY_AGG($joinTable.$ref_id))")))
      case _ =>
        val arrayContains = set.map(v =>
          s"ARRAY[$v]::bigint[] <@ ARRAY_AGG($joinTable.$ref_id)"
        ).mkString(" OR\n      ")
        where += (("", arrayMatches(arrayContains)))
    }
  }

  override protected def refHasNo[T](set: Set[T]): Unit = {
    set.size match {
      case 0 => ()
      case 1 => where += (("", arrayMatches(s"  ${set.head} != ALL(ARRAY_AGG($joinTable.$ref_id))")))
      case _ =>
        val arrayContains = set.map(v =>
          s"NOT (ARRAY[$v]::bigint[] <@ ARRAY_AGG($joinTable.$ref_id))"
        ).mkString(" AND\n      ")
        where += (("", arrayMatches(arrayContains)))
    }
  }
}