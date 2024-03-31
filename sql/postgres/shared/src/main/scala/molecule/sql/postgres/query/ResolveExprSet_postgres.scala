package molecule.sql.postgres.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.sql.core.query.{ResolveExprSet, SqlQueryBase}
import scala.reflect.ClassTag


trait ResolveExprSet_postgres
  extends ResolveExprSet
    with LambdasSet_postgres { self: SqlQueryBase =>


  override protected def setMan[T: ClassTag](
    attr: Attr, args: Set[T], res: ResSet[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns

    if (isNestedOpt) {
      addCast(res.sql2setOrNull)
    } else {
      addCast(res.sql2set)
      notNull += col
    }

    attr.filterAttr.fold {
      val pathAttr = path :+ attr.cleanAttr
      if (filterAttrVars.contains(pathAttr) && attr.op != V) {
        // Runtime check needed since we can't type infer it
        throw ModelError(s"Cardinality-set filter attributes not allowed to " +
          s"do additional filtering. Found:\n  " + attr)
      }
      setExpr(attr, col, attr.op, args, res, true)
    } {
      case (dir, filterPath, filterAttr) => filterAttr match {
        case filterAttr: AttrOne => setFilterExpr(col, attr.op, filterAttr.name, res, true)
        case filterAttr          => setFilterExpr(col, attr.op, filterAttr.name, res, true)
      }
    }
  }


  // attr ----------------------------------------------------------------------

  override protected def setAttr[T: ClassTag](
    col: String, res: ResSet[T], mandatory: Boolean
  ): Unit = {
    coalesce(col, res, if (mandatory) "man" else "tac")
    having += "COUNT(*) > 0"
  }

  override protected def setOptAttr[T](col: String, res: ResSet[T]): Unit = {
    coalesce(col, res, "opt")
  }

  override protected def setHas[T: ClassTag](
    col: String, set: Set[T], res: ResSet[T], one2sql: T => String, mandatory: Boolean
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
    col: String, set: Set[T], res: ResSet[T], one2sql: T => String, mandatory: Boolean
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

  override protected def setFilterHas(col: String, filterAttr: String): Unit = {
    where += (("", s"$col @> ARRAY(SELECT $filterAttr)"))
  }

  override protected def setFilterHasNo[T](col: String, filterAttr: String, res: ResSet[T], mandatory: Boolean): Unit = {
    if (mandatory) {
      val colAlias = col.replace(".", "_")
      select -= col
      select += s"ARRAY_AGG($colAlias)"
      tempTables += s"UNNEST($col) AS $colAlias"
      groupByCols -= col
      having += "COUNT(*) > 0"
      aggregate = true
      replaceCast(res.array2set)
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
        replaceCast(res.array2set)

      case "opt" =>
        select += s"COALESCE(ARRAY_AGG($col) FILTER (WHERE $col <> '{}'), '{}')"
        replaceCast(res.nestedArray2optCoalescedSet)

      case "tac" =>
        where += (("", s"$col <> '{}'"))
    }
    aggregate = true
  }
}