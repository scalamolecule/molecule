package molecule.core.marshalling

import java.io.FileNotFoundException
import java.net.URI
import java.time._
import java.util.Date
import boopickle.CompositePickler
import boopickle.Default._
import molecule.base.ast._
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging


object Boopicklers extends MoleculeLogging {

  implicit val pickleDate          : Pickler[Date]           = transformPickler((t: Long) => new java.util.Date(t))(_.getTime)
  implicit val pickleDuration      : Pickler[Duration]       = transformPickler((t: String) => Duration.parse(t))(_.toString)
  implicit val pickleInstant       : Pickler[Instant]        = transformPickler((t: String) => Instant.parse(t))(_.toString)
  implicit val pickleLocalDate     : Pickler[LocalDate]      = transformPickler((t: String) => LocalDate.parse(t))(_.toString)
  implicit val pickleLocalTime     : Pickler[LocalTime]      = transformPickler((t: String) => LocalTime.parse(t))(_.toString)
  implicit val pickleLocalDateTime : Pickler[LocalDateTime]  = transformPickler((t: String) => LocalDateTime.parse(t))(_.toString)
  implicit val pickleOffsetTime    : Pickler[OffsetTime]     = transformPickler((t: String) => OffsetTime.parse(t))(_.toString)
  implicit val pickleOffsetDateTime: Pickler[OffsetDateTime] = transformPickler((t: String) => OffsetDateTime.parse(t))(_.toString)
  implicit val pickleZonedDateTime : Pickler[ZonedDateTime]  = transformPickler((t: String) => ZonedDateTime.parse(t))(_.toString)
  implicit val pickleURI           : Pickler[URI]            = transformPickler((t: String) => new URI(t))(_.toString)

  implicit val pickleCard: CompositePickler[Card] = compositePickler[Card]
  pickleCard.addConcreteType[CardOne.type]
  pickleCard.addConcreteType[CardSet.type]

  implicit val pickleMetaAttr  : CompositePickler[MetaAttr]   = compositePickler[MetaAttr]
  implicit val pickleMetaNs    : CompositePickler[MetaNs]     = compositePickler[MetaNs]
  implicit val pickleMetaPart  : CompositePickler[MetaPart]   = compositePickler[MetaPart]
  implicit val pickleMetaSchema: CompositePickler[MetaSchema] = compositePickler[MetaSchema]

  implicit val pickleMetaModel: CompositePickler[MetaModel] = compositePickler[MetaModel]
  pickleMetaModel.addConcreteType[MetaAttr]
  pickleMetaModel.addConcreteType[MetaNs]
  pickleMetaModel.addConcreteType[MetaPart]
  pickleMetaModel.addConcreteType[MetaSchema]

  implicit val pickleOp: CompositePickler[Op] = compositePickler[Op]
  pickleOp.addConcreteType[NoValue.type]
  pickleOp.addConcreteType[V.type]
  pickleOp.addConcreteType[Eq.type]
  pickleOp.addConcreteType[Neq.type]
  pickleOp.addConcreteType[Lt.type]
  pickleOp.addConcreteType[Le.type]
  pickleOp.addConcreteType[Gt.type]
  pickleOp.addConcreteType[Ge.type]
  pickleOp.addConcreteType[StartsWith.type]
  pickleOp.addConcreteType[EndsWith.type]
  pickleOp.addConcreteType[Contains.type]
  pickleOp.addConcreteType[Matches.type]
  pickleOp.addConcreteType[Remainder.type]
  pickleOp.addConcreteType[Even.type]
  pickleOp.addConcreteType[Odd.type]
  pickleOp.addConcreteType[Has.type]
  pickleOp.addConcreteType[HasNo.type]
  pickleOp.addConcreteType[GetV.type]
  pickleOp.addConcreteType[Add.type]
  pickleOp.addConcreteType[Remove.type]
  pickleOp.addConcreteType[Fn]

