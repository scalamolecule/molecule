package molecule.core.marshalling

import java.net.URI
import java.util.{Date, UUID}
import scala.collection.mutable.ArrayBuffer


case class DTObuffer(initialSize: Int = 20) {

  lazy val oneString     = new ArrayBuffer[String](initialSize)
  lazy val oneInt        = new ArrayBuffer[Int](3)
  lazy val oneLong       = new ArrayBuffer[Long](initialSize)
  lazy val oneFloat      = new ArrayBuffer[Float](initialSize)
  lazy val oneDouble     = new ArrayBuffer[Double](initialSize)
  lazy val oneBoolean    = new ArrayBuffer[Boolean](initialSize)
  lazy val oneBigInt     = new ArrayBuffer[BigInt](initialSize)
  lazy val oneBigDecimal = new ArrayBuffer[BigDecimal](initialSize)
  lazy val oneDate       = new ArrayBuffer[Date](initialSize)
  lazy val oneUUID       = new ArrayBuffer[UUID](initialSize)
  lazy val oneURI        = new ArrayBuffer[URI](initialSize)
  lazy val oneByte       = new ArrayBuffer[Byte](initialSize)
  lazy val oneShort      = new ArrayBuffer[Short](initialSize)
  lazy val oneChar       = new ArrayBuffer[Char](initialSize)

  lazy val oneOptString     = new ArrayBuffer[Option[String]](initialSize)
  lazy val oneOptInt        = new ArrayBuffer[Option[Int]](initialSize)
  lazy val oneOptLong       = new ArrayBuffer[Option[Long]](initialSize)
  lazy val oneOptFloat      = new ArrayBuffer[Option[Float]](initialSize)
  lazy val oneOptDouble     = new ArrayBuffer[Option[Double]](initialSize)
  lazy val oneOptBoolean    = new ArrayBuffer[Option[Boolean]](initialSize)
  lazy val oneOptBigInt     = new ArrayBuffer[Option[BigInt]](initialSize)
  lazy val oneOptBigDecimal = new ArrayBuffer[Option[BigDecimal]](initialSize)
  lazy val oneOptDate       = new ArrayBuffer[Option[Date]](initialSize)
  lazy val oneOptUUID       = new ArrayBuffer[Option[UUID]](initialSize)
  lazy val oneOptURI        = new ArrayBuffer[Option[URI]](initialSize)
  lazy val oneOptByte       = new ArrayBuffer[Option[Byte]](initialSize)
  lazy val oneOptShort      = new ArrayBuffer[Option[Short]](initialSize)
  lazy val oneOptChar       = new ArrayBuffer[Option[Char]](initialSize)

  lazy val setString     = new ArrayBuffer[Set[String]](initialSize)
  lazy val setInt        = new ArrayBuffer[Set[Int]](initialSize)
  lazy val setLong       = new ArrayBuffer[Set[Long]](initialSize)
  lazy val setFloat      = new ArrayBuffer[Set[Float]](initialSize)
  lazy val setDouble     = new ArrayBuffer[Set[Double]](initialSize)
  lazy val setBoolean    = new ArrayBuffer[Set[Boolean]](initialSize)
  lazy val setBigInt     = new ArrayBuffer[Set[BigInt]](initialSize)
  lazy val setBigDecimal = new ArrayBuffer[Set[BigDecimal]](initialSize)
  lazy val setDate       = new ArrayBuffer[Set[Date]](initialSize)
  lazy val setUUID       = new ArrayBuffer[Set[UUID]](initialSize)
  lazy val setURI        = new ArrayBuffer[Set[URI]](initialSize)
  lazy val setByte       = new ArrayBuffer[Set[Byte]](initialSize)
  lazy val setShort      = new ArrayBuffer[Set[Short]](initialSize)
  lazy val setChar       = new ArrayBuffer[Set[Char]](initialSize)

  lazy val setOptString     = new ArrayBuffer[Option[Set[String]]](initialSize)
  lazy val setOptInt        = new ArrayBuffer[Option[Set[Int]]](initialSize)
  lazy val setOptLong       = new ArrayBuffer[Option[Set[Long]]](initialSize)
  lazy val setOptFloat      = new ArrayBuffer[Option[Set[Float]]](initialSize)
  lazy val setOptDouble     = new ArrayBuffer[Option[Set[Double]]](initialSize)
  lazy val setOptBoolean    = new ArrayBuffer[Option[Set[Boolean]]](initialSize)
  lazy val setOptBigInt     = new ArrayBuffer[Option[Set[BigInt]]](initialSize)
  lazy val setOptBigDecimal = new ArrayBuffer[Option[Set[BigDecimal]]](initialSize)
  lazy val setOptDate       = new ArrayBuffer[Option[Set[Date]]](initialSize)
  lazy val setOptUUID       = new ArrayBuffer[Option[Set[UUID]]](initialSize)
  lazy val setOptURI        = new ArrayBuffer[Option[Set[URI]]](initialSize)
  lazy val setOptByte       = new ArrayBuffer[Option[Set[Byte]]](initialSize)
  lazy val setOptShort      = new ArrayBuffer[Option[Set[Short]]](initialSize)
  lazy val setOptChar       = new ArrayBuffer[Option[Set[Char]]](initialSize)
}
