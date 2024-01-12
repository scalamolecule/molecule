package molecule.document.mongodb.query

import com.mongodb.client.model.{Filters, Projections}
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.query.ResolveExpr
import org.bson._
import org.bson.conversions.Bson

trait ResolveExprSet extends ResolveExpr { self: MongoQueryBase with LambdasSet =>

  override protected def resolveAttrSetMan(attr: AttrSetMan): Unit = {
    attr match {
      case at: AttrSetManID             => man(attr, at.vs, resSetID)
      case at: AttrSetManString         => man(attr, at.vs, resSetString)
      case at: AttrSetManInt            => man(attr, at.vs, resSetInt)
      case at: AttrSetManLong           => man(attr, at.vs, resSetLong)
      case at: AttrSetManFloat          => man(attr, at.vs, resSetFloat)
      case at: AttrSetManDouble         => man(attr, at.vs, resSetDouble)
      case at: AttrSetManBoolean        => man(attr, at.vs, resSetBoolean)
      case at: AttrSetManBigInt         => man(attr, at.vs, resSetBigInt)
      case at: AttrSetManBigDecimal     => man(attr, at.vs, resSetBigDecimal)
      case at: AttrSetManDate           => man(attr, at.vs, resSetDate)
      case at: AttrSetManDuration       => man(attr, at.vs, resSetDuration)
      case at: AttrSetManInstant        => man(attr, at.vs, resSetInstant)
      case at: AttrSetManLocalDate      => man(attr, at.vs, resSetLocalDate)
      case at: AttrSetManLocalTime      => man(attr, at.vs, resSetLocalTime)
      case at: AttrSetManLocalDateTime  => man(attr, at.vs, resSetLocalDateTime)
      case at: AttrSetManOffsetTime     => man(attr, at.vs, resSetOffsetTime)
      case at: AttrSetManOffsetDateTime => man(attr, at.vs, resSetOffsetDateTime)
      case at: AttrSetManZonedDateTime  => man(attr, at.vs, resSetZonedDateTime)
      case at: AttrSetManUUID           => man(attr, at.vs, resSetUUID)
      case at: AttrSetManURI            => man(attr, at.vs, resSetURI)
      case at: AttrSetManByte           => man(attr, at.vs, resSetByte)
      case at: AttrSetManShort          => man(attr, at.vs, resSetShort)
      case at: AttrSetManChar           => man(attr, at.vs, resSetChar)
    }
  }

  override protected def resolveAttrSetTac(attr: AttrSetTac): Unit = {
    attr match {
      case at: AttrSetTacID             => tac(attr, at.vs, resSetID)
      case at: AttrSetTacString         => tac(attr, at.vs, resSetString)
      case at: AttrSetTacInt            => tac(attr, at.vs, resSetInt)
      case at: AttrSetTacLong           => tac(attr, at.vs, resSetLong)
      case at: AttrSetTacFloat          => tac(attr, at.vs, resSetFloat)
      case at: AttrSetTacDouble         => tac(attr, at.vs, resSetDouble)
      case at: AttrSetTacBoolean        => tac(attr, at.vs, resSetBoolean)
      case at: AttrSetTacBigInt         => tac(attr, at.vs, resSetBigInt)
      case at: AttrSetTacBigDecimal     => tac(attr, at.vs, resSetBigDecimal)
      case at: AttrSetTacDate           => tac(attr, at.vs, resSetDate)
      case at: AttrSetTacDuration       => tac(attr, at.vs, resSetDuration)
      case at: AttrSetTacInstant        => tac(attr, at.vs, resSetInstant)
      case at: AttrSetTacLocalDate      => tac(attr, at.vs, resSetLocalDate)
      case at: AttrSetTacLocalTime      => tac(attr, at.vs, resSetLocalTime)
      case at: AttrSetTacLocalDateTime  => tac(attr, at.vs, resSetLocalDateTime)
      case at: AttrSetTacOffsetTime     => tac(attr, at.vs, resSetOffsetTime)
      case at: AttrSetTacOffsetDateTime => tac(attr, at.vs, resSetOffsetDateTime)
      case at: AttrSetTacZonedDateTime  => tac(attr, at.vs, resSetZonedDateTime)
      case at: AttrSetTacUUID           => tac(attr, at.vs, resSetUUID)
      case at: AttrSetTacURI            => tac(attr, at.vs, resSetURI)
      case at: AttrSetTacByte           => tac(attr, at.vs, resSetByte)
      case at: AttrSetTacShort          => tac(attr, at.vs, resSetShort)
      case at: AttrSetTacChar           => tac(attr, at.vs, resSetChar)
    }
  }

