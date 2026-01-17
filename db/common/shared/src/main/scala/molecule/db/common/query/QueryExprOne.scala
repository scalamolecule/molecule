package molecule.db.common.query

import scala.reflect.ClassTag
import molecule.core.dataModel.*
import molecule.core.dataModel.AttrOp.*
import molecule.core.error.ModelError
import molecule.db.common.javaSql.PrepStmt

/** Base class for cardinality-one attribute expressions
 *
 * H2 implementations serve as the outset for the other databases to override
 */
trait QueryExprOne extends QueryExpr { self: Model2Query & QueryExprRef & SqlQueryBase & LambdasOne =>

  override protected def queryIdMan(a: AttrOneMan): Unit = queryAttrOneMan(a)
  override protected def queryIdTac(a: AttrOneTac): Unit = queryAttrOneTac(a)


  override protected def queryAttrOneMan(attr: AttrOneMan): Unit = {
    attr match {
      case at: AttrOneManID             => man(attr, at.vs, resId1)
      case at: AttrOneManString         => man(attr, at.vs, resString1)
      case at: AttrOneManInt            => man(attr, at.vs, resInt1)
      case at: AttrOneManLong           => man(attr, at.vs, resLong1)
      case at: AttrOneManFloat          => man(attr, at.vs, resFloat1)
      case at: AttrOneManDouble         => man(attr, at.vs, resDouble1)
      case at: AttrOneManBoolean        => man(attr, at.vs, resBoolean1)
      case at: AttrOneManBigInt         => man(attr, at.vs, resBigInt1)
      case at: AttrOneManBigDecimal     => man(attr, at.vs, resBigDecimal1)
      case at: AttrOneManDate           => man(attr, at.vs, resDate1)
      case at: AttrOneManDuration       => man(attr, at.vs, resDuration1)
      case at: AttrOneManInstant        => man(attr, at.vs, resInstant1)
      case at: AttrOneManLocalDate      => man(attr, at.vs, resLocalDate1)
      case at: AttrOneManLocalTime      => man(attr, at.vs, resLocalTime1)
      case at: AttrOneManLocalDateTime  => man(attr, at.vs, resLocalDateTime1)
      case at: AttrOneManOffsetTime     => man(attr, at.vs, resOffsetTime1)
      case at: AttrOneManOffsetDateTime => man(attr, at.vs, resOffsetDateTime1)
      case at: AttrOneManZonedDateTime  => man(attr, at.vs, resZonedDateTime1)
      case at: AttrOneManUUID           => man(attr, at.vs, resUUID1)
      case at: AttrOneManURI            => man(attr, at.vs, resURI1)
      case at: AttrOneManByte           => man(attr, at.vs, resByte1)
      case at: AttrOneManShort          => man(attr, at.vs, resShort1)
      case at: AttrOneManChar           => man(attr, at.vs, resChar1)
    }

  }

  override protected def queryAttrOneTac(attr: AttrOneTac): Unit = {
    if (isOptNested && !attr.isInstanceOf[AttrOneTacID])
      throw ModelError(s"Tacit attributes not allowed in optional nested queries (${attr.name}_).")
    attr match {
      case at: AttrOneTacID             =>
        // Sort each nested level
        addSort(attr, at.ent + ".id")
        tac(attr, at.vs, resId1)
      case at: AttrOneTacString         => tac(attr, at.vs, resString1)
      case at: AttrOneTacInt            => tac(attr, at.vs, resInt1)
      case at: AttrOneTacLong           => tac(attr, at.vs, resLong1)
      case at: AttrOneTacFloat          => tac(attr, at.vs, resFloat1)
      case at: AttrOneTacDouble         => tac(attr, at.vs, resDouble1)
      case at: AttrOneTacBoolean        => tac(attr, at.vs, resBoolean1)
      case at: AttrOneTacBigInt         => tac(attr, at.vs, resBigInt1)
      case at: AttrOneTacBigDecimal     => tac(attr, at.vs, resBigDecimal1)
      case at: AttrOneTacDate           => tac(attr, at.vs, resDate1)
      case at: AttrOneTacDuration       => tac(attr, at.vs, resDuration1)
      case at: AttrOneTacInstant        => tac(attr, at.vs, resInstant1)
      case at: AttrOneTacLocalDate      => tac(attr, at.vs, resLocalDate1)
      case at: AttrOneTacLocalTime      => tac(attr, at.vs, resLocalTime1)
      case at: AttrOneTacLocalDateTime  => tac(attr, at.vs, resLocalDateTime1)
      case at: AttrOneTacOffsetTime     => tac(attr, at.vs, resOffsetTime1)
      case at: AttrOneTacOffsetDateTime => tac(attr, at.vs, resOffsetDateTime1)
      case at: AttrOneTacZonedDateTime  => tac(attr, at.vs, resZonedDateTime1)
      case at: AttrOneTacUUID           => tac(attr, at.vs, resUUID1)
      case at: AttrOneTacURI            => tac(attr, at.vs, resURI1)
      case at: AttrOneTacByte           => tac(attr, at.vs, resByte1)
      case at: AttrOneTacShort          => tac(attr, at.vs, resShort1)
      case at: AttrOneTacChar           => tac(attr, at.vs, resChar1)
    }
  }

