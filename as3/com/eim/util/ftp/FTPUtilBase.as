/**
 * Generated by Gas3 v2.2.1 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (FTPUtil.as).
 */

package com.eim.util.ftp {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;
    import org.granite.collections.IMap;

    [Bindable]
    public class FTPUtilBase implements IExternalizable {

        private var _DEFAULT_TIMEOUT:int;
        private var _connections:IMap;

        public function readExternal(input:IDataInput):void {
            _DEFAULT_TIMEOUT = input.readObject() as int;
            _connections = input.readObject() as IMap;
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_DEFAULT_TIMEOUT);
            output.writeObject(_connections);
        }
    }
}