package molecule.sql.sqlite.query

import molecule.sql.core.query.{LambdasOne, SqlQueryBase}

trait LambdasOne_sqlite extends LambdasOne { self: SqlQueryBase =>

  // SQlite data conversions
  override lazy val sql2oneId        : (RS, Int) => Long       = (row: RS, paramIndex: Int) => row.getLong(paramIndex)
  override lazy val sql2oneBoolean   : (RS, Int) => Boolean    = (row: RS, paramIndex: Int) => row.getInt(paramIndex) == 1
  override lazy val sql2oneBigInt    : (RS, Int) => BigInt     = (row: RS, paramIndex: Int) => BigInt(row.getString(paramIndex))
  override lazy val sql2oneBigDecimal: (RS, Int) => BigDecimal = (row: RS, paramIndex: Int) => BigDecimal(row.getString(paramIndex))
  override lazy val sql2oneByte      : (RS, Int) => Byte       = (row: RS, paramIndex: Int) => row.getInt(paramIndex).toByte
  override lazy val sql2oneShort     : (RS, Int) => Short      = (row: RS, paramIndex: Int) => row.getInt(paramIndex).toShort
}