package molecule.sql.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import scala.reflect.ClassTag

trait ResolveExprOne[Tpl] { self: SqlModel2Query[Tpl] with LambdasOne =>

  protected def resolveAttrOneMan(attr: AttrOneMan): Unit = {
    aritiesAttr()
    attr match {
      case at: AttrOneManString     => man(attr, at.vs, resString1)
      case at: AttrOneManInt        => man(attr, at.vs, resInt1)
      case at: AttrOneManLong       => manLong(attr, at.vs, resLong1)
      case at: AttrOneManFloat      => man(attr, at.vs, resFloat1)
      case at: AttrOneManDouble     => man(attr, at.vs, resDouble1)
      case at: AttrOneManBoolean    => man(attr, at.vs, resBoolean1)
      case at: AttrOneManBigInt     => man(attr, at.vs, resBigInt1)
      case at: AttrOneManBigDecimal => man(attr, at.vs, resBigDecimal1)
      case at: AttrOneManDate       => man(attr, at.vs, resDate1)
      case at: AttrOneManUUID       => man(attr, at.vs, resUUID1)
      case at: AttrOneManURI        => man(attr, at.vs, resURI1)
      case at: AttrOneManByte       => man(attr, at.vs, resByte1)
      case at: AttrOneManShort      => man(attr, at.vs, resShort1)
      case at: AttrOneManChar       => man(attr, at.vs, resChar1)
    }
  }

  protected def resolveAttrOneTac(attr: AttrOneTac): Unit = {
    if (isNestedOpt && !isTxMetaData)
      throw ModelError("Tacit attributes not allowed in optional nested queries. Found: " + attr.name)
    attr match {
      case at: AttrOneTacString     => tac(attr, at.vs, resString1)
      case at: AttrOneTacInt        => tac(attr, at.vs, resInt1)
      case at: AttrOneTacLong       => tac(attr, at.vs, resLong1)
      case at: AttrOneTacFloat      => tac(attr, at.vs, resFloat1)
      case at: AttrOneTacDouble     => tac(attr, at.vs, resDouble1)
      case at: AttrOneTacBoolean    => tac(attr, at.vs, resBoolean1)
      case at: AttrOneTacBigInt     => tac(attr, at.vs, resBigInt1)
      case at: AttrOneTacBigDecimal => tac(attr, at.vs, resBigDecimal1)
      case at: AttrOneTacDate       => tac(attr, at.vs, resDate1)
      case at: AttrOneTacUUID       => tac(attr, at.vs, resUUID1)
      case at: AttrOneTacURI        => tac(attr, at.vs, resURI1)
      case at: AttrOneTacByte       => tac(attr, at.vs, resByte1)
      case at: AttrOneTacShort      => tac(attr, at.vs, resShort1)
      case at: AttrOneTacChar       => tac(attr, at.vs, resChar1)
    }
  }

  protected def resolveAttrOneOpt(attr: AttrOneOpt): Unit = {
    aritiesAttr()
    hasOptAttr = true // to avoid redundant None's
    attr match {
      case at: AttrOneOptString     => opt(attr, at.vs, resOptString)
      case at: AttrOneOptInt        => opt(attr, at.vs, resOptInt)
      case at: AttrOneOptLong       => opt(attr, at.vs, resOptLong)
      case at: AttrOneOptFloat      => opt(attr, at.vs, resOptFloat)
      case at: AttrOneOptDouble     => opt(attr, at.vs, resOptDouble)
      case at: AttrOneOptBoolean    => opt(attr, at.vs, resOptBoolean)
      case at: AttrOneOptBigInt     => opt(attr, at.vs, resOptBigInt)
      case at: AttrOneOptBigDecimal => opt(attr, at.vs, resOptBigDecimal)
      case at: AttrOneOptDate       => opt(attr, at.vs, resOptDate)
      case at: AttrOneOptUUID       => opt(attr, at.vs, resOptUUID)
      case at: AttrOneOptURI        => opt(attr, at.vs, resOptURI)
      case at: AttrOneOptByte       => opt(attr, at.vs, resOptByte)
      case at: AttrOneOptShort      => opt(attr, at.vs, resOptShort)
      case at: AttrOneOptChar       => opt(attr, at.vs, resOptChar)
    }
  }

