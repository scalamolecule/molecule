// GENERATED CODE ********************************
package molecule.core.validation.insert

import molecule.base.ast.SchemaAST._
import molecule.base.error.InsertError
import molecule.boilerplate.ast.Model._

trait InsertValidationResolvers_ {

  def getValidators(
    nsMap: Map[String, MetaNs],
    elements: List[Element],
    validators: List[Product => Seq[InsertError]],
    outerTpl: Int,
    tplIndex: Int,
    prevRefs: List[String]
  ): List[Product => Seq[InsertError]]

  def getInsertValidator(
    nsMap: Map[String, MetaNs],
    elements: List[Element],
    outerTpl: Int = 0
  ): Product => Seq[InsertError] = {
    val validators: List[Product => Seq[InsertError]] =
      getValidators(nsMap, elements, Nil, outerTpl, 0, Nil)

    validators.length match {
      case 1  => validate1(validators)
      case 2  => validate2(validators)
      case 3  => validate3(validators)
      case 4  => validate4(validators)
      case 5  => validate5(validators)
      case 6  => validate6(validators)
      case 7  => validate7(validators)
      case 8  => validate8(validators)
      case 9  => validate9(validators)
      case 10 => validate10(validators)
      case 11 => validate11(validators)
      case 12 => validate12(validators)
      case 13 => validate13(validators)
      case 14 => validate14(validators)
      case 15 => validate15(validators)
      case 16 => validate16(validators)
      case 17 => validate17(validators)
      case 18 => validate18(validators)
      case 19 => validate19(validators)
      case 20 => validate20(validators)
      case 21 => validate21(validators)
      case 22 => validate22(validators)
    }
  }

  final private def validate1(
    validators: List[Product => Seq[InsertError]]
  ): Product => Seq[InsertError] = {
    val List(v1) = validators
    (tpl: Product) =>
      Seq(
        v1(tpl)
      ).flatten
  }

  final private def validate2(
    validators: List[Product => Seq[InsertError]]
  ): Product => Seq[InsertError] = {
    val List(v1, v2) = validators
    (tpl: Product) =>
      Seq(
        v1(tpl),
        v2(tpl)
      ).flatten
  }

  final private def validate3(
    validators: List[Product => Seq[InsertError]]
  ): Product => Seq[InsertError] = {
    val List(v1, v2, v3) = validators
    (tpl: Product) =>
      Seq(
        v1(tpl),
        v2(tpl),
        v3(tpl)
      ).flatten
  }

  final private def validate4(
    validators: List[Product => Seq[InsertError]]
  ): Product => Seq[InsertError] = {
    val List(v1, v2, v3, v4) = validators
    (tpl: Product) =>
      Seq(
        v1(tpl),
        v2(tpl),
        v3(tpl),
        v4(tpl)
      ).flatten
  }

  final private def validate5(
    validators: List[Product => Seq[InsertError]]
  ): Product => Seq[InsertError] = {
    val List(v1, v2, v3, v4, v5) = validators
    (tpl: Product) =>
      Seq(
        v1(tpl),
        v2(tpl),
        v3(tpl),
        v4(tpl),
        v5(tpl)
      ).flatten
  }

  final private def validate6(
    validators: List[Product => Seq[InsertError]]
  ): Product => Seq[InsertError] = {
    val List(v1, v2, v3, v4, v5, v6) = validators
    (tpl: Product) =>
      Seq(
        v1(tpl),
        v2(tpl),
        v3(tpl),
        v4(tpl),
        v5(tpl),
        v6(tpl)
      ).flatten
  }

  final private def validate7(
    validators: List[Product => Seq[InsertError]]
  ): Product => Seq[InsertError] = {
    val List(v1, v2, v3, v4, v5, v6, v7) = validators
    (tpl: Product) =>
      Seq(
        v1(tpl),
        v2(tpl),
        v3(tpl),
        v4(tpl),
        v5(tpl),
        v6(tpl),
        v7(tpl)
      ).flatten
  }

