package molecule.sql.mariadb.query

import molecule.base.error.ModelError
import molecule.sql.core.query.{LambdasSeq, ResolveExprSeq, SqlQueryBase}


trait ResolveExprSeq_mariadb
  extends ResolveExprSeq
    with LambdasSeq { self: SqlQueryBase =>

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
    seq.size match {
      case 0 => where += (("FALSE", ""))
      case 1 => where += (("", s"JSON_CONTAINS($col, JSON_ARRAY(${one2json(seq.head)}))"))
      case _ => where += (("", seq.map(v => containsSeq(Seq(v))).mkString("(", " OR\n   ", ")")))
    }
    mandatoryCast(res, mandatory)
  }

  override protected def seqHasNo[T](
    col: String, seq: Seq[T], one2json: T => String, res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    def notContains(v: T): String = s"NOT JSON_CONTAINS($col, JSON_ARRAY(${one2json(v)}))"
    def notContainsSeq(seq: Seq[T]): String = {
      val jsonValues = seq.map(one2json).mkString(", ")
      s"NOT JSON_CONTAINS($col, JSON_ARRAY($jsonValues))"
    }
    seq.size match {
      case 0 => ()
      case 1 => where += (("", notContains(seq.head)))
      case _ => where += (("", seq.map(v => notContainsSeq(Seq(v))).mkString("(", " AND\n   ", ")")))
    }
    mandatoryCast(res, mandatory)
  }


  // filter attribute ----------------------------------------------------------

  override protected def seqFilterHas[T](
    col: String, filterAttr: String, res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    where += (("", hasClause(col, filterAttr, res)))
    mandatoryCast(res, mandatory)
  }

  override protected def seqFilterHasNo[T](
    col: String, filterAttr: String, res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    where += (("", s"NOT ${hasClause(col, filterAttr, res)}"))
    mandatoryCast(res, mandatory)
  }


  // helpers -------------------------------------------------------------------

  private def mandatoryCast[T](res: ResSeq[T], mandatory: Boolean): Unit = {
    if (mandatory) {
      if (isNestedOpt) {
        // Allow empty optional nested rows.
        // So let non-asserted Seq values (null) be checked in NestedOpt
        replaceCast((row: RS, paramIndex: Int) => {
          row.getString(paramIndex) match {
            case null => null
            case s    => res.json2array(s).toList
          }
        })
      } else {
        replaceCast((row: RS, paramIndex: Int) =>
          res.json2array(row.getString(paramIndex)).toList
        )
      }
    }
  }

  private def hasClause[T](col: String, filterAttr: String, res: ResSeq[T]): String = {
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