  private def addSort(attr: Attr, col: String): Unit = {
    attr.sort.foreach { sort =>
      val (dir, arity) = (sort.head, sort.last)
      if (orderBy.exists(ob => ob._1 == level && ob._2 == arity)) {
        throw ModelError(s"Sort arity $arity is already used on this level. " +
          s"Please use distinct continuous sort arities beginning from 1.")
      }
      dir match {
        case 'a' => orderBy += ((level, arity, col, ""))
        case 'd' => orderBy += ((level, arity, col, " DESC"))
      }
    }
  }

  private def man[T: ClassTag](attr: Attr, args: Seq[T], res: ResOne[T]): Unit = {
    val col = getCol(attr: Attr)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns
    if (isNestedOpt) {
      addCast(res.sql2oneOrNull)
    } else {
      addCast(res.sql2one)
      notNull += col
    }
    addSort(attr, col)
    attr.filterAttr.fold {
      expr(col, attr.op, args, res)
      //      filterAttrVars1 = filterAttrVars1 + (a -> (e, v))
      //      filterAttrVars2.get(a).foreach(_(e, v))
    } { filterAttr =>
      expr2(col, attr.op)
    }
  }

  private def manLong(attr: Attr, args: Seq[Long], res: ResOne[Long]): Unit = {
    attr.attr match {
      case "id" =>
        val id = attr.ns + ".id"
        select += id
        groupByCols += id // if we later need to group by non-aggregated columns
        addCast(res.sql2one)
        addSort(attr, id)
      case "tx" =>
        throw ModelError("tx id not implemented yet for jdbc")
      case _    =>
        man(attr, args, res)
    }
  }

  private def tac[T: ClassTag](attr: Attr, args: Seq[T], res: ResOne[T]): Unit = {
    val col = getCol(attr: Attr)
    notNull += col
    attr.filterAttr.fold {
      expr(col, attr.op, args, res)
      //      filterAttrVars1 = filterAttrVars1 + (a -> (e, v))
      //      filterAttrVars2.get(a).foreach(_(e, v))
    } { filterAttr =>
      expr2(col, attr.op)
    }
  }


  private def opt[T: ClassTag](
    attr: Attr,
    optArgs: Option[Seq[T]],
    resOpt: ResOneOpt[T],
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns
    addCast(resOpt.sql2oneOpt)
    addSort(attr, col)
    attr.filterAttr.fold {
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
    } { filterAttr =>
      addSort(attr, col)
      val w = getVar(filterAttr)
      attr.op match {
        case Eq    => optEqual2(col)
        case Neq   => optNeq2(col)
        case Lt    => optCompare2(col, "<")
        case Gt    => optCompare2(col, ">")
        case Le    => optCompare2(col, "<=")
        case Ge    => optCompare2(col, ">=")
        case other => unexpectedOp(other)
      }
    }
  }

  private def expr[T: ClassTag](
    col: String,
    op: Op,
    args: Seq[T],
    res: ResOne[T],
  ): Unit = {
    op match {
      case V          => ()
      case Eq         => equal(col, args, res.one2sql)
      case Neq        => neq(col, args, res.one2sql)
      case Lt         => compare(col, args.head, "<", res.one2sql)
      case Gt         => compare(col, args.head, ">", res.one2sql)
      case Le         => compare(col, args.head, "<=", res.one2sql)
      case Ge         => compare(col, args.head, ">=", res.one2sql)
      case NoValue    => noValue(col)
      case Fn(kw, n)  => aggr(col, kw, n, res)
      case StartsWith => startsWith(col, args.head)
      case EndsWith   => endsWith(col, args.head)
      case Contains   => contains(col, args.head)
      case Matches    => matches(col, args.head)
      case Take       => take(col, args.head, "LEFT")
      case TakeRight  => take(col, args.head, "RIGHT")
      case Drop       => drop(col, args.head, true)
      case DropRight  => drop(col, args.head, false)
      case Slice      => slice(col, args)
      case SubString  => subString(col, args)
      case Remainder  => remainder(col, args)
      case Even       => even(col)
      case Odd        => odd(col)
      case other      => unexpectedOp(other)
    }
  }
  private def expr2(
    col: String,
    op: Op,
  ): Unit = op match {
    //    case Eq    => equal2(col, w)
    //    case Neq   => neq2(col, w)
    //    case Lt    => compare2(col, w, "<")
    //    case Gt    => compare2(col, w, ">")
    //    case Le    => compare2(col, w, "<=")
    //    case Ge    => compare2(col, w, ">=")
    case other => unexpectedOp(other)
  }


