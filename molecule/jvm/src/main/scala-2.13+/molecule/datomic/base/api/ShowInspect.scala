package molecule.datomic.base.api

import java.util.{Date, List => jList, Map => jMap}
import clojure.lang.{PersistentHashSet, PersistentVector}
import molecule.core.api.Molecule_0
import molecule.core.ast.elements._
import molecule.datomic.base.ast.query.QueryExpr
import molecule.datomic.base.ast.tempDb._
import molecule.core.exceptions.{MoleculeException, QueryException}
import molecule.core.ops.VerifyModel
import molecule.datomic.base.ops.QueryOps._
import molecule.datomic.base.transform._
import molecule.datomic.base.ast.transactionModel.Statement
import molecule.datomic.base.facade.{Conn, TxReport}
import molecule.datomic.base.transform.Model2Transaction
import molecule.datomic.base.util.Inspect
import scala.collection.mutable.ListBuffer
import scala.jdk.CollectionConverters._
import scala.language.implicitConversions


/** Inspect methods
  *
  * Call a inspect method on a molecule to see the internal transformations and
  * produced transaction statements or sample data.
  * */
trait ShowInspect[Obj, Tpl] { self: Molecule_0[Obj, Tpl] =>


  /** Inspect call to `get` on a molecule (without affecting the db).
    * <br><br>
    * Prints the following to output:
    * <br><br>
    * 1. Internal molecule transformation representations:
    * <br>Molecule DSL --> Model --> Query --> Datomic query
    * <br><br>
    * 2. Data returned from get query (max 500 rows).
    * OBS: printing raw Date's (Clojure Instant) will miss the time-zone
    *
    * @group inspectGet
    * @param conn Implicit [[molecule.datomic.base.facade.Conn Conn]] value in scope
    */
  def inspectGet(implicit conn: Conn): Unit = {

    def generic(): Unit = {
      val rows = conn._index(_model)
      val pE   = 14
      val pA   = 20
      val pV   = 50
      val pT   = 6
      val pD   = 28

      print(
        s"""
           |${_model.toString}
           |
           |     """.stripMargin)

      var pad = 0
      def p(v: Any, i: Int): String = v.toString.padTo(i, ' ') + "   "
      val n = {
        val dataElements: Seq[Generic] = _model.elements.collect {
          case e@Generic(_, attr, _, _)
            if attr != "args_" && attr != "range" => e
        }

        //        _model.elements.zipWithIndex.map {
        dataElements.zipWithIndex.map {
          case (Generic(_, "e", _, _), i)         => print(p("E", pE)); pad += pE + 3; i -> pE
          case (Generic(_, "a", _, _), i)         => print(p("A", pA)); pad += pA + 3; i -> pA
          case (Generic(_, "v", _, _), i)         => print(p("V", pV)); pad += pV + 3; i -> pV
          case (Generic(_, "t", _, _), i)         => print(p("T", pT)); pad += pT + 3; i -> pT
          case (Generic(_, "tx", _, _), i)        => print(p("Tx", pE)); pad += pE + 3; i -> pE
          case (Generic(_, "txInstant", _, _), i) => print(p("TxInstant", pD)); pad += pD + 3; i -> pD
          case (Generic(_, "op", _, _), i)        => print(p("Op", pT)); pad += pT + 3; i -> pT
          case other                              => throw new MoleculeException("Unexpected element: " + other)
        }.toMap
      }

      println()
      println("-----" + "-" * pad)

      var i = 0
      n.size match {
        case 0 => rows.forEach { row => i += 1; if (i <= 500) { println(p(i, 2)) }}
        case 1 => rows.forEach { row => i += 1; if (i <= 500) { println(p(i, 2) + p(row.get(0), n(0)))} }
        case 2 => rows.forEach { row => i += 1; if (i <= 500) { println(p(i, 2) + p(row.get(0), n(0)) + p(row.get(1), n(1)))} }
        case 3 => rows.forEach { row => i += 1; if (i <= 500) { println(p(i, 2) + p(row.get(0), n(0)) + p(row.get(1), n(1)) + p(row.get(2), n(2)))} }
        case 4 => rows.forEach { row => i += 1; if (i <= 500) { println(p(i, 2) + p(row.get(0), n(0)) + p(row.get(1), n(1)) + p(row.get(2), n(2)) + p(row.get(3), n(3)))} }
        case 5 => rows.forEach { row => i += 1; if (i <= 500) { println(p(i, 2) + p(row.get(0), n(0)) + p(row.get(1), n(1)) + p(row.get(2), n(2)) + p(row.get(3), n(3)) + p(row.get(4), n(4)))} }
        case 6 => rows.forEach { row => i += 1; if (i <= 500) { println(p(i, 2) + p(row.get(0), n(0)) + p(row.get(1), n(1)) + p(row.get(2), n(2)) + p(row.get(3), n(3)) + p(row.get(4), n(4)) + p(row.get(5), n(5)))} }
        case 7 => rows.forEach { row => i += 1; if (i <= 500) { println(p(i, 2) + p(row.get(0), n(0)) + p(row.get(1), n(1)) + p(row.get(2), n(2)) + p(row.get(3), n(3)) + p(row.get(4), n(4)) + p(row.get(5), n(5)) + p(row.get(6), n(6)))} }
      }

      println("-----" + "-" * pad)
      if (rows.size > 500)
        println(s"(showing 500 out of ${rows.size} rows)")
      println()
    }

    def isAggr(fn: String) = Seq("count", "count-distinct", "avg", "variance", "stddev").contains(fn)

    // outputMatrix:
    // card, isOptional, isDate, isAggr
    def recurse(i: Int, acc: Seq[(Int, Boolean, Boolean, Boolean)], elements: Seq[Element])
    : (Int, Seq[(Int, Boolean, Boolean, Boolean)]) = {
      elements.foldLeft(i, acc) {
        case ((i, acc), Generic(_, "txInstant", _, _))                      => (i + 1, acc :+ (1, false, true, false))
        case ((i, acc), Generic(_, _, "datom" | "schema", _))               => (i + 1, acc :+ (1, false, false, false))
        case ((i, acc), ga: GenericAtom) if ga.attr.last == '_'             => (i, acc)
        case ((i, acc), Atom(_, _, _, _, Fn(fn, _), _, _, _)) if isAggr(fn) => (i + 1, acc :+ (1, false, false, true))
        case ((i, acc), Atom(_, attr, "Date", card, _, _, _, _))  => (i + 1, acc :+ (card, attr.last == '$', true, false))
        case ((i, acc), Atom(_, attr, _, card, _, _, _, _))                 => (i + 1, acc :+ (card, attr.last == '$', false, false))
        case ((i, acc), Nested(_, nestedElements))                          => recurse(i, acc, nestedElements)
        case ((i, acc), Composite(compositeElements))                       => recurse(i, acc, compositeElements)
        case ((i, acc), TxMetaData(txElements))                             => recurse(i, acc, txElements)
        case ((i, acc), _)                                                  => (i, acc)
      }
    }

    val outputMatrix: Seq[(Int, Boolean, Boolean, Boolean)] = {
      recurse(0, Seq.empty[(Int, Boolean, Boolean, Boolean)], _model.elements)._2
    }

    def resolve(rawRows: Iterable[jList[AnyRef]]): Seq[ListBuffer[Any]] = {
      def cardOneOpt(v: Any, isDate: Boolean): Option[String] = {
        if (v == null) {
          Option.empty[String]
        } else if (isDate) {
          if (v.isInstanceOf[jMap[_, _]]) {
            Some(date2str(v.asInstanceOf[jMap[String, Date]].values.iterator.next))
          } else {
            Some(date2str(v.asInstanceOf[Date]))
          }
        } else {
          if (v.isInstanceOf[jMap[_, _]]) {
            Some(v.asInstanceOf[jMap[String, Any]].values.iterator.next.toString)
          } else {
            Some(v.toString)
          }
        }
      }

      def cardMany(v: Any, isDate: Boolean): Set[String] = {
        val it  = v.asInstanceOf[PersistentHashSet].iterator
        var set = Set.empty[String]
        if (isDate) {
          while (it.hasNext) {
            set = set + date2str(it.next.asInstanceOf[Date])
          }
        } else {
          while (it.hasNext) {
            set = set + it.next.toString
          }
        }
        set
      }
      def cardManyOpt(v: Any, isDate: Boolean): Option[Set[String]] = {
        if (v == null) {
          Option.empty[Set[String]]
        } else v match {
          case set1: PersistentHashSet =>
            val it  = set1.iterator
            var set = Set.empty[String]
            if (isDate) {
              while (it.hasNext)
                set = set + date2str(it.next.asInstanceOf[Date])
            } else {
              while (it.hasNext)
                set = set + it.next.toString
            }
            Some(set)
          case _                       =>
            val it  = v.asInstanceOf[jMap[String, PersistentVector]].values.iterator.next.iterator
            var set = Set.empty[String]
            if (isDate) {
              while (it.hasNext)
                set = set + date2str(it.next.asInstanceOf[Date])
            } else {
              while (it.hasNext)
                set = set + it.next.toString
            }
            Some(set)
        }
      }

      def cardMap(v: Any): Map[String, String] = {
        val it  = v.asInstanceOf[PersistentHashSet].iterator
        var map = Map.empty[String, String]
        var vs  = new Array[String](2)
        while (it.hasNext) {
          vs = it.next.toString.split("@", 2)
          map = map + (vs(0) -> vs(1))
        }
        map
      }
      def cardMapOpt(v: Any): Option[Map[String, String]] = {
        if (v == null) {
          Option.empty[Map[String, String]]
        } else {
          val it  = v.asInstanceOf[jMap[String, PersistentVector]].values.iterator.next.iterator
          var map = Map.empty[String, String]
          var vs  = new Array[String](2)
          while (it.hasNext) {
            vs = it.next.toString.split("@", 2)
            map = map + (vs(0) -> vs(1))
          }
          Some(map)
        }
      }

      val rowLength = rawRows.headOption.fold(0)(_.size)
      val rows1     = new ListBuffer[ListBuffer[Any]]
      var i         = 0
      var j         = 0
      val it        = rawRows.iterator
      while (it.hasNext) {
        val oldRow = it.next()
        val newRow = new ListBuffer[Any]
        j = 0
        while (j < rowLength) {
          val v = oldRow.get(j)
          newRow += (outputMatrix(j) match {
            case (_, _, _, true)       => v
            case (1, false, true, _)   => date2str(v.asInstanceOf[Date])
            case (1, true, isDate, _)  => cardOneOpt(v, isDate)
            case (2, false, isDate, _) => cardMany(v, isDate)
            case (2, true, isDate, _)  => cardManyOpt(v, isDate)
            case (3, false, _, _)      => cardMap(v)
            case (3, true, _, _)       => cardMapOpt(v)
            case (_, _, _, _)          => v
          })
          j += 1
        }
        rows1 += newRow
        i += 1
      }
      rows1.toList
    }

    def data(): Unit = {
      // Also do Model2Query transformation at runtime to be able to inspect it.
      // Note though that input variables are only bound in the macro at compile
      // time and are therefore not present in this runtime process.
      Model2Query(_model)

      val ins   = QueryOps(_query).inputs
      val p     = (expr: QueryExpr) => Query2String(_query).p(expr)
      val rules = "[" + (_query.i.rules.map(p).mkString(" ")) + "]"
      val db    = conn.db
      val first = if (_query.i.rules.isEmpty) Seq(db) else Seq(db, rules)
      val rows  = try {
        resolve(conn._query(_model, _query, Some(db)).asScala.take(500))
      } catch {
        case ex: Throwable =>
          throw new QueryException(ex, _model, _query, first ++ ins, p)
      }

      val rulesOut: String = if (_query.i.rules.isEmpty) "none\n\n" else "[\n " + _query.i.rules.map(Query2String(_query).p(_)).mkString("\n ") + "\n]\n\n"
      val inputs  : String = if (ins.isEmpty) "none\n\n" else "\n" + ins.zipWithIndex.map(r => s"${r._2 + 1}: ${r._1}").mkString("\n") + "\n\n"
      val outs    : String = rows.zipWithIndex.map(r => s"${r._2 + 1}: ${r._1.mkString("[", "  ", "]")}").mkString("\n")
      println(
        "\n--------------------------------------------------------------------------\n" +
          _model + "\n\n" +
          _query + "\n\n" +
          _query.datalog + "\n\n" +
          "RULES: " + rulesOut +
          "INPUTS: " + inputs +
          "OUTPUTS:\n" + outs + "\n(showing up to 500 rows)" +
          "\n--------------------------------------------------------------------------\n"
      )
    }

    _model.elements.head match {
      case Generic("Log" | "EAVT" | "AEVT" | "AVET" | "VAET", _, _, _) => generic()
      case _                                                           => data()
    }
  }


