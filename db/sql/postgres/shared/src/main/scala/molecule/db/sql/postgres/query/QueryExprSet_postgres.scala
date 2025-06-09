package molecule.db.sql.postgres.query

import molecule.core.ast.*
import molecule.db.core.query.Model2Query
import molecule.db.sql.core.query.{QueryExprSet, SqlQueryBase}

trait QueryExprSet_postgres
  extends QueryExprSet
    with LambdasSet_postgres { self: Model2Query & SqlQueryBase =>


  override protected def setMan[T](
    attr: Attr, args: Set[T], res: ResSet[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns

    if (isOptNested) {
      castStrategy.add(res.sql2setOrNull)
    } else {
      castStrategy.add(res.sql2set)
      setNotNull(col)
    }

    attr.filterAttr.fold {
      val pathAttr = path :+ attr.cleanAttr
      if (filterAttrVars.contains(pathAttr) && attr.op != V) {
        noCardManyFilterAttrExpr(attr)
      }
      setExpr(attr, col, args, res, true)
    } {
      case (dir, filterPath, filterAttr) => filterAttr match {
        case filterAttr: AttrOne => setFilterExpr(col, attr.op, filterAttr.name, res, true)
        case filterAttr          => setFilterExpr(col, attr.op, filterAttr.name, res, true)
      }
    }
  }


  // attr ----------------------------------------------------------------------

  override protected def setAttr[T](
    col: String, res: ResSet[T], mandatory: Boolean
  ): Unit = {
    coalesce(col, res, if (mandatory) "man" else "tac")
    having += "COUNT(*) > 0"
  }

  override protected def setOptAttr[T](col: String, res: ResSet[T]): Unit = {
    coalesce(col, res, "opt")
  }

  override protected def setHas[T](
    col: String,
    set: Set[T],
    res: ResSet[T],
    one2sql: T => String,
    mandatory: Boolean,
  ): Unit = {
    def contains(v: T): String = s"${one2sql(v)} = ANY($col)"
    def containsSet(set: Set[T]): String = set.map(contains).mkString(" AND ")
    coalesce(col, res, if (mandatory) "man" else "tac")
    set.size match {
      case 0 => where += (("FALSE", ""))
      case 1 => where += (("", contains(set.head)))
      case _ => where += (("", set.map(v => containsSet(Set(v))).mkString("(", " OR\n   ", ")")))
    }
  }

  override protected def setHasNo[T](
    col: String,
    set: Set[T],
    res: ResSet[T],
    one2sql: T => String,
    mandatory: Boolean,
  ): Unit = {
    def notContains(v: T): String = s"${one2sql(v)} != ALL($col)"
    def notContainsSet(set: Set[T]): String = set.map(notContains).mkString("(", " OR ", ")")
    coalesce(col, res, if (mandatory) "man" else "tac")
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
    where += (("", s"$col @> ARRAY(SELECT $filterAttr)"))
  }

  override protected def setFilterHasNo[T](
    col: String, filterAttr: String, res: ResSet[T], mandatory: Boolean
  ): Unit = {
    if (mandatory) {
      val colAlias = col.replace(".", "_")
      select -= col
      select += s"ARRAY_AGG($colAlias)"
      tempTables += s"UNNEST($col) AS $colAlias"
      groupByCols -= col
      having += "COUNT(*) > 0"
      aggregate = true
      castStrategy.replace(res.array2set)
    }
    where += (("", s"ARRAY(SELECT UNNEST($col) INTERSECT SELECT $filterAttr) = '{}'"))
  }


  // helpers -------------------------------------------------------------------

  private def coalesce[T](col: String, res: ResSet[T], mode: String) = {
    val colAlias = col.replace(".", "_")
    select -= col
    groupByCols -= col
    mode match {
      case "man" =>
        select += s"ARRAY_AGG(DISTINCT $colAlias)"
        val tpe = res.tpeDb
        tempTables += s"UNNEST(CASE WHEN $col IS NULL THEN array[null]::$tpe[] ELSE $col END) AS $colAlias"
        castStrategy.replace(res.array2set)

      case "opt" =>
        select += s"COALESCE(ARRAY_AGG($col) FILTER (WHERE $col <> '{}'), '{}')"
        castStrategy.replace(res.nestedArray2optCoalescedSet)

      case "tac" =>
        where += (("", s"$col <> '{}'"))
    }
    aggregate = true
  }
}