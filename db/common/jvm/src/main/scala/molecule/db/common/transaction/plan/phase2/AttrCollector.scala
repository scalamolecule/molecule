package molecule.db.common.transaction.plan.phase2

import molecule.core.dataModel.*

object AttrCollector {
  def collect(elements: List[Element]): List[Attr] = {
    val buf = scala.collection.mutable.ListBuffer.empty[Attr]

    def collectInner(els: List[Element]): Unit =
      els.foreach {
        case a: Attr            => buf += a
        case Ref(_, _, _, _, _, _, _) => () // Refs themselves carry no tuple values
        case OptRef(_, inner)   => collectInner(inner)
        case Nested(_, inner)   => collectInner(inner)
        case OptNested(_, inner)=> collectInner(inner)
        case OptEntity(attrs)   => attrs.foreach(buf += _)
        case BackRef(_, _, _)   => ()
        case _                  => ()
      }

    collectInner(elements)
    buf.toList
  }
}
