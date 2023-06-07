package molecule.sql.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import scala.reflect.ClassTag

trait ResolveExprSet[Tpl] { self: SqlModel2Query[Tpl] with LambdasSet =>

  protected def resolveAttrSetMan(attr: AttrSetMan): Unit = {
    aritiesAttr()
    sortAttrIndex += 1
    attr match {
      case at: AttrSetManString     => man(attr, at.vs, resSetString)
      case at: AttrSetManInt        => man(attr, at.vs, resSetInt)
      case at: AttrSetManLong       => man(attr, at.vs, resSetLong)
      case at: AttrSetManFloat      => man(attr, at.vs, resSetFloat)
      case at: AttrSetManDouble     => man(attr, at.vs, resSetDouble)
      case at: AttrSetManBoolean    => man(attr, at.vs, resSetBoolean)
      case at: AttrSetManBigInt     => man(attr, at.vs, resSetBigInt)
      case at: AttrSetManBigDecimal => man(attr, at.vs, resSetBigDecimal)
      case at: AttrSetManDate       => man(attr, at.vs, resSetDate)
      case at: AttrSetManUUID       => man(attr, at.vs, resSetUUID)
      case at: AttrSetManURI        => man(attr, at.vs, resSetURI)
      case at: AttrSetManByte       => man(attr, at.vs, resSetByte)
      case at: AttrSetManShort      => man(attr, at.vs, resSetShort)
      case at: AttrSetManChar       => man(attr, at.vs, resSetChar)
    }
  }

  protected def resolveAttrSetTac(attr: AttrSetTac): Unit = {
    attr match {
      case at: AttrSetTacString     => tac(attr, at.vs, resSetString)
      case at: AttrSetTacInt        => tac(attr, at.vs, resSetInt)
      case at: AttrSetTacLong       => tac(attr, at.vs, resSetLong)
      case at: AttrSetTacFloat      => tac(attr, at.vs, resSetFloat)
      case at: AttrSetTacDouble     => tac(attr, at.vs, resSetDouble)
      case at: AttrSetTacBoolean    => tac(attr, at.vs, resSetBoolean)
      case at: AttrSetTacBigInt     => tac(attr, at.vs, resSetBigInt)
      case at: AttrSetTacBigDecimal => tac(attr, at.vs, resSetBigDecimal)
      case at: AttrSetTacDate       => tac(attr, at.vs, resSetDate)
      case at: AttrSetTacUUID       => tac(attr, at.vs, resSetUUID)
      case at: AttrSetTacURI        => tac(attr, at.vs, resSetURI)
      case at: AttrSetTacByte       => tac(attr, at.vs, resSetByte)
      case at: AttrSetTacShort      => tac(attr, at.vs, resSetShort)
      case at: AttrSetTacChar       => tac(attr, at.vs, resSetChar)
    }
  }

  protected def resolveAttrSetOpt(attr: AttrSetOpt): Unit = {
    aritiesAttr()
    sortAttrIndex += 1
    hasOptAttr = true // to avoid redundant None's
    attr match {
      case at: AttrSetOptString     => opt(at, at.vs, resOptSetString)
      case at: AttrSetOptInt        => opt(at, at.vs, resOptSetInt)
      case at: AttrSetOptLong       => opt(at, at.vs, resOptSetLong)
      case at: AttrSetOptFloat      => opt(at, at.vs, resOptSetFloat)
      case at: AttrSetOptDouble     => opt(at, at.vs, resOptSetDouble)
      case at: AttrSetOptBoolean    => opt(at, at.vs, resOptSetBoolean)
      case at: AttrSetOptBigInt     => opt(at, at.vs, resOptSetBigInt)
      case at: AttrSetOptBigDecimal => opt(at, at.vs, resOptSetBigDecimal)
      case at: AttrSetOptDate       => opt(at, at.vs, resOptSetDate)
      case at: AttrSetOptUUID       => opt(at, at.vs, resOptSetUUID)
      case at: AttrSetOptURI        => opt(at, at.vs, resOptSetURI)
      case at: AttrSetOptByte       => opt(at, at.vs, resOptSetByte)
      case at: AttrSetOptShort      => opt(at, at.vs, resOptSetShort)
      case at: AttrSetOptChar       => opt(at, at.vs, resOptSetChar)
    }
  }


