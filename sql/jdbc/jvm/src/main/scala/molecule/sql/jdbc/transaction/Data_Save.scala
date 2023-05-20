package molecule.sql.jdbc.transaction

import java.net.URI
import java.sql.{SQLException, Statement, PreparedStatement => PS}
import java.util.{Date, UUID}
import molecule.base.error.{ExecutionError, ModelError, MoleculeError}
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.SaveExtraction
import molecule.core.transaction.ops.SaveOps
import scala.collection.mutable.ListBuffer
import scala.util.control.NonFatal

trait Data_Save extends JdbcTxBase_JVM with SaveOps with MoleculeLogging { self: SaveExtraction =>

  var colIndex = 0

  def getData(elements: List[Element]): Data = {
    table = getInitialNs(elements)

    // create insert statement
    resolve(elements)
    saveNs()
    (insertStmts, batchSetters)

    ???
  }

  override protected def handleNs(ns: String): Unit = {
    backRefs = backRefs + (ns -> e)
  }
  override protected def handleComposite(isInsertTxMetaData: Boolean): Unit = {
    //    e = if (isInsertTxMetaData) datomicTx else e0
  }
  override protected def handleTxMetaData(): Unit = {
    //    e = datomicTx
    //    e0 = datomicTx
  }

  override protected def addV(ns: String, attr: String, optValue: Option[Any]): Unit = {
    columns += attr
    optValue.fold {
      colSetters = colSetters :+ ((ps: PS, _: Map[(Int, List[String], String), Array[Long]], _: Int) => {
        ps.setNull(colIndex, 0)
      })
    } { value =>
      colIndex += 1
      val j = colIndex
      colSetters = colSetters :+ ((ps: PS, _: Map[(Int, List[String], String), Array[Long]], _: Int) => {
        value.asInstanceOf[(PS, Int) => Unit](ps, j)
      })
    }
  }

  override protected def addSet[T](ns: String, attr: String, optSet: Option[Set[T]]): Unit = {
    //    optSet.foreach { set =>
    //      val a = kw(ns, attr)
    //      set.foreach { v =>
    //        appendStmt(add, e, a, v.asInstanceOf[AnyRef])
    //      }
    //    }
  }

  override protected def backRef(backRefNs: String): Unit = {
    e = backRefs(backRefNs)
  }

  override protected def ref(ns: String, refAttr: String, refNs: String): Unit = {
    columns += refAttr
    val j                 = colIndex + 1
    val colSetter: Setter = (ps: PS, insertIds: Map[(Int, List[String], String), Array[Long]], _: Int) => {
      ps.setLong(j, insertIds((0, Nil, ns)).head)
    }
    colSetters = colSetters :+ colSetter
    saveNs()
    table = refNs
  }

  def saveNs(): Unit = {
    val stmt =
      s"""insert into $table(
         |  ${columns.mkString(",\n  ")}
         |) values (${columns.map(_ => "?").mkString(", ")})""".stripMargin

    println("---- stmt -----------\n" + stmt)
    insertStmts = stmt :: insertStmts
    val colSetters1    = colSetters
    val insert: Setter = (ps: PS, insertIds: Map[(Int, List[String], String), Array[Long]], rowIndex: Int) => {
      colSetters1.foreach { colSetter =>
        colSetter(ps, insertIds, rowIndex)
      }
      ps.addBatch()
    }
    batchSetters = insert :: batchSetters
    colIndex = 0
    columns.clear()
  }

  override protected lazy val valueString     = (v: String) => (ps: PS, n: Int) => ps.setString(n, v)
  override protected lazy val valueInt        = (v: Int) => (ps: PS, n: Int) => ps.setInt(n, v)
  override protected lazy val valueLong       = (v: Long) => (ps: PS, n: Int) => ps.setLong(n, v)
  override protected lazy val valueFloat      = (v: Float) => (ps: PS, n: Int) => ps.setFloat(n, v)
  override protected lazy val valueDouble     = (v: Double) => (ps: PS, n: Int) => ps.setDouble(n, v)
  override protected lazy val valueBoolean    = (v: Boolean) => (ps: PS, n: Int) => ps.setBoolean(n, v)
  override protected lazy val valueBigInt     = (v: BigInt) => (ps: PS, n: Int) => ps.setBigDecimal(n, BigDecimal(v).bigDecimal)
  override protected lazy val valueBigDecimal = (v: BigDecimal) => (ps: PS, n: Int) => ps.setBigDecimal(n, v.bigDecimal)
  override protected lazy val valueDate       = (v: Date) => (ps: PS, n: Int) => ps.setDate(n, new java.sql.Date(v.getTime))
  override protected lazy val valueUUID       = (v: UUID) => (ps: PS, n: Int) => ps.setString(n, v.toString)
  override protected lazy val valueURI        = (v: URI) => (ps: PS, n: Int) => ps.setString(n, v.toString)
  override protected lazy val valueByte       = (v: Byte) => (ps: PS, n: Int) => ps.setByte(n, v)
  override protected lazy val valueShort      = (v: Short) => (ps: PS, n: Int) => ps.setShort(n, v)
  override protected lazy val valueChar       = (v: Char) => (ps: PS, n: Int) => ps.setString(n, v.toString)
}