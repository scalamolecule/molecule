package molecule.document.mongodb.query

import com.mongodb.MongoClientSettings
import com.mongodb.client.model.mql.MqlValues._
import com.mongodb.client.model.{Filters, Projections, Sorts}
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.query.ResolveExpr
import org.bson
import org.bson._
import org.bson.conversions.Bson

trait ResolveExprOne extends ResolveExpr with LambdasOne with LambdasSet { self: MongoQueryBase =>

  var indexField = ""

  override protected def resolveAttrOneMan(attr: AttrOneMan): Unit = {
    //    aritiesAttr()
    fieldIndex += 1
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
    //    aritiesAttr()
    hasOptAttr = true // to avoid redundant None's
    fieldIndex += 1
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
    val uniqueField = unique(field)
    projectField(uniqueField)
    addCast(uniqueField, res.cast(uniqueField))
    addSort(attr, uniqueField)

    groupIdFields += ((pathUnderscore, pathDot, field))
    if (path.nonEmpty)
      addFields(path) = addFields(path) :+ uniqueField

    attr.filterAttr.fold(
      //      expr(pathDot + uniqueField, field, attr.op, args, res)
      expr(uniqueField, field, attr.op, args, res)
    )(filterAttr =>
      expr2(uniqueField, field, attr.op, filterAttr.name)
    )
  }

  private def tac[T](attr: Attr, args: Seq[T], res: ResOne[T]): Unit = {
    val field     = attr.attr
    val pathField = unique(field)
    attr.filterAttr.fold {
      expr(pathField, field, attr.op, args, res)
    } { filterAttr =>
      expr2(pathField, field, attr.op, filterAttr.name)
    }
  }


