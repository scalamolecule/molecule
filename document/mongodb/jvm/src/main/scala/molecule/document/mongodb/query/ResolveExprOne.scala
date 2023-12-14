package molecule.document.mongodb.query

import com.mongodb.client.model.{Filters, Projections, Sorts}
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.query.ResolveExpr
import org.bson._
import org.bson.conversions.Bson

trait ResolveExprOne extends ResolveExpr with LambdasOne with LambdasSet { self: MongoQueryBase =>

  override protected def resolveAttrOneMan(attr: AttrOneMan): Unit = {
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
    //todo
    /*
    {
      "$project": {
        "_id": 0,
        "1_s": 1,
        "bb": {
          $sortArray: {
            input: "$bb",
            sortBy: {
              "i": -1
            }
          }
        }
      }
    },
     */
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
    val field       = attr.attr
    val uniqueField = b.unique(field)
    projectField(uniqueField)
    addSort(attr, uniqueField)
    addCast(uniqueField, b.nested, res.cast(uniqueField))

    if (b.path.nonEmpty) {
      val prefix = if (attr.op.isInstanceOf[Fn]) "$" else "$_id."
      b.addField(uniqueField, new BsonString(prefix + b.pathUnderscore + uniqueField))
    }

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
    //    b.projectField(uniqueField)
    projectField(uniqueField)
    addCast(uniqueField, b.nested, res.castOpt(uniqueField))
    addSort(attr, uniqueField)

    b.groupIdFields += ((b.pathUnderscore, b.pathDot, field))

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
    b.matches.add(filter)
  }


  private def expr[T](uniqueField: String, field: String, op: Op, args: Seq[T], res: ResOne[T]): Unit = {
    val pathField = b.pathDot + field
    op match {
      case V          => attr(field)
      case Eq         => equal(pathField, args, res)
      case Neq        => neq(pathField, args, res)
      case Lt         => compare(field, res.lt(pathField, args.head))
      case Gt         => compare(field, res.gt(pathField, args.head))
      case Le         => compare(field, res.le(pathField, args.head))
      case Ge         => compare(field, res.ge(pathField, args.head))
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
    b.matches.add(Filters.ne(b.pathDot + field, null.asInstanceOf[T]))
    b.groupIdFields += ((b.pathUnderscore, b.pathDot, field))
  }

  private def equal[T](field: String, args: Seq[T], res: ResOne[T]): Unit = {
    b.matches.add(equalValue(field, args, res))
    b.groupIdFields += ((b.pathUnderscore, b.pathDot, field))
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
    b.groupIdFields += ((b.pathUnderscore, b.pathDot, field))
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
    b.groupIdFields += ((b.pathUnderscore, b.pathDot, field))
  }
  private def compare2(field: String, op: String, filterAttr: String): Unit = {
    //    where += ((field, op + " " + filterAttr))
  }

  private def noValue(field: String): Unit = {
    b.matches.add(Filters.eq(field, new BsonNull()))
    b.groupIdFields += ((b.pathUnderscore, b.pathDot, field))
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
    b.groupIdFields += ((b.pathUnderscore, b.pathDot, field))
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
    b.groupIdFields += ((b.pathUnderscore, b.pathDot, field))
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
    lazy val n         = optN.getOrElse(0)
    lazy val dotField  = "$" + b.pathDot + field
    lazy val usField   = "$" + b.pathUnderscore + field
    lazy val idUsField = "$_id." + b.pathUnderscore + field
    fn match {
      case "distinct" =>
        b.groupSets(uniqueField, if (b.path.nonEmpty) dotField else usField)
        replaceCast(uniqueField, b.embedded, res.castSet(uniqueField))

      case "min" =>
        b.groupExpr(uniqueField, new BsonDocument().append("$min", new BsonString(dotField)))

      case "mins" =>
        b.preGroupFields += ((b.pathUnderscore + uniqueField) -> (b.pathDot + field))
        b.groupExpr(uniqueField, aggrFn("$minN", new BsonString(idUsField), n))
        replaceCast(uniqueField, b.embedded, res.castSet(uniqueField))

      case "max" =>
        b.groupExpr(uniqueField, new BsonDocument().append("$max", new BsonString(dotField)))

      case "maxs" =>
        b.preGroupFields += ((b.pathUnderscore + uniqueField) -> (b.pathDot + field))
        b.groupExpr(uniqueField, aggrFn("$maxN", new BsonString(idUsField), n))
        replaceCast(uniqueField, b.embedded, res.castSet(uniqueField))

      case "sample" =>
        sampleSize = 1

      case "samples" =>
        sampleSize = n
        b.groupSets(uniqueField, dotField)
        replaceCast(uniqueField, b.embedded, res.castSet(uniqueField))

      case "count" =>
        b.groupExpr(uniqueField, new BsonDocument().append("$sum", new BsonInt32(1)))
        replaceCast(uniqueField, b.embedded, castInt(uniqueField))

      case "countDistinct" =>
        b.preGroupFields += ((b.pathUnderscore + uniqueField) -> (b.pathDot + field))
        b.groupExpr(uniqueField, new BsonDocument().append("$sum", new BsonInt32(1)))
        replaceCast(uniqueField, b.embedded, castInt(uniqueField))

      case "sum" =>
        b.preGroupFields += ((b.pathUnderscore + uniqueField) -> (b.pathDot + field))
        b.groupExpr(uniqueField, new BsonDocument().append("$sum", new BsonString(idUsField)))

      case "median" =>
        b.preGroupFields += ((b.pathUnderscore + uniqueField) -> (b.pathDot + field))
        b.groupExprs += ((b.pathUnderscore + uniqueField, new BsonDocument().append("$median",
          new BsonDocument()
            .append("input", new BsonString(idUsField))
            .append("method", new BsonString("approximate"))
        )))
        replaceCast(uniqueField, b.embedded, hardCastDouble(uniqueField))

      case "avg" =>
        b.preGroupFields += ((b.pathUnderscore + uniqueField) -> (b.pathDot + field))
        b.groupExpr(uniqueField, new BsonDocument().append("$avg", new BsonString(idUsField)))
        replaceCast(uniqueField, b.embedded, hardCastDouble(uniqueField))

      case "variance" =>
        b.preGroupFields += ((b.pathUnderscore + uniqueField) -> (b.pathDot + field))
        b.groupExpr(uniqueField, new BsonDocument().append("$stdDevPop", new BsonString(idUsField)))
        //        b.removeField(b.pathDot + field)
        removeField(b.pathDot + field)
        val pow = new BsonArray()
        pow.add(new BsonString(dotField))
        pow.add(new BsonInt32(2))
        // todo
        //        projections.add(new BsonDocument().append(b.pathDot + field, new BsonDocument().append("$pow", pow)))
        projections(b.path) = projections(b.path).append(field, new BsonInt32(1))

        replaceCast(uniqueField, b.embedded, hardCastDouble(uniqueField))

      case "stddev" =>
        b.preGroupFields += ((b.pathUnderscore + uniqueField) -> (b.pathDot + field))
        b.groupExpr(uniqueField, new BsonDocument().append("$stdDevPop", new BsonString(idUsField)))
        replaceCast(uniqueField, b.embedded, hardCastDouble(uniqueField))

      case other => unexpectedKw(other)
    }
  }
}
