// GENERATED CODE ********************************
package molecule.core.transaction

import java.net.URI
import java.util.{Date, UUID}
import molecule.boilerplate.ast.Model._
import molecule.core.validation.insert.InsertValueResolvers_

trait InsertValidators_ extends InsertValueResolvers_ {
  
  protected def validatorString(
    optValidator: Option[ValidateString],
    a: Attr,
    curElements: List[Element]
  ): Option[Product => String => Seq[String]] = {
    optValidator.fold(
      Option.empty[Product => String => Seq[String]]
    ) { validator =>
      if (a.valueAttrs.isEmpty) {
        Some((_: Product) => (v: String) => validator.validate(v))
      } else {
        val tpl2values = tpl2valueResolver(a, curElements)
        Some(
          (tpl: Product) => {
            val values = tpl2values(tpl)
            (v: String) =>
              validator.withValues(values).validate(v)
          }
        )
      }
    }
  }

  protected def validatorInt(
    optValidator: Option[ValidateInt],
    a: Attr,
    curElements: List[Element]
  ): Option[Product => Int => Seq[String]] = {
    optValidator.fold(
      Option.empty[Product => Int => Seq[String]]
    ) { validator =>
      if (a.valueAttrs.isEmpty) {
        Some((_: Product) => (v: Int) => validator.validate(v))
      } else {
        val tpl2values = tpl2valueResolver(a, curElements)
        Some(
          (tpl: Product) => {
            val values = tpl2values(tpl)
            (v: Int) =>
              validator.withValues(values).validate(v)
          }
        )
      }
    }
  }

  protected def validatorLong(
    optValidator: Option[ValidateLong],
    a: Attr,
    curElements: List[Element]
  ): Option[Product => Long => Seq[String]] = {
    optValidator.fold(
      Option.empty[Product => Long => Seq[String]]
    ) { validator =>
      if (a.valueAttrs.isEmpty) {
        Some((_: Product) => (v: Long) => validator.validate(v))
      } else {
        val tpl2values = tpl2valueResolver(a, curElements)
        Some(
          (tpl: Product) => {
            val values = tpl2values(tpl)
            (v: Long) =>
              validator.withValues(values).validate(v)
          }
        )
      }
    }
  }

  protected def validatorFloat(
    optValidator: Option[ValidateFloat],
    a: Attr,
    curElements: List[Element]
  ): Option[Product => Float => Seq[String]] = {
    optValidator.fold(
      Option.empty[Product => Float => Seq[String]]
    ) { validator =>
      if (a.valueAttrs.isEmpty) {
        Some((_: Product) => (v: Float) => validator.validate(v))
      } else {
        val tpl2values = tpl2valueResolver(a, curElements)
        Some(
          (tpl: Product) => {
            val values = tpl2values(tpl)
            (v: Float) =>
              validator.withValues(values).validate(v)
          }
        )
      }
    }
  }

  protected def validatorDouble(
    optValidator: Option[ValidateDouble],
    a: Attr,
    curElements: List[Element]
  ): Option[Product => Double => Seq[String]] = {
    optValidator.fold(
      Option.empty[Product => Double => Seq[String]]
    ) { validator =>
      if (a.valueAttrs.isEmpty) {
        Some((_: Product) => (v: Double) => validator.validate(v))
      } else {
        val tpl2values = tpl2valueResolver(a, curElements)
        Some(
          (tpl: Product) => {
            val values = tpl2values(tpl)
            (v: Double) =>
              validator.withValues(values).validate(v)
          }
        )
      }
    }
  }

  protected def validatorBoolean(
    optValidator: Option[ValidateBoolean],
    a: Attr,
    curElements: List[Element]
  ): Option[Product => Boolean => Seq[String]] = {
    optValidator.fold(
      Option.empty[Product => Boolean => Seq[String]]
    ) { validator =>
      if (a.valueAttrs.isEmpty) {
        Some((_: Product) => (v: Boolean) => validator.validate(v))
      } else {
        val tpl2values = tpl2valueResolver(a, curElements)
        Some(
          (tpl: Product) => {
            val values = tpl2values(tpl)
            (v: Boolean) =>
              validator.withValues(values).validate(v)
          }
        )
      }
    }
  }

  protected def validatorBigInt(
    optValidator: Option[ValidateBigInt],
    a: Attr,
    curElements: List[Element]
  ): Option[Product => BigInt => Seq[String]] = {
    optValidator.fold(
      Option.empty[Product => BigInt => Seq[String]]
    ) { validator =>
      if (a.valueAttrs.isEmpty) {
        Some((_: Product) => (v: BigInt) => validator.validate(v))
      } else {
        val tpl2values = tpl2valueResolver(a, curElements)
        Some(
          (tpl: Product) => {
            val values = tpl2values(tpl)
            (v: BigInt) =>
              validator.withValues(values).validate(v)
          }
        )
      }
    }
  }

