package boilerplate.db.common.action

import boilerplate.db.common.DbCommonBase

object _QueryBind extends DbCommonBase("QueryBind", "/action") {

  val content = {
    val bindMethods = (0 to 21).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.common.action
       |
       |import java.net.URI
       |import java.time.*
       |import java.util.{Date, UUID}
       |import molecule.base.error.ModelError
       |import molecule.core.dataModel.*
       |
       |
       |trait $fileName_[Tpl, QueryType[_] <: Action] {
       |
       |  // Simply encoding the given value.
       |  // If of wrong type, it will be discovered further down the call chain.
       |  def x(raw: Any): Value = raw match {
       |    case v: String         => OneString(v)
       |    case v: Int            => OneInt(v)
       |    case v: Long           => OneLong(v)
       |    case v: Float          => OneFloat(v)
       |    case v: Double         => OneDouble(v)
       |    case v: Boolean        => OneBoolean(v)
       |    case v: BigInt         => OneBigInt(v)
       |    case v: BigDecimal     => OneBigDecimal(v)
       |    case v: Date           => OneDate(v)
       |    case v: Duration       => OneDuration(v)
       |    case v: Instant        => OneInstant(v)
       |    case v: LocalDate      => OneLocalDate(v)
       |    case v: LocalTime      => OneLocalTime(v)
       |    case v: LocalDateTime  => OneLocalDateTime(v)
       |    case v: OffsetTime     => OneOffsetTime(v)
       |    case v: OffsetDateTime => OneOffsetDateTime(v)
       |    case v: ZonedDateTime  => OneZonedDateTime(v)
       |    case v: UUID           => OneUUID(v)
       |    case v: URI            => OneURI(v)
       |    case v: Byte           => OneByte(v)
       |    case v: Short          => OneShort(v)
       |    case v: Char           => OneChar(v)
       |    case other             =>
       |      throw ModelError(
       |        s\"\"\"Bind input of unexpected type:
       |           |value: $$other
       |           |type : $${other.getClass.getSimpleName}
       |           |\"\"\".stripMargin
       |      )
       |  }
       |
       |  protected def bind(inputs: List[Value]): QueryType[Tpl]
       |
       |$bindMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val c       = 'a'.toInt
    val inputs  = (0 to i).map { j => s"${(c + j).toChar}: Any" }.mkString(", ")
    val outputs = (0 to i).map { j => s"x(${(c + j).toChar})" }.mkString(", ")
    val body    = s"  def apply($inputs): QueryType[Tpl] = bind(List($outputs))"
  }
}