  private def man[T: ClassTag](attr: Attr, args: Seq[Set[T]], res: ResSet[T]): Unit = {
    val col = getCol(attr: Attr)
    select += col
    if (isNestedOpt) {
      addCast(res.sql2setOrNull)
    } else {
      addCast(res.sql2set)
      notNull += col
    }

    attr.filterAttr.fold {
      if (filterAttrVars.contains(attr.name) && attr.op != V) {
        // Runtime check needed since we can't type infer it
        throw ModelError(s"Cardinality-set filter attributes not allowed to do additional filtering. Found:\n  " + attr)
      }
      expr(col, attr.op, args, res)
      //      filterAttrVars1 = filterAttrVars1 + (a -> (e, v))
      //      filterAttrVars2.get(a).foreach(_(e, v))
    } { filterAttr =>
      expr2(col, attr.op, s":${filterAttr.ns}/${filterAttr.attr}")
    }
  }

  private def tac[T: ClassTag](attr: Attr, args: Seq[Set[T]], res: ResSet[T]): Unit = {
    val col = getCol(attr: Attr)
    attr.filterAttr.fold {
      expr(col, attr.op, args, res)
      //      filterAttrVars1 = filterAttrVars1 + (a -> (e, v))
      //      filterAttrVars2.get(a).foreach(_(e, v))
    } { filterAttr =>
      expr2(col, attr.op, s":${filterAttr.ns}/${filterAttr.attr}")
    }
  }

  private def opt[T: ClassTag](attr: Attr, optSets: Option[Seq[Set[T]]], resOpt: ResSetOpt[T]): Unit = {
    val col = getCol(attr: Attr)
    select += col
    addCast(resOpt.sql2setOpt)
    attr.op match {
      case V     => ()
      case Eq    => optEq(col, optSets, resOpt.set2sql)
      case Neq   => optNeq(col, optSets, resOpt.set2sql)
      case Has   => optHas(col, optSets, resOpt.one2sql)
      case HasNo => optHasNo(col, optSets, resOpt.one2sql)
      case HasLt => optCompare(col, optSets, "<", resOpt.one2sql)
      case HasGt => optCompare(col, optSets, ">", resOpt.one2sql)
      case HasLe => optCompare(col, optSets, "<=", resOpt.one2sql)
      case HasGe => optCompare(col, optSets, ">=", resOpt.one2sql)
      case other => unexpectedOp(other)
    }
  }

  private def expr[T: ClassTag](col: String, op: Op, sets: Seq[Set[T]], res: ResSet[T]): Unit = {
    op match {
      case V         => ()
      case Eq        => equal(col, sets, res.set2sql)
      case Neq       => neq(col, sets, res.set2sql)
      case Has       => has(col, sets, res.one2sql)
      case HasNo     => hasNo(col, sets, res.one2sql)
      case HasLt     => compare(col, sets.head.head, "<", res.one2sql)
      case HasGt     => compare(col, sets.head.head, ">", res.one2sql)
      case HasLe     => compare(col, sets.head.head, "<=", res.one2sql)
      case HasGe     => compare(col, sets.head.head, ">=", res.one2sql)
      case NoValue   => noValue(col)
      case Fn(kw, n) => aggr(col, kw, n, res)
      case other     => unexpectedOp(other)
    }
  }

  private def expr2(col: String, op: Op, filterAttr: String): Unit = {
    op match {
      case Eq    => equal2(col, filterAttr)
      case Neq   => neq2(col, filterAttr)
      case Has   => has2(col, filterAttr)
      case HasNo => hasNo2(col, filterAttr)
      case HasLt => compare2(col, "<", filterAttr)
      case HasGt => compare2(col, ">", filterAttr)
      case HasLe => compare2(col, "<=", filterAttr)
      case HasGe => compare2(col, ">=", filterAttr)
      case other => unexpectedOp(other)
    }
  }


