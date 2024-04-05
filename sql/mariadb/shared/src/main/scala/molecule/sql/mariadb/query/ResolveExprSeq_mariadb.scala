package molecule.sql.mariadb.query

import molecule.sql.core.query.{ResolveExprSeq, SqlQueryBase}


trait ResolveExprSeq_mariadb
  extends ResolveExprSeq
    with LambdasSeq_mariadb { self: SqlQueryBase =>

  // attr ----------------------------------------------------------------------

  override protected def seqAttr[T](
    col: String, res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    mandatoryCast(res, mandatory)
  }

  override protected def seqOptAttr[T](
    res: ResSeq[T]
  ): Unit = {
    replaceCast((row: RS, paramIndex: Int) => row.getString(paramIndex) match {
      case null | "[]" => Option.empty[Set[T]]
      case json        => Some(res.json2array(json).toList)
    })
  }

  override protected def seqHas[T](
    col: String, seq: Seq[T], one2json: T => String, res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    def containsSeq(seq: Seq[T]): String = {
      val jsonValues = seq.map(one2json).mkString(", ")
      s"JSON_CONTAINS($col, JSON_ARRAY($jsonValues))"
    }
    mandatoryCast(res, mandatory)
    seq.size match {
      case 0 => where += (("FALSE", ""))
      case 1 => where += (("", s"JSON_CONTAINS($col, JSON_ARRAY(${one2json(seq.head)}))"))
      case _ => where += (("", seq.map(v => containsSeq(Seq(v))).mkString("(", " OR\n   ", ")")))
    }
  }

  override protected def seqHasNo[T](
    col: String, seq: Seq[T], one2json: T => String, res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    def notContains(v: T): String = s"NOT JSON_CONTAINS($col, JSON_ARRAY(${one2json(v)}))"
    def notContainsSeq(seq: Seq[T]): String = {
      val jsonValues = seq.map(one2json).mkString(", ")
      s"NOT JSON_CONTAINS($col, JSON_ARRAY($jsonValues))"
    }
    mandatoryCast(res, mandatory)
    seq.size match {
      case 0 => ()
      case 1 => where += (("", notContains(seq.head)))
      case _ => where += (("", seq.map(v => notContainsSeq(Seq(v))).mkString("(", " AND\n   ", ")")))
    }
  }


  // filter attribute ----------------------------------------------------------

  override protected def seqFilterHas[T](
    col: String, filterAttr: String, res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    where += (("", s"JSON_CONTAINS($col, JSON_ARRAY($filterAttr))"))
    mandatoryCast(res, mandatory)
  }

  override protected def seqFilterHasNo[T](
    col: String, filterAttr: String, res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    where += (("", s"NOT JSON_CONTAINS($col, JSON_ARRAY($filterAttr))"))
    mandatoryCast(res, mandatory)
  }


  // helpers -------------------------------------------------------------------

  private def mandatoryCast[T](res: ResSeq[T], mandatory: Boolean): Unit = {
    if (mandatory) {
      replaceCast((row: RS, paramIndex: Int) =>
        res.json2array(row.getString(paramIndex)).toList
      )
    }
  }
}