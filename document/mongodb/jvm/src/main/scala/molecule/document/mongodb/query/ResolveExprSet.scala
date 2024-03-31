package molecule.document.mongodb.query

import com.mongodb.client.model.{Filters, Projections}
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.query.ResolveExpr
import molecule.document.mongodb.query.mongoModel.{Branch, NestedEmbed, NestedRef}
import org.bson._
import org.bson.conversions.Bson

trait ResolveExprSet extends ResolveExpr { self: MongoQueryBase with LambdasSet =>

  override protected def resolveAttrSetMan(attr: AttrSetMan): Unit = {
    attr match {
      case at: AttrSetManID             => setMan(attr, at.vs, resSetID)
      case at: AttrSetManString         => setMan(attr, at.vs, resSetString)
      case at: AttrSetManInt            => setMan(attr, at.vs, resSetInt)
      case at: AttrSetManLong           => setMan(attr, at.vs, resSetLong)
      case at: AttrSetManFloat          => setMan(attr, at.vs, resSetFloat)
      case at: AttrSetManDouble         => setMan(attr, at.vs, resSetDouble)
      case at: AttrSetManBoolean        => setMan(attr, at.vs, resSetBoolean)
      case at: AttrSetManBigInt         => setMan(attr, at.vs, resSetBigInt)
      case at: AttrSetManBigDecimal     => setMan(attr, at.vs, resSetBigDecimal)
      case at: AttrSetManDate           => setMan(attr, at.vs, resSetDate)
      case at: AttrSetManDuration       => setMan(attr, at.vs, resSetDuration)
      case at: AttrSetManInstant        => setMan(attr, at.vs, resSetInstant)
      case at: AttrSetManLocalDate      => setMan(attr, at.vs, resSetLocalDate)
      case at: AttrSetManLocalTime      => setMan(attr, at.vs, resSetLocalTime)
      case at: AttrSetManLocalDateTime  => setMan(attr, at.vs, resSetLocalDateTime)
      case at: AttrSetManOffsetTime     => setMan(attr, at.vs, resSetOffsetTime)
      case at: AttrSetManOffsetDateTime => setMan(attr, at.vs, resSetOffsetDateTime)
      case at: AttrSetManZonedDateTime  => setMan(attr, at.vs, resSetZonedDateTime)
      case at: AttrSetManUUID           => setMan(attr, at.vs, resSetUUID)
      case at: AttrSetManURI            => setMan(attr, at.vs, resSetURI)
      case at: AttrSetManByte           => setMan(attr, at.vs, resSetByte)
      case at: AttrSetManShort          => setMan(attr, at.vs, resSetShort)
      case at: AttrSetManChar           => setMan(attr, at.vs, resSetChar)
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
    attr match {
      case _: AttrSetOptID             => setOpt(attr, resSetID)
      case _: AttrSetOptString         => setOpt(attr, resSetString)
      case _: AttrSetOptInt            => setOpt(attr, resSetInt)
      case _: AttrSetOptLong           => setOpt(attr, resSetLong)
      case _: AttrSetOptFloat          => setOpt(attr, resSetFloat)
      case _: AttrSetOptDouble         => setOpt(attr, resSetDouble)
      case _: AttrSetOptBoolean        => setOpt(attr, resSetBoolean)
      case _: AttrSetOptBigInt         => setOpt(attr, resSetBigInt)
      case _: AttrSetOptBigDecimal     => setOpt(attr, resSetBigDecimal)
      case _: AttrSetOptDate           => setOpt(attr, resSetDate)
      case _: AttrSetOptDuration       => setOpt(attr, resSetDuration)
      case _: AttrSetOptInstant        => setOpt(attr, resSetInstant)
      case _: AttrSetOptLocalDate      => setOpt(attr, resSetLocalDate)
      case _: AttrSetOptLocalTime      => setOpt(attr, resSetLocalTime)
      case _: AttrSetOptLocalDateTime  => setOpt(attr, resSetLocalDateTime)
      case _: AttrSetOptOffsetTime     => setOpt(attr, resSetOffsetTime)
      case _: AttrSetOptOffsetDateTime => setOpt(attr, resSetOffsetDateTime)
      case _: AttrSetOptZonedDateTime  => setOpt(attr, resSetZonedDateTime)
      case _: AttrSetOptUUID           => setOpt(attr, resSetUUID)
      case _: AttrSetOptURI            => setOpt(attr, resSetURI)
      case _: AttrSetOptByte           => setOpt(attr, resSetByte)
      case _: AttrSetOptShort          => setOpt(attr, resSetShort)
      case _: AttrSetOptChar           => setOpt(attr, resSetChar)
    }
  }


  private def setMan[T](attr: Attr, args: Set[T], resSet: ResSet[T]): Unit = {
    val field       = attr.attr
    val uniqueField = b.unique(field)
    projectField(field)
    addCast(field, resSet.castSet(field))
    prefixedFieldPair = if (b.parent.isEmpty)
      (nestedLevel, field, field)
    else
      (nestedLevel, b.path + field, b.alias + field)
    topBranch.groupIdFields += prefixedFieldPair
    handleExpr(uniqueField, field, attr, args, resSet, true)
  }

  private def tac[T](attr: Attr, args: Set[T], resSet: ResSet[T]): Unit = {
    val field       = attr.attr
    val uniqueField = b.unique(field)
    b.base.matches.add(Filters.exists(b.dot + field))
    handleExpr(uniqueField, field, attr, args, resSet, false)
  }

  private def handleExpr[T](
    uniqueField: String,
    field: String,
    attr: Attr,
    sets: Set[T],
    resSet: ResSet[T],
    mandatory: Boolean
  ): Unit = {
    if (branchesByPath.isEmpty) {
      path = List(attr.ns)
      pathLevels(path) = 0
    }
    branchesByPath(path) = b
    attr.filterAttr.fold {
      if (hasFilterAttr) {
        // Add filter if this attribute is a filter attribute pointed to
        postFilters.get(path :+ attr.cleanAttr).foreach(_(b))
      }
      attr.op match {
        case V       => setAttr(uniqueField, field, mandatory)
        case Eq      => noCollectionMatching(attr)
        case Has     => setHas(uniqueField, field, sets, resSet, mandatory)
        case HasNo   => setHasNo(uniqueField, field, sets, resSet, mandatory)
        case NoValue => if (mandatory) noApplyNothing(attr) else noValue(field)
        case other   => unexpectedOp(other)
      }
    } { filterAttr =>
      attr.op match {
        case Has   => setFilterHas(field, filterAttr, mandatory)
        case HasNo => setFilterHasNo(field, filterAttr, mandatory)
        case other => unexpectedOp(other)
      }
    }
  }

  private def setOpt[T](attr: Attr, res: ResSet[T]): Unit = {
    val field       = attr.attr
    val uniqueField = b.unique(field)
    projectField(field)
    addCast(field, res.castOptSet(field))

    prefixedFieldPair = if (b.parent.isEmpty)
      (nestedLevel, field, field)
    else
      (nestedLevel, b.path + field, b.alias + field)
    topBranch.groupIdFields += prefixedFieldPair

    attr.op match {
      case V     => setOptAttr(uniqueField, field)
      case Eq    => noCollectionMatching(attr)
      case other => unexpectedOp(other)
    }
  }


  // attr ----------------------------------------------------------------------

  private def setAttr(uniqueField: String, field: String, mandatory: Boolean): Unit = {
    //    println(s"\n======== $uniqueField ============================================")

    // Skipping this for all set attributes solves the own-ref rest in UpdateSet_id.
    // But what distinguishes it?
    b.base.matches.add(Filters.ne(b.dot + field, new BsonNull))

    // Exclude orphaned arrays too
    b.base.matches.add(Filters.ne(b.dot + field, new BsonArray()))
    coalesce(uniqueField, field, mandatory)
  }

  private def setOptAttr(uniqueField: String, field: String): Unit = {
    topBranch.groupIdFields -= prefixedFieldPair
    if (!(b.parent.isDefined && b.parent.get.isInstanceOf[NestedRef]
      || b.isInstanceOf[NestedRef])
    ) {
      // Separate nulls from arrays/sets of values when grouping
      topBranch.optSetSeparators += (field + "_") -> new BsonDocument("$cond", {
        val condArgs = new BsonArray()
        condArgs.add(new BsonDocument("$or", {
          val orArgs = new BsonArray()
          orArgs.add(
            new BsonDocument("$ifNull", {
              val ifNullArgs = new BsonArray()
              ifNullArgs.add(new BsonString("$" + b.path + field))
              ifNullArgs.add(new BsonBoolean(false))
              ifNullArgs
            })
          )
          orArgs.add(
            new BsonDocument("$ne", {
              val neArgs = new BsonArray()
              neArgs.add(new BsonString("$" + b.path + field))
              neArgs.add(new BsonArray())
              neArgs
            })
          )
          orArgs
        }))
        condArgs.add(new BsonBoolean(true))
        condArgs.add(new BsonBoolean(false))
        condArgs
      })

      topBranch.groupExprs += (b.alias + uniqueField) ->
        new BsonDocument("$addToSet", new BsonString("$" + b.path + field))
      topBranch.addFields += (b.path + field) -> reduce("$" + b.alias + field)
    }
  }

  private def setHas[T](
    uniqueField: String, field: String, set: Set[T], res: ResSet[T], mandatory: Boolean
  ): Unit = {
    set.size match {
      case 1 => b.base.matches.add(Filters.all(b.dot + field, res.v2bson(set.head)))
      case 0 => b.base.matches.add(Filters.eq("_id", -1))
      case _ => b.base.matches.add(Filters.or(
        set.map(v => Filters.all(b.dot + field, res.v2bson(v))).asJava
      ))
    }
    coalesce(uniqueField, field, mandatory)
  }

  private def setHasNo[T](
    uniqueField: String, field: String, set: Set[T], res: ResSet[T], mandatory: Boolean
  ): Unit = {
    b.base.matches.add(Filters.ne(b.dot + field, new BsonNull))
    // Exclude orphaned arrays too
    b.base.matches.add(Filters.ne(b.dot + field, new BsonArray()))
    set.size match {
      case 1 => b.base.matches.add(Filters.ne(b.dot + field, res.v2bson(set.head)))
      case 0 => b.base.matches.add(Filters.ne("_id", -1))
      case _ => b.base.matches.add(Filters.nor(
        set.map(v => Filters.all(b.dot + field, res.v2bson(v))).asJava
      ))
    }
    coalesce(uniqueField, field, mandatory)
  }

  private def noValue(field: String): Unit = {
    b.base.matches.remove(b.base.matches.size() - 1)
    b.base.matches.add(
      Filters.or(
        Filters.eq(b.dot + field, new BsonNull),
        Filters.eq(b.dot + field, new BsonArray)
      )
    )
  }


  // filter attribute ----------------------------------------------------------

  private def setFilterHas(
    field: String, filterAttr0: (Int, List[String], Attr), mandatory: Boolean
  ): Unit = {
    val (dir, filterPath, filterAttr1) = filterAttr0
    val filterAttr                     = filterAttr1.cleanAttr
    val args                           = new BsonArray()
    val cardOne                        = filterAttr1.isInstanceOf[AttrOne]
    dir match {
      case 0 =>
        val b1 = b
        val b2 = branchesByPath(filterPath)
        val op = if (cardOne) {
          args.add(new BsonString("$" + b2.path + filterAttr))
          args.add(new BsonString("$" + b1.path + field))
          "$in"
        } else {
          args.add(new BsonString("$" + b1.path + field))
          args.add(new BsonString("$" + b2.path + filterAttr))
          "$setIsSubset"
        }
        b.matches.add(Filters.eq("$expr", new BsonDocument(op, args)))

      case -1 =>
        val b1 = b
        val b2 = branchesByPath(filterPath)
        args.add(new BsonString("$" + b2.path + filterAttr))
        args.add(new BsonString("$" + b1.path + field))
        val op = if (cardOne) "$in" else "$setIsSubset"
        // Exclude grouping of calling set attribute
        topBranch.groupIdFields -= prefixedFieldPair
        // Filter before coalescing (running addAggregationStages in FlatEmbed)
        topBranch.preMatches.add(Filters.eq("$expr", new BsonDocument(op, args)))

      case 1 =>
        val b1        = b
        val addFilter = (b2: Branch) => {
          args.add(new BsonString("$" + b2.path + filterAttr))
          args.add(new BsonString("$" + b1.path + field))
          if (cardOne) {
            topBranch.preMatches.add(
              Filters.eq("$expr", new BsonDocument("$in", args))
            )
          } else {
            if (mandatory) {
              topBranch.groupIdFields -= prefixedFieldPair
            }
            topBranch.preMatches.add(
              Filters.eq("$expr", new BsonDocument("$setIsSubset", args))
            )
          }
        }
        postFilters(filterPath :+ filterAttr) = addFilter
    }

    if (mandatory) coalesceSets(field)
  }

  private def setFilterHasNo(
    field: String, filterAttr0: (Int, List[String], Attr), mandatory: Boolean
  ): Unit = {
    val (dir, filterPath, filterAttr1) = filterAttr0
    val filterAttr                     = filterAttr1.cleanAttr
    val fields                         = new BsonArray()
    val cardOne                        = filterAttr1.isInstanceOf[AttrOne]
    dir match {
      case 0 =>
        val b1 = b
        val b2 = branchesByPath(filterPath)
        if (cardOne) {
          fields.add(new BsonString("$" + b2.path + filterAttr))
          fields.add(new BsonString("$" + b1.path + field))
          b.matches.add(Filters.eq("$expr",
            new BsonDocument("$not", new BsonDocument("$in", fields))))
        } else {
          fields.add(new BsonString("$" + b1.path + field))
          fields.add(new BsonString("$" + filterAttr))
          val emptyArgs = new BsonArray()
          emptyArgs.add(new BsonDocument("$setIntersection", fields))
          emptyArgs.add(new BsonArray)
          b.matches.add(Filters.eq("$expr", new BsonDocument("$eq", emptyArgs)))
        }

      case -1 =>
        val b1 = b
        val b2 = branchesByPath(filterPath)
        if (cardOne) {
          fields.add(new BsonString("$" + b2.path + filterAttr))
          fields.add(new BsonString("$" + b1.path + field))
          // Exclude grouping of calling set attribute
          topBranch.groupIdFields -= prefixedFieldPair
          topBranch.preMatches.add(Filters.eq("$expr",
            new BsonDocument("$not", new BsonDocument("$in", fields))))
        } else {
          fields.add(new BsonString("$" + b1.path + field))
          fields.add(new BsonString("$" + b2.path + filterAttr))
          val emptyArgs = new BsonArray()
          emptyArgs.add(new BsonDocument("$setIntersection", fields))
          emptyArgs.add(new BsonArray)
          topBranch.preMatches.add(Filters.eq("$expr", new BsonDocument("$eq", emptyArgs)))
        }

      case 1 =>
        val b1        = b
        val addFilter = (b2: Branch) => {
          if (cardOne) {
            fields.add(new BsonString("$" + b2.path + filterAttr))
            fields.add(new BsonString("$" + b1.path + field))
            topBranch.preMatches.add(Filters.eq("$expr",
              new BsonDocument("$not", new BsonDocument("$in", fields))))
          } else {
            fields.add(new BsonString("$" + b1.path + field))
            fields.add(new BsonString("$" + b2.path + filterAttr))
            val emptyArgs = new BsonArray()
            emptyArgs.add(new BsonDocument("$setIntersection", fields))
            emptyArgs.add(new BsonArray)
            topBranch.preMatches.add(Filters.eq("$expr", new BsonDocument("$eq", emptyArgs)))
          }
        }
        postFilters(filterPath :+ filterAttr) = addFilter
    }

    if (mandatory) coalesceSets(field)
  }


  // helpers -------------------------------------------------------------------

  private def reduce(field: String): BsonDocument = {
    new BsonDocument("$reduce",
      new BsonDocument()
        .append("input", new BsonString(field))
        .append("initialValue", new BsonArray())
        .append("in", new BsonDocument("$setUnion", {
          val params = new BsonArray()
          params.add(new BsonString("$$value"))
          params.add(new BsonString("$$this"))
          params
        })))
  }

  private def coalesce(uniqueField: String, field: String, mandatory: Boolean): Unit = {
    // (there's probably a simpler way here...)
    if (mandatory
      && !b.isInstanceOf[NestedEmbed]
      && !(b.parent.isDefined && b.parent.get.isInstanceOf[NestedRef] || b.isInstanceOf[NestedRef])
    ) {
      topBranch.groupIdFields -= prefixedFieldPair
      topBranch.groupExprs += (b.alias + uniqueField) ->
        new BsonDocument("$addToSet", new BsonString("$" + b.path + field))
      topBranch.addFields += (b.path + field) -> reduce("$" + b.alias + field)
    }
  }

  private def coalesceSets(field: String) = {
    // Coalesce Sets of values to single Set
    topBranch.groupExprs += (b.alias + field) ->
      new BsonDocument("$addToSet", new BsonString("$" + b.path + field))
    topBranch.addFields += (b.path + field) -> reduce("$" + b.alias + field)
  }
}