  implicit val pickleValidator: CompositePickler[Validator] = compositePickler[Validator]
  pickleValidator.addConcreteType[ValidateString]
  pickleValidator.addConcreteType[ValidateInt]
  pickleValidator.addConcreteType[ValidateLong]
  pickleValidator.addConcreteType[ValidateFloat]
  pickleValidator.addConcreteType[ValidateDouble]
  pickleValidator.addConcreteType[ValidateBoolean]
  pickleValidator.addConcreteType[ValidateBigInt]
  pickleValidator.addConcreteType[ValidateBigDecimal]
  pickleValidator.addConcreteType[ValidateDate]
  pickleValidator.addConcreteType[ValidateDuration]
  pickleValidator.addConcreteType[ValidateInstant]
  pickleValidator.addConcreteType[ValidateLocalDate]
  pickleValidator.addConcreteType[ValidateLocalTime]
  pickleValidator.addConcreteType[ValidateLocalDateTime]
  pickleValidator.addConcreteType[ValidateOffsetTime]
  pickleValidator.addConcreteType[ValidateOffsetDateTime]
  pickleValidator.addConcreteType[ValidateZonedDateTime]
  pickleValidator.addConcreteType[ValidateUUID]
  pickleValidator.addConcreteType[ValidateURI]
  pickleValidator.addConcreteType[ValidateByte]
  pickleValidator.addConcreteType[ValidateShort]
  pickleValidator.addConcreteType[ValidateChar]

  implicit val pickleValue: CompositePickler[Value] = compositePickler[Value]
  pickleValue.addConcreteType[OneString]
  pickleValue.addConcreteType[OneInt]
  pickleValue.addConcreteType[OneLong]
  pickleValue.addConcreteType[OneFloat]
  pickleValue.addConcreteType[OneDouble]
  pickleValue.addConcreteType[OneBoolean]
  pickleValue.addConcreteType[OneBigInt]
  pickleValue.addConcreteType[OneBigDecimal]
  pickleValue.addConcreteType[OneDate]
  pickleValue.addConcreteType[OneDuration]
  pickleValue.addConcreteType[OneInstant]
  pickleValue.addConcreteType[OneLocalDate]
  pickleValue.addConcreteType[OneLocalTime]
  pickleValue.addConcreteType[OneLocalDateTime]
  pickleValue.addConcreteType[OneOffsetTime]
  pickleValue.addConcreteType[OneOffsetDateTime]
  pickleValue.addConcreteType[OneZonedDateTime]
  pickleValue.addConcreteType[OneUUID]
  pickleValue.addConcreteType[OneURI]
  pickleValue.addConcreteType[OneByte]
  pickleValue.addConcreteType[OneShort]
  pickleValue.addConcreteType[OneChar]

  implicit val pickleAttr: CompositePickler[Attr] = compositePickler[Attr]
  pickleAttr.addConcreteType[AttrOneManID]
  pickleAttr.addConcreteType[AttrOneManString]
  pickleAttr.addConcreteType[AttrOneManInt]
  pickleAttr.addConcreteType[AttrOneManLong]
  pickleAttr.addConcreteType[AttrOneManDouble]
  pickleAttr.addConcreteType[AttrOneManBoolean]
  pickleAttr.addConcreteType[AttrOneManBigInt]
  pickleAttr.addConcreteType[AttrOneManBigDecimal]
  pickleAttr.addConcreteType[AttrOneManDate]
  pickleAttr.addConcreteType[AttrOneManDuration]
  pickleAttr.addConcreteType[AttrOneManInstant]
  pickleAttr.addConcreteType[AttrOneManLocalDate]
  pickleAttr.addConcreteType[AttrOneManLocalTime]
  pickleAttr.addConcreteType[AttrOneManLocalDateTime]
  pickleAttr.addConcreteType[AttrOneManOffsetTime]
  pickleAttr.addConcreteType[AttrOneManOffsetDateTime]
  pickleAttr.addConcreteType[AttrOneManZonedDateTime]
  pickleAttr.addConcreteType[AttrOneManUUID]
  pickleAttr.addConcreteType[AttrOneManURI]
  pickleAttr.addConcreteType[AttrOneManByte]
  pickleAttr.addConcreteType[AttrOneManShort]
  pickleAttr.addConcreteType[AttrOneManFloat]
  pickleAttr.addConcreteType[AttrOneManChar]

