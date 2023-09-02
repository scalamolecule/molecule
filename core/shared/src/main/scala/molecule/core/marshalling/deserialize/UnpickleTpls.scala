package molecule.core.marshalling.deserialize

import java.net.URI
import java.nio.ByteBuffer
import java.util.{Date, UUID}
import boopickle.BasicPicklers._
import boopickle.Default._
import boopickle._
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.Boopicklers._
import molecule.core.util.ModelUtils
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

case class UnpickleTpls[Tpl](elements: List[Element], eitherSerialized: ByteBuffer)
  extends UnpickleTpl_[Tpl]
    with ModelUtils
    with MoleculeLogging {

  private val prevRefs: ListBuffer[String] = ListBuffer.empty[String]

  private val state = new UnpickleState(
    new DecoderSize(eitherSerialized),
    false,
    false
  )
  private val dec   = state.dec

  def unpickle: Either[MoleculeError, List[Tpl]] = {
    dec.readInt match { // decode Left/Right
      case 2 => Right(unpickleTpls)
      case _ => Left(Unpickle.apply[MoleculeError].fromState(state))
    }
  }
  def unpickleOffset: Either[MoleculeError, (List[Tpl], Int, Boolean)] = {
    dec.readInt match { // decode Left/Right
      case 2 => Right((unpickleTpls, dek.readInt, dek.readBoolean))
      case _ => Left(Unpickle.apply[MoleculeError].fromState(state))
    }
  }
  def unpickleCursor: Either[MoleculeError, (List[Tpl], String, Boolean)] = {
    dec.readInt match { // decode Left/Right
      case 2 => Right((unpickleTpls, dek.readString, dek.readBoolean))
      case _ => Left(Unpickle.apply[MoleculeError].fromState(state))
    }
  }

  private def unpickleTpls: List[Tpl] = {
    dec.readInt match { // decode List size
      case 0   =>
        // empty result
        List.empty[Tpl]
      case len =>
        val tpls = ListBuffer.empty[Tpl]
        tpls.sizeHint(len)
        val unpickleTpl = getUnpickler(elements)
        var i           = 0
        while (i < len) {
          tpls += unpickleTpl().asInstanceOf[Tpl]
          i += 1
        }
        tpls.result()
    }
  }

  @tailrec
  final protected def resolveUnpicklers(
    elements: List[Element],
    unpicklers: List[() => Any]
  ): List[() => Any] = {
    elements match {
      case element :: tail => element match {
        case a: Attr =>
          a match {
            case a: AttrOne =>
              a match {
                case a: AttrOneMan => resolveUnpicklers(tail, unpicklers :+ unpickleAttrOneMan(a))
                case a: AttrOneOpt => resolveUnpicklers(tail, unpicklers :+ unpickleAttrOneOpt(a))
                case _: AttrOneTac => resolveUnpicklers(tail, unpicklers)
              }
            case a: AttrSet =>
              a match {
                case a: AttrSetMan => resolveUnpicklers(tail, unpicklers :+ unpickleAttrSetMan(a))
                case a: AttrSetOpt => resolveUnpicklers(tail, unpicklers :+ unpickleAttrSetOpt(a))
                case _: AttrSetTac => resolveUnpicklers(tail, unpicklers)
              }
          }

        case Ref(_, refAttr, _, _) =>
          prevRefs += refAttr
          resolveUnpicklers(tail, unpicklers)

        case BackRef(backRefNs, _) =>
          tail.head match {
            case Ref(_, refAttr, _, _) if prevRefs.contains(refAttr) => throw ModelError(
              s"Can't re-use previous namespace ${refAttr.capitalize} after backref _$backRefNs."
            )
            case _                                                   => // ok
          }
          resolveUnpicklers(tail, unpicklers)

        case Nested(_, nestedElements) =>
          prevRefs.clear()
          resolveUnpicklers(tail, unpicklers :+ unpickleNested(nestedElements))

        case NestedOpt(_, nestedElements) =>
          prevRefs.clear()
          resolveUnpicklers(tail, unpicklers :+ unpickleNested(nestedElements))
      }
      case Nil             => unpicklers
    }
  }


  private def unpickleNested(
    nestedElements: List[Element]
  ): () => Any = {
    () =>
      dec.readInt match {
        case 0   =>
          // empty result
          List.empty[Any]
        case len =>
          val tpls = ListBuffer.empty[Any]
          tpls.sizeHint(len)
          // Recursively unpickle nested levels
          val unpickleNestedData = getUnpickler(nestedElements)
          var i                  = 0
          while (i < len) {
            tpls += unpickleNestedData()
            i += 1
          }
          tpls.result()
      }
  }

  object dek {
    def readString = dec.readString
    def readInt = dec.readInt

    // todo: Skip brute forcing values to Strings
    def readLong = dec.readString.toLong
    def readFloat = dec.readString.toFloat
    def readDouble = dec.readString.toDouble

    // todo: how can we make these work instead?
    //    def readLong = dec.readLong
    //    def readFloat = dec.readFloat
    //    def readDouble = dec.readDouble


    def readBoolean = BooleanPickler.unpickle(state)
    def readBigInt = {
      val len = dec.readInt
      val ba  = Array.ofDim[Byte](len)
      (0 until len).foreach(i => ba(i) = dec.readByte)
      BigInt(ba)
    }
    def readBigDecimal = {
      val scale = dec.readInt
      val len   = dec.readInt
      val ba    = Array.ofDim[Byte](len)
      (0 until len).foreach(i => ba(i) = dec.readByte)
      BigDecimal(BigInt(ba), scale)
    }
    def readDate = new Date(dec.readString.toLong)
    def readUUID = UUID.fromString(dec.readString)
    def readURI = new URI(dec.readString)
    def readByte = dec.readByte
    def readShort = dec.readString.toShort
    def readChar = dec.readChar

    def readSet[T](reader: => T): () => Set[T] = {
      () => {
        dec.readInt match {
          case 0   => Set.empty[T]
          case len =>
            var set = Set.empty[T]
            var i   = 0
            while (i < len) {
              set += reader
              i += 1
            }
            set
        }
      }
    }

    def readSets[T](reader: => T): () => Set[Set[T]] = {
      () => {
        dec.readInt match {
          case 0         => Set.empty[Set[T]]
          case setsCount =>
            var sets = Set.empty[Set[T]]
            var i    = 0
            while (i < setsCount) {
              sets += readSet[T](reader)()
              i += 1
            }
            sets
        }
      }
    }

    def readOpt[T](reader: => T): () => Option[T] = {
      () => {
        state.dec.readInt match {
          case 2 => Some(reader)
          case 1 => None
        }
      }
    }

    def readOptSet[T](reader: => T): () => Option[Set[T]] = {
      () => {
        state.dec.readInt match {
          case 2 => Some(readSet(reader)())
          case 1 => None
        }
      }
    }
  }

  protected def unexpected(element: Element) =
    throw ModelError("Unexpected element: " + element)

  private def unpickleAttrOneMan(a: AttrOneMan): () => Any = {
    a.op match {
      case Fn(kw, _) => kw match {
        case "count" | "countDistinct"                          => () => dek.readInt
        case "distinct" | "mins" | "maxs" | "rands" | "samples" => unpickleAttrOneManSet(a)
        case "avg" | "variance" | "stddev"                      => () => dek.readDouble
        case _                                                  => unpickleAttrOneManV(a)
      }
      case _         => unpickleAttrOneManV(a)
    }
  }
  private def unpickleAttrOneManV(a: AttrOneMan): () => Any = {
    a match {
      case _: AttrOneManString     => () => dek.readString
      case _: AttrOneManInt        => () => dek.readInt
      case _: AttrOneManLong       => () => dek.readLong
      case _: AttrOneManFloat      => () => dek.readFloat
      case _: AttrOneManDouble     => () => dek.readDouble
      case _: AttrOneManBoolean    => () => dek.readBoolean
      case _: AttrOneManBigInt     => () => dek.readBigInt
      case _: AttrOneManBigDecimal => () => dek.readBigDecimal
      case _: AttrOneManDate       => () => dek.readDate
      case _: AttrOneManUUID       => () => dek.readUUID
      case _: AttrOneManURI        => () => dek.readURI
      case _: AttrOneManByte       => () => dek.readByte
      case _: AttrOneManShort      => () => dek.readShort
      case _: AttrOneManChar       => () => dek.readChar
    }
  }
  private def unpickleAttrOneManSet(a: AttrOneMan): () => Any = {
    a match {
      case _: AttrOneManString     => dek.readSet[String](dek.readString)
      case _: AttrOneManInt        => dek.readSet[Int](dek.readInt)
      case _: AttrOneManLong       => dek.readSet[Long](dek.readLong)
      case _: AttrOneManFloat      => dek.readSet[Float](dek.readFloat)
      case _: AttrOneManDouble     => dek.readSet[Double](dek.readDouble)
      case _: AttrOneManBoolean    => dek.readSet[Boolean](dek.readBoolean)
      case _: AttrOneManBigInt     => dek.readSet[BigInt](dek.readBigInt)
      case _: AttrOneManBigDecimal => dek.readSet[BigDecimal](dek.readBigDecimal)
      case _: AttrOneManDate       => dek.readSet[Date](dek.readDate)
      case _: AttrOneManUUID       => dek.readSet[UUID](dek.readUUID)
      case _: AttrOneManURI        => dek.readSet[URI](dek.readURI)
      case _: AttrOneManByte       => dek.readSet[Byte](dek.readByte)
      case _: AttrOneManShort      => dek.readSet[Short](dek.readShort)
      case _: AttrOneManChar       => dek.readSet[Char](dek.readChar)
    }
  }

  private def unpickleAttrOneOpt(a: AttrOneOpt): () => Any = {
    a match {
      case _: AttrOneOptString     => dek.readOpt[String](dek.readString)
      case _: AttrOneOptInt        => dek.readOpt[Int](dek.readInt)
      case _: AttrOneOptLong       => dek.readOpt[Long](dek.readLong)
      case _: AttrOneOptFloat      => dek.readOpt[Float](dek.readFloat)
      case _: AttrOneOptDouble     => dek.readOpt[Double](dek.readDouble)
      case _: AttrOneOptBoolean    => dek.readOpt[Boolean](dek.readBoolean)
      case _: AttrOneOptBigInt     => dek.readOpt[BigInt](dek.readBigInt)
      case _: AttrOneOptBigDecimal => dek.readOpt[BigDecimal](dek.readBigDecimal)
      case _: AttrOneOptDate       => dek.readOpt[Date](dek.readDate)
      case _: AttrOneOptUUID       => dek.readOpt[UUID](dek.readUUID)
      case _: AttrOneOptURI        => dek.readOpt[URI](dek.readURI)
      case _: AttrOneOptByte       => dek.readOpt[Byte](dek.readByte)
      case _: AttrOneOptShort      => dek.readOpt[Short](dek.readShort)
      case _: AttrOneOptChar       => dek.readOpt[Char](dek.readChar)
    }
  }

  private def unpickleAttrSetMan(a: AttrSetMan): () => Any = {
    a.op match {
      case Fn(kw, _) => kw match {
        case "count" | "countDistinct"             => () => dek.readInt
        case "distinct"                            => unpickleAttrSetManSet(a)
        case "mins" | "maxs" | "rands" | "samples" => unpickleAttrSetManV(a)
        case "avg" | "variance" | "stddev"         => () => dek.readSet[Double](dek.readDouble)
        case _                                     => unpickleAttrSetManV(a)
      }
      case _         => unpickleAttrSetManV(a)
    }
  }
  private def unpickleAttrSetManV(a: AttrSetMan): () => Any = {
    a match {
      case _: AttrSetManString     => dek.readSet[String](dek.readString)
      case _: AttrSetManInt        => dek.readSet[Int](dek.readInt)
      case _: AttrSetManLong       => dek.readSet[Long](dek.readLong)
      case _: AttrSetManFloat      => dek.readSet[Float](dek.readFloat)
      case _: AttrSetManDouble     => dek.readSet[Double](dek.readDouble)
      case _: AttrSetManBoolean    => dek.readSet[Boolean](dek.readBoolean)
      case _: AttrSetManBigInt     => dek.readSet[BigInt](dek.readBigInt)
      case _: AttrSetManBigDecimal => dek.readSet[BigDecimal](dek.readBigDecimal)
      case _: AttrSetManDate       => dek.readSet[Date](dek.readDate)
      case _: AttrSetManUUID       => dek.readSet[UUID](dek.readUUID)
      case _: AttrSetManURI        => dek.readSet[URI](dek.readURI)
      case _: AttrSetManByte       => dek.readSet[Byte](dek.readByte)
      case _: AttrSetManShort      => dek.readSet[Short](dek.readShort)
      case _: AttrSetManChar       => dek.readSet[Char](dek.readChar)
    }
  }
  private def unpickleAttrSetManSet(a: AttrSetMan): () => Any = {
    a match {
      case _: AttrSetManString     => dek.readSets[String](dek.readString)
      case _: AttrSetManInt        => dek.readSets[Int](dek.readInt)
      case _: AttrSetManLong       => dek.readSets[Long](dek.readLong)
      case _: AttrSetManFloat      => dek.readSets[Float](dek.readFloat)
      case _: AttrSetManDouble     => dek.readSets[Double](dek.readDouble)
      case _: AttrSetManBoolean    => dek.readSets[Boolean](dek.readBoolean)
      case _: AttrSetManBigInt     => dek.readSets[BigInt](dek.readBigInt)
      case _: AttrSetManBigDecimal => dek.readSets[BigDecimal](dek.readBigDecimal)
      case _: AttrSetManDate       => dek.readSets[Date](dek.readDate)
      case _: AttrSetManUUID       => dek.readSets[UUID](dek.readUUID)
      case _: AttrSetManURI        => dek.readSets[URI](dek.readURI)
      case _: AttrSetManByte       => dek.readSets[Byte](dek.readByte)
      case _: AttrSetManShort      => dek.readSets[Short](dek.readShort)
      case _: AttrSetManChar       => dek.readSets[Char](dek.readChar)
    }
  }

  private def unpickleAttrSetOpt(a: AttrSetOpt): () => Any = {
    a match {
      case _: AttrSetOptString     => dek.readOptSet(dek.readString)
      case _: AttrSetOptInt        => dek.readOptSet(dek.readInt)
      case _: AttrSetOptLong       => dek.readOptSet(dek.readLong)
      case _: AttrSetOptFloat      => dek.readOptSet(dek.readFloat)
      case _: AttrSetOptDouble     => dek.readOptSet(dek.readDouble)
      case _: AttrSetOptBoolean    => dek.readOptSet(dek.readBoolean)
      case _: AttrSetOptBigInt     => dek.readOptSet(dek.readBigInt)
      case _: AttrSetOptBigDecimal => dek.readOptSet(dek.readBigDecimal)
      case _: AttrSetOptDate       => dek.readOptSet(dek.readDate)
      case _: AttrSetOptUUID       => dek.readOptSet(dek.readUUID)
      case _: AttrSetOptURI        => dek.readOptSet(dek.readURI)
      case _: AttrSetOptByte       => dek.readOptSet(dek.readByte)
      case _: AttrSetOptShort      => dek.readOptSet(dek.readShort)
      case _: AttrSetOptChar       => dek.readOptSet(dek.readChar)
    }
  }
}
