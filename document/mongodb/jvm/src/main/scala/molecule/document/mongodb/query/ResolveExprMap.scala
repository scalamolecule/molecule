package molecule.document.mongodb.query

import com.mongodb.client.model.Filters
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.query.ResolveExpr
import molecule.document.mongodb.query.mongoModel.{Branch, NestedEmbed, NestedRef}
import org.bson._
import org.bson.conversions.Bson

trait ResolveExprMap extends ResolveExpr with LambdasMap { self: MongoQueryBase =>

  override protected def resolveAttrMapMan(attr: AttrMapMan): Unit = {
    //    attr match {
    //      case at: AttrMapManID             => man(attr, at.vs, resSetID)
    //      case at: AttrMapManString         => man(attr, at.vs, resSetString)
    //      case at: AttrMapManInt            => man(attr, at.vs, resSetInt)
    //      case at: AttrMapManLong           => man(attr, at.vs, resSetLong)
    //      case at: AttrMapManFloat          => man(attr, at.vs, resSetFloat)
    //      case at: AttrMapManDouble         => man(attr, at.vs, resSetDouble)
    //      case at: AttrMapManBoolean        => man(attr, at.vs, resSetBoolean)
    //      case at: AttrMapManBigInt         => man(attr, at.vs, resSetBigInt)
    //      case at: AttrMapManBigDecimal     => man(attr, at.vs, resSetBigDecimal)
    //      case at: AttrMapManDate           => man(attr, at.vs, resSetDate)
    //      case at: AttrMapManDuration       => man(attr, at.vs, resSetDuration)
    //      case at: AttrMapManInstant        => man(attr, at.vs, resSetInstant)
    //      case at: AttrMapManLocalDate      => man(attr, at.vs, resSetLocalDate)
    //      case at: AttrMapManLocalTime      => man(attr, at.vs, resSetLocalTime)
    //      case at: AttrMapManLocalDateTime  => man(attr, at.vs, resSetLocalDateTime)
    //      case at: AttrMapManOffsetTime     => man(attr, at.vs, resSetOffsetTime)
    //      case at: AttrMapManOffsetDateTime => man(attr, at.vs, resSetOffsetDateTime)
    //      case at: AttrMapManZonedDateTime  => man(attr, at.vs, resSetZonedDateTime)
    //      case at: AttrMapManUUID           => man(attr, at.vs, resSetUUID)
    //      case at: AttrMapManURI            => man(attr, at.vs, resSetURI)
    //      case at: AttrMapManByte           => man(attr, at.vs, resSetByte)
    //      case at: AttrMapManShort          => man(attr, at.vs, resSetShort)
    //      case at: AttrMapManChar           => man(attr, at.vs, resSetChar)
    //    }
  }

  override protected def resolveAttrMapTac(attr: AttrMapTac): Unit = {
    //    attr match {
    //      case at: AttrMapTacID             => tac(attr, at.vs, resSetID)
    //      case at: AttrMapTacString         => tac(attr, at.vs, resSetString)
    //      case at: AttrMapTacInt            => tac(attr, at.vs, resSetInt)
    //      case at: AttrMapTacLong           => tac(attr, at.vs, resSetLong)
    //      case at: AttrMapTacFloat          => tac(attr, at.vs, resSetFloat)
    //      case at: AttrMapTacDouble         => tac(attr, at.vs, resSetDouble)
    //      case at: AttrMapTacBoolean        => tac(attr, at.vs, resSetBoolean)
    //      case at: AttrMapTacBigInt         => tac(attr, at.vs, resSetBigInt)
    //      case at: AttrMapTacBigDecimal     => tac(attr, at.vs, resSetBigDecimal)
    //      case at: AttrMapTacDate           => tac(attr, at.vs, resSetDate)
    //      case at: AttrMapTacDuration       => tac(attr, at.vs, resSetDuration)
    //      case at: AttrMapTacInstant        => tac(attr, at.vs, resSetInstant)
    //      case at: AttrMapTacLocalDate      => tac(attr, at.vs, resSetLocalDate)
    //      case at: AttrMapTacLocalTime      => tac(attr, at.vs, resSetLocalTime)
    //      case at: AttrMapTacLocalDateTime  => tac(attr, at.vs, resSetLocalDateTime)
    //      case at: AttrMapTacOffsetTime     => tac(attr, at.vs, resSetOffsetTime)
    //      case at: AttrMapTacOffsetDateTime => tac(attr, at.vs, resSetOffsetDateTime)
    //      case at: AttrMapTacZonedDateTime  => tac(attr, at.vs, resSetZonedDateTime)
    //      case at: AttrMapTacUUID           => tac(attr, at.vs, resSetUUID)
    //      case at: AttrMapTacURI            => tac(attr, at.vs, resSetURI)
    //      case at: AttrMapTacByte           => tac(attr, at.vs, resSetByte)
    //      case at: AttrMapTacShort          => tac(attr, at.vs, resSetShort)
    //      case at: AttrMapTacChar           => tac(attr, at.vs, resSetChar)
    //    }
  }