  pickleAttr.addConcreteType[AttrOneOptID]
  pickleAttr.addConcreteType[AttrOneOptString]
  pickleAttr.addConcreteType[AttrOneOptInt]
  pickleAttr.addConcreteType[AttrOneOptLong]
  pickleAttr.addConcreteType[AttrOneOptDouble]
  pickleAttr.addConcreteType[AttrOneOptBoolean]
  pickleAttr.addConcreteType[AttrOneOptBigInt]
  pickleAttr.addConcreteType[AttrOneOptBigDecimal]
  pickleAttr.addConcreteType[AttrOneOptDate]
  pickleAttr.addConcreteType[AttrOneOptDuration]
  pickleAttr.addConcreteType[AttrOneOptInstant]
  pickleAttr.addConcreteType[AttrOneOptLocalDate]
  pickleAttr.addConcreteType[AttrOneOptLocalTime]
  pickleAttr.addConcreteType[AttrOneOptLocalDateTime]
  pickleAttr.addConcreteType[AttrOneOptOffsetTime]
  pickleAttr.addConcreteType[AttrOneOptOffsetDateTime]
  pickleAttr.addConcreteType[AttrOneOptZonedDateTime]
  pickleAttr.addConcreteType[AttrOneOptUUID]
  pickleAttr.addConcreteType[AttrOneOptURI]
  pickleAttr.addConcreteType[AttrOneOptByte]
  pickleAttr.addConcreteType[AttrOneOptShort]
  pickleAttr.addConcreteType[AttrOneOptFloat]
  pickleAttr.addConcreteType[AttrOneOptChar]

  pickleAttr.addConcreteType[AttrOneTacID]
  pickleAttr.addConcreteType[AttrOneTacString]
  pickleAttr.addConcreteType[AttrOneTacInt]
  pickleAttr.addConcreteType[AttrOneTacLong]
  pickleAttr.addConcreteType[AttrOneTacDouble]
  pickleAttr.addConcreteType[AttrOneTacBoolean]
  pickleAttr.addConcreteType[AttrOneTacBigInt]
  pickleAttr.addConcreteType[AttrOneTacBigDecimal]
  pickleAttr.addConcreteType[AttrOneTacDate]
  pickleAttr.addConcreteType[AttrOneTacDuration]
  pickleAttr.addConcreteType[AttrOneTacInstant]
  pickleAttr.addConcreteType[AttrOneTacLocalDate]
  pickleAttr.addConcreteType[AttrOneTacLocalTime]
  pickleAttr.addConcreteType[AttrOneTacLocalDateTime]
  pickleAttr.addConcreteType[AttrOneTacOffsetTime]
  pickleAttr.addConcreteType[AttrOneTacOffsetDateTime]
  pickleAttr.addConcreteType[AttrOneTacZonedDateTime]
  pickleAttr.addConcreteType[AttrOneTacUUID]
  pickleAttr.addConcreteType[AttrOneTacURI]
  pickleAttr.addConcreteType[AttrOneTacByte]
  pickleAttr.addConcreteType[AttrOneTacShort]
  pickleAttr.addConcreteType[AttrOneTacFloat]
  pickleAttr.addConcreteType[AttrOneTacChar]

  pickleAttr.addConcreteType[AttrSetManID]
  pickleAttr.addConcreteType[AttrSetManString]
  pickleAttr.addConcreteType[AttrSetManInt]
  pickleAttr.addConcreteType[AttrSetManLong]
  pickleAttr.addConcreteType[AttrSetManDouble]
  pickleAttr.addConcreteType[AttrSetManBoolean]
  pickleAttr.addConcreteType[AttrSetManBigInt]
  pickleAttr.addConcreteType[AttrSetManBigDecimal]
  pickleAttr.addConcreteType[AttrSetManDate]
  pickleAttr.addConcreteType[AttrSetManDuration]
  pickleAttr.addConcreteType[AttrSetManInstant]
  pickleAttr.addConcreteType[AttrSetManLocalDate]
  pickleAttr.addConcreteType[AttrSetManLocalTime]
  pickleAttr.addConcreteType[AttrSetManLocalDateTime]
  pickleAttr.addConcreteType[AttrSetManOffsetTime]
  pickleAttr.addConcreteType[AttrSetManOffsetDateTime]
  pickleAttr.addConcreteType[AttrSetManZonedDateTime]
  pickleAttr.addConcreteType[AttrSetManUUID]
  pickleAttr.addConcreteType[AttrSetManURI]
  pickleAttr.addConcreteType[AttrSetManByte]
  pickleAttr.addConcreteType[AttrSetManShort]
  pickleAttr.addConcreteType[AttrSetManFloat]
  pickleAttr.addConcreteType[AttrSetManChar]

