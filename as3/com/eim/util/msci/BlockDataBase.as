/**
 * Generated by Gas3 v2.2.1 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (BlockData.as).
 */

package com.eim.util.msci {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;

    [Bindable]
    public class BlockDataBase implements IExternalizable {

        public var _alias:String;
        public var _decimal:int;
        public var _id:int;
        public var _name:String;
        public var _size:int;
        public var _type:String;

        public function set alias(value:String):void {
            _alias = value;
        }
        public function get alias():String {
            return _alias;
        }

        public function set decimal(value:int):void {
            _decimal = value;
        }
        public function get decimal():int {
            return _decimal;
        }

        public function set id(value:int):void {
            _id = value;
        }
        public function get id():int {
            return _id;
        }

        public function set name(value:String):void {
            _name = value;
        }
        public function get name():String {
            return _name;
        }

        public function set size(value:int):void {
            _size = value;
        }
        public function get size():int {
            return _size;
        }

        public function set type(value:String):void {
            _type = value;
        }
        public function get type():String {
            return _type;
        }

        public function readExternal(input:IDataInput):void {
            _alias = input.readObject() as String;
            _decimal = input.readObject() as int;
            _id = input.readObject() as int;
            _name = input.readObject() as String;
            _size = input.readObject() as int;
            _type = input.readObject() as String;
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_alias);
            output.writeObject(_decimal);
            output.writeObject(_id);
            output.writeObject(_name);
            output.writeObject(_size);
            output.writeObject(_type);
        }
    }
}