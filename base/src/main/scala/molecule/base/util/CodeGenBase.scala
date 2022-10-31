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
  val numTypes = Seq(
    "Int",
    "Long",
    "Float",
    "Double",
    "BigInt",
    "BigDecimal",
    "Byte",
    "Short",
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

  val tpeVarImp = Map(
    "String" -> ("string", ""),
    "Int" -> ("int", ""),
    "Long" -> ("long", ""),
    "Float" -> ("float", ""),
    "Double" -> ("double", ""),
    "Boolean" -> ("boolean", ""),
    "BigInt" -> ("bigInt", ""),
    "BigDecimal" -> ("bigDecimal", ""),
    "Date" -> ("date", "java.util.Date"),
    "UUID" -> ("uuid", "java.util.UUID"),
    "URI" -> ("uri", "java.net.URI"),
    "Byte" -> ("byte", ""),
    "Short" -> ("short", ""),
    "Char" -> ("char", ""),
  )
  val tpeVarImpNum = Map(
    "Int" -> ("int", ""),
    "Long" -> ("long", ""),
    "Float" -> ("float", ""),
    "Double" -> ("double", ""),
    "BigInt" -> ("bigInt", ""),
    "BigDecimal" -> ("bigDecimal", ""),
    "Byte" -> ("byte", ""),
    "Short" -> ("short", ""),
  )
}
