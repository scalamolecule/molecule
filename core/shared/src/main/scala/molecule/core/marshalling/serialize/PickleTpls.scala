package molecule.core.marshalling.serialize

import java.net.URI
import java.util.{Date, UUID}
import boopickle.BasicPicklers._
import boopickle.Default._
import boopickle._
import molecule.base.error.{ModelError, MoleculeError}
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.Boopicklers._
import molecule.core.util.{ModelUtils, SerializationUtils}
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer


case class PickleTpls(
  elements0: List[Element],
  allTuples: Boolean
) extends PickleTpl_
  with ModelUtils
  with SerializationUtils
  with MoleculeLogging {

  // Separate adjacent filter attributes
  @tailrec
  private def prepare(elements: List[Element], acc: List[Element]): List[Element] = {
    elements match {
      case element :: tail =>
        element match {
          case a: Attr       => prepare(tail, acc ++ prepareAttr(a))
          case e: Composite  => prepare(tail, acc :+ prepareComposite(e))
          case n: Nested     => prepare(tail, acc :+ prepareNested(n))
          case n: NestedOpt  => prepare(tail, acc :+ prepareNestedOpt(n))
          case t: TxMetaData => prepare(tail, acc :+ prepareTxMetaData(t))
          case refOrBackRef  => prepare(tail, acc :+ refOrBackRef)
        }
      case Nil             => acc
    }
  }
  // Add adjacent filter attribute after this attribute
  private def prepareAttr(a: Attr): List[Attr] =
    a.filterAttr.fold(List(a))(fa => if (fa.ns == a.ns) List(a, fa) else List(a))
  private def prepareComposite(composite: Composite): Composite = Composite(prepare(composite.elements, Nil))
  private def prepareNested(nested: Nested): Nested = Nested(nested.ref, prepare(nested.elements, Nil))
  private def prepareNestedOpt(nested: NestedOpt): NestedOpt = NestedOpt(nested.ref, prepare(nested.elements, Nil))
  private def prepareTxMetaData(t: TxMetaData): TxMetaData = TxMetaData(prepare(t.elements, Nil))

  private val elements = prepare(elements0, Nil)

  private val prevRefs: ListBuffer[String] = ListBuffer.empty[String]

  private val state = new PickleState(new EncoderSize(new HeapByteBufferProvider))
  private val enc   = state.enc
  type DummyNotUsed = Int

  def pickle(result: Either[MoleculeError, Seq[Any]]): Array[Byte] = {
    result match {
      case Right(tpls) => pickleTpls(tpls)
      case Left(err)   => LeftPickler[MoleculeError, DummyNotUsed].pickle(Left(err))(state)
    }
    state.toByteBuffer.toArray
  }

  def pickleOffset(result: Either[MoleculeError, (Seq[Any], Int, Boolean)]): Array[Byte] = {
    result match {
      case Right((tpls, limit, more)) =>
        pickleTpls(tpls)
        enk.writeInt(limit)
        enk.writeBoolean(more)
      case Left(err)                  =>
        LeftPickler[MoleculeError, DummyNotUsed].pickle(Left(err))(state)
    }
    state.toByteBuffer.toArray
  }

  def pickleCursor(result: Either[MoleculeError, (Seq[Any], String, Boolean)]): Array[Byte] = {
    result match {
      case Right((tpls, cursor, more)) =>
        pickleTpls(tpls)
        enk.writeString(cursor)
        enk.writeBoolean(more)
      case Left(err)                   =>
        LeftPickler[MoleculeError, DummyNotUsed].pickle(Left(err))(state)
    }
    state.toByteBuffer.toArray
  }

  private def pickleTpls(tpls: Seq[Any]): Unit = {
    enc.writeInt(2) // Encode Right
    enc.writeInt(tpls.size) // encode length of List of Tpl
    if (tpls.nonEmpty) {
      if (allTuples) {
        val pickleTpl = getPickler(elements)
        tpls.asInstanceOf[Seq[Product]].foreach(pickleTpl)
      } else {
        val arity = countValueAttrs(elements)
        if (arity == 1) {
          val pickleValue = resolvePicklers(elements, Nil, 0).head
          tpls.foreach(v => pickleValue(Tuple1(v)))
        } else {
          val pickleTpl = getPickler(elements)
          tpls.asInstanceOf[Seq[Product]].foreach(pickleTpl)
        }
      }
    }
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
          //          val tail1 = a.filterAttr.fold(tail)(_ +: tail)

          //          println("tail1: " + tail1)

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
            case a          => throw new Exception("Attribute family not implemented for " + a)
          }

        case Ref(_, refAttr, _, _) =>
          prevRefs += refAttr
          resolvePicklers(tail, picklers, tplIndex)

        case BackRef(backRefNs) =>
          tail.head match {
            case Ref(_, refAttr, _, _) if prevRefs.contains(refAttr) => throw ModelError(
              s"Can't re-use previous namespace ${refAttr.capitalize} after backref _$backRefNs."
            )
            case _                                                   => // ok
          }
          resolvePicklers(tail, picklers, tplIndex)

        case Nested(_, nestedElements) =>
          prevRefs.clear()
          resolvePicklers(tail, picklers :+ pickleNested(tplIndex, nestedElements), tplIndex + 1)

        case NestedOpt(_, nestedElements) =>
          prevRefs.clear()
          resolvePicklers(tail, picklers :+ pickleNested(tplIndex, nestedElements), tplIndex + 1)

        case Composite(compositeElements) =>
          pickleComposite(tplIndex, compositeElements) match {
            case Nil          => resolvePicklers(tail, picklers, tplIndex)
            case Seq(pickler) => resolvePicklers(tail, picklers :+ pickler, tplIndex + 1)
          }

        case TxMetaData(txMetaDataElements) =>
          // Tx meta data is last attribute values in top level tuple
          // TxMetaData is only pickled for queries. Inserts handle tx meta elements separately after all tpl inserts.
          picklers ++ pickleTxMeta(txMetaDataElements, tplIndex)
      }
      case Nil             => picklers
    }
  }


  private def pickleTxMeta(
    txMetaDataElements: List[Element],
    tplIndex: Int
  ): List[Product => Unit] = {
    resolvePicklers(txMetaDataElements, Nil, tplIndex)
  }

  private def pickleComposite(
    tplIndex: Int,
    compositeElements: List[Element]
  ): Seq[Product => Unit] = {
    lazy val pickleCompositeData = getPickler(compositeElements)
    // Start from initial entity id for each composite sub group
    countValueAttrs(compositeElements) match {
      case 0 => Nil
      case 1 => Seq((tpl: Product) => pickleCompositeData(Tuple1(tpl.productElement(tplIndex))))
      case _ => Seq((tpl: Product) => pickleCompositeData(tpl.productElement(tplIndex).asInstanceOf[Product]))
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
    lazy val writeInt   : Int => Unit    = (value: Int) => enc.writeInt(value.toString.toInt)
    lazy val writeLong  : Long => Unit   = (value: Long) => enc.writeString(value.toString)
    lazy val writeFloat : Float => Unit  = (value: Float) => enc.writeString(value.toString)
    lazy val writeDouble: Double => Unit = (value: Double) => enc.writeString(value.toString)

    // todo: how can we make these work instead?
    //    lazy val writeInt       : Int => Unit        = (value: Int) => enc.writeInt(value)
    //    lazy val writeLong      : Long => Unit       = (value: Long) => enc.writeLong(value)
    //    lazy val writeFloat     : Float => Unit      = (value: Float) => enc.writeFloat(value)
    //    lazy val writeDouble    : Double => Unit     = (value: Double) => enc.writeDouble(value)


    lazy val writeBoolean   : Boolean => Unit    = (value: Boolean) => BooleanPickler.pickle(value)(state)
    lazy val writeBigInt    : BigInt => Unit     = (value: BigInt) => {
      val ba = value.toByteArray
      enc.writeInt(ba.length)
      ba.foreach(enc.writeByte)
    }
    lazy val writeBigDecimal: BigDecimal => Unit = (value: BigDecimal) => {
      enc.writeInt(value.scale)
      val ba = value.underlying().unscaledValue.toByteArray
      enc.writeInt(ba.length)
      ba.foreach(enc.writeByte)
    }
    lazy val writeDate      : Date => Unit       = (value: Date) => enc.writeString(value.getTime.toString)
    lazy val writeUUID      : UUID => Unit       = (value: UUID) => enc.writeString(value.toString)
    lazy val writeURI       : URI => Unit        = (value: URI) => enc.writeString(value.toString)
    lazy val writeByte      : Byte => Unit       = (value: Byte) => enc.writeByte(value)
    lazy val writeShort     : Short => Unit      = (value: Short) => enc.writeString(value.toString)
    lazy val writeChar      : Char => Unit       = (value: Char) => enc.writeChar(value)


    def writeSet[T](tplIndex: Int, writer: T => Unit): Product => Unit = {
      (tpl: Product) => {
        val set = tpl.productElement(tplIndex).asInstanceOf[Set[T]]
        enc.writeInt(set.size)
        set.foreach(v => writer(v))
      }
    }

    def writeSets[T](tplIndex: Int, writer: T => Unit): Product => Unit = {
      (tpl: Product) => {
        val sets = tpl.productElement(tplIndex).asInstanceOf[Set[Set[T]]]
        enc.writeInt(sets.size)
        sets.foreach { set =>
          enc.writeInt(set.size)
          set.foreach(v => writer(v))
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
  }

  private def pickleAttrOneMan(a: AttrOneMan, tplIndex: Int): Product => Unit = {
    a.op match {
      case Fn(kw, _) => kw match {
        case "count" | "countDistinct"                          => pickleAttrOneManInt(tplIndex)
        case "distinct" | "mins" | "maxs" | "rands" | "samples" => pickleAttrOneManSet(a, tplIndex)
        case "avg" | "variance" | "stddev"                      => pickleAttrOneManDouble(tplIndex)
        case _                                                  => pickleAttrOneManV(a, tplIndex)
      }
      case _         => pickleAttrOneManV(a, tplIndex)
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
      case _: AttrOneManString     => (tpl: Product) => enk.writeString(tpl.productElement(tplIndex).asInstanceOf[String])
      case _: AttrOneManInt        => (tpl: Product) => enk.writeInt(tpl.productElement(tplIndex).toString.toInt)
      case _: AttrOneManLong       => (tpl: Product) => enk.writeLong(tpl.productElement(tplIndex).asInstanceOf[Long])
      case _: AttrOneManFloat      => (tpl: Product) => enk.writeFloat(tpl.productElement(tplIndex).asInstanceOf[Float])
      case _: AttrOneManDouble     => (tpl: Product) => enk.writeDouble(tpl.productElement(tplIndex).asInstanceOf[Double])
      case _: AttrOneManBoolean    => (tpl: Product) => enk.writeBoolean(tpl.productElement(tplIndex).asInstanceOf[Boolean])
      case _: AttrOneManBigInt     => (tpl: Product) => enk.writeBigInt(tpl.productElement(tplIndex).asInstanceOf[BigInt])
      case _: AttrOneManBigDecimal => (tpl: Product) => enk.writeBigDecimal(tpl.productElement(tplIndex).asInstanceOf[BigDecimal])
      case _: AttrOneManDate       => (tpl: Product) => enk.writeDate(tpl.productElement(tplIndex).asInstanceOf[Date])
      case _: AttrOneManUUID       => (tpl: Product) => enk.writeUUID(tpl.productElement(tplIndex).asInstanceOf[UUID])
      case _: AttrOneManURI        => (tpl: Product) => enk.writeURI(tpl.productElement(tplIndex).asInstanceOf[URI])
      case _: AttrOneManByte       => (tpl: Product) => enk.writeByte(tpl.productElement(tplIndex).asInstanceOf[Byte])
      case _: AttrOneManShort      => (tpl: Product) => enk.writeShort(tpl.productElement(tplIndex).asInstanceOf[Short])
      case _: AttrOneManChar       => (tpl: Product) => enk.writeChar(tpl.productElement(tplIndex).asInstanceOf[Char])
    }
  }

  private def pickleAttrOneManSet(a: AttrOneMan, tplIndex: Int): Product => Unit = {
    a match {
      case _: AttrOneManString     => enk.writeSet[String](tplIndex, enk.writeString)
      case _: AttrOneManInt        => enk.writeSet[Int](tplIndex, enk.writeInt)
      case _: AttrOneManLong       => enk.writeSet[Long](tplIndex, enk.writeLong)
      case _: AttrOneManFloat      => enk.writeSet[Float](tplIndex, enk.writeFloat)
      case _: AttrOneManDouble     => enk.writeSet[Double](tplIndex, enk.writeDouble)
      case _: AttrOneManBoolean    => enk.writeSet[Boolean](tplIndex, enk.writeBoolean)
      case _: AttrOneManBigInt     => enk.writeSet[BigInt](tplIndex, enk.writeBigInt)
      case _: AttrOneManBigDecimal => enk.writeSet[BigDecimal](tplIndex, enk.writeBigDecimal)
      case _: AttrOneManDate       => enk.writeSet[Date](tplIndex, enk.writeDate)
      case _: AttrOneManUUID       => enk.writeSet[UUID](tplIndex, enk.writeUUID)
      case _: AttrOneManURI        => enk.writeSet[URI](tplIndex, enk.writeURI)
      case _: AttrOneManByte       => enk.writeSet[Byte](tplIndex, enk.writeByte)
      case _: AttrOneManShort      => enk.writeSet[Short](tplIndex, enk.writeShort)
      case _: AttrOneManChar       => enk.writeSet[Char](tplIndex, enk.writeChar)
    }
  }

  private def pickleAttrOneOpt(a: AttrOneOpt, tplIndex: Int): Product => Unit = {
    a match {
      case _: AttrOneOptString     => enk.writeOpt[String](tplIndex, enk.writeString)
      case _: AttrOneOptInt        => enk.writeOpt[Int](tplIndex, enk.writeInt)
      case _: AttrOneOptLong       => enk.writeOpt[Long](tplIndex, enk.writeLong)
      case _: AttrOneOptFloat      => enk.writeOpt[Float](tplIndex, enk.writeFloat)
      case _: AttrOneOptDouble     => enk.writeOpt[Double](tplIndex, enk.writeDouble)
      case _: AttrOneOptBoolean    => enk.writeOpt[Boolean](tplIndex, enk.writeBoolean)
      case _: AttrOneOptBigInt     => enk.writeOpt[BigInt](tplIndex, enk.writeBigInt)
      case _: AttrOneOptBigDecimal => enk.writeOpt[BigDecimal](tplIndex, enk.writeBigDecimal)
      case _: AttrOneOptDate       => enk.writeOpt[Date](tplIndex, enk.writeDate)
      case _: AttrOneOptUUID       => enk.writeOpt[UUID](tplIndex, enk.writeUUID)
      case _: AttrOneOptURI        => enk.writeOpt[URI](tplIndex, enk.writeURI)
      case _: AttrOneOptByte       => enk.writeOpt[Byte](tplIndex, enk.writeByte)
      case _: AttrOneOptShort      => enk.writeOpt[Short](tplIndex, enk.writeShort)
      case _: AttrOneOptChar       => enk.writeOpt[Char](tplIndex, enk.writeChar)
    }
  }

  private def pickleAttrSetMan(a: AttrSetMan, tplIndex: Int): Product => Unit = {
    a.op match {
      case Fn(kw, _) => kw match {
        case "count" | "countDistinct"             => pickleAttrSetManInt(tplIndex)
        case "distinct"                            => pickleAttrSetManSet(a, tplIndex)
        case "mins" | "maxs" | "rands" | "samples" => pickleAttrSetManV(a, tplIndex)
        case "avg" | "variance" | "stddev"         => pickleAttrSetManDouble(tplIndex)
        case _                                     => pickleAttrSetManV(a, tplIndex)
      }
      case _         => pickleAttrSetManV(a, tplIndex)
    }
  }
  private def pickleAttrSetManInt(tplIndex: Int): Product => Unit = {
    (tpl: Product) => enk.writeInt(tpl.productElement(tplIndex).toString.toInt)
  }
  private def pickleAttrSetManDouble(tplIndex: Int): Product => Unit = {
    enk.writeSet[Double](tplIndex, enk.writeDouble)
  }
  private def pickleAttrSetManV(a: AttrSetMan, tplIndex: Int): Product => Unit = {
    a match {
      case _: AttrSetManString     => enk.writeSet[String](tplIndex, enk.writeString)
      case _: AttrSetManInt        => enk.writeSet[Int](tplIndex, enk.writeInt)
      case _: AttrSetManLong       => enk.writeSet[Long](tplIndex, enk.writeLong)
      case _: AttrSetManFloat      => enk.writeSet[Float](tplIndex, enk.writeFloat)
      case _: AttrSetManDouble     => enk.writeSet[Double](tplIndex, enk.writeDouble)
      case _: AttrSetManBoolean    => enk.writeSet[Boolean](tplIndex, enk.writeBoolean)
      case _: AttrSetManBigInt     => enk.writeSet[BigInt](tplIndex, enk.writeBigInt)
      case _: AttrSetManBigDecimal => enk.writeSet[BigDecimal](tplIndex, enk.writeBigDecimal)
      case _: AttrSetManDate       => enk.writeSet[Date](tplIndex, enk.writeDate)
      case _: AttrSetManUUID       => enk.writeSet[UUID](tplIndex, enk.writeUUID)
      case _: AttrSetManURI        => enk.writeSet[URI](tplIndex, enk.writeURI)
      case _: AttrSetManByte       => enk.writeSet[Byte](tplIndex, enk.writeByte)
      case _: AttrSetManShort      => enk.writeSet[Short](tplIndex, enk.writeShort)
      case _: AttrSetManChar       => enk.writeSet[Char](tplIndex, enk.writeChar)
    }
  }
  private def pickleAttrSetManSet(a: AttrSetMan, tplIndex: Int): Product => Unit = {
    a match {
      case _: AttrSetManString     => enk.writeSets[String](tplIndex, enk.writeString)
      case _: AttrSetManInt        => enk.writeSets[Int](tplIndex, enk.writeInt)
      case _: AttrSetManLong       => enk.writeSets[Long](tplIndex, enk.writeLong)
      case _: AttrSetManFloat      => enk.writeSets[Float](tplIndex, enk.writeFloat)
      case _: AttrSetManDouble     => enk.writeSets[Double](tplIndex, enk.writeDouble)
      case _: AttrSetManBoolean    => enk.writeSets[Boolean](tplIndex, enk.writeBoolean)
      case _: AttrSetManBigInt     => enk.writeSets[BigInt](tplIndex, enk.writeBigInt)
      case _: AttrSetManBigDecimal => enk.writeSets[BigDecimal](tplIndex, enk.writeBigDecimal)
      case _: AttrSetManDate       => enk.writeSets[Date](tplIndex, enk.writeDate)
      case _: AttrSetManUUID       => enk.writeSets[UUID](tplIndex, enk.writeUUID)
      case _: AttrSetManURI        => enk.writeSets[URI](tplIndex, enk.writeURI)
      case _: AttrSetManByte       => enk.writeSets[Byte](tplIndex, enk.writeByte)
      case _: AttrSetManShort      => enk.writeSets[Short](tplIndex, enk.writeShort)
      case _: AttrSetManChar       => enk.writeSets[Char](tplIndex, enk.writeChar)
    }
  }

  private def pickleAttrSetOpt(a: AttrSetOpt, tplIndex: Int): Product => Unit = {
    a match {
      case _: AttrSetOptString     => enk.writeOptSet[String](tplIndex, enk.writeString)
      case _: AttrSetOptInt        => enk.writeOptSet[Int](tplIndex, enk.writeInt)
      case _: AttrSetOptLong       => enk.writeOptSet[Long](tplIndex, enk.writeLong)
      case _: AttrSetOptFloat      => enk.writeOptSet[Float](tplIndex, enk.writeFloat)
      case _: AttrSetOptDouble     => enk.writeOptSet[Double](tplIndex, enk.writeDouble)
      case _: AttrSetOptBoolean    => enk.writeOptSet[Boolean](tplIndex, enk.writeBoolean)
      case _: AttrSetOptBigInt     => enk.writeOptSet[BigInt](tplIndex, enk.writeBigInt)
      case _: AttrSetOptBigDecimal => enk.writeOptSet[BigDecimal](tplIndex, enk.writeBigDecimal)
      case _: AttrSetOptDate       => enk.writeOptSet[Date](tplIndex, enk.writeDate)
      case _: AttrSetOptUUID       => enk.writeOptSet[UUID](tplIndex, enk.writeUUID)
      case _: AttrSetOptURI        => enk.writeOptSet[URI](tplIndex, enk.writeURI)
      case _: AttrSetOptByte       => enk.writeOptSet[Byte](tplIndex, enk.writeByte)
      case _: AttrSetOptShort      => enk.writeOptSet[Short](tplIndex, enk.writeShort)
      case _: AttrSetOptChar       => enk.writeOptSet[Char](tplIndex, enk.writeChar)
    }
  }
}