  protected def validatorBigDecimal(
    optValidator: Option[ValidateBigDecimal],
    a: Attr,
    curElements: List[Element]
  ): Option[Product => BigDecimal => Seq[String]] = {
    optValidator.fold(
      Option.empty[Product => BigDecimal => Seq[String]]
    ) { validator =>
      if (a.valueAttrs.isEmpty) {
        Some((_: Product) => (v: BigDecimal) => validator.validate(v))
      } else {
        val tpl2values = tpl2valueResolver(a, curElements)
        Some(
          (tpl: Product) => {
            val values = tpl2values(tpl)
            (v: BigDecimal) =>
              validator.withValues(values).validate(v)
          }
        )
      }
    }
  }

  protected def validatorDate(
    optValidator: Option[ValidateDate],
    a: Attr,
    curElements: List[Element]
  ): Option[Product => Date => Seq[String]] = {
    optValidator.fold(
      Option.empty[Product => Date => Seq[String]]
    ) { validator =>
      if (a.valueAttrs.isEmpty) {
        Some((_: Product) => (v: Date) => validator.validate(v))
      } else {
        val tpl2values = tpl2valueResolver(a, curElements)
        Some(
          (tpl: Product) => {
            val values = tpl2values(tpl)
            (v: Date) =>
              validator.withValues(values).validate(v)
          }
        )
      }
    }
  }

  protected def validatorUUID(
    optValidator: Option[ValidateUUID],
    a: Attr,
    curElements: List[Element]
  ): Option[Product => UUID => Seq[String]] = {
    optValidator.fold(
      Option.empty[Product => UUID => Seq[String]]
    ) { validator =>
      if (a.valueAttrs.isEmpty) {
        Some((_: Product) => (v: UUID) => validator.validate(v))
      } else {
        val tpl2values = tpl2valueResolver(a, curElements)
        Some(
          (tpl: Product) => {
            val values = tpl2values(tpl)
            (v: UUID) =>
              validator.withValues(values).validate(v)
          }
        )
      }
    }
  }

  protected def validatorURI(
    optValidator: Option[ValidateURI],
    a: Attr,
    curElements: List[Element]
  ): Option[Product => URI => Seq[String]] = {
    optValidator.fold(
      Option.empty[Product => URI => Seq[String]]
    ) { validator =>
      if (a.valueAttrs.isEmpty) {
        Some((_: Product) => (v: URI) => validator.validate(v))
      } else {
        val tpl2values = tpl2valueResolver(a, curElements)
        Some(
          (tpl: Product) => {
            val values = tpl2values(tpl)
            (v: URI) =>
              validator.withValues(values).validate(v)
          }
        )
      }
    }
  }

  protected def validatorByte(
    optValidator: Option[ValidateByte],
    a: Attr,
    curElements: List[Element]
  ): Option[Product => Byte => Seq[String]] = {
    optValidator.fold(
      Option.empty[Product => Byte => Seq[String]]
    ) { validator =>
      if (a.valueAttrs.isEmpty) {
        Some((_: Product) => (v: Byte) => validator.validate(v))
      } else {
        val tpl2values = tpl2valueResolver(a, curElements)
        Some(
          (tpl: Product) => {
            val values = tpl2values(tpl)
            (v: Byte) =>
              validator.withValues(values).validate(v)
          }
        )
      }
    }
  }

  protected def validatorShort(
    optValidator: Option[ValidateShort],
    a: Attr,
    curElements: List[Element]
  ): Option[Product => Short => Seq[String]] = {
    optValidator.fold(
      Option.empty[Product => Short => Seq[String]]
    ) { validator =>
      if (a.valueAttrs.isEmpty) {
        Some((_: Product) => (v: Short) => validator.validate(v))
      } else {
        val tpl2values = tpl2valueResolver(a, curElements)
        Some(
          (tpl: Product) => {
            val values = tpl2values(tpl)
            (v: Short) =>
              validator.withValues(values).validate(v)
          }
        )
      }
    }
  }

  protected def validatorChar(
    optValidator: Option[ValidateChar],
    a: Attr,
    curElements: List[Element]
  ): Option[Product => Char => Seq[String]] = {
    optValidator.fold(
      Option.empty[Product => Char => Seq[String]]
    ) { validator =>
      if (a.valueAttrs.isEmpty) {
        Some((_: Product) => (v: Char) => validator.validate(v))
      } else {
        val tpl2values = tpl2valueResolver(a, curElements)
        Some(
          (tpl: Product) => {
            val values = tpl2values(tpl)
            (v: Char) =>
              validator.withValues(values).validate(v)
          }
        )
      }
    }
  }
}