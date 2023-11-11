package molecule.document.mongodb.query

import com.mongodb.client.model.mql.{MqlDocument, MqlString}
import com.mongodb.client.model.mql.MqlValues._
import com.mongodb.client.model.{Filters, Projections, Sorts}
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.query.ResolveExpr
import org.bson._
import org.bson.conversions.Bson

trait ResolveExprOne extends ResolveExpr with LambdasOne { self: MongoQueryBase =>

  override protected def resolveAttrOneMan(attr: AttrOneMan): Unit = {
    aritiesAttr()
    attr match {
//      case at: AttrOneManID             => man(attr, at.vs, resId) // refs??
      case at: AttrOneManID             => man(attr, at.vs, resString) // refs??
      case at: AttrOneManString         => man(attr, at.vs, resString)
      case at: AttrOneManInt            => man(attr, at.vs, resInt)
      case at: AttrOneManLong           => man(attr, at.vs, resLong)
      case at: AttrOneManFloat          => man(attr, at.vs, resFloat)
      case at: AttrOneManDouble         => man(attr, at.vs, resDouble)
      case at: AttrOneManBoolean        => man(attr, at.vs, resBoolean)
      case at: AttrOneManBigInt         => man(attr, at.vs, resBigInt)
      case at: AttrOneManBigDecimal     => man(attr, at.vs, resBigDecimal)
      case at: AttrOneManDate           => man(attr, at.vs, resDate)
      case at: AttrOneManDuration       => man(attr, at.vs, resDuration)
      case at: AttrOneManInstant        => man(attr, at.vs, resInstant)
      case at: AttrOneManLocalDate      => man(attr, at.vs, resLocalDate)
      case at: AttrOneManLocalTime      => man(attr, at.vs, resLocalTime)
      case at: AttrOneManLocalDateTime  => man(attr, at.vs, resLocalDateTime)
      case at: AttrOneManOffsetTime     => man(attr, at.vs, resOffsetTime)
      case at: AttrOneManOffsetDateTime => man(attr, at.vs, resOffsetDateTime)
      case at: AttrOneManZonedDateTime  => man(attr, at.vs, resZonedDateTime)
      case at: AttrOneManUUID           => man(attr, at.vs, resUUID)
      case at: AttrOneManURI            => man(attr, at.vs, resURI)
      case at: AttrOneManByte           => man(attr, at.vs, resByte)
      case at: AttrOneManShort          => man(attr, at.vs, resShort)
      case at: AttrOneManChar           => man(attr, at.vs, resChar)
    }
  }

  override protected def resolveAttrOneTac(attr: AttrOneTac): Unit = {
    if (isNestedOpt)
      throw ModelError("Tacit attributes not allowed in optional nested queries. Found: " + attr.name + "_")
    attr match {
      case at: AttrOneTacID             => tac(attr, at.vs, resString)
      case at: AttrOneTacString         => tac(attr, at.vs, resString)
      case at: AttrOneTacInt            => tac(attr, at.vs, resInt)
      case at: AttrOneTacLong           => tac(attr, at.vs, resLong)
      case at: AttrOneTacFloat          => tac(attr, at.vs, resFloat)
      case at: AttrOneTacDouble         => tac(attr, at.vs, resDouble)
      case at: AttrOneTacBoolean        => tac(attr, at.vs, resBoolean)
      case at: AttrOneTacBigInt         => tac(attr, at.vs, resBigInt)
      case at: AttrOneTacBigDecimal     => tac(attr, at.vs, resBigDecimal)
      case at: AttrOneTacDate           => tac(attr, at.vs, resDate)
      case at: AttrOneTacDuration       => tac(attr, at.vs, resDuration)
      case at: AttrOneTacInstant        => tac(attr, at.vs, resInstant)
      case at: AttrOneTacLocalDate      => tac(attr, at.vs, resLocalDate)
      case at: AttrOneTacLocalTime      => tac(attr, at.vs, resLocalTime)
      case at: AttrOneTacLocalDateTime  => tac(attr, at.vs, resLocalDateTime)
      case at: AttrOneTacOffsetTime     => tac(attr, at.vs, resOffsetTime)
      case at: AttrOneTacOffsetDateTime => tac(attr, at.vs, resOffsetDateTime)
      case at: AttrOneTacZonedDateTime  => tac(attr, at.vs, resZonedDateTime)
      case at: AttrOneTacUUID           => tac(attr, at.vs, resUUID)
      case at: AttrOneTacURI            => tac(attr, at.vs, resURI)
      case at: AttrOneTacByte           => tac(attr, at.vs, resByte)
      case at: AttrOneTacShort          => tac(attr, at.vs, resShort)
      case at: AttrOneTacChar           => tac(attr, at.vs, resChar)
    }
  }

