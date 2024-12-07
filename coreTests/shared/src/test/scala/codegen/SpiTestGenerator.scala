package codegen


object SpiTestGenerator extends App {

  //  test.aggr.any._Aggr.generate()
  //  test.aggr.number._AggrNum.generate()
  test.crud.update.attrOp.decimal._AttrOpDecimal.generate()
  test.crud.update.attrOp.number._AttrOpInteger.generate()
  test.filter.one.decimal._FilterOneDecimal.generate()
  test.filter.one.number._FilterOneInteger.generate()
  //  test.filter.one.types._FilterOne.generate()
  //  test.filter.set.types._FilterSet.generate()
  //  test.filter.seq.types._FilterSeq.generate()
  //  test.filter.map.types._FilterMap.generate()
}
