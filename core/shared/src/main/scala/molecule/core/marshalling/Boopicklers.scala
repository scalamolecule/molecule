package molecule.core.marshalling

import java.io.FileNotFoundException
import java.net.URI
import java.nio.ByteBuffer
import java.util.Date
import boopickle.CompositePickler
import boopickle.Default._
import chameleon._
import molecule.base.util.exceptions.{MoleculeCompileException, MoleculeError}
import molecule.boilerplate
import molecule.boilerplate.ast
import molecule.boilerplate.ast.Model
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import scala.util.{Failure, Success, Try}


object Boopicklers extends MoleculeLogging {

  implicit val pickleDate: Pickler[Date] = transformPickler((t: Long) => new java.util.Date(t))(_.getTime)
  implicit val pickleURI : Pickler[URI]  = transformPickler((t: String) => new URI(t))(_.toString)

  implicit val pickleOp: CompositePickler[Op] = compositePickler[Op]
  pickleOp.addConcreteType[V.type]
  pickleOp.addConcreteType[Appl.type]
  pickleOp.addConcreteType[Not.type]
  pickleOp.addConcreteType[Eq.type]
  pickleOp.addConcreteType[Neq.type]
  pickleOp.addConcreteType[Lt.type]
  pickleOp.addConcreteType[Le.type]
  pickleOp.addConcreteType[Gt.type]
  pickleOp.addConcreteType[Ge.type]
  pickleOp.addConcreteType[NoValue.type]
  pickleOp.addConcreteType[Fn]
  pickleOp.addConcreteType[Unify.type]
  pickleOp.addConcreteType[Add.type]
  pickleOp.addConcreteType[Swap.type]
  pickleOp.addConcreteType[Remove.type]

  implicit val pickleValidateString    : CompositePickler[ValidateString]     = compositePickler[ValidateString]
  implicit val pickleValidateInt       : CompositePickler[ValidateInt]        = compositePickler[ValidateInt]
  implicit val pickleValidateLong      : CompositePickler[ValidateLong]       = compositePickler[ValidateLong]
  implicit val pickleValidateDouble    : CompositePickler[ValidateDouble]     = compositePickler[ValidateDouble]
  implicit val pickleValidateBoolean   : CompositePickler[ValidateBoolean]    = compositePickler[ValidateBoolean]
  implicit val pickleValidateBigInt    : CompositePickler[ValidateBigInt]     = compositePickler[ValidateBigInt]
  implicit val pickleValidateBigDecimal: CompositePickler[ValidateBigDecimal] = compositePickler[ValidateBigDecimal]
  implicit val pickleValidateDate      : CompositePickler[ValidateDate]       = compositePickler[ValidateDate]
  implicit val pickleValidateUUID      : CompositePickler[ValidateUUID]       = compositePickler[ValidateUUID]
  implicit val pickleValidateURI       : CompositePickler[ValidateURI]        = compositePickler[ValidateURI]
  implicit val pickleValidateByte      : CompositePickler[ValidateByte]       = compositePickler[ValidateByte]
  implicit val pickleValidateShort     : CompositePickler[ValidateShort]      = compositePickler[ValidateShort]
  implicit val pickleValidateFloat     : CompositePickler[ValidateFloat]      = compositePickler[ValidateFloat]
  implicit val pickleValidateChar      : CompositePickler[ValidateChar]       = compositePickler[ValidateChar]

  implicit val pickleElement: CompositePickler[Element] = compositePickler[Element]
  pickleElement.addConcreteType[Ref]
  pickleElement.addConcreteType[BackRef]
  pickleElement.addConcreteType[Composite]
  pickleElement.addConcreteType[Nested]
  pickleElement.addConcreteType[NestedOpt]
  pickleElement.addConcreteType[TxMetaData]

