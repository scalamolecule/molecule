package molecule.db.sqlite.query

import molecule.db.common.query.{Model2Query, QueryExprSeq, SqlQueryBase}

trait QueryExprSeq_sqlite
  extends QueryExprSeq
    with LambdasSeq_sqlite { self: Model2Query & SqlQueryBase =>


  // attr ----------------------------------------------------------------------

  override protected def seqAttr[T](
    col: String, res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    mandatoryCast(res, mandatory)
  }

  override protected def seqOptAttr[T](
    res: ResSeq[T]
  ): Unit = {
    castStrategy.replace((row: RS, paramIndex: Int) => row.getString(paramIndex) match {
      case null | "[]" => Option.empty[Set[T]]
      case json        => Some(res.json2array(json).toList)
    })
  }

  override protected def seqHas[T](
    col: String,
    seq: Seq[T],
    one2sql: T => String,
    res: ResSeq[T],
    mandatory: Boolean,
  ): Unit = {
    seq.size match {
      case 0 => where += (("FALSE", ""))
      case 1 => where += (("",
        s"""EXISTS (
           |    SELECT *
           |    FROM JSON_EACH($col)
           |    WHERE JSON_EACH.VALUE = ${one2sql(seq.head)}
           |  )""".stripMargin
      ))
      case _ =>
        val values = seq.map(one2sql).mkString(", ")
        where += (("",
          s"""EXISTS (
             |    SELECT *
             |    FROM JSON_EACH($col)
             |    WHERE JSON_EACH.VALUE IN ($values)
             |  )""".stripMargin
        ))
    }
    mandatoryCast(res, mandatory)
  }

  override protected def seqHasNo[T](
    col: String,
    seq: Seq[T],
    one2sql: T => String,
    res: ResSeq[T],
    mandatory: Boolean,
  ): Unit = {
    seq.size match {
      case 0 => ()
      case 1 => where += (("",
        s"""NOT EXISTS (
           |    SELECT *
           |    FROM JSON_EACH($col)
           |    WHERE JSON_EACH.VALUE = ${one2sql(seq.head)}
           |  )""".stripMargin
      ))
      case _ =>
        val values = seq.map(one2sql).mkString(", ")
        where += (("",
          s"""NOT EXISTS (
             |    SELECT *
             |    FROM JSON_EACH($col)
             |    WHERE JSON_EACH.VALUE IN ($values)
             |  )""".stripMargin
        ))
    }
    mandatoryCast(res, mandatory)
  }


  // filter attribute ----------------------------------------------------------

  override protected def seqFilterHas[T](
    col: String, filterAttr: String, res: ResSeq[T], mandatory: Boolean
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

  override protected def seqFilterHasNo[T](
    col: String, filterAttr: String, res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    where += (("",
      s"""NOT EXISTS (
         |    SELECT *
         |    FROM JSON_EACH($col)
         |    WHERE JSON_EACH.VALUE = $filterAttr
         |  )""".stripMargin
    ))
    mandatoryCast(res, mandatory)
  }


  // helpers -------------------------------------------------------------------

  private def mandatoryCast[T](res: ResSeq[T], mandatory: Boolean): Unit = {
    if (mandatory) {
      if (isOptNested || nestedOptRef) {
        // Allow empty optional nested rows.
        // So let non-asserted Seq values (null) be checked in OptNested
        castStrategy.replace((row: RS, paramIndex: Int) => {
          row.getString(paramIndex) match {
            case null => null
            case s    => res.json2array(s).toList
          }
        })
      } else {
        castStrategy.replace((row: RS, paramIndex: Int) =>
          res.json2array(row.getString(paramIndex)).toList
        )
      }
    }
  }
}