  private def startsWith[T](col: String, arg: T): Unit = where += ((col, s"LIKE '$arg%'"))
  private def endsWith[T](col: String, arg: T): Unit = where += ((col, s"LIKE '%$arg'"))
  private def contains[T](col: String, arg: T): Unit = where += ((col, s"LIKE '%$arg%'"))
  private def matches[T](col: String, regex: T): Unit = where += ((col, s"~ '$regex'"))

  private def take[T](col: String, length: T, fn: String): Unit = {
    if (length.toString.toInt > 0) {
      select -= col
      notNull -= col
      val alias = col.replace('.', '_')
      select += s"$fn($col, $length) AS $alias"
      orderBy = orderBy.map {
        case (level, arity, `col`, dir) => (level, arity, alias, dir)
        case other                      => other
      }
    } else {
      // Take nothing
      where += (("FALSE", ""))
    }
  }

  private def drop[T](col: String, arg: T, left: Boolean): Unit = {
    val i = arg.toString.toInt
    if (i > 0) {
      select -= col
      notNull -= col
      val alias           = col.replace('.', '_')
      val len             = s"LENGTH($col)"
      val (start, length) = if (left) (i + 1, len) else ("1", s"$len - $i")
      select += s"SUBSTRING($col, $start, $length) AS $alias"
      orderBy = orderBy.map {
        case (level, arity, `col`, dir) => (level, arity, alias, dir)
        case other                      => other
      }
      val clause = if (left) s"$len > $i" else s"$len > $i"
      where += ((clause, ""))
    } else {
      // Drop nothing
    }
  }

  private def slice[T](col: String, args: Seq[T]): Unit = subString(col, args)
  private def subString[T](col: String, args: Seq[T]): Unit = {
    // 1-based string position
    val from  = args.head.toString.toInt.max(0) + 1
    val until = args(1).toString.toInt + 1
    if (from >= until) {
      where += (("FALSE", ""))

    } else {
      select -= col
      notNull -= col
      val alias  = col.replace('.', '_')
      val len    = s"LENGTH($col)"
      val length = until - from
      select += s"SUBSTRING($col, $from, $length) AS $alias"
      orderBy = orderBy.map {
        case (level, arity, `col`, dir) => (level, arity, alias, dir)
        case other                      => other
      }
      where += ((s"$len >= $from", ""))
    }
  }


  private def remainder[T](col: String, args: Seq[T]): Unit = where += ((col, s"% ${args.head} = ${args(1)}"))
  private def even(col: String): Unit = where += ((col, s"% 2 = 0"))
  private def odd(col: String): Unit = where += ((col, s"% 2 = 1"))

  private def aggr[T](col: String, fn: String, optN: Option[Int], res: ResOne[T]): Unit = {
    lazy val n = optN.getOrElse(0)
    // Replace find/casting with aggregate function/cast
    select -= col
    fn match {
      case "distinct" =>
        select += s"ARRAY_AGG(DISTINCT $col)"
        groupByCols -= col
        aggregate = true
        replaceCast(res.array2set)

      case "mins" =>
        select +=
          s"""ARRAY_SLICE(
             |    ARRAY_AGG(DISTINCT $col order by $col ASC),
             |    1,
             |    LEAST(
             |      $n,
             |      ARRAY_LENGTH(ARRAY_AGG(DISTINCT $col))
             |    )
             |  )""".stripMargin
        groupByCols -= col
        aggregate = true
        replaceCast(res.array2set)

      case "min" =>
        select += s"MIN($col)"

      case "maxs" =>
        select +=
          s"""ARRAY_SLICE(
             |    ARRAY_AGG(DISTINCT $col order by $col DESC),
             |    1,
             |    LEAST(
             |      $n,
             |      ARRAY_LENGTH(ARRAY_AGG(DISTINCT $col))
             |    )
             |  )""".stripMargin
        groupByCols -= col
        aggregate = true
        replaceCast(res.array2set)

      case "max" =>
        select += s"MAX($col)"

      case "rands" =>
        select +=
          s"""ARRAY_SLICE(
             |    ARRAY_AGG($col order by RAND()),
             |    1,
             |    LEAST(
             |      $n,
             |      ARRAY_LENGTH(ARRAY_AGG($col))
             |    )
             |  )""".stripMargin
        groupByCols -= col
        aggregate = true
        replaceCast(res.array2set)

      case "rand" =>
        distinct = false
        select += col
        orderBy += ((level, -1, "RAND()", ""))
        limitClause = "1"

      case "samples" =>
        select +=
          s"""ARRAY_SLICE(
             |    ARRAY_AGG(DISTINCT $col order by RAND()),
             |    1,
             |    LEAST(
             |      $n,
             |      ARRAY_LENGTH(ARRAY_AGG(DISTINCT $col))
             |    )
             |  )""".stripMargin
        groupByCols -= col
        aggregate = true
        replaceCast(res.array2set)

      case "sample" =>
        distinct = false
        select += col
        orderBy += ((level, -1, "RAND()", ""))
        limitClause = "1"

      case "count" =>
        distinct = false
        groupByCols -= col
        aggregate = true
        selectWithOrder(col, "COUNT", "")
        replaceCast(toInt)

      case "countDistinct" =>
        distinct = false
        groupByCols -= col
        aggregate = true
        selectWithOrder(col, "COUNT")
        replaceCast(toInt)

      case "sum" =>
        groupByCols -= col
        aggregate = true
        selectWithOrder(col, "SUM")

      case "median" =>
        groupByCols -= col
        aggregate = true
        selectWithOrder(col, "MEDIAN")

      case "avg" =>
        groupByCols -= col
        aggregate = true
        selectWithOrder(col, "AVG")

      case "variance" =>
        groupByCols -= col
        aggregate = true
        selectWithOrder(col, "VAR_POP")

      case "stddev" =>
        groupByCols -= col
        aggregate = true
        selectWithOrder(col, "STDDEV_POP")

      case other => unexpectedKw(other)
    }
  }