  override protected def resolveAttrSetOpt(attr: AttrSetOpt): Unit = {
    hasOptAttr = true // to avoid redundant None's
    attr match {
      case at: AttrSetOptID             => opt(at, at.vs, resSetID)
      case at: AttrSetOptString         => opt(at, at.vs, resSetString)
      case at: AttrSetOptInt            => opt(at, at.vs, resSetInt)
      case at: AttrSetOptLong           => opt(at, at.vs, resSetLong)
      case at: AttrSetOptFloat          => opt(at, at.vs, resSetFloat)
      case at: AttrSetOptDouble         => opt(at, at.vs, resSetDouble)
      case at: AttrSetOptBoolean        => opt(at, at.vs, resSetBoolean)
      case at: AttrSetOptBigInt         => opt(at, at.vs, resSetBigInt)
      case at: AttrSetOptBigDecimal     => opt(at, at.vs, resSetBigDecimal)
      case at: AttrSetOptDate           => opt(at, at.vs, resSetDate)
      case at: AttrSetOptDuration       => opt(at, at.vs, resSetDuration)
      case at: AttrSetOptInstant        => opt(at, at.vs, resSetInstant)
      case at: AttrSetOptLocalDate      => opt(at, at.vs, resSetLocalDate)
      case at: AttrSetOptLocalTime      => opt(at, at.vs, resSetLocalTime)
      case at: AttrSetOptLocalDateTime  => opt(at, at.vs, resSetLocalDateTime)
      case at: AttrSetOptOffsetTime     => opt(at, at.vs, resSetOffsetTime)
      case at: AttrSetOptOffsetDateTime => opt(at, at.vs, resSetOffsetDateTime)
      case at: AttrSetOptZonedDateTime  => opt(at, at.vs, resSetZonedDateTime)
      case at: AttrSetOptUUID           => opt(at, at.vs, resSetUUID)
      case at: AttrSetOptURI            => opt(at, at.vs, resSetURI)
      case at: AttrSetOptByte           => opt(at, at.vs, resSetByte)
      case at: AttrSetOptShort          => opt(at, at.vs, resSetShort)
      case at: AttrSetOptChar           => opt(at, at.vs, resSetChar)
    }
  }


  private def man[T](attr: Attr, args: Seq[Set[T]], resSet: ResSet[T]): Unit = {
    val field       = attr.attr
    val uniqueField = b.unique(field)
    projectField(field)
    addCast(field, resSet.castSet(field))

    prefixedFieldPair = if (b.parent.isEmpty) (field, field) else (b.path + field, b.alias + field)
    topBranch.groupIdFields += prefixedFieldPair

    attr.filterAttr.fold {
      if (filterAttrVars.contains(attr.name) && attr.op != V) {
        // Runtime check needed since we can't type infer it
        throw ModelError(s"Cardinality-set filter attributes not allowed to do additional filtering. Found:\n  " + attr)
      }
      expr(uniqueField, field, attr.op, args, resSet, true)
    } {
      case filterAttr: AttrOne => ??? //setExpr2(field, attr.op, filterAttr.name, true, tpe)
      case filterAttr          => ??? //setExpr2(field, attr.op, filterAttr.name, false, tpe)
    }
  }

  private def tac[T](attr: Attr, args: Seq[Set[T]], res: ResSet[T]): Unit = {
    val field       = attr.attr
    val uniqueField = b.unique(field)
    b.base.matches.add(Filters.exists(field))
    attr.filterAttr.fold {
      expr(uniqueField, field, attr.op, args, res, false)
    } {
      //      expr2(field, attr.op, filterAttr.name)
      case filterAttr: AttrOne => expr2(field, attr.op, filterAttr.name, true, res.tpe)
      case filterAttr          => expr2(field, attr.op, filterAttr.name, false, res.tpe)
    }
  }