  override protected def queryAttrOneOpt(attr: AttrOneOpt): Unit = {
    attr match {
      case at: AttrOneOptID             => opt(attr, at.vs, resOptId)
      case at: AttrOneOptString         => opt(attr, at.vs, resOptString)
      case at: AttrOneOptInt            => opt(attr, at.vs, resOptInt)
      case at: AttrOneOptLong           => opt(attr, at.vs, resOptLong)
      case at: AttrOneOptFloat          => opt(attr, at.vs, resOptFloat)
      case at: AttrOneOptDouble         => opt(attr, at.vs, resOptDouble)
      case at: AttrOneOptBoolean        => opt(attr, at.vs, resOptBoolean)
      case at: AttrOneOptBigInt         => opt(attr, at.vs, resOptBigInt)
      case at: AttrOneOptBigDecimal     => opt(attr, at.vs, resOptBigDecimal)
      case at: AttrOneOptDate           => opt(attr, at.vs, resOptDate)
      case at: AttrOneOptDuration       => opt(attr, at.vs, resOptDuration)
      case at: AttrOneOptInstant        => opt(attr, at.vs, resOptInstant)
      case at: AttrOneOptLocalDate      => opt(attr, at.vs, resOptLocalDate)
      case at: AttrOneOptLocalTime      => opt(attr, at.vs, resOptLocalTime)
      case at: AttrOneOptLocalDateTime  => opt(attr, at.vs, resOptLocalDateTime)
      case at: AttrOneOptOffsetTime     => opt(attr, at.vs, resOptOffsetTime)
      case at: AttrOneOptOffsetDateTime => opt(attr, at.vs, resOptOffsetDateTime)
      case at: AttrOneOptZonedDateTime  => opt(attr, at.vs, resOptZonedDateTime)
      case at: AttrOneOptUUID           => opt(attr, at.vs, resOptUUID)
      case at: AttrOneOptURI            => opt(attr, at.vs, resOptURI)
      case at: AttrOneOptByte           => opt(attr, at.vs, resOptByte)
      case at: AttrOneOptShort          => opt(attr, at.vs, resOptShort)
      case at: AttrOneOptChar           => opt(attr, at.vs, resOptChar)
    }
  }

  protected def man[T: ClassTag](attr: AttrOne, args: Seq[T], res: ResOne[T]): Unit = {
    val col = getCol(attr: AttrOne)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns

    // Choose cast strategy based on context from DataModel
    (isOptNested, insideSubQuery, attr.op.isInstanceOf[AggrFn]) match {
      case (true, _, _) =>
        // Optional nested: use sql2oneOrNull (allows NULL for optional values)
        castStrategy.add(res.sql2oneOrNull)

      case (_, true, true) =>
        // Aggregate in subquery: will be replaced by aggr(), which handles NULL→0 conversion
        // For now, use sql2one (will be replaced anyway)
        castStrategy.add(res.sql2one)

      case (_, true, false) =>
        // Non-aggregate mandatory attr in subquery: NULL means no match, exclude row
        castStrategy.add(res.sql2oneOrNull)
        hasManSubQueryAttr = true

      case _ =>
        // Regular mandatory attribute
        castStrategy.add(res.sql2one)
    }

    addSort(attr, col)
    (attr.filterAttr, attr.subquery) match {
      case (Some((dir, filterPath, filterAttr)), None) =>
        expr2(col, attr.op, filterAttr.name)
      case (None, Some(subQuery))                      =>
        selectSubQuery(subQuery, col, attr)
      case (None, None)                                =>
        expr(attr.ent, attr.attr, col, attr.op, args, attr.sort.isDefined, attr.binding, res)
      case _                                           => throw ModelError(s"Attribute ${attr.name} cannot have both filterAttr and subquery")
    }
  }

