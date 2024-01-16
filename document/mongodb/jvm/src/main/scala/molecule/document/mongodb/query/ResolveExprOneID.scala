package molecule.document.mongodb.query

import com.mongodb.client.model.mql.MqlValues._
import com.mongodb.client.model.{Filters, Projections, Sorts}
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.query.ResolveExpr
import org.bson._
import org.bson.conversions.Bson

trait ResolveExprOneID extends ResolveExpr with LambdasOne { self: MongoQueryBase =>

  protected def resolveAttrOneManID(attr: AttrOneMan): Unit = {
    attr match {
      case at: AttrOneManID => man(at.copy(attr = "_id"), at.vs, resID)
      case _                => throw ModelError("Unexpected mandatory expr one ID type")
    }
  }

  protected def resolveAttrOneTacID(attr: AttrOneTac): Unit = {
    if (isNestedOpt)
      throw ModelError("Tacit attributes not allowed in optional nested queries. Found: " + attr.name + "_")
    attr match {
      case at: AttrOneTacID => tac(at.copy(attr = "_id"), at.vs, resID)
      case _                => throw ModelError("Unexpected tacit expr one ID type")
    }
  }

  private def addSort(attr: Attr, field: String): Unit = {
    attr.sort.foreach { sort =>
      val (dir, index) = (sort.head, sort.substring(1, 2).toInt)
      dir match {
        case 'a' => topBranch.sorts += index -> Sorts.ascending(b.path + field)
        case 'd' => topBranch.sorts += index -> Sorts.descending(b.path + field)
      }
    }
  }

  private def man(attr: Attr, args: Seq[String], res: ResOne[String]): Unit = {
    val field = attr.attr
    projectField(field)
    addSort(attr, field)
    addCast(field, res.cast(field))

    prefixedFieldPair = if (b.parent.isEmpty) (nestedLevel, field, field) else (nestedLevel, b.path + field, b.alias + field)
    topBranch.groupIdFields += prefixedFieldPair

    attr.filterAttr.fold(
      expr(field, attr.op, args, res)
    ) { case (dir, filterPath, filterAttr) =>
      expr2(field, attr.op, filterAttr.name)
    }
  }

  private def tac[T](attr: Attr, args: Seq[T], res: ResOne[T]): Unit = {
    val field = attr.attr
    attr.filterAttr.fold {
      expr(field, attr.op, args, res)
    } { case (dir, filterPath, filterAttr) =>
      expr2(field, attr.op, filterAttr.name)
    }
  }


  //  private def opt[T](attr: Attr, optArgs: Option[Seq[T]], res: ResOne[T]): Unit = {
  //    val field = attr.attr
  //    fields.add(Projections.include(field))
  //    addCast(res.castOpt(field))
  //    addSort(attr, field)
  //    val filter = attr.op match {
  //      case V     => Filters.empty() // selected field can already be a value or null
  //      case Eq    => optEqual(field, optArgs, res)
  //      case Neq   => optNeq(field, optArgs, res)
  //      case Lt    => optCompare(field, optArgs, res.lt)
  //      case Gt    => optCompare(field, optArgs, res.gt)
  //      case Le    => optCompare(field, optArgs, res.le)
  //      case Ge    => optCompare(field, optArgs, res.ge)
  //      case other => unexpectedOp(other)
  //    }
  //    filters.add(filter)
  //  }


  private def expr[T](field0: String, op: Op, args: Seq[T], res: ResOne[T]): Unit = {
    val field = b.dot + field0
    op match {
      case V          => b.matches.add(Filters.ne(field, null))
      case Eq         => b.matches.add(equal(field, args, res))
      case Neq        => b.matches.add(neq(field, args, res))
      case Lt         => b.matches.add(res.lt(field, args.head))
      case Gt         => b.matches.add(res.gt(field, args.head))
      case Le         => b.matches.add(res.le(field, args.head))
      case Ge         => b.matches.add(res.ge(field, args.head))
      case NoValue    => b.matches.add(noValue(field))
      case Fn(kw, n)  => aggr(field, kw, n, res)
      case StartsWith => b.matches.add(startsWith(field, args.head))
      case EndsWith   => b.matches.add(endsWith(field, args.head))
      case Contains   => b.matches.add(contains(field, args.head))
      case Matches    => b.matches.add(regExMatch(field, args.head.toString))
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
      case 1 => res.eq(field, args.head)
      case 0 => res.eq(field, null.asInstanceOf[T])
      case _ => Filters.or(args.map(arg => res.eq(field, arg)).asJava)
    }
  }
  private def equal2(field: String, filterAttr: String): Unit = {
//    where += ((field, "= " + filterAttr))
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
//    where += ((field, " != " + filterAttr))
  }

  private def compare2(field: String, op: String, filterAttr: String): Unit = {
//    where += ((field, op + " " + filterAttr))
  }

  private def noValue(field: String): Bson = {
    Filters.eq(field, new BsonNull())
  }


  private def optEqual[T](field: String, optArgs: Option[Seq[T]], res: ResOne[T]): Bson = {
    optArgs.fold {
      Filters.eq(field, new BsonNull())
    } {
      case Nil => res.eq(field, null.asInstanceOf[T])
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
  private def regExMatch(field: String, regex: String): Bson = {
    if (regex.nonEmpty) {
      Filters.regex(field, regex)
    } else {
      // No filtering
      Filters.empty()
    }
  }

  private def remainder[T](field: String, args: Seq[T]): Unit = {
    val Seq(divisor, remainder) = args.map(_.toString.toInt)
    b.matches.add(Filters.mod(field, divisor, remainder))
  }

  private def even(field: String): Unit = {
    b.matches.add(Filters.mod(field, 2, 0))
  }

  private def odd(field: String): Unit = {
    b.matches.add(Filters.mod(field, 2, 1))
  }

  private def aggr[T](field: String, fn: String, optN: Option[Int], res: ResOne[T]): Unit = {
    lazy val n = optN.getOrElse(0)
    // Replace find/casting with aggregate function/cast
//    select -= field
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

//  private def selectWithOrder(
//    field: String,
//    fn: String,
//    distinct: String = "DISTINCT ",
//    cast: String = ""
//  ): Unit = {
//    if (orderBy.nonEmpty && orderBy.last._3 == field) {
//      // order by aggregate alias instead
//      val alias = field.replace('.', '_') + "_" + fn.toLowerCase
////      select += s"$fn($distinct$field$cast) $alias"
//      val (level, _, _, dir) = orderBy.last
//      orderBy.remove(orderBy.size - 1)
//      orderBy += ((level, 1, alias, dir))
//    } else {
////      select += s"$fn($distinct$field$cast)"
//    }
//  }


}