  pickleElement.addConcreteType[AttrOneManString]
  pickleElement.addConcreteType[AttrOneManInt]
  pickleElement.addConcreteType[AttrOneManLong]
  pickleElement.addConcreteType[AttrOneManDouble]
  pickleElement.addConcreteType[AttrOneManBoolean]
  pickleElement.addConcreteType[AttrOneManBigInt]
  pickleElement.addConcreteType[AttrOneManBigDecimal]
  pickleElement.addConcreteType[AttrOneManDate]
  pickleElement.addConcreteType[AttrOneManUUID]
  pickleElement.addConcreteType[AttrOneManURI]
  pickleElement.addConcreteType[AttrOneManByte]
  pickleElement.addConcreteType[AttrOneManShort]
  pickleElement.addConcreteType[AttrOneManFloat]
  pickleElement.addConcreteType[AttrOneManChar]
  pickleElement.addConcreteType[AttrOneOptString]
  pickleElement.addConcreteType[AttrOneOptInt]
  pickleElement.addConcreteType[AttrOneOptLong]
  pickleElement.addConcreteType[AttrOneOptDouble]
  pickleElement.addConcreteType[AttrOneOptBoolean]
  pickleElement.addConcreteType[AttrOneOptBigInt]
  pickleElement.addConcreteType[AttrOneOptBigDecimal]
  pickleElement.addConcreteType[AttrOneOptDate]
  pickleElement.addConcreteType[AttrOneOptUUID]
  pickleElement.addConcreteType[AttrOneOptURI]
  pickleElement.addConcreteType[AttrOneOptByte]
  pickleElement.addConcreteType[AttrOneOptShort]
  pickleElement.addConcreteType[AttrOneOptFloat]
  pickleElement.addConcreteType[AttrOneOptChar]
  pickleElement.addConcreteType[AttrOneTacString]
  pickleElement.addConcreteType[AttrOneTacInt]
  pickleElement.addConcreteType[AttrOneTacLong]
  pickleElement.addConcreteType[AttrOneTacDouble]
  pickleElement.addConcreteType[AttrOneTacBoolean]
  pickleElement.addConcreteType[AttrOneTacBigInt]
  pickleElement.addConcreteType[AttrOneTacBigDecimal]
  pickleElement.addConcreteType[AttrOneTacDate]
  pickleElement.addConcreteType[AttrOneTacUUID]
  pickleElement.addConcreteType[AttrOneTacURI]
  pickleElement.addConcreteType[AttrOneTacByte]
  pickleElement.addConcreteType[AttrOneTacShort]
  pickleElement.addConcreteType[AttrOneTacFloat]
  pickleElement.addConcreteType[AttrOneTacChar]
  pickleElement.addConcreteType[AttrSetManString]
  pickleElement.addConcreteType[AttrSetManInt]
  pickleElement.addConcreteType[AttrSetManLong]
  pickleElement.addConcreteType[AttrSetManDouble]
  pickleElement.addConcreteType[AttrSetManBoolean]
  pickleElement.addConcreteType[AttrSetManBigInt]
  pickleElement.addConcreteType[AttrSetManBigDecimal]
  pickleElement.addConcreteType[AttrSetManDate]
  pickleElement.addConcreteType[AttrSetManUUID]
  pickleElement.addConcreteType[AttrSetManURI]
  pickleElement.addConcreteType[AttrSetManByte]
  pickleElement.addConcreteType[AttrSetManShort]
  pickleElement.addConcreteType[AttrSetManFloat]
  pickleElement.addConcreteType[AttrSetManChar]
  pickleElement.addConcreteType[AttrSetOptString]
  pickleElement.addConcreteType[AttrSetOptInt]
  pickleElement.addConcreteType[AttrSetOptLong]
  pickleElement.addConcreteType[AttrSetOptDouble]
  pickleElement.addConcreteType[AttrSetOptBoolean]
  pickleElement.addConcreteType[AttrSetOptBigInt]
  pickleElement.addConcreteType[AttrSetOptBigDecimal]
  pickleElement.addConcreteType[AttrSetOptDate]
  pickleElement.addConcreteType[AttrSetOptUUID]
  pickleElement.addConcreteType[AttrSetOptURI]
  pickleElement.addConcreteType[AttrSetOptByte]
  pickleElement.addConcreteType[AttrSetOptShort]
  pickleElement.addConcreteType[AttrSetOptFloat]
  pickleElement.addConcreteType[AttrSetOptChar]
  pickleElement.addConcreteType[AttrSetTacString]
  pickleElement.addConcreteType[AttrSetTacInt]
  pickleElement.addConcreteType[AttrSetTacLong]
  pickleElement.addConcreteType[AttrSetTacDouble]
  pickleElement.addConcreteType[AttrSetTacBoolean]
  pickleElement.addConcreteType[AttrSetTacBigInt]
  pickleElement.addConcreteType[AttrSetTacBigDecimal]
  pickleElement.addConcreteType[AttrSetTacDate]
  pickleElement.addConcreteType[AttrSetTacUUID]
  pickleElement.addConcreteType[AttrSetTacURI]
  pickleElement.addConcreteType[AttrSetTacByte]
  pickleElement.addConcreteType[AttrSetTacShort]
  pickleElement.addConcreteType[AttrSetTacFloat]
  pickleElement.addConcreteType[AttrSetTacChar]


  implicit val pickleExceptions: CompositePickler[Throwable] = exceptionPickler
  pickleExceptions
    .addConcreteType[MoleculeError]
    .addConcreteType[MoleculeCompileException]

  implicit val pickleFileNotFoundEception: CompositePickler[FileNotFoundException] =
    compositePickler[FileNotFoundException]

  implicit val pickleConnProxy: CompositePickler[ConnProxy] = compositePickler[ConnProxy]
    .addConcreteType[DatomicPeerProxy]


//  // Copying this method so that we can avoid `import chameleon.ext.boopickle._`
//  // in all custom SlothControllers and WebClients
//  implicit def boopickleSerializerDeserializer[T: Pickler]: SerializerDeserializer[T, ByteBuffer] = {
//    new Serializer[T, ByteBuffer] with Deserializer[T, ByteBuffer] {
//      override def serialize(arg: T): ByteBuffer = {
//        try {
//          logger.error(arg.toString)
//          Pickle.intoBytes(arg)
//        } catch {
//          case e: Throwable =>
//            logger.warn(e.toString)
//            throw e
//        }
//      }
//      override def deserialize(arg: ByteBuffer): Either[Throwable, T] = {
//        Try(Unpickle.apply[T].fromBytes(arg)) match {
//          case Success(data) =>
//            logger.error(data.toString)
//            Right(data)
//          case Failure(exc)  =>
//            logger.warn(exc.toString)
//            Left(exc)
//        }
//      }
//    }
//  }
}