  /** Inspect call to `getAsOf(t)` on a molecule (without affecting the db).
    * <br><br>
    * Prints the following to output:
    * <br><br>
    * 1. Internal molecule transformation representations:
    * <br>Molecule DSL --> Model --> Query --> Datomic query
    * <br><br>
    * 2. Data returned from get query (max 500 rows).
    *
    * @group inspectGet
    * @param t
    * @param conn Implicit [[molecule.datomic.base.facade.Conn Conn]] value in scope
    */
  def inspectGetAsOf(t: Long)(implicit conn: Conn): Unit = inspectGet(conn.usingTempDb(AsOf(TxLong(t))))


  /** Inspect call to `getAsOf(tx)` on a molecule (without affecting the db).
    * <br><br>
    * Prints the following to output:
    * <br><br>
    * 1. Internal molecule transformation representations:
    * <br>Molecule DSL --> Model --> Query --> Datomic query
    * <br><br>
    * 2. Data returned from get query (max 500 rows).
    *
    * @group inspectGet
    * @param tx   [[molecule.datomic.base.facade.TxReport TxReport]]
    * @param conn Implicit [[molecule.datomic.base.facade.Conn Conn]] value in scope
    */
  def inspectGetAsOf(tx: TxReport)(implicit conn: Conn): Unit = inspectGet(conn.usingTempDb(AsOf(TxLong(tx.t))))


