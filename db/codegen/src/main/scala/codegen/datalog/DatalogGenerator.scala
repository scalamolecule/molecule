package codegen.datalog

object DatalogGenerator extends App {

  codegen.datalog.core.query.casting._CastNestedBranch.generate()
  codegen.datalog.core.query.casting._CastOptNestedBranch.generate()
  codegen.datalog.core.query.casting._CastOptNestedLeaf.generate()
  codegen.datalog.core.query.casting._CastOptRefBranch.generate()
  codegen.datalog.core.query.casting._CastOptRefLeaf.generate()
  codegen.datalog.core.query.casting._CastRow2AnyTpl.generate()
  codegen.datalog.core.query._SortOne.generate()
  codegen.datalog.core.query._SortOneOpt.generate()
  codegen.datalog.core.query._SortOneOptFlat.generate()
}