  override protected def resolveAttrMapOpt(attr: AttrMapOpt): Unit = {
    //    attr match {
    //      case at: AttrMapOptID             => opt(at, at.vs, resSetID)
    //      case at: AttrMapOptString         => opt(at, at.vs, resSetString)
    //      case at: AttrMapOptInt            => opt(at, at.vs, resSetInt)
    //      case at: AttrMapOptLong           => opt(at, at.vs, resSetLong)
    //      case at: AttrMapOptFloat          => opt(at, at.vs, resSetFloat)
    //      case at: AttrMapOptDouble         => opt(at, at.vs, resSetDouble)
    //      case at: AttrMapOptBoolean        => opt(at, at.vs, resSetBoolean)
    //      case at: AttrMapOptBigInt         => opt(at, at.vs, resSetBigInt)
    //      case at: AttrMapOptBigDecimal     => opt(at, at.vs, resSetBigDecimal)
    //      case at: AttrMapOptDate           => opt(at, at.vs, resSetDate)
    //      case at: AttrMapOptDuration       => opt(at, at.vs, resSetDuration)
    //      case at: AttrMapOptInstant        => opt(at, at.vs, resSetInstant)
    //      case at: AttrMapOptLocalDate      => opt(at, at.vs, resSetLocalDate)
    //      case at: AttrMapOptLocalTime      => opt(at, at.vs, resSetLocalTime)
    //      case at: AttrMapOptLocalDateTime  => opt(at, at.vs, resSetLocalDateTime)
    //      case at: AttrMapOptOffsetTime     => opt(at, at.vs, resSetOffsetTime)
    //      case at: AttrMapOptOffsetDateTime => opt(at, at.vs, resSetOffsetDateTime)
    //      case at: AttrMapOptZonedDateTime  => opt(at, at.vs, resSetZonedDateTime)
    //      case at: AttrMapOptUUID           => opt(at, at.vs, resSetUUID)
    //      case at: AttrMapOptURI            => opt(at, at.vs, resSetURI)
    //      case at: AttrMapOptByte           => opt(at, at.vs, resSetByte)
    //      case at: AttrMapOptShort          => opt(at, at.vs, resSetShort)
    //      case at: AttrMapOptChar           => opt(at, at.vs, resSetChar)
    //    }
  }
  //
  //
  //  private def man[T](attr: Attr, args: Seq[Set[T]], resSet: ResSet[T]): Unit = {
  //    val field       = attr.attr
  //    val uniqueField = b.unique(field)
  //    projectField(field)
  //    addCast(field, resSet.castSet(field))
  //    prefixedFieldPair = if (b.parent.isEmpty)
  //      (nestedLevel, field, field)
  //    else
  //      (nestedLevel, b.path + field, b.alias + field)
  //    topBranch.groupIdFields += prefixedFieldPair
  //    handleExpr(uniqueField, field, attr, args, resSet, true)
  //  }
  //
  //  private def tac[T](attr: Attr, args: Seq[Set[T]], resSet: ResSet[T]): Unit = {
  //    val field       = attr.attr
  //    val uniqueField = b.unique(field)
  //    b.base.matches.add(Filters.exists(b.dot + field))
  //    handleExpr(uniqueField, field, attr, args, resSet, false)
  //  }
  //
  //  private def handleExpr[T](
  //    uniqueField: String,
  //    field: String,
  //    attr: Attr,
  //    args: Seq[Set[T]],
  //    resSet: ResSet[T],
  //    mandatory: Boolean
  //  ): Unit = {
  //    if (branchesByPath.isEmpty) {
  //      path = List(attr.ns)
  //      pathLevels(path) = 0
  //    }
  //    branchesByPath(path) = b
  //    attr.filterAttr.fold {
  //      if (hasFilterAttr) {
  //        // Add filter if this attribute is a filter attribute pointed to
  //        postFilters.get(path :+ attr.cleanAttr).foreach(_(b))
  //      }
  //      expr(uniqueField, field, attr.op, args, resSet, mandatory)
  //    } { filterAttr =>
  //      attr.op match {
  //        case Eq    => equal2(field, filterAttr, mandatory)
  //        case Neq   => neq2(field, filterAttr, mandatory)
  //        case Has   => has2(field, filterAttr, mandatory)
  //        case HasNo => hasNo2(field, filterAttr, mandatory)
  //        case other => unexpectedOp(other)
  //      }
  //    }
  //  }
  //
  //  private def expr[T](
  //    uniqueField: String,
  //    field: String,
  //    op: Op,
  //    sets: Seq[Set[T]],
  //    res: ResSet[T],
  //    mandatory: Boolean
  //  ): Unit = {
  //    op match {
  //      case V         => attr(uniqueField, field, mandatory)
  //      case Eq        => equal(field, sets, res)
  //      case Neq       => neq(uniqueField, field, sets, res, mandatory)
  //      case Has       => has(uniqueField, field, sets, res, mandatory)
  //      case HasNo     => hasNo(uniqueField, field, sets, res, mandatory)
  //      case NoValue   => noValue(field)
  //      case Fn(kw, n) => aggr(uniqueField, field, kw, n, res)
  //      case other     => unexpectedOp(other)
  //    }
  //  }
  //
  //  private def opt[T](attr: Attr, optSets: Option[Seq[Set[T]]], res: ResSet[T]): Unit = {
  //    val field       = attr.attr
  //    val uniqueField = b.unique(field)
  //    projectField(field)
  //    addCast(field, res.castOptSet(field))
  //
  //    prefixedFieldPair = if (b.parent.isEmpty)
  //      (nestedLevel, field, field)
  //    else
  //      (nestedLevel, b.path + field, b.alias + field)
  //    topBranch.groupIdFields += prefixedFieldPair
  //
  //    attr.op match {
  //      case V     => optAttr(uniqueField, field)
  //      case Eq    => optEqual(field, optSets, res)
  //      case Neq   => optNeq(uniqueField, field, optSets, res)
  //      case Has   => optHas(uniqueField, field, optSets, res)
  //      case HasNo => optHasNo(uniqueField, field, optSets, res)
  //      case other => unexpectedOp(other)
  //    }
  //  }
  //
  //
  //  // attr ----------------------------------------------------------------------
  //
  //  private def attr(uniqueField: String, field: String, mandatory: Boolean): Unit = {
  //    b.base.matches.add(Filters.ne(b.dot + field, new BsonNull))
  //    // Exclude orphaned arrays too
  //    b.base.matches.add(Filters.ne(b.dot + field, new BsonArray()))
  //    coalesce(uniqueField, field, mandatory)
  //  }
  //
  //  private def optAttr[T](uniqueField: String, field: String): Unit = {
  //    topBranch.groupIdFields -= prefixedFieldPair
  //    if (!(b.parent.isDefined && b.parent.get.isInstanceOf[NestedRef]
  //      || b.isInstanceOf[NestedRef])
  //    ) {
  //      // Separate nulls from arrays/sets of values when grouping
  //      topBranch.optSetSeparators += (field + "_") -> new BsonDocument("$cond", {
  //        val condArgs = new BsonArray()
  //        condArgs.add(new BsonDocument("$or", {
  //          val orArgs = new BsonArray()
  //          orArgs.add(
  //            new BsonDocument("$ifNull", {
  //              val ifNullArgs = new BsonArray()
  //              ifNullArgs.add(new BsonString("$" + b.path + field))
  //              ifNullArgs.add(new BsonBoolean(false))
  //              ifNullArgs
  //            })
  //          )
  //          orArgs.add(
  //            new BsonDocument("$ne", {
  //              val neArgs = new BsonArray()
  //              neArgs.add(new BsonString("$" + b.path + field))
  //              neArgs.add(new BsonArray())
  //              neArgs
  //            })
  //          )
  //          orArgs
  //        }))
  //        condArgs.add(new BsonBoolean(true))
  //        condArgs.add(new BsonBoolean(false))
  //        condArgs
  //      })
  //
  //      topBranch.groupExprs += (b.alias + uniqueField) ->
  //        new BsonDocument("$addToSet", new BsonString("$" + b.path + field))
  //      topBranch.addFields += (b.path + field) -> reduce("$" + b.alias + field)
  //    }
  //  }
  //
  //
  //  // eq ------------------------------------------------------------------------
  //
  //  private def equal[T](field: String, sets0: Seq[Set[T]], res: ResSet[T]): Unit = {
  //    def filter(set: Set[T]): Bson = filterSet(field, set, res)
  //    val sets = sets0.filterNot(_.isEmpty)
  //    sets.length match {
  //      case 0 => b.base.matches.add(Filters.eq("_id", -1))
  //      case 1 => b.base.matches.add(filter(sets.head))
  //      case _ => b.base.matches.add(Filters.or(sets.map(filter).asJava))
  //    }
  //  }
  //
  //  private def optEqual[T](
  //    field: String, optSets: Option[Seq[Set[T]]], res: ResSet[T]
  //  ): Unit = {
  //    optSets.fold[Unit] {
  //      b.base.matches.add(Filters.eq(b.dot + field, new BsonNull))
  //    } { sets =>
  //      equal(field, sets, res)
  //    }
  //  }
  //
  //
  //  // neq -----------------------------------------------------------------------
  //
  //  private def neq[T](
  //    uniqueField: String, field: String, sets0: Seq[Set[T]], res: ResSet[T], mandatory: Boolean
  //  ): Unit = {
  //    b.base.matches.add(Filters.ne(b.dot + field, new BsonNull))
  //    // Exclude orphaned arrays too
  //    b.base.matches.add(Filters.ne(b.dot + field, new BsonArray()))
  //
  //    def filter(set: Set[T]): Bson = filterSet(field, set, res)
  //    val sets = sets0.filterNot(_.isEmpty)
  //    sets.length match {
  //      case 0 => ()
  //      case 1 => b.base.matches.add(Filters.nor(filter(sets.head)))
  //      case _ => b.base.matches.add(Filters.nor(Filters.or(sets.map(filter).asJava)))
  //    }
  //    coalesce(uniqueField, field, mandatory)
  //  }
  //
  //  private def optNeq[T](
  //    uniqueField: String, field: String, optSets: Option[Seq[Set[T]]], res: ResSet[T]
  //  ): Unit = {
  //    if (optSets.isDefined && optSets.get.nonEmpty) {
  //      neq(uniqueField, field, optSets.get, res, true)
  //    }
  //    b.base.matches.add(Filters.ne(b.dot + field, new BsonNull))
  //    coalesce(uniqueField, field, true)
  //  }
  //
  //
  //  // has -----------------------------------------------------------------------
  //
  //  private def has[T](
  //    uniqueField: String, field: String, sets0: Seq[Set[T]], res: ResSet[T], mandatory: Boolean
  //  ): Unit = {
  //    def containsSet(set: Set[T]): Bson = {
  //      Filters.all(b.dot + field, set.map(v => res.v2bson(v)).asJava)
  //    }
  //    val sets = sets0.filterNot(_.isEmpty)
  //    sets.length match {
  //      case 0 => b.base.matches.add(Filters.eq("_id", -1))
  //      case 1 =>
  //        val set = sets.head
  //        if (set.nonEmpty)
  //          b.base.matches.add(containsSet(set))
  //        else
  //          b.base.matches.add(Filters.eq("_id", -1))
  //
  //      case _ => b.base.matches.add(Filters.or(sets.map(set => containsSet(set)).asJava))
  //    }
  //    coalesce(uniqueField, field, mandatory)
  //  }
  //
  //  private def optHas[T](
  //    uniqueField: String, field: String, optSets: Option[Seq[Set[T]]], res: ResSet[T] //, mandatory: Boolean
  //  ): Unit = {
  //    optSets.fold[Unit] {
  //      b.base.matches.add(Filters.eq(b.dot + field, new BsonNull))
  //    } { sets =>
  //      has(uniqueField, field, sets, res, false)
  //    }
  //  }
  //
  //
  //  // hasNo ---------------------------------------------------------------------
  //
  //  private def hasNo[T](
  //    uniqueField: String, field: String, sets: Seq[Set[T]], res: ResSet[T], mandatory: Boolean
  //  ): Unit = {
  //    b.base.matches.add(Filters.ne(b.dot + field, new BsonNull))
  //    // Exclude orphaned arrays too
  //    b.base.matches.add(Filters.ne(b.dot + field, new BsonArray()))
  //    def notContainsSet(set: Set[T]): Bson = Filters.nor(
  //      Filters.all(b.dot + field, set.map(v => res.v2bson(v)).asJava)
  //    )
  //    sets.length match {
  //      case 0 => ()
  //      case 1 =>
  //        val set = sets.head
  //        if (set.nonEmpty)
  //          b.base.matches.add(notContainsSet(set))
  //
  //      case _ => b.base.matches.add(Filters.and(sets.map(set => notContainsSet(set)).asJava))
  //    }
  //    coalesce(uniqueField, field, mandatory)
  //  }
  //
  //  private def optHasNo[T](
  //    uniqueField: String, field: String, optSets: Option[Seq[Set[T]]], res: ResSet[T]
  //  ): Unit = {
  //    optSets.foreach { sets0 =>
  //      val sets = sets0.filterNot(_.isEmpty)
  //      if (sets.nonEmpty) {
  //        hasNo(uniqueField, field, sets, res, false)
  //      }
  //    }
  //    b.base.matches.add(Filters.ne(b.dot + field, new BsonNull))
  //    coalesce(uniqueField, field, true)
  //  }
  //
  //
  //  // no value -----------------------------------------------------------------
  //
  //  private def noValue(field: String): Unit = {
  //    b.base.matches.remove(b.base.matches.size() - 1)
  //    b.base.matches.add(
  //      Filters.or(
  //        Filters.eq(b.dot + field, new BsonNull),
  //        Filters.eq(b.dot + field, new BsonArray)
  //      )
  //    )
  //  }
  //
  //
  //  // aggregation ---------------------------------------------------------------
  //
  //  private def aggr[T](
  //    uniqueField: String, field: String, fn: String, optN: Option[Int], res: ResSet[T]
  //  ): Unit = {
  //    checkAggrSet()
  //    lazy val n           = optN.getOrElse(0)
  //    lazy val aliasField  = b.alias + uniqueField
  //    lazy val pathField   = b.path + field
  //    lazy val aliasField2 = "$" + aliasField
  //    lazy val pathField2  = "$" + pathField
  //    topBranch.groupIdFields -= prefixedFieldPair
  //    fn match {
  //      case "distinct" =>
  //        noBooleanSetAggr(res)
  //        b.base.matches.add(Filters.ne(b.dot + field, new BsonNull))
  //        topBranch.groupExprs += aliasField ->
  //          new BsonDocument("$addToSet", new BsonString(pathField2))
  //        addField(uniqueField)
  //        replaceCast(uniqueField, res.castSetSet(uniqueField))
  //
  //      case "min" =>
  //        noBooleanSetAggr(res)
  //        topBranch.groupAddToSet(aliasField, pathField2)
  //        topBranch.addFields += pathField -> aggrFn("$minN", reduce(aliasField2), 1)
  //
  //      case "max" =>
  //        noBooleanSetAggr(res)
  //        topBranch.groupAddToSet(aliasField, pathField2)
  //        topBranch.addFields += pathField -> aggrFn("$maxN", reduce(aliasField2), 1)
  //
  //      case "mins" =>
  //        noBooleanSetAggr(res)
  //        topBranch.groupAddToSet(aliasField, pathField2)
  //        topBranch.addFields += pathField -> aggrFn("$minN", reduce(aliasField2), n)
  //
  //      case "maxs" =>
  //        noBooleanSetAggr(res)
  //        topBranch.groupAddToSet(aliasField, pathField2)
  //        topBranch.addFields += pathField -> aggrFn("$maxN", reduce(aliasField2), n)
  //
  //      case "sample" =>
  //        noBooleanSetAggr(res)
  //        sampleSize = 1
  //
  //      case "samples" =>
  //        noBooleanSetAggr(res)
  //        sampleSize = n
  //
  //      case "count" =>
  //        noBooleanSetCounts(n)
  //        topBranch.groupExpr(aliasField,
  //          new BsonDocument("$sum", new BsonDocument("$size", new BsonString(pathField2)))
  //        )
  //        addField(uniqueField)
  //        replaceCast(uniqueField, castInt(uniqueField))
  //
  //      case "countDistinct" =>
  //        noBooleanSetCounts(n)
  //        topBranch.groupAddToSet(aliasField, pathField2)
  //        topBranch.addFields += pathField -> new BsonDocument("$size", reduce(aliasField2))
  //        replaceCast(uniqueField, castInt(uniqueField))
  //
  //      case "sum" =>
  //        topBranch.groupExprs += aliasField -> new BsonDocument("$sum",
  //          new BsonDocument("$sum", new BsonString(pathField2))
  //        )
  //        topBranch.addFields += pathField -> new BsonString(aliasField2)
  //        replaceCast(uniqueField, res.v2set(uniqueField))
  //
  //      case "median" =>
  //        topBranch.aggregate = true
  //        topBranch.groupAddToSet(aliasField, pathField2)
  //        topBranch.addFields += pathField -> new BsonDocument("$median",
  //          new BsonDocument()
  //            .append("input", reduce(aliasField2))
  //            .append("method", new BsonString("approximate"))
  //        )
  //        replaceCast(uniqueField, hardCastDouble(uniqueField))
  //
  //      case "avg" =>
  //        topBranch.unwinds += pathField2
  //        topBranch.groupExprs += aliasField -> new BsonDocument("$avg", new BsonString(pathField2))
  //        topBranch.addFields += pathField -> new BsonString(aliasField2)
  //        replaceCast(uniqueField, hardCastDouble(uniqueField))
  //
  //      case "variance" =>
  //        topBranch.unwinds += pathField2
  //        topBranch.groupExprs += aliasField -> new BsonDocument("$stdDevPop", new BsonString(pathField2))
  //        topBranch.addFields += pathField -> new BsonString(aliasField2)
  //        b.projection.remove(b.dot + uniqueField)
  //        val pow = new BsonArray()
  //        pow.add(new BsonString(pathField2))
  //        pow.add(new BsonInt32(2))
  //        b.projection.append(b.dot + field, new BsonDocument("$pow", pow))
  //        replaceCast(uniqueField, hardCastDouble(uniqueField))
  //
  //      case "stddev" =>
  //        topBranch.unwinds += pathField2
  //        topBranch.groupExprs += aliasField -> new BsonDocument("$stdDevPop", new BsonString(pathField2))
  //        topBranch.addFields += pathField -> new BsonString(aliasField2)
  //        replaceCast(uniqueField, hardCastDouble(uniqueField))
  //
  //      case other => unexpectedKw(other)
  //    }
  //  }
  //
  //
  //  // filter attribute ----------------------------------------------------------
  //
  //  private def equal2(
  //    field: String,
  //    filterAttr0: (Int, List[String], Attr),
  //    mandatory: Boolean
  //  ): Unit = {
  //    handleFilterExpr(field, "$eq", filterAttr0, mandatory)
  //  }
  //
  //  private def neq2(
  //    field: String, filterAttr0: (Int, List[String], Attr), mandatory: Boolean
  //  ): Unit = {
  //    handleFilterExpr(field, "$ne", filterAttr0, mandatory)
  //  }
  //
  //  private def has2(
  //    field: String, filterAttr0: (Int, List[String], Attr), mandatory: Boolean
  //  ): Unit = {
  //    val (dir, filterPath, filterAttr1) = filterAttr0
  //    val filterAttr                     = filterAttr1.cleanAttr
  //    val args                           = new BsonArray()
  //    val cardOne                        = filterAttr1.isInstanceOf[AttrOne]
  //    dir match {
  //      case 0 =>
  //        val b1 = b
  //        val b2 = branchesByPath(filterPath)
  //        val op = if (cardOne) {
  //          args.add(new BsonString("$" + b2.path + filterAttr))
  //          args.add(new BsonString("$" + b1.path + field))
  //          "$in"
  //        } else {
  //          args.add(new BsonString("$" + b1.path + field))
  //          args.add(new BsonString("$" + b2.path + filterAttr))
  //          "$setIsSubset"
  //        }
  //        b.matches.add(Filters.eq("$expr", new BsonDocument(op, args)))
  //
  //      case -1 =>
  //        val b1 = b
  //        val b2 = branchesByPath(filterPath)
  //        args.add(new BsonString("$" + b2.path + filterAttr))
  //        args.add(new BsonString("$" + b1.path + field))
  //        val op = if (cardOne) "$in" else "$setIsSubset"
  //        // Exclude grouping of calling set attribute
  //        topBranch.groupIdFields -= prefixedFieldPair
  //        // Filter before coalescing (running addAggregationStages in FlatEmbed)
  //        topBranch.preMatches.add(Filters.eq("$expr", new BsonDocument(op, args)))
  //
  //      case 1 =>
  //        val b1        = b
  //        val addFilter = (b2: Branch) => {
  //          args.add(new BsonString("$" + b2.path + filterAttr))
  //          args.add(new BsonString("$" + b1.path + field))
  //          if (cardOne) {
  //            topBranch.preMatches.add(
  //              Filters.eq("$expr", new BsonDocument("$in", args))
  //            )
  //          } else {
  //            if (mandatory) {
  //              topBranch.groupIdFields -= prefixedFieldPair
  //            }
  //            topBranch.preMatches.add(
  //              Filters.eq("$expr", new BsonDocument("$setIsSubset", args))
  //            )
  //          }
  //        }
  //        postFilters(filterPath :+ filterAttr) = addFilter
  //    }
  //
  //    if (mandatory) coalesceSets(field)
  //  }
  //
  //  private def hasNo2(
  //    field: String, filterAttr0: (Int, List[String], Attr), mandatory: Boolean
  //  ): Unit = {
  //    val (dir, filterPath, filterAttr1) = filterAttr0
  //    val filterAttr                     = filterAttr1.cleanAttr
  //    val fields                         = new BsonArray()
  //    val cardOne                        = filterAttr1.isInstanceOf[AttrOne]
  //    dir match {
  //      case 0 =>
  //        val b1 = b
  //        val b2 = branchesByPath(filterPath)
  //        if (cardOne) {
  //          fields.add(new BsonString("$" + b2.path + filterAttr))
  //          fields.add(new BsonString("$" + b1.path + field))
  //          b.matches.add(Filters.eq("$expr",
  //            new BsonDocument("$not", new BsonDocument("$in", fields))))
  //        } else {
  //          fields.add(new BsonString("$" + b1.path + field))
  //          fields.add(new BsonString("$" + filterAttr))
  //          val emptyArgs = new BsonArray()
  //          emptyArgs.add(new BsonDocument("$setIntersection", fields))
  //          emptyArgs.add(new BsonArray)
  //          b.matches.add(Filters.eq("$expr", new BsonDocument("$eq", emptyArgs)))
  //        }
  //
  //      case -1 =>
  //        val b1 = b
  //        val b2 = branchesByPath(filterPath)
  //        if (cardOne) {
  //          fields.add(new BsonString("$" + b2.path + filterAttr))
  //          fields.add(new BsonString("$" + b1.path + field))
  //          // Exclude grouping of calling set attribute
  //          topBranch.groupIdFields -= prefixedFieldPair
  //          topBranch.preMatches.add(Filters.eq("$expr",
  //            new BsonDocument("$not", new BsonDocument("$in", fields))))
  //        } else {
  //          fields.add(new BsonString("$" + b1.path + field))
  //          fields.add(new BsonString("$" + b2.path + filterAttr))
  //          val emptyArgs = new BsonArray()
  //          emptyArgs.add(new BsonDocument("$setIntersection", fields))
  //          emptyArgs.add(new BsonArray)
  //          topBranch.preMatches.add(Filters.eq("$expr", new BsonDocument("$eq", emptyArgs)))
  //        }
  //
  //      case 1 =>
  //        val b1        = b
  //        val addFilter = (b2: Branch) => {
  //          if (cardOne) {
  //            fields.add(new BsonString("$" + b2.path + filterAttr))
  //            fields.add(new BsonString("$" + b1.path + field))
  //            topBranch.preMatches.add(Filters.eq("$expr",
  //              new BsonDocument("$not", new BsonDocument("$in", fields))))
  //          } else {
  //            fields.add(new BsonString("$" + b1.path + field))
  //            fields.add(new BsonString("$" + b2.path + filterAttr))
  //            val emptyArgs = new BsonArray()
  //            emptyArgs.add(new BsonDocument("$setIntersection", fields))
  //            emptyArgs.add(new BsonArray)
  //            topBranch.preMatches.add(Filters.eq("$expr", new BsonDocument("$eq", emptyArgs)))
  //          }
  //        }
  //        postFilters(filterPath :+ filterAttr) = addFilter
  //    }
  //
  //    if (mandatory) coalesceSets(field)
  //  }
  //
  //
  //  // helpers -------------------------------------------------------------------
  //
  //  private def handleFilterExpr(
  //    field: String, op: String, filterAttr0: (Int, List[String], Attr), mandatory: Boolean
  //  ): Unit = {
  //    //        println(topBranch)
  //    val (dir, filterPath, filterAttr1) = filterAttr0
  //    val filterAttr                     = filterAttr1.cleanAttr
  //    val args                           = new BsonArray()
  //    dir match {
  //      case 0 => // Same namespace
  //        args.add(new BsonString("$" + field))
  //        args.add(new BsonString("$" + filterAttr))
  //        // Base and filter attributes can be compared directly
  //        b.matches.add(Filters.eq("$expr", new BsonDocument(op, args)))
  //
  //      case -1 => // Backwards
  //        val b1 = b
  //        val b2 = branchesByPath(filterPath)
  //        args.add(new BsonString("$" + b1.path + field))
  //        args.add(new BsonString("$" + b2.path + filterAttr))
  //        // Exclude grouping of calling set attribute
  //        topBranch.groupIdFields -= prefixedFieldPair
  //        // Filter before coalescing (running addAggregationStages in FlatEmbed)
  //        topBranch.preMatches.add(Filters.eq("$expr", new BsonDocument(op, args)))
  //
  //      case 1 => // Forward
  //        val b1        = b
  //        val addFilter = (b2: Branch) => {
  //          args.add(new BsonString("$" + b1.path + field))
  //          args.add(new BsonString("$" + b2.path + filterAttr))
  //          topBranch.preMatches.add(Filters.eq("$expr", new BsonDocument(op, args)))
  //        }
  //        postFilters(filterPath :+ filterAttr) = addFilter
  //    }
  //    if (mandatory) coalesceSets(field)
  //  }
  //
  //  private def reduce(field: String): BsonDocument = {
  //    new BsonDocument("$reduce",
  //      new BsonDocument()
  //        .append("input", new BsonString(field))
  //        .append("initialValue", new BsonArray())
  //        .append("in", new BsonDocument("$setUnion", {
  //          val params = new BsonArray()
  //          params.add(new BsonString("$$value"))
  //          params.add(new BsonString("$$this"))
  //          params
  //        })))
  //  }
  //
  //  private def filterSet[T](field: String, set: Set[T], res: ResSet[T]): Bson = {
  //    Filters.and(
  //      Filters.size(b.dot + field, set.size),
  //      Filters.all(b.dot + field, set.map(res.v2bson).asJava)
  //    )
  //  }
  //
  //  private def coalesce(uniqueField: String, field: String, mandatory: Boolean): Unit = {
  //    // (there's probably a simpler way here...)
  //    if (mandatory
  //      && !b.isInstanceOf[NestedEmbed]
  //      && !(b.parent.isDefined && b.parent.get.isInstanceOf[NestedRef] || b.isInstanceOf[NestedRef])
  //    ) {
  //      topBranch.groupIdFields -= prefixedFieldPair
  //      topBranch.groupExprs += (b.alias + uniqueField) ->
  //        new BsonDocument("$addToSet", new BsonString("$" + b.path + field))
  //      topBranch.addFields += (b.path + field) -> reduce("$" + b.alias + field)
  //    }
  //  }
  //
  //  private def coalesceSets(field: String) = {
  //    // Coalesce Sets of values to single Set
  //    topBranch.groupExprs += (b.alias + field) ->
  //      new BsonDocument("$addToSet", new BsonString("$" + b.path + field))
  //    topBranch.addFields += (b.path + field) -> reduce("$" + b.alias + field)
  //  }
  //
  //  private def noBooleanSetAggr[T](res: ResSet[T]): Unit = {
  //    if (res.tpe == "Boolean")
  //      throw ModelError("Aggregate functions not implemented for Sets of boolean values.")
  //  }
  //  private def noBooleanSetCounts(n: Int): Unit = {
  //    if (n == -1)
  //      throw ModelError("Aggregate functions not implemented for Sets of boolean values.")
  //  }
}