  private def aggr[T](col: String, fn: String, optN: Option[Int], res: ResSet[T]): Unit = {
    lazy val n = optN.getOrElse(0)
    // Replace find/casting with aggregate function/cast
    //    select -= s"(distinct $v)"
    fn match {
      case "distinct" =>
      //        val (v1, v2, e1) = (v + 1, v + 2, e + 1)
      //        select += s"(distinct $v2)"
      //        whereOLD += s"[$e $a $v$tx]" -> wClause
      //        whereOLD +=
      //          s"""[(datomic.api/q
      //             |          "[:find (distinct $v1)
      //             |            :in $$ $e1
      //             |            :where [$e1 $a $v1]]" $$ $e) [[$v2]]]""".stripMargin -> wClause
      //        replaceCast(res.sets)

      case "mins" =>
      //        select += s"(min $n $v)"
      //        replaceCast(res.vector2set)

      case "min" =>
      //        select += s"(min 1 $v)"
      //        replaceCast(res.vector2set)

      case "maxs" =>
      //        select += s"(max $n $v)"
      //        replaceCast(res.vector2set)

      case "max" =>
      //        select += s"(max 1 $v)"
      //        replaceCast(res.vector2set)

      case "rands" =>
      //        select += s"(rand $n $v)"
      //        replaceCast(res.vector2set)

      case "rand" =>
      //        select += s"(rand 1 $v)"
      //        replaceCast(res.vector2set)

      case "samples" =>
      //        select += s"(sample $n $v)"
      //        replaceCast(res.vector2set)

      case "sample" =>
      //        select += s"(sample 1 $v)"
      //        replaceCast(res.vector2set)

      case "count" =>
      //        select += s"(count $v)"
      //        widh += e
      //        replaceCast(toInt)

      case "countDistinct" =>
      //        select += s"(count-distinct $v)"
      //        widh += e
      //        replaceCast(toInt)

      case "sum" =>
      //        select += s"(sum $v)"
      //        replaceCast(res.j2sSet)

      case "median" =>
      //        select += s"(median $v)"
      //        replaceCast(res.j2sSet)

      case "avg" =>
      //        select += s"(avg $v)"
      //        replaceCast(res.j2sSet)

      case "variance" =>
      //        select += s"(variance $v)"
      //        replaceCast(res.j2sSet)

      case "stddev" =>
      //        select += s"(stddev $v)"
      //        replaceCast(res.j2sSet)

      case other => unexpectedKw(other)
    }
    //    whereOLD += s"[$e $a $v$tx]" -> wClause
  }


  private def equal[T](col: String, sets: Seq[Set[T]], set2sql: Set[T] => String): Unit = {
    val sets1 = sets.filterNot(_.isEmpty)
    sets1.length match {
      case 0 => where += (("FALSE", ""))
      case 1 =>
        if (sets1.head.nonEmpty)
          where += ((col, "= " + set2sql(sets1.head)))
      case _ =>
        where += ((col, sets1.map(set2sql).mkString("IN (", ", ", ")")))
    }
    //    if (sets.length == 1)
    //      where += ((col, "= " + set2sql(sets.head)))
    //    else
    //      where += ((col, sets.map(set2sql).mkString("IN (", ", ", ")")))


  }
  private def equal2(col: String, filterAttr: String): Unit = {
    //    preFind = e
    //    whereOLD += s"[$e $a $v$tx]" -> wClause
    //    whereOLD +=
    //      s"""[(datomic.api/q
    //         |          "[:find (distinct ${v}1)
    //         |            :in $$ ${e}1
    //         |            :where [${e}1 $a ${v}1]]" $$ $e) [[${v}2]]]""".stripMargin -> wClause
    //    val link: (Var, Var) => Unit = (e1: Var, v1: Var) => {
    //      whereOLD +=
    //        s"""[(datomic.api/q
    //           |          "[:find (distinct ${v1}1)
    //           |            :in $$ ${e1}1
    //           |            :where [${e1}1 $filterAttr ${v1}1]]" $$ $e1) [[${v1}2]]]""".stripMargin -> wClause
    //      whereOLD += s"[(= ${v}2 ${v1}2)]" -> wClause
    //    }
    //    filterAttrVars1.get(filterAttr).fold {
    //      filterAttrVars2 = filterAttrVars2 + (filterAttr -> link)
    //    } {
    //  case (e, a) => link(e, a)
    //}
  }

