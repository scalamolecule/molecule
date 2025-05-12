package codegen.core

object CoreGenerator extends App {

    codegen.core.api._Molecules.generate()
    codegen.core.action._Insert.generate()
    codegen.core.marshalling.deserialize._UnpickleTpl.generate()
    codegen.core.marshalling.serialize._PickleTpl.generate()
    codegen.core.transaction._InsertResolvers.generate()
    codegen.core.transaction._InsertValidators.generate()
    codegen.core.validation.insert._InsertValidationResolvers.generate()


    codegen.core.api.expression._ExprAttr.generate()

    codegen.core.api.expression._ExprBArMan.generate()
    codegen.core.api.expression._ExprBArOpt.generate()
    codegen.core.api.expression._ExprBArTac.generate()

    codegen.core.api.expression._ExprOneMan.generate()
    codegen.core.api.expression._ExprOneOpt.generate()
    codegen.core.api.expression._ExprOneTac.generate()

    codegen.core.api.expression._ExprSetMan.generate()
    codegen.core.api.expression._ExprSetOpt.generate()
    codegen.core.api.expression._ExprSetTac.generate()

    codegen.core.api.expression._ExprSeqMan.generate()
    codegen.core.api.expression._ExprSeqOpt.generate()
    codegen.core.api.expression._ExprSeqTac.generate()

    codegen.core.api.expression._ExprMapMan.generate()
    codegen.core.api.expression._ExprMapOpt.generate()
    codegen.core.api.expression._ExprMapTac.generate()

    codegen.core.api._Aggregates.generate()
    codegen.core.api._ModelOps.generate()
    codegen.core.api._Nested.generate()
    codegen.core.api._OptRef.generate()
    codegen.core.api._SortAttrs.generate()

    codegen.core.ast._Model.generate()
    codegen.core.ops._ModelTransformations.generate()
}
