package codegen


object DatomicGenerator extends App {


  core.action._Actions.generate()
  datomic.action._DatomicActions.generate()
  core.action._Insert.generate()
  datomic.action._DatomicInsert.generate()


  datomic.query.casting._CastIt2Tpl.generate()
  datomic.query.casting._CastNestedBranch.generate()
  datomic.query.casting._CastNestedOptBranch.generate()
  datomic.query.casting._CastNestedOptLeaf.generate()
  datomic.query.casting._CastRow2Tpl.generate()
  datomic.query.casting._NestOpt.generate()

  datomic.query._SortOne.generate()
  datomic.query._SortOneOpt.generate()
  datomic.query._SortOneOptFlat.generate()

  codegen.core._MoleculeImplicits.generate()
  datomic._DatomicMoleculeImplicits.generate()
}