  /** Inspect call to `getAsOf(date)` on a molecule (without affecting the db).
    * <br><br>
    * Prints the following to output:
    * <br><br>
    * 1. Internal molecule transformation representations:
    * <br>Molecule DSL --> Model --> Query --> Datomic query
    * <br><br>
    * 2. Data returned from get query (max 500 rows).
    *
    * @group inspectGet
    * @param date
    * @param conn Implicit [[molecule.datomic.base.facade.Conn Conn]] value in scsope
    */
  def inspectGetAsOf(date: Date)(implicit conn: Conn): Unit = inspectGet(conn.usingTempDb(AsOf(TxDate(date))))


  /** Inspect call to `getSince(t)` on a molecule (without affecting the db).
    * <br><br>
    * Prints the following to output:
    * <br><br>
    * 1. Internal molecule transformation representations:
    * <br>Molecule DSL --> Model --> Query --> Datomic query
    * <br><br>
    * 2. Data returned from get query (max 500 rows).
    *
    * @group inspectGet
    * @param t
    * @param conn Implicit [[molecule.datomic.base.facade.Conn Conn]] value in scope
    */
  def inspectGetSince(t: Long)(implicit conn: Conn): Unit = inspectGet(conn.usingTempDb(Since(TxLong(t))))


  /** Inspect call to `getSince(tx)` on a molecule (without affecting the db).
    * <br><br>
    * Prints the following to output:
    * <br><br>
    * 1. Internal molecule transformation representations:
    * <br>Molecule DSL --> Model --> Query --> Datomic query
    * <br><br>
    * 2. Data returned from get query (max 500 rows).
    *
    * @group inspectGet
    * @param tx   [[molecule.datomic.base.facade.TxReport TxReport]]
    * @param conn Implicit [[molecule.datomic.base.facade.Conn Conn]] value in scope
    */
  def inspectGetSince(tx: TxReport)(implicit conn: Conn): Unit = inspectGet(conn.usingTempDb(Since(TxLong(tx.t))))


