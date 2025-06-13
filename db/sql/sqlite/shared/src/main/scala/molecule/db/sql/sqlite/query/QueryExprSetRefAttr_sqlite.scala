package molecule.db.sql.sqlite.query

import molecule.core.dataModel.*
import molecule.db.core.query.Model2Query
import molecule.db.sql.core.query.{QueryExprSetRefAttr, SqlQueryBase}


trait QueryExprSetRefAttr_sqlite
  extends QueryExprSetRefAttr
    with LambdasSet_sqlite { self: Model2Query & SqlQueryBase =>

  override protected def setRefMan[T](
    attr: Attr, args: Set[T], res: ResSet[T]
  ): Unit = {
    select += s"json_group_array($joinTable.$rid) $refIds"
    joins += (("INNER JOIN", joinTable, "", List(s"$entId = $joinTable.$eid")))
    groupBy += entId
    castStrategy.add(
      (row: RS, paramIndex: Int) =>
        res.json2array(row.getString(paramIndex)).toSet
    )

    attr.filterAttr.fold {
      val pathAttr = path :+ attr.cleanAttr
      if (filterAttrVars.contains(pathAttr) && attr.op != V) {
        noCardManyFilterAttrExpr(attr)
      }
      setRefExpr(attr, refIds, attr.op, args)
    } { case (dir, filterPath, filterAttr) =>
      setFilterRefExpr(refIds, attr.op, filterAttr.name)
    }
  }

  override protected def setRefOpt[T](
    attr: Attr, optSet: Option[Set[T]], resOpt: ResSetOpt[T], res: ResSet[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += s"json_group_array($joinTable.$rid) $refIds"
    joins += (("LEFT JOIN", joinTable, "", List(s"$entId = $joinTable.$eid")))
    groupBy += entId
    castStrategy.add((row: RS, paramIndex: Int) => {
      row.getString(paramIndex) match {
        case "[null]" => Option.empty[Set[T]]
        case json     => Some(res.json2array(json).toSet)
      }
    })

    attr.op match {
      case V     => ()
      case Eq    => setRefOptEqual(optSet, res)
      case Neq   => setRefOptNeq(optSet, res)
      case Has   => refOptHas(col, optSet)
      case HasNo => refOptHasNo(optSet)
      case other => unexpectedOp(other)
    }
  }

  private def refMatchArray[T](set: Set[T], one2json: T => String): String = {
    val size       = set.size
    val jsonValues = set.map(one2json).mkString(", ")
    s"""(
       |    SELECT
       |      JSON_LENGTH(json_group_array($joinTable.$rid)) = $size AND
       |      JSON_CONTAINS(json_group_array($joinTable.$rid), JSON_ARRAY($jsonValues))
       |    FROM $joinTable
       |    WHERE $joinTable.$eid = $entId
       |  )""".stripMargin
  }

  override protected def setRefEqual[T](set: Set[T], res: ResSet[T]): Unit = {
    where += (("", refMatchArray(set, res.one2json)))
  }

  override protected def setRefOptEqual[T](optSet: Option[Set[T]], res: ResSet[T]): Unit = {
    optSet.fold[Unit] {
      where += (("",
        s"""(
           |    SELECT count($joinTable.$rid) = 0
           |    FROM $joinTable
           |    WHERE $joinTable.$eid = $entId
           |  )""".stripMargin
      ))
    } { set =>
      setRefEqual(set, res)
    }
  }

  override protected def setRefNeq[T](set: Set[T], res: ResSet[T]): Unit = {
    where += (("", "NOT (" + refMatchArray(set, res.one2json) + ")"))
  }

  override protected def setRefOptNeq[T](optSet: Option[Set[T]], res: ResSet[T]): Unit = {
    optSet.foreach(set => setRefNeq(set, res))
    setNotNull(s"$joinTable.$eid")
  }


  private def arrayMatches(matches: Seq[String], logic: String): String = {
    s"""(
       |    SELECT
       |      ${matches.mkString(s" $logic\n      ")}
       |    FROM $joinTable
       |    WHERE $joinTable.$eid = $entId
       |  )""".stripMargin
  }

  private def arrayMatch[T](set: Set[T], not: String = ""): String = {
    s"""${not}JSON_CONTAINS(
       |        JSON_ARRAYAGG($joinTable.$rid),
       |        JSON_ARRAY(${set.mkString(", ")})
       |      )""".stripMargin
  }

  override protected def refHas[T](set: Set[T]): Unit = {
    set.size match {
      case 0 => where += (("FALSE", ""))
      case 1 => where += (("",
        s"""EXISTS (
           |    SELECT *
           |    FROM $joinTable
           |    WHERE
           |      $joinTable.$eid = $entId AND
           |      $joinTable.$rid = ${set.head}
           |  )""".stripMargin
      ))
      case _ => where += (("",
        s"""EXISTS (
           |    SELECT *
           |    FROM $joinTable
           |    WHERE
           |      $joinTable.$eid = $entId AND
           |      $joinTable.$rid IN (${set.mkString(", ")})
           |  )""".stripMargin
      ))
    }
  }

  override protected def refHasNo[T](set: Set[T]): Unit = {
    set.size match {
      case 0 => ()
      case 1 => where += (("",
        s"""NOT EXISTS (
           |    SELECT *
           |    FROM $joinTable
           |    WHERE
           |      $joinTable.$eid = $entId AND
           |      $joinTable.$rid = ${set.head}
           |  )""".stripMargin
      ))
      case _ => where += (("",
        s"""NOT EXISTS (
           |    SELECT *
           |    FROM $joinTable
           |    WHERE
           |      $joinTable.$eid = $entId AND
           |      $joinTable.$rid IN (${set.mkString(", ")})
           |  )""".stripMargin
      ))
    }
  }
}