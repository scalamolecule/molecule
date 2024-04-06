package molecule.document.mongodb.query

import com.mongodb.client.model.Filters
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.query.ResolveExpr
import molecule.document.mongodb.query.mongoModel.{Branch, NestedEmbed, NestedRef}
import org.bson._
import scala.collection.mutable.ArrayBuffer

trait ResolveExprSeq extends ResolveExpr with LambdasSeq { self: MongoQueryBase =>

  override protected def resolveAttrSeqMan(attr: AttrSeqMan): Unit = {
    attr match {
      case a: AttrSeqManID             => seqMan(a, a.vs, resSeqID)
      case a: AttrSeqManString         => seqMan(a, a.vs, resSeqString)
      case a: AttrSeqManInt            => seqMan(a, a.vs, resSeqInt)
      case a: AttrSeqManLong           => seqMan(a, a.vs, resSeqLong)
      case a: AttrSeqManFloat          => seqMan(a, a.vs, resSeqFloat)
      case a: AttrSeqManDouble         => seqMan(a, a.vs, resSeqDouble)
      case a: AttrSeqManBoolean        => seqMan(a, a.vs, resSeqBoolean)
      case a: AttrSeqManBigInt         => seqMan(a, a.vs, resSeqBigInt)
      case a: AttrSeqManBigDecimal     => seqMan(a, a.vs, resSeqBigDecimal)
      case a: AttrSeqManDate           => seqMan(a, a.vs, resSeqDate)
      case a: AttrSeqManDuration       => seqMan(a, a.vs, resSeqDuration)
      case a: AttrSeqManInstant        => seqMan(a, a.vs, resSeqInstant)
      case a: AttrSeqManLocalDate      => seqMan(a, a.vs, resSeqLocalDate)
      case a: AttrSeqManLocalTime      => seqMan(a, a.vs, resSeqLocalTime)
      case a: AttrSeqManLocalDateTime  => seqMan(a, a.vs, resSeqLocalDateTime)
      case a: AttrSeqManOffsetTime     => seqMan(a, a.vs, resSeqOffsetTime)
      case a: AttrSeqManOffsetDateTime => seqMan(a, a.vs, resSeqOffsetDateTime)
      case a: AttrSeqManZonedDateTime  => seqMan(a, a.vs, resSeqZonedDateTime)
      case a: AttrSeqManUUID           => seqMan(a, a.vs, resSeqUUID)
      case a: AttrSeqManURI            => seqMan(a, a.vs, resSeqURI)
      case a: AttrSeqManByte           => manByteArray(a, a.vs) // Byte Array only semantics
      case a: AttrSeqManShort          => seqMan(a, a.vs, resSeqShort)
      case a: AttrSeqManChar           => seqMan(a, a.vs, resSeqChar)
    }
  }

  override protected def resolveAttrSeqTac(attr: AttrSeqTac): Unit = {
    attr match {
      case a: AttrSeqTacID             => seqTac(a, a.vs, resSeqID)
      case a: AttrSeqTacString         => seqTac(a, a.vs, resSeqString)
      case a: AttrSeqTacInt            => seqTac(a, a.vs, resSeqInt)
      case a: AttrSeqTacLong           => seqTac(a, a.vs, resSeqLong)
      case a: AttrSeqTacFloat          => seqTac(a, a.vs, resSeqFloat)
      case a: AttrSeqTacDouble         => seqTac(a, a.vs, resSeqDouble)
      case a: AttrSeqTacBoolean        => seqTac(a, a.vs, resSeqBoolean)
      case a: AttrSeqTacBigInt         => seqTac(a, a.vs, resSeqBigInt)
      case a: AttrSeqTacBigDecimal     => seqTac(a, a.vs, resSeqBigDecimal)
      case a: AttrSeqTacDate           => seqTac(a, a.vs, resSeqDate)
      case a: AttrSeqTacDuration       => seqTac(a, a.vs, resSeqDuration)
      case a: AttrSeqTacInstant        => seqTac(a, a.vs, resSeqInstant)
      case a: AttrSeqTacLocalDate      => seqTac(a, a.vs, resSeqLocalDate)
      case a: AttrSeqTacLocalTime      => seqTac(a, a.vs, resSeqLocalTime)
      case a: AttrSeqTacLocalDateTime  => seqTac(a, a.vs, resSeqLocalDateTime)
      case a: AttrSeqTacOffsetTime     => seqTac(a, a.vs, resSeqOffsetTime)
      case a: AttrSeqTacOffsetDateTime => seqTac(a, a.vs, resSeqOffsetDateTime)
      case a: AttrSeqTacZonedDateTime  => seqTac(a, a.vs, resSeqZonedDateTime)
      case a: AttrSeqTacUUID           => seqTac(a, a.vs, resSeqUUID)
      case a: AttrSeqTacURI            => seqTac(a, a.vs, resSeqURI)
      case a: AttrSeqTacByte           => tacByteArray(a, a.vs) // Byte Array only semantics
      case a: AttrSeqTacShort          => seqTac(a, a.vs, resSeqShort)
      case a: AttrSeqTacChar           => seqTac(a, a.vs, resSeqChar)
    }
  }

