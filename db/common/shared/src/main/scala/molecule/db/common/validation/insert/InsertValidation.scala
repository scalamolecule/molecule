package molecule.db.common.validation.insert

import molecule.core.dataModel.Element
import molecule.core.error.InsertError
import molecule.db.common.spi.Conn
import molecule.db.common.validation.TxModelValidation

object InsertValidation extends InsertValidationExtraction with InsertValidationResolvers_ {

  def validate(
    conn: Conn,
    elements: List[Element],
    tpls: Seq[Product]
  ): Seq[(Int, Seq[InsertError])] = {
    val metaDb = conn.proxy.metaDb

    // Basic model validation
    TxModelValidation(metaDb, "insert").validate(elements)

    // Validator to validate each row
    val validate = getInsertValidator(metaDb, elements)

    val rowErrors = tpls.zipWithIndex.flatMap { case (tpl, rowIndex) =>
      val rowErrors = validate(tpl)
      if (rowErrors.isEmpty) None else Some((rowIndex, rowErrors))
    }

    rowErrors
  }
}
