package molecule.db.common.transaction

import molecule.core.dataModel.*
import molecule.core.error.{ExecutionError, ModelError, MoleculeError}
import molecule.core.util.BaseHelpers
import molecule.db.common.javaSql.ResultSetInterface as RS
import molecule.db.common.marshalling.ConnProxy
import molecule.db.common.spi.SpiHelpers
import molecule.db.common.validation.TxModelValidation

trait SqlUpdateSetValidator extends SpiHelpers with BaseHelpers {

  protected def validateUpdateSet_array(
    proxy: ConnProxy,
    elements: List[Element],
    query2resultSet: String => RS
  ): Map[String, Seq[String]] = {
    val curSetValues: Attr => Set[Any] = (a: Attr) => try {
      val ent   = a.ent
      val attr  = a.attr
      val query = a.ref.fold(
        s"""SELECT DISTINCT
           |  ARRAY_AGG($ent.$attr)
           |FROM $ent
           |WHERE
           |  $ent.$attr IS NOT NULL
           |HAVING COUNT(*) > 0;""".stripMargin

      ) { ref =>
        val joinTable = ss(ent, attr, ref)
        val ref_id    = ss(ref, "id")
        val eid       = ss(ent, "id")
        s"""SELECT DISTINCT
           |  ARRAY_AGG($joinTable.$ref_id)
           |FROM $ent
           |INNER JOIN $joinTable ON $ent.id = $joinTable.$eid
           |GROUP BY $ent.id;""".stripMargin
      }

      nestedArray2coalescedSet(a, query2resultSet(query), a.ref.isEmpty)
    } catch {
      case e: MoleculeError => throw e
      case t: Throwable     =>
        t.printStackTrace()
        throw ExecutionError(
          s"Unexpected error trying to find current values of mandatory attribute ${a.name}")
    }
    TxModelValidation(proxy.metaDb, "update", Some(curSetValues)).validate(elements)
  }

  protected def validateUpdateSet_json(
    proxy: ConnProxy,
    elements: List[Element],
    query2resultSet: String => RS
  ): Map[String, Seq[String]] = {
    val curSetValues: Attr => Set[Any] = (a: Attr) => try {
      val ent   = a.ent
      val attr  = a.attr
      val tpe   = dbType(a)
      val query = a.ref.fold(
        s"""SELECT
           |  JSON_ARRAYAGG(t_1.vs)
           |FROM MandatoryAttr,
           |  JSON_TABLE($ent.$attr, '$$[*]' columns(vs $tpe path '$$')) t_1
           |""".stripMargin
      ) { ref =>
        val joinTable = ss(ent, attr, ref)
        val ref_id    = ss(ref, "id")
        val eid       = ss(ent, "id")
        s"""SELECT DISTINCT
           |  JSON_ARRAYAGG($joinTable.$ref_id)
           |FROM $ent
           |INNER JOIN $joinTable ON $ent.id = $joinTable.$eid
           |GROUP BY $ent.id;""".stripMargin
      }
      jsonArray2coalescedSet(a, query2resultSet(query))
    } catch {
      case e: MoleculeError => throw e
      case t: Throwable     =>
        t.printStackTrace()
        throw ExecutionError(
          s"Unexpected error trying to find current values of mandatory attribute ${a.name}")
    }
    TxModelValidation(proxy.metaDb, "update", Some(curSetValues)).validate(elements)
  }

  protected def validateUpdateSet_sqlite(
    proxy: ConnProxy,
    elements: List[Element],
    query2resultSet: String => RS
  ): Map[String, Seq[String]] = {
    val curSetValues: Attr => Set[Any] = (a: Attr) => try {
      val ent      = a.ent
      val attr     = a.attr
      val ent_attr = ent + "_" + attr
      val query    = a.ref.fold(
        s"""SELECT DISTINCT
           |  JSON_GROUP_ARRAY(_$ent_attr.VALUE) AS $ent_attr
           |FROM $ent
           |  INNER JOIN JSON_EACH($ent.$attr) AS _$ent_attr
           |WHERE
           |  $ent.$attr IS NOT NULL
           |HAVING COUNT(*) > 0
           |""".stripMargin
      ) { ref =>
        val joinTable = ss(ent, attr, ref)
        val ref_id    = ss(ref, "id")
        val eid       = ss(ent, "id")
        s"""SELECT DISTINCT
           |  json_group_array($joinTable.$ref_id)
           |FROM $ent
           |  INNER JOIN $joinTable ON $ent.id = $joinTable.$eid
           |GROUP BY $ent.id""".stripMargin
      }
      jsonArray2coalescedSet(a, query2resultSet(query))
    } catch {
      case e: MoleculeError => throw e
      case t: Throwable     =>
        t.printStackTrace()
        throw ExecutionError(
          s"Unexpected error trying to find current values of mandatory attribute ${a.name}")
    }
    TxModelValidation(proxy.metaDb, "update", Some(curSetValues)).validate(elements)
  }

  // Mysql data types
  private def dbType(a: Attr): String = a match {
    case _: AttrSetManID             => "BIGINT"
    case _: AttrSetManString         => "LONGTEXT"
    case _: AttrSetManInt            => "INT"
    case _: AttrSetManLong           => "BIGINT"
    case _: AttrSetManFloat          => "REAL"
    case _: AttrSetManDouble         => "DOUBLE"
    case _: AttrSetManBoolean        => "TINYINT(1)"
    case _: AttrSetManBigInt         => "DECIMAL(65, 0)"
    case _: AttrSetManBigDecimal     => "DECIMAL(65, 30)"
    case _: AttrSetManDate           => "BIGINT"
    case _: AttrSetManDuration       => "TINYTEXT"
    case _: AttrSetManInstant        => "TINYTEXT"
    case _: AttrSetManLocalDate      => "TINYTEXT"
    case _: AttrSetManLocalTime      => "TINYTEXT"
    case _: AttrSetManLocalDateTime  => "TINYTEXT"
    case _: AttrSetManOffsetTime     => "TINYTEXT"
    case _: AttrSetManOffsetDateTime => "TINYTEXT"
    case _: AttrSetManZonedDateTime  => "TINYTEXT"
    case _: AttrSetManUUID           => "TINYTEXT"
    case _: AttrSetManURI            => "TEXT"
    case _: AttrSetManByte           => "TINYINT"
    case _: AttrSetManShort          => "SMALLINT"
    case _: AttrSetManChar           => "CHAR"
    case a                           => throw ModelError(s"Unexpected attribute (${a.name}).")
  }
}
