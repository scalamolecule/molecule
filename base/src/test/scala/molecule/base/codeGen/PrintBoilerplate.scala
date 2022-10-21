package molecule.base.codeGen

import molecule.base.CodeGenTesting
import utest._

object PrintBoilerplate extends CodeGenTesting {


  override def tests: Tests = Tests {

    "boilerplate" - {

      //      dataModel.coreTest.nsBase("Ns")
      //      dataModel.coreTest.nsArity("Ns", 0)
      //      dataModel.coreTest.nsArity("Ns", 0)
      //      dataModel.coreTest.nsArity("Ns", 0)
      //      dataModel.coreTest.nsArity("Ns", 21)
      //      dataModel.coreTest.nsArity("Ns", 22)

      //      dataModel.coreTest.nsBase("Ref3")
      dataModel.coreTest.nsBase("Ref2")
      dataModel.coreTest.nsArity("Ref2", 0)
      dataModel.coreTest.nsArity("Ref2", 1)
      dataModel.coreTest.nsArity("Ref2", 2)
      dataModel.coreTest.nsArity("Ref2", 3)
      //            dataModel.coreTest.nsArity("Ref2", 4)

      //      dataModel.coreTest.nsBase("Ref4")
      //      dataModel.coreTest.nsArity("Ref4", 0)
      //      dataModel.coreTest.nsArity("Ref4", 1)
      //      dataModel.coreTest.nsArity("Ref4", 21)
      //      dataModel.coreTest.nsArity("Ref4", 22)

      //      dataModel.datom.nsBase
      //      dataModel.datom.nsArity(0)
      //      dataModel.datom.nsArity(1)
      //      dataModel.datom.nsArity(21)
      //      dataModel.datom.nsArity(22)

      //      dataModel.schema.nsBase
      //      dataModel.schema.nsArity(0)
      //      dataModel.schema.nsArity(1)
      //      dataModel.schema.nsArity(21)
      //      dataModel.schema.nsArity(22)

      //      dataModel.aevt.nsBase
      //      dataModel.aevt.nsArity(0)
      //      dataModel.aevt.nsArity(1)
      //      dataModel.aevt.nsArity(6)
      //      dataModel.aevt.nsArity(7)


      /*


      final def Ref1 : OneRef [Ns_[p0], Ref1_[p0]] with Ref1_0_0[o0, p0] = ???
      final def Refs1: ManyRef[Ns_[p0], Ref1_[p0]] with Ref1_0_0[o0, p0] with Nested_0_0[o0, p0, Ns_1_0] = ???

    //  final def Ref1 : OneRef [Ns_[p0], Ref1_[p0]] with Ref1_0_1[o0, p0, Ns__Ref1 , Init] = ???
    //  final def Refs1: ManyRef[Ns_[p0], Ref1_[p0]] with Ref1_0_1[o0, p0, Ns__Refs1, Init] with Nested_0_1[o0, p0, Ns__Refs1, Ns_1_0] = ???
       */
    }
  }
}
