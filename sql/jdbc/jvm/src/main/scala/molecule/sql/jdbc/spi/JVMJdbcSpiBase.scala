package molecule.sql.jdbc.spi

import molecule.base.ast.SchemaAST.CardOne
import molecule.base.error.ModelError
import molecule.boilerplate
import molecule.boilerplate.ast
import molecule.boilerplate.ast.Model
import molecule.boilerplate.ast.Model._
import molecule.core.action.{Query, Update}
import molecule.core.spi.Conn
import molecule.core.util.ModelUtils
import molecule.core.validation.ModelValidation
import molecule.sql.jdbc.facade.JdbcConn_jvm
import scala.collection.immutable.List
import scala.collection.mutable.ListBuffer


trait JVMJdbcSpiBase extends ModelUtils {

  def validateUpdate(conn0: Conn, update: Update): Map[String, Seq[String]] = {
    val conn  = conn0.asInstanceOf[JdbcConn_jvm]
    val proxy = conn.proxy
    //    val db                                = conn.sqlConn.db()
    //    val getCurSetValues: Attr => Set[Any] = (attr: Attr) => {
    //      val a = s":${attr.ns}/${attr.attr}"
    //      try {
    //        val curValues = Peer.q(s"[:find ?vs :where [_ $a ?vs]]", db)
    //        if (curValues.isEmpty) {
    //          throw ExecutionError(s"While checking to avoid removing the last values of mandatory " +
    //            s"attribute ${attr.ns}.${attr.attr} the current Set of values couldn't be found.")
    //        }
    //        val vs = ListBuffer.empty[Any]
    //        curValues.forEach(row => vs.addOne(row.get(0)))
    //        vs.toSet
    //      } catch {
    //        case e: MoleculeError => throw e
    //        case t: Throwable     => throw ExecutionError(
    //          s"Unexpected error trying to find current values of mandatory attribute ${attr.name}")
    //      }
    //    }
    ModelValidation(
      proxy.nsMap,
      proxy.attrMap,
      "update",
      //      Some(getCurSetValues)
      None
    ).validate(update.elements)
  }

  def prepareMultipleUpdates(
    elements: List[Element],
    isUpsert: Boolean
  ): (List[Element], List[Long => List[Element]]) = {
    val update       = if (isUpsert) "upsert" else "update"
    var firstNs      = true
    var prevNs       = ""
    val idsModel     = ListBuffer.empty[Element]
    val updateModel  = ListBuffer.empty[Element]
    var updateModels = List.empty[Long => List[Element]]
    elements.foreach {
      case a: Attr =>
        updateModel += a
        a match {
          case a: AttrOneMan =>
            prevNs = a.ns
            a match {
              case a: AttrOneManString     => idsModel += AttrOneTacString(a.ns, a.attr)
              case a: AttrOneManInt        => idsModel += AttrOneTacInt(a.ns, a.attr)
              case a: AttrOneManLong       => idsModel += AttrOneTacLong(a.ns, a.attr)
              case a: AttrOneManFloat      => idsModel += AttrOneTacFloat(a.ns, a.attr)
              case a: AttrOneManDouble     => idsModel += AttrOneTacDouble(a.ns, a.attr)
              case a: AttrOneManBoolean    => idsModel += AttrOneTacBoolean(a.ns, a.attr)
              case a: AttrOneManBigInt     => idsModel += AttrOneTacBigInt(a.ns, a.attr)
              case a: AttrOneManBigDecimal => idsModel += AttrOneTacBigDecimal(a.ns, a.attr)
              case a: AttrOneManDate       => idsModel += AttrOneTacDate(a.ns, a.attr)
              case a: AttrOneManUUID       => idsModel += AttrOneTacUUID(a.ns, a.attr)
              case a: AttrOneManURI        => idsModel += AttrOneTacURI(a.ns, a.attr)
              case a: AttrOneManByte       => idsModel += AttrOneTacByte(a.ns, a.attr)
              case a: AttrOneManShort      => idsModel += AttrOneTacShort(a.ns, a.attr)
              case a: AttrOneManChar       => idsModel += AttrOneTacChar(a.ns, a.attr)
            }
          case a: AttrOneTac => idsModel += a

          case a: AttrSetMan =>
            if (a.op == Eq || a.op == Add || a.op == Swap || a.op == Remove) {
              prevNs = a.ns
              a match {
                case a: AttrSetManString     => idsModel += AttrSetTacString(a.ns, a.attr)
                case a: AttrSetManInt        => idsModel += AttrSetTacInt(a.ns, a.attr)
                case a: AttrSetManLong       => idsModel += AttrSetTacLong(a.ns, a.attr)
                case a: AttrSetManFloat      => idsModel += AttrSetTacFloat(a.ns, a.attr)
                case a: AttrSetManDouble     => idsModel += AttrSetTacDouble(a.ns, a.attr)
                case a: AttrSetManBoolean    => idsModel += AttrSetTacBoolean(a.ns, a.attr)
                case a: AttrSetManBigInt     => idsModel += AttrSetTacBigInt(a.ns, a.attr)
                case a: AttrSetManBigDecimal => idsModel += AttrSetTacBigDecimal(a.ns, a.attr)
                case a: AttrSetManDate       => idsModel += AttrSetTacDate(a.ns, a.attr)
                case a: AttrSetManUUID       => idsModel += AttrSetTacUUID(a.ns, a.attr)
                case a: AttrSetManURI        => idsModel += AttrSetTacURI(a.ns, a.attr)
                case a: AttrSetManByte       => idsModel += AttrSetTacByte(a.ns, a.attr)
                case a: AttrSetManShort      => idsModel += AttrSetTacShort(a.ns, a.attr)
                case a: AttrSetManChar       => idsModel += AttrSetTacChar(a.ns, a.attr)
              }

            } else {
              throw ModelError(s"Unexpected $update operation for card-many attribute. Found:\n" + a)
            }

          case _: AttrOneOpt => throw ModelError(s"Can't $update optional values. Found:\n" + a)
          case _: AttrSetTac => throw ModelError("Can only lookup entity with card-one attribute value. Found:\n" + a)
          case _: AttrSetOpt => throw ModelError(s"Can't $update optional values. Found:\n" + a)
        }

      case Ref(_, _, "Tx", CardOne, _)  => throw ModelError("Updating tx meta data not yet implemented...")

      case ref@Ref(_, _, _, CardOne, _) =>
        if (firstNs) {
          firstNs = false
          val tacitElements = updateModel.toList
          // First namespace already has a tacit id attribute
          updateModels = updateModels :+ ((_: Long) => tacitElements)

        } else if (prevNs.nonEmpty) {
          // Get id
          idsModel += AttrOneManLong(prevNs, "id")

          // Make update model once we get an id
          val ns            = prevNs
          val tacitElements = updateModel.toList
          updateModels = updateModels :+ ((id: Long) => AttrOneTacLong(ns, "id", Eq, Seq(id)) +: tacitElements)
        }

        idsModel += ref
        prevNs = ""
        updateModel.clear()

      case ref: Ref => throw ModelError(
        s"Can't $update attributes in card-many referenced namespace `${ref.refAttr.capitalize}`"
      )

      case other => idsModel += other
    }

    // Add id to last ref ns
    if (prevNs.nonEmpty) {
      // Get id
      idsModel += AttrOneManLong(prevNs, "id")

      // Make update model once we get an id
      val id2updateModel = (id: Long) => AttrOneTacLong(prevNs, "id", Eq, Seq(id)) +: updateModel.toList
      updateModels = updateModels :+ id2updateModel
    }

    (idsModel.toList, updateModels)
  }
}
