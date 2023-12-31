package molecule.document.mongodb.dataModel

import molecule.DataModel

object Types2 extends DataModel(5) {

  trait Ns {
    val i  = oneInt
    val ii = setInt
    val s  = oneString
    val u  = oneInt.unique

    // Cardinality one
    val string         = oneString
    val int            = oneInt
    val long           = oneLong
    val float          = oneFloat
    val double         = oneDouble
    val boolean        = oneBoolean
    val bigInt         = oneBigInt
    val bigDecimal     = oneBigDecimal
    val date           = oneDate
    val duration       = oneDuration
    val instant        = oneInstant
    val localDate      = oneLocalDate
    val localTime      = oneLocalTime
    val localDateTime  = oneLocalDateTime
    val offsetTime     = oneOffsetTime
    val offsetDateTime = oneOffsetDateTime
    val zonedDateTime  = oneZonedDateTime
    val uuid           = oneUUID
    val uri            = oneURI
    val byte           = oneByte
    val short          = oneShort
    val char           = oneChar
    val ref            = one[Ref].owner
    val other          = one[Other].owner

    // Cardinality many (Set)
    val strings         = setString
    val ints            = setInt
    val longs           = setLong
    val floats          = setFloat
    val doubles         = setDouble
    val booleans        = setBoolean
    val bigInts         = setBigInt
    val bigDecimals     = setBigDecimal
    val dates           = setDate
    val durations       = setDuration
    val instants        = setInstant
    val localDates      = setLocalDate
    val localTimes      = setLocalTime
    val localDateTimes  = setLocalDateTime
    val offsetTimes     = setOffsetTime
    val offsetDateTimes = setOffsetDateTime
    val zonedDateTimes  = setZonedDateTime
    val uuids           = setUUID
    val uris            = setURI
    val bytes           = setByte
    val shorts          = setShort
    val chars           = setChar
    val refs            = many[Ref].owner
  }

  trait Ref {
    val i = oneInt
    val s = oneString

    val string         = oneString
    val int            = oneInt
    val long           = oneLong
    val float          = oneFloat
    val double         = oneDouble
    val boolean        = oneBoolean
    val bigInt         = oneBigInt
    val bigDecimal     = oneBigDecimal
    val date           = oneDate
    val duration       = oneDuration
    val instant        = oneInstant
    val localDate      = oneLocalDate
    val localTime      = oneLocalTime
    val localDateTime  = oneLocalDateTime
    val offsetTime     = oneOffsetTime
    val offsetDateTime = oneOffsetDateTime
    val zonedDateTime  = oneZonedDateTime
    val uuid           = oneUUID
    val uri            = oneURI
    val byte           = oneByte
    val short          = oneShort
    val char           = oneChar

    val ii = setInt
    val ss = setString

    val strings         = setString
    val ints            = setInt
    val longs           = setLong
    val floats          = setFloat
    val doubles         = setDouble
    val booleans        = setBoolean
    val bigInts         = setBigInt
    val bigDecimals     = setBigDecimal
    val dates           = setDate
    val durations       = setDuration
    val instants        = setInstant
    val localDates      = setLocalDate
    val localTimes      = setLocalTime
    val localDateTimes  = setLocalDateTime
    val offsetTimes     = setOffsetTime
    val offsetDateTimes = setOffsetDateTime
    val zonedDateTimes  = setZonedDateTime
    val uuids           = setUUID
    val uris            = setURI
    val bytes           = setByte
    val shorts          = setShort
    val chars           = setChar

    val nss = many[Ns].owner
  }

  trait Other {
    val i = oneInt
    val s = oneString

    val ii = setInt
    val ss = setString

    // 'select' is a reserved keyword in all sql databases.
    // Test if it correctly resolves to 'select_'
    val select = oneInt
  }
}