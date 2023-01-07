package molecule.core.marshalling.pack

import java.net.URI
import java.util.{Date, UUID}
import molecule.boilerplate.ast.Model._
import molecule.core.marshalling.DTO
import scribe.Logging
import scala.collection.mutable.ListBuffer


case class Tpls2DTO(elements: Seq[Element], tpls: Seq[Product])
  extends Packers_ with PackTuple with Logging {

  def pack: DTO = try {
    if (tpls.nonEmpty) {
      val packTpl = getPacker(elements, 0)
      tpls.foreach(packTpl)
    }
    dto
  } catch{
    case e: Throwable =>
      logger.error(e.getStackTrace.mkString("\n"))
      throw e
  }

  private val initialSize = tpls.length
  protected val levelCounts = List(
    new ListBuffer[Int], // top level (not used, just to have level match index)
    new ListBuffer[Int], // level 1
    new ListBuffer[Int], // level 2
    new ListBuffer[Int], // level 3
    new ListBuffer[Int], // level 4
    new ListBuffer[Int], // level 5
    new ListBuffer[Int], // level 6
    new ListBuffer[Int], // level 7
  )
  protected val oneString        = new ListBuffer[String]
  protected val oneInt           = new ListBuffer[Int]
  protected val oneLong          = new ListBuffer[Long]
  protected val oneFloat         = new ListBuffer[Float]
  protected val oneDouble        = new ListBuffer[Double]
  protected val oneBoolean       = new ListBuffer[Boolean]
  protected val oneBigInt        = new ListBuffer[BigInt]
  protected val oneBigDecimal    = new ListBuffer[BigDecimal]
  protected val oneDate          = new ListBuffer[Date]
  protected val oneUUID          = new ListBuffer[UUID]
  protected val oneURI           = new ListBuffer[URI]
  protected val oneByte          = new ListBuffer[Byte]
  protected val oneShort         = new ListBuffer[Short]
  protected val oneChar          = new ListBuffer[Char]
  protected val oneOptString     = new ListBuffer[Option[String]]
  protected val oneOptInt        = new ListBuffer[Option[Int]]
  protected val oneOptLong       = new ListBuffer[Option[Long]]
  protected val oneOptFloat      = new ListBuffer[Option[Float]]
  protected val oneOptDouble     = new ListBuffer[Option[Double]]
  protected val oneOptBoolean    = new ListBuffer[Option[Boolean]]
  protected val oneOptBigInt     = new ListBuffer[Option[BigInt]]
  protected val oneOptBigDecimal = new ListBuffer[Option[BigDecimal]]
  protected val oneOptDate       = new ListBuffer[Option[Date]]
  protected val oneOptUUID       = new ListBuffer[Option[UUID]]
  protected val oneOptURI        = new ListBuffer[Option[URI]]
  protected val oneOptByte       = new ListBuffer[Option[Byte]]
  protected val oneOptShort      = new ListBuffer[Option[Short]]
  protected val oneOptChar       = new ListBuffer[Option[Char]]
  protected val setString        = new ListBuffer[Set[String]]
  protected val setInt           = new ListBuffer[Set[Int]]
  protected val setLong          = new ListBuffer[Set[Long]]
  protected val setFloat         = new ListBuffer[Set[Float]]
  protected val setDouble        = new ListBuffer[Set[Double]]
  protected val setBoolean       = new ListBuffer[Set[Boolean]]
  protected val setBigInt        = new ListBuffer[Set[BigInt]]
  protected val setBigDecimal    = new ListBuffer[Set[BigDecimal]]
  protected val setDate          = new ListBuffer[Set[Date]]
  protected val setUUID          = new ListBuffer[Set[UUID]]
  protected val setURI           = new ListBuffer[Set[URI]]
  protected val setByte          = new ListBuffer[Set[Byte]]
  protected val setShort         = new ListBuffer[Set[Short]]
  protected val setChar          = new ListBuffer[Set[Char]]
  protected val setOptString     = new ListBuffer[Option[Set[String]]]
  protected val setOptInt        = new ListBuffer[Option[Set[Int]]]
  protected val setOptLong       = new ListBuffer[Option[Set[Long]]]
  protected val setOptFloat      = new ListBuffer[Option[Set[Float]]]
  protected val setOptDouble     = new ListBuffer[Option[Set[Double]]]
  protected val setOptBoolean    = new ListBuffer[Option[Set[Boolean]]]
  protected val setOptBigInt     = new ListBuffer[Option[Set[BigInt]]]
  protected val setOptBigDecimal = new ListBuffer[Option[Set[BigDecimal]]]
  protected val setOptDate       = new ListBuffer[Option[Set[Date]]]
  protected val setOptUUID       = new ListBuffer[Option[Set[UUID]]]
  protected val setOptURI        = new ListBuffer[Option[Set[URI]]]
  protected val setOptByte       = new ListBuffer[Option[Set[Byte]]]
  protected val setOptShort      = new ListBuffer[Option[Set[Short]]]
  protected val setOptChar       = new ListBuffer[Option[Set[Char]]]

  private def dto = DTO(
    initialSize,
    levelCounts.map(_.toList),
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
