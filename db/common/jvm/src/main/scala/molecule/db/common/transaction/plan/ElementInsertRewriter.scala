package molecule.db.common.transaction.plan

import molecule.core.dataModel.*

/**
 * Reorder a sequence of DataModel Elements into a FK-safe insertion order, using Element types only.
 *
 * Semantics:
 * - ManyToOne (current entity holds FK to target):
 *   Emit target subtree first, then the Ref, then current entity's attributes.
 * - OneToMany (reverse traversal view where current is parent):
 *   Emit current entity's attributes first, then the Ref, then the child subtree.
 *
 * BackRefs:
 * - Treated as structural delimiters marking return to a previous entity (branching).
 * - Not included in the emitted output.
 *
 * Nested/OptNested/OptRef:
 * - Recursively transformed with the same ordering rules, then flattened into the output.
 *
 * Product index (tuple position):
 * - Attr.coord is preserved; no extra index tracking is needed.
 */
object ElementInsertRewriter {

  sealed trait ReorderError extends Product with Serializable
  final case class ParseError(msg: String) extends ReorderError

  /**
   * Public entry: reorder a flat/nested Element list into insertion order.
   * Starts a new entity branch at each encountered entity occurrence (Attr, Ref, or Nested).
   */
  def reorderForInsert(elements: List[Element]): Either[ReorderError, List[Element]] = {
    def loop(xs: List[Element], accRev: List[Element]): Either[ReorderError, List[Element]] =
      xs match {
        case Nil => Right(accRev.reverse)

        case (_: BackRef) :: tail =>
          // Top-level BackRefs are purely structural; skip.
          loop(tail, accRev)

        case (a: Attr) :: _ =>
          val (out, rest) = consumeEntity(a.ent, xs)
          loop(rest, out.reverse ::: accRev)

        case (r: Ref) :: _ =>
          val (out, rest) = consumeEntity(r.ent, xs)
          loop(rest, out.reverse ::: accRev)

        case (n: Nested) :: _ =>
          val (out, rest) = consumeEntity(n.ref.ent, xs)
          loop(rest, out.reverse ::: accRev)

        case (on: OptNested) :: _ =>
          val (out, rest) = consumeEntity(on.ref.ent, xs)
          loop(rest, out.reverse ::: accRev)

        case (or: OptRef) :: _ =>
          val (out, rest) = consumeEntity(or.ref.ent, xs)
          loop(rest, out.reverse ::: accRev)

        case other :: _ =>
          Left(ParseError(s"Unexpected element in insert model: $other"))
      }

    loop(elements, Nil)
  }

  /**
   * Consume one entity branch starting at the first element for `ent` in the list.
   *
   * Stops consumption when:
   * - BackRef is encountered (end of this branch; BackRef is consumed), or
   * - An element belonging to a different entity boundary is reached (element is not consumed).
   *
   * Returns:
   * - (emittedElementsForThisEntity, remainingElementsNotConsumed)
   */
  private def consumeEntity(ent: String, xs: List[Element]): (List[Element], List[Element]) = {
    // Accumulate three regions immutably in reverse order for efficiency:
    // beforeRev: ManyToOne children outputs (and their Refs), to appear before current attrs
    // attrsRev : Current entity attributes
    // afterRev : OneToMany children outputs (prefixed by their Refs), to appear after current attrs
    def go(ys: List[Element],
           beforeRev: List[Element],
           attrsRev: List[Attr],
           afterRev: List[Element]): (List[Element], List[Element]) = ys match {

      case Nil =>
        val emitted = beforeRev.reverse ::: attrsRev.reverse ::: afterRev.reverse
        (emitted, Nil)

      case (_: BackRef) :: tail =>
        val emitted = beforeRev.reverse ::: attrsRev.reverse ::: afterRev.reverse
        (emitted, tail)

      case (a: Attr) :: tail if a.ent == ent =>
        go(tail, beforeRev, a :: attrsRev, afterRev)

      case (r: Ref) :: tail if r.ent == ent =>
        // Child subtree follows in the flat list
        val (childOut, rest) = consumeEntity(r.ref, tail)
        r.relationship match {
          case ManyToOne =>
            // Emit child first, then the Ref, before current attrs
            val produced = childOut ::: List(r)
            go(rest, produced.reverse ::: beforeRev, attrsRev, afterRev)

          case OneToMany =>
            // Emit current attrs first, then the Ref, then child
            val produced = r :: childOut
            go(rest, beforeRev, attrsRev, produced.reverse ::: afterRev)
        }

      case (n: Nested) :: tail if n.ref.ent == ent =>
        // Recurse into nested child elements and flatten
        reorderForInsert(n.elements) match {
          case Left(err) => throw new IllegalStateException(err.toString)
          case Right(childOut) =>
            n.ref.relationship match {
              case ManyToOne =>
                val produced = childOut ::: List(n.ref)
                go(tail, produced.reverse ::: beforeRev, attrsRev, afterRev)
              case OneToMany =>
                val produced = n.ref :: childOut
                go(tail, beforeRev, attrsRev, produced.reverse ::: afterRev)
            }
        }

      case (on: OptNested) :: tail if on.ref.ent == ent =>
        reorderForInsert(on.elements) match {
          case Left(err) => throw new IllegalStateException(err.toString)
          case Right(childOut) =>
            on.ref.relationship match {
              case ManyToOne =>
                val produced = childOut ::: List(on.ref)
                go(tail, produced.reverse ::: beforeRev, attrsRev, afterRev)
              case OneToMany =>
                val produced = on.ref :: childOut
                go(tail, beforeRev, attrsRev, produced.reverse ::: afterRev)
            }
        }

      case (or: OptRef) :: tail if or.ref.ent == ent =>
        reorderForInsert(or.elements) match {
          case Left(err) => throw new IllegalStateException(err.toString)
          case Right(childOut) =>
            or.ref.relationship match {
              case ManyToOne =>
                val produced = childOut ::: List(or.ref)
                go(tail, produced.reverse ::: beforeRev, attrsRev, afterRev)
              case OneToMany =>
                val produced = or.ref :: childOut
                go(tail, beforeRev, attrsRev, produced.reverse ::: afterRev)
            }
        }

      // Boundary: element of a different entity or unexpected structure -> stop here
      case other :: _ =>
        val emitted = beforeRev.reverse ::: attrsRev.reverse ::: afterRev.reverse
        (emitted, ys)
    }

    go(xs, Nil, Nil, Nil)
  }
}
