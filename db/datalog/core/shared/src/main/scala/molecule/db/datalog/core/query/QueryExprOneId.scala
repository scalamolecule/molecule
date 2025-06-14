package molecule.db.datalog.core.query

import molecule.base.error.ModelError
import molecule.core.dataModel.*
import molecule.db.core.query.QueryExpr


trait QueryExprOneId[Tpl]
  extends SortOneSpecial[Tpl]
    with SortOneOpt_[Tpl]
    with QueryExpr { self: Model2DatomicQuery[Tpl] & LambdasOne =>

  override protected def queryIdMan(attr: AttrOneMan): Unit = {
    attrIndex += 1
    val e = es.last
    attr match {
      case at: AttrOneManID     => man(attr, e, at.vs, resId, sortOneID(at, attrIndex))
      case at: AttrOneManInt    => man(attr, e, at.vs, resInt, intSorter(at, attrIndex))
      case at: AttrOneManLong   => man(attr, e, at.vs, resLong, sortOneLong(at, attrIndex))
      case at: AttrOneManDouble => man(attr, e, at.vs, resDouble, sortOneDouble(at, attrIndex))
      case _                    => unexpectedElement(attr)
    }
  }

  override protected def queryIdTac(attr: AttrOneTac): Unit = {
    val e = es.last
    if (isOptNested) {
      throw ModelError("Tacit id not allowed in optional nested queries.")
    }
    attr match {
      case at: AttrOneTacID     => tac(attr, e, at.vs, resId)
      case at: AttrOneTacInt    => tac(attr, e, at.vs, resInt)
      case at: AttrOneTacLong   => tac(attr, e, at.vs, resLong)
      case at: AttrOneTacDouble => tac(attr, e, at.vs, resDouble)
      case _                    => unexpectedElement(attr)
    }
  }

  private def man[T](
    attr: Attr,
    e: Var,
    args: Seq[T],
    res: ResOne[T],
    sorter: Option[(Int, Int => (Row, Row) => Int)]
  ): Unit = {
    addCast(res.j2s)
    addSort(sorter)
    find += e // get entity id
    val v = getVar(attr)
    expr(e, v, attr.op, args, res)
  }

  private def tac[T](
    attr: Attr,
    e: Var,
    args: Seq[T],
    res: ResOne[T],
  ): Unit = {
    val v = getVar(attr)
    expr(e, v, attr.op, args, res)
  }

  private def expr[T](
    e: Var,
    v: Var,
    op: Op,
    args: Seq[T],
    res: ResOne[T],
  ): Unit = {
    op match {
      case V         => () // no additional where clause needed
      case Eq        => equal(e, args, res.s2j)
      case Neq       => neq(e, args, res.toDatalog)
      case Lt        => compare(e, v, args.head, "<", res.s2j)
      case Gt        => compare(e, v, args.head, ">", res.s2j)
      case Le        => compare(e, v, args.head, "<=", res.s2j)
      case Ge        => compare(e, v, args.head, ">=", res.s2j)
      case NoValue   => where += s"(not [$e _])" -> wNeqOne
      case Fn(kw, n) => aggr(e, kw, n, res)
      case other     => unexpectedOp(other)
    }
  }

  private def aggr[T](e: Var, fn: String, optN: Option[Int], res: ResOne[T]): Unit = {
    checkAggrOne()
    lazy val n = optN.getOrElse(0)
    // Replace find/casting with aggregate function/cast
    find -= e
    fn match {
      case "distinct" =>
        find += s"(distinct $e)"
        replaceCast(res.set2set)

      case "mins" =>
        find += s"(min $n $e)"
        replaceCast(res.vector2set)

      case "min" =>
        find += s"(min $e)"

      case "maxs" =>
        find += s"(max $n $e)"
        replaceCast(res.vector2set)

      case "max" =>
        find += s"(max $e)"

      case "samples" =>
        find += s"(sample $n $e)"
        replaceCast(res.vector2set)

      case "sample" =>
        find += s"(sample 1 $e)"
        replaceCast(res.seq2t)

      case "count" =>
        find += s"(count $e)"
        replaceCast(toInt)

      case "countDistinct" =>
        find += s"(count-distinct $e)"
        replaceCast(toInt)

      case "sum" =>
        find += s"(sum $e)"

      case "median" =>
        find += s"(median $e)"
        replaceCast(any2double)

      // OBS! Datomic rounds down to nearest whole number
      // when calculating the median for multiple numbers instead of
      // following the semantic described on wikipedia:
      // https://en.wikipedia.org/wiki/Median
      // See also
      // https://forum.datomic.com/t/unexpected-median-rounding/517
      // If we wanted the rounded version we can do this instead if desired:
      //        find += s"(distinct $e)"
      //        val medianConverter: AnyRef => Double = {
      ////          (v: AnyRef) => getMedian(v.asInstanceOf[jSet[_]].toArray.map(_.toString.toDouble).toSet)
      //          (v: AnyRef) => {
      //            println(v)
      //            getMedian(v.asInstanceOf[jSet[_]].toArray.map(_.toString.toDouble).toSet)
      //          }
      //        }
      //        replaceCast(medianConverter.asInstanceOf[AnyRef => AnyRef])

      case "avg"      => find += s"(avg $e)"
      case "variance" => find += s"(variance $e)"
      case "stddev"   => find += s"(stddev $e)"


      case other => unexpectedKw(other)
    }
  }

  private def equal[T](e: Var, argValues: Seq[T], fromScala: Any => Any): Unit = {
    in += s"[$e ...]"
    args += argValues.map(fromScala).toArray
  }

  private def neq[T](e: Var, args: Seq[T], toDatalog: T => String): Unit = {
    args.foreach { arg =>
      where += s"[(!= $e ${toDatalog(arg)})]" -> wNeqOne
    }
  }

  private def compare[T](e: Var, v: Var, arg: T, op: String, fromScala: Any => Any): Unit = {
    val v1 = v + 1
    in += v1
    where += s"[($op $e $v1)]" -> wNeqOne
    args += fromScala(arg).asInstanceOf[AnyRef]
  }
}