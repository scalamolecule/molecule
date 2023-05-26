package molecule.sql.jdbc.transaction

import java.sql.{PreparedStatement => PS}

case class Resolver(
  level: Int,
  refPath: List[String],
  ns: String,
  stmt: String,
  ps: PS,
  populatePS: (PS, Map[(Int, List[String], String), Array[Long]], Int) => Unit = null,
  nestedArities: List[Int] = Nil
)