  override protected def resolveAttrSeqOpt(attr: AttrSeqOpt): Unit = {
    attr match {
      case a: AttrSeqOptID             => seqOpt(a, resSeqID)
      case a: AttrSeqOptString         => seqOpt(a, resSeqString)
      case a: AttrSeqOptInt            => seqOpt(a, resSeqInt)
      case a: AttrSeqOptLong           => seqOpt(a, resSeqLong)
      case a: AttrSeqOptFloat          => seqOpt(a, resSeqFloat)
      case a: AttrSeqOptDouble         => seqOpt(a, resSeqDouble)
      case a: AttrSeqOptBoolean        => seqOpt(a, resSeqBoolean)
      case a: AttrSeqOptBigInt         => seqOpt(a, resSeqBigInt)
      case a: AttrSeqOptBigDecimal     => seqOpt(a, resSeqBigDecimal)
      case a: AttrSeqOptDate           => seqOpt(a, resSeqDate)
      case a: AttrSeqOptDuration       => seqOpt(a, resSeqDuration)
      case a: AttrSeqOptInstant        => seqOpt(a, resSeqInstant)
      case a: AttrSeqOptLocalDate      => seqOpt(a, resSeqLocalDate)
      case a: AttrSeqOptLocalTime      => seqOpt(a, resSeqLocalTime)
      case a: AttrSeqOptLocalDateTime  => seqOpt(a, resSeqLocalDateTime)
      case a: AttrSeqOptOffsetTime     => seqOpt(a, resSeqOffsetTime)
      case a: AttrSeqOptOffsetDateTime => seqOpt(a, resSeqOffsetDateTime)
      case a: AttrSeqOptZonedDateTime  => seqOpt(a, resSeqZonedDateTime)
      case a: AttrSeqOptUUID           => seqOpt(a, resSeqUUID)
      case a: AttrSeqOptURI            => seqOpt(a, resSeqURI)
      case a: AttrSeqOptByte           => optByteArray(a) // Byte Array only semantics
      case a: AttrSeqOptShort          => seqOpt(a, resSeqShort)
      case a: AttrSeqOptChar           => seqOpt(a, resSeqChar)
    }
  }


  private def seqMan[T](attr: Attr, seq: Seq[T], resSeq: ResSeq[T]): Unit = {
    val field       = attr.attr
    val uniqueField = b.unique(field)
    projectField(field)
    addCast(field, resSeq.castSeq(field))
    prefixedFieldPair = if (b.parent.isEmpty)
      (nestedLevel, field, field)
    else
      (nestedLevel, b.path + field, b.alias + field)
    topBranch.groupIdFields += prefixedFieldPair
    handleExpr(uniqueField, field, attr, seq, resSeq, true)
  }

  private def seqTac[T](attr: Attr, seq: Seq[T], resSeq: ResSeq[T]): Unit = {
    val field       = attr.attr
    val uniqueField = b.unique(field)
    b.base.matches.add(Filters.exists(b.dot + field))
    handleExpr(uniqueField, field, attr, seq, resSeq, false)
  }

