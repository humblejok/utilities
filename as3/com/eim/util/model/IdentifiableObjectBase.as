/**
 * Generated by Gas3 v2.2.1 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (IdentifiableObject.as).
 */

package com.eim.util.model {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;

    [Bindable]
    public class IdentifiableObjectBase extends SerializableObject {

        public var _id:Number;

        public function set id(value:Number):void {
            _id = value;
        }
        public function get id():Number {
            return _id;
        }

        public override function readExternal(input:IDataInput):void {
            super.readExternal(input);
            _id = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
        }

        public override function writeExternal(output:IDataOutput):void {
            super.writeExternal(output);
            output.writeObject(_id);
        }
    }
}