  final private def validate8(
    validators: List[Product => Seq[InsertError]]
  ): Product => Seq[InsertError] = {
    val List(v1, v2, v3, v4, v5, v6, v7, v8) = validators
    (tpl: Product) =>
      Seq(
        v1(tpl),
        v2(tpl),
        v3(tpl),
        v4(tpl),
        v5(tpl),
        v6(tpl),
        v7(tpl),
        v8(tpl)
      ).flatten
  }

  final private def validate9(
    validators: List[Product => Seq[InsertError]]
  ): Product => Seq[InsertError] = {
    val List(v1, v2, v3, v4, v5, v6, v7, v8, v9) = validators
    (tpl: Product) =>
      Seq(
        v1(tpl),
        v2(tpl),
        v3(tpl),
        v4(tpl),
        v5(tpl),
        v6(tpl),
        v7(tpl),
        v8(tpl),
        v9(tpl)
      ).flatten
  }

  final private def validate10(
    validators: List[Product => Seq[InsertError]]
  ): Product => Seq[InsertError] = {
    val List(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10) = validators
    (tpl: Product) =>
      Seq(
        v1(tpl),
        v2(tpl),
        v3(tpl),
        v4(tpl),
        v5(tpl),
        v6(tpl),
        v7(tpl),
        v8(tpl),
        v9(tpl),
        v10(tpl)
      ).flatten
  }

  final private def validate11(
    validators: List[Product => Seq[InsertError]]
  ): Product => Seq[InsertError] = {
    val List(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11) = validators
    (tpl: Product) =>
      Seq(
        v1(tpl),
        v2(tpl),
        v3(tpl),
        v4(tpl),
        v5(tpl),
        v6(tpl),
        v7(tpl),
        v8(tpl),
        v9(tpl),
        v10(tpl),
        v11(tpl)
      ).flatten
  }

  final private def validate12(
    validators: List[Product => Seq[InsertError]]
  ): Product => Seq[InsertError] = {
    val List(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12) = validators
    (tpl: Product) =>
      Seq(
        v1(tpl),
        v2(tpl),
        v3(tpl),
        v4(tpl),
        v5(tpl),
        v6(tpl),
        v7(tpl),
        v8(tpl),
        v9(tpl),
        v10(tpl),
        v11(tpl),
        v12(tpl)
      ).flatten
  }

  final private def validate13(
    validators: List[Product => Seq[InsertError]]
  ): Product => Seq[InsertError] = {
    val List(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13) = validators
    (tpl: Product) =>
      Seq(
        v1(tpl),
        v2(tpl),
        v3(tpl),
        v4(tpl),
        v5(tpl),
        v6(tpl),
        v7(tpl),
        v8(tpl),
        v9(tpl),
        v10(tpl),
        v11(tpl),
        v12(tpl),
        v13(tpl)
      ).flatten
  }

  final private def validate14(
    validators: List[Product => Seq[InsertError]]
  ): Product => Seq[InsertError] = {
    val List(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14) = validators
    (tpl: Product) =>
      Seq(
        v1(tpl),
        v2(tpl),
        v3(tpl),
        v4(tpl),
        v5(tpl),
        v6(tpl),
        v7(tpl),
        v8(tpl),
        v9(tpl),
        v10(tpl),
        v11(tpl),
        v12(tpl),
        v13(tpl),
        v14(tpl)
      ).flatten
  }

  final private def validate15(
    validators: List[Product => Seq[InsertError]]
  ): Product => Seq[InsertError] = {
    val List(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15) = validators
    (tpl: Product) =>
      Seq(
        v1(tpl),
        v2(tpl),
        v3(tpl),
        v4(tpl),
        v5(tpl),
        v6(tpl),
        v7(tpl),
        v8(tpl),
        v9(tpl),
        v10(tpl),
        v11(tpl),
        v12(tpl),
        v13(tpl),
        v14(tpl),
        v15(tpl)
      ).flatten
  }

  final private def validate16(
    validators: List[Product => Seq[InsertError]]
  ): Product => Seq[InsertError] = {
    val List(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16) = validators
    (tpl: Product) =>
      Seq(
        v1(tpl),
        v2(tpl),
        v3(tpl),
        v4(tpl),
        v5(tpl),
        v6(tpl),
        v7(tpl),
        v8(tpl),
        v9(tpl),
        v10(tpl),
        v11(tpl),
        v12(tpl),
        v13(tpl),
        v14(tpl),
        v15(tpl),
        v16(tpl)
      ).flatten
  }