  private def opt[T](attr: Attr, optArgs: Option[Seq[T]], res: ResOne[T]): Unit = {
    val field       = attr.attr
    val uniqueField = unique(field)
    projectField(uniqueField)
    addCast(uniqueField, res.castOpt(uniqueField))
    addSort(attr, uniqueField)

    groupIdFields += ((pathUnderscore, pathDot, field))

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

  private def addMatch(field: String, op: String, value: BsonValue): Unit = {
    op match {
      case "$or" | "$and" => matches3(field -> op) = List(op -> value) // OR/AND is the only operation
      case _              => matches3(field -> op) = matches3.getOrElse(field -> op, Nil) :+ op -> value
    }
  }

  private def expr[T](uniqueField: String, field: String, op: Op, args: Seq[T], res: ResOne[T]): Unit = {
    val pathField = pathDot + field
    op match {
      case V          => addMatch(pathField, "$ne", new BsonNull)
      case Eq         => equal(field, args, res)
      case Neq        => neq(field, args, res)
      case Lt         => addMatch(pathField, "$lt", res.v2bson(args.head))
      case Gt         => addMatch(pathField, "$gt", res.v2bson(args.head))
      case Le         => addMatch(pathField, "$lte", res.v2bson(args.head))
      case Ge         => addMatch(pathField, "$gte", res.v2bson(args.head))
      case NoValue    => addMatch(pathField, "$eq", new BsonNull)
      case Fn(kw, n)  => aggr(uniqueField, field, kw, n, res)
      case StartsWith => addMatch(pathField, "regex", new BsonRegularExpression(s"^${args.head.toString}.*"))
      case EndsWith   => addMatch(pathField, "regex", new BsonRegularExpression(s".*${args.head.toString}$$"))
      case Contains   => addMatch(pathField, "regex", new BsonRegularExpression(s".*${args.head.toString}.*"))
      case Matches    => matches(pathField, args.head.toString)
      case Remainder  => remainder(uniqueField, args)
      case Even       => even(uniqueField)
      case Odd        => odd(uniqueField)
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

  private def equalOLD[T](field: String, args: Seq[T], res: ResOne[T]): Bson = {
    args.length match {
      case 0 => Filters.eq("_id", -1)
      case 1 => res.eq(field, args.head)
      case _ => Filters.or(args.map(arg => res.eq(field, arg)).asJava)
    }
  }
  private def equal[T](field: String, args: Seq[T], res: ResOne[T]): Unit = {
    args.length match {
      case 0 => addMatch("_id", "$eq", new BsonInt32(-1)) // no match
      case 1 => addMatch(pathDot + field, "$eq", res.v2bson(args.head))
      case _ =>
        val orMatches = new BsonArray()
        args.map(arg => orMatches.add(new BsonDocument().append(field, res.v2bson(arg))))
        addMatch(pathDot + field, "$or", orMatches)
    }
  }
  private def equal2(field: String, filterAttr: String): Unit = {
    //    where += ((field, "= " + filterAttr))
  }


  private def neqOLD[T](field: String, args: Seq[T], res: ResOne[T]): Bson = {
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
  private def neq[T](field: String, args: Seq[T], res: ResOne[T]): Unit = {
    if (args.nonEmpty) {
      val andMatches = new BsonArray()
      andMatches.add(new BsonDocument().append(field, new BsonDocument().append("$ne", new BsonNull)))
      args.map(arg => andMatches.add(
        new BsonDocument().append(field, new BsonDocument().append("$ne", res.v2bson(arg)))
      ))
      addMatch(pathDot + field, "$and", andMatches)
    } else {
      addMatch(pathDot + field, "$ne", new BsonNull)
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
      case Nil =>
        //        res.eq(field, null.asInstanceOf[T])
        Filters.eq("_id", -1)
      case vs  => equalOLD(field, vs, res)
    }
  }

  private def optNeq[T](field: String, optArgs: Option[Seq[T]], res: ResOne[T]): Bson = {
    if (optArgs.isDefined && optArgs.get.nonEmpty) {
      Filters.and(
        Filters.ne(field, null.asInstanceOf[T]),
        neqOLD(field, optArgs.get, res),
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

  /*
  {
    "string": {
      "$regularExpression": {
        "pattern": "^he.*",
        "options": ""
      }
    }
  }
   */
  private def regex(pattern: String) = {
    //    new BsonDocument()
    //      .append("pattern", new BsonRegularExpression(pattern))
    //      .append("options", new BsonString(""))

    new BsonRegularExpression(pattern)
  }

  private def startsWith[T](field: String, arg: T): Bson = Filters.regex(field, s"^$arg.*")
  private def endsWith[T](field: String, arg: T): Bson = Filters.regex(field, s".*$arg$$")
  private def contains[T](field: String, arg: T): Bson = Filters.regex(field, s".*$arg.*")
  private def matchesOLD(field: String, regex: String): Bson = {
    if (regex.nonEmpty) {
      Filters.regex(field, regex)
    } else {
      // No filtering
      Filters.empty()
    }
  }
  private def matches(field: String, pattern: String): Unit = {
    if (pattern.nonEmpty) {addMatch(pathDot + field, "regex", regex(pattern))}
    //    else {
    //      // No filtering
    //      Filters.empty()
    //      addMatch(pathDot + field, "", regex(pattern))
    //    }
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

  private def aggr[T](uniqueField: String, field: String, fn: String, optN: Option[Int], res: ResOne[T]): Unit = {
    lazy val n = optN.getOrElse(0)
    // Replace find/casting with aggregate function/cast
    //    groupIdFields -= field
    //    groupIdFields -= pathAlias + field
    //    groupIdFields -= (pathAlias + field) -> (pathDot + field)
    groupIdFields -= ((pathUnderscore, pathDot, field))

    fn match {
      case "distinct" =>
        groupExprs += ((pathUnderscore + uniqueField, new BsonDocument().append("$addToSet", new BsonString("$" + pathDot + field))))
        //        groupExprs += ((uniqueField, new BsonDocument().append("$addToSet", new BsonString("$" + pathDot + field))))
        replaceCast(uniqueField, res.castSet(uniqueField))

      case "min" =>
        groupExprs += ((pathUnderscore + uniqueField, new BsonDocument().append("$min", new BsonString("$" + pathDot + field))))
      //        if (path.nonEmpty)
      //          addFields3(path) = addFields3(path) :+ uniqueField


      case "mins" =>
        groupExprs += ((pathUnderscore + uniqueField,
          new BsonDocument().append("$minN",
            new BsonDocument()
              .append("input", new BsonString("$" + field))
              .append("n", new BsonInt32(n))
          )))
        replaceCast(uniqueField, res.castSet(uniqueField))

      case "max" =>
        groupExprs += ((pathUnderscore + uniqueField, new BsonDocument().append("$max", new BsonString("$" + pathDot + field))))
      //        if (path.nonEmpty)
      //          addFields3(path) = addFields3(path) :+ uniqueField

      case "maxs" =>
        groupExprs += ((pathUnderscore + uniqueField,
          new BsonDocument().append("$maxN",
            new BsonDocument()
              .append("input", new BsonString("$" + field))
              .append("n", new BsonInt32(n))
          )))
        replaceCast(uniqueField, res.castSet(uniqueField))

      case "rand" =>
        groupFields += (field -> field)
        //        groupExprs += ((pathAlias + uniqueField, new BsonString(field))) // instead of groupFields?
        sampleSize = 1

      case "rands" =>
        sampleSize = n
        groupExprs += ((field, new BsonDocument().append("$addToSet", new BsonString(field))))
        replaceCast(field, res.castSet(field))

      case "sample" =>
        groupFields += (field -> field)
        sampleSize = 1

      case "samples" =>
        sampleSize = n
        groupExprs += ((field, new BsonDocument().append("$addToSet", new BsonString(field))))
        replaceCast(field, res.castSet(field))

      case "count" =>
        removeField(field)
        projections.add(Projections.include(field))

        groupExprs += ((field, new BsonDocument().append("$sum", new BsonInt32(1))))
        replaceCast(field, castInt(field))

      case "countDistinct" =>
        preGroupFields += (field -> field)
        groupExprs += ((field, new BsonDocument().append("$sum", new BsonInt32(1))))
        replaceCast(field, castInt(field))

      //      case "sum" =>
      //        groupByCols -= field
      //        aggregate = true
      //        selectWithOrder(field, "SUM")
      //
      //      case "median" =>
      //        groupByCols -= field
      //        aggregate = true
      //        selectWithOrder(field, "MEDIAN")
      //
      //      case "avg" =>
      //        groupByCols -= field
      //        aggregate = true
      //        selectWithOrder(field, "AVG")
      //
      //      case "variance" =>
      //        groupByCols -= field
      //        aggregate = true
      //        selectWithOrder(field, "VAR_POP")
      //
      //      case "stddev" =>
      //        groupByCols -= field
      //        aggregate = true
      //        selectWithOrder(field, "STDDEV_POP")

      case other => unexpectedKw(other)
    }
  }

  private def selectWithOrder(
    field: String,
    fn: String,
    distinct: String = "DISTINCT ",
    cast: String = ""
  ): Unit = {
    if (orderBy.nonEmpty && orderBy.last._3 == field) {
      // order by aggregate alias instead
      val alias              = field.replace('.', '_') + "_" + fn.toLowerCase
      //      select += s"$fn($distinct$field$cast) $alias"
      val (level, _, _, dir) = orderBy.last
      orderBy.remove(orderBy.size - 1)
      orderBy += ((level, 1, alias, dir))
    } else {
      //      select += s"$fn($distinct$field$cast)"
    }
  }


}
