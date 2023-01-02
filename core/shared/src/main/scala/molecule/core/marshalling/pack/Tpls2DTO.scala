package molecule.core.marshalling.pack

import java.net.URI
import java.util.{Date, UUID}
import molecule.boilerplate.ast.Model._
import molecule.core.marshalling.DTO
import scala.collection.mutable.{ArrayBuffer, ListBuffer}


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

  protected lazy val oneString        = new ListBuffer[String]
  protected lazy val oneInt           = new ListBuffer[Int]
  protected lazy val oneLong          = new ListBuffer[Long]
  protected lazy val oneFloat         = new ListBuffer[Float]
  protected lazy val oneDouble        = new ListBuffer[Double]
  protected lazy val oneBoolean       = new ListBuffer[Boolean]
  protected lazy val oneBigInt        = new ListBuffer[BigInt]
  protected lazy val oneBigDecimal    = new ListBuffer[BigDecimal]
  protected lazy val oneDate          = new ListBuffer[Date]
  protected lazy val oneUUID          = new ListBuffer[UUID]
  protected lazy val oneURI           = new ListBuffer[URI]
  protected lazy val oneByte          = new ListBuffer[Byte]
  protected lazy val oneShort         = new ListBuffer[Short]
  protected lazy val oneChar          = new ListBuffer[Char]
  protected lazy val oneOptString     = new ListBuffer[Option[String]]
  protected lazy val oneOptInt        = new ListBuffer[Option[Int]]
  protected lazy val oneOptLong       = new ListBuffer[Option[Long]]
  protected lazy val oneOptFloat      = new ListBuffer[Option[Float]]
  protected lazy val oneOptDouble     = new ListBuffer[Option[Double]]
  protected lazy val oneOptBoolean    = new ListBuffer[Option[Boolean]]
  protected lazy val oneOptBigInt     = new ListBuffer[Option[BigInt]]
  protected lazy val oneOptBigDecimal = new ListBuffer[Option[BigDecimal]]
  protected lazy val oneOptDate       = new ListBuffer[Option[Date]]
  protected lazy val oneOptUUID       = new ListBuffer[Option[UUID]]
  protected lazy val oneOptURI        = new ListBuffer[Option[URI]]
  protected lazy val oneOptByte       = new ListBuffer[Option[Byte]]
  protected lazy val oneOptShort      = new ListBuffer[Option[Short]]
  protected lazy val oneOptChar       = new ListBuffer[Option[Char]]
  protected lazy val setString        = new ListBuffer[Set[String]]
  protected lazy val setInt           = new ListBuffer[Set[Int]]
  protected lazy val setLong          = new ListBuffer[Set[Long]]
  protected lazy val setFloat         = new ListBuffer[Set[Float]]
  protected lazy val setDouble        = new ListBuffer[Set[Double]]
  protected lazy val setBoolean       = new ListBuffer[Set[Boolean]]
  protected lazy val setBigInt        = new ListBuffer[Set[BigInt]]
  protected lazy val setBigDecimal    = new ListBuffer[Set[BigDecimal]]
  protected lazy val setDate          = new ListBuffer[Set[Date]]
  protected lazy val setUUID          = new ListBuffer[Set[UUID]]
  protected lazy val setURI           = new ListBuffer[Set[URI]]
  protected lazy val setByte          = new ListBuffer[Set[Byte]]
  protected lazy val setShort         = new ListBuffer[Set[Short]]
  protected lazy val setChar          = new ListBuffer[Set[Char]]
  protected lazy val setOptString     = new ListBuffer[Option[Set[String]]]
  protected lazy val setOptInt        = new ListBuffer[Option[Set[Int]]]
  protected lazy val setOptLong       = new ListBuffer[Option[Set[Long]]]
  protected lazy val setOptFloat      = new ListBuffer[Option[Set[Float]]]
  protected lazy val setOptDouble     = new ListBuffer[Option[Set[Double]]]
  protected lazy val setOptBoolean    = new ListBuffer[Option[Set[Boolean]]]
  protected lazy val setOptBigInt     = new ListBuffer[Option[Set[BigInt]]]
  protected lazy val setOptBigDecimal = new ListBuffer[Option[Set[BigDecimal]]]
  protected lazy val setOptDate       = new ListBuffer[Option[Set[Date]]]
  protected lazy val setOptUUID       = new ListBuffer[Option[Set[UUID]]]
  protected lazy val setOptURI        = new ListBuffer[Option[Set[URI]]]
  protected lazy val setOptByte       = new ListBuffer[Option[Set[Byte]]]
  protected lazy val setOptShort      = new ListBuffer[Option[Set[Short]]]
  protected lazy val setOptChar       = new ListBuffer[Option[Set[Char]]]

  private def dto = DTO(
    initialSize,
    oneString.toList,
    oneInt.toList,
    oneLong.toList,
    oneFloat.toList,
    oneDouble.toList,
    oneBoolean.toList,
    oneBigInt.toList,
    oneBigDecimal.toList,
    oneDate.toList,
    oneUUID.toList,
    oneURI.toList,
    oneByte.toList,
    oneShort.toList,
    oneChar.toList,
    oneOptString.toList,
    oneOptInt.toList,
    oneOptLong.toList,
    oneOptFloat.toList,
    oneOptDouble.toList,
    oneOptBoolean.toList,
    oneOptBigInt.toList,
    oneOptBigDecimal.toList,
    oneOptDate.toList,
    oneOptUUID.toList,
    oneOptURI.toList,
    oneOptByte.toList,
    oneOptShort.toList,
    oneOptChar.toList,
    setString.toList,
    setInt.toList,
    setLong.toList,
    setFloat.toList,
    setDouble.toList,
    setBoolean.toList,
    setBigInt.toList,
    setBigDecimal.toList,
    setDate.toList,
    setUUID.toList,
    setURI.toList,
    setByte.toList,
    setShort.toList,
    setChar.toList,
    setOptString.toList,
    setOptInt.toList,
    setOptLong.toList,
    setOptFloat.toList,
    setOptDouble.toList,
    setOptBoolean.toList,
    setOptBigInt.toList,
    setOptBigDecimal.toList,
    setOptDate.toList,
    setOptUUID.toList,
    setOptURI.toList,
    setOptByte.toList,
    setOptShort.toList,
    setOptChar.toList,
  )
}
