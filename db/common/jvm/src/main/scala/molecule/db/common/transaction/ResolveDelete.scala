package molecule.db.common.transaction

import scala.annotation.tailrec
import molecule.core.dataModel.*
import molecule.core.error.ModelError

trait ResolveDelete() {

  @tailrec
  final def resolve(
    elements: List[Element],
    topLevel: Boolean,
    tableDelete: TableDelete
  ): TableDelete = {
    elements match {
      case element :: tail => element match {
        case a: Attr                                                          => a match {
          case AttrOneTacID(_, "id", Eq, ids1, _, _, _, _, _, _, _, _, _) =>
            if (!topLevel)
              throw ModelError(
                s"Can only apply entity ids to be deleted to the initial entity."
              )
            resolve(tail, topLevel, tableDelete.add(a))

          case a if a.attr == "id" => throw ModelError(
            s"Mandatory id attribute not allowed in delete molecule (${a.name}).")

          case _: AttrOneTac =>
            resolve(tail, topLevel, tableDelete.add(a))

          case _ => throw ModelError(
            s"Can only filter delete by values applied to tacit card-one attributes (${a.name})."
          )
        }
        case r@Ref(ent, refAttr, ref, relationship, _, reverseRefAttr, owner) =>
          if (topLevel) {
            val joinClause = if (relationship == OneToMany) {
              if (!owner)
                throw ModelError(s"Can't delete parent $ent of children $ref if children are not owned. " +
                  s"If children are owned they would be deleted with the owning parent.")
              s"$ref.${reverseRefAttr.get} = $ent.id"
            } else {
              s"$ref.id = $ent.$refAttr"
            }
            resolve(tail, false, tableDelete.copy(
              joinTable = Some(ref),
              joinClause = Some(joinClause)
            ))
          } else {
            resolve(tail, false, tableDelete.add(r))
          }

        case br@BackRef(backRef, _, _) =>
          resolve(tail, false, tableDelete.add(br))

        case _: SubQuery  => throw ModelError("SubQuery not allowed in delete operations")
        case _: Nested    => throw ModelError(s"Nested data structure not allowed in delete molecule.")
        case _: OptNested => throw ModelError(s"Optional nested data structure not allowed in delete molecule.")
        case _: OptRef    => throw ModelError(s"Optional ref data structure not allowed in delete molecule.")
        case _: OptEntity => throw ModelError(s"Optional entity data structure not allowed in delete molecule.")
      }

      case Nil => tableDelete
    }
  }
}