  override protected def resolveAttrOneOpt(attr: AttrOneOpt): Unit = {
    aritiesAttr()
    hasOptAttr = true // to avoid redundant None's
    attr match {
      case at: AttrOneOptID             => opt(attr, at.vs, resString)
      case at: AttrOneOptString         => opt(attr, at.vs, resString)
      case at: AttrOneOptInt            => opt(attr, at.vs, resInt)
      case at: AttrOneOptLong           => opt(attr, at.vs, resLong)
      case at: AttrOneOptFloat          => opt(attr, at.vs, resFloat)
      case at: AttrOneOptDouble         => opt(attr, at.vs, resDouble)
      case at: AttrOneOptBoolean        => opt(attr, at.vs, resBoolean)
      case at: AttrOneOptBigInt         => opt(attr, at.vs, resBigInt)
      case at: AttrOneOptBigDecimal     => opt(attr, at.vs, resBigDecimal)
      case at: AttrOneOptDate           => opt(attr, at.vs, resDate)
      case at: AttrOneOptDuration       => opt(attr, at.vs, resDuration)
      case at: AttrOneOptInstant        => opt(attr, at.vs, resInstant)
      case at: AttrOneOptLocalDate      => opt(attr, at.vs, resLocalDate)
      case at: AttrOneOptLocalTime      => opt(attr, at.vs, resLocalTime)
      case at: AttrOneOptLocalDateTime  => opt(attr, at.vs, resLocalDateTime)
      case at: AttrOneOptOffsetTime     => opt(attr, at.vs, resOffsetTime)
      case at: AttrOneOptOffsetDateTime => opt(attr, at.vs, resOffsetDateTime)
      case at: AttrOneOptZonedDateTime  => opt(attr, at.vs, resZonedDateTime)
      case at: AttrOneOptUUID           => opt(attr, at.vs, resUUID)
      case at: AttrOneOptURI            => opt(attr, at.vs, resURI)
      case at: AttrOneOptByte           => opt(attr, at.vs, resByte)
      case at: AttrOneOptShort          => opt(attr, at.vs, resShort)
      case at: AttrOneOptChar           => opt(attr, at.vs, resChar)
    }
  }

  private def addSort(attr: Attr, field: String): Unit = {
    attr.sort.foreach { sort =>
      val (dir, arity) = (sort.head, sort.substring(1, 2).toInt)
      dir match {
        //        case 'a' => orderBy += ((level, arity, field, ""))
        //        case 'd' => orderBy += ((level, arity, field, " DESC"))
        case 'a' => sorts.add(Sorts.ascending(field))
        case 'd' => sorts.add(Sorts.descending(field))
      }
    }
  }

  private def man[T](attr: Attr, args: Seq[T], res: ResOne[T]): Unit = {
    val field = attr.attr
    addField(field)
    addCast(field, res.cast(field))
    addSort(attr, field)

    attr.filterAttr.fold(
      expr(field, attr.op, args, res)
    )(filterAttr =>
      expr2(field, attr.op, filterAttr.name)
    )
  }

  private def tac[T](attr: Attr, args: Seq[T], res: ResOne[T]): Unit = {
    val field = attr.attr
    attr.filterAttr.fold {
      expr(field, attr.op, args, res)
    } { filterAttr =>
      expr2(field, attr.op, filterAttr.name)
    }
  }


  private def opt[T](attr: Attr, optArgs: Option[Seq[T]], res: ResOne[T]): Unit = {
    val field = attr.attr
    addField(field)
    addCast(field, res.castOpt(field))
    addSort(attr, field)
    val filter = attr.op match {
      case V     => Filters.empty() // selected field can already be a value or null
      case Eq    => optEqual(field, optArgs, res)
      case Neq   => optNeq(field, optArgs, res)
      case Lt    => optCompare(field, optArgs, res.lt)
      case Gt    => optCompare(field, optArgs, res.gt)
      case Le    => optCompare(field, optArgs, res.le)
      case Ge    => optCompare(field, optArgs, res.ge)
      case other => unexpectedOp(other)
    }
    matches.add(filter)
  }


