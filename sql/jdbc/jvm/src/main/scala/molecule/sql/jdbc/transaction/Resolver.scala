package molecule.sql.jdbc.transaction

  import java.sql.{PreparedStatement => PS}

case class Resolver(
  level: Int,
  ns: String,
  selfRef: String,
  stmt: String,
  ps: PS,
  resolve: (PS, Map[(Int, String, String), Array[Long]], Int) => Unit = null,
  nestedArities: List[Int] = Nil
)
