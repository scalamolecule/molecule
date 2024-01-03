package molecule.document.mongodb.query

import com.mongodb.client.model.{Filters, Projections, Sorts}
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.query.ResolveExpr
import molecule.document.mongodb.query.mongoModel.Branch
import org.bson._
import org.bson.conversions.Bson
import scala.annotation.tailrec

trait ResolveExprOne extends ResolveExpr with LambdasOne with LambdasSet { self: MongoQueryBase =>

  override protected def resolveAttrOneMan(attr: AttrOneMan): Unit = {
    attr match {
      case at: AttrOneManID             => man(attr, at.vs, resID) // refs??
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
      case at: AttrOneTacID             => tac(attr, at.vs, resID)
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
    hasOptAttr = true // to avoid redundant None's
    attr match {
      case at: AttrOneOptID             => opt(attr, at.vs, resID)
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
      //      println(s"========  ${attr.name}   $dir   $arity  ${b.nested}")
      //      if (b.nested) {
      //        //      if (isNested) {
      //        dir match {
      ////          case 'a' => b.nestedSorts += field -> 1
      ////          case 'd' => b.nestedSorts += field -> -1
      //
      //          case 'a' => baseBranch.sorts.add(Sorts.ascending(field))
      //          case 'd' => baseBranch.sorts.add(Sorts.descending(field))
      //        }
      //        //        println(s"A ############## ${b.hashCode()}  " + b.nestedSorts)
      //      } else {
      //        dir match {
      //          case 'a' => baseBranch.sorts.add(Sorts.ascending(field))
      //          case 'd' => baseBranch.sorts.add(Sorts.descending(field))
      //        }
      //      }

      //      println(s"  ---  $dir  $arity  ${b.refAttr}  $field     ${b.path}")
      dir match {
        case 'a' => topBranch.sorts.add(Sorts.ascending(b.path + field))
        case 'd' => topBranch.sorts.add(Sorts.descending(b.path + field))
      }
    }

  }

  private def man[T](attr: Attr, args: Seq[T], res: ResOne[T]): Unit = {
    val field       = attr.attr
    val uniqueField = b.unique(field)
    projectField(uniqueField)
    addSort(attr, uniqueField)
    addCast(uniqueField, res.cast(uniqueField))

    prefixedFieldPair = if (b.parent.isEmpty) (field, field) else (b.path + field, b.alias + field)
    topBranch.groupIdFields += prefixedFieldPair

    attr.filterAttr.fold(
      expr(uniqueField, field, attr.op, args, res)
    )(filterAttr =>
      expr2(uniqueField, field, attr.op, filterAttr.name)
    )
  }

  private def tac[T](attr: Attr, args: Seq[T], res: ResOne[T]): Unit = {
    val field       = attr.attr
    val uniqueField = b.unique(field)
    attr.filterAttr.fold {
      expr(uniqueField, field, attr.op, args, res)
    } { filterAttr =>
      expr2(uniqueField, field, attr.op, filterAttr.name)
    }
  }

  private def opt[T](attr: Attr, optArgs: Option[Seq[T]], res: ResOne[T]): Unit = {
    val field       = attr.attr
    val uniqueField = b.unique(field)
    projectField(uniqueField)
    addCast(uniqueField, res.castOpt(uniqueField))
    addSort(attr, uniqueField)

    //    b.groupIdFields += ((b.und, b.dot, field))

    attr.op match {
      case V     => () // selected field can already be a value or null
      case Eq    => b.matches.add(optEqual(field, optArgs, res))
      case Neq   => b.matches.add(optNeq(field, optArgs, res))
      case Lt    => b.matches.add(optCompare(field, optArgs, res.lt))
      case Gt    => b.matches.add(optCompare(field, optArgs, res.gt))
      case Le    => b.matches.add(optCompare(field, optArgs, res.le))
      case Ge    => b.matches.add(optCompare(field, optArgs, res.ge))
      case other => unexpectedOp(other)
    }
  }


  private def expr[T](uniqueField: String, field: String, op: Op, args: Seq[T], res: ResOne[T]): Unit = {
    val pathField = b.dot + field
    op match {
      //      case V          => attr(pathField)
      case V          => attr(field)
      case Eq         => equal(pathField, args, res)
      case Neq        => neq(pathField, args, res)
      case Lt         => compare(pathField, res.lt(pathField, args.head))
      case Gt         => compare(pathField, res.gt(pathField, args.head))
      case Le         => compare(pathField, res.le(pathField, args.head))
      case Ge         => compare(pathField, res.ge(pathField, args.head))
      case NoValue    => noValue(pathField)
      case Fn(kw, n)  => aggr(uniqueField, field, kw, n, res)
      case StartsWith => startsWith(pathField, args.head)
      case EndsWith   => endsWith(pathField, args.head)
      case Contains   => contains(pathField, args.head)
      case Matches    => matches(pathField, args.head.toString)
      case Remainder  => remainder(pathField, args)
      case Even       => even(pathField)
      case Odd        => odd(pathField)
      case other      => unexpectedOp(other)
    }
  }

  private def expr2(uniqueField: String, field: String, op: Op, filterAttr: String): Unit = op match {
    case Eq    => equal2(field, filterAttr)
    case Neq   => neq2(field, filterAttr)
    case Lt    => compare2(field, "<", filterAttr)
    case Gt    => compare2(field, ">", filterAttr)
    case Le    => compare2(field, "<=", filterAttr)
    case Ge    => compare2(field, ">=", filterAttr)
    case other => unexpectedOp(other)
  }

  protected def attr[T](field: String): Unit = {
    val path = if (b.isEmbedded) b.dot else ""
    b.matches.add(Filters.ne(path + field, null.asInstanceOf[T]))
  }

  private def equal[T](field: String, args: Seq[T], res: ResOne[T]): Unit = {
    //    b.groupIdFields += ((b.und, b.dot, field))
    b.matches.add(equalValue(field, args, res))
  }
  private def equalValue[T](field: String, args: Seq[T], res: ResOne[T]): Bson = {
    args.length match {
      case 0 => Filters.eq("_id", -1)
      case 1 => res.eq(field, args.head)
      case _ => Filters.or(args.map(arg => res.eq(field, arg)).asJava)
    }
  }
  private def equal2(field: String, filterAttr: String): Unit = {
    //    where += ((field, "= " + filterAttr))
  }

  private def neq[T](field: String, args: Seq[T], res: ResOne[T]): Unit = {
    b.matches.add(neqValue(field, args, res))
    //    b.groupIdFields += ((b.und, b.dot, field))
  }
  private def neqValue[T](field: String, args: Seq[T], res: ResOne[T]): Bson = {
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

  private def compare(field: String, filter: Bson): Unit = {
    b.matches.add(filter)
    //    b.groupIdFields += ((b.und, b.dot, field))
  }
  private def compare2(field: String, op: String, filterAttr: String): Unit = {
    //    where += ((field, op + " " + filterAttr))
  }

  private def noValue(field: String): Unit = {
    b.matches.add(Filters.eq(field, new BsonNull()))
    //    b.groupIdFields += ((b.und, b.dot, field))
  }


  private def optEqual[T](field: String, optArgs: Option[Seq[T]], res: ResOne[T]): Bson = {
    optArgs.fold {
      Filters.eq(field, new BsonNull())
    } {
      case Nil => Filters.eq("_id", -1)
      case vs  => equalValue(field, vs, res)
    }
  }

  private def optNeq[T](field: String, optArgs: Option[Seq[T]], res: ResOne[T]): Bson = {
    if (optArgs.isDefined && optArgs.get.nonEmpty) {
      Filters.and(
        Filters.ne(field, null.asInstanceOf[T]),
        neqValue(field, optArgs.get, res),
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

  private def regex(field: String, pattern: String): Unit = {
    b.matches.add(Filters.regex(field, pattern))
    //    b.groupIdFields += ((b.und, b.dot, field))
  }
  private def startsWith[T](field: String, arg: T): Unit = {
    regex(field, s"^$arg.*")
  }
  private def endsWith[T](field: String, arg: T): Unit = {
    regex(field, s".*$arg$$")
  }
  private def contains[T](field: String, arg: T): Unit = {
    regex(field, s".*$arg.*")
  }
  private def matches(field: String, regex: String): Unit = {
    b.matches.add(
      if (regex.nonEmpty) {
        Filters.regex(field, regex)
      } else {
        // No filtering
        Filters.empty()
      }
    )
    //    b.groupIdFields += ((b.und, b.dot, field))
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


  private def aggr[T](uniqueField: String, field: String, fn: String, optN: Option[Int], res: ResOne[T]): Unit = {
    lazy val n          = optN.getOrElse(0)
    lazy val aliasField = b.alias + uniqueField
    lazy val pathField  = b.path + uniqueField
    lazy val pathField2 = "$" + b.path + field
    lazy val idField    = "$_id." + b.alias + field
    topBranch.groupIdFields -= prefixedFieldPair
    fn match {
      case "distinct" =>
        topBranch.groupAddToSet(aliasField, pathField2)
        addField(uniqueField)
        replaceCast(uniqueField, res.castSet(uniqueField))

      case "min" =>
        topBranch.groupExprs += aliasField -> new BsonDocument().append("$min", new BsonString(pathField2))
        addField(uniqueField)

      case "max" =>
        topBranch.groupExprs += aliasField -> new BsonDocument().append("$max", new BsonString(pathField2))
        addField(uniqueField)

      case "mins" =>
        topBranch.preGroupFields += pathField -> aliasField
        topBranch.groupExprs += aliasField -> aggrFn("$minN", new BsonString(idField), n)
        addField(uniqueField)
        replaceCast(uniqueField, res.castSet(uniqueField))

      case "maxs" =>
        topBranch.preGroupFields += pathField -> aliasField
        topBranch.groupExprs += aliasField -> aggrFn("$maxN", new BsonString(idField), n)
        addField(uniqueField)
        replaceCast(uniqueField, res.castSet(uniqueField))

      case "sample" =>
        sampleSize = 1

      case "samples" =>
        sampleSize = n
        topBranch.groupAddToSet(aliasField, pathField2)
        addField(uniqueField)
        replaceCast(uniqueField, res.castSet(uniqueField))

      case "count" =>
        topBranch.groupExprs += aliasField -> new BsonDocument().append("$sum", new BsonInt32(1))
        addField(uniqueField)
        replaceCast(uniqueField, castInt(uniqueField))

      case "countDistinct" =>
        topBranch.preGroupFields += pathField -> aliasField
        topBranch.groupExprs += aliasField -> new BsonDocument().append("$sum", new BsonInt32(1))
        addField(uniqueField)
        replaceCast(uniqueField, castInt(uniqueField))

      case "sum" =>
        topBranch.preGroupFields += pathField -> aliasField
        topBranch.groupExprs += aliasField -> new BsonDocument().append("$sum", new BsonString(idField))
        addField(uniqueField)

      case "median" =>
        topBranch.preGroupFields += pathField -> aliasField
        topBranch.groupExprs += aliasField -> new BsonDocument()
          .append("$median", new BsonDocument()
            .append("input", new BsonString(idField))
            .append("method", new BsonString("approximate"))
          )
        addField(uniqueField)
        replaceCast(uniqueField, hardCastDouble(uniqueField))

      case "avg" =>
        topBranch.preGroupFields += pathField -> aliasField
        topBranch.groupExprs += aliasField -> new BsonDocument().append("$avg", new BsonString(idField))
        addField(uniqueField)
        replaceCast(uniqueField, hardCastDouble(uniqueField))

      case "variance" =>
        topBranch.preGroupFields += pathField -> aliasField
        topBranch.groupExprs += aliasField -> new BsonDocument().append("$stdDevPop", new BsonString(idField))
        b.projection.remove(b.dot + uniqueField)
        val pow = new BsonArray()
        pow.add(new BsonString("$" + aliasField))
        pow.add(new BsonInt32(2))
        b.projection.append(b.dot + field, new BsonDocument().append("$pow", pow))
        replaceCast(uniqueField, hardCastDouble(uniqueField))

      case "stddev" =>
        topBranch.preGroupFields += pathField -> aliasField
        topBranch.groupExprs += aliasField -> new BsonDocument().append("$stdDevPop", new BsonString(idField))
        addField(uniqueField)
        replaceCast(uniqueField, hardCastDouble(uniqueField))

      case other => unexpectedKw(other)
    }
  }
}