  private def expr[T](field0: String, op: Op, args: Seq[T], res: ResOne[T]): Unit = {
    val field = path + field0
    op match {
      case V          => matches.add(Filters.ne(field, null.asInstanceOf[T]))
      case Eq         => matches.add(equal(field, args, res))
      case Neq        => matches.add(neq(field, args, res))
      case Lt         => matches.add(res.lt(field, args.head))
      case Gt         => matches.add(res.gt(field, args.head))
      case Le         => matches.add(res.le(field, args.head))
      case Ge         => matches.add(res.ge(field, args.head))
      case NoValue    => matches.add(noValue(field))
      case Fn(kw, n)  => aggr(field, kw, n, res)
      case StartsWith => matches.add(startsWith(field, args.head))
      case EndsWith   => matches.add(endsWith(field, args.head))
      case Contains   => matches.add(contains(field, args.head))
      case Matches    => matches.add(matches(field, args.head.toString))
      case Take       => take(field, args.head.toString.toInt)
      case TakeRight  => takeRight(field, args.head.toString.toInt)
      case Drop       => drop(field, args.head.toString.toInt)
      case DropRight  => dropRight(field, args.head.toString.toInt)
      case Slice      => slice(field, args)
      case SubString  => slice(field, args)
      case Remainder  => remainder(field, args)
      case Even       => even(field)
      case Odd        => odd(field)
      case other      => unexpectedOp(other)
    }

  }
  private def expr2(field: String, op: Op, filterAttr: String): Unit = op match {
    case Eq    => equal2(field, filterAttr)
    case Neq   => neq2(field, filterAttr)
    case Lt    => compare2(field, "<", filterAttr)
    case Gt    => compare2(field, ">", filterAttr)
    case Le    => compare2(field, "<=", filterAttr)
    case Ge    => compare2(field, ">=", filterAttr)
    case other => unexpectedOp(other)
  }

  private def equal[T](field: String, args: Seq[T], res: ResOne[T]): Bson = {
    args.length match {
      case 0 => Filters.eq("_id", -1)
      case 1 => res.eq(field, args.head)
      case _ => Filters.or(args.map(arg => res.eq(field, arg)).asJava)
    }
  }
  private def equal2(field: String, filterAttr: String): Unit = {
    where += ((field, "= " + filterAttr))
  }

  private def neq[T](field: String, args: Seq[T], res: ResOne[T]): Bson = {
    args.length match {
      case 1 => Filters.and(
        Filters.ne(field, null.asInstanceOf[T]),
        res.neq(field, args.head)
      )
      case 0 => Filters.ne(field, null.asInstanceOf[T])
      case _ => Filters.and(
        (Filters.ne(field, null.asInstanceOf[T]) +: args.map(arg => res.neq(field, arg))).asJava
      )
    }
  }
  private def neq2(field: String, filterAttr: String): Unit = {
    where += ((field, " != " + filterAttr))
  }

  private def compare2(field: String, op: String, filterAttr: String): Unit = {
    where += ((field, op + " " + filterAttr))
  }

  private def noValue(field: String): Bson = {
    Filters.eq(field, new BsonNull())
  }


  private def optEqual[T](field: String, optArgs: Option[Seq[T]], res: ResOne[T]): Bson = {
    optArgs.fold {
      Filters.eq(field, new BsonNull())
    } {
      case Nil =>
//        res.eq(field, null.asInstanceOf[T])
        Filters.eq("_id", -1)
      case vs  => equal(field, vs, res)
    }
  }

  private def optNeq[T](field: String, optArgs: Option[Seq[T]], res: ResOne[T]): Bson = {
    if (optArgs.isDefined && optArgs.get.nonEmpty) {
      Filters.and(
        Filters.ne(field, null.asInstanceOf[T]),
        neq(field, optArgs.get, res),
      )
    } else {
      Filters.ne(field, new BsonNull())
    }
  }

  private def optCompare[T](field: String, optArgs: Option[Seq[T]], filter: (String, T) => Bson): Bson = {
    optArgs.fold {
      // Always return empty result when trying to compare None
      Filters.eq("_id", -1)
    } { vs =>
      filter(field, vs.head)
    }
  }