  private def opt[T](attr: Attr, optSets: Option[Seq[Set[T]]], res: ResSet[T]): Unit = {
    val field       = attr.attr
    val uniqueField = b.unique(field)
    projectField(field)
    addCast(field, res.castOptSet(field))

    prefixedFieldPair = if (b.parent.isEmpty) (field, field) else (b.path + field, b.alias + field)
    topBranch.groupIdFields += prefixedFieldPair

    attr.op match {
      case V     => optAttr(uniqueField, field)
      case Eq    => optEqual(field, optSets, res)
      case Neq   => optNeq(field, optSets, res)
      case Has   => optHas(field, optSets, res)
      case HasNo => optHasNo(field, optSets, res)
      case other => unexpectedOp(other)
    }
  }

  private def expr[T](
    uniqueField: String,
    field: String,
    op: Op,
    sets: Seq[Set[T]],
    res: ResSet[T],
    mandatory: Boolean
  ): Unit = {
    op match {
      case V         => attr(uniqueField, field, mandatory)
      case Eq        => equal(field, sets, res)
      case Neq       => neq(field, sets, res)
      case Has       => has(field, sets, res)
      case HasNo     => hasNo(field, sets, res)
      case NoValue   => noValue(field)
      case Fn(kw, n) => aggr(uniqueField, field, kw, n, res)
      case other     => unexpectedOp(other)
    }
  }

  private def expr2(field: String, op: Op, filterAttr: String, cardOne: Boolean, tpe: String): Unit = {
    op match {
      case Eq    => equal2(field, filterAttr)
      case Neq   => neq2(field, filterAttr)
      case Has   => has2(field, filterAttr, cardOne, tpe)
      case HasNo => hasNo2(field, filterAttr, cardOne, tpe)
      case other => unexpectedOp(other)
    }
  }

  private def noBooleanSetAggr[T](res: ResSet[T]): Unit = {
    if (res.tpe == "Boolean")
      throw ModelError("Aggregate functions not implemented for Sets of boolean values.")
  }
  private def noBooleanSetCounts(n: Int): Unit = {
    if (n == -1)
      throw ModelError("Aggregate functions not implemented for Sets of boolean values.")
  }


  private def attr[T](uniqueField: String, field: String, mandatory: Boolean): Unit = {
    b.base.matches.add(Filters.ne(b.dot + field, null.asInstanceOf[T]))
    // Exclude orphaned arrays too
    b.base.matches.add(Filters.ne(b.dot + field, new BsonArray()))
    if (mandatory) {
      topBranch.groupIdFields -= prefixedFieldPair
      topBranch.groupExprs += (b.alias + uniqueField) -> new BsonDocument()
        .append("$addToSet", new BsonString("$" + b.path + field))
      topBranch.addFields += (b.path + field) -> reduce("$" + b.alias + field)
    }
  }

  private def optAttr[T](uniqueField: String, field: String): Unit = {
    topBranch.groupIdFields -= prefixedFieldPair

    // Separate nulls from arrays/sets of values when grouping

    val ifNull = new BsonArray()
    ifNull.add(new BsonString("$" + b.path + field))
    ifNull.add(new BsonBoolean(false))

    val condArgs = new BsonArray()
    condArgs.add(new BsonDocument("$ifNull", ifNull))
    condArgs.add(new BsonBoolean(true))
    condArgs.add(new BsonBoolean(false))

    topBranch.optSetSeparators += (field + "_") -> new BsonDocument("$cond", condArgs)

    topBranch.groupExprs += (b.alias + uniqueField) -> new BsonDocument()
      .append("$addToSet", new BsonString("$" + b.path + field))
    topBranch.addFields += (b.path + field) -> reduce("$" + b.alias + field)
  }


  private def filterSet[T](field: String, set: Set[T], res: ResSet[T]): Bson = {
    Filters.and(
      Filters.size(field, set.size),
      Filters.all(field, set.map(res.v2bson).asJava)
    )
  }

  private def equal[T](field: String, sets0: Seq[Set[T]], res: ResSet[T]): Unit = {
    def filter(set: Set[T]): Bson = filterSet(field, set, res)
    val sets = sets0.filterNot(_.isEmpty)
    sets.length match {
      case 0 => b.base.matches.add(Filters.eq("_id", -1))
      case 1 => b.base.matches.add(filter(sets.head))
      case _ => b.base.matches.add(Filters.or(sets.map(filter).asJava))
    }
  }

  private def equal2(field: String, filterAttr: String): Unit = {
    //    where += ((field, "= " + filterAttr))
    val args = new BsonArray()
    args.add(new BsonString("$" + field))
    //    args.add(new BsonString("$" + filterAttr))
    args.add(new BsonString("$ref." + filterAttr))
    b.base.matches.add(Filters.eq("$expr", new BsonDocument("$in", args)))
    b.filterMatches.add(Filters.eq("$expr", new BsonDocument("$in", args)))
  }


