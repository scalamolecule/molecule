package molecule.sql.postgres.query

import molecule.core.query.Model2Query
import molecule.sql.core.query.{QueryExprSeq, SqlQueryBase}


trait QueryExprSeq_postgres
  extends QueryExprSeq
    with LambdasSeq_postgres { self: Model2Query with SqlQueryBase =>


  // attr ----------------------------------------------------------------------

  override protected def seqAttr[T](
    col: String, res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    mandatoryCast(res, mandatory)
  }

  override protected def seqHas[T](
    col: String, seq: Seq[T], one2sql: T => String, res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    mandatoryCast(res, mandatory)
    if (seq.isEmpty) {
      where += (("FALSE", ""))
    } else {
      where += (("", s"$col && ${res.seq2sqlArray(seq)}"))
    }
  }

  override protected def seqHasNo[T](
    col: String, seq: Seq[T], one2sql: T => String, res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    mandatoryCast(res, mandatory)
    if (seq.isEmpty) {
      () // get all
    } else {
      where += (("", s"NOT $col && ${res.seq2sqlArray(seq)}"))
    }
  }


  // filter attribute ----------------------------------------------------------

  override protected def seqFilterHas[T](
    col: String, filterAttr: String, res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    where += (("", s"$filterAttr = ANY($col)"))
    mandatoryCast(res, mandatory)
  }

  override protected def seqFilterHasNo[T](
    col: String, filterAttr: String, res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    where += (("", s"NOT $filterAttr = ANY($col)"))
    mandatoryCast(res, mandatory)
  }


  // helpers -------------------------------------------------------------------

  private def mandatoryCast[T](res: ResSeq[T], mandatory: Boolean): Unit = {
    if (mandatory) {
      if (isOptNested || nestedOptRef) {
        // Allow empty optional nested rows.
        // So let non-asserted Seq values (null) be checked in NestedOpt
        castStrategy.replace((row: RS, paramIndex: Int) => {
          row.getString(paramIndex) match {
            case null => null
            case _    => res.array2list(row, paramIndex)
          }
        })
      } else {
        castStrategy.replace((row: RS, paramIndex: Int) =>
          res.array2list(row, paramIndex)
        )
      }
    }
  }

  override protected lazy val json2oneBoolean: String => Boolean = {
    (v: String) => v == "t" || v == "1"
  }
}