  private def selectWithOrder(col: String, fn: String, distinct: String = "DISTINCT "): Unit = {
    if (orderBy.nonEmpty && orderBy.last._3 == col) {
      // order by aggregate alias instead
      val alias = col.replace('.', '_') + "_" + fn.toLowerCase
      select += s"$fn($distinct$col) $alias"
      val (level, _, _, dir) = orderBy.last
      orderBy.remove(orderBy.size - 1)
      orderBy += ((level, 1, alias, dir))
    } else {
      select += s"$fn($distinct$col)"
    }
  }

  private def equal[T: ClassTag](col: String, args: Seq[T], one2sql: T => String): Unit = {
    if (args.length == 1)
      where += ((col, "= " + one2sql(args.head)))
    else
      where += ((col, args.map(one2sql).mkString("IN (", ", ", ")")))
  }
  private def equal2(col: String, w: Var): Unit = {
    //    whereOLD += s"[$e $a $w$tx]" -> wClause
    //    whereOLD += s"[(identity $w) $v]" -> wGround
  }

  private def neq[T](col: String, args: Seq[T], one2sql: T => String): Unit = {
    if (args.length == 1)
      where += ((col, "<> " + one2sql(args.head)))
    else
      where += ((col, args.map(one2sql).mkString("NOT IN (", ", ", ")")))
  }
  private def neq2(col: String, w: Var): Unit = {
    //    whereOLD += s"[$e $a $v$tx]" -> wClause
    //    whereOLD += s"[(!= $v $w)]" -> wNeqOne
  }

  private def compare[T](col: String, arg: T, op: String, one2sql: T => String): Unit = {
    where += ((col, op + " " + one2sql(arg)))
  }
  private def compare2(col: String, w: Var, op: String): Unit = {
    //    whereOLD += s"[$e $a $v$tx]" -> wClause
    //    whereOLD += s"[($op $v $w)]" -> wNeqOne
  }

  private def noValue(col: String): Unit = {
    notNull -= col
    where += ((col, s"IS NULL"))
  }


  private def optEqual[T: ClassTag](
    col: String,
    optArgs: Option[Seq[T]],
    one2sql: T => String,
  ): Unit = {
    optArgs.fold[Unit] {
      where += ((col, s"IS NULL"))
    } { vs =>
      equal(col, vs, one2sql)
    }
  }
  private def optEqual2(
    col: String,
  ): Unit = {
    //    select += v
    //    equal2(col, w)
  }

  private def optNeq[T](
    col: String,
    optArgs: Option[Seq[T]],
    one2sql: T => String
  ): Unit = {
    if (optArgs.isDefined && optArgs.get.nonEmpty) {
      neq(col, optArgs.get, one2sql)
    } else {
      notNull += col
    }
  }
  private def optNeq2(
    col: String,
  ): Unit = {
    //    select += v
    //    neq2(col, w)
  }

  private def optCompare[T](
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
  private def optCompare2(
    col: String,
    op: String,
  ): Unit = {
    //    select += v
    //    compare2(col, w, op)
  }
}