package molecule.base.util


trait CodeGenBase extends BaseHelpers {

  val baseTypes = Seq(
    "String",
    "Int",
    "Long",
    "Float",
    "Double",
    "Boolean",
    "BigInt",
    "BigDecimal",
    "Date",
    "UUID",
    "URI",
    "Byte",
    "Short",
    "Char",
  )

  val baseTypesWithSpaces = Seq(
    "String" -> "    ",
    "Int" -> "       ",
    "Long" -> "      ",
    "Float" -> "     ",
    "Double" -> "    ",
    "Boolean" -> "   ",
    "BigInt" -> "    ",
    "BigDecimal" -> "",
    "Date" -> "      ",
    "UUID" -> "      ",
    "URI" -> "       ",
    "Byte" -> "      ",
    "Short" -> "     ",
    "Char" -> "      ",
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
    "UUID" -> "UUID",
    "URI" -> "URI",
    "Byte" -> "jInteger",
    "Short" -> "jInteger",
    "Char" -> "String",
  )

  // All types except Int
  val tpeVarImp = List(
    ("String", "String", "string", ""),
    ("Long", "Long", "long", ""),
    ("Float", "Float", "float", ""),
    ("Double", "Double", "double", ""),
    ("BigInt", "BigInt", "bigInt", ""),
    ("BigDecimal", "BigDecimal", "bigDecimal", ""),
    ("Date", "Date", "date", "java.util.Date"),
    ("UUID", "UUID", "uuid", "java.util.UUID"),
    ("URI", "URI", "uri", "java.net.URI"),
    ("Byte", "Byte", "byte", ""),
    ("Short", "Short", "short", ""),
    ("Char", "Char", "char", ""),
    ("ref", "Long", "ref", ""),
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
    ("ref", "Long", "ref"),
  )

  def format(baseType: String, v: String) : String = baseType match {
    case "String"     => "\"" + escStr(v) + "\""
    case "Int"        => v
    case "Long"       => v + "L"
    case "Float"      => v + "f"
    case "Double"     => v
    case "Boolean"    => v
    case "BigInt"     => "BigInt(" + v + ")"
    case "BigDecimal" => "BigDecimal(" + v + ")"
    case "Date"       => "new Date(" + v.toLong + ")"
    case "UUID"       => "UUID.fromString(\"" + v + "\")"
    case "URI"        => "new URI(\"" + v + "\")"
    case "Byte"       => v
    case "Short"      => v
    case "Char"       => "'" + v + "'"
  }
}