  private def startsWith[T](field: String, arg: T): Bson = Filters.regex(field, s"^$arg.*")
  private def endsWith[T](field: String, arg: T): Bson = Filters.regex(field, s".*$arg$$")
  private def contains[T](field: String, arg: T): Bson = Filters.regex(field, s".*$arg.*")
  private def matches(field: String, regex: String): Bson = {
    if (regex.nonEmpty) {
      Filters.regex(field, regex)
    } else {
      // No filtering
      Filters.empty()
    }
  }

  private def take(field: String, n: Int): Unit = {
    if (n > 0) {
      // Empty result string discarded
      projections.add(Projections.computed(field, current().getString(field).substr(0, n)))
    } else {
      // Take nothing
      matches.add(Filters.eq("_id", -1))
    }
  }

  private def drop(field: String, n: Int): Unit = {
    if (n > 0) {
      // Skip if trying to drop more than string length
      matches.add(Filters.expr(current().getString(field).length().gt(of(n))))
      projections.add(Projections.computed(field, current().getString(field).substr(n, Int.MaxValue)))
    } else {
      // Drop nothing
    }
  }

  private def takeRight(field: String, n: Int): Unit = {
    if (n > 0) {
      projections.add(
        Projections.computed(
          field,
          current().getString(field).substr(
            current().getString(field).length().subtract(n).max(of(0)),
            of(Int.MaxValue)
          )
        )
      )
    } else {
      // Take nothing
      matches.add(Filters.eq("_id", -1))
    }
  }

  private def dropRight(field: String, n: Int): Unit = {
    if (n > 0) {
      // Skip if trying to drop more than string length
      matches.add(Filters.expr(current().getString(field).length().gt(of(n))))
      projections.add(
        Projections.computed(
          field,
          current().getString(field).substr(
            of(0),
            current().getString(field).length().subtract(n).max(of(0))
          )
        )
      )
    } else {
      // Drop nothing
    }
  }

  private def slice[T](field: String, args: Seq[T]): Unit = {
    val Seq(from, until) = args.asInstanceOf[Seq[String]].map(_.toInt)
    if (from >= until) {
      // Take nothing
      matches.add(Filters.eq("_id", -1))
    } else if (from >= 0) {
      val length = until - from
      // Skip if from is greater than string length
      matches.add(Filters.expr(current().getString(field).length().gt(of(from))))
      projections.add(Projections.computed(field, current().getString(field).substr(from, length)))
    } else {
      // Drop nothing
    }
  }

  private def subString[T](field: String, args: Seq[T]): Bson = {
    // 1-based string position
    val from  = args.head.toString.toInt.max(0) + 1
    val until = args(1).toString.toInt + 1
    if (from >= until) {
      where += (("FALSE", ""))

    } else {
      select -= field
      notNull -= field
      val alias  = field.replace('.', '_')
      val len    = s"LENGTH($field)"
      val length = until - from
      select += s"SUBSTRING($field, $from, $length) AS $alias"
      orderBy = orderBy.map {
        case (level, arity, `field`, dir) => (level, arity, alias, dir)
        case other                        => other
      }
      where += ((s"$len >= $from", ""))
    }
    ???
  }

  private def remainder[T](field: String, args: Seq[T]): Unit = {
    val Seq(divisor, remainder) = args.map(_.toString.toInt)
    matches.add(Filters.mod(field, divisor, remainder))
  }

  private def even(field: String): Unit = {
    matches.add(Filters.mod(field, 2, 0))
  }

  private def odd(field: String): Unit = {
    matches.add(Filters.mod(field, 2, 1))
  }