  protected def tac[T: ClassTag](attr: AttrOne, args: Seq[T], res: ResOne[T]): Unit = {
    val col = getCol(attr: AttrOne)
    (attr.filterAttr, attr.subquery) match {
      case (Some((dir, filterPath, filterAttr)), None) =>
        // For JOIN subqueries, include filter attribute in SELECT for JOIN ON clause
        // but don't add cast (it's not part of the result, just used for correlation)
        if (insideJoinSubQuery) {
          select += col
          // If this is a JOIN subquery with aggregates, add correlation column to GROUP BY
          if (aggregate) {
            groupBy += col
          }
        }
        expr2(col, attr.op, getCol(filterAttr, filterPath))
      case (None, Some(subQuery))                      =>
        selectSubQuery(subQuery, col, attr)
      case (None, None)                                =>
        expr(attr.ent, attr.attr, col, attr.op, args, attr.sort.isDefined, attr.binding, res)
      case _                                           => throw ModelError(s"Attribute ${attr.name}_ cannot have both filterAttr and subquery")
    }
  }

  protected def opt[T](
    attr: AttrOne,
    optArgs: Option[Seq[T]],
    resOpt: ResOneOpt[T],
  ): Unit = {
    val col = getCol(attr: AttrOne)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns
    castStrategy.add(resOpt.sql2oneOpt)
    addSort(attr, col)
    attr.op match {
      case V     => () // selected col can already be a value or null
      case Eq    => optEqual(col, optArgs, resOpt.one2sql)
      case Neq   => optNeq(col, optArgs, resOpt.one2sql)
      case Lt    => optCompare(col, optArgs, "<", resOpt.one2sql)
      case Gt    => optCompare(col, optArgs, ">", resOpt.one2sql)
      case Le    => optCompare(col, optArgs, "<=", resOpt.one2sql)
      case Ge    => optCompare(col, optArgs, ">=", resOpt.one2sql)
      case other => unexpectedOp(other)
    }
  }

  protected def addSort(attr: AttrOne, col: String): Unit = {
    attr.sort.foreach { sort =>
      val (dir, arity) = (sort.head, sort.substring(1, 2).toInt)
      dir match {
        case 'a' => orderBy += ((level, arity, col, " NULLS FIRST"))
        case 'd' => orderBy += ((level, arity, col, " DESC NULLS LAST"))
      }
    }
  }

  protected def expr[T: ClassTag](
    ent: String,
    attr: String,
    col: String,
    op: Op,
    args: Seq[T],
    hasSort: Boolean,
    binding: Boolean,
    res: ResOne[T],
  ): Unit = op match {
    case V            => attrV(col)
    case Eq           => equal(col, args, res.one2sql, binding, res.bind, res.tpe)
    case Neq          => neq(col, args, res.one2sql, binding, res.bind, res.tpe)
    case Lt           => compare(col, args, "<", res.one2sql, binding, res.bind, res.tpe)
    case Gt           => compare(col, args, ">", res.one2sql, binding, res.bind, res.tpe)
    case Le           => compare(col, args, "<=", res.one2sql, binding, res.bind, res.tpe)
    case Ge           => compare(col, args, ">=", res.one2sql, binding, res.bind, res.tpe)
    case NoValue      => noValue(col)
    case StartsWith   => startsWith(col, args, binding, res.bind)
    case EndsWith     => endsWith(col, args, binding, res.bind)
    case Contains     => contains(col, args, binding, res.bind)
    case Matches      => matches(col, args, binding, res.bind)
    case Remainder    => remainder(col, args)
    case Even         => even(col)
    case Odd          => odd(col)
    case AttrOp.Ceil  => ceil(col)
    case AttrOp.Floor => floor(col)
    case And          => and(col, args.head)
    case Or           => or(col, args.head)
    case Not          => not(col)

    case AggrFn(baseType, kw, n, aggrOp, aggrOpValue) =>
      aggr(baseType, ent, attr, col, kw, n, aggrOp, aggrOpValue, hasSort, res)

    case other => unexpectedOp(other)
  }

  protected def expr2(
    col: String,
    op: Op,
    filterAttr: String
  ): Unit = op match {
    case Eq    => equal2(col, filterAttr)
    case Neq   => neq2(col, filterAttr)
    case Lt    => compare2(col, "<", filterAttr)
    case Gt    => compare2(col, ">", filterAttr)
    case Le    => compare2(col, "<=", filterAttr)
    case Ge    => compare2(col, ">=", filterAttr)
    case other => unexpectedOp(other)
  }