  final private def validate17(
    validators: List[Product => Seq[InsertError]]
  ): Product => Seq[InsertError] = {
    val List(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17) = validators
    (tpl: Product) =>
      Seq(
        v1(tpl),
        v2(tpl),
        v3(tpl),
        v4(tpl),
        v5(tpl),
        v6(tpl),
        v7(tpl),
        v8(tpl),
        v9(tpl),
        v10(tpl),
        v11(tpl),
        v12(tpl),
        v13(tpl),
        v14(tpl),
        v15(tpl),
        v16(tpl),
        v17(tpl)
      ).flatten
  }

  final private def validate18(
    validators: List[Product => Seq[InsertError]]
  ): Product => Seq[InsertError] = {
    val List(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18) = validators
    (tpl: Product) =>
      Seq(
        v1(tpl),
        v2(tpl),
        v3(tpl),
        v4(tpl),
        v5(tpl),
        v6(tpl),
        v7(tpl),
        v8(tpl),
        v9(tpl),
        v10(tpl),
        v11(tpl),
        v12(tpl),
        v13(tpl),
        v14(tpl),
        v15(tpl),
        v16(tpl),
        v17(tpl),
        v18(tpl)
      ).flatten
  }

  final private def validate19(
    validators: List[Product => Seq[InsertError]]
  ): Product => Seq[InsertError] = {
    val List(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19) = validators
    (tpl: Product) =>
      Seq(
        v1(tpl),
        v2(tpl),
        v3(tpl),
        v4(tpl),
        v5(tpl),
        v6(tpl),
        v7(tpl),
        v8(tpl),
        v9(tpl),
        v10(tpl),
        v11(tpl),
        v12(tpl),
        v13(tpl),
        v14(tpl),
        v15(tpl),
        v16(tpl),
        v17(tpl),
        v18(tpl),
        v19(tpl)
      ).flatten
  }

  final private def validate20(
    validators: List[Product => Seq[InsertError]]
  ): Product => Seq[InsertError] = {
    val List(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20) = validators
    (tpl: Product) =>
      Seq(
        v1(tpl),
        v2(tpl),
        v3(tpl),
        v4(tpl),
        v5(tpl),
        v6(tpl),
        v7(tpl),
        v8(tpl),
        v9(tpl),
        v10(tpl),
        v11(tpl),
        v12(tpl),
        v13(tpl),
        v14(tpl),
        v15(tpl),
        v16(tpl),
        v17(tpl),
        v18(tpl),
        v19(tpl),
        v20(tpl)
      ).flatten
  }

  final private def validate21(
    validators: List[Product => Seq[InsertError]]
  ): Product => Seq[InsertError] = {
    val List(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, v21) = validators
    (tpl: Product) =>
      Seq(
        v1(tpl),
        v2(tpl),
        v3(tpl),
        v4(tpl),
        v5(tpl),
        v6(tpl),
        v7(tpl),
        v8(tpl),
        v9(tpl),
        v10(tpl),
        v11(tpl),
        v12(tpl),
        v13(tpl),
        v14(tpl),
        v15(tpl),
        v16(tpl),
        v17(tpl),
        v18(tpl),
        v19(tpl),
        v20(tpl),
        v21(tpl)
      ).flatten
  }

  final private def validate22(
    validators: List[Product => Seq[InsertError]]
  ): Product => Seq[InsertError] = {
    val List(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, v21, v22) = validators
    (tpl: Product) =>
      Seq(
        v1(tpl),
        v2(tpl),
        v3(tpl),
        v4(tpl),
        v5(tpl),
        v6(tpl),
        v7(tpl),
        v8(tpl),
        v9(tpl),
        v10(tpl),
        v11(tpl),
        v12(tpl),
        v13(tpl),
        v14(tpl),
        v15(tpl),
        v16(tpl),
        v17(tpl),
        v18(tpl),
        v19(tpl),
        v20(tpl),
        v21(tpl),
        v22(tpl)
      ).flatten
  }
}