  pickleAttr.addConcreteType[AttrSetOptID]
  pickleAttr.addConcreteType[AttrSetOptString]
  pickleAttr.addConcreteType[AttrSetOptInt]
  pickleAttr.addConcreteType[AttrSetOptLong]
  pickleAttr.addConcreteType[AttrSetOptDouble]
  pickleAttr.addConcreteType[AttrSetOptBoolean]
  pickleAttr.addConcreteType[AttrSetOptBigInt]
  pickleAttr.addConcreteType[AttrSetOptBigDecimal]
  pickleAttr.addConcreteType[AttrSetOptDate]
  pickleAttr.addConcreteType[AttrSetOptDuration]
  pickleAttr.addConcreteType[AttrSetOptInstant]
  pickleAttr.addConcreteType[AttrSetOptLocalDate]
  pickleAttr.addConcreteType[AttrSetOptLocalTime]
  pickleAttr.addConcreteType[AttrSetOptLocalDateTime]
  pickleAttr.addConcreteType[AttrSetOptOffsetTime]
  pickleAttr.addConcreteType[AttrSetOptOffsetDateTime]
  pickleAttr.addConcreteType[AttrSetOptZonedDateTime]
  pickleAttr.addConcreteType[AttrSetOptUUID]
  pickleAttr.addConcreteType[AttrSetOptURI]
  pickleAttr.addConcreteType[AttrSetOptByte]
  pickleAttr.addConcreteType[AttrSetOptShort]
  pickleAttr.addConcreteType[AttrSetOptFloat]
  pickleAttr.addConcreteType[AttrSetOptChar]

  pickleAttr.addConcreteType[AttrSetTacID]
  pickleAttr.addConcreteType[AttrSetTacString]
  pickleAttr.addConcreteType[AttrSetTacInt]
  pickleAttr.addConcreteType[AttrSetTacLong]
  pickleAttr.addConcreteType[AttrSetTacDouble]
  pickleAttr.addConcreteType[AttrSetTacBoolean]
  pickleAttr.addConcreteType[AttrSetTacBigInt]
  pickleAttr.addConcreteType[AttrSetTacBigDecimal]
  pickleAttr.addConcreteType[AttrSetTacDate]
  pickleAttr.addConcreteType[AttrSetTacDuration]
  pickleAttr.addConcreteType[AttrSetTacInstant]
  pickleAttr.addConcreteType[AttrSetTacLocalDate]
  pickleAttr.addConcreteType[AttrSetTacLocalTime]
  pickleAttr.addConcreteType[AttrSetTacLocalDateTime]
  pickleAttr.addConcreteType[AttrSetTacOffsetTime]
  pickleAttr.addConcreteType[AttrSetTacOffsetDateTime]
  pickleAttr.addConcreteType[AttrSetTacZonedDateTime]
  pickleAttr.addConcreteType[AttrSetTacUUID]
  pickleAttr.addConcreteType[AttrSetTacURI]
  pickleAttr.addConcreteType[AttrSetTacByte]
  pickleAttr.addConcreteType[AttrSetTacShort]
  pickleAttr.addConcreteType[AttrSetTacFloat]
  pickleAttr.addConcreteType[AttrSetTacChar]

  implicit val pickleElement: CompositePickler[Element] = compositePickler[Element]
  pickleElement.addConcreteType[Attr]
  pickleElement.addConcreteType[Ref]
  pickleElement.addConcreteType[BackRef]
  pickleElement.addConcreteType[Nested]
  pickleElement.addConcreteType[NestedOpt]

  pickleElement.addConcreteType[AttrOneManID]
  pickleElement.addConcreteType[AttrOneManString]
  pickleElement.addConcreteType[AttrOneManInt]
  pickleElement.addConcreteType[AttrOneManLong]
  pickleElement.addConcreteType[AttrOneManDouble]
  pickleElement.addConcreteType[AttrOneManBoolean]
  pickleElement.addConcreteType[AttrOneManBigInt]
  pickleElement.addConcreteType[AttrOneManBigDecimal]
  pickleElement.addConcreteType[AttrOneManDate]
  pickleElement.addConcreteType[AttrOneManDuration]
  pickleElement.addConcreteType[AttrOneManInstant]
  pickleElement.addConcreteType[AttrOneManLocalDate]
  pickleElement.addConcreteType[AttrOneManLocalTime]
  pickleElement.addConcreteType[AttrOneManLocalDateTime]
  pickleElement.addConcreteType[AttrOneManOffsetTime]
  pickleElement.addConcreteType[AttrOneManOffsetDateTime]
  pickleElement.addConcreteType[AttrOneManZonedDateTime]
  pickleElement.addConcreteType[AttrOneManUUID]
  pickleElement.addConcreteType[AttrOneManURI]
  pickleElement.addConcreteType[AttrOneManByte]
  pickleElement.addConcreteType[AttrOneManShort]
  pickleElement.addConcreteType[AttrOneManFloat]
  pickleElement.addConcreteType[AttrOneManChar]

