package molecule.core.validation.insert

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.spi.Conn
import molecule.core.validation.ModelValidation

object InsertValidation extends InsertValidationExtraction with InsertValidationResolvers_ {

  def validate(
    conn: Conn,
    elements: List[Element],
    tpls: Seq[Product]
  ): Seq[(Int, Seq[InsertError])] = {
    val (nsMap, attrMap)               = (conn.proxy.nsMap, conn.proxy.attrMap)
    val (mainElements, txElements) = separateTxElements(elements)

    // Basic model validation
    ModelValidation(nsMap, attrMap, "insert").validate(mainElements)

    // Validator to validate each row
    val validate = getInsertValidator(nsMap, mainElements)

    val rowErrors = tpls.zipWithIndex.flatMap { case (tpl, rowIndex) =>
      val rowErrors = validate(tpl)
      if (rowErrors.isEmpty) None else Some((rowIndex, rowErrors))
    }

    val txModelErrors = ModelValidation(nsMap, attrMap, "insertTx").validate(txElements).toSeq
    val txDataErrors  = if (txModelErrors.isEmpty) Nil else {
      val txInsertErrors = txModelErrors.zipWithIndex.map {
        case ((fullAttr, errors), i) => InsertError(0, i, fullAttr, errors, Nil)
      }
      // Append a tx data errors row (with meta row index -1)
      Seq((-1, txInsertErrors))
    }

    rowErrors ++ txDataErrors
  }
}
