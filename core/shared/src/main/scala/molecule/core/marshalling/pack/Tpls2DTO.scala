package molecule.core.marshalling.pack

import java.net.URI
import java.util.{Date, UUID}
import molecule.boilerplate.ast.Model._
import molecule.core.marshalling.DTO
import scala.collection.mutable.ArrayBuffer


case class Tpls2DTO(elements: Seq[Element], tpls: List[Any])
  extends Packers_
    with PackTuple
    with PackValue {

  private val initialSize = tpls.length

  def pack: DTO = {
    if (tpls.isEmpty) {
      dto
    } else {
      tpls.head match {
        case _: Product =>
          val packTpl = getPacker(elements)
          tpls.asInstanceOf[List[Product]].foreach { tpl =>
            packTpl(tpl)
          }
        case _          =>
          val packValue = packSingleValue(elements.head)
          tpls.foreach(packValue)
      }
      dto
    }
  }

  protected lazy val oneString        = new ArrayBuffer[String](initialSize)
  protected lazy val oneInt           = new ArrayBuffer[Int](initialSize)
  protected lazy val oneLong          = new ArrayBuffer[Long](initialSize)
  protected lazy val oneFloat         = new ArrayBuffer[Float](initialSize)
  protected lazy val oneDouble        = new ArrayBuffer[Double](initialSize)
  protected lazy val oneBoolean       = new ArrayBuffer[Boolean](initialSize)
  protected lazy val oneBigInt        = new ArrayBuffer[BigInt](initialSize)
  protected lazy val oneBigDecimal    = new ArrayBuffer[BigDecimal](initialSize)
  protected lazy val oneDate          = new ArrayBuffer[Date](initialSize)
  protected lazy val oneUUID          = new ArrayBuffer[UUID](initialSize)
  protected lazy val oneURI           = new ArrayBuffer[URI](initialSize)
  protected lazy val oneByte          = new ArrayBuffer[Byte](initialSize)
  protected lazy val oneShort         = new ArrayBuffer[Short](initialSize)
  protected lazy val oneChar          = new ArrayBuffer[Char](initialSize)
  protected lazy val oneOptString     = new ArrayBuffer[Option[String]](initialSize)
  protected lazy val oneOptInt        = new ArrayBuffer[Option[Int]](initialSize)
  protected lazy val oneOptLong       = new ArrayBuffer[Option[Long]](initialSize)
  protected lazy val oneOptFloat      = new ArrayBuffer[Option[Float]](initialSize)
  protected lazy val oneOptDouble     = new ArrayBuffer[Option[Double]](initialSize)
  protected lazy val oneOptBoolean    = new ArrayBuffer[Option[Boolean]](initialSize)
  protected lazy val oneOptBigInt     = new ArrayBuffer[Option[BigInt]](initialSize)
  protected lazy val oneOptBigDecimal = new ArrayBuffer[Option[BigDecimal]](initialSize)
  protected lazy val oneOptDate       = new ArrayBuffer[Option[Date]](initialSize)
  protected lazy val oneOptUUID       = new ArrayBuffer[Option[UUID]](initialSize)
  protected lazy val oneOptURI        = new ArrayBuffer[Option[URI]](initialSize)
  protected lazy val oneOptByte       = new ArrayBuffer[Option[Byte]](initialSize)
  protected lazy val oneOptShort      = new ArrayBuffer[Option[Short]](initialSize)
  protected lazy val oneOptChar       = new ArrayBuffer[Option[Char]](initialSize)
  protected lazy val setString        = new ArrayBuffer[Set[String]](initialSize)
  protected lazy val setInt           = new ArrayBuffer[Set[Int]](initialSize)
  protected lazy val setLong          = new ArrayBuffer[Set[Long]](initialSize)
  protected lazy val setFloat         = new ArrayBuffer[Set[Float]](initialSize)
  protected lazy val setDouble        = new ArrayBuffer[Set[Double]](initialSize)
  protected lazy val setBoolean       = new ArrayBuffer[Set[Boolean]](initialSize)
  protected lazy val setBigInt        = new ArrayBuffer[Set[BigInt]](initialSize)
  protected lazy val setBigDecimal    = new ArrayBuffer[Set[BigDecimal]](initialSize)
  protected lazy val setDate          = new ArrayBuffer[Set[Date]](initialSize)
  protected lazy val setUUID          = new ArrayBuffer[Set[UUID]](initialSize)
  protected lazy val setURI           = new ArrayBuffer[Set[URI]](initialSize)
  protected lazy val setByte          = new ArrayBuffer[Set[Byte]](initialSize)
  protected lazy val setShort         = new ArrayBuffer[Set[Short]](initialSize)
  protected lazy val setChar          = new ArrayBuffer[Set[Char]](initialSize)
  protected lazy val setOptString     = new ArrayBuffer[Option[Set[String]]](initialSize)
  protected lazy val setOptInt        = new ArrayBuffer[Option[Set[Int]]](initialSize)
  protected lazy val setOptLong       = new ArrayBuffer[Option[Set[Long]]](initialSize)
  protected lazy val setOptFloat      = new ArrayBuffer[Option[Set[Float]]](initialSize)
  protected lazy val setOptDouble     = new ArrayBuffer[Option[Set[Double]]](initialSize)
  protected lazy val setOptBoolean    = new ArrayBuffer[Option[Set[Boolean]]](initialSize)
  protected lazy val setOptBigInt     = new ArrayBuffer[Option[Set[BigInt]]](initialSize)
  protected lazy val setOptBigDecimal = new ArrayBuffer[Option[Set[BigDecimal]]](initialSize)
  protected lazy val setOptDate       = new ArrayBuffer[Option[Set[Date]]](initialSize)
  protected lazy val setOptUUID       = new ArrayBuffer[Option[Set[UUID]]](initialSize)
  protected lazy val setOptURI        = new ArrayBuffer[Option[Set[URI]]](initialSize)
  protected lazy val setOptByte       = new ArrayBuffer[Option[Set[Byte]]](initialSize)
  protected lazy val setOptShort      = new ArrayBuffer[Option[Set[Short]]](initialSize)
  protected lazy val setOptChar       = new ArrayBuffer[Option[Set[Char]]](initialSize)

  private def dto = DTO(
    initialSize,
    oneString.iterator.to(Iterable),
    oneInt.iterator.to(Iterable),
    oneLong.iterator.to(Iterable),
    oneFloat.iterator.to(Iterable),
    oneDouble.iterator.to(Iterable),
    oneBoolean.iterator.to(Iterable),
    oneBigInt.iterator.to(Iterable),
    oneBigDecimal.iterator.to(Iterable),
    oneDate.iterator.to(Iterable),
    oneUUID.iterator.to(Iterable),
    oneURI.iterator.to(Iterable),
    oneByte.iterator.to(Iterable),
    oneShort.iterator.to(Iterable),
    oneChar.iterator.to(Iterable),
    oneOptString.iterator.to(Iterable),
    oneOptInt.iterator.to(Iterable),
    oneOptLong.iterator.to(Iterable),
    oneOptFloat.iterator.to(Iterable),
    oneOptDouble.iterator.to(Iterable),
    oneOptBoolean.iterator.to(Iterable),
    oneOptBigInt.iterator.to(Iterable),
    oneOptBigDecimal.iterator.to(Iterable),
    oneOptDate.iterator.to(Iterable),
    oneOptUUID.iterator.to(Iterable),
    oneOptURI.iterator.to(Iterable),
    oneOptByte.iterator.to(Iterable),
    oneOptShort.iterator.to(Iterable),
    oneOptChar.iterator.to(Iterable),
    setString.iterator.to(Iterable),
    setInt.iterator.to(Iterable),
    setLong.iterator.to(Iterable),
    setFloat.iterator.to(Iterable),
    setDouble.iterator.to(Iterable),
    setBoolean.iterator.to(Iterable),
    setBigInt.iterator.to(Iterable),
    setBigDecimal.iterator.to(Iterable),
    setDate.iterator.to(Iterable),
    setUUID.iterator.to(Iterable),
    setURI.iterator.to(Iterable),
    setByte.iterator.to(Iterable),
    setShort.iterator.to(Iterable),
    setChar.iterator.to(Iterable),
    setOptString.iterator.to(Iterable),
    setOptInt.iterator.to(Iterable),
    setOptLong.iterator.to(Iterable),
    setOptFloat.iterator.to(Iterable),
    setOptDouble.iterator.to(Iterable),
    setOptBoolean.iterator.to(Iterable),
    setOptBigInt.iterator.to(Iterable),
    setOptBigDecimal.iterator.to(Iterable),
    setOptDate.iterator.to(Iterable),
    setOptUUID.iterator.to(Iterable),
    setOptURI.iterator.to(Iterable),
    setOptByte.iterator.to(Iterable),
    setOptShort.iterator.to(Iterable),
    setOptChar.iterator.to(Iterable),
  )
}