  private def neq[T](col: String, sets: Seq[Set[T]], set2sql: Set[T] => String): Unit = {
    val sets1 = sets.filterNot(_.isEmpty)
    sets1.length match {
      case 0 => ()
      case 1 => where += ((col, "<> " + set2sql(sets1.head)))
      case _ => where += ((col, sets1.map(set2sql).mkString("NOT IN (", ", ", ")")))
    }
  }
  private def neq2(col: String, filterAttr: String): Unit = {
    //    whereOLD += s"[$e $a $v$tx]" -> wClause
    //    val process: (Var, Var) => Unit = (e1: Var, v1: Var) => {
    //      val blacklist   = v1 + "-blacklist"
    //      val blacklisted = v1 + "-blacklisted"
    //
    //      // Pre-query
    //      preFind = e1
    //      preWhere +=
    //        s"""[(datomic.api/q
    //           |          "[:find (distinct ${v}1)
    //           |            :in $$ ${e}1
    //           |            :where [${e}1 $a ${v}1]]" $$ $e) [[${v}2]]]""".stripMargin -> wClause
    //      preWhere +=
    //        s"""[(datomic.api/q
    //           |          "[:find (distinct ${v1}1)
    //           |            :in $$ ${e1}1
    //           |            :where [${e1}1 $filterAttr ${v1}1]]" $$ $e1) [[${v1}2]]]""".stripMargin -> wClause
    //      preWhere += s"[(= ${v}2 ${v1}2)]" -> wClause
    //
    //      // Main query
    //      inPost += blacklist
    //      wherePost += s"[(contains? $blacklist $e1) $blacklisted]" -> wClause
    //      wherePost += s"[(not $blacklisted)]" -> wClause
    //    }
    //    filterAttrVars1.get(filterAttr).fold {
    //      filterAttrVars2 = filterAttrVars2 + (filterAttr -> process)
    //    } { case (e, a) =>
    //      process(e, a)
    //    }
  }

  private def has[T: ClassTag](col: String, sets: Seq[Set[T]], one2sql: T => String): Unit = {
    def contains(v: T): String = s"ARRAY_CONTAINS($col, ${one2sql(v)})"
    def containsSet(set: Set[T]): String = set.map(contains).mkString("(", " AND\n   ", ")")
    sets.length match {
      case 0 => where += (("FALSE", ""))
      case 1 =>
        val set = sets.head
        set.size match {
          case 0 => where += (("FALSE", ""))
          case 1 => where += (("", contains(set.head)))
          case _ => where += (("", containsSet(set)))
        }
      case _ =>
        val expr = sets
          .filterNot(_.isEmpty)
          .map(containsSet).mkString("(", " OR\n   ", ")")
        where += (("", expr))
    }
  }
  private def has2(col: String, filterAttr: String): Unit = {
    //    whereOLD += s"[$e $a $v$tx]" -> wClause
    //    val process: (Var, Var) => Unit = (e1: Var, v1: Var) => {
    //      whereOLD +=
    //        s"""[(datomic.api/q
    //           |          "[:find (distinct ${v}1)
    //           |            :in $$ ${e}1
    //           |            :where [${e}1 $a ${v}1]]" $$ $e) [[${v}2]]]""".stripMargin -> wClause
    //      whereOLD +=
    //        s"""[(datomic.api/q
    //           |          "[:find (distinct ${v1}1)
    //           |            :in $$ ${e1}1
    //           |            :where [${e1}1 $filterAttr ${v1}1]]" $$ $e1) [[${v1}2]]]""".stripMargin -> wClause
    //      whereOLD += s"[(clojure.set/intersection ${v}2 ${v1}2) $v1-intersection]" -> wClause
    //      whereOLD += s"[(= ${v1}2 $v1-intersection)]" -> wClause
    //    }
    //    filterAttrVars1.get(filterAttr).fold {
    //      filterAttrVars2 = filterAttrVars2 + (filterAttr -> process)
    //    } { case (e, a) =>
    //      process(e, a)
    //    }
  }

