/**
 * Generated by Gas3 v2.2.1 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (StartZipDecompressEvent.as).
 */

package com.eim.util.msci.event {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;

    [Bindable]
    public class StartZipDecompressEventBase extends ZipEvent {

        protected var _entriesNb:int;

        public function set entriesNb(value:int):void {
            _entriesNb = value;
        }
        public function get entriesNb():int {
            return _entriesNb;
        }

        public override function readExternal(input:IDataInput):void {
            super.readExternal(input);
            _entriesNb = input.readObject() as int;
        }

        public override function writeExternal(output:IDataOutput):void {
            super.writeExternal(output);
            output.writeObject(_entriesNb);
        }
    }
}