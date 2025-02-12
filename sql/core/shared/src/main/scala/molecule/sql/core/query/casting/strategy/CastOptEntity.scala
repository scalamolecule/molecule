package molecule.sql.core.query.casting.strategy

import molecule.sql.core.query.casting.{CastOptRefLeaf_, CastTpl_}

case class CastOptEntity(
  optEntityTuple: CastTuple,
  nextTuple: CastTuple
) extends CastStrategy {

  override def row2tpl: RS => Any = {
    val castOptEntity = (row: RS, _: ParamIndex) =>
      CastOptRefLeaf_.cast(optEntityTuple.casts0, 1)(row)

    CastTpl_.cast(
      castOptEntity +: nextTuple.getCasts,
      optEntityTuple.casts0.length
    )
  }

  override def add(cast: Cast): Unit = nextTuple.add(cast)
  override def replace(cast: Cast): Unit = nextTuple.replace(cast)

  override def render(indent: Int): String = {
    s"""CastOptEntity(
       |${optEntityTuple.render(indent + 1)},
       |${nextTuple.render(indent + 1).mkString(s",\n")}
       |)""".stripMargin
  }

  override def toString: String = render(0)
}