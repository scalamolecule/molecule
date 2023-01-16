package molecule.db.datomic.marshalling

import molecule.core.marshalling.MoleculeRpcServer

object DatomicRpcServer extends MoleculeRpcServer(DatomicRpcJVM) with App
