package molecule.sql.mariadb.query

import molecule.boilerplate.ast.Model._
import molecule.sql.core.query.{ResolveExprSeq, SqlQueryBase}


trait ResolveExprSeq_mariadb
  extends ResolveExprSeq
    with LambdasSeq_mariadb { self: SqlQueryBase =>


  override protected def seqMan[T](
    attr: Attr, args: Seq[T], res: ResSeq[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    if (!isNestedOpt) {
      notNull += col
    }
    addCast((row: RS, paramIndex: Int) =>
      res.json2array(row.getString(paramIndex)).toList
    )
    attr.filterAttr.fold {
      val pathAttr = path :+ attr.cleanAttr
      if (filterAttrVars.contains(pathAttr) && attr.op != V) {
        noCardManyFilterAttrExpr(attr)
      }
      seqExpr(attr, col, args, res, true)
    } {
      case (dir, filterPath, filterAttr) => filterAttr match {
        case filterAttr: AttrOne => seqFilterExpr(col, attr.op, filterAttr.name)
        case filterAttr          => seqFilterExpr(col, attr.op, filterAttr.name)
      }
    }
  }

  override protected def seqTac[T](
    attr: Attr, args: Seq[T], res: ResSeq[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    notNull += col
    attr.filterAttr.fold {
      seqExpr(attr, col, args, res, false)
    } { case (dir, filterPath, filterAttr) =>
      filterAttr match {
        case filterAttr: AttrOne => seqFilterExpr(col, attr.op, filterAttr.name)
        case filterAttr          => seqFilterExpr(col, attr.op, filterAttr.name)
      }
    }
  }

  override protected def seqExpr[T](
    attr: Attr, col: String, seq: Seq[T], res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    attr.op match {
      case V       => seqAttr(res, mandatory)
      case Eq      => noCollectionMatching(attr)
      case Has     => seqHas(col, seq, res, res.one2sql, mandatory)
      case HasNo   => seqHasNo(col, seq, res, res.one2sql, mandatory)
      case NoValue => if (mandatory) noApplyNothing(attr) else seqNoValue(col)
      case other   => unexpectedOp(other)
    }
  }

  override protected def seqFilterExpr(
    col: String, op: Op, filterAttr: String
  ): Unit = {
    op match {
      case Has   => seqFilterHas(col, filterAttr)
      case HasNo => seqFilterHasNo(col, filterAttr)
      case other => unexpectedOp(other)
    }
  }

  override protected def seqOpt[T](
    attr: Attr, resOpt: ResSeqOpt[T], res: ResSeq[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns
    addCast((row: RS, paramIndex: Int) => row.getString(paramIndex) match {
      case null | "[]" => Option.empty[Set[T]]
      case json        => Some(res.json2array(json).toList)
    })
    attr.op match {
      case V     => () // get array as-is
      case Eq    => noCollectionMatching(attr)
      case other => unexpectedOp(other)
    }
  }


  // attr ----------------------------------------------------------------------

  private def seqAttr[T](
    res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    if (mandatory) {
      replaceCast((row: RS, paramIndex: Int) =>
        res.json2array(row.getString(paramIndex)).toList
      )
    }
  }

  private def seqHas[T](
    col: String, seq: Seq[T], res: ResSeq[T], one2json: T => String, mandatory: Boolean
  ): Unit = {
    def containsSeq(seq: Seq[T]): String = {
      val jsonValues = seq.map(one2json).mkString(", ")
      s"JSON_CONTAINS($col, JSON_ARRAY($jsonValues))"
    }
    if (mandatory) {
      replaceCast((row: RS, paramIndex: Int) =>
        res.json2array(row.getString(paramIndex)).toList
      )
    }
    seq.size match {
      case 0 => where += (("FALSE", ""))
      case 1 => where += (("", s"JSON_CONTAINS($col, JSON_ARRAY(${one2json(seq.head)}))"))
      case _ => where += (("", seq.map(v => containsSeq(Seq(v))).mkString("(", " OR\n   ", ")")))
    }
  }

  private def seqHasNo[T](
    col: String, seq: Seq[T], res: ResSeq[T], one2json: T => String, mandatory: Boolean
  ): Unit = {
    def notContains(v: T): String = s"NOT JSON_CONTAINS($col, JSON_ARRAY(${one2json(v)}))"
    def notContainsSeq(seq: Seq[T]): String = {
      val jsonValues = seq.map(one2json).mkString(", ")
      s"NOT JSON_CONTAINS($col, JSON_ARRAY($jsonValues))"
    }
    if (mandatory) {
      replaceCast((row: RS, paramIndex: Int) =>
        res.json2array(row.getString(paramIndex)).toList
      )
    }
    seq.size match {
      case 0 => ()
      case 1 => where += (("", notContains(seq.head)))
      case _ => where += (("", seq.map(v => notContainsSeq(Seq(v))).mkString("(", " AND\n   ", ")")))
    }
  }


  // filter attribute ----------------------------------------------------------

  override protected def seqFilterHas(col: String, filterAttr: String): Unit = {
    where += (("", s"JSON_CONTAINS($col, JSON_ARRAY($filterAttr))"))
  }

  override protected def seqFilterHasNo(
    col: String, filterAttr: String, //res: ResSeq[T] //, mandatory: Boolean
  ): Unit = {
    where += (("", s"NOT JSON_CONTAINS($col, JSON_ARRAY($filterAttr))"))
  }
}