  protected def selectSubQuery(subQuery: SubQuery, col: String, attr: AttrOne): Unit = {
    subQueryIndex += 1
    val alias                        = subQueryAlias
    // isImplicit = true for comparison operations (always Molecule_1, needs "col" alias)
    val (subQuerySql, subQueryCasts) = buildSubQuerySqlWithCasts(
      subQuery.elements, alias, subQuery.optLimit, subQuery.optOffset,
      isImplicit = true
    )
    val opStr                        = getAggrOp(Some(attr.op))
    joins += (("CROSS JOIN", subQuerySql, alias, Nil))
    select += s"$alias.col"
    groupByCols += s"$alias.col"
    where += ((col, s"$opStr $alias.col"))
    subQueryCasts.foreach(castStrategy.add)
  }


  // attr ----------------------------------------------------------------------

  private def attrV(col: String): Unit = {
    if (!isOptNested && !nestedOptRef) {
      setNotNull(col)
    }
  }


  // eq ------------------------------------------------------------------------

  protected def equal[T](
    col: String,
    args: Seq[T],
    one2sql: T => String,
    binding: Boolean,
    bind: (PrepStmt, Int, Int, Value) => Unit,
    tpe: String
  ): Unit = {
    if binding then {
      addBinding(col, bind, "= ?")
    } else {
      where += (args.length match {
        case 1 => (col, "= " + one2sql(args.head))
        case 0 => ("FALSE", "") // Empty Seq of args matches no values
        case _ => (col, args.map(one2sql).mkString("IN (", ", ", ")"))
      })
    }
  }

  protected def optEqual[T](
    col: String,
    optArgs: Option[Seq[T]],
    one2sql: T => String
  ): Unit = {
    optArgs.fold[Unit] {
      setNull(col)
    } {
      case Nil => where += (("FALSE", ""))
      case vs  => equal(col, vs, one2sql, false, null, "")
    }
  }


  // neq -----------------------------------------------------------------------

  protected def neq[T](
    col: String,
    args: Seq[T],
    one2sql: T => String,
    binding: Boolean,
    bind: (PrepStmt, Int, Int, Value) => Unit,
    tpe: String
  ): Unit = {
    if binding then {
      addBinding(col, bind, "<> ?")
    } else {
      where += (args.length match {
        case 1 => (col, "<> " + one2sql(args.head))
        case 0 => (col, "IS NOT NULL ")
        case _ => (col, args.map(one2sql).mkString("NOT IN (", ", ", ")"))
      })
    }
  }

  protected def optNeq[T](
    col: String,
    optArgs: Option[Seq[T]],
    one2sql: T => String
  ): Unit = {
    if (optArgs.isDefined && optArgs.get.nonEmpty) {
      neq(col, optArgs.get, one2sql, false, null, "")
    } else {
      setNotNull(col)
    }
  }


  // compare -------------------------------------------------------------------

  protected def compare[T](
    col: String,
    args: Seq[T],
    op: String,
    one2sql: T => String,
    binding: Boolean = false,
    bind: (PrepStmt, Int, Int, Value) => Unit = null,
    tpe: String = ""
  ): Unit = {
    if binding then {
      addBinding(col, bind, s"$op ?")
    } else {
      where += ((col, op + " " + one2sql(args.head)))
    }
  }

  protected def optCompare[T](
    col: String,
    optArgs: Option[Seq[T]],
    op: String,
    one2sql: T => String
  ): Unit = {
    optArgs.fold[Unit] {
      // Always return empty result when trying to compare None
      where += (("FALSE", ""))
    } { vs =>
      where += ((col, op + " " + one2sql(vs.head)))
    }
  }


  // no value ------------------------------------------------------------------

  protected def noValue(col: String): Unit = {
    unsetNotNull(col)
    setNull(col)
  }


  // string filters ------------------------------------------------------------

  protected def startsWith[T](
    col: String,
    args: Seq[T],
    binding: Boolean,
    bind: (PrepStmt, Int, Int, Value) => Unit
  ): Unit = {
    if binding then {
      where += ((col, "LIKE ?"))
      val paramIndex = binders.length + 1
      bindIndex = bindIndex + 1
      binders += ((ps: PrepStmt) => bind(ps, paramIndex, bindIndex, OneString(s"${bindValues(bindIndex).asInstanceOf[OneString].v}%")))

    } else {
      where += ((col, s"LIKE '${args.head}%'"))
    }
  }

