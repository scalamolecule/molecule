package molecule.sql.postgres.query

import molecule.sql.core.query.{ResolveExprMap, SqlQueryBase}


trait ResolveExprMap_postgres
  extends ResolveExprMap
    with LambdasMap_postgres { self: SqlQueryBase =>


  // value lookup by key -------------------------------------------------------

  override protected def key2value[T](
    col: String, key: String, resMap: ResMap[T]
  ): Unit = {
    select -= col
    select += s"$col ->> '$key'"
    where += (("", s"$col ?? '$key'"))
    replaceCast((row: RS, paramIndex: Int) =>
      resMap.json2tpe(row.getString(paramIndex)))
  }

  override protected def key2optValue[T](
    col: String, key: String, resMap: ResMap[T]
  ): Unit = {
    select -= col
    select += s"$col ->> '$key'"
    replaceCast((row: RS, paramIndex: Int) => {
      val value = row.getString(paramIndex)
      if (row.wasNull()) Option.empty[T] else Some(resMap.json2tpe(value))
    })
  }


  // tacit ---------------------------------------------------------------------

  override protected def mapContainsKeys(
    col: String, keys: Seq[String]
  ): Unit = {
    if (keys.nonEmpty) {
      where += (("", keys.map(k => s"$col ?? '$k'").mkString("(", " OR\n   ", ")")))
    } else {
      where += (("FALSE", ""))
    }
  }

  override protected def mapContainsNoKeys(
    col: String, keys: Seq[String]
  ): Unit = {
    if (keys.nonEmpty) {
      where += (("", keys.map(k => s"$col ?? '$k'").mkString("NOT (", " OR\n   ", ")")))
    } else {
      () // get all
    }
  }

  override protected def mapHasValues[T](
    col: String, values: Seq[T], resMap: ResMap[T]
  ): Unit = {
    if (values.nonEmpty) {
      val values1 = values.map(v =>
        s"JSONB_PATH_QUERY_ARRAY($col, '$$.*') @> '${resMap.one2json(v)}'"
      )
      where += (("", values1.mkString("(", " OR\n   ", ")")))
    } else {
      // Get none
      where += (("FALSE", ""))
    }
  }

  override protected def mapHasNoValues[T](
    col: String, values: Seq[T], resMap: ResMap[T]
  ): Unit = {
    if (values.nonEmpty) {
      val values1 = values.map(v =>
        s"JSONB_PATH_QUERY_ARRAY($col, '$$.*') @> '${resMap.one2json(v)}'"
      )
      where += (("", values1.mkString("NOT (", " OR\n   ", ")")))
    } else {
      // Get all
      ()
    }
  }

  override protected def mapNoValue(col: String): Unit = {
    where += ((col, s"IS NULL"))
  }

  override protected lazy val json2oneBoolean: String => Boolean = {
    (v: String) => v == "t" || v == "1"
  }
}