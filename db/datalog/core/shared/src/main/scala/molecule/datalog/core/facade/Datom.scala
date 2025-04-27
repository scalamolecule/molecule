package molecule.datalog.core.facade

case class Datom(
  e: Long,
  a: Int,
  v: String, // Only for inspection/debugging
  tx: Long,
  added: Boolean
) {

  override def toString = {
    val e_ = s"$e" + " " * (14 - e.toString.length)
    val a_ = s"$a" + " " * (4 - a.toString.length)
    val v_ = s"$v" + " " * (33 - v.length)
    s"[$e_  $a_  $v_    $tx   $added]"
  }
}