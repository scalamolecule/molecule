package molecule.core.marshalling.pack

import java.net.URI
import java.util.{Date, UUID}
import molecule.boilerplate.ast.Model._
import molecule.core.marshalling.{DTO, DTObuffer}


case class Tpls2DTO(elements: Seq[Element], tpls: List[Any])
  extends PackTuple(elements) with PackValue {

  protected val buf = DTObuffer(tpls.length)

  def pack: DTO = {
    if (tpls.isEmpty) {
      emptyDto
    } else {
      tpls.head match {
        case _: Product =>
          tpls.asInstanceOf[List[Product]].foreach(packTpl)
        case v          =>
          val packValue = packSingleValue(elements.head, v)
          tpls.foreach(packValue)
      }
      dto
    }
  }

  private lazy val emptyDto = DTO(
    Iterable.empty[String],
    Iterable.empty[Int],
    Iterable.empty[Long],
    Iterable.empty[Float],
    Iterable.empty[Double],
    Iterable.empty[Boolean],
    Iterable.empty[BigInt],
    Iterable.empty[BigDecimal],
    Iterable.empty[Date],
    Iterable.empty[UUID],
    Iterable.empty[URI],
    Iterable.empty[Byte],
    Iterable.empty[Short],
    Iterable.empty[Char],
    Iterable.empty[Option[String]],
    Iterable.empty[Option[Int]],
    Iterable.empty[Option[Long]],
    Iterable.empty[Option[Float]],
    Iterable.empty[Option[Double]],
    Iterable.empty[Option[Boolean]],
    Iterable.empty[Option[BigInt]],
    Iterable.empty[Option[BigDecimal]],
    Iterable.empty[Option[Date]],
    Iterable.empty[Option[UUID]],
    Iterable.empty[Option[URI]],
    Iterable.empty[Option[Byte]],
    Iterable.empty[Option[Short]],
    Iterable.empty[Option[Char]],
    Iterable.empty[Set[String]],
    Iterable.empty[Set[Int]],
    Iterable.empty[Set[Long]],
    Iterable.empty[Set[Float]],
    Iterable.empty[Set[Double]],
    Iterable.empty[Set[Boolean]],
    Iterable.empty[Set[BigInt]],
    Iterable.empty[Set[BigDecimal]],
    Iterable.empty[Set[Date]],
    Iterable.empty[Set[UUID]],
    Iterable.empty[Set[URI]],
    Iterable.empty[Set[Byte]],
    Iterable.empty[Set[Short]],
    Iterable.empty[Set[Char]],
    Iterable.empty[Option[Set[String]]],
    Iterable.empty[Option[Set[Int]]],
    Iterable.empty[Option[Set[Long]]],
    Iterable.empty[Option[Set[Float]]],
    Iterable.empty[Option[Set[Double]]],
    Iterable.empty[Option[Set[Boolean]]],
    Iterable.empty[Option[Set[BigInt]]],
    Iterable.empty[Option[Set[BigDecimal]]],
    Iterable.empty[Option[Set[Date]]],
    Iterable.empty[Option[Set[UUID]]],
    Iterable.empty[Option[Set[URI]]],
    Iterable.empty[Option[Set[Byte]]],
    Iterable.empty[Option[Set[Short]]],
    Iterable.empty[Option[Set[Char]]],
  )

  private lazy val dto = DTO(
    buf.oneString.iterator.to(Iterable),
    buf.oneInt.iterator.to(Iterable),
    buf.oneLong.iterator.to(Iterable),
    buf.oneFloat.iterator.to(Iterable),
    buf.oneDouble.iterator.to(Iterable),
    buf.oneBoolean.iterator.to(Iterable),
    buf.oneBigInt.iterator.to(Iterable),
    buf.oneBigDecimal.iterator.to(Iterable),
    buf.oneDate.iterator.to(Iterable),
    buf.oneUUID.iterator.to(Iterable),
    buf.oneURI.iterator.to(Iterable),
    buf.oneByte.iterator.to(Iterable),
    buf.oneShort.iterator.to(Iterable),
    buf.oneChar.iterator.to(Iterable),
    buf.oneOptString.iterator.to(Iterable),
    buf.oneOptInt.iterator.to(Iterable),
    buf.oneOptLong.iterator.to(Iterable),
    buf.oneOptFloat.iterator.to(Iterable),
    buf.oneOptDouble.iterator.to(Iterable),
    buf.oneOptBoolean.iterator.to(Iterable),
    buf.oneOptBigInt.iterator.to(Iterable),
    buf.oneOptBigDecimal.iterator.to(Iterable),
    buf.oneOptDate.iterator.to(Iterable),
    buf.oneOptUUID.iterator.to(Iterable),
    buf.oneOptURI.iterator.to(Iterable),
    buf.oneOptByte.iterator.to(Iterable),
    buf.oneOptShort.iterator.to(Iterable),
    buf.oneOptChar.iterator.to(Iterable),
    buf.setString.iterator.to(Iterable),
    buf.setInt.iterator.to(Iterable),
    buf.setLong.iterator.to(Iterable),
    buf.setFloat.iterator.to(Iterable),
    buf.setDouble.iterator.to(Iterable),
    buf.setBoolean.iterator.to(Iterable),
    buf.setBigInt.iterator.to(Iterable),
    buf.setBigDecimal.iterator.to(Iterable),
    buf.setDate.iterator.to(Iterable),
    buf.setUUID.iterator.to(Iterable),
    buf.setURI.iterator.to(Iterable),
    buf.setByte.iterator.to(Iterable),
    buf.setShort.iterator.to(Iterable),
    buf.setChar.iterator.to(Iterable),
    buf.setOptString.iterator.to(Iterable),
    buf.setOptInt.iterator.to(Iterable),
    buf.setOptLong.iterator.to(Iterable),
    buf.setOptFloat.iterator.to(Iterable),
    buf.setOptDouble.iterator.to(Iterable),
    buf.setOptBoolean.iterator.to(Iterable),
    buf.setOptBigInt.iterator.to(Iterable),
    buf.setOptBigDecimal.iterator.to(Iterable),
    buf.setOptDate.iterator.to(Iterable),
    buf.setOptUUID.iterator.to(Iterable),
    buf.setOptURI.iterator.to(Iterable),
    buf.setOptByte.iterator.to(Iterable),
    buf.setOptShort.iterator.to(Iterable),
    buf.setOptChar.iterator.to(Iterable),
  )
}