  protected def endsWith[T](
    col: String,
    args: Seq[T],
    binding: Boolean,
    bind: (PrepStmt, Int, Int, Value) => Unit
  ): Unit = {
    if binding then {
      where += ((col, "LIKE ?"))
      val paramIndex = binders.length + 1
      bindIndex = bindIndex + 1
      binders += ((ps: PrepStmt) => bind(ps, paramIndex, bindIndex, OneString(s"%${bindValues(bindIndex).asInstanceOf[OneString].v}")))
    } else {
      where += ((col, s"LIKE '%${args.head}'"))
    }
  }

  protected def contains[T](
    col: String,
    args: Seq[T],
    binding: Boolean,
    bind: (PrepStmt, Int, Int, Value) => Unit
  ): Unit = {
    if binding then {
      where += ((col, "LIKE ?"))
      val paramIndex = binders.length + 1
      bindIndex = bindIndex + 1
      binders += ((ps: PrepStmt) => bind(ps, paramIndex, bindIndex, OneString(s"%${bindValues(bindIndex).asInstanceOf[OneString].v}%")))
    } else {
      where += ((col, s"LIKE '%${args.head}%'"))
    }
  }

  protected def matches[T](
    col: String,
    args: Seq[T],
    binding: Boolean,
    bind: (PrepStmt, Int, Int, Value) => Unit
  ): Unit = {
    if binding then {
      addBinding(col, bind, "~ ?")
    } else {
      val regex = args.head.toString
      if (regex.nonEmpty)
        where += ((col, s"~ '$regex'"))
    }
  }


  // number filters ------------------------------------------------------------

  protected def remainder[T](col: String, args: Seq[T]): Unit = {
    if (args(1).toString == "0") // only whole numbers allowed by the generated code / compiler
      where += ((col, s"% ${args.head} = 0"))
    else
      where += ((col, s"% ${args.head} IN (${args(1)}, -${args(1)})"))
  }

  protected def even(col: String): Unit = where += ((col, s"% 2 = 0"))

  protected def odd(col: String): Unit = where += (("", s"$col % 2 IN (1, -1)"))

  protected def ceil(col: String): Unit = {
    select -= col
    select += s"CEIL($col)"
  }

  protected def floor(col: String): Unit = {
    select -= col
    select += s"FLOOR($col)"
  }

  protected def and[T](col: String, arg: T): Unit = {
    where += ((col, s" AND $arg"))
  }
  protected def or[T](col: String, arg: T): Unit = {
    where += ((s"($col IS NOT NULL AND ($col OR $arg))", ""))
  }
  protected def not[T](col: String): Unit = {
    where += ((s"($col IS NOT NULL AND NOT $col)", ""))
  }


  // aggregation ---------------------------------------------------------------

