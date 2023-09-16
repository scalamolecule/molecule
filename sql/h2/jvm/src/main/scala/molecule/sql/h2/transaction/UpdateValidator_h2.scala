package molecule.sql.h2.transaction

import molecule.base.error.{ExecutionError, ModelError, MoleculeError}
import molecule.boilerplate.ast.Model._
import molecule.core.marshalling.ConnProxy
import molecule.core.validation.ModelValidation
import molecule.sql.core.javaSql.{ResultSetInterface => Row}
import molecule.sql.core.spi.SpiHelpers

trait UpdateValidator_h2 extends SpiHelpers {

  def validateUpdate(
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
        val joinTable = s"${ns}_${attr}_$refNs"
        s"""SELECT DISTINCT
           |  ARRAY_AGG($joinTable.${refNs}_id)
           |FROM $ns
           |INNER JOIN $joinTable ON $ns.id = $joinTable.${ns}_id
           |GROUP BY $ns.id;""".stripMargin
      }
      nestedArray2coalescedSet(a, query2resultSet(query))
    } catch {
      case e: MoleculeError => throw e
      case t: Throwable     =>
        t.printStackTrace()
        throw ExecutionError(
          s"Unexpected error trying to find current values of mandatory attribute ${a.name}")
    }
    ModelValidation(proxy.nsMap, proxy.attrMap, "update", Some(curSetValues)).validate(elements)
  }
}