  private def aggr[T](field: String, fn: String, optN: Option[Int], res: ResOne[T]): Unit = {
    lazy val n = optN.getOrElse(0)
    // Replace find/casting with aggregate function/cast
    select -= field
    //      fn match {
    //        case "distinct" =>
    //          select += s"ARRAY_AGG(DISTINCT $field)"
    //          groupByCols -= field
    //          aggregate = true
    //          replaceCast(res.array2set)
    //
    //        case "min" =>
    //          select += s"MIN($field)"
    //          if (field.endsWith(".id")) {
    //            groupByCols -= field
    //            aggregate = true
    //          }
    //
    //        case "mins" =>
    //          select +=
    //            s"""ARRAY_SLICE(
    //               |    ARRAY_AGG(DISTINCT $field order by $field ASC),
    //               |    1,
    //               |    LEAST(
    //               |      $n,
    //               |      ARRAY_LENGTH(ARRAY_AGG(DISTINCT $field))
    //               |    )
    //               |  )""".stripMargin
    //          groupByCols -= field
    //          aggregate = true
    //          replaceCast(res.array2set)
    //
    //        case "max" =>
    //          select += s"MAX($field)"
    //          if (field.endsWith(".id")) {
    //            groupByCols -= field
    //            aggregate = true
    //          }
    //
    //        case "maxs" =>
    //          select +=
    //            s"""ARRAY_SLICE(
    //               |    ARRAY_AGG(DISTINCT $field order by $field DESC),
    //               |    1,
    //               |    LEAST(
    //               |      $n,
    //               |      ARRAY_LENGTH(ARRAY_AGG(DISTINCT $field))
    //               |    )
    //               |  )""".stripMargin
    //          groupByCols -= field
    //          aggregate = true
    //          replaceCast(res.array2set)
    //
    //        case "rand" =>
    //          distinct = false
    //          select += field
    //          orderBy += ((level, -1, "RAND()", ""))
    //          hardLimit = 1
    //
    //        case "rands" =>
    //          select +=
    //            s"""ARRAY_SLICE(
    //               |    ARRAY_AGG($field order by RAND()),
    //               |    1,
    //               |    LEAST(
    //               |      $n,
    //               |      ARRAY_LENGTH(ARRAY_AGG($field))
    //               |    )
    //               |  )""".stripMargin
    //          groupByCols -= field
    //          aggregate = true
    //          replaceCast(res.array2set)
    //
    //        case "sample" =>
    //          distinct = false
    //          select += field
    //          orderBy += ((level, -1, "RAND()", ""))
    //          hardLimit = 1
    //
    //        case "samples" =>
    //          select +=
    //            s"""ARRAY_SLICE(
    //               |    ARRAY_AGG(DISTINCT $field order by RAND()),
    //               |    1,
    //               |    LEAST(
    //               |      $n,
    //               |      ARRAY_LENGTH(ARRAY_AGG(DISTINCT $field))
    //               |    )
    //               |  )""".stripMargin
    //          groupByCols -= field
    //          aggregate = true
    //          replaceCast(res.array2set)
    //
    //        case "count" =>
    //          distinct = false
    //          groupByCols -= field
    //          aggregate = true
    //          selectWithOrder(field, "COUNT", "")
    //          replaceCast(toInt)
    //
    //        case "countDistinct" =>
    //          distinct = false
    //          groupByCols -= field
    //          aggregate = true
    //          selectWithOrder(field, "COUNT")
    //          replaceCast(toInt)
    //
    //        case "sum" =>
    //          groupByCols -= field
    //          aggregate = true
    //          selectWithOrder(field, "SUM")
    //
    //        case "median" =>
    //          groupByCols -= field
    //          aggregate = true
    //          selectWithOrder(field, "MEDIAN")
    //
    //        case "avg" =>
    //          groupByCols -= field
    //          aggregate = true
    //          selectWithOrder(field, "AVG")
    //
    //        case "variance" =>
    //          groupByCols -= field
    //          aggregate = true
    //          selectWithOrder(field, "VAR_POP")
    //
    //        case "stddev" =>
    //          groupByCols -= field
    //          aggregate = true
    //          selectWithOrder(field, "STDDEV_POP")
    //
    //        case other => unexpectedKw(other)
    //      }
  }

  private def selectWithOrder(
    field: String,
    fn: String,
    distinct: String = "DISTINCT ",
    cast: String = ""
  ): Unit = {
    if (orderBy.nonEmpty && orderBy.last._3 == field) {
      // order by aggregate alias instead
      val alias = field.replace('.', '_') + "_" + fn.toLowerCase
      select += s"$fn($distinct$field$cast) $alias"
      val (level, _, _, dir) = orderBy.last
      orderBy.remove(orderBy.size - 1)
      orderBy += ((level, 1, alias, dir))
    } else {
      select += s"$fn($distinct$field$cast)"
    }
  }


}
