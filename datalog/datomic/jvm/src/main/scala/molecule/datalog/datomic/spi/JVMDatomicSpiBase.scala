package molecule.datalog.datomic.spi

import java.util.{Date, UUID, Collection => jCollection}
import datomic.Peer
import molecule.base.error._
import molecule.core.action.Update
import molecule.core.spi.Conn
import molecule.core.util.{JavaConversions, ModelUtils}
import molecule.core.validation.TxModelValidation
import molecule.datalog.datomic.facade.DatomicConn_JVM
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
import molecule.core.ast.DataModel._


trait JVMDatomicSpiBase extends ModelUtils with JavaConversions {

  protected def validateUpdate(conn0: Conn, update: Update): Map[String, Seq[String]] = {
    val conn  = conn0.asInstanceOf[DatomicConn_JVM]
    val proxy = conn.proxy
    val db    = conn.peerConn.db()

    val getCurSetValues: Attr => Set[Any] = (attr: Attr) => {
      val a = s":${attr.ent}/${attr.attr}"
      try {
        val curValues = Peer.q(s"[:find ?vs :where [_ $a ?vs]]", db)
        if (curValues.isEmpty) {
          throw ExecutionError(s"While checking to avoid removing the last values of mandatory " +
            s"attribute ${attr.ent}.${attr.attr} the current Set of values couldn't be found.")
        }
        val vs = ListBuffer.empty[Any]
        curValues.forEach(row => vs += row.get(0))
        vs.toSet
      } catch {
        case e: MoleculeError => throw e
        case _: Throwable     => throw ExecutionError(
          s"Unexpected error trying to find current values of mandatory attribute ${attr.name}")
      }
    }

    TxModelValidation(
      proxy.entityMap,
      proxy.attrMap,
      "update",
      Some(getCurSetValues)
    ).validate(update.elements)
  }

  protected def toScala(
    value: Any,
    depth: Int = 1,
    maxDepth: Int = 5,
  ): Any = {
    def retrieve(value: Any): Any = value match {
      case v: java.lang.String                => v
      case v: java.lang.Integer               => v.toLong: Long
      case v: java.lang.Long                  => v: Long
      case v: java.lang.Float                 => v: Float
      case v: java.lang.Double                => v: Double
      case v: java.lang.Boolean               => v: Boolean
      case v: Date                            => v
      case v: UUID                            => v
      case v: java.net.URI                    => v
      case v: clojure.lang.BigInt             => BigInt(v.toString)
      case v: java.math.BigInteger            => BigInt(v)
      case v: java.math.BigDecimal            => BigDecimal(v)
      case vs: Array[Byte]                    => vs
      case kw: clojure.lang.Keyword           => kw.toString
      case vs: clojure.lang.PersistentHashSet => vs.asInstanceOf[java.util.Collection[_]].asScala.map(retrieve).toSet
      case vs: clojure.lang.PersistentVector  => vs.asInstanceOf[java.util.Collection[_]].asScala.map(retrieve).toSet

      case vs: clojure.lang.PersistentArrayMap =>
        @tailrec
        def flat(set: Set[Any]): Set[Any] = {
          set.head match {
            case _: Set[_] => flat(set.asInstanceOf[Set[Set[Any]]].flatten)
            case _         => set
          }
        }
        // Flatten single Set
        flat(vs.values.asScala.map(retrieve).toSet)

      case col: jCollection[_] =>
        new Iterable[Any] {
          override def iterator: Iterator[Any] = new Iterator[Any] {
            private val jIter = col.iterator.asInstanceOf[java.util.Iterator[AnyRef]]
            override def hasNext = jIter.hasNext
            override def next(): Any = if (depth < maxDepth)
              retrieve(jIter.next())
            else
              jIter.next()
          }
          override def isEmpty = col.isEmpty
          override def size: Int = col.size
          override def toString = col.toString
        }

      case None       => None
      case null       => null
      case unexpected => new Exception(
        "Unexpected Datalog type to convert: " + unexpected.getClass.toString
      )
    }
    retrieve(value)
  }
}
