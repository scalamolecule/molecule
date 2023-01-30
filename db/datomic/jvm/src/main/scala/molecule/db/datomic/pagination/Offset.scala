package molecule.db.datomic.pagination

trait Offset {

  protected def getFromUntil(
    totalCount: Int,
    limit: Option[Int],
    offset: Option[Int]
  ): Option[(Int, Int)] = {
    (offset, limit) match {
      case (None, None)                => None
      case (None, Some(l)) if l > 0    => Some((0, l.min(totalCount)))
      case (None, Some(l))             => Some(((totalCount + l).max(0), totalCount))
      case (Some(o), None) if o > 0    => Some((o, totalCount))
      case (Some(o), None)             => Some((-o.max(0), totalCount))
      case (Some(o), Some(l)) if l > 0 => Some((o, (o + l).min(totalCount)))
      case (Some(o), Some(l))          => Some(((totalCount + o + l).max(0), (totalCount + o).max(0)))
    }
  }
}