  pickleElement.addConcreteType[AttrOneOptID]
  pickleElement.addConcreteType[AttrOneOptString]
  pickleElement.addConcreteType[AttrOneOptInt]
  pickleElement.addConcreteType[AttrOneOptLong]
  pickleElement.addConcreteType[AttrOneOptDouble]
  pickleElement.addConcreteType[AttrOneOptBoolean]
  pickleElement.addConcreteType[AttrOneOptBigInt]
  pickleElement.addConcreteType[AttrOneOptBigDecimal]
  pickleElement.addConcreteType[AttrOneOptDate]
  pickleElement.addConcreteType[AttrOneOptDuration]
  pickleElement.addConcreteType[AttrOneOptInstant]
  pickleElement.addConcreteType[AttrOneOptLocalDate]
  pickleElement.addConcreteType[AttrOneOptLocalTime]
  pickleElement.addConcreteType[AttrOneOptLocalDateTime]
  pickleElement.addConcreteType[AttrOneOptOffsetTime]
  pickleElement.addConcreteType[AttrOneOptOffsetDateTime]
  pickleElement.addConcreteType[AttrOneOptZonedDateTime]
  pickleElement.addConcreteType[AttrOneOptUUID]
  pickleElement.addConcreteType[AttrOneOptURI]
  pickleElement.addConcreteType[AttrOneOptByte]
  pickleElement.addConcreteType[AttrOneOptShort]
  pickleElement.addConcreteType[AttrOneOptFloat]
  pickleElement.addConcreteType[AttrOneOptChar]

  pickleElement.addConcreteType[AttrOneTacID]
  pickleElement.addConcreteType[AttrOneTacString]
  pickleElement.addConcreteType[AttrOneTacInt]
  pickleElement.addConcreteType[AttrOneTacLong]
  pickleElement.addConcreteType[AttrOneTacDouble]
  pickleElement.addConcreteType[AttrOneTacBoolean]
  pickleElement.addConcreteType[AttrOneTacBigInt]
  pickleElement.addConcreteType[AttrOneTacBigDecimal]
  pickleElement.addConcreteType[AttrOneTacDate]
  pickleElement.addConcreteType[AttrOneTacDuration]
  pickleElement.addConcreteType[AttrOneTacInstant]
  pickleElement.addConcreteType[AttrOneTacLocalDate]
  pickleElement.addConcreteType[AttrOneTacLocalTime]
  pickleElement.addConcreteType[AttrOneTacLocalDateTime]
  pickleElement.addConcreteType[AttrOneTacOffsetTime]
  pickleElement.addConcreteType[AttrOneTacOffsetDateTime]
  pickleElement.addConcreteType[AttrOneTacZonedDateTime]
  pickleElement.addConcreteType[AttrOneTacUUID]
  pickleElement.addConcreteType[AttrOneTacURI]
  pickleElement.addConcreteType[AttrOneTacByte]
  pickleElement.addConcreteType[AttrOneTacShort]
  pickleElement.addConcreteType[AttrOneTacFloat]
  pickleElement.addConcreteType[AttrOneTacChar]

  pickleElement.addConcreteType[AttrSetManID]
  pickleElement.addConcreteType[AttrSetManString]
  pickleElement.addConcreteType[AttrSetManInt]
  pickleElement.addConcreteType[AttrSetManLong]
  pickleElement.addConcreteType[AttrSetManDouble]
  pickleElement.addConcreteType[AttrSetManBoolean]
  pickleElement.addConcreteType[AttrSetManBigInt]
  pickleElement.addConcreteType[AttrSetManBigDecimal]
  pickleElement.addConcreteType[AttrSetManDate]
  pickleElement.addConcreteType[AttrSetManDuration]
  pickleElement.addConcreteType[AttrSetManInstant]
  pickleElement.addConcreteType[AttrSetManLocalDate]
  pickleElement.addConcreteType[AttrSetManLocalTime]
  pickleElement.addConcreteType[AttrSetManLocalDateTime]
  pickleElement.addConcreteType[AttrSetManOffsetTime]
  pickleElement.addConcreteType[AttrSetManOffsetDateTime]
  pickleElement.addConcreteType[AttrSetManZonedDateTime]
  pickleElement.addConcreteType[AttrSetManUUID]
  pickleElement.addConcreteType[AttrSetManURI]
  pickleElement.addConcreteType[AttrSetManByte]
  pickleElement.addConcreteType[AttrSetManShort]
  pickleElement.addConcreteType[AttrSetManFloat]
  pickleElement.addConcreteType[AttrSetManChar]

