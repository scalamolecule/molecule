package molecule.document.mongodb.query.casting

import org.bson.BsonDocument


sealed trait Cast {
  def render(indent: Int): String
}

case class CastAttr(field: String, cast: BsonDocument => Any) extends Cast {
  override def toString = s"CastAttr($field, <casts>)"
  override def render(indent: Int): String = " " * indent + toString
}

case class CastNs(refAttr: String, casts: List[Cast], nested: Boolean = false) extends Cast {
  override def toString = render(0)
  override def render(indent: Int): String = {
    val pad      = " " * indent
    val children = casts.map(c => s"\n$pad" + c.render(indent + 1)).mkString(",")
    s"""${pad}CastNs($refAttr, List($children), $nested)"""
  }
}
