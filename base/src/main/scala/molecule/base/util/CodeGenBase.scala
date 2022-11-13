package molecule.base.util


trait CodeGenBase {

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
  
  val javaTypes = Map(
    "String" -> "String",
    "Int" -> "jInteger",
    "Long" -> "jLong",
    "Float" -> "jDouble",
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
}
