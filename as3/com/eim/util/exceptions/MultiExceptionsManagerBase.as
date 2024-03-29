/**
 * Generated by Gas3 v2.2.1 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (MultiExceptionsManager.as).
 */

package com.eim.util.exceptions {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;
    import org.granite.collections.IMap;

    [Bindable]
    public class MultiExceptionsManagerBase implements IExternalizable {

        private var _messages:IMap;
        protected var _multiExceptions:MultiExceptionsException;

        public function set multiExceptions(value:MultiExceptionsException):void {
            _multiExceptions = value;
        }
        public function get multiExceptions():MultiExceptionsException {
            return _multiExceptions;
        }

        public function readExternal(input:IDataInput):void {
            _messages = input.readObject() as IMap;
            _multiExceptions = input.readObject() as MultiExceptionsException;
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_messages);
            output.writeObject(_multiExceptions);
        }
    }
}