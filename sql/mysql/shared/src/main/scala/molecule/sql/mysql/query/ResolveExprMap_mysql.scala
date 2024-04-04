package molecule.sql.mysql.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.sql.core.query.{ResolveExprMap, ResolveExprSet, SqlQueryBase}
import scala.reflect.ClassTag
import scala.util.Random


trait ResolveExprMap_mysql
  extends ResolveExprMap
    with LambdasMap_mysql { self: SqlQueryBase =>


  override protected def mapMan[T](
    attr: Attr, map: Map[String, T], resMap: ResMap[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns
    if (!isNestedOpt) {
      notNull += col
    }
    addCast(resMap.sqlJson2map)
    attr.op match {
      case V       => ()
      case Has     => key2value(col, map.head._1, resMap)
      case NoValue => noApplyNothing(attr)
      case Eq      => noCollectionMatching(attr)
      case other   => unexpectedOp(other)
    }
  }

  override protected def mapOpt[T](
    attr: Attr,
    optMap: Option[Map[String, T]],
    resMapOpt: ResMapOpt[T],
    resMap: ResMap[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns
    addCast(resMapOpt.sql2optMap)
    attr.op match {
      case V     => ()
      case Has   => key2optValue(col, optMap.get.head._1, resMap)
      case Eq    => noCollectionMatching(attr)
      case other => unexpectedOp(other)
    }
  }

  override protected def mapTac[T](
    attr: Attr, map: Map[String, T], resMap: ResMap[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    attr.op match {
      case V       => where += ((col, s"IS NOT NULL"))
      case Eq      => mapContainsKeys(col, map, resMap)
      case Neq     => mapContainsNoKeys(col, map, resMap)
      case Has     => mapHasValues(col, map, resMap)
      case HasNo   => mapHasNoValues(col, map, resMap)
      case NoValue => mapNoValue(col)
      case other   => unexpectedOp(other)
    }
  }


  // value lookup by key -------------------------------------------------------

  private def key2value[T](col: String, key: String, resMap: ResMap[T]): Unit = {
    val value = s"JSON_VALUE($col, '$$.$key')"
    select -= col
    select += value
    where += ((value, s"IS NOT NULL"))
    replaceCast((row: RS, paramIndex: Int) => resMap.json2tpe(row.getString(paramIndex)))
  }

  private def key2optValue[T](
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

  private def mapContainsKeys[T](col: String, map: Map[String, T], resMap: ResMap[T]): Unit = {
    val keys = map.keys
    keys.size match {
      case 0 => where += (("FALSE", ""))
      case 1 => where += ((s"""JSON_VALUE($col, '$$.${keys.head}')""", s"IS NOT NULL"))
      case _ => where += (("", keys.map(key =>
        s"""JSON_VALUE($col, '$$.$key') IS NOT NULL"""
      ).mkString("(", " OR\n   ", ")")))
    }
  }

  private def mapContainsNoKeys[T](
    col: String, map: Map[String, T], resMap: ResMap[T]
  ): Unit = {
    val keys = map.keys
    keys.size match {
      case 0 => () // get all
      case 1 => where += (("", s"JSON_VALUE($col, '$$.${keys.head}') IS NULL"))
      case _ => where += (("", keys.map(key =>
        s"""JSON_VALUE($col, '$$.$key') IS NULL"""
      ).mkString("(", " AND\n   ", ")")))
    }
  }

  override protected def mapHasValues[T](
    col: String, map: Map[String, T], resMap: ResMap[T]
  ): Unit = {
    if (map.nonEmpty) {
      val values = map.values.map(resMap.one2json)
      where += (("", s"""$col REGEXP '${regex(resMap.tpe, values)}' = 1"""))
    } else {
      // Get none
      where += (("FALSE", ""))
    }
  }

  override protected def mapHasNoValues[T](
    col: String, map: Map[String, T], resMap: ResMap[T]
  ): Unit = {
    if (map.nonEmpty) {
      val values = map.values.map(resMap.one2json)
      where += (("", s"""$col REGEXP '${regex(resMap.tpe, values)}' = 0"""))
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