  private def hasNo[T](col: String, sets: Seq[Set[T]], one2sql: T => String): Unit = {
    def notContains(v: T): String = s"NOT ARRAY_CONTAINS($col, ${one2sql(v)})"
    def notContainsSet(set: Set[T]): String = set.map(notContains).mkString("(", " OR\n   ", ")")
    sets.length match {
      case 0 => //where += (("FALSE", ""))
      case 1 =>
        val set = sets.head
        set.size match {
          case 0 => //where += (("FALSE", ""))
          case 1 => where += (("", notContains(set.head)))
          case _ => where += (("", notContainsSet(set)))
        }
      case _ =>
        val expr = sets
          .filterNot(_.isEmpty)
          .map(notContainsSet).mkString("(", " AND\n   ", ")")
        where += (("", expr))
    }
  }
  private def hasNo2(col: String, filterAttr: String): Unit = {
    //    // Common for pre-query and main query
    //    whereOLD += s"[$e $a $v$tx]" -> wClause
    //    val process: (Var, Var) => Unit = (e1: Var, v1: Var) => {
    //      whereOLD +=
    //        s"""[(datomic.api/q
    //           |          "[:find (distinct ${v}1)
    //           |            :in $$ ${e}1
    //           |            :where [${e}1 $a ${v}1]]" $$ $e) [[${v}2]]]""".stripMargin -> wClause
    //      whereOLD +=
    //        s"""[(datomic.api/q
    //           |          "[:find (distinct ${v1}1)
    //           |            :in $$ ${e1}1
    //           |            :where [${e1}1 $filterAttr ${v1}1]]" $$ $e1) [[${v1}2]]]""".stripMargin -> wClause
    //      whereOLD += s"[(clojure.set/intersection ${v}2 ${v1}2) $v1-intersection]" -> wClause
    //      whereOLD += s"[(empty? $v1-intersection)]" -> wClause
    //    }
    //    filterAttrVars1.get(filterAttr).fold {
    //      filterAttrVars2 = filterAttrVars2 + (filterAttr -> process)
    //    } { case (e, a) =>
    //      process(e, a)
    //    }
  }

  private def compare[T](col: String, arg: T, op: String, one2sql: T => String): Unit = {
    //    def contains(v: T): String = s"ARRAY_CONTAINS($col, ${one2sql(v)})"
    //    def containsSet(set: Set[T]): String = set.map(contains).mkString("(", " AND\n   ", ")")
    //    sets.length match {
    //      case 0 => where += (("FALSE", ""))
    //      case 1 =>
    //        val set = sets.head
    //        set.size match {
    //          case 0 => where += (("FALSE", ""))
    //          case 1 => where += (("", contains(set.head)))
    //          case _ => where += (("", containsSet(set)))
    //        }
    //      case _ =>
    //        val expr = sets
    //          .filterNot(_.isEmpty)
    //          .map(containsSet).mkString("(", " OR\n   ", ")")
    //        where += (("", expr))
    //    }

    //    val expr =  s"${one2sql(arg)} $op ALL($col)"
    //    val expr =  s"ANY(SELECT * FROM UNNEST($col)) $op ${one2sql(arg)}"
    //    val expr = s"1 = ANY(SELECT * FROM UNNEST($col))"
    //    where += (("", expr))
    noSetCompareYet()
  }
  private def compare2(col: String, op: String, filterAttr: String): Unit = {
    //    whereOLD += s"[$e $a $v$tx]" -> wClause
    //    val process: (Var, Var) => Unit = (e1: Var, v1: Var) => {
    //      whereOLD +=
    //        s"""[(datomic.api/q
    //           |          "[:find $e
    //           |            :in $$ $e $v1
    //           |            :where [$e $a $v][($op $v $v1)]]" $$ $e $v1) [[${e}1]]]""".stripMargin -> wClause
    //    }
    //    filterAttrVars1.get(filterAttr).fold {
    //      filterAttrVars2 = filterAttrVars2 + (filterAttr -> process)
    //    } { case (e, a) =>
    //      process(e, a)
    //    }
  }