  private def optEqual[T](field: String, optSets: Option[Seq[Set[T]]], res: ResSet[T]): Unit = {
    optSets.fold[Unit] {
      //      where += ((field, s"IS NULL"))
      b.base.matches.add(Filters.eq(field, null.asInstanceOf[T]))
    } { sets =>
      equal(field, sets, res)
    }
  }

  private def neq[T](field: String, sets0: Seq[Set[T]], res: ResSet[T]): Unit = {
    def filter(set: Set[T]): Bson = filterSet(field, set, res)
    val sets = sets0.filterNot(_.isEmpty)
    sets.length match {
      case 0 => ()
      case 1 => b.base.matches.add(Filters.nor(filter(sets.head)))
      case _ => b.base.matches.add(Filters.nor(Filters.or(sets.map(filter).asJava)))
    }
  }

  private def neq2(field: String, filterAttr: String): Unit = {
    //    where += ((field, "<> " + filterAttr))
  }

  private def optNeq[T](field: String, optSets: Option[Seq[Set[T]]], res: ResSet[T]): Unit = {
    if (optSets.isDefined && optSets.get.nonEmpty) {
      neq(field, optSets.get, res)
    }
    b.base.matches.add(Filters.ne(field, null.asInstanceOf[T]))
  }

  private def has[T](field: String, sets0: Seq[Set[T]], res: ResSet[T]): Unit = {
    def containsSet(set: Set[T]): Bson = {
      Filters.all(field, set.map(v => res.v2bson(v)).asJava)
    }
    val sets = sets0.filterNot(_.isEmpty)
    sets.length match {
      case 0 => b.base.matches.add(Filters.eq("_id", -1))
      case 1 =>
        val set = sets.head
        if (set.nonEmpty)
          b.base.matches.add(containsSet(set))
        else
          b.base.matches.add(Filters.eq("_id", -1))

      case _ => b.base.matches.add(Filters.or(sets.map(set => containsSet(set)).asJava))
    }
  }

  private def has2(field: String, filterAttr: String, cardOne: Boolean, tpe: String): Unit = {
    if (cardOne) {
      //      where += (("", s"ARRAY_CONTAINS($field, $filterAttr)"))
    } else {
      //      where += (("", s"has_$tpe($field, $filterAttr)"))
    }
  }

  private def optHas[T](
    field: String,
    optSets: Option[Seq[Set[T]]],
    //    one2sql: T => String
    res: ResSet[T]
  ): Unit = {
    optSets.fold[Unit] {
      b.base.matches.add(Filters.eq(field, null.asInstanceOf[T]))
    } { sets =>
      has(field, sets, res)
    }
  }

  private def hasNo[T](field: String, sets: Seq[Set[T]], res: ResSet[T]): Unit = {
    def notContainsSet(set: Set[T]): Bson = Filters.nor(Filters.all(field, set.map(v => res.v2bson(v)).asJava))
    sets.length match {
      case 0 => ()
      case 1 =>
        val set = sets.head
        if (set.nonEmpty)
          b.base.matches.add(notContainsSet(set))

      case _ => b.base.matches.add(Filters.and(sets.map(set => notContainsSet(set)).asJava))
    }
  }

  private def hasNo2(field: String, filterAttr: String, cardOne: Boolean, tpe: String): Unit = {
    if (cardOne) {
      //      where += (("", s"NOT ARRAY_CONTAINS($field, $filterAttr)"))
    } else {
      //      where += (("", s"hasNo_$tpe($field, $filterAttr)"))
    }
  }

  private def optHasNo[T](
    field: String,
    optSets: Option[Seq[Set[T]]],
    res: ResSet[T]
  ): Unit = {
    optSets.foreach { sets0 =>
      val sets = sets0.filterNot(_.isEmpty)
      if (sets.nonEmpty) {
        hasNo(field, sets, res)
      }
    }
    b.base.matches.add(Filters.ne(field, null.asInstanceOf[T]))
  }

  private def noValue(field: String): Unit = {
    //    notNull -= field
    //    where += ((field, s"IS NULL"))
  }


