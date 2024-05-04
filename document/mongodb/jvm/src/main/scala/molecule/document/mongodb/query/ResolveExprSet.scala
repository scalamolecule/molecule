package molecule.document.mongodb.query

import com.mongodb.client.model.Filters
import molecule.boilerplate.ast.Model._
import molecule.core.query.ResolveExpr
import molecule.document.mongodb.query.mongoModel.{Branch, NestedEmbed, NestedRef}
import org.bson._

trait ResolveExprSet extends ResolveExpr with LambdasSet { self: MongoQueryBase =>

  override protected def resolveAttrSetMan(attr: AttrSetMan): Unit = {
    attr match {
      case a: AttrSetManID             => if (a.owner) noEmbeddedIds else setMan(attr, a.vs, resSetID)
      case a: AttrSetManString         => setMan(attr, a.vs, resSetString)
      case a: AttrSetManInt            => setMan(attr, a.vs, resSetInt)
      case a: AttrSetManLong           => setMan(attr, a.vs, resSetLong)
      case a: AttrSetManFloat          => setMan(attr, a.vs, resSetFloat)
      case a: AttrSetManDouble         => setMan(attr, a.vs, resSetDouble)
      case a: AttrSetManBoolean        => setMan(attr, a.vs, resSetBoolean)
      case a: AttrSetManBigInt         => setMan(attr, a.vs, resSetBigInt)
      case a: AttrSetManBigDecimal     => setMan(attr, a.vs, resSetBigDecimal)
      case a: AttrSetManDate           => setMan(attr, a.vs, resSetDate)
      case a: AttrSetManDuration       => setMan(attr, a.vs, resSetDuration)
      case a: AttrSetManInstant        => setMan(attr, a.vs, resSetInstant)
      case a: AttrSetManLocalDate      => setMan(attr, a.vs, resSetLocalDate)
      case a: AttrSetManLocalTime      => setMan(attr, a.vs, resSetLocalTime)
      case a: AttrSetManLocalDateTime  => setMan(attr, a.vs, resSetLocalDateTime)
      case a: AttrSetManOffsetTime     => setMan(attr, a.vs, resSetOffsetTime)
      case a: AttrSetManOffsetDateTime => setMan(attr, a.vs, resSetOffsetDateTime)
      case a: AttrSetManZonedDateTime  => setMan(attr, a.vs, resSetZonedDateTime)
      case a: AttrSetManUUID           => setMan(attr, a.vs, resSetUUID)
      case a: AttrSetManURI            => setMan(attr, a.vs, resSetURI)
      case a: AttrSetManByte           => setMan(attr, a.vs, resSetByte)
      case a: AttrSetManShort          => setMan(attr, a.vs, resSetShort)
      case a: AttrSetManChar           => setMan(attr, a.vs, resSetChar)
    }
  }

  override protected def resolveAttrSetTac(attr: AttrSetTac): Unit = {
    attr match {
      case a: AttrSetTacID             => if (a.owner) noEmbeddedIds else tac(attr, a.vs, resSetID)
      case a: AttrSetTacString         => tac(attr, a.vs, resSetString)
      case a: AttrSetTacInt            => tac(attr, a.vs, resSetInt)
      case a: AttrSetTacLong           => tac(attr, a.vs, resSetLong)
      case a: AttrSetTacFloat          => tac(attr, a.vs, resSetFloat)
      case a: AttrSetTacDouble         => tac(attr, a.vs, resSetDouble)
      case a: AttrSetTacBoolean        => tac(attr, a.vs, resSetBoolean)
      case a: AttrSetTacBigInt         => tac(attr, a.vs, resSetBigInt)
      case a: AttrSetTacBigDecimal     => tac(attr, a.vs, resSetBigDecimal)
      case a: AttrSetTacDate           => tac(attr, a.vs, resSetDate)
      case a: AttrSetTacDuration       => tac(attr, a.vs, resSetDuration)
      case a: AttrSetTacInstant        => tac(attr, a.vs, resSetInstant)
      case a: AttrSetTacLocalDate      => tac(attr, a.vs, resSetLocalDate)
      case a: AttrSetTacLocalTime      => tac(attr, a.vs, resSetLocalTime)
      case a: AttrSetTacLocalDateTime  => tac(attr, a.vs, resSetLocalDateTime)
      case a: AttrSetTacOffsetTime     => tac(attr, a.vs, resSetOffsetTime)
      case a: AttrSetTacOffsetDateTime => tac(attr, a.vs, resSetOffsetDateTime)
      case a: AttrSetTacZonedDateTime  => tac(attr, a.vs, resSetZonedDateTime)
      case a: AttrSetTacUUID           => tac(attr, a.vs, resSetUUID)
      case a: AttrSetTacURI            => tac(attr, a.vs, resSetURI)
      case a: AttrSetTacByte           => tac(attr, a.vs, resSetByte)
      case a: AttrSetTacShort          => tac(attr, a.vs, resSetShort)
      case a: AttrSetTacChar           => tac(attr, a.vs, resSetChar)
    }
  }

  override protected def resolveAttrSetOpt(attr: AttrSetOpt): Unit = {
    attr match {
      case _: AttrSetOptID             => if (attr.owner) noEmbeddedIds else setOpt(attr, resSetID)
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


  private def setMan[T](attr: Attr, set: Set[T], resSet: ResSet[T]): Unit = {
    val field       = attr.attr
    val uniqueField = b.unique(field)
    projectField(field)
    addCast(field, resSet.castSet(field))
    addGrouping(field)
    handleExpr(uniqueField, field, attr, set, resSet, true)
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
    set: Set[T],
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
        case Has     => setHas(uniqueField, field, set, resSet, mandatory)
        case HasNo   => setHasNo(uniqueField, field, set, resSet, mandatory)
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
    addGrouping(field)
    attr.op match {
      case V     => setOptAttr(uniqueField, field)
      case Eq    => noCollectionMatching(attr)
      case other => unexpectedOp(other)
    }
  }


  // attr ----------------------------------------------------------------------

  private def setAttr(uniqueField: String, field: String, mandatory: Boolean): Unit = {
    val prefix = if (b.isEmbedded) b.dot else ""
    b.matches.add(Filters.ne(prefix + field, new BsonNull))
    b.matches.add(Filters.ne(prefix + field, new BsonArray()))
//    coalesce(uniqueField, field, mandatory)
  }

  private def setOptAttr(uniqueField: String, field: String): Unit = {
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
////      topBranch.groupExprs += (b.alias + uniqueField) ->
////        new BsonDocument("$addToSet", new BsonString("$" + b.path + field))
////      topBranch.addFields += (b.path + field) -> reduce("$" + b.alias + field)
//    }
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