  pickleElement.addConcreteType[AttrSetOptID]
  pickleElement.addConcreteType[AttrSetOptString]
  pickleElement.addConcreteType[AttrSetOptInt]
  pickleElement.addConcreteType[AttrSetOptLong]
  pickleElement.addConcreteType[AttrSetOptDouble]
  pickleElement.addConcreteType[AttrSetOptBoolean]
  pickleElement.addConcreteType[AttrSetOptBigInt]
  pickleElement.addConcreteType[AttrSetOptBigDecimal]
  pickleElement.addConcreteType[AttrSetOptDate]
  pickleElement.addConcreteType[AttrSetOptDuration]
  pickleElement.addConcreteType[AttrSetOptInstant]
  pickleElement.addConcreteType[AttrSetOptLocalDate]
  pickleElement.addConcreteType[AttrSetOptLocalTime]
  pickleElement.addConcreteType[AttrSetOptLocalDateTime]
  pickleElement.addConcreteType[AttrSetOptOffsetTime]
  pickleElement.addConcreteType[AttrSetOptOffsetDateTime]
  pickleElement.addConcreteType[AttrSetOptZonedDateTime]
  pickleElement.addConcreteType[AttrSetOptUUID]
  pickleElement.addConcreteType[AttrSetOptURI]
  pickleElement.addConcreteType[AttrSetOptByte]
  pickleElement.addConcreteType[AttrSetOptShort]
  pickleElement.addConcreteType[AttrSetOptFloat]
  pickleElement.addConcreteType[AttrSetOptChar]

  pickleElement.addConcreteType[AttrSetTacID]
  pickleElement.addConcreteType[AttrSetTacString]
  pickleElement.addConcreteType[AttrSetTacInt]
  pickleElement.addConcreteType[AttrSetTacLong]
  pickleElement.addConcreteType[AttrSetTacDouble]
  pickleElement.addConcreteType[AttrSetTacBoolean]
  pickleElement.addConcreteType[AttrSetTacBigInt]
  pickleElement.addConcreteType[AttrSetTacBigDecimal]
  pickleElement.addConcreteType[AttrSetTacDate]
  pickleElement.addConcreteType[AttrSetTacDuration]
  pickleElement.addConcreteType[AttrSetTacInstant]
  pickleElement.addConcreteType[AttrSetTacLocalDate]
  pickleElement.addConcreteType[AttrSetTacLocalTime]
  pickleElement.addConcreteType[AttrSetTacLocalDateTime]
  pickleElement.addConcreteType[AttrSetTacOffsetTime]
  pickleElement.addConcreteType[AttrSetTacOffsetDateTime]
  pickleElement.addConcreteType[AttrSetTacZonedDateTime]
  pickleElement.addConcreteType[AttrSetTacUUID]
  pickleElement.addConcreteType[AttrSetTacURI]
  pickleElement.addConcreteType[AttrSetTacByte]
  pickleElement.addConcreteType[AttrSetTacShort]
  pickleElement.addConcreteType[AttrSetTacFloat]
  pickleElement.addConcreteType[AttrSetTacChar]

  implicit val pickleInsertError: CompositePickler[InsertError] =
    compositePickler[InsertError]

  implicit val pickleExceptions: CompositePickler[Throwable] = exceptionPickler
  pickleExceptions
    .addConcreteType[MoleculeError]
    .addConcreteType[ModelError]
    .addConcreteType[ValidationErrors]
    .addConcreteType[InsertErrors]
    .addConcreteType[ExecutionError]

  implicit val pickleFileNotFoundEception: CompositePickler[FileNotFoundException] =
    compositePickler[FileNotFoundException]

  implicit val pickleConnProxy: CompositePickler[ConnProxy] =
    compositePickler[ConnProxy]
      .addConcreteType[DatomicProxy]
      .addConcreteType[JdbcProxy]
}
