package molecule.sql.mariadb.query

import molecule.sql.core.query.{LambdasMap, QueryExprMap, SqlQueryBase}


trait QueryExprMap_mariadb
  extends QueryExprMap
    with LambdasMap { self: SqlQueryBase =>

  // value lookup by key -------------------------------------------------------

  override protected def key2value[T](
    col: String, key: String, resMap: ResMap[T]
  ): Unit = {
    val value = s"JSON_VALUE($col, '$$.$key')"
    select -= col
    select += value
    where += ((value, s"IS NOT NULL"))
    replaceCast((row: RS, paramIndex: Int) =>
      resMap.json2tpe(row.getString(paramIndex)))
  }

  override protected def key2optValue[T](
    col: String, key: String, resMap: ResMap[T]
  ): Unit = {
    select -= col
    select += s"JSON_VALUE($col, '$$.$key')"
    replaceCast((row: RS, paramIndex: Int) => {
      val value = row.getString(paramIndex)
      if (row.wasNull()) Option.empty[T] else Some(resMap.json2tpe(value))
    })
  }


  // tacit ---------------------------------------------------------------------

  override protected def mapContainsKeys(
    col: String, keys: Seq[String]
  ): Unit = {
    keys.size match {
      case 0 => where += (("FALSE", ""))
      case 1 => where += ((s"""JSON_VALUE($col, '$$.${keys.head}')""", s"IS NOT NULL"))
      case _ => where += (("", keys.map(key =>
        s"""JSON_VALUE($col, '$$.$key') IS NOT NULL"""
      ).mkString("(", " OR\n   ", ")")))
    }
  }

  override protected def mapContainsNoKeys(
    col: String, keys: Seq[String]
  ): Unit = {
    keys.size match {
      case 0 => () // get all
      case 1 => where += (("", s"JSON_VALUE($col, '$$.${keys.head}') IS NULL"))
      case _ => where += (("", keys.map(key =>
        s"""JSON_VALUE($col, '$$.$key') IS NULL"""
      ).mkString("(", " AND\n   ", ")")))
    }
  }

  override protected def mapHasValues[T](
    col: String, values: Seq[T], resMap: ResMap[T]
  ): Unit = {
    if (values.nonEmpty) {
      val values1 = values.map(resMap.one2json)
      where += (("", s"""$col REGEXP '${regex(resMap.tpe, values1)}' = 1"""))
    } else {
      // Get none
      where += (("FALSE", ""))
    }
  }

  override protected def mapHasNoValues[T](
    col: String, values: Seq[T], resMap: ResMap[T]
  ): Unit = {
    if (values.nonEmpty) {
      val values1 = values.map(resMap.one2json)
      where += (("", s"""$col REGEXP '${regex(resMap.tpe, values1)}' = 0"""))
    } else {
      // Get all
      ()
    }
  }


  // helpers -------------------------------------------------------------------

  private def regex[T](tpe: String, values: Iterable[T]): String = {
    lazy val field = """"[^"]+""""
    tpe match {
      case "String" => values.mkString(s"$field: ", s"|$field: ", "")

      case "OffsetTime" | "OffsetDateTime" | "ZonedDateTime" =>
        values.asInstanceOf[Iterable[String]].map(_.replace("+", "\\\\+?")).mkString("|: ")

      case _ => values.mkString(": ", "|: ", "")
    }
  }
}