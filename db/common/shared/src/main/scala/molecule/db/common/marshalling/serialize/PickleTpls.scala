package molecule.db.common.marshalling.serialize

import java.net.URI
import java.nio.ByteBuffer
import java.time.*
import java.util.{Date, UUID}
import boopickle.*
import boopickle.BasicPicklers.*
import boopickle.Default.*
import molecule.db.common.marshalling.Boopicklers.*
import molecule.base.error.{ModelError, MoleculeError}
import molecule.core.dataModel.*
import molecule.core.util.MoleculeLogging
import molecule.db.common.util.{ModelUtils, SerializationUtils}
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

case class PickleTpls(
  dataModel: DataModel,
  allTuples: Boolean
) extends PickleTpl_
  with ModelUtils
  with SerializationUtils
  with MoleculeLogging {

  private val prevRefs = ListBuffer.empty[String]
  private val state    = new PickleState(new EncoderSize())
  private val enc      = state.enc
  type DummyNotUsed = Int


  def pickleOffset(tpls: Seq[Any], limit: Int, more: Boolean): ByteBuffer = {
    pickleTpls(tpls)
    enk.writeInt(limit)
    enk.writeBoolean(more)
    state.toByteBuffer
  }

  def pickleCursor(tpls: Seq[Any], cursor: String, more: Boolean): ByteBuffer = {
    pickleTpls(tpls)
    enk.writeString(cursor)
    enk.writeBoolean(more)
    state.toByteBuffer
  }

  def getPickledTpls(tpls: Seq[Any]): ByteBuffer = {
    pickleTpls(tpls)
    state.toByteBuffer
  }

  private def pickleTpls(tpls: Seq[Any]): Unit = {
    enc.writeInt(tpls.size) // encode length of List of Tpl
    if (tpls.nonEmpty) {
      if (allTuples) {
        val pickleTpl = getPickler(dataModel.elements)
        tpls.asInstanceOf[Seq[Product]].foreach(pickleTpl)
      } else {
        val arity = countValueAttrs(dataModel.elements)
        if (arity == 1) {
          val pickleValue = resolvePicklers(dataModel.elements, Nil, 0).head
          tpls.foreach(v => pickleValue(Tuple1(v)))
        } else {
          val pickleTpl = getPickler(dataModel.elements)
          tpls.asInstanceOf[Seq[Product]].foreach(pickleTpl)
        }
      }
    }
  }

  def pickleEither2ByteArray(result: Either[MoleculeError, Seq[Any]]): Array[Byte] = {
    result match {
      case Right(tpls) =>
        enc.writeInt(2) // Encode Right
        pickleTpls(tpls)
      case Left(err)   => LeftPickler[MoleculeError, DummyNotUsed].pickle(Left(err))(using state)
    }
    state.toByteBuffer.toArray
  }

  def pickleEither(result: Either[MoleculeError, Seq[Any]]): ByteBuffer = {
    result match {
      case Right(tpls) =>
        enc.writeInt(2) // Encode Right
        pickleTpls(tpls)
      case Left(err)   => LeftPickler[MoleculeError, DummyNotUsed].pickle(Left(err))(using state)
    }
    state.toByteBuffer
  }


  @tailrec
  final protected def resolvePicklers(
    elements: List[Element],
    picklers: List[Product => Unit],
    tplIndex: Int
  ): List[Product => Unit] = {
    elements match {
      case element :: tail => element match {
        case a: Attr =>
          a match {
            case a: AttrOne =>
              a match {
                case a: AttrOneMan => resolvePicklers(tail, picklers :+ pickleAttrOneMan(a, tplIndex), tplIndex + 1)
                case a: AttrOneOpt => resolvePicklers(tail, picklers :+ pickleAttrOneOpt(a, tplIndex), tplIndex + 1)
                case _: AttrOneTac => resolvePicklers(tail, picklers, tplIndex)
              }
            case a: AttrSet =>
              a match {
                case a: AttrSetMan => resolvePicklers(tail, picklers :+ pickleAttrSetMan(a, tplIndex), tplIndex + 1)
                case a: AttrSetOpt => resolvePicklers(tail, picklers :+ pickleAttrSetOpt(a, tplIndex), tplIndex + 1)
                case _: AttrSetTac => resolvePicklers(tail, picklers, tplIndex)
              }
            case a: AttrSeq =>
              a match {
                case a: AttrSeqMan => resolvePicklers(tail, picklers :+ pickleAttrSeqMan(a, tplIndex), tplIndex + 1)
                case a: AttrSeqOpt => resolvePicklers(tail, picklers :+ pickleAttrSeqOpt(a, tplIndex), tplIndex + 1)
                case _: AttrSeqTac => resolvePicklers(tail, picklers, tplIndex)
              }
            case a: AttrMap =>
              a match {
                case a: AttrMapMan => resolvePicklers(tail, picklers :+ pickleAttrMapMan(a, tplIndex), tplIndex + 1)
                case a: AttrMapOpt => resolvePicklers(tail, picklers :+ pickleAttrMapOpt(a, tplIndex), tplIndex + 1)
                case _: AttrMapTac => resolvePicklers(tail, picklers, tplIndex)
              }
          }

        case Ref(_, refAttr, _, _, _, _) =>
          prevRefs += refAttr
          resolvePicklers(tail, picklers, tplIndex)

        case BackRef(backRef, _, _) =>
          tail.head match {
            case Ref(_, refAttr, _, _, _, _) if prevRefs.contains(refAttr) => throw ModelError(
              s"Can't re-use previous entity ${refAttr.capitalize} after backref _$backRef."
            )
            case _                                                         => // ok
          }
          resolvePicklers(tail, picklers, tplIndex)

        case OptRef(_, optRefElements) =>
          prevRefs.clear()
          resolvePicklers(tail, picklers :+ pickleOptElements(tplIndex, optRefElements), tplIndex + 1)

        case OptEntity(attrs) =>
          prevRefs.clear()
          val all                      = attrs.length
          val pickler: Product => Unit = attrs.collect {
            case e if e.isInstanceOf[Tacit] => e
          }.length match {
            // Pickle None if all optional attributes are tacit
            case `all` => (_: Product) => enc.writeInt(1)
            case _     => pickleOptElements(tplIndex, attrs)
          }
          resolvePicklers(tail, picklers :+ pickler, tplIndex + 1)

        case Nested(_, nestedElements) =>
          prevRefs.clear()
          resolvePicklers(tail, picklers :+ pickleNested(tplIndex, nestedElements), tplIndex + 1)

        case OptNested(_, nestedElements) =>
          prevRefs.clear()
          resolvePicklers(tail, picklers :+ pickleNested(tplIndex, nestedElements), tplIndex + 1)
      }
      case Nil             => picklers
    }
  }

  private def pickleOptElements(
    tplIndex: Int,
    optElements: List[Element]
  ): Product => Unit = {
    // Recursively pickle nested optional refs
    val pickleOptData = getPickler(optElements)
    countValueAttrs(optElements) match {
      case 1 => // Single nested values
        (tpl: Product) => {
          val optValue = tpl.productElement(tplIndex).asInstanceOf[Option[Any]]
          optValue match {
            case None        => enc.writeInt(1)
            case Some(value) =>
              enc.writeInt(2)
              pickleOptData(Tuple1(value))
          }
        }
      case _ =>
        (tpl: Product) => {
          val optTuple = tpl.productElement(tplIndex).asInstanceOf[Option[Product]]
          optTuple match {
            case None      => enc.writeInt(1)
            case Some(tpl) =>
              enc.writeInt(2)
              pickleOptData(tpl)
          }
        }
    }
  }

  private def pickleNested(
    tplIndex: Int,
    nestedElements: List[Element]
  ): Product => Unit = {
    // Recursively pickle nested levels
    val pickleNestedData = getPickler(nestedElements)
    countValueAttrs(nestedElements) match {
      case 1 => // Single nested values
        (tpl: Product) => {
          val nestedValues = tpl.productElement(tplIndex).asInstanceOf[Seq[Any]]
          // encode length
          enc.writeInt(nestedValues.size)
          nestedValues.foreach { nestedValue =>
            pickleNestedData(Tuple1(nestedValue))
          }
        }
      case _ =>
        (tpl: Product) => {
          val nestedTpls = tpl.productElement(tplIndex).asInstanceOf[Seq[Product]]
          // encode length
          enc.writeInt(nestedTpls.size)
          nestedTpls.foreach(pickleNestedData)
        }
    }
  }

  object enk {
    // todo: find out why we can't encode all types directly as-is
    lazy val writeString: String => Unit = (value: String) => enc.writeString(value)

    // todo: Skip brute forcing values to Strings
    lazy val writeInt   : Int => Unit    = (value: Int) => enc.writeString(value.toString)
    lazy val writeLong  : Long => Unit   = (value: Long) => enc.writeString(value.toString)
    lazy val writeFloat : Float => Unit  = (value: Float) => enc.writeString(value.toString)
    lazy val writeDouble: Double => Unit = (value: Double) => enc.writeString(value.toString)

    // todo: how can we make these work instead?
    //    lazy val writeInt   : Int => Unit    = (value: Int) => enc.writeInt(value)
    //    lazy val writeLong  : Long => Unit   = (value: Long) => enc.writeLong(value)
    //    lazy val writeFloat : Float => Unit  = (value: Float) => enc.writeFloat(value)
    //    lazy val writeDouble: Double => Unit = (value: Double) => enc.writeDouble(value)

    lazy val writeBoolean       : Boolean => Unit        = (value: Boolean) => BooleanPickler.pickle(value)(using state)
    lazy val writeBigInt        : BigInt => Unit         = (value: BigInt) => {
      //      val ba = value.toByteArray
      //      enc.writeInt(ba.length)
      //      ba.foreach(enc.writeByte)
      enc.writeString(value.toString)
    }
    lazy val writeBigDecimal    : BigDecimal => Unit     = (value: BigDecimal) => {
      //      enc.writeInt(value.scale)
      //      val ba = value.underlying().unscaledValue.toByteArray
      //      enc.writeInt(ba.length)
      //      ba.foreach(enc.writeByte)
      enc.writeString(value.toString)
    }
    lazy val writeDate          : Date => Unit           = (value: Date) => enc.writeString(value.getTime.toString)
    lazy val writeDuration      : Duration => Unit       = (value: Duration) => enc.writeString(value.toString)
    lazy val writeInstant       : Instant => Unit        = (value: Instant) => enc.writeString(value.toString)
    lazy val writeLocalDate     : LocalDate => Unit      = (value: LocalDate) => enc.writeString(value.toString)
    lazy val writeLocalTime     : LocalTime => Unit      = (value: LocalTime) => enc.writeString(value.toString)
    lazy val writeLocalDateTime : LocalDateTime => Unit  = (value: LocalDateTime) => enc.writeString(value.toString)
    lazy val writeOffsetTime    : OffsetTime => Unit     = (value: OffsetTime) => enc.writeString(value.toString)
    lazy val writeOffsetDateTime: OffsetDateTime => Unit = (value: OffsetDateTime) => enc.writeString(value.toString)
    lazy val writeZonedDateTime : ZonedDateTime => Unit  = (value: ZonedDateTime) => enc.writeString(value.toString)
    lazy val writeUUID          : UUID => Unit           = (value: UUID) => enc.writeString(value.toString)
    lazy val writeURI           : URI => Unit            = (value: URI) => enc.writeString(value.toString)
    lazy val writeByte          : Byte => Unit           = (value: Byte) => enc.writeByte(value)
    lazy val writeShort         : Short => Unit          = (value: Short) => enc.writeString(value.toString)
    lazy val writeChar          : Char => Unit           = (value: Char) => enc.writeChar(value)


    def writeSet[T](tplIndex: Int, writer: T => Unit): Product => Unit = {
      (tpl: Product) => {
        val set = tpl.productElement(tplIndex).asInstanceOf[Set[T]]
        enc.writeInt(set.size)
        set.foreach(v => writer(v))
      }
    }

    def writeSeq[T](tplIndex: Int, writer: T => Unit): Product => Unit = {
      (tpl: Product) => {
        val seq = tpl.productElement(tplIndex).asInstanceOf[Seq[T]]
        enc.writeInt(seq.size)
        seq.foreach(v => writer(v))
      }
    }

    def writeByteArray(tplIndex: Int): Product => Unit = {
      (tpl: Product) => {
        val byteArray = tpl.productElement(tplIndex).asInstanceOf[Array[Byte]]
        // Don't know why we can't use enc.writeByteArray(byteArray) instead
        enc.writeInt(byteArray.length)
        byteArray.foreach(byte => enc.writeByte(byte))
      }
    }

    def writeMap[T](tplIndex: Int, writer: T => Unit): Product => Unit = {
      (tpl: Product) => {
        val map = tpl.productElement(tplIndex).asInstanceOf[Map[String, T]]
        enc.writeInt(map.size)
        map.foreach { case (k, v) =>
          enc.writeString(k)
          writer(v)
        }
      }
    }

    def writeOpt[T](tplIndex: Int, writer: T => Unit): Product => Unit = {
      (tpl: Product) => {
        val opt = tpl.productElement(tplIndex).asInstanceOf[Option[T]]
        opt match {
          case Some(x) =>
            enc.writeInt(2)
            writer(x)
          case None    =>
            enc.writeInt(1)
        }
      }
    }

    def writeOptSet[T](tplIndex: Int, writer: T => Unit): Product => Unit = {
      (tpl: Product) => {
        val opt = tpl.productElement(tplIndex).asInstanceOf[Option[Iterable[T]]]
        opt match {
          case Some(set) =>
            enc.writeInt(2)
            enc.writeInt(set.size)
            set.foreach(v => writer(v))
          case None      =>
            enc.writeInt(1)
        }
      }
    }

    def writeOptSeq[T](tplIndex: Int, writer: T => Unit): Product => Unit = {
      (tpl: Product) => {
        val opt = tpl.productElement(tplIndex).asInstanceOf[Option[Seq[T]]]
        opt match {
          case Some(seq) =>
            enc.writeInt(2)
            enc.writeInt(seq.size)
            seq.foreach(v => writer(v))
          case None      =>
            enc.writeInt(1)
        }
      }
    }

    def writeOptByteArray(tplIndex: Int): Product => Unit = {
      (tpl: Product) => {
        val opt = tpl.productElement(tplIndex).asInstanceOf[Option[Array[Byte]]]
        opt match {
          case Some(byteArray) =>
            enc.writeInt(2)
            // Don't know why we can't use enc.writeByteArray(byteArray) instead
            enc.writeInt(byteArray.length)
            byteArray.foreach(byte => enc.writeByte(byte))
          case None            =>
            enc.writeInt(1)
        }
      }
    }

    def writeOptMap[T](tplIndex: Int, writer: T => Unit): Product => Unit = {
      (tpl: Product) => {
        val opt = tpl.productElement(tplIndex).asInstanceOf[Option[Map[String, T]]]
        opt match {
          case Some(map) =>
            enc.writeInt(2)
            enc.writeInt(map.size)
            map.foreach { case (k, v) =>
              enc.writeString(k)
              writer(v)
            }
          case None      =>
            enc.writeInt(1)
        }
      }
    }
  }

  private def pickleAttrOneMan(a: AttrOneMan, tplIndex: Int): Product => Unit = {
    a.op match {
      case Fn(_, kw, _, _, _) => kw match {
        case "count" | "countDistinct"                => pickleAttrOneManInt(tplIndex)
        case "distinct" | "mins" | "maxs" | "samples" => pickleAttrOneManSet(a, tplIndex)
        case "avg" | "variance" | "stddev"            => pickleAttrOneManDouble(tplIndex)
        case _                                        => pickleAttrOneManV(a, tplIndex)
      }
      case _                  => pickleAttrOneManV(a, tplIndex)
    }
  }
  private def pickleAttrOneManInt(tplIndex: Int): Product => Unit = {
    (tpl: Product) => enk.writeInt(tpl.productElement(tplIndex).toString.toInt)
  }
  private def pickleAttrOneManDouble(tplIndex: Int): Product => Unit = {
    (tpl: Product) => enk.writeDouble(tpl.productElement(tplIndex).asInstanceOf[Double])

  }
  private def pickleAttrOneManV(a: AttrOneMan, tplIndex: Int): Product => Unit = {
    a match {
      case _: AttrOneManID             => (tpl: Product) => enk.writeLong(tpl.productElement(tplIndex).asInstanceOf[Long])
      case _: AttrOneManString         => (tpl: Product) => enk.writeString(tpl.productElement(tplIndex).asInstanceOf[String])
      case _: AttrOneManInt            => (tpl: Product) => enk.writeInt(tpl.productElement(tplIndex).toString.toInt)
      case _: AttrOneManLong           => (tpl: Product) => enk.writeLong(tpl.productElement(tplIndex).asInstanceOf[Long])
      case _: AttrOneManFloat          => (tpl: Product) => enk.writeFloat(tpl.productElement(tplIndex).asInstanceOf[Float])
      case _: AttrOneManDouble         => (tpl: Product) => enk.writeDouble(tpl.productElement(tplIndex).asInstanceOf[Double])
      case _: AttrOneManBoolean        => (tpl: Product) => enk.writeBoolean(tpl.productElement(tplIndex).asInstanceOf[Boolean])
      case _: AttrOneManBigInt         => (tpl: Product) => enk.writeBigInt(tpl.productElement(tplIndex).asInstanceOf[BigInt])
      case _: AttrOneManBigDecimal     => (tpl: Product) => enk.writeBigDecimal(tpl.productElement(tplIndex).asInstanceOf[BigDecimal])
      case _: AttrOneManDate           => (tpl: Product) => enk.writeDate(tpl.productElement(tplIndex).asInstanceOf[Date])
      case _: AttrOneManDuration       => (tpl: Product) => enk.writeDuration(tpl.productElement(tplIndex).asInstanceOf[Duration])
      case _: AttrOneManInstant        => (tpl: Product) => enk.writeInstant(tpl.productElement(tplIndex).asInstanceOf[Instant])
      case _: AttrOneManLocalDate      => (tpl: Product) => enk.writeLocalDate(tpl.productElement(tplIndex).asInstanceOf[LocalDate])
      case _: AttrOneManLocalTime      => (tpl: Product) => enk.writeLocalTime(tpl.productElement(tplIndex).asInstanceOf[LocalTime])
      case _: AttrOneManLocalDateTime  => (tpl: Product) => enk.writeLocalDateTime(tpl.productElement(tplIndex).asInstanceOf[LocalDateTime])
      case _: AttrOneManOffsetTime     => (tpl: Product) => enk.writeOffsetTime(tpl.productElement(tplIndex).asInstanceOf[OffsetTime])
      case _: AttrOneManOffsetDateTime => (tpl: Product) => enk.writeOffsetDateTime(tpl.productElement(tplIndex).asInstanceOf[OffsetDateTime])
      case _: AttrOneManZonedDateTime  => (tpl: Product) => enk.writeZonedDateTime(tpl.productElement(tplIndex).asInstanceOf[ZonedDateTime])
      case _: AttrOneManUUID           => (tpl: Product) => enk.writeUUID(tpl.productElement(tplIndex).asInstanceOf[UUID])
      case _: AttrOneManURI            => (tpl: Product) => enk.writeURI(tpl.productElement(tplIndex).asInstanceOf[URI])
      case _: AttrOneManByte           => (tpl: Product) => enk.writeByte(tpl.productElement(tplIndex).asInstanceOf[Byte])
      case _: AttrOneManShort          => (tpl: Product) => enk.writeShort(tpl.productElement(tplIndex).asInstanceOf[Short])
      case _: AttrOneManChar           => (tpl: Product) => enk.writeChar(tpl.productElement(tplIndex).asInstanceOf[Char])
    }
  }

  private def pickleAttrOneManSet(a: AttrOneMan, tplIndex: Int): Product => Unit = {
    a match {
      case _: AttrOneManID             => enk.writeSet[Long](tplIndex, enk.writeLong)
      case _: AttrOneManString         => enk.writeSet[String](tplIndex, enk.writeString)
      case _: AttrOneManInt            => enk.writeSet[Int](tplIndex, enk.writeInt)
      case _: AttrOneManLong           => enk.writeSet[Long](tplIndex, enk.writeLong)
      case _: AttrOneManFloat          => enk.writeSet[Float](tplIndex, enk.writeFloat)
      case _: AttrOneManDouble         => enk.writeSet[Double](tplIndex, enk.writeDouble)
      case _: AttrOneManBoolean        => enk.writeSet[Boolean](tplIndex, enk.writeBoolean)
      case _: AttrOneManBigInt         => enk.writeSet[BigInt](tplIndex, enk.writeBigInt)
      case _: AttrOneManBigDecimal     => enk.writeSet[BigDecimal](tplIndex, enk.writeBigDecimal)
      case _: AttrOneManDate           => enk.writeSet[Date](tplIndex, enk.writeDate)
      case _: AttrOneManDuration       => enk.writeSet[Duration](tplIndex, enk.writeDuration)
      case _: AttrOneManInstant        => enk.writeSet[Instant](tplIndex, enk.writeInstant)
      case _: AttrOneManLocalDate      => enk.writeSet[LocalDate](tplIndex, enk.writeLocalDate)
      case _: AttrOneManLocalTime      => enk.writeSet[LocalTime](tplIndex, enk.writeLocalTime)
      case _: AttrOneManLocalDateTime  => enk.writeSet[LocalDateTime](tplIndex, enk.writeLocalDateTime)
      case _: AttrOneManOffsetTime     => enk.writeSet[OffsetTime](tplIndex, enk.writeOffsetTime)
      case _: AttrOneManOffsetDateTime => enk.writeSet[OffsetDateTime](tplIndex, enk.writeOffsetDateTime)
      case _: AttrOneManZonedDateTime  => enk.writeSet[ZonedDateTime](tplIndex, enk.writeZonedDateTime)
      case _: AttrOneManUUID           => enk.writeSet[UUID](tplIndex, enk.writeUUID)
      case _: AttrOneManURI            => enk.writeSet[URI](tplIndex, enk.writeURI)
      case _: AttrOneManByte           => enk.writeSet[Byte](tplIndex, enk.writeByte)
      case _: AttrOneManShort          => enk.writeSet[Short](tplIndex, enk.writeShort)
      case _: AttrOneManChar           => enk.writeSet[Char](tplIndex, enk.writeChar)
    }
  }

  private def pickleAttrOneOpt(a: AttrOneOpt, tplIndex: Int): Product => Unit = {
    a match {
      case _: AttrOneOptID             => enk.writeOpt[Long](tplIndex, enk.writeLong)
      case _: AttrOneOptString         => enk.writeOpt[String](tplIndex, enk.writeString)
      case _: AttrOneOptInt            => enk.writeOpt[Int](tplIndex, enk.writeInt)
      case _: AttrOneOptLong           => enk.writeOpt[Long](tplIndex, enk.writeLong)
      case _: AttrOneOptFloat          => enk.writeOpt[Float](tplIndex, enk.writeFloat)
      case _: AttrOneOptDouble         => enk.writeOpt[Double](tplIndex, enk.writeDouble)
      case _: AttrOneOptBoolean        => enk.writeOpt[Boolean](tplIndex, enk.writeBoolean)
      case _: AttrOneOptBigInt         => enk.writeOpt[BigInt](tplIndex, enk.writeBigInt)
      case _: AttrOneOptBigDecimal     => enk.writeOpt[BigDecimal](tplIndex, enk.writeBigDecimal)
      case _: AttrOneOptDate           => enk.writeOpt[Date](tplIndex, enk.writeDate)
      case _: AttrOneOptDuration       => enk.writeOpt[Duration](tplIndex, enk.writeDuration)
      case _: AttrOneOptInstant        => enk.writeOpt[Instant](tplIndex, enk.writeInstant)
      case _: AttrOneOptLocalDate      => enk.writeOpt[LocalDate](tplIndex, enk.writeLocalDate)
      case _: AttrOneOptLocalTime      => enk.writeOpt[LocalTime](tplIndex, enk.writeLocalTime)
      case _: AttrOneOptLocalDateTime  => enk.writeOpt[LocalDateTime](tplIndex, enk.writeLocalDateTime)
      case _: AttrOneOptOffsetTime     => enk.writeOpt[OffsetTime](tplIndex, enk.writeOffsetTime)
      case _: AttrOneOptOffsetDateTime => enk.writeOpt[OffsetDateTime](tplIndex, enk.writeOffsetDateTime)
      case _: AttrOneOptZonedDateTime  => enk.writeOpt[ZonedDateTime](tplIndex, enk.writeZonedDateTime)
      case _: AttrOneOptUUID           => enk.writeOpt[UUID](tplIndex, enk.writeUUID)
      case _: AttrOneOptURI            => enk.writeOpt[URI](tplIndex, enk.writeURI)
      case _: AttrOneOptByte           => enk.writeOpt[Byte](tplIndex, enk.writeByte)
      case _: AttrOneOptShort          => enk.writeOpt[Short](tplIndex, enk.writeShort)
      case _: AttrOneOptChar           => enk.writeOpt[Char](tplIndex, enk.writeChar)
    }
  }

  private def pickleAttrSetMan(a: AttrSetMan, tplIndex: Int): Product => Unit = {
    a match {
      case _: AttrSetManID             => enk.writeSet[Long](tplIndex, enk.writeLong)
      case _: AttrSetManString         => enk.writeSet[String](tplIndex, enk.writeString)
      case _: AttrSetManInt            => enk.writeSet[Int](tplIndex, enk.writeInt)
      case _: AttrSetManLong           => enk.writeSet[Long](tplIndex, enk.writeLong)
      case _: AttrSetManFloat          => enk.writeSet[Float](tplIndex, enk.writeFloat)
      case _: AttrSetManDouble         => enk.writeSet[Double](tplIndex, enk.writeDouble)
      case _: AttrSetManBoolean        => enk.writeSet[Boolean](tplIndex, enk.writeBoolean)
      case _: AttrSetManBigInt         => enk.writeSet[BigInt](tplIndex, enk.writeBigInt)
      case _: AttrSetManBigDecimal     => enk.writeSet[BigDecimal](tplIndex, enk.writeBigDecimal)
      case _: AttrSetManDate           => enk.writeSet[Date](tplIndex, enk.writeDate)
      case _: AttrSetManDuration       => enk.writeSet[Duration](tplIndex, enk.writeDuration)
      case _: AttrSetManInstant        => enk.writeSet[Instant](tplIndex, enk.writeInstant)
      case _: AttrSetManLocalDate      => enk.writeSet[LocalDate](tplIndex, enk.writeLocalDate)
      case _: AttrSetManLocalTime      => enk.writeSet[LocalTime](tplIndex, enk.writeLocalTime)
      case _: AttrSetManLocalDateTime  => enk.writeSet[LocalDateTime](tplIndex, enk.writeLocalDateTime)
      case _: AttrSetManOffsetTime     => enk.writeSet[OffsetTime](tplIndex, enk.writeOffsetTime)
      case _: AttrSetManOffsetDateTime => enk.writeSet[OffsetDateTime](tplIndex, enk.writeOffsetDateTime)
      case _: AttrSetManZonedDateTime  => enk.writeSet[ZonedDateTime](tplIndex, enk.writeZonedDateTime)
      case _: AttrSetManUUID           => enk.writeSet[UUID](tplIndex, enk.writeUUID)
      case _: AttrSetManURI            => enk.writeSet[URI](tplIndex, enk.writeURI)
      case _: AttrSetManByte           => enk.writeSet[Byte](tplIndex, enk.writeByte)
      case _: AttrSetManShort          => enk.writeSet[Short](tplIndex, enk.writeShort)
      case _: AttrSetManChar           => enk.writeSet[Char](tplIndex, enk.writeChar)
    }
  }

  private def pickleAttrSetOpt(a: AttrSetOpt, tplIndex: Int): Product => Unit = {
    a match {
      case _: AttrSetOptID             => enk.writeOptSet[Long](tplIndex, enk.writeLong)
      case _: AttrSetOptString         => enk.writeOptSet[String](tplIndex, enk.writeString)
      case _: AttrSetOptInt            => enk.writeOptSet[Int](tplIndex, enk.writeInt)
      case _: AttrSetOptLong           => enk.writeOptSet[Long](tplIndex, enk.writeLong)
      case _: AttrSetOptFloat          => enk.writeOptSet[Float](tplIndex, enk.writeFloat)
      case _: AttrSetOptDouble         => enk.writeOptSet[Double](tplIndex, enk.writeDouble)
      case _: AttrSetOptBoolean        => enk.writeOptSet[Boolean](tplIndex, enk.writeBoolean)
      case _: AttrSetOptBigInt         => enk.writeOptSet[BigInt](tplIndex, enk.writeBigInt)
      case _: AttrSetOptBigDecimal     => enk.writeOptSet[BigDecimal](tplIndex, enk.writeBigDecimal)
      case _: AttrSetOptDate           => enk.writeOptSet[Date](tplIndex, enk.writeDate)
      case _: AttrSetOptDuration       => enk.writeOptSet[Duration](tplIndex, enk.writeDuration)
      case _: AttrSetOptInstant        => enk.writeOptSet[Instant](tplIndex, enk.writeInstant)
      case _: AttrSetOptLocalDate      => enk.writeOptSet[LocalDate](tplIndex, enk.writeLocalDate)
      case _: AttrSetOptLocalTime      => enk.writeOptSet[LocalTime](tplIndex, enk.writeLocalTime)
      case _: AttrSetOptLocalDateTime  => enk.writeOptSet[LocalDateTime](tplIndex, enk.writeLocalDateTime)
      case _: AttrSetOptOffsetTime     => enk.writeOptSet[OffsetTime](tplIndex, enk.writeOffsetTime)
      case _: AttrSetOptOffsetDateTime => enk.writeOptSet[OffsetDateTime](tplIndex, enk.writeOffsetDateTime)
      case _: AttrSetOptZonedDateTime  => enk.writeOptSet[ZonedDateTime](tplIndex, enk.writeZonedDateTime)
      case _: AttrSetOptUUID           => enk.writeOptSet[UUID](tplIndex, enk.writeUUID)
      case _: AttrSetOptURI            => enk.writeOptSet[URI](tplIndex, enk.writeURI)
      case _: AttrSetOptByte           => enk.writeOptSet[Byte](tplIndex, enk.writeByte)
      case _: AttrSetOptShort          => enk.writeOptSet[Short](tplIndex, enk.writeShort)
      case _: AttrSetOptChar           => enk.writeOptSet[Char](tplIndex, enk.writeChar)
    }
  }

  private def pickleAttrSeqMan(a: AttrSeqMan, tplIndex: Int): Product => Unit = {
    a match {
      case _: AttrSeqManID             => enk.writeSeq[Long](tplIndex, enk.writeLong)
      case _: AttrSeqManString         => enk.writeSeq[String](tplIndex, enk.writeString)
      case _: AttrSeqManInt            => enk.writeSeq[Int](tplIndex, enk.writeInt)
      case _: AttrSeqManLong           => enk.writeSeq[Long](tplIndex, enk.writeLong)
      case _: AttrSeqManFloat          => enk.writeSeq[Float](tplIndex, enk.writeFloat)
      case _: AttrSeqManDouble         => enk.writeSeq[Double](tplIndex, enk.writeDouble)
      case _: AttrSeqManBoolean        => enk.writeSeq[Boolean](tplIndex, enk.writeBoolean)
      case _: AttrSeqManBigInt         => enk.writeSeq[BigInt](tplIndex, enk.writeBigInt)
      case _: AttrSeqManBigDecimal     => enk.writeSeq[BigDecimal](tplIndex, enk.writeBigDecimal)
      case _: AttrSeqManDate           => enk.writeSeq[Date](tplIndex, enk.writeDate)
      case _: AttrSeqManDuration       => enk.writeSeq[Duration](tplIndex, enk.writeDuration)
      case _: AttrSeqManInstant        => enk.writeSeq[Instant](tplIndex, enk.writeInstant)
      case _: AttrSeqManLocalDate      => enk.writeSeq[LocalDate](tplIndex, enk.writeLocalDate)
      case _: AttrSeqManLocalTime      => enk.writeSeq[LocalTime](tplIndex, enk.writeLocalTime)
      case _: AttrSeqManLocalDateTime  => enk.writeSeq[LocalDateTime](tplIndex, enk.writeLocalDateTime)
      case _: AttrSeqManOffsetTime     => enk.writeSeq[OffsetTime](tplIndex, enk.writeOffsetTime)
      case _: AttrSeqManOffsetDateTime => enk.writeSeq[OffsetDateTime](tplIndex, enk.writeOffsetDateTime)
      case _: AttrSeqManZonedDateTime  => enk.writeSeq[ZonedDateTime](tplIndex, enk.writeZonedDateTime)
      case _: AttrSeqManUUID           => enk.writeSeq[UUID](tplIndex, enk.writeUUID)
      case _: AttrSeqManURI            => enk.writeSeq[URI](tplIndex, enk.writeURI)
      case _: AttrSeqManByte           => enk.writeByteArray(tplIndex)
      case _: AttrSeqManShort          => enk.writeSeq[Short](tplIndex, enk.writeShort)
      case _: AttrSeqManChar           => enk.writeSeq[Char](tplIndex, enk.writeChar)
    }
  }

  private def pickleAttrSeqOpt(a: AttrSeqOpt, tplIndex: Int): Product => Unit = {
    a match {
      case _: AttrSeqOptID             => enk.writeOptSeq[Long](tplIndex, enk.writeLong)
      case _: AttrSeqOptString         => enk.writeOptSeq[String](tplIndex, enk.writeString)
      case _: AttrSeqOptInt            => enk.writeOptSeq[Int](tplIndex, enk.writeInt)
      case _: AttrSeqOptLong           => enk.writeOptSeq[Long](tplIndex, enk.writeLong)
      case _: AttrSeqOptFloat          => enk.writeOptSeq[Float](tplIndex, enk.writeFloat)
      case _: AttrSeqOptDouble         => enk.writeOptSeq[Double](tplIndex, enk.writeDouble)
      case _: AttrSeqOptBoolean        => enk.writeOptSeq[Boolean](tplIndex, enk.writeBoolean)
      case _: AttrSeqOptBigInt         => enk.writeOptSeq[BigInt](tplIndex, enk.writeBigInt)
      case _: AttrSeqOptBigDecimal     => enk.writeOptSeq[BigDecimal](tplIndex, enk.writeBigDecimal)
      case _: AttrSeqOptDate           => enk.writeOptSeq[Date](tplIndex, enk.writeDate)
      case _: AttrSeqOptDuration       => enk.writeOptSeq[Duration](tplIndex, enk.writeDuration)
      case _: AttrSeqOptInstant        => enk.writeOptSeq[Instant](tplIndex, enk.writeInstant)
      case _: AttrSeqOptLocalDate      => enk.writeOptSeq[LocalDate](tplIndex, enk.writeLocalDate)
      case _: AttrSeqOptLocalTime      => enk.writeOptSeq[LocalTime](tplIndex, enk.writeLocalTime)
      case _: AttrSeqOptLocalDateTime  => enk.writeOptSeq[LocalDateTime](tplIndex, enk.writeLocalDateTime)
      case _: AttrSeqOptOffsetTime     => enk.writeOptSeq[OffsetTime](tplIndex, enk.writeOffsetTime)
      case _: AttrSeqOptOffsetDateTime => enk.writeOptSeq[OffsetDateTime](tplIndex, enk.writeOffsetDateTime)
      case _: AttrSeqOptZonedDateTime  => enk.writeOptSeq[ZonedDateTime](tplIndex, enk.writeZonedDateTime)
      case _: AttrSeqOptUUID           => enk.writeOptSeq[UUID](tplIndex, enk.writeUUID)
      case _: AttrSeqOptURI            => enk.writeOptSeq[URI](tplIndex, enk.writeURI)
      case _: AttrSeqOptByte           => enk.writeOptByteArray(tplIndex)
      case _: AttrSeqOptShort          => enk.writeOptSeq[Short](tplIndex, enk.writeShort)
      case _: AttrSeqOptChar           => enk.writeOptSeq[Char](tplIndex, enk.writeChar)
    }
  }


  private def pickleAttrMapMan(a: AttrMapMan, tplIndex: Int): Product => Unit = {
    a.op match {
      case Eq => pickleAttrMapManValue(a, tplIndex)
      case _  => pickleAttrMapManOther(a, tplIndex)
    }
  }
  private def pickleAttrMapManValue(a: AttrMapMan, tplIndex: Int): Product => Unit = {
    a match {
      case _: AttrMapManID             => (tpl: Product) => enk.writeLong(tpl.productElement(tplIndex).asInstanceOf[Long])
      case _: AttrMapManString         => (tpl: Product) => enk.writeString(tpl.productElement(tplIndex).asInstanceOf[String])
      case _: AttrMapManInt            => (tpl: Product) => enk.writeInt(tpl.productElement(tplIndex).toString.toInt)
      case _: AttrMapManLong           => (tpl: Product) => enk.writeLong(tpl.productElement(tplIndex).asInstanceOf[Long])
      case _: AttrMapManFloat          => (tpl: Product) => enk.writeFloat(tpl.productElement(tplIndex).asInstanceOf[Float])
      case _: AttrMapManDouble         => (tpl: Product) => enk.writeDouble(tpl.productElement(tplIndex).asInstanceOf[Double])
      case _: AttrMapManBoolean        => (tpl: Product) => enk.writeBoolean(tpl.productElement(tplIndex).asInstanceOf[Boolean])
      case _: AttrMapManBigInt         => (tpl: Product) => enk.writeBigInt(tpl.productElement(tplIndex).asInstanceOf[BigInt])
      case _: AttrMapManBigDecimal     => (tpl: Product) => enk.writeBigDecimal(tpl.productElement(tplIndex).asInstanceOf[BigDecimal])
      case _: AttrMapManDate           => (tpl: Product) => enk.writeDate(tpl.productElement(tplIndex).asInstanceOf[Date])
      case _: AttrMapManDuration       => (tpl: Product) => enk.writeDuration(tpl.productElement(tplIndex).asInstanceOf[Duration])
      case _: AttrMapManInstant        => (tpl: Product) => enk.writeInstant(tpl.productElement(tplIndex).asInstanceOf[Instant])
      case _: AttrMapManLocalDate      => (tpl: Product) => enk.writeLocalDate(tpl.productElement(tplIndex).asInstanceOf[LocalDate])
      case _: AttrMapManLocalTime      => (tpl: Product) => enk.writeLocalTime(tpl.productElement(tplIndex).asInstanceOf[LocalTime])
      case _: AttrMapManLocalDateTime  => (tpl: Product) => enk.writeLocalDateTime(tpl.productElement(tplIndex).asInstanceOf[LocalDateTime])
      case _: AttrMapManOffsetTime     => (tpl: Product) => enk.writeOffsetTime(tpl.productElement(tplIndex).asInstanceOf[OffsetTime])
      case _: AttrMapManOffsetDateTime => (tpl: Product) => enk.writeOffsetDateTime(tpl.productElement(tplIndex).asInstanceOf[OffsetDateTime])
      case _: AttrMapManZonedDateTime  => (tpl: Product) => enk.writeZonedDateTime(tpl.productElement(tplIndex).asInstanceOf[ZonedDateTime])
      case _: AttrMapManUUID           => (tpl: Product) => enk.writeUUID(tpl.productElement(tplIndex).asInstanceOf[UUID])
      case _: AttrMapManURI            => (tpl: Product) => enk.writeURI(tpl.productElement(tplIndex).asInstanceOf[URI])
      case _: AttrMapManByte           => (tpl: Product) => enk.writeByte(tpl.productElement(tplIndex).asInstanceOf[Byte])
      case _: AttrMapManShort          => (tpl: Product) => enk.writeShort(tpl.productElement(tplIndex).asInstanceOf[Short])
      case _: AttrMapManChar           => (tpl: Product) => enk.writeChar(tpl.productElement(tplIndex).asInstanceOf[Char])
    }
  }
  private def pickleAttrMapManOther(a: AttrMapMan, tplIndex: Int): Product => Unit = {
    a match {
      case _: AttrMapManID             => enk.writeMap[Long](tplIndex, enk.writeLong)
      case _: AttrMapManString         => enk.writeMap[String](tplIndex, enk.writeString)
      case _: AttrMapManInt            => enk.writeMap[Int](tplIndex, enk.writeInt)
      case _: AttrMapManLong           => enk.writeMap[Long](tplIndex, enk.writeLong)
      case _: AttrMapManFloat          => enk.writeMap[Float](tplIndex, enk.writeFloat)
      case _: AttrMapManDouble         => enk.writeMap[Double](tplIndex, enk.writeDouble)
      case _: AttrMapManBoolean        => enk.writeMap[Boolean](tplIndex, enk.writeBoolean)
      case _: AttrMapManBigInt         => enk.writeMap[BigInt](tplIndex, enk.writeBigInt)
      case _: AttrMapManBigDecimal     => enk.writeMap[BigDecimal](tplIndex, enk.writeBigDecimal)
      case _: AttrMapManDate           => enk.writeMap[Date](tplIndex, enk.writeDate)
      case _: AttrMapManDuration       => enk.writeMap[Duration](tplIndex, enk.writeDuration)
      case _: AttrMapManInstant        => enk.writeMap[Instant](tplIndex, enk.writeInstant)
      case _: AttrMapManLocalDate      => enk.writeMap[LocalDate](tplIndex, enk.writeLocalDate)
      case _: AttrMapManLocalTime      => enk.writeMap[LocalTime](tplIndex, enk.writeLocalTime)
      case _: AttrMapManLocalDateTime  => enk.writeMap[LocalDateTime](tplIndex, enk.writeLocalDateTime)
      case _: AttrMapManOffsetTime     => enk.writeMap[OffsetTime](tplIndex, enk.writeOffsetTime)
      case _: AttrMapManOffsetDateTime => enk.writeMap[OffsetDateTime](tplIndex, enk.writeOffsetDateTime)
      case _: AttrMapManZonedDateTime  => enk.writeMap[ZonedDateTime](tplIndex, enk.writeZonedDateTime)
      case _: AttrMapManUUID           => enk.writeMap[UUID](tplIndex, enk.writeUUID)
      case _: AttrMapManURI            => enk.writeMap[URI](tplIndex, enk.writeURI)
      case _: AttrMapManByte           => enk.writeMap[Byte](tplIndex, enk.writeByte)
      case _: AttrMapManShort          => enk.writeMap[Short](tplIndex, enk.writeShort)
      case _: AttrMapManChar           => enk.writeMap[Char](tplIndex, enk.writeChar)
    }
  }

  private def pickleAttrMapOpt(a: AttrMapOpt, tplIndex: Int): Product => Unit = {
    a.op match {
      case Has => pickleAttrMapOptHas(a, tplIndex)
      case _   => pickleAttrMapOptOther(a, tplIndex)
    }
  }
  private def pickleAttrMapOptHas(a: AttrMapOpt, tplIndex: Int): Product => Unit = {
    a match {
      case _: AttrMapOptID             => enk.writeOpt[Long](tplIndex, enk.writeLong)
      case _: AttrMapOptString         => enk.writeOpt[String](tplIndex, enk.writeString)
      case _: AttrMapOptInt            => enk.writeOpt[Int](tplIndex, enk.writeInt)
      case _: AttrMapOptLong           => enk.writeOpt[Long](tplIndex, enk.writeLong)
      case _: AttrMapOptFloat          => enk.writeOpt[Float](tplIndex, enk.writeFloat)
      case _: AttrMapOptDouble         => enk.writeOpt[Double](tplIndex, enk.writeDouble)
      case _: AttrMapOptBoolean        => enk.writeOpt[Boolean](tplIndex, enk.writeBoolean)
      case _: AttrMapOptBigInt         => enk.writeOpt[BigInt](tplIndex, enk.writeBigInt)
      case _: AttrMapOptBigDecimal     => enk.writeOpt[BigDecimal](tplIndex, enk.writeBigDecimal)
      case _: AttrMapOptDate           => enk.writeOpt[Date](tplIndex, enk.writeDate)
      case _: AttrMapOptDuration       => enk.writeOpt[Duration](tplIndex, enk.writeDuration)
      case _: AttrMapOptInstant        => enk.writeOpt[Instant](tplIndex, enk.writeInstant)
      case _: AttrMapOptLocalDate      => enk.writeOpt[LocalDate](tplIndex, enk.writeLocalDate)
      case _: AttrMapOptLocalTime      => enk.writeOpt[LocalTime](tplIndex, enk.writeLocalTime)
      case _: AttrMapOptLocalDateTime  => enk.writeOpt[LocalDateTime](tplIndex, enk.writeLocalDateTime)
      case _: AttrMapOptOffsetTime     => enk.writeOpt[OffsetTime](tplIndex, enk.writeOffsetTime)
      case _: AttrMapOptOffsetDateTime => enk.writeOpt[OffsetDateTime](tplIndex, enk.writeOffsetDateTime)
      case _: AttrMapOptZonedDateTime  => enk.writeOpt[ZonedDateTime](tplIndex, enk.writeZonedDateTime)
      case _: AttrMapOptUUID           => enk.writeOpt[UUID](tplIndex, enk.writeUUID)
      case _: AttrMapOptURI            => enk.writeOpt[URI](tplIndex, enk.writeURI)
      case _: AttrMapOptByte           => enk.writeOpt[Byte](tplIndex, enk.writeByte)
      case _: AttrMapOptShort          => enk.writeOpt[Short](tplIndex, enk.writeShort)
      case _: AttrMapOptChar           => enk.writeOpt[Char](tplIndex, enk.writeChar)
    }
  }
  private def pickleAttrMapOptOther(a: AttrMapOpt, tplIndex: Int): Product => Unit = {
    a match {
      case _: AttrMapOptID             => enk.writeOptMap[Long](tplIndex, enk.writeLong)
      case _: AttrMapOptString         => enk.writeOptMap[String](tplIndex, enk.writeString)
      case _: AttrMapOptInt            => enk.writeOptMap[Int](tplIndex, enk.writeInt)
      case _: AttrMapOptLong           => enk.writeOptMap[Long](tplIndex, enk.writeLong)
      case _: AttrMapOptFloat          => enk.writeOptMap[Float](tplIndex, enk.writeFloat)
      case _: AttrMapOptDouble         => enk.writeOptMap[Double](tplIndex, enk.writeDouble)
      case _: AttrMapOptBoolean        => enk.writeOptMap[Boolean](tplIndex, enk.writeBoolean)
      case _: AttrMapOptBigInt         => enk.writeOptMap[BigInt](tplIndex, enk.writeBigInt)
      case _: AttrMapOptBigDecimal     => enk.writeOptMap[BigDecimal](tplIndex, enk.writeBigDecimal)
      case _: AttrMapOptDate           => enk.writeOptMap[Date](tplIndex, enk.writeDate)
      case _: AttrMapOptDuration       => enk.writeOptMap[Duration](tplIndex, enk.writeDuration)
      case _: AttrMapOptInstant        => enk.writeOptMap[Instant](tplIndex, enk.writeInstant)
      case _: AttrMapOptLocalDate      => enk.writeOptMap[LocalDate](tplIndex, enk.writeLocalDate)
      case _: AttrMapOptLocalTime      => enk.writeOptMap[LocalTime](tplIndex, enk.writeLocalTime)
      case _: AttrMapOptLocalDateTime  => enk.writeOptMap[LocalDateTime](tplIndex, enk.writeLocalDateTime)
      case _: AttrMapOptOffsetTime     => enk.writeOptMap[OffsetTime](tplIndex, enk.writeOffsetTime)
      case _: AttrMapOptOffsetDateTime => enk.writeOptMap[OffsetDateTime](tplIndex, enk.writeOffsetDateTime)
      case _: AttrMapOptZonedDateTime  => enk.writeOptMap[ZonedDateTime](tplIndex, enk.writeZonedDateTime)
      case _: AttrMapOptUUID           => enk.writeOptMap[UUID](tplIndex, enk.writeUUID)
      case _: AttrMapOptURI            => enk.writeOptMap[URI](tplIndex, enk.writeURI)
      case _: AttrMapOptByte           => enk.writeOptMap[Byte](tplIndex, enk.writeByte)
      case _: AttrMapOptShort          => enk.writeOptMap[Short](tplIndex, enk.writeShort)
      case _: AttrMapOptChar           => enk.writeOptMap[Char](tplIndex, enk.writeChar)
    }
  }
}
