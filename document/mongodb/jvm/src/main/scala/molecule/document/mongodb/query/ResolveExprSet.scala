package molecule.document.mongodb.query

import com.mongodb.client.model.{Filters, Projections}
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.query.ResolveExpr
import org.bson.BsonArray
import org.bson.conversions.Bson
import scala.reflect.ClassTag

trait ResolveExprSet extends ResolveExpr { self: MongoQueryBase with LambdasSet =>

  override protected def resolveAttrSetMan(attr: AttrSetMan): Unit = {
//    aritiesAttr()
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
//    aritiesAttr()
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
    //    val field = getCol(attr: Attr)
    //    select += field
    //    if (!isNestedOpt) {
    //      notNull += field
    //    }
    //    addCast(res.cast)
    //    attr.filterAttr.fold {
    //      if (filterAttrVars.contains(attr.name) && attr.op != V) {
    //        // Runtime check needed since we can't type infer it
    //        throw ModelError(s"Cardinality-set filter attributes not allowed to do additional filtering. Found:\n  " + attr)
    //      }
    //      setExpr(field, attr.op, args, res, "man")
    //    } {
    //      case filterAttr: AttrOne => setExpr2(field, attr.op, filterAttr.name, true, tpe)
    //      case filterAttr          => setExpr2(field, attr.op, filterAttr.name, false, tpe)
    //    }

    val field = attr.attr
    projections.add(Projections.include(field))
    addCast(field, resSet.castSet(field))

    attr.filterAttr.fold {
      if (filterAttrVars.contains(attr.name) && attr.op != V) {
        // Runtime check needed since we can't type infer it
        throw ModelError(s"Cardinality-set filter attributes not allowed to do additional filtering. Found:\n  " + attr)
      }
      expr(field, attr.op, args, resSet)
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

    val field = attr.attr
    matches.add(Filters.exists(field))
    attr.filterAttr.fold {
      expr(field, attr.op, args, res)
    } {
      //      expr2(field, attr.op, filterAttr.name)
      case filterAttr: AttrOne => expr2(field, attr.op, filterAttr.name, true, res.tpe)
      case filterAttr          => expr2(field, attr.op, filterAttr.name, false, res.tpe)
    }
  }

  private def opt[T](
    attr: Attr,
    optSets: Option[Seq[Set[T]]],
    res: ResSet[T],
  ): Unit = {
    val field = attr.attr
    projections.add(Projections.include(field))
    addCast(field, res.castOptSet(field))
    attr.op match {
      case V     => ()
      case Eq    => optEqual(field, optSets, res)
      case Neq   => optNeq(field, optSets, res)
      case Has   => optHas(field, optSets, res)
      case HasNo => optHasNo(field, optSets, res)
      case other => unexpectedOp(other)
    }
  }

  private def expr[T](field: String, op: Op, sets: Seq[Set[T]], res: ResSet[T]): Unit = {
    op match {
      case V         => matches.add(Filters.ne(field, null.asInstanceOf[T]))
      case Eq        => equal(field, sets, res)
      case Neq       => neq(field, sets, res)
      case Has       => has(field, sets, res)
      case HasNo     => hasNo(field, sets, res)
      case NoValue   => noValue(field)
      case Fn(kw, n) => aggr(field, kw, n, res)
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

  private def aggr[T](field: String, fn: String, optN: Option[Int], res: ResSet[T]): Unit = {
//    select -= field
    lazy val n = optN.getOrElse(0)
    //      fn match {
    //        case "distinct" =>
    //          noBooleanSetAggr(res)
    //          select += s"ARRAY_AGG(DISTINCT $field)"
    //          groupByCols -= field
    //          aggregate = true
    //          replaceCast(res.nestedArray2nestedSet)
    //  
    //        case "min" =>
    //          noBooleanSetAggr(res)
    //          select += s"MIN($field)"
    //          groupByCols -= field
    //          aggregate = true
    //          replaceCast(res.array2setFirst)
    //  
    //        case "mins" =>
    //          noBooleanSetAggr(res)
    //          select += s"ARRAY_AGG(DISTINCT $field)"
    //          groupByCols -= field
    //          aggregate = true
    //          replaceCast(res.nestedArray2setAsc(n))
    //  
    //        case "max" =>
    //          noBooleanSetAggr(res)
    //          select += s"MAX($field)"
    //          groupByCols -= field
    //          aggregate = true
    //          replaceCast(res.array2setLast)
    //  
    //        case "maxs" =>
    //          noBooleanSetAggr(res)
    //          select += s"ARRAY_AGG(DISTINCT $field)"
    //          groupByCols -= field
    //          aggregate = true
    //          replaceCast(res.nestedArray2setDesc(n))
    //  
    //        case "rand" =>
    //          noBooleanSetAggr(res)
    //          distinct = false
    //          select += field
    //          orderBy += ((level, -1, "RAND()", ""))
    //          hardLimit = 1
    //          replaceCast(res.nestedArray2coalescedSet)
    //  
    //        case "rands" =>
    //          noBooleanSetAggr(res)
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
    //          replaceCast(res.nestedArray2coalescedSet)
    //  
    //        case "sample" =>
    //          noBooleanSetAggr(res)
    //          distinct = false
    //          select += field
    //          orderBy += ((level, -1, "RAND()", ""))
    //          hardLimit = 1
    //          replaceCast(res.nestedArray2coalescedSet)
    //  
    //        case "samples" =>
    //          noBooleanSetAggr(res)
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
    //          replaceCast(res.nestedArray2coalescedSet)
    //  
    //  
    //        // Using brute force in the following aggregate functions to be able to
    //        // aggregate _unique_ values (Set semantics instead of Array semantics)
    //  
    //        case "count" =>
    //          noBooleanSetCounts(n)
    //          // Count of all (non-unique) values
    //          select += s"ARRAY_AGG($field)"
    //          groupByCols -= field
    //          aggregate = true
    //          replaceCast(
    //            (row: Row, paramIndex: Int) => {
    //              //            val outerArrayResultSet = row.getArray(paramIndex).getResultSet
    //              //            var count               = 0
    //              //            while (outerArrayResultSet.next()) {
    //              //              count += outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]].length
    //              //            }
    //              //            count
    //              ???
    //            }
    //          )
    //  
    //        case "countDistinct" =>
    //          noBooleanSetCounts(n)
    //          // Count of unique values (Set semantics)
    //          select += s"ARRAY_AGG($field)"
    //          groupByCols -= field
    //          aggregate = true
    //          replaceCast(
    //            (row: Row, paramIndex: Int) => {
    //              //            val outerArrayResultSet = row.getArray(paramIndex).getResultSet
    //              //            var set                 = Set.empty[Any]
    //              //            while (outerArrayResultSet.next()) {
    //              //              outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]].foreach { value =>
    //              //                set += value
    //              //              }
    //              //            }
    //              //            set.size
    //              ???
    //            }
    //          )
    //  
    //        case "sum" =>
    //          // Sum of unique values (Set semantics)
    //          select += s"ARRAY_AGG($field)"
    //          groupByCols -= field
    //          aggregate = true
    //          replaceCast(res.array2setSum)
    //  
    //        case "median" =>
    //          // Using brute force and fieldlecting all unique values to calculate the median value
    //          // Median of unique values (Set semantics)
    //          select += s"ARRAY_AGG($field)"
    //          groupByCols -= field
    //          aggregate = true
    //          replaceCast(
    //            (row: Row, paramIndex: Int) => {
    //              //            val outerArrayResultSet = row.getArray(paramIndex).getResultSet
    //              //            var set                 = Set.empty[Double]
    //              //            while (outerArrayResultSet.next()) {
    //              //              val array = outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]]
    //              //              array.foreach(v => set += v.toString.toDouble) // not the most efficient...
    //              //            }
    //              //            getMedian(set)
    //              ???
    //            }
    //          )
    //  
    //        case "avg" =>
    //          // Average of unique values (Set semantics)
    //          select += s"ARRAY_AGG($field)"
    //          groupByCols -= field
    //          aggregate = true
    //          replaceCast(
    //            (row: Row, paramIndex: Int) => {
    //              //            val outerArrayResultSet = row.getArray(paramIndex).getResultSet
    //              //            var set                 = Set.empty[Double]
    //              //            while (outerArrayResultSet.next()) {
    //              //              val array = outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]]
    //              //              array.foreach(v => set += v.toString.toDouble) // not the most efficient...
    //              //            }
    //              //            set.sum / set.size
    //              ???
    //            }
    //          )
    //  
    //        case "variance" =>
    //          // Variance of unique values (Set semantics)
    //          select += s"ARRAY_AGG($field)"
    //          groupByCols -= field
    //          aggregate = true
    //          replaceCast(
    //            (row: Row, paramIndex: Int) => {
    //              //            val outerArrayResultSet = row.getArray(paramIndex).getResultSet
    //              //            var set                 = Set.empty[Double]
    //              //            while (outerArrayResultSet.next()) {
    //              //              val array = outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]]
    //              //              array.foreach(v => set += v.toString.toDouble) // not the most efficient...
    //              //            }
    //              //            varianceOf(set.toList: _*)
    //              ???
    //            }
    //          )
    //  
    //        case "stddev" =>
    //          // Standard deviation of unique values (Set semantics)
    //          select += s"ARRAY_AGG($field)"
    //          groupByCols -= field
    //          aggregate = true
    //          replaceCast(
    //            (row: Row, paramIndex: Int) => {
    //              //            val outerArrayResultSet = row.getArray(paramIndex).getResultSet
    //              //            var set                 = Set.empty[Double]
    //              //            while (outerArrayResultSet.next()) {
    //              //              val array = outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]]
    //              //              array.foreach(v => set += v.toString.toDouble) // not the most efficient...
    //              //            }
    //              //            stdDevOf(set.toList: _*)
    //              ???
    //            }
    //          )
    //  
    //        case other => unexpectedKw(other)
    //      }
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
      case 0 => matches.add(Filters.eq("_id", -1))
      case 1 => matches.add(filter(sets.head))
      case _ => matches.add(Filters.or(sets.map(filter).asJava))
    }
  }

  private def equal2(field: String, filterAttr: String): Unit = {
//    where += ((field, "= " + filterAttr))
  }

  private def optEqual[T](field: String, optSets: Option[Seq[Set[T]]], res: ResSet[T]): Unit = {
    optSets.fold[Unit] {
      //      where += ((field, s"IS NULL"))
      matches.add(Filters.eq(field, null.asInstanceOf[T]))
    } { sets =>
      equal(field, sets, res)
    }
  }

  private def neq[T](field: String, sets0: Seq[Set[T]], res: ResSet[T]): Unit = {
    def filter(set: Set[T]): Bson = filterSet(field, set, res)
    val sets = sets0.filterNot(_.isEmpty)
    sets.length match {
      case 0 => ()
      case 1 => matches.add(Filters.nor(filter(sets.head)))
      case _ => matches.add(Filters.nor(Filters.or(sets.map(filter).asJava)))
    }
  }

  private def neq2(field: String, filterAttr: String): Unit = {
//    where += ((field, "<> " + filterAttr))
  }

  private def optNeq[T](field: String, optSets: Option[Seq[Set[T]]], res: ResSet[T]): Unit = {
    if (optSets.isDefined && optSets.get.nonEmpty) {
      neq(field, optSets.get, res)
    }
    matches.add(Filters.ne(field, null.asInstanceOf[T]))
  }

  private def has[T](field: String, sets0: Seq[Set[T]], res: ResSet[T]): Unit = {
    def containsSet(set: Set[T]): Bson = Filters.all(field, set.map(v => res.v2bson(v)).asJava)
    val sets = sets0.filterNot(_.isEmpty)
    sets.length match {
      case 0 => matches.add(Filters.eq("_id", -1))
      case 1 =>
        val set = sets.head
        if (set.nonEmpty)
          matches.add(containsSet(set))
        else
          matches.add(Filters.eq("_id", -1))

      case _ => matches.add(Filters.or(sets.map(set => containsSet(set)).asJava))
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
      matches.add(Filters.eq(field, null.asInstanceOf[T]))
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
          matches.add(notContainsSet(set))

      case _ => matches.add(Filters.and(sets.map(set => notContainsSet(set)).asJava))
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
    matches.add(Filters.ne(field, null.asInstanceOf[T]))
  }

  private def noValue(field: String): Unit = {
//    notNull -= field
//    where += ((field, s"IS NULL"))
  }
}
