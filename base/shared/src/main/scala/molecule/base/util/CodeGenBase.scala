package molecule.base.util

import java.net.URI
import java.time._
import java.util.{Date, UUID}


trait CodeGenBase extends BaseHelpers {

  val baseTypes                = Seq(
    "ID",
    "String",
    "Int",
    "Long",
    "Float",
    "Double",
    "Boolean",
    "BigInt",
    "BigDecimal",
    "Date",
    "Duration",
    "Instant",
    "LocalDate",
    "LocalTime",
    "LocalDateTime",
    "OffsetTime",
    "OffsetDateTime",
    "ZonedDateTime",
    "UUID",
    "URI",
    "Byte",
    "Short",
    "Char",
  )

  val baseTypesWithDummyValues = Seq(
    ("ID", "-42L"),
    ("String", """"foo""""),
    ("Int", "-42"),
    ("Long", "-42L"),
    ("Float", "-42f"),
    ("Double", "-42.0"),
    ("Boolean", "false"),
    ("BigInt", "BigInt(-42)"),
    ("BigDecimal", "BigDecimal(-42.0)"),
    ("Date", "new Date()"),
    ("Duration", "Duration.ofDays(0)"),
    ("Instant", "Instant.now()"),
    ("LocalDate", "LocalDate.now()"),
    ("LocalTime", "LocalTime.now()"),
    ("LocalDateTime", "LocalDateTime.now()"),
    ("OffsetTime", "OffsetTime.now()"),
    ("OffsetDateTime", "OffsetDateTime.now()"),
    ("ZonedDateTime", "ZonedDateTime.now()"),
    ("UUID", "UUID.randomUUID()"),
    ("URI", """new URI("foo")"""),
    ("Byte", "42.toByte"),
    ("Short", "42.toShort"),
    ("Char", "'-'"),
  )

  val baseTypesWithSpaces = Seq(
    "ID" -> "            ",
    "String" -> "        ",
    "Int" -> "           ",
    "Long" -> "          ",
    "Float" -> "         ",
    "Double" -> "        ",
    "Boolean" -> "       ",
    "BigInt" -> "        ",
    "BigDecimal" -> "    ",
    "Date" -> "          ",
    "Duration" -> "      ",
    "Instant" -> "       ",
    "LocalDate" -> "     ",
    "LocalTime" -> "     ",
    "LocalDateTime" -> " ",
    "OffsetTime" -> "    ",
    "OffsetDateTime" -> "",
    "ZonedDateTime" -> " ",
    "UUID" -> "          ",
    "URI" -> "           ",
    "Byte" -> "          ",
    "Short" -> "         ",
    "Char" -> "          ",
  )

  val javaTypes = Map(
    "String" -> "String",
    "Int" -> "jInteger",
    "Long" -> "jLong",
    "Float" -> "jFloat",
    "Double" -> "jDouble",
    "Boolean" -> "jBoolean",
    "BigInt" -> "jBigInt",
    "BigDecimal" -> "jBigDecimal",
    "Date" -> "Date",
    "Duration" -> "Duration",
    "Instant" -> "Instant",
    "LocalDate" -> "LocalDate",
    "LocalTime" -> "LocalTime",
    "LocalDateTime" -> "LocalDateTime",
    "OffsetTime" -> "OffsetTime",
    "OffsetDateTime" -> "OffsetDateTime",
    "ZonedDateTime" -> "ZonedDateTime",
    "UUID" -> "UUID",
    "URI" -> "URI",
    "Byte" -> "jInteger",
    "Short" -> "jInteger",
    "Char" -> "String",
  )

  // All types except Int and Boolean
  val tpeVarImp = List(
    ("String", "String", "string", ""),
    ("Long", "Long", "long", ""),
    ("Float", "Float", "float", ""),
    ("Double", "Double", "double", ""),
    ("BigInt", "BigInt", "bigInt", ""),
    ("BigDecimal", "BigDecimal", "bigDecimal", ""),
    ("Date", "Date", "date", "java.util.Date"),
    ("Duration", "Duration", "duration", "java.time.Duration"),
    ("Instant", "Instant", "instant", "java.time.Instant"),
    ("LocalDate", "LocalDate", "localDate", "java.time.LocalDate"),
    ("LocalTime", "LocalTime", "localTime", "java.time.LocalTime"),
    ("LocalDateTime", "LocalDateTime", "localDateTime", "java.time.LocalDateTime"),
    ("OffsetTime", "OffsetTime", "offsetTime", "java.time.OffsetTime"),
    ("OffsetDateTime", "OffsetDateTime", "offsetDateTime", "java.time.OffsetDateTime"),
    ("ZonedDateTime", "ZonedDateTime", "zonedDateTime", "java.time.ZonedDateTime"),
    ("UUID", "UUID", "uuid", "java.util.UUID"),
    ("URI", "URI", "uri", "java.net.URI"),
    ("Byte", "Byte", "byte", ""),
    ("Short", "Short", "short", ""),
    ("Char", "Char", "char", ""),
    ("ref", "String", "ref", ""),
  )

  // Number types except Int
  val numberTypes = List(
    ("Long", "Long", "long"),
    ("Float", "Float", "float"),
    ("Double", "Double", "double"),
    ("BigInt", "BigInt", "bigInt"),
    ("BigDecimal", "BigDecimal", "bigDecimal"),
    ("Byte", "Byte", "byte"),
    ("Short", "Short", "short"),
  )
}
