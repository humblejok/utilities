/**
 * Generated by Gas3 v2.2.1 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (Stamp.as).
 */

package com.eim.util.model {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;

    [Bindable]
    public class StampBase implements IExternalizable {

        public var _stamp:Number;

        public function set stamp(value:Number):void {
            _stamp = value;
        }
        public function get stamp():Number {
            return _stamp;
        }

        public function readExternal(input:IDataInput):void {
            _stamp = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_stamp);
        }
    }
}