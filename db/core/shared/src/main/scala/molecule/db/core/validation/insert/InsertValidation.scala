package molecule.db.core.validation.insert

import molecule.base.error.InsertError
import molecule.core.dataModel.Element
import molecule.db.core.spi.Conn
import molecule.db.core.validation.TxModelValidation

object InsertValidation extends InsertValidationExtraction with InsertValidationResolvers_ {

  def validate(
    conn: Conn,
    elements: List[Element],
    tpls: Seq[Product]
  ): Seq[(Int, Seq[InsertError])] = {
    val (entityMap, attrMap) = (conn.proxy.entityMap, conn.proxy.attrMap)

    // Basic model validation
    TxModelValidation(entityMap, attrMap, "insert").validate(elements)

    // Validator to validate each row
    val validate = getInsertValidator(entityMap, elements)

    val rowErrors = tpls.zipWithIndex.flatMap { case (tpl, rowIndex) =>
      val rowErrors = validate(tpl)
      if (rowErrors.isEmpty) None else Some((rowIndex, rowErrors))
    }

    rowErrors
  }
}