  protected def aggr[T: ClassTag](
    baseType: String,
    ent: String,
    attr: String,
    col: String,
    fn: String,
    optN: Option[Int],
    aggrOp: Option[Op],
    aggrOpValue: Option[Value],
    hasSort: Boolean,
    res: ResOne[T]
  ): Unit = {
    checkAggrOne()
    lazy val n = optN.getOrElse(0)
    def havingOp(expr: String) = addHaving(baseType, fn, expr, aggrOp, aggrOpValue, res)

    // Replace find/casting with aggregate function/cast
    select -= col
    fn match {
      case "distinct" =>
        aggregate = true
        select += s"ARRAY_AGG(DISTINCT $col)"
        if (!select.contains(col)) groupByCols -= col
        castStrategy.replace(res.array2set)

      case "min" =>
        aggregate = true
        if (insideJoinSubQuery) {
          val alias = col.replace('.', '_') + "_min"
          select += s"MIN($col) AS $alias"
          if (hasSort) {
            val (level, _, _, dir) = orderBy.last
            orderBy.remove(orderBy.size - 1)
            orderBy += ((level, 1, alias, dir))
          }
        } else {
          select += s"MIN($col)"
        }
        if (!select.contains(col)) groupByCols -= col
        castStrategy.replace(subQueryAggrCast(res))
        havingOp(s"MIN($col)")

      case "mins" =>
        aggregate = true
        select +=
          s"""ARRAY_SLICE(
             |    ARRAY_AGG(DISTINCT $col order by $col ASC),
             |    1,
             |    LEAST(
             |      $n,
             |      ARRAY_LENGTH(ARRAY_AGG(DISTINCT $col))
             |    )
             |  )""".stripMargin
        if (!select.contains(col)) groupByCols -= col
        castStrategy.replace(res.array2set)

      case "max" =>
        aggregate = true
        if (insideJoinSubQuery) {
          val alias = col.replace('.', '_') + "_max"
          select += s"MAX($col) AS $alias"
          if (hasSort) {
            val (level, _, _, dir) = orderBy.last
            orderBy.remove(orderBy.size - 1)
            orderBy += ((level, 1, alias, dir))
          }
        } else {
          select += s"MAX($col)"
        }
        if (!select.contains(col)) groupByCols -= col
        castStrategy.replace(subQueryAggrCast(res))
        havingOp(s"MAX($col)")

      case "maxs" =>
        aggregate = true
        select +=
          s"""ARRAY_SLICE(
             |    ARRAY_AGG(DISTINCT $col order by $col DESC),
             |    1,
             |    LEAST(
             |      $n,
             |      ARRAY_LENGTH(ARRAY_AGG(DISTINCT $col))
             |    )
             |  )""".stripMargin
        if (!select.contains(col)) groupByCols -= col
        castStrategy.replace(res.array2set)

      case "sample" =>
        distinct = false
        select += col
        addWhere(col, aggrOp, aggrOpValue, res)
        orderBy += ((level, -1, "RAND()", ""))
        hardLimit = 1

      case "samples" =>
        aggregate = true
        select +=
          s"""ARRAY_SLICE(
             |    ARRAY_AGG(DISTINCT $col order by RAND()),
             |    1,
             |    LEAST(
             |      $n,
             |      ARRAY_LENGTH(ARRAY_AGG(DISTINCT $col))
             |    )
             |  )""".stripMargin
        if (!select.contains(col)) groupByCols -= col
        castStrategy.replace(res.array2set)

      case "count" =>
        aggregate = true
        distinct = false
        selectWithOrder(col, "COUNT", hasSort, "")
        if (!select.contains(col)) groupByCols -= col
        havingOp(s"COUNT($col)")
        castStrategy.replace(toInt)

      case "countDistinct" =>
        aggregate = true
        distinct = false
        selectWithOrder(col, "COUNT", hasSort, aliasSuffix = Some("countDistinct"))
        if (!select.contains(col)) groupByCols -= col
        havingOp(s"COUNT(DISTINCT $col)")
        castStrategy.replace(toInt)

      case "sum" =>
        aggregate = true
        selectWithOrder(col, "SUM", hasSort, "")
        if (!select.contains(col)) groupByCols -= col
        havingOp(s"SUM($col)")
        castStrategy.replace(subQueryAggrCast(res))

      case "median" =>
        aggregate = true
        selectWithOrder(col, "MEDIAN", hasSort, "")
        if (!select.contains(col)) groupByCols -= col
        havingOp(s"MEDIAN($col)")
        castStrategy.replace(subQueryAggrCast(res))

      case "avg" =>
        aggregate = true
        selectWithOrder(col, "AVG", hasSort, "")
        if (!select.contains(col)) groupByCols -= col
        havingOp(s"AVG($col)")
        castStrategy.replace(subQueryAggrCast(res))

      case "variance" =>
        aggregate = true
        selectWithOrder(col, "VAR_POP", hasSort, "", "", "", "", Some("variance"))
        if (!select.contains(col)) groupByCols -= col
        havingOp(s"VAR_POP($col)")
        castStrategy.replace(subQueryAggrCast(res))

      case "stddev" =>
        aggregate = true
        selectWithOrder(col, "STDDEV_POP", hasSort, "", "", "", "", Some("stddev"))
        if (!select.contains(col)) groupByCols -= col
        havingOp(s"STDDEV_POP($col)")
        castStrategy.replace(subQueryAggrCast(res))

      case other => unexpectedKw(other)
    }
  }

  // Helper to wrap aggregate casts in subqueries to convert NULL to typed zero
  private def subQueryAggrCast[T](res: ResOne[T]): Cast = {
    if (!insideSubQuery) {
      res.sql2one
    } else {
      // In subqueries, aggregates may return NULL - convert to typed zero
      (row: RS, paramIndex: Int) => {
        val v = res.sql2oneOrNull(row, paramIndex)
        // sql2oneOrNull returns null for NULL, use typed zero based on type
        if (v == null) {
          res.tpe match {
            case "String" => res.json2tpe("\"\"")  // Empty string as JSON
            case _        => res.json2tpe("0")     // Numeric zero
          }
        } else v
      }
    }
  }

