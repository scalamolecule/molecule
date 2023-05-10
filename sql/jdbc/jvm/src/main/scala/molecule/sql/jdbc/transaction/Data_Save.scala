package molecule.sql.jdbc.transaction

import java.net.URI
import java.sql.{PreparedStatement => PS, SQLException, Statement}
import java.util.{Date, UUID}
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.SaveExtraction
import molecule.core.transaction.ops.SaveOps
import scala.collection.mutable.ListBuffer
import scala.util.control.NonFatal

trait Data_Save extends JdbcTxBase_JVM with SaveOps with MoleculeLogging { self: SaveExtraction =>

  // Column index
  var i = 0

  def getData(elements: List[Element]): Data = {
    table = getInitialNs(elements)

    // create insert statement
    resolve(elements)

    // Add top level stmt
    addStmt()

    (stmts, setters)
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
      colSetters = colSetters :+ ((ps: PS, _: List[Long]) => {i += 1; ps.setNull(i, 0)})
    } { value =>
      colSetters = colSetters :+ ((ps: PS, _: List[Long]) => {
        i += 1
        value.asInstanceOf[(PS, Int) => Unit](ps, i)
      })
    }
//    i += 1
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

  override protected def ref(ns: String, refAttr: String): Unit = {
    addStmt()
    colSetters = colSetters :+ ((ps: PS, refIds: List[Long]) => {i += 1; ps.setLong(i, refIds.head)})
//    i += 1
    table = ns
  }

  def addStmt(): Unit = {
    val cols = columns.toList
    val stmt =
      s"""insert into $table(
         |  ${cols.mkString(",\n  ")}
         |) values (${cols.map(_ => "?").mkString(", ")})""".stripMargin

    println("---- stmt -----------\n" + stmt)
    stmts = stmt :: stmts
    val setter: Setter = (ps: PS, refIds: List[Long]) => colSetters.foreach(_(ps, refIds))
    setters = setter :: setters
    columns.clear()
  }

  override protected lazy val valueString     =
    (v: String) =>
      (ps: PS, n: Int) =>
        ps.setString(n, v)
  override protected lazy val valueInt        =
    (v: Int) =>
      (ps: PS, n: Int) =>
        ps.setInt(n, v)
  override protected lazy val valueLong       = (v: Long) => (ps: PS, n: Int) => ps.setLong(n, v)
  override protected lazy val valueFloat      = (v: Float) => (ps: PS, n: Int) => ps.setFloat(n, v)
  override protected lazy val valueDouble     = (v: Double) => (ps: PS, n: Int) => ps.setDouble(n, v)
  override protected lazy val valueBoolean    = (v: Boolean) => (ps: PS, n: Int) => ps.setBoolean(n, v)
  override protected lazy val valueBigInt     = (v: BigInt) => (ps: PS, n: Int) => ps.setString(n, v.toString)
  override protected lazy val valueBigDecimal = (v: BigDecimal) => (ps: PS, n: Int) => ps.setBigDecimal(n, v.bigDecimal)
  override protected lazy val valueDate       = (v: Date) => (ps: PS, n: Int) => ps.setLong(n, v.getTime)
  override protected lazy val valueUUID       = (v: UUID) => (ps: PS, n: Int) => ps.setString(n, v.toString)
  override protected lazy val valueURI        = (v: URI) => (ps: PS, n: Int) => ps.setURL(n, v.toURL)
  override protected lazy val valueByte       = (v: Byte) => (ps: PS, n: Int) => ps.setByte(n, v)
  override protected lazy val valueShort      = (v: Short) => (ps: PS, n: Int) => ps.setShort(n, v)
  override protected lazy val valueChar       = (v: Char) => (ps: PS, n: Int) => ps.setString(n, v.toString)
}