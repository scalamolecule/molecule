package molecule.sql.mysql.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.sql.core.query.{ResolveExprSet, SqlQueryBase}
import scala.reflect.ClassTag
import scala.util.Random


trait ResolveExprSet_mysql
  extends ResolveExprSet
    with LambdasSet_mysql { self: SqlQueryBase =>


  override protected def setMan[T: ClassTag](attr: Attr, tpe: String, args: Seq[Set[T]], res: ResSet[T]): Unit = {
    val col = getCol(attr: Attr)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns
    if (!isNestedOpt) {
      notNull += col
    }
    addCast((row: Row, paramIndex: Int) =>
      res.json2array(row.getString(paramIndex)).toSet
    )

    attr.filterAttr.fold {
      if (filterAttrVars.contains(attr.name) && attr.op != V) {
        // Runtime check needed since we can't type infer it
        throw ModelError(s"Cardinality-set filter attributes not allowed to do additional filtering. Found:\n  " + attr)
      }
      setExpr(col, attr.op, args, res, "man")
    } {
      case filterAttr: AttrOne => setExpr2(col, attr.op, filterAttr.name, true, tpe)
      case filterAttr          => setExpr2(col, attr.op, filterAttr.name, false, tpe)
    }
  }

  override protected def setOpt[T: ClassTag](
    attr: Attr,
    optSets: Option[Seq[Set[T]]],
    resOpt: ResSetOpt[T],
    res: ResSet[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns
    addCast((row: Row, paramIndex: Int) => row.getString(paramIndex) match {
      case null | "[]" => Option.empty[Set[T]]
      case json        => Some(res.json2array(json).toSet)
    })
    attr.op match {
      case V     => ()
      case Eq    => setOptEqual(col, optSets, res)
      case Neq   => setOptNeq(col, optSets, res)
      case Has   => optHas(col, optSets, resOpt.one2json)
      case HasNo => optHasNo(col, optSets, resOpt.one2json)
      case other => unexpectedOp(other)
    }
  }

  override protected def setExpr[T: ClassTag](col: String, op: Op, sets: Seq[Set[T]], res: ResSet[T], mode: String): Unit = {
    op match {
      case V         => if (mode == "man") setAttr(col, res) else ()
      case Eq        => setEqual(col, sets, res)
      case Neq       => setNeq(col, sets, res)
      case Has       => has(col, sets, res.one2json)
      case HasNo     => hasNo(col, sets, res.one2json)
      case NoValue   => setNoValue(col)
      case Fn(kw, n) => setAggr(col, kw, n, res)
      case other     => unexpectedOp(other)
    }
  }

  override protected def setAttr[T](col: String, res: ResSet[T]): Unit = {
    select -= col
    selectWithOrder(col, res.tpeDb, "JSON_ARRAYAGG")
    groupByCols -= col
    having += "COUNT(*) > 0"
    aggregate = true
    replaceCast((row: Row, paramIndex: Int) =>
      res.json2array(row.getString(paramIndex)).toSet
    )
  }


  override protected def setAggr[T: ClassTag](col: String, fn: String, optN: Option[Int], res: ResSet[T]): Unit = {
    lazy val sep     = "0x1D" // Use ascii Group Selector to separate concatenated values
    lazy val sepChar = 29.toChar
    lazy val tpeDb   = res.tpeDb
    select -= col
    lazy val n = optN.getOrElse(0)
    fn match {
      case "distinct" =>
        noBooleanSetAggr(res)
        select += s"JSON_ARRAYAGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast((row: Row, paramIndex: Int) => {
          val json = row.getString(paramIndex)
          if (row.wasNull()) {
            Set.empty[Set[T]]
          } else {
            // Need res.json2array for various types with/without quotes
            json //                              "[[1, 2], [3, 4], [5, 6]]"
              .substring(1, json.length - 1) //  "[1, 2], [3, 4], [5, 6]"
              .replace("], [", "]], [[") //      "[1, 2]], [[3, 4]], [[5, 6]"
              .split("], \\[") //                Array("[1, 2]", "[3, 4]", "[5, 6]")
              .map(res.json2array(_).toSet) //   Array(Set(1, 2), Set(3, 4), Set(5, 6))
              .toSet //                          Set(Set(1, 2), Set(3, 4), Set(5, 6))
          }
        })

      case "min" =>
        noBooleanSetAggr(res)
        selectWithOrder(col, tpeDb, "MIN")
        groupByCols -= col
        aggregate = true
        replaceCast((row: Row, paramIndex: Int) => {
          Set(res.json2tpe(row.getString(paramIndex)))
        })

      case "mins" =>
        noBooleanSetAggr(res)
        groupByCols -= col
        aggregate = true
        val i = select.size + 1
        select += s"GROUP_CONCAT(DISTINCT t_$i.vs SEPARATOR $sep)"
        tempTables = tempTables :+ s"JSON_TABLE($col, '$$[*]' COLUMNS (vs $tpeDb PATH '$$')) t_$i"
        replaceCast((row: Row, paramIndex: Int) =>
          row.getString(paramIndex).split(sepChar).map(res.json2tpe).take(n).toSet
        )

      case "max" =>
        noBooleanSetAggr(res)
        selectWithOrder(col, tpeDb, "MAX")
        groupByCols -= col
        aggregate = true
        replaceCast((row: Row, paramIndex: Int) => {
          Set(res.json2tpe(row.getString(paramIndex)))
        })

      case "maxs" =>
        noBooleanSetAggr(res)
        groupByCols -= col
        aggregate = true
        val i = select.size + 1
        select += s"GROUP_CONCAT(DISTINCT t_$i.vs ORDER BY t_$i.vs DESC SEPARATOR $sep)"
        tempTables = tempTables :+ s"JSON_TABLE($col, '$$[*]' COLUMNS (vs $tpeDb PATH '$$')) t_$i"
        replaceCast((row: Row, paramIndex: Int) =>
          row.getString(paramIndex).split(sepChar).map(res.json2tpe).take(n).toSet
        )

      case "rand" =>
        noBooleanSetAggr(res)
        selectWithOrder(col, tpeDb, "JSON_ARRAYAGG")
        replaceCast((row: Row, paramIndex: Int) => {
          val array = res.json2array(row.getString(paramIndex))
          val rnd   = new Random().nextInt(array.length)
          Set(array(rnd))
        })

      case "rands" =>
        noBooleanSetAggr(res)
        selectWithOrder(col, tpeDb, "JSON_ARRAYAGG")
        replaceCast((row: Row, paramIndex: Int) => {
          val array = res.json2array(row.getString(paramIndex))
          Random.shuffle(array.toSet).take(n)
        })

      case "sample" =>
        noBooleanSetAggr(res)
        selectWithOrder(col, tpeDb, "JSON_ARRAYAGG")
        replaceCast((row: Row, paramIndex: Int) => {
          val array = res.json2array(row.getString(paramIndex))
          val rnd   = new Random().nextInt(array.length)
          Set(array(rnd))
        })

      case "samples" =>
        noBooleanSetAggr(res)
        selectWithOrder(col, tpeDb, "JSON_ARRAYAGG")
        replaceCast((row: Row, paramIndex: Int) => {
          val array = res.json2array(row.getString(paramIndex))
          Random.shuffle(array.toSet).take(n)
        })

      case "count" =>
        noBooleanSetCounts(n)
        selectWithOrder(col, dbType(col), "COUNT")
        groupByCols -= col
        aggregate = true
        replaceCast(toInt)

      case "countDistinct" =>
        noBooleanSetCounts(n)
        // Count of unique values (Set semantics)
        selectWithOrder(col, dbType(col), "COUNT", "DISTINCT ")
        groupByCols -= col
        aggregate = true
        replaceCast(toInt)

      case "sum" =>
        // Sum of unique values (Set semantics)
        selectWithOrder(col, tpeDb, "SUM", "DISTINCT ")
        groupByCols -= col
        aggregate = true
        replaceCast((row: Row, paramIndex: Int) =>
          Set(res.json2tpe(row.getString(paramIndex)))
        )

      case "median" =>
        selectWithOrder(col, tpeDb, "JSON_ARRAYAGG")
        groupByCols -= col
        aggregate = true
        replaceCast((row: Row, paramIndex: Int) => {
          getMedian(res.json2array(row.getString(paramIndex)).map(_.toString.toDouble).toSet)
        })

      case "avg" =>
        // Average of unique values (Set semantics)
        selectWithOrder(col, tpeDb, "AVG", "DISTINCT ")
        groupByCols -= col
        aggregate = true
        replaceCast((row: Row, paramIndex: Int) =>
          row.getString(paramIndex).toDouble
        )

      case "variance" =>
        // Variance of unique values (Set semantics)
        selectWithOrder(col, tpeDb, "JSON_ARRAYAGG")
        groupByCols -= col
        aggregate = true
        replaceCast((row: Row, paramIndex: Int) => {
          val doubles = res.json2array(row.getString(paramIndex)).map(_.toString.toDouble).toSet.toList
          varianceOf(doubles: _*)
        })

      case "stddev" =>
        // Standard deviation of unique values (Set semantics)
        selectWithOrder(col, tpeDb, "JSON_ARRAYAGG")
        groupByCols -= col
        aggregate = true
        replaceCast((row: Row, paramIndex: Int) => {
          val doubles = res.json2array(row.getString(paramIndex)).map(_.toString.toDouble).toSet.toList
          stdDevOf(doubles: _*)
        })

      case other => unexpectedKw(other)
    }
  }

  private def selectWithOrder(
    col: String,
    tpeDb: String,
    fn: String,
    distinct: String = "",
  ): Unit = {
    val i  = select.size + 1
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
    tempTables = tempTables :+ s"JSON_TABLE($col, '$$[*]' COLUMNS (vs $tpeDb PATH '$$')) t_$i"
  }

  private def dbType(col: String): String = attrMap(col)._2 match {
    case "String"         => "LONGTEXT"
    case "Int"            => "INT"
    case "Long"           => "BIGINT"
    case "Float"          => "REAL"
    case "Double"         => "DOUBLE"
    case "Boolean"        => "TINYINT(1)"
    case "BigInt"         => "DECIMAL(65, 0)"
    case "BigDecimal"     => "DECIMAL(65, 30)"
    case "Date"           => "BIGINT"
    case "Duration"       => "TINYTEXT"
    case "Instant"        => "TINYTEXT"
    case "LocalDate"      => "TINYTEXT"
    case "LocalTime"      => "TINYTEXT"
    case "LocalDateTime"  => "TINYTEXT"
    case "OffsetTime"     => "TINYTEXT"
    case "OffsetDateTime" => "TINYTEXT"
    case "ZonedDateTime"  => "TINYTEXT"
    case "UUID"           => "TINYTEXT"
    case "URI"            => "TEXT"
    case "Byte"           => "TINYINT"
    case "Short"          => "SMALLINT"
    case "Char"           => "CHAR"
  }

  private def matchArray[T](col: String, set: Set[T], one2json: T => String): String = {
    val size       = set.size
    val jsonValues = set.map(one2json).mkString(", ")
    s"JSON_LENGTH($col) = $size AND JSON_CONTAINS($col, JSON_ARRAY($jsonValues))"
  }

  private def matchArrays[T](col: String, sets: Seq[Set[T]], one2json: T => String): String = {
    sets.map(set =>
      matchArray(col, set, one2json)
    ).mkString("(\n    ", " OR\n    ", "\n  )")
  }

  override protected def setEqual[T](col: String, sets: Seq[Set[T]], res: ResSet[T]): Unit = {
    val setsNonEmpty = sets.filterNot(_.isEmpty)
    setsNonEmpty.length match {
      case 0 => where += (("FALSE", ""))
      case 1 => where += (("", matchArray(col, setsNonEmpty.head, res.one2json)))
      case _ => where += (("", matchArrays(col, setsNonEmpty, res.one2json)))
    }
  }

  override protected def setNeq[T](col: String, sets: Seq[Set[T]], res: ResSet[T]): Unit = {
    val setsNonEmpty = sets.filterNot(_.isEmpty)
    setsNonEmpty.length match {
      case 0 => ()
      case 1 => where += (("", "NOT (" + matchArray(col, setsNonEmpty.head, res.one2json) + ")"))
      case _ => where += (("", "NOT (" + matchArrays(col, setsNonEmpty, res.one2json) + ")"))
    }
  }

  override protected def setNeq2(col: String, filterAttr: String): Unit = {
    where += ((col, "<> " + filterAttr))
  }

  override protected def has[T: ClassTag](col: String, sets: Seq[Set[T]], one2json: T => String): Unit = {
    def containsSet(set: Set[T]): String = {
      val jsonValues = set.map(one2json).mkString(", ")
      s"JSON_CONTAINS($col, JSON_ARRAY($jsonValues))"
    }
    sets.length match {
      case 0 => where += (("FALSE", ""))
      case 1 =>
        val set = sets.head
        set.size match {
          case 0 => where += (("FALSE", ""))
          case 1 => where += (("", s"JSON_CONTAINS($col, JSON_ARRAY(${one2json(set.head)}))"))
          case _ => where += (("", containsSet(set)))
        }
      case _ =>
        val expr = sets
          .filterNot(_.isEmpty)
          .map(containsSet).mkString("(\n    ", " OR\n    ", "\n  )")
        where += (("", expr))
    }
  }

  override protected def has2(col: String, filterAttr: String, cardOne: Boolean, tpe: String): Unit = {
    if (cardOne) {
      where += (("", s"JSON_CONTAINS($col, JSON_ARRAY($filterAttr))"))
    } else {
      where += (("", s"JSON_CONTAINS($col, $filterAttr)"))
    }
  }

  override protected def hasNo[T](col: String, sets: Seq[Set[T]], one2json: T => String): Unit = {
    def notContains(v: T): String = s"NOT JSON_CONTAINS($col, JSON_ARRAY(${one2json(v)}))"
    def notContainsSet(set: Set[T]): String = {
      val jsonValues = set.map(one2json).mkString(", ")
      s"NOT JSON_CONTAINS($col, JSON_ARRAY($jsonValues))"
    }
    sets.length match {
      case 0 => ()
      case 1 =>
        val set = sets.head
        set.size match {
          case 0 => ()
          case 1 => where += (("", notContains(set.head)))
          case _ => where += (("", notContainsSet(set)))
        }
      case _ =>
        val expr = sets
          .filterNot(_.isEmpty)
          .map(notContainsSet)
          .mkString("(\n    ", " AND\n    ", "\n  )")
        where += (("", expr))
    }
  }

  override protected def hasNo2(col: String, filterAttr: String, cardOne: Boolean, tpe: String): Unit = {
    if (cardOne) {
      where += (("", s"NOT JSON_CONTAINS($col, JSON_ARRAY($filterAttr))"))
    } else {
      where += (("", s"NOT JSON_OVERLAPS($col, $filterAttr)"))
    }
  }
}