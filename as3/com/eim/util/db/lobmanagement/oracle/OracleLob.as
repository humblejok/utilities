/**
 * Generated by Gas3 v2.2.0 (Granite Data Services).
 *
 * NOTE: this file is only generated if it does not exist. You may safely put
 * your custom code here.
 */

package com.eim.util.db.lobmanagement.oracle {

    import java.sql.Blob;
    import java.sql.Clob;

    [Bindable]
    [RemoteClass(alias="com.eim.util.db.lobmanagement.oracle.OracleLob")]
    public class OracleLob extends OracleLobBase {

        public override function get emptyBlob():Blob {
            // TODO: Gas3 default generated getter.
            return null;
        }

        public override function get emptyClob():Clob {
            // TODO: Gas3 default generated getter.
            return null;
        }
    }
}