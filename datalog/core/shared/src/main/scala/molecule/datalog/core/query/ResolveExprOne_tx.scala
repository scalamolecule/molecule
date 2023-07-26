package molecule.datalog.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import scala.reflect.ClassTag

trait ResolveExprOne_tx[Tpl]
  extends SortOneSpecial[Tpl]
    with SortOneOpt_[Tpl] { self: DatomicModel2Query[Tpl] with LambdasOne =>

  protected def resolveTxMan(es: List[Var], attr: AttrOneMan): List[Var] = {
    aritiesAttr()
    attrIndex += 1
    val tx = txVar
    attr match {
      case at: AttrOneManInt    => man(attr, tx, at.vs, resInt, intSorter(at, attrIndex))
      case at: AttrOneManLong   => man(attr, tx, at.vs, resLong, sortOneLong(at, attrIndex))
      case at: AttrOneManDouble => man(attr, tx, at.vs, resDouble, sortOneDouble(at, attrIndex))
      case _                    => unexpectedElement(attr)
    }
    es
  }

  protected def resolveTxTac(es: List[Var], attr: AttrOneTac): List[Var] = {
    val tx = txVar
    if (isNestedOpt && !isTxMetaData) {
      throw ModelError("Tacit id not allowed in optional nested queries.")
    }
    attr match {
      case at: AttrOneTacInt    => tac(attr, tx, at.vs, resInt)
      case at: AttrOneTacLong   => tac(attr, tx, at.vs, resLong)
      case at: AttrOneTacDouble => tac(attr, tx, at.vs, resDouble)
      case _                    => unexpectedElement(attr)
    }
    es
  }

  private def addSort(sorter: Option[(Int, Int => (Row, Row) => Int)]): Unit = {
    sorter.foreach {
      case s if isTxMetaData => sortss = (sortss.head :+ s) +: sortss.tail
      case s                 => sortss = sortss.init :+ (sortss.last :+ s)
    }
  }

  private def man[T: ClassTag](
    attr: Attr,
    tx: Var,
    args: Seq[T],
    res: ResOne[T],
    sorter: Option[(Int, Int => (Row, Row) => Int)]
  ): Unit = {
    addCast(res.j2s)
    addSort(sorter)
    find += tx // get transaction entity id
    val v = getVar(attr)
    expr(tx, v, attr.op, args, res)
  }

  private def tac[T: ClassTag](
    attr: Attr,
    tx: Var,
    args: Seq[T],
    res: ResOne[T],
  ): Unit = {
    val v = getVar(attr)
    expr(tx, v, attr.op, args, res)
  }

  private def expr[T: ClassTag](
    tx: Var,
    v: Var,
    op: Op,
    args: Seq[T],
    res: ResOne[T],
  ): Unit = {
    op match {
      case V         => () // no additional where clause needed
      case Eq        => equal(tx, args, res.s2j)
      case Neq       => neq(tx, args, res.toDatalog)
      case Lt        => compare(tx, v, args.head, "<", res.s2j)
      case Gt        => compare(tx, v, args.head, ">", res.s2j)
      case Le        => compare(tx, v, args.head, "<=", res.s2j)
      case Ge        => compare(tx, v, args.head, ">=", res.s2j)
      case NoValue   => noValue()
      case Fn(kw, n) => aggr(tx, kw, n, res)
      case other     => unexpectedOp(other)
    }
  }

  private def aggr[T](tx: Var, fn: String, optN: Option[Int], res: ResOne[T]): Unit = {
    lazy val n = optN.getOrElse(0)
    // Replace find/casting with aggregate function/cast
    find -= tx
    fn match {
      case "distinct" =>
        find += s"(distinct $tx)"
        replaceCast(res.set2set)

      case "mins" =>
        find += s"(min $n $tx)"
        replaceCast(res.vector2set)

      case "min" =>
        find += s"(min $tx)"

      case "maxs" =>
        find += s"(max $n $tx)"
        replaceCast(res.vector2set)

      case "max" =>
        find += s"(max $tx)"

      case "rands" =>
        find += s"(rand $n $tx)"
        replaceCast(res.vector2set)

      case "rand" =>
        find += s"(rand $tx)"

      case "samples" =>
        find += s"(sample $n $tx)"
        replaceCast(res.vector2set)

      case "sample" =>
        find += s"(sample 1 $tx)"
        replaceCast(res.seq2t)

      case "count" =>
        find += s"(count $tx)"
        replaceCast(toInt)

      case "countDistinct" =>
        find += s"(count-distinct $tx)"
        replaceCast(toInt)

      case "sum"      => notImpl("sum")
      case "median"   => notImpl("median")
      case "avg"      => notImpl("avg")
      case "variance" => notImpl("variance")
      case "stddev"   => notImpl("stddev")
      case other      => unexpectedKw(other)
    }
  }

  private def notImpl(fn: String): Unit = {
    throw ModelError(s"Aggregating $fn for transaction entity ids not implemented.")
  }

  private def equal[T: ClassTag](tx: Var, argValues: Seq[T], fromScala: Any => Any): Unit = {
    in += s"[$tx ...]"
    args += argValues.map(fromScala).toArray
  }

  private def neq[T](tx: Var, args: Seq[T], toDatalog: T => String): Unit = {
    args.foreach { arg =>
      where += s"[(!= $tx ${toDatalog(arg)})]" -> wNeqOne
    }
  }

  private def compare[T](tx: Var, v: Var, arg: T, op: String, fromScala: Any => Any): Unit = {
    val v1 = v + 1
    in += v1
    where += s"[($op $tx $v1)]" -> wNeqOne
    args += fromScala(arg).asInstanceOf[AnyRef]
  }

  private def noValue(): Unit = {
    throw ModelError("Since all transaction entities have an id, asking for those without is not implemented.")
  }
}