  // filter attribute filters --------------------------------------------------

  protected def equal2(col: String, filterAttr: String): Unit = {
    if (!insideJoinSubQuery) {
      where += ((col, "= " + filterAttr))
    }
    // For JOIN subqueries, skip WHERE correlation - will be extracted for JOIN ON
  }

  protected def neq2(col: String, filterAttr: String): Unit = {
    if (!insideJoinSubQuery) {
      where += ((col, " != " + filterAttr))
    }
  }

  protected def compare2(col: String, op: String, filterAttr: String): Unit = {
    if (!insideJoinSubQuery) {
      where += ((col, op + " " + filterAttr))
    }
  }


  // helpers -------------------------------------------------------------------

  protected def selectWithOrder(
    col: String,
    fn: String,
    hasSort: Boolean = false,
    distinct: String = "DISTINCT ",
    cast: String = "",
    prefix: String = "",
    suffix: String = "",
    aliasSuffix: Option[String] = None
  ): Unit = {
    if (hasSort || insideJoinSubQuery) {
      // order by aggregate alias OR inside JOIN subquery (needs alias for outer query reference)
      val alias = col.replace('.', '_') + "_" + aliasSuffix.getOrElse(fn.toLowerCase)
      select += s"$prefix$fn($distinct$col$cast)$suffix AS $alias"
      if (hasSort) {
        val (level, _, _, dir) = orderBy.last
        orderBy.remove(orderBy.size - 1)
        orderBy += ((level, 1, alias, dir))
      }
    } else {
      select += s"$prefix$fn($distinct$col$cast)$suffix"
    }
  }

  private def getAggrOp(aggrOp: Option[Op]): String = {
    aggrOp match {
      case Some(Eq)  => "="
      case Some(Neq) => "<>"
      case Some(Lt)  => "<"
      case Some(Le)  => "<="
      case Some(Gt)  => ">"
      case Some(Ge)  => ">="
      case _         => ""
    }
  }

  protected def addHaving[T: ClassTag](
    baseType: String,
    fn: String,
    expr: String,
    aggrOp: Option[Op],
    aggrOpValue: Option[Value],
    res: ResOne[T],
    prefix: String = "",
    suffix: String = "",
  ): Unit = {
    val op = getAggrOp(aggrOp)
    if (baseType == "Float" && aggrOp.isDefined) {
      if (List("sum", "median", "avg", "variance", "stddev").contains(fn)) {
        throw ModelError(
          "sum/median/avg/variance/stddev operations on aggregated Float values are not supported " +
            "to avoid floating precision problems."
        )
      }
    }
    if (op.nonEmpty) {
      aggrOpValue.fold {
        val paramIndex = binders.length + 1
        bindIndex = bindIndex + 1
        val bindIndexStable = bindIndex
        binders += ((ps: PrepStmt) => res.bind(ps, paramIndex, bindIndexStable, bindValues(bindIndexStable)))
        having += s"$expr $op ?"
      } {
        case OneString(v)         => having += s"$expr $op $prefix" + one2sqlString(v) + suffix
        case OneInt(v)            => having += s"$expr $op $prefix" + one2sqlInt(v) + suffix
        case OneLong(v)           => having += s"$expr $op $prefix" + one2sqlLong(v) + suffix
        case OneFloat(v)          => having += s"$expr $op $prefix" + one2sqlFloat(v) + suffix
        case OneDouble(v)         => having += s"$expr $op $prefix" + one2sqlDouble(v) + suffix
        case OneBoolean(v)        => having += s"$expr $op $prefix" + one2sqlBoolean(v) + suffix
        case OneBigInt(v)         => having += s"$expr $op $prefix" + one2sqlBigInt(v) + suffix
        case OneBigDecimal(v)     => having += s"$expr $op $prefix" + one2sqlBigDecimal(v) + suffix
        case OneDate(v)           => having += s"$expr $op $prefix" + one2sqlDate(v) + suffix
        case OneDuration(v)       => having += s"$expr $op $prefix" + one2sqlDuration(v) + suffix
        case OneInstant(v)        => having += s"$expr $op $prefix" + one2sqlInstant(v) + suffix
        case OneLocalDate(v)      => having += s"$expr $op $prefix" + one2sqlLocalDate(v) + suffix
        case OneLocalTime(v)      => having += s"$expr $op $prefix" + one2sqlLocalTime(v) + suffix
        case OneLocalDateTime(v)  => having += s"$expr $op $prefix" + one2sqlLocalDateTime(v) + suffix
        case OneOffsetTime(v)     => having += s"$expr $op $prefix" + one2sqlOffsetTime(v) + suffix
        case OneOffsetDateTime(v) => having += s"$expr $op $prefix" + one2sqlOffsetDateTime(v) + suffix
        case OneZonedDateTime(v)  => having += s"$expr $op $prefix" + one2sqlZonedDateTime(v) + suffix
        case OneUUID(v)           => having += s"$expr $op $prefix" + one2sqlUUID(v) + suffix
        case OneURI(v)            => having += s"$expr $op $prefix" + one2sqlURI(v) + suffix
        case OneByte(v)           => having += s"$expr $op $prefix" + one2sqlByte(v) + suffix
        case OneShort(v)          => having += s"$expr $op $prefix" + one2sqlShort(v) + suffix
        case OneChar(v)           => having += s"$expr $op $prefix" + one2sqlChar(v) + suffix
        case _                    => throw new Exception("Unexpected Value type for aggregation Having clause.")
      }
    }
  }

