package molecule.document.mongodb.query

import com.mongodb.client.model.{Filters, Sorts}
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.query.ResolveExpr
import molecule.document.mongodb.query.mongoModel.Branch
import org.bson._
import org.bson.conversions.Bson

trait ResolveExprOne extends ResolveExpr with LambdasOne with LambdasSet { self: MongoQueryBase =>

  override protected def resolveAttrOneMan(attr: AttrOneMan): Unit = {
    attr match {
      case at: AttrOneManID             => man(attr, at.vs, resID)
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
      val (dir, index) = (sort.head, sort.substring(1, 2).toInt)
      dir match {
        case 'a' => topBranch.sorts += index -> Sorts.ascending(b.path + field)
        case 'd' => topBranch.sorts += index -> Sorts.descending(b.path + field)
      }
    }

  }

  private def man[T](attr: Attr, args: Seq[T], res: ResOne[T]): Unit = {
    val field       = attr.attr
    val uniqueField = b.unique(field)
    projectField(uniqueField)
    addSort(attr, uniqueField)
    addCast(uniqueField, res.cast(uniqueField))
    prefixedFieldPair = if (b.parent.isEmpty)
      (nestedLevel, field, field)
    else
      (nestedLevel, b.path + field, b.alias + field)
    topBranch.groupIdFields += prefixedFieldPair
    handleExpr(uniqueField, field, attr, args, res)
  }

  private def tac[T](attr: Attr, args: Seq[T], res: ResOne[T]): Unit = {
    val field       = attr.attr
    val uniqueField = b.unique(field)
    handleExpr(uniqueField, field, attr, args, res)
  }

