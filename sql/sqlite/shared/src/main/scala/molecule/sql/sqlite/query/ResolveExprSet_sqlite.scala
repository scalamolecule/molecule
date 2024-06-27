package molecule.sql.sqlite.query

import molecule.sql.core.query.{ResolveExprSet, SqlQueryBase}

trait ResolveExprSet_sqlite
  extends ResolveExprSet
    with LambdasSet_sqlite { self: SqlQueryBase =>


  // attr ----------------------------------------------------------------------

  override protected def setAttr[T](
    col: String, res: ResSet[T], mandatory: Boolean
  ): Unit = {
    if (mandatory) {
      val attr     = col.replace('.', '_')
      val colTable = "_" + col.replace('.', '_')
      select -= col
      select += s"JSON_GROUP_ARRAY($colTable.VALUE) as $attr"

      // Allow empty mandatory value in optional nested structures
      val mode = if (isNestedOpt) "LEFT" else "INNER"
      joins += ((s"$mode JOIN", s"JSON_EACH($col)", colTable, "", ""))
      having += "COUNT(*) > 0"
      aggregate = true
      mandatoryCast(res, mandatory)
    }
  }

  override protected def setOptAttr[T](col: String, res: ResSet[T]): Unit = {
    val attr     = col.replace('.', '_')
    val colTable = "_" + col.replace('.', '_')
    select -= col
    select += s"JSON_GROUP_ARRAY($colTable.VALUE) as $attr"
    joins += (("LEFT JOIN", s"JSON_EACH($col)", colTable, "", ""))
    aggregate = true
    replaceCast((row: RS, paramIndex: Int) =>
      res.json2optArray(row.getString(paramIndex)).map(_.toSet)
    )
  }

  override protected def setHas[T](
    col: String, set: Set[T], res: ResSet[T], one2json: T => String, mandatory: Boolean
  ): Unit = {
    if (mandatory) {
      val attr     = col.replace('.', '_')
      val colTable = "_" + col.replace('.', '_')
      select -= col
      select += s"JSON_GROUP_ARRAY($colTable.VALUE) as $attr"

      // Allow empty mandatory value in optional nested structures
      val mode = if (isNestedOpt) "LEFT" else "INNER"
      joins += ((s"$mode JOIN", s"JSON_EACH($col)", colTable, "", ""))
      having += "COUNT(*) > 0"
      aggregate = true
      mandatoryCast(res, mandatory)
    }
    set.size match {
      case 0 => where += (("FALSE", ""))
      case 1 => where += (("",
        s"""EXISTS (
           |    SELECT *
           |    FROM JSON_EACH($col)
           |    WHERE JSON_EACH.VALUE = ${one2json(set.head)}
           |  )""".stripMargin
      ))
      case _ =>
        val values = set.map(one2json).mkString(", ")
        where += (("",
          s"""EXISTS (
             |    SELECT *
             |    FROM JSON_EACH($col)
             |    WHERE JSON_EACH.VALUE IN ($values)
             |  )""".stripMargin
      ))
    }
  }

  override protected def setHasNo[T](
    col: String, set: Set[T], res: ResSet[T], one2json: T => String, mandatory: Boolean
  ): Unit = {
    if (mandatory) {
      val attr     = col.replace('.', '_')
      val colTable = "_" + col.replace('.', '_')
      select -= col
      select += s"JSON_GROUP_ARRAY($colTable.VALUE) as $attr"

      // Allow empty mandatory value in optional nested structures
      val mode = if (isNestedOpt) "LEFT" else "INNER"
      joins += ((s"$mode JOIN", s"JSON_EACH($col)", colTable, "", ""))
      aggregate = true
      mandatoryCast(res, mandatory)
    }
    set.size match {
      case 0 => ()
      case 1 => where += (("",
        s"""NOT EXISTS (
           |    SELECT *
           |    FROM JSON_EACH($col)
           |    WHERE JSON_EACH.VALUE = ${one2json(set.head)}
           |  )""".stripMargin
      ))
      case _ =>
        val values = set.map(one2json).mkString(", ")
        where += (("",
          s"""NOT EXISTS (
             |    SELECT *
             |    FROM JSON_EACH($col)
             |    WHERE JSON_EACH.VALUE IN ($values)
             |  )""".stripMargin
        ))
    }
  }


  // filter attribute ----------------------------------------------------------

  override protected def setFilterHas[T](
    col: String, filterAttr: String, res: ResSet[T], mandatory: Boolean
  ): Unit = {
    where += (("",
      s"""EXISTS (
         |    SELECT *
         |    FROM JSON_EACH($col)
         |    WHERE JSON_EACH.VALUE = $filterAttr
         |  )""".stripMargin
    ))
    mandatoryCast(res, mandatory)
  }

  override protected def setFilterHasNo[T](
    col: String, filterAttr: String, res: ResSet[T], mandatory: Boolean
  ): Unit = {
    if (mandatory) {
      val attr     = col.replace('.', '_')
      val colTable = "_" + col.replace('.', '_')
      select -= col
      select += s"JSON_GROUP_ARRAY($colTable.VALUE) as $attr"

      // Allow empty mandatory value in optional nested structures
      val mode = if (isNestedOpt) "LEFT" else "INNER"
      joins += ((s"$mode JOIN", s"JSON_EACH($col)", colTable, "", ""))
      having += "COUNT(*) > 0"
      aggregate = true
      mandatoryCast(res, mandatory)
    }
    where += (("",
      s"""NOT EXISTS (
         |    SELECT *
         |    FROM JSON_EACH($col)
         |    WHERE JSON_EACH.VALUE = $filterAttr
         |  )""".stripMargin
    ))
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
        "true",
        ""
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
      replaceCast((row: RS, paramIndex: Int) =>
        res.json2array(row.getString(paramIndex)).toSet
      )
    }
  }
}