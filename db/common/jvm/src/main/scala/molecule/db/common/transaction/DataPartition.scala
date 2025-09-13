package molecule.db.common.transaction

case class DataPartition(tuples: Seq[Product], childCounts: Vector[Int]) {
  override def toString: String = {
    val data = if (tuples.length < 10) tuples.mkString("\n    ") else
      tuples.take(10).mkString("\n    ") + s"\n    ...(${tuples.length - 10} more)"
    s"""........................................
       |DataPartition(
       |  tuples:
       |    $tuples
       |  childCounts:
       |    $childCounts
       |)
       |""".stripMargin
  }
}
