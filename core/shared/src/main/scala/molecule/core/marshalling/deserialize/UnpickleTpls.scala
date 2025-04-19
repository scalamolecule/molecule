package molecule.core.marshalling.deserialize

import java.net.URI
import java.nio.ByteBuffer
import java.time.*
import java.util.{Date, UUID}
import boopickle.*
import boopickle.BasicPicklers.*
import boopickle.Default.*
import molecule.base.error.*
import molecule.core.ast.DataModel.*
import molecule.core.marshalling.Boopicklers.*
import molecule.core.util.{ModelUtils, MoleculeLogging}
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

  def unpickleSeqOfProduct: Seq[Product] = {
    val data = unpickleTpls
    if (countValueAttrs(elements) == 1) {
      data.map(Tuple1(_))
    } else {
      data.asInstanceOf[Seq[Product]]
    }
  }

  def unpickleOffset: (List[Tpl], Int, Boolean) =
    (unpickleTpls, dek.readInt, dek.readBoolean)

  def unpickleCursor: (List[Tpl], String, Boolean) =
    (unpickleTpls, dek.readString, dek.readBoolean)

  def unpickleTpls: List[Tpl] = {
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
            case a: AttrSeq =>
              a match {
                case a: AttrSeqMan => resolveUnpicklers(tail, unpicklers :+ unpickleAttrSeqMan(a))
                case a: AttrSeqOpt => resolveUnpicklers(tail, unpicklers :+ unpickleAttrSeqOpt(a))
                case _: AttrSeqTac => resolveUnpicklers(tail, unpicklers)
              }
            case a: AttrMap =>
              a match {
                case a: AttrMapMan => resolveUnpicklers(tail, unpicklers :+ unpickleAttrMapMan(a))
                case a: AttrMapOpt => resolveUnpicklers(tail, unpicklers :+ unpickleAttrMapOpt(a))
                case _: AttrMapTac => resolveUnpicklers(tail, unpicklers)
              }
          }

        case Ref(_, refAttr, _, _, _, _) =>
          prevRefs += refAttr
          resolveUnpicklers(tail, unpicklers)

        case BackRef(backRef, _, _) =>
          tail.head match {
            case Ref(_, refAttr, _, _, _, _) if prevRefs.contains(refAttr) => throw ModelError(
              s"Can't re-use previous entity ${refAttr.capitalize} after backref _$backRef."
            )
            case _                                                         => // ok
          }
          resolveUnpicklers(tail, unpicklers)

        case OptRef(_, optRefElements) =>
          prevRefs.clear()
          resolveUnpicklers(tail, unpicklers :+ unpickleOptElements(optRefElements))

        case OptEntity(attrs) =>
          prevRefs.clear()
          resolveUnpicklers(tail, unpicklers :+ unpickleOptElements(attrs))

        case Nested(_, nestedElements) =>
          prevRefs.clear()
          resolveUnpicklers(tail, unpicklers :+ unpickleNested(nestedElements))

        case OptNested(_, nestedElements) =>
          prevRefs.clear()
          resolveUnpicklers(tail, unpicklers :+ unpickleNested(nestedElements))
      }
      case Nil             => unpicklers
    }
  }


  private def unpickleOptElements(
    optElements: List[Element]
  ): () => Any = {
    () =>
      dec.readInt match {
        case 1 => Option.empty[Any]
        case 2 =>
          // Recursively unpickle opt ref data
          Some(getUnpickler(optElements)())
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

    // todo: Skip brute forcing values to Strings
    def readInt = dec.readString.toInt
    def readLong = dec.readString.toLong
    def readFloat = dec.readString.toFloat
    def readDouble = dec.readString.toDouble

    // todo: how can we make these work instead?
    //    def readInt = dec.readInt
    //    def readLong = dec.readLong
    //    def readFloat = dec.readFloat
    //    def readDouble = dec.readDouble

    def readBoolean = BooleanPickler.unpickle(state)
    def readBigInt = {
      //      val len = dec.readInt
      //      val ba  = Array.ofDim[Byte](len)
      //      (0 until len).foreach(i => ba(i) = dec.readByte)
      //      BigInt(ba)
      BigInt(dec.readString)
    }
    def readBigDecimal = {
      //      val scale = dec.readInt
      //      val len   = dec.readInt
      //      val ba    = Array.ofDim[Byte](len)
      //      (0 until len).foreach(i => ba(i) = dec.readByte)
      //      BigDecimal(BigInt(ba), scale)
      BigDecimal(dec.readString)
    }
    def readDate = new Date(dec.readString.toLong)
    def readDuration = Duration.parse(dec.readString)
    def readInstant = Instant.parse(dec.readString)
    def readLocalDate = LocalDate.parse(dec.readString)
    def readLocalTime = LocalTime.parse(dec.readString)
    def readLocalDateTime = LocalDateTime.parse(dec.readString)
    def readOffsetTime = OffsetTime.parse(dec.readString)
    def readOffsetDateTime = OffsetDateTime.parse(dec.readString)
    def readZonedDateTime = ZonedDateTime.parse(dec.readString)
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

    def readSeq[T](reader: => T): () => Seq[T] = {
      () => {
        dec.readInt match {
          case 0   => Seq.empty[T]
          case len =>
            var seq = Seq.empty[T]
            var i   = 0
            while (i < len) {
              seq = seq :+ reader
              i += 1
            }
            seq
        }
      }
    }

    def readByteArray: () => Array[Byte] = {
      () => {
        dec.readInt match {
          case 0   => Array.empty[Byte]
          case len =>
            val array = new Array[Byte](len)
            var i     = 0
            while (i < len) {
              array(i) = dec.readByte
              i += 1
            }
            array
        }
      }
    }

    def readMap[T](reader: => T): () => Map[String, T] = {
      () => {
        dec.readInt match {
          case 0   => Map.empty[String, T]
          case len =>
            var map = Map.empty[String, T]
            var i   = 0
            while (i < len) {
              map = map + (readString -> reader)
              i += 1
            }
            map
        }
      }
    }

    def readOpt[T](reader: => T): () => Option[T] = {
      () => {
        dec.readInt match {
          case 2 => Some(reader)
          case 1 => None
        }
      }
    }

    def readOptSet[T](reader: => T): () => Option[Set[T]] = {
      () => {
        dec.readInt match {
          case 2 => Some(readSet(reader)())
          case 1 => None
        }
      }
    }

    def readOptSeq[T](reader: => T): () => Option[Seq[T]] = {
      () => {
        dec.readInt match {
          case 2 => Some(readSeq(reader)())
          case 1 => None
        }
      }
    }

    def readOptByteArray: () => Option[Array[Byte]] = {
      () => {
        dec.readInt match {
          case 2 => Some(readByteArray())
          case 1 => None
        }
      }
    }

    def readOptMap[T](reader: => T): () => Option[Map[String, T]] = {
      () => {
        dec.readInt match {
          case 2 => Some(readMap(reader)())
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
        case "count" | "countDistinct"                => () => dek.readInt
        case "distinct" | "mins" | "maxs" | "samples" => unpickleAttrOneManSet(a)
        case "avg" | "variance" | "stddev"            => () => dek.readDouble
        case _                                        => unpickleAttrOneManV(a)
      }
      case _         => unpickleAttrOneManV(a)
    }
  }
  private def unpickleAttrOneManV(a: AttrOneMan): () => Any = {
    a match {
      case _: AttrOneManID             => () => dek.readLong
      case _: AttrOneManString         => () => dek.readString
      case _: AttrOneManInt            => () => dek.readInt
      case _: AttrOneManLong           => () => dek.readLong
      case _: AttrOneManFloat          => () => dek.readFloat
      case _: AttrOneManDouble         => () => dek.readDouble
      case _: AttrOneManBoolean        => () => dek.readBoolean
      case _: AttrOneManBigInt         => () => dek.readBigInt
      case _: AttrOneManBigDecimal     => () => dek.readBigDecimal
      case _: AttrOneManDate           => () => dek.readDate
      case _: AttrOneManDuration       => () => dek.readDuration
      case _: AttrOneManInstant        => () => dek.readInstant
      case _: AttrOneManLocalDate      => () => dek.readLocalDate
      case _: AttrOneManLocalTime      => () => dek.readLocalTime
      case _: AttrOneManLocalDateTime  => () => dek.readLocalDateTime
      case _: AttrOneManOffsetTime     => () => dek.readOffsetTime
      case _: AttrOneManOffsetDateTime => () => dek.readOffsetDateTime
      case _: AttrOneManZonedDateTime  => () => dek.readZonedDateTime
      case _: AttrOneManUUID           => () => dek.readUUID
      case _: AttrOneManURI            => () => dek.readURI
      case _: AttrOneManByte           => () => dek.readByte
      case _: AttrOneManShort          => () => dek.readShort
      case _: AttrOneManChar           => () => dek.readChar
    }
  }
  private def unpickleAttrOneManSet(a: AttrOneMan): () => Any = {
    a match {
      case _: AttrOneManID             => dek.readSet[Long](dek.readLong)
      case _: AttrOneManString         => dek.readSet[String](dek.readString)
      case _: AttrOneManInt            => dek.readSet[Int](dek.readInt)
      case _: AttrOneManLong           => dek.readSet[Long](dek.readLong)
      case _: AttrOneManFloat          => dek.readSet[Float](dek.readFloat)
      case _: AttrOneManDouble         => dek.readSet[Double](dek.readDouble)
      case _: AttrOneManBoolean        => dek.readSet[Boolean](dek.readBoolean)
      case _: AttrOneManBigInt         => dek.readSet[BigInt](dek.readBigInt)
      case _: AttrOneManBigDecimal     => dek.readSet[BigDecimal](dek.readBigDecimal)
      case _: AttrOneManDate           => dek.readSet[Date](dek.readDate)
      case _: AttrOneManDuration       => dek.readSet[Duration](dek.readDuration)
      case _: AttrOneManInstant        => dek.readSet[Instant](dek.readInstant)
      case _: AttrOneManLocalDate      => dek.readSet[LocalDate](dek.readLocalDate)
      case _: AttrOneManLocalTime      => dek.readSet[LocalTime](dek.readLocalTime)
      case _: AttrOneManLocalDateTime  => dek.readSet[LocalDateTime](dek.readLocalDateTime)
      case _: AttrOneManOffsetTime     => dek.readSet[OffsetTime](dek.readOffsetTime)
      case _: AttrOneManOffsetDateTime => dek.readSet[OffsetDateTime](dek.readOffsetDateTime)
      case _: AttrOneManZonedDateTime  => dek.readSet[ZonedDateTime](dek.readZonedDateTime)
      case _: AttrOneManUUID           => dek.readSet[UUID](dek.readUUID)
      case _: AttrOneManURI            => dek.readSet[URI](dek.readURI)
      case _: AttrOneManByte           => dek.readSet[Byte](dek.readByte)
      case _: AttrOneManShort          => dek.readSet[Short](dek.readShort)
      case _: AttrOneManChar           => dek.readSet[Char](dek.readChar)
    }
  }

  private def unpickleAttrOneOpt(a: AttrOneOpt): () => Any = {
    a match {
      case _: AttrOneOptID             => dek.readOpt[Long](dek.readLong)
      case _: AttrOneOptString         => dek.readOpt[String](dek.readString)
      case _: AttrOneOptInt            => dek.readOpt[Int](dek.readInt)
      case _: AttrOneOptLong           => dek.readOpt[Long](dek.readLong)
      case _: AttrOneOptFloat          => dek.readOpt[Float](dek.readFloat)
      case _: AttrOneOptDouble         => dek.readOpt[Double](dek.readDouble)
      case _: AttrOneOptBoolean        => dek.readOpt[Boolean](dek.readBoolean)
      case _: AttrOneOptBigInt         => dek.readOpt[BigInt](dek.readBigInt)
      case _: AttrOneOptBigDecimal     => dek.readOpt[BigDecimal](dek.readBigDecimal)
      case _: AttrOneOptDate           => dek.readOpt[Date](dek.readDate)
      case _: AttrOneOptDuration       => dek.readOpt[Duration](dek.readDuration)
      case _: AttrOneOptInstant        => dek.readOpt[Instant](dek.readInstant)
      case _: AttrOneOptLocalDate      => dek.readOpt[LocalDate](dek.readLocalDate)
      case _: AttrOneOptLocalTime      => dek.readOpt[LocalTime](dek.readLocalTime)
      case _: AttrOneOptLocalDateTime  => dek.readOpt[LocalDateTime](dek.readLocalDateTime)
      case _: AttrOneOptOffsetTime     => dek.readOpt[OffsetTime](dek.readOffsetTime)
      case _: AttrOneOptOffsetDateTime => dek.readOpt[OffsetDateTime](dek.readOffsetDateTime)
      case _: AttrOneOptZonedDateTime  => dek.readOpt[ZonedDateTime](dek.readZonedDateTime)
      case _: AttrOneOptUUID           => dek.readOpt[UUID](dek.readUUID)
      case _: AttrOneOptURI            => dek.readOpt[URI](dek.readURI)
      case _: AttrOneOptByte           => dek.readOpt[Byte](dek.readByte)
      case _: AttrOneOptShort          => dek.readOpt[Short](dek.readShort)
      case _: AttrOneOptChar           => dek.readOpt[Char](dek.readChar)
    }
  }

  private def unpickleAttrSetMan(a: AttrSetMan): () => Any = {
    a match {
      case _: AttrSetManID             => dek.readSet[Long](dek.readLong)
      case _: AttrSetManString         => dek.readSet[String](dek.readString)
      case _: AttrSetManInt            => dek.readSet[Int](dek.readInt)
      case _: AttrSetManLong           => dek.readSet[Long](dek.readLong)
      case _: AttrSetManFloat          => dek.readSet[Float](dek.readFloat)
      case _: AttrSetManDouble         => dek.readSet[Double](dek.readDouble)
      case _: AttrSetManBoolean        => dek.readSet[Boolean](dek.readBoolean)
      case _: AttrSetManBigInt         => dek.readSet[BigInt](dek.readBigInt)
      case _: AttrSetManBigDecimal     => dek.readSet[BigDecimal](dek.readBigDecimal)
      case _: AttrSetManDate           => dek.readSet[Date](dek.readDate)
      case _: AttrSetManDuration       => dek.readSet[Duration](dek.readDuration)
      case _: AttrSetManInstant        => dek.readSet[Instant](dek.readInstant)
      case _: AttrSetManLocalDate      => dek.readSet[LocalDate](dek.readLocalDate)
      case _: AttrSetManLocalTime      => dek.readSet[LocalTime](dek.readLocalTime)
      case _: AttrSetManLocalDateTime  => dek.readSet[LocalDateTime](dek.readLocalDateTime)
      case _: AttrSetManOffsetTime     => dek.readSet[OffsetTime](dek.readOffsetTime)
      case _: AttrSetManOffsetDateTime => dek.readSet[OffsetDateTime](dek.readOffsetDateTime)
      case _: AttrSetManZonedDateTime  => dek.readSet[ZonedDateTime](dek.readZonedDateTime)
      case _: AttrSetManUUID           => dek.readSet[UUID](dek.readUUID)
      case _: AttrSetManURI            => dek.readSet[URI](dek.readURI)
      case _: AttrSetManByte           => dek.readSet[Byte](dek.readByte)
      case _: AttrSetManShort          => dek.readSet[Short](dek.readShort)
      case _: AttrSetManChar           => dek.readSet[Char](dek.readChar)
    }
  }

  private def unpickleAttrSetOpt(a: AttrSetOpt): () => Any = {
    a match {
      case _: AttrSetOptID             => dek.readOptSet[Long](dek.readLong)
      case _: AttrSetOptString         => dek.readOptSet[String](dek.readString)
      case _: AttrSetOptInt            => dek.readOptSet[Int](dek.readInt)
      case _: AttrSetOptLong           => dek.readOptSet[Long](dek.readLong)
      case _: AttrSetOptFloat          => dek.readOptSet[Float](dek.readFloat)
      case _: AttrSetOptDouble         => dek.readOptSet[Double](dek.readDouble)
      case _: AttrSetOptBoolean        => dek.readOptSet[Boolean](dek.readBoolean)
      case _: AttrSetOptBigInt         => dek.readOptSet[BigInt](dek.readBigInt)
      case _: AttrSetOptBigDecimal     => dek.readOptSet[BigDecimal](dek.readBigDecimal)
      case _: AttrSetOptDate           => dek.readOptSet[Date](dek.readDate)
      case _: AttrSetOptDuration       => dek.readOptSet[Duration](dek.readDuration)
      case _: AttrSetOptInstant        => dek.readOptSet[Instant](dek.readInstant)
      case _: AttrSetOptLocalDate      => dek.readOptSet[LocalDate](dek.readLocalDate)
      case _: AttrSetOptLocalTime      => dek.readOptSet[LocalTime](dek.readLocalTime)
      case _: AttrSetOptLocalDateTime  => dek.readOptSet[LocalDateTime](dek.readLocalDateTime)
      case _: AttrSetOptOffsetTime     => dek.readOptSet[OffsetTime](dek.readOffsetTime)
      case _: AttrSetOptOffsetDateTime => dek.readOptSet[OffsetDateTime](dek.readOffsetDateTime)
      case _: AttrSetOptZonedDateTime  => dek.readOptSet[ZonedDateTime](dek.readZonedDateTime)
      case _: AttrSetOptUUID           => dek.readOptSet[UUID](dek.readUUID)
      case _: AttrSetOptURI            => dek.readOptSet[URI](dek.readURI)
      case _: AttrSetOptByte           => dek.readOptSet[Byte](dek.readByte)
      case _: AttrSetOptShort          => dek.readOptSet[Short](dek.readShort)
      case _: AttrSetOptChar           => dek.readOptSet[Char](dek.readChar)
    }
  }

  private def unpickleAttrSeqMan(a: AttrSeqMan): () => Any = {
    a match {
      case _: AttrSeqManID             => dek.readSeq[Long](dek.readLong)
      case _: AttrSeqManString         => dek.readSeq[String](dek.readString)
      case _: AttrSeqManInt            => dek.readSeq[Int](dek.readInt)
      case _: AttrSeqManLong           => dek.readSeq[Long](dek.readLong)
      case _: AttrSeqManFloat          => dek.readSeq[Float](dek.readFloat)
      case _: AttrSeqManDouble         => dek.readSeq[Double](dek.readDouble)
      case _: AttrSeqManBoolean        => dek.readSeq[Boolean](dek.readBoolean)
      case _: AttrSeqManBigInt         => dek.readSeq[BigInt](dek.readBigInt)
      case _: AttrSeqManBigDecimal     => dek.readSeq[BigDecimal](dek.readBigDecimal)
      case _: AttrSeqManDate           => dek.readSeq[Date](dek.readDate)
      case _: AttrSeqManDuration       => dek.readSeq[Duration](dek.readDuration)
      case _: AttrSeqManInstant        => dek.readSeq[Instant](dek.readInstant)
      case _: AttrSeqManLocalDate      => dek.readSeq[LocalDate](dek.readLocalDate)
      case _: AttrSeqManLocalTime      => dek.readSeq[LocalTime](dek.readLocalTime)
      case _: AttrSeqManLocalDateTime  => dek.readSeq[LocalDateTime](dek.readLocalDateTime)
      case _: AttrSeqManOffsetTime     => dek.readSeq[OffsetTime](dek.readOffsetTime)
      case _: AttrSeqManOffsetDateTime => dek.readSeq[OffsetDateTime](dek.readOffsetDateTime)
      case _: AttrSeqManZonedDateTime  => dek.readSeq[ZonedDateTime](dek.readZonedDateTime)
      case _: AttrSeqManUUID           => dek.readSeq[UUID](dek.readUUID)
      case _: AttrSeqManURI            => dek.readSeq[URI](dek.readURI)
      case _: AttrSeqManByte           => dek.readByteArray
      case _: AttrSeqManShort          => dek.readSeq[Short](dek.readShort)
      case _: AttrSeqManChar           => dek.readSeq[Char](dek.readChar)
    }
  }

  private def unpickleAttrSeqOpt(a: AttrSeqOpt): () => Any = {
    a match {
      case _: AttrSeqOptID             => dek.readOptSeq[Long](dek.readLong)
      case _: AttrSeqOptString         => dek.readOptSeq[String](dek.readString)
      case _: AttrSeqOptInt            => dek.readOptSeq[Int](dek.readInt)
      case _: AttrSeqOptLong           => dek.readOptSeq[Long](dek.readLong)
      case _: AttrSeqOptFloat          => dek.readOptSeq[Float](dek.readFloat)
      case _: AttrSeqOptDouble         => dek.readOptSeq[Double](dek.readDouble)
      case _: AttrSeqOptBoolean        => dek.readOptSeq[Boolean](dek.readBoolean)
      case _: AttrSeqOptBigInt         => dek.readOptSeq[BigInt](dek.readBigInt)
      case _: AttrSeqOptBigDecimal     => dek.readOptSeq[BigDecimal](dek.readBigDecimal)
      case _: AttrSeqOptDate           => dek.readOptSeq[Date](dek.readDate)
      case _: AttrSeqOptDuration       => dek.readOptSeq[Duration](dek.readDuration)
      case _: AttrSeqOptInstant        => dek.readOptSeq[Instant](dek.readInstant)
      case _: AttrSeqOptLocalDate      => dek.readOptSeq[LocalDate](dek.readLocalDate)
      case _: AttrSeqOptLocalTime      => dek.readOptSeq[LocalTime](dek.readLocalTime)
      case _: AttrSeqOptLocalDateTime  => dek.readOptSeq[LocalDateTime](dek.readLocalDateTime)
      case _: AttrSeqOptOffsetTime     => dek.readOptSeq[OffsetTime](dek.readOffsetTime)
      case _: AttrSeqOptOffsetDateTime => dek.readOptSeq[OffsetDateTime](dek.readOffsetDateTime)
      case _: AttrSeqOptZonedDateTime  => dek.readOptSeq[ZonedDateTime](dek.readZonedDateTime)
      case _: AttrSeqOptUUID           => dek.readOptSeq[UUID](dek.readUUID)
      case _: AttrSeqOptURI            => dek.readOptSeq[URI](dek.readURI)
      case _: AttrSeqOptByte           => dek.readOptByteArray
      case _: AttrSeqOptShort          => dek.readOptSeq[Short](dek.readShort)
      case _: AttrSeqOptChar           => dek.readOptSeq[Char](dek.readChar)
    }
  }


  private def unpickleAttrMapMan(a: AttrMapMan): () => Any = {
    a.op match {
      case Eq => unpickleAttrMapManValue(a)
      case _  => unpickleAttrMapManOther(a)
    }
  }
  private def unpickleAttrMapManValue(a: AttrMapMan): () => Any = {
    a match {
      case _: AttrMapManID             => () => dek.readLong
      case _: AttrMapManString         => () => dek.readString
      case _: AttrMapManInt            => () => dek.readInt
      case _: AttrMapManLong           => () => dek.readLong
      case _: AttrMapManFloat          => () => dek.readFloat
      case _: AttrMapManDouble         => () => dek.readDouble
      case _: AttrMapManBoolean        => () => dek.readBoolean
      case _: AttrMapManBigInt         => () => dek.readBigInt
      case _: AttrMapManBigDecimal     => () => dek.readBigDecimal
      case _: AttrMapManDate           => () => dek.readDate
      case _: AttrMapManDuration       => () => dek.readDuration
      case _: AttrMapManInstant        => () => dek.readInstant
      case _: AttrMapManLocalDate      => () => dek.readLocalDate
      case _: AttrMapManLocalTime      => () => dek.readLocalTime
      case _: AttrMapManLocalDateTime  => () => dek.readLocalDateTime
      case _: AttrMapManOffsetTime     => () => dek.readOffsetTime
      case _: AttrMapManOffsetDateTime => () => dek.readOffsetDateTime
      case _: AttrMapManZonedDateTime  => () => dek.readZonedDateTime
      case _: AttrMapManUUID           => () => dek.readUUID
      case _: AttrMapManURI            => () => dek.readURI
      case _: AttrMapManByte           => () => dek.readByte
      case _: AttrMapManShort          => () => dek.readShort
      case _: AttrMapManChar           => () => dek.readChar
    }
  }
  private def unpickleAttrMapManOther(a: AttrMapMan): () => Any = {
    a match {
      case _: AttrMapManID             => dek.readMap[Long](dek.readLong)
      case _: AttrMapManString         => dek.readMap[String](dek.readString)
      case _: AttrMapManInt            => dek.readMap[Int](dek.readInt)
      case _: AttrMapManLong           => dek.readMap[Long](dek.readLong)
      case _: AttrMapManFloat          => dek.readMap[Float](dek.readFloat)
      case _: AttrMapManDouble         => dek.readMap[Double](dek.readDouble)
      case _: AttrMapManBoolean        => dek.readMap[Boolean](dek.readBoolean)
      case _: AttrMapManBigInt         => dek.readMap[BigInt](dek.readBigInt)
      case _: AttrMapManBigDecimal     => dek.readMap[BigDecimal](dek.readBigDecimal)
      case _: AttrMapManDate           => dek.readMap[Date](dek.readDate)
      case _: AttrMapManDuration       => dek.readMap[Duration](dek.readDuration)
      case _: AttrMapManInstant        => dek.readMap[Instant](dek.readInstant)
      case _: AttrMapManLocalDate      => dek.readMap[LocalDate](dek.readLocalDate)
      case _: AttrMapManLocalTime      => dek.readMap[LocalTime](dek.readLocalTime)
      case _: AttrMapManLocalDateTime  => dek.readMap[LocalDateTime](dek.readLocalDateTime)
      case _: AttrMapManOffsetTime     => dek.readMap[OffsetTime](dek.readOffsetTime)
      case _: AttrMapManOffsetDateTime => dek.readMap[OffsetDateTime](dek.readOffsetDateTime)
      case _: AttrMapManZonedDateTime  => dek.readMap[ZonedDateTime](dek.readZonedDateTime)
      case _: AttrMapManUUID           => dek.readMap[UUID](dek.readUUID)
      case _: AttrMapManURI            => dek.readMap[URI](dek.readURI)
      case _: AttrMapManByte           => dek.readMap[Byte](dek.readByte)
      case _: AttrMapManShort          => dek.readMap[Short](dek.readShort)
      case _: AttrMapManChar           => dek.readMap[Char](dek.readChar)
    }
  }


  private def unpickleAttrMapOpt(a: AttrMapOpt): () => Any = {
    a.op match {
      case Has => unpickleAttrMapOptHas(a)
      case _   => unpickleAttrMapOptOther(a)
    }
  }
  private def unpickleAttrMapOptHas(a: AttrMapOpt): () => Any = {
    a match {
      case _: AttrMapOptID             => dek.readOpt[Long](dek.readLong)
      case _: AttrMapOptString         => dek.readOpt[String](dek.readString)
      case _: AttrMapOptInt            => dek.readOpt[Int](dek.readInt)
      case _: AttrMapOptLong           => dek.readOpt[Long](dek.readLong)
      case _: AttrMapOptFloat          => dek.readOpt[Float](dek.readFloat)
      case _: AttrMapOptDouble         => dek.readOpt[Double](dek.readDouble)
      case _: AttrMapOptBoolean        => dek.readOpt[Boolean](dek.readBoolean)
      case _: AttrMapOptBigInt         => dek.readOpt[BigInt](dek.readBigInt)
      case _: AttrMapOptBigDecimal     => dek.readOpt[BigDecimal](dek.readBigDecimal)
      case _: AttrMapOptDate           => dek.readOpt[Date](dek.readDate)
      case _: AttrMapOptDuration       => dek.readOpt[Duration](dek.readDuration)
      case _: AttrMapOptInstant        => dek.readOpt[Instant](dek.readInstant)
      case _: AttrMapOptLocalDate      => dek.readOpt[LocalDate](dek.readLocalDate)
      case _: AttrMapOptLocalTime      => dek.readOpt[LocalTime](dek.readLocalTime)
      case _: AttrMapOptLocalDateTime  => dek.readOpt[LocalDateTime](dek.readLocalDateTime)
      case _: AttrMapOptOffsetTime     => dek.readOpt[OffsetTime](dek.readOffsetTime)
      case _: AttrMapOptOffsetDateTime => dek.readOpt[OffsetDateTime](dek.readOffsetDateTime)
      case _: AttrMapOptZonedDateTime  => dek.readOpt[ZonedDateTime](dek.readZonedDateTime)
      case _: AttrMapOptUUID           => dek.readOpt[UUID](dek.readUUID)
      case _: AttrMapOptURI            => dek.readOpt[URI](dek.readURI)
      case _: AttrMapOptByte           => dek.readOpt[Byte](dek.readByte)
      case _: AttrMapOptShort          => dek.readOpt[Short](dek.readShort)
      case _: AttrMapOptChar           => dek.readOpt[Char](dek.readChar)
    }
  }
  private def unpickleAttrMapOptOther(a: AttrMapOpt): () => Any = {
    a match {
      case _: AttrMapOptID             => dek.readOptMap[Long](dek.readLong)
      case _: AttrMapOptString         => dek.readOptMap[String](dek.readString)
      case _: AttrMapOptInt            => dek.readOptMap[Int](dek.readInt)
      case _: AttrMapOptLong           => dek.readOptMap[Long](dek.readLong)
      case _: AttrMapOptFloat          => dek.readOptMap[Float](dek.readFloat)
      case _: AttrMapOptDouble         => dek.readOptMap[Double](dek.readDouble)
      case _: AttrMapOptBoolean        => dek.readOptMap[Boolean](dek.readBoolean)
      case _: AttrMapOptBigInt         => dek.readOptMap[BigInt](dek.readBigInt)
      case _: AttrMapOptBigDecimal     => dek.readOptMap[BigDecimal](dek.readBigDecimal)
      case _: AttrMapOptDate           => dek.readOptMap[Date](dek.readDate)
      case _: AttrMapOptDuration       => dek.readOptMap[Duration](dek.readDuration)
      case _: AttrMapOptInstant        => dek.readOptMap[Instant](dek.readInstant)
      case _: AttrMapOptLocalDate      => dek.readOptMap[LocalDate](dek.readLocalDate)
      case _: AttrMapOptLocalTime      => dek.readOptMap[LocalTime](dek.readLocalTime)
      case _: AttrMapOptLocalDateTime  => dek.readOptMap[LocalDateTime](dek.readLocalDateTime)
      case _: AttrMapOptOffsetTime     => dek.readOptMap[OffsetTime](dek.readOffsetTime)
      case _: AttrMapOptOffsetDateTime => dek.readOptMap[OffsetDateTime](dek.readOffsetDateTime)
      case _: AttrMapOptZonedDateTime  => dek.readOptMap[ZonedDateTime](dek.readZonedDateTime)
      case _: AttrMapOptUUID           => dek.readOptMap[UUID](dek.readUUID)
      case _: AttrMapOptURI            => dek.readOptMap[URI](dek.readURI)
      case _: AttrMapOptByte           => dek.readOptMap[Byte](dek.readByte)
      case _: AttrMapOptShort          => dek.readOptMap[Short](dek.readShort)
      case _: AttrMapOptChar           => dek.readOptMap[Char](dek.readChar)
    }
  }
}
