package molecule.core.validation.insert

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.api.Connection
import molecule.core.validation.ModelValidation

object InsertValidation extends InsertValidationExtraction with InsertValidationResolvers_ {

  def validate(
    conn: Connection,
    elements: List[Element],
    tpls: Seq[Product]
  ): Seq[(Int, Seq[InsertError])] = {
    val (nsMap, attrMap)               = (conn.proxy.nsMap, conn.proxy.attrMap)
    val (mainElements, txMetaElements) = splitElements(elements)

    // Basic model validation
    ModelValidation(nsMap, attrMap, "insert").validate(mainElements)

    // Validator to validate each row
    val validate = getInsertValidator(nsMap, mainElements)

    val rowErrors = tpls.zipWithIndex.flatMap { case (tpl, rowIndex) =>
      val rowErrors = validate(tpl)
      if (rowErrors.isEmpty) None else Some((rowIndex, rowErrors))
    }

    val txMetaModelErrors = ModelValidation(nsMap, attrMap, "save").validate(txMetaElements).toSeq
    val txMetaDataErrors  = if (txMetaModelErrors.isEmpty) Nil else {
      val txMetaInsertErrors = txMetaModelErrors.zipWithIndex.map {
        case ((fullAttr, errors), i) => InsertError(0, i, fullAttr, errors, Nil)
      }
      // Append a tx meta data errors row (with meta row index -1)
      Seq((-1, txMetaInsertErrors))
    }

    rowErrors ++ txMetaDataErrors
  }
}
