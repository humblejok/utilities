/**
 * Generated by Gas3 v2.2.1 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (AbstractDAO.as).
 */

package com.eim.util.db {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;
    import java.sql.Connection;

    [Bindable]
    public class AbstractDAOBase implements IExternalizable {

        private var _connection:Connection;

        public function set connection(value:Connection):void {
            _connection = value;
        }
        public function get connection():Connection {
            return _connection;
        }

        public function readExternal(input:IDataInput):void {
            _connection = input.readObject() as Connection;
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_connection);
        }
    }
}