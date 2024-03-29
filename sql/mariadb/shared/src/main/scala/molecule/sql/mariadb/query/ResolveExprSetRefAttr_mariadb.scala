package molecule.sql.mariadb.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.sql.core.query.{ResolveExprSetRefAttr, SqlQueryBase}
import scala.reflect.ClassTag

trait ResolveExprSetRefAttr_mariadb
  extends ResolveExprSetRefAttr
    with LambdasSet_mariadb { self: SqlQueryBase =>


  override protected def refMan[T: ClassTag](
    attr: Attr, args: Seq[Set[T]], res: ResSet[T]
  ): Unit = {
    select += s"JSON_ARRAYAGG($joinTable.$ref_id) $refIds"
    joins += (("INNER JOIN", joinTable, "", s"$nsId", s"= $joinTable.$ns_id"))
    groupBy += nsId
    addCast(
      (row: RS, paramIndex: Int) =>
        res.json2array(row.getString(paramIndex)).toSet
    )

    attr.filterAttr.fold {
      val pathAttr = path :+ attr.cleanAttr
      if (filterAttrVars.contains(pathAttr) && attr.op != V) {
        // Runtime check needed since we can't type infer it
        throw ModelError(s"Cardinality-set filter attributes not allowed to " +
          s"do additional filtering. Found:\n  " + attr)
      }
      refExpr(refIds, attr.op, args, res)
    } { case (dir, filterPath, filterAttr) =>
      refExpr2(refIds, attr.op, filterAttr.name)
    }
  }

  override protected def refOpt[T: ClassTag](
    attr: Attr,
    optSets: Option[Seq[Set[T]]],
    resOpt: ResSetOpt[T],
    res: ResSet[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += s"JSON_ARRAYAGG($joinTable.$ref_id) $refIds"
    joins += (("LEFT JOIN", joinTable, "", s"$nsId", s"= $joinTable.$ns_id"))
    groupBy += nsId
    addCast((row: RS, paramIndex: Int) => {
      row.getString(paramIndex) match {
        case "[null]" => Option.empty[Set[T]]
        case json     => Some(res.json2array(json).toSet)
      }
    })

    attr.op match {
      case V     => ()
      case Eq    => refOptEqual(optSets, res)
      case Neq   => refOptNeq(optSets, res)
      case Has   => refOptHas(col, optSets)
      case HasNo => refOptHasNo(optSets)
      case other => unexpectedOp(other)
    }
  }

  private def refMatchArray[T](set: Set[T], one2json: T => String): String = {
    val size       = set.size
    val jsonValues = set.map(one2json).mkString(", ")
    s"""(
       |    SELECT
       |      JSON_LENGTH(JSON_ARRAYAGG($joinTable.$ref_id)) = $size AND
       |      JSON_CONTAINS(JSON_ARRAYAGG($joinTable.$ref_id), JSON_ARRAY($jsonValues))
       |    FROM $joinTable
       |    WHERE $joinTable.$ns_id = $nsId
       |  )""".stripMargin
  }

  private def refMatchArrays[T](sets: Seq[Set[T]], one2json: T => String): String = {
    sets.map(set =>
      refMatchArray(set, one2json)
    ).mkString("(\n    ", " OR\n    ", "\n  )")
  }

  override protected def refEqual[T](sets: Seq[Set[T]], res: ResSet[T]): Unit = {
    val setsNonEmpty = sets.filterNot(_.isEmpty)
    setsNonEmpty.length match {
      case 0 => where += (("FALSE", ""))
      case 1 => where += (("", refMatchArray(setsNonEmpty.head, res.one2json)))
      case _ => where += (("", refMatchArrays(setsNonEmpty, res.one2json)))
    }
  }

  override protected def refOptEqual[T](optSets: Option[Seq[Set[T]]], res: ResSet[T]): Unit = {
    optSets.fold[Unit] {
      where += (("",
        s"""(
           |    SELECT count($joinTable.$ref_id) = 0
           |    FROM $joinTable
           |    WHERE $joinTable.$ns_id = $nsId
           |  )""".stripMargin
      ))
    } { sets =>
      refEqual(sets, res)
    }
  }

  override protected def refNeq[T](sets: Seq[Set[T]], res: ResSet[T]): Unit = {
    val setsNonEmpty = sets.filterNot(_.isEmpty)
    setsNonEmpty.length match {
      case 0 => ()
      case 1 => where += (("", "NOT (" +
        refMatchArray(setsNonEmpty.head, res.one2json) +
        ")"))
      case _ => where += (("", "NOT (" +
        refMatchArrays(setsNonEmpty, res.one2json) +
        ")"))
    }
  }

  override protected def refOptNeq[T](optSets: Option[Seq[Set[T]]], res: ResSet[T]): Unit = {
    if (optSets.isDefined && optSets.get.nonEmpty) {
      refNeq(optSets.get, res)
    }
    notNull += s"$joinTable.$ns_id"
  }


  private def arrayMatches(matches: Seq[String], logic: String): String = {
    s"""(
       |    SELECT
       |      ${matches.mkString(s" $logic\n      ")}
       |    FROM $joinTable
       |    WHERE $joinTable.$ns_id = $nsId
       |  )""".stripMargin
  }

  private def arrayMatch[T](set: Set[T], not: String = ""): String = {
    s"""${not}JSON_CONTAINS(
       |        JSON_ARRAYAGG($joinTable.$ref_id),
       |        JSON_ARRAY(${set.mkString(", ")})
       |      )""".stripMargin
  }

  override protected def refHas[T: ClassTag](sets: Seq[Set[T]]): Unit = {
    sets.length match {
      case 0 => where += (("FALSE", ""))
      case 1 =>
        val set = sets.head
        if (set.isEmpty)
          where += (("FALSE", ""))
        else
          where += (("", arrayMatches(Seq(arrayMatch(set)), "OR")))
      case _ =>
        sets.filterNot(_.isEmpty) match {
          case Nil  => where += (("FALSE", ""))
          case sets => where += (("", arrayMatches(sets.map(arrayMatch(_)), "OR")))
        }

    }
  }

  override protected def refHasNo[T](sets: Seq[Set[T]]): Unit = {
    sets.length match {
      case 0 => ()
      case 1 =>
        val set = sets.head
        if (set.nonEmpty) {
          where += (("", arrayMatches(Seq(arrayMatch(set, "NOT ")), "AND")))
        }
      case _ => sets.filterNot(_.isEmpty) match {
        case Nil  => () // all
        case sets => where += (("", arrayMatches(sets.map(arrayMatch(_, "NOT ")), "AND")))
      }
    }
  }
}