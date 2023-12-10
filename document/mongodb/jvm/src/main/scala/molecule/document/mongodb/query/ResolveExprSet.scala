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
      case at: AttrSetManID             => man(attr, at.vs, resSetString)
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
      case at: AttrSetTacID             => tac(attr, at.vs, resSetString)
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
      case at: AttrSetOptID             => opt(at, at.vs, resSetString)
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
//    projections.add(Projections.include(b.pathDot + field))
//    b.projectField(field)
    projectField(field)
    addCast(field, b.embedded, resSet.castSet(field))

    attr.filterAttr.fold {
      if (filterAttrVars.contains(attr.name) && attr.op != V) {
        // Runtime check needed since we can't type infer it
        throw ModelError(s"Cardinality-set filter attributes not allowed to do additional filtering. Found:\n  " + attr)
      }
      expr(uniqueField, field, attr.op, args, resSet)
    } {
      case filterAttr: AttrOne => ??? //setExpr2(field, attr.op, filterAttr.name, true, tpe)
      case filterAttr          => ??? //setExpr2(field, attr.op, filterAttr.name, false, tpe)
    }
  }

  private def tac[T](attr: Attr, args: Seq[Set[T]], res: ResSet[T]): Unit = {
    //    val field = getCol(attr: Attr)
    //    attr.filterAttr.fold {
    //      setExpr(field, attr.op, args, res, "tac")
    //    } {
    //      case filterAttr: AttrOne => setExpr2(field, attr.op, filterAttr.name, true, tpe)
    //      case filterAttr          => setExpr2(field, attr.op, filterAttr.name, false, tpe)
    //    }
    //    notNull += field

    //    val field = getCol(attr: Attr)
    //    attr.filterAttr.fold {
    //      expr(field, attr.op, args, res)
    //    } {
    //      case filterAttr: AttrOne => expr2(field, attr.op, filterAttr.name, true, res.tpe)
    //      case filterAttr          => expr2(field, attr.op, filterAttr.name, false, res.tpe)
    //    }
    //    notNull += field

    val field       = attr.attr
    val uniqueField = b.unique(field)
    b.matches.add(Filters.exists(field))
    attr.filterAttr.fold {
      expr(uniqueField, field, attr.op, args, res)
    } {
      //      expr2(field, attr.op, filterAttr.name)
      case filterAttr: AttrOne => expr2(field, attr.op, filterAttr.name, true, res.tpe)
      case filterAttr          => expr2(field, attr.op, filterAttr.name, false, res.tpe)
    }
  }

  private def opt[T](attr: Attr, optSets: Option[Seq[Set[T]]], res: ResSet[T]): Unit = {
    val field = attr.attr
//    projections.add(Projections.include(field))
//    b.projectField(field)
    projectField(field)
    addCast(field, b.embedded, res.castOptSet(field))
    attr.op match {
      case V     => ()
      case Eq    => optEqual(field, optSets, res)
      case Neq   => optNeq(field, optSets, res)
      case Has   => optHas(field, optSets, res)
      case HasNo => optHasNo(field, optSets, res)
      case other => unexpectedOp(other)
    }
  }

  private def expr[T](uniqueField: String, field: String, op: Op, sets: Seq[Set[T]], res: ResSet[T]): Unit = {
    op match {
      case V         => attr(uniqueField, field, res)
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


  protected def attr[T](uniqueField: String, field: String, res: ResSet[T]): Unit = {
    b.matches.add(Filters.ne(b.pathDot + field, null.asInstanceOf[T]))
    //    groupExprs += ((b.pathUnderscore + uniqueField,
    //      new BsonDocument().append("$addToSet", new BsonString("$" + b.pathDot + field))))
    b.groupExpr(uniqueField, new BsonDocument().append("$addToSet", new BsonString("$" + b.pathDot + field)))

    //    val reduce = new BsonDocument().append("$reduce",
    //      new BsonDocument()
    //        .append("input", new BsonString("$" + b.pathDot + field))
    //        .append("initialValue", new BsonArray())
    //        .append("in", new BsonDocument()
    //          .append("$setUnion", {
    //            val params = new BsonArray()
    //            params.add(new BsonString("$$value"))
    //            params.add(new BsonString("$$this"))
    //            params
    //          })
    //        )
    //    )
    b.addFields(b.refPath) = b.addFields.getOrElse(b.refPath, Nil) :+
      uniqueField -> reduce("$" + b.pathUnderscore + field)
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
      case 0 =>  b.matches.add(Filters.eq("_id", -1))
      case 1 =>  b.matches.add(filter(sets.head))
      case _ =>  b.matches.add(Filters.or(sets.map(filter).asJava))
    }
  }

  private def equal2(field: String, filterAttr: String): Unit = {
    //    where += ((field, "= " + filterAttr))
  }

  private def optEqual[T](field: String, optSets: Option[Seq[Set[T]]], res: ResSet[T]): Unit = {
    optSets.fold[Unit] {
      //      where += ((field, s"IS NULL"))
       b.matches.add(Filters.eq(field, null.asInstanceOf[T]))
    } { sets =>
      equal(field, sets, res)
    }
  }

  private def neq[T](field: String, sets0: Seq[Set[T]], res: ResSet[T]): Unit = {
    def filter(set: Set[T]): Bson = filterSet(field, set, res)
    val sets = sets0.filterNot(_.isEmpty)
    sets.length match {
      case 0 => ()
      case 1 =>  b.matches.add(Filters.nor(filter(sets.head)))
      case _ =>  b.matches.add(Filters.nor(Filters.or(sets.map(filter).asJava)))
    }
  }

  private def neq2(field: String, filterAttr: String): Unit = {
    //    where += ((field, "<> " + filterAttr))
  }

  private def optNeq[T](field: String, optSets: Option[Seq[Set[T]]], res: ResSet[T]): Unit = {
    if (optSets.isDefined && optSets.get.nonEmpty) {
      neq(field, optSets.get, res)
    }
     b.matches.add(Filters.ne(field, null.asInstanceOf[T]))
  }

  private def has[T](field: String, sets0: Seq[Set[T]], res: ResSet[T]): Unit = {
    def containsSet(set: Set[T]): Bson = Filters.all(field, set.map(v => res.v2bson(v)).asJava)
    val sets = sets0.filterNot(_.isEmpty)
    sets.length match {
      case 0 =>  b.matches.add(Filters.eq("_id", -1))
      case 1 =>
        val set = sets.head
        if (set.nonEmpty)
           b.matches.add(containsSet(set))
        else
           b.matches.add(Filters.eq("_id", -1))

      case _ =>  b.matches.add(Filters.or(sets.map(set => containsSet(set)).asJava))
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
       b.matches.add(Filters.eq(field, null.asInstanceOf[T]))
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
           b.matches.add(notContainsSet(set))

      case _ =>  b.matches.add(Filters.and(sets.map(set => notContainsSet(set)).asJava))
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
     b.matches.add(Filters.ne(field, null.asInstanceOf[T]))
  }

  private def noValue(field: String): Unit = {
    //    notNull -= field
    //    where += ((field, s"IS NULL"))
  }


  private def reduce(field: String): BsonDocument = {
    new BsonDocument().append("$reduce",
      new BsonDocument()
        //        .append("input", new BsonString("$" + b.pathDot + field))
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
    lazy val n        = optN.getOrElse(0)
    lazy val dotField = "$" + b.pathDot + field
    lazy val usField  = "$" + b.pathUnderscore + field


    fn match {
      case "distinct" =>
        noBooleanSetAggr(res)
         b.matches.add(Filters.ne(b.pathDot + field, null.asInstanceOf[T]))
        b.groupSets(uniqueField, dotField)
        if (b.refPath.nonEmpty) {
          b.addField(uniqueField, new BsonString(usField))
        }
        replaceCast(uniqueField, b.embedded, res.castSetSet(uniqueField))

      case "min" =>
        noBooleanSetAggr(res)
        b.groupSets(uniqueField, dotField)
        b.addField(uniqueField, aggrFn("$minN", reduce(usField), 1))

      case "mins" =>
        noBooleanSetAggr(res)
        b.groupSets(uniqueField, dotField)
        b.addField(uniqueField, aggrFn("$minN", reduce(usField), n))

      case "max" =>
        noBooleanSetAggr(res)
        b.groupSets(uniqueField, dotField)
        b.addField(uniqueField, aggrFn("$maxN", reduce(usField), 1))

      case "maxs" =>
        noBooleanSetAggr(res)
        b.groupSets(uniqueField, dotField)
        b.addField(uniqueField, aggrFn("$maxN", reduce(usField), n))

      case "sample" =>
        noBooleanSetAggr(res)
        sampleSize = 1

      case "samples" =>
        noBooleanSetAggr(res)
        sampleSize = n

      case "count" =>
        noBooleanSetCounts(n)
        b.groupExpr(uniqueField,
          new BsonDocument().append("$sum",
            new BsonDocument().append("$size", new BsonString(dotField)))
        )
        if (b.refPath.nonEmpty)
          b.addField(uniqueField, new BsonString(usField))
        replaceCast(uniqueField, b.embedded, castInt(uniqueField))

      case "countDistinct" =>
        noBooleanSetCounts(n)
        b.groupSets(uniqueField, dotField)
        b.addField(uniqueField, new BsonDocument().append("$size", reduce(usField)))
        replaceCast(uniqueField, b.embedded, castInt(uniqueField))

      case "sum" =>
        b.groupSets(uniqueField, dotField)
        b.addField(uniqueField, new BsonDocument().append("$sum", reduce(usField)))
        replaceCast(uniqueField, b.embedded, res.v2set(uniqueField))

      case "median" =>
        b.groupSets(uniqueField, dotField)
        b.addField(uniqueField, new BsonDocument().append("$median",
          new BsonDocument()
            .append("input", reduce(usField))
            .append("method", new BsonString("approximate"))
        ))
        replaceCast(uniqueField, b.embedded, hardCastDouble(uniqueField))

      case "avg" =>
        b.groupSets(uniqueField, dotField)
        b.addField(uniqueField, new BsonDocument().append("$avg", reduce(usField)))
        replaceCast(uniqueField, b.embedded, hardCastDouble(uniqueField))

      case "variance" =>
        b.groupSets(uniqueField, dotField)
        b.addField(uniqueField, new BsonDocument().append("$stdDevPop", reduce(usField)))
//        b.removeField(b.pathDot + field)
        removeField(b.pathDot + field)
        val pow = new BsonArray()
        pow.add(new BsonString(dotField))
        pow.add(new BsonInt32(2))
//        projections.add(new BsonDocument().append(b.pathDot + field, new BsonDocument().append("$pow", pow)))
//
//        projections2(refPath) = projections2(refPath).append(field, new BsonInt32(1))
//        projectField(field)

        replaceCast(uniqueField, b.embedded, hardCastDouble(uniqueField))

      case "stddev" =>
        b.groupSets(uniqueField, dotField)
        b.addField(uniqueField, new BsonDocument().append("$stdDevPop", reduce(usField)))
        replaceCast(uniqueField, b.embedded, hardCastDouble(uniqueField))

      case other => unexpectedKw(other)
    }
  }
}