  /** Inspect call to `getSince(date)` on a molecule (without affecting the db).
    * <br><br>
    * Prints the following to output:
    * <br><br>
    * 1. Internal molecule transformation representations:
    * <br>Molecule DSL --> Model --> Query --> Datomic query
    * <br><br>
    * 2. Data returned from get query (max 500 rows).
    *
    * @group inspectGet
    * @param date
    * @param conn Implicit [[molecule.datomic.base.facade.Conn Conn]] value in scope
    */
  def inspectGetSince(date: Date)(implicit conn: Conn): Unit = inspectGet(conn.usingTempDb(Since(TxDate(date))))


  /** Inspect call to `getWith(txMolecules)` on a molecule (without affecting the db).
    * <br><br>
    * Prints the following to output:
    * <br><br>
    * 1. Internal molecule transformation representations:
    * <br>Molecule DSL --> Model --> Query --> Datomic query
    * <br><br>
    * 2. Data returned from get query (max 500 rows).
    * <br><br>
    * 3. Transactions of applied transaction molecules.
    *
    * @group inspectGet
    * @param txMolecules Transaction statements from applied Molecules with test data
    * @param conn        Implicit [[molecule.datomic.base.facade.Conn Conn]] value in scope
    */
  def inspectGetWith(txMolecules: Seq[Seq[Statement]]*)(implicit conn: Conn): Unit = {
    inspectGet(conn.usingTempDb(With(txMolecules.flatten.flatten.map(_.toJava).asJava)))
    txMolecules.zipWithIndex foreach { case (stmts, i) =>
      Inspect(s"Statements, transaction molecule ${i + 1}:", 1)(i + 1, stmts)
    }
  }


