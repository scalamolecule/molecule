package molecule.sql.core.transaction

import molecule.base.error.{ExecutionError, ModelError, MoleculeError}
import molecule.boilerplate.ast.Model._
import molecule.core.marshalling.ConnProxy
import molecule.core.validation.TxModelValidation
import molecule.sql.core.javaSql.{ResultSetInterface => Row}
import molecule.sql.core.spi.SpiHelpers

trait SqlUpdateSetValidator extends SpiHelpers {

  protected def validateUpdateSet(
    proxy: ConnProxy,
    elements: List[Element],
    isUpsert: Boolean,
    query2resultSet: String => Row
  ): Map[String, Seq[String]] = {
    if (isUpsert && isRefUpdate(elements))
      throw ModelError("Can't upsert referenced attributes. Please update instead.")

    val curSetValues: Attr => Set[Any] = (a: Attr) => try {
      val ns    = a.ns
      val attr  = a.attr
      val query = a.refNs.fold(
        s"""SELECT DISTINCT
           |  ARRAY_AGG($ns.$attr)
           |FROM $ns
           |WHERE
           |  $ns.$attr IS NOT NULL
           |HAVING COUNT(*) > 0;""".stripMargin

      ) { refNs =>
        val joinTable = ss(ns, attr, refNs)
        val refNs_id  = ss(refNs, "id")
        val ns_id     = ss(ns, "id")
        s"""SELECT DISTINCT
           |  ARRAY_AGG($joinTable.$refNs_id)
           |FROM $ns
           |INNER JOIN $joinTable ON $ns.id = $joinTable.$ns_id
           |GROUP BY $ns.id;""".stripMargin
      }

      nestedArray2coalescedSet(a, query2resultSet(query), a.refNs.isEmpty)
    } catch {
      case e: MoleculeError => throw e
      case t: Throwable     =>
        t.printStackTrace()
        throw ExecutionError(
          s"Unexpected error trying to find current values of mandatory attribute ${a.name}")
    }
    TxModelValidation(proxy.nsMap, proxy.attrMap, "update", Some(curSetValues)).validate(elements)
  }

  protected def validateUpdateSet2(
    proxy: ConnProxy,
    elements: List[Element],
    isUpsert: Boolean,
    query2resultSet: String => Row
  ): Map[String, Seq[String]] = {
    if (isUpsert && isRefUpdate(elements))
      throw ModelError("Can't upsert referenced attributes. Please update instead.")

    //    val ids = elements.head.asInstanceOf[AttrOneTacLong].vs.mkString(", ")

    val curSetValues: Attr => Set[Any] = (a: Attr) => try {
      val ns    = a.ns
      val attr  = a.attr
      val tpe   = dbType(a)
      val query = a.refNs.fold(
        s"""SELECT
           |  JSON_ARRAYAGG(t_1.vs)
           |FROM MandatoryAttr,
           |  JSON_TABLE($ns.$attr, '$$[*]' columns(vs $tpe path '$$')) t_1
           |""".stripMargin
        //           |WHERE $ns.id in ($ids)
        //           |GROUP BY $ns.id
      ) { refNs =>
        val joinTable = ss(ns, attr, refNs)
        val refNs_id  = ss(refNs, "id")
        val ns_id     = ss(ns, "id")
        s"""SELECT DISTINCT
           |  JSON_ARRAYAGG($joinTable.$refNs_id)
           |FROM $ns
           |INNER JOIN $joinTable ON $ns.id = $joinTable.$ns_id
           |GROUP BY $ns.id;""".stripMargin
      }
      jsonArray2coalescedSet(a, query2resultSet(query))
    } catch {
      case e: MoleculeError => throw e
      case t: Throwable     =>
        t.printStackTrace()
        throw ExecutionError(
          s"Unexpected error trying to find current values of mandatory attribute ${a.name}")
    }
    TxModelValidation(proxy.nsMap, proxy.attrMap, "update", Some(curSetValues)).validate(elements)
  }

  // Mysql data types
  private def dbType(a: Attr): String = a match {
    case _: AttrSetManString     => "LONGTEXT"
    case _: AttrSetManInt        => "INT"
    case _: AttrSetManLong       => "BIGINT"
    case _: AttrSetManFloat      => "REAL"
    case _: AttrSetManDouble     => "DOUBLE"
    case _: AttrSetManBoolean    => "TINYINT(1)"
    case _: AttrSetManBigInt     => "DECIMAL(65, 0)"
    case _: AttrSetManBigDecimal => "DECIMAL(65, 30)"
    case _: AttrSetManDate       => "BIGINT"
    case _: AttrSetManUUID       => "TINYTEXT"
    case _: AttrSetManURI        => "TEXT"
    case _: AttrSetManByte       => "TINYINT"
    case _: AttrSetManShort      => "SMALLINT"
    case _: AttrSetManChar       => "CHAR"
    case a                       =>
      throw ModelError(s"Unexpected attribute. Found:\n" + a)
  }
}