  private def noValue(col: String): Unit = {
    // Skip tacit not null clause in this special case
    notNull -= col
    where += ((col, s"IS NULL"))
  }


  private def optHas[T: ClassTag](
    col: String,
    optSets: Option[Seq[Set[T]]],
    one2sql: T => String
  ): Unit = {
    optSets.fold[Unit] {
      where += ((col, s"IS NULL"))
    } { sets =>
      has(col, sets, one2sql)
    }
  }

  private def optHasNo[T](
    col: String,
    optSets: Option[Seq[Set[T]]],
    one2sql: T => String
  ): Unit = {
    optSets.fold[Unit] {
      where += ((col, s"IS NOT NULL"))
    } { sets =>
      val setsWithValues = sets.filterNot(_.isEmpty)
      if (setsWithValues.nonEmpty) {
        hasNo(col, sets.filterNot(_.isEmpty), one2sql)
      } else {
        where += ((col, s"IS NOT NULL"))
      }
    }
  }

  private def optEq[T](col: String, optSets: Option[Seq[Set[T]]], set2sql: Set[T] => String): Unit = {
    optSets.fold[Unit] {
      where += ((col, s"IS NULL"))
    } { sets =>
      equal(col, sets, set2sql)
    }
  }

  private def optNeq[T](col: String, optSets: Option[Seq[Set[T]]], set2sql: Set[T] => String): Unit = {
    if (optSets.isDefined && optSets.get.nonEmpty) {
      neq(col, optSets.get, set2sql)
    }
    notNull += col
  }

  private def optCompare[T](
    col: String,
    optSets: Option[Seq[Set[T]]],
    op: String,
    one2sql: T => String
  ): Unit = {
    optSets.fold[Unit] {
      where += (("FALSE", ""))
    } { sets =>
      //      compare(col, sets.head.head, op, one2sql)
      noSetCompareYet()
    }
  }

  private def noSetCompareYet(): Unit = {
    throw ModelError("Comparing values of Arrays in H2 database not supported yet")
  }

  private def mkRules[T](col: String, sets: Seq[Set[T]], tpe: String, toDatalog: T => String): Seq[String] = {
    tpe match {
      case "Float" =>
        sets.flatMap {
          case set if set.isEmpty => None
          case set                => Some(
            //            set.zipWithIndex.map { case (arg, i) =>
            //              // Coerce Datomic float values for correct comparison (don't know why this is necessary)
            //              // See example: https://clojurians-log.clojureverse.org/datomic/2019-10-29
            //              s"""[$e $a $v$i] [(float $v$i) $v$i-float] [(= $v$i-float (float $arg))]"""
            //            }.mkString(s"[(rule$v $e)\n    ", "\n    ", "]")
            "??"
          )
        }
      case "URI"   =>
        sets.flatMap {
          case set if set.isEmpty => None
          case set                => Some(
            //            set.zipWithIndex.map { case (arg, i) =>
            //              s"""[(ground (new java.net.URI "$arg")) $v$i-uri] [$e $a $v$i-uri]"""
            //            }.mkString(s"[(rule$v $e)\n    ", "\n    ", "]")
            "??"
          )
        }
      case _       =>
        sets.flatMap {
          case set if set.isEmpty => None
          case set                => Some(
            //            set.map(arg => s"[$e $a ${toDatalog(arg)}]")
            //              .mkString(s"[(rule$v $e)\n    ", "\n    ", "]")
            "??"
          )
        }
    }
  }
}