  private def reduce(field: String): BsonDocument = {
    new BsonDocument().append("$reduce",
      new BsonDocument()
        .append("input", new BsonString(field))
        .append("initialValue", new BsonArray())
        .append("in", new BsonDocument()
          .append("$setUnion", {
            val params = new BsonArray()
            params.add(new BsonString("$$value"))
            params.add(new BsonString("$$this"))
            params
          })
        )
    )
  }


  private def aggr[T](uniqueField: String, field: String, fn: String, optN: Option[Int], res: ResSet[T]): Unit = {
    lazy val n           = optN.getOrElse(0)
    lazy val aliasField  = b.alias + uniqueField
    lazy val pathField   = b.path + field
    lazy val aliasField2 = "$" + aliasField
    lazy val pathField2  = "$" + pathField
    topBranch.groupIdFields -= prefixedFieldPair
    fn match {
      case "distinct" =>
        noBooleanSetAggr(res)
        b.base.matches.add(Filters.ne(b.dot + field, null.asInstanceOf[T]))
        topBranch.groupExprs += aliasField -> new BsonDocument().append("$addToSet", new BsonString(pathField2))
        addField(uniqueField)
        replaceCast(uniqueField, res.castSetSet(uniqueField))

      case "min" =>
        noBooleanSetAggr(res)
        topBranch.groupAddToSet(aliasField, pathField2)
        topBranch.addFields += pathField -> aggrFn("$minN", reduce(aliasField2), 1)

      case "max" =>
        noBooleanSetAggr(res)
        topBranch.groupAddToSet(aliasField, pathField2)
        topBranch.addFields += pathField -> aggrFn("$maxN", reduce(aliasField2), 1)

      case "mins" =>
        noBooleanSetAggr(res)
        topBranch.groupAddToSet(aliasField, pathField2)
        topBranch.addFields += pathField -> aggrFn("$minN", reduce(aliasField2), n)

      case "maxs" =>
        noBooleanSetAggr(res)
        topBranch.groupAddToSet(aliasField, pathField2)
        topBranch.addFields += pathField -> aggrFn("$maxN", reduce(aliasField2), n)

      case "sample" =>
        noBooleanSetAggr(res)
        sampleSize = 1

      case "samples" =>
        noBooleanSetAggr(res)
        sampleSize = n

      case "count" =>
        noBooleanSetCounts(n)
        topBranch.groupExpr(aliasField,
          new BsonDocument().append("$sum",
            new BsonDocument().append("$size", new BsonString(pathField2)))
        )
        addField(uniqueField)
        replaceCast(uniqueField, castInt(uniqueField))

      case "countDistinct" =>
        noBooleanSetCounts(n)
        topBranch.groupAddToSet(aliasField, pathField2)
        topBranch.addFields += pathField -> new BsonDocument().append("$size", reduce(aliasField2))
        replaceCast(uniqueField, castInt(uniqueField))

      case "sum" =>
        topBranch.groupAddToSet(aliasField, pathField2)
        topBranch.addFields += pathField -> new BsonDocument().append("$sum", reduce(aliasField2))
        replaceCast(uniqueField, res.v2set(uniqueField))

      case "median" =>
        topBranch.groupAddToSet(aliasField, pathField2)
        topBranch.addFields += pathField -> new BsonDocument()
          .append("$median",
            new BsonDocument()
              .append("input", reduce(aliasField2))
              .append("method", new BsonString("approximate"))
          )
        replaceCast(uniqueField, hardCastDouble(uniqueField))

      case "avg" =>
        topBranch.groupAddToSet(aliasField, pathField2)
        topBranch.addFields += pathField -> new BsonDocument().append("$avg", reduce(aliasField2))
        replaceCast(uniqueField, hardCastDouble(uniqueField))

      case "variance" =>
        topBranch.groupAddToSet(aliasField, pathField2)
        topBranch.addFields += pathField -> new BsonDocument().append("$stdDevPop", reduce(aliasField2))
        b.projection.remove(b.dot + uniqueField)
        val pow = new BsonArray()
        pow.add(new BsonString(pathField2))
        pow.add(new BsonInt32(2))
        b.projection.append(b.dot + field, new BsonDocument().append("$pow", pow))
        replaceCast(uniqueField, hardCastDouble(uniqueField))

      case "stddev" =>
        topBranch.groupAddToSet(aliasField, pathField2)
        topBranch.addFields += pathField -> new BsonDocument().append("$stdDevPop", reduce(aliasField2))
        replaceCast(uniqueField, hardCastDouble(uniqueField))

      case other => unexpectedKw(other)
    }
  }
}