  private def handleExpr[T](uniqueField: String, field: String, attr: Attr, args: Seq[T], res: ResOne[T]): Unit = {
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
      expr(uniqueField, field, attr.op, args, res)
    } { filterAttr =>
      expr2(field, attr.op, filterAttr)
    }
  }

  private def opt[T](attr: Attr, optArgs: Option[Seq[T]], res: ResOne[T]): Unit = {
    val field       = attr.attr
    val uniqueField = b.unique(field)
    projectField(uniqueField)
    addCast(uniqueField, res.castOpt(uniqueField))
    addSort(attr, uniqueField)

    attr.op match {
      case V     => () // selected field can already be a value or null
      case Eq    => optEqual(field, optArgs, res)
      case Neq   => optNeq(field, optArgs, res)
      case Lt    => optCompare(field, optArgs, res.lt)
      case Gt    => optCompare(field, optArgs, res.gt)
      case Le    => optCompare(field, optArgs, res.le)
      case Ge    => optCompare(field, optArgs, res.ge)
      case other => unexpectedOp(other)
    }
  }

  private def expr[T](uniqueField: String, field: String, op: Op, args: Seq[T], res: ResOne[T]): Unit = {
    val pathField = b.dot + field
    op match {
      case V          => attr(field)
      case Eq         => equal(pathField, args, res)
      case Neq        => neq(pathField, args, res)
      case Lt         => compare(res.lt(pathField, args.head))
      case Gt         => compare(res.gt(pathField, args.head))
      case Le         => compare(res.le(pathField, args.head))
      case Ge         => compare(res.ge(pathField, args.head))
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
  private def expr2(field: String, op: Op, filterAttr: (Int, List[String], Attr)): Unit = op match {
    case Eq    => equal2(field, filterAttr)
    case Neq   => neq2(field, filterAttr)
    case Lt    => compare2(field, "$lt", filterAttr)
    case Gt    => compare2(field, "$gt", filterAttr)
    case Le    => compare2(field, "$lte", filterAttr)
    case Ge    => compare2(field, "$gte", filterAttr)
    case other => unexpectedOp(other)
  }



  protected def attr[T](field: String): Unit = {
    val path = if (b.base.isEmbedded) b.dot else ""
    b.base.matches.add(Filters.ne(path + field, null.asInstanceOf[T]))
  }

  private def equal[T](pathField: String, args: Seq[T], res: ResOne[T]): Unit = {
    b.base.matches.add(equalValue(pathField, args, res))
  }
  private def equalValue[T](field: String, args: Seq[T], res: ResOne[T]): Bson = {
    args.length match {
      case 0 => Filters.eq("_id", -1)
      case 1 => res.eq(field, args.head)
      case _ => Filters.or(args.map(arg => res.eq(field, arg)).asJava)
    }
  }


  private def neq[T](pathField: String, args: Seq[T], res: ResOne[T]): Unit = {
    b.base.matches.add(Filters.ne(pathField, null.asInstanceOf[T]))
    args.map(arg => b.base.matches.add(res.neq(pathField, arg)))
  }


  private def compare(filter: Bson): Unit = {
    b.base.matches.add(filter)
  }


  private def noValue(field: String): Unit = {
    b.base.matches.add(Filters.eq(field, new BsonNull()))
  }

  private def optEqual[T](field: String, optArgs: Option[Seq[T]], res: ResOne[T]): Unit = {
    b.base.matches.add(
      optArgs.fold {
        Filters.eq(field, new BsonNull())
      } {
        case Nil => Filters.eq("_id", -1)
        case vs  => equalValue(field, vs, res)
      }
    )
  }

  private def optNeq[T](field: String, optArgs: Option[Seq[T]], res: ResOne[T]): Unit = {
    b.base.matches.add(Filters.ne(field, null.asInstanceOf[T]))
    optArgs.map(_.map(arg => b.base.matches.add(res.neq(field, arg))))
  }

  private def optCompare[T](field: String, optArgs: Option[Seq[T]], filter: (String, T) => Bson): Unit = {
    b.base.matches.add(
      optArgs.fold {
        // Always return empty result when trying to compare None
        Filters.eq("_id", -1)
      } { vs =>
        filter(field, vs.head)
      }
    )
  }

  private def regex(field: String, pattern: String): Unit = {
    b.base.matches.add(Filters.regex(field, pattern))
  }
  private def startsWith[T](pathField: String, arg: T): Unit = {
    regex(pathField, s"^$arg.*")
  }
  private def endsWith[T](pathField: String, arg: T): Unit = {
    regex(pathField, s".*$arg$$")
  }
  private def contains[T](pathField: String, arg: T): Unit = {
    regex(pathField, s".*$arg.*")
  }
  private def matches(pathField: String, regex: String): Unit = {
    b.base.matches.add(
      if (regex.nonEmpty) {
        Filters.regex(pathField, regex)
      } else {
        // No filtering
        Filters.empty()
      }
    )
  }

  private def remainder[T](field: String, args: Seq[T]): Unit = {
    val Seq(divisor, remainder) = args.map(_.toString.toInt)
    b.base.matches.add(Filters.mod(field, divisor, remainder))
  }

  private def even(pathField: String): Unit = {
    b.base.matches.add(Filters.mod(pathField, 2, 0))
  }

  private def odd(pathField: String): Unit = {
    b.base.matches.add(Filters.mod(pathField, 2, 1))
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
        topBranch.groupExprs += aliasField -> new BsonDocument("$min", new BsonString(pathField2))
        addField(uniqueField)

      case "max" =>
        topBranch.groupExprs += aliasField -> new BsonDocument("$max", new BsonString(pathField2))
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
        topBranch.groupExprs += aliasField -> new BsonDocument("$sum", new BsonInt32(1))
        addField(uniqueField)
        replaceCast(uniqueField, castInt(uniqueField))

      case "countDistinct" =>
        topBranch.preGroupFields += pathField -> aliasField
        topBranch.groupExprs += aliasField -> new BsonDocument("$sum", new BsonInt32(1))
        addField(uniqueField)
        replaceCast(uniqueField, castInt(uniqueField))

      case "sum" =>
        topBranch.preGroupFields += pathField -> aliasField
        topBranch.groupExprs += aliasField -> new BsonDocument("$sum", new BsonString(idField))
        addField(uniqueField)

      case "median" =>
        topBranch.preGroupFields += pathField -> aliasField
        topBranch.groupExprs += aliasField -> new BsonDocument("$median",
          new BsonDocument()
            .append("input", new BsonString(idField))
            .append("method", new BsonString("approximate"))
        )
        addField(uniqueField)
        replaceCast(uniqueField, hardCastDouble(uniqueField))

      case "avg" =>
        topBranch.preGroupFields += pathField -> aliasField
        topBranch.groupExprs += aliasField -> new BsonDocument("$avg", new BsonString(idField))
        addField(uniqueField)
        replaceCast(uniqueField, hardCastDouble(uniqueField))

      case "variance" =>
        topBranch.preGroupFields += pathField -> aliasField
        topBranch.groupExprs += aliasField -> new BsonDocument("$stdDevPop", new BsonString(idField))
        b.projection.remove(b.dot + uniqueField)
        val pow = new BsonArray()
        pow.add(new BsonString("$" + aliasField))
        pow.add(new BsonInt32(2))
        b.projection.append(b.dot + field, new BsonDocument("$pow", pow))
        replaceCast(uniqueField, hardCastDouble(uniqueField))

      case "stddev" =>
        topBranch.preGroupFields += pathField -> aliasField
        topBranch.groupExprs += aliasField -> new BsonDocument("$stdDevPop", new BsonString(idField))
        addField(uniqueField)
        replaceCast(uniqueField, hardCastDouble(uniqueField))

      case other => unexpectedKw(other)
    }
  }

  private def equal2(field: String, filterAttr: (Int, List[String], Attr)): Unit = {
    handleFilterExpr(field, "$eq", filterAttr)
  }
  private def neq2(field: String, filterAttr: (Int, List[String], Attr)): Unit = {
    handleFilterExpr(field, "$ne", filterAttr)
  }
  private def compare2(field: String, op: String, filterAttr: (Int, List[String], Attr)): Unit = {
    handleFilterExpr(field, op, filterAttr)
  }

  private def handleFilterExpr(field: String, op: String, filterAttr0: (Int, List[String], Attr)): Unit = {
    val (dir, filterPath, filterAttr1) = filterAttr0
    val filterAttr                         = filterAttr1.cleanAttr
    dir match {
      case 0 =>
        val b1 = b
        val b2 = branchesByPath(filterPath)

        val args = new BsonArray()
        args.add(new BsonString("$" + b1.path + field))
        args.add(new BsonString("$" + b2.path + filterAttr))

        if (isNested && b1.level != b2.level) {
          addStagesForNested(b2, b1, args)
        } else {
          topBranch.postMatches.add(Filters.eq("$expr", new BsonDocument(op, args)))
        }
      case -1 =>
        val b1 = b
        val b2 = branchesByPath(filterPath)

        val args = new BsonArray()
        args.add(new BsonString("$" + b1.path + field))
        args.add(new BsonString("$" + b2.path + filterAttr))

        if (isNested && b1.level != b2.level) {
          addStagesForNested(b2, b1, args)
        } else {
          topBranch.postMatches.add(Filters.eq("$expr", new BsonDocument(op, args)))
        }
      case 1 =>
        val b1        = b
        val addFilter = (b2: Branch) => {
          val args = new BsonArray()
          args.add(new BsonString("$" + b1.path + field))
          args.add(new BsonString("$" + b2.path + filterAttr))

          if (isNested && b1.level != b2.level) {
            addStagesForNested(b1, b2, args)
          } else {
            topBranch.postMatches.add(Filters.eq("$expr", new BsonDocument(op, args)))
          }
        }
        postFilters(filterPath :+ filterAttr) = addFilter
    }

    def addStagesForNested(parentBranch: Branch, nestedBranch: Branch, args: BsonArray): Boolean = {
      val parentFields = parentBranch.groupIdFields.filter(_._1 == parentBranch.level)
      val refAttr      = nestedBaseBranches(nestedBranch.level)._1
      val refAttrBson  = new BsonString("$" + refAttr)

      // unwind
      parentBranch.postStages.add(new BsonDocument("$unwind", refAttrBson))

      // match
      parentBranch.postStages.add(new BsonDocument("$match",
        new BsonDocument("$expr", new BsonDocument(op, args))))

      // group
      val groupIdFieldsDoc = new BsonDocument("_id", new BsonString("$_id"))
      parentFields.foreach { case (_, fieldPath, _) =>
        groupIdFieldsDoc.put(fieldPath, new BsonString("$" + fieldPath))
      }
      val groupDoc = new BsonDocument()
      groupDoc.append("_id", groupIdFieldsDoc)
      groupDoc.append(refAttr, new BsonDocument("$push", refAttrBson))
      parentBranch.postStages.add(new BsonDocument("$group", groupDoc))

      // addFields
      val addFieldsDoc = new BsonDocument()
      parentFields.foreach { case (_, fieldPath, fieldAlias) =>
        addFieldsDoc.put(fieldPath, new BsonString("$_id." + fieldAlias))
      }
      parentBranch.postStages.add(new BsonDocument("$addFields", addFieldsDoc))
    }
  }
}
