package molecule.sql.mariadb.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.sql.core.javaSql.PrepStmt
import molecule.sql.core.query.{ResolveExprSeq, ResolveExprSet, SqlQueryBase}
import scala.reflect.ClassTag
import scala.util.Random


trait ResolveExprSeq_mariadb
  extends ResolveExprSeq
    with LambdasSeq_mariadb { self: SqlQueryBase =>


//  override protected def setMan[T](
//    attr: Attr, tpe: String, args: Seq[Set[T]], res: ResSet[T]
//  ): Unit = {
//    val col = getCol(attr: Attr)
//    select += col
//    groupByCols += col // if we later need to group by non-aggregated columns
//    if (!isNestedOpt) {
//      notNull += col
//    }
//    addCast((row: RS, paramIndex: Int) =>
//      res.json2array(row.getString(paramIndex)).toSet
//    )
//    attr.filterAttr.fold {
//      val pathAttr = path :+ attr.cleanAttr
//      if (filterAttrVars.contains(pathAttr) && attr.op != V) {
//        noCardManyFilterAttrExpr(attr)
//      }
//      setExpr(col, attr.op, args, res, true)
//    } {
//      case (dir, filterPath, filterAttr) => filterAttr match {
//        case filterAttr: AttrOne => setExpr2(col, attr.op, filterAttr.name, true, tpe, res, true)
//        case filterAttr          =>
//          setExpr2(col, attr.op, filterAttr.name, false, tpe, res, true)
//      }
//    }
//  }

//  override protected def seqMan[T](
//    attr: Attr, args: Seq[T], res: ResSeq[T]
//  ): Unit = {
//    val col = getCol(attr: Attr)
//    select += col
//    if (!isNestedOpt) {
//      notNull += col
//    }
//    addCast(res.sql2list)
//    attr.filterAttr.fold {
//      val pathAttr = path :+ attr.cleanAttr
//      if (filterAttrVars.contains(pathAttr) && attr.op != V) {
//        noCardManyFilterAttrExpr(attr)
//      }
//      seqExpr(attr, col, args, res, true)
//    } {
//      case (dir, filterPath, filterAttr) => filterAttr match {
//        case filterAttr: AttrOne => seqExpr2(col, attr.op, filterAttr.name)
//        case filterAttr          => seqExpr2(col, attr.op, filterAttr.name)
//      }
//    }
//  }


  override protected def seqExpr[T](
    attr: Attr, col: String, seq: Seq[T], res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    attr.op match {
      case V         => seqAttr(col, res, mandatory)
//      case Eq        => noCollectionMatching(attr)
//      case Neq       => setNeq(col, sets, res, mandatory)
//      case Has       => has(col, sets, res, res.one2sql, mandatory)
//      case HasNo     => hasNo(col, sets, res, res.one2sql, mandatory)
//      case NoValue   => setNoValue(col)
//      case Fn(kw, n) => setAggr(col, kw, n, res)
      case other     => unexpectedOp(other)
    }
  }


  override protected def seqOpt[T](
    attr: Attr, resOpt: ResSeqOpt[T], res: ResSeq[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns
//    addCast(resOpt.sql2listOpt)

    addCast((row: RS, paramIndex: Int) => row.getString(paramIndex) match {
      case null | "[]" => Option.empty[Set[T]]
      case json        =>

        Some(res.json2array(json).toList)
    })

    attr.op match {
      case V     => () // get array as-is
      case Eq    => noCollectionMatching(attr)
      case other => unexpectedOp(other)
    }
  }


  // attr ----------------------------------------------------------------------

  private def seqAttr[T](
    col: String, res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    if (mandatory) {
//      selectWithOrder(col, res.tpeDb, "JSON_ARRAYAGG")
//      select -= col
//      groupByCols -= col
//      having += "COUNT(*) > 0"
//      aggregate = true
      replaceCast((row: RS, paramIndex: Int) =>{
//        val json = row.getString(paramIndex)
//        println("json: " + json)
//        res.json2array(json).toList

        res.json2array(row.getString(paramIndex)).toList
      }
      )
    }
  }

//  private def seqOptAttr[T](col: String, res: ResSeq[T]): Unit = {
//    select -= col
//    selectWithOrder(col, res.tpeDb, "JSON_ARRAYAGG", "DISTINCT ", true)
//    groupByCols -= col
//    aggregate = true
//    replaceCast((row: RS, paramIndex: Int) =>
//      res.json2optArray(row.getString(paramIndex)).map(_.toList)
//    )
//  }


//  // eq ------------------------------------------------------------------------
//
//  override protected def setEqual[T](col: String, sets: Seq[Set[T]], res: ResSet[T]): Unit = {
//    val setsNonEmpty = sets.filterNot(_.isEmpty)
//    setsNonEmpty.length match {
//      case 0 => where += (("FALSE", ""))
//      case 1 => where += (("", matchArray(col, setsNonEmpty.head, res.one2json)))
//      case _ => where += (("", matchArrays(col, setsNonEmpty, res.one2json)))
//    }
//  }
//
//  override protected def setOptEqual[T](
//    col: String, optSets: Option[Seq[Set[T]]], res: ResSet[T]
//  ): Unit = {
//    optSets.fold[Unit] {
//      where += ((col, s"IS NULL"))
//    } { sets =>
//      setEqual(col, sets, res)
//      replaceCast((row: RS, paramIndex: Int) =>
//        res.json2optArray(row.getString(paramIndex)).map(_.toSet)
//      )
//    }
//  }
//
//
//  // neq -----------------------------------------------------------------------
//
//  override protected def setNeq[T](
//    col: String, sets: Seq[Set[T]], res: ResSet[T], mandatory: Boolean
//  ): Unit = {
//    if (mandatory) {
//      select -= col
//      selectWithOrder(col, res.tpeDb, "JSON_ARRAYAGG", optional = true)
//      groupByCols -= col
//      aggregate = true
//    }
//    val setsNonEmpty = sets.filterNot(_.isEmpty)
//    setsNonEmpty.length match {
//      case 0 => notNull += col
//      case 1 => where += (("", "NOT (" + matchArray(col, setsNonEmpty.head, res.one2json) + ")"))
//      case _ => where += (("", "NOT (" + matchArrays(col, setsNonEmpty, res.one2json) + ")"))
//    }
//  }
//
//  override protected def setOptNeq[T](
//    col: String, optSets: Option[Seq[Set[T]]], res: ResSet[T]
//  ): Unit = {
//    optSets match {
//      case None | Some(Nil) =>
//        setOptAttr(col, res)
//        notNull += col
//
//      case Some(sets) => setNeq(col, sets, res, true)
//        setNeq(col, sets, res, true)
//        replaceCast((row: RS, paramIndex: Int) =>
//          res.json2optArray(row.getString(paramIndex)).map(_.toSet)
//        )
//    }
//  }
//
//
//  // has -----------------------------------------------------------------------
//
//  override protected def has[T](
//    col: String, sets: Seq[Set[T]], res: ResSet[T], one2json: T => String, mandatory: Boolean
//  ): Unit = {
//    def containsSet(set: Set[T]): String = {
//      val jsonValues = set.map(one2json).mkString(", ")
//      s"JSON_CONTAINS($col, JSON_ARRAY($jsonValues))"
//    }
//    if (mandatory) {
//      select -= col
//      selectWithOrder(col, res.tpeDb, "JSON_ARRAYAGG", optional = true)
//      groupByCols -= col
//      aggregate = true
//    }
//    sets.length match {
//      case 0 => where += (("FALSE", ""))
//      case 1 =>
//        val set = sets.head
//        set.size match {
//          case 0 => where += (("FALSE", ""))
//          case 1 => where += (("", s"JSON_CONTAINS($col, JSON_ARRAY(${one2json(set.head)}))"))
//          case _ => where += (("", containsSet(set)))
//        }
//      case _ =>
//        val expr = sets
//          .filterNot(_.isEmpty)
//          .map(containsSet).mkString("(\n    ", " OR\n    ", "\n  )")
//        where += (("", expr))
//    }
//  }
//
//  override protected def optHas[T](
//    col: String,
//    optSets: Option[Seq[Set[T]]],
//    res: ResSet[T],
//    one2sql: T => String,
//  ): Unit = {
//    optSets.fold[Unit] {
//      where += ((col, s"IS NULL"))
//    } { sets =>
//      has(col, sets, res, one2sql, true)
//      replaceCast((row: RS, paramIndex: Int) =>
//        res.json2optArray(row.getString(paramIndex)).map(_.toSet)
//      )
//    }
//  }
//
//
//  // hasNo ---------------------------------------------------------------------
//
//  override protected def hasNo[T](
//    col: String, sets: Seq[Set[T]], res: ResSet[T], one2json: T => String, mandatory: Boolean
//  ): Unit = {
//    def notContains(v: T): String = s"NOT JSON_CONTAINS($col, JSON_ARRAY(${one2json(v)}))"
//    def notContainsSet(set: Set[T]): String = {
//      val jsonValues = set.map(one2json).mkString(", ")
//      s"NOT JSON_CONTAINS($col, JSON_ARRAY($jsonValues))"
//    }
//    if (mandatory) {
//      selectWithOrder(col, res.tpeDb, "JSON_ARRAYAGG", optional = true)
//      select -= col
//      groupByCols -= col
//      aggregate = true
//    }
//    sets.length match {
//      case 0 => ()
//      case 1 =>
//        val set = sets.head
//        set.size match {
//          case 0 => ()
//          case 1 => where += (("", notContains(set.head)))
//          case _ => where += (("", notContainsSet(set)))
//        }
//      case _ =>
//        val expr = sets
//          .filterNot(_.isEmpty)
//          .map(notContainsSet)
//          .mkString("(\n    ", " AND\n    ", "\n  )")
//        where += (("", expr))
//    }
//  }
//
//  override protected def optHasNo[T](
//    col: String,
//    optSets: Option[Seq[Set[T]]],
//    res: ResSet[T],
//    one2sql: T => String
//  ): Unit = {
//    optSets.fold[Unit] {
//      setOptAttr(col, res)
//    } { sets =>
//      hasNo(col, sets, res, one2sql, true)
//      replaceCast((row: RS, paramIndex: Int) =>
//        res.json2optArray(row.getString(paramIndex)).map(_.toSet)
//      )
//    }
//    // Only asserted values
//    notNull += col
//  }
//
//
//  // aggregation ---------------------------------------------------------------
//
//  override protected def setAggr[T](
//    col: String, fn: String, optN: Option[Int], res: ResSet[T]
//  ): Unit = {
//    checkAggrSet()
//    lazy val sep     = "0x1D" // Use ascii Group Selector to separate concatenated values
//    lazy val sepChar = 29.toChar
//    lazy val tpeDb   = res.tpeDb
//    select -= col
//    lazy val n = optN.getOrElse(0)
//    fn match {
//      case "distinct" =>
//        noBooleanSetAggr(res)
//        select += s"JSON_ARRAYAGG($col)"
//        groupByCols -= col
//        aggregate = true
//        replaceCast((row: RS, paramIndex: Int) => {
//          val json = row.getString(paramIndex)
//          if (row.wasNull()) {
//            Set.empty[Set[T]]
//          } else {
//            // Need res.json2array for various types with/without quotes
//            json //                              "[[1, 2], [3, 4], [5, 6]]"
//              .substring(1, json.length - 1) //  "[1, 2], [3, 4], [5, 6]"
//              .replace("],[", "]],[[") //        "[1, 2]], [[3, 4]], [[5, 6]"
//              .split("], ?\\[") //               Array("[1, 2]", "[3, 4]", "[5, 6]")
//              .map(res.json2array(_).toSet) //   Array(Set(1, 2), Set(3, 4), Set(5, 6))
//              .toSet //                          Set(Set(1, 2), Set(3, 4), Set(5, 6))
//          }
//        })
//
//      case "min" =>
//        noBooleanSetAggr(res)
//        selectWithOrder(col, tpeDb, "MIN")
//        groupByCols -= col
//        aggregate = true
//        replaceCast((row: RS, paramIndex: Int) =>
//          Set(res.json2tpe(row.getString(paramIndex)))
//        )
//
//      case "mins" =>
//        noBooleanSetAggr(res)
//        groupByCols -= col
//        aggregate = true
//        val i = getIndex
//        select += s"GROUP_CONCAT(DISTINCT t_$i.vs SEPARATOR $sep)"
//        tempTables += s"JSON_TABLE($col, '$$[*]' COLUMNS (vs $tpeDb PATH '$$')) t_$i"
//        replaceCast((row: RS, paramIndex: Int) =>
//          row.getString(paramIndex).split(sepChar).map(res.json2tpe).take(n).toSet
//        )
//
//      case "max" =>
//        noBooleanSetAggr(res)
//        selectWithOrder(col, tpeDb, "MAX")
//        groupByCols -= col
//        aggregate = true
//        replaceCast((row: RS, paramIndex: Int) =>
//          Set(res.json2tpe(row.getString(paramIndex)))
//        )
//
//      case "maxs" =>
//        noBooleanSetAggr(res)
//        groupByCols -= col
//        aggregate = true
//        val i = getIndex
//        select += s"GROUP_CONCAT(DISTINCT t_$i.vs ORDER BY t_$i.vs DESC SEPARATOR $sep)"
//        tempTables += s"JSON_TABLE($col, '$$[*]' COLUMNS (vs $tpeDb PATH '$$')) t_$i"
//        replaceCast((row: RS, paramIndex: Int) =>
//          row.getString(paramIndex).split(sepChar).map(res.json2tpe).take(n).toSet
//        )
//
//      case "sample" =>
//        noBooleanSetAggr(res)
//        selectWithOrder(col, tpeDb, "JSON_ARRAYAGG")
//        replaceCast((row: RS, paramIndex: Int) => {
//          val array = res.json2array(row.getString(paramIndex))
//          val rnd   = new Random().nextInt(array.length)
//          Set(array(rnd))
//        })
//
//      case "samples" =>
//        noBooleanSetAggr(res)
//        selectWithOrder(col, tpeDb, "JSON_ARRAYAGG")
//        replaceCast((row: RS, paramIndex: Int) => {
//          val array = res.json2array(row.getString(paramIndex))
//          Random.shuffle(array.toSet).take(n)
//        })
//
//      case "count" =>
//        noBooleanSetCounts(n)
//        select += s"GROUP_CONCAT($col)"
//        groupByCols -= col
//        aggregate = true
//        replaceCast((row: RS, paramIndex: Int) => {
//          val json = row.getString(paramIndex)
//          if (row.wasNull()) {
//            0
//          } else {
//            json.substring(1, json.length - 1).split("\\]?, ?\\[?").length
//          }
//        })
//
//      case "countDistinct" =>
//        noBooleanSetCounts(n)
//        // Count of unique values (Set semantics)
//        selectWithOrder(col, dbType(col), "COUNT", "DISTINCT ")
//        //        selectWithOrder(col, tpeDb, "COUNT", "DISTINCT ")
//        groupByCols -= col
//        aggregate = true
//        replaceCast(toInt)
//
//      case "sum" =>
//        groupByCols -= col
//        aggregate = true
//        select += s"GROUP_CONCAT($col)"
//        replaceCast((row: RS, paramIndex: Int) => {
//          val json = row.getString(paramIndex)
//          if (row.wasNull()) {
//            Set.empty[T]
//          } else {
//            Set(
//              res.stringArray2sum(
//                json.substring(1, json.length - 1).split("\\]?, ?\\[?")
//              )
//            )
//          }
//        })
//
//
//      case "median" =>
//        selectWithOrder(col, tpeDb, "JSON_ARRAYAGG")
//        groupByCols -= col
//        aggregate = true
//        replaceCast((row: RS, paramIndex: Int) =>
//          getMedian(res.json2array(row.getString(paramIndex))
//            .map(_.toString.toDouble).toList)
//        )
//
//      case "avg" =>
//        selectWithOrder(col, tpeDb, "AVG")
//        groupByCols -= col
//        aggregate = true
//        replaceCast((row: RS, paramIndex: Int) =>
//          row.getString(paramIndex).toDouble
//        )
//
//      case "variance" =>
//        selectWithOrder(col, tpeDb, "JSON_ARRAYAGG")
//        groupByCols -= col
//        aggregate = true
//        replaceCast((row: RS, paramIndex: Int) => {
//          val doubleSet = res.json2array(row.getString(paramIndex))
//            .map(_.toString.toDouble).toList
//          varianceOf(doubleSet: _*)
//        })
//
//      case "stddev" =>
//        selectWithOrder(col, tpeDb, "JSON_ARRAYAGG")
//        groupByCols -= col
//        aggregate = true
//        replaceCast((row: RS, paramIndex: Int) => {
//          val doubleSet = res.json2array(row.getString(paramIndex))
//            .map(_.toString.toDouble).toList
//          stdDevOf(doubleSet: _*)
//        })
//
//      case other => unexpectedKw(other)
//    }
//  }
//
//
//  // filter attribute ----------------------------------------------------------
//
//
//  override protected def setEqual2[T](
//    col: String, filterAttr: String, res: ResSet[T], mandatory: Boolean
//  ): Unit = {
//    if (mandatory) {
//      val i = getIndex
//      select -= col
//      select += s"JSON_ARRAYAGG(t_$i.vs)"
//      having += "COUNT(*) > 0"
//      aggregate = true
//      groupByCols -= col
//      val tpeDb = res.tpeDb
//      tempTables += s"JSON_TABLE($col, '$$[*]' COLUMNS (vs $tpeDb PATH '$$')) t_$i"
//    }
//    where += ((col, "= " + filterAttr))
//  }
//
//  override protected def setNeq2(col: String, filterAttr: String): Unit = {
//    where += ((col, "<> " + filterAttr))
//  }
//
//  override protected def has2[T](
//    col: String, filterAttr: String, cardOne: Boolean, tpe: String,
//    res: ResSet[T], mandatory: Boolean
//  ): Unit = {
//    if (cardOne) {
//      where += (("", s"JSON_CONTAINS($col, JSON_ARRAY($filterAttr))"))
//    } else {
//      if (mandatory) {
//        val i = getIndex
//        select -= col
//        select += s"JSON_ARRAYAGG(t_$i.vs)"
//        having += "COUNT(*) > 0"
//        aggregate = true
//        groupByCols -= col
//        val tpeDb = res.tpeDb
//        tempTables += s"JSON_TABLE($col, '$$[*]' COLUMNS (vs $tpeDb PATH '$$')) t_$i"
//
//      }
//      where += (("", s"JSON_CONTAINS($col, $filterAttr)"))
//    }
//  }
//
//  override protected def hasNo2[T](
//    col: String, filterAttr: String, cardOne: Boolean, tpe: String,
//    res: ResSet[T], mandatory: Boolean
//  ): Unit = {
//    if (cardOne) {
//      if (mandatory) {
//        val i = getIndex
//        select -= col
//        select += s"JSON_ARRAYAGG(t_$i.vs)"
//        having += "COUNT(*) > 0"
//        aggregate = true
//        groupByCols -= col
//        val tpeDb = res.tpeDb
//        tempTables += s"JSON_TABLE($col, '$$[*]' COLUMNS (vs $tpeDb PATH '$$')) t_$i"
//      }
//      where += (("", s"NOT JSON_CONTAINS($col, JSON_ARRAY($filterAttr))"))
//    } else {
//      where += (("", s"NOT JSON_OVERLAPS($col, $filterAttr)"))
//    }
//  }
//



//  // byte array ----------------------------------------------------------------
//
//  private def manByteArray(attr: Attr, byteArray: Array[Byte]): Unit = {
//    val col = getCol(attr: Attr)
//    select += col
//    if (!isNestedOpt) {
//      notNull += col
//    }
//    attr.filterAttr.fold {
//      byteArrayOps(attr, col, byteArray)
//    } { _ =>
//      throw ModelError(s"Filter attributes not allowed with byte arrays.")
//    }
//    // return Byte array as-is
//    addCast((row: RS, paramIndex: Int) => row.getBytes(paramIndex))
//  }
//
//  private def tacByteArray(attr: Attr, vs: Array[Byte]): Unit = {
//    val col = getCol(attr: Attr)
//    if (!isNestedOpt) {
//      notNull += col
//    }
//    attr.filterAttr.fold {
//      byteArrayOps(attr, col, vs)
//    } { _ =>
//      throw ModelError(s"Filter attributes not allowed with byte arrays.")
//    }
//  }
//
//  private def byteArrayOps(attr: Attr, col: String, byteArray: Array[Byte]): Unit = {
//    attr.op match {
//      case V  => ()
//      case Eq =>
//        if (byteArray.length != 0) {
//          where += ((col, s"= ?"))
//          val paramIndex = inputs.size + 1
//          inputs += ((ps: PrepStmt) => ps.setBytes(paramIndex, byteArray))
//
//        } else {
//          // Get none
//          where += (("FALSE", ""))
//        }
//
//      case NoValue =>
//        // Get none
//        where += (("FALSE", ""))
//
//      case Neq =>
//        if (byteArray.length != 0) {
//          where += ((col, s"!= ?"))
//          val paramIndex = inputs.size + 1
//          inputs += ((ps: PrepStmt) => ps.setBytes(paramIndex, byteArray))
//
//        } else {
//          // get all
//        }
//
//      case _ => throw ModelError(
//        s"Byte arrays (${attr.cleanName}) can only be retrieved as-is. Filters not allowed.")
//    }
//  }
//
//  private def optByteArray(attr: Attr): Unit = {
//    attr.op match {
//      case V =>
//        val col = getCol(attr: Attr)
//        select += col
//        groupByCols += col // if we later need to group by non-aggregated columns
//
//        // return optional Byte array as-is
//        addCast((row: RS, paramIndex: Int) =>
//          row.getBytes(paramIndex) match {
//            case null      => Option.empty[Array[Byte]]
//            case byteArray => Some(byteArray)
//          }
//        )
//
//      case _ => throw ModelError(
//        s"Byte arrays (${attr.cleanName}) can only be retrieved as-is. Filters not allowed."
//      )
//    }
//  }

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

//  private def dbType(col: String): String = {
//    attrMap(col)._2 match {
//      case "String"         => "LONGTEXT"
//      case "Int"            => "INT"
//      case "Long"           => "BIGINT"
//      case "Float"          => "REAL"
//      case "Double"         => "DOUBLE"
//      case "Boolean"        => "TINYINT(1)"
//      case "BigInt"         => "DECIMAL(65, 0)"
//      case "BigDecimal"     => "DECIMAL(65, 30)"
//      case "Date"           => "BIGINT"
//      case "Duration"       => "TINYTEXT"
//      case "Instant"        => "TINYTEXT"
//      case "LocalDate"      => "TINYTEXT"
//      case "LocalTime"      => "TINYTEXT"
//      case "LocalDateTime"  => "TINYTEXT"
//      case "OffsetTime"     => "TINYTEXT"
//      case "OffsetDateTime" => "TINYTEXT"
//      case "ZonedDateTime"  => "TINYTEXT"
//      case "UUID"           => "TINYTEXT"
//      case "URI"            => "TEXT"
//      case "Byte"           => "TINYINT"
//      case "Short"          => "SMALLINT"
//      case "Char"           => "CHAR"
//    }
//  }
//
//  private def matchArray[T](
//    col: String, set: Set[T], one2json: T => String
//  ): String = {
//    val size       = set.size
//    val jsonValues = set.map(one2json).mkString(", ")
//    s"JSON_LENGTH($col) = $size AND JSON_CONTAINS($col, JSON_ARRAY($jsonValues))"
//  }
//
//  private def matchArrays[T](
//    col: String, sets: Seq[Set[T]], one2json: T => String
//  ): String = {
//    sets.map(set =>
//      matchArray(col, set, one2json)
//    ).mkString("(\n    ", " OR\n    ", "\n  )")
//  }
}