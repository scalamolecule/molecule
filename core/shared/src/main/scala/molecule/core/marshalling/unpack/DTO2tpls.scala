package molecule.core.marshalling.unpack

import java.net.URI
import java.util.{Date, UUID}
import molecule.boilerplate.ast.Model._
import molecule.core.marshalling.DTO


case class DTO2tpls[Tpl](elements: Seq[Element], dto: DTO)
  extends Unpackers_[Tpl] with UnpackTpls[Tpl] {

  protected lazy val tuples = List.newBuilder[Tpl]

  def unpack: List[Tpl] = try {
    val unpackRow = getUnpacker(elements, 0)
    (0 until rowCount).foreach { _ =>
      tuples.addOne(unpackRow().asInstanceOf[Tpl])
    }
    tuples.result()
  } catch {
    case e: Throwable =>
      println(e.getStackTrace.mkString("\n"))
      throw e
  }

  protected lazy val rowCount        : Int                               = dto.rowCount
  protected lazy val levelCounts     : List[Iterator[Int]]               = dto.levelCounts.map(_.iterator)
  protected lazy val oneString       : Iterator[String]                  = dto.oneString.iterator
  protected lazy val oneInt          : Iterator[Int]                     = dto.oneInt.iterator
  protected lazy val oneLong         : Iterator[Long]                    = dto.oneLong.iterator
  protected lazy val oneFloat        : Iterator[Float]                   = dto.oneFloat.iterator
  protected lazy val oneDouble       : Iterator[Double]                  = dto.oneDouble.iterator
  protected lazy val oneBoolean      : Iterator[Boolean]                 = dto.oneBoolean.iterator
  protected lazy val oneBigInt       : Iterator[BigInt]                  = dto.oneBigInt.iterator
  protected lazy val oneBigDecimal   : Iterator[BigDecimal]              = dto.oneBigDecimal.iterator
  protected lazy val oneDate         : Iterator[Date]                    = dto.oneDate.iterator
  protected lazy val oneUUID         : Iterator[UUID]                    = dto.oneUUID.iterator
  protected lazy val oneURI          : Iterator[URI]                     = dto.oneURI.iterator
  protected lazy val oneByte         : Iterator[Byte]                    = dto.oneByte.iterator
  protected lazy val oneShort        : Iterator[Short]                   = dto.oneShort.iterator
  protected lazy val oneChar         : Iterator[Char]                    = dto.oneChar.iterator
  protected lazy val oneOptString    : Iterator[Option[String]]          = dto.oneOptString.iterator
  protected lazy val oneOptInt       : Iterator[Option[Int]]             = dto.oneOptInt.iterator
  protected lazy val oneOptLong      : Iterator[Option[Long]]            = dto.oneOptLong.iterator
  protected lazy val oneOptFloat     : Iterator[Option[Float]]           = dto.oneOptFloat.iterator
  protected lazy val oneOptDouble    : Iterator[Option[Double]]          = dto.oneOptDouble.iterator
  protected lazy val oneOptBoolean   : Iterator[Option[Boolean]]         = dto.oneOptBoolean.iterator
  protected lazy val oneOptBigInt    : Iterator[Option[BigInt]]          = dto.oneOptBigInt.iterator
  protected lazy val oneOptBigDecimal: Iterator[Option[BigDecimal]]      = dto.oneOptBigDecimal.iterator
  protected lazy val oneOptDate      : Iterator[Option[Date]]            = dto.oneOptDate.iterator
  protected lazy val oneOptUUID      : Iterator[Option[UUID]]            = dto.oneOptUUID.iterator
  protected lazy val oneOptURI       : Iterator[Option[URI]]             = dto.oneOptURI.iterator
  protected lazy val oneOptByte      : Iterator[Option[Byte]]            = dto.oneOptByte.iterator
  protected lazy val oneOptShort     : Iterator[Option[Short]]           = dto.oneOptShort.iterator
  protected lazy val oneOptChar      : Iterator[Option[Char]]            = dto.oneOptChar.iterator
  protected lazy val setString       : Iterator[Set[String]]             = dto.setString.iterator
  protected lazy val setInt          : Iterator[Set[Int]]                = dto.setInt.iterator
  protected lazy val setLong         : Iterator[Set[Long]]               = dto.setLong.iterator
  protected lazy val setFloat        : Iterator[Set[Float]]              = dto.setFloat.iterator
  protected lazy val setDouble       : Iterator[Set[Double]]             = dto.setDouble.iterator
  protected lazy val setBoolean      : Iterator[Set[Boolean]]            = dto.setBoolean.iterator
  protected lazy val setBigInt       : Iterator[Set[BigInt]]             = dto.setBigInt.iterator
  protected lazy val setBigDecimal   : Iterator[Set[BigDecimal]]         = dto.setBigDecimal.iterator
  protected lazy val setDate         : Iterator[Set[Date]]               = dto.setDate.iterator
  protected lazy val setUUID         : Iterator[Set[UUID]]               = dto.setUUID.iterator
  protected lazy val setURI          : Iterator[Set[URI]]                = dto.setURI.iterator
  protected lazy val setByte         : Iterator[Set[Byte]]               = dto.setByte.iterator
  protected lazy val setShort        : Iterator[Set[Short]]              = dto.setShort.iterator
  protected lazy val setChar         : Iterator[Set[Char]]               = dto.setChar.iterator
  protected lazy val setOptString    : Iterator[Option[Set[String]]]     = dto.setOptString.iterator
  protected lazy val setOptInt       : Iterator[Option[Set[Int]]]        = dto.setOptInt.iterator
  protected lazy val setOptLong      : Iterator[Option[Set[Long]]]       = dto.setOptLong.iterator
  protected lazy val setOptFloat     : Iterator[Option[Set[Float]]]      = dto.setOptFloat.iterator
  protected lazy val setOptDouble    : Iterator[Option[Set[Double]]]     = dto.setOptDouble.iterator
  protected lazy val setOptBoolean   : Iterator[Option[Set[Boolean]]]    = dto.setOptBoolean.iterator
  protected lazy val setOptBigInt    : Iterator[Option[Set[BigInt]]]     = dto.setOptBigInt.iterator
  protected lazy val setOptBigDecimal: Iterator[Option[Set[BigDecimal]]] = dto.setOptBigDecimal.iterator
  protected lazy val setOptDate      : Iterator[Option[Set[Date]]]       = dto.setOptDate.iterator
  protected lazy val setOptUUID      : Iterator[Option[Set[UUID]]]       = dto.setOptUUID.iterator
  protected lazy val setOptURI       : Iterator[Option[Set[URI]]]        = dto.setOptURI.iterator
  protected lazy val setOptByte      : Iterator[Option[Set[Byte]]]       = dto.setOptByte.iterator
  protected lazy val setOptShort     : Iterator[Option[Set[Short]]]      = dto.setOptShort.iterator
  protected lazy val setOptChar      : Iterator[Option[Set[Char]]]       = dto.setOptChar.iterator
}
