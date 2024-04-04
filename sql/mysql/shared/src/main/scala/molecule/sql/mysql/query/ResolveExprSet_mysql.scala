package molecule.sql.mysql.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.sql.core.query.{ResolveExprSet, SqlQueryBase}
import scala.reflect.ClassTag


trait ResolveExprSet_mysql
  extends ResolveExprSet
    with LambdasSet_mysql { self: SqlQueryBase =>


  override protected def setMan[T](
    attr: Attr, args: Set[T], res: ResSet[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns
    if (!isNestedOpt) {
      notNull += col
    }
    addCast((row: RS, paramIndex: Int) =>
      res.json2array(row.getString(paramIndex)).toSet
    )

    attr.filterAttr.fold {
      val pathAttr = path :+ attr.cleanAttr
      if (filterAttrVars.contains(pathAttr) && attr.op != V) {
        noCardManyFilterAttrExpr(attr)
      }
      setExpr(attr, col, attr.op, args, res, true)
    } {
      case (dir, filterPath, filterAttr) => filterAttr match {
        case filterAttr: AttrOne => setFilterExpr(col, attr.op, filterAttr.name, res, true)
        case filterAttr          => setFilterExpr(col, attr.op, filterAttr.name, res, true)
      }
    }
  }

  override protected def setExpr[T](
    attr: Attr, col: String, op: Op, set: Set[T], res: ResSet[T], mandatory: Boolean
  ): Unit = {
    op match {
      case V       => setAttr(col, res, mandatory)
      case Eq      => noCollectionMatching(attr)
      case Has     => setHas(col, set, res, res.one2sql, mandatory)
      case HasNo   => setHasNo(col, set, res, res.one2sql, mandatory)
      case NoValue => if (mandatory) noApplyNothing(attr) else setNoValue(col)
      case other   => unexpectedOp(other)
    }
  }

  override protected def setOpt[T](
    attr: Attr, resOpt: ResSetOpt[T], res: ResSet[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns
    addCast((row: RS, paramIndex: Int) => row.getString(paramIndex) match {
      case null | "[]" => Option.empty[Set[T]]
      case json        => Some(res.json2array(json).toSet)
    })
    attr.op match {
      case V     => setOptAttr(col, res)
      case Eq    => noCollectionMatching(attr)
      case other => unexpectedOp(other)
    }
  }


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
      replaceCast((row: RS, paramIndex: Int) =>
        res.json2array(row.getString(paramIndex)).toSet
      )
    }
  }

  override protected def setOptAttr[T](col: String, res: ResSet[T]): Unit = {
    select -= col
    selectWithOrder(col, res.tpeDb, "JSON_ARRAYAGG", optional = true)
    groupByCols -= col
    aggregate = true
    replaceCast((row: RS, paramIndex: Int) =>
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
      selectWithOrder(col, res.tpeDb, "JSON_ARRAYAGG", optional = true)
      groupByCols -= col
      aggregate = true
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
      select -= col
      selectWithOrder(col, res.tpeDb, "JSON_ARRAYAGG", optional = true)
      groupByCols -= col
      aggregate = true
    }
    set.size match {
      case 0 => ()
      case 1 => where += (("", notContains(set.head)))
      case _ => where += (("", set.map(v => notContainsSet(Set(v))).mkString("(", " AND\n   ", ")")))
    }
  }


  // filter attribute ----------------------------------------------------------

  override protected def setFilterHas(col: String, filterAttr: String): Unit = {
    where += (("", s"JSON_CONTAINS($col, JSON_ARRAY($filterAttr))"))
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
    }
    where += (("", s"NOT JSON_CONTAINS($col, JSON_ARRAY($filterAttr))"))
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
}