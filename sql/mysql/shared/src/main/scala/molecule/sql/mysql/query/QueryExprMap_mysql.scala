package molecule.sql.mysql.query

import molecule.core.query.Model2Query
import molecule.sql.core.query.{QueryExprMap, SqlQueryBase}


trait QueryExprMap_mysql
  extends QueryExprMap
    with LambdasMap_mysql { self: Model2Query with SqlQueryBase =>

  // value lookup by key -------------------------------------------------------

  override protected def mapManKey2value[T](
    col: String, keys: Seq[String], resMap: ResMap[T]
  ): Unit = {
    if (keys.nonEmpty) {
      val key = keys.head
      val value = s"JSON_VALUE($col, '$$.$key')"
      select -= col
      select += value
      where += ((value, s"IS NOT NULL"))
      castStrategy.replace((row: RS, paramIndex: Int) =>
        resMap.json2tpe(row.getString(paramIndex))
      )
    } else {
      noCollectionMatching(col)
    }
  }

  override protected def key2optValue[T](
    col: String, key: String, resMap: ResMap[T]
  ): Unit = {
    select -= col
    select += s"JSON_VALUE($col, '$$.$key')"
    castStrategy.replace((row: RS, paramIndex: Int) => {
      val value = row.getString(paramIndex)
      if (row.wasNull()) Option.empty[T] else Some(resMap.json2tpe(value))
    })
  }


  // mandatory -----------------------------------------------------------------

  override protected def mapManHasValues[T](
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

  override protected def mapManHasNoValues[T](
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


  // tacit ---------------------------------------------------------------------

  override protected def mapTacKeys(
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

  override protected def mapTacNoKeys(
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

  override protected def mapTacHasValues[T](
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

  override protected def mapTacHasNoValues[T](
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