  protected def addWhere[T: ClassTag](expr: String, aggrOp: Option[Op], aggrOpValue: Option[Value], res: ResOne[T]): Unit = {
    val op = getAggrOp(aggrOp)
    if (op.nonEmpty) {
      aggrOpValue.fold {
        val paramIndex = binders.length + 1
        bindIndex = bindIndex + 1
        val bindIndexStable = bindIndex
        binders += ((ps: PrepStmt) => res.bind(ps, paramIndex, bindIndexStable, bindValues(bindIndexStable)))
        where += ((s"$expr $op ?", ""))
      } {
        case OneString(v)         => where += ((s"$expr $op " + one2sqlString(v), ""))
        case OneInt(v)            => where += ((s"$expr $op " + one2sqlInt(v), ""))
        case OneLong(v)           => where += ((s"$expr $op " + one2sqlLong(v), ""))
        case OneFloat(v)          => where += ((s"$expr $op " + one2sqlFloat(v), ""))
        case OneDouble(v)         => where += ((s"$expr $op " + one2sqlDouble(v), ""))
        case OneBoolean(v)        => where += ((s"$expr $op " + one2sqlBoolean(v), ""))
        case OneBigInt(v)         => where += ((s"$expr $op " + one2sqlBigInt(v), ""))
        case OneBigDecimal(v)     => where += ((s"$expr $op " + one2sqlBigDecimal(v), ""))
        case OneDate(v)           => where += ((s"$expr $op " + one2sqlDate(v), ""))
        case OneDuration(v)       => where += ((s"$expr $op " + one2sqlDuration(v), ""))
        case OneInstant(v)        => where += ((s"$expr $op " + one2sqlInstant(v), ""))
        case OneLocalDate(v)      => where += ((s"$expr $op " + one2sqlLocalDate(v), ""))
        case OneLocalTime(v)      => where += ((s"$expr $op " + one2sqlLocalTime(v), ""))
        case OneLocalDateTime(v)  => where += ((s"$expr $op " + one2sqlLocalDateTime(v), ""))
        case OneOffsetTime(v)     => where += ((s"$expr $op " + one2sqlOffsetTime(v), ""))
        case OneOffsetDateTime(v) => where += ((s"$expr $op " + one2sqlOffsetDateTime(v), ""))
        case OneZonedDateTime(v)  => where += ((s"$expr $op " + one2sqlZonedDateTime(v), ""))
        case OneUUID(v)           => where += ((s"$expr $op " + one2sqlUUID(v), ""))
        case OneURI(v)            => where += ((s"$expr $op " + one2sqlURI(v), ""))
        case OneByte(v)           => where += ((s"$expr $op " + one2sqlByte(v), ""))
        case OneShort(v)          => where += ((s"$expr $op " + one2sqlShort(v), ""))
        case OneChar(v)           => where += ((s"$expr $op " + one2sqlChar(v), ""))
        case _                    => throw new Exception("Unexpected Value type for aggregation Having clause.")
      }
    }
  }

  protected val noStatisticalFunctionsInSubquery =
    "Median, variance and stddev in subqueries not supported for this database."
}