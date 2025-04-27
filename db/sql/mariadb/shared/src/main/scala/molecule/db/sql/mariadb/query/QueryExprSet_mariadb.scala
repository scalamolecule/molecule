package molecule.db.sql.mariadb.query

import molecule.core.query.Model2Query
import molecule.db.sql.core.query.{LambdasSet, QueryExprSet, SqlQueryBase}


trait QueryExprSet_mariadb
  extends QueryExprSet
    with LambdasSet { self: Model2Query & SqlQueryBase =>

  // attr ----------------------------------------------------------------------

  override protected def setAttr[T](
    col: String, res: ResSet[T], mandatory: Boolean
  ): Unit = {
    if (mandatory) {
      select -= col
      selectWithOrder(col, res.tpeDb, "JSON_ARRAYAGG")
      groupByCols -= col
      having += "COUNT(*) > 0"
      aggregate = true
      mandatoryCast(res, mandatory)
    }
  }

  override protected def setOptAttr[T](col: String, res: ResSet[T]): Unit = {
    select -= col
    selectWithOrder(col, res.tpeDb, "JSON_ARRAYAGG", "DISTINCT ", true)
    groupByCols -= col
    aggregate = true
    castStrategy.replace((row: RS, paramIndex: Int) =>
      res.json2optArray(row.getString(paramIndex)).map(_.toSet)
    )
  }

  override protected def setHas[T](
    col: String, set: Set[T], res: ResSet[T], one2json: T => String, mandatory: Boolean
  ): Unit = {
    def containsSet(set: Set[T]): String = {
      val jsonValues = set.map(one2json).mkString(", ")
      s"JSON_CONTAINS($col, JSON_ARRAY($jsonValues))"
    }
    if (mandatory) {
      select -= col
      // We need this to coalesce Sets
      selectWithOrder(col, res.tpeDb, "JSON_ARRAYAGG", optional = true)
      groupByCols -= col
      aggregate = true
      mandatoryCast(res, mandatory)
    }
    set.size match {
      case 0 => where += (("FALSE", ""))
      case 1 => where += (("", s"JSON_CONTAINS($col, JSON_ARRAY(${one2json(set.head)}))"))
      case _ => where += (("", set.map(v => containsSet(Set(v))).mkString("(", " OR\n   ", ")")))
    }
  }

  override protected def setHasNo[T](
    col: String, set: Set[T], res: ResSet[T], one2json: T => String, mandatory: Boolean
  ): Unit = {
    def notContains(v: T): String = s"NOT JSON_CONTAINS($col, JSON_ARRAY(${one2json(v)}))"
    def notContainsSet(set: Set[T]): String = {
      val jsonValues = set.map(one2json).mkString(", ")
      s"NOT JSON_CONTAINS($col, JSON_ARRAY($jsonValues))"
    }
    if (mandatory) {
      // We need this to coalesce Sets
      select -= col
      selectWithOrder(col, res.tpeDb, "JSON_ARRAYAGG", optional = true)
      groupByCols -= col
      aggregate = true
      mandatoryCast(res, mandatory)
    }
    set.size match {
      case 0 => ()
      case 1 => where += (("", notContains(set.head)))
      case _ => where += (("", set.map(v => notContainsSet(Set(v))).mkString("(", " AND\n   ", ")")))
    }
  }


  // filter attribute ----------------------------------------------------------

  override protected def setFilterHas[T](
    col: String, filterAttr: String, res: ResSet[T], mandatory: Boolean
  ): Unit = {
    where += (("", hasClause(col, filterAttr, res)))
    mandatoryCast(res, mandatory)
  }

  override protected def setFilterHasNo[T](
    col: String, filterAttr: String, res: ResSet[T], mandatory: Boolean
  ): Unit = {
    if (mandatory) {
      val i = getIndex
      select -= col
      select += s"JSON_ARRAYAGG(t_$i.vs)"
      having += "COUNT(*) > 0"
      aggregate = true
      groupByCols -= col
      val tpeDb = res.tpeDb
      tempTables += s"JSON_TABLE($col, '$$[*]' COLUMNS (vs $tpeDb PATH '$$')) t_$i"
      mandatoryCast(res, true)
    }
    where += (("", s"NOT ${hasClause(col, filterAttr, res)}"))
  }


  // helpers -------------------------------------------------------------------

  private def selectWithOrder(
    col: String,
    tpeDb: String,
    fn: String,
    distinct: String = "",
    optional: Boolean = false
  ): Unit = {
    val i  = getIndex
    val vs = s"t_$i.vs"
    if (orderBy.nonEmpty && orderBy.last._3 == col) {
      // order by aggregate alias instead
      val alias = col.replace('.', '_') + "_" + fn.toLowerCase
      select += s"$fn($distinct$vs) $alias"
      val (level, _, _, dir) = orderBy.last
      orderBy.remove(orderBy.size - 1)
      orderBy += ((level, 1, alias, dir))
    } else {
      select += s"$fn($distinct$vs)"
    }
    if (optional) {
      joins += ((
        "LEFT OUTER JOIN",
        s"JSON_TABLE($col, '$$[*]' COLUMNS (vs $tpeDb PATH '$$')) t_$i",
        "",
        List("true")
      ))

    } else {
      tempTables +=
        s"""JSON_TABLE(
           |    IF($col IS NULL, '[null]', $col),
           |    '$$[*]' COLUMNS (vs $tpeDb PATH '$$')
           |  ) t_$i""".stripMargin
    }
  }

  private def mandatoryCast[T](res: ResSet[T], mandatory: Boolean): Unit = {
    if (mandatory) {
      castStrategy.replace((row: RS, paramIndex: Int) =>
        res.json2array(row.getString(paramIndex)).toSet
      )
    }
  }

  private def hasClause[T](col: String, filterAttr: String, res: ResSet[T]): String = {
    res.tpe match {
      case "BigInt" =>
        s"JSON_CONTAINS($col, JSON_ARRAY(CAST($filterAttr AS CHAR)))"

      case "BigDecimal" =>
        // Compare Decimals, not Strings.
        // String representation of filterAttr pads 0's so we can't use that
        // against the truncated String representations in the json array
        s"""(
           |    SELECT count(_v) > 0
           |    FROM
           |      JSON_TABLE(
           |        $col, '$$[*]'
           |        COLUMNS(_v varchar(65) path '$$')
           |      ) AS alias
           |    WHERE CONVERT(_v, DECIMAL(65, 38)) = $filterAttr
           |  )"""

      case _ => s"JSON_CONTAINS($col, JSON_ARRAY($filterAttr))"
    }
  }
}