package molecule.core.validation.insert

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.spi.Conn
import molecule.core.validation.TxModelValidation

object InsertValidation extends InsertValidationExtraction with InsertValidationResolvers_ {

  def validate(
    conn: Conn,
    elements: List[Element],
    tpls: Seq[Product]
  ): Seq[(Int, Seq[InsertError])] = {
    val (nsMap, attrMap)               = (conn.proxy.nsMap, conn.proxy.attrMap)

    // Basic model validation
    TxModelValidation(nsMap, attrMap, "insert").validate(elements)

    // Validator to validate each row
    val validate = getInsertValidator(nsMap, elements)

    val rowErrors = tpls.zipWithIndex.flatMap { case (tpl, rowIndex) =>
      val rowErrors = validate(tpl)
      if (rowErrors.isEmpty) None else Some((rowIndex, rowErrors))
    }

    rowErrors
  }
}