  /** Inspect call to `getWith(txData)` on a molecule (without affecting the db).
    * <br><br>
    * Prints the following to output:
    * <br><br>
    * 1. Internal molecule transformation representations:
    * <br>Molecule DSL --> Model --> Query --> Datomic query
    * <br><br>
    * 2. Data returned from get query (max 500 rows).
    * <br><br>
    * 3. Transactions of applied transaction data.
    *
    * @group inspectGet
    * @param txData Raw transaction data as java.util.List[Object]
    * @param conn   Implicit [[molecule.datomic.base.facade.Conn Conn]] value in scope
    */
  def inspectGetWith(txData: jList[jList[_]])(implicit conn: Conn): Unit = {
    inspectGet(conn.usingTempDb(With(txData.asInstanceOf[jList[jList[_]]])))
    println("Transaction data:\n========================================================================")
    txData.toArray foreach println
  }


  /** Inspect call to `getHistory` on a molecule (without affecting the db).
    * <br><br>
    * Prints the following to output:
    * <br><br>
    * 1. Internal molecule transformation representations:
    * <br>Molecule DSL --> Model --> Query --> Datomic query
    * <br><br>
    * 2. Data returned from get query (max 500 rows).
    *
    * @group inspectGet
    * @param conn Implicit [[molecule.datomic.base.facade.Conn Conn]] value in scope
    */
  def inspectGetHistory(implicit conn: Conn): Unit = inspectGet(conn.usingTempDb(History))


  /** Inspect call to `save` on a molecule (without affecting the db).
    * <br><br>
    * Prints internal molecule transformation representations to output:
    * <br><br>
    * Model --> Generic statements --> Datomic statements
    *
    * @group inspectOp
    * @param conn Implicit [[molecule.datomic.base.facade.Conn Conn]] value in scope
    * @return Unit
    */
  def inspectSave(implicit conn: Conn): Unit = {
    VerifyModel(_model, "save")
    val transformer = Model2Transaction(conn, _model)
    val stmts       = try {
      transformer.saveStmts()
    } catch {
      case e: Throwable =>
        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  Error - data processed so far:  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n")
        Inspect("output.Molecule.inspectSave", 1)(1, _model, transformer.stmtsModel)
        throw e
    }
    Inspect("output.Molecule.inspectSave", 1)(1, _model, transformer.stmtsModel, stmts)
  }


  protected def _inspectInsert(conn: Conn, dataRows: Iterable[Seq[Any]]): Unit = {
    val transformer = Model2Transaction(conn, _model)
    val data        = untupled(dataRows)
    val stmtss      = try {
      transformer.insertStmts(data)
    } catch {
      case e: Throwable =>
        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  Error - data processed so far:  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n")
        Inspect("output.Molecule._inspectInsert", 1)(1, _model, transformer.stmtsModel, dataRows, data)
        throw e
    }
    Inspect("output.Molecule._inspectInsert", 1)(1, _model, transformer.stmtsModel, dataRows, data, stmtss)
  }


  /** Inspect call to `update` on a molecule (without affecting the db).
    * <br><br>
    * Prints internal molecule transformation representations to output:
    * <br><br>
    * Model --> Generic statements --> Datomic statements
    *
    * @group inspectOp
    * @param conn Implicit [[molecule.datomic.base.facade.Conn Conn]] value in scope
    * @return
    */
  def inspectUpdate(implicit conn: Conn): Unit = {
    VerifyModel(_model, "update")
    val transformer = Model2Transaction(conn, _model)
    val stmts       = try {
      transformer.updateStmts()
    } catch {
      case e: Throwable =>
        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  Error - data processed so far:  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n")
        Inspect("output.Molecule.inspectUpdate", 1)(1, _model, transformer.stmtsModel)
        throw e
    }
    Inspect("output.Molecule.inspectUpdate", 1)(1, _model, transformer.stmtsModel, stmts)
  }
}