  private def handleExpr[T](
    uniqueField: String,
    field: String,
    attr: Attr,
    sets: Seq[T],
    resSeq: ResSeq[T],
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
        case V       => seqAttr(uniqueField, field, mandatory)
        case Eq      => noCollectionMatching(attr)
        case Has     => seqHas(uniqueField, field, sets, resSeq, mandatory)
        case HasNo   => seqHasNo(uniqueField, field, sets, resSeq, mandatory)
        case NoValue => if (mandatory) noApplyNothing(attr) else noValue(field)
        case other   => unexpectedOp(other)
      }
    } { filterAttr =>
      attr.op match {
        case Has   => seqFilterHas(field, filterAttr, mandatory)
        case HasNo => seqFilterHasNo(field, filterAttr, mandatory)
        case other => unexpectedOp(other)
      }
    }
  }

  private def seqOpt[T](attr: Attr, res: ResSeq[T]): Unit = {
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

  private def seqAttr(uniqueField: String, field: String, mandatory: Boolean): Unit = {
    //    println(s"\n======== $uniqueField ============================================")

    // Skipping this for all set attributes solves the own-ref rest in UpdateSet_id.
    // But what distinguishes it?
    b.base.matches.add(Filters.ne(b.dot + field, new BsonNull))

    // Exclude orphaned arrays too
    b.base.matches.add(Filters.ne(b.dot + field, new BsonArray()))
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

  private def seqHas[T](
    uniqueField: String, field: String, seq: Seq[T], res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    seq.size match {
      case 1 => b.base.matches.add(Filters.all(b.dot + field, res.v2bson(seq.head)))
      case 0 => b.base.matches.add(Filters.eq("_id", -1))
      case _ => b.base.matches.add(Filters.or(
        seq.map(v => Filters.all(b.dot + field, res.v2bson(v))).asJava
      ))
    }
  }

  private def seqHasNo[T](
    uniqueField: String, field: String, seq: Seq[T], res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    b.base.matches.add(Filters.ne(b.dot + field, new BsonNull))
    // Exclude orphaned arrays too
    b.base.matches.add(Filters.ne(b.dot + field, new BsonArray()))
    seq.size match {
      case 1 => b.base.matches.add(Filters.ne(b.dot + field, res.v2bson(seq.head)))
      case 0 => b.base.matches.add(Filters.ne("_id", -1))
      case _ => b.base.matches.add(Filters.nor(
        seq.map(v => Filters.all(b.dot + field, res.v2bson(v))).asJava
      ))
    }
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

  private def seqFilterHas(
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
  }

  private def seqFilterHasNo(
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
  }



  // byte array ----------------------------------------------------------------

  private def manByteArray(attr: Attr, byteArray: Array[Byte]): Unit = {
    val field       = attr.attr
    val uniqueField = b.unique(field)
    projectField(uniqueField)

    addCast(uniqueField, (doc: BsonDocument) => {
      // Can't get BsonBinary working - todo
      // doc.getBinary(field).getData

      val raw = doc.get(field)
      if (raw == null) {
        Array.empty[Byte]
      } else {
        val rawArray = raw.asArray
        val array    = new Array[Byte](rawArray.size)
        val it       = rawArray.iterator
        var i        = 0
        while (it.hasNext) {
          array(i) = it.next.asInt32.getValue.toByte
          i += 1
        }
        array
      }
    })

    prefixedFieldPair = if (b.parent.isEmpty)
      (nestedLevel, field, field)
    else
      (nestedLevel, b.path + field, b.alias + field)
    topBranch.groupIdFields += prefixedFieldPair

    // needed?
    //    if (branchesByPath.isEmpty) {
    //      path = List(attr.ns)
    //      pathLevels(path) = 0
    //    }
    //    branchesByPath(path) = b

    attr.filterAttr.fold {
      byteArrayOps(attr, field, byteArray)
    } { _ =>
      throw ModelError(s"Filter attributes not allowed with byte arrays.")
    }
    seqAttr(uniqueField, field, true)
  }

  private def tacByteArray(attr: Attr, byteArray: Array[Byte]): Unit = {
    attr.filterAttr.fold {
      byteArrayOps(attr, attr.attr, byteArray)
    } { _ =>
      throw ModelError(s"Filter attributes not allowed with byte arrays.")
    }
  }

  private def byteArrayOps(attr: Attr, field: String, byteArray: Array[Byte]): Unit = {
    attr.op match {
      case V =>
        val path = if (b.isEmbedded) b.dot else ""
        b.base.matches.add(Filters.ne(path + field, null.asInstanceOf[Array[Byte]]))

      case Eq =>
        //        if (byteArray.length != 0) {
        //          where += ((col, s"= ?"))
        //          val paramIndex = inputs.size + 1
        //          inputs += ((ps: PrepStmt) => ps.setBytes(paramIndex, byteArray))
        //
        //        } else {
        //          // Get none
        //          where += (("FALSE", ""))
        //        }
        ???


      case NoValue =>
        // Get none
        //        where += (("FALSE", ""))
        ???

      case Neq =>
        //        if (byteArray.length != 0) {
        //          where += ((col, s"!= ?"))
        //          val paramIndex = inputs.size + 1
        //          inputs += ((ps: PrepStmt) => ps.setBytes(paramIndex, byteArray))
        //
        //        } else {
        //          // get all
        //        }
        ???

      case _ => throw ModelError(
        s"Byte arrays (${attr.cleanName}) can only be retrieved as-is. Filters not allowed.")
    }
  }

  private def optByteArray(attr: Attr): Unit = {
    attr.op match {
      case V =>
        val field       = attr.attr
        val uniqueField = b.unique(field)
        projectField(uniqueField)

        addCast(uniqueField, (doc: BsonDocument) => {
          // Can't get BsonBinary working - todo
          // doc.getBinary(field).getData

          val buf = new ArrayBuffer[Byte]
          doc.get(field) match {
            case null | BsonNull.VALUE => Option.empty[Array[Byte]]
            case raw                   =>
              val array = raw.asArray.iterator
              while (array.hasNext) {
                buf += array.next.asInt32.getValue.toByte
              }
              Some(buf.toArray)
          }
        })

      case _ => throw ModelError(
        s"Byte arrays (${attr.cleanName}) can only be retrieved as-is. Filters not allowed."